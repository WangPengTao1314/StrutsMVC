/**
 * @prjName:喜临门营销平台
 * @fileName:Changeadvcorder_Frame
 * @author ghx
 * @time   2014-05-20 15:14:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
	    addRow();
	});
	
	$("body").dblclick(function(){
	    addRow();
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		
//		//检查是否仅剩一条记录
//		if(checkBox.length==1){
//			parent.showErrorMsg("唯一变更明细记录，不可删除！");
//			return;
//		}
		
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
		if("" == ids){
			checkBox.parent().parent().remove();
		}else{
			parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
		$("#selectParams").val("DEL_FLAG=0 and FROM_BILL_DTL_ID="+parent.topcontent.document.getElementById("FROM_BILL_ID").value);
	});
	
	$("#save").click(function(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
//		//检查是否仅剩一条记录
//		if(checkBox.length==0){
//			parent.showErrorMsg("请添加变更明细！");
//			return;
//		}			
		
		//订货数量校验
		var msg = $("#errorMsg").val();
		if(msg != ""){
			parent.showErrorMsg(msg);
			return;
		}
		if(!checkNum()){
			return;
		}
		//检查变更情况,并且保存
		var ids = "";
		$("input[name='ADVC_ORDER_DTL_ID']").each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
		
		if(ids == ""){
			allSave();
		}
		else{
			$.ajax({
				url: "changeadvcorder.shtml?action=queryAdvcOrderChildList",
				type:"POST",
				dataType:"text",
				data:{"ADVC_ORDER_DTL_IDS":ids},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success==true){
						var data = jsonResult.data;				 	
					 	//compareAlterInfo(data);
					 	
					 	var msg = $("#errorMsg").val();
						if(msg != ""){
							parent.showErrorMsg(msg);
							$("#errorMsg").val("");
							return;
						}
					 	
					 	//当前所在页面有2种可能，列表展示页面，编辑页面。
						var actionType = getActionType();
						if("list" == actionType){
							//查找当前是否有选中的记录
							var checkBox = $("#jsontb tr:gt(0) input:checked");
							if(checkBox.length<1){
								parent.showErrorMsg("请至少选择一条记录");
								return;
							}
							
							//页面 查重
							if(dataColumnsValidation("jsontb",['PRD_NO'],"red")){
								childSave();				
							}
							
						}else{
							//编辑页面，子表没有选中记录也可以提交，故不需要校验。
							if(dataColumnsValidation("jsontb",['PRD_NO'],"red")){
								allSave();
							}
						}
					}else{
						showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});	
		}
		
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	
});

//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = getSelRowId();	
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		if(dataColumnsValidation("jsontb",['PRD_NO'],"red")){//页面查重
			childData = multiPackJsonData();
		}else{
			return;
		}
	}
		
	$.ajax({
		url: "changeadvcorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_ORDER_CHANGE_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","changeadvcorder.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	
	var selRowId = getSelRowId();
	var oldId = parent.topcontent.document.getElementById("FROM_BILL_ID").value;
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "changeadvcorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_ORDER_CHANGE_ID":selRowId,"ADVC_ORDER_ID":oldId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.parent.topcontent.pageForm.submit();
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "changeadvcorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_CHANGE_DTL_IDS":ids,"ADVC_ORDER_CHANGE_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("changeadvcorder.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}

// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	var SPCL_TECH_FLAG;
	if(arrs[15]==null||arrs[15]==""){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'   value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input  type='checkbox' style='height:12px;valign:middle' value='"+arrs[0]+"'/></td>")
            .append('<input type="hidden" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/>')
            .append('<td nowrap align="left" name="sss"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text" size="8" mustinput="true"   READONLY  value="'+arrs[2]+'"/>&nbsp;' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="hidden"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[5]+'"/>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"   readonly  size="3"   maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string" size="3"  readonly      maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价" onkeyup="countPRICE_AMOUNT('+rownum+')" type="text"  mustinput="true" size="5" value="'+arrs[8]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率" onkeyup="countPRICE_AMOUNT('+rownum+')" type="text" size="3"    mustinput="true"    value="'+arrs[9]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价" onkeyup="countPAYABLE_AMOUNT('+rownum+')" type="text" size="5"    mustinput="true" value="'+arrs[10]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="订货数量" onkeyup="checkAndCount('+rownum+');"  type="text"    size="3"   mustinput="true"     maxlength="22"  value="'+arrs[11]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;<input type="hidden" id="BEFORE_ORDER_NUM'+rownum+'" name="BEFORE_ORDER_NUM'+rownum+'"  value="'+arrs[11]+'" /></td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" name="PAYABLE_AMOUNT"  autocheck="true" label="应收金额"  type="text" READONLY size="5" onkeyup="countDECT_PRICE('+rownum+')"  inputtype="float"      valueType="12,2"   value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_RECV_DATE" id="ORDER_RECV_DATE'+rownum+'" name="ORDER_RECV_DATE"  autocheck="true" label="交货日期" onclick="SelectDate();" size="8"  readonly  type="text" inputtype="date"   value="'+arrs[19]+'" />&nbsp;' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE'+rownum+');"></td>')
            .append('<td nowrap align="left"><input  json="FREEZE_NUM" id="FREEZE_NUM'+rownum+'" name="FREEZE_NUM'+rownum+'"  autocheck="true" label="冻结数量" readonly type="text" size="3"  inputtype="float"  valueType="8,2" onkeyup="countFREEZE('+rownum+')"  maxlength="22"  value="'+arrs[20]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="变更原因"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<input type="hidden" json="ADVC_ORDER_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID'+rownum+'"  label="来源货品信息"  value="'+arrs[14]+'"/>')
            .append('<input   type="hidden" json="ADVC_ORDER_DTL_ID" id="ADVC_ORDER_DTL_ID'+rownum+'" name="ADVC_ORDER_DTL_ID"   value="'+arrs[0]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[15]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺单标记" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="ADVC_ORDER_CHANGE_DTL_ID" id="ADVC_ORDER_CHANGE_DTL_ID'+rownum+'" name="ADVC_ORDER_CHANGE_DTL_ID"  label="特殊工艺单标记" type="text" value="'+arrs[17]+'"/>')
            .append('<input  json="ADVC_ORDER_CHANGE_ID" id="ADVC_ORDER_CHANGE_ID'+rownum+'" name="ADVC_ORDER_CHANGE_ID"  label="特殊工艺单标记" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="OLDPRICE" id="OLDPRICE'+rownum+'" name="OLDPRICE"  label="原应收金额" type="hidden" value="'+arrs[12]+'"/>')
              ;
	//uploadFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function getFROM_BILL_ID(){
	return parent.topcontent.document.getElementById("FROM_BILL_ID").value;
}
//货品通用选取
    function selcPrd(rownum){
    	var FROM_BILL_ID=getFROM_BILL_ID();    	
    	if(FROM_BILL_ID==null || FROM_BILL_ID == ""){
    		alert("请选择来源编号");
    		return false;
    	}else{
//    		//把float和int类型的默认值改为空
//    		$("#PRICE"+rownum).val("");
//    		$("#DECT_RATE"+rownum).val("");
//    		$("#DECT_PRICE"+rownum).val("");
//    		$("#ORDER_NUM"+rownum).val("");
//    		$("#PAYABLE_AMOUNT"+rownum).val("");
//    		$("#FREEZE_NUM"+rownum).val("");
    		//获取编辑和列表里的来源单据id设置通用选取条件
    		$("#selectParams").val("DEL_FLAG=0 and ADVC_ORDER_ID='"+FROM_BILL_ID+"'");
    		var obj=selCommon("BS_39", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]",
    				"PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",ORDER_NUM"+rownum + ",BEFORE_ORDER_NUM" + rownum +",PAYABLE_AMOUNT"+rownum+",FROM_BILL_DTL_ID"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum +",ORDER_RECV_DATE"+rownum +",ADVC_ORDER_DTL_ID"+rownum+",OLDPRICE"+rownum+",FREEZE_NUM"+rownum, 
    				"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,ORDER_NUM,PAYABLE_AMOUNT,ADVC_ORDER_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,ORDER_RECV_DATE,ADVC_ORDER_DTL_ID,PAYABLE_AMOUNT,FREEZE_NUM", "selectParams");
//	        rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,BEFORE_ORDER_NUM,PAYABLE_AMOUNT,ADVC_ORDER_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,ORDER_RECV_DATE,PAYABLE_AMOUNT,FREEZE_NUM",rownum);
	        var SPCL_TECH_FLAG=$("#SPCL_TECH_FLAG"+rownum).val();
	        if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""){
				$("#SPECIAL_FLAG"+rownum).html('无');
			}else{
				$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span><input   value='查看' onclick='url("+rownum+")' class='btn'  type='button' />");
			}
    	}
	}
 //当键盘按键松开时触发事件
	//填写订货单价和订货数量计算应收金额
	 function countPAYABLE_AMOUNT(i){
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//订货单价
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//订货单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var PAYABLE_AMOUNT = Math.round((isNaN(ORDER_NUM*DECT_PRICE)?0:ORDER_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
		var DECT_RATE = Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
		$("#DECT_RATE"+i).val(DECT_RATE);
		countTotalMoney();
	}
	//计算输入订货金额计算折后单价
	function countDECT_PRICE(i){
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var temp_PAYABLE_AMOUNT=$("#PAYABLE_AMOUNT"+i).val();//应收金额
		var PAYABLE_AMOUNT=isNaN(temp_PAYABLE_AMOUNT)?0:parseFloat(temp_PAYABLE_AMOUNT);//应收金额
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		if(0==ORDER_NUM){
			$("#DECT_PRICE"+i).val(0);
			$("#DECT_RATE"+i).val(0);
			return;
		}
		var DECT_PRICE = Math.round((isNaN(PAYABLE_AMOUNT/ORDER_NUM)?0:PAYABLE_AMOUNT/ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
		var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		$("#DECT_RATE"+i).val(DECT_RATE);
	}
	//填写单价和折扣率计算折后单价和应收金额
	 function countPRICE_AMOUNT(i){				
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
		var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
		
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		
		var PAYABLE_AMOUNT = Math.round((isNaN(ORDER_NUM*DECT_PRICE)?0:ORDER_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
		countTotalMoney();
	}
	
	//检查订货数量
	function checkOrderNum(i){
		var ADVC_ORDER_DTL_ID = $("#ADVC_ORDER_DTL_ID"+i).val();
		$.ajax({
			url: "changeadvcorder.shtml?action=queryAdvcOrderChildInfo",
			type:"POST",
			dataType:"text",
			data:{"ADVC_ORDER_DTL_ID":ADVC_ORDER_DTL_ID},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success==true){
					var data = jsonResult.data;				 	
				 	compareOrderNum(i,data.ORDER_NUM);
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
		countTotalMoney();
	}
	//修改冻结数量时
	function countFREEZE(id){
		var ORDER_NUM=$("#ORDER_NUM"+id).val();
		if(""==ORDER_NUM||null==ORDER_NUM){
			ORDER_NUM=0
		}
		var FREEZE_NUM=$("#FREEZE_NUM"+id).val();
		if(""==FREEZE_NUM||null==FREEZE_NUM){
			FREEZE_NUM=0
		}
		ORDER_NUM=isNaN(ORDER_NUM)?0:parseFloat(ORDER_NUM);
		FREEZE_NUM=isNaN(FREEZE_NUM)?0:parseFloat(FREEZE_NUM);
		if(FREEZE_NUM>ORDER_NUM){
			msg = "冻结数量不能大于订货数量！";
			$("#FREEZE_NUM"+id).val("0");
			parent.showErrorMsg(msg);
		}
	}
	//比较订货数量
	function compareOrderNum(i,before_temp){
		var temp=$("#ORDER_NUM"+i).val();//订货数量
		var curr_ORDER_NUM=isNaN(temp)?0:parseFloat(temp);//订货数量
		var before_ORDER_NUM = isNaN(before_temp)?0:parseFloat(before_temp);//原订货数量
		
		var msg = "";
		if(curr_ORDER_NUM > before_ORDER_NUM){
			msg = "订货数量不能大于变更前的数量！";			
			parent.showErrorMsg(msg);
		}				
					
		$("#errorMsg").val(msg);
	}
	
	
	//检查订货数量并且计算应收金额、
	function checkAndCount(i){
		//验证变更数量不能大于订货数量
		checkOrderNum(i);
		
		if($("#errorMsg").val()== ''){
			countPAYABLE_AMOUNT(i);
		}
		countTotalMoney();
	}
	
	//下帧改变应收金额时算出总金额赋值到上贞应收总金额
	function countTotalMoney(){
		var actionType = getActionType();
		if("edit" == actionType){
			var inputs = $("input[name='OLDPRICE']");
			var total=0;
			inputs.each(function(){
				var OLDPRICE = $(this).val();
				OLDPRICE=isNaN(OLDPRICE)?0:parseFloat(OLDPRICE);
				var check=$(this).parent().find("input[type='checkbox']").prop("checked");
				var ADVC_ORDER_CHANGE_DTL_ID=$(this).parent().find("input[name='ADVC_ORDER_CHANGE_DTL_ID']").val();
				var PAYABLE_AMOUNT=$(this).parent().find("input[name='PAYABLE_AMOUNT']").val();
				PAYABLE_AMOUNT=isNaN(PAYABLE_AMOUNT)?0:parseFloat(PAYABLE_AMOUNT);
				var v=Math.round((isNaN(PAYABLE_AMOUNT-OLDPRICE)?0:PAYABLE_AMOUNT-OLDPRICE)*100)/100;
				if(""!=ADVC_ORDER_CHANGE_DTL_ID&&null!=ADVC_ORDER_CHANGE_DTL_ID){
					total=Math.round((isNaN(total+v)?0:total+v)*100)/100;
				}
				if((""==ADVC_ORDER_CHANGE_DTL_ID||null==ADVC_ORDER_CHANGE_DTL_ID)&&check){
					total=Math.round((isNaN(total+v)?0:total+v)*100)/100;
				}
			});
			parent.topcontent.setAmoutVal(total);
		}
	}
		
	//比较变更信息
	function compareAlterInfo(data){		
		$.each(data,function(i,item){
			
			var childID = item.ADVC_ORDER_DTL_ID;
			var currRow = i;
			
			//单价
			var before_PRICE = item.PRICE;
			//折扣率
			var before_DECT_RATE = item.DECT_RATE;
			//折后单价
			var before_DECT_PRICE = item.DECT_PRICE;
			//订货数量
			var before_ORDER_NUM = item.ORDER_NUM;
			//交货日期
			var before_ORDER_RECV_DATE = (item.ORDER_RECV_DATE == null)? '' : item.ORDER_RECV_DATE;
			
			$("input[name='ADVC_ORDER_DTL_ID']").each(function(){
								
				if(childID == $(this).val()){
					currRow = $(this).attr("id").substring("ADVC_ORDER_DTL_ID".length) ;
					
					var curr_PRICE=$("#PRICE"+currRow).val();//单价
					var curr_DECT_RATE=$("#DECT_RATE"+currRow).val();//折扣率
					var curr_DECT_PRICE=$("#DECT_PRICE"+currRow).val();//折后单价
					var curr_ORDER_NUM=$("#ORDER_NUM"+currRow).val();//订货数量
					var curr_ORDER_RECV_DATE = $("#ORDER_RECV_DATE"+currRow).val();// 交货日期
					
					if(before_PRICE == curr_PRICE && 
					   before_DECT_RATE == curr_DECT_RATE &&
					   before_DECT_PRICE == curr_DECT_PRICE &&
					   before_ORDER_NUM == curr_ORDER_NUM &&
					   before_ORDER_RECV_DATE == curr_ORDER_RECV_DATE){
					
					   $("#errorMsg").val("尚无变更，请变更预订单申请明细！");
					   return;
					}					
				}
			});
								
		});		
	}
	
	function url(rownum){
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	}
function delDtl(){
	$("#jsontb :checkbox").attr("checked","true");
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
}
//验证折扣，订货数量，折后单价，单价
function checkNum(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag=true;
	checkBox.each(function(){
		var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
		var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
		var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//折后单价
		var ORDER_NUM=$(this).parent().parent().find("input[name='ORDER_NUM']").val();//订货数量
		if("0"==DECT_PRICE){
			parent.showErrorMsg("折后单价不能为0");
	            flag=false;
				return false;
		}
		var re1 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
		var re2 = new RegExp(/^\d{1,4}(\.\d{1,2})?$/);
        if(!re1.test(PRICE)){
            parent.showErrorMsg("单价最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
        if(!re2.test(DECT_RATE)){
            parent.showErrorMsg("折扣率最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
        if(!re1.test(DECT_PRICE)){
            parent.showErrorMsg("折后单价最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
        if(!re1.test(ORDER_NUM)){
            parent.showErrorMsg("订货数量最多可输入8位正整数");
            flag=false;
			return false;
        }
	});
	return flag;
}
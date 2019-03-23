/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
		var checkBox = $("#jsontb tr:gt(0) input[name='ADVC_ORDER_DTL_ID']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
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
	});
	
	$("#save").click(function(){
		var checkBox = $("#jsontb tr:gt(0) input[name='ADVC_ORDER_DTL_ID']:checked");
		var flag=true;
		checkBox.each(function(){
			$(this).parent().parent().find("select[name='PRD_TYPE']").each(function(){
				if(null==$(this).val()||""==$(this).val()){
					flag=false;
					parent.showErrorMsg("请选择货品类型");
					return;
				}
			})
		});
		if(!flag){
			return;
		}
		//验证单价，折扣率，折后单价，订货数量是否为0
		if(!checkNum()){
			return;
		}
		
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input[name='ADVC_ORDER_DTL_ID']:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				return;
			}
			
			childSave();
		}else{
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			allSave();
			
		}
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb  input[name='ADVC_ORDER_DTL_ID']").attr("checked","true");
		}else{
			$("#jsontb input[name='ADVC_ORDER_DTL_ID']").removeAttr("checked");
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
	if(!parent.topcontent.checkParent()){
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
	if($("#jsontb tr:gt(0) input[name='ADVC_ORDER_DTL_ID']").length>0){
		childData = multiPackJsonData();
	}
	$.ajax({
		url: "uncomm.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","uncomm.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "uncomm.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_ORDER_ID":selRowId},
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
	var checkBox = $("#jsontb tr:gt(0)  input[name='ADVC_ORDER_DTL_ID']:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "uncomm.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_DTL_IDS":ids,"ADVC_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
//				window.parent.topcontent.pageForm.submit();
				countTotalMoney();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("uncomm.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find(" input[name='ADVC_ORDER_DTL_ID']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
	 $("#jsontb tr:gt(0)").find("input[name='ADVC_ORDER_DTL_ID']").attr("checked","true");
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
              '',
              '',
              '',
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var SPECIAL_FLAG;
	if(arrs[15]==null||arrs[15]==""||arrs[15]=="0"){
		SPECIAL_FLAG='无';
	}else{
		SPECIAL_FLAG="<span style='color: red'>有</span>"
	}
	if(arrs[9]==null||arrs[9]==""||arrs[9]=="0"){
		arrs[9]="1";
	}
	if(arrs[22]==null||arrs[22]==""){
		arrs[22]=0;
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='ADVC_ORDER_DTL_ID' id='ADVC_ORDER_DTL_ID"+rownum+"' name='ADVC_ORDER_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号" size="8"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;' +
            "<img  align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID" type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[2]+'"/>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><select  json="PRD_TYPE" id="PRD_TYPE'+rownum+'" name="PRD_TYPE" mustinput="true"  autocheck="true" label="货品类型" inputtype="string"    value="'+arrs[17]+'"></select>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="hidden"   inputtype="string"    maxlength="50"  value="'+arrs[5]+'"/>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text" size="3"  inputtype="string"   readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text" size="3"  inputtype="string"   readonly       maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPECIAL_FLAG+'</font> <input class="btn"  value="编辑" onclick="url('+rownum+')"  type="button" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价" mustinput="true" size="5" type="text" onkeyup="countPRICE('+rownum+')"    value="'+arrs[8]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率" size="2" onkeyup="count('+rownum+')" type="text"   value="'+arrs[9]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价" size="5"  type="text" onkeyup="countDECT_RATE('+rownum+')"  mustinput="true"      valueType="8,2"   value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="订货数量" onkeyup="countORDER_NUM('+rownum+')" type="text" size="3"    mustinput="true"        maxlength="22"  value="'+arrs[11]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_RECV_DATE" id="ORDER_RECV_DATE'+rownum+'" name="ORDER_RECV_DATE"  autocheck="true" label="交货日期"  mustinput="true"  onclick="SelectDate();" size="8" onfocus="dateCheck('+rownum+')" readonly  type="text" inputtype="date"   value="'+arrs[23]+'" />&nbsp;' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="delCheck('+rownum+');SelectDate(ORDER_RECV_DATE'+rownum+');"></td>')
            .append('<td nowrap align="center" style="display: none;" ><input  json="IS_FREEZE_FLAG" id="IS_FREEZE_FLAG'+rownum+'" name="IS_FREEZE_FLAG"  label="是否冻结" onclick="freezeClick('+rownum+')"     type="checkbox" value="'+arrs[18]+'"/>&nbsp;</td>')
//            .append('<td nowrap align="left"><input  json="FREEZE_NUM" id="FREEZE_NUM'+rownum+'" name="FREEZE_NUM'+rownum+'"  autocheck="true" label="冻结数量"  type="text" readonly size="3" inputtype="int"  mustinput="true"     maxlength="22"  value="'+arrs[22]+'" />&nbsp;</td>')
//            .append('<td nowrap align="left"><input  json="FREEZE_STORE_NAME" id="FREEZE_STORE_NAME'+rownum+'" name="FREEZE_STORE_NAME"  inputtype="string"  autocheck="true" label="冻结库房名称"  type="text"    READONLY  value="'+arrs[21]+'"/>&nbsp;' +
//            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selStore("+rownum+")'/></td>")
//            .append('<input  json="FREEZE_STORE_ID" id="FREEZE_STORE_ID'+rownum+'" name="FREEZE_STORE_ID'+rownum+'"  label="冻结库房ID" type="hidden" value="'+arrs[19]+'"/>')
//            .append('<input  json="FREEZE_STORE_NO" id="FREEZE_STORE_NO'+rownum+'" name="FREEZE_STORE_NO'+rownum+'"  label="冻结库房NO" type="hidden" value="'+arrs[20]+'"/>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" name="PAYABLE_AMOUNT"  autocheck="true" label="应收金额"  type="text" size="7" onkeyup="countDECT_PRICE('+rownum+')"    value="'+arrs[12]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<input  json="ADVC_ORDER_ID" id="ADVC_ORDER_ID'+rownum+'" name="ADVC_ORDER_ID'+rownum+'"  label="预订单ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[15]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺标记" type="hidden" value="'+arrs[24]+'"/>')
              ;
	var PRD_TYPE=arrs[17];
	if(""==PRD_TYPE||null==PRD_TYPE){
		PRD_TYPE="销售货品";
	}
	SelDictShow("PRD_TYPE"+rownum, "BS_53", PRD_TYPE, "");
	if(arrs[18]==1){
		$("#IS_FREEZE_FLAG"+rownum).attr("checked","checked");
	}
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[name='ADVC_ORDER_DTL_ID']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}

//货品通用选取
    function selcPrd(rownum){
    	 var PRD_ID_TEMP=$("#PRD_ID"+rownum).val();
    	 var PRICE=$("#PRICE"+rownum).val();
    	 if(0==PRICE){
    		 $("#PRICE"+rownum).val("");
    	 }
	     var obj=selCommon("BS_21", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,FACT_PRICE", "selectParams");
	     rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE",rownum);
	     var PRD_ID=$("#PRD_ID"+rownum).val();
	     if(PRD_ID_TEMP!=PRD_ID){
	    	$("#SPECIAL_FLAG"+rownum).html("无");
			$("#SPCL_TECH_ID"+rownum).val("");
			$("#DECT_RATE"+rownum).val("1");
			$("#IS_FREEZE_FLAG"+rownum).prop("checked",false);
			$("#ORDER_NUM"+rownum).val("");
			$("#FREEZE_NUM"+rownum).val("0");
			$("#FREEZE_STORE_ID"+rownum).val("");
			$("#FREEZE_STORE_NO"+rownum).val("");
			$("#FREEZE_STORE_NAME"+rownum).val("");
			$("#PAYABLE_AMOUNT"+rownum).val("");
			$("#DECT_PRICE"+rownum).val($("#PRICE"+rownum).val());
	     }
	     paddingPrice();
	}
//冻结库房通用选取
    function selStore(rownum){
    	 var check=$("#IS_FREEZE_FLAG"+rownum).prop("checked");
    	 if(!check){
    		 parent.showErrorMsg("不冻结货品不需要选择冻结库房");
    		 return;
    	 }
    	 var PRD_ID=$("#PRD_ID"+rownum).val();
    	 if(""==PRD_ID||null==PRD_ID){
    		 parent.showErrorMsg("请先选择货品");
    		 return;
    	 }
    	 var CHANN_ID=$("#CHANN_ID").val();
    	 var BMXXID=$("#BMXXID").val();
    	 var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
    	 var sql="(" +
    	 			"select b.STORE_NO," +
	    	 			" b.STORE_NAME," +
	    	 			" b.DTL_ADDR," +
	    	 			" b.STORE_ID," +
	    	 			"NVL((" +
		    	 			" select sum(NVL(a.STORE_NUM, 0) - NVL(a.FREEZE_NUM, 0)) " +
		    	 			" from DRP_STORE_ACCT a " +
		    	 			" where a.STORE_ID = b.STORE_ID " +
		    	 			" and a.STATE = 0 " +
		    	 			" and NVL(a.SPCL_TECH_ID, 'null') = NVL('"+SPCL_TECH_ID+"', 'null')" +
		    	 			" and a.PRD_ID = '"+PRD_ID+"'" +
		    	 		"),0) NUM" +
	    	 		" from DRP_STORE b" +
	    	 		" where BEL_CHANN_ID in ('"+CHANN_ID+"','"+BMXXID+"')  and b.DEL_FLAG = 0)temp";
	     var obj=selCommon("BS_104", false, "FREEZE_STORE_ID"+rownum, "STORE_ID", "forms[0]","FREEZE_STORE_ID"+rownum+",FREEZE_STORE_NO"+rownum+",FREEZE_STORE_NAME"+rownum, "STORE_ID,STORE_NO,STORE_NAME", "","",sql);
	}
    //当键盘按键松开时触发事件
	//输入折扣率时，计算折后单价、应收金额
	 function count(i){
		var TERM_DECT_FROM=$("#TERM_DECT_FROM").val();
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		//判断输入是否为数字，如果部为数字则默认为0
		var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
		var PRD_TYPE=$("#PRD_TYPE"+i).val();
		if(null==PRD_TYPE||""==PRD_TYPE){
			$("#DECT_RATE"+i).val("");
			parent.showErrorMsg("请选择货品类型");
				return;
		}
//		if(""!=TERM_DECT_FROM&&null!=TERM_DECT_FROM&&0!=TERM_DECT_FROM&&"赠品"!=PRD_TYPE){
//			if(TERM_DECT_FROM>DECT_RATE&&DECT_RATE>0){
//				parent.showErrorMsg("所输入的折扣率超过可输入最低折扣率");
//			}
//		}
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
		var PAYABLE_AMOUNT = Math.round((isNaN(ORDER_NUM*DECT_PRICE)?0:ORDER_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
		countTotalMoney();
	}
	 //输入单价时，计算折后单价、应收金额
	function countPRICE(i){
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		//判断输入是否为数字，如果部为数字则默认为0
		var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
		var PAYABLE_AMOUNT = Math.round((isNaN(ORDER_NUM*DECT_PRICE)?0:ORDER_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
		countTotalMoney();
	}
	//输入折后单价时，计算折扣率和应收金额
	function countDECT_RATE(i){
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var PAYABLE_AMOUNT = Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
		var DECT_RATE = Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
		$("#DECT_RATE"+i).val(DECT_RATE);
		countTotalMoney();
	}
	//输入应收金额后计算折后单价和 折扣率
	function countDECT_PRICE(i){
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		var temp_PAYABLE_AMOUNT=$("#PAYABLE_AMOUNT"+i).val();//应收金额
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		if(0==ORDER_NUM){
			$("#DECT_PRICE"+i).val(0);
			$("#DECT_RATE"+i).val(0);
			countTotalMoney();
			return;
		}
		var PAYABLE_AMOUNT=isNaN(temp_PAYABLE_AMOUNT)?0:parseFloat(temp_PAYABLE_AMOUNT);//应收金额
		var DECT_PRICE= Math.round((isNaN(PAYABLE_AMOUNT/ORDER_NUM)?0:PAYABLE_AMOUNT/ORDER_NUM)*100)/100;//计算折后单价，保留2位小数
		var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		$("#DECT_RATE"+i).val(DECT_RATE);
		countTotalMoney();
	}
	//输入订货数量计算应收金额
	function countORDER_NUM(i){
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		if(""==temp_ORDER_NUM||null==temp_ORDER_NUM){
				temp_ORDER_NUM=0;
			}
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var PAYABLE_AMOUNT = Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
		var check=$("#IS_FREEZE_FLAG"+i).prop("checked");
		if(check){
			$("#FREEZE_NUM"+i).val(ORDER_NUM);
		}
		countTotalMoney();
	}
	//下帧改变应收金额时算出总金额赋值到上贞应收总金额
	function countTotalMoney(){
		var inputs = $("input[name='PAYABLE_AMOUNT']");
		var total=0;
		inputs.each(function(){
			var v = $(this).val();
			if(""==v){
				v=0;
			}
			var PAYABLE_AMOUNT=isNaN(v)?0:parseFloat(v);
			total = Math.round((isNaN(total+PAYABLE_AMOUNT)?0:total+PAYABLE_AMOUNT)*100)/100;
		});
		parent.topcontent.setAmoutVal(total);
	}
	function url(rownum){
		var PRD_ID=$("#PRD_ID"+rownum).val();
		if(""==PRD_ID||null==PRD_ID){
			parent.showErrorMsg("请先选择货品");
			return;
		}
		var CHANN_ID=$("#CHANN_ID").val();
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var ADVC_ORDER_DTL_ID=$("#ADVC_ORDER_DTL_ID"+rownum).val();
		//不验证渠道折扣
		var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID+"&citeUrl=editTechWithOutPrice&check=N",window,"dialogwidth=800px; dialogheight=600px; status=no");
		//验证渠道折扣
		//var data=window.showModalDialog("techorderManager.shtml?action=editTechWithOutPrice&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
		if(null==data){
			return;
		}
		if(data.SPCL_TECH_FLAG>0){
			$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span>");
		}else{
			$("#SPECIAL_FLAG"+rownum).html("无");
		}
		$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
		$("#SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
	}
	function freezeClick(id){
		var check=$("#IS_FREEZE_FLAG"+id).prop("checked");
		var freezeDate = $("#ORDER_RECV_DATE"+id).val();
		if(check){
			if(""==freezeDate||null==freezeDate){
				parent.showErrorMsg("请选择交货日期");
				delCheck(id);
				return ;
			}
			if(!checkFreezeDay(id)){
				$("#IS_FREEZE_FLAG"+id).prop("checked",false);
				
				return;
			}
			var ORDER_NUM_temp=$("#ORDER_NUM"+id).val();
			if(""==ORDER_NUM_temp||null==ORDER_NUM_temp){
				ORDER_NUM_temp=0;
			}
			var ORDER_NUM=isNaN(ORDER_NUM_temp)?0:parseFloat(ORDER_NUM_temp);
			$("#FREEZE_NUM"+id).val(ORDER_NUM);
		}else{
			$("#FREEZE_NUM"+id).val(0);
			$("#FREEZE_STORE_ID"+id).val("");
			$("#FREEZE_STORE_NO"+id).val("");
			$("#FREEZE_STORE_NAME"+id).val("");
		}
		
	}
	
	function checkFreezeDay(i){
		var maxFreezeDate = $("#MAX_FREEZE_DATE").val();
		if(maxFreezeDate==''||maxFreezeDate==0)
		{
			return true;
		}
		var now= new Date();
  		var year=now.getFullYear();
  		var month=now.getMonth()+1;
  		var date=now.getDate();
  		var strCurrDate = year+"-"+month+"-"+date;
		var freezeDate = $("#ORDER_RECV_DATE"+i).val();
		var fdate = new Date(freezeDate.replace(/-/g, "/"));
 		var currDate = new Date(strCurrDate.replace(/-/g, "/"));
		var Milliseconds = fdate.getTime() - currDate.getTime();
 		var date = parseInt(Milliseconds / (1000 * 60 * 60 * 24));
		if(date>maxFreezeDate){
			alert('交期超过允许最大冻结天数('+maxFreezeDate+'天)，不允许冻结!');
			return false;
		}
		return true;
	}
	//去掉是否冻结
	function delCheck(id){
		$("#IS_FREEZE_FLAG"+id).prop("checked",false);
	}
	//验证时间
	function dateCheck(id){
		var check=$("#IS_FREEZE_FLAG"+id).prop("checked");
		if(check){
			if(!checkFreezeDay(id)){
				$("#IS_FREEZE_FLAG"+id).prop("checked",false);
//				$("#ORDER_RECV_DATE"+id).val("");
				return;
			}
		}

	}
	//验证单价，折扣率，折后单价，订货数量是否为0
	function checkNum(){
		var checkBox = $("#jsontb tr:gt(0)  input[name='ADVC_ORDER_DTL_ID']:checked");
		var flag=true;
		checkBox.each(function(){
			var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
			var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
			var DECT_PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//折后价
			var ORDER_NUM=$(this).parent().parent().find("input[name='ORDER_NUM']").val();//订货数量
			var PAYABLE_AMOUNT=$(this).parent().parent().find("input[name='PAYABLE_AMOUNT']").val();//应收金额
			if(""==PRICE||null==PRICE){
				parent.showErrorMsg("请输入单价");
	            flag=false;
				return false;
			}
			if(""==DECT_RATE||null==DECT_RATE){
				parent.showErrorMsg("请输入折扣率");
	            flag=false;
				return false;
			}
			if(""==DECT_PRICE||null==DECT_PRICE){
				parent.showErrorMsg("请输入折后价");
	            flag=false;
				return false;
			}
			if(""==ORDER_NUM||null==ORDER_NUM){
				parent.showErrorMsg("请输入订货数量");
	            flag=false;
				return false;
			}
			if(""==PAYABLE_AMOUNT||null==PAYABLE_AMOUNT){
				parent.showErrorMsg("请输入应收金额");
	            flag=false;
				return false;
			}
			var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
	        if(!re2.test(PRICE)){
	            parent.showErrorMsg("单价最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
			var re1 = new RegExp(/^\d{1,2}(\.\d{1,2})?$/);
	        if(!re1.test(DECT_RATE)){
	            parent.showErrorMsg("折扣率最多可输入2位正整数和2位小数");
	            flag=false;
				return false;
	        }
			
	        if(!re2.test(DECT_PRICE)){
	            parent.showErrorMsg("折后价最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
	        if(!re2.test(ORDER_NUM)){
	            parent.showErrorMsg("订货数量最多可输入8位正整数");
	            flag=false;
				return false;
	        }
	        if(!re2.test(PAYABLE_AMOUNT)){
	            parent.showErrorMsg("应收金额最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
	        
			if(0==ORDER_NUM&&""!=ORDER_NUM&&null!=ORDER_NUM){
				parent.showErrorMsg("订货数量不能为0");
				flag=false;
				return false;
			}
		});
		return flag;
	}
	function paddingPrice(){
		var checkBox = $("#jsontb tr:gt(0)  input[name='ADVC_ORDER_DTL_ID']:checked");
		checkBox.each(function(){
			var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
			if(1==DECT_RATE){
				var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
				$(this).parent().parent().find("input[name='DECT_PRICE']").val(PRICE);//折后单价
			}
		});
	}
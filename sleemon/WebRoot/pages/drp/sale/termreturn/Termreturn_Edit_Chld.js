/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit_Chld
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if(!checkNum()){
				return;
			}
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
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
				allSave();
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
			childData = multiPackJsonData();
	}
	$.ajax({
		url: "termreturn.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","termreturn.shtml?action=toFrame");
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
		url: "termreturn.shtml?action=childEdit",
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
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "termreturn.shtml?action=childDelete",
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
   newGoBack("termreturn.shtml?action=toFrame");
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
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	var radio=getRadio();
	var SPCL_TECH_FLAG;
	if(1==radio){
		if(arrs[17]==null||arrs[17]==""){
			SPCL_TECH_FLAG='无';
		}else{
			SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'   value='查看' onclick='url("+rownum+")'  type='button' />"
		}
	}else{
		if(arrs[17]==null||arrs[17]==""){
			SPCL_TECH_FLAG="<input class='btn'  value='编辑' onclick='editUrl("+rownum+")'  type='button' />";
		}else{
			SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='编辑' onclick='editUrl("+rownum+")'  type='button' />"
		}
		
	}
	
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' value='"+arrs[0]+"'/></td>")
            .append('<input type="hidden" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/>')
            .append('<td nowrap align="left" name="sss"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" size="8" label="货品编号" size="10"  type="text"  mustinput="true"   READONLY  value="'+arrs[2]+'"/>&nbsp;' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="hidden"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[5]+'"/>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌" size="5"  type="text"   inputtype="string"   readonly     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" size="3" label="标准单位"  type="text"   inputtype="string"   readonly      maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_TYPE" id="PRD_TYPE'+rownum+'" name="PRD_TYPE"  label="货品类型" size="5" type="text" readonly value="'+arrs[18]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'" name="SPECIAL_FLAG">'+SPCL_TECH_FLAG+'</font>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true"  size="5" label="单价"  type="text"   onkeyup="count('+rownum+')"  readonly   mustinput="true"      value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率" size="5" onkeyup="count('+rownum+')"  type="text"     readonly    mustinput="true"    value="'+arrs[9]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="退货单价" size="5" onkeyup="countPAYABLE_AMOUNT('+rownum+')" type="text"        mustinput="true"      value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="退货数量" size="3"  onkeyup="countPAYABLE_AMOUNT('+rownum+')"  type="text"        mustinput="true"     maxlength="22"  value="'+arrs[11]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" name="PAYABLE_AMOUNT'+rownum+'"  autocheck="true" label="应退金额" size="8" readonly type="text" onkeyup="countDECT_PRICE('+rownum+')"  inputtype="float"      valueType="12,2"   value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="退货原因"  type="text" mustinput="true"  inputtype="string"        maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_ATT" id="RETURN_ATT'+rownum+'" name="RETURN_ATT"  autocheck="true" label="退货附件"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<input type="text" json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID"  label="来源货品信息"  value="'+arrs[14]+'"/>')
            .append('<input   type="hidden" json="ADVC_ORDER_DTL_ID" id="ADVC_ORDER_DTL_ID'+rownum+'" name="ADVC_ORDER_DTL_ID"   value="'+arrs[0]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="特殊工艺单标记" type="hidden" value="'+arrs[17]+'"/>')
            .append('<input  json="REAL_NUM" id="REAL_NUM'+rownum+'" type="text" name="REAL_NUM"  label="已发数量" type="hidden" value="'+arrs[19]+'"/>')
              ;
	uploadFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
	upAttr(rownum);
}
function getFROM_BILL_ID(){
	return parent.topcontent.document.getElementById("FROM_BILL_ID").value;
}
function getRadio(){
	return parent.topcontent.getRadio();
}
//货品通用选取
    function selcPrd(rownum){
    	var FROM_BILL_ID=getFROM_BILL_ID();
    	var actionType=getActionType();
    	var radio=getRadio();
    	var ADVC_ORDER_DTL_ID = $("#ADVC_ORDER_DTL_ID"+rownum).val();
    	
    	if((FROM_BILL_ID==null || FROM_BILL_ID == "")&&"1"==radio&&"edit"==actionType){
    		alert("请选择来源编号");
    		return false;
    	}
    	var CHANN_TYPE=$("#CHANN_TYPE").val();
    	if(("0"==radio&&"edit"==actionType)||("list"==actionType&&(FROM_BILL_ID==null||FROM_BILL_ID == ""))){
    		var LEDGER_ID=$("#CHANN_ID").val();
    		$("#selectParams").val(" ZTXXID='"+LEDGER_ID+"' ");
    		var obj=selCommon("BS_129", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum, 
    			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,FACT_PRICE", "selectParams");
	     	rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE",rownum);
	     	var DECT_RATE=$("#DECT_RATE"+rownum).val();
	     	if(1==DECT_RATE){
	     		$("#DECT_PRICE"+rownum).val($("#PRICE"+rownum).val());//折后单价
	     	}
	     	paddingPrice();
    	}else{
    		//获取编辑和列表里的来源单据id设置通用选取条件
	    	 //0156867--start 刘曰刚 --2014-06-19//
	        //修改过滤同一条明细只选一次
	    	var dtlId=$("#FROM_BILL_DTL_IDS").val();
	    	var ids;
	    	if(""==dtlId||null==dtlId){
	    		ids="";
	    	}else{
	    		ids=dtlId+",";
	    	}
	    	$("input[name='FROM_BILL_DTL_ID']").each(function(){
	    		//已经保存过的记录，在通用选取的时候 要显示
	    		var temp_id = $(this).val();
	    		if(null != temp_id && "" != temp_id &&(null == ADVC_ORDER_DTL_ID || "" == ADVC_ORDER_DTL_ID)){
	    			ids=ids+"'"+temp_id+"',";
	    		}
	    	});
	    	ids = ids.substr(0,ids.length-1);
	    	var sql;
	    	if(""==ids||null==ids){
	    		sql="1=1";
	    	}else{
	    		sql="ADVC_ORDER_DTL_ID not in ("+ids+")";
	    	}
	    	 //0156867--End 刘曰刚 --2014-06-19//
    		//获取编辑和列表里的来源单据id设置通用选取条件
    		$("#selectParams").val("DEL_FLAG=0 and ADVC_ORDER_ID='"+getFROM_BILL_ID()+"' and "+sql);
    		if("直营办"==CHANN_TYPE){
    			var obj=selCommon("BS_198", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",ORDER_NUM"+rownum+",PAYABLE_AMOUNT"+rownum+",FROM_BILL_DTL_ID"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum+",PRD_TYPE"+rownum+",REAL_NUM"+rownum, 
    			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,PAYABLE_AMOUNT,ADVC_ORDER_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,PRD_TYPE,REAL_NUM", "selectParams");
    		}else{
    			var obj=selCommon("BS_126", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",ORDER_NUM"+rownum+",PAYABLE_AMOUNT"+rownum+",FROM_BILL_DTL_ID"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum+",PRD_TYPE"+rownum+",REAL_NUM"+rownum, 
    			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,PAYABLE_AMOUNT,ADVC_ORDER_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,PRD_TYPE,REAL_NUM", "selectParams");
    		}
    		
	        rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,PAYABLE_AMOUNT,FROM_BILL_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,PRD_TYPE,REAL_NUM",rownum);
//	        var SPCL_TECH_FLAG=$("#SPCL_TECH_FLAG"+rownum).val();
//	        if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""){
//				$("#SPECIAL_FLAG"+rownum).html('无');
//			}else{
//				$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span><input   value='查看' onclick='url("+rownum+")' class='btn'  type='button' />");
//			}
	        var checkBox=$("input[type='checkbox']");
	        checkBox.each(function(){
				var SPCL_TECH_FLAG=$(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
				var SPCL_TECH_ID=$(this).parent().parent().find("input[name='SPCL_TECH_ID']").val();
				if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""||SPCL_TECH_FLAG==0){
					$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("无");
				}else{
					alert(1);
					var onclick="urlById('"+SPCL_TECH_ID+"')";
					$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("<span style='color: red'>有</span><input class='btn'  value='查看' onclick="+onclick+"  type='button' />");
				}
			})
    	}
	}
function urlById(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
 //当键盘按键松开时触发事件
	//填写退货单价和退货数量计算退货金额
	 function countPAYABLE_AMOUNT(i){
//		var PRD_TYPE=$("#PRD_TYPE"+i).val();//货品类型
//		if("赠品"==PRD_TYPE){
//			$("#PAYABLE_AMOUNT"+i).val("0");
//		}
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//退货单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//退货单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//退货数量
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//退货数量
		var PAYABLE_AMOUNT = Math.round((isNaN(ORDER_NUM*DECT_PRICE)?0:ORDER_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
		var FROM_BILL_ID=getFROM_BILL_ID();
		if(""==FROM_BILL_ID||null==FROM_BILL_ID){
			var PRICE=$("#PRICE"+i).val();
			PRICE=isNaN(PRICE)?0:parseFloat(PRICE);//单价
			if(PRICE==0){
				$("#DECT_RATE"+i).val("0");
			}
			var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算应收金额，保留2位小数
			$("#DECT_RATE"+i).val(DECT_RATE);
		}
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
	}
//计算输入退货金额计算退货单价
	function countDECT_PRICE(rownum){
		var temp_PAYABLE_AMOUNT=$("#PAYABLE_AMOUNT"+i).val();//退货金额
		var PAYABLE_AMOUNT=isNaN(temp_PAYABLE_AMOUNT)?0:parseFloat(temp_PAYABLE_AMOUNT);//退货金额
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//退货数量
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//退货数量
		var DECT_PRICE = Math.round((isNaN(PAYABLE_AMOUNT/ORDER_NUM)?0:PAYABLE_AMOUNT/ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
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
function upAttr(id){
	var FROM_BILL_ID=getFROM_BILL_ID();
	if(""==FROM_BILL_ID||null==FROM_BILL_ID){
		$("#DECT_RATE"+id).val("1");
		$("#DECT_RATE"+id).attr("readonly",false);
		$("#DECT_RATE"+id).css("background","white");
		$("#DECT_PRICE"+id).attr("readonly",false);
		$("#DECT_PRICE"+id).css("background","white");
	}
}
function paddingPrice(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		checkBox.each(function(){
			var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
			if(1==DECT_RATE){
				var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
				$(this).parent().parent().find("input[name='DECT_PRICE']").val(PRICE);//折后单价
			}
		});
	}
function count(i){
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
	}
function checkNum(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag=true;
	checkBox.each(function(){
		var ORDER_NUM=$(this).parent().parent().find("input[name='ORDER_NUM']").val();//退货数量
		var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
		var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//退货单价
		var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
		var REAL_NUM=$(this).parent().parent().find("input[name='REAL_NUM']").val();//已发数量
		var PRD_NO=$(this).parent().parent().find("input[name='PRD_NO']").val();//货品编号
		if(""==ORDER_NUM||null==ORDER_NUM){
				parent.showErrorMsg("请输入退货数量");
	            flag=false;
				return false;
		}
		if("0"==ORDER_NUM){
			parent.showErrorMsg("退货数量不能为0");
	            flag=false;
				return false;
		}
		var re1 = new RegExp(/^\d{1,8}?$/);
        if(!re1.test(ORDER_NUM)){
            parent.showErrorMsg("退货数量最多可输入8位正整数");
            flag=false;
			return false;
        }
        if(""!=REAL_NUM&&null!=REAL_NUM&&"undefined"!=REAL_NUM){
        	if(parseFloat(ORDER_NUM)>parseFloat(REAL_NUM)){
        		parent.showErrorMsg("货品编号："+PRD_NO+"退货数量不能大于已发货数量,已发数量:"+REAL_NUM);
	            flag=false;
				return false;
        	}
        }
        if(""==PRICE||null==PRICE){
				parent.showErrorMsg("请输入单价");
	            flag=false;
				return false;
		}
//		if("0"==PRICE){
//			parent.showErrorMsg("单价不能为0");
//	            flag=false;
//				return false;
//		}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re2.test(PRICE)){
            parent.showErrorMsg("单价最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
        if(""==DECT_PRICE||null==DECT_PRICE){
				parent.showErrorMsg("请输入退货单价");
	            flag=false;
				return false;
		}
		var re4 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re4.test(DECT_PRICE)){
            parent.showErrorMsg("退货单价最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
        
         if(""==DECT_RATE||null==DECT_RATE){
				parent.showErrorMsg("请输入折扣率");
	            flag=false;
				return false;
		}
//		if("0"==DECT_RATE){
//			parent.showErrorMsg("折扣率不能为0");
//	            flag=false;
//				return false;
//		}
		var re3 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re3.test(DECT_RATE)){
            parent.showErrorMsg("折扣率最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
	});
	return flag;
}
function editUrl(rownum){
		var PRD_ID=$("#PRD_ID"+rownum).val();
		if(""==PRD_ID||null==PRD_ID){
			parent.showErrorMsg("请先选择货品");
			return;
		}
		var CHANN_ID=$("#CHANN_ID").val();
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var ADVC_ORDER_DTL_ID=$("#ADVC_ORDER_DTL_ID"+rownum).val();
		//不验证渠道折扣
		var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID+"&citeUrl=editTechWithOutPrice&check=N&filterSpclFlag=1",window,"dialogwidth=800px; dialogheight=600px; status=no");
		if(null==data){
			return;
		}
		if(data.SPCL_TECH_FLAG>0){
			$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span><input class='btn'  value='编辑' onclick='editUrl("+rownum+")'  type='button' />");
		}else{
			$("#SPECIAL_FLAG"+rownum).html("<input class='btn'  value='编辑' onclick='editUrl("+rownum+")'  type='button' />");
		}
		$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
		$("#SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
	}
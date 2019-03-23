/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit_Chld
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
		var actionType = getActionType();
//		if ("edit" == actionType) {
//			var DEF_STORE_ID = parent.topcontent.document.getElementById("DEF_STORE_ID").value;
//			var DEF_STORE_NO = parent.topcontent.document.getElementById("DEF_STORE_NO").value;
//			var DEF_STORE_NAME = parent.topcontent.document.getElementById("DEF_STORE_NAME").value;
//			arrs = ['', DEF_STORE_ID, DEF_STORE_NO, DEF_STORE_NAME,'','','','',
//				    '', '', '', '', '', '', '', '', ''];
//		    addRow(arrs);
//		} else {
			addRow();
//		}
	});
	
//	$("body").dblclick(function(){
//    	var actionType = getActionType();
//		if ("edit" == actionType) {
//			var DEF_STORE_ID = parent.topcontent.document.getElementById("DEF_STORE_ID").value;
//			var DEF_STORE_NO = parent.topcontent.document.getElementById("DEF_STORE_NO").value;
//			var DEF_STORE_NAME = parent.topcontent.document.getElementById("DEF_STORE_NAME").value;
//			arrs = ['', DEF_STORE_ID, DEF_STORE_NO, DEF_STORE_NAME,'','','','',
//				    '', '', '', '', '', '', '', '', ''];
//		    addRow(arrs);
//		} else {
//			addRow();
//		}
//	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var state = parent.topcontent.document.getElementById("state").value;
		var billtype = parent.topcontent.getBIll_TYPE();
		//alert("billtype======"+billtype);
		/*
		if ((billtype != "手工新增" && state != "撤销") || (billtype != "手工新增" && state != "未提交")) {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
			return;
		}*/
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
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中明细校验
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
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	
	 disabledChild();
	
});


function disabledChild(){
	var BILL_TYPE = parent.topcontent.getBIll_TYPE();
	if("订货入库" == BILL_TYPE || "返修入库" == BILL_TYPE){
//		$("#jsontb").attr("disabled","disabled");
//		$("#jsontb tr:gt(0) input[json !='REMARK']").attr("disabled","disabled");
//		$("#jsontb tr:gt(0) img").attr("disabled","disabled");
		
//		$("#add").attr("disabled","disabled");
		
//		$("#delete").attr("disabled","disabled");
	} 
}
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
	
	if(!checkChild()){
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
		url: "tstoreIn.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STOREIN_NOTICE_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","tstoreIn.shtml?action=toFrame");
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
	if(!checkChild()){
		return;
	}
	$.ajax({
		url: "tstoreIn.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREIN_NOTICE_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
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
	
	$.ajax({
		url: "tstoreIn.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_NOTICE_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew(){
   newGoBack("tstoreIn.shtml?action=toFrame");
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	var selSotre = '<img align="absmiddle" name="selDEF_STORE_NO" id="selDEF_STORE_NO" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selStore('+rownum+')"/>'
	rownum=_row_num;_row_num++;
	
	//默认库房
	var DEF_STORE_ID = parent.topcontent.getDEF_STORE_ID();
	var DEF_STORE_NO = parent.topcontent.getDEF_STORE_NO();
	var DEF_STORE_NAME = parent.topcontent.getDEF_STORE_NAME();
	//新增的时候 是主表的默认库房
	if((null == arrs[0] || "" == arrs[1]) && (null != DEF_STORE_ID && "" != DEF_STORE_ID)){
			arrs[1] = DEF_STORE_ID;
			arrs[2] = DEF_STORE_NO;
			arrs[3] = DEF_STORE_NAME;
	}

	var techText = "无";
	var funText = "selectTechPage('"+arrs[18]+"','"+arrs[11]+"')";
	var BILL_TYPE = parent.topcontent.getBIll_TYPE();
	var FROM_TYPE = parent.topcontent.getFROM_TYPE();
	if(arrs[17]>=1){
		if("手工新增" == BILL_TYPE && "总部订货" != FROM_TYPE){
			techText = "<font color='red'>有</font> <input class='btn'  value='编辑' onclick='url("+rownum+")'  type='button' />";
		}else{
			techText = "<font color='red'>有</font> <input type='button' class='btn' onclick="+funText+" value='查看'/>";
		}
	}else{
		if("手工新增" == BILL_TYPE && "总部订货" != FROM_TYPE){
			techText = "无 <input class='btn'  value='编辑' onclick='url("+rownum+")'  type='button' />";
		}
	}
	
	if(null == arrs[12] || "" == arrs[12]){
		arrs[12] = "1";
	}
	
	var FROM_BILL_ID = parent.topcontent.getFROM_BILL_ID();
	var NOTICE_NUM_DISABLED = "";
	var IS_UPDATE_STOREIN = $("#IS_UPDATE_STOREIN").val();
	//alert("IS_UPDATE_STOREIN======"+IS_UPDATE_STOREIN);
	if(IS_UPDATE_STOREIN != 1){
	   NOTICE_NUM_DISABLED = "disabled";
	}
	/*
	if("手工新增" == BILL_TYPE || (null == arrs[0] || "" == arrs[0])){
		NOTICE_NUM_DISABLED = "";
	}*/
	var selectSpectStyle = "";
	if("手工新增" == BILL_TYPE && "总部订货" == FROM_TYPE){
		selectSpectStyle = "display: none;";
	}
	var GOODS_ORDER_NO = parent.topcontent.getFROM_BILL_NO();
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREIN_NOTICE_DTL_ID' name='STOREIN_NOTICE_DTL_ID"+rownum+"' id='STOREIN_NOTICE_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="STOREIN_STORE_ID" id="STOREIN_STORE_ID'+rownum+'" name="STOREIN_STORE_ID'+rownum+'" label="入库库房ID"  type="hidden" value="'+arrs[1]+'"/><input  json="STOREIN_STORE_NO" id="STOREIN_STORE_NO'+rownum+'" size="12"  name="STOREIN_STORE_NO'+rownum+'"  autocheck="true" label="入库库房编号" size="6"  type="text"   inputtype="string"   readonly   maxlength="30"  value="'+arrs[2]+'"/>&nbsp;<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selStore('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STOREIN_STORE_NAME" id="STOREIN_STORE_NAME'+rownum+'" name="STOREIN_STORE_NAME'+rownum+'"  autocheck="true" label="入库库房名称"  type="text"   inputtype="string"  size="12"  readonly   maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" value="'+arrs[4]+'"/><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'" size="12"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="32"  value="'+arrs[5]+'"/>&nbsp;<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selProduct('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100" size="12"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50" size="12"   value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display:none"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"   label="花号"  type="text"   inputtype="string"  readonly        maxlength="50" size="12"   value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50" size="12"   value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50" size="3"   value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><span id="SPECIAL_FLAG'+rownum+'">'+techText+'</span> <span id="" style="'+selectSpectStyle+'"><input type="hidden" id="SPCL_TECH_ID'+rownum+'" json="SPCL_TECH_ID" value="'+arrs[18]+'"/></span> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  autocheck="true" label="单价"  type="text"   readonly    size="8"  value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'" onkeyup="count('+rownum+')"    mustinput="true"  autocheck="true" label="折扣率"  type="text" textType="float"     valueType="4,2"   size="4"     value="'+arrs[12]+'"    /><span class="validate">*</span>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  autocheck="true" mustinput="true" label="折扣价"  onkeyup="countDECT_RATE('+rownum+')" onblur="countDECT_RATE('+rownum+')"  type="text"   textType="float"     size="8"  mustinput="true"   valueType="8,2"   value="'+arrs[13]+'"  /><span class="validate">*</span>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'"  autocheck="true" mustinput="true" label="通知入库数量"  type="text"   '+NOTICE_NUM_DISABLED+'  onkeyup="countNOTICE_NUM('+rownum+')" onblur="countNOTICE_NUM('+rownum+')" textType="float"  size="8"  mustinput="true"     maxlength="8"  value="'+arrs[14]+'" /><span class="validate">*</span>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT'+rownum+'"  autocheck="true"mustinput="true"  label="折后金额" onkeyup="countDECT_AMOUNT('+rownum+')" type="text"  onblur="countDECT_AMOUNT('+rownum+')"  textType="float"   size="8"    mustinput="true"   valueType="8,2"   value="'+arrs[15]+'" /><span class="validate">*</span>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"  maxlength="250"   value="'+arrs[16]+'" />')
            .append('<input  json="GOODS_ORDER_NO" id="GOODS_ORDER_NO'+rownum+'" name="GOODS_ORDER_NO'+rownum+'"  autocheck="true" label="订单订单编号"  type="hidden"   inputtype="string"  maxlength="250"   value="'+GOODS_ORDER_NO+'" />&nbsp;</td>')
              ;
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}

//仓库通用选取
function selStore(rownum){
	 selCommon('BS_35', false, 'STOREIN_STORE_ID'+rownum, 'STORE_ID','childForm', "STOREIN_STORE_NO"+rownum+",STOREIN_STORE_NAME"+rownum, 'STORE_NO,STORE_NAME','selectParam');
	 //注释此行 否则会触发改变折后单价的js count(i) 
	 //$("#STOREIN_STORE_NAME"+rownum).focus();
}

//
function selProduct(rownum){
	var FROM_TYPE = parent.topcontent.getFROM_TYPE();
	var BILL_TYPE = parent.topcontent.getBIll_TYPE();
	var FROM_BILL_ID = parent.topcontent.getFROM_BILL_ID();
	 
	if("手工新增" == BILL_TYPE && "总部订货" == FROM_TYPE){
		$("#selectPrdParams").val(" GOODS_ORDER_ID='"+FROM_BILL_ID+"'");
		 selCommon("BS_119", false, "PRD_ID"+rownum, "PRD_ID", "childForm",
			"PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",SPCL_TECH_ID"+rownum, 
			"PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,SPCL_TECH_ID","selectPrdParams");
		
		//multiSelCommonSet(rtnArr,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,SPCL_TECH_ID",rownum);
	}else{
		selCommon("BS_110", false, "PRD_ID"+rownum, "PRD_ID", "childForm",
			"PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum, 
			"PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE","selectPrdParams");
	}
	
	
	var DECT_RATE = $("#DECT_RATE"+rownum).val();
	var PRICE = $("#PRICE"+rownum).val();
	var DECT_PRICE = FloatMul(DECT_RATE,PRICE);
	$("#DECT_PRICE"+rownum).val(DECT_PRICE);
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	if(null != SPCL_TECH_ID && "" != SPCL_TECH_ID){
		var url = "selectTechPage('"+SPCL_TECH_ID+"','"+PRICE+"')"
		var techText = "<font color='red'>有</font> <input type='button' class='btn' onclick="+url+" value='查看'/>";	 
		$("#SPECIAL_FLAG"+rownum).html(techText);
	}
	
//	$("#SPECIAL_FLAG"+rownum).html("");
//	$("#SPCL_TECH_ID"+rownum).val("");
	
}

//选择特殊工艺
function slectTech(rownum){
	var PRD_NO = $("#PRD_NO"+rownum).val();
	var LEDGER_ID = $("#LEDGER_ID").val();
	var params = " DEL_FLAG=0 and PRD_NO='"+PRD_NO+"' ";
	if(null != LEDGER_ID && "" != LEDGER_ID){
		params = params +" and LEDGER_ID='"+LEDGER_ID+"' and TECH_NO_EDIT_FLAG=0 ";
	}
	$("#selectTechParams").val(params);
	selCommon("BS_115", false, "SPCL_TECH_ID"+rownum, "SPCL_TECH_ID", "childForm", "SPCL_DTL_REMARK"+rownum,  "SPCL_DTL_REMARK","selectTechParams");
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	var PRICE=$("#PRICE"+rownum).val();
	if(null != SPCL_TECH_ID && "" != SPCL_TECH_ID){
		var url = "selectTechPage('"+SPCL_TECH_ID+"','"+PRICE+"')"
		var techText = "<font color='red'>有</font> <input type='button' class='btn' onclick="+url+" value='查看'/>";	 
		$("#SPECIAL_FLAG"+rownum).html(techText);
	}
	
	
	
	
}


function selectTechPage(SPCL_TECH_ID,PRICE){
//	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


//计算折后单价 onblur事件
function computePrice(rownum){
	var DECT_RATE = $("#DECT_RATE"+rownum).val();
	var PRICE = $("#PRICE"+rownum).val();
	var DECT_PRICE = FloatMul(DECT_RATE,PRICE);
	$("#DECT_PRICE"+rownum).val(DECT_PRICE);
}

//计算折扣率 onblur事件
function computeRate(rownum){
	var PRICE = $("#PRICE"+rownum).val();
	var DECT_PRICE = $("#DECT_PRICE"+rownum).val();
	var DECT_RATE = FloatDiv(DECT_RATE,PRICE);
	$("#DECT_RATE"+rownum).val(DECT_RATE);
}

function checkChild(){
	 var inputs = $("#jsontb tr:gt(0) input");
	 var flag = true;
	 inputs.each(function(){
		 var textType = $(this).attr("textType");
		 if("float" == textType){
			 if(!CheckFloatInput(this)){
				 flag = false;
				return false;
			}
		 }
		 if("int" == textType){
			 if(!CheckIntegerInput(this)){
				 flag = false;
				return false;
			}
		 }
		 
	 });
	 
	 return flag;
}


function url(rownum){
	var PRD_ID = $("#PRD_ID"+rownum).val();
	if(null == PRD_ID || "" == PRD_ID){
		parent.showErrorMsg("请先选择货品");
		return;
	}
	var CHANN_ID = parent.topcontent.getRECEIVE_CHANN_ID();
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	var STOREIN_NOTICE_DTL_ID = $("#STOREIN_NOTICE_DTL_ID"+rownum).val();
	var PRICE = $("#PRICE"+rownum).val();
	var url = "techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_STOREIN_NOTICE_DTL&BUSSID="+STOREIN_NOTICE_DTL_ID+"&citeUrl=editTechWithPrice&PRICE="+PRICE;
	//不验证渠道折扣
	var data = window.showModalDialog(url,window,"dialogwidth=800px; dialogheight=600px; status=no");
	//验证渠道折扣
	//var data=window.showModalDialog("techorderManager.shtml?action=editTechWithOutPrice&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	if(null == data){
		return;
	}
	if(data.SPCL_TECH_FLAG>0){
		$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有<input class='btn'  value='编辑' onclick='url("+rownum+")'  type='button' /></span>");
	}else{
		$("#SPECIAL_FLAG"+rownum).html("无");
	}
	$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
	$("#SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
}

//当键盘按键松开时触发事件
//输入折扣率时，计算折后单价、应收金额
 function count(i){
	 
//	if(!CheckFloatInput($("#DECT_RATE"+i))){
//		return;
//	}
	
	var TERM_DECT_FROM=$("#TERM_DECT_FROM").val();
	var temp_PRICE=$("#PRICE"+i).val();//单价
	var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
	if(isNaN(temp_DECT_RATE)||""==temp_DECT_RATE||null==temp_DECT_RATE){
		temp_DECT_RATE=0;
	}
	var temp_NOTICE_NUM=$("#NOTICE_NUM"+i).val();//订货数量
	//判断输入是否为数字，如果部为数字则默认为0
	var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
	var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
	var NOTICE_NUM=isNaN(temp_NOTICE_NUM)?0:parseFloat(temp_NOTICE_NUM);//订货数量
	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
	var DECT_AMOUNT = Math.round((isNaN(NOTICE_NUM*DECT_PRICE)?0:NOTICE_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
	$("#DECT_PRICE"+i).val(DECT_PRICE);
	$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
}
 
 
//输入折后单价时，计算折扣率和应收金额
function countDECT_RATE(i){
//	if(!CheckFloatInput($("#DECT_PRICE"+i))){
//		return
//	}
	var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
	if(isNaN(temp_DECT_PRICE)||""==temp_DECT_PRICE||null==temp_DECT_PRICE){
		temp_DECT_PRICE=0;
	}
	var temp_PRICE=$("#PRICE"+i).val();//单价
	var temp_NOTICE_NUM=$("#NOTICE_NUM"+i).val();//订货数量
	var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
	var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
	var NOTICE_NUM=isNaN(temp_NOTICE_NUM)?0:parseFloat(temp_NOTICE_NUM);//订货数量
	var DECT_AMOUNT = Math.round((isNaN(DECT_PRICE*NOTICE_NUM)?0:DECT_PRICE*NOTICE_NUM)*100)/100;//计算应收金额，保留2位小数
	var DECT_RATE = Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
	$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
	$("#DECT_RATE"+i).val(DECT_RATE);
	
}
	
	
	
	
//输入订货数量计算应收金额
function countNOTICE_NUM(i){
//	if(!CheckIntegerInput($("#NOTICE_NUM"+i))){
//		return;
//	}
	var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
	var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
	var temp_NOTICE_NUM=$("#NOTICE_NUM"+i).val();//订货数量
	if(isNaN(temp_NOTICE_NUM)||""==temp_NOTICE_NUM||null==temp_NOTICE_NUM){
		temp_NOTICE_NUM=0;
	}
	var NOTICE_NUM=isNaN(temp_NOTICE_NUM)?0:parseFloat(temp_NOTICE_NUM);//订货数量
	var DECT_AMOUNT = Math.round((isNaN(DECT_PRICE*NOTICE_NUM)?0:DECT_PRICE*NOTICE_NUM)*100)/100;//计算应收金额，保留2位小数
	$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
	var check=$("#IS_FREEZE_FLAG"+i).prop("checked");
	if(check){
		$("#FREEZE_NUM"+i).val(NOTICE_NUM);
	}
}
//输入应收金额后计算折后单价和 折扣率
function countDECT_AMOUNT(i){
//	if(!CheckFloatInput($("#DECT_AMOUNT"+i))){
//		return
//	}
	var temp_PRICE=$("#PRICE"+i).val();//单价
	var temp_NOTICE_NUM=$("#NOTICE_NUM"+i).val();//订货数量
	var temp_DECT_AMOUNT=$("#DECT_AMOUNT"+i).val();//应收金额
	if(isNaN(temp_DECT_AMOUNT)||""==temp_DECT_AMOUNT||null==temp_DECT_AMOUNT){
		temp_DECT_AMOUNT=0;
	}
	var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
	var NOTICE_NUM=isNaN(temp_NOTICE_NUM)?0:parseFloat(temp_NOTICE_NUM);//订货数量
	if(0==NOTICE_NUM){
		$("#DECT_PRICE"+i).val(0);
		$("#DECT_RATE"+i).val(0);
		countTotalMoney();
		return;
	}
	var DECT_AMOUNT=isNaN(temp_DECT_AMOUNT)?0:parseFloat(temp_DECT_AMOUNT);//应收金额
	var DECT_PRICE= Math.round((isNaN(DECT_AMOUNT/NOTICE_NUM)?0:DECT_AMOUNT/NOTICE_NUM)*100)/100;//计算折后单价，保留2位小数
	var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
//	$("#DECT_PRICE"+i).val(DECT_PRICE);
	$("#DECT_RATE"+i).val(DECT_RATE);
	DECT_PRICE = FloatMul(DECT_RATE,temp_PRICE);
	$("#DECT_PRICE"+i).val(DECT_PRICE);
}
	
	
	
	
/**
 * 浮点数的 乘法
 * @param {Object} arg1
 * @param {Object} arg2
 * @return {TypeName} 
 */
function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
} 


//浮点数除法运算   
function FloatDiv(arg1,arg2){   
	var t1=0,t2=0,r1,r2;   
	try{t1=arg1.toString().split(".")[1].length}catch(e){}   
	try{t2=arg2.toString().split(".")[1].length}catch(e){}   
	with(Math){   
		r1=Number(arg1.toString().replace(".",""))   
		r2=Number(arg2.toString().replace(".",""))   
		return (r1/r2)*pow(10,t2-t1);   
	}   
} 



//删除操作
function multiRecDeleteChilds(){s
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
	 
}

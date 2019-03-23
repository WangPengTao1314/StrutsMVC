/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit_Chld
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
	    //addRow();
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
			//检查 退货数量
			var f = clickSaveCheckNum();
			if(!f){
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
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
});

//主子表保存
function allSave(){
	var re = parent.topcontent.checkFormSelect();
	if(!re){
		return;
	}
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//检查 退货数量
	var f = clickSaveCheckNum();
	if(!f){
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
		url: "prdreturnreq.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"PRD_RETURN_REQ_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","prdreturnreq.shtml?action=toFrame");
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
		url: "prdreturnreq.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"PRD_RETURN_REQ_ID":selRowId},
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
		url: "prdreturnreq.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"PRD_RETURN_DTL_REQ_IDS":ids},
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
function gobacknew()
{
   newGoBack("prdreturnreq.shtml?action=toFrame");
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
	rownum=_row_num;_row_num++;

	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_RETURN_DTL_REQ_ID' name='PRD_RETURN_DTL_REQ_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            //.append('<td nowrap align="left"><input  json="FROM_BILL_ID" id="FROM_BILL_ID'+rownum+'" name="FROM_BILL_ID'+rownum+'"  autocheck="true" label="来源单据ID"  type="hidden" value= "'+arrs[1]+'"/>' +
           // '<input type="hidden" id="FROM_BILL_NO_OLD'+rownum+'" value="'+arrs[2]+'"/> <input  json="FROM_BILL_NO" id="FROM_BILL_NO'+rownum+'" size="15" name="FROM_BILL_NO'+rownum+'"  autocheck="true" label="来源单据编号" onchange="clearPrd('+rownum+');" type="text"   inputtype="string"   readonly     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selFROM_BILL('+rownum+');clearRowPrd('+rownum+');"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" value="'+arrs[3]+'"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO" size="10" autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="32"  value="'+arrs[4]+'"/>&nbsp;' +
            '<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="selProduct('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"  style="display: none;"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌" size="5" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="5" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[9]+'"/>&nbsp;</td>')
 			.append('<td nowrap align="center"><span id="SPECIAL_FLAG_SCRN'+rownum+'"></span><input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  autocheck="true" label="特殊工艺ID"  type="hidden" value="'+arrs[16]+'"/><input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  autocheck="true" label="特殊工艺标记"  type="hidden" value="'+arrs[17]+'"/></td>')
            .append('<td nowrap align="left"><input  json="RETURN_PRICE" id="RETURN_PRICE'+rownum+'" name="RETURN_PRICE"  autocheck="true" label="退货单价" size="5" type="text"   valueType="8,2" onkeyup="checkNum('+rownum+')" inputtype="float"   maxlength="22" mustinput="true"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_NUM" id="RETURN_NUM'+rownum+'" name="RETURN_NUM'+rownum+'"  autocheck="true" label="退货数量"  type="text" size="5"   onkeyup="checkNum('+rownum+')" inputtype="float" valueType="8,2"  mustinput="true"     maxlength="22"  value="'+arrs[11]+'"/>&nbsp;<input type="hidden" id="MAX_RETURN_NUM'+rownum+'" name="MAX_RETURN_NUM" value="'+arrs[18]+'" /></td>')
            .append('<td nowrap align="left"   ><input  json="RETURN_AMOUNT" id="RETURN_AMOUNT'+rownum+'" name="RETURN_AMOUNT'+rownum+'" size="5" autocheck="true" label="退货金额"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><select  json="RETURN_RSON_TYPE" id="RETURN_RSON_TYPE'+rownum+'" name="RETURN_RSON_TYPE"  style="width:80px" autocheck="true" label="原因归类"    mustinput="true"  inputtype="string"  maxlength="30"  ></select></td>')
            .append('<td nowrap align="left"><input  json="RETURN_RSON" id="RETURN_RSON'+rownum+'" name="RETURN_RSON'+rownum+'"  autocheck="true" label="退货原因"  type="text"   inputtype="string"   mustinput="true"     maxlength="250"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="USE_TIME" id="USE_TIME'+rownum+'" name="USE_TIME"  autocheck="true" label="使用时间"  type="text"   inputtype="float" size="5" valueType="8,2"   mustinput="true"     maxlength="100"  value="'+arrs[19]+'"/>&nbsp;(月)</td>')
            .append('<td nowrap align="left">' +
            			'<input  json="SHIP_POINT_ID" id="SHIP_POINT_ID'+rownum+'" name="SHIP_POINT_ID"  autocheck="true" label="发货点ID"  type="hidden"   inputtype="string"     maxlength="100"  value="'+arrs[20]+'"/>' +
            			'<input  json="SHIP_POINT_NO" id="SHIP_POINT_NO'+rownum+'" name="SHIP_POINT_NO"  autocheck="true" label="发货点NO"  type="hidden"   inputtype="string"       value="'+arrs[21]+'"/>'+
            			'<input  json="SHIP_POINT_NAME" id="SHIP_POINT_NAME'+rownum+'" name="SHIP_POINT_NAME"  autocheck="true" label="生产基地"  type="text" readonly  inputtype="string"      mustinput="true"   value="'+arrs[22]+'"/>'+
            			"<img  align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcShip("+rownum+")'/>"+
            		'</td>')
            .append('<td nowrap align="left"><input  json="PRDC_DATE" id="PRDC_DATE'+rownum+'"  name="PRDC_DATE"  autocheck="true" label="生产日期"  type="text"  onclick="SelectDate();" inputtype="date"   readonly    mustinput="true"   value="'+arrs[23]+'"/>&nbsp;' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(PRDC_DATE'+rownum+');"></td>')
            .append('<td nowrap align="left" width="300"><input  json="RETURN_ATT" id="RETURN_ATT'+rownum+'" name="RETURN_ATT'+rownum+'"  autocheck="true" label="退货附件"  type="text"   inputtype="string"       maxlength="100"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<input type="hidden" id="STORE_ID'+rownum+'" json="STORE_ID" name="STORE_ID'+rownum+'"  inputtype="string" value=""  /> ')
            .append('<input type="hidden" id="CHECKONLY'+rownum+'" json="CHECKONLY" name="CHECKONLY"  inputtype="string" value="'+arrs[24]+'"  /> ')
              ;
		setSpecTech(rownum);
		SelDictShow("RETURN_RSON_TYPE"+rownum,"BS_168",arrs[13],"");
		uploadFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


//来源单据通用选取
function selFROM_BILL(rowNum){
    var RECV_CHANN_ID = '';
    var LEDGER_ID = "";
	var actionType = parent.document.getElementById("actionType").value;
	if('edit' == actionType){
		//主表收货方ID
		 RECV_CHANN_ID=parent.topcontent.document.getElementById("RECV_CHANN_ID").value;
		 //当前帐套ID
		 LEDGER_ID = $("#LEDGER_ID").val();
		
	}else if ('list' == actionType){
		RECV_CHANN_ID = $(window.parent.topcontent.document).find("input[name='eid']:checked").attr('recvChannId');
		//当前帐套ID
		 LEDGER_ID = $("#LEDGER_ID",window.parent.document).val();
	}
	
	if(null == RECV_CHANN_ID || '' ==RECV_CHANN_ID){
		chkCanErrMsg($("#LEDGER_ID"), "请输入收货方编号!");
		return;
	}
	var selectCondition =" STATE='已处理' and LEDGER_ID ='"+LEDGER_ID+"' and  SEND_CHANN_ID='"+RECV_CHANN_ID+"' and BILL_TYPE='订货入库' and RECV_CHANN_ID='"+LEDGER_ID+"'";
	$("#selectCondition").val(selectCondition);
	selCommon("BS_36", false, "FROM_BILL_ID"+rowNum, "STOREIN_ID", "forms[0]","FROM_BILL_NO"+rowNum, "STOREIN_NO",'selectCondition');
	$("#PRD_NO"+rowNum).focus();
 
}
//货品通用选取
function selProduct(rowNum){
	//对应行的来源单据ID
	var FROM_BILL_ID = $("#FROM_BILL_ID"+rowNum).val();
	//当前帐套ID
	var LEDGER_ID = "";
	var RETURN_STORE_ID = parent.topcontent.getStoreId();
//	var actionType = parent.document.getElementById("actionType").value;
//	if('edit' == actionType){
//		//主表的退货出库库房ID
//		 RECV_CHANN_ID=parent.topcontent.document.getElementById("RETURN_STORE_ID").value;
//		 //当前帐套ID
//		   = $("#LEDGER_ID").val();
//	}else if ('list' == actionType){
//		RECV_CHANN_ID = $(window.parent.topcontent.document).find("input[name='eid']:checked").attr('returnStoreId');
//		//当前帐套ID
//		LEDGER_ID = $("#LEDGER_ID",window.parent.document).val();
//	}
	if(null == RETURN_STORE_ID || "" == RETURN_STORE_ID){
		chkCanErrMsg($("#LEDGER_ID"), "请输入退货出库库房编号!");
		return;
	}
//	if(null == FROM_BILL_ID || '' ==FROM_BILL_ID){
//		selCommon("BS_21", false, "PRD_ID"+rowNum, "PRD_ID", "forms[0]",
//			"PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum+",PRD_COLOR"+rowNum+",BRAND"+rowNum+",STD_UNIT"+rowNum, 
//			"PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT", "selectParams");
//		return;
//	}
	
//	$("#selectPrd").val(" STORE_ID='"+RETURN_STORE_ID+"'  ");
//		selCommon("BS_37", false, "CHECKONLY"+rowNum, "CHECKONLY", "forms[0]",
//			"PRD_ID"+rowNum+",PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum+",PRD_COLOR"+rowNum+",BRAND"+rowNum+",STD_UNIT"+rowNum+",RETURN_PRICE"+rowNum+",MAX_RETURN_NUM"+rowNum+",SPCL_TECH_ID"+rowNum+",SPCL_TECH_FLAG"+rowNum+",STORE_ID"+rowNum, 
//			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,DECT_PRICE,MAX_RETURN_NUM,SPCL_TECH_ID,SPCL_TECH_FLAG,STORE_ID","selectPrd");
//	
	
	 $("#selectPrd").val(" STORE_ID='"+RETURN_STORE_ID+"'  ");
		selCommon("BS_176", false, "CHECKONLY"+rowNum, "CHECKONLY", "forms[0]",
			"PRD_ID"+rowNum+",PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum+",PRD_COLOR"+rowNum+",BRAND"+rowNum+",STD_UNIT"+rowNum+",MAX_RETURN_NUM"+rowNum+",SPCL_TECH_ID"+rowNum+",SPCL_TECH_FLAG"+rowNum+",STORE_ID"+rowNum+",RETURN_PRICE"+rowNum,
			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,MAX_RETURN_NUM,SPCL_TECH_ID,SPCL_TECH_FLAG,STORE_ID,RETURN_PRICE","selectPrd");
		
    setSpecTech(rowNum);
}

function setSpecTech(rownum){
	var spclTechFlag =  $("#SPCL_TECH_FLAG"+rownum).val();
	if(spclTechFlag !='1' && spclTechFlag !='2'){
		$("#SPECIAL_FLAG_SCRN"+rownum).text('无');
	}else{
		$("#SPECIAL_FLAG_SCRN"+rownum).html('<font  style="color: red">有</font><input class="btn" value="查看" onclick="showSpecTech('+rownum+')"  type="button" />&nbsp;');
	}
}

function checkNum(rowNum){
	//退货数量
	var returnNum = $("#RETURN_NUM"+rowNum).val();
	if(""==returnNum||null==returnNum){
		returnNum=0;
	}
	returnNum=isNaN(returnNum)?0:parseFloat(returnNum);
	//退货单价
	var RETURN_PRICE=$("#RETURN_PRICE"+rowNum).val();
	if(""==RETURN_PRICE||null==RETURN_PRICE){
		RETURN_PRICE=0;
	}
	RETURN_PRICE=isNaN(RETURN_PRICE)?0:parseFloat(RETURN_PRICE);
	
	//最大退货量
	var maxReturnNum = $("#MAX_RETURN_NUM"+rowNum).val();
	var FROM_BILL_ID=$("#FROM_BILL_ID"+rowNum).val();
	maxReturnNum = parseInt(maxReturnNum);
	if(""!=FROM_BILL_ID&&null!=FROM_BILL_ID&&isNaN(maxReturnNum)){
		maxReturnNum = 0
	}
	if(parseInt(returnNum) > maxReturnNum){
		chkCanErrMsg($("#LEDGER_ID"), "退货数量不能大于最大退货量");
		$("#RETURN_AMOUNT"+rowNum).val("");
		//$("#RETURN_NUM"+rowNum).focus();
	}else{
		var account = Math.round((isNaN(returnNum*RETURN_PRICE)?0:returnNum*RETURN_PRICE)*100)/100;
		$("#RETURN_AMOUNT"+rowNum).val(account);
	}
}

function formCheckedEx() {
	
	var flag = true;
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
			var select = $(this).find("select:first");
			if('' == select[0].value){
				chkCanErrMsg("", "请选择'原因归类'!");
				flag = false;
				return false;
			}

		}
	});
	return flag;
}
//主表 收货方改变的时候 清除所有货品信息
function clearAllPrd(){
	var length = $("#jsontb tr:gt(0)").length;
	for(var rowNum=1;rowNum<=length; rowNum++){
		$("#FROM_BILL_ID"+rowNum).val("");
		$("#FROM_BILL_NO"+rowNum).val("");
		clearPrdVal(rowNum);
	}
}
//来源单据 变化的时候 清除 货品信息
function clearRowPrd(rowNum,isParent){
	var FROM_BILL_NO_OLD = $("#FROM_BILL_NO_OLD"+rowNum).val();
	var FROM_BILL_NO = $("#FROM_BILL_NO"+rowNum).val();
 
	if(FROM_BILL_NO == FROM_BILL_NO_OLD){
		return;
	}
	 
	clearPrdVal(rowNum);
	
}

function clearPrdVal(rowNum){
	$("#PRD_ID"+rowNum).val("");
	$("#PRD_NO"+rowNum).val("");
	$("#PRD_NAME"+rowNum).val("");
	$("#PRD_SPEC"+rowNum).val("");
	$("#PRD_COLOR"+rowNum).val("");
	$("#BRAND"+rowNum).val("");
	$("#STD_UNIT"+rowNum).val("");
	$("#SPECIAL_FLAG_SCRN"+rowNum).html("");
	$("#SPECIAL_FLAG_SCRN"+rowNum).text('无');
	$("#SPCL_TECH_ID"+rowNum).val("");
	$("#SPCL_TECH_FLAG"+rowNum).val("");
	
	$("#RETURN_PRICE"+rowNum).val("");
	$("#RETURN_NUM"+rowNum).val("");
	$("#MAX_RETURN_NUM"+rowNum).val("");
	$("#RETURN_AMOUNT"+rowNum).val("");
	$("#RETURN_RSON_TYPE"+rowNum).val("");
	$("#RETURN_RSON"+rowNum).val("");
	$("#RETURN_ATT"+rowNum).val("");
	
	var RETURN_ATT = document.getElementById("RETURN_ATT"+rowNum+"UploadertheCancel");
	if(null != RETURN_ATT){
		RETURN_ATT.click();
	}
	
}


//点击保存的时候 检查退货数量不能大于最大退货数
function clickSaveCheckNum(){
	var flag = true;
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
		    var STD_UNIT = $(this).find("input[json='STD_UNIT']").val();
		    STD_UNIT = $.trim(STD_UNIT);
		    if(null == STD_UNIT || "" == STD_UNIT){
		    	return false;
		    }
			var RETURN_NUM = $(this).find("input[json='RETURN_NUM']").val();
			var MAX_RETURN_NUM = $(this).find("input[name='MAX_RETURN_NUM']").val();
			var FROM_BILL_NO=$(this).find("input[json='FROM_BILL_NO']").val();
			var RETURN_PRICE=$(this).find("input[json='RETURN_PRICE']").val();
			if(""!=FROM_BILL_NO&&null!=FROM_BILL_NO&&parseInt(RETURN_NUM) > parseInt(MAX_RETURN_NUM)){
				chkCanErrMsg($("#LEDGER_ID"), "退货数量不能大于最大退货量");
				flag = false;
				return false;
			}
			if(""!=FROM_BILL_NO&&null!=FROM_BILL_NO){
				if(""==RETURN_PRICE||null==RETURN_PRICE){
				chkCanErrMsg($("#LEDGER_ID"), "有来源单据时退货单价必填！");
				flag = false;
				return false;
				}
				
			}
		}
	});
	return flag;
}
 
function showSpecTech(rowNum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rowNum).val();
	window.showModalDialog('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	
}
function selcShip(rownum){
	selCommon("BS_22", false, "SHIP_POINT_ID"+rownum, "SHIP_POINT_ID", "forms[0]","SHIP_POINT_ID"+rownum+",SHIP_POINT_NO"+rownum+",SHIP_POINT_NAME"+rownum,
		"SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME", "selShip");
}

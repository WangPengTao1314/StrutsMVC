/**
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核 明细 列表
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["edit","delete"]);
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		if (state != "未提交" && state != "\u9000\u56de" && state != "\u5426\u51b3" && state != "\u64a4\u9500") {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
			return;
		}
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});

	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#SALE_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","saleorderrls.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb input[name='trCheckBox']:checkbox").attr("checked","true");
		}else{
			$("#ordertb input[name='trCheckBox']:checkbox").removeAttr("checked");
		}
	});
	setSelOperateEx();
	
	//取消预订
	$("#cancel").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		toCancelOrderEdit();
	});
	//恢复预订
	$("#recover").click(function(){
		recover();
	});
	$("#save").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var selRowId = parent.document.getElementById("selRowId").value;
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		if (state != "未提交" ) {
			parent.showErrorMsg("当前主表状态下，不能保存明细！");
			return;
		}
		childSave();
	});
	
	changeColor();
	
	
});

function toCancelOrderEdit(){
	//获取所有选中的记录
	var selRowId = parent.document.getElementById("selRowId").value;
	var ids = "";
	var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
//	BILL_TYPE = utf8(BILL_TYPE);
//    window.open("saleorderrls.shtml?action=toCancelOrderEdit&SALE_ORDER_DTL_IDS="+ids+"&SALE_ORDER_ID="+selRowId+"&BILL_TYPE="+BILL_TYPE,"取消货品明细","height=400, width=1250, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
	$("#SALE_ORDER_DTL_IDS").val(ids);
	$("#BILL_TYPE").val(BILL_TYPE);
	$("#SALE_ORDER_ID").val(selRowId);
	
    window.open('about:blank','newWin','height=400, width=1250, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no'); 
    $("#form1").attr("action","saleorderrls.shtml?action=toCancelOrderEdit");
    $("#form1").attr("method","post");
    $("#form1").attr("target","newWin");
    $("#form1").submit();  

}



//转标准订单
function convert(){
	var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
	var ids = "";
	var leg = checkBox.length;
	if(leg>0){
		//获取所有选中的记录
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
	}else{
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	var resultSize = $("#resultSize").val();
	var isAll = false;
	if(leg>=resultSize){
		isAll = true;
	}
	 
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "saleorderrls.shtml?action=convert",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_DTL_IDS":ids,"isAll":isAll},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"saleorderrls.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//取消预定
function cancel(){
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "saleorderrls.shtml?action=cancelOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function recover(){
	//selCommon("BS_59", false, "SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID","forms[0]","PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,ORDER_NUM,ORDER_AMOUNT", "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,ORDER_NUM,ORDER_AMOUNT", "selectContion")
    
	var selRowId = parent.document.getElementById("selRowId").value;
	$("#selectContion").val(" SALE_ORDER_ID='"+selRowId+"' and CANCEL_FLAG=1 ");
	
	var returnArr = selCommon("BS_61", false, "SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID","forms[0]","SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID", "selectContion")
    var isSelect = returnArr[1];
	if(!isSelect){
		return;
	}
 
    var SALE_ORDER_DTL_ID = $("#SALE_ORDER_DTL_ID").val();
	$.ajax({
		url: "saleorderrls.shtml?action=recoverOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_DTL_ID":SALE_ORDER_DTL_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"saleorderrls.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});

}




function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input[name='trCheckBox']:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "saleorderrls.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate) {
		var state = vstate.value;
		if(state == "已取消" || state == "已完成") {
			btnDisable(["cancel"]);
		}
		
		if(state != "未提交") {
			btnDisable(["save"]);
		}
	}
}



function url(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
//查询库存
function queryStore(PRD_NO,SPCL_TECH_ID,rowNum){
	$("#ordertb div[name='showDiv']").hide();
	var selRowId = parent.document.getElementById("selRowId").value;
	var ORDER_CHANN_NO = parent.topcontent.$("#ORDER_CHANN_NO"+selRowId).val();
	var SHIP_POINT_ID = parent.topcontent.$("#SHIP_POINT_ID"+selRowId).val();
	 
	$.ajax({
		url: "goodsorder.shtml?action=queryStore",
		type:"POST",
		dataType:"text",
		data:{"PRD_NO":PRD_NO,"SPCL_TECH_ID":SPCL_TECH_ID,"ORDER_CHANN_NO":ORDER_CHANN_NO,"SHIP_POINT_ID":SHIP_POINT_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			var TOTAL_STORE = 0;
			if(jsonResult.success===true){
				var data  = jsonResult.data;
				TOTAL_STORE = data.TOTAL_STORE;
				if(null == TOTAL_STORE || "" == TOTAL_STORE){
					TOTAL_STORE = 0;
				}
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			
			$("#showSpan"+rowNum).text(TOTAL_STORE);
			$("#showDiv"+rowNum).show();
		}
	});
}
//check是否非标 非标不能备货
function checkIsFB(obj,IS_NO_STAND_FLAG,id){
	 if("1" == IS_NO_STAND_FLAG){
		 parent.showErrorMsg("非标货品不能抵库");
		 if($(obj).attr("checked")){
			 $(obj).removeAttr("checked");
		 }
	 }
	 $("#"+id).prop("checked",false);
}
//子表保存
function childSave(){
	var jsonData = multiRadioJsonData("ordertb");
	$.ajax({
		url: "saleorderrls.shtml?action=childSave",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				if(mergeFlage){
					$("#queryForm").submit();
				}else{
					parent.window.gotoBottomPage("label_1");
				}
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

 
//是否取消过标记 标颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var IS_CANCELED_FLAG = $(this).find("input[name='IS_CANCELED_FLAG']").val();
		var CANCEL_FLAG = $(this).find("input[name='CANCEL_FLAG']").val();
		CANCEL_FLAG = parseInt(CANCEL_FLAG);
		IS_CANCELED_FLAG = parseInt(IS_CANCELED_FLAG);
		if(IS_CANCELED_FLAG == 1 || 1 == CANCEL_FLAG){
			var id = $(this).attr("id");
		    $(this).find("td").css("background-color", "#E6B9B8");//#F3F3F3花号还原
		}
	});
	
}

function hidIself(obj){
	$(obj).hide();
}



/**
 * 拼装 子表的json 
 * @param {Object} tableid 表ID
 * @param {Object} isAll true->无视是否选择 拼接所有
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function multiRadioJsonData(tableid,isAll){
	if(null == tableid){
		tableid = "jsontb";
	}
	if(null == isAll){
		isAll = false;
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = false;
		if(isAll){
			isEdit = true;
		}else{
			isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
			//isEdit = $(this).find("input[type='radio']:eq(0)").attr("checked");
		}
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
//			var inputs = $(this).find(":input");
			var tds = $(this).find("td");
			tds.each(function(){
				var inputs = $(this).find(":input");
				if(inputs.length>0){
					inputs.each(function(){
						if(null != $(this).attr("json")){
							var key;
							var value;
							var type = $(this).attr("type");
							if("checkbox" == type){
								key = $(this).attr("json");
								if($(this).attr("checked")){
									value= 1;
								}else{
									value= 0;
								}
							}else if("radio" == type){
								if($(this).attr("checked")){
									key = $(this).attr("json");
									value= $(this).attr("value");
								}
							}else{
								key = $(this).attr("json");
								value = $(this).attr("value");
								
								isFirst = false;
							}
							var inputtype = $(this).attr("inputtype");
							jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
						}
					});
				}else{
					if(null != $(this).attr("json")){
						var k = $(this).attr("json");
					    var text = $.trim($(this).text());
					    jsonData = jsonData+ "'" + k + "':'" + text +"',";
				    }
				}
				
				
			});
			
		
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}
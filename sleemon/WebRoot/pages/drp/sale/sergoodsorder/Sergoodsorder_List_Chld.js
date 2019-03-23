/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_List_Chld
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});

	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#GOODS_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","sergoodsorder.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
//	//取消
//	$("#bussClose").click(function(){
//		//查找当前是否有选中的记录
//		var checkBox = $("#ordertb tr:gt(0) input[name='mx']:checked"); //加上name 防止点击其他的checkbox 也认为是选中
//		if(checkBox.length<1){
//			parent.showErrorMsg("请至少选择一条记录");
//			return;
//		}
//		parent.showConfirm("您确认要取消吗?","bottomcontent.closeFn();","N");
//	});
		
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb [name='mx']:checkbox").attr("checked","true");
		}else{
			$("#ordertb [name='mx']:checkbox").removeAttr("checked");
		}
	});
	setSelOperateEx();
	
	changeColor();
});


 
//取消 订货
function closeFn(){
	var GOODS_ORDER_ID =  getSelRowId();
	var resultSize = $("#resultSize").val();
	var NO = parent.topcontent.$("#GOODS_ORDER_NO"+GOODS_ORDER_ID).val();
	var ids = "";
	var prdNo = "";
	//明细的条数
	var mxSize = $("#ordertb tr:gt(0) input[name='mx']").length;
	//选中的明细
	var checkBox = $("#ordertb tr:gt(0) input[name='mx']:checked");
	var leg = checkBox.length;
	var isAll = false;
    if(mxSize == leg){
    	isAll = true;
    }
 
	checkBox.each(function(){
		var id = $(this).val();
		ids = ids+"'"+id+"',";
		prdNo = $(this).parent().parent().find("td[json='PRD_NO']").text();
		prdNo = $.trim(prdNo);
	});
	ids = ids.substr(0,ids.length-1);
	var remark = "单据'"+NO+"'下，货品'"+prdNo+"'已取消订货";
 
	$.ajax({
	 	url: "sergoodsorder.shtml?action=orderClose",
		type:"POST",
		data:{"GOODS_ORDER_ID":GOODS_ORDER_ID,"mxIds":ids,"remark":remark,"isAll":isAll},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("取消成功");
				checkBox.parent().parent().remove();
				parent.topcontent.sendMessage(remark);
				countAmount();
				saveSuccess("取消成功","sergoodsorder.shtml?action=toFrame");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

///设置 一览页面 ‘订货总额’
function countAmount(){
	var selRowId = getSelRowId();
	var tds = $("#ordertb").find("td[json='ORDER_AMOUNT']");
	var total = 0;
	tds.each(function(){
		total = Number(total) + Number($.trim($(this).text()));
	});
	parent.topcontent.setTotalAmount(total);
}


//总部 销售订单 取消的 明细 特殊颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var CANCEL_FLAG = $(this).find("input[name='CANCEL_FLAG']").val();
		if(CANCEL_FLAG == 1){
			var id = $(this).attr("id");
		    $(this).find("td").css("background-color", "#E6B9B8");//#F3F3F3花号还原
		}
	});
	
}

function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "sergoodsorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_DTL_IDS":ids},
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


//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRD_ID,rownum){
    var selRowId = getSelRowId();
	var CHANN_ID = parent.topcontent.getORDER_CHANN_ID();
	var PRICE=$("#PRICE"+rownum).val();
    if(null == SPCL_TECH_ID || "" == SPCL_TECH_ID){
    	SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
    }
 	var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_GOODS_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
    if(null == data){
		return;
	}
    var SPCL_TECH_FLAG = data.SPCL_TECH_FLAG;
	if(SPCL_TECH_FLAG!=0){
		$("#SPECIAL_FLAG" + rownum).html("<font style='color: red'>有</font>");
	}else{
		$("#SPECIAL_FLAG" + rownum).html("无");
	}
	$("#SPCL_TECH_ID" + rownum).val(data.SPCL_TECH_ID);
	
	var PRICE = data.PRICE;//单价
	var DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
	
	$("#PRICE"+rownum).val(PRICE);//单价
	//$("#OLD_PRICE"+rownum).val(data.PRICE);//原价格
	$("#DECT_PRICE"+rownum).text(DECT_PRICE);//折后单价
	
	if(SPCL_TECH_FLAG == 2){
		$("#IS_NO_STAND_FLAG"+rownum).val("1");//非标
		$("#IS_NO_STAND_FLAG_TD"+rownum).text("是");
	}else{
		$("#IS_NO_STAND_FLAG"+rownum).val("0");//标准
//		$("#IS_NO_STAND_FLAG_CHECKBOX"+rownum).removeAttr("checked");
		$("#IS_NO_STAND_FLAG_TD"+rownum).text("否");
	}
	
	
	var num = $.trim($("#ORDER_NUM"+rownum).text());
	var ORDER_AMOUNT = Number(DECT_PRICE)*Number(num);
	$("#ORDER_AMOUNT"+rownum).text(ORDER_AMOUNT);
	
	
    //查找当前是否有选中的记录
	var tds = $("#ordertb tr:gt(0) td[name='ORDER_AMOUNT']");
	var t = 0;
	tds.each(function(){
		 var v = $.trim($(this).text());
		 t = Number(t)+Number(v);
	});
	 
	parent.topcontent.$("#TOTAL_AMOUNT"+selRowId).text(t);
	childSave();
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiRadioJsonData("ordertb",true);
	 
	$.ajax({
		url: "sergoodsorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"GOODS_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}


function showTechPage(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
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


function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态!=未处理
		if (state != "未处理") {
			btnDisable(["bussClose"]);
		}
	}
}



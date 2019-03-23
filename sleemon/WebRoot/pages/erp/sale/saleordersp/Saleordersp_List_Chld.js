/**
 * @prjName:喜临门营销平台
 * @fileName:非标订单明细 列表
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
		$("#SALE_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","saleordersp.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
	setSelOperateEx();
	
	//转标准订单
	$("#convert").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		var leg = checkBox.length;
		var index = 0;
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		checkTechOrder();
	});
	
	//取消订单
	$("#cancel").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		var leg = checkBox.length;
		var index = 0;
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}else{
			var HID_IS_CAN_PRD_FLAG = $(this).parent().parent().find("input[name='HID_IS_CAN_PRD_FLAG']").val();
			HID_IS_CAN_PRD_FLAG = parseInt(HID_IS_CAN_PRD_FLAG);
			if(HID_IS_CAN_PRD_FLAG == 1){
				index = parseInt(index)+1;
			}
		}
		
		if(index>0 && index == leg){
			parent.showErrorMsg("货品可生产不可取消");
			return;
		}
		parent.showConfirm("您确认要取消预订吗","bottomcontent.cancel();","N");
	});
	$("#recover").click(function(){
		recover();
	});
	
	//如果 对应的工艺单 不生产 提交按钮置灰
	//checkCanPrdFlag();
	
});

//转标准订单
function convert(){
	var flag = true;
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	var ids = "";
	var leg = checkBox.length;
	if(leg>0){
		//获取所有选中的记录
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
			var SPCL_TECH_FLAG = $(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
			SPCL_TECH_FLAG = parseInt(SPCL_TECH_FLAG);
			if(isNaN(SPCL_TECH_FLAG)){
				SPCL_TECH_FLAG = 0;
			}
			if(SPCL_TECH_FLAG>1){
				flag = false;
				return false;
			}
		});
		ids = ids.substr(0,ids.length-1);
	}
	
	if(!flag){
		parent.showErrorMsg("有非标的特殊工艺，不能转标准订单，请修改");
		return;
	}
	var resultSize = $("#resultSize").val();
	var isAll = false;
	if(leg>=resultSize){
		isAll = true;
	}
	 
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "saleordersp.shtml?action=convert",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_DTL_IDS":ids,"isAll":isAll},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				saveSuccess(utf8Decode(jsonResult.messages),"saleordersp.shtml?action=toFrame");
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					parent.topcontent.$("#queryForm").submit();
				});
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					parent.topcontent.$("#queryForm").submit();
				});
			}
		}
	});
}

//取消预定
function cancel(){
	var selRowId = parent.document.getElementById("selRowId").value;
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	
	//获取所有选中的记录
	var ids = "";
	var formIds = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
		var formId = $(this).parent().find("input[name='FROM_BILL_DTL_ID']").val();
		if(null != formId &&　"" != formId){
			formIds = formIds+"'"+formId+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	formIds = formIds.substr(0,formIds.length-1);
	$.ajax({
		url: "saleordersp.shtml?action=cancelOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids,"SALE_ORDER_ID":selRowId,"FROM_BILL_DTL_IDS":formIds},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				checkBox.parent().parent().remove();
				countAmount();
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					parent.topcontent.$("#pageForm").submit();
				});
				//saveSuccess(utf8Decode(jsonResult.messages),"saleordersp.shtml?action=toFrame");
				//parent.topcontent.$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function recover(){
	var selRowId = parent.document.getElementById("selRowId").value;
	$("#selectContion").val(" SALE_ORDER_ID='"+selRowId+"' and CANCEL_FLAG=1 ");
	var returnArr = selCommon("BS_61", true, "SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID","forms[0]","SALE_ORDER_DTL_IDS,FROM_BILL_DTL_IDS", "SALE_ORDER_DTL_ID,FROM_BILL_DTL_ID", "selectContion")
    var isSelect = returnArr[1];
	if(!isSelect){
		return;
	}
 
    var SALE_ORDER_DTL_IDS = $("#SALE_ORDER_DTL_IDS").val();
    var FROM_BILL_DTL_IDS = $("#FROM_BILL_DTL_IDS").val();
    
	$.ajax({
		url: "saleordersp.shtml?action=recoverOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_DTL_IDS":SALE_ORDER_DTL_IDS,"FROM_BILL_DTL_IDS":FROM_BILL_DTL_IDS},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"saleordersp.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});

}


///设置 一览页面 ‘订货总额’
function countAmount(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var inputs = $("#ordertb").find("input[name='HID_ORDER_AMOUNT']");
	var total = 0;
	inputs.each(function(){
		total = Number(total) + Number($(this).val());
	});
	parent.topcontent.setTotalAmount(total);
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
		url: "saleordersp.shtml?action=childDelete",
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




//查询 对应的 工艺单 的状态 是 “已核价” 才能提交
function checkTechOrder(){
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "saleordersp.shtml?action=queryTechSate",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var sate = utf8Decode(jsonResult.data.STATE);
				var techNo = jsonResult.data.TECH_ORDER_NO;
				if("已核价" == sate){
					 parent.showErrorMsg("该单据'已核价'不能做修改");
					 btnDisable(["convert","cancel","recover"]);
				}else{
					//查找当前是否有选中的记录
					var checkBox = $("#ordertb tr:gt(0) input:checked");
					if(checkBox.length<1){
						parent.showErrorMsg("请至少选择一条记录");
						return;
					}
					parent.showConfirm("您确认要转标准订单吗","bottomcontent.convert();","N");
				}
			}
		}
	});
}



 
function checkCanPrdFlag(){
	var inputs = $("#ordertb tr:gt(0) input[name='HID_IS_CAN_PRD_FLAG']");
	var resultSize = $("#resultSize").val();
	var i = 0;
	inputs.each(function(){
		var v = $.trim($(this).val());
		if("0" == v || 0==v){
			i++;
		}
	});
	if(i == resultSize){
			 
		parent.topcontent.disableCommit();
	}else{
 
		//parent.topcontent.removeDisable(["commit"]);
		//有可生产的货品，就不能转标准订单
		btnDisable(["convert","recover"]);
	}
}
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		var TECH_STATE = parent.topcontent.$("#TECH_STATE"+selRowId).val();
		if(state != "待核价"){
			btnDisable(["convert","recover"]);
		}
		 
	}
}

//编辑特殊工艺
function selectTechPage(SALE_ORDER_DTL_ID,SPCL_TECH_ID,PRD_ID,PRICE,DECT_RATE,ORDER_NUM){
	var selRowId = parent.document.getElementById("selRowId").value;
	var ORDER_CHANN_ID = parent.topcontent.$("#ORDER_CHANN_ID"+selRowId).val();
	var url = "techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+ORDER_CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=ERP_SALE_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+PRICE;
	var data = window.showModalDialog(url,window,"dialogwidth=800px; dialogheight=600px; status=no");
	if(null == data){
		return;
	}
	
    var PRICE = data.PRICE;//单价
    var DECT_PRICE = FloatMul(PRICE,DECT_RATE);
    var ORDER_AMOUNT = FloatMul(DECT_PRICE,ORDER_NUM);
	var jsonData = "[{'SALE_ORDER_DTL_ID':'"+SALE_ORDER_DTL_ID+"','PRICE':'"+PRICE+"','DECT_RATE':'"+DECT_RATE+"','DECT_PRICE':'"+DECT_PRICE+"','ORDER_AMOUNT':'"+ORDER_AMOUNT+"'}]"
 
	updateChildPrice(jsonData);
	 
}
//查看特殊工艺
function url(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}



//修改过 特殊工艺点击保存的时候 页面要回填相关字段 同时更新数据库相关字段
function updateChildPrice(jsonData){
//	//返利标记
//	var IS_USE_REBATE = parent.topcontent.getIS_USE_REBATE();
//	 //返利折扣
//	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	$.ajax({
		url: "saleordersp.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			parent.$("#label_1").click(); 
		}
	});
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



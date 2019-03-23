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
    headColumnSort("ordertb","ordertbForm");
	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
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
			$("#ordertb input[name='mx_checkbox']:checkbox").attr("checked","true");
			justMX();
		}else{
			$("#ordertb input[name='mx_checkbox']:checkbox").removeAttr("checked");
		}
	});
	setSelOperateEx();
	
	 
	$("#save").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var selRowId = parent.document.getElementById("selRowId").value;
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		if (state != "审核通过" ) {
			parent.showErrorMsg("当前主表状态下，不能保存明细！");
			return;
		}
		childSave();
	});
	
	$("#close").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要关闭吗","bottomcontent.close();");
	});
	//强制关闭
	$("#forceclose").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要强制关闭吗","bottomcontent.forceclose();");
	});
	
	
	//批量修改发货日期
	$("#batchModify").click(function(){
	   var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
	   if(checkBox.length<1){
 		  parent.showErrorMsg("请选择一条记录");
 	   }else{
	      $("#mycredit_show").show();
 	   }
	});
	
	
	changeColor();
	
	//test();
	
});

var isStart = false;
function test(){
	$("#ordertb tr:gt(0) td").mouseover(function(e){
		//这里可得到鼠标X坐标
		var pointX = e.pageX;
		//这里可以得到鼠标Y坐标
		var pointY = e.pageY;
		
		var hight = $(this).height(); 
		var width =  $(this).width();
		
	    var top =  $(this).offset().top;
	    var left =  $(this).offset().left;
	    
	    
	    var dfh = pointY-top;
	    var dfl = pointX-left;
	    if(22<dfh && dfh<25){
	    	$(this).css("cursor","crosshair");
	    	$(this).find("div").addClass("div_border");
	    }else{
	    	$(this).find("div").removeClass("div_border");
	    }
	    
	    $(this).mousemove(function(e){
	    	 isStart = true;
	    }).mouseup(function(){
	    	isStart = false;
	    });
	    
	    if(isStart){
	    	$(this).css("cursor","crosshair");
	    	$(this).find("div").addClass("div_border");
	    }
	    
	    if(22<dfl && dfl<116){
	    	
	    }
	     
	    
	   // alert("top: "+top+"  pointY: "+pointY);
		 
	});
}




 function closePage(){
	 $("#mycredit_show").hide();
 }
 

//修改交期
function batchModifyFn(){
	var TEMP_DATE = $("#TEMP_DATE").val();
    if(null == TEMP_DATE || "" == TEMP_DATE){
	   parent.showErrorMsg("请选择'预计发货日期'");
	   return ;
    }
    var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
    checkBox.each(function(){
    	$(this).parent().parent().find("input[name='ADVC_SEND_DATE']").val(TEMP_DATE);
    }); 
    
//    if(isWeekEnd(TEMP_DATE)){
//		parent.showErrorMsg("你选择的交期是星期天,请重新选择!");
//		return ;
//	}
    closePage();
    childSave();
	 
}



//行关闭
function close(){
	parent.showWaitPanel();
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}else{
		var flag = true;
		checkBox.each(function(){
			var STATE = $(this).parent().parent().find("input[name='STATE']").val();
			if("关闭" == STATE){
				parent.showErrorMsg("已经关闭的行不能重复关闭，请取消");
				flag = false;
				return false;
			}
		});
		
		if(!flag){
			return;
		}
	}
	
	
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var STATE = $(this).parent().parent().find("input[name='STATE']").val();
		if("关闭" == STATE){
			return;
		}
		ids = ids+"'"+$(this).val()+"',";
	});
	 
	if(ids.length == 0){
		parent.showErrorMsg("明细当前状态下不能关闭");
		return;
	}
	
	ids = ids.substr(0,ids.length-1);
	 
	var selRowId = parent.document.getElementById("selRowId").value;
	var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
	$.ajax({
		url: "saleordermge.shtml?action=closeOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids,"SALE_ORDER_ID":selRowId,"BILL_TYPE":BILL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#label_1").click();
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//强制关闭
function forceclose(){
	parent.showWaitPanel();
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}else{
		var flag = true;
		checkBox.each(function(){
			var STATE = $(this).parent().parent().find("input[name='STATE']").val();
			if("关闭" == STATE){
				parent.showErrorMsg("已经关闭的行不能重复关闭，请取消");
				flag = false;
				return false;
			}
		});
		
		if(!flag){
			return;
		}
	}
	
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var STATE = $(this).parent().parent().find("input[name='STATE']").val();
		if("关闭" == STATE){
			return;
		}
		ids = ids+"'"+$(this).val()+"',";
	});
	 
	if(ids.length == 0){
		parent.showErrorMsg("明细当前状态下不能关闭");
		return;
	}
	
	ids = ids.substr(0,ids.length-1);
	 
	var selRowId = parent.document.getElementById("selRowId").value;
	var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
	
	$.ajax({
		url: "saleordermge.shtml?action=forceCloseOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids,"SALE_ORDER_ID":selRowId,"BILL_TYPE":BILL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#label_1").click();
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "saleordermge.shtml?action=childDelete",
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
	var BILL_TYPE = parent.topcontent.getBILL_TYPE();
	if(vstate){
		var state = vstate.value;
		if(state != "审核通过" && state != "待发货" && state !="已发货") {
			btnDisable(["close","forceclose"]);
		}
		
		if(state != "审核通过") {
			btnDisable(["save","batchModify"]);
		}
		
		if(state == "弃审") {
			$("#ordertb tr:gt(0)").find("input[type='text']").attr("disabled","disabled").attr("class","readonly");
		}
		
		if("返修订单" == BILL_TYPE){
				btnDisable(["close","forceclose"]);
		}
		 
	}
}


function justMX(){
	var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
	var size = checkBox.length;
	var leg = 0;
	checkBox.each(function(){
		var STATE = $(this).parent().parent().find("input[name='STATE']").val();
		if("关闭" == STATE){
			 leg = parseInt(leg)+1;
		}
	});
   
    var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	var arrBtu = [];
	if(vstate){
		var state = vstate.value;
		if(state == "审核通过") {
			arrBtu = ["close","save","forceclose","batchModify"];
		}else{
			arrBtu = ["close","forceclose"];
		}
	}
	if(leg == 0){
		 btnReset(arrBtu);
    	return;
    }
	if(size == leg){
		btnDisable(arrBtu);
	}else{
		btnReset(arrBtu);
	}
}


function btnReset(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).removeAttr("disabled");
	}
}

//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).removeAttr("checked");
	}else{
		$(obj).attr("checked","true");
	}
	justMX();
}


function checkline(obj){
	$(obj).parent().parent().find("input[name='mx_checkbox']").attr("checked","true");
}

function selectTechPage(SPCL_TECH_ID,SALE_ORDER_DTL_ID,PRICE){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 var state = parent.topcontent.document.getElementById("state" + selRowId).value;
	 var delState=$("#STATE"+SALE_ORDER_DTL_ID).val();
	if(state == "审核通过"&&"关闭"!=delState) {
		var NEW_SPEC=$("#NEW_SPEC"+SALE_ORDER_DTL_ID).val();
		var NEW_COLOR=$("#NEW_COLOR"+SALE_ORDER_DTL_ID).val();
		var PRODUCT_REMARK=$("#PRODUCT_REMARK"+SALE_ORDER_DTL_ID).val();
		var data=window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE+"&saleFlag=1&NEW_SPEC="+utf8(NEW_SPEC)+"&NEW_COLOR="+utf8(NEW_COLOR)+"&ORDERID="+SALE_ORDER_DTL_ID+"&PRODUCT_REMARK="+utf8(PRODUCT_REMARK)+"&editTable=ERP_SALE_ORDER_DTL",window,"dialogwidth=800px; dialogheight=600px; status=no");
		if(null!=data){
			$("#NEW_SPEC"+SALE_ORDER_DTL_ID).val(data.NEW_SPEC);
			$("#NEW_COLOR"+SALE_ORDER_DTL_ID).val(data.NEW_COLOR);
			$("#PRODUCT_REMARK"+SALE_ORDER_DTL_ID).val(data.PRODUCT_REMARK);
		}
	}else{
		window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
	}
	
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
		url: "saleordermge.shtml?action=childSave",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
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

 
//是否取消过标记 标颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var STATE = $(this).find("input[name='STATE']").val();
		if("关闭" == STATE){
		    $(this).find("td").css("background-color", "#FFEC8B");//#F3F3F3花号还原
		    
		     $(this).find("input[type='text']").attr("disabled","disabled").attr("class","readonly");//#F3F3F3花号还原
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
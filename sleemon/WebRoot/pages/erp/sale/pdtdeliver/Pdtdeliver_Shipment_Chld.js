/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 货品发运
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	headColumnSort("ordertb","ordertbForm");
	setSelOperateEx();
   //初始化校验
   InitFormValidator("ordertbForm");
   
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		
		 
	});
   
   	$("#close").click(function(){
	  	var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("关闭之后不可逆,请确认真的要关闭吗","bottomcontent.multiRecClose();");
   });
   
	
});
 

//行关闭
function multiRecClose(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = $("#state"+selRowId).val();
	
	 var checkBox = $("#ordertb tr:gt(0) input:checked");
	 var ids = "";
	 var flag = true;
	 if(checkBox.length>0){
		//获取所有选中的记录
		checkBox.each(function(){
			var IS_SEND_FIN = $(this).parent().parent().find("input[name='IS_SEND_FIN']").val();
			if(3 == IS_SEND_FIN || "3" == IS_SEND_FIN){
				 flag = false;
				 return false;
			}
		 
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
	 }
	if(!flag){
		parent.showErrorMsg("存在已关闭的行记录，请取消");
		return false;
	}
	$.ajax({
		url: "pdtdeliver.shtml?action=closeChilds",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"DELIVER_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					parent.topcontent.$("#queryForm").submit();
				});
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});

}





function childSave(obj){
	 
//	var checkBox = $("#ordertb tr:gt(0) input:checked");
//	if(checkBox.length<1){
//		parent.showErrorMsg("请至少选择一条记录");
//		return;
//	}
//	var GOODS_ORDER_ID = parent.document.getElementById("selRowId").value;
//	var childData = multiPackJsonData("ordertb");
	
	var PLAN_NUM = $(obj).val();
	if(""==PLAN_NUM||null==PLAN_NUM){
		PLAN_NUM=0;
	}
	var ADVC_PLAN_NUM = $(obj).parent().parent().find("input[json='ADVC_PLAN_NUM']").val();
	var REMARK=$(obj).parent().parent().find("input[json='REMARK']").val();
	var DELIVER_ORDER_DTL_ID = $(obj).parent().parent().find("input[json='DELIVER_ORDER_DTL_ID']").val();
	var NO_SEND_DEAL_TYPE = $(obj).parent().parent().find("select[json='NO_SEND_DEAL_TYPE']").val();
	
	if(null == PLAN_NUM || "" == PLAN_NUM){
		return;
	}
	if(null == DELIVER_ORDER_DTL_ID || "" == DELIVER_ORDER_DTL_ID){
		return;
	}
	REMARK = $.trim(REMARK);
	var NO_SEND_NUM = parseInt(ADVC_PLAN_NUM)-parseInt(PLAN_NUM);
	$(obj).parent().parent().find("input[json='NO_SEND_NUM']").val(NO_SEND_NUM);
	$.ajax({
	 	url: "pdtdeliver.shtml?action=childSave",
		type:"POST",
		data:{"DELIVER_ORDER_DTL_ID":DELIVER_ORDER_DTL_ID,"PLAN_NUM":PLAN_NUM,"NO_SEND_NUM":NO_SEND_NUM,"REMARK":REMARK,"NO_SEND_DEAL_TYPE":NO_SEND_DEAL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				saveSuccess(utf8Decode(jsonResult.messages),"pdtdeliver.shtml?action=toFrame");
				//parent.showMsgPanel(utf8Decode(jsonResult.messages));
				//parent.document.getElementById("label_1").click();
			}else{
				//showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function saveInfo(obj){
	var REMARK = $(obj).val();
	REMARK = $.trim(REMARK);
	var NO_SEND_DEAL_TYPE = $(obj).parent().parent().find("select[json='NO_SEND_DEAL_TYPE']").val();
	var DELIVER_ORDER_DTL_ID = $(obj).parent().parent().find("input[json='DELIVER_ORDER_DTL_ID']").val();
	if(null == DELIVER_ORDER_DTL_ID || "" == DELIVER_ORDER_DTL_ID){
		return;
	}
	 
	$.ajax({
	 	url: "pdtdeliver.shtml?action=updateChildSave",
		type:"POST",
		data:{"DELIVER_ORDER_DTL_ID":DELIVER_ORDER_DTL_ID,"REMARK":REMARK,"NO_SEND_DEAL_TYPE":NO_SEND_DEAL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				saveSuccess(utf8Decode(jsonResult.messages),"pdtdeliver.shtml?action=toFrame");
				//parent.showMsgPanel(utf8Decode(jsonResult.messages));
				//parent.document.getElementById("label_1").click();
			}else{
				//showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function disSelect(row){
	$("#NO_SEND_DEAL_TYPE"+row).attr("disabled","disabled");
}


/**
 * 剩余货品处理方式
 * @param {Object} obj
 * @return {TypeName} 
 */
function countNotSend(obj,row){
	if(!CheckIntegerInput(obj)){
		return;
	}
	var num = $(obj).val();
	if(""==num||null==num){
		num=0;
	}
	var last = $(obj).parent().find("input[name='ADVC_PLAN_NUM']").val();
	var sel = $(obj).parent().parent().find("select[name='NO_SEND_DEAL_TYPE']");
	if(!isNaN(num)){
		 if(parseInt(num) > parseInt(last)){
			 parent.showErrorMsg("'计划发运数量'不能大于'预排发运数量'");
			 return;
		 }
		 var selDisabled = sel.attr("disabled");
		 if(num == last){
			 //查找索引大于0的所有input元素
			 sel.find("option:gt(0)").remove();
			 sel.attr("disabled","true");
		 }else if(selDisabled){
			 var selRowId = parent.document.getElementById("selRowId").value;
			 var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
			  if("返修发运" == BILL_TYPE){
				  SelDictShow("NO_SEND_DEAL_TYPE"+row,"BS_78","","");
			  }else{
				  SelDictShow("NO_SEND_DEAL_TYPE"+row,"BS_56","","");
			  }
			 
			 sel.removeAttr("disabled");
		 }
		 var n = Number(last)-Number(num);
		 $(obj).parent().parent().find("td[name='NO_SEND_NUM']").text(n);
		 childSave(obj);
		 var DECT_PRICE = $(obj).parent().find("input[name='DECT_PRICE']").val();
		 DECT_PRICE=isNaN(DECT_PRICE)?0:parseFloat(DECT_PRICE);//折后价
		 var VOLUME=$(obj).parent().find("input[name='VOLUME']").val()//体积
		 VOLUME=isNaN(VOLUME)?0:parseFloat(VOLUME);
		 num=isNaN(num)?0:parseFloat(num);
		 var allPrice=(DECT_PRICE*num).toFixed(2);
		 var allVol=(VOLUME*num).toFixed(2);
		 $("#allPrice"+row).html(allPrice);
		 $("#allVol"+row).html(allVol);
	}else{
		sel.removeAttr("disabled");
	}
	
	//
	$("#allNum").val(0);
	$("#allAmount").val(0);
	$("#allVolume").val(0);
	var sel = $("#ordertb tr:gt(0) input[name='PLAN_NUM']");
	sel.each(function(){
		var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//折后单价
		var VOLUME=$(this).parent().parent().find("input[name='VOLUME']").val();//体积
		var PLAN_NUM=$(this).parent().parent().find("input[name='PLAN_NUM']").val();//个数
		
	    if(""==VOLUME||null==VOLUME){
	  	  VOLUME=0;
	    }
	    if(""==PLAN_NUM||null==PLAN_NUM){
	  	  PLAN_NUM=0;
	    }
		
		var perNum = parseFloat(PLAN_NUM);
		var perAmount = parseFloat(DECT_PRICE,10)*parseFloat(PLAN_NUM,10);
		var perVolume = parseFloat(VOLUME,10)*parseFloat(PLAN_NUM,10);
		
		$("#allAmount").val((parseFloat($("#allAmount").val(),10) + perAmount).toFixed(2));
		$("#allVolume").val((parseFloat($("#allVolume").val(),10) + perVolume).toFixed(2));
		$("#allNum").val((parseFloat($("#allNum").val(),10) + perNum).toFixed(2));
	});
}



//剩余货品发运方式 onchange事件
function saveNextType(obj){
	var NO_SEND_DEAL_TYPE = $(obj).val();
	var DELIVER_ORDER_DTL_ID = $(obj).parent().parent().find("input[json='DELIVER_ORDER_DTL_ID']").val();
	var REMARK = $(obj).parent().parent().find("input[json='REMARK']").val();
	REMARK = $.trim(REMARK);
	if(null == DELIVER_ORDER_DTL_ID || "" == DELIVER_ORDER_DTL_ID){
		return;
	}
	 
	$.ajax({
	 	url: "pdtdeliver.shtml?action=updateChildSave",
		type:"POST",
		data:{"DELIVER_ORDER_DTL_ID":DELIVER_ORDER_DTL_ID,"REMARK":REMARK,"NO_SEND_DEAL_TYPE":NO_SEND_DEAL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			}else{
			}
		}
	});
}





//计算 选中明细的‘预排发运数量’ 是否等于 ‘计划发运数量’
function checkChild(){
	
	
	var trs = $("#ordertb tr:gt(0)");
	
	var total_adv_num = 0;
	var total_plan_num = 0;
	var flag = true;
	
	trs.each(function(){
		var num = $.trim($(this).find("td[name='ADVC_PLAN_NUM']").text());
		var inptObj = $(this).find("input[json='PLAN_NUM']");
		if(!CheckIntegerInput(inptObj)){
		   flag = false;
		   return false;
	    }
		var plan_num = inptObj.val();
		plan_num = parseInt(plan_num);
		num = parseInt(num);
//		if(plan_num == 0){
//			parent.showErrorMsg("'计划发运数量'不能为'0'");
//			flag = false;
//			return false;
//		}
		
		if(plan_num>num){
			parent.showErrorMsg("'计划发运数量'不能大于'预排发运数量'");
			flag = false;
			return false;
		}
		
		$(this).find("input[name='NO_SEND_NUM']").val((num-plan_num));
		if(num != plan_num){
			var NO_SEND_DEAL_TYPE = $(this).find("select[name='NO_SEND_DEAL_TYPE']").val();
			if(null == NO_SEND_DEAL_TYPE || "" == NO_SEND_DEAL_TYPE){
				parent.showErrorMsg("请选择'剩余货品处理方式'");
				flag = false;
				return false;
			}
		}
		 
	});
	
	return flag;
}
 

function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态!=已提交生产
		if (state != "已提交生产") {
			 $("#ordertb").find("input[name='PLAN_NUM']").attr("disabled","true");
			 $("#ordertb").find("select[name='NO_SEND_DEAL_TYPE']").attr("disabled","true");
		}
		if (state != "已提交库房") {
			document.getElementById("close").disabled = true;
		}
	}
}


//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


/*
 *将多个form封装成json串
 *
 *@param 
 *@return json格式字符串
 */
function multiPackJsonData(tableid,isEdit){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
			var inputs = $(this).find(":input");
			inputs.each(function(){
				if(null != $(this).attr("json")){
					var key;
					var value;
					var type = $(this).attr("type");
					if(!isFirst && "checkbox" == type){
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
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}





//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).attr("checked","true");
	}else{
		$(obj).removeAttr("checked");
	}
}



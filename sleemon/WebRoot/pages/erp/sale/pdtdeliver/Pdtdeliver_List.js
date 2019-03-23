/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 货品发运
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("pdtdeliver.shtml?action=toList&module=" + module);
	
//	$("#query").click(function(){
//		$("#STATE option[text='未提交']").remove();
//	});
	//新增修改初始化
	addModiToEditFrameInit("pdtdeliver.shtml?action=toEditFrame");
//	$("#modify").click(function(){
//		 var selRowId = $("#selRowId").val();
//	 	 if(null == selRowId || "" == selRowId){
//	 		 parent.showErrorMsg("请选择一条记录");
//	 	 }else{
//	 	 	setCommonPageInfo(true);
//			parent.window.gotoTopPage("pdtdeliver.shtml?action=toEdit&DELIVER_ORDER_ID="+selRowId);
//			parent.$("#bottomdiv").css("display","none");
//			parent.$("#tablayer").css("display","none");
//			parent.$("#topdiv").css("height","100%");
//			
//			
//	 	 }
//	});
 
    $("#commit").click(function(){
       
//       $(this).attr("disabled","disabled");
	   var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 	   }else{
 		   var cf = parent.bottomcontent.checkChild();
 		   if(cf){
 			   var f = checkPRVD_NAME(selRowId);
	 		   if(f){
	 			   parent.showWaitPanel();
	 			   commitStore();
	 		   }
 		   }
 		   
 		   
 	   }
   });
    $("#queryProStatus").click(function(){
    	var selRowId = $("#selRowId").val();
    	if(null == selRowId || "" == selRowId){
    		parent.showErrorMsg("请选择一条记录");
    	}else{
    		var DELIVER_ORDER_NO=$("#NO"+selRowId).val();
    		window.showModalDialog("pdtdeliver.shtml?action=queryProStatus&DELIVER_ORDER_NO="+DELIVER_ORDER_NO,window,"dialogwidth=800px; dialogheight=600px; status=no");
    	}
    })
    
    $("#creatplan").click(function(){
    	// selCommon('BS_68', true, 'DELIVER_ORDER_ID_PLAN', 'DELIVER_ORDER_ID', 'forms[0]','DELIVER_ORDER_ID_PLAN', 'DELIVER_ORDER_ID', 'selectDeliverToplan')
    	// var DELIVER_ORDER_IDS = $("#DELIVER_ORDER_ID_PLAN").val();
    	 creatPlan();
    });
   $("#dunning").click(function(){
//	    selCommon('BS_68', true, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[0]','DELIVER_ORDER_NO', 'DELIVER_ORDER_NO', 'selectParamsDunning')
	   // window.showModalDialog("pdtdeliver.shtml?action=toList&forWard=select",window,"dialogwidth=800px; dialogheight=600px; status=no");
	    window.showModalDialog("pdtdeliver.shtml?action=selectFrame",window,"dialogwidth=800px; dialogheight=600px; status=no");
//	    window.open("pdtdeliver.shtml?action=toList&forWard=select","qq","");
	    var DELIVER_ORDER_IDS = $("#SELECT_DELIVER_ORDER_ID").val();
	    var DELIVER_ORDER_NOS = $("#SELECT_DELIVER_ORDER_NO").val();
	    var RECV_CHANN_ID= $("#SELECT_RECV_CHANN_ID").val();
	  
	    if(null == DELIVER_ORDER_IDS || "" == DELIVER_ORDER_IDS){
	    	return;
	    }
	    
	    $.ajax({
		url: "pdtdeliver.shtml?action=queryGooderOrderCreate",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_IDS":DELIVER_ORDER_IDS,"RECV_CHANN_ID":RECV_CHANN_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				$("#SELECT_DELIVER_ORDER_ID").val("");
	            $("#SELECT_DELIVER_ORDER_NO").val("");
	            $("#SELECT_RECV_CHANN_ID").val("");
	            var data = jsonResult.data;
	            $.each(data,function(i,item){
	            	 var LAST_CREDIT = item.LAST_CREDIT;
	            	 var DELIVER_ORDER_NOS_ = item.DELIVER_ORDER_NO;
	            	 var receiver = item.XTYHID;
					 var content = "您好：您有【"+DELIVER_ORDER_NOS_+"】发运单等待发运，但需要付款："+LAST_CREDIT+"，请您速度付款！"
					 sendMessage(receiver,content);
	            });
	            
	            parent.showErrorMsg("催款成功！消息已发");
				
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
   });
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","pdtdeliver.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
   
   dateDiffColor();
   
    $("#close").click(function(){
		parent.showConfirm("关闭之后不可逆,请确认真的要关闭吗","topcontent.deliverOrderClose('true');");
   });
    //整单冻结deliverOrderClose
    $("#freeze").click(function(){
		 parent.showConfirm("确定要整单冻结吗","topcontent.deliverOrderFreeze();");
	 });
    //整单解冻
    $("#unfreeze").click(function(){
		 parent.showConfirm("确定要整单解冻吗","topcontent.deliverOrderunFreeze();");
	 });
});

function creatPlan(ids){
	
	window.showModalDialog("pdtdeliver.shtml?action=shipmentFrame",window,"dialogwidth=1000px; dialogheight=800px; status=no");
//    window.open("pdtdeliver.shtml?action=shipmentFrame","qq","");
	var DELIVER_ORDER_IDS = $("#SELECT_DELIVER_ORDER_ID").val();
    if(null == DELIVER_ORDER_IDS || "" == DELIVER_ORDER_IDS){
    	return;
    }
	 
    $.ajax({
		url: "pdtdeliver.shtml?action=createPlanNo",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_IDS":DELIVER_ORDER_IDS},
		complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var JOIN_DELIVER_ORDER_NO = jsonResult.data;
				 parent.showMsgPanel("操作成功！</br>出货计划号："+JOIN_DELIVER_ORDER_NO);
				 $("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function dateDiffColor(){
	var inputs = $("#ordertb tr:gt(0)").find("input[name='SEND_DATE_DIFF']");
	inputs.each(function(){
		var date = $(this).val();
		var state = $(this).parent().parent().find("input[name='state']").val();
		date = parseInt(date);
		if("已提交生产" == state){
			if(date<=2){
				$(this).parent().parent().find("td").css("background-color", "#B4EEB4");//花号还原
				  
			}
		}
		
	});
}



/**
 * 验证物流公司
 * @param {Object} selRowId
 * @return {TypeName} 
 */
function checkPRVD_NAME(selRowId){
	var PRVD_NAME = $.trim($("#PRVD_NAME_TD"+selRowId).text());
	var APPCH_TIME = $.trim($("#APPCH_TIME_TD"+selRowId).text());
	var JOIN_DELIVER_ORDER_NO = $.trim($("#JOIN_DELIVER_ORDER_NO"+selRowId).text());
	if(null == PRVD_NAME || "" == PRVD_NAME){
		parent.showErrorMsg("请点击修改按钮，填写'物流公司'");
		return false;
	}
	if(null == APPCH_TIME || "" == APPCH_TIME){
		parent.showErrorMsg("请点击修改按钮，填写'进场时间'");
		return false;
		
	}
	if(null == JOIN_DELIVER_ORDER_NO || "" == JOIN_DELIVER_ORDER_NO){
		parent.showErrorMsg("该单据还没有出货计划号");
		return false;
		
	}
	return true;
}

//提交库房
function commitStore(){
	var selRowId = $("#selRowId").val();
	var childData = parent.bottomcontent.multiPackJsonData("ordertb",true);
	
	$.ajax({
		url: "pdtdeliver.shtml?action=commitStore",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var data = jsonResult.data;
				var message = data.strMessage;
				var no = "";
				var deliveNoSet = data.deliveNoSet;
			 
				if(null != deliveNoSet && "" != deliveNoSet){
					no = no+"</br>请牢记新产生的发运单号："
					 $.each(deliveNoSet,function(i,item){
						 no = no + deliveNoSet[i]+"</br>";
					 })
				}
				message = message+no;
				parent.showMsgPanel(utf8Decode(message));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					$("#commit").removeAttr("disabled");
					$("#pageForm").submit();
				});
//				saveSuccess(utf8Decode(jsonResult.messages),"pdtdeliver.shtml?action=toFrame"+utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	
}

 
 

//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	
	//当状态!=已提交生产
	if (state != "已提交生产") {
		btnDisable(["modify","commit","dunning","freeze","unfreeze"]);
	}
	if (state != "已提交库房") {
		btnDisable(["close"]);
	}
	 
}


//催款
function dunning(){
	
}


/**
 * 催款 给订货订单的制单人发消息
 * 
 */
function sendMessage(XTYHIDS,content){
	var _xtyhids = XTYHIDS;
	var _jgxxids = "";//$("#JGXXID").val();//如果加了部门，就会对同部门的人发 信息
	var _bmxxids = "";// $("#BMXXID").val();//同上
	var _fsnr = content;
	
	$.ajax({
		url: "firstPage.shtml?action=txInsertMessage",
		type:"POST",
		dataType:"text",
		data:{xtyhids:_xtyhids,bmxxids:_bmxxids,jgxxids:_jgxxids,fsnr:_fsnr},
		complete: function(xhr){
		    eval("json = "+xhr.responseText);
		    if(json.success==true){
		        //parent.showErrorMsg("催款成功！消息已发");
		    }else{
		       // parent.showErrorMsg(utf8Decode(json.messages));
		    }
		}
	});
}



//行关闭
function deliverOrderClose(IS_ALL_CLOSE){
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "pdtdeliver.shtml?action=closeChilds",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"IS_ALL_CLOSE":IS_ALL_CLOSE},
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

//整单冻结
function deliverOrderFreeze(){
	 parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "pdtdeliver.shtml?action=deliverOrderFreeze",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
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

//整单冻结
function deliverOrderunFreeze(){
	 parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "pdtdeliver.shtml?action=deliverOrderunFreeze",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
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



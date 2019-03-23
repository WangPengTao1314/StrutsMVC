/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 发运确认
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
	listPageInit("deliverconfm.shtml?action=toList&module=" + module);
	
	//新增和修改按钮初始化
	addModiToEditFrameInit("deliverconfm.shtml?action=toEditFrame");
	
//	$("#modify").click(function(){
//		 var selRowId = $("#selRowId").val();
//	 	 if(null == selRowId || "" == selRowId){
//	 		 parent.showErrorMsg("请选择一条记录");
//	 	 }else{
//	 	 	setCommonPageInfo(true);
//			parent.window.gotoTopPage("deliverconfm.shtml?action=toEdit&DELIVER_ORDER_ID="+selRowId);
//			parent.$("#bottomdiv").css("display","none");
//			parent.$("#tablayer").css("display","none");
//			parent.$("#topdiv").css("height","100%");
//			
//			
//	 	 }
//	});
 
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","deliverconfm.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
 
    $("#affirm").click(function(){
	   var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 	   }else{
 		  //发运确认的时候 先检查 明细是否有差异
 		  checkCommit();
 	      
 	   }
   });
    
});
  


//发运确认
function despatchAffirm(){
	 parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
	//2014-7-16 改为后台查询数据库，前台会有点击其他页签报js错的问题
	var childData = "" ;//parent.bottomcontent.multiPackJsonData("ordertb",true);
	$.ajax({
		url: "deliverconfm.shtml?action=despatchAffirm",
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
					$("#pageForm").submit();
				});
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
	
}


//发运确认的时候 先检查 明细是否有差异
function checkCommit(){
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "deliverconfm.shtml?action=checkCommit",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 //明细已做处理之后 填写延迟原因
				  var STOREOUT_TIME_DIFF = $("#STOREOUT_TIME_DIFF"+selRowId).val();
		 	      var REMARK = $("#REMARK"+selRowId).val();
		 	      if(STOREOUT_TIME_DIFF >= 2){
		 	    	  openReasonPage();
		 	    	  
		 	      }else{
		 	    	  despatchAffirm();
		 	      }
 	      
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
	
	
}



//测试 U9接口
function handlePdtDeliver(){
	var f = parent.bottomcontent.checkHasChild();
    if(!f){
    	return;
    }
	var selRowId = $("#selRowId").val();
	var childData = parent.bottomcontent.multiPackJsonData("ordertb");
	 
	$.ajax({
		url: "deliverconfm.shtml?action=handlePdtDeliver",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"deliverconfm.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
	
}

//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	
	if (state != "已发货") {
		btnDisable(["affirm","shifa","modify"]);
	}
	 
}


function U9_ReciveNum(){
	window.open('pages/erp/sale/deliverconfm/DeliverConfm_U9_Test.jsp','newwindow','height=400, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

 function closePage(){
	 $("#mycredit_show").hide();
 }

 function openReasonPage(){
	var return_v = window.showModalDialog('pages/erp/sale/deliverconfm/reason.jsp',self,'dialogwidth=510px; dialogheight=300px; status=no');
	if("1" == return_v){
		writeReason(); 
    }
	 
//	 window.open('pages/erp/sale/deliverconfm/reason.jsp','newwindow','height=400, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');

}
 
 function writeReason(){
	 var selRowId = $("#selRowId").val();
	 var DELAY_TYPE = $("#DELAY_TYPE").val();
	  
	 var REMARK = $("#REMARK").val(); 
	 $.ajax({
		url: "deliverconfm.shtml?action=writeReason",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"DELAY_TYPE":DELAY_TYPE,"REMARK":REMARK},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				//货品发运
			    despatchAffirm();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			 
		}
	});
 }
 
 
 

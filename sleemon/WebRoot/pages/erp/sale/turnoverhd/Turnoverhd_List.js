/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护
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
	listPageInit("turnoverhd.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	//addModiToEditFrameInit("turnoverhd.shtml?action=toEdit");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("turnoverhd.shtml?action=delete", "SALE_ORDER_ID");
   
	//新增修改初始化
	addModiToEditFrameInit("turnoverhd.shtml?action=toEditFrame");
	
//	$("#modify").click(function(){
//		 var selRowId = $("#selRowId").val();
//	 	 if(null == selRowId || "" == selRowId){
//	 		 parent.showErrorMsg("请选择一条记录");
//	 	 }else{
//	 	 	setCommonPageInfo(true);
//			parent.window.gotoTopPage("turnoverhd.shtml?action=toEdit&DELIVER_ORDER_ID="+selRowId);
//	 	 }
//	});
 
   $("#unplan").click(function(){
	   window.open("turnoverhd.shtml?action=toNotPlanList");
   })
   $("#cancel").click(function(){
	    var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 		  return;
 	  }else{
 		  parent.showConfirm("您确认要撤销吗?","topcontent.cancel();","N");
 	  }
   })
   $("#again").click(function(){
	   var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 		  return;
 	  }else{
 		  parent.showConfirm("您确认要重排吗?","topcontent.again();","N");
 	  }
	   
   });
 
    $("#commit").click(function(){
	   var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 	   }else{
		  commitPlan();
 	   }
   });
   //修改交期
   $("#modifyAdvDate").click(function(){
       var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 	   }else{
	      $("#mycredit_show").show();
 	   }
    	
    });
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","turnoverhd.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
});
  


 function closePage(){
	 $("#mycredit_show").hide();
 }
 
//重排
function again(){
	 var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "turnoverhd.shtml?action=again",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"turnoverhd.shtml?action=toFrame");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
			closePage();
		}
	});
	
}

 
//提交交付计划
function commitPlan(){
	 showWaitPanel();
	var selRowId = $("#selRowId").val();
	var DELIVER_ORDER_NO = $("#DELIVER_ORDER_NO"+selRowId).val();
	var ADVC_SEND_DATE = document.getElementById("ADVC_SEND_DATE" + selRowId).value;
	if(isWeekEnd(ADVC_SEND_DATE)){
		parent.showErrorMsg("你选择的交期是星期天,请重新选择!");
		return ;
	}
	
	$.ajax({
		url: "turnoverhd.shtml?action=commitPlan",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"DELIVER_ORDER_NO":DELIVER_ORDER_NO},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel(utf8Decode(jsonResult.messages));
				$("#YT_MSG_BTN_OK").click(function(){
					$("#pageForm").submit();
				});
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
	
}
//修改交期
function modifyAdvDate(){
	var TEMP_DATE = $("#TEMP_DATE").val();
    if(null == TEMP_DATE || "" == TEMP_DATE){
	   parent.showErrorMsg("请选择交付日期");
	   return ;
    }
    
    if(isWeekEnd(TEMP_DATE)){
		parent.showErrorMsg("你选择的交期是星期天,请重新选择!");
		return ;
	}
    
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "turnoverhd.shtml?action=modifyAdvDate",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"TEMP_DATE":TEMP_DATE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"turnoverhd.shtml?action=toFrame");
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
	var BILL_TYPE = $("#BILL_TYPE"+selRowId).val();
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state != "未提交") {
		btnDisable(["again","modify","commit"]);
	}
	if(BILL_TYPE == "返修发运"){
		btnDisable(["again"]);
	}
	
	if(state != "已提交生产"){
		btnDisable(["modifyAdvDate","cancel"]);
	}
	if(state!="未提交"&&state!="已提交生产"){
		btnDisable(["modifyAdvDate","cancel","again","modify","commit"]);
	}
}

//撤销
function cancel(){
	var selRowId = $("#selRowId").val();
	var FROM_BILL_ID = $("#FROM_BILL_ID"+selRowId).val();
	if(null != FROM_BILL_ID && "" != FROM_BILL_ID){
		parent.showErrorMsg("有来源发运单据的不能撤销");
		return;
	}
//	var IS_ALL_FREEZE_FLAG = $("#IS_ALL_FREEZE_FLAG"+selRowId).val();
//	if(1 == IS_ALL_FREEZE_FLAG || "1" == IS_ALL_FREEZE_FLAG){
//		parent.showErrorMsg("请先解除冻结，再撤销");
//		return;
//	}
	 $.ajax({
		url: "turnoverhd.shtml?action=toCancel",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				$("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}

	function isWeekEnd(date){
		var myDate = new Date(getDate(date))
		var idate = myDate.getDay();
  		if(idate ==0 ){
  			return true;
  		}
  		return false;
	}
	
	 function getDate(strDate) {
            var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
             function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
            return date;
       }


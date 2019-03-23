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
	listPageInit("deliveryhd.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	//addModiToEditFrameInit("deliveryhd.shtml?action=toEdit");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("deliveryhd.shtml?action=delete", "DELIVER_ORDER_ID");
   
//	//新增修改初始化
	addModiToEditFrameInit("deliveryhd.shtml?action=toEditFrame");
	
//	$("#modify").click(function(){
//		 var selRowId = $("#selRowId").val();
//	 	 if(null == selRowId || "" == selRowId){
//	 		 parent.showErrorMsg("请选择一条记录");
//	 	 }else{
//	 	 	setCommonPageInfo(true);
//			parent.window.gotoTopPage("deliveryhd.shtml?action=toEdit&DELIVER_ORDER_ID="+selRowId);
//	 	 }
//	});
   $("#cancel").click(function(){
	  var selRowId = $("#selRowId").val();
 	  if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 		  return;
 	  }else{
 		  parent.showConfirm("您确认要撤销吗?","topcontent.cancel();");
 	  }
   })
   $("#close").click(function(){
	  var selRowId = $("#selRowId").val();
 	  if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 		  return;
 	  }else{
 		  parent.showConfirm("您确认要关闭吗?","topcontent.close();","N");
 	  }
   })
 
    $("#commit").click(function(){
	   var selRowId = $("#selRowId").val();
 	   if(null == selRowId || "" == selRowId){
 		  parent.showErrorMsg("请选择一条记录");
 	   }else{
 		  parent.showWaitPanel();
		  commitPlan();
 	   }
   });
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","deliveryhd.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
    
    $("#addSave").click(function(){
    	window.open("deliveryhd.shtml?action=toEdit",'发货计划维护新增','height=800, width=1400, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
    })
});
  
 
//提交库房发货
function commitPlan(){
	var selRowId = $("#selRowId").val();
	var childData = parent.bottomcontent.multiPackJsonData("ordertb",true);
	$.ajax({
		url: "deliveryhd.shtml?action=commitStore",
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
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	var BILL_TYPE = $("#BILL_TYPE"+selRowId).val();
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state != "未提交") {
		btnDisable(["delete","commit"]);
	}
	if(state!="已提交库房"){
		btnDisable(["cancel","close"]);
	}
	if(state=="关闭" || state == "已提交库房"){
		btnDisable(["modify"]);
	}
}

//撤销
function cancel(){
	var selRowId = $("#selRowId").val();
//	var FROM_BILL_ID = $("#FROM_BILL_ID"+selRowId).val();
//	if(null != FROM_BILL_ID && "" != FROM_BILL_ID){
//		parent.showErrorMsg("有来源发运单据的不能撤销");
//		return;
//	}
//	var IS_ALL_FREEZE_FLAG = $("#IS_ALL_FREEZE_FLAG"+selRowId).val();
//	if(1 == IS_ALL_FREEZE_FLAG || "1" == IS_ALL_FREEZE_FLAG){
//		parent.showErrorMsg("请先解除冻结，再撤销");
//		return;
//	}
	 $.ajax({
		url: "deliveryhd.shtml?action=toCancel",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				$("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}

//关闭
function close(){
	parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "deliveryhd.shtml?action=toClose",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("关闭成功");
				$("#queryForm").submit();
			}else{
				parent.closeWindow();
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function listRef(){
	$("#queryForm").submit();
}
function getSHIP_POINT_ID(){
	var selRowId = $("#selRowId").val();
	return $("#SHIP_POINT_ID"+selRowId).val();
}

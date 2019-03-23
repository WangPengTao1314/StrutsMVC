/**
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_List
 * @author lyg
 * @time   2013-11-02 18:55:53 
 * @version 1.1
 */
$(function(){
	$("#reverseDiv").hide();
    var module = parent.window.document.getElementById("module").value;
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit","reverse","upDate"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke","begin","download","uploading","end"]);
	//主从及主从从列表页面通用加载方法
	listPageInit("advcgoodsapp.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advcgoodsapp.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advcgoodsapp.shtml?action=delete", "ADVC_SEND_REQ_ID");
	
	$("#query").click(function(){
		if("sh" == module){
			$("#STATE option[text='未提交']").remove();
		}
	});
	$("#commit").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确认提交吗?","topcontent.upSTATE('"+selRowId+"','提交');");
		
	})
	$("#audit").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
//		upSTATE(selRowId,"审核通过");
		checkAmount(selRowId);
	})
	$("#reverse").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$("#ADVC_SEND_REQ_ID").val(selRowId);
    	$("#reverseDiv").show();
	});
	$("#close").click(function(){
    	$("#reverseDiv").hide();
    	$("#REMARK").val("");
    	$("#ADVC_SEND_REQ_ID").val("");
    });
    $("#revAudit").click(function(){
    	var ADVC_SEND_REQ_ID=$("#ADVC_SEND_REQ_ID").val();
    	upSTATE(ADVC_SEND_REQ_ID,"退回");
    })
    $("#upDate").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
    	$("#ADVC_SEND_REQ_ID").val(selRowId);
    	var date=$("#date"+selRowId).val();
    	var REMARK=$("#REMARK"+selRowId).val();
    	$("#ORDER_RECV_DATE").val(date);
    	$("#REMARK").val(REMARK);
    	$("#upDateDiv").show();
    })
});
function getFROM_BILL_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var FROM_BILL_ID=$("#from"+selRowId).val();
	return FROM_BILL_ID;
}
function getSTOREOUT_STORE_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var STOREOUT_STORE_ID=$("#storeout"+selRowId).val();
	return STOREOUT_STORE_ID;
}
function upSTATE(ADVC_SEND_REQ_ID,type){
	parent.showWaitPanel();
	var SEND_TYPE = $("#SEND_TYPE"+ADVC_SEND_REQ_ID).val();
	var RETURN_RSON=$("#RETURN_RSON").val();
//	parent.showWaitPanel();
	$.ajax({
	 url: "advcgoodsapp.shtml?action=toCommit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_SEND_REQ_ID":ADVC_SEND_REQ_ID,"type":type,"SEND_TYPE":SEND_TYPE,"RETURN_RSON":RETURN_RSON},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			if("提交"==type){
				parent.showMsgPanel("提交成功");
			}else if("审核通过"==type){
				parent.showMsgPanel("提交发货成功");
			}else if("退回"==type){
				parent.showMsgPanel("退回成功");
			}
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
	closeWindow();
}

//记录按钮根据状态控制
 function setSelOperateEx(){
	var selRowId = $("#selRowId").val();
	var state =  $.trim($("#state"+selRowId).val());
	//按钮状态重置
	btnReset();
	if(state != "提交"){
		btnDisable(["audit","reverse","upDate"]);
	}
	if(state == "提交" || state == "审核通过"){
		btnDisable(["modify","delete","commit"]);
	}
	if(state == "已发货"){
		btnDisable(["modify","delete","audit","reverse","commit"]);
	}
	if(state == "已取消" ){
		btnDisable(["modify","delete","audit","reverse","commit"]);
	}
 }
function closeUpDateDiv(){
	$("#upDateDiv").hide();
	$("#ADVC_SEND_REQ_ID").val("");
}
//修改要求到货日期
function upDate(){
	var ADVC_SEND_REQ_ID=$("#ADVC_SEND_REQ_ID").val();
	var REMARK=$("#REMARK").val();
	var date=$("#ORDER_RECV_DATE").val();
	$.ajax({
	 url: "advcgoodsapp.shtml?action=upOrderDate",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_SEND_REQ_ID":ADVC_SEND_REQ_ID,"ORDER_RECV_DATE":date,"REMARK":REMARK},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("修改成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
function checkAmount(id){
	$.ajax({
	 url: "advcgoodsapp.shtml?action=checkAmount",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_SEND_REQ_ID":id},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showConfirm("请确认是否提交库房发货?","topcontent.upSTATE('"+id+"','审核通过');");
		}else{
			var message=utf8Decode(jsonResult.messages);
			if(message=="1"){
				parent.showConfirm("该预订单款项未付清，请确认是否提交库房发货?","topcontent.upSTATE('"+id+"','审核通过');");
			}else{
				parent.showErrorMsg(message);
			}
			
		}
	  }
    });
}
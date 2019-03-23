/**
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核
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
	listPageInit("saleordermge.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("saleordermge.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("saleordermge.shtml?action=delete", "SALE_ORDER_ID");
    $("#commit").click(function () {
    	var selRowId = $("#selRowId").val();
    	if(null == selRowId || "" == selRowId){
    		parent.showErrorMsg("请选择一条记录");
    		return;
    	}
    	 parent.showConfirm( "确定维护好了发货基地、抵库、抛转信息？您确认要这么操作吗？","topcontent.toCommit()","N");
//		 toCommit();
	});
    
	$("#revoke").click(function () {
		var selRowId = $("#selRowId").val();
    	if(null == selRowId || "" == selRowId){
    		parent.showErrorMsg("请选择一条记录");
    		return;
    	}
		 parent.showConfirm( "确定撤销生产吗？","topcontent.revoke()");
	});
	 
	 
	 $("#expdate").click(function(){
		 $("#queryForm").attr("action","saleordermge.shtml?action=toExpData&module=" + module);
		 $("#queryForm").submit();
	 });
	 
	 //反审核
	$("#opposeAudit").click(function(){
		var selRowId = $("#selRowId").val();
    	if(null == selRowId || "" == selRowId){
    		parent.showErrorMsg("请选择一条记录");
    		return;
    	}
		parent.showConfirm( "确定反审核吗？该操作会删除相关的销售订单","topcontent.opposeAudit();");
		
	});
	 
	
   $("#queryProStatus").click(function(){
    	var selRowId = $("#selRowId").val();
    	if(null == selRowId || "" == selRowId){
    		parent.showErrorMsg("请选择一条记录");
    	}else{
    		var SALE_ORDER_NO = $("#SALE_ORDER_NO"+selRowId).val();
    		window.showModalDialog("drp/report/queryProStatus.shtml?action=listFromU9&DeliverPlanNo="+SALE_ORDER_NO,window,"dialogwidth=800px; dialogheight=600px; status=no");
    	}
    })
    
    $("#plan").click(function(){
    	var mainFrame = window.top.mainFrame;  
	    mainFrame.addTab("BS0G06","出货计划维护","../../deliveryhd.shtml?action=toFrame&module=wh");
    });
	$("#noSendExport").click(function(){
    	$("#queryForm").attr("action","saleordermge.shtml?action=noSendExport&module=" + module);
		 $("#queryForm").submit();
	 });
   $("#queryCredit").click(function(){
	   queryCredit();
   });
//	$("#query").click(function(){
//		$("#STATE option[text='未提交']").remove();
//	});
	
  //dateDiffColor();

});

 function closePage(){
	 $("#mycredit_show").hide();
 }
 
 
function queryCredit(){
	var selRowId = $("#selRowId").val();
	var CHANN_ID = $("#ORDER_CHANN_ID"+selRowId).val();
	var CHANN_NO = $("#ORDER_CHANN_NO"+selRowId).val();
 	$.ajax({
	url: "saleordermge.shtml?action=queryCredit",
	type:"POST",
	dataType:"text",
	data:{"CHANN_ID":CHANN_ID,"CHANN_NO":CHANN_NO},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var data = jsonResult.data;
			var userCredit = Number(data.USER_CREDIT)+Number(data.INI_CREDIT)
			+Number(data.TEMP_CREDIT_REQ)-Number(data.FREEZE_CREDIT);
			$("#userCredit").val(userCredit); 
			$("#PAYMENT_CREDIT").val(data.PAYMENT_CREDIT); 
			$("#REBATE_CURRT").val(data.REBATE_CURRT);
			$("#CHANN_NO").val(data.CHANN_NO);
			$("#CHANN_NAME").val(data.CHANN_NAME);
		}else{
			 
	    }
		$("#mycredit_show").show();
	}
  });
}
 
 
 
//提交生产之前的check
function toCommit(){
	var IS_NO_ADVC_DATE_FLAG=$("#IS_NO_ADVC_DATE_FLAG").val();
	var selRowId = parent.document.getElementById("selRowId").value;
	if("1"==IS_NO_ADVC_DATE_FLAG){
		var SALE_ORDER_NO = $("#SALE_ORDER_NO"+selRowId).val();
		window.open("saleordermge.shtml?action=toChldAdvcData&SALE_ORDER_ID="+selRowId+"&SALE_ORDER_NO="+SALE_ORDER_NO,"预计发货日期修改","dialogwidth=1300px; dialogheight=600px; status=no;");
	}else{
		showWaitPanel();
		$.ajax({
			url: "saleordermge.shtml?action=toCommit",
			type:"POST",
			dataType:"text",
			data:{"SALE_ORDER_ID":selRowId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					commitProduction();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
					closeWindow();
				}
			}
	  });
	}
}


//提交生产
function commitProduction(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var SALE_ORDER_NO = $("#SALE_ORDER_NO"+selRowId).val();
	$.ajax({
		url: "saleordermge.shtml?action=commitProduction",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_NO":SALE_ORDER_NO},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 parent.showMsgPanel(utf8Decode(jsonResult.messages));
				// saveSuccess(utf8Decode(jsonResult.messages),"saleordermge.shtml?action=toFrame");
				 $("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
  });
}

function getBILL_TYPE(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#BILL_TYPE"+selRowId).val();
}
//反审核
function opposeAudit(){
	parent.showWaitPanel();
	var selRowId = parent.document.getElementById("selRowId").value;
	var FROM_BILL_ID = $("#FROM_BILL_ID"+selRowId).val();
	var BILL_TYPE = $("#BILL_TYPE"+selRowId).val();
	if(null == FROM_BILL_ID || "" == FROM_BILL_ID){
		parent.showErrorMsg("该单据找不到来源单据，不能反审核");
		return;
	}
	$.ajax({
		url: "saleordermge.shtml?action=opposeAudit",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"FROM_BILL_ID":FROM_BILL_ID,"BILL_TYPE":BILL_TYPE},
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

//撤销生产
function  revoke(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var FROM_BILL_ID = $("#FROM_BILL_ID"+selRowId).val();
	var SALE_ORDER_NO = $("#SALE_ORDER_NO"+selRowId).val();
	$.ajax({
		url: "saleordermge.shtml?action=toRevoke",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"FROM_BILL_ID":FROM_BILL_ID,"SALE_ORDER_NO":SALE_ORDER_NO},
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


function orpenWindow(SALE_ORDER_ID){
	window.showModalDialog("saleorderview.shtml?action=toGoodsFrame&SALE_ORDER_ID="+SALE_ORDER_ID,window,"dialogwidth=900px; dialogheight=700px; status=no");
}


//时间差变色  
function dateDiffColor(){
	var inputs = $("#ordertb tr:gt(0)").find("input[name='DATE_DIFF']");
	inputs.each(function(){
		var date = $(this).val();
		date = parseInt(date);
		if(date >=1 ){
			$(this).parent().parent().find("td").css("background-color", "#E693A2");//花号还原
			  
		}
	});
}
	
  
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	var TECH_STATE = document.getElementById("TECH_STATE" + selRowId).value;
	var STANDFLAG = document.getElementById("STANDFLAG" + selRowId).value;
    var BILL_TYPE = getBILL_TYPE();
	//按钮状态重置
	btnReset();
	if(state =="未提交" || state =="提交" || state =="关闭" || state=="弃审"){
		btnDisable(["opposeAudit","commit","modify","revoke"]);
	}
	if(state =="审核通过"){
		btnDisable(["revoke"]);
	}
	if(state =="待发货"){
		btnDisable(["opposeAudit","commit","modify"]);
	}
	if(state =="已发货"){
		btnDisable(["opposeAudit","commit","modify","revoke"]);
	}
	if(state =="待核价"){
		btnDisable(["","commit","modify","revoke"]);
	}
	if(state =="已核价"){
		btnDisable(["commit","modify","revoke"]);
	}
	if("返修订单" == BILL_TYPE){
		btnDisable(["revoke"]);
	}
}
function listRef(){
	$("#queryForm").submit();
}

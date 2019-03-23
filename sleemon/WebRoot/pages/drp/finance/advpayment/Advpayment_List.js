﻿/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_List
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advpayment.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advpayment.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advpayment.shtml?action=delete", "STATEMENTS_ID");
	
	$("#revoke").click(function(){
 		  var selRowId = $("#selRowId").val();
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	  } 
	 	  parent.showConfirm("您确认要撤销吗?","topcontent.revoke();");
 	});
	 $("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
    })
    
    $("#PaymentExport").click(function(){
    	$("#queryForm").attr("action","advpayment.shtml?action=PaymentToExcel&module=" + module);
		 $("#queryForm").submit();
	 });
});

//查询预付款列表
 function queryPayNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" BILL_TYPE in ('订金','正常收款','预付款') and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_54', false, 'STATEMENTS_NO', 'STATEMENTS_NO', 'forms[1]','STATEMENTS_NO', 'STATEMENTS_NO','con');
 }
 
 //查询预定单列表
 function queryOrderNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" DEL_FLAG=0 and STATE in ('提交','待发货') and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_55', false, 'ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'forms[1]','ADVC_ORDER_NO', 'ADVC_ORDER_NO','con');
 }
 
 //查询终端列表
 function queryTeamNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" CHANN_ID_P = '"+ztxxid+"'");
	selCommon('BS_27', false, 'TERM_NO','TERM_NO','forms[1]','TERM_NAME','TERM_NAME','con');
 }
 
  //提交
 function opSub(){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
// 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要提交吗?","topcontent.mainSubmit('"+selRowId+"','sub');");
 }
 
 //主体提交方法
 function mainSubmit(selRowId,op){
 	$.ajax({
        type:"POST", 
        url:"advpayment.shtml?action=opSub", 
        data:{"id":selRowId}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("操作成功,已付款金额已修改为"+jsonResult.messages);
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
        }
    });
 	
 	
 }
 
 //点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	var BILL_TYPE=document.getElementById("BILL_TYPE" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (state == "已结算" || state == "已核销") {
		btnDisable(["delete","modify","commit"]);
	}
	if("订金"==BILL_TYPE){
		btnDisable(["delete","modify","commit","revoke"]);
	}
	if(state!="已结算"){
		btnDisable(["revoke"])
	}
}

function my_reset(){
	$("#ZTXX_ID").val("");
	$("#STATEMENTS_NO").val("");
	$("#ADVC_ORDER_NO").val("");
	$("#TERM_NO").val("");
	$("#TERM_NAME").val("");
	$("#CRE_TIME_BEG").val("");
	$("#CRE_TIME_END").val("");
	$("#CUST_NAME").val("");
	$("#STATE").val("");
}

function getBillState(){
	var selRowId = $("#selRowId").val();
	return $("#state"+selRowId).val();
}
function revoke(){
	 var selRowId = $("#selRowId").val();
	 $.ajax({
        type:"POST", 
        url:"advpayment.shtml?action=toRevoke", 
        data:{"STATEMENTS_ID":selRowId}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel(utf8Decode(jsonResult.messages));
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
        }
    });
}
function print(selRowId){
	window.open('advpayment.shtml?action=printInfo&STATEMENTS_ID='+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}
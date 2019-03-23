/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_List
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("preorderpayment.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("preorderpayment.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("preorderpayment.shtml?action=delete", "STATEMENTS_ID");
});

//查询预付款列表
 function queryStatementNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" BILL_TYPE='预订单结算' and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_58', false, 'STATEMENTS_NO', 'STATEMENTS_NO', 'forms[1]','STATEMENTS_NO', 'STATEMENTS_NO','con');
 }
 
 //查询预定单列表
 function queryOrderNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" DEL_FLAG=0 and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_55', false, 'ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'forms[1]','ADVC_ORDER_NO', 'ADVC_ORDER_NO','con');
 }
 
 //查询终端列表
 function queryTeamNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_27', false, 'TERM_NO', 'TERM_NAME','forms[1]','TERM_NO','TERM_NAME','con');
 }
 
  //提交
 function opSub(){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要提交吗?","topcontent.mainSubmit('"+selRowId+"','sub');");
}
 
 //主体提交方法
 function mainSubmit(selRowId,op){
 	var actionUrl = "";
 	if(op==="sub"){
 		actionUrl = "preorderpayment.shtml?action=opSub";
 	}
 	$.ajax({
        type:"POST", 
        url:actionUrl, 
        data:{"id":selRowId}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("操作成功");
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
    }});
 }
 
  //点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (state == "已结算") {
		btnDisable(["delete","modify","commit"]);
	}
}

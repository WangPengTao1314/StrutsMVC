/**
 * @prjName:喜临门营销平台
 * @fileName:Payment_List
 * @author glw
 * @time   2013-08-15 09:31:13 
 * @version 1.1
 */
$(function(){
	//页面初始化
	listPageInit("payment.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("payment.shtml?action=delete","PAYMENT_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	   //btnDisable(["modify","delete","getPayMent_U9"]);
	};
	$("#getPayMent_U9").click(function(){
		
		window.open('pages/erp/sale/payment/RecivePayment.jsp','newwindow','height=400, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	});
});

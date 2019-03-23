/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	
  $("#save").click(function(){
	  showWaitPanel();
	  commitPlan();
   });
});
//提交库房发货
function commitPlan(){
	$("#commitFlag").val("1");
	var SALE_ORDER_ID = $("#SALE_ORDER_ID").val();
	var SALE_ORDER_NO = $("#SALE_ORDER_NO").val();
	var childData = multiPackJsonData("ordertb");
	$.ajax({
		url: "saleordermge.shtml?action=commitProduction",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":SALE_ORDER_ID,"SALE_ORDER_NO":SALE_ORDER_NO,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				$("#commitFlag").val("0");
				alert("操作成功");
				window.opener.parent.topcontent.listRef();
				window.close();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				$("#commitFlag").val("0");
			}
		}
  });
//	closeWindow();
}
//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

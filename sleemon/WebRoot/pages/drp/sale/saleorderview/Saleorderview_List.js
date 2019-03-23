/**
 * @prjName:订货订单处理
 * @fileName:Goodsorder_List
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("saleorderview.shtml?action=toList&module=" + module);
	//0158271--start--刘曰刚--2014-07-02//
	//修改状态为未提交、已取消、审核通过
	$("#query").click(function(){
		$("#STATE option[text='提交']").remove();
		$("#STATE option[text='退回']").remove();
		$("#STATE option[text='已发货']").remove();
	});
	//0158271--End--刘曰刚--2014-07-02//
	$("#tempCredit").click(function(){
		tempCredit();
	});
	//form校验设置
	InitFormValidator(0);
	$("#expdate").click(function(){
		 $("#queryForm").attr("action","saleorderview.shtml?action=toExpData&module=" + module);
		 $("#queryForm").submit();
	 });
	 $("#queryProStatus").click(function(){
    	var selRowId = $("#selRowId").val();
    	if(null == selRowId || "" == selRowId){
    		parent.showErrorMsg("请选择一条记录");
    	}else{
    		var SALE_ORDER_NO = $("#saleNo"+selRowId).val();
    		window.showModalDialog("drp/report/queryProStatus.shtml?action=listFromU9&DeliverPlanNo="+SALE_ORDER_NO,window,"dialogwidth=800px; dialogheight=600px; status=no");
    	}
    })
});
function orpenWindow(SALE_ORDER_ID){
	window.showModalDialog("saleorderview.shtml?action=toGoodsFrame&SALE_ORDER_ID="+SALE_ORDER_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
 function showPage(){
	 $("#mycredit_show").show();
 }
  function closePage(){
	 $("#mycredit_show").hide();
 }
  //临时信用申请
 function tempCredit(){
	 var mainFrame = window.top.mainFrame;  
	  mainFrame.addTab("w-009","临时信用申请","../../temp_credit_req.shtml?action=toFrame&module=wh");
 }

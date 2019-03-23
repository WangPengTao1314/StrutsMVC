/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_Frame
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
	 //框架页面初始化
	 var module = document.getElementById("module").value;
	 var flag = $("#flag").val();
	 var SALE_ORDER_ID=$("#SALE_ORDER_ID").val();
	 framePageInit("saleorderview.shtml?action=toGoodsList&SALE_ORDER_ID="+SALE_ORDER_ID);
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "saleorderview.shtml?action=goodsChildList"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&GOODS_ORDER_IDS="+selRowId);
}



 
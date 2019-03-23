/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_List_Chld
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
	changeColor();
});



//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.open('techorderManager.shtml?action=viewTechWithPrice&acqModel=1&SPCL_TECH_ID='+SPCL_TECH_ID+'&PRICE='+PRICE,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}


//是否取消过标记 标颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var IS_CANCELED_FLAG = $(this).find("input[name='IS_CANCELED_FLAG']").val();
		var CANCEL_FLAG = $(this).find("input[name='CANCEL_FLAG']").val();
		IS_CANCELED_FLAG = parseInt(IS_CANCELED_FLAG);
		CANCEL_FLAG = parseInt(CANCEL_FLAG);
		if(IS_CANCELED_FLAG == 1 || CANCEL_FLAG == 1){
			var id = $(this).attr("id");
		    $(this).find("td").css("background-color", "#E6B9B8");//#F3F3F3花号还原
		}
	});
	
}





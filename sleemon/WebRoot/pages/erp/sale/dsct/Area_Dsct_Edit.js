/**
 * @prjName:喜临门营销平台
 * @fileName:区域折扣
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("dsct.shtml?action=edit","AREA_DSCT_ID");
});
function countPrice(){
	var BASE_PRICE=$("#BASE_PRICE").val();
	BASE_PRICE=isNaN(BASE_PRICE)?0:parseFloat(BASE_PRICE);//基准价
	var DECT_RATE=$("#DECT_RATE").val();
	DECT_RATE=isNaN(DECT_RATE)?0:parseFloat(DECT_RATE);//折扣率
	var DECT_PRICE=Math.round((isNaN(BASE_PRICE*DECT_RATE)?0:BASE_PRICE*DECT_RATE)*100)/100;
	$("#DECT_PRICE").val(DECT_PRICE);
}
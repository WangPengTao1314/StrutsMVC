/**
 * @prjName:喜临门营销平台
 * @fileName:Resvstore_Edit
 * @author zzb
 * @time   2013-09-07 14:13:12 
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("resvstore.shtml?action=edit","RESV_STOCK_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
	
});
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	var SAFE_STORE_NUM = $("#SAFE_STORE_NUM").val();
	var MIN_STORE_NUM = $("#MIN_STORE_NUM").val();
   return true;
}
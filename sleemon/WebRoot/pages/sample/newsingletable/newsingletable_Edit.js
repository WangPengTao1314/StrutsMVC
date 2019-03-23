/**
 * @module Sample
 * @func 单表示例
 * @version 1.1
 * @author zhuxw
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("newsingletable.shtml?action=edit","JGXXID");
});
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
/**
function formCheckedEx()
{
  if()
  {
    alert('');
    return false;
  }
}
**/
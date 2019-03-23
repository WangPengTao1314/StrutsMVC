/**
 *@module 基础数据
 *@func品牌信息
 *@version 1.1
 *@author 郭利伟
 */
$(function(){
    if($("#BRAND_ID").val()!=null && $("#BRAND_ID").val() != ""){
	    $("#BRAND_ID").attr("readonly","readonly");
	}
	
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("ppxx.shtml?action=edit","BRAND_ID","ppxx.shtml?action=toList","mainForm");
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formChecked(){

   if($("#BRAND").val() ==null || $("#BRAND").val() == ""){
         parent.showErrorMsg(utf8Decode("品牌名称不能为空！"));
         return false;
   }
    
    if(!newFormCheck('mainForm'))
    {
     return false;
    }
    return true;
}
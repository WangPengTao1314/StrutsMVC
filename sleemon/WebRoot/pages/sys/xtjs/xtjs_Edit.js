/**
  *@module 系统管理 
  *@fuc 系统角色主表编辑
  *@version 1.1 
  *@author 唐赟
*/
var status = $("#status").val();
$(function(){
    
    if("modify" == status){
        $("#JSBH").attr("readonly","true");
    }
	//下帧跳转
	parent.window.gotoBottomPage();
	//form校验设置
	InitFormValidator(0);
	
});

//function formCheckedEx(){
//   if($("#JSBH").val().length != 3){
//        parent.showErrorMsg(utf8Decode("角色编号请输入三位！"));
//        return false;
//   }
//   return true;
//}

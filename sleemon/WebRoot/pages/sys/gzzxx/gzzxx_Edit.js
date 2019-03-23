/**
  *@module 系统管理 
  *@func 工作组信息
  *@version 1.1 
  *@author 吴亚林
*/
//var status = $("#status").val();
$(function(){
    
    /*if("modify" == status){
        $("#JSBH").attr("readonly","true");
    }*/
	//下帧跳转
	parent.window.gotoBottomPage();
	//form校验设置
	InitFormValidator(0);
	
});

/*function formCheckedEx(){
   if($("#JSBH").val().length != 3){
        parent.showErrorMsg(utf8Decode("角色编号请输入三位！"));
        return false;
   }
   return true;
}*/

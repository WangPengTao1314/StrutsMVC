/**
 *@module 信用管理
 *@func 信用额度设定
 *@version 1.1
 *@author 郭利伟
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("credit.shtml?action=edit","CHANN_ID");
});

//业务校验 
function formChecked(){
	if($("#TEL").val() != null && $("#TEL").val() != ""){
        var re1 = new RegExp(/^([\d]{3}-[\d]{8})|([\d]{4}-[\d]{7})$/);//国内电话号码匹配
        var tel = re1.test($("#TEL").val());
        if(!tel){
           parent.showErrorMsg(utf8Decode("电话号码格式输入不正确！"));
           return false;
        }
   }
	
 	if($("#MOBILE_PHONE").val()!=null && $("#MOBILE_PHONE").val() != ""){
        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
        var phone = re2.test($("#MOBILE_PHONE").val());
        if(!phone ){
      	   parent.showErrorMsg(utf8Decode("手机格式输入不正确！"));
           return false;
   		}
    }
 	
 	if($("#FAX").val() != null && $("#FAX").val() != ""){
        var re3 = new RegExp(/^([\d]{3}-[\d]{8})|([\d]{4}-[\d]{7})$/);//国内传真号码匹配
        var fax = re3.test($("#FAX").val());
        if(!fax){
           parent.showErrorMsg(utf8Decode("传真号码格式输入不正确！"));
           return false;
        }
    }
 	
	if($("#POST").val() != null && $("#POST").val() != ""){
        var re4 = new RegExp(/^[\d]{6}$/);//中国邮政编码匹配
        var post = re4.test($("#POST").val());
        if(!post){
           parent.showErrorMsg(utf8Decode("邮政编码格式输入不正确！"));
           return false;
        }
    }
	
   if($("#EMAIL").val()!=null && $("#EMAIL").val() != ""){
      var re5 = new RegExp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);//email匹配
      var email = re5.test($("#EMAIL").val());
      if(!email ){
         parent.showErrorMsg(utf8Decode("电子邮件格式输入不正确！"));
         return false;
      }
   }
   
	if($("#WEB").val() != null && $("#WEB").val() != ""){
        var re6 = new RegExp(/^(http:\/\/)?(www\.)?([\w-]+\.?\w+)[a-z0-9]+\.[a-z]+$/);//网站匹配
        var web = re6.test($("#WEB").val());
        if(!web){
           parent.showErrorMsg(utf8Decode("网站格式输入不正确！"));
           return false;
        }
    }
    
    if(!newFormCheck('mainForm'))
    {
     return false;
    }
    return true;
}

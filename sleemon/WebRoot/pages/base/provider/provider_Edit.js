/**
 * @module 系统管理
 * @func 供应商信息
 * @version 1.1
 * @author 张涛
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("provider.shtml?action=edit","PRVD_ID");
});
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();


//业务校验 
function formCheckedEx(){
	if ($("#PRVD_TYPE").val() == null || $("#PRVD_TYPE").val() == "") {
		 parent.showErrorMsg(utf8Decode('请选择"供应商类别"！'));
         return false;
	}
	
	if ($("#PRVD_LVL").val() == null || $("#PRVD_LVL").val() == "" ) {
		 parent.showErrorMsg(utf8Decode('请选择"供应商级别"！'));
         return false;
	}
	
	if ($("#PRVD_NATRUE").val() == null || $("#PRVD_NATRUE").val() == "" ) {
		 parent.showErrorMsg(utf8Decode('请选择"供应商性质"！'));
         return false;
	}
	
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
 	
 	if($("#TAX").val() != null && $("#TAX").val() != ""){
        var re3 = new RegExp(/^([\d]{3}-[\d]{8})|([\d]{4}-[\d]{7})$/);//国内传真号码匹配
        var fax = re3.test($("#TAX").val());
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
      var re5 = new RegExp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);//Email匹配
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
    return true;
}
function DEFAULT_FLAGClick(){
	var flag = document.getElementById("DEFAULT_FLAGCHECK").checked;
	if(flag){
		$("#DEFAULT_FLAG").val("1");
	}else{
		$("#DEFAULT_FLAG").val("0");
	}
}
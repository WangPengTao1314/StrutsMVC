

/**
 * @prjName:喜临门营销平台
 * @fileName:Paymentup_Edit
 * @author lyg
 * @time   2013-08-23 10:25:58 
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("paymentup.shtml?action=edit","PAYMENT_UPLOAD_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
	
});
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	//验证联系方式
	if($("#TEL").val()!=null && $("#TEL").val() != ""){
	        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
	        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
	        var TEL1 = re1.test($("#TEL").val());
	        var TEL2 = re2.test($("#TEL").val());
	        if(!TEL1&&!TEL2 ){
	      	   parent.showErrorMsg(utf8Decode("联系方式输入不正确！"));
	           return false;
	        }
		}
	
	//验证付款凭证路径文件名长度和是否上传付款凭证路径
		if($("#PAYMENT_PATH").val()==null||$("#PAYMENT_PATH").val()==""){
			parent.showErrorMsg(utf8Decode("请上传付款凭证！"));
	      		    return false;
		}else if($("#PAYMENT_PATH").val().length>80){
  		    parent.showErrorMsg(utf8Decode("付款凭证路径文件名过长！"));
  		    return false;
		}
   return true;
}
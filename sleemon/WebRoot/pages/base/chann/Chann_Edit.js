 
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("chann.shtml?action=edit","CHANN_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
});
function upFlag(){
	var flag = document.getElementById("flag").checked;
	if(flag){
		$("#TERM_DECT_CTR_FLAG").val("1");
	}else{
		$("#TERM_DECT_CTR_FLAG").val("0");
	}
}

function upStoreInFlag(){
    var flag = document.getElementById("storeInflag").checked;
	if(flag){
		$("#IS_UPDATE_STOREIN_FLAG").val("1");
	}else{
		$("#IS_UPDATE_STOREIN_FLAG").val("0");
	}
}

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
		if($("#POST").val()!=null && $("#POST").val() != ""){
	        var re1 = new RegExp(/^[0-9]{6}$/);//邮政编码匹配
	        var POST = re1.test($("#POST").val());
	        if(!POST ){
	      	   parent.showErrorMsg(utf8Decode("邮政编码格式输入不正确！"));
	           return false;
	        }
		}
		/*
		if($("#TEL").val()!=null && $("#TEL").val() != ""){
	        var re1 = new RegExp(/^((0\d{2,3})-)|(\d{7,8})(-(\d{3,}))?$/);//电话匹配
	        var TEL = re1.test($("#TEL").val());
	        if(!TEL ){
	      	   parent.showErrorMsg(utf8Decode("电话格式输入不正确！"));
	           return false;
	        }
		}
		if($("#MOBILE").val()!=null && $("#MOBILE").val() != ""){
	        var re1 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
	        var MOBILE = re1.test($("#MOBILE").val());
	        if(!MOBILE ){
	      	   parent.showErrorMsg(utf8Decode("手机格式输入不正确！"));
	           return false;
	        }
	   }
		 if($("#TAX").val()!=null && $("#TAX").val() != ""){
	        var re1 = new RegExp(/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/);//传真匹配
	        var TAX = re1.test($("#TAX").val());
	        if(!TAX ){
	      	   parent.showErrorMsg(utf8Decode("传真格式输入不正确！"));
	           return false;
	        }
		}
		if($("#EMAIL").val()!=null && $("#EMAIL").val() != ""){
	        var re1 = new RegExp(/^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/);//email匹配
	        var email = re1.test($("#EMAIL").val());
	        if(!email ){
	      	   parent.showErrorMsg(utf8Decode("电子邮件格式输入不正确！"));
	           return false;
	        }
		}*/
		//验证渠道类型不为空
		if($("#CHANN_TYPE").val()==null || $("#CHANN_TYPE").val() == ""){
	      	   parent.showErrorMsg(utf8Decode("请选择渠道类型！"));
	           return false;
		}
		//验证渠道级别不为空
		if($("#CHAA_LVL").val()==null || $("#CHAA_LVL").val() == ""){
	      	   parent.showErrorMsg(utf8Decode("请选择渠道级别！"));
	           return false;
		}
		//验证详细地址不为空
		if($("#ADDRESS").val()==null || $("#ADDRESS").val() == ""){
	      	   parent.showErrorMsg(utf8Decode("请填写详细地址！"));
	           return false;
		}
		//验证营业执照附件文件名长度
		if($("#BUSS_LIC_ATT").val()!=null || $("#BUSS_LIC_ATT").val() != ""){
	      	   if($("#BUSS_LIC_ATT").val().length>80){
	      		    parent.showErrorMsg(utf8Decode("营业执照附件文件名过长！"));
	      		    return false;
	      	   }
		}
		//验证税务登记附件文件名长度
		if($("#TAX_BURDEN_ATT").val()!=null || $("#TAX_BURDEN_ATT").val() != ""){
	      	   if($("#TAX_BURDEN_ATT").val().length>80){
	      		    parent.showErrorMsg(utf8Decode("税务登记附件文件名过长！"));
	      		    return false;
	      	   }
		}
		//验证组织结构代码证附件文件名长度
		if($("#ORG_CERT_ATT").val()!=null || $("#ORG_CERT_ATT").val() != ""){
	      	   if($("#ORG_CERT_ATT").val().length>80){
	      		    parent.showErrorMsg(utf8Decode("组织结构代码证附件文件名过长！"));
	      		    return false;
	      	   }
		}
		//验证付款比例为小数
		if($("#PAY_RATE").val()!=null || $("#PAY_RATE").val() != ""){
	      	   if($("#PAY_RATE").val()>1||$("#PAY_RATE").val()<0){
	      		   parent.showErrorMsg(utf8Decode("付款比例应该大于等于0并且小于等于1"));
	      		   return false;
	      	   }
		}
	    return true;
	}
	
	function openWindow(){
       var arguemnts = new Object();
       arguemnts.window = window;
  	   window.showModalDialog("chann.shtml?action=toUpdateStoreFrame",arguemnts,"dialogwidth=900px; dialogheight=700px; status=no");
	}
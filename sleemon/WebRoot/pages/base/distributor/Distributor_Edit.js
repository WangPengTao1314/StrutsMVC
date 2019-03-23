
/**
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.1
 * @author  gu_hongxiu
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("distributor.shtml?action=edit","DISTRIBUTOR_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
		
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
				
	    return true;
	}
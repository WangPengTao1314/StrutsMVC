
/**
 * @module 渠道管理-转撤店管理
 * @func 专卖店撤店及终止申请单维护
 * @version 1.1
 * @author ghx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//var selId = parent.document.getElementById("selRowId").value;
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("storeclose.shtml?action=edit","SPCL_STORE_CC_REQ_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
});

//终端信息
function selAdvcorder(){
	var BUSS_AREA = $("#BUSS_AREA").val();
	if(BUSS_AREA == 0){
		$("#BUSS_AREA").val("");
	}
	
	selCommon('BS_181', false, 'TERM_ID', 'TERM_ID', 'forms[0]',
		'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,PERSON_CON,TEL,MOBILE,ADDRESS,ZONE_ID,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,ZONE_ADDR,BUSS_AREA,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,BEG_SBUSS_DATE,TAX', 
		'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,PERSON_CON,TEL,MOBILE,ADDRESS,ZONE_ID,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,ZONE_ADDR,BUSS_AREA,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,BEG_SBUSS_DATE,TAX', 
		'selectParams');	
}

function upFlag(){
	var flag = document.getElementById("flag").checked;
	if(flag){
		$("#TERM_DECT_CTR_FLAG").val("1");
	}else{
		$("#TERM_DECT_CTR_FLAG").val("0");
	}
 
}
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
		//传真
		if($("#TAX").val()!=null && $("#TAX").val() != ""){
		     var re1 = new RegExp(/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/);//传真匹配
		     var TAX = re1.test($("#TAX").val());
		     if(!TAX ){
		      	parent.showErrorMsg(utf8Decode("传真格式输入不正确！"));
		        return false;
		   }
		}
		
		
		//申报理由 
		if($("#REQ_RSON").val()==null || $("#REQ_RSON").val() == ""){
		      parent.showErrorMsg(utf8Decode("请填入申报理由！"));
		      return false;
		}
		
		//验证图片文件名长度
		if($("#PIC_PATH").val()!=null && $("#PIC_PATH").val() != ""){
	      	   if($("#PIC_PATH").val().length>260){
	      		    parent.showErrorMsg(utf8Decode("上传图片文件名过长！"));
	      		    return false;
	      	   }
		}
		
		//撤店类型
		var STORE_CANCEL_TYPE = $("#STORE_CANCEL_TYPE").val();
		if(null == STORE_CANCEL_TYPE || "" == STORE_CANCEL_TYPE){
			parent.showErrorMsg("请选择'"+$("#STORE_CANCEL_TYPE").attr("label")+"'");
			$("#STORE_CANCEL_TYPE").focus();
			return false;
		}
	
	    return true;
	}
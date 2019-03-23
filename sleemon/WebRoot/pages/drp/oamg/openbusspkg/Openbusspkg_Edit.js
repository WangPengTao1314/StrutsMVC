
/**
 * @module 渠道管理-开业礼包管理
 * @func   开业大礼包申请单维护
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
	mtbSaveListener("openbusspkg.shtml?action=edit","OPEN_BUSS_PKG_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
});

//终端信息
function selAdvcorder(){
	
	var BUSS_AREA = $("#BUSS_AREA").val();
	if(BUSS_AREA == 0){
		$("#BUSS_AREA").val("");
	}
	
	
	selCommon('BS_82', false, 'TERM_ID', 'TERM_ID', 'forms[0]',
		'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,ADDRESS,ZONE_ID,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,ZONE_ADDR,BUSS_AREA,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,BEG_SBUSS_DATE', 
		'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,ADDRESS,ZONE_ID,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,ZONE_ADDR,BUSS_AREA,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,BEG_SBUSS_DATE', 
		'selectParams');	
}

//渠道信息
function selChann(){
	selCommon('BS_19', false, 'NEW_CHANN_ID', 'CHANN_ID', 'forms[0]',
		'NEW_CHANN_ID,CHANN_NO2,CHANN_NAME2', 
		'CHANN_ID,CHANN_NO,CHANN_NAME', 
		'selectParamsChann');
}

//装修报销单
function selBuild(){
	selCommon('BS_85', false, 'RNVTN_REIT_REQ_ID', 'CHANN_RNVTN_ID', 'forms[0]',
		'RNVTN_REIT_REQ_ID,RNVTN_REIT_REQ_NO,TOTAL_REIT_AMOUNT', 
		'CHANN_RNVTN_ID,CHANN_RNVTN_NO,REIT_AMOUNT', 
		'selectParamsBuild');
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
	
	//详细内容
	if($("#REQ_RSON").val()==null || $("#REQ_RSON").val() == ""){
	      parent.showErrorMsg(utf8Decode("请填入详细内容！"));
	      return false;
	}
	
	//验证图片文件名长度
		if($("#PIC_PATH").val()!=null && $("#PIC_PATH").val() != ""){
	      	   if($("#PIC_PATH").val().length>260){
	      		    parent.showErrorMsg(utf8Decode("上传图片文件名过长！"));
	      		    return false;
	      	   }
		}
			
	    return true;
	}
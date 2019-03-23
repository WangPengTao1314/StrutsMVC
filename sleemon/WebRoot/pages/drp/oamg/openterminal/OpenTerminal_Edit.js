
/**
 *@module 渠道管理-终端管理
 *@func   新开门店申请单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	//addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("openterminal.shtml?action=edit","OPEN_TERMINAL_REQ_ID");
	//下帧跳转
	//parent.window.gotoBottomPage();
 
});


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){

    var RNVTN_PROP= $("#RNVTN_PROP").val();
	//详细地址 
	if($("#ADDRESS").val()==null || $("#ADDRESS").val() == ""){
	      parent.showErrorMsg(utf8Decode("请填入'详细地址'！"));
	      return false;
	}
	if(RNVTN_PROP=="" || RNVTN_PROP == null){
	      parent.showErrorMsg(utf8Decode("请选择'装修性质'！"));
	      return false; 
	}
	if($("#TERM_TYPE").val()==null || $("#TERM_TYPE").val()==""){
	      parent.showErrorMsg(utf8Decode("请选择'终端类型'！"));
	      return false;
	}
	if($("#TERM_VERSION").val()==null || $("#TERM_VERSION").val()== ""){
	    parent.showErrorMsg(utf8Decode("请选择'终端版本'！"));
	    return false; 
	}
	if($("#CITY_TYPE").val()==null || $("#CITY_TYPE").val()==""){
	    parent.showErrorMsg(utf8Decode("请选择'城市类型'！"));
	    return false; 
	}
	if($("#BEG_BUSS_TYPE").val()==null || $("#BEG_BUSS_TYPE").val()==""){
	    parent.showErrorMsg(utf8Decode("请选择'开店类型'！"));
	    return false;
	}
	if($("#LOCAL_TYPE").val()==null || $("#LOCAL_TYPE").val()==""){
	    parent.showErrorMsg(utf8Decode("请选择'商城位置类别'！"));
	    return false;
	}
	return true;
}

function getRnvtnProp(){

   var RNVTN_PROP= $("#RNVTN_PROP").val();
   $.ajax({
		url: "openterminal.shtml?action=loadModel",
		type:"POST",
		dataType:"text",
		data:{"RNVTN_PROP":RNVTN_PROP},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			     var result = jsonResult.data;
			     if(result.RNVTN_PROP=="翻新"){
			        $("#TERM_NO").val("");
			        $("#image").show();
			     }
			     if(result.RNVTN_PROP=="新开"){
			        $("#TERM_NO").val("自动生成");
			        $("#image").hide();
			     }
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function  getTerm(){
 selCommon('BS_134', false, 'TERM_NO', 'TERM_NO', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME,TERM_ABBR,TERM_TYPE,CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P,BUSS_NATRUE,PLAY_BANK_NAME,PLAY_BANK_ACCT,LOGIC_TYPE,TERM_VERSION,AREA_ID,AREA_NO,AREA_NAME,NATION,PROV,CITY,COUNTY,CITY_TYPE,BEG_BUSS_TYPE,ZONE_ID,SALE_STORE_ID,SALE_STORE_NAME,LOCAL_TYPE,BEG_SBUSS_DATE,PERSON_CON,TEL,MOBILE,TAX,POST,EMAIL,WEB,LEGAL_REPRE,BUSS_LIC,AX_BURDEN,ORG_CODE_CERT,BUSS_SCOPE,FI_CMP_NO,BUSS_AREA,STOR_NO,LAST_DECOR_TIME,ADDRESS,BUSS_STATE', 
									                         'TERM_ID,TERM_NO,TERM_NAME,TERM_ABBR,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_NATRUE,PLAY_BANK_NAME,PLAY_BANK_ACCT,LOGIC_TYPE,TERM_VERSION,AREA_ID,AREA_NO,AREA_NAME,NATION,PROV,CITY,COUNTY,CITY_TYPE,BEG_BUSS_TYPE,ZONE_ID,SALE_STORE_ID,SALE_STORE_NAME,LOCAL_TYPE,BEG_SBUSS_DATE,PERSON_CON,TEL,MOBILE,TAX,POST,EMAIL,WEB,LEGAL_REPRE,BUSS_LIC,AX_BURDEN,ORG_CODE_CERT,BUSS_SCOPE,FI_CMP_NO,BUSS_AREA,STOR_NO,LAST_DECOR_TIME,ADDRESS,BUSS_STATE', 'selectParamsT')
}
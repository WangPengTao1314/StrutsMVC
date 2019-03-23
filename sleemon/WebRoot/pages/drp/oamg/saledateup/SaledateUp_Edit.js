/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

 
 
function formCheckedEx(){
	var YEAR = $("#YEAR").val();
	if(null == YEAR || "" == YEAR){
  	   parent.showErrorMsg("请选择'年份'！");
       return false;
	        
    }
	var MONTH = $("#MONTH").val();
	if(null == MONTH || "" == MONTH){
  	   parent.showErrorMsg("请选择'月份'！");
       return false;
	        
    }
	return true;
 }
 

function changeChann(){
	var CHANN_ID_OLD = $("#CHANN_ID_OLD").val();
	var CHANN_ID = $("#CHANN_ID").val();
	if(null == CHANN_ID_OLD || "" == CHANN_ID_OLD){
		 $("#CHANN_ID_OLD").val(CHANN_ID);
		 return;
	}
	 
	if(CHANN_ID_OLD != CHANN_ID){
		$("#TERM_ID").val("");
		$("#TERM_NO").val("");
		$("#TERM_NAME").val("");
	}
    $("#CHANN_ID_OLD").val(CHANN_ID);
}
 
//选择终端信息
function selectTerm(){
	var CHANN_ID = $("#CHANN_ID").val();
	var CHANNS_CHARG = $("#CHANNS_CHARG").val();
	if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请先选择'渠道信息'！");
		return ;
	}
	$("#selTermParam").val(" STATE='启用' and DEL_FLAG=0 and  CHANN_ID_P IN "+CHANNS_CHARG+" and CHANN_ID_P='"+CHANN_ID+"'");
    selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam');
}
 
 
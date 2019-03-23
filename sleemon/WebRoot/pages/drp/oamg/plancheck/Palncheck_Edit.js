/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Edit
 * @author lyg
 * @time   2014-01-26 14:46:31 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});
//终端通用选取
function selAdvcorder(){
	selCommon('BS_82', false, 'TERM_ID', 'TERM_ID', 'forms[0]',
		'TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TEL,TERM_VERSION,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_NAME,AREA_MANAGE_ID,BUSS_SCOPE', 
		'TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TEL,TERM_VERSION,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_NAME,AREA_MANAGE_ID,BUSS_SCOPE', 
		'selectParams');	
}
//机构信息通用选取
function selOrg(){
	selCommon('BS_2', false, 'CHECK_ORG_ID', 'JGXXID', 'forms[0]','CHECK_ORG_ID,CHECK_ORG_NAME','JGXXID,JGMC','selectOrg');
}

function setScoreVal(v){
	$("#CHECK_TOTAL_SCORE").val(v);
}

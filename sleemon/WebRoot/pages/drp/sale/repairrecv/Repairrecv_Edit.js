/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_Edit
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});
function selAddrInfo(){
	var chann=$("#REPAIR_CHANN_ID").val();
	if(""==chann||null==""){
		parent.showErrorMsg(utf8Decode("请选择返修方！"));
		return;
	}
	$("#selectAddrParams").val("DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+chann+"'");
	selCommon('BS_76', false, 'DELIVER_ADDR_ID', 'DELIVER_ADDR_ID', 'forms[0]','DELIVER_ADDR_ID,DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'DELIVER_ADDR_ID,DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams');
}
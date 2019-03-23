/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_Edit
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

function getADVC_SEND_REQ_ID(){
  return $("#ADVC_SEND_REQ_ID").val();
}


 function toFlow(i,cutid) {
//	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/goodsorderhd.shtml?action=toList"+document.getElementById("paramUrl").value ;
    switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
 
 
 function clearPrd(){
	 var ADVC_SEND_REQ_ID_OLD = $("#ADVC_SEND_REQ_ID_OLD").val();
	 var ADVC_SEND_REQ_ID = $("#ADVC_SEND_REQ_ID").val();
	 if(null == ADVC_SEND_REQ_ID_OLD || "" == ADVC_SEND_REQ_ID_OLD){
		 $("#ADVC_SEND_REQ_ID_OLD").val(ADVC_SEND_REQ_ID); 
	 }else if(ADVC_SEND_REQ_ID_OLD != ADVC_SEND_REQ_ID){
		 $("#ADVC_SEND_REQ_ID_OLD").val(ADVC_SEND_REQ_ID); 
		 parent.bottomcontent.$("#jsontb tr:gt(0) input[type='text']").val("");
		  parent.bottomcontent.$("#jsontb tr:gt(0) input[type='hidden']").val("");
	     parent.bottomcontent.$("#jsontb tr:gt(0) td[name='SPECIAL_FLAG_TD']").html("无");
	 }
	 
 }
 
 
 
 
 
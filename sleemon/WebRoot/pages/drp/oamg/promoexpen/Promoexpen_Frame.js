/**
 * @prjName:喜临门营销平台
 * @fileName:Promoexpen_Frame
 * @author chenj
 * @time   2014-01-24 10:59:55 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var paramUrl=document.getElementById("paramUrl");
	var module = document.getElementById("module").value;
	var PRMT_COST_REQ_NO = $("#PRMT_COST_REQ_NO").val();
	if(PRMT_COST_REQ_NO==""){
		 if(paramUrl!=null&&paramUrl.value!="")
		 {
		    framePageInit("promoexpen.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
		 }	
		 else
	     {	 
		    framePageInit("promoexpen.shtml?action=toList&module=" + module+"&audit="+$("#audit").val());
		 }	
	}else{
		 framePageInit("promoexpen.shtml?action=toListByParam&module=" + module+"&audit="+$("#audit").val()+"&PRMT_COST_REQ_NO="+PRMT_COST_REQ_NO);
	}
});

//bottomcontent页面跳转
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var module   = $("#module").val();
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){
	//"label_1"的判断是为了与主从界面通用
	  action = "toDetail";
	}
	var url = "promoexpen.shtml?action="+action+"&PRMT_COST_REQ_ID="+selRowId+"&module="+module;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
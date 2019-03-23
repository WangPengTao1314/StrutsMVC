/**
 * @prjName:供应链_贵人鸟
 * @fileName:Grant_Frame
 * @author zhuxw
 * @time   2013-05-15 10:35:30 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("grant.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("grant.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//明细ID
	var selDtlId = $("#selDtlId").val();
	if("label_3" == showLabelId)
	{
	  if(selDtlId==null||selDtlId=="")
	   {
	      showMsgPanel("请选择一条明细记录！");
	      $("#label_1").click();
	      return false;
	   }
	}
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "grant.shtml?action=childList"
	}else if("label_3" == showLabelId){
	  url = "grant.shtml?action=gchildList&CPBLTZDMXID="+selDtlId;
	}else{
		url = "grant.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&CPBLTZDID="+selRowId);
}
/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Frame
 * @author lyg
 * @time   2014-01-26 14:46:31 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("plancheck.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("plancheck.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "plancheck.shtml?action=childList"
	}else if("label_3" == showLabelId){
	    url = "plancheck.shtml?action=childList2";
	      if(parent.document.getElementById("bottomcontent")!=null){
	      var selRowId = parent.topcontent.document.getElementById("CHANN_CHECK_PLAN_NO").value;
	      //alert("selRowId====="+selRowId);
	      parent.document.getElementById("bottomcontent").src=url+"&CHANN_CHECK_PLAN_NO="+utf8(selRowId);
	      return;
	    }
	 }else{
		url = "plancheck.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&CHANN_CHECK_PLAN_ID="+selRowId);
}
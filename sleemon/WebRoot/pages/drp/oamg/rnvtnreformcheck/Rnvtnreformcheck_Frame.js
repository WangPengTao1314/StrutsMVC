/**
 *@module 渠道管理-装修管理
 *@func   装修整改验收单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	 //框架页面初始化
	 //framePageInit("rnvtnreformcheck.shtml?action=toList");
     var module = document.getElementById("module").value;
     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("rnvtnreformcheck.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("rnvtnreformcheck.shtml?action=toList&module=" + module);
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
    //alert(showLabelId);
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
		url = "rnvtnreformcheck.shtml?action=childList"
	}else{
		url = "rnvtnreformcheck.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&RNVTN_REFORM_ID="+utf8(selRowId));
}
 

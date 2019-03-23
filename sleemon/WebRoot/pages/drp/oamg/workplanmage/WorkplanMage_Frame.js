/**
 *@module渠道管理-工作计划管理
 *@func  工作计划维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//框架页面初始化
	 var module = document.getElementById("module").value;
     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("workplanmage.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("workplanmage.shtml?action=toList&module=" + module);
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var WAREA_ID = $("#WAREA_ID").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "workplanmage.shtml?action=childList"
	}else if("label_3" == showLabelId){
	    url = "workplanmage.shtml?action=childList2";
	    if(parent.document.getElementById("bottomcontent")!=null){
	       var selRowId = parent.topcontent.document.getElementById("WORK_PLAN_ID").value;
	       parent.document.getElementById("bottomcontent").src=url+"&WORK_PLAN_ID="+utf8(selRowId)+"&WAREA_ID="+utf8(WAREA_ID);
	       return;
	    }
	}else{
		url = "workplanmage.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&WORK_PLAN_ID="+utf8(selRowId));
}

//是否刷新标记
var refresh = true;
function setRefreshFlag(isRefresh){
	refresh = isRefresh;
}


/**
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
 *@version 1.1
 *@author zcx
 */
 
$(function(){
	 //framePageInit("rnvtncheck.shtml?action=toList&STATE="+state);
	 var module = document.getElementById("module").value;
     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("rnvtncheck.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	   }
	 else{
	    framePageInit("rnvtncheck.shtml?action=toList&module=" + module);
	  }
});

function gotoBottomPage(showLabelId){
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "rnvtncheck.shtml?action=childList"
	}else{
		url = "rnvtncheck.shtml?action=toDetail"
	}
	$("#bottomcontent").attr("src",url+"&RNVTN_CHECK_ID="+utf8(selRowId));
   }
	var refresh = true;
	function setRefreshFlag(isRefresh){
		refresh = isRefresh;
	}

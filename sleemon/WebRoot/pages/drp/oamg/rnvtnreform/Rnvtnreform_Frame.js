/**
 *@module 渠道管理-装修管理
 *@func   装修整改单维护
 *@version 1.1
 *@author zcx
 */
 
$(function(){
    
	 //framePageInit("rnvtnreform.shtml?action=toList&STATE="+state);
     var module = document.getElementById("module").value;
     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("rnvtnreform.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("rnvtnreform.shtml?action=toList&module=" + module);
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
		url = "rnvtnreform.shtml?action=childList"
	}else{
		url = "rnvtnreform.shtml?action=toDetail"
	}
	$("#bottomcontent").attr("src",url+"&RNVTN_REFORM_ID="+utf8(selRowId));
   }
	var refresh = true;
	function setRefreshFlag(isRefresh){
		refresh = isRefresh;
	}

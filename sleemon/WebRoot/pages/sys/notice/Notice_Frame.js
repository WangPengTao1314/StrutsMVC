$(function(){	//框架页面初始化	//framePageInit("notice.shtml?action=toList");		var module = document.getElementById("module").value;		var paramUrl=document.getElementById("paramUrl");	 if(paramUrl!=null&&paramUrl.value!=""){	    framePageInit("notice.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));	 }else{		   framePageInit("notice.shtml?action=toList&module=" + module);	 }		  });//bottomcontent页面跳转。//function gotoBottomPage(action){//	//获取当前选中的记录//	var selRowId = $("#selRowId").val();//	//初始化时下帧页面的action//	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用//		action = "toDetail";//	}//	var url = "notice.shtml?action="+action+"&NOTICEID="+selRowId;//	//下帧页面跳转//	$("#bottomcontent").attr("src",url);//}//bottomcontent页面跳转。function gotoBottomPage(action){	//获取当前选中的记录	var selRowId = $("#selRowId").val(); 	if(null == action || "" == action){		action = $("#showLabel").attr("value");	} 	   		 	if("label_1" == action){		action = "toDetail";		 $("#showLabel").attr("value","label_1");		 setLabelSelected("label_1");	}else if("label_2" == action){		action = "childList";		 $("#showLabel").attr("value","label_2");		 	setLabelSelected("label_2");	}else if("label_3" == action){		action = "childDeptList";		 $("#showLabel").attr("value","label_3");		 	setLabelSelected("label_3");	}else{		 action = "toEdit";	}	var url = "notice.shtml?action="+action;		//下帧页面跳转	$("#bottomcontent").attr("src",url+"&NOTICEID="+selRowId);	 }

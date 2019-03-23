
/**
  *@module 报表中心
  *@fuc 应收余额查询
  *@version 1.1
  *@author zhu_changxia
  */ 
 
$(function(){
	 //框架页面初始化	
	gotoBottomPage();
	$("#up").click(function(){
		download();
	})
	$("#butHidTop").click(function(){
		var divId=$("#divId").is(":hidden");
		var bottomdiv=$("#bottomdiv").is(":hidden");
		if(!divId&&bottomdiv){
			$("#divId").show();
			$("#divId").css({height:'10%'});
			$("#bottomdiv").show();
			$("#bottomdiv").css({height:'83%'});
		}else if(!divId&&!bottomdiv){
			$("#divId").hide();
			$("#bottomdiv").show();
			$("#bottomdiv").css({height:'93%'});
		}
	})
	$("#butHidBottom").click(function(){
		var divId=$("#divId").is(":hidden");
		var bottomdiv=$("#bottomdiv").is(":hidden");
		if(divId&&!bottomdiv){
			$("#divId").show();
			$("#divId").css({height:'10%'});
			$("#bottomdiv").show();
			$("#bottomdiv").css({height:'83%'});
		}else if(!divId&&!bottomdiv){
			$("#bottomdiv").hide();
			$("#divId").show();
			$("#divId").css({height:'90%'});
		}
	})
	$("#query").click(function(){
	   var CustNo = $("#CHANN_NO").val();
	   var OrgCode = $("#OrgCode").val();
	   if(""==CustNo){
	       showErrorMsg(utf8Decode("请选择渠道编号！"));
		   return;
	   }
	   $("#queryForm").attr("action","querypaymentrep.shtml?action=toList&CustNo="+CustNo+"&OrgCode="+OrgCode+"&flag=1");
	   $("#queryForm").submit();
	}); 
});
 function gotoBottomPage(action){	
	//初始化时下帧页面的action
	if(null == action){
	  action = "toListT";
	}

	var url = "querypaymentrep.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
function reset(){
	$("#CHANN_NO").val("");
	$("#ZTBH").val("");
}
function download(){
	 var CustNo = $("#CHANN_NO").val();
	  var OrgCode = $("#ZTBH").val();
	var url="querypaymentrep.shtml?action=ExcelOutput&CustNo="+CustNo+"&OrgCode="+OrgCode+"&flag=1"
	window.location=url;
}

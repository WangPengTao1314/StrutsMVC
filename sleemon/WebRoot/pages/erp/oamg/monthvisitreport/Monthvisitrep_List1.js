/**
 *@module 销售报表
 *@func 退货单查询
 *@version 1.1
 *@author ghx
 */
$(function(){
	//框架页面初始化	
	gotoBottomPage();
	$("#up").click(function(){
		var RETURN_NO=$("#RETURN_NO").val();//发运单号
		if("" == RETURN_NO){
			showErrorMsg(utf8Decode("请填入发运单号！"));
			return;
		}
		download(RETURN_NO);
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
});
//导出
function download(RETURN_NO){
	window.location="queryReutrnRep.shtml?action=ExcelOutput&RETURN_NO="+utf8(RETURN_NO);
}
function clickBut(){
	var returnNo="";//退货单号

	if($("#RETURN_NO").val()!=null&&$("#RETURN_NO").val()!=""){
		returnNo=$("#RETURN_NO").val();
	}
	
	if("" == returnNo){
		showErrorMsg(utf8Decode("请填入退货单号！"));
		return;
	}	
	
	btnDisable(["btn_query"]);
	
	$("#queryForm").submit();
}

function gotoBottomPage(action){	
	//初始化时下帧页面的action
	if(null == action){
	  action = "listFromU9";
	}

	var url = "queryReutrnRep.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
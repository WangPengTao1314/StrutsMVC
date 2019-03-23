/**
 *@module 销售报表
 *@func 生产情况查询
 *@version 1.1
 *@author ghx
 */
$(function(){
	//框架页面初始化	
	gotoBottomPage();
	$("#up").click(function(){
		var deliverPlanNo=$("#DeliverPlanNo").val();//发运单号
		if("" == deliverPlanNo){
			showErrorMsg(utf8Decode("请填入发运单号！"));
			return;
		}
		download(deliverPlanNo);
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
function download(deliverPlanNo){
	window.location="queryProStatus.shtml?action=ExcelOutput&DELIVERPLANNO="+utf8(deliverPlanNo);
}
function clickBut(){
	var deliverPlanNo="";//发运单号

	if($("#DeliverPlanNo").val()!=null&&$("#DeliverPlanNo").val()!=""){
		deliverPlanNo=$("#DeliverPlanNo").val();
	}
	
	if("" == deliverPlanNo){
		showErrorMsg(utf8Decode("请填入发运单号！"));
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

	var url = "queryProStatus.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
function reset(){
	$("#DELIVER_ORDER_ID").val("");
	$("#DeliverPlanNo").val("");
}
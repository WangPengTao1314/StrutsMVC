/**
 *@module 销售报表
 *@func 销售订单生产状态查询
 *@version 1.1
 *@author ghx
 */
$(function(){
	//框架页面初始化	
	//点击标准订单/非标订单 传递订单编号 直接查询
	var SALE_ORDER_NO = $("#SALE_ORDER_NO").val();
	if(null != SALE_ORDER_NO && "" != SALE_ORDER_NO){
		clickBut();
	}else{
		gotoBottomPage();
	}
	
	$("#up").click(function(){
		var SALE_ORDER_NO=$("#SALE_ORDER_NO").val();//销售订单号
		if("" == SALE_ORDER_NO){
			showErrorMsg(utf8Decode("请填入销售订单号！"));
			return;
		}
		download(SALE_ORDER_NO);
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




function clickBut(){
	var SALE_ORDER_NO = $("#SALE_ORDER_NO").val();//销售订单号
	SALE_ORDER_NO = $.trim(SALE_ORDER_NO);
	if(null == SALE_ORDER_NO || "" == SALE_ORDER_NO){
		showErrorMsg(utf8Decode("请填入销售订单号！"));
		return;
	}
	
	btnDisable(["btn_query"]);
	$("#queryForm").submit();
}



//导出
function download(SALE_ORDER_NO){
	window.location="saleProStatus.shtml?action=ExcelOutput&SALE_ORDER_NO="+utf8(SALE_ORDER_NO);
}

function gotoBottomPage(action){	
	//初始化时下帧页面的action
	 
	//下帧页面跳转
	$("#bottomcontent").attr("src","saleProStatus.shtml?action=listFromU9");
}
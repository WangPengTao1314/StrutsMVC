$(function(){
	var flag=$("#flag").val();
	if("hidden"==flag){
		$("#historySpan").hide();
	}
	var optOldFlag=$("#optOldFlag").val();
	if("1"==optOldFlag){
		$("#resetting").hide();
	}
	url();
})
//点击查看历史记录缩放左侧div和右侧div
function showRightDiv(){
	var history=$("#history").val();
	if("←查看历史记录"==history){
		$("#leftDiv").width("80%");
		//$("#leftDiv").height("90%");
		$("#rightDiv").show();
		$("#history").val("查看历史记录→");
	}else{
		$("#leftDiv").width("95%");
		//$("#leftDiv").height("90%");
		$("#rightDiv").hide();
		$("#history").val("←查看历史记录");
	}
}
function url(id,addFlag){
	var SPCL_TECH_ID="";
	if(""==id||null==id){
		SPCL_TECH_ID=$("#SPCL_TECH_ID").val();
	}else{
		SPCL_TECH_ID=id;
	}
	var PRICE=$("#PRICE").val();
	var DECT_PRICE=$("#DECT_PRICE").val();
	var PRD_ID=$("#PRD_ID").val();
	var CHANN_ID=$("#CHANN_ID").val();
	var USE_FLAG=$("#USE_FLAG").val();
	var TABLE=$("#TABLE").val();
	var BUSSID=$("#BUSSID").val();
	var check=$("#check").val();
	var citeUrl=$("#citeUrl").val();
	var acqModel=$("#acqModel").val();
	var optOldFlag=$("#optOldFlag").val();
	var url="techorderManager.shtml?action="+citeUrl+"&PRD_ID="+PRD_ID+"&CHANN_ID="
			+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE="+TABLE+"&BUSSID="+BUSSID+"&check="
			+check+"&addFlag="+addFlag+"&acqModel="+acqModel+"&PRICE="+PRICE+"&DECT_PRICE="+DECT_PRICE+"&optOldFlag="+optOldFlag;
	$("#topcontent").attr("src",url);
}
function historyWindow(){
	var PRD_ID=$("#PRD_ID").val();
	var filterSpclFlag=$("#filterSpclFlag").val();
	//var data=window.showModalDialog('techorderManager.shtml?action=toHistory&PRD_ID='+PRD_ID,window,"dialogwidth=1000px; dialogheight=400px; status=no");
	window.open('techorderManager.shtml?action=toHistory&PRD_ID='+PRD_ID+'&filterSpclFlag='+filterSpclFlag,'历史特殊工艺','height=400, width=1000, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
function promptInfo(str){
	$("#prompt").val(str);
}
function resetting(){
	var SPCL_TECH_ID="";
	var PRICE=window.frames["topcontent"].document.getElementById("BASE_PRICE").value;
	var DECT_PRICE=$("#DECT_PRICE").val();
	var PRD_ID=$("#PRD_ID").val();
	var CHANN_ID=$("#CHANN_ID").val();
	var USE_FLAG=$("#USE_FLAG").val();
	var TABLE=$("#TABLE").val();
	var BUSSID=$("#BUSSID").val();
	var check=$("#check").val();
	var citeUrl=$("#citeUrl").val();
	var acqModel=$("#acqModel").val();
	var addFlag="0";
	var url="techorderManager.shtml?action="+citeUrl+"&PRD_ID="+PRD_ID+"&CHANN_ID="
			+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE="+TABLE+"&BUSSID="+BUSSID+"&check="
			+check+"&addFlag="+addFlag+"&acqModel="+acqModel+"&PRICE="+PRICE+"&DECT_PRICE="+DECT_PRICE;
	$("#topcontent").attr("src",url);
}

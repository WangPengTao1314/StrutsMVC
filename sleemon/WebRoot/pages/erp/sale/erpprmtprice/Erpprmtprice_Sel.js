
/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	gotoBottomPage();
	InitFormValidator("mainForm");
	$("#res").click(function(){
//		$("#PRMT_PLAN_NO").val("");
//		$("#PRMT_PLAN_ID").val("");
//		$("#PRMT_PLAN_NAME").val("");
		$("#PAR_PRD_NAME").val("");
		$("#PRD_NO").val("");
		$("#PRD_NAME").val("");
	})
	$("input[name='set']").click(function(){
			var set=$('input:radio:checked').val();
			clickBut(set)
	})
});
function clickBut(set){
	var PRMT_PLAN_ID=$("#PRMT_PLAN_ID").val();
	//页面查询条件联动
	if(""==set||null==set){
		set=$('input:radio:checked').val();
	}
	var PRMT_PLAN_NO=$("#PRMT_PLAN_NO").val();
	if(""==PRMT_PLAN_NO){
		alert("请选择促销活动编号")
		return;
	}
	var PRMT_PLAN_NAME=$("#PRMT_PLAN_NAME").val();
	var PAR_PRD_NAME=$("#PAR_PRD_NAME").val();
	var PRD_NO=$("#PRD_NO").val();
	var PRD_NAME=$("#PRD_NAME").val();
	var STATE=$("#STATE").val();
	var PRD_GROUP_NAME=$("#PRD_GROUP_NAME").val();
	var action="toList&PRMT_PLAN_ID="+utf8(PRMT_PLAN_ID)+"&PRMT_PLAN_NO="+utf8(PRMT_PLAN_NO)+"&PRMT_PLAN_NAME="+utf8(PRMT_PLAN_NAME)
						+"&PAR_PRD_NAME="+utf8(PAR_PRD_NAME)+"&PRD_NO="+utf8(PRD_NO)+"&PRD_NAME="+utf8(PRD_NAME)
						+"&set="+utf8(set)+"&STATE="+utf8(STATE)+"&PRD_GROUP_NAME="+utf8(PRD_GROUP_NAME);
	gotoBottomPage(action);
}
//下帧初始化
function gotoBottomPage(action){
	//初始化时下帧页面的action
	if(null == action){
	  action = "toList";
	}
	var url = "erpprmtprice.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
function getPRMT_PLAN_ID(){
	var PRMT_PLAN_ID=$("#PRMT_PLAN_ID").val();
	return PRMT_PLAN_ID;
}

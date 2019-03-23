
/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	gotoBottomPage();
	$("#res").click(function(){
		$("#AREA_NO").val("");
		$("#AREA_NAME").val("");
		$("#CHANN_TYPE").val("");
		$("#CHAA_LVL").val("");
		$("#CHANN_NO").val("");
		$("#CHANN_NAME").val("");
		$("#DECT_TYPE").val("");
	})
	$("input[name='set']").click(function(){
			var set=$('input:radio:checked').val();
			clickBut(set)
	})
});
function clickBut(set){
	var DECT_TYPE=$("#DECT_TYPE").val();
	//0156890--start--刘曰刚--2014-06-19//
	//页面查询条件联动
	if(""==set||null==set){
		set=$('input:radio:checked').val();
	}
	//0156890--End--刘曰刚--2014-06-19//
	var AREA_NO=$("#AREA_NO").val();
	var AREA_NAME=$("#AREA_NAME").val();
	var CHANN_TYPE=$("#CHANN_TYPE").val();
	var CHAA_LVL=$("#CHAA_LVL").val();
	var CHANN_NO=$("#CHANN_NO").val();
	var CHANN_NAME=$("#CHANN_NAME").val();
	var action="toList&DECT_TYPE="+utf8(DECT_TYPE)+"&AREA_NO="+utf8(AREA_NO)+"&AREA_NAME="+utf8(AREA_NAME)
						+"&CHANN_TYPE="+utf8(CHANN_TYPE)+"&CHAA_LVL="+utf8(CHAA_LVL)+"&CHANN_NO="+utf8(CHANN_NO)
						+"&CHANN_NAME="+utf8(CHANN_NAME)+"&set="+utf8(set);
	gotoBottomPage(action);
}
//下帧初始化
function gotoBottomPage(action){
	//初始化时下帧页面的action
	if(null == action){
	  action = "toList";
	}
	var url = "channdiscount.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
function getDECT_TYPE(){
	var DECT_TYPE=$("#DECT_TYPE").val();
	return DECT_TYPE;
}

/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Frame
 * @author lyg
 * @time   2014-01-26 14:46:31 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	framePageInit("termrefinecheck.shtml?action=toParentEdit");
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else if("label_3" == showLabelId){
	      url = "termrefinecheck.shtml?action=childList2";	 
	      var BUSS_SCOPE = topcontent.document.getElementById("BUSS_SCOPE").value;
	      var selRowId =  topcontent.document.getElementById("CHANN_CHECK_PLAN_NO").value;
	      document.getElementById("bottomcontent").src=url+"&CHANN_CHECK_PLAN_NO="+utf8(selRowId)+"&BUSS_SCOPE="+utf8(BUSS_SCOPE);
	      return;
	 }else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "termrefinecheck.shtml?action=modifyToChildEdit"
	}//else if //如果是多个直接定义即可
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&TERM_REFINE_CHECK_ID="+selRowId);
}
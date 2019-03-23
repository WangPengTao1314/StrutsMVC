/**
 * @prjName:喜临门营销平台
 * @fileName:Advise_List_Chld
 * @author wdb
 * @time   2013-08-17 10:29:35 
 * @version 1.1
 */
$(function(){
	var selRowId = $(window.parent.frames["topcontent"].document).find("#selRowId").val(); 
	var FEEDBACK_INFO = $(window.parent.frames["topcontent"].document).find("#FEEDBACK_INFO"+selRowId).val();
	$("#FEEDBACK_INFO").text(FEEDBACK_INFO);
});

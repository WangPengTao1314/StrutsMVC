/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author wdb
 * @time   2013-08-17 10:29:35 
 * @version 1.1
 */
$(function(){
	$("#FEEDBACK_INFO").keyup(function(){
		var text = $(this).text();
		var length = stringLength(text);
		var maxlength = $(this).attr("maxlength");
		if (length > maxlength) {
			$(this).text(this.oVal);
		} else {
			this.oVal = text;
		}
	});
	
	checkButton();
});


//点击的时候 设置按钮权限
function checkButton(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var XTYHID = $("#XTYHID").val();
	var QX = $("#QX").val();
	var APPOINT_PSON_ID = parent.topcontent.$("#APPOINT_PSON_ID"+selRowId).val();
	var state = parent.topcontent.$("#state" + selRowId).val();
	
	if (state !="未反馈") {
		 parent.topcontent.busDisabled();
	}
	
	if("1" != QX){
		parent.topcontent.busDisabled();
	}else{
		if ("00" != APPOINT_PSON_ID && XTYHID != APPOINT_PSON_ID) {
		   parent.topcontent.busDisabled();
	    }
	}
}

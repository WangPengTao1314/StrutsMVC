/**
 * @prjName:喜临门营销平台
 * @fileName:Appoint_Pson.js
 * @author wdb
 * @time   2013-08-17 10:29:35 
 * @version 1.1
 */
$(function(){
	$("#save").click(function(){
		var CMPL_ADVS_ID = $("#CMPL_ADVS_ID").val();
		var APPOINT_PSON_ID = $("#APPOINT_PSON_ID").val();
		if (APPOINT_PSON_ID == ""){
			parent.showErrorMsg("请选择一条记录");
		} else {
			$.ajax({
		url: "advisehd.shtml?action=appointPson",
		type:"POST",
		dataType:"text",
		data:{"CMPL_ADVS_ID":CMPL_ADVS_ID,"APPOINT_PSON_ID":APPOINT_PSON_ID},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.close();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
		}
	});
});

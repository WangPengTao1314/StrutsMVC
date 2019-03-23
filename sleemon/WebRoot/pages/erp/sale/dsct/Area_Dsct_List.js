/**
 *@module 基础数据
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
 */

$(function(){
	
	//页面初始化
	listPageInit("dsct.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	
	$("#delete").click(function(){
		 var selRowId = $("#selRowId").val();
		 if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
		 parent.showConfirm("您确认要删除吗?","topcontent.mtbDelConfirm();","N");

	});
});


function mtbDelConfirm(){
	var AREA_DSCT_ID = $("#selRowId").val();
	var AREA_ID = $("#AREA_ID_"+AREA_DSCT_ID).val();
	 $.ajax({
	 	url:"dsct.shtml?action=delete",
		type:"POST",
	    data:{"AREA_DSCT_ID":AREA_DSCT_ID,"AREA_ID":AREA_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel("删除成功");
				parent.topcontent.pageForm.submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

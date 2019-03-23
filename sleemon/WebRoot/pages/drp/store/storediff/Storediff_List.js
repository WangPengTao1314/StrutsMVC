/**
 * @prjName:喜临门营销平台
 * @fileName:Storediff_List
 * @author wzg
 * @time   2013-08-30 14:03:21 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("storediff.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("storediff.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("storediff.shtml?action=delete", "STOREIN_DIFF_ID");
	
	$("#commit").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}
		parent.showConfirm("您确认要提交吗?","topcontent.doCommit();","N");
		
		
		
		
	});
	
	$("#over").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}
		parent.showConfirm("您确认要完成吗?","topcontent.doOver();","N");

	});
	
});


//提交
function doCommit(){
	showWaitPanel();
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "storediff.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_DIFF_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("提交成功");
				parent.topcontent.pageForm.submit();
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				closeWindow();
			}
		}
	 });
}

//完成
function doOver(){
	var selRowId = $("#selRowId").val();	
	$.ajax({
		url: "storediff.shtml?action=over",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_DIFF_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				parent.topcontent.pageForm.submit();
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
}


function setSelOperateEx(trObj,data){
	
	btnReset();
	
	var state = $(trObj).find("input:last").val();
	if(!("未提交" == state || "出库方打回" == state||"退回"==state)){
		btnDisable(['commit','delete']);
	}
	if("待入库方确认" != state){
		btnDisable(['over']);
	}
	
	
	
	$("#STOREIN_DIFF_DTL_ID",parent.window.document).val("");
	
}
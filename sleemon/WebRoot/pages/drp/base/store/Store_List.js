/**
 * @prjName:喜临门营销平台
 * @fileName:Store_List
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("store.shtml?action=toList&module=" + module);
	mtbAddModiInit();
//	//新增和修改按钮初始化
//	addModiToEditFrameInit("store.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("store.shtml?action=delete", "STORE_ID");
    // 停用
	$("#stop").click(function(){
	   var selRowId = parent.document.getElementById("selRowId").value;
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("该操作会停用记录，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   var selRowId = parent.document.getElementById("selRowId").value;
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("该操作会启用记录，是否继续?","topcontent.listStartConfirm();");
   });
});
//记录按钮根据状态控制
 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if(state == "启用"){
		btnDisable(["start","modify","delete"]);
	}else if(state == "停用"){
		btnDisable(["stop","delete"]);
	}else if (state == "制定"){
		btnDisable(["stop"]);
	}
 }
//停用记录
function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "store.shtml?action=changeStateStop&STORE_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}
//启用记录
function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "store.shtml?action=changeStateStart&STORE_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("状态修改成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}

//获取库位标记位
function getStorageFlag(storageFlag){
//	if (storageFlag == 0) {
//		parent.$("#label_1").attr("disabled","disabled");
//		parent.document.getElementById("label_2").click();
//	} else {
//		parent.$("#label_1").removeAttr("disabled");
//	}
}

//根据终端库房标记设置所属单位通用选取
function channCheck(){
	var flag = $("input[name='TERM_STROE_FLAG']:checked").val();
	if(flag == 1){
		selCommon('BS_27', false, 'BEL_CHANN_ID', 'TERM_ID', 'forms[1]','BEL_CHANN_NO,BEL_CHANN_NAME','TERM_NO,TERM_NAME', 'selTerm');
	} else if (flag == 0){
		selCommon('BS_19', false, 'BEL_CHANN_ID', 'CHANN_ID', 'forms[1]','BEL_CHANN_NO,BEL_CHANN_NAME','CHANN_NO,CHANN_NAME', 'selChann');
	} else {
		parent.showErrorMsg("请先选择是否终端库房");
	}
}
function getSTATE(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return document.getElementById("state" + selRowId).value;
}
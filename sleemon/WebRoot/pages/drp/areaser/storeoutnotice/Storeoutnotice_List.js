
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	
	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	var module = parent.window.document.getElementById("module").value;	
    $("#query").click(function(){
    	if("sh"==module){
    		$("#STATE option[text='未提交']").remove();
    	}
	});
    //主从及主从从列表页面通用加载方法
	listPageInit("storeoutnotice.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("storeoutnotice.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("storeoutnotice.shtml?action=delete", "STOREOUT_NOTICE_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		commit(selRowId);
	});
})
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
}



function getFrom(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var from=$("#from"+selRowId).val()
	return from;
}



function setAmoutVal(v){
	var selRowId = parent.document.getElementById("selRowId").value;
	$("#STOREOUT_AMOUNT"+selRowId).html(v);
}


function commit(selRowId){
    $.ajax({
	 url: "storeoutnotice.shtml?action=toCommit&STOREOUT_NOTICE_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("提交成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}



function getStoreId(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#STORE_ID"+selRowId).val();
}







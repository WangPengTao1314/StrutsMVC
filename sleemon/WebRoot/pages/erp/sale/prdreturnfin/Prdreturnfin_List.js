/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_List
 * @author wzg
 * @time   2013-08-19 15:33:31 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("prdreturnfin.shtml?action=toList&module=" + module);
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确定已经核价","topcontent.commit();");
	});
    
    $("#reback").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$("#PRD_RETURN_ID").val(selRowId);
    	$("#reverseDiv").show();
	});
	$("#close").click(function(){
    	$("#reverseDiv").hide();
    	$("#REMARK").val("");
    	$("#PRD_RETURN_ID").val("");
    });
    $("#audit").click(function(){
    	reback();
    })
    
    setSelOperateEx();
});


function reback(){
 var PRD_RETURN_ID = $("#PRD_RETURN_ID").val();
 var REMARK=$("#REMARK").val();
 $.ajax({
	 url: "prdreturnfin.shtml?action=reback&PRD_RETURN_ID="+utf8(PRD_RETURN_ID)+"&REMARK="+utf8(REMARK),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel(utf8Decode(jsonResult.messages));
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}


function commit(){
	var selRowId = parent.document.getElementById("selRowId").value;
	 $.ajax({
	 url: "prdreturnfin.shtml?action=toCommit&PRD_RETURN_ID="+utf8(selRowId),
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
	closeWindow();
}


//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=退回
	if (state == "退回") {
	 	btnDisable(["commit","reback"]);
    }
	
	if (state == "已提交工厂") {
	 	btnDisable(["commit","reback"]);
    }
}

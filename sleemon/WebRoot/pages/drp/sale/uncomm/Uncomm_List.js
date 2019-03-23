
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;
	//主从及主从从列表页面通用加载方法
	listPageInit("uncomm.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("uncomm.shtml?action=toEditFrame"); 
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("uncomm.shtml?action=delete", "ADVC_ORDER_ID");
	//提交按钮单独写
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		commit(selRowId);
	});
    $("#close").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		advcClose(selRowId);
	});
    
    $("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
		//parent.showErrorMsg("功能开发中。。。");
    })
//	$("#uploading").click(function () {
//		var selRowId = parent.document.getElementById("selRowId").value;
//		if (selRowId == "") {
//			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
//			return;
//		}
//		$("#upFile").show();
//	});
//	$("#up").bind("input propertychange" ,function(){
//		var up=$("#up").val();
//		if($("#up").val()==null||$("#up").val()==""){
//			return;
//		}else{
//			$("#upFile").hide();
//				uploading();
//		}
//	})
	$("#close").click(function(){
		$("#RYXXID").val("");
		$("#XM").val("");
		$("#RYBH").val("");
		$("#audit").hide();
	})
//	$("#download").click(function(){
//		var selRowId = parent.document.getElementById("selRowId").value;
//		if (selRowId == "") {
//			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
//			return;
//		}
//		download();
//	})
	$("#moveAdv").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		audit(selRowId);
	})
});
function commit(selRowId){
	showWaitPanel();
    $.ajax({
	 url: "uncomm.shtml?action=toCommit&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("提交成功");
            $("#pageForm").submit();
		}else{
			if(null==jsonResult.messages||""==jsonResult.messages){
				$("#audit").show();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				closeWindow();
			}
		}
	  }
    });
}
function advcClose(selRowId){
    $.ajax({
	 url: "uncomm.shtml?action=toClose&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("关闭成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
//导入
//function uploading(){
//	var fileName=$("#up").val();
//	$("#up").val("");
//	if(""==fileName||null==fileName){
//		return;
//	}
//	$.ajax({
//	 url: "advcorder.shtml?action=parseExecl&fileName="+utf8(fileName),
//	 type:"POST",
// 	 dataType:"text",
//	 complete: function(xhr){
//		eval("jsonResult = "+xhr.responseText);
//		if(jsonResult.success===true){
//			$("#up").val("");
//            $("#pageForm").submit();
//		}else{
//			parent.showErrorMsg(utf8Decode(jsonResult.messages));
//		}
//	  }
//    });
//}
//当明细列表的折扣率有低于当前登录人员的最低输入折扣率时，弹出审核人窗口，把该条预订单转到审核人那里审核
function audit(selRowId){
	var RYXXID=$("#RYXXID").val();
	var XM=$("#XM").val();
	if(""==RYXXID||null==RYXXID){
		parent.showErrorMsg("请选择核价人");
			return;
	}
    $.ajax({
	 url: "uncomm.shtml?action=toPrice&ADVC_ORDER_ID="+utf8(selRowId)+"&RYXXID="+utf8(RYXXID)+"&XM="+utf8(XM),
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
function setAmoutVal(v){
	var selRowId = parent.document.getElementById("selRowId").value;
	$("#PAYABLE_AMOUNT"+selRowId).html(v);
}
function print(selRowId){
	//跳转扫码打印页面
//	$("#printIframe").attr("src","uncomm.shtml?action=printInfo&ADVC_ORDER_ID="+selRowId);
	window.open('uncomm.shtml?action=printInfo&ADVC_ORDER_ID='+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}
function selRYXX(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var termID=$("#term"+selRowId).val();
	var sql=" RYZT ='启用' and DELFLAG=0  and RYJB='门店_店长' and  BMXXID='"+termID+"' ";
	$("#RY").val(sql);
	selCommon('System_0', false, 'RYXXID','RYXXID', 'forms[0]','RYXXID,RYBH,XM','RYXXID,RYBH,XM', 'RY');
}
//导出
//function download(){
//	var selRowId = parent.document.getElementById("selRowId").value;
//	window.location="uncomm.shtml?action=ExcelOutput&ADVC_ORDER_ID="+utf8(selRowId);
//}
function openUrl(id){
	window.open('changeadvcorder.shtml?action=toFrame&ADVC_ORDER_ID='+id+"&showFlag=1",'预订单变更查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}

function setSelOperateEx(obj){

	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
    if (state == "关闭") {
	  btnDisable(["modify","commit","close"]);
	}
 }
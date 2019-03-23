/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_List
 * @author lyg
 * @time   2013-08-19 14:35:52 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("termreturn.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("termreturn.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("termreturn.shtml?action=delete", "ADVC_ORDER_ID");
	$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("确定提交退货吗？","topcontent.commit();");
		
	});
	
	
	$("#query").click(function(){
		if("wh" == module){
		     $("#STATE option[text='已入库']").remove();
		      $("#STATE option[text='已结算']").remove();
		}
		if("sh" == module){
		} 
	});
	
	$("#expertExcel").click(function(){
		 $("#queryForm").attr("action","termreturn.shtml?action=expertExcel&module=" + module);
		 $("#queryForm").submit();
	});
	
});



 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//0157688--Start--刘曰刚--2014-06-26//
	//修改处未提交和退回状态外所有状态锁定修改删除按钮
    if(state != "未提交"&&state != "退回"){
		btnDisable(["modify","delete","commit"]);
	}
    //0157688--End--刘曰刚--2014-06-26//
 }
function commit(){
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "termreturn.shtml?action=toCommit&ADVC_ORDER_ID="+utf8(selRowId),
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
function getRadio(){
	return null;
}
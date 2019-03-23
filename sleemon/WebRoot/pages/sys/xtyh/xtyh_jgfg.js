/**

  *@module 系统管理
  *@fuc 系统用户列表

  *@version 1.1

  *@author 陈建军
  */
$(document).ready(function(){

	var xtyhzt = parent.topcontent.$("#selYhzt").val();
	if(xtyhzt=='启用'){
		$("#add").attr("disabled",true);
		$("#delete").attr("disabled",true);
	}else{
		$("#add").attr("disabled",false);
		$("#delete").attr("disabled",false);
	}
	
	var selRowId = parent.document.getElementById("selRowId").value;

	if(selRowId == null || '' == selRowId){
	    btnDisable(["add","delete"]);
	}
	
	$("#add").click(function(){
		$("#JGXXID").val("");
		var _jgFilter = $("jgFilter").val();
		var _xtyhid = parent.document.getElementById("selRowId").value;
		_jgFilter = " NOT EXISTS (SELECT 1 FROM T_SYS_XTYHJGFG B WHERE A.JGXXID=B.JGXXID AND B.XTYHID='"+_xtyhid+"') AND A.JGZT='启用' AND A.DELFLAG=0";
		$("#jgFilter").val(_jgFilter);
		
		var rnt = selCommon('175', true, 'JGXXID', 'JGXXID', 'forms[0]','JGBH', 'JGBH', 'jgFilter');
		if(rnt[1]){
			var _jgxxids = $("#JGXXID").val();
			var _rowNum=0;
			if(_jgxxids!=""){
				_rowNum=_jgxxids.split(",").length;
			}
			$.ajax({
				url: "xtyh.shtml?action=insertJgfgMx",
				type:"POST",
				dataType:"text",
				data:{JGXXIDS:_jgxxids,XTYHID:_xtyhid,ROWNUM:_rowNum},
				complete: function(xhr){
		            eval("json = "+xhr.responseText);
		            var data = json.messages;
		            parent.showMsgPanel(data);
		            
		        	location.reload();
		        }
			});
		}
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
});
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "xtyh.shtml?action=deleteJgfgMx",
		type:"POST",
		dataType:"text",
		data:{"XTYHJGFGID":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			location.reload();
		}
	});
}
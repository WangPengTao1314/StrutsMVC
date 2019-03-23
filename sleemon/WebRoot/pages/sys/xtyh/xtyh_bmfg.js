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
		$("#BMXXID").val("");
		var _bmFilter = $("bmFilter").val();
		var _xtyhid = parent.document.getElementById("selRowId").value;
		_bmFilter = " NOT EXISTS (SELECT 1 FROM T_SYS_XTYHBMFG B WHERE A.BMXXID=B.BMXXID AND B.XTYHID='"+_xtyhid+"') AND A.BMZT='启用' AND A.DELFLAG=0";
		$("#bmFilter").val(_bmFilter);
		var rnt = selCommon('176', true, 'BMXXID', 'BMXXID', 'forms[0]','BMBH', 'BMBH', 'bmFilter');
		if(rnt[1]){
			var _bmxxids = $("#BMXXID").val();
			var _rowNum=0;
			if(_bmxxids!=""){
				_rowNum=_bmxxids.split(",").length;
			}
			$.ajax({
				url: "xtyh.shtml?action=insertBmfgMx",
				type:"POST",
				dataType:"text",
				data:{BMXXIDS:_bmxxids,XTYHID:_xtyhid,ROWNUM:_rowNum},
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
		url: "xtyh.shtml?action=deleteBmfgMx",
		type:"POST",
		dataType:"text",
		data:{"XTYHBMFGID":ids},
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
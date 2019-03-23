
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	headColumnSort("ordertb","pageForm");
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
	$("#allPrint").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条单据");
			return;
		}
		var ids="";
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
		$.ajax({
			 url: "drpreturna.shtml?action=getSaleSession",
			 type:"POST",
			 data:{"PRD_RETURN_IDS":ids},
		 	 dataType:"text",
			 complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					var data = jsonResult.data;
					if(null==data){
						parent.showErrorMsg("存储选取退货单信息失败！");
					}else{
						window.open('drpreturna.shtml?action=allPrint&sessionId='+data.sessionId,'批量打印','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
					}
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			  }
		    });
	})
});
function queryForm(){
	$("#queryForm").submit();
}

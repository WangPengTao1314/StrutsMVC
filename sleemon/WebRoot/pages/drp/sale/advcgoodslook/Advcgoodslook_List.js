
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
	listPageInit("advcgoodslook.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advcgoodslook.shtml?action=toEditFrame"); 
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advcgoodslook.shtml?action=delete", "ADVC_ORDER_ID");
 
    $("#toCerit").click(function () {
    	$("#toCeritPage").click();
	});
    $("#expertExcel").click(function(){
		 $("#queryForm").attr("action","advcgoodslook.shtml?action=expertExcel&module=" + module);
		 $("#queryForm").submit();
	});
   toShowPage();
   dateDiffColor();
});
 


function dateDiffColor(){
	$("#ordertb tr").each(function(){
		//子表的交期有在三天之内的今天、明天、后天
		var CHILD_COUNT = $(this).find("input[name='CHILD_COUNT']").val();
		//子表的交期有在今天之前的
		var CHILD_WARN_COUNT = $(this).find("input[name='CHILD_WARN_COUNT']").val();
		if(CHILD_WARN_COUNT>0){
			$(this).find("td").css("background-color", "#E6B9B8");
		}else if(CHILD_COUNT>0){
			$(this).find("td").css("background-color", "#B4EEB4");
		}
		
	});
	
	
 
}



function toShowPage(){
	var mainFrame = window.top.mainFrame;
	$("#toCeritPage").click(function(){
		 var label = $(this).attr("label");
		 var url = $(this).attr("href");
		 mainFrame.addTab(label,label,"../../"+url);
		return false;

	});
}



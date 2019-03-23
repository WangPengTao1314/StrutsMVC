
/**
 * @prjName: 喜临门营销平台
 * @fileName:Monthvisitrep_List
 * @author zcx
 * @time   2014-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
})
//url:报表跳转action
//flag:是否是报表工程，为1时拼接报表工程ip地址，为0时拼接sleemon工程地址，默认为1
function openUrl(lable,url,flag){
	if(""==url||null==url){
		showErrorMsg("该报表开发还未完成，敬请期待！");
		return;
	}
	if(""==flag||null==flag){
		flag=1;
	}
	var urlAll;
	if("0"==flag){
		urlAll="../../"+url;
	}else{
		var S_USER_ID=$("#S_USER_ID").val();
		var S_ZTXXID=$("#S_ZTXXID").val();
		var S_GOTO_FLG=$("#S_GOTO_FLG").val();
		urlAll=$("#RUNQIAN_REPORT_URL").val()+"/"+url+"&S_USER_ID="+S_USER_ID+"&S_ZTXXID="+S_ZTXXID+"&S_GOTO_FLG="+S_GOTO_FLG;
	}
	toShowPage(lable,urlAll)
//	$("#queryForm").attr("action",urlAll);
//	$("#queryForm").submit();
//	window.open(urlAll,'报表查看',' height=500,width=700,  top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
}

function toShowPage(lable,urlAll){
	var mainFrame = window.top.mainFrame;
 	mainFrame.addTab(lable,lable,urlAll);
}

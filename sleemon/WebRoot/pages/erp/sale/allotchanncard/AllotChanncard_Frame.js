/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
	 //框架页面初始化
	 framePageInit("allotchanncard.shtml?action=toMainPage");
	 gotoBottomPage();
});

//bottomcontent页面跳转
function gotoBottomPage(){
	var  url = "allotchanncard.shtml?action=toList&firstFlag=true"
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}

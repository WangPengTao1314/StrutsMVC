/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 发运确认
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	framePageInit("deliverconfm.shtml?action=toParentEdit");
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "deliverconfm.shtml?action=modifyToChildEdit"
	} 
	
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&DELIVER_ORDER_ID="+selRowId);
}
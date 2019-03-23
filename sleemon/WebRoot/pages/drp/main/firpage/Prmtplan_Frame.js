/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtplan_Frame
 * @author zzb
 * @time   2013-08-23 16:04:28 
 * @version 1.1
 */
$(function(){
	  framePageInit("drpFirpage.shtml?action=listPrmt" );
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
 
	var url = "drpFirpage.shtml?action=toPrmtDetail";
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&PRMT_PLAN_ID="+selRowId);
}
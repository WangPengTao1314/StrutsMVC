/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["edit","delete"]);
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
});
function url(SPCL_TECH_ID){
		window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

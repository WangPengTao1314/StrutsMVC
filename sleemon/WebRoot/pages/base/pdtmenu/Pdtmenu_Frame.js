


/**
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function(){
	//框架页面初始化
	framePageInit("pdtmenu.shtml?action=toList");
	$("#leftcontent").attr("src", "pdtmenu.shtml?action=showTree");
	treeShowHide();
	var paramUrl=document.getElementById("paramUrl");
//	 if(paramUrl!=null&&paramUrl.value!="")
//	    framePageInit("product.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
//	 else
//	    framePageInit("product.shtml?action=toList&module=" + module);
});
function gotoBottomPage(showLabelId) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var url;
	//初始化时下帧页面的action
	if("toEdit" == showLabelId){//跳转新增或修改页面
		url="pdtmenu.shtml?action=toEdit&PRD_ID="+selRowId;
	}else{//详细信息页面
		url = "pdtmenu.shtml?action=toDetail&PRD_ID="+selRowId;
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

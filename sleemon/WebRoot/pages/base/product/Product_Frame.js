


/**
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function(){
	//框架页面初始化
	framePageInit("product.shtml?action=toList");
	$("#leftcontent").attr("src", "product.shtml?action=showTree");
	treeShowHide();
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("product.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{
	    framePageInit("product.shtml?action=toList&module=" + module);
	 }
});
function gotoBottomPage(showLabelId) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var url;
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
		if("toEdit"==showLabelId){
			showLabelId="label_1";
		}
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	//初始化时下帧页面的action
	if ("label_4" == showLabelId) {//label_4跳转货品套明细
		url = "product.shtml?action=toSuitList";
	}else if("label_3" == showLabelId){//label_3跳转计量单位明细
		url="product.shtml?action=toUnitList";
	}else if("label_2" == showLabelId){
		url="product.shtml?action=toPrdTech";//跳转货品特殊工艺维护
	}else if("toEdit"==showLabelId){
		url="product.shtml?action=toEdit";//新增
		setLabelSelected("label_1");
	}else if("label_1"==showLabelId){//详细信息页面
		url="product.shtml?action=toDetail";
		setLabelSelected("label_1");
	}else{
		url="product.shtml?action="+showLabelId;
	}
	$("#flag").val("0");
	//下帧页面跳转
	if("label_4" == showLabelId){
		$("#bottomcontent").attr("src", url+"&MAIN_PRD_ID="+utf8(selRowId));
	}else{
		$("#bottomcontent").attr("src", url+"&PRD_ID="+utf8(selRowId));
	}
}

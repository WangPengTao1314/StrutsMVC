/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_Frame
 * @author lyg
 * @time   2013-09-07 09:54:59 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	framePageInit("inventory.shtml?action=toParentEdit");
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
		url = "inventory.shtml?action=modifyToChildEdit"
	}//else if //如果是多个直接定义即可
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&PRD_INV_ID="+selRowId);
}
//获取库房，库位，货品id查找货品信息跳转下帧明细为编辑页面并带入数据
function gotoBottom(STORE_ID,STORG_ID,PRD_ID){
	var url= "inventory.shtml?action=AccTypeToChildEdit";
	var parameter="";
	if(STORE_ID!=null){
		parameter=parameter+"&STORE_ID="+STORE_ID;
	}
	if(STORG_ID!=null){
		parameter=parameter+"&STORG_ID="+STORG_ID;
	}
	if(PRD_ID!=null){
		var PRD_IDS="";
		var PRD_IDarr=PRD_ID.split(",");
		for(var i=0;i<PRD_IDarr.length;i++){
			PRD_IDS=PRD_IDS+"'"+PRD_IDarr[i]+"',";
		}
		PRD_IDS = PRD_IDS.substr(0,PRD_IDS.length-1);
		parameter=parameter+"&PRD_IDS="+PRD_IDS;
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+parameter);
}
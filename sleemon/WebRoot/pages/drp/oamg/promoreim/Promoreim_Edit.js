/**
 * @prjName:喜临门营销平台
 * @fileName:Promoreim_Edit
 * @author chenj
 * @time   2014-01-25 19:49:05 
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("promoreim.shtml?action=edit","PRMT_COST_REIT_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
	
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
   if($("#COST_TYPE").val()==''){
   		parent.showErrorMsg("'费用类别'不能为空！");
   		return false;
   }
   return true;
}

function queryPromoexpen(){
	var CHANN_ID = $("#CHANN_ID").val();
 	$("#con").val(" STATE='审核通过' and DEL_FLAG=0 and CHANN_ID in "+CHANN_ID);
	var returnArray = selCommon('BS_87', false, 'PRMT_COST_REQ_ID', 'PRMT_COST_REQ_ID', 'forms[0]','PRMT_COST_REQ_NO,CHANN_NO,CHANN_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_NO,AREA_NAME,ZONE_ADDR,AREA_MANAGE_NAME,CHANN_ID,ZONE_ID,AREA_MANAGE_ID,AREA_MANAGE_TEL,TOTAL_PRMT_COST,REIT_REQ_AMOUNT,COST_TYPE', 'PRMT_COST_REQ_NO,CHANN_NO,CHANN_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_NO,AREA_NAME,CITY_NAME,AREA_MANAGE_NAME,CHANN_ID,ZONE_ID,AREA_MANAGE_ID,AREA_MANAGE_TEL,TOTAL_PRMT_COST,REIT_REQ_AMOUNT,COST_TYPE','con');
	$("#con").val("");
}
$(function(){
	headColumnSort();
	$("#reset").click(function(){
		$("#CRE_TIME_BEGIN").val("");
		$("#CRE_TIME_END").val("");
		$("#DM_SPCL_TECH_NO").val("");
		$("#SPCL_DTL_REMARK").val("");
	})
});
function selAdvcorder(){
	selCommon('BS_55', false, 'ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'forms[0]','ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'selectADC');
}
function selGoodsOrder(){
	selCommon('BS_56', false, 'GOODS_ORDER_NO', 'GOODS_ORDER_NO', 'forms[0]','GOODS_ORDER_NO', 'GOODS_ORDER_NO', 'selectGoods');
}
function getSpcl(){
	var SPCL_TECH_ID=$('input[name="eid"]:checked').val();
	if(null==SPCL_TECH_ID||""==SPCL_TECH_ID){
		parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
		return;
	}
	window.parent.opener.url(SPCL_TECH_ID,"1");
	window.close();
}
function url(SPCL_TECH_ID){
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
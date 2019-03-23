
/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	gotoBottomPage();
});
function clickBut(){
	var rebateQuery=$("#rebateQuery").prop("checked");//返利商品
	var lassQuery=$("#lassQuery").prop("checked");//赠品
	var type=$("#PRD_TYPE").val();//选中的分类
	var brand="";//选中的品牌
	var spec=$("#PRD_SPEC").val();//选中的规格型号
	var prd_Info="";//文本框输入的货品名称
	var prmt="";//促销信息
	$("input[name='brand']").each(function(){
		if($(this).attr("checked")){
			brand=brand+"'"+$(this).val()+"',";
		}
	})
	brand = brand.substr(0,brand.length-1);
//	$("input[name='spec']").each(function(){
//		if($(this).attr("checked")){
//			spec=spec+"'"+$(this).val()+"',";
//		}
//	})
//	spec = spec.substr(0,spec.length-1);
//	$("input[name='type']").each(function(){
//		if($(this).attr("checked")){
//			type=type+"'"+$(this).val()+"',";
//		}
//	})
//	type = type.substr(0,type.length-1);
	
	$("input[name='prmt']").each(function(){
		if($(this).attr("checked")){
			prmt=prmt+"'"+$(this).val()+"',";
		}
	})
	prmt = prmt.substr(0,prmt.length-1);
	if($("#prd_Info").val()!=null&&$("#prd_Info").val()!=""){
		prd_Info=$("#prd_Info").val();
	}
	
	var action="toList&brand="+utf8(brand)+"&spec="+utf8(spec)+"&type="+utf8(type)+"&prd_Info="+utf8(prd_Info)+"&prmt="+utf8(prmt)+"&rebateQuery="+utf8(rebateQuery)+"&lassQuery="+utf8(lassQuery);
	gotoBottomPage(action);
}
//下帧初始化
function gotoBottomPage(action){
	//初始化时下帧页面的action
	if(null == action){
	  action = "toList";
	}
	var url = "myorder.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
function hide(){
	if($("#showOrHide").val()=="↑"){
		$("#topDiv").hide();
		$("#showOrHide").val("↓");
		$("#bottomdiv").css({height:'100%'});
	}else{
		$("#topDiv").show();
		$("#showOrHide").val("↑");
		$("#bottomdiv").css({height:'78%'});
	}
}
//0156609 --start--刘曰刚--2013-06-18//
//修改规格型号通用选取查询条件，去重
function selectSpec(){
	var LEDGER_ID=$("#LEDGER_ID").val();
	var sql="(select distinct PRD_SPEC from BASE_PRODUCT where STATE='启用' and FINAL_NODE_FLAG=1 and DEL_FLAG=0 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='"+LEDGER_ID+"')))";
	selCommon('BS_94', false, 'PRD_SPEC','PRD_SPEC', 'forms[0]','PRD_SPEC','PRD_SPEC', '','',sql);
	clickBut();
}
//0156609 --End--刘曰刚--2013-06-18//

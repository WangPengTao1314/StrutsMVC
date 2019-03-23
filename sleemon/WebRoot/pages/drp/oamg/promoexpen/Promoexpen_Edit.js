/**
 * @prjName:喜临门营销平台
 * @fileName:Promoexpen_Edit
 * @author chenj
 * @time   2014-01-24 10:59:55 
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("promoexpen.shtml?action=edit","PRMT_COST_REQ_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
});
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){

   if($("#COST_TYPE").val()==''){
   		parent.showErrorMsg("'费用类别'不能为空！");
   		return false;
   }
   var reg = new RegExp("^[0-9]*$");
   var TOTAL_PRMT_COST = $("#TOTAL_PRMT_COST").val();
   if(!reg.test(TOTAL_PRMT_COST)){
        parent.showErrorMsg("'此次费用共计'请输入数字!");
        return false;
   }
    
   var BUDGET_AMOUNT = $("#BUDGET_AMOUNT").val();
   if(!reg.test(BUDGET_AMOUNT)){
        parent.showErrorMsg("'预批金额'请输入数字!");
        return false;
   }
   
   var RETAIL_AMOUNT = $("#RETAIL_AMOUNT").val();
   if(!reg.test(RETAIL_AMOUNT)){
        parent.showErrorMsg("'计划零售额'请输入数字!");
        return false;
   }
   
   var BILL_AMOUNT = $("#BILL_AMOUNT").val();
   if(!reg.test(BILL_AMOUNT)){
        parent.showErrorMsg("'计划开单数'请输入数字!");
        return false;
   }
   
   var PRO_SCREEN = "";
   var PRO_SCREENs = document.getElementsByName("PRO_SCREENs");
   for(var i=0;i<PRO_SCREENs.length;i++){
	    if(PRO_SCREENs[i].checked){
	       PRO_SCREEN = PRO_SCREENs[i].value;
	    }
	}
   if(PRO_SCREEN==""){
	   chkCanErrMsg("", "请选择'是否制作VI画面'");
	   return false;
	}
   return true;
}

//查询加盟商列表
 function queryChannel(){
	selCommon('BS_86', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
		'CHANN_ID,CHANN_NO,CHANN_NAME,ZONE_ID,ZONE_ADDR,CHANN_PERSON_CON,CHANN_TEL,AREA_NO,AREA_NAME,AREA_MANAGE_NAME,AREA_MANAGE_TEL,AREA_ID,AREA_MANAGE_ID',
		'CHANN_ID,CHANN_NO,CHANN_NAME,ZONE_ID,CITY,PERSON_CON,TEL,AREA_NO,AREA_NAME,CHRG_NAME,SJ,AREA_ID,CHRG_ID','selectParams');
	
 }
 
 function getChannSendCost(){
    $("#TOTAL_REAL_AMOUNT").val(($("#RETAIL_AMOUNT").val()*$("#BILL_AMOUNT").val()*0.03).toFixed(2));  //.toFixed(2);
    $("#TOTAL_ADVC_AMOUNT").val(($("#BUDGET_AMOUNT").val()*0.03).toFixed(2));
    $("#TOTAL_RATE").val(($("#TOTAL_ADVC_AMOUNT").val()/$("#TOTAL_REAL_AMOUNT").val()).toFixed(2));
    $("#WARE_TOTAL_BUDGET_AMOUNT").val($("#TOTAL_PRMT_COST").val()-$("#TOTAL_REAL_AMOUNT").val());
    $("#WARE_TOTAL_PRE_AMOUNT").val(($("#WARE_TOTAL_BUDGET_AMOUNT").val()*0.03).toFixed(2));
    $("#WARE_TOTAL_AVALIABLE_AMOUNT").val($("#WARE_TOTAL_BUDGET_AMOUNT").val()-$("#WARE_TOTAL_PRE_AMOUNT").val());
    $("#AREA_TOTAL_BUDGET_AMOUNT").val(0);
    $("#AREA_TOTAL_PRE_AMOUNT").val(0);
    $("#AREA_TOTAL_AVALIABLE_AMOUNT").val(0);
 }
 
 function jsOne(){
 	//加盟商本季度累计已发货费用
 	$("#TOTAL_SEND_COST_AMOUNT").val(($("#TOTAL_SEND_AMOUNT").val()*0.03).toFixed(2));
 	//加盟商本季度发货剩余费用
 	if($("#TOTAL_SEND_COST_AMOUNT").val()!="" && $("#REQ_COST_AMOUNT").val()!=""){
 	  $("#TOTAL_SEND_LEFT_AMOUNT").val(($("#TOTAL_SEND_COST_AMOUNT").val()-$("#REQ_COST_AMOUNT").val()).toFixed(2));
 	}
 	//加盟商本季度发货费效比
 	if($("#REQ_COST_AMOUNT").val()!="" && $("#TOTAL_SEND_AMOUNT").val()!=""){
 	  $("#SEND_COST_RATE").val(($("#REQ_COST_AMOUNT").val()/$("#TOTAL_SEND_AMOUNT").val()).toFixed(4));
 	}
 	//加盟商本季度累计发货预算费用：
 	$("#TOTAL_SEND_BUDGET_AMOUNT").val(($("#TOTAL_BUDGET_AMOUNT").val()*0.03).toFixed(2));
 	//加盟商本季度预算剩余费用
 	if($("#TOTAL_SEND_BUDGET_AMOUNT").val()!="" && $("#REQ_COST_AMOUNT").val()!=""){
 	  $("#TOTAL_BUDGET_LEFT_AMOUNT").val(($("#TOTAL_SEND_BUDGET_AMOUNT").val()-$("#REQ_COST_AMOUNT").val()).toFixed(2));
 	}
 	//加盟商本季度预算费效比
 	if($("#REQ_COST_AMOUNT").val()!="" && $("#TOTAL_BUDGET_AMOUNT").val()!="") {
 	  $("#SEND_BUDGET_RATE").val(($("#REQ_COST_AMOUNT").val()/$("#TOTAL_BUDGET_AMOUNT").val()).toFixed(4));
 	}
 	//所属战区本季度发货费效比
 	if($("#AREA_REQ_COST_AMOUNT").val()!="" && $("#TOTAL_AREA_SEND_AMOUNT").val()!=""){
 	  $("#AREA_SEND_COST_RATE").val(($("#AREA_REQ_COST_AMOUNT").val()/$("#TOTAL_AREA_SEND_AMOUNT").val()).toFixed(4));
 	}
 }
 
 function jsTwo(){
 	//所属战区本季度累计已发货费用
 	$("#TOTAL_AREA_SEND_COST_AMOUNT").val(($("#TOTAL_AREA_SEND_AMOUNT").val()*0.03).toFixed(2));
 	//所属战区本季度发货剩余费用
 	if($("#TOTAL_AREA_SEND_COST_AMOUNT").val()!="" && $("#AREA_REQ_COST_AMOUNT").val()!=""){
 	   $("#TOTAL_AREA_SEND_LEFT_AMOUNT").val(($("#TOTAL_AREA_SEND_COST_AMOUNT").val()-$("#AREA_REQ_COST_AMOUNT").val()).toFixed(2));
 	}
 	//所属战区本季度发货费效比
 	if($("#AREA_REQ_COST_AMOUNT").val()!="" && $("#TOTAL_AREA_SEND_AMOUNT").val()!=""){
 	   $("#AREA_SEND_COST_RATE").val(($("#AREA_REQ_COST_AMOUNT").val()/$("#TOTAL_AREA_SEND_AMOUNT").val()).toFixed(4));
 	}
 	//所属战区本季度累计发货预算费用
 	$("#TOTAL_AREA_SEND_BUDGET_AMOUNT").val(($("#TOTAL_AREA_BUDGET_AMOUNT").val()*0.03).toFixed(2));
 	//所属战区本季度预算剩余费用
 	if($("#TOTAL_AREA_SEND_BUDGET_AMOUNT").val()!="" && $("#AREA_REQ_COST_AMOUNT").val()!=""){
 	   $("#TOTAL_AREA_BUDGET_LEFT_AMOUNT").val(($("#TOTAL_AREA_SEND_BUDGET_AMOUNT").val()-$("#AREA_REQ_COST_AMOUNT").val()).toFixed(2));
 	}
 	//所属战区本季度预算费效比
 	if($("#AREA_REQ_COST_AMOUNT").val()!="" && $("#TOTAL_AREA_BUDGET_AMOUNT").val()!="") {
 	   $("#AREA_SEND_BUDGET_RATE").val(($("#AREA_REQ_COST_AMOUNT").val()/$("#TOTAL_AREA_BUDGET_AMOUNT").val()).toFixed(4));
 	}
 }
 
 function   getChann(){
    var DRP_LEDGER = $("#DRP_LEDGER").val();
    var LEDGER_ID  = $("#LEDGER_ID").val();
    var CHANN_ID   = $("#CHANN_ID_T").val();
    if(DRP_LEDGER=="1"){
       $("#selectParamsTt").val(" CHANN_ID = '"+LEDGER_ID+"'");
    }else{
       $("#selectParamsTt").val(" CHANN_ID in "+CHANN_ID+" ");
    }
    selCommon('BS_159', false, 'CHANN_IDT', 'CHANN_ID', 'forms[0]','CHANN_IDT,CHANN_NOT,CHANN_NAMET,CHANN_PERSON_CON,CHANN_TEL,AREA_MANAGE_IDT,AREA_MANAGE_NAMET,AREA_NAME,CITY_NAME,CITY_LVL,AREA_IDT,AREA_NOT,AREA_NAME_SUB','CHANN_ID,CHANN_NO,CHANN_NAME,PERSON_CON,TEL,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_NAME_P,CITY,CITY_TYPE,AREA_ID_P,AREA_NO_P,AREA_NAME', 'selectParamsTt')
    var AREA_NAME = $("#AREA_NAME").val();
    if(AREA_NAME==""){
       $("#AREA_NAME").val($("#AREA_NAME_SUB").val());
    }
 }
 
 function chkProScreen(){
  var PRO_SCREEN = document.getElementsByName("PRO_SCREENs");
  for(var i=0;i<PRO_SCREEN.length;i++){
     if(PRO_SCREEN[i].checked){
        $("#PRO_SCREEN").val(PRO_SCREEN[i].value);
     }
  }
 }
 
 
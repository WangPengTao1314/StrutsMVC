/**
 * @module 营销管理-销售指标管理
 * @func   区域销售指标设定维护
 * @version 1.1
 * @author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//var selId = parent.document.getElementById("selRowId").value;
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("areasaleplan.shtml?action=edit","AREA_SALE_PLAN_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
});

//终端信息
function selAdvcorder(){
	var BUSS_AREA = $("#BUSS_AREA").val();
	if(BUSS_AREA == 0){
		$("#BUSS_AREA").val("");
	}
	selCommon('BS_82', false, 'TERM_ID', 'TERM_ID', 'forms[0]',
		'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,ADDRESS,ZONE_ID,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,ZONE_ADDR,BUSS_AREA,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,BEG_SBUSS_DATE', 
		'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,CHANN_ID,CHANN_NO,CHANN_NAME,ADDRESS,ZONE_ID,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,ZONE_ADDR,BUSS_AREA,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,BEG_SBUSS_DATE', 
		'selectParams');	
}

//渠道信息
function selChann(){
	selCommon('BS_19', false, 'NEW_CHANN_ID', 'CHANN_ID', 'forms[0]',
		'NEW_CHANN_ID,CHANN_NO2,CHANN_NAME2', 
		'CHANN_ID,CHANN_NO,CHANN_NAME', 
		'selectParamsChann');
}

//装修报销单
function selBuild(){
	selCommon('BS_85', false, 'RNVTN_REIT_REQ_ID', 'CHANN_RNVTN_ID', 'forms[0]',
		'RNVTN_REIT_REQ_ID,RNVTN_REIT_REQ_NO,TOTAL_REIT_AMOUNT', 
		'CHANN_RNVTN_ID,CHANN_RNVTN_NO,REIT_AMOUNT', 
		'selectParamsBuild');
}

function upFlag(){
	var flag = document.getElementById("flag").checked;
	if(flag){
		$("#TERM_DECT_CTR_FLAG").val("1");
	}else{
		$("#TERM_DECT_CTR_FLAG").val("0");
	}
}
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){	
	//验证图片文件名长度
		if($("#PIC_PATH").val()!=null && $("#PIC_PATH").val() != ""){
	      	   if($("#PIC_PATH").val().length>260){
	      		    parent.showErrorMsg(utf8Decode("上传图片文件名过长！"));
	      		    return false;
	      	   }
		   }
	    return true;
	}
	
function getFirtstAmount(){
   var JAN_AMOUNT = $("#JAN_AMOUNT").val();
   var FEB_AMOUNT = $("#FEB_AMOUNT").val();
   var MAR_AMOUNT = $("#MAR_AMOUNT").val();
   var First = 0;
   if(JAN_AMOUNT == ""){
       JAN_AMOUNT = 0;
    } else {
       JAN_AMOUNT = parseFloat(JAN_AMOUNT);
    }
   if(FEB_AMOUNT == ""){
       FEB_AMOUNT = 0;
   }  else {
       FEB_AMOUNT = parseFloat(FEB_AMOUNT);
   }
   if(MAR_AMOUNT == ""){
       MAR_AMOUNT = 0;
   } else {
       MAR_AMOUNT = parseFloat(MAR_AMOUNT);
   }
   First  = parseFloat(JAN_AMOUNT)+parseFloat(FEB_AMOUNT)+parseFloat(MAR_AMOUNT);
   $("#FIRST_QUARTER_AMOUNT").val(First);
   
   if($("#FIRST_QUARTER_AMOUNT").val()==""){
      $("#FIRST_QUARTER_AMOUNT").val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT").val()==""){
      $("#SECOND_QUARTER_AMOUNT").val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT").val()==""){
      $("#THIRD_QUARTER_AMOUNT").val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT").val()==""){
      $("#FOURTH_QUARTER_AMOUNT").val(0);
   }
   document.getElementById("YEAR_PLAN_AMOUNTL").value = parseInt(parseFloat($("#FIRST_QUARTER_AMOUNT").val()) + parseFloat($("#SECOND_QUARTER_AMOUNT").val()) + parseFloat($("#THIRD_QUARTER_AMOUNT").val())+ parseFloat($("#FOURTH_QUARTER_AMOUNT").val()));
}

function getSecondAmount(){
   
   var APR_AMOUNT = $("#APR_AMOUNT").val();
   var MAY_AMOUNT = $("#MAY_AMOUNT").val();
   var JUN_AMOUNT = $("#JUN_AMOUNT").val();
   var Second = 0;
   if(APR_AMOUNT == ""){
       APR_AMOUNT = 0;
    } else {
       APR_AMOUNT = parseFloat(APR_AMOUNT);
    }
   if(MAY_AMOUNT == ""){
       MAY_AMOUNT = 0;
   }  else {
       MAY_AMOUNT = parseFloat(MAY_AMOUNT);
   }
   if(JUN_AMOUNT == ""){
       JUN_AMOUNT = 0;
   } else {
       JUN_AMOUNT = parseFloat(JUN_AMOUNT);
   }
   Second  = parseFloat(APR_AMOUNT)+parseFloat(MAY_AMOUNT)+parseFloat(JUN_AMOUNT);
   $("#SECOND_QUARTER_AMOUNT").val(Second);
   
   if($("#FIRST_QUARTER_AMOUNT").val()==""){
      $("#FIRST_QUARTER_AMOUNT").val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT").val()==""){
      $("#SECOND_QUARTER_AMOUNT").val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT").val()==""){
      $("#THIRD_QUARTER_AMOUNT").val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT").val()==""){
      $("#FOURTH_QUARTER_AMOUNT").val(0);
   }
   document.getElementById("YEAR_PLAN_AMOUNTL").value = (parseFloat($("#FIRST_QUARTER_AMOUNT").val()) + parseFloat($("#SECOND_QUARTER_AMOUNT").val()) + parseFloat($("#THIRD_QUARTER_AMOUNT").val())+ parseFloat($("#FOURTH_QUARTER_AMOUNT").val())).toFixed(2);
}

function getThirdAmount(){
   var JUL_AMOUNT = $("#JUL_AMOUNT").val();
   var AUG_AMOUNT = $("#AUG_AMOUNT").val();
   var SEP_AMOUNT = $("#SEP_AMOUNT").val();
   var Third = 0;
   if(JUL_AMOUNT == ""){
       JUL_AMOUNT = 0;
    } else {
       JUL_AMOUNT = parseFloat(JUL_AMOUNT);
    }
   if(AUG_AMOUNT == ""){
       AUG_AMOUNT = 0;
   }  else {
       AUG_AMOUNT = parseFloat(AUG_AMOUNT);
   }
   if(SEP_AMOUNT == ""){
       SEP_AMOUNT = 0;
   } else {
       SEP_AMOUNT = parseFloat(SEP_AMOUNT);
   }
   Third  = parseFloat(JUL_AMOUNT)+parseFloat(AUG_AMOUNT)+parseFloat(SEP_AMOUNT);
   $("#THIRD_QUARTER_AMOUNT").val(Third);
   
   if($("#FIRST_QUARTER_AMOUNT").val()==""){
      $("#FIRST_QUARTER_AMOUNT").val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT").val()==""){
      $("#SECOND_QUARTER_AMOUNT").val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT").val()==""){
      $("#THIRD_QUARTER_AMOUNT").val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT").val()==""){
      $("#FOURTH_QUARTER_AMOUNT").val(0);
   }
   document.getElementById("YEAR_PLAN_AMOUNTL").value = (parseFloat($("#FIRST_QUARTER_AMOUNT").val()) + parseFloat($("#SECOND_QUARTER_AMOUNT").val()) + parseFloat($("#THIRD_QUARTER_AMOUNT").val())+ parseFloat($("#FOURTH_QUARTER_AMOUNT").val())).toFixed(2);
}

function  getFourthAmount(){

   var OCT_AMOUNT = $("#OCT_AMOUNT").val();
   var NOV_AMOUNT = $("#NOV_AMOUNT").val();
   var DEC_AMOUNT = $("#DEC_AMOUNT").val();
   var Fourth = 0;
   if(OCT_AMOUNT == ""){
       OCT_AMOUNT = 0;
    } else {
       OCT_AMOUNT = parseFloat(OCT_AMOUNT);
    }
   if(NOV_AMOUNT == ""){
       NOV_AMOUNT = 0;
   }  else {
       NOV_AMOUNT = parseFloat(NOV_AMOUNT);
   }
   if(DEC_AMOUNT == ""){
       DEC_AMOUNT = 0;
   } else {
       DEC_AMOUNT = parseFloat(DEC_AMOUNT);
   }
   Fourth  = parseFloat(OCT_AMOUNT)+parseFloat(NOV_AMOUNT)+parseFloat(DEC_AMOUNT);
   $("#FOURTH_QUARTER_AMOUNT").val(Fourth);
   
   if($("#FIRST_QUARTER_AMOUNT").val()==""){
      $("#FIRST_QUARTER_AMOUNT").val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT").val()==""){
      $("#SECOND_QUARTER_AMOUNT").val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT").val()==""){
      $("#THIRD_QUARTER_AMOUNT").val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT").val()==""){
      $("#FOURTH_QUARTER_AMOUNT").val(0);
   }
   document.getElementById("YEAR_PLAN_AMOUNTL").value = (parseFloat($("#FIRST_QUARTER_AMOUNT").val()) + parseFloat($("#SECOND_QUARTER_AMOUNT").val()) + parseFloat($("#THIRD_QUARTER_AMOUNT").val())+ parseFloat($("#FOURTH_QUARTER_AMOUNT").val())).toFixed(2);
}

function  getRate(){
    var PLAN_YEAR = $("#PLAN_YEAR").val();
    $.ajax({
 	url: "areasaleplan.shtml?action=loadModel&PLAN_YEAR="+PLAN_YEAR,
	type:"POST",
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			 var result = jsonResult.data;
			 if(typeof(result) != "undefined") {
		        $("#JAN_AMOUNT").val(parseFloat(result[0].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#FEB_AMOUNT").val(parseFloat(result[1].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#MAR_AMOUNT").val(parseFloat(result[2].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
			    $("#FIRST_QUARTER_AMOUNT").val((parseFloat($("#JAN_AMOUNT").val())+parseFloat($("#FEB_AMOUNT").val())+parseFloat($("#MAR_AMOUNT").val())).toFixed(2));
		        $("#APR_AMOUNT").val(parseFloat(result[3].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#MAY_AMOUNT").val(parseFloat(result[4].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#JUN_AMOUNT").val(parseFloat(result[5].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#SECOND_QUARTER_AMOUNT").val((parseFloat($("#APR_AMOUNT").val())+parseFloat($("#MAY_AMOUNT").val())+parseFloat($("#JUN_AMOUNT").val())).toFixed(2));
		        $("#JUL_AMOUNT").val(parseFloat(result[6].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#AUG_AMOUNT").val(parseFloat(result[7].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#SEP_AMOUNT").val(parseFloat(result[8].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#THIRD_QUARTER_AMOUNT").val((parseFloat($("#JUL_AMOUNT").val())+parseFloat($("#AUG_AMOUNT").val())+parseFloat($("#SEP_AMOUNT").val())).toFixed(2));
		        $("#OCT_AMOUNT").val(parseFloat(result[9].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#NOV_AMOUNT").val(parseFloat(result[10].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#DEC_AMOUNT").val(parseFloat(result[11].SJGL*$("#YEAR_PLAN_AMOUNTL").val()).toFixed(2));
		        $("#FOURTH_QUARTER_AMOUNT").val((parseFloat($("#OCT_AMOUNT").val())+parseFloat($("#NOV_AMOUNT").val())+parseFloat($("#DEC_AMOUNT").val())).toFixed(2));
		       // $("#YEAR_PLAN_AMOUNTL").val((parseFloat($("#FIRST_QUARTER_AMOUNT").val())+parseFloat($("#SECOND_QUARTER_AMOUNT").val())+parseFloat($("#THIRD_QUARTER_AMOUNT").val())+parseFloat($("#FOURTH_QUARTER_AMOUNT").val())).toFixed(2));
		      } else {
		        $("#JAN_AMOUNT").val("");
		        $("#FEB_AMOUNT").val("");
		        $("#MAR_AMOUNT").val("");
			    $("#FIRST_QUARTER_AMOUNT").val("");
		        $("#APR_AMOUNT").val("");
		        $("#MAY_AMOUNT").val("");
		        $("#JUN_AMOUNT").val("");
		        $("#SECOND_QUARTER_AMOUNT").val("");
		        $("#JUL_AMOUNT").val("");
		        $("#AUG_AMOUNT").val("");
		        $("#SEP_AMOUNT").val("");
		        $("#THIRD_QUARTER_AMOUNT").val("");
		        $("#OCT_AMOUNT").val("");
		        $("#NOV_AMOUNT").val("");
		        $("#DEC_AMOUNT").val("");
		        $("#FOURTH_QUARTER_AMOUNT").val("");
		        //$("#YEAR_PLAN_AMOUNTL").val("");
		      }
		}else{
			showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
  }


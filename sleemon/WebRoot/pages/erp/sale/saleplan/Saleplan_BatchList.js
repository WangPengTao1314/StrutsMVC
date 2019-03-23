 /**
 * @prjName:喜临门营销平台
 * @fileName:Saleplan_BatchList
 * @author zcx
 * @time   2014-12-5 
 * @version 1.1
 */
 
function getFirtstAmount(num){
   var JAN_AMOUNT = $("#JAN_AMOUNT"+num).val();
   var FEB_AMOUNT = $("#FEB_AMOUNT"+num).val();
   var MAR_AMOUNT = $("#MAR_AMOUNT"+num).val();
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
   First = parseFloat(JAN_AMOUNT)+parseFloat(FEB_AMOUNT)+parseFloat(MAR_AMOUNT);
   if(First!=0){
   	First  = parseFloat(First).toFixed(2);
   }
   //First  = parseFloat(parseFloat(JAN_AMOUNT)+parseFloat(FEB_AMOUNT)+parseFloat(MAR_AMOUNT)).toFixed(2);
   $("#FIRST_QUARTER_AMOUNT"+num).val(First);
   
   if($("#FIRST_QUARTER_AMOUNT"+num).val()==""){
      $("#FIRST_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT"+num).val()==""){
      $("#SECOND_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT"+num).val()==""){
      $("#THIRD_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT"+num).val()==""){
      $("#FOURTH_QUARTER_AMOUNT"+num).val(0);
   }
   var total = parseFloat($("#FIRST_QUARTER_AMOUNT"+num).val())+parseFloat($("#SECOND_QUARTER_AMOUNT"+num).val())+parseFloat($("#THIRD_QUARTER_AMOUNT"+num).val())+parseFloat($("#FOURTH_QUARTER_AMOUNT"+num).val());
   $("#YEAR_PLAN_AMOUNT"+num).val(total);
}

function getSecondAmount(num){
   
   var APR_AMOUNT = $("#APR_AMOUNT"+num).val();
   var MAY_AMOUNT = $("#MAY_AMOUNT"+num).val();
   var JUN_AMOUNT = $("#JUN_AMOUNT"+num).val();
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
   Second = parseFloat(APR_AMOUNT)+parseFloat(MAY_AMOUNT)+parseFloat(JUN_AMOUNT);
   if(Second !=0){
      Second = parseFloat(Second).toFixed(2);
   }
   //Second  = parseFloat(parseFloat(APR_AMOUNT)+parseFloat(MAY_AMOUNT)+parseFloat(JUN_AMOUNT)).toFixed(2);
   $("#SECOND_QUARTER_AMOUNT"+num).val(Second);
   
   if($("#FIRST_QUARTER_AMOUNT"+num).val()==""){
      $("#FIRST_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT"+num).val()==""){
      $("#SECOND_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT"+num).val()==""){
      $("#THIRD_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT"+num).val()==""){
      $("#FOURTH_QUARTER_AMOUNT"+num).val(0);
   }
   var total = parseFloat($("#FIRST_QUARTER_AMOUNT"+num).val())+parseFloat($("#SECOND_QUARTER_AMOUNT"+num).val())+parseFloat($("#THIRD_QUARTER_AMOUNT"+num).val())+parseFloat($("#FOURTH_QUARTER_AMOUNT"+num).val());
   $("#YEAR_PLAN_AMOUNT"+num).val(total);
}

function getThirdAmount(num){
   var JUL_AMOUNT = $("#JUL_AMOUNT"+num).val();
   var AUG_AMOUNT = $("#AUG_AMOUNT"+num).val();
   var SEP_AMOUNT = $("#SEP_AMOUNT"+num).val();
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
   Third = parseFloat(JUL_AMOUNT)+parseFloat(AUG_AMOUNT)+parseFloat(SEP_AMOUNT);
   if(Third !=0){
      Third = parseFloat(Third).toFixed(2);
   }
   //Third  = parseFloat(parseFloat(JUL_AMOUNT)+parseFloat(AUG_AMOUNT)+parseFloat(SEP_AMOUNT)).toFixed(2);
   $("#THIRD_QUARTER_AMOUNT"+num).val(Third);
   
   if($("#FIRST_QUARTER_AMOUNT"+num).val()==""){
      $("#FIRST_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT"+num).val()==""){
      $("#SECOND_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT"+num).val()==""){
      $("#THIRD_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT"+num).val()==""){
      $("#FOURTH_QUARTER_AMOUNT"+num).val(0);
   }
   var total = parseFloat($("#FIRST_QUARTER_AMOUNT"+num).val())+parseFloat($("#SECOND_QUARTER_AMOUNT"+num).val())+parseFloat($("#THIRD_QUARTER_AMOUNT"+num).val())+parseFloat($("#FOURTH_QUARTER_AMOUNT"+num).val());
   $("#YEAR_PLAN_AMOUNT"+num).val(total);
}

function  getFourthAmount(num){

   var OCT_AMOUNT = $("#OCT_AMOUNT"+num).val();
   var NOV_AMOUNT = $("#NOV_AMOUNT"+num).val();
   var DEC_AMOUNT = $("#DEC_AMOUNT"+num).val();
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
   Fourth = parseFloat(OCT_AMOUNT)+parseFloat(NOV_AMOUNT)+parseFloat(DEC_AMOUNT);
   if(Fourth !=0){
      Fourth = parseFloat(Fourth).toFixed(2);
   }
   //Fourth  = parseFloat(parseFloat(OCT_AMOUNT)+parseFloat(NOV_AMOUNT)+parseFloat(DEC_AMOUNT)).toFixed(2);
   $("#FOURTH_QUARTER_AMOUNT"+num).val(Fourth);
   
   if($("#FIRST_QUARTER_AMOUNT"+num).val()==""){
      $("#FIRST_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#SECOND_QUARTER_AMOUNT"+num).val()==""){
      $("#SECOND_QUARTER_AMOUNT"+num).val(0);
   }
   if($("#THIRD_QUARTER_AMOUNT").val()==""){
      $("#THIRD_QUARTER_AMOUNT").val(0);
   }
   if($("#FOURTH_QUARTER_AMOUNT"+num).val()==""){
      $("#FOURTH_QUARTER_AMOUNT"+num).val(0);
   }
   var total = parseFloat($("#FIRST_QUARTER_AMOUNT"+num).val())+parseFloat($("#SECOND_QUARTER_AMOUNT"+num).val())+parseFloat($("#THIRD_QUARTER_AMOUNT"+num).val())+parseFloat($("#FOURTH_QUARTER_AMOUNT"+num).val());
   $("#YEAR_PLAN_AMOUNT"+num).val(total);
}

function checkYearAmount(param){
   var reg = new RegExp("^[0-9]*$");
   if(!reg.test(param.value)){
    parent.showErrorMsg("年度指标请输入有效数值");
    param.value="";
    return;
   }  
}
 
function validate(param){
  if(!(/^[0-9]+(.[0-9]{1,2})?$/.test(param.value))){parent.showErrorMsg('指标保留2位有效数字');
  param.value=""};
}

function  getRate(num){
    var PLAN_YEAR = $("#PLAN_YEAR").val();
    $.ajax({
 	url: "saleplan.shtml?action=loadModel&PLAN_YEAR="+PLAN_YEAR,
	type:"POST",
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			 var result = jsonResult.data;
			 if(typeof(result) != "undefined") {
			    if(document.getElementById('YEAR_PLAN_AMOUNT'+num).value !=""){
			        document.getElementById('eid'+num).checked=true;
			        document.getElementById('JAN_AMOUNT'+num).value = parseFloat(result[0].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
			        document.getElementById('FEB_AMOUNT'+num).value = parseFloat(result[1].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('MAR_AMOUNT'+num).value = parseFloat(result[2].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('APR_AMOUNT'+num).value = parseFloat(result[3].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('MAY_AMOUNT'+num).value = parseFloat(result[4].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('JUN_AMOUNT'+num).value = parseFloat(result[5].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('JUL_AMOUNT'+num).value = parseFloat(result[6].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('AUG_AMOUNT'+num).value = parseFloat(result[7].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('SEP_AMOUNT'+num).value = parseFloat(result[8].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('OCT_AMOUNT'+num).value = parseFloat(result[9].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('NOV_AMOUNT'+num).value = parseFloat(result[10].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
		            document.getElementById('DEC_AMOUNT'+num).value = parseFloat(result[11].SJGL*(document.getElementById('YEAR_PLAN_AMOUNT'+num).value)).toFixed(2);
			        
			        document.getElementById('FIRST_QUARTER_AMOUNT'+num).value = parseFloat(parseFloat(document.getElementById('JAN_AMOUNT'+num).value)+parseFloat(document.getElementById('FEB_AMOUNT'+num).value)+parseFloat(document.getElementById('MAR_AMOUNT'+num).value)).toFixed(2);           
				    document.getElementById('SECOND_QUARTER_AMOUNT'+num).value= parseFloat(parseFloat(document.getElementById('APR_AMOUNT'+num).value)+parseFloat(document.getElementById('MAY_AMOUNT'+num).value)+parseFloat(document.getElementById('JUN_AMOUNT'+num).value)).toFixed(2);   
				    document.getElementById('THIRD_QUARTER_AMOUNT'+num).value = parseFloat(parseFloat(document.getElementById('JUL_AMOUNT'+num).value)+parseFloat(document.getElementById('AUG_AMOUNT'+num).value)+parseFloat(document.getElementById('SEP_AMOUNT'+num).value)).toFixed(2);
				    document.getElementById('FOURTH_QUARTER_AMOUNT'+num).value= parseFloat(parseFloat(document.getElementById('OCT_AMOUNT'+num).value)+parseFloat(document.getElementById('NOV_AMOUNT'+num).value)+parseFloat(document.getElementById('DEC_AMOUNT'+num).value)).toFixed(2);
		       } else {
		         parent.showErrorMsg("请输入有效的年度指标数值");
		         $("#JAN_AMOUNT"+num).val("");
		         $("#FEB_AMOUNT"+num).val("");
		         $("#MAR_AMOUNT"+num).val("");
			     $("#FIRST_QUARTER_AMOUNT"+num).val("");
			     $("#APR_AMOUNT"+num).val("");
			     $("#MAY_AMOUNT"+num).val("");
			     $("#JUN_AMOUNT"+num).val("");
			     $("#SECOND_QUARTER_AMOUNT"+num).val("");
			     $("#JUL_AMOUNT"+num).val("");
			     $("#AUG_AMOUNT"+num).val("");
			     $("#SEP_AMOUNT"+num).val("");
			     $("#THIRD_QUARTER_AMOUNT"+num).val("");
			     $("#OCT_AMOUNT"+num).val("");
			     $("#NOV_AMOUNT"+num).val("");
			     $("#DEC_AMOUNT"+num).val("");
			     $("#FOURTH_QUARTER_AMOUNT"+num).val("");
		       }
		      } else {
		        $("#JAN_AMOUNT"+num).val("");
		        $("#FEB_AMOUNT"+num).val("");
		        $("#MAR_AMOUNT"+num).val("");
			    $("#FIRST_QUARTER_AMOUNT"+num).val("");
		        $("#APR_AMOUNT"+num).val("");
		        $("#MAY_AMOUNT"+num).val("");
		        $("#JUN_AMOUNT"+num).val("");
		        $("#SECOND_QUARTER_AMOUNT"+num).val("");
		        $("#JUL_AMOUNT"+num).val("");
		        $("#AUG_AMOUNT"+num).val("");
		        $("#SEP_AMOUNT"+num).val("");
		        $("#THIRD_QUARTER_AMOUNT"+num).val("");
		        $("#OCT_AMOUNT"+num).val("");
		        $("#NOV_AMOUNT"+num).val("");
		        $("#DEC_AMOUNT"+num).val("");
		        $("#FOURTH_QUARTER_AMOUNT"+num).val("");
		      }
		}else{
			showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
  }
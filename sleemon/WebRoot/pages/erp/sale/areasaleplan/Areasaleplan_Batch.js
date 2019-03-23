$(function(){
	url();
})

function url(){
	var url="areasaleplan.shtml?action=toBatchList";
	$("#topcontent").attr("src",url);
}

function saveBatchT(){
	 var eid = topcontent.document.getElementsByName("eid");
	 var YEAR_PLAN_AMOUNTs = "";
     var JAN_AMOUNTs = "";
	 var FEB_AMOUNTs = "";
	 var MAR_AMOUNTs = "";
	 var FIRST_QUARTER_AMOUNTs = "";
	 var APR_AMOUNTs = "";
	 var MAY_AMOUNTs = "";
	 var JUN_AMOUNTs = "";
	 var SECOND_QUARTER_AMOUNTs = "";
	 var JUL_AMOUNTs = "";
	 var AUG_AMOUNTs = "";
	 var SEP_AMOUNTs = "";
	 var THIRD_QUARTER_AMOUNTs = "";
	 var OCT_AMOUNTs = "";
	 var NOV_AMOUNTs = "";
	 var DEC_AMOUNTs = "";
	 var FOURTH_QUARTER_AMOUNTs = "";
	 var AREA_SALE_PLAN_IDs = "";
	 var AREA_MANAGE_NAMEs = "";
	 var AREA_MANAGE_IDs = "";
	 var t=0;
	 for(var i=0;i<eid.length;i++){
	    if(eid[i].checked==true){
	       t++;
	       var AREA_SALE_PLAN_ID = topcontent.document.getElementById("AREA_SALE_PLAN_ID"+(i+1)).value;
	       AREA_SALE_PLAN_IDs += AREA_SALE_PLAN_ID+",";
	       var PLAN_YEAR = topcontent.document.getElementById("PLAN_YEAR"+(i+1)).value;
	       var YEAR_PLAN_AMOUNT = topcontent.document.getElementById("YEAR_PLAN_AMOUNT"+(i+1)).value;
	       YEAR_PLAN_AMOUNTs += YEAR_PLAN_AMOUNT+",";
	       
	       var JAN_AMOUNT= topcontent.document.getElementById("JAN_AMOUNT"+(i+1)).value;
	       JAN_AMOUNTs += JAN_AMOUNT+",";
	       var FEB_AMOUNT= topcontent.document.getElementById("FEB_AMOUNT"+(i+1)).value;
	       FEB_AMOUNTs += FEB_AMOUNT+",";
	        var MAR_AMOUNT= topcontent.document.getElementById("MAR_AMOUNT"+(i+1)).value;
	       MAR_AMOUNTs += MAR_AMOUNT+",";
	        var APR_AMOUNT= topcontent.document.getElementById("APR_AMOUNT"+(i+1)).value;
	       APR_AMOUNTs += APR_AMOUNT+",";
	        var MAY_AMOUNT= topcontent.document.getElementById("MAY_AMOUNT"+(i+1)).value;
	       MAY_AMOUNTs += MAY_AMOUNT+",";
	        var JUN_AMOUNT= topcontent.document.getElementById("JUN_AMOUNT"+(i+1)).value;
	       JUN_AMOUNTs += JUN_AMOUNT+",";
	        var JUL_AMOUNT= topcontent.document.getElementById("JUL_AMOUNT"+(i+1)).value;
	       JUL_AMOUNTs += JUL_AMOUNT+",";
	        var AUG_AMOUNT= topcontent.document.getElementById("AUG_AMOUNT"+(i+1)).value;
	       AUG_AMOUNTs += AUG_AMOUNT+",";
	        var SEP_AMOUNT= topcontent.document.getElementById("SEP_AMOUNT"+(i+1)).value;
	       SEP_AMOUNTs += SEP_AMOUNT+",";
	        var OCT_AMOUNT= topcontent.document.getElementById("OCT_AMOUNT"+(i+1)).value;
	       OCT_AMOUNTs += OCT_AMOUNT+",";
	        var NOV_AMOUNT= topcontent.document.getElementById("NOV_AMOUNT"+(i+1)).value;
	       NOV_AMOUNTs += NOV_AMOUNT+",";
	        var DEC_AMOUNT= topcontent.document.getElementById("DEC_AMOUNT"+(i+1)).value;
	       DEC_AMOUNTs += DEC_AMOUNT+",";
	        var FIRST_QUARTER_AMOUNT= topcontent.document.getElementById("FIRST_QUARTER_AMOUNT"+(i+1)).value;
	       FIRST_QUARTER_AMOUNTs += FIRST_QUARTER_AMOUNT+",";
	        var SECOND_QUARTER_AMOUNT= topcontent.document.getElementById("SECOND_QUARTER_AMOUNT"+(i+1)).value;
	       SECOND_QUARTER_AMOUNTs += SECOND_QUARTER_AMOUNT+",";
	        var THIRD_QUARTER_AMOUNT= topcontent.document.getElementById("THIRD_QUARTER_AMOUNT"+(i+1)).value;
	       THIRD_QUARTER_AMOUNTs += THIRD_QUARTER_AMOUNT+",";
	        var FOURTH_QUARTER_AMOUNT= topcontent.document.getElementById("FOURTH_QUARTER_AMOUNT"+(i+1)).value;
	       FOURTH_QUARTER_AMOUNTs += FOURTH_QUARTER_AMOUNT+",";
	        var AREA_MANAGE_NAME = topcontent.document.getElementById("AREA_MANAGE_NAME"+(i+1)).value;
	       AREA_MANAGE_NAMEs += AREA_MANAGE_NAME+",";
	        var AREA_MANAGE_ID   = topcontent.document.getElementById("AREA_MANAGE_ID"+(i+1)).value;
	       AREA_MANAGE_IDs += AREA_MANAGE_ID+",";
	    }
	 } 
	 AREA_SALE_PLAN_IDs     = AREA_SALE_PLAN_IDs.substr(0,AREA_SALE_PLAN_IDs.length-1);
	 YEAR_PLAN_AMOUNTs = YEAR_PLAN_AMOUNTs.substr(0,YEAR_PLAN_AMOUNTs.length-1);
	 JAN_AMOUNTs  = JAN_AMOUNTs.substr(0,JAN_AMOUNTs.length-1);
	 FEB_AMOUNTs  = FEB_AMOUNTs.substr(0,FEB_AMOUNTs.length-1);
	 MAR_AMOUNTs  = MAR_AMOUNTs.substr(0,MAR_AMOUNTs.length-1);
	 FIRST_QUARTER_AMOUNTs  = FIRST_QUARTER_AMOUNTs.substr(0,FIRST_QUARTER_AMOUNTs.length-1);
	 APR_AMOUNTs  = APR_AMOUNTs.substr(0,APR_AMOUNTs.length-1);
	 MAY_AMOUNTs  = MAY_AMOUNTs.substr(0,MAY_AMOUNTs.length-1);
	 JUN_AMOUNTs  = JUN_AMOUNTs.substr(0,JUN_AMOUNTs.length-1);
	 SECOND_QUARTER_AMOUNTs  = SECOND_QUARTER_AMOUNTs.substr(0,SECOND_QUARTER_AMOUNTs.length-1);
	 JUL_AMOUNTs  = JUL_AMOUNTs.substr(0,JUL_AMOUNTs.length-1);
	 AUG_AMOUNTs  = AUG_AMOUNTs.substr(0,AUG_AMOUNTs.length-1);
	 SEP_AMOUNTs  = SEP_AMOUNTs.substr(0,SEP_AMOUNTs.length-1);
	 THIRD_QUARTER_AMOUNTs  = THIRD_QUARTER_AMOUNTs.substr(0,THIRD_QUARTER_AMOUNTs.length-1);
	 OCT_AMOUNTs  = OCT_AMOUNTs.substr(0,OCT_AMOUNTs.length-1);
	 NOV_AMOUNTs  = NOV_AMOUNTs.substr(0,NOV_AMOUNTs.length-1);
 	 DEC_AMOUNTs  = DEC_AMOUNTs.substr(0,DEC_AMOUNTs.length-1);
 	 FOURTH_QUARTER_AMOUNTs  = FOURTH_QUARTER_AMOUNTs.substr(0,FOURTH_QUARTER_AMOUNTs.length-1);
 	 AREA_MANAGE_NAMEs    = AREA_MANAGE_NAMEs.substr(0,AREA_MANAGE_NAMEs.length-1);
 	 AREA_MANAGE_IDs      = AREA_MANAGE_IDs.substr(0,AREA_MANAGE_IDs.length-1);
     if(t!=0) {
	     $.ajax({
				url: "areasaleplan.shtml?action=batchUpdate",
				type:"POST",
				dataType:"text",
				data:{"YEAR_PLAN_AMOUNTs":YEAR_PLAN_AMOUNTs,"JAN_AMOUNTs":JAN_AMOUNTs,"FEB_AMOUNTs":FEB_AMOUNTs,"MAR_AMOUNTs":MAR_AMOUNTs,"FIRST_QUARTER_AMOUNTs":FIRST_QUARTER_AMOUNTs,"APR_AMOUNTs":APR_AMOUNTs,"MAY_AMOUNTs":MAY_AMOUNTs,"JUN_AMOUNTs":JUN_AMOUNTs,"SECOND_QUARTER_AMOUNTs":SECOND_QUARTER_AMOUNTs,"JUL_AMOUNTs":JUL_AMOUNTs,"AUG_AMOUNTs":AUG_AMOUNTs,"SEP_AMOUNTs":SEP_AMOUNTs,"THIRD_QUARTER_AMOUNTs":THIRD_QUARTER_AMOUNTs,"OCT_AMOUNTs":OCT_AMOUNTs,"NOV_AMOUNTs":NOV_AMOUNTs,"DEC_AMOUNTs":DEC_AMOUNTs,"FOURTH_QUARTER_AMOUNTs":FOURTH_QUARTER_AMOUNTs,"AREA_SALE_PLAN_IDs":AREA_SALE_PLAN_IDs,"AREA_MANAGE_NAMEs":AREA_MANAGE_NAMEs,"AREA_MANAGE_IDs":AREA_MANAGE_IDs},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						parent.showMsgPanel("保存成功");
						//url();
						//var urlT="saleplan.shtml?action=toFrame";
		                //parent.parent.document.getElementById("topcontent").attr("src",urlT);
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});
	   } else {
	       parent.showErrorMsg("请选择区域销售指标信息");
	 	   return;
	   } 
}

function multiPackJsonDataT(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		jsonData = jsonData+"{";
		var isFirst = true;
		var inputs = $(this).find(":input");
		inputs.each(function(){
			if(null != $(this).attr("json")){
				var key;
				var value;
				var type = $(this).attr("type");
				if(!isFirst && "checkbox" == type){
					key = $(this).attr("json");
					if($(this).attr("checked")){
						value= 1;
					}else{
						value= 0;
					}
				}else if("radio" == type){
					if($(this).attr("checked")){
						key = $(this).attr("json");
						value= $(this).attr("value");
					}
				}else{
					key = $(this).attr("json");
					value = $(this).attr("value");
					
					isFirst = false;
				}
				var inputtype = $(this).attr("inputtype");
				jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
			}
		});
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}
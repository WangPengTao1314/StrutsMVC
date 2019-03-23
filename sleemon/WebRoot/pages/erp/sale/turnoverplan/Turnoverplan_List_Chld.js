﻿﻿/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 制定交付计划
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	$("#selAddr").hide();
	InitFormValidator("selForm");
	InitFormValidator("ordertbForm");
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		
		countVolum();
		
		countNumPriceVolulmn();
	});
	setSelOperateEx();
	
	//整车发运
	$("#despatch_all").click(function(){
		var checked = $(this).attr("checked");
		if(checked){
			$("#topTab td[name='f_dis']").removeAttr("disabled");
			$("#topTab img").removeAttr("disabled");
			$("#topDate").removeAttr("disabled");
			$("#SEND_DATE_INT").removeAttr("disabled");
			$("#qxBtnTb span[name='consign']").attr("disabled","true");
			$("#SEND_DATE_INP").val("");
			$("#SEND_DATE_INP").attr("disabled","true");
			$("#checkFlag").css("color","red");
			 
		}
	});
  
	document.getElementById("despatch_all").click();
	
	//托运
	$("#despatch_consign").click(function(){
		var checked = $(this).attr("checked");
		if(checked){
			$("#topTab td[name='f_dis']").attr("disabled","true");
			$("#topDate").attr("disabled","true");
			$("#topTab img").attr("disabled","true");
			$("#SEND_DATE_INT").val("");
			$("#SEND_DATE_INT").attr("disabled","true");
			$("#qxBtnTb span[name='consign']").removeAttr("disabled");
			$("#SEND_DATE_INP").removeAttr("disabled");
			 $("#checkFlag").css("color","#666666");
		}
	});
	 
	
//	$("#ordertb input[type='checkbox']").click(function(){
//		alert($(this).attr("checked"));
//		countVolum();
//	});
//	
//	$("#ordertb input[type='checkbox']").change(function(){
//		alert($(this).attr("checked"));
//		countVolum();
//	});
	
	$("#create").click(function(){
		create();
	});
	
//	initRecvAddr();
	 
	//设置区域服务中心
	setAreaSerId();
	
	//初始化页面计算所有
	countNumPriceVolulmn(1);
	
});
 

function initRecvAddr(){
	var RECV_ADDR =  parent.topcontent.getRecvAddr();
	$("#RECV_ADDR").val(RECV_ADDR);
}

function create(){
	var SALE_ORDER_IDS =  parent.$("#selRowId").val();
	var parentCheckBoxs = parent.topcontent.$("#ordertb tr:gt(0) input:checked");
	//是否勾选了 区域代发
	var CHANN_TYPE = $("#CHANN_TYPE").attr("checked");
	var leg = parentCheckBoxs.length;
	if(0 == leg){
		parent.showErrorMsg("请选择要发运的订单");
		return;
	}
//	var AREA_SER_NO = "";
//	var AREA_SER_NAME = "";
//	var OLD_AREA_SER_ID = "";
//	var SHIP_POINT_NAME = "";
//	var SHIP_POINT_ID = "";
//	var flag = true;
//	
//	if("checked" == CHANN_TYPE){
//		for(var i=0;i<leg;i++){
//			OLD_AREA_SER_ID = $(parentCheckBoxs[i]).parent().parent().find("input[name='AREA_SER_ID']").val();
//			for(var j=leg-1; j>i; j--) {
//			 var  AREA_SER_ID = $(parentCheckBoxs[j]).parent().parent().find("input[name='AREA_SER_ID']").val();
//			  if(OLD_AREA_SER_ID != AREA_SER_ID){
//				  flag = false;
//				  break;
//			  }
//		    }
//			if(!flag){
//			   break;
//			}
//		}
//	}
// 
//	AREA_SER_NO = $(parentCheckBoxs[0]).parent().parent().find("input[name='AREA_SER_NO']").val();
//	AREA_SER_NAME = $(parentCheckBoxs[0]).parent().parent().find("input[name='AREA_SER_NAME']").val();
//	SHIP_POINT_NAME = $(parentCheckBoxs[0]).parent().parent().find("input[name='SHIP_POINT_NAME']").val();
//	SHIP_POINT_ID = $(parentCheckBoxs[0]).parent().parent().find("input[name='SHIP_POINT_ID']").val();
//	if(!flag){
//		parent.showErrorMsg("订单的区域服务中心不同不能下发运单");
//		return ;
//	}else{
//		$("#AREA_SER_ID").val(OLD_AREA_SER_ID);
//		$("#AREA_SER_NO").val(AREA_SER_NO);
//		$("#AREA_SER_NAME").val(AREA_SER_NAME);
//		$("#SHIP_POINT_ID").val(SHIP_POINT_ID);
//		$("#SHIP_POINT_NAME").val(SHIP_POINT_NAME);
//	}
	
	//设置区域服务中心
	setAreaSerId();
	var REMARK = parent.topcontent.$("#REMARK").val();
	REMARK = $.trim(REMARK);
	$("#REMARK").val(REMARK);
	var DELIVER_TYPE = $("#qxBtnTb input[type='radio']:checked").val();
	$("#DELIVER_TYPE").val(DELIVER_TYPE);
	var ADVC_SEND_DATE = "";
	if("整车发运" == DELIVER_TYPE){
		var TRUCK_TYPE = $("#TRUCK_TYPE").val();
		if(null == TRUCK_TYPE || "" == TRUCK_TYPE){
			parent.showErrorMsg("请选择'装载车型'");
			$("#TRUCK_TYPE").focus();
			return;
		}
		
		ADVC_SEND_DATE = $("#SEND_DATE_INT").val();
		if(null == ADVC_SEND_DATE || "" == ADVC_SEND_DATE){
			parent.showErrorMsg("请输入'预计发货日期'");
			$("#SEND_DATE_INT").focus();
			return;
		} 
		
	}else{
		ADVC_SEND_DATE = $("#SEND_DATE_INP").val();
		if(null == ADVC_SEND_DATE || "" == ADVC_SEND_DATE){
			parent.showErrorMsg("请输入'预计发货日期'");
			$("#SEND_DATE_INP").focus();
			return;
		} 
	}
	$("#ADVC_SEND_DATE").val(ADVC_SEND_DATE);
	
	var childCheckBoxs = $("#ordertb input[name='mx_checkbox']:checked");
	var l = childCheckBoxs.length;
	if(0 == l){
		parent.showErrorMsg("请至少选择一条明细");
		return;
	}
	//校验 发运数量
	if(!checkADVC_PLAN_NUM()){
		return;
	}
	
	//托运不判断整车
	if("托运" == DELIVER_TYPE){
		createPlan();
		return;
	}
	var TOTAL_VOLUME = $.trim($("#TOTAL_VOLUME").val());
	var MIN_VOLUME = $("#MIN_VOLUME").val();
    var MAX_VOLUME = $("#MAX_VOLUME").val();
    TOTAL_VOLUME = parseFloat(TOTAL_VOLUME);
    MIN_VOLUME = parseFloat(MIN_VOLUME);
    MAX_VOLUME = parseFloat(MAX_VOLUME);
	if(TOTAL_VOLUME>MAX_VOLUME){
		parent.showErrorMsg("大于一车，不能下单");
		return ;
	}
	 
	if(TOTAL_VOLUME < MIN_VOLUME){
		parent.showConfirm("不满整车，您确认发运吗?","bottomcontent.createPlan();","N");
		return ;
	}else{
		createPlan();
	}
 
		
		
}
 

//设置区域服务中心的值
function setAreaSerId(){
	var parentCheckBoxs = parent.topcontent.$("#ordertb tr:gt(0) input:checked");
	var AREA_SER_ID = "";
	var AREA_SER_NO = "";
	var AREA_SER_NAME = "";
	
	var CHANN_TYPE = $(parentCheckBoxs[0]).parent().parent().find("input[name='CHANN_TYPE']").val();
	//如果收货方是区域服务中心，区域服务中心就是当前的收货方
	if("区域服务中心" == CHANN_TYPE){
		 AREA_SER_ID = $(parentCheckBoxs[0]).parent().parent().find("input[name='RECV_CHANN_ID']").val();
		 AREA_SER_NO = $(parentCheckBoxs[0]).parent().parent().find("input[name='RECV_CHANN_NO']").val();
		 AREA_SER_NAME = $(parentCheckBoxs[0]).parent().parent().find("input[name='RECV_CHANN_NAME']").val();
	 
	}else{
		//反之是后台查出来的区域服务中心
	   AREA_SER_ID = $(parentCheckBoxs[0]).parent().parent().find("input[name='AREA_SER_ID']").val();
	   AREA_SER_NO = $(parentCheckBoxs[0]).parent().parent().find("input[name='AREA_SER_NO']").val();
	   AREA_SER_NAME = $(parentCheckBoxs[0]).parent().parent().find("input[name='AREA_SER_NAME']").val();
	}

	var SHIP_POINT_NAME = $(parentCheckBoxs[0]).parent().parent().find("input[name='SHIP_POINT_NAME']").val();
	var SHIP_POINT_ID = $(parentCheckBoxs[0]).parent().parent().find("input[name='SHIP_POINT_ID']").val();
	$("#AREA_SER_ID").val(AREA_SER_ID);
	$("#AREA_SER_NO").val(AREA_SER_NO);
	$("#AREA_SER_NAME").val(AREA_SER_NAME);
	$("#SHIP_POINT_ID").val(SHIP_POINT_ID);
	$("#SHIP_POINT_NAME").val(SHIP_POINT_NAME);
	
	var RECV_CHANN_ID_OLD = "";
	var RECV_CHANN_ID = "";
	var RECV_CHANN_NO = "";
	var RECV_CHANN_NAME = "";
	var RECV_ADDR_NO_SER = "";
	var RECV_ADDR_NO = "";
	var RECV_ADDR = "";
	var RECV_ADDR_SER = "";
	var ORDER_CHANN_ID = "";//有区域服务中心的订货方是区域服务中心
	var ORDER_CHANN_NO = "";
	var ORDER_CHANN_NAME = "";
	parentCheckBoxs.each(function(){
		var parentJsonTb = $(this).parent().parent();
		var RECV_CHANN_ID = parentJsonTb.find("input[name='RECV_CHANN_ID']").val();
		RECV_ADDR_NO = parentJsonTb.find("input[name='RECV_ADDR_NO']").val();//收货地址编号
		RECV_ADDR = parentJsonTb.find("input[name='RECV_ADDR']").val();//收货地址
		var CHANN_TYPE = parentJsonTb.find("input[name='CHANN_TYPE']").val();
		if("区域服务中心" == CHANN_TYPE){
			RECV_ADDR_NO_SER = parentJsonTb.find("input[name='RECV_ADDR_NO']").val();//收货地址编号
			RECV_ADDR_SER = parentJsonTb.find("input[name='RECV_ADDR']").val();//收货地址
		}
		if("" == RECV_CHANN_ID_OLD){
			RECV_CHANN_ID_OLD = RECV_CHANN_ID;
			RECV_CHANN_NO = parentJsonTb.find("input[name='RECV_CHANN_NO']").val();
			RECV_CHANN_NAME = parentJsonTb.find("input[name='RECV_CHANN_NAME']").val();
		}else if(RECV_CHANN_ID_OLD != RECV_CHANN_ID){//如果选择的单据有区域服务中心和其下级加盟 收货方写 区域服务中心
			RECV_CHANN_ID_OLD = AREA_SER_ID;
			RECV_CHANN_NO = AREA_SER_NO;
			RECV_CHANN_NAME = AREA_SER_NAME;
			if(null != RECV_ADDR_NO_SER && "" != RECV_ADDR_NO_SER){
				RECV_ADDR_NO = RECV_ADDR_NO_SER;
				RECV_ADDR = RECV_ADDR_SER;
			}
			
		}
		
	});
	
	 var USER_SELECT_ADDR = $("#USER_SELECT_ADDR").val();
	 if("USER_SELECT_ADDR" == USER_SELECT_ADDR){
		 //点击了区域代发 就使当前自己点击同意选取选择的地址
	 }else{
		 $("#RECV_ADDR_NO").val(RECV_ADDR_NO);
	     $("#RECV_ADDR").val(RECV_ADDR);
	 }
//	alert("RECV_CHANN_ID_OLD: "+RECV_CHANN_ID_OLD+" RECV_CHANN_ID: "+RECV_CHANN_ID+" RECV_CHANN_NO: "+RECV_CHANN_NO+" RECV_CHANN_NAME:"+RECV_CHANN_NAME+"--"+RECV_ADDR_NO)
	$("#RECV_CHANN_ID").val(RECV_CHANN_ID_OLD);
	$("#RECV_CHANN_NO").val(RECV_CHANN_NO);
	$("#RECV_CHANN_NAME").val(RECV_CHANN_NAME);
 
	return;
}


function createPlan(){
	//计算 总容积
	countVolum();
	var parentData = parent.bottomcontent.siglePackJsonData("qxBtnTb");
	var childData = parent.bottomcontent.multiPackJsonData("ordertb");
	 
	$.ajax({
		url: "turnoverplan.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentData":parentData,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				window.parent.topcontent.pageForm.submit();
			}
		}
	});
}


function checkedCheckBox(obj){
	$(obj).parent().parent().find("input[name='mx_checkbox']").attr("checked","checked");
}


//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).attr("checked","true");
	}else{
		$(obj).removeAttr("checked");
	}
}


///计算可用 容积
function countVolum(obj){ 
	$(obj).parent().parent().find("input[name='mx_checkbox']").attr("checked","checked");
	var DELIVER_TYPE = $("#qxBtnTb input:checked").val();
	if("托运" == DELIVER_TYPE){
		return;
	}
	var childCheckBoxs = $("#ordertb input[name='mx_checkbox']:checked");
	var carVolum =  $("#carVolum").val();
	var TRUCK_TYPE = $("#TRUCK_TYPE").val();
	var MIN_VOLUME = $("#MIN_VOLUME").val();
    var MAX_VOLUME = $("#MAX_VOLUME").val();
//	if(null == TRUCK_TYPE || "" == TRUCK_TYPE){
//		parent.showErrorMsg("请选择'装载车型'");
//		return;
//	}
	var total = 0;
	childCheckBoxs.each(function(){
		var obj = $(this).parent().parent().find("input[name='ADVC_PLAN_NUM']");
		var ADVC_PLAN_NUM = obj.val();
		var textType = obj.attr("textType");
		if("int" == textType){
			 if(!CheckIntegerInput(obj)){
				 flag = false;
				return false;
			}
		 }
		
		if(isNaN(ADVC_PLAN_NUM)){
			ADVC_PLAN_NUM = 0;
		}
		var VOLUME = $(this).parent().parent().find("input[name='VOLUME']").val();
		total = Number(total) + Number(ADVC_PLAN_NUM)*Number(VOLUME);
		total = Math.round((total)*100)/100;
		
	});
	
	$("#TOTAL_VOLUME").val(total);
//	$("#cuur_volum").text(MAX_VOLUME-total);
	$("#cuur_volum").text(total);
	
	countNumPriceVolulmn();
	
}

//判断是否是整车
function checkCarVol(num){
	var MIN_VOLUME = $("#MIN_VOLUME").val();
    var MAX_VOLUME = $("#MAX_VOLUME").val();
    
	var arry = num.split(".");
	num = parseInt(num);
	if("00" == arry[1] || 0 == num){
		  return true;
	}
	return false;
	 
}


//校验 发运数量
function checkADVC_PLAN_NUM(){
    var childCheckBoxs = $("#ordertb input[name='mx_checkbox']:checked");
    var flag = true;
    childCheckBoxs.each(function(){
    	//check新规格
    	var SPCL_TECH_FLAG = $(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
    	SPCL_TECH_FLAG = parseInt(SPCL_TECH_FLAG);
    	if(SPCL_TECH_FLAG == 1){
//    		var NEW_SPEC = $(this).parent().parent().find("input[name='NEW_SPEC']").val();
//    		NEW_SPEC = $.trim(NEW_SPEC);
//    		if(null == NEW_SPEC || "" == NEW_SPEC){
//    			parent.showErrorMsg("请填写'新规格'")
//	    		flag = false;
//			    return false;
//    		}
//    		var NEW_COLOR = $(this).parent().parent().find("input[name='NEW_COLOR']").val();
//    		NEW_COLOR = $.trim(NEW_COLOR);
//    		if(null == NEW_COLOR || "" == NEW_COLOR){
//    			parent.showErrorMsg("请填写'新花号'")
//	    		flag = false;
//			    return false;
//    		}
    	}
    	
    	
    	var input = $(this).parent().parent().find("input[name='ADVC_PLAN_NUM']");
    	if(!InputCheck("#"+input.attr("id"))){
		   flag = false;
		   return false;
	    }
    	var ADVC_PLAN_NUM = $.trim(input.val());
    	ADVC_PLAN_NUM = Number(ADVC_PLAN_NUM);
    	if(0 == ADVC_PLAN_NUM){
    		parent.showErrorMsg("'发运数量'不能为'0'")
    		flag = false;
		    return false;
    	}
    	
    	var order_num = $(this).parent().parent().find("input[name='ORDER_NUM']").val();
    	var PLANED_NUM_TD = $(this).parent().parent().find("td[name='PLANED_NUM_TD']").text();
    	var NO_SEND_NUM = $(this).parent().parent().find("input[name='NO_SEND_NUM']").val();
    	PLANED_NUM_TD = $.trim(PLANED_NUM_TD);
    	order_num = Number(order_num);
    	NO_SEND_NUM = Number(NO_SEND_NUM);
    	if(ADVC_PLAN_NUM>NO_SEND_NUM){
    		parent.showErrorMsg("'发运数量'不能大于'未排车数量'")
    		flag = false;
		    return false;
    	}
    });
    
    return flag;
}


//计算 总金额 总体积 总数量
function countNumPriceVolulmn(init){
	var childCheckBoxs = $("#ordertb input[name='mx_checkbox']:checked");
	if(1 == init){
		childCheckBoxs = $("#ordertb input[name='mx_checkbox']");
	}
	
	var TOTAL_NUM = 0;
	var TOTAL_AMOUNT = 0;
	var TOTAL_VOLUMN = 0;
	childCheckBoxs.each(function(){
		var ADVC_PLAN_NUM = $(this).parent().parent().find("input[json='ADVC_PLAN_NUM']").val();
		var DECT_PRICE = $(this).parent().parent().find("td[name='DECT_PRICE_TD']").text();
		var VOLUME = $(this).parent().parent().find("input[name='VOLUME']").val();
		if(null != DECT_PRICE && "" != DECT_PRICE){
			DECT_PRICE = $.trim(DECT_PRICE);
		}
		ADVC_PLAN_NUM = Number(ADVC_PLAN_NUM);
		DECT_PRICE = Number(DECT_PRICE);
		VOLUME = Number(VOLUME);
		if(isNaN(ADVC_PLAN_NUM)){
			ADVC_PLAN_NUM = 0;
		}
		if(isNaN(DECT_PRICE)){
			DECT_PRICE = 0;
		}
		
		if(isNaN(VOLUME)){
			VOLUME = 0;
		}
		//alert(ADVC_PLAN_NUM+"--"+DECT_PRICE+"--"+VOLUME);
		TOTAL_NUM = TOTAL_NUM + ADVC_PLAN_NUM;
		var cuur_amount = FloatMul(ADVC_PLAN_NUM,DECT_PRICE);
		TOTAL_AMOUNT = TOTAL_AMOUNT + cuur_amount;
		TOTAL_VOLUMN = TOTAL_VOLUMN + FloatMul(ADVC_PLAN_NUM,VOLUME);
		$(this).parent().parent().find("td[name='ADVC_PLAN_NUM_AMOUNT_TD']").text(cuur_amount);
	});
	
	$("#allNum").val(TOTAL_NUM);
	TOTAL_AMOUNT = TOTAL_AMOUNT.toFixed(2);
	$("#allAmount").val(TOTAL_AMOUNT);
	TOTAL_VOLUMN = TOTAL_VOLUMN.toFixed(2);
	$("#allVolume").val(TOTAL_VOLUMN);
}



 
function setVolum(obj){
  var TRUCK_TYPE =	$("#TRUCK_TYPE").val();
  	$.ajax({
		url: "carcalculate.shtml?action=getVolum",
		type:"POST",
		dataType:"text",
		data:{"carType":TRUCK_TYPE},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var MIN_VOLUME = jsonResult.data.MIN_VOLUME;
				 var MAX_VOLUME = jsonResult.data.MAX_VOLUME;
				 if(null == MIN_VOLUME || ""==MIN_VOLUME){
					 MIN_VOLUME = 0;
				 }
				 if(null == MAX_VOLUME || ""==MAX_VOLUME){
					 MAX_VOLUME = 0;
				 }
				 $("#MIN_VOLUME").val(MIN_VOLUME);
				 $("#MAX_VOLUME").val(MAX_VOLUME);
				 $("#car_VOLUMN").text("(满车："+MIN_VOLUME+"~"+MAX_VOLUME+")");
				 countVolum();
			}else{
				 
			}
		}
	});
}

function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (state == "\u63d0\u4ea4"||state == "\u9000\u56de"||state == "\u5ba1\u6838\u901a\u8fc7") {
			btnDisable(["recover","cancel","convert"]);
		}
	}
}


//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


//选择收货地址
function selRevcAddr(){
	var parentCheckBoxs = parent.topcontent.$("#ordertb tr:gt(0) input:checked");
	var leg = parentCheckBoxs.length;
	var flag = true;
	if(0 == leg){
		parent.showErrorMsg("请选择要发运的订单");
		return;
	}
	//设置区域服务中心
	setAreaSerId();
	 
	var AREA_SER_ID=$("#AREA_SER_ID").val();
	if(null==AREA_SER_ID||""==AREA_SER_ID){
		parent.showErrorMsg("所选订单里没有区域服务中心");
		return ;
	}
	$("#selectAddrParams").val(" DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+AREA_SER_ID+"' ");
	selCommon('BS_76', false, 'RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'forms[0]','RECV_ADDR_NO,RECV_ADDR', 'DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams');
	$("#USER_SELECT_ADDR").val("USER_SELECT_ADDR");
	
}



//点击区域代发时判断是否显示收货地址
function showAddr(){
	var checked=$("#CHANN_TYPE").prop("checked");
	if(checked==true){
		$("#selAddr").show();
	}else{
		$("#selAddr").hide();
	}
}



function rollUp(param){
	if("1" == param){
		$("#selForm").show();
	    $("#rollDown").hide();
	}else{
		$("#selForm").hide();
	    $("#rollDown").show();
	}
	
}


function showPage(){
	parent.topcontent.showPage();
}


function changeTextAreaLength(obj) {
	var upper = obj.className;
	if("uppercase"==upper){
		obj.value=obj.value.toUpperCase();
	}
	var maxL = obj.maxlength || obj.maxLength;
	var L = stringLength(obj.value);
	if (L > maxL) {
		obj.value = obj.oVal;
	} else {
		obj.oVal = obj.value;
	}
}


/**
 * 浮点数的 乘法
 * @param {Object} arg1
 * @param {Object} arg2
 * @return {TypeName} 
 */
function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}  


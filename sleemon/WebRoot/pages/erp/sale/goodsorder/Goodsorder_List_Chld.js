/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_List_Chld
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
	
	headColumnSort("ordertb","listForm");
	
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});

	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#GOODS_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","goodsorder.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
//	//取消
//	$("#bussClose").click(function(){
//		//查找当前是否有选中的记录
//		var checkBox = $("#ordertb tr:gt(0) input[name='mx']:checked"); //加上name 防止点击其他的checkbox 也认为是选中
//		if(checkBox.length<1){
//			parent.showErrorMsg("请至少选择一条记录");
//			return;
//		}
//		parent.showConfirm("您确认要取消吗?","bottomcontent.closeFn();","N");
//	});
		
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb [name='mx']:checkbox").attr("checked","true");
		}else{
			$("#ordertb [name='mx']:checkbox").removeAttr("checked");
		}
	});
	
	$("#save").click(function(){
		childSave();
	});
	setSelOperateEx();
	
	changeColor();
	//鼠标移除下帧 可用库存div隐藏
	bottomdivHover();
});


 
//取消 订货
function closeFn(){
	var GOODS_ORDER_ID =  getSelRowId();
	var resultSize = $("#resultSize").val();
	var NO = parent.topcontent.$("#GOODS_ORDER_NO"+GOODS_ORDER_ID).val();
	var ids = "";
	var prdNo = "";
	//明细的条数
	var mxSize = $("#ordertb tr:gt(0) input[name='mx']").length;
	//选中的明细
	var checkBox = $("#ordertb tr:gt(0) input[name='mx']:checked");
	var leg = checkBox.length;
	var isAll = false;
    if(mxSize == leg){
    	isAll = true;
    }
 
	checkBox.each(function(){
		var id = $(this).val();
		ids = ids+"'"+id+"',";
		prdNo = $(this).parent().parent().find("td[json='PRD_NO']").text();
		prdNo = $.trim(prdNo);
	});
	ids = ids.substr(0,ids.length-1);
	var remark = "单据'"+NO+"'下，货品'"+prdNo+"'已取消订货";
 
	$.ajax({
	 	url: "goodsorder.shtml?action=orderClose",
		type:"POST",
		data:{"GOODS_ORDER_ID":GOODS_ORDER_ID,"mxIds":ids,"remark":remark,"isAll":isAll},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("取消成功");
				checkBox.parent().parent().remove();
				parent.topcontent.sendMessage(remark);
				countAmount();
				saveSuccess("取消成功","goodsorder.shtml?action=toFrame");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

///设置 一览页面 ‘订货总额’
function countAmount(){
	var selRowId = getSelRowId();
	var tds = $("#ordertb").find("td[json='ORDER_AMOUNT']");
	var total = 0;
	tds.each(function(){
		total = Number(total) + Number($.trim($(this).text()));
	});
	parent.topcontent.setTotalAmount(total);
}


//总部 销售订单 取消的 明细 特殊颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var CANCEL_FLAG = $(this).find("input[name='CANCEL_FLAG']").val();
		if(CANCEL_FLAG == 1){
			var id = $(this).attr("id");
		    $(this).find("td").css("background-color", "#E6B9B8");//#F3F3F3花号还原
		    $(this).find("input[type='text']").attr("readonly","readonly").addClass("readonly");
		    $(this).find("input[type='checkbox']").attr("disabled","disabled");
		}
	});
	
}

function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "goodsorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,GOODS_ORDER_DTL_ID,PRICE){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = parent.topcontent.document.getElementById("state" + selRowId).value;
	if (state == "总部退回" || state == "已处理") {
		window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
	}else if(state == "未处理") {
		var NEW_SPEC=$("#NEW_SPEC"+GOODS_ORDER_DTL_ID).val();
		var NEW_COLOR=$("#NEW_COLOR"+GOODS_ORDER_DTL_ID).val();
		var PRODUCT_REMARK=$("#PRODUCT_REMARK"+GOODS_ORDER_DTL_ID).val();
		var param="&editTable=DRP_GOODS_ORDER_DTL&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE+"&saleFlag=1&NEW_SPEC="+utf8(NEW_SPEC)+"&NEW_COLOR="+utf8(NEW_COLOR)+"&ORDERID="+GOODS_ORDER_DTL_ID+"&PRODUCT_REMARK="+utf8(PRODUCT_REMARK);
		var data=window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice"+param,window,"dialogwidth=800px; dialogheight=600px; status=no");
		if(null!=data){
			$("#NEW_SPEC"+GOODS_ORDER_DTL_ID).val(data.NEW_SPEC);
			$("#NEW_COLOR"+GOODS_ORDER_DTL_ID).val(data.NEW_COLOR);
			$("#PRODUCT_REMARK"+GOODS_ORDER_DTL_ID).val(data.PRODUCT_REMARK);
		}
	}
}

//编辑特殊工艺
function urlshow(SPCL_TECH_ID,PRD_ID,rownum){
    var selRowId = getSelRowId();
	var CHANN_ID = parent.topcontent.getORDER_CHANN_ID();
    if(null == SPCL_TECH_ID || "" == SPCL_TECH_ID){
    	SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
    }
    var PRICE=$("#PRICE"+rownum).val();
 	var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_GOODS_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
    if(null == data){
		return;
	}
    var SPCL_TECH_FLAG = data.SPCL_TECH_FLAG;
	if(SPCL_TECH_FLAG!=0){
		$("#SPECIAL_FLAG" + rownum).html("<font style='color: red'>有</font>");
	}else{
		$("#SPECIAL_FLAG" + rownum).html("无");
	}
	$("#SPCL_TECH_ID" + rownum).val(data.SPCL_TECH_ID);
	
	var PRICE = data.PRICE;//单价
	var DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
	
	$("#PRICE"+rownum).val(PRICE);//单价
	//$("#OLD_PRICE"+rownum).val(data.PRICE);//原价格
	$("#DECT_PRICE"+rownum).text(DECT_PRICE);//折后单价
	
	if(SPCL_TECH_FLAG == 2){
		$("#IS_NO_STAND_FLAG"+rownum).val("1");//非标
		$("#IS_NO_STAND_FLAG_TD"+rownum).text("是");
	}else{
		$("#IS_NO_STAND_FLAG"+rownum).val("0");//标准
//		$("#IS_NO_STAND_FLAG_CHECKBOX"+rownum).removeAttr("checked");
		$("#IS_NO_STAND_FLAG_TD"+rownum).text("否");
	}
	
	
	var num = $.trim($("#ORDER_NUM"+rownum).text());
	var ORDER_AMOUNT = Number(DECT_PRICE)*Number(num);
	$("#ORDER_AMOUNT"+rownum).text(ORDER_AMOUNT);
	
	
    //查找当前是否有选中的记录
	var tds = $("#ordertb tr:gt(0) td[name='ORDER_AMOUNT']");
	var t = 0;
	tds.each(function(){
		 var v = $.trim($(this).text());
		 t = Number(t)+Number(v);
	});
	 
	parent.topcontent.$("#TOTAL_AMOUNT"+selRowId).text(t);
	childSave();
}

function bottomdivHover(){
//	parent.$("#bottomcontent").hover(
//		function() {
//		    //鼠标移入事件
//	    }, 
//	   function() {
//		  //hideShowDiv();
//	    }
//	);
	
}

function hideShowDiv(){
	$("#ordertb  tr:gt(0) div").hide();
}

//查询库存
function queryStore(PRD_NO,SPCL_TECH_ID,rowNum){
	$("#ordertb div[name='showDiv']").hide();
	var selRowId = getSelRowId();
	var ORDER_CHANN_NO = parent.topcontent.$("#ORDER_CHANN_NO"+selRowId).val();
	var SHIP_POINT_ID = parent.topcontent.$("#SHIP_POINT_ID"+selRowId).val();
	 
	$.ajax({
		url: "goodsorder.shtml?action=queryStore",
		type:"POST",
		dataType:"text",
		data:{"PRD_NO":PRD_NO,"SPCL_TECH_ID":SPCL_TECH_ID,"ORDER_CHANN_NO":ORDER_CHANN_NO,"SHIP_POINT_ID":SHIP_POINT_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			var TOTAL_STORE = 0;  // 可用库存
			var U9_STORE_NUM = 0; //u9库存
			var DM_STORE = 0;   //DM抵库库存
			if(jsonResult.success===true){
				var data  = jsonResult.data;
				TOTAL_STORE = data.TOTAL_STORE;
				if(null == TOTAL_STORE || "" == TOTAL_STORE){
					TOTAL_STORE = 0;
				}
				U9_STORE_NUM = data.U9_STORE_NUM;
				if(null == U9_STORE_NUM || "" == U9_STORE_NUM){
					U9_STORE_NUM = 0;
				}
				DM_STORE = data.DM_STORE;
				if(null == DM_STORE || "" == DM_STORE){
					DM_STORE = 0;
				}
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			
			$("#showSpan"+rowNum).text(TOTAL_STORE);
			$("#showSpanU9"+rowNum).text(U9_STORE_NUM);
			$("#showSpanDm"+rowNum).text(DM_STORE);
			$("#showDiv"+rowNum).show();
		}
	});
}


//check是否非标 非标不能备货
function checkIsFB(obj,IS_NO_STAND_FLAG){
	 if("1" == IS_NO_STAND_FLAG){
		 parent.showErrorMsg("非标货品不能抵库");
		 if($(obj).attr("checked")){
			 $(obj).removeAttr("checked");
		 }
	 }
}


//子表保存
function childSave(){
	var selRowId = getSelRowId();
	//check明细
	if(!checkChildPrice()){
		return;
	}
	var mergeFlage = $("#mergeFlage").val();//合并提交页面的标记
	var jsonData = multiRadioJsonData("ordertb",true);
	var BILL_TYPE = parent.topcontent.getBILL_TYPE();
	$.ajax({
		url: "goodsorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"GOODS_ORDER_ID":selRowId,"BILL_TYPE":BILL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				if(mergeFlage){
					parent.window.gotoBottomPage("");
				}else{
					parent.window.gotoBottomPage("label_1");
				}
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}


function showTechPage(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

/**
 * 拼装 子表的json 
 * @param {Object} tableid 表ID
 * @param {Object} isAll true->无视是否选择 拼接所有
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function multiRadioJsonData(tableid,isAll){
	if(null == tableid){
		tableid = "jsontb";
	}
	if(null == isAll){
		isAll = false;
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = false;
		if(isAll){
			isEdit = true;
		}else{
			isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
			//isEdit = $(this).find("input[type='radio']:eq(0)").attr("checked");
		}
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
//			var inputs = $(this).find(":input");
			var tds = $(this).find("td");
			tds.each(function(){
				var inputs = $(this).find(":input");
				if(inputs.length>0){
					inputs.each(function(){
						if(null != $(this).attr("json")){
							var key;
							var value;
							var type = $(this).attr("type");
							if("checkbox" == type){
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
				}else{
					if(null != $(this).attr("json")){
						var k = $(this).attr("json");
					    var text = $.trim($(this).text());
					    jsonData = jsonData+ "'" + k + "':'" + text +"',";
				    }
				}
				
				
			});
			
		
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}


function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态=已处理
		if (state == "总部退回" || state == "已处理") {
			btnDisable(["save"]);
		}else if(state == "未处理") {
			parent.topcontent.$("#commit").removeAttr("disabled");
		}
	}
	var resultSize = $("#resultSize").val();
	if(null == resultSize || "" == resultSize || resultSize == 0){
		btnDisable(["save"]);
		
	}
}


//check字表
function checkChildPrice(){
	var flag = true;
	var inputs = $("#ordertb tr:gt(0) input[json='DECT_PRICE']");
	inputs.each(function(){
	     var textType = $(this).attr("textType");
		 if("float" == textType){
			 if(!CheckFloatInput(this)){
				 flag = false;
				 return false;
			}
			 var DECT_PRICE = $(this).val();
			 DECT_PRICE = parseFloat(DECT_PRICE);
			 if(0 == DECT_PRICE || "0" == DECT_PRICE){
				 parent.showErrorMsg("'折后单价'不能为0！");
				 flag = false;
				 return false;
			 }
		 }
//		 var IS_BACKUP_FLAG=$(this).parent().parent().find("input[name='BACKUPFLAG']").val();//是否抵库
//		 var DEAFULT_ADVC_SEND_DATE=$(this).parent().parent().find("input[name='DEAFULT_ADVC_SEND_DATE']").val();//默认交期
//		 if(0==IS_BACKUP_FLAG&&(""==DEAFULT_ADVC_SEND_DATE||null==DEAFULT_ADVC_SEND_DATE)){
//			 var OLD_ADVC_SEND_DATE =$(this).parent().parent().find("input[name='advcdate']").val();//初始交期
//		 	 var ADVC_SEND_DATE=$(this).parent().parent().find("input[name='ADVC_SEND_DATE']").val();//交期
//		     var oldDate=new Date(OLD_ADVC_SEND_DATE.replace("-", "/").replace("-", "/"));  
//		     var newDate=new Date(ADVC_SEND_DATE.replace("-", "/").replace("-", "/"));  
//		    if(newDate<oldDate){  
//		    	parent.showErrorMsg("预计交货日期不能小于货品分类设定默认交期！");
//		    	flag = false;
//		        return false;  
//		    }  
//		 }
		 
	});
	return flag;
}


function changeTextArea(obj) {
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

//修改折后单价 同步更新金额
function changeOrderAmount(obj){
		 
	var DECT_PRICE = $(obj).val();
	var ORDER_NUM = $(obj).parent().parent().find("td[json='ORDER_NUM']").text();
	ORDER_NUM = $.trim(ORDER_NUM);
	ORDER_NUM = parseInt(ORDER_NUM);
	
//	alert("DECT_PRICE: "+DECT_PRICE+" ORDER_NUM:"+ORDER_NUM);
	
	DECT_PRICE = $.trim(DECT_PRICE);
	DECT_PRICE = Number(DECT_PRICE);
	if(isNaN(DECT_PRICE)){
		DECT_PRICE = 0;
	}
	if(isNaN(ORDER_NUM)){
		ORDER_NUM = 0;
	}
	var ORDER_AMOUNT = FloatMul(DECT_PRICE,ORDER_NUM);
	ORDER_AMOUNT = ORDER_AMOUNT.toFixed(2);
	$(obj).parent().parent().find("td[json='ORDER_AMOUNT']").text(ORDER_AMOUNT);
}



function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}  


function hidIself(obj){
	$(obj).hide();
}


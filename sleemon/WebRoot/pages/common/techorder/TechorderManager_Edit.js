$(function(){
	var type=$("#type").val();
	var TECH_NO_EDIT_FLAG=$("#TECH_NO_EDIT_FLAG").val();
	var optOldFlag=$("#optOldFlag").val();
	if("1"==optOldFlag){
		$("input[class='text_underline']").attr("disabled","disabled");
		$("input[type='checkbox']").attr("disabled","disabled");
		$("select").attr("disabled","disabled");
		$("#EXPLAIN").attr("readonly","readonly");
		$("#SAMEREMARK").attr("readonly","readonly");
	}
	if("view"==type){
		$("input[class='text_underline']").attr("disabled","disabled");
		$("input[type='checkbox']").attr("disabled","disabled");
		$("select").attr("disabled","disabled");
		$("#EXPLAIN").attr("readonly","readonly");
		$("#SAMEREMARK").attr("readonly","readonly");
		$("#save").css({display:'none'});
//		$(".btn").css({display:'none'});
//		$("#EXPLAIN").css({color:'red'});
//		$("#SAMEREMARK").css({color:'red'});
	}else{
		var IS_NO_SPCL_FLAG=$("#IS_NO_SPCL_FLAG").val();
		if(0==IS_NO_SPCL_FLAG){
			$("#specialDiv").css({display:'none'});
		}
		
	}
	if("1"==TECH_NO_EDIT_FLAG){
		$("input").attr("disabled","disabled");
		$("select").attr("disabled","disabled");
		$("#EXPLAIN").attr("readonly","readonly");
		$("#SAMEREMARK").attr("readonly","readonly");
		$("#save").attr("disabled",false);
		$("#close").attr("disabled",false);
		window.parent.promptInfo("当前工艺为生效工艺，不可编辑！");
	}
	
	var error=$("#error").val();
	if(""!=error&&null!=error){
		alert(error);
		window.close();
	}
	$("input[type='checkbox']").click(function(){
		var checked = $(this).prop("checked");
		if(checked==true){
			$(this).attr("checked","checked");
		}else{
			$(this).removeAttr("checked");
		}
		upTrColor();
		countPrice();
	})
	upTrColor();
	countPrice();
})

function upSel(id){
	var sel=$("#sel"+id).val();
	$("#CUST_TURN_VAL"+id).val(sel);
}
function childSave(){
	if(!check()){
		return;
	}
	countPrice();
	removeNL();
	var adjustSizeData = multiPackJsonData("adjustSize");
	var unitReplaceData = multiPackJsonData("unitReplace");
	var addUnitData = multiPackJsonTdData("addUnit");
	var REMARKDate = multiPackJsonData("REMARK");
	var SAMEREMARK=multiPackJsonData("SAMEREMARKTB");
	var PRD_ID=$("#PRD_ID").val();
	var SPCL_TECH_ID=$("#SPCL_TECH_ID").val();
	var BASE_PRICE=$("#BASE_PRICE").val();
	var USE_FLAG=$("#USE_FLAG").val();
	var TABLE=$("#TABLE").val();
	var BUSSID=$("#BUSSID").val();
	var addFlag=$("#addFlag").val();
	 
	$.ajax({
		url: "techorderManager.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"adjustSizeData":adjustSizeData,"unitReplaceData":unitReplaceData,"addUnitData":addUnitData,"REMARKDate":REMARKDate,"PRD_ID":PRD_ID,"SPCL_TECH_ID":SPCL_TECH_ID,"BASE_PRICE":BASE_PRICE,"USE_FLAG":USE_FLAG,"TABLE":TABLE,"BUSSID":BUSSID,"addFlag":addFlag,"SAMEREMARK":SAMEREMARK },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("保存成功");
				var data = jsonResult.data;
				window.returnValue=data;
				window.close();
			}else{
				alert(utf8Decode(jsonResult.messages));
			}
		}
	});
}



function getMax(a,b,c){
	a = Number(a);
	b = Number(b);
	c = Number(c);
	if(isNaN(a)){
		a = 0;
	}
	if(isNaN(b)){
		b = 0;
	}
	
	if(isNaN(c)){
		c = 0;
	}
//	alert("a= "+a+" b= "+b+" c= "+c);
	return Math.max(Math.max(a, b), c);
}


//数字数组 升序排
function sortNumber(a, b){
   return a - b
}


//点击checkbox时计算价格
function countPrice(){
		//显示价格标记，不显示价格就不计算
		var showPrice=$("#showPrice").val();
		if("show"!=showPrice){
			return;
		}
		//判断是否有备注
		var flag=true;
		//货品基准价
		var BASE_PRICE=isNaN($("#BASE_PRICE").val())?0:parseFloat($("#BASE_PRICE").val());
//		alert("BASE_PRICE: " + BASE_PRICE);
		//调价后价格，初始为基准价
		var price=BASE_PRICE;
		//特殊工艺加价倍数
		var TECH_MULT=0;
		//特殊工艺加价 金额
		var TECH_AMOUNT=0;
		
		
		//尺寸调整拿出来计算，取他的下面的最大值做为尺寸调整的 价格
		var sizeCheckBoxs = $("input[sizename='sizeadjust']:checked");
		var tempArray = new Array();
		var SIZE_ADJUST = "";
		sizeCheckBoxs.each(function(){
			//特殊工艺类型
			var SPCL_TECH_TYPE=$(this).parent().parent().find("input[name='SPCL_TECH_TYPE']").val();
			if("尺寸调整" == SPCL_TECH_TYPE){
				//调价类型
				var PRICE_TURN_TYPE_N = $(this).parent().parent().find("input[name='PRICE_TURN_TYPE']").val();
				//调价值
				var PRICE_TURN_VAL_N = $(this).parent().parent().find("input[name='PRICE_TURN_VAL']").val();
				//调整范围内的  调整值
				var CUST_TURN_VAL = $(this).parent().parent().find("input[name='CUST_TURN_VAL']").val();
				
				CUST_TURN_VAL = Number(CUST_TURN_VAL);
				if(isNaN(CUST_TURN_VAL)){
					CUST_TURN_VAL = 0;
				}
				
				PRICE_TURN_VAL_N = Number(PRICE_TURN_VAL_N);
				if(isNaN(PRICE_TURN_VAL_N)){
					PRICE_TURN_VAL_N = 0;
				}
				
				var SIZE_ADJUST_RESULT = 0;
				if("倍率加价" == PRICE_TURN_TYPE_N){
					SIZE_ADJUST_RESULT = BASE_PRICE*PRICE_TURN_VAL_N;
				}
				if("值加价" == PRICE_TURN_TYPE_N){
					 SIZE_ADJUST_RESULT = PRICE_TURN_VAL_N;
				}
				if("单位费用" == PRICE_TURN_TYPE_N){
					 SIZE_ADJUST_RESULT = CUST_TURN_VAL*PRICE_TURN_VAL_N;
				}
				tempArray.push(SIZE_ADJUST_RESULT);
			}
			
			
		});
		
		//数组升序
		tempArray.sort(sortNumber);
		SIZE_ADJUST = tempArray[tempArray.length-1];
		SIZE_ADJUST = Number(SIZE_ADJUST);
		if(isNaN(SIZE_ADJUST)){
			SIZE_ADJUST = 0;
		}
//		alert(SIZE_ADJUST); 
		 
		//其它特殊工艺的价格计算法
		var check=$("input[type='checkbox']:checked");
		check.each(function(){
			//备注，如果备注不为空时，为非标，不计算价格
			var EXPLAIN=$("#EXPLAIN").val();
			EXPLAIN = $.trim(EXPLAIN);
			if(""!=EXPLAIN&&null!=EXPLAIN){
				flag=false;
				return;
			}
			//特殊工艺类型
			var SPCL_TECH_TYPE=$(this).parent().parent().find("input[name='SPCL_TECH_TYPE']").val();
			
//			if("非标工艺说明"!=SPCL_TECH_TYPE&&"部件新增"!=SPCL_TECH_TYPE){
//				//调价类型
//				var PRICE_TURN_TYPE=$(this).parent().parent().find("input[name='PRICE_TURN_TYPE']").val();
//				//调价值
//				var PRICE_TURN_VAL_temp=$(this).parent().parent().find("input[name='PRICE_TURN_VAL']").val();
//				var PRICE_TURN_VAL=isNaN(PRICE_TURN_VAL_temp)?0:parseFloat(PRICE_TURN_VAL_temp);
//				if("倍率加价"==PRICE_TURN_TYPE){
//					TECH_MULT=TECH_MULT+PRICE_TURN_VAL;
//				}
//				if("值加价"==PRICE_TURN_TYPE){
//					TECH_AMOUNT=TECH_AMOUNT+PRICE_TURN_VAL;
//				}
//			}
			
			 
			var SPCL_TECH_TYPE0=$(this).parent().find("input[name='SPCL_TECH_TYPE0']").val();
			var SPCL_TECH_TYPE1=$(this).parent().find("input[name='SPCL_TECH_TYPE1']").val();
			if("部件新增"==SPCL_TECH_TYPE0){
				//调价类型
				var PRICE_TURN_TYPE0=$(this).parent().parent().find("input[name='PRICE_TURN_TYPE0']").val();
				//调价值
				var PRICE_TURN_VAL_temp0=$(this).parent().parent().find("input[name='PRICE_TURN_VAL0']").val();
				var PRICE_TURN_VAL0=isNaN(PRICE_TURN_VAL_temp0)?0:parseFloat(PRICE_TURN_VAL_temp0);
				if("倍率加价"==PRICE_TURN_TYPE0){
					TECH_MULT=TECH_MULT+PRICE_TURN_VAL0;
				}
				if("值加价"==PRICE_TURN_TYPE0){
					TECH_AMOUNT=TECH_AMOUNT+PRICE_TURN_VAL0;
				}
			}
			if("部件新增"==SPCL_TECH_TYPE1){
				//调价类型
				var PRICE_TURN_TYPE1=$(this).parent().parent().find("input[name='PRICE_TURN_TYPE1']").val();
				//调价值
				var PRICE_TURN_VAL_temp1=$(this).parent().parent().find("input[name='PRICE_TURN_VAL1']").val();
				var PRICE_TURN_VAL1=isNaN(PRICE_TURN_VAL_temp1)?0:parseFloat(PRICE_TURN_VAL_temp1);
				if("倍率加价"==PRICE_TURN_TYPE1){
					TECH_MULT=TECH_MULT+PRICE_TURN_VAL1;
				}
				if("值加价"==PRICE_TURN_TYPE1){
					TECH_AMOUNT=TECH_AMOUNT+PRICE_TURN_VAL1;
				}
			}
		})
		if(flag){
			price=Math.round((isNaN(price+price*TECH_MULT+TECH_AMOUNT)?0:price+price*TECH_MULT+TECH_AMOUNT)*100)/100;
			price = price + SIZE_ADJUST
		}
		$("#price").html(price);
}
//验证尺寸调整值在调整范围之内，备注可输入长度和限制字符
function check(){
	
	var SAMEREMARK=$("#SAMEREMARK").val();
	SAMEREMARK = $.trim(SAMEREMARK);
	//验证备注长度
	if(SAMEREMARK!=null || SAMEREMARK != ""){
  	   if(SAMEREMARK.length>80){
  		    alert("一般特殊工艺描述过长！");
  		    return false;
  	   }
	}
	if(SAMEREMARK.indexOf("\\")>=0||SAMEREMARK.indexOf("'")>=0){
		alert("一般特殊工艺描述不允许输入\"'\"和\"\\\"符号！");
		return false;
	}
	
	
	var EXPLAIN=$("#EXPLAIN").val();
	EXPLAIN = $.trim(EXPLAIN);
	//验证备注长度
	if(EXPLAIN!=null || EXPLAIN != ""){
  	   if(EXPLAIN.length>80){
  		    alert("特殊工艺描述过长！");
  		    return false;
  	   }
	}
	if(EXPLAIN.indexOf("\\")>=0||EXPLAIN.indexOf("'")>=0){
		alert("特殊工艺描述不允许输入\"'\"和\"\\\"符号！");
		return false;
	}
	
	
	
	
	var checkBox=$("input[type='checkbox']:checked");
	var flag=true;
	checkBox.each(function(){
		var SPCL_TECH_TYPE=$(this).parent().parent().find("input[name='SPCL_TECH_TYPE']").val();
		if("尺寸调整"==SPCL_TECH_TYPE){
			var CUST_TURN_VAL_temp=$(this).parent().parent().find("input[name='CUST_TURN_VAL']").val();
			CUST_TURN_VAL_temp = $.trim(CUST_TURN_VAL_temp);
			if(""==CUST_TURN_VAL_temp||null==CUST_TURN_VAL_temp){
				alert("请填写勾中的尺寸调整");
				flag=false;
				return false;
			}
			if(isNaN(CUST_TURN_VAL_temp)){
				alert("尺寸调整值只能输入数字");
				flag=false;
				return false;
			}
			var CUST_TURN_VAL=isNaN(CUST_TURN_VAL_temp)?0:parseFloat(CUST_TURN_VAL_temp);
			var CURRT_VAL_TURN_BEG_temp=$(this).parent().parent().find("input[name='CURRT_VAL_TURN_BEG']").val();
			var CURRT_VAL_TURN_BEG=isNaN(CURRT_VAL_TURN_BEG_temp)?0:parseFloat(CURRT_VAL_TURN_BEG_temp)
			var CURRT_VAL_TURN_END_temp=$(this).parent().parent().find("input[name='CURRT_VAL_TURN_END']").val();
			var CURRT_VAL_TURN_END=isNaN(CURRT_VAL_TURN_END_temp)?0:parseFloat(CURRT_VAL_TURN_END_temp)
			if(CURRT_VAL_TURN_BEG>CUST_TURN_VAL||CUST_TURN_VAL>CURRT_VAL_TURN_END){
				alert("尺寸调整值填写请在允许范围"+CURRT_VAL_TURN_BEG+"----"+CURRT_VAL_TURN_END+"之内");
				flag=false;
				return false;
			}
		}
		if("部件替换"==SPCL_TECH_TYPE){
			var sel=$(this).parent().parent().find("select[name='sel']").val();
			sel = $.trim(sel);
			if(""==sel||"--请选择--"==sel){
				alert("请选择勾中的部件替换");
				flag=false;
				return false;
			}
		}
	})
	return flag
}




/*
 *将多个form封装成json串
 *
 *@param 
 *@return json格式字符串
 */
function multiPackJsonTdData(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var tds = $("#"+tableid+" tr:gt(0) td[name='value_td']");
	tds.each(function(){
		    var checkbox = $(this).find("input[type='checkbox']");
		    if(null == checkbox){
		    	return
		    }
			var checked = checkbox.attr("checked");
			if("checked" != checked){
				return;
			}
			 
				
			var inputs = $(this).find(":input");
			if(inputs.length>0){
				jsonData = jsonData+"{";
			}
			
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
			if(inputs.length>0){
				jsonData = jsonData.substr(0,jsonData.length-1)+"},"
			}
			
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}
function upTrColor(){
	var checkBox = $("input[type=checkbox]");
	checkBox.each(function(){
		var checked=$(this).prop("checked");
		var colorFlag=$(this).parent().parent().find("input[name='colorFlag']").val();
		var addUnitFlag=$(this).parent().parent().find("input[name='addUnitFlag']").val();
		if(checked){
			if(colorFlag!="1"&&addUnitFlag!="1"){
				$(this).parent().parent().css('background-color','#9FB6CD');	
			}else if(addUnitFlag=="1"){
				$(this).parent().css('background-color','#9FB6CD');
				var tdName=$(this).attr("id");
				$(this).parent().parent().find("td[name='"+tdName+"']").css('background-color','#9FB6CD');
			}
		}else{
			$(this).parent().parent().css('background-color','white');	
		}
		if(!checked&&addUnitFlag=="1"){
			$(this).parent().css('background-color','white');
			var tdName=$(this).attr("id");
			$(this).parent().parent().find("td[name='"+tdName+"']").css('background-color','white');
		}
	})
}
function saveSale(){
	var NEW_SPEC=$("#NEW_SPEC").val();
	var NEW_COLOR=$("#NEW_COLOR").val();
	var PRODUCT_REMARK=$("#PRODUCT_REMARK").val();
	var editTable=$("#editTable").val();
	var ORDERID=$("#ORDERID").val();
	$.ajax({
		url: "techorderManager.shtml?action=editSaleChld",
		type:"POST",
		dataType:"text",
		data:{"ORDERID":ORDERID,"NEW_SPEC":NEW_SPEC,"NEW_COLOR":NEW_COLOR,"PRODUCT_REMARK":PRODUCT_REMARK,"editTable":editTable},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("保存成功");
				var data = jsonResult.data;
				window.returnValue=data;
				window.close();
			}else{
				alert(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//去掉换行符
function removeNL()
{
	var SAMEREMARK=$("#SAMEREMARK").val();
     $("#SAMEREMARK").val(SAMEREMARK.replace(/\n/g,"")); 
}

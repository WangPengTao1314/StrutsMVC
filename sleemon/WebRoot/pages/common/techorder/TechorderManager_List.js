$(function(){
	var type=$("#type").val();
	if("view"==type){
		$("input").attr("disabled","disabled");
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
		countPrice();
	})
	countPrice();
})

function upSel(id){
	var sel=$("#sel"+id).val();
	$("#CUST_TURN_VAL"+id).val(sel);
}
function childSave(){
	countPrice();
	var adjustSizeData = multiPackJsonData("adjustSize");
	var unitReplaceData = multiPackJsonData("unitReplace");
	var addUnitData = multiPackJsonData("addUnit");
	var REMARKDate = multiPackJsonData("REMARK");
	var PRD_ID=$("#PRD_ID").val();
	var SPCL_TECH_ID=$("#SPCL_TECH_ID").val();
	var BASE_PRICE=$("#BASE_PRICE").val();
	var DECT_RATE=$("#DECT_RATE").val();
	var USE_FLAG=$("#USE_FLAG").val();
	var TABLE=$("#TABLE").val();
	var BUSSID=$("#BUSSID").val();
	$.ajax({
		url: "techorderManager.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"adjustSizeData":adjustSizeData,"unitReplaceData":unitReplaceData,"addUnitData":addUnitData,"REMARKDate":REMARKDate,"PRD_ID":PRD_ID,"SPCL_TECH_ID":SPCL_TECH_ID,"BASE_PRICE":BASE_PRICE,"DECT_RATE":DECT_RATE,"USE_FLAG":USE_FLAG,"TABLE":TABLE,"BUSSID":BUSSID },
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
//点击checkbox时计算价格
function countPrice(){
	//显示价格标记，不显示价格就不计算
	var showPrice=$("#showPrice").val();
	if("show"!=showPrice){
		return;
	}
	var check=$("input[type='checkbox']:checked");
		//货品基准价
		var BASE_PRICE=isNaN($("#BASE_PRICE").val())?0:parseFloat($("#BASE_PRICE").val());
		//折扣率
		var DECT_RATE=isNaN($("#DECT_RATE").val())?0:parseFloat($("#DECT_RATE").val());
		//调价后价格，初始为基准价
		var price=BASE_PRICE;
		//特殊工艺加价倍数
		var TECH_MULT=DECT_RATE;
		//特殊工艺加价 金额
		var TECH_AMOUNT=0;
		check.each(function(){
			//备注，如果备注不为空时，为非标，不计算价格
			var EXPLAIN=$("#EXPLAIN").val();
			if(""!=EXPLAIN&&null!=EXPLAIN){
				price=0;
				return;
			}
			//特殊工艺类型
			var SPCL_TECH_TYPE=$(this).parent().parent().find("input[name='SPCL_TECH_TYPE']").val();
			if("非标工艺说明"!=SPCL_TECH_TYPE){
				var PRICE_TURN_TYPE=$(this).parent().parent().find("input[name='PRICE_TURN_TYPE']").val();
				//调价类型
				var PRICE_TURN_TYPE=$(this).parent().parent().find("input[name='PRICE_TURN_TYPE']").val();
				//调价值
				var PRICE_TURN_VAL_temp=$(this).parent().parent().find("input[name='PRICE_TURN_VAL']").val();
				var PRICE_TURN_VAL=isNaN(PRICE_TURN_VAL_temp)?0:parseFloat(PRICE_TURN_VAL_temp);
				if("倍率加价"==PRICE_TURN_TYPE){
					TECH_MULT=TECH_MULT+PRICE_TURN_VAL;
				}
				if("值加价"==PRICE_TURN_TYPE){
					TECH_AMOUNT=TECH_AMOUNT+PRICE_TURN_VAL;
				}
			}
		})
		price=Math.round((isNaN(price*TECH_MULT+TECH_AMOUNT)?0:price*TECH_MULT+TECH_AMOUNT)*100)/100;
		$("#price").html(price);
}
//当备注框按键抬起事件计算价格
function countKeyPrice(){
	//备注，如果备注不为空时，为非标，不计算价格
	var EXPLAIN=$("#EXPLAIN").val();
	if(""!=EXPLAIN&&null!=EXPLAIN){
		$("#price").html("0");
	}else{
		countPrice();
	}
	
}
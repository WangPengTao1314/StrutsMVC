
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	$("#save").click(function(){
		var checkBox = $("#mainForm tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		if(!checkFrom()){
			return;
		}
		saveChld();
	})
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
});
function saveChld(){
	$("#save").attr("disabled","disabled");
	var jsonData = multiPackJsonData("mainForm");
	$.ajax({
		url: "advcorder.shtml?action=childPriceEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("保存成功");
				window.opener.parent.topcontent.countPrint();
				window.close();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				$("#save").removeAttr("disabled");
			}
		}
	});
}
function url(SPCL_TECH_ID){
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
//输入折扣率时计算折后价和应收金额
function countRate(id){
	var PRICE=changeNum($("#PRICE"+id).val());//单价
	var DECT_RATE=changeNum($("#DECT_RATE"+id).val());//折扣率
	var ORDER_NUM=changeNum($("#ORDER_NUM"+id).val());//订货数量
	var DECT_PRICE=Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后价
	var PAYABLE_AMOUNT=Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//
	$("#DECT_PRICE"+id).val(DECT_PRICE);
	$("#PAYABLE_AMOUNT"+id).val(PAYABLE_AMOUNT);
}
//输入折后价时计算折扣率和应收金额
function countPrice(id){
	var PRICE=changeNum($("#PRICE"+id).val());//单价
	var DECT_PRICE=changeNum($("#DECT_PRICE"+id).val());//折后价
	var ORDER_NUM=changeNum($("#ORDER_NUM"+id).val());//订货数量
	var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//折扣率
	var PAYABLE_AMOUNT=Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//
	$("#DECT_RATE"+id).val(DECT_RATE);
	$("#PAYABLE_AMOUNT"+id).val(PAYABLE_AMOUNT);
}
//输入应收金额 计算折后价和折扣率
function countAmount(id){
	var PAYABLE_AMOUNT=changeNum($("#PAYABLE_AMOUNT"+id).val());//应收金额
	var ORDER_NUM=changeNum($("#ORDER_NUM"+id).val());//订货数量
	var PRICE=changeNum($("#PRICE"+id).val());//单价
	var DECT_PRICE=Math.round((isNaN(PAYABLE_AMOUNT/ORDER_NUM)?0:PAYABLE_AMOUNT/ORDER_NUM)*100)/100;//折后价
	var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//折扣率
	$("#DECT_RATE"+id).val(DECT_RATE);
	$("#DECT_PRICE"+id).val(DECT_PRICE);
}
function changeNum(val){
	if(""==val||null==val){
		return 0;
	}
	return isNaN(val)?0:parseFloat(val);
}
function checkFrom(){
	var checkBox=$("#mainForm tr:gt(0) input:checked");
	var flag=true;
		checkBox.each(function(){
			var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
			var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//折后价
			var PAYABLE_AMOUNT=$(this).parent().parent().find("input[name='PAYABLE_AMOUNT']").val();//应收金额
			if(""==DECT_RATE||null==DECT_RATE){
				parent.showErrorMsg("请输入折扣率");
	            flag=false;
				return false;
			}
			if(""==DECT_PRICE||null==DECT_PRICE){
				parent.showErrorMsg("请输入折后单价");
	            flag=false;
				return false;
			}
			if(""==PAYABLE_AMOUNT||null==PAYABLE_AMOUNT){
				parent.showErrorMsg("请输入应收金额");
	            flag=false;
				return false;
			}
			var re1 = new RegExp(/^\d{1,2}(\.\d{1,2})?$/);
			var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
	        if(!re1.test(DECT_RATE)){
	            parent.showErrorMsg("折扣率最多可输入2位正整数和2位小数");
	            flag=false;
				return false;
	        }
			
	        if(!re2.test(DECT_PRICE)){
	            parent.showErrorMsg("折后价最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
	        if(!re2.test(PAYABLE_AMOUNT)){
	            parent.showErrorMsg("应收金额最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
		});
		return flag;
}
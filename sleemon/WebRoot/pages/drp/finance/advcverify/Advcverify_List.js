/**
 * @prjName:喜临门营销平台
 * @fileName:预订单核销
 * @author zzb
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advcverify.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advcverify.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advcverify.shtml?action=delete", "STATEMENTS_ID");
	
	$("#checkPayMore").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			showErrorMsg("请至少选择一条记录");
			return;
	    }
		
		parent.showConfirm("您确认要核销吗?","topcontent.checkPayment();","N");
 
	});
	$("input[name='checkPayment']").click(function(){
		var id = $(this).attr("id");
		parent.showConfirm("您确认要核销吗?","topcontent.checkPayment(1,'"+id+"');","N");
	});
 
	
	countPAY_AMONT();
	
	$("#ordertb span").click(function(){
		addTabTag(this);
	});
	
	$("#ordertb input[name='eid']").click(function(){
		justCheck();
	});
});

//点击checkbox的时候 判断核销按钮灰
function justCheck(){
	var checkbox = $("#ordertb input[name='eid']:checked");
	var length = checkbox.length;
	var i = 0;
	checkbox.each (function(){
		var PAY_AMONT = $(this).parent().parent().find("td[name='PAY_AMONT']").text();
		var WRITE_OFF_AMONT = $(this).parent().parent().find("td[name='WRITE_OFF_AMONT']").text();
		WRITE_OFF_AMONT = Number($.trim(WRITE_OFF_AMONT));
		PAY_AMONT = Number($.trim(PAY_AMONT));
		if(!isNaN(PAY_AMONT) && PAY_AMONT == WRITE_OFF_AMONT){
			i = Number(i)+1;
		} 
	});
	
	if(i>0 && i == length){
		btnDisable(["checkPayMore"]);
	}else{
		btnReset(["checkPayMore"]);
	}
	return i;
}



function getIds(){
	var ids = "";
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	checkBox.each(function(){
	    if("" != $(this).val()){
			ids = ids+"'"+$(this).val()+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	return ids;
}

//核销
function checkPayment(isRow,bunId){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请选择一条记录");
		return;
	} 
	//如果点击行上面的核销 则取消已经选择的其它行
	if(isRow == 1 || isRow == "1" ){
		checkBox.removeAttr("checked");
		selectParent($("#"+bunId));
	}
	checkBox = $("#ordertb tr:gt(0) input:checked");
    if(!checkRow()){
    	return;
    }
    
    var ids = "";
    var STATEMENTS_IDS = "";
	checkBox.each(function(){
		if("" != $(this).val()){
			ids = ids+"'"+$(this).val()+"',";
		}
		var STATEMENTS_ID = $(this).parent().parent().find("input[name='STATEMENTS_ID']").val();
		
		STATEMENTS_IDS = STATEMENTS_IDS+"'"+STATEMENTS_ID+"',";
	});
	ids = ids.substr(0,ids.length-1);
	STATEMENTS_IDS = STATEMENTS_IDS.substr(0,STATEMENTS_IDS.length-1);
	
    var jsonDate = multiPackJsonData("ordertb");
    
    $.ajax({
		url: "advcverify.shtml?action=checkPayment",
		type:"POST",
		data:{"jsonData":jsonDate,"IDS":ids,"STATEMENTS_IDS":STATEMENTS_IDS},
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel(utf8Decode(jsonResult.messages));
				//弹出框的ID
				$("#YT_MSG_BTN_OK").click(function(){
					$("#pageForm").submit();
				});
	            
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
	
}

//反核销
function unCheckPaymentFn(index){
	var STATEMENTS_PAYMENT_DTL_ID = $("#eid"+index).val();
	$("#slectParams").val(" STATEMENTS_PAYMENT_DTL_ID='"+STATEMENTS_PAYMENT_DTL_ID+"' and DEL_FLAG=0 ");
	selCommon('BS_122', true, 'WRITE_OFF_DTL_ID'+index, 'WRITE_OFF_DTL_ID','forms[0]','WRITE_OFF_PSON_TIME'+index,'WRITE_OFF_PSON_TIME','slectParams');
	 
	var WRITE_OFF_DTL_IDS = $("#WRITE_OFF_DTL_ID"+index).val();
	var WRITE_OFF_PSON_TIMES = $("#WRITE_OFF_PSON_TIME"+index).val();
    if(null == WRITE_OFF_DTL_IDS || WRITE_OFF_DTL_IDS == ""){
    	return;
    }
    if(null == WRITE_OFF_PSON_TIMES || WRITE_OFF_PSON_TIMES == ""){
    	return;
    }
    var STATEMENTS_ID = $("#STATEMENTS_ID"+index).val();
    $.ajax({
		url: "advcverify.shtml?action=unCheckPayment",
		type:"POST",
		data:{"STATEMENTS_ID":STATEMENTS_ID,"STATEMENTS_PAYMENT_DTL_ID":STATEMENTS_PAYMENT_DTL_ID,"WRITE_OFF_DTL_IDS":WRITE_OFF_DTL_IDS,"WRITE_OFF_PSON_TIMES":WRITE_OFF_PSON_TIMES},
		dataType:"text",
		complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		   if(jsonResult.success===true){
			   showMsgPanel(utf8Decode(jsonResult.messages));
			   //弹出框的ID
			   $("#YT_MSG_BTN_OK").click(function(){
					$("#pageForm").submit();
			  });
			}else{
			  showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
	
}

function beforeUnCheckPaymentFn(index){
	parent.showConfirm("您确认要反核销吗?","topcontent.unCheckPaymentFn("+index+");","N");
}

//计算 总金额
function countPAY_AMONT(){
	var tds = $("#ordertb tr:gt(0) td[name='PAY_AMONT']");
	var WRITE_OFF_AMONT=$("#ordertb tr:gt(0) td[name='WRITE_OFF_AMONT']");
	var total = 0;
	var totalVerifyAmount=0;
	var totalNoVerifyAmount=0;
	tds.each(function(){
		total = total + Number($.trim($(this).text()));
	});
	WRITE_OFF_AMONT.each(function(){
		totalVerifyAmount = totalVerifyAmount + Number($.trim($(this).text()));
	})
	totalNoVerifyAmount=total-totalVerifyAmount;
	$("#totalAmount").val(total);
	$("#totalVerifyAmount").val(totalVerifyAmount);
	$("#totalNoVerifyAmount").val(totalNoVerifyAmount);
}


function checkFlag(obj){
//	btnReset();
//	var checks = $("#ordertb tr:gt(0) input:checked");
//	checks.each(function(){
//		var selRowId = $(this).val();
//		var WRITE_OFF_FLAG = $("#WRITE_OFF_FLAG_" + selRowId).val();
//		if (WRITE_OFF_FLAG == "1") {
//			btnDisable(["checkPayMore"]);
//		}
//	});
	
	 
}

function selectParent(obj){
	$(obj).parent().parent().find("input[type='checkbox']").attr("checked","checked");
}

 //点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var WRITE_OFF_FLAG = document.getElementById("WRITE_OFF_FLAG_" + selRowId).value;
	var WRITE_OFF_FLAG = document.getElementById("WRITE_OFF_FLAG_" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (WRITE_OFF_FLAG == "1") {
		btnDisable(["checkPayMore"]);
	}
}

function addTabTag(obj){
	var mainFrame = window.top.mainFrame;  
	var url = $(obj).attr("url");
	var label = $(obj).attr("label");
//	mainFrame.addTab(label,label,"../../"+url);
	
	window.showModalDialog(url,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

function checkRow(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	var flag = true;
	checkBox.each(function(){
		var hx = justCheck();
		if(hx>0){
			parent.showErrorMsg("已经核销的记录不能再核销，请取消选择");
			flag = false;
			return false;
		}
		var WRITE_OFF_AMONT_OBJ = $(this).parent().parent().find("input[json='WRITE_OFF_AMONT']");
		if (!CheckFloatInput(WRITE_OFF_AMONT_OBJ)) {
			flag = false;
			return false;
	    }
		var WRITE_OFF_AMONT_VAL = WRITE_OFF_AMONT_OBJ.val();
		WRITE_OFF_AMONT_VAL = Number($.trim(WRITE_OFF_AMONT_VAL));
		if(isNaN(WRITE_OFF_AMONT_VAL)){
			WRITE_OFF_AMONT_VAL = 0;
		}
		if(WRITE_OFF_AMONT_VAL == 0){
			//WRITE_OFF_AMONT_OBJ.parent().css("background-color","red");
			parent.showErrorMsg("请输入'本次核销金额'");
			flag = false;
			return false;
		}
		var PAY_AMONT = $(this).parent().parent().find("td[name='PAY_AMONT']").text();
		PAY_AMONT = Number($.trim(PAY_AMONT));
		if(isNaN(PAY_AMONT)){
			PAY_AMONT = 0;
		}
		
		if(WRITE_OFF_AMONT_VAL>PAY_AMONT){
			parent.showErrorMsg("'本次核销金额'不能大于'收款金额'");
			flag = false;
			return false;
		}
		
	});
	
	return flag;
}

function btnReset(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).removeAttr("disabled");
	}
}
$(function() {
	 
	document.onkeydown = function(event) {
		e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == 13) {
			var scanSN = $("#SN_TEMP").val();
			matchScanPdt(scanSN);
			$("#SN_TEMP").val("");
			
		}
	}
	
	$("#q_commit").click(function () {
		childSave();
		 
	});
	
	$("#q_close").click(function () {
		window.close();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
});


//子表保存
function childSave(){
    //查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条明细")
		return;
	}
	//对于选中的明细校验
	if(!checkchildRealNum()){
		return;
	}
	var billType=document.getElementById("BILL_TYPE").value;
	var storeoutId=document.getElementById("STOREOUT_ID").value;
	
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storeout.shtml?action=scanChildEdit&BILL_TYPE="+billType+"&STOREOUT_ID="+storeoutId,
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				$("#YT_MSG_BTN_OK").click(function(){
					window.opener.parent.window.gotoBottomPage("label_1");
				    window.close();
				});
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				$("#YT_MSG_BTN_OK").click(function(){
					window.opener.parent.document.getElementById("label_1").click();
				    window.close();
				});
			}
		}
	});
}



////匹配货品
function matchScanPdt(scanSN){
	$.ajax({
	 	url: "storeout.shtml?action=editScanList&SN="+scanSN,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var data = jsonResult.data;
				 setSN(data.PRD_ID,scanSN);
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//给货品序列号赋值
function setSN(strPdtId,scanSN){
	var pdtObj = $("#jsontb tr:gt(0)");
	var flag = true;
	var size = 0;
	var length = pdtObj.length;
	pdtObj.each(function(){
		var sn  = $(this).find("input[json='SN']").attr("value");
		var pdtId = $(this).find("input[json='PRD_ID']").attr("value");
	    sn = $.trim(sn);
	    if(null != sn && "" != sn && sn == scanSN){
	    	flag = false;
	    	return false;
	    } 
	    if(strPdtId != pdtId){
	    	size = parseInt(size)+1;
	    }
	});

	if(size == length){
		parent.showErrorMsg("没有匹配的货品，请检查");
		return;
	}
	
	if(!flag){
		parent.showErrorMsg("已扫过该序列号");
		return;
	}
	//如果数据库还有改货品的序列号，而页面上与之匹配的货品已全部扫码完毕，提示
	var notHave = true;
	pdtObj.each(function(){
		var pdtId = $(this).find("input[json='PRD_ID']").attr("value");
		var sn  = $(this).find("input[json='SN']").attr("value");
		sn = $.trim(sn);
		if(strPdtId==pdtId){
			if(null == sn || "" == sn){
				 $(this).find("input[json='SN']").val(scanSN);
				 $(this).find("input:checkbox").attr("checked","true");
				 $(this).find("input[json='REAL_NUM']").val(1);
				 notHave = false;
				 return false;
			}
		}
	});
	
	if(notHave){
		 parent.showErrorMsg("没有匹配的货品，请检查");
	}
	
}


function url(SPCL_TECH_ID){
		var data=window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


function setRealNum(rownum){
	var SN = $("#SN"+rownum);
	var SNVal = $.trim(SN.val());
	if(null != SNVal && "" != SNVal){
		SN.parent().parent().find("input[json='REAL_NUM']").val(1);
	}
}


//检查扫码数量
function checkchildRealNum(){
	var checkboxs = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkboxs.each(function(){
		var input = $(this).parent().parent().find("input[name='REAL_NUM']");
		var NOTICE_NUM = $(this).parent().parent().find("td[name='NOTICE_NUM']").html();
		if (!InputCheck(input)) {
			flag = false;
			return false;
		}
		var REAL_NUM = input.val();
//		if(0 == REAL_NUM || "0" == REAL_NUM){
//			parent.showErrorMsg("'扫码数量'不能为0");
//			flag = false;
//			return false;
//		}
		if(REAL_NUM>NOTICE_NUM){
			parent.showErrorMsg("'扫码数量'不能大于'通知出库数量'");
			flag = false;
			return false;
		}
	});
	
	return flag;
}


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
		url: "sporadicstoreout.shtml?action=scanChildEdit&BILL_TYPE="+billType+"&STOREOUT_ID="+storeoutId,
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				$("#YT_MSG_BTN_OK").click(function(){
//					window.opener.parent.document.getElementById("label_1").click();
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
	 	url: "sporadicstoreout.shtml?action=editScanList&SN="+scanSN,
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


function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var SPCL_TECH_FLAG;
	if(arrs[13]==null||arrs[13]==""||arrs[13]==0){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	var functionText = "setRealNum('"+rownum+"');";
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
 
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREOUT_DTL_ID' name='STOREOUT_DTL_ID"+rownum+"' id='STOREOUT_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="货品序列号" mustinput="true" type="text"   maxlength="50" size="12"  value="'+arrs[2]+'"  onkeyup="'+functionText+'"  />&nbsp;</td>')
		    .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" inputtype="string" value="'+arrs[3]+'"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text" mustinput="true" READONLY inputtype="string" maxlength="30" size="12" value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" mustinput="true" type="text" READONLY inputtype="string" maxlength="100" value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" mustinput="true" type="text" READONLY inputtype="string" maxlength="50" size="10" value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input type="text" json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" READONLY  autocheck="true" inputtype="string" label="花号" maxlength="50" size="6"  value="'+arrs[7]+'"size="15" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" inputtype="string" label="品牌"   size="5"  type="text" READONLY  mustinput="true"  READONLY   value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" inputtype="string" label="标准单位"    type="text"  mustinput="true"   READONLY size="6"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'"  autocheck="true" label="通知出库数量"  type="text" READONLY inputtype="string" maxlength="11" size="5" value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'"  autocheck="true" label="扫码数量"  type="text"   inputtype="int" maxlength="11" size="5" value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[12]+'"/>')
            ;
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
}
function url(rownum){
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
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
		var input = $(this).parent().parent().find("input[json='REAL_NUM']");
		var NOTICE_NUM = $(this).parent().parent().find("input[json='NOTICE_NUM']").val();
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


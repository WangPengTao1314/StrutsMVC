$(function() {
	document.onkeydown = function(event) {
		e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == 13) {
			var scanSN = document.getElementById("SN_TEMP").value;
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
//	if(!formMutiTrChecked()){
//		return;
//	}
	
	var billType=document.getElementById("BILL_TYPE").value;
	var storeoutId=document.getElementById("STOREOUT_ID").value;
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "repairstoreout.shtml?action=scanChildEdit&BILL_TYPE="+billType+"&STOREOUT_ID="+storeoutId,
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
			}
		}
	});
}



////匹配货品
function matchScanPdt(scanSN){
	$.ajax({
	 	url: "repairstoreout.shtml?action=editScanList&SN="+scanSN,
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
	var num = 0;
	var smNum = 0;
	var size = 0;
	var length = pdtObj.length;
	pdtObj.each(function(){
		var pdtId = $(this).find("input[json='PRD_ID']").attr("value");
		if(strPdtId == pdtId){
			num = parseInt(num)+1;
		}else{
	    	size = parseInt(size)+1;
	    }
	});
	
	if(size == length){
		parent.showErrorMsg("没有匹配的货品，请检查");
		return;
	}
	
	
	pdtObj.each(function(){
		var pdtId = $(this).find("input[json='PRD_ID']").attr("value");
		if(strPdtId==pdtId){
			 $(this).find("input[json='SN']").val(scanSN);
			 var NOTICE_NUM = $(this).find("input[json='NOTICE_NUM']").val();//通知数量
			 var REAL_NUM_EL = $(this).find("input[json='REAL_NUM']");
			 var REAL_NUM = REAL_NUM_EL.val();//扫码数量
			 if(null == REAL_NUM || "" == REAL_NUM){
				 REAL_NUM = 0;
			 }
			  
			 if(parseInt(NOTICE_NUM) == parseInt(REAL_NUM)){
				 smNum = parseInt(smNum)+1;
				 if(num>1){
					   if(num == smNum){
						  parent.showErrorMsg("该货品扫码完毕");
						  return false;
					   }else{
						   return ;
					   }
				   }else{
					    parent.showErrorMsg("该货品扫码完毕");
				        return false;
				  }
			  
		     }
	 
			 REAL_NUM = parseInt(REAL_NUM) + 1;
			 REAL_NUM_EL.val(REAL_NUM);
			 $(this).find("input:checkbox").attr("checked","true");
			 return false;
		}
		
		 
	});
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
	var functionText = "setRealNum('"+rownum+"');";
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREOUT_DTL_ID' name='STOREOUT_DTL_ID"+rownum+"' id='STOREOUT_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="货品序列号" type="text"   maxlength="50" size="12" onkeyup="'+functionText+'"   value="'+arrs[2]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" inputtype="string" value="'+arrs[3]+'"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text" mustinput="true" READONLY inputtype="string" maxlength="30" size="12" value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" mustinput="true" type="text" READONLY inputtype="string" maxlength="100" value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" mustinput="true" type="text" READONLY inputtype="string" maxlength="50" size="10" value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input type="text" json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" READONLY  autocheck="true" inputtype="string" label="花号" maxlength="50" size="6"  value="'+arrs[7]+'"size="15" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" inputtype="string" label="品牌"   size="5" type="text" READONLY  mustinput="true"  READONLY   value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" inputtype="string" label="标准单位"    type="text"  mustinput="true"   READONLY size="6"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'"  autocheck="true" label="通知出库数量"  type="text" READONLY inputtype="string" maxlength="11" size="5" value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'"  autocheck="true" label="扫码数量"  type="text"  inputtype="int"   maxlength="11" size="5" value="'+arrs[11]+'"/>&nbsp;</td>')
            ;
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
}


function setRealNum(rownum){
	var SN = $("#SN"+rownum);
	var SNVal = $.trim(SN.val());
	if(null != SNVal && "" != SNVal){
		SN.parent().parent().find("input[json='REAL_NUM']").val(1);
	}
}


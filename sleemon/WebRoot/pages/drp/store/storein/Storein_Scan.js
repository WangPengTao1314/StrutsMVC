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
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}else{
		var flag = true;
		checkBox.each(function(){
			var REAL_NUM = $(this).parent().parent().find("input[json='REAL_NUM']").val();
			if(0 == REAL_NUM || "0" == REAL_NUM){
				parent.showErrorMsg("'扫码数量'应该大于0");
				flag = false;
				return false;
			}
		});
		
		if(!flag){
			return;
		}
		 
	}
	
	var STOREIN_ID=document.getElementById("STOREIN_ID").value;
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storein.shtml?action=scanChildEdit&STOREIN_ID="+STOREIN_ID,
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
	 	url: "storein.shtml?action=editScanList&SN="+scanSN,
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
	var noNum = 0;
	pdtObj.each(function(){
		var pdtId = $(this).find("input[json='PRD_ID']").attr("value");
		if(strPdtId == pdtId){
			num = parseInt(num)+1;
		}else{
			noNum = parseInt(noNum)+1;
		}
	});
	
	if(noNum == pdtObj.length){
		parent.showErrorMsg("没有匹配的货品，请检查");
		return;
	}
	
	pdtObj.each(function(){
		var pdtId = $(this).find("input[json='PRD_ID']").attr("value");
		if(strPdtId == pdtId){
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

//获取单据类型
function getBillType(){
	var billType = window.opener.getBillType();
	return billType;
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
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input   value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	var billType = getBillType();
	var readOnlyFlag = "READONLY";
	//modify zzb 2014-7-21 改为所有入库都可以手输数量
	//if("终端退货" == billType){
		readOnlyFlag = "";
	//}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREIN_DTL_ID' name='STOREIN_DTL_ID"+rownum+"' id='STOREIN_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left" style="display: none;" ><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="货品序列号"  type="text" inputtype="string" maxlength="50" size="12"  value="'+arrs[2]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" inputtype="string" value="'+arrs[3]+'"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text" mustinput="true" READONLY inputtype="string" maxlength="30" size="12" value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" mustinput="true" type="text" READONLY inputtype="string" maxlength="100" value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" mustinput="true" type="text" READONLY inputtype="string" maxlength="50" size="10" value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input type="text" json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" READONLY   autocheck="true" inputtype="string" label="花号" maxlength="50" size="6"  value="'+arrs[7]+'"size="15" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" inputtype="string" label="品牌"  size="5"  type="text" READONLY  mustinput="true"  READONLY   value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" inputtype="string" label="标准单位"    type="text"  mustinput="true"   READONLY size="6"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'"  autocheck="true" label="通知入库数量"  type="text" READONLY inputtype="string" maxlength="11" size="5" value="'+arrs[10]+'"/>&nbsp;</td>')
//            .append('<td nowrap align="left"><input  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'"  autocheck="true" label="扫码数量"  type="text" READONLY inputtype="string" maxlength="11" size="5" value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'"  autocheck="true"  inputtype="float" mustinput="true" label="扫码数量"  type="text" '+readOnlyFlag+'   maxlength="11" size="5" value="'+arrs[11]+'"/>&nbsp;</td>')
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
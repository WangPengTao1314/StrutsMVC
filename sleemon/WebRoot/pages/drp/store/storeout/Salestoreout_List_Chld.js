/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List_Chld
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
	$("#add").click(function(){
	    addRow();
	});
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
		var billType=parent.window.document.getElementById("BILL_TYPE").value;
        var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#STOREOUT_DTL_IDS").val(ids);
		$("#form1").attr("action","storeout.shtml?action=toChildEdit&BILL_TYPE="+billType);
		$("#form1").submit();
	});
	
	$("#scan").click(function(){
		var billType=parent.window.document.getElementById("BILL_TYPE").value;
		var storeoutId=document.getElementById("STOREOUT_ID").value;
		window.open('storeout.shtml?action=toEditScanDtl&STOREOUT_ID='+storeoutId+'&BILL_TYPE='+billType,'货品扫码明细','height=400, width=1100, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
	
	setSelOperateEx();
});
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
		url: "storeout.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREOUT_DTL_IDS":ids},
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


function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (null == state || "" == state || state == "提交"||state == "审核通过"||"已取消"==state||"已处理"==state) {
			btnDisable(["delete","edit","scan"]);
		}
	} 
}

function showStoge(STOREOUT_DTL_ID){
	var billType=parent.window.document.getElementById("BILL_TYPE").value;
	var STOREOUT_DTL_IDS = "'"+STOREOUT_DTL_ID+"'";
	var STOREOUT_ID = parent.document.getElementById("selRowId").value;
	window.showModalDialog('storeout.shtml?action=storgChildList&STOREOUT_DTL_IDS='+ STOREOUT_DTL_IDS+"&BILL_TYPE="+billType+"&STOREOUT_ID="+STOREOUT_ID,window,"dialogWidth=900px;dialogHeight=500px;depended=no");
	window.location.reload();  
}


function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '','', '', '', '', '', '', '', '', '', '', '', '', '','','',''
              
	        ];
		}
	//样式行
	var rownum = $("#ordertb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#ordertb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#ordertb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREOUT_DTL_ID' name='STOREOUT_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" type="hidden" value="'+arrs[2]+'"/>&nbsp;<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'" label="货品编号" readonly type="text" value="'+arrs[3]+'"/>&nbsp;&nbsp;<img align="absmiddle" name="selcomm" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selProduct('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'" label="货品序列号"  type="text" readonly value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'" label="货品名称"  type="text" readonly value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'" label="规格型号"  type="text" readonly value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" label="花号"  type="text" readonly value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'" label="品牌"  type="text" readonly value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'" label="标准单位"  type="text" readonly value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'" label="通知出库数量"  type="text" readonly value="'+arrs[9]+'"/>&nbsp;</td>')
 			.append('<td nowrap align="left"><input class="REAL_NUM"  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'" label="实际出库数量"  type="text" inputtype="int"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  label="单价" type="text" readonly value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'"  label="折扣" type="text" readonly value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  label="折扣单价" type="text" readonly value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT'+rownum+'"  label="折后金额" type="text" readonly value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  label="备注" type="text" readonly value="'+arrs[14]+'"/>&nbsp;</td>')
            ;
			  
    $("#ordertb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#ordertb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#ordertb tr:gt("+(rownum-1)+") select"));
	var STORAGE_FLAG = parent.document.getElementById("STORAGE_FLAG").value;
	if (1 == STORAGE_FLAG) {
		$(".REAL_NUM").attr('readonly','readonly'); 
		$(".REAL_NUM").css('color','blue'); 
		$(".REAL_NUM").css('text-decoration','underline'); 
		$(".REAL_NUM").css('text-decoration','underline'); 
		$(".REAL_NUM").css('text-align','right'); 
		$("#REAL_NUM"+rownum).click(function(){
			editGrendChild(arrs[0],arrs[1]);
		});
	}
}

function selProduct(rownum){
	selCommon("BS_21", false, "PRD_ID"+rowNum, "PRD_ID", "forms[0]",
			"PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum+",PRD_COLOR"+rowNum+",BRAND"+rowNum+",STD_UNIT"+rowNum, 
			"PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT","selectCondition");
}

function url(SPCL_TECH_ID){
//	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

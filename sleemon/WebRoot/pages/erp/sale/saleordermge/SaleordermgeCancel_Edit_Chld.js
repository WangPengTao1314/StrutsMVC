$(function() {
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
              '',
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var SPCL_TECH_FLAG;
	if(arrs[10]==null||arrs[10]==""||arrs[10]==0){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'   value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
    var noPlanNum =  arrs[14]-arrs[18]
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='SALE_ORDER_DTL_ID' name='SALE_ORDER_DTL_ID"+rownum+"' id='SALE_ORDER_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" inputtype="string" value="'+arrs[2]+'"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text" mustinput="true" READONLY inputtype="string" maxlength="30" size="12" value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" mustinput="true" type="text" READONLY inputtype="string" maxlength="100" value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text" READONLY inputtype="string" maxlength="50" size="10" value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input type="text" json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" READONLY   inputtype="string" label="花号" maxlength="50" size="6"  value="'+arrs[6]+'"size="15" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" inputtype="string" label="品牌"    type="text" READONLY  mustinput="true"  size="5"   value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" inputtype="string" label="标准单位"    type="text"  mustinput="true"   READONLY size="6"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  autocheck="true" label="单价"  type="text" READONLY inputtype="string" maxlength="11" size="5" value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'"  autocheck="true" label="折扣率"  type="text" READONLY inputtype="string" maxlength="11" size="5" READONLY value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  autocheck="true" label="折后单价"  type="text"   maxlength="11" size="5" READONLY value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM'+rownum+'"  autocheck="true" label="订货数量"  type="text"   inputtype="int" maxlength="11" size="5" READONLY value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NO_PLANED_NUM" id="NO_PLANED_NUM'+rownum+'" name="NO_PLANED_NUM'+rownum+'"  autocheck="true" label="未排数量"  type="text"   inputtype="int" maxlength="11" size="5" READONLY value="'+noPlanNum+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CANCEL_NUM" id="CANCEL_NUM'+rownum+'" name="CANCEL_NUM'+rownum+'"  autocheck="true" label="取消数量"  type="text"   inputtype="int" maxlength="11" size="5"  onblur="checkCancelNum('+rownum+')" mustinput="true" value=""/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[9]+'"/>')
           	.append('<input  json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID"  label="来源单据ID" type="hidden" value="'+arrs[15]+'"/>')
           	.append('<input  json="OLD_ORDER_NUM" id="OLD_ORDER_NUM'+rownum+'" name="OLD_ORDER_NUM"  label="原订货数量" type="hidden" value="'+arrs[17]+'"/>')
           	.append('<input  json="REBATE_PRICE" id="REBATE_PRICE'+rownum+'" name="REBATE_PRICE"  label="返利单价" type="hidden" value="'+arrs[16]+'"/>')
           	;
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
}

function checkCancelNum(rowNum){
	var CANCEL_NUM = parseFloat($("#CANCEL_NUM"+rowNum).val());
	var ORDER_NUM = parseFloat($("#NO_PLANED_NUM"+rowNum).val()); 
	if(CANCEL_NUM>ORDER_NUM){
		alert('取消数量已超过未排车数量,请输新输入!');
		$("#CANCEL_NUM"+rowNum).val('0');
		return;
	}
}

//子表保存
function childSave(){
    //查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条明细")
		return;
	}
	if(!formMutiTrChecked()){
		return;
	}

	var jsonData = multiPackJsonData();
    var SALE_ORDER_ID = document.getElementById("SALE_ORDER_ID").value;
	var ids = "";
	
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var formIds = "";
	$("input[name='FROM_BILL_DTL_ID']").each(function(){
    		if(""!=$(this).val()&&null!=$(this).val()){
				formIds = formIds+"'"+$(this).val()+"',";
    		}
    	});
	
	formIds = formIds.substr(0,formIds.length-1);
	var jsonData = multiPackJsonData();
	var BILL_TYPE = $("#BILL_TYPE").val();
	$.ajax({
		url: "saleorderrls.shtml?action=cancelOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids,"FROM_BILL_DTL_IDS":formIds,"SALE_ORDER_ID":SALE_ORDER_ID,"JsonData":jsonData,"BILL_TYPE":BILL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				$("#YT_MSG_BTN_OK").click(function(){
					window.opener.parent.window.gotoBottomPage("label_1");
					window.opener.parent.topcontent.$("#pageForm").submit();
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


function url(rownum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
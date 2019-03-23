/**
 * @prjName:喜临门营销平台
 * @fileName:Payment_List
 * @author glw
 * @time   2013-08-15 09:31:13 
 * @version 1.1
 */
$(function(){
	InitFormValidator(0);
	$("#save").click(function(){
		$("#jsontb :input[json='REAL_SEND_NUM']").each(function(){
			if($(this).val()>0){
				$(this).parent().parent().find("input[type='checkbox']").attr("checked","checked");
			}
		});
		childSave();
	});	
});
function childSave() {
	var deliverOrderNo = $("#DELIVER_ORDER_NO").val();
	var jsonData = multiPackJsonData();
	$.ajax({
		url : ctxPath+"/deliverconfm.shtml?action=handlePdtDeliver",
		type : "POST",
		dataType : "text",
		data : {"DELIVER_ORDER_NO" : deliverOrderNo,"jsonData" : jsonData},
		complete : function(xhr) {
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("保存成功");
				window.close();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}


function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='TEST' name='TEST"+rownum+"' value=''/></td>")
            .append('<td nowrap align="left"><input  json="SALE_ORDER_NO" id="SALE_ORDER_NO'+rownum+'" name="SALE_ORDER_NO'+rownum+'"  autocheck="true" label="销售订单编号"  type="text"   inputtype="string"            maxlength="100"  value="'+arrs[0]+'"/>&nbsp;</td>')
             .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string"        maxlength="50"  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REAL_SEND_NUM" id="REAL_SEND_NUM'+rownum+'" name="REAL_SEND_NUM'+rownum+'"  autocheck="true" label="实发数量"  type="text"   inputtype="string"        maxlength="50"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="扫码"  type="text"   inputtype="string"     maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='radio']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	
	//form校验设置
	InitFormValidator(0);
}
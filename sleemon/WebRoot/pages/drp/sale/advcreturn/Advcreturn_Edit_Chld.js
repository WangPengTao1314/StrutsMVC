

/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //form校验设置
	InitFormValidator(0);
	//点击关闭按钮关闭页面
	$("#close").click(function () {
		window.close();
	});
	//查看库存
	$("#select").click(function () {
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		window.open('advctoout.shtml?action=toListPro&PRD_IDS='+ids,'库存','height=200, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	});
	
	
	$("#create").click(function(){
			childSave();
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
	var STORE_ID=$("#STORE_ID").val();
	var STORE_NO=$("#STORE_NO").val();
	var STORE_NAME=$("#STORE_NAME").val();
//	var STORAGE_FLAG=$("#STORAGE_FLAG").val();
	if((STORE_NO==""||STORE_NO==null)&&(STORE_NAME==""||STORE_NAME==null)){
		alert("请选择库房信息");
		return ;
	}
	var ADVC_ORDER_ID=$("#ADVC_ORDER_ID").val();
	$.ajax({
		url: "advcreturn.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_ID":ADVC_ORDER_ID,"STORE_ID":STORE_ID,"STORE_NO":STORE_NO,"STORE_NAME":STORE_NAME},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("提交成功");
				window.opener.parent.topcontent.listRef();
				window.close();				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
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
              '',
              '',
              '',
              '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(arrs[7]==null||arrs[7]==""||arrs[7]==0){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input   value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='PRD_ID' json='PRD_ID' name='PRD_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input   name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;')
            .append('<td nowrap align="left"><input   name="PRD_NAME"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input   name="PRD_SPEC"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left">'+SPCL_TECH_FLAG+'&nbsp;</td>')
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID"  label="实发货品id"  type="hidden"    value="'+arrs[0]+'"/>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="实发货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;'
            	+"<img  align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="实发货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="实发规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR"  autocheck="true" label="实发花号"  type="text"   inputtype="string"   readonly         maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" label="实发品牌"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT"  autocheck="true" label="实发标准单位"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="出厂单价"  type="text"      readonly       value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="实发折扣率"   type="text"   inputtype="float"   readonly  mustinput="true"   valueType="2,2"   value="'+arrs[9]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="实发单价"  type="text" mustinput="true"     inputtype="float"   readonly   valueType="8,2"   value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="实发数量" readonly type="text" mustinput="true"    inputtype="float"   maxlength="22"  value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" name="PAYABLE_AMOUNT"  autocheck="true" label="实发金额"  type="text" mustinput="true"    readonly inputtype="float"      valueType="12,2"   value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<input  json="ADVC_ORDER_DTL_ID" id="ADVC_ORDER_DTL_ID'+rownum+'" name="ADVC_ORDER_DTL_ID"  label="预订单明细ID" type="hidden" value="'+arrs[14]+'"/>')
              ;
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}


//货品通用选取
    function selcPrd(rownum){
	     var obj=selCommon("BS_53", false, "ADVC_ORDER_DTL_ID"+rownum, "ADVC_ORDER_DTL_ID", "forms[1]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",ORDER_NUM"+rownum+",PAYABLE_AMOUNT"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,PAYABLE_AMOUNT", "selectParam");
	}
	function url(i){
		var ADVC_ORDER_DTL_ID=$("#ADVC_ORDER_DTL_ID"+i).val();
		window.open('advctoout.shtml?action=toEditSpecialTech&ADVC_ORDER_DTL_ID='+ADVC_ORDER_DTL_ID,'特殊工艺单维护','height=400, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

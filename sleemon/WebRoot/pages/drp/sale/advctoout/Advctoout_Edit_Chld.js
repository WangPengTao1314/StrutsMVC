

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
	$("#create").click(function(){
			var tab=true;
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
				}
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				return;
			}
			checkBox.each(function (){
				if(tab){
					var ORDER_NUM=$(this).parent().parent().find("input[name='ORDER_NUM']").val();
					if(ORDER_NUM==0||ORDER_NUM<0){
						alert("所选货品信息存在不合法数量，请重新选择货品");
						tab=false;
						return ;
					}
				}
				
			});
			if(tab){
				childSave();
			}
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
	var STOREOUT_STORE_NO=$("#STOREOUT_STORE_NO").val();
	var STOREOUT_STORE_NAME=$("#STOREOUT_STORE_NAME").val();
	if((STOREOUT_STORE_NO==""||STOREOUT_STORE_NO==null)&&(STOREOUT_STORE_NAME==""||STOREOUT_STORE_NAME==null)){
		alert("请选择库房信息");
		return ;
	}
	var ADVC_ORDER_ID=$("#ADVC_ORDER_ID").val();
	//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	var REMARK=$("#REMARK").val();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "advctoout.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_ID":ADVC_ORDER_ID,"STOREOUT_STORE_NO":STOREOUT_STORE_NO,"STOREOUT_STORE_NAME":STOREOUT_STORE_NAME,"REMARK":REMARK,"childJsonData":jsonData},
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
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='PRD_ID"+rownum+"' json='PRD_ID' name='PRD_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input   json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" style="width:80px" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称" style="width:80px"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input   json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号" style="width:80px"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR" style="width:50px"  autocheck="true" label="花号"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" style="width:50px" label="品牌"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT" style="width:50px"  autocheck="true" label="标准单位"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" style="width:50px" label="零售单价"  type="text"      readonly       value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE" style="width:50px"  autocheck="true" label="折扣率"   type="text"   inputtype="float"   readonly  mustinput="true"   valueType="2,2"   value="'+arrs[9]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE" style="width:50px" autocheck="true" label="折后单价"  type="text" mustinput="true"     inputtype="float"   readonly   valueType="8,2"   value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  id="RES_NUM'+rownum+'" name="RES_NUM'+rownum+'" style="width:50px" autocheck="true"  label="订货数量"  readonly type="text"  mustinput="true"    inputtype="float"   maxlength="22"  value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NUM" id="NUM'+rownum+'" name="NUM"  autocheck="true" style="width:50px" label="可用库存" readonly type="text" mustinput="true"    inputtype="float"   maxlength="22"  value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SEND_NUM" id="SEND_NUM'+rownum+'" name="SEND_NUM" style="width:50px" autocheck="true" label="已发数量" readonly type="text" mustinput="true"    inputtype="float"   maxlength="22"  value="'+arrs[13]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM" style="width:50px" autocheck="true" label="实发数量" type="text" mustinput="true" onkeyup="countPAYABLE_AMOUNT('+rownum+')"    inputtype="float"   maxlength="22"  value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" style="width:80px" name="PAYABLE_AMOUNT"  autocheck="true" label="实发金额"  type="text" mustinput="true"    readonly inputtype="float"      valueType="12,2"   value="'+arrs[14]+'" />&nbsp;</td>')
            //.append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK" style="width:50px" autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<input  json="ADVC_ORDER_DTL_ID" id="ADVC_ORDER_DTL_ID'+rownum+'" name="ADVC_ORDER_DTL_ID"  label="预订单明细ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[17]+'"/>')
              ;
	countPAYABLE_AMOUNT(rownum);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
	function url(rownum){
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var data=window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	}
	//当键盘抬起时根据订货实发数量计算实发金额
	function countPAYABLE_AMOUNT(i){
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var temp_ORDER_NUM=$("#ORDER_NUM"+i).val();//订货数量
		var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//订货数量
		var PAYABLE_AMOUNT = Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
		$("#PAYABLE_AMOUNT"+i).val(PAYABLE_AMOUNT);
	}

/**
 * @prjName:喜临门营销平台
 * @fileName:Storediffdeal_Edit_Chld
 * @author wzg
 * @time   2013-08-30 14:17:25 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
	    addRow();
	});
	
	$("body").dblclick(function(){
	    addRow();
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
		if("" == ids){
			checkBox.parent().parent().remove();
		}else{
			parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	$("#save").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if("list" == actionType){
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
			childSave();
		}else{
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			allSave();
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

//主子表保存
function allSave(){
	
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "storediffaffdeal.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STOREIN_DIFF_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","storediffaffdeal.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){

	var selRowId = getSelRowId();
	var STOREIN_DIFF_DTL_ID = $("#STOREIN_DIFF_DTL_ID",parent.window.document).val();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storediffaff.shtml?action=dealEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREIN_DIFF_ID":selRowId,"STOREIN_DIFF_DTL_ID":STOREIN_DIFF_DTL_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_2");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "storediffaffdeal.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DIFF_DEAL_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("storediffaff.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
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
              $("#PRD_ID").val(),
              $("#PRD_NO").val(),
              $("#PRD_NAME").val(),
              $("#PRD_SPEC").val(),
              $("#PRD_COLOR").val(),
              $("#BRAND").val(),
              $("#STD_UNIT").val(),
              $("#PRICE").val(),
              $("#DECT_RATE").val(),
              $("#DECT_PRICE").val(),
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
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='DIFF_DEAL_DTL_ID' name='DIFF_DEAL_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" value="'+arrs[1]+'"/><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  autocheck="true" label="单价"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'"  autocheck="true" label="折扣率"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  autocheck="true" label="折后单价"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DIFF_NUM" id="DIFF_NUM'+rownum+'" name="DIFF_NUM'+rownum+'"  autocheck="true" label="差异数量"  type="text" onblur="checkNum('+rownum+')"  inputtype="string"     mustinput="true"     maxlength="11"  value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><select  json="DIFF_RSON" id="DIFF_RSON'+rownum+'" name="DIFF_RSON'+rownum+'"  autocheck="true" label="差异原因"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="'+arrs[12]+'"></select></td>')
            .append('<td nowrap align="left"><select  json="DEAL_WAY" id="DEAL_WAY'+rownum+'" name="DEAL_WAY'+rownum+'"  autocheck="true" label="处理方式"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="'+arrs[13]+'"></select></td>')
            .append('<td nowrap align="left"><input  json="DIFF_AMOUNT" id="DIFF_AMOUNT'+rownum+'" name="DIFF_AMOUNT'+rownum+'"  autocheck="true" label="差异金额"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="22"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DIFF_ATT" id="DIFF_ATT'+rownum+'" name="DIFF_ATT'+rownum+'"  autocheck="true" label="差异附件"  type="text"   inputtype="string"     mustinput="true"     maxlength="100"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[16]+'"/>&nbsp;</td>')
            ;
	
	SelDictShow("DIFF_RSON"+rownum,"BS_30",arrs[12],"");
	SelDictShow("DEAL_WAY"+rownum,"BS_31",arrs[13],"");
	uploadFile('DIFF_ATT'+rownum,'SAMPLE_DIR',true);
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

function checkNum(rowNum){
	var diffNum = parseFloat($("#DIFF_NUM"+rowNum).val());
	if("NaN" == diffNum.toString()){
		$("#DIFF_NUM"+rowNum).val("");
		$("#DIFF_AMOUNT"+rowNum).val("");
		showErrorMsg("请输入正确的差异数量!");
		return;
	}
	var dectPrice = parseFloat($("#DECT_PRICE"+rowNum).val())
	if("NaN" == dectPrice.toString()){
		showErrorMsg("折后单价不正确!");
		return;
	}
	var diffAmount = diffNum * dectPrice;
	$("#DIFF_AMOUNT"+rowNum).val(diffAmount);
}

function formCheckedEx() {

	var flag = true;
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){

			$(this).find("select").each(function(index,select){
				
				if('' == $(select).val() ){
					if(index/2 == 0){
						showErrorMsg("请选择'差异原因'!");
					}else{
						showErrorMsg("请选择'处理方式'!");
					}
				
				flag = false;
				return flag;
				}
			});
		}
	});
	return flag;
}


/**
 * @prjName:喜临门营销平台
 * @fileName:Techorder_Edit_Chld
 * @author lyg
 * @time   2013-10-12 17:37:51 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#save").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input[name='TECH_ORDER_DTL_ID']");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				return;
			}
			childSave();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb  input[name='TECH_ORDER_DTL_ID']").attr("checked","true");
		}else{
			$("#jsontb input[name='TECH_ORDER_DTL_ID']").removeAttr("checked");
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
	if($("#jsontb tr:gt(0) input[name='TECH_ORDER_DTL_ID']").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "techorderprice.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"TECH_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","techorderprice.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "techorderprice.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"TECH_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input[name='TECH_ORDER_DTL_ID']");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "techorderprice.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"TECH_ORDER_DTL_IDS":ids},
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
   newGoBack("techorderprice.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[name='TECH_ORDER_DTL_ID']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0)").find("input[name='TECH_ORDER_DTL_ID']").attr("checked","true");
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
	var SPCL_TECH_FLAG;
	if(arrs[7]==null||arrs[7]==""||arrs[7]==0){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input   value='查看' onclick='url("+rownum+")' class='btn'  type='button' />"
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='TECH_ORDER_DTL_ID' id='TECH_ORDER_DTL_ID"+rownum+"' name='TECH_ORDER_DTL_ID' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号" size="10" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" size="13" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" size="13" type="text"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  size="13" type="text"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text" size="5"  inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="5" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="center"><input  json="IS_CAN_PRD_FLAG" id="IS_CAN_PRD_FLAG'+rownum+'" name="IS_CAN_PRD_FLAG"  label="是否可生产" size="13" disabled="disabled"    type="checkbox" value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  autocheck="true" label="单价"  type="text" size="11"   inputtype="string"   readonly       maxlength="22"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<input  json="NEW_PRD_ID" id="NEW_PRD_ID'+rownum+'" name="NEW_PRD_ID'+rownum+'"  autocheck="true" label="新货品ID" type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[10]+'"/>')
            .append('<td nowrap align="left"><input  json="NEW_PRD_NO" id="NEW_PRD_NO'+rownum+'" name="NEW_PRD_NO'+rownum+'"  autocheck="true" label="新货品编号"  size="10" type="text"   inputtype="string"   readonly       maxlength="30"  value="'+arrs[11]+'"/></td>')
            .append('<td nowrap align="left"><input  json="NEW_PRD_NAME" id="NEW_PRD_NAME'+rownum+'" name="NEW_PRD_NAME'+rownum+'"    label="新货品名称" size="13" type="text"   inputtype="string"   readonly       maxlength="100"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NEW_PRD_SPEC" id="NEW_PRD_SPEC'+rownum+'" name="NEW_PRD_SPEC'+rownum+'"  autocheck="true" label="新规格型号" size="13" type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE_DECIDE" id="PRICE_DECIDE'+rownum+'" name="PRICE_DECIDE"  autocheck="true" label="核价价格"  size="11" type="text"    inputtype="float"      valueType="8,2"       value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"         maxlength="250"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[16]+'"/>')
              ;
	if(arrs[8]==0){
		$("#PRICE_DECIDE"+rownum).attr("readonly","readonly");
		$("#REMARK"+rownum).attr("readonly","readonly");
	}
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[name='TECH_ORDER_DTL_ID']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	defCheck(rownum);
}
function url(rownum){
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var PRICE=$("#PRICE"+rownum).val();
//		window.open('techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
		window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
	}
//货品通用选取
    function selcPrd(rownum){
	     var obj=selCommon("BS_21", false, "NEW_PRD_ID"+rownum, "PRD_ID", "forms[0]","NEW_PRD_ID"+rownum+",NEW_PRD_NO"+rownum+",NEW_PRD_NAME"+rownum+",NEW_PRD_SPEC"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC", "selectParam");
	     rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC",rownum);
	}
function defCheck(id){
	var IS_CAN_PRD_FLAG=$("#IS_CAN_PRD_FLAG"+id).val();
	if(IS_CAN_PRD_FLAG=="1"){
		$("#IS_CAN_PRD_FLAG"+id).attr("checked","true");
		$("#IS_CAN_PRD_FLAG"+id).val("1");
	}else{
		$("#IS_CAN_PRD_FLAG"+id).removeAttr("checked");
		$("#IS_CAN_PRD_FLAG"+id).val("0");
	}
}
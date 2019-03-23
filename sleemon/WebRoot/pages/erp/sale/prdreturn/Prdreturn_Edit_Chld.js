
/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit_Chld
 * @author wzg
 * @time   2013-08-19 15:33:31 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
//	$("#add").click(function(){
//	    addRow();
//	});
//	
//	$("body").dblclick(function(){
//	    addRow();
//	});
	
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
		url: "prdreturn.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"PRD_RETURN_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","prdreturn.shtml?action=toFrame");
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
		url: "prdreturn.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"PRD_RETURN_ID":selRowId},
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
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "prdreturn.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"PRD_RETURN_DTL_IDS":ids},
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
   newGoBack("prdreturn.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	 
	var BILL_TYPE = gerType();
	 
	if('手工新增' == BILL_TYPE){
		$("#add").click(function(){
		    addRow();
		});
		
		$("body").dblclick(function(){
		    addRow();
		});
	}else{
		btnHidden(['add']);
//		$("input[json=RETURN_PRICE]").each(function(index,ipt){
//			$(ipt).attr("readonly","readonly");
//		});
//		$("input[json=RETURN_AMOUNT]").each(function(index,ipt){
//			$(ipt).attr("readonly","readonly");
//		});
//		
//		$("input[json=RETURN_RSON]").each(function(index,ipt){
//			$(ipt).attr("readonly","readonly");
//		});
//		
//		$("img[name=selcomm]").each(function(index,ipt){
//			$(ipt).attr("disabled","true");
//		});
//		
//		//todo
//		$("div[name=select]").each(function(index,ipt){
//			$(ipt).attr("readonly ","readonly");
//		});
//		
//		$("input[json=RETURN_ATT]").each(function(index,ipt){
//			$(ipt).attr("readonly","readonly");
//		});
		
	}
		
	
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

//获取上帧 页面的 类型
function gerType(){
	var actionType = parent.document.getElementById("actionType").value;
	var BILL_TYPE = "";
	if("edit" == actionType){
		BILL_TYPE = parent.topcontent.document.getElementById("BILL_TYPE").value;		
	}else if ( "list"== actionType ){
		BILL_TYPE = parent.document.getElementById("BILL_TYPE").value;		
	}
	return BILL_TYPE;
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var type = "";
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_RETURN_DTL_ID' name='PRD_RETURN_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left" style="display:none;"><input type="text" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/></td>')
            .append('<td nowrap align="left"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="货品序列号"  type="text"   inputtype="string"  readonly      maxlength="100"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;<img align="absmiddle" name="selcomm" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selProduct('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"  readonly     maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"  mustinput="true"  readonly    maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
  			.append('<td nowrap align="center"><div id="SPECIAL_FLAG_SCRN'+rownum+'"></div><input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  autocheck="true" label="特殊工艺ID"  type="hidden" value="'+arrs[15]+'"/><input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  autocheck="true" label="特殊工艺标记"  type="hidden" value="'+arrs[16]+'"/></td>')           
            .append('<td nowrap align="left"><input  json="RETURN_PRICE" id="RETURN_PRICE'+rownum+'" name="RETURN_PRICE'+rownum+'"  autocheck="true" label="退货单价"  onblur="checkPrice('+rownum+')" type="text"   inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ADVC_RETURN_PRICE" id="ADVC_RETURN_PRICE'+rownum+'" name="ADVC_RETURN_PRICE'+rownum+'"  autocheck="true" label="建议退货单价"   type="text"   inputtype="float" valueType="8,2"    value="'+arrs[17]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_NUM" id="RETURN_NUM'+rownum+'" name="RETURN_NUM'+rownum+'"  autocheck="true" label="退货数量"  type="text"  onblur="checkNum('+rownum+')"  inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_AMOUNT" id="RETURN_AMOUNT'+rownum+'" name="RETURN_AMOUNT'+rownum+'"  autocheck="true" label="退货金额"  type="text" onblur="checkAmount('+rownum+')"  inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><select json="RETURN_RSON_TYPE" id="RETURN_RSON_TYPE'+rownum+'" name="RETURN_RSON_TYPE'+rownum+'"  autocheck="true" label="原因归类"  inputtype="string"     mustinput="true"></select></td>')
            .append('<td nowrap align="left"><input  json="RETURN_RSON" id="RETURN_RSON'+rownum+'" name="RETURN_RSON'+rownum+'"  autocheck="true"  label="退货原因"  type="text"   inputtype="string"     mustinput="true"     maxlength="250"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_ATT" id="RETURN_ATT'+rownum+'" name="RETURN_ATT'+rownum+'"  autocheck="true" label="退货附件"  type="text"   inputtype="string"      maxlength="100"  value="'+arrs[13]+'"/>&nbsp;</td>')
              ;
		SelDictShow("RETURN_RSON_TYPE"+rownum,"BS_12",arrs[11],"");
		uploadFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
    setSpecTech(rownum);
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}

function addRow2(arrs){
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_RETURN_DTL_ID' name='PRD_RETURN_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left" style="display:none;"><input type="text" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/></td>')
             .append('<td nowrap align="left"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="货品序列号"  type="text"   inputtype="string"  readonly       maxlength="100"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;<img align="absmiddle" name="selcomm" disabled="disabled" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selProduct('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"  readonly      maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"  mustinput="true"  readonly    maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
 			.append('<td nowrap align="left"><span id="SPECIAL_FLAG_SCRN'+rownum+'"></span><input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"   label="特殊工艺ID"  type="hidden" value="'+arrs[15]+'"/><input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  autocheck="true" label="特殊工艺标记"  type="hidden" value="'+arrs[16]+'"/></td>')           
            .append('<td nowrap align="left"><input  json="RETURN_PRICE" id="RETURN_PRICE'+rownum+'" name="RETURN_PRICE'+rownum+'"  autocheck="true" label="退货单价"  onblur="checkPrice('+rownum+')" type="text" readonly  inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ADVC_RETURN_PRICE" id="ADVC_RETURN_PRICE'+rownum+'" name="ADVC_RETURN_PRICE'+rownum+'"  autocheck="true" label="建议退货单价"   type="text"   inputtype="float" valueType="8,2"    value="'+arrs[17]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_NUM" id="RETURN_NUM'+rownum+'" name="RETURN_NUM'+rownum+'"  autocheck="true" label="退货数量"  type="text"  onblur="checkNum('+rownum+')"  inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_AMOUNT" id="RETURN_AMOUNT'+rownum+'" name="RETURN_AMOUNT'+rownum+'"  autocheck="true" label="退货金额"  type="text" onblur="checkAmount('+rownum+')"  readonly inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[10]+'"/>&nbsp;</td>')
            //0157336--start--刘曰刚--2014-06-23//
            //去掉退货原因必填字段，如果是新增单据，有必填校验，如果是别的单据生成过来的 则去掉必填校验
            .append('<td nowrap align="left"><input json="RETURN_RSON_TYPE" id="RETURN_RSON_TYPE'+rownum+'" name="RETURN_RSON_TYPE'+rownum+'"  autocheck="true" label="原因归类"  type="text"   inputtype="string" readonly        maxlength="30"  value="'+arrs[11]+'"/></input></td>')
             //0157336--End--刘曰刚--2014-06-23//
            .append('<td nowrap align="left"><input  json="RETURN_RSON" id="RETURN_RSON'+rownum+'" name="RETURN_RSON'+rownum+'"  autocheck="true" label="退货原因"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="250"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_ATT" id="RETURN_ATT'+rownum+'"  name="RETURN_ATT'+rownum+'"  autocheck="true" label="退货附件"  type="hidden"   inputtype="string"        maxlength="100"  value="'+arrs[13]+'"/>&nbsp;</td>')
              ;
		//SelDictShow("RETURN_RSON_TYPE"+rownum,"BS_12",arrs[11],"");
		setSpecTech(rownum);
		//uploadFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true);
		displayDownFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true,false);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}
function formCheckedEx() {
	
	var flag = true;
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
			var select = $(this).find("select:first");
			if(select.length != 0){
				if('' == select[0].value){
				chkCanErrMsg("", "请选择'原因归类'!");
				flag = false;
				}
			}	
		}
	});
	return flag;
}
//货品通用选取
function selProduct(rowNum){
	selCommon("BS_21", false, "PRD_ID"+rowNum, "PRD_ID", "forms[0]",
			"PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum+",PRD_COLOR"+rowNum+",BRAND"+rowNum+",STD_UNIT"+rowNum, 
			"PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT","selectCondition");
	setSpecTech(rowNum);

}

function checkPrice(rowNum)
{
	var price = parseFloat($("#RETURN_PRICE"+rowNum).val());
	var num = parseFloat($("#RETURN_NUM"+rowNum).val()); 
	if("NaN" == price.toString()){
		$("#RETURN_PRICE"+rowNum).val("");
		$("#RETURN_AMOUNT"+rowNum).val("");
		chkCanErrMsg("", "请输入正确的退货单价");
		return;
	}
	if("NaN" == num.toString()){
		return;
	}
	
	var amount  = price * num;
	$("#RETURN_AMOUNT"+rowNum).val(amount);
}
function checkNum(rowNum)
{
	var price = parseFloat($("#RETURN_PRICE"+rowNum).val());
	var num = parseFloat($("#RETURN_NUM"+rowNum).val()); 
	if("NaN" == num.toString()){
		$("#RETURN_NUM"+rowNum).val("");
		$("#RETURN_AMOUNT"+rowNum).val("");
		chkCanErrMsg("", "请输入正确的退货数量");
		return;
	}
	if("NaN" == price.toString()){
		return;
	}
	
	var amount  = price * num;
	$("#RETURN_AMOUNT"+rowNum).val(amount);
}
function checkAmount(rowNum)
{
	var amount = parseFloat($("#RETURN_AMOUNT"+rowNum).val());
	var num = parseFloat($("#RETURN_NUM"+rowNum).val()); 
	if("NaN" == amount.toString()){
		$("#RETURN_PRICE"+rowNum).val("");
		$("#RETURN_AMOUNT"+rowNum).val("");
		chkCanErrMsg("", "请输入正确的退货金额");
		return;
	}
	if("NaN" == num.toString()){
		return;
	}
	
	var price  = (Number(amount/num)).toFixed(2);
	$("#RETURN_PRICE"+rowNum).val(price);
}

function setSpecTech(rownum){
	var spclTechFlag =  $("#SPCL_TECH_FLAG"+rownum).val();
	if(spclTechFlag !='1' && spclTechFlag !='2'){
		$("#SPECIAL_FLAG_SCRN"+rownum).text('无');
	}else{
		$("#SPECIAL_FLAG_SCRN"+rownum).html('<font  style="color: red">有</font><input value="查看" class="btn" onclick="showSpecTech('+rownum+')"  type="button" />&nbsp;');
	}
}

function showSpecTech(rowNum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rowNum).val();
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

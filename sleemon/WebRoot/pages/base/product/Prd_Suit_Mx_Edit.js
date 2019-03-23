 
/**
 * @module 系统管理
 * @func 货品套
 * @version 1.1
 * @author 刘曰刚
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
		var checkBox = $("#jsontb tr:gt(0) input[name='PRD_UNIT_ID']:checked");
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
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input[name='PRD_UNIT_ID']:checked");
			if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
			if(!formMutiTrChecked()){
				return;
			}
			if(dataColumnsValidation("jsontb",['PRD_NO'],"red")){
				childSave();
			}
			
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
		}
	);
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb input[name='PRD_UNIT_ID']:checkbox").attr("checked","true");
		}else{
			$("#jsontb input[name='PRD_UNIT_ID']:checkbox").removeAttr("checked");
		}
	});
});



//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "product.shtml?action=suitEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"MAIN_PRD_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_4");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input[name='PRD_UNIT_ID']:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "product.shtml?action=suitDelete",
		type:"POST",
		dataType:"text",
		data:{"PRD_UNIT_IDS":ids},
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
   newGoBack("product.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[name='PRD_UNIT_ID']").attr("checked","checked");
		});
	});
	//var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) input[name='PRD_UNIT_ID']:checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
//table增加一行
 
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
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='PRD_UNIT_ID"+rownum+"' json='PRD_UNIT_ID' name='PRD_UNIT_ID' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[2]+'"/>&nbsp;' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcUnit("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  inputtype="string"   autocheck="true"   label="货品名称"    type="text"  mustinput="true"   READONLY   value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"   inputtype="string" autocheck="true"  label="规格型号"   type="text"    READONLY  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" inputtype="string" autocheck="true"  label="花号"    type="text"     READONLY  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" inputtype="string" label="品牌"    type="text"    READONLY   value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" inputtype="string" label="标准单位"    type="text"    READONLY   value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRVD_PRICE" id="PRVD_PRICE'+rownum+'" name="PRVD_PRICE'+rownum+'"  autocheck="true" inputtype="string" label="供货价格"    type="text"     value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="COMP_NUM" id="COMP_NUM'+rownum+'" name="COMP_NUM"  autocheck="true"  label="组成数量"    type="text"  inputtype="int" maxlength="8"  mustinput="true"    value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="MAIN_BOM_FLAG" id="MAIN_BOM_FLAG'+rownum+'" name="MAIN_BOM_FLAG"  autocheck="true"   label="是否主组成货品"    type="checkbox"   "/>&nbsp;</td>')
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'" type="hidden" value="'+arrs[1]+'"/>')
              ;
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[name='PRD_UNIT_ID']").attr("checked","checked");
	});
	var MAIN_BOM_FLAG=arrs[10];
	if(""==MAIN_BOM_FLAG||null==MAIN_BOM_FLAG||1==MAIN_BOM_FLAG){
		$("#MAIN_BOM_FLAG"+rownum).attr("checked","checked");
	}
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

function selcUnit(rownum){
	var obj=selCommon("BS_21", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRVD_PRICE"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE", "selectParams");
	rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE",rownum);
}
//点击选择某条记录后，需要根据状态判断是否可用
function setButtonState(){
	var child =$.trim($("#chistate").val());
	if(child=="停用"){
		//没数据的状态下，主表已经置过状态了，这里直接返回
		//btnDisable(["delete"]);
	}
}

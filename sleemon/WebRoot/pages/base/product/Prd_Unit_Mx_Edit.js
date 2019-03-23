 
/**
 * @module 系统管理
 * @func 货品计量单位
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
			if(dataColumnsValidation("jsontb",['MEAS_UNIT_NO'],"red")){
				childSave();
			}
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
		}
	);
	
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
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "product.shtml?action=unitEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"PRD_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_3");
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
		url: "product.shtml?action=unitDelete",
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
//   newGoBack("product.shtml?action=toFrame");
   parent.window.gotoBottomPage("label_3");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	//var actionType = parent.document.getElementById("actionType").value;
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_UNIT_ID' name='PRD_UNIT_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<input  json="MEAS_UNIT_ID" id="MEAS_UNIT_ID'+rownum+'" name="MEAS_UNIT_ID'+rownum+'" type="hidden"  value="'+arrs[1]+'"/>')
            .append('<td nowrap align="left"><input  json="MEAS_UNIT_NO" id="MEAS_UNIT_NO'+rownum+'" name="MEAS_UNIT_NO"  inputtype="string"  autocheck="true" label="计量单位编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[3]+'"/>&nbsp;' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcUnit("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="MEAS_UNIT_NAME" id="MEAS_UNIT_NAME'+rownum+'" name="MEAS_UNIT_NAME'+rownum+'"  inputtype="string"   autocheck="true"   label="计量单位名称"    type="text"  mustinput="true"   READONLY   value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="MEAS_UNIT_TYPE" id="MEAS_UNIT_TYPE'+rownum+'" name="MEAS_UNIT_TYPE'+rownum+'"   inputtype="string" autocheck="true"  label="单位类型"   type="text"  mustinput="true"   READONLY  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RATIO" id="RATIO'+rownum+'" name="RATIO'+rownum+'"  autocheck="true"  label="换算关系"    type="text"   inputtype="float"   valueType="4,2"  mustinput="true"   value="'+arrs[6]+'"/>&nbsp;</td>')
            
              ;
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

function selcUnit(rownum){
	selCommon("BS_23", false, "MEAS_UNIT_ID"+rownum, "MEAS_UNIT_ID", "forms[0]","MEAS_UNIT_ID"+rownum+",MEAS_UNIT_NO"+rownum+",MEAS_UNIT_NAME"+rownum+",MEAS_UNIT_TYPE"+rownum, "MEAS_UNIT_ID,MEAS_UNIT_NO,MEAS_UNIT_NAME,UNIT_TYPE", "selectParams");
}
//点击选择某条记录后，需要根据状态判断是否可用
function setButtonState(){
	var child =$.trim($("#chistate").val());
	if(child=="停用"){
		//没数据的状态下，主表已经置过状态了，这里直接返回
		//btnDisable(["delete"]);
	}
}

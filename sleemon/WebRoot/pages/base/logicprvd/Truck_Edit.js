/**
 *@module 系统管理
 *@fuc 车型明细编辑js
 *@version 1.1
 *@author 王栋斌
 */
$(function() {
	$("#add").click(function() {
		addRow();
	});

	$("#delete").click(function() {
		//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if (checkBox.length < 1) {
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//获取所有选中的记录
			var ids = "";
			checkBox.each(function() {
				if ("" != $(this).val()) {
					ids = ids + "'" + $(this).val() + "',";
				}
			});
			ids = ids.substr(0, ids.length - 1);
			//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
			if ("" == ids) {
				checkBox.parent().parent().remove();
			} else {
				parent.showErrorMsg("已存在记录不能在此删除");
			}
			//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
			setFloatPostion();
		});

	//保存按钮
	$("#save").click(function() {
		//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if (checkBox.length < 1) {
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//下拉选择框校验
			if(!formCheckedEx()){
				return;
			}
			//明细校验
			if (!formMutiTrChecked()) {
				return;
			}
			childSave();
		});

	$("#allChecked").click(function() {
		var flag = document.getElementById("allChecked").checked;
		if (flag) {
			$("#jsontb :checkbox").attr("checked", "true");
		} else {
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
});

//子表保存
function childSave() {
	//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	if (checkBox.length < 1) {
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}

	var prvdId = $("#prvdId").val();
	var jsonData = multiPackJsonData();
	$.ajax( {
		url : "logicprvd.shtml?action=saveTruck",
		type : "POST",
		dataType : "text",
		data : {
			"PRVD_ID" : prvdId,
			"jsonData" : jsonData
		},
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_2");
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//table增加一行
function addRow(arrs) {
	var state = ""
	if (null == arrs) {
		arrs = [ '', '', '', '', '', '', '','' ];//添加字段的时候必须添加
	}

	var rownum = $("#jsontb tr").length;
	var classrow = rownum % 2;
	rownum = _row_num;
	_row_num++;

	var selTruckType = "<td nowrap align='center'><select name='TRUCK_TYPE"
			+ rownum
			+ "' id='TRUCK_TYPE"
			+ rownum
			+ "' json='TRUCK_TYPE' autocheck='true'  label='车型' inputtype='string' mustinput='true'>< option value=''>--请选择--</option></select></td>";

	$("#jsontb tr:last-child")
			.after(
					"<tr class='list_row"
							+ classrow
							+ "' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
			.append(
					"<td nowrap align='center'><input type='checkbox' style='valign:middle' json='TRUCK_ID' name='TRUCK_ID"
							+ rownum
							+ "' value='"
							+ arrs[0]
							+ "' rows='TRUCK_TYPE" + rownum + "'/>")
			.append(
					"<input type='hidden' name='PRVD_ID' json='PRVD_ID' value='"
							+ arrs[1] + "'/></td>")
			.append(selTruckType)
			.append(
					"<td nowrap align='center'><input type='text' name='MIN_VOLUME' json='MIN_VOLUME' mustinput='true' label='最小容积' inputtype='float' valueType='8,2' autocheck='true' value='"
							+ arrs[3] + "'/></td>")
			.append(
					"<td nowrap align='center'><input type='text' name='MAX_VOLUME' json='MAX_VOLUME' mustinput='true' label='最大容积' inputtype='float' valueType='8,2' autocheck='true' value='"
							+ arrs[4] + "'/></td>")
			.append(
					"<td nowrap align='center'><input type='text' name='LENGTH' json='LENGTH' mustinput='true' label='长度' inputtype='float' valueType='4,2' autocheck='true' value='"
							+ arrs[5] + "'/></td>")
			.append(
					"<td nowrap align='center'><input type='text' name='WIDTH' json='WIDTH' mustinput='true' label='宽度' inputtype='float' valueType='4,2' autocheck='true' value='"
							+ arrs[6] + "'/></td>")
			.append(
					"<td nowrap align='center'><input type='text' name='HEIGHT' json='HEIGHT' mustinput='true' label='高度' inputtype='float' valueType='4,2' autocheck='true' value='"
							+ arrs[7] + "'/></td>")

//	SelDictShow("TRUCK_TYPE" + rownum, "185", arrs[2], "");
	SelDictShow("TRUCK_TYPE" + rownum, "BS_109", arrs[2], "");

	//form校验设置
	InitFormValidator(0);
}

//校验下拉列表
function formCheckedEx() {
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkBox.each(function() {
		var id = $(this).attr("rows");
		var type = $("#" + id).val();
		if (type == ""){
			parent.showErrorMsg("请选择车型");
			flag = false;
			return false;
		} 
		var MIN_VOLUME = $(this).parent().parent().find("input[json='MIN_VOLUME']").val();
		var MAX_VOLUME = $(this).parent().parent().find("input[json='MAX_VOLUME']").val();
		if(0 == MIN_VOLUME || "0" == MIN_VOLUME){
			parent.showErrorMsg("'最小容积'不能为0");
			flag = false;
			return false;
		}
		if(0 == MAX_VOLUME || "0" == MAX_VOLUME){
			parent.showErrorMsg("'最大容积'不能为0");
			flag = false;
			return false;
		}
		
		MIN_VOLUME = Number(MIN_VOLUME);
		MAX_VOLUME = Number(MAX_VOLUME);
		if(MIN_VOLUME>MAX_VOLUME){
			parent.showErrorMsg("'最大容积'的值应大于'最小容积'的值");
			flag = false;
			return false;
		}
	});
	return flag;
}

//返回按钮
function gobacknew(){
	parent.window.gotoBottomPage('label_2');
}
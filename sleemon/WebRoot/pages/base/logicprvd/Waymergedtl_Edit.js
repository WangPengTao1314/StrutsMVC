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
				parent.showConfirm("您确认要删除吗", "multiRecDeletes();");
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

	var waymergeId = $("#waymergeId").val();
	var jsonData = multiPackJsonData();
	$.ajax( {
		url : "logicprvd.shtml?action=saveWaymergedtl",
		type : "POST",
		dataType : "text",
		data : {
			"WAY_MERGE_ID" : waymergeId,
			"jsonData" : jsonData
		},
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				 parent.showMsgPanel("保存成功");
				 //解决 弹出框  和 弹框之后 事件不按顺序执行的问题
				 $("#YT_MSG_BTN_OK").click(function(){
//					window.location.reload();
					window.close();
				 });
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//路线编号0通用选取
function sel(rownum){
	selCommon("BS_34", false, "HAULWAY_ID"+rownum, "HAULWAY_ID", "forms[0]","HAULWAY_ID"+rownum+",HAULWAY_NO"+rownum+",HAULWAY_NAME"+rownum+",DELV_CITY"+rownum+",SHIP_POINT_NAME"+rownum+",ARRV_CITY"+rownum+",CHANN_NO"+rownum+",CHANN_NAME"+rownum, "HAULWAY_ID,HAULWAY_NO,HAULWAY_NAME,DELV_CITY,SHIP_POIT_NAME,ARRV_CITY,CHANN_NO,CHANN_NAME","selparams");
}

//table增加一行
function addRow(arrs) {
	var state = ""
	if (null == arrs) {
		arrs = [ '', '', '', '', '', '', '', '', '', '', '' ];//添加字段的时候必须添加
	}

	var rownum = $("#jsontb tr").length;
	var classrow = rownum % 2;
	rownum = _row_num;
	_row_num++;
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+ classrow + "' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child").append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='WAY_MERGE_DTL_ID' name='WAY_MERGE_DTL_ID"+ rownum + "' value='" + arrs[0] + "'/>")
			.append("<input type='hidden' name='WAY_MERGE_ID' json='WAY_MERGE_ID' value='"+ arrs[1] + "'/></td>")
			.append("<input type='hidden' name='HAULWAY_ID"+ rownum + "' json='HAULWAY_ID' value='"+ arrs[2] + "' id='HAULWAY_ID"+ rownum + "'/></td>")
			.append("<td nowrap align='center'><input type='text' readonly name='HAULWAY_NO' json='HAULWAY_NO' mustinput='true' label='路线编号' id='HAULWAY_NO"+rownum+"' inputtype='string' autocheck='true' value='" + arrs[3] +"'/><img align='absmiddle' name='selAREA' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='sel("+rownum+")'></td>")
			.append("<td nowrap align='center'><input type='text' readonly name='HAULWAY_NAME' json='HAULWAY_NAME' mustinput='true' label='路线名称' id='HAULWAY_NAME"+rownum+"' inputtype='string' autocheck='true'  value='" + arrs[4] + "'/></td>")
			.append("<td nowrap align='center'><input type='text' readonly name='DELV_CITY' json='DELV_CITY' mustinput='true' label='发出城市' id='DELV_CITY"+rownum+"' inputtype='string' autocheck='true'  value='" + arrs[5] + "'/>")
			.append("<td nowrap align='center'><input type='text' readonly name='SHIP_POINT_NAME' json='SHIP_POINT_NAME' mustinput='true' label='发货点名称' id='SHIP_POINT_NAME"+rownum+"' inputtype='string' autocheck='true'  value='" + arrs[6] + "'/></td>")
			.append("<td nowrap align='center'><input type='text' readonly name='ARRV_CITY' json='ARRV_CITY' mustinput='true' label='到达城市' id='ARRV_CITY"+rownum+"' inputtype='string' autocheck='true'  value='" + arrs[7] + "'/>")
			.append("<td nowrap align='center'><input type='text' readonly name='CHANN_NO' json='CHANN_NO' mustinput='true' label='渠道编号' id='CHANN_NO"+rownum+"' inputtype='string'  autocheck='true' value='" + arrs[8] + "'/></td>")
			.append("<td nowrap align='center'><input type='text' readonly name='CHANN_NAME' json='CHANN_NAME'  mustinput='true' label='渠道名称' id='CHANN_NAME"+rownum+"' inputtype='string' autocheck='true' value='" + arrs[9] + "'/></td>")
	//form校验设置
	InitFormValidator(0);
}

function multiRecDeletes(){
	//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function() {
		ids = ids + "'" + $(this).val() + "',";
	});
	ids = ids.substr(0, ids.length - 1);
	$.ajax( {
		url : "logicprvd.shtml?action=deleteWaymergedtl",
		type : "POST",
		dataType : "text",
		data : {
			"WAY_MERGE_DTL_ID" : ids
		},
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("删除成功");
				window.location.reload();
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
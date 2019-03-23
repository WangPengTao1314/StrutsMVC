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
		url : "logicprvd.shtml?action=saveWaymerge",
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
				parent.window.gotoBottomPage("label_3");
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//发货点通用选取
function sel(rownum){
	selCommon("BS_22", false, "SHIP_POINT_ID"+rownum, "SHIP_POINT_ID", "forms[0]","SHIP_POINT_ID"+rownum+",SHIP_POINT_NO"+rownum+",SHIP_POINT_NAME"+rownum, "SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME");
}
//发出城市选取
function selDelv(rownum){
	selCommon("BS_20", false, "ZONE_ID"+rownum, "ZONE_ID", "forms[0]","DELV_CITY"+rownum, "CITY");
}

//到达城市选取
function selArrv(rownum){
	selCommon("BS_20", false, "ZONE_ID"+rownum, "ZONE_ID", "forms[0]","ARRV_CITY"+rownum, "CITY");
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
	
	var flag;
	if(arrs[2] == ""){
		flag = "";
	}else{
		flag = "readonly";
	}
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+ classrow + "' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child").append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='WAY_MERGE_ID' name='WAY_MERGE_ID"+ rownum + "' value='" + arrs[0] + "'/>")
			.append("<input type='hidden' name='PRVD_ID' json='PRVD_ID' value='"+ arrs[1] + "'/></td>")
			.append("<input type='hidden' name='SHIP_POINT_ID"+ rownum + "' json='SHIP_POINT_ID' value='"+ arrs[4] + "' id='SHIP_POINT_ID"+ rownum + "'/></td>")
			.append("<input type='hidden' name='ZONE_ID' id='ZONE_ID"+ rownum + "'/></td>")
			.append("<td nowrap align='center'><input type='text' name='WAY_MERGE_NO' " + flag + " json='WAY_MERGE_NO' mustinput='true' label='合并路线编号' inputtype='string' autocheck='true' maxlength='30' value='" + arrs[2] +"'/></td>")
			.append("<td nowrap align='center'><input type='text' name='WAY_MERGE_NAME' json='WAY_MERGE_NAME' mustinput='true' label='合并路线名称' inputtype='string' autocheck='true' maxlength='100' value='" + arrs[3] + "'/></td>")
			.append("<td nowrap align='center'><input type='text' name='SHIP_POINT_NO"+rownum+"' json='SHIP_POINT_NO' mustinput='true' label='发货点编号' readonly='readonly' inputtype='string' autocheck='true'  value='" + arrs[5] + "' id='SHIP_POINT_NO"+rownum+"'/><img align='absmiddle' name='selAREA' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='sel("+rownum+")'></td>")
			.append("<td nowrap align='center'><input type='text' name='SHIP_POINT_NAME"+rownum+"' json='SHIP_POINT_NAME' mustinput='true' label='发货点名称' readonly='readonly' inputtype='string' autocheck='true'  value='" + arrs[6] + "' id='SHIP_POINT_NAME"+rownum+"'/></td>")
			.append("<td nowrap align='center'><input type='text' name='DELV_CITY' json='DELV_CITY' mustinput='true' label='发出城市' readonly='readonly' inputtype='string' autocheck='true'  value='" + arrs[7] + "' id='DELV_CITY" + rownum + "'/><img align='absmiddle' name='selAREA' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selDelv("+rownum+")'></td>")
			.append("<td nowrap align='center'><input type='text' name='ARRV_CITY' json='ARRV_CITY' mustinput='true' label='到达城市' readonly='readonly' inputtype='string' autocheck='true'  value='" + arrs[8] + "' id='ARRV_CITY" + rownum + "'/><img align='absmiddle' name='selAREA' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selArrv("+rownum+")'></td>")
			.append("<td nowrap align='center'><input type='text' name='LENGTH' json='LENGTH' inputtype='float' autocheck='true' maxlength='11' valueType='8,2' label='全程' value='" + arrs[9] + "' id='LENGTH" + rownum + "'/></td>")
			.append("<td nowrap align='center'><input type='text' name='DAYS' json='DAYS'  inputtype='float' autocheck='true' maxlength='11' valueType='8,2' label='天数' value='" + arrs[10] + "' id='DAYS" + rownum + "'/></td>")
			.append("<td nowrap align='center'><a href='#' onclick='url()'>子路线</a></td>")
	//form校验设置
	InitFormValidator(0);
}

//子路线弹窗
function url(){
	 window.open('logicprvd.shtml?action=toEditWaymergeDtl&WAY_MERGE_ID='+ arrs[0],'合并路线明细','height=400, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

//返回按钮
function gobacknew(){
	parent.window.gotoBottomPage('label_3');
}
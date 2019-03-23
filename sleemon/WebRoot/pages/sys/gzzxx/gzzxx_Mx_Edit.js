/**
  *@module 系统管理 
  *@func 工作组信息
  *@version 1.1 
  *@author 吴亚林
*/
$(function () {
	init();//页面初始化
	//添加
	$("#add").click(function () {
		addRow();
	});
	//增行
	$("#clickAddRow").dblclick(function () {
		addRow();
	});
	//删除
	$("#delete").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		//var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	
	    //if('启用' == state){
	       //parent.showErrorMsg("主表为启用状态,不能删除。");
		   //return;
	    //}
	    /*
	    if('停用' == state){
	       parent.showErrorMsg("主表为停用状态,不能删除。");
		   return;
	    }*/
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if (checkBox.length < 1) {
			parent.showErrorMsg("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u6761\u8bb0\u5f55");
			return;
		}
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function () {
			if ("" != $(this).val()) {
				ids = ids + "'" + $(this).val() + "',";
			}
		});
		ids = ids.substr(0, ids.length - 1);
		//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
		if ("" == ids) {
			checkBox.parent().parent().remove();
		} else {
			parent.showConfirm("\u60a8\u786e\u8ba4\u8981\u5220\u9664\u5417", "bottomcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	//保存
	$("#save").click(function () {
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
	
	//全选/反选
	$("#allChecked").click(function () {
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
	var selRowId = parent.document.getElementById("selRowId").value;
	var jsonData = multiPackJsonData();
	$.ajax({url:"gzzxx.shtml?action=childEdit", type:"POST", dataType:"text", data:{"childJsonData":jsonData, "GZZXXID":selRowId}, complete:function (xhr) {
		eval("jsonResult = " + xhr.responseText);
		if (jsonResult.success === true) {
			parent.showMsgPanel("\u4fdd\u5b58\u6210\u529f");
			window.parent.topcontent.pageForm.submit();
//			parent.window.gotoBottomPage("label_1");
		} else {
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}});
}

//删除操作
function multiRecDeletes() {
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function () {
		ids = ids + "'" + $(this).val() + "',";
	});
	ids = ids.substr(0, ids.length - 1);
	var jsonData = multiPackJsonData();
	$.ajax({url:"gzzxx.shtml?action=childDelete", type:"POST", dataType:"text", data:{"childJsonData":jsonData,"GZZCYID":ids}, complete:function (xhr) {
		eval("jsonResult = " + xhr.responseText);
		if (jsonResult.success === true) {
			parent.showMsgPanel("删除成功！");
			checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
			$("#delFlag").val("true");
		} else {
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}});
}

function allSave(){
	//零星领料单form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//对于选中的零星领料单明细校验
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
		url: "gzzxx.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"GZZXXID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","gzzxx.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
				
//table增加一行
function addRow(arrs) {
	if (null == arrs) {
		arrs = ["", "", "", "","","","","","",""];
	}
	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum % 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row" + classrow + "' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		
		.append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='GZZCYID' name='GZZCYID" + rownum + "' value='" + arrs[0] + "'/></td>")
		.append("<td nowrap align='left'><input id='YHBH"+rownum+"' name='YHBH"+rownum+"' seltarget='selWLXX' size='20' autocheck='true' label='用户编号' inputtype='String' mustinput='true' value='"+arrs[2]+"' READONLY/><input json='XTYHID' id='XTYHID"+rownum+"' name='XTYHID"+rownum+"' type='hidden' seltarget='selWLXX' value='"+arrs[1]+"'/>"
		+"<img align='absmiddle' id='img"+ rownum +"' name='selWLXX' style='cursor:hand' src='"+ctxPath+"/styles/"+theme+"/images/plus/select.gif'></td>")
		.append("<td nowrap align='left'><input json='YHM' id='YHM"+rownum+"' readonly name='YHM"+rownum+"' type='text' seltarget='selWLXX' value='"+arrs[3]+"'/></td>")
		.append("<td nowrap align='left'><input json='YHLB' id='YHLB"+rownum+"' readonly name='YHLB"+rownum+"' type='text' seltarget='selWLXX' value='"+arrs[4]+"'/></td>")
		.append("<td nowrap align='left'><input json='JGXXID' id='JGXXID"+rownum+"' name='JGXXID"+rownum+"' type='hidden' seltarget='selWLXX' value='"+arrs[5]+"'/><input json='JGMC' id='JGMC"+rownum+"' name='JGMC"+rownum+"' type='text' seltarget='selWLXX' readonly value='"+arrs[6]+"'/></td>")
		.append("<td nowrap align='left'><input json='BMXXID' id='BMXXID"+rownum+"' name='BMXXID"+rownum+"' type='hidden' seltarget='selWLXX' value='"+arrs[7]+"'/><input json='BMMC' id='BMMC"+rownum+"' name='BMMC"+rownum+"' type='text' seltarget='selWLXX' readonly value='"+arrs[8]+"'/></td>");
	
	$("#img"+rownum).click(function () {

		  selWlxx(rownum);
	});
	
	$("#jsontb tr:last-child").find("td:gt(0)").click(function () {
		$(this).parent().find("input[type='checkbox']").attr("checked", "checked");
	});
	//form校验设置
	InitFormValidator(0);
}

function init() {
	$("#jsontb tr").each(function () {
		$(this).find("td:gt(0)").click(function () {
			$(this).parent().find("input[type='checkbox']").attr("checked", "checked");
		});
	});
	var actionType = getActionType();//parent.document.getElementById("actionType").value;
	if ("list" == actionType) {
		$("#jsontb tr:gt(0) :checkbox").attr("checked", "true");
	}	
	//form校验设置
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
}


//通用选取：物料信息
function selWlxx(i){
		var obj = selCommon('System_7', true, 'XTYHID'+i, 'XTYHID', 'forms[0]','XTYHID'+i+',YHBH'+i+',YHM'+i+',YHLB'+i+',JGXXID'+i+',JGMC'+i+',BMXXID'+i+',BMMC'+i, 'XTYHID,YHBH,YHM,YHLB,JGXXID,JGMC,BMXXID,BMMC', 'condition');
		multiSelCommonSet(obj,"XTYHID,YHBH,YHM,YHLB,JGXXID,JGMC,BMXXID,BMMC",i);
		
}

function formCheckedEx(){
	
	var checkBox = $("input[type='checkbox']");
	var wlbhs = [];
	var index = 0;
	checkBox.each(function(i){
		var selVal = $("#YHBH"+i).val();
		if($(this).attr("checked") && selVal != null && selVal != ""){
			wlbhs[index++] = selVal;
		}
	});
	var wlbhsTemp = wlbhs.sort();
		for(var i=0;i<wlbhsTemp.length;i++){
		if (wlbhsTemp[i] == wlbhsTemp[i+1]){
			parent.showErrorMsg("明细中存在重复数据");
			return false;
		}
	}
	return true;
}

function getActionType() {
	return parent.document.getElementById("actionType").value;
}
function getSelRowId() {
	return parent.document.getElementById("selRowId").value;
}


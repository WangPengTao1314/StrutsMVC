 /**
  *@module 系统管理
  *@fuc 数据字典明细编辑js
  *@version 1.1
  *@author 张羽
*/

$(function(){
	setButtonState();
 	init();//页面初始化
	$("#add").click(function(){
	    addRow(); 
	});
	
	$("#clickAddRow").dblclick(function(){
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
		var actionType = parent.document.getElementById("actionType").value;
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

//子表保存
function childSave(){
    //查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	var selRowId = parent.document.getElementById("selRowId").value;
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "sjzd.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"SJZDID":selRowId,"SJZDMXIDS":ids},
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
		url: "sjzd.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"SJZDMXIDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
	
function allSave(){
    //查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);

	//零星领料单form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//对于选中的零星领料单明细校验
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	$.ajax({
		url: "sjzd.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"SJZDID":selRowId,"SJZDMXIDS":ids},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","sjzd.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
				
//table增加一行
function addRow(arrs){
	var state  =""
	if(null == arrs){
		arrs = ['','','','','','','',''];
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	//上级数据项选取规则，新增时，选取该字典下任意节点，如果该字典下无明细，则不选
	//修改时，选取该字典下非自己的任一节点，不可与已有的数据项值重复。
	
	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='SJZDMXID' name='SJZDMXID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='left'><input type='hidden' name='pk"+rownum+"' id='pk"+rownum+"' >"+"<input json='SJXMC' size='20' maxlength='150' name='SJXMC"+rownum+"' id='SJXMC"+rownum+"' autocheck='true' label='数据项名称' inputtype='string'  mustinput='true'  value='"+arrs[1]+"'/></td>")
		    .append("<td nowrap align='left'><input name='SJXZ"+rownum+"' id='SJXZ"+rownum+"'  size='20' maxlength='50' json='SJXZ' autocheck='true' label='数据项值' inputtype='string' onblur='chksj(this)'  mustinput='true' value='"+arrs[2]+"'/></td>")
		    .append("<td nowrap align='left'><input name='KEYCODE"+rownum+"' id='KEYCODE"+rownum+"' size='20' maxlength='50' json='KEYCODE' autocheck='true' label='数据项代码' inputtype='string' mustinput='true' value='"+arrs[7]+"'/></td>")
		    //.append("<td nowrap align='left'><input name='SJSJZDMXMC"+rownum+"' size='20' json='SJSJZDMXMC' readonly='true' seltarget='selSJSJX' autocheck='true' label='上级数据项名称' inputtype='string'  value='"+arrs[7]+"'/>" +
		    //"<input type='hidden' id='con"+rownum+"' name='con"+rownum+"' value=''>" +
		    //"<img align='absmiddle' name='selSJSJX' style='cursor:hand' src='"+ctxPath+"/styles/"+theme+"/images/plus/select.gif' onclick='selSJ("+rownum+");'></td>")
		    .append("<td nowrap align='left'><input name='SJGL"+rownum+"' id='SJGL"+rownum+"' size='10' maxlength='30' json='SJGL' autocheck='true' label='数据归类' inputtype='string' mustinput='true' value='"+arrs[3]+"'/></td>")
		    .append("<td nowrap align='left'><input name='REMARK"+rownum+"' id='SJGL"+rownum+"' size='20' maxlength='250' json='REMARK' autocheck='true' label='其他说明' inputtype='string'  value='"+arrs[4]+"'/></td>");
		    //.append("<td nowrap align='left' display:none><input type='hidden' name='SJSJZDMXID"+rownum+"' id='SJSJZDMXID"+rownum+"' seltarget='selSJSJX'  json='SJSJZDMXID'   value='"+arrs[3]+"'/></td>")
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	InitFormValidator(0);
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
	if("list" == actionType){
		$("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	}	
	//form校验设置
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
}

//通用选取：物料信息
function selSJ(i){
	var selRowId = parent.document.getElementById("selRowId").value;
	var sjzdid = parent.topcontent.$("#SJZDBH").val();
	var sjzdmxid = $("#SJZDMXID"+i).val();
	 alert(sjzdmxid);
	var condition="";
	//新增时数据字典id为空，这时候应该选不到数据
	if(selRowId==""){
		condition=" sjzdid='' and delflag='0' ";
	}
	else if(sjzdmxid==""){
		condition = "sjzdid='"+selRowId+"' and delflag='0'" ;
	}else{
		condition = "sjzdid='"+selRowId+"' and sjzdmxid<> '"+sjzdmxid+"' and delflag='0' ";
	}
	document.getElementById("con"+i).value=condition;
	selCommon('System_6', false, 'SJSJZDMXID'+i, 'SJZDMXID', 'forms[0]','SJSJZDMXID'+i+',SJSJZDMXMC'+i, 'SJZDMXID,SJXMC', 'con'+i);
}

//校验是否已经存在该数据项值
function chksj(obj){
	var selRowId = parent.document.getElementById("selRowId").value;
	var sjxz = $(obj).val();
	return;
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	$.ajax({
		url: "sjzd.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"SJZDID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","sjzd.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	
}


//点击选择某条记录后，需要根据状态判断是否可用
function setButtonState(){
	var child =$.trim($("#chistate").val());
	if(child=="停用"){
		//没数据的状态下，主表已经置过状态了，这里直接返回
		//btnDisable(["delete"]);
	}
}

function btnDisable(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).attr("disabled","true");
	}
}

function getActionType() {
	return parent.document.getElementById("actionType").value;
}


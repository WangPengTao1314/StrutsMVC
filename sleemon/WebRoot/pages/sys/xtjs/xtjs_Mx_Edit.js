/*
  *@module 系统管理
  *@fuc 系统角色明细列表编辑
  *@version 1.1
  *@author 唐赟
  */
$(function(){

 	init();//页面初始化
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
		var selRowId = parent.document.getElementById("selRowId").value; 
		//var state = parent.topcontent.document.getElementById("state"+selRowId).value;
		/*if('停用' == state){
	       parent.showErrorMsg("主表为停用状态,不能删除。");
		   return;
	    }*/
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
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	var xtyhjsid = $("#XTYHJSID").val();//判断新增还是修改
	
	$.ajax({
		url: "xtjs.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"XTJSID":selRowId,"XTYHJSID":xtyhjsid},
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
		url: "xtjs.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"XTJSYHID":ids},
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
	
function allSave(){
	//系统角色主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//对于选中的系统角色明细校验
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = getSelRowId();
	//added by gsy start 2013-7-9
	//角色说明长度校验
	var jssm = parent.topcontent.$("#JSSM").val();
	var jssmlen = stringLength(jssm);
	 if(jssmlen>300){
	 parent.showErrorMsg('角色说明长度不能大于300');
	 return;
	 }
	 //added by gsy end 2013-7-9
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "xtjs.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"XTJSID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","xtjs.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
				
//table增加一行
function addRow(arrs){
	if(null == arrs){
		arrs = ['','',''];
	}
	var a= '启用';
	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'>"
		    			+"<input type='checkbox' style='height:12px;valign:middle' json='XTYHJSID' name='XTYHJSID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='left'>"
		    	      +"<input json='YHBH' size='10' id='YHBH"+rownum+"' name='YHBH"+rownum+"' seltarget='selJSXX' autocheck='true' label='用户编号' inputtype='string'  mustinput='true' readonly value='"+arrs[1]+"'/>"
		              +"<input  json='XTYHID' id='XTYHID"+rownum+"' name='XTYHID"+rownum+"' type='hidden' seltarget='selJSXX' value='"+arrs[1]+"'/>"
		              +"<img align='absmiddle' name='selJSXX' style='cursor:hand' src='"+ctxPath+"/styles/"+theme+"/images/plus/select.gif' onclick='selJSxx("+rownum+");'></td>")
		    .append("<td nowrap align='left'><input id='YHMC"+rownum+"' name='YHMC"+rownum+"' size='10' autocheck='true' label='用户名称' inputtype='string'  mustinput='true' readonly value='"+arrs[2]+"'/></td>");
		    
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


function selJSxx(i){
    var selcondition = $("#selcondition");
	var obj = selCommon('System_3', true, 'YHBH'+i, 'XTYHID', 'forms[0]','XTYHID'+i+',YHBH'+i+',YHMC'+i, 'XTYHID,YHBH,YHM', 'selcondition');
	//多条记录设置
	multiSelCommonSet(obj,"XTYHID,YHBH,YHMC",i);
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}



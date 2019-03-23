 /**
  *@module 系统管理
  *@fuc 区域分管明细编辑js
  *@version 1.1
  *@author zzb
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
	
	//保存按钮
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
			//对于选中的明细校验
			if(!formCheckedEx()){
				return;
			}
			//页面 查重
			if(dataColumnsValidation("jsontb",['CHRG_NO'],"red")){
				childSave();
			}
			
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
		var id = $(this).val();
		if(null != id & ""!=id){
			ids = ids+"'"+id+"',";
		}
		
	});
	ids = ids.substr(0,ids.length-1);
	
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "area.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"areaID":selRowId,"areaChrgIds":ids},
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
	var CHRG_IDS = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
		var id = $(this).parent().parent().find("input[name=CHRG_ID]").val();
	    if(null != id && "" != id){
	    	CHRG_IDS = CHRG_IDS +"'"+ id+"',";
	    }
	});
	
	ids = ids.substr(0,ids.length-1);
	CHRG_IDS = CHRG_IDS.substr(0,CHRG_IDS.length-1);
	var selRowId = getSelRowId();
	
	$.ajax({
		url: "area.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"areaChrgIds":ids,"CHRG_IDS":CHRG_IDS,"AREA_ID":selRowId},
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

	//主表单form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//对于选中从表单明细校验
	if(!formCheckedEx()){
		return;
	}
	 
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
		if(!dataColumnsValidation("jsontb",['CHRG_NO'],"red")){//页面查重
	      return;
        }
	}
	$.ajax({
		url: "area.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"areaID":selRowId,"areaChrgIds":ids},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","area.shtml?action=toFrame");
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
		arrs = ['','','','',''];//添加字段的时候必须添加
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	//上级数据项选取规则，新增时，选取该字典下任意节点，如果该字典下无明细，则不选
	//修改时，选取该字典下非自己的任一节点，不可与已有的数据项值重复。
	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	
	//分管对象类型 下拉框
	var selChrgType = "<select name='CHRG_TYPE"+rownum+"' id='CHRG_TYPE"+rownum+"' json='CHRG_TYPE' autocheck='true'  label='分管对象类型' inputtype='string' mustinput='true'><option value='用户'>用户</option></select>";
	//职位  下拉框
	var selJob = "<select name='JOB"+rownum+"' id='JOB"+rownum+"' json='JOB' autocheck='true'  label='职位' inputtype='string' mustinput='true'>< option value=''>--请选择--</option></select>";
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='AREA_CHRG_ID' name='AREA_CHRG_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='center'>"+rownum+"</td>")
		    .append("<td nowrap align='left'><input type='hidden' name='pk"+rownum+"' id='pk"+rownum+"' >"+selChrgType+"</td>");
	
	//分管对象类型 下拉框 设置
	//SelDictShow("CHRG_TYPE"+rownum,"BS_5",arrs[1],"");
	//设置通用选取
	var html = changeSelCommon(rownum,arrs);
	$("#jsontb tr:last-child").append(html);
    $("#jsontb tr:last-child").append("<td nowrap align='left'>"+selJob+"</td>");
	//设置职位 下拉框
	SelDictShow("JOB"+rownum,"BS_4",arrs[5],"");
	
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	InitFormValidator(0);
}


//根据分管对象类型 选择后面的通用选取
function changeSelCommon(rownum,arrs){
	var html = "";
	var inpChrNo =  "CHRG_NO"+rownum
	var inpChrName = "CHRG_NAME"+rownum;
	var inpID = "ID"+rownum;
	  
	html = "<td nowrap name='chrgNo'><input type='text' name='CHRG_NO' id='"+inpChrNo+"' json= 'CHRG_NO' seltarget='selGZZ' value='"+arrs[2]+"' label='分管对象编号'  onblur='chksj(this)'  mustinput='true' inputtype='string' READONLY  autocheck='true'/>"
             +" <input type='hidden' json='CHRG_ID'  name='CHRG_ID' id='"+inpID+"' seltarget='selGZZ' value='"+arrs[1]+"'>"
             +" <img align='absmiddle' name='selJGXX' style='cursor:hand' src='/sleemon/styles/newTheme/images/plus/select.gif'"
             +" onClick='sel("+rownum+")'> </td>"
			 +"<td><input type='text' json='CHRG_NAME' name='"+inpChrName+"' id='"+inpChrName+"' seltarget='selGZZ' value='"+arrs[4]+"' READONLY /></td>";
	
	return html;
}

function sel(rownum){
	var v = $("#CHRG_TYPE"+rownum).val();
	if("工作组" == v){
		selCommon("System_13", false, "ID"+rownum, "GZZXXID","forms[0]","ID"+rownum+",CHRG_NO"+rownum+",CHRG_NAME"+rownum, "GZZXXID,GZZBH,GZZMC", "selecGZZtParams")
	}else{
		selCommon("System_7", false, "ID"+rownum, "XTYHID","forms[0]","ID"+rownum+",CHRG_NO"+rownum+",CHRG_NAME"+rownum, "XTYHID,YHBH,YHM", "")
	}
	
}

//校验其它参数 如：下拉列表
function formCheckedEx(){
	var flag = true;
	//明细提交时，可能会有多行，只有选中的才校验
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
			var selects = $(this).find("select");
			var selectChrgType = selects[0].value;
			var selectJob = selects[1].value;
			
			if(null == selectChrgType || ""==selectChrgType){
		       parent.showErrorMsg("请选择'分管对象类型'!");
		       flag = false;
		       return flag;
	        } 
			var inputs = $(this).find("input:gt(0)");
			inputs.each(function(){
				if (!InputCheck(this)) {
					flag = false;
					return flag;
				}
			});
			if(flag){
				if(null == selectJob || ""==selectJob){
			       parent.showErrorMsg("请选择'职位'!");
			       flag = false;
			       return flag;
		       } 
			}
		}
	});

   return flag;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
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


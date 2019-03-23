 /**
  *@module 系统管理
  *@fuc 送货地址信息js
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
			if(!formMutiTrChecked()){
				return;
			}
			
			//页面 查重
			if(dataColumnsValidation("jsontb",['DELIVER_ADDR_NO'],"#E6B9B8")){
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
	var DELIVER_ADDR_NOS = "";
	checkBox.each(function(){
		var id = $(this).val();
		if(null != id & ""!=id){
			ids = ids+"'"+id+"',";
		}
		var no = $(this).parent().parent().find("input[json='DELIVER_ADDR_NO']").val();
		if(null != no && "" != no){
			no = $.trim(no);
			DELIVER_ADDR_NOS = DELIVER_ADDR_NOS+"'"+no+"',";
		}
		
	});
	ids = ids.substr(0,ids.length-1);
	DELIVER_ADDR_NOS = DELIVER_ADDR_NOS.substr(0,DELIVER_ADDR_NOS.length-1);
	
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "chann.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"CHANN_ID":selRowId,"DELIVER_ADDR_NOS":DELIVER_ADDR_NOS,"DELIVER_ADDR_IDS":ids},
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
	var selRowId = getSelRowId();
	$.ajax({
		url: "chann.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ADDR_IDS":ids,"CHANN_ID":selRowId},
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
 
	$.ajax({
		url: "chann.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"CHANN_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
				
//table增加一行
 
function addRow(arrs){
	if(null == arrs){
		arrs = ['','',''];//添加字段的时候必须添加
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
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='DELIVER_ADDR_ID' name='DELIVER_ADDR_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="DELIVER_ADDR_NO" id="DELIVER_ADDR_NO'+rownum+'" style="width:250px;"  name="DELIVER_ADDR_NO"  autocheck="true" mustinput="true" label="送货地址信息编号"  type="text"   inputtype="string"   maxlength="30"  value="'+arrs[1]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="left"><input  json="DELIVER_DTL_ADDR" id="DELIVER_DTL_ADDR'+rownum+'" style="width:450px;"    name="DELIVER_DTL_ADDR'+rownum+'"  autocheck="true" mustinput="true"  label="送货详细地址"  type="text"   inputtype="string"   maxlength="350"  value="'+arrs[2]+'"/>&nbsp;</td>')
	
	
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

function gobacknew(){
	 newGoBack("chann.shtml?action=toFrame");
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


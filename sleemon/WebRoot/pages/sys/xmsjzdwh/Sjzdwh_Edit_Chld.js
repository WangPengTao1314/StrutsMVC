/**
 * @prjName:喜临门营销平台
 * @fileName:Sjzdwh_Edit_Chld
 * @author chenj
 * @time   2014-01-30 10:18:20 
 * @version 1.1
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
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
});

//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//明细校验
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
		url: "xmsjzdwh.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"DATA_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","xmsjzdwh.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "xmsjzdwh.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"DATA_ID":selRowId},
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
		url: "xmsjzdwh.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DATA_DTL_IDS":ids},
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
   newGoBack("xmsjzdwh.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='DATA_DTL_ID"+rownum+"' json='DATA_DTL_ID' name='DATA_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='left'><input  json='DATA_DTL_CODE' id='DATA_DTL_CODE"+rownum+"' name='DATA_DTL_CODE"+rownum+"'  autocheck='true' label='数据明细编号'  type='text'   inputtype='string'     mustinput='true'     maxlength='30'  value='"+arrs[1]+"'/>&nbsp;</td>")
		    .append("<td nowrap align='left'><input  json='DATA_DTL_NAME' id='DATA_DTL_NAME"+rownum+"' name='DATA_DTL_NAME"+rownum+"'  autocheck='true' label='数据明细名称'  type='text'   inputtype='string'     mustinput='true'     maxlength='100'  value='"+arrs[2]+"'/>&nbsp;</td>")
		    .append("<td nowrap align='left'>"
		    			+"<input  json='PAR_DATA_DTL_CODE' id='PAR_DATA_DTL_CODE"+rownum+"' name='PAR_DATA_DTL_CODE"+rownum+"'  autocheck='true' label='上级数据明细编号'  type='text'   inputtype='string'        maxlength='100'  value='"+arrs[3]+"'/>"
		    			+"<input  json='PAR_DATA_DTL_ID' id='PAR_DATA_DTL_ID"+rownum+"' name='PAR_DATA_DTL_ID"+rownum+"' type='hidden' value='"+arrs[8]+"'/>"
		              	+"<img align='absmiddle' name='selJSXX' style='cursor:hand' src='"+ctxPath+"/styles/"+theme+"/images/plus/select.gif' onclick='selJSxx("+rownum+");'></td>")
		              	
            .append("<td nowrap align='left'><input  json='PAR_DATA_DTL_NAME' id='PAR_DATA_DTL_NAME"+rownum+"' name='PAR_DATA_DTL_NAME"+rownum+"'  autocheck='true' label='上级数据明细名称'  type='text'   inputtype='string'   readonly  maxlength='100'  value='"+arrs[4]+"'/>&nbsp;</td>")
            
            
            .append("<td nowrap align='left'><input  json='DATA_DTL_DES_1' id='DATA_DTL_DES_1"+rownum+"' name='DATA_DTL_DES_1"+rownum+"'  autocheck='true' label='数据明细描述1'  type='text'   inputtype='string'        maxlength='300'  value='"+arrs[5]+"'/>&nbsp;</td>")
            .append("<td nowrap align='left'><input  json='DATA_DTL_DES_2' id='DATA_DTL_DES_2"+rownum+"' name='DATA_DTL_DES_2"+rownum+"'  autocheck='true' label='数据明细描述2'  type='text'   inputtype='string'        maxlength='300'  value='"+arrs[6]+"'/>&nbsp;</td>")
            .append("<td nowrap align='left'><input  json='REMARK' id='REMARK"+rownum+"' name='REMARK"+rownum+"'  autocheck='true' label='备注'  type='text'   inputtype='string'        maxlength='300'  value='"+arrs[7]+"'/>&nbsp;</td>")
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

function selJSxx(i){
    var dataId = $("#DATA_ID").val();
    var dtldataId = $("#DATA_DTL_ID"+i).val();
    $("#con").val(" DATA_DTL_ID not in (select DATA_DTL_ID from PROJECT_DATA_DICTIONARY_DTL start with data_dtl_id ='"+ dtldataId +"' connect by prior data_dtl_id = par_data_dtl_id) and DATA_ID = '" + dataId + "'");
	var obj = selCommon('System_18', false, 'PAR_DATA_DTL_ID'+i, 'DATA_DTL_ID', 'forms[0]','PAR_DATA_DTL_ID'+i+',PAR_DATA_DTL_CODE'+i+',PAR_DATA_DTL_NAME'+i, 'DATA_DTL_ID,DATA_DTL_CODE,DATA_DTL_NAME', 'con');
}

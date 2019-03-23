/**
 * @prjName:喜临门营销平台
 * @fileName:Trainreq_Edit_Chld
 * @author ghx
 * @time   2014-02-28 14:01:04 
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
	
	if(function_exists('formCheckedEx')){
		return formCheckedEx();
	}
	
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	
	var REQ_REASON = parent.topcontent.document.getElementById("REQ_REASON").value;
	if(REQ_REASON==""){
	   parent.showErrorMsg("请输入申请理由");
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
		url: "trainreq.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"TRAIN_REQ_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","trainreq.shtml?action=toFrame");
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
		url: "trainreq.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"TRAIN_REQ_ID":selRowId},
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
		url: "trainreq.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"TRAIN_REQ_DTL_IDS":ids},
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
   newGoBack("trainreq.shtml?action=toFrame");
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
var selTrainType = "";

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
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='TRAIN_REQ_DTL_ID' id='TRAIN_REQ_DTL_ID"+rownum+"' name='TRAIN_REQ_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='center'><select json='TRAIN_TYPE' id='TRAIN_TYPE"+rownum+"' name='TRAIN_TYPE' onchange='getDynamicSel("+rownum+");' value='"+arrs[1]+"'></select></td>")
		    .append("<td nowrap align='center'><input type='hidden' style='width:155' json='TRAIN_OBJECT_ID' id='TRAIN_OBJECT_ID"+rownum+"' name='TRAIN_OBJECT_ID' value='"+arrs[2]+"'/><input  json='TRAIN_OBJECT_NO' id='TRAIN_OBJECT_NO"+rownum+"' name='TRAIN_OBJECT_NO'  autocheck='true' label='培训对象编号'  type='text'   inputtype='string' readonly='readonly'    mustinput='true'     maxlength='50'  value='"+arrs[3]+"'/>&nbsp;<img align='absmiddle' id='selObj"+rownum+"' name='selObj' style='cursor:hand' src='/sleemon/styles/newTheme/images/plus/select.gif' />&nbsp;</td>")
            .append("<td nowrap align='center'><input type='text' json='TRAIN_OBJECT_NAME' id='TRAIN_OBJECT_NAME"+rownum+"' name='TRAIN_OBJECT_NAME'  autocheck='true' label='培训对象名称'     inputtype='string'  readonly='readonly' maxlength='30' value='"+arrs[4]+"'/>&nbsp;</td>")
		    .append("<td nowrap align='center'><input type='text' json='REMARK' id='REMARK"+rownum+"' name='REMARK' value='"+arrs[5]+"'/></td>")		    
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
    var trainType = arrs[1];
    if("" == trainType && "" != selTrainType){
    	trainType = selTrainType;
    }
    
    SelDictShow("TRAIN_TYPE"+rownum,"BS_104",trainType,"");
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

//根据培训类型，动态通用选取
function getDynamicSel(rowNum){	
	clearSelValue(rowNum);
	
	var trainType = $("#TRAIN_TYPE"+rowNum).val();
	var selObj = $("#selObj"+rowNum);
	
	selObj.click(function() { 

		var obj;
		switch(trainType){
			case '导购员':			
				obj = selTerm(rowNum);				
				break;
			case '渠道':	
				obj = selChann(rowNum);
				break;
			case '内部':
				obj = selDept(rowNum);
				break;
		}
			
		var len = obj[0];
		if(len>1){
			selTrainType = trainType;
			
			for(var j=1;j<len;j++){
				var index = rowNum + j;
				
				if($("#TRAIN_TYPE"+index +" option").length == 0){
					$("#TRAIN_TYPE"+index).append("<option value='"+trainType+"'>"+trainType+"</option>");
				}
			}
		}
		
		var rtnArr = multiSelCommonSet(obj,"TRAIN_TYPE,TRAIN_OBJECT_ID,TRAIN_OBJECT_NO,TRAIN_OBJECT_NAME",rowNum);
	
	});
}

function clearSelValue(rowNum){
	$("#TRAIN_OBJECT_ID" + rowNum).val("");
	$("#TRAIN_OBJECT_NO" + rowNum).val("");
	$("#TRAIN_OBJECT_NAME" + rowNum).val("");	
	$("#selObj"+rowNum).unbind('click');
	
	selTrainType = "";
}

function selTerm(rowNum){
	return selCommon('BS_27', true, 'TRAIN_OBJECT_ID'+ rowNum, 'TERM_ID', 'forms[0]','TRAIN_OBJECT_ID'+ rowNum +',TRAIN_OBJECT_NO'+ rowNum +',TRAIN_OBJECT_NAME'+ rowNum, 'TERM_ID,TERM_NO,TERM_NAME', 'selectTerminal')
}
function selChann(rowNum){
	return selCommon('BS_19', true, 'TRAIN_OBJECT_ID'+ rowNum, 'CHANN_ID', 'forms[0]','TRAIN_OBJECT_ID'+ rowNum +',TRAIN_OBJECT_NO'+ rowNum +',TRAIN_OBJECT_NAME'+ rowNum, 'CHANN_ID,CHANN_NO,CHANN_NAME','selectChann')
}
function selDept(rowNum){
	return selCommon('System_9', true, 'TRAIN_OBJECT_ID'+ rowNum, 'BMXXID', 'forms[0]','TRAIN_OBJECT_ID'+ rowNum +',TRAIN_OBJECT_NO'+ rowNum +',TRAIN_OBJECT_NAME'+ rowNum, 'BMXXID,BMBH,BMMC', 'selectDept');
}

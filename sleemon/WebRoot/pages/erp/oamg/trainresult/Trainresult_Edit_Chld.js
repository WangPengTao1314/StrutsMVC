/**
 * @prjName:喜临门营销平台
 * @fileName:Trainresult_Edit_Chld
 * @author ghx
 * @time   2014-07-10 
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
	$.ajax({
		url: "trainresult.shtml?action=childEdit",
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
		url: "trainresult.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"TRAIN_RESULT_DTL_IDS":ids},
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
   newGoBack("trainresult.shtml?action=toFrame");
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
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='TRAIN_RESULT_DTL_ID' id='TRAIN_RESULT_DTL_ID"+rownum+"' name='TRAIN_RESULT_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='center'><input type='hidden' json='TRAIN_PER_ID' id='TRAIN_PER_ID"+rownum+"' name='TRAIN_PER_ID' /><input  json='TRAIN_PER_NO' id='TRAIN_PER_NO"+rownum+"' name='TRAIN_PER_NO'  autocheck='true' label='培训人员编号'  type='text'   inputtype='string' readonly='readonly'    mustinput='true'     maxlength='50'  value='"+arrs[1]+"'/>&nbsp;<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selEmployee("+rownum+")' />&nbsp;</td>")
            .append("<td nowrap align='center'><input type='text' json='TRAIN_PER_NAME' id='TRAIN_PER_NAME"+rownum+"' name='TRAIN_PER_NAME'  autocheck='true' label='培训人员姓名'     inputtype='string'  readonly='readonly' maxlength='30' value='"+arrs[2]+"'/>&nbsp;</td>")
		    .append("<td nowrap align='center'><input type='text' json='THE_ORGAN' id='THE_ORGAN"+rownum+"' name='THE_ORGAN'  readonly='readonly' value='"+arrs[3]+"'/></td>")
		    .append("<td nowrap align='center'><input type='text' json='THE_POST' id='THE_POST"+rownum+"' name='THE_POST'  readonly='readonly' value='"+arrs[4]+"'/></td>")
		    .append("<td nowrap align='center'><select json='SIGN_STATE' id='SIGN_STATE"+rownum+"' name='SIGN_STATE' value='"+arrs[5]+"'></select></td>")
		    .append("<td nowrap align='center'><select json='TRAIN_PER' id='TRAIN_PER"+rownum+"' name='TRAIN_PER' value='"+arrs[6]+"'></select></td>")
		    .append("<td nowrap align='center'><select json='TRAIN_ASSES' id='TRAIN_ASSES"+rownum+"' name='TRAIN_ASSES' value='"+arrs[7]+"'></select></td>")
		    .append("<td nowrap align='center'><select json='TRAIN_OVERALL' id='TRAIN_OVERALL"+rownum+"' name='TRAIN_OVERALL' value='"+arrs[8]+"'></select></td>")
		    .append("<td nowrap align='center'><input type='text' json='REMARK' id='REMARK"+rownum+"' name='REMARK' value='"+arrs[9]+"'/></td>")
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});    
    
    var SIGN_STATE = arrs[5];
    if(""==SIGN_STATE||null==SIGN_STATE){
    	SIGN_STATE ='正常';
    }
    
    var TRAIN_PER = arrs[6];
    if(""==TRAIN_PER||null==TRAIN_PER){
    	TRAIN_PER ='合格';
    }
    
    var TRAIN_ASSES = arrs[7];
    if(""==TRAIN_ASSES||null==TRAIN_ASSES){
    	TRAIN_ASSES ='合格';
    }
    
    var TRAIN_OVERALL = arrs[8];
    if(""==TRAIN_OVERALL||null==TRAIN_OVERALL){
    	TRAIN_OVERALL ='合格';
    }
   
    SelDictShow("SIGN_STATE"+rownum,"BS_107",SIGN_STATE,"");
    SelDictShow("TRAIN_PER"+rownum,"BS_108",TRAIN_PER,"");
    SelDictShow("TRAIN_ASSES"+rownum,"BS_108",TRAIN_ASSES,"");
    SelDictShow("TRAIN_OVERALL"+rownum,"BS_108",TRAIN_OVERALL,"");
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

function selEmployee(rowNum){
	var obj = selCommon('BS_10', true, 'TRAIN_PER_ID'+ rowNum, 'RYXXID', 'forms[0]','TRAIN_PER_ID'+ rowNum + ',TRAIN_PER_NO'+ rowNum +',TRAIN_PER_NAME'+ rowNum +',THE_POST'+ rowNum +',THE_ORGAN'+ rowNum, 'RYXXID,RYBH,XM,ZW,BMMC', 'selectEmployee');
	var rtnArr = multiSelCommonSet(obj,"TRAIN_PER_ID,TRAIN_PER_NO,TRAIN_PER_NAME,THE_POST,THE_ORGAN",rowNum);
}


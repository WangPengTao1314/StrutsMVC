/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Edit_Chld
 * @author lyg
 * @time   2014-01-26 14:46:31 
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
	/* 
	var selRowId =  $("#CHANN_CHECK_PLAN_ID").val();
	var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	if(state=="启用"){
	   btnDisable(["add","delete"]);
	}*/
	
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
			
			if(checkChildMyself()!=''){
				alert(checkChildMyself());
			}else{
				childSave();
			}
		}else{
			if(checkChildMyself()!=''){
				alert(checkChildMyself());
			}else{
				//编辑页面，子表没有选中记录也可以提交，故不需要校验。
				allSave();
			}
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			//allSave();
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
	/*
	if(!formMutiTrChecked()){
		return;
	}*/
	var selRowId = $("#CHANN_CHECK_PLAN_ID").val();
	var childData ;
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
    var chkLen = $("#jsontb tr:gt(0)").length;
	var len = $("#jsontb tr:gt(0) input:checked").length;
    if($("#jsontb tr:gt(0)").length>0){
    	if($("#jsontb tr:gt(0) input:checked").length==0){
    	  parent.showErrorMsg("请至少选择一条记录");
		  return;
    	}else{
    	  childData = multiPackJsonData();
    	}
	}
	$.ajax({
		url: "plancheck.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"CHANN_CHECK_PLAN_ID":selRowId,"chkLen":chkLen},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","plancheck.shtml?action=toFrame");
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
		url: "plancheck.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"CHANN_CHECK_PLAN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.parent.topcontent.pageForm.submit();
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function checkChildMyself(){
	
	var trs = $("#jsontb tr:gt(0)");
	var returnValue = "";
	
	trs.each(function(){
		var isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
		if(isEdit){
			var prjScore = "";
			var checkScore = "";
			var prjName = "";
			
			var inputs = $(this).find(":input");
			inputs.each(function(){
			
				if(null != $(this).attr("json")){
					var jsontype = $(this).attr("json");
					if(jsontype=="PRJ_NAME"){
						prjName = $(this).attr("value");
					}
				
					if(jsontype=="PRJ_SCORE"){
						prjScore = $(this).attr("value")-0;
					}
					
					if(jsontype=="CHECK_SCORE"){
						checkScore  = $(this).attr("value")-0;
						if(checkScore>prjScore){
							returnValue = returnValue + prjName + "检查结果得分大于项目分值\n";
						}
					}
				}
			});
		}
	});
	return returnValue;
}


//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var selRowId = parent.document.getElementById("selRowId").value;
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "termrefinecheck.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"TERM_REFINE_CHECK_DTL_IDS":ids,"TERM_REFINE_CHECK_ID":selRowId},
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
   newGoBack("plancheck.shtml?action=toFrame");
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

function disable(){
   //btnDisable(["add","delete"]);
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
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='CHANN_CHECK_PLAN_DTL_ID' name='CHANN_CHECK_PLAN_DTL_ID' id='CHANN_CHECK_PLAN_DTL_ID' value='"+arrs[0]+"'/></td>")
             .append('<input type="hidden" json="CHANN_CHECK_PLAN_ID" id="CHANN_CHECK_PLAN_ID'+rownum+'" name="CHANN_CHECK_PLAN_ID"  label="门店精致化检查ID" maxlength="32" value="'+arrs[1]+'"/>')
            .append('<td nowrap align="center"><input  json="PRJ_TYPE" id="PRJ_TYPE'+rownum+'" name="PRJ_TYPE"  autocheck="true" label="门店精致化检查项目类型"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>' +
            '<img  align="absmiddle" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrj('+rownum+')"/></td>')
            .append('<td nowrap align="center"><input  json="PRJ_CODE" id="PRJ_CODE'+rownum+'" name="PRJ_CODE"  autocheck="true" label="检查项目编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="PRJ_NAME" id="PRJ_NAME'+rownum+'" name="PRJ_NAME"  autocheck="true" label="检查项目名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="PRJ_SCORE" id="PRJ_SCORE'+rownum+'" name="PRJ_SCORE"  autocheck="true" label="项目分值"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<input type="hidden" id="DATA_ID'+rownum+'"')
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}
//门店精致化检查项目类型通用选取
function selcPrj(rownum){
	var obj=selCommon("BS_88", true, "PRJ_TYPE"+rownum, "DATA_NAME", "forms[0]","PRJ_TYPE"+rownum+",PRJ_CODE"+rownum+",PRJ_NAME"+rownum+",PRJ_SCORE"+rownum, "DATA_NAME,DATA_DTL_CODE,DATA_DTL_NAME,DATA_DTL_DES_1", "selectData");
	rtnArr=multiSelCommonSet(obj,"PRJ_TYPE,PRJ_CODE,PRJ_NAME,PRJ_SCORE",rownum);
}

function countScore(i){
	var temp_ORDER_NUM=$("#CHECK_SCORE"+i).val();//分数
	
	var inputs = $("input[name='CHECK_SCORE']");
	var total=0;
	inputs.each(function(){
		var v = $(this).val();
		if(""==v){
			v=0;
		}
		var CHECK_SCORE=isNaN(v)?0:parseFloat(v);
		total = isNaN(total+CHECK_SCORE)?0:total+CHECK_SCORE;
	});
	parent.topcontent.setScoreVal(total);
}

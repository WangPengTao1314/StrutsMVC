/**
 *@module 渠道管理-工作计划管理
 *@func   工作计划维护
 *@version 1.1
 *@author zcx
 */
$(function(){

 	init();//页面初始化
	
	$("#add").click(function(){
	    addRow(); 
	});
	
	$("#delete").click(function(){
	    var actionType = getActionType();
	    var selRowId =  $("#WORK_PLAN_ID").val();
	    var state;
	    if("list" == actionType){
           //state = parent.topcontent.document.getElementById("state"+selRowId).value;
        } else {//主表是编辑页面
           //state = parent.topcontent.document.getElementById("state").value;
        }
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
			var RYBH  = parent.bottomcontent.document.getElementsByName("RYBH");
			parent.topcontent.document.getElementById("TOTAL_UP_REPORT_NUM").value = RYBH.length;
			//parent.topcontent.document.getElementById("TOTAL_UP_REPORT_NUM").value = "";
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
			//对于选中的零星领料单明细校验
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

function gobacknew(){
   newGoBack("workplanmage.shtml?action=toFrames");
}

//子表保存
function childSave(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var jsonData = multiPackJsonDataT();
	$.ajax({
		url: "workplanmage.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"WORK_PLAN_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    saveSuccess("保存成功","workplanmage.shtml?action=toFrames");
			    parent.setRefreshFlag(false);
				parent.topcontent.location.reload();
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
	var selRowId = $("#WORK_PLAN_ID").val();
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+""+$(this).val()+",";
	});
	ids = ids.substr(0,ids.length-1);
	var actionType = getActionType(); 
	$.ajax({
		url: "workplanmage.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"WORK_PLAN_DTL_IDs":ids,"WORK_PLAN_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				var TOTAL_UP_REPORT_NUM = jsonResult.messages;
				parent.topcontent.document.getElementById("TOTAL_UP_REPORT_NUM").value = TOTAL_UP_REPORT_NUM;
				parent.setRefreshFlag(false);
				parent.topcontent.location.reload();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
	
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
	} */
	var selRowId = $("#WORK_PLAN_ID").val();
	if(selRowId=="自动生成"){
	   selRowId = "";
	}
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData = multiPackJsonDataT();
	$.ajax({
		url: "workplanmage.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"WORK_PLAN_ID":utf8(selRowId)},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","workplanmage.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function multiPackJsonDataT(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		jsonData = jsonData+"{";
		var isFirst = true;
		var inputs = $(this).find(":input");
		inputs.each(function(){
			if(null != $(this).attr("json")){
				var key;
				var value;
				var type = $(this).attr("type");
				if(!isFirst && "checkbox" == type){
					key = $(this).attr("json");
					if($(this).attr("checked")){
						value= 1;
					}else{
						value= 0;
					}
				}else if("radio" == type){
					if($(this).attr("checked")){
						key = $(this).attr("json");
						value= $(this).attr("value");
					}
				}else{
					key = $(this).attr("json");
					value = $(this).attr("value");
					
					isFirst = false;
				}
				var inputtype = $(this).attr("inputtype");
				jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
			}
		});
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}

//table增加一行
function addRow(arrs){
     // 减去allCheck的checkBox 
     var len = $("input[type='checkbox']").length - 1;
	 if(null == arrs){
		arrs = ['','','','','','','',''];
	 }
	 //样式行   
	 var rownum = $("#jsontb tr").length;
	 var classrow = rownum% 2;
	 rownum=_row_num;_row_num++;
	 $("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	 $("#jsontb tr:last-child")
	        .append("<td nowrap align='center' width='10%'><input type='checkbox' style='height:12px;valign:middle' json='WORK_PLAN_DTL_ID' id='WORK_PLAN_DTL_ID"+rownum+"' name='WORK_PLAN_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
	        .append('<td nowrap align="center" width="45%"><input type="hidden" json="WORK_PLAN_ID" id="WORK_PLAN_ID'+rownum+'" name="WORK_PLAN_ID'+rownum+'" value="'+arrs[1]+'"/><input type="hidden" json="RYXXID"  id="RYXXID'+rownum+'"  name="RYXXID"  label="员工信息ID" value="'+arrs[2]+'"/><input  json="RYBH"  id="RYBH'+rownum+'" name="RYBH" size="40"  autocheck="true" label="人员编号" value="'+arrs[3]+'" type="text"   inputtype="string"   readonly    mustinput="true" />' +
                    '<img  align="absmiddle" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrj('+rownum+')"/></td>')
		    .append("<td nowrap align='center' width='45%'><input type='text' json='RYMC'  id='RYMC"+rownum+"' value='"+arrs[4]+"'  name='RYMC'  size='40' label='员工姓名'/></td>")
     $("#RYBH"+rownum).val(arrs[3]);
	 $("#RYMC"+rownum).val(arrs[4]);
	 
	 if(parent.topcontent.document.getElementById("TOTAL_UP_REPORT_NUM")!=null){
	   parent.topcontent.document.getElementById("TOTAL_UP_REPORT_NUM").value = document.getElementsByName("RYBH").length;
	 } 
}

//人员信息
function selcPrj(rownum){
	var obj=selCommon("BS_0", true, "RYXXID"+rownum+",RYBH"+rownum+",RYMC"+rownum,'RYXXID,RYBH,XM', "forms[0]","RYXXID"+rownum+",RYBH"+rownum+",RYMC"+rownum,'RYXXID,RYBH,XM', "selectData");
	rtnArr=multiSelCommonSet(obj,"RYXXID,RYBH,RYMC",rownum);
}

function  getSaleAmount(){
    var len = document.getElementsByName("PLAN_SALE_AMOUNT").length;
    var str = 0;
    for(var i=0;i<len;i++){
      if(document.getElementsByName("PLAN_SALE_AMOUNT")[i].value !=""){
          var amount = parseFloat(document.getElementsByName("PLAN_SALE_AMOUNT")[i].value);
          str+=amount;
      }
    }
   document.getElementById("PLAN_SALE_AMOUNT_TOTAL").value=str;
}

function getChannAmount(){
    var len = document.getElementsByName("CHANN_SALE_AMOUNT").length;
    var str = 0;
    for(var i=0;i<len;i++){
      if(document.getElementsByName("CHANN_SALE_AMOUNT")[i].value !=""){
          var amount = parseFloat(document.getElementsByName("CHANN_SALE_AMOUNT")[i].value);
          str+=amount;
      }
    }
   document.getElementById("CHANN_SALE_AMOUNT_TOTAL").value=str;
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
	//添加浮动按钮层的监听
	addFloatDivListener();
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}

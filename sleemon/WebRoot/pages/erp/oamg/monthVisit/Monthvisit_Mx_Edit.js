/**
 *@module 渠道管理-拜访管理
 *@func   月度拜访计划维护
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
	    var selRowId =  $("#MONTH_VISIT_PLAN_ID").val();
	    var state;
	    if("list" == actionType){
           state = parent.topcontent.document.getElementById("state"+selRowId).value;
        } else {//主表是编辑页面
           state = parent.topcontent.document.getElementById("state").value;
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
   newGoBack("monthVisit.shtml?action=toFrames");
}

//子表保存
function childSave(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var jsonData = multiPackJsonDataT();
	
	var r   = /^\+?[1-9][0-9]*$/;　　//正整数     
	var PLAN_VISIT_NUM = parent.bottomcontent.document.getElementsByName("PLAN_VISIT_NUM");
	for(var i=1;i<PLAN_VISIT_NUM.length+1;i++){
	   var str1 = parent.bottomcontent.document.getElementById("PLAN_VISIT_NUM"+i).value;
       if(!r.test(str1)){
          parent.showErrorMsg("拜访次数请输入正整数");
	      return ;
       }
	}
	
	$.ajax({
		url: "monthVisit.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"MONTH_VISIT_PLAN_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    saveSuccess("保存成功","monthVisit.shtml?action=toFrames");
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
	var selRowId = $("#MONTH_VISIT_PLAN_ID").val();
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+""+$(this).val()+",";
	});
	ids = ids.substr(0,ids.length-1);
	var actionType = getActionType(); 
	$.ajax({
		url: "monthVisit.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"MONTH_VISIT_PLAN_DTL_IDs":ids,"MONTH_VISIT_PLAN_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				var PLAN_VISIT_NUM_TOTAL = jsonResult.messages;
				if(parent.topcontent.document.getElementById("PLAN_VISIT_NUM_TOTAL")!=null){
				  parent.topcontent.document.getElementById("PLAN_VISIT_NUM_TOTAL").value = PLAN_VISIT_NUM_TOTAL;
				}
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
	
	var r   = /^\+?[1-9][0-9]*$/;　　//正整数     
	var PLAN_VISIT_NUM = parent.bottomcontent.document.getElementsByName("PLAN_VISIT_NUM");
	for(var i=1;i<PLAN_VISIT_NUM.length+1;i++){
	   var str1 = parent.bottomcontent.document.getElementById("PLAN_VISIT_NUM"+i).value;
       if(!r.test(str1)){
          parent.showErrorMsg("拜访次数请输入正整数");
	      return ;
       }
	}
	
	var YEAR = parent.topcontent.document.getElementById("PLAN_YEAR").value;
	if(YEAR==""){
	   parent.showErrorMsg("请选择计划年份");
	   return ;
	}
	
	var MONTH = parent.topcontent.document.getElementById("PLAN_MONTH").value;
	if(MONTH==""){
	   parent.showErrorMsg("请选择计划月份");
	   return ;
	}
	
	var selRowId = $("#MONTH_VISIT_PLAN_ID").val();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData = multiPackJsonDataT();
	$.ajax({
		url: "monthVisit.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"MONTH_VISIT_PLAN_ID":utf8(selRowId)},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","monthVisit.shtml?action=toFrames");
				parent.topcontent.location.reload();
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
	        .append('<td nowrap align="center" width="23%"><input type="hidden" json="MONTH_VISIT_PLAN_DTL_ID" id="MONTH_VISIT_PLAN_DTL_ID'+rownum+'" name="MONTH_VISIT_PLAN_DTL_ID" value="'+arrs[0]+'"/>' +
	        '<select json="VISIT_OBJ_TYPE"  id="VISIT_OBJ_TYPE'+rownum+'"  name="VISIT_OBJ_TYPE"  label="拜访类型" onchange="upVisitType('+rownum+')" ></select></td>')
	        .append('<td nowrap align="center" width="23%"><select json="VISIT_TYPE"  id="VISIT_TYPE'+rownum+'"  name="VISIT_TYPE"  label="拜访方式" ></select></td>')
		    .append("<td nowrap align='center' width='23%'><input type='text'   json='PLAN_VISIT_NUM'  id='PLAN_VISIT_NUM"+rownum+"' value='"+arrs[2]+"'  name='PLAN_VISIT_NUM'  size='40' label='计划拜访次数' onblur='getNumType("+rownum+");getTotalNum("+rownum+")' value='1'/></td>")
     SelDictShow("VISIT_OBJ_TYPE"+rownum, "BS_142", arrs[1], "");
	 if("加盟商"==arrs[1]){
		 SelDictShow("VISIT_TYPE"+rownum,"BS_156",arrs[3],"");
	 }else if("门店"==arrs[1]){
		 SelDictShow("VISIT_TYPE"+rownum,"BS_155",arrs[3],"");
	 }
     $("#PLAN_VISIT_NUM"+rownum).val(arrs[2]);
}

function getNumType(param){
    var r   = /^\+?[1-9][0-9]*$/;　　//正整数     
	var PLAN_VISIT_NUM = parent.bottomcontent.document.getElementsByName("PLAN_VISIT_NUM");
	for(var i=1;i<PLAN_VISIT_NUM.length+1;i++){
	   var str1 = parent.bottomcontent.document.getElementById("PLAN_VISIT_NUM"+i).value;
       if(!r.test(str1)){
          parent.showErrorMsg("拜访次数请输入正整数");
	      return ;
       }
	}
}

//获取到计划拜访次数
function getTotalNum(rownum){
    var str = 0;
    var length = document.getElementsByName("PLAN_VISIT_NUM").length;
    for(var i=1;i<length+1;i++){
       var str1 = parseInt(document.getElementById("PLAN_VISIT_NUM"+i).value);
       str = str+str1;
    }
    if(!isNaN(str)){
      parent.topcontent.document.getElementById("PLAN_VISIT_NUM_TOTAL").value = str;
    }
}

//人员信息
function selcPrj(rownum){
	var VISIT_OBJ_TYPE = $("#VISIT_OBJ_TYPE"+rownum).val();
	if(VISIT_OBJ_TYPE=="加盟商"){
	  var obj=selCommon("BS_19", true, "VISIT_OBJ_ID"+rownum+",VISIT_OBJ_NO"+rownum+",VISIT_OBJ_NAME"+rownum,'CHANN_ID,CHANN_NO,CHANN_NAME', "forms[0]","VISIT_OBJ_ID"+rownum+",VISIT_OBJ_NO"+rownum+",VISIT_OBJ_NAME"+rownum,'CHANN_ID,CHANN_NO,CHANN_NAME', "selectParamsChann");
	  rtnArr=multiSelCommonSet(obj,"VISIT_OBJ_ID,VISIT_OBJ_NO,VISIT_OBJ_NAME",rownum);
	}
	if(VISIT_OBJ_TYPE=="门店"){
	  var obj=selCommon("BS_82", true, "VISIT_OBJ_ID"+rownum+",VISIT_OBJ_NO"+rownum+",VISIT_OBJ_NAME"+rownum,'TERM_ID,TERM_NO,TERM_NAME', "forms[0]","VISIT_OBJ_ID"+rownum+",VISIT_OBJ_NO"+rownum+",VISIT_OBJ_NAME"+rownum,'TERM_ID,TERM_NO,TERM_NAME', "selectParams");
	  rtnArr=multiSelCommonSet(obj,"VISIT_OBJ_ID,VISIT_OBJ_NO,VISIT_OBJ_NAME",rownum);
	}
	if(VISIT_OBJ_TYPE=="城市"){
	  var obj=selCommon("BS_145", true, "VISIT_OBJ_ID"+rownum+",VISIT_OBJ_NO"+rownum+",VISIT_OBJ_NAME"+rownum,'ZONE_ID,ZONE_ID,CITY', "forms[0]","VISIT_OBJ_ID"+rownum+",VISIT_OBJ_NO"+rownum+",VISIT_OBJ_NAME"+rownum,'ZONE_ID,ZONE_ID,CITY', "zoneConditionCity");
	  rtnArr=multiSelCommonSet(obj,"VISIT_OBJ_ID,VISIT_OBJ_NO,VISIT_OBJ_NAME",rownum);
	}
	if(VISIT_OBJ_TYPE==""){
	   parent.showErrorMsg("请先选择拜访对象类型");
	   return ;
	}
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
function upVisitType(id){
	var VISIT_OBJ_TYPE=$("#VISIT_OBJ_TYPE"+id).val();
	 if("加盟商"==VISIT_OBJ_TYPE){
		 SelDictShow("VISIT_TYPE"+id,"BS_156","","");
	 }else if("门店"==VISIT_OBJ_TYPE){
		 SelDictShow("VISIT_TYPE"+id,"BS_155","","");
	 }
}

function  load(){
  var Tflag =  $("#Tflag").val();
  if(Tflag=="1"){
   document.getElementById("delete").style.display="block";
  }
  

}

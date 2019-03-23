/**
 *@module 渠道管理-装修管理
 *@func   装修整改单维护
 *@version 1.1
 *@author zcx
 */
$(function(){

 	init();//页面初始化
	$("#add").click(function(){
	    addRow(); 
	});
	
	$("clickAddRow").dblclick(function(){
	    addRow(); 
	});
	
	$("#delete").click(function(){
	    var actionType = getActionType();
	    var selRowId = parent.document.getElementById("selRowId").value;
	    var state;
	    if("list" == actionType){
           state = parent.topcontent.document.getElementById("state"+selRowId).value;
        } else {//主表是编辑页面
           state = parent.topcontent.document.getElementById("state").value;
        }
	   // if('启用' == state || '停用' == state){
	       //parent.showErrorMsg("启用、停用状态的数据不允许删除！");
		   //return;
	    //}
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

function gobacknew(){
   newGoBack("rnvtnreform.shtml?action=toFrames");
}

//子表保存
function childSave(){
	var selRowId =  parent.document.getElementById("selRowId").value; //$("#RNVTN_REFORM_ID").val();
	var jsonData = multiPackJsonData();
	var len = $("input[type='checkbox']").length-1;
	var str = "";
	for(var i=0;i<len;i++){
	    var score  = $("#SCORE"+(i+1)).val();
	    if(score != ""){
	    var PRJ_SCORE = $("#PRJ_SCORE"+(i+1)).val();
	    if(eval(score)>eval(PRJ_SCORE)){
	          parent.showErrorMsg("验收得分不能大于项目分值");
	          str=1;
	          return;
	    }
	   } else {
	      parent.showErrorMsg("请输入验收得分");
		  return;
	   }
	}
	if(str!="1"){
		$.ajax({
			url: "rnvtnreform.shtml?action=childEdit",
			type:"POST",
			dataType:"text",
			data:{"childJsonData":jsonData,"RNVTN_REFORM_ID":utf8(selRowId)},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
				    saveSuccess("保存成功","rnvtnreform.shtml?action=toFrames");
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	 }
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
	var actionType = getActionType(); 
	$.ajax({
		url: "rnvtnreform.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"RNVTN_REFORM_DTL_IDs":ids,"RNVTN_REFORM_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				  //saveSuccess("删除成功","rnvtncheck.shtml?action=toFrames");
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
	
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = $("#RNVTN_REFORM_ID").val();
	
	var chkLen = $("#jsontb tr:gt(0)").length;
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var len = $("#jsontb tr:gt(0) input:checked").length;
    if($("#jsontb tr:gt(0)").length>0){
    	if($("#jsontb tr:gt(0) input:checked").length==0){
    	  parent.showErrorMsg("请至少选择一条记录");
		  return;
    	}else{
    	  childData = multiPackJsonData();
    	}
	}
	var str = "";
	var len = $("input[type='checkbox']").length-1;
	for(var i=0;i<len;i++){
	    var score  = $("#SCORE"+(i+1)).val();
	    if(score !="") {
	    var PRJ_SCORE = $("#PRJ_SCORE"+(i+1)).val();
	    if(eval(score)>eval(PRJ_SCORE)){
	          parent.showErrorMsg("验收得分不能大于项目分值");
	          str=1;
	          return;
	    }
	   } else {
	       parent.showErrorMsg("请输入验收得分");
		   return;
	   }
	}
	if(str!="1"){
	$.ajax({
		url: "rnvtnreform.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"RNVTN_REFORM_ID":utf8(selRowId),"chkLen":chkLen},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","rnvtnreform.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
  }
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

function  getTotal(params,len){
   
   var value = params.value;
   var reg = /^\d+$/; 
   if( value.constructor === String ){    
       var re = value.match( reg );     
       if(re==null){
         parent.showErrorMsg("验收得分请输入合法数据");
         return;
       }else {
           var str =0;
	       var len = parseInt(len+2);
	       for(var i=1;i<len;i++){
	       var score = $("#SCORE"+i).val();
	       var PRJ_SCORE = $("#PRJ_SCORE"+i).val();
 	       if(eval(score)>eval(PRJ_SCORE)){
	          parent.showErrorMsg("验收得分不能大于项目分值");
	          return;
	       }
	       if(!isNaN(str)){
	         str =  eval(str)+eval(score);
	       }
         }
       }
    }   
  }
				
//table增加一行
function addRow(arrs){
    // 减去allCheck的checkBox 
    var len = $("input[type='checkbox']").length - 1;
	if(null == arrs){
		arrs = ['','','','','',''];
	}
	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'>"
		    			+"<input type='checkbox' style='height:12px;valign:middle' json='RNVTN_REFORM_DTL_ID' id='RNVTN_REFORM_DTL_ID"+rownum+"' name='RNVTN_REFORM_DTL_ID"+rownum+"'  value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="center"><input  json="PRJ_TYPE"  id="PRJ_TYPE'+rownum+'" name="PRJ_TYPE'+rownum+'"   autocheck="true" label="验收项目类型"  type="text"   inputtype="string"   readonly    mustinput="true" />' +
                    '<img  align="absmiddle" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrj('+rownum+')"/></td>')
		    .append('<td nowrap align="center"><input json="PRJ_NAME"  id="PRJ_NAME'+rownum+'" name="PRJ_NAME'+rownum+'"  autocheck="true" label="验收项目名称"  type="text"   inputtype="string"   readonly    mustinput="true"  /></td>')
		    .append("<td nowrap align='center'>"
		    		+"<input json='PRJ_SCORE' name='PRJ_SCORE"+rownum+"' id='PRJ_SCORE"+rownum+"' autocheck='true' label='项目分值'  inputtype='string' mustinput='true' readonly /></td>")
		    .append("<td nowrap align='center'><input type='text' json='CHECK_SCORE' id='SCORE"+rownum+"' name='SCORE"+rownum+"' size='40' label='验收得分' autocheck='true' inputtype='float' valueType='4,2' maxlength='4' mustinput='true' onblur='getTotal(this,"+len+")'/></td>")
		    .append("<td nowrap align='center'><input type='text' json='CHECK_REMARK' id='CHECK_REMARK"+rownum+"' name='CHECK_REMARK"+rownum+"' size='40' label='验收意见' autocheck='true' inputtype='string'   maxlength='250' /></td>")
	$("#PRJ_TYPE"+rownum).val(arrs[1]);
	$("#PRJ_NAME"+rownum).val(arrs[2]);
	$("#PRJ_SCORE"+rownum).val(arrs[3]);
	$("#SCORE"+rownum).val(arrs[4]);
	$("#CHECK_REMARK"+rownum).val(arrs[5]);
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
	$(this).parent().find("input[type='checkbox']").attr("checked","checked");
    });
	InitFormValidator(0);
}



//装修验收项目类型通用选取
function selcPrj(rownum){
    var obj=selCommon("BS_99", true, "PRJ_TYPE"+rownum+",PRJ_NAME"+rownum+",PRJ_SCORE"+rownum,'PAR_DATA_DTL_NAME,DATA_DTL_NAME,DATA_DTL_DES_1', "forms[0]","PRJ_TYPE"+rownum+",PRJ_NAME"+rownum+",PRJ_SCORE"+rownum,'PAR_DATA_DTL_NAME,DATA_DTL_NAME,DATA_DTL_DES_1', "selectData");
	rtnArr=multiSelCommonSet(obj,"PRJ_TYPE,PRJ_NAME,PRJ_SCORE",rownum);
}

//装修验收项目名称通用选取
function selcPrjName(rownum){
    var val = $("#PRJ_TYPE"+rownum).val();
    if(val==""){
       parent.showErrorMsg("请选择验收项目类型");
       return;
    }
    document.getElementById("selectDataName").value = "PAR_DATA_DTL_NAME='"+val+"'" ;
    var obj=selCommon("BS_100", false, "PRJ_NAME"+rownum,'DATA_DTL_NAME', "forms[0]","PRJ_NAME"+rownum+",PRJ_SCORE"+rownum,'DATA_DTL_NAME,DATA_DTL_DES_1', "selectDataName");
	rtnArr=multiSelCommonSet(obj,"PRJ_NAME",rownum);
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
	//if("list" == actionType){
		$("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//}	
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

 
function Len(str)
{
     var i,sum;
     sum=0;
     for(i=0;i<str.length;i++)
     {
         if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
             sum=sum+1;
         else
             sum=sum+3;
     }
     return sum;
}

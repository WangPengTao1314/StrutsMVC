/**
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
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
	    var selRowId =  $("#RNVTN_SUBST_STD_ID").val();
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
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
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
   newGoBack("decorationallo.shtml?action=toFrames");
}

//子表保存
function childSave(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var jsonData = multiPackJsonData();
	
	var reg = /^\d+$/; 
	var SUBST_AMOUNT = parent.bottomcontent.document.getElementsByName("SUBST_AMOUNT");
	var Amount = "";
	for(var i=0;i<SUBST_AMOUNT.length;i++){
	    Amount = SUBST_AMOUNT[i].value;
	    if(Amount == ""){
		    parent.showErrorMsg("请输入补贴金额");
	        return;
	    } else {
	        var re = Amount.match(reg);     
	        if(re==null){
	         parent.showErrorMsg("补贴金额请输入合法数据");
	         return;
	        } else{
	          if(Amount.length>8){
	            parent.showErrorMsg("补贴金额请小于8位");
	            return;
	          }
	       }
	    }
	}
	
	$.ajax({
		url: "decorationallo.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"RNVTN_SUBST_STD_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    saveSuccess("保存成功","decorationallo.shtml?action=toFrames");
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
	var selRowId = parent.document.getElementById("selRowId").value;
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var actionType = getActionType(); 
	$.ajax({
		url: "decorationallo.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"RNVTN_SUBST_STD_DTL_IDs":ids,"RNVTN_SUBST_STD_DTL_ID":utf8(selRowId)},
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
	var Amount = "";
	var reg = /^\d+$/; 
	var SUBST_AMOUNT = parent.bottomcontent.document.getElementsByName("SUBST_AMOUNT");
	for(var i=0;i<SUBST_AMOUNT.length;i++){
	    Amount = SUBST_AMOUNT[i].value;
	    if(Amount==""){
	        parent.showErrorMsg("请输入补贴金额");
	        return;
	    } else {
	        var re = Amount.match(reg);     
	        if(re==null){
	         parent.showErrorMsg("补贴金额请输入合法数据");
	         return;
	        } else{
	          if(Amount.length>8){
	            parent.showErrorMsg("补贴金额请小于8位");
	            return;
	          }
	       }
	    }
	}
	
	var BRAND = parent.topcontent.document.getElementById("BRAND").value;
	if (BRAND == "") {
		chkCanErrMsg("", "请选择'品牌'");
		return false;
	}
	var selRowId = $("#RNVTN_SUBST_STD_ID").val();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	$.ajax({
		url: "decorationallo.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"RNVTN_SUBST_STD_ID":utf8(selRowId)},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","decorationallo.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='RNVTN_SUBST_STD_DTL_ID' id='RNVTN_SUBST_STD_DTL_ID"+rownum+"' name='RNVTN_SUBST_STD_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="center"><select json="LOCAL_TYPE"  id="LOCAL_TYPE'+rownum+'" name="LOCAL_TYPE'+rownum+'"   autocheck="true" label="商场位置类型"  readonly    mustinput="true"></select></td>')
		    .append("<td nowrap align='center'><input type='text' json='SUBST_AMOUNT' id='SUBST_AMOUNT"+rownum+"' name='SUBST_AMOUNT' size='40' label='补贴金额' onblur='check(this)' autocheck='true' inputtype='float' valueType='8,2'/><font id='reqBC"+rownum+"' color='red'>*</font></td>")
		    .append("<td nowrap align='center'><input type='text' json='REMARK' id='REMARK"+rownum+"' name='REMARK"+rownum+"' size='40' autocheck='true' label='备注' inputtype='string' maxlength='250' /><font id='reqCSZ"+rownum+"' color='red'></font></td>");
	 
	 SelDictShow("LOCAL_TYPE"+rownum, "BS_86", arrs[1], "");
     $("#SUBST_AMOUNT"+rownum).val(arrs[2]);
	 $("#REMARK"+rownum).val(arrs[3]);
	 
	 $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	})
}

//商场位置类型通用选取
function selcPrj(rownum){
	var obj=selCommon("BS_101", false, "LOCAL_TYPE"+rownum,'DATA_DTL_NAME', "forms[0]","LOCAL_TYPE"+rownum,'DATA_DTL_NAME', "selectData");
	rtnArr=multiSelCommonSet(obj,"LOCAL_TYPE",rownum);
}

function check(params){
   var value = params.value;
   var reg = /^\d+$/; 
   if( value.constructor === String ){    
       var re = value.match( reg );     
       if(re==null){
         parent.showErrorMsg("补贴金额请输入合法数据");
         return;
       } else{
          if(value.length>8){
            parent.showErrorMsg("补贴金额请小于8位");
            return;
          }
       }
    }   
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

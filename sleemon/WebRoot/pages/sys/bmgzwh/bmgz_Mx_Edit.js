/**
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
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

//子表保存
function childSave(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "bmgz.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"BMGZID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
				parent.topcontent.document.getElementById("zcd" + selRowId).innerHTML = utf8Decode(jsonResult.messages);
				//saveSuccess("保存成功","bmgz.shtml?action=toFrame");
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
		url: "bmgz.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"BMGZMXIDS":ids,"BMGZID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				if(actionType=="list")
				{
				   parent.setRefreshFlag(false);
				   parent.topcontent.location.reload();
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
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	//var isNew = parent.topcontent.document.getElementById("isNew").value;
	//alert(isNew);
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	$.ajax({
		url: "bmgz.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"BMGZID":utf8(selRowId)},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","bmgz.shtml?action=toFrame");
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
    if(len == 2){
      alert("最多有2条记录");
      return;       
    }
	if(null == arrs){
		arrs = ['','','','','','','',''];
	}

	//样式行   
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'>"
		    			+"<input type='checkbox' style='height:12px;valign:middle' json='BMGZMXID' name='BMGZMXID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append("<td style='display:none'>"
	                     +"<input type='hidden' json='SXH' name='SXH"+rownum+"' value='"+arrs[7]+"'/></td>")
		    .append("<td nowrap align='left'>"
		    		+"<select json='DLX' name='DLX"+rownum+"' id='DLX"+rownum+"' autocheck='true' label='段类型' inputtype='string' mustinput='true'>< option value=''>--请选择--</option></select></td>")
		    .append("<td nowrap align='left'><input type='text' json='DCD' id='DCD"+rownum+"' name='DCD"+rownum+"' size='10' autocheck='true' label='段长度' inputtype='int' mustinput='true' value='"+arrs[2]+"'/></td>")
		    .append("<td nowrap align='left'><input type='text' json='DT' id='DT"+rownum+"' name='DT"+rownum+"' size='10' autocheck='true' label='段头' inputtype='string' value='"+arrs[3]+"'/><font id='reqDt"+rownum+"' color='red'></font></td>")
		    .append("<td nowrap align='left'>"
		            +"<select json='NYS' id='NYS"+rownum+"' name='NYS"+rownum+"' autocheck='true' label='年样式' inputtype='string'><option value=''></option><option value='01'>01</option><option value='2001'>2001</option></select><font id='reqNYS"+rownum+"' color='red'></font></td>")
		    .append("<td nowrap align='left'><input type='text' json='BC' id='BC"+rownum+"' name='BC"+rownum+"' size='10' autocheck='true' label='步长' inputtype='int' value='"+arrs[5]+"'/><font id='reqBC"+rownum+"' color='red'></font></td>")
		    .append("<td nowrap align='left'><input type='text' json='CSZ' id='CSZ"+rownum+"' name='CSZ"+rownum+"' size='10' autocheck='true' label='初始值' inputtype='int' value='"+arrs[6]+"'/><font id='reqCSZ"+rownum+"' color='red'></font></td>");
	
	SelDictShow("DLX"+rownum,"10",arrs[1],"");
	//$("#DLX"+rownum).val(arrs[1]);
	setOption(arrs[1],rownum);
	$("#NYS"+rownum).val(arrs[4]);
	
	$("#DLX"+rownum).change(function(){
	   var selValue = $("#DLX"+rownum).val();
	   setOption(selValue,rownum);
	});
	
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	InitFormValidator(0);
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

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}

function setOption(value,rownum){
   if("年月日" == value){
      //$("#DT"+rownum).attr("disabled","");
      $("#DT"+rownum).removeAttr("disabled");
      $("#BC"+rownum).val("");
      $("#BC"+rownum).attr("disabled","true");
      $("#CSZ"+rownum).val("");
      $("#CSZ"+rownum).attr("disabled","true");
      //$("#NYS"+rownum).attr("disabled","");
      $("#NYS"+rownum).removeAttr("disabled");
      $("#reqDt"+rownum).html('*');
      $("#reqNYS"+rownum).html('*');
      $("#reqBC"+rownum).html('');
      $("#reqCSZ"+rownum).html('');
   }
   if("流水号" == value){
      $("#DT"+rownum).val("");
      $("#DT"+rownum).attr("disabled","true");
      $("#NYS"+rownum).val("");
      $("#NYS"+rownum).attr("disabled","true");
      
      //$("#BC"+rownum).attr("disabled","");
      //$("#CSZ"+rownum).attr("disabled","");
      $("#BC"+rownum).removeAttr("disabled");
      $("#CSZ"+rownum).removeAttr("disabled");
      $("#reqDt"+rownum).html('');
      $("#reqNYS"+rownum).html('');
      $("#reqBC"+rownum).html('*');
      $("#reqCSZ"+rownum).html('*');
   }
   if("" == value){
//      $("#DT"+rownum).attr("disabled","");
//      $("#BC"+rownum).attr("disabled","");
//      $("#CSZ"+rownum).attr("disabled","");
//      $("#NYS"+rownum).attr("disabled","");
      $("#DT"+rownum).removeAttr("disabled");
      $("#BC"+rownum).removeAttr("disabled");
      $("#CSZ"+rownum).removeAttr("disabled");
      $("#NYS"+rownum).removeAttr("disabled");
      $("#reqDt"+rownum).html('');
      $("#reqNYS"+rownum).html('');
      $("#reqBC"+rownum).html('');
      $("#reqCSZ"+rownum).html('');
   }
}

function formCheckedEx(){

   var bz = parent.topcontent.document.getElementById("REMARK");

   if(bz != null){
      var bzVal = bz.value;

      if(Len(bzVal) > 250){
         parent.showErrorMsg("主表备注长度不能大于250！");
         return;
      }
   }
   
   var len = $("#jsontb tr:gt(0) input:checked").length;
   var firstSelVal = " ";
   for(var i=1;i<= len;i++){
      var selVal = $("#DLX"+i).val();
      
      if(firstSelVal == selVal){
         parent.showErrorMsg("段类型不能一样！");
         return false;
      }
      
      firstSelVal = selVal;      
      if(null == selVal || ""==selVal){
	      parent.showErrorMsg("请选择段类型");
	      return false;
      } else {
          if("年月日" == selVal){
          
             var dcd = $("#DCD"+i).val();
             
          
             var dt = $("#DT"+i).val();//段头
             if("" == dt || null==dt){
	             parent.showErrorMsg("请输入段头");
		         return false;
             }
             var nys = $("#NYS"+i).val();//年样式
             if("" == nys || null==nys){
	             parent.showErrorMsg("请选择年样式");
		         return false;
             }
          }
          if("流水号" == selVal){
             
             var dcd = $("#DCD"+i).val();
             
          
             var bc = $("#BC"+i).val();//步长
             if("" == bc || null==bc){
	             parent.showErrorMsg("请输入步长");
		         return false;
             }
             var csz = $("#CSZ"+i).val();//初始值
             if("" == csz || null==csz){
	             parent.showErrorMsg("请输入初始值");
		         return false;
             }
          }
          var dcd = $("#DCD"+i).val();//段长度
          var csz = $("#CSZ"+i).val();//初始值
          if(csz.length > dcd){
              parent.showErrorMsg("初始值长度超过段长度");
		      return false;
          }
      }
   }
   return true;
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

/**
 *@module 系统管理
 *@func 自定义查询
 *@version 1.1
 *@author zhuxw
 */
$(function(){ 
	//页面初始化
	listPageInit("customQuery.shtml?action=queryList"); 
	
	//启用
	$("#open").click(function(){
		var rptPk = $("input[type='radio']:checked").val();
		doRptState("1",rptPk);
	});
	//停用
	$("#close").click(function(){	
		var rptPk = $("input[type='radio']:checked").val();
		doRptState("2",rptPk);
	});
	
	//新增
	$("#add").click(function(){	 
		doAddRpt("");
	}); 
});

//新增、编辑自定义报表
function doAddRpt(rptPk){ 
	var url = "customQuery.shtml?action=addEditRpt&hidRptPk="+rptPk;
	window.location=url;
} 

//展示
function displayRpt(hidRptPk){
	$("#hidRptPk").val(hidRptPk);
	$("#queryType").val("0");
	$("#qryForm").submit();
}

//处理报表状态
function doRptState(state,rptPk){
    if(rptPk==''||rptPk==null)
    {
      showErrorMsg("请至少选择一条记录!");
      return false;
    }
  
	$.ajax({
		url: "customQuery.shtml?action=changeState&rptState="+state+"&rptPk="+rptPk,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){ 
			  $("#queryForm").submit();
			//	query(state); 
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function query(state){
	var url = "customQuery.shtml?action=queryList";
	window.location=url;  
}
//明细编辑
function doEdit(rptPk){
	doAddRpt(rptPk);
}

//删除报表
function doDelete(rptPk){
	//删除
	//TODO:删除判断
	showConfirm("是否确认删除此单据","doRptState('3','"+rptPk+"');");
}
//显示主按钮状态
function setSelOperateEx(obj){
	btnReset(); 
	var state = $.trim($(obj).find("td").eq(8).text()); 
	
	//如果为启用状态，则启用不显示
	if (state == "启用") {	
		btnDisable(["open"]);
	}
	//如果为停用状态，则停用不显示
	else if (state == "停用") {
		btnDisable(["close"]);
	}
	else if (state == "创建"){
		btnDisable(["close"]);
	}
	//非正常状态，则都不显示
	else{
		btnDisable(["open"]);
		btnDisable(["close"]);
	}
}

function doOpen(obj){    
	var rptSql = document.getElementById(obj).value;
	window.showModalDialog('customQuery.shtml?action=openWindow&rptSql='+rptSql,'','dialogwidth=650px; dialogheight=260px; status=no');
}
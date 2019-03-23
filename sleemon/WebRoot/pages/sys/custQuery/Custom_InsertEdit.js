/**
 *@module 系统管理
 *@func 自定义查询
 *@version 1.1
 *@author zhuxw
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	
	//下一步
	$("#next").click(function(){ 
		//系统自动校验
		if(!formChecked('qryForm')){
			return;
		}
		
		//报表SQL
		var rptSql = $("#rptSql").val();
		$.ajax({
			url: "customQuery.shtml?action=checkRptSql&rptSql="+rptSql,
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					$("#qryForm").submit();
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	}); 
	
	//返回
	$("#back").click(function(){ 
//		var url = "customQuery.shtml?action=queryList";
//		window.location=url;
		history.back();
	}); 
	
	//重置
	$("#res").click(function(){ 
		$("#rptName").val("");
		$("#rptSql").val("");
		$("#roleUser").val("");
		$("#roleUserName").val("");
		$("#roleCode").val("");
		$("#roleName").val("");
		$("#workGroupCode").val("");
		$("#workGroupName").val("");
		$("#remark").val("");
		$("input:radio").eq(0).attr("checked",true);
		//$("input[@type=radio]").attr("checked",'0');
	});
	
	//校验正确性
	$("#check").click(function(){ 
		//报表SQL
		var rptSql = $("#rptSql").val();
		 
		$.ajax({
			url: "customQuery.shtml?action=checkRptSql&rptSql="+rptSql,
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					showMsgPanel("报表SQL正确"); 
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	}); 
	
});
 
//选择用户
function selectUser(){ 
	$("#selPzr").val(" YHZT = '启用'");
	var obj=selCommon('System_3', true, 'roleUser', 'XTYHID', 'forms[0]','roleUser,roleUserName', 'XTYHID,YHM', 'selPzr');
}

//选择用户
function selectRole(){ 
	$("#selPzr").val("");
	var obj=selCommon('System_4', true, 'roleCode', 'JSBH', 'forms[0]','roleCode,roleName', 'JSBH,JSMC', 'selPzr');
}

//选择工作组
function selectWorkGroup(){ 
	$("#selPzr").val("");
	var obj=selCommon('System_7', true, 'workGroupCode', 'GZZBH', 'forms[0]','workGroupCode,workGroupName', 'GZZBH,GZZMC', 'selPzr');
}

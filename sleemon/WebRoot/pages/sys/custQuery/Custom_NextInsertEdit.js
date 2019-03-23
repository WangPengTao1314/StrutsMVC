/**
 *@module 系统管理
 *@func 自定义查询
 *@version 1.1
 *@author zhuxw
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	
	//完成，提交数据
	$("#commit_b").click(function(){
		if(mulitiTrValidate()){
			var jsonData = multiPackJsonData();
			if(!formChecked('qryForm')){
				return;
			}  
			
			//判断当前是否有明细
			if(jsonData == '' || jsonData == '[]'){
				showErrorMsg("当前无明细数据或者未选中任何记录");
				return;
			} 
			var messageInfo = "";
			count = 0;
			//校验逻辑名称重复问题
			$("input[name^=logicName]").each(function(){
				var name1 = this.value;
				count++;
				var dtlCount = 1;
				$("input[name^=logicName]").each(function(){ 
					var name2  =this.value; 
					if(name1 === name2 && dtlCount >count){
						showErrorMsg("逻辑名称不能重复");
						messageInfo = "Name";
						return false;
					}
					dtlCount++;
				});
			}); 
			
			if(messageInfo !== ""){
				return false;
			}
			var hidRptPk = $("#hidRptPk").val();
			var rptSql = $("#rptSql").val(); 
			$.ajax({
				url: "customQuery.shtml?action=saveRptDtl&hidRptPk="+hidRptPk+"&rptSql="+rptSql,
				type:"POST",
				async:false,
				dataType:"text",
				data:{"jsonData":jsonData},
				complete: function(xhr){  
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){ 
						$("#messageInfo").val("success");
						reLoadDate(hidRptPk,rptSql);
					}else{
						$("#messageInfo").val("fail"); 
					}
				}
			});
		}
	}); 
	
	//上一步
	$("#previous").click(function(){
		var rptPk = $("#hidRptPk").val(); 
		var url = "customQuery.shtml?action=addEditRpt&hidRptPk="+rptPk; 
		window.location=url;
	}); 
	
	//上一步
	$("#back_").click(function(){ 
		var url = "customQuery.shtml?action=queryList";
		window.location=url;
	}); 
	
	$("input[name^=eid]").each(function(){
		$(this).attr("checked",'true');
	});
}); 


//重新初始化数据
function reLoadDate(hidRptPk,rptSql){ 
	//var messageInfo = $("#messageInfo").val();
	var url = "customQuery.shtml?action=queryList";
	window.location=url;
}
 
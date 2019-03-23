/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	var selId = parent.document.getElementById("selRowId").value;
	if(null == selId || "" == selId){
		/*var treeNodes = parent.leftcontent.getSelNodes();
		if("" != treeNodes){
			$("#SJJGID").val(treeNodes.id);
			var jgbhname = treeNodes.name;
			var index = jgbhname.indexOf("(");
			$("#SJJGBH").val(jgbhname.substring(0,index));
			$("#SJJGMC").val(jgbhname.substring(index+1,jgbhname.length-1));
		}*/
	}else{
		$("#JGBH").attr("readonly","readonly").addClass("readonly");;
	}
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "numformat.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"NUMFORMATID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					//var jgxxId = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="numformat.shtml?action=toList&NUMFORMATID="+selId;					
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("jgxx.shtml?action=edit","JGXXID","jgxx.shtml?action=toList","mainForm");
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
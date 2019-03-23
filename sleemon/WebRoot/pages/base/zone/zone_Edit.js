/**
 * @module 系统管理
 * @func 行政区划
 * @version 1.1
 * @author 张涛
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	var selId = parent.document.getElementById("selRowId").value;
	if(null == selId || "" == selId){
		var treeNodes = parent.leftcontent.getSelNodes();
		if(treeNodes!=null&&treeNodes.id!=null){
			$("#SJJGID").val(treeNodes.id);
			var jgbhname = treeNodes.name;
			var index = jgbhname.indexOf("(");
			$("#SJJGBH").val(jgbhname.substring(0,index));
			$("#SJJGMC").val(jgbhname.substring(index+1,jgbhname.length-1));
		}
	}
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "zone.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"ZONE_ID":selId},
			async:false,
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					var zone_Id = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="zone.shtml?action=toList&ZONE_ID="+zone_Id+parent.window.reqParamsEx();					
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
		window.parent.leftcontent.location= "zone.shtml?action=showTree";
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("zone.shtml?action=edit","ZONE_ID","zone.shtml?action=toList","mainForm");
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
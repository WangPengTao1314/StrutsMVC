/**
 * @module 系统管理
 * @func 区域信息
 * @version 1.1
 * @author 张忠斌
 */


$(function(){
	//下帧跳转
	parent.window.gotoBottomPage();
	//form校验设置
	InitFormValidator(0);
});
/**
 * 
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	var selId = parent.document.getElementById("selRowId").value;
	if(null == selId || "" == selId){
		var treeNodes = parent.leftcontent.getSelNodes();
		//modify by zhuxw
		//old version
		//if("" != treeNodes&&){
		if(treeNodes!=null&&treeNodes.id!=null){
			$("#AREA_ID_P").val(treeNodes.id);
			var jgbhname = treeNodes.name;
			var index = jgbhname.indexOf("(");
			$("#AREA_NO_P").val(jgbhname.substring(0,index));
			$("#AREA_NAME_P").val(jgbhname.substring(index+1,jgbhname.length-1));
		}
	}
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "area.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"AREA_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					var AREA_ID = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="area.shtml?action=toList&AREA_ID="+AREA_ID+parent.window.reqParamsEx();
					//左边树节点变更
					var sjjg = $("#AREA_ID_P").val();
					if(null == selId || "" == selId){//新增记录
						var newNode = {id:AREA_ID,
									   name:$("#AREA_NO").val()+"("+$("#AREA_NAME").val()+")", 
									   pId:sjjg};
						parent.leftcontent.addTreeNodes(sjjg,newNode);
					}else{//修改记录
						parent.leftcontent.updateNodes(AREA_ID,$("#AREA_NO").val()+"("+$("#AREA_NAME").val()+")");
					}
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("area.shtml?action=edit","quxxID","area.shtml?action=toList","mainForm");
});
**/
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
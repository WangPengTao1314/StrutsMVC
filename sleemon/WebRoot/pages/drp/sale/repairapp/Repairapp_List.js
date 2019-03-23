/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_List
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
    
    shBtnHidden(["add","modify","delete","commit"]);
	//主从及主从从列表页面通用加载方法
	listPageInit("repairapp.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("repairapp.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("repairapp.shtml?action=delete", "ERP_REPAIR_ORDER_ID");
	
	setSelOperateEx();
	
	//alert(obj.options[0]);
	
	 $("#commit").click(function () {
     	
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("1"); 20140311 by huangru
		$.ajax({
		 	url: "repairapp.shtml?action=toCommit&selRowId="+selRowId+"&type=commit&module=wh",
			type:"POST",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
	 				//document.location.reload();
					saveSuccess(utf8Decode(jsonResult.messages),"repairapp.shtml?action=toFrame");
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	
	$("#auditPass").click(function (){
     	
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		
		var SHIP_POINT_ID = document.getElementById("SHIP_POINT_ID"+selRowId).value;
		var SHIP_POINT_NAME = $("#SHIP_POINT_NAME"+selRowId).val();
		if(SHIP_POINT_ID ==null || $.trim(SHIP_POINT_ID)==""){
			parent.showErrorMsg("请选择维修基地");
			return;
		}
		//toFlow("1"); 20140311 by huangru
		
		$.ajax({
		 	url: "repairapp.shtml?action=toCommit&selRowId="+selRowId+"&type=auditPass&module=sh&SHIP_POINT_ID="+SHIP_POINT_ID+"&SHIP_POINT_NAME="+utf8(SHIP_POINT_NAME),
			type:"POST",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
	 				//document.location.reload();
	 				saveSuccess(utf8Decode(jsonResult.messages),"repairapp.shtml?action=toFrame");
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
});


 
function setSelOperateEx(){

	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = parent.document.getElementById("selRowId").value;
	var state=parent.topcontent.document.getElementById("state" + selRowId).value;
	
	//按钮状态重置
	btnReset();
	
	if(state != "未提交"){
		btnDisable(["commit","modify","delete"]);
	} 
	 
 }
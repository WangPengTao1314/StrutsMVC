/**
 * @prjName:喜临门营销平台
 * @fileName:Advise_List
 * @author wdb
 * @time   2013-08-17 10:29:35 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advise.shtml?action=toList&module=" + module);
	
	$("#sercmplselect").hide();
	$("#advsselect").hide();
	var CAType = $("#CAType").val();
	if (CAType == "产品投诉") {
		$("#sercmplselect").hide();
		$("#advsselect").hide();
	} else if (CAType == "服务投诉") {
		$("#sercmplselect").show();
		$("#advsselect").hide();
	} else if (CAType == "建议") {
		$("#sercmplselect").hide();
		$("#advsselect").show();
	} else {
		$("#sercmplselect").hide();
		$("#advsselect").hide();
	}
	setSelect();
	
	$("#again").click(function(){
		parent.showConfirm("您确认重提吗?","topcontent.againFn();","N");
	});
});


//重提
function againFn(){
	var CMPL_ADVS_ID = parent.window.document.getElementById("selRowId").value;	;
	  $.ajax({
		url: "advise.shtml?action=again",
		type:"POST",
		dataType:"text",
		data:{"CMPL_ADVS_ID":CMPL_ADVS_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			   parent.showMsgPanel(utf8Decode(jsonResult.messages));
			   $("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}




//清空 服务投诉
function clearSercmplselect(){
	$("#CMPL_OBJ").find("option").remove();
	$("#CMPL_TO_PSON").val("");
}
//清空 建议
function clearAdvsselect(){
	$("#ADVS_TYPE").find("option").remove();;
}
//根据选择的数据类型改变下帧页面
function pagechange(type){
	parent.$("#CMPL_ADVS_TYPE").val(type);
}

//设置查询页面查询条件
function setSelect(){
	$("#prdcmpl").click(function(){
		$("#sercmplselect").hide();
		$("#advsselect").hide();
//		clearSercmplselect();
//		clearAdvsselect();
	});
	$("#srvcmpl").click(function(){
		$("#sercmplselect").show();
		$("#advsselect").hide();
//		clearAdvsselect();
	});
	$("#advs").click(function(){
		$("#sercmplselect").hide();
		$("#advsselect").show();
//		clearSercmplselect();
	});
 
}




	//记录按钮根据状态控制
 function setSelOperateEx(obj){
	var selRowId = $("#selRowId").val();
	var state =  $.trim($("#state"+selRowId).val());
	//按钮状态重置
	btnReset();
	if(state == "未反馈" || state == "重提" || state=="已反馈"){
		btnDisable(["again"]);
	}
	 
 }
 
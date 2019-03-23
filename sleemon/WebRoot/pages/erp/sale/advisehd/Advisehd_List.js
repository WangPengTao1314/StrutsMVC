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
	listPageInit("advisehd.shtml?action=toList&module=" + module);
	
	$("#sercmplselect").hide();
	$("#advsselect").hide();
	$("#prdselect").hide();
	var CAType = $("#CAType").val();
	if (CAType == "产品投诉") {
		$("#sercmplselect").hide();
		$("#advsselect").hide();
		$("#prdselect").show();
	} else if (CAType == "服务投诉") {
		$("#sercmplselect").show();
		$("#prdselect").hide();
		$("#advsselect").hide();
	} else if (CAType == "建议") {
		$("#sercmplselect").hide();
		$("#advsselect").show();
		$("#prdselect").hide();
	} else {
		$("#sercmplselect").hide();
		$("#advsselect").hide();
		$("#prdselect").hide();
	}
	setSelect();
	$("#retu").click(function(){
		$("#CMPL_ADVS_ID").val($("#selRowId").val());
		$("#reverseDiv").show();
	})
	//处理按钮
	$("#todeal").click(function(){
		var id = $("#selRowId").val();
	 
		if(id!=null && id!=""){
			var state = $("#state"+id).val();
			if (state == "未反馈" || state == "重提") {
				//$(window.parent.frames["bottomcontent"].document).find("#FEEDBACK_INFO").removeAttr("readonly");
				window.showModalDialog('advisehd.shtml?action=dealInformation&CMPL_ADVS_ID='+id,window,'dialogwidth=800px; dialogheight=600px; status=no');
			} else {
				parent.showErrorMsg("已存在反馈信息！");	
				return;		
			}
		}
		
		
	});
	
	//处理提交按钮
	$("#dealhd").click(function(){
		var flag = $(window.parent.frames["bottomcontent"].document).find("#FEEDBACK_INFO").attr("readonly");
		if (flag == "readonly") {
			parent.showErrorMsg("尚未对该条记录进行处理");
			return;
		}
		var feedback = $(window.parent.frames["bottomcontent"].document).find("#FEEDBACK_INFO").text();
		feedback = $.trim(feedback);
		if (feedback == "") {
			parent.showErrorMsg("尚未填写回馈信息");
		} else {
			updFeedback(feedback);
		}
	});
	
	//指派处理人按钮
	$("#deal").click(function(){
		var id = $("#selRowId").val();
		var state = $("#state"+id).val();
		if (state == "未反馈") {
			window.open('advisehd.shtml?action=toAppointPson&CMPL_ADVS_ID='+ id,'指派处理人','height=130, width=400, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
		} else {
			parent.showErrorMsg("已存在反馈信息！");			
		}
	});
	 $("#import").click(function(){
		 $("#queryForm").attr("action","advisehd.shtml?action=toExpData&module=" + module);
	 	 $("#queryForm").submit();
	 });
	 $("#close").click(function(){
    	$("#reverseDiv").hide();
    	$("#REMARK").val("");
    	$("#CMPL_ADVS_ID").val("");
    });
	 $("#revAudit").click(function(){
    	var CMPL_ADVS_ID=$("#CMPL_ADVS_ID").val();
    	upSTATE(CMPL_ADVS_ID,"退回");
    })
});

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
	
	//服务投诉
	$("#srvcmpl").click(function(){
		$("#sercmplselect").show();
		$("#advsselect").hide();
		$("#prdselect").hide();
		$("#CAType").val("服务投诉");
//		clearAdvsselect();
	});
	//产品投诉
	$("#prdcmpl").click(function(){
		$("#sercmplselect").hide();
		$("#advsselect").hide();
		$("#prdselect").show();
		$("#CAType").val("产品投诉");
		
	})
	//建议
	$("#advs").click(function(){
		$("#sercmplselect").hide();
		$("#prdselect").hide();
		$("#advsselect").show();
		$("#CAType").val("建议");
//		clearSercmplselect();
	});
}

//回馈信息更新
function updFeedback(feedback){
	var CMPL_ADVS_ID = $("#selRowId").val();
	$.ajax({
		url: "advisehd.shtml?action=saveFeedback",
		type:"POST",
		dataType:"text",
		data:{"feedback":feedback,"CMPL_ADVS_ID":CMPL_ADVS_ID},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	$(window.parent.frames["bottomcontent"].document).find("#FEEDBACK_INFO").attr("readonly","readonly");
}

//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if (state == "已反馈"||state=="退回") {
		btnDisable(["todeal"]);
	}
	if(state!="未反馈"&&state!="重提"){
		btnDisable(["retu"]);
	}
	
}

//禁用按钮
function busDisabled(){
	//btnDisable(["todeal"]);
}

function tt(){
	saveSuccess("wwwww","advisehd.shtml?action=toFrame");
}

function upSTATE(CMPL_ADVS_ID){
	parent.showWaitPanel();
	var REMARK=$("#REMARK").val();
	$.ajax({
	 url: "advisehd.shtml?action=toReturn",
	 type:"POST",
 	 dataType:"text",
 	 data:{"CMPL_ADVS_ID":CMPL_ADVS_ID,"REMARK":REMARK},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("退回成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
	closeWindow();
}

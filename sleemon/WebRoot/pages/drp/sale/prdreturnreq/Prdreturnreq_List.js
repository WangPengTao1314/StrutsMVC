/**
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_List
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;
    
    if(module!="wh"){
    	$("#query").click(function(){
			$("#STATE option[text='未提交']").remove();
		});
    }
    var firstAudit = parent.window.document.getElementById("firstAudit").value;	
    
	//主从及主从从列表页面通用加载方法
	listPageInit("prdreturnreq.shtml?action=toList&module=" + module+"&firstAudit="+firstAudit);
	//新增和修改按钮初始化
	addModiToEditFrameInit("prdreturnreq.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("prdreturnreq.shtml?action=delete", "PRD_RETURN_REQ_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		showWaitPanel();
		var AREA_SER_ID = $("#AREA_SER_ID"+selRowId).val();
		$.ajax({
			 url: "prdreturnreq.shtml?action=toCommit",
			 type:"POST",
			 data:{"PRD_RETURN_REQ_ID":selRowId,"AREA_SER_ID":AREA_SER_ID},
		 	 dataType:"text",
			 complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					//有区域服务中心走
//					if(null != AREA_SER_ID && "" != AREA_SER_ID){
//			            haveAreaSerToCommit(selRowId);
//		            }else{
//		            	toFlow("1");
//		            }
					toFlow("1");
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
					closeWindow();
				}
			  }
	    });
	});
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		toFlow("3");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		toFlow("2");
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		toFlow("4");
	}); 
	$("#expdate").click(function(){
		var module = parent.window.document.getElementById("module").value;
		 $("#queryForm").attr("action","prdreturnreq.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	});
	
	setSelectParams();
});



function setSelectParams(){
	var module = parent.window.document.getElementById("module").value;
	var ZTXXID = $("#ZTXXID").val();
	var RECV_CHANN_ID = $("#RECV_CHANN_ID_Search").val();
    if(module == "wh"){
    	$("#selectReturn").val("STATE in ( '启用','停用' )  and CHANN_ID='"+ZTXXID+"' or CHANN_ID_P='"+ZTXXID+"' and IS_BASE_FLAG!=1 ");
    	$("#selectSTORECondition").val(" STATE in ( '启用','停用' )  and TERM_STROE_FLAG=0  and BEL_CHANN_ID='"+ZTXXID+"' ");
    	$("#selectCondition").val("STATE in ( '启用','停用' ) and CHANN_ID='"+RECV_CHANN_ID+"' ");
    	$("#selectRYXX").val("RYZT in ( '启用','停用' ) and RYLB!='门店'  and ZTXXID='"+ZTXXID+"'");
    	
    }else{
    	$("#selectReturn").val("STATE in ( '启用','停用' ) and IS_BASE_FLAG!=1");
    }
    
}
///有区域服务中心的走下面的提交
function haveAreaSerToCommit(selRowId){
	var AREA_SER_ID = $("#AREA_SER_ID"+selRowId).val();
	$.ajax({
		 url: "prdreturnreq.shtml?action=toCommit",
		 type:"POST",
		 data:{"PRD_RETURN_REQ_ID":selRowId,"AREA_SER_ID":AREA_SER_ID,"flag":"commit"},
	 	 dataType:"text",
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				return;
			}
		  }
    });
}



function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
	var firstAudit = parent.window.document.getElementById("firstAudit").value;
	
    document.affirm.sourceURI.value=ctxPath+"/prdreturnreq.shtml?action=toList&firstAudit="+firstAudit+document.getElementById("paramUrl").value;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}


//退货库房ID
function getStoreId(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#RETURN_STORE_ID"+selRowId).val();
}

//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "提交") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "回退") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
}


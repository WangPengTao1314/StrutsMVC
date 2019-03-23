
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;
	//主从及主从从列表页面通用加载方法
	listPageInit("advcorder.shtml?action=toList&module=" + module);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "uncomm","print","cancel","update","allPrint"]);
	//维护页面需要隐藏的按钮
	whBtnHidden(["auditAdvc","returnAdvc","retuAudit","priceModify"]);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advcorder.shtml?action=toEditFrame"); 
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advcorder.shtml?action=delete", "ADVC_ORDER_ID");
	
	//提交按钮单独写
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		btnDisable(["commit"]);
		var STATE = $("#state"+selRowId).val();
		commit(selRowId);
	});
    $("#cancel").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确定撤销吗?","topcontent.toCancel();");
    })
    $("#retuAudit").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确定反审核吗?","topcontent.toRetuAuditCheck();");
    })
    
    
    $("#up").click(function () {
		$("#tempUp").show()
		$("#upFile").show();
	});
    $("#close").click(function (){
    	$("#upFile").hide();
    })
	$("#tempUp").click(function(){
		window.location="advcorder.shtml?action=ExcelOutputModel";
	})
    $("#priceModify").click(function(){
    	var isMonthAcc=$("#isMonthAcc").val();
    	if("true"==isMonthAcc){
    		parent.showErrorMsg("当前时间已月结，不能修改销售价格");
    		return;
    	}
    	var selRowId = parent.document.getElementById("selRowId").value;
		var state = document.getElementById("state" + selRowId).value;
		if("审核通过"!=state){
			parent.showErrorMsg("该单据还未审核，不能修改销售价格");
    		return;
		}
		window.open("advcorder.shtml?action=openPriceModify&ADVC_ORDER_ID="+selRowId,"111","height=600, width=1200, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
//		window.showModalDialog("advcorder.shtml?action=openPriceModify&ADVC_ORDER_ID="+selRowId,window,"dialogwidth=1200px; dialogheight=600px; status=no");
    })
    
    $("#auditAdvc").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		
		parent.showConfirm("您确定审核通过吗?","topcontent.toAudit();");
    })
     $("#returnAdvc").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确定退回吗?","topcontent.reverse();","N");
    })
    
    $("#query").click(function(){
    	if("sh"==module){
    		$("#STATE option[text='未提交']").remove();
			$("#STATE option[text='待核价']").remove();
			$("#STATE option[text='待结算']").remove();
			$("#STATE option[text='已结算']").remove();
			$("#STATE option[text='关闭']").remove();
			$("#STATE option[text='部分发货']").remove();
			$("#STATE option[text='已发货']").remove();
			$("#STATE option[text='已收货']").remove();
			$("#STATE option[text='待确认']").remove();
    	}
    	if("wh"==module){
			$("#STATE option[text='待结算']").remove();
			$("#STATE option[text='已结算']").remove();
			$("#STATE option[text='部分发货']").remove();
			$("#STATE option[text='已收货']").remove();
    	}
	});
    
    
    $("#uncomm").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		btnDisable(["uncomm"]);
		uncomm(selRowId);
    })
    $("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
    })
    $("#allPrint").click(function(){
    	window.open("advcorder.shtml?action=toAllPrint","预订单批量打印","height=650, width=1200, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
    })
    $("#advcClosePrint").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		closePrint(selRowId);
    })

	$("#moveAdv").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		audit(selRowId);
	});
    
    $("#expertExcel").click(function(){
		 $("#queryForm").attr("action","advcorder.shtml?action=expertExcel&module=" + module);
		 $("#queryForm").submit();
	});
	
     $("#update").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toUpdate();
    })
        
	setSelOperateEx();
});

function reverse(){
	$('#returnDiv').show();

}
function determineReturn(){
    	var RETURN_RSON=$("#RETURN_RSON").val();
		if(""!=RETURN_RSON&&null!=RETURN_RSON){
		if(RETURN_RSON.length>120){
			parent.showErrorMsg(utf8Decode("退回原因超过最大字符数！"));
	           return false;
		}
		}
    	toReturn(RETURN_RSON);
    }
function divClose(id){
	$("#RYXXID").val("");
	$("#XM").val("");
	$("#RYBH").val("");
	$("#RETURN_RSON").val("");
	$("#"+id).hide();
}
		
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	var closeFlag = document.getElementById("CLOSEFLAG" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state != "未提交"&&state != "退回") {
		btnDisable(["modify","delete","commit","uncomm"]);
	}
	if(state!="提交"){
		btnDisable(["auditAdvc","returnAdvc","cancel"]);
	}
	if(state!="审核通过"){
		btnDisable(["retuAudit","update","priceModify"]);
	}
	if("0"==closeFlag){
		btnDisable(["advcClosePrint"]);
	}
}

//退回打单据 再提交的时候 检查 是否核销，已核销的要反核销
function checkAdvcAmout(selRowId){
	 var ADVC_AMOUNT = $("#ADVC_AMOUNT"+selRowId).val();
	$.ajax({
	 url: "advcorder.shtml?action=checkAdvcAmout",
	 type:"POST",
	 data:{"ADVC_ORDER_ID":selRowId,"ADVC_AMOUNT":ADVC_AMOUNT},
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
            commit(selRowId);
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}

function toAudit(){
	btnDisable(["auditAdvc"]);
	var selRowId = parent.document.getElementById("selRowId").value;
    $.ajax({
	 url: "advcorder.shtml?action=toAudit&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("审核成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
    setSelOperateEx();
}
function toCancel(){
	btnDisable(["cancel"]);
	var selRowId = parent.document.getElementById("selRowId").value;
    $.ajax({
	 url: "advcorder.shtml?action=toCancel&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("撤销成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
    setSelOperateEx();
}
function toReturn(RETURN_RSON){
	var selRowId = parent.document.getElementById("selRowId").value;
    $.ajax({
	 url: "advcorder.shtml?action=toReturn",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_ORDER_ID":utf8(selRowId),"RETURN_RSON":RETURN_RSON},
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
}


function commit(selRowId){
	showWaitPanel();
    $.ajax({
	 url: "advcorder.shtml?action=toCommit&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("提交成功");
            $("#pageForm").submit();
		}else{
			closeWindow();
			var data = jsonResult.data;
			if(null != data && "" != data){
				var RATE = data.RATE;
				$("#RATE").val(RATE);
				$("#audit").show();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				closeWindow();
			}
		}
	  }
    });
    setSelOperateEx();
}
//当明细列表的折扣率有低于当前登录人员的最低输入折扣率时，弹出审核人窗口，把该条预订单转到审核人那里审核
function audit(selRowId){
	var RYXXID=$("#RYXXID").val();
	var XM=$("#XM").val();
	if(""==RYXXID||null==RYXXID){
		parent.showErrorMsg("请选择核价人");
			return;
	}
    $.ajax({
	 url: "advcorder.shtml?action=toPrice&ADVC_ORDER_ID="+utf8(selRowId)+"&RYXXID="+utf8(RYXXID)+"&XM="+utf8(XM),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("提交成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
function uncomm(id){
    $.ajax({
	 url: "advcorder.shtml?action=toUncomm&ADVC_ORDER_ID="+utf8(id),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("操作成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
    setSelOperateEx();
}
//反审核前验证是否是待确认预订单
function toRetuAuditCheck(){
	btnDisable(["retuAudit"]);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
	 url: "advcorder.shtml?action=toRetuAuditCheck&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var flag=jsonResult.data.flag;
			if(flag){
				parent.showConfirm("反审核后订单将退回到待确认预订单页面，确定反审核吗?","topcontent.toRetuAudit();");
			}else{
				toRetuAudit();
			}
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
	setSelOperateEx();
}
//反审核
function toRetuAudit(){
	btnDisable(["retuAudit"]);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
	 url: "advcorder.shtml?action=toRetuAudit&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("反审核成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
	setSelOperateEx();
}

function setAmoutVal(v){
//	var selRowId = parent.document.getElementById("selRowId").value;
//	$("#PAYABLE_AMOUNT"+selRowId).html(v);
}
function setDISCOUNT_AMOUNT(v){
}
function print(selRowId){
	//跳转扫码打印页面
//	$("#printIframe").attr("src","advcorder.shtml?action=printInfo&ADVC_ORDER_ID="+selRowId);
	window.open('advcorder.shtml?action=printInfo&ADVC_ORDER_ID='+selRowId,'预订单打印','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}
function closePrint(selRowId){
	//跳转扫码打印页面
	window.open('advcorder.shtml?action=closePrintInfo&ADVC_ORDER_ID='+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}

//选择核价人员
function selRYXX(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var termID = $("#term"+selRowId).val();
	var ZTXXID = $("#ZTXXID").val(); 
	var RATE = $("#RATE").val();
	var sql="   (BMXXID='"+ZTXXID+"' or BMXXID='"+termID+"') and NVL(TERM_DECT_FROM,0)<="+RATE ;
	$("#RY").val(sql);
	selCommon('BS_193', false, 'RYXXID','RYXXID', 'forms[0]','RYXXID,RYBH,XM','RYXXID,RYBH,XM', 'RY');
}


function openUrl(id){
	window.open('changeadvcorder.shtml?action=toFrame&ADVC_ORDER_ID='+id+"&showFlag=1",'预订单变更查看','height=600, width=900, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=no,location=no, status=no');
}
function checkParent(){
	return true;
}
function getTermId(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#term"+selRowId).val();
}
function toUpdate(){
	var selRowId = $("#selRowId").val();
	$.ajax({
	 url: "advcorder.shtml?action=checkSendAdvcNum",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_ORDER_ID":selRowId},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var url="advcorder.shtml?action=toEditFrame&updateFlag=1";
		 	 if(null == selRowId || "" == selRowId){
		 		 parent.showErrorMsg("请选择一条记录");
		 	 }else{
		 	     var paramUrl=document.getElementById("paramUrl");
		 	     if(paramUrl!=null&&paramUrl.value!="")
		 	     {
		 	       window.parent.location=url+reqParamsEx()+"&module="+getModule()+"&paramUrl="+utf8((paramUrl.value.replaceAll("&","andflag")).replaceAll("=","equalsflag")); 
		 	     }else
		 	     {
		 	      window.parent.location=url+reqParamsEx()+"&module="+getModule(); 
		 	     }
		 	 }
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
	
}
function countPrint(){
	$("#queryForm").submit();
}
function resetQueryInput(){
	$("#queryForm table table input[type='text']").each(function(){
		this.value="";		
	})
}
 function getUpdateFlag(){
	 return null;
 }
 function openAdvcInfo(id){
	 window.open('advcorder.shtml?action=openAdvcInfo&ADVC_ORDER_ID='+id,'相关单据跟踪','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
 }
 
 //选择终端了 选择对应终端的人员
 function changeRY(){
	var ZTXXID = $("#ZTXXID").val(); 
	var TERM_NO = $("#TERM_NO").val();
	var TERM_NAME = $("#TERM_NAME").val();
	var params = "RYZT in ('启用','停用') and DELFLAG=0 and JGXXID in (select JGXXID from T_SYS_JGXX where ZTXXID ='"+ZTXXID+"') ";
	if(null != TERM_NO && "" != TERM_NO){
		params = params+" and BMXXID in (select b.TERM_ID from BASE_TERMINAL b where b.TERM_NO like '%"+TERM_NO+"%')";
	}
	if(null != TERM_NAME && "" != TERM_NAME){
		params = params+" and BMXXID in (select b.TERM_ID from BASE_TERMINAL b where b.TERM_NAME like '%"+TERM_NAME+"%')";
	}
	$("#selectParam").val(params);
 }
 //导入
function uploading(){
	var fileName = $("#upInfo").val();
	$.ajax({
	 url: "advcorder.shtml?action=parseExecl",
	 type:"POST",
 	 dataType:"text",
 	 data:{"fileName":fileName},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			alert("上传成功");
            $("#pageForm").submit();
            $("#upFile").hide();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
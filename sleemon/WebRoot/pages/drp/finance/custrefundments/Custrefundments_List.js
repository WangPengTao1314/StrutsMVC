/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_List
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;
    //维护页面需要隐藏的按钮
	whBtnHidden(["affirm","return"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	//主从及主从从列表页面通用加载方法
	listPageInit("custrefundments.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("custrefundments.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("custrefundments.shtml?action=delete", "STATEMENTS_ID");
	
	
	$("#revoke").click(function(){
 		  var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	  } 
 	  parent.showConfirm("您确认要撤销吗?","topcontent.revoke();");
 	});
	 $("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
    })
	$("#affirm").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确认要审核通过吗?","topcontent.toCommit('"+selRowId+"','affirm');");
	})
	$("#return").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$("#STATEMENTS_ID").val(selRowId);
    	$("#reverseDiv").show();
//		parent.showConfirm("您确认要退回吗?","topcontent.returnStatements();");
	})
	$("#revAudit").click(function(){
		returnStatements();
	})
});

//查询预付款列表
 function queryPayNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" BILL_TYPE in ('其它退款','终端退货退款') and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_96', false, 'STATEMENTS_NO', 'STATEMENTS_NO', 'forms[1]','STATEMENTS_NO', 'STATEMENTS_NO','con');
 }
 
 //查询预定单列表
 function queryOrderNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" DEL_FLAG=0 and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_55', false, 'ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'forms[1]','ADVC_ORDER_NO', 'ADVC_ORDER_NO','con');
 }
 
 //查询终端列表
 function queryTeamNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" del_flag=0 and state in ('启用','停用')and CHANN_ID_P = '"+ztxxid+"'");
	selCommon('BS_27', false, 'TERM_NO', 'TERM_NO','forms[1]','TERM_NAME','TERM_NAME','con');
 }
 
  //提交
 function opSub(){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要提交吗?","topcontent.toCommit('"+selRowId+"','commit');");
 }
 function toCommit(selRowId,op){
	 var amount=$("#amount"+selRowId).val();
	 var PAYABLE=$("#PAYABLE"+selRowId).val();
	 $.ajax({
	 url: "custrefundments.shtml?action=toCommit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"STATEMENTS_ID":selRowId,"STATEMENTS_AMOUNT":amount,"PAYABLE_AMOUNT":PAYABLE},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			mainSubmit(selRowId,op);
		}else{
			if(null==jsonResult.messages||""==jsonResult.messages){
				parent.showConfirm("当前退货金额已超出最大可退金额，是否强行提交?","topcontent.mainSubmit('"+selRowId+"','"+op+"');");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  }
    });
 }
 //主体提交方法
 function mainSubmit(selRowId,op){
 	$.ajax({
        type:"POST", 
        url:"custrefundments.shtml?action=opSub", 
        data:{"STATEMENTS_ID":selRowId,"op":op}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("操作成功");
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
    }});
 }
 
 //点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (state != "未提交"  ) {
		btnDisable(["delete","modify","commit"]);
	}
	if(state!="待结算"){
		btnDisable(["affirm","return"]);
	}
	if(state!="已结算"){
		btnDisable(["revoke"])
	}
}

function my_reset(){
	$("#ZTXX_ID").val("");
	$("#STATEMENTS_NO").val("");
	$("#ADVC_ORDER_NO").val("");
	$("#TERM_NO").val("");
	$("#TERM_NAME").val("");
	$("#CRE_TIME_BEG").val("");
	$("#CRE_TIME_END").val("");
	$("#CUST_NAME").val("");
	$("#STATE").val("");
}

function revoke(){
	 var selRowId = $("#selRowId").val();
	 $.ajax({
        type:"POST", 
        url:"advpayment.shtml?action=toRevoke", 
        data:{"STATEMENTS_ID":selRowId,"type":"custre"}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("撤销成功");
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
        }
    });
}
function returnStatements(){
	 var STATEMENTS_ID = $("#STATEMENTS_ID").val();
	 var RETURN_RSON=$("#RETURN_RSON").val();
	 $.ajax({
        type:"POST", 
        url:"custrefundments.shtml?action=toReturn", 
        data:{"STATEMENTS_ID":STATEMENTS_ID,"RETURN_RSON":RETURN_RSON}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("退回成功");
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
        }
    });
}
function print(selRowId){
	window.open('custrefundments.shtml?action=printInfo&STATEMENTS_ID='+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}
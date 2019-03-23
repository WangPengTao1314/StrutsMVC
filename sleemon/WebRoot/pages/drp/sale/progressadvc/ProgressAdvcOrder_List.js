
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
	listPageInit("propressadvcorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("propressadvcorder.shtml?action=toEditFrame"); 
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("propressadvcorder.shtml?action=delete", "ADVC_ORDER_ID");
	
	//提交按钮单独写
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == ""){
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		var flag = parent.bottomcontent.justHavePrd();
		if(!flag){
			parent.showErrorMsg("请完善预订单明细信息");
			return;
		}
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
 
    
    $("#expertExcel").click(function(){
		 $("#queryForm").attr("action","propressadvcorder.shtml?action=expertExcel&module=" + module);
		 $("#queryForm").submit();
	});
        
        
	setSelOperateEx();
});
  
		
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId);
    //按钮状态重置
	btnReset();
	if(state){
		//当状态=未提交
		if (state.value =="待完善") {
			btnDisable(["cancel"]);
		}
		if (state.value =="未提交") {
			btnDisable(["modify","delete","commit"]);
		}
	}else{
		btnDisable(["modify","delete","commit","cancel"]);
	}
	
}
 
function toCancel(){
	btnDisable(["cancel"]);
	var selRowId = parent.document.getElementById("selRowId").value;
    $.ajax({
	 url: "propressadvcorder.shtml?action=toRevoke",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_ORDER_ID":selRowId},
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
}
 
function commit(selRowId){
    $.ajax({
	 url: "propressadvcorder.shtml?action=toCommit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_ORDER_ID":selRowId},
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



//导出
//function download(){
//	var selRowId = parent.document.getElementById("selRowId").value;
//	window.location="advcorder.shtml?action=ExcelOutput&ADVC_ORDER_ID="+utf8(selRowId);
//}
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
/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_List_Chld
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
	$(window).unload(function () { 
		windowClose();
	});
	var error=$("#error").val();
	if(""!=error&&null!=error){
		alert(error);
		window.close();
	}
	$("#dispose").click(function(){
		if(checkNum()){
			dispose();
		}
	})
});

//显示特殊工艺单
function url(SPCL_TECH_ID,PRICE){
	var data = window.showModalDialog('techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID='+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
function allotNum(PRD_ID,SPCL_TECH_ID,REAL_SEND_NUM,id){
	var BASE_DELIVER_NOTICE_ID=$("#BASE_DELIVER_NOTICE_ID").val();
	var sessionId=$("#sessionId").val();
	var data = window.showModalDialog('senddirectnotice.shtml?action=toListShift&BASE_DELIVER_NOTICE_ID='+BASE_DELIVER_NOTICE_ID+"&PRD_ID="+PRD_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&REAL_SEND_NUM="+REAL_SEND_NUM+"&sessionId="+sessionId,window,"dialogwidth=1000px; dialogheight=500px; status=no");
	$("#allotNum"+id).val(data);
}
//窗口关闭时删除session
function windowClose(){
	var sessionId=$("#sessionId").val();
	$.ajax({
	 url: "senddirectnotice.shtml?action=windowClose&sessionId="+utf8(sessionId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			window.close();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
//验证货品是否全部分配完毕
function checkNum(){
	var totalNum=$("#totalNum").val();
	totalNum=isNaN(totalNum)?0:parseFloat(totalNum);
	var tempTotalNum=0;
	$("input[name='allotNum']").each(function(){
		if(""!=$(this).val()&&null!=$(this).val()){
			var num=isNaN($(this).val())?0:parseFloat($(this).val());
			tempTotalNum=tempTotalNum+num;
		}
	})
	if(tempTotalNum<totalNum){
		alert("有剩余货品未全部分配完毕！");
		return false;
	}
	return true;
}
function dispose(){
	var sessionId=$("#sessionId").val();
	var BASE_DELIVER_NOTICE_ID=$("#BASE_DELIVER_NOTICE_ID").val();
	$.ajax({
	 url: "senddirectnotice.shtml?action=toAllot&sessionId="+utf8(sessionId)+"&BASE_DELIVER_NOTICE_ID="+utf8(BASE_DELIVER_NOTICE_ID),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			alert("分配成功");
			var data = jsonResult.data;
			window.returnValue=data;
			window.close();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
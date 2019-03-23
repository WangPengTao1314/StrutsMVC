/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	 
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("cusregist.shtml?action=toList&module=" + module);
	
	$("#add").click(function(){
	   var  returnValue = window.showModalDialog('cusregist.shtml?action=toEdit','','dialogwidth=950px; dialogheight=600px; status=no');
	   if(1 == returnValue || "1" == returnValue){
		   $("#pageForm").submit();
	   }
//    window.open('erpFirpage.shtml?action=toNotice&NOTICE_ID='+NOTICE_ID,'','');
	});
	$("#modify").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
	    window.showModalDialog('cusregist.shtml?action=toEdit&selRowId='+selRowId,'','dialogwidth=950px; dialogheight=600px; status=no');
	})
	
	$("#registout").click(function(){
		 var selRowId = $("#selRowId").val();
		 if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("您确认要签出吗?","topcontent.registoutConfirm();");
	});
	
});

 
 
 
 
//签出
function registoutConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "cusregist.shtml?action=registout",
		type:"POST",
		data:{"selRowId":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("签出成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if (state == "已签出") {
		btnDisable(["modify","registout"]);
	}
    if (state == "未签到") {
		btnDisable(["modify","registout"]);
	}
	 
 
}
 
  

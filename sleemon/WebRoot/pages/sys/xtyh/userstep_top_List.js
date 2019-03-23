
/**
 * @module 系统管理
 * @func 渠道分管
 * @version 1.1
 * @author 张忠斌
 */
$(function () {
     $("#querydiv").css("display","block");
     //初始化校验
	InitFormValidator("mainForm");
	
	
	$("#save").click(function(){
		 saveXtyhxx();
		 
	});
	
	$("#query").click(function(){
	    queryXtyhxx();
	});
	
	$("#calcel").click(function(){
		parent.showConfirm("您确认要取消该分管吗?","topcontent.calcel();");
	});
 
});
 
function queryXtyhxx(){
	var YHBH = $("#YHBH").val();
	var notcharg = "0";
	var falg1 = $("#notcharg_1").attr("checked");//显示未分管
	var falg2 = $("#notcharg_2").attr("checked");//显示已分管
	if("checked" == falg1){
		notcharg = "1";
	}
	if("checked" == falg2){
		notcharg = "2";
	}
	parent.bottomcontent.$("#notcharg").val(notcharg);
	parent.bottomcontent.$("#PAR_USER_ID").val($("#PAR_USER_ID").val());
	parent.bottomcontent.$("#XTYHID").val($("#XTYHID").val());
	parent.bottomcontent.$("#YHBH").val($("#YHBH").val());
	parent.bottomcontent.$("#YHM").val($("#YHM").val());
 
	parent.bottomcontent.$("#JGMC").val($("#JGMC").val());
	parent.bottomcontent.$("#BMMC").val($("#BMMC").val());
	 
	parent.bottomcontent.$("#queryForm").attr("action","xtyh.shtml?action=userList");
	parent.bottomcontent.$("#queryForm").submit();
}
 

function saveXtyhxx(){
	var PAR_USER_ID = $("#PAR_USER_ID").val();
	var XTYHIDS =  parent.document.getElementById("selRowId").value;
	if(null == XTYHIDS || "" == XTYHIDS){
		parent.showErrorMsg("请至少选择一条用户信息");
		return;
	}
    $.ajax({
	 url: "xtyh.shtml?action=editStepUser",
	 type:"POST",
 	 dataType:"text",
 	 data:{"PAR_USER_ID":PAR_USER_ID,"XTYHIDS":XTYHIDS},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel(utf8Decode(jsonResult.messages));
			$("#query").click();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}

//取消分管
function calcel(){
	var PAR_USER_ID = $("#PAR_USER_ID").val();
	var XTYHIDS =  parent.document.getElementById("selRowId").value;
	if(null == XTYHIDS || "" == XTYHIDS){
		parent.showErrorMsg("请至少选择一条用户信息");
		return;
	}
	 $.ajax({
		 url: "xtyh.shtml?action=deleteStepUser",
		 type:"POST",
	 	 dataType:"text",
	 	 data:{"PAR_USER_ID":PAR_USER_ID,"XTYHIDS":XTYHIDS},
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				$("#query").click();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		  }
	    });
}
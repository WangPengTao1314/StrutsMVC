<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script type=text/javascript src="${ctx}/scripts/core/md5.js"></script>
    	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>退回原因</title>
	</head>
	<body>
	<form action="">
		<table width="99.9%" border="0" cellpadding="1" cellspacing="1">
				<tr align="center">
					<td><h3>退回原因</h3></td>
				</tr>
				<tr>
					<td><textarea id="msg" name="msg" inputtype="string" autocheck="true" maxlength="500" style="width:500px;height:150px;overflow:hidden;"></textarea></td>
				</tr>
				<tr align="center">
					<td height="30px">
						<input id="verif" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O" onclick="verifFn();">
						<input id="leave" type="button" class="btn" value="离开(L)" title="Alt+L" accesskey="O" onclick="leaveFn();">
					</td>
				</tr>
			 
			</table>
	 
	</form>
	</body>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
	
	    function verifFn(){
	    	var msg = $.trim($("#msg").val());
	    	if(null == msg || ""==msg){
	    		parent.showMsgPanel("请填写退回原因");
	    		return;
	    	}
	    	window.dialogArguments.document.getElementById("msg").value = msg;
	    	var selRowId = window.dialogArguments.document.getElementById("selRowId").value;
	    	selRowId = selRowId.replaceAll("'","");
//	    	msg = "退回原因："+msg;
	        $.ajax({
			 	url: "goodsorder.shtml?action=returnBack",
				type:"POST",
				data:{"GOODS_ORDER_ID":selRowId,"msg":msg},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						 window.returnValue='1';
						 window.close();
					}else{
						showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});
	    }
	  
	    function leaveFn(){
	    	window.close();
	    }
	</script>
</html>
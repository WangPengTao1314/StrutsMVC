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
		<title>延迟原因</title>
	</head>
	<body>
	<form action="" method="post" id="reasonForm" >
		<table width="99.9%" border="0" cellpadding="1" cellspacing="1">
				<tr align="center">
					<td colspan="2"><h3>延迟原因</h3></td>
				</tr>
				<tr>
	    			<td class="wenben"  > 延迟原因: </td>
	    			<td >
	    				 <select id="DELAY_TYPE"  inputtype="string" autocheck="true" style="width: 155px;" mustinput="true" ></select> 
	    			     <SPAN style="color: red; text-decoration: none;border-style: none;">&nbsp;*</SPAN> 
	    			</td>
	    		</tr>
				<tr> 
				    <td>备注：</td>
					<td>
					 <textarea id="REMARK" name="REMARK" inputtype="string" autocheck="true" maxlength="250" style="width:300px;height:100px;overflow:hidden;">
					  </textarea>
					</td>
				</tr>
				<tr align="center">
					<td height="30px" colspan="2">
						<input id="verif" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O" onclick="verifFn();">
						<input id="leave" type="button" class="btn" value="离开(L)" title="Alt+L" accesskey="O" onclick="leaveFn();">
					</td>
				</tr>
			 
			</table>
	 
	</form>
	</body>
	<script type="text/javascript">
	    $(function(){
	    	 var selRowId = window.dialogArguments.document.getElementById("selRowId").value
	    	 var REMARK = window.dialogArguments.$("#REMARK"+selRowId).val();
	    	 $("#REMARK").val(REMARK);
	    });
	    SelDictShow("DELAY_TYPE","BS_115","","");
	    
	    function verifFn(){
	    	var DELAY_TYPE = $.trim($("#DELAY_TYPE").val());
	    	var REMARK = $.trim($("#REMARK").val());
	    	if(null == DELAY_TYPE || ""==DELAY_TYPE){
	    		parent.showMsgPanel("请选择延迟原因");
	    		return;
	    	}
	    	window.dialogArguments.document.getElementById("DELAY_TYPE").value = DELAY_TYPE;
	    	window.dialogArguments.document.getElementById("REMARK").value = REMARK;
	    	
	    	window.returnValue='1';
	    	window.close();
	    }
	    function leaveFn(){
	    	window.close();
	    }
	</script>
</html>
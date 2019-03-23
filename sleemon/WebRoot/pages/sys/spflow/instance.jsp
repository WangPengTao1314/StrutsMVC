<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<title>审签</title>
<script language="javaScript">
	window.returnValue = '${refresh}';
	var flowNext = '${flowNext}';
	var msg='${msg}';
	if(msg!=null&&msg!="")
	{
	 alert(msg);
	}
	if (flowNext != "F") {
		if (window.returnValue == "T") {
			top.window.opener=null;
			top.window.close();
		}
	}
</script>
</head>
<frameset rows="0,*" cols="*" frameborder="NO" border="0" framespacing="0">
 <frame src="" name="topmenu">
  <frame name="MainFrame" noresize src="${ctx }/system/flow.shtml?action=toStraight&ztbgFieldName=${ztbgFieldName}&businessType=${businessType}&flowInterfaceName=${flowInterfaceName}&tableName=${tableName}&fieldName=${fieldName}&id=${id}&flowInstanceId=${flowInstanceId}&refresh=${refresh}&hasOfficial=${hasOfficial}" >
</frameset><noframes></noframes>
</html>
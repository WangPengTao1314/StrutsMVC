<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<title>流程跟踪</title>
</head>
<frameset rows="0,*" cols="*" frameborder="NO" border="0" framespacing="0"> 
  
  <frame src="" name="topmenu">
  <frame name="MainFrame" noresize src="${ctx}/system/flow.shtml?action=toFlowshow&tableName=${tableName}&fieldName=${fieldName}&id=${id}&businessType=${businessType}" > 
</frameset><noframes></noframes>
</html>
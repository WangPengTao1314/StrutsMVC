<!-- 
 * @module 报表管理
 * @fuc flash报表打印
 * @version 1.0
 * @author 刘曰刚
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=GBK" %>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hoperun.commons.model.Consts"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<title>
		</title>
	</head>
	<body onload="loadFlashReport();"> 
	</body>
		<%
 			//报表路径名称 如【../reportFile/*.raq】
 			request.setCharacterEncoding( "UTF-8" );
 			String params = new String(request.getAttribute("params").toString().getBytes("gb2312"),"gbk");
 		%>
	<script type="text/javascript">
		function loadFlashReport(){
			var url="<%=Consts.FLASH_PRINT_URL+params%>";
			//var url="http://192.168.10.112:8070/reportmis/gezEntry.url?patternID=1001&raq=sleemon/<%=params%>";
			window.location.replace(url);
		}
	</script>
</html>

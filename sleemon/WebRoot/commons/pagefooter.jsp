<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 1.1
  *@author zhuxw  
 -->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>翻页工具栏</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr class="listToolbar">
				<td>
					${page.footerHtml}
				</td>
				<td align="right">
					${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</body>
</html>


<%-- 
  *@module 系统管理
  *@func 自定义查询
  *@version 1.1
  *@author zhuxw
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">   
		<title>报表SQL</title>
	</head>
	<body>

		<div class="lst_area" style="width:650px;heigth:260px" aling='center'>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="list_row1" width='90%'>
					
						<td align="left" rowspan='10'>${rptSql}&nbsp;</td>
						
			   </tr>
			</table>
		</div>
	</body>
</html>

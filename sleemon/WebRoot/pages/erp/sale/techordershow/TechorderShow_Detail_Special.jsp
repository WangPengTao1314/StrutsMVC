<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>特殊工艺维护</title>
    <script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
	<body>
		<form>
			<table width="500" align="center" id="jsontb">
			  <tr>
			    <td colspan="8" align="center"><h1>特殊工艺维护</h1></td>
			  </tr>
			  <c:forEach items="${rst}" var="rst" >
			  	<tr>
			  		<td>${rst.TECH_TYPE }</td>
			  		<c:if test="${rst.TECH_TYPE!='备注'}">
			  			<td>${rst.REMARK }</td>
			  		</c:if>
			  		<c:if test="${rst.TECH_TYPE=='备注'}">
			  			<td>
			  				<textarea  rows="4" disabled="disabled" cols="80%" json="REMARK" name="REMARK" >${rst.REMARK }</textarea>
						</td>
			  		</c:if>
			  	</tr>
			  </c:forEach>
			  <c:if test="${empty rst}">
			  	<tr>
			  		<td>
			  			<input type='checkbox' checked="checked" style="display: none;" />
			  			备注：
			  			<textarea  rows="4" cols="80%" disabled="disabled" ></textarea>
			  		</td>
			  	</tr>
			  </c:if>
			</table>
		</form>
	</body>
</html>

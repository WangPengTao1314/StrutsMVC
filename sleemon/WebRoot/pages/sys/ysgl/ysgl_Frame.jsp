<%--
/**
 * @author 葛俊卿
 * @function 
 * @version 2012年2月14日
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ page import="com.hoperun.sys.model.UserBean"%>
<%
	UserBean userBean = (UserBean) session.getAttribute("UserBean");
	String XTYHID=userBean.getXTYHID();
 %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/sys/ysgl/ysgl_Frame.js"></script>
</head>
<BODY>
<input type="hidden" id="XTYHID" name="XTYHID" value="<%=XTYHID%>">
<table cellspacing="0" cellpadding="0" width="100%" height="100%">
	<tr>
		<td valign="top" height="95%">
			<div id="topdiv" style="height:100%;width:100%">
				<!-- 上帧 -->
				<iframe id="topcontent" name="topcontent" style="margin-left: 0px" src="#" frameBorder=0 width="100%" height="100%" vspace="0px" hspace="0px" scrolling="AUTO"></iframe>
			</div>

		</td>
	</tr>
</table>
<input type="hidden" id="actionType" value="list"/>
</body>


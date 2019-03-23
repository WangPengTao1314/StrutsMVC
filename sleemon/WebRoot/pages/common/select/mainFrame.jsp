<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ page import="com.hoperun.commons.util.*,com.hoperun.commons.select.object.*" %>
<%
	String selCommId = StringUtil.tranCodeP(request.getParameter("selCommId"));
	String tableId = StringUtil.tranCodeP(request.getParameter("tableId"));
	String selectType = StringUtil.tranCodeP(request.getParameter("selectType"));
	String keyNames = StringUtil.tranCodeP(request.getParameter("keyNames"));
	String selCommFields = StringUtil.tranCodeP(request.getParameter("selCommFields"));
	String oldCndt = StringUtil.tranCodeP(request.getParameter("oldCndt"));
	String specialTable = StringUtil.tranCodeP(request.getParameter("specialTable"));
	String specialAsTable = StringUtil.tranCodeP(request.getParameter("specialAsTable"));
	String isShowSearchLayer = StringUtil.tranCodeP(request.getParameter("isShowSearchLayer"));
	String searchCondition = StringUtil.tranCodeP(request.getParameter("searchCondition"));
	String showQYTree = StringUtil.tranCodeP(request.getParameter("showQYTree"));
	
	TableObject tableObject = (TableObject)request.getAttribute("TableObject");
	if(!searchCondition.equals("")&&!searchCondition.equals(" 1=1 ")){
		tableObject.setDisplayTreeFlag(false);
	}
%>
<html>
<head>
<title>通用选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
<script language="JavaScript">
	function funLoad(){
		document.forms[0].submit();
	}
</script>
</head>
<body style="border:0;margin:0px 0px 0px 0px;overflow:hidden" onLoad="funLoad()">
<iframe src="" name="MainList" id="MainList" style="width:100%;height:<%=(tableObject.getSubTableObjects()!=null&&tableObject.getSubTableObjects().length>0)?"330px":"540px"%>;" frameborder="0"></iframe>
<form target="MainList" method="post" action="${ctx}/common/select.shtml?action=doPost" style="display:none">
<%if(tableObject.getDisplayTreeFlag()){%>
<input type="hidden" name="cmdFlag" value="ShowTree">
<%}else{%>
<input type="hidden" name="cmdFlag" value="MainList">
<%}%>
<input type="hidden" name="selCommId" value="<%=selCommId%>">
<input type="hidden" name="tableId" value="<%=tableId%>">
<input type="hidden" name="selectType" value="<%=selectType%>">
<input type="hidden" name="keyNames" value="<%=keyNames%>">
<input type="hidden" name="selCommFields" value="<%=selCommFields%>">
<input type="hidden" name="oldCndt" value="<%=oldCndt%>">
<input type="hidden" name="specialTable" value="<%=specialTable%>">
<input type="hidden" name="specialAsTable" value="<%=specialAsTable%>">
<input type="hidden" name="isShowSearchLayer" value="<%=isShowSearchLayer%>">
<input type="hidden" name="searchCondition" value="<%=searchCondition%>">
<input type="hidden" name="showQYTree" value="<%=showQYTree%>">
</form>
<%if(tableObject.getSubTableObjects()!=null&&tableObject.getSubTableObjects().length>0){%>
<iframe name="SubFrame" id="SubFrame" src="${ctx}/common/select.shtml?action=doPost&cmdFlag=SubFrame&selectType=<%=selectType%>&tableId=<%=tableId%>&selCommId=<%=selCommId%>" name="subFrame" id="subFrame"  style="width:100%;height:210px" frameborder="0"></iframe>
<%}%>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ page import="java.util.*" %>
<html>
<head>
<title>列表</title>
 <link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
</head>

<body bgcolor="#FFFFFF" text="#000000">
  <table width="100%" cellspacing="1" cellpadding="0" class="lst" >
  <form name="selnode">
    <tr>
       <th nowrap>选中</th>
      <th nowrap>节点操作者</th>
      <th nowrap>操作者类型</th>
      <th nowrap>操作</th>
  </tr>
  <%
  	int i = 0;
  	List resList =(List) request.getAttribute("resList");
  	for (; i < resList.size(); i++) {
  	Map temMap = (Map) resList.get(i);
 %>
  <tr class="noClick" id="tr<%=i%>" style="cursor:hand" onClick="pickit(<%=i%>)">
  	<input type="hidden" name="node<%=i%>" id="node<%=i%>" value="<%=temMap.get("INSTANCENODEID")%>">
  	<input type="hidden" name="nodeType<%=i%>" id="nodeType<%=i%>" value="<%=temMap.get("TYPE")%>">
  	<td nowrap align='center' >
	<input type="checkbox" style="height:12px;valign:middle" >
	</td>
    <td nowrap height="12"><%=temMap.get("NAME")%></td>
    <td nowrap height="12"><span id="oper<%=i%>"><%=temMap.get("OPERATYPE")%></span></td>
    <td nowrap height="12"><%=temMap.get("OPERATIONNAME")%></td>
   </tr>
	<%
   }
  if (i == 0) {   
   %>
     <tr  bgcolor="#ffffff">
        <td colspan="15">系统暂无记录！</td>
     </tr>
  <%}%>
 
</table>
<script language="JavaScript">
	function pickit(vid) {
		//var operationId = eval("oper"+vid+".innerHTML");
		var instanceNodeId = eval("document.selnode.node"+vid+".value");
		var nodeType = eval("document.selnode.nodeType"+vid+".value");
		for (i=0; i<<%=i%>; i++) {
			eval("tr"+i+".style.backgroundColor = '#ffffff'");
		}
		eval("tr"+vid+".style.backgroundColor = '#9999ff'");
		
		var resultArray = new Array();
		resultArray[0] = instanceNodeId;
		resultArray[1] = nodeType;
		window.returnValue = resultArray;
		window.close();
	}
</script>
</body>
</html>

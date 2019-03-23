<%@ page import="java.util.*" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%
	
	 List list = null;

	  list = (List)request.getAttribute("resList");
	 
	 ArrayList  tempListMb = null;  //审批模板信息
	 ArrayList  tempListXM = null;  //审核人姓名
	 ArrayList  tempListTime =null; //审核时间
	 String errorInfo = ""; //出错信息
	 if (list!=null&&list.size()>2){
	 	tempListMb = (ArrayList)list.get(0);
	 	tempListXM = (ArrayList)list.get(1);
	 	tempListTime = (ArrayList)list.get(2);
	 }else {
	 	errorInfo = "没有定义审批流程或者程序出现错误,请联系管理员!";
	 	tempListMb = new ArrayList();
	 	tempListXM = new ArrayList();
	 		tempListTime = new ArrayList();
	 }
  
	int MBsize = tempListMb.size();
%>
<html>
<head>
    
</head>
<body background="${ctx}/styles/${theme}/images/index/lcgzbg.png">
<FORM id="spageone" method="POST"  name="pageone" >
<div  style="position:relative;top:50px">
<%if (MBsize > 0) { %>
<table background="${ctx}/styles/${theme}/images/index/newIndex2.png" style="border:2 solid #585858;" align="center"  cellspacing="0"  cellpadding="0"   width="90%">
	
<tr>
		<% for(int i = 0;i<tempListMb.size();i++) { %>
			<th bgcolor="#9dadaa" style="border:#585858 solid 1px;"><%=tempListMb.get(i)%></th>
			<%}%>
</tr>
		
		<tr  bgcolor="#b9c5c5">
		<% for(int i = 0;i<MBsize;i++) { %>
		<%if (i<tempListXM.size()) {%>
			<td align="center" style="border:#585858 solid 1px;"><%=tempListXM.get(i)%></td>
			<%} else {%>
			<td style="border:#585858 solid 1px;">&nbsp;</td>
			<%}}%>
	</tr>	 
	 <tr  bgcolor="#b9c5c5">
		<% for(int i = 0;i<MBsize;i++) { %>
		<%if (i<tempListTime.size()) {%>
			<td align="center" style="border:#585858 solid 1px;"><%=tempListTime.get(i)%></td>
			
			<%} else {%>
			<td style="border:#585858 solid 1px;">&nbsp;</td>
			<%}}%>
	</tr>	 
		
	
</table>
<%} else { %>
 草拟，尚未进入审核流程!
<%}%>
 </div>
</FORM>
</body>
</html>

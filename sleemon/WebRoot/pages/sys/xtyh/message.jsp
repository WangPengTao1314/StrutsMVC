<%@ page language="java"%>
<%@ page isErrorPage="true" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
  String info = request.getParameter("value");
  if(info==null)info="";
  if(info.equals("1"))
  info="点击标签选择需要设置的权限系统!";
%>
<html>
<head>
<title>信息提示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style  type="text/css">
	<!--
		body {  font-family: "宋体"; font-size: 12px; margin-top: 0px; margin-left: 10px; SCROLLBAR-FACE-COLOR: #eeeeee; SCROLLBAR-HIGHLIGHT-COLOR: #c0c0c0; SCROLLBAR-SHADOW-COLOR: #c0c0c0; SCROLLBAR-3DLIGHT-COLOR: #c0c0c0; SCROLLBAR-TRACK-COLOR: #c0c0c0; SCROLLBAR-DARKSHADOW-COLOR: #c0c0c0; color: #505050}
		td {  font-family: "宋体"; font-size: 12px; color: #505050; background-color: #efefef; text-align: center;}
		.engstyle {  font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; font-weight: bold; color: #FFFFFF}
		.norepeat {  background-repeat: no-repeat}
		.button {  font-family: "宋体"; font-size: 12px; color: #505050; background-color: #91D7FA; border: 1px #ffffff solid; cursor: hand}
		.tran {  font-family: "宋体"; font-size: 12px; color: #000000; border: 1px #000000 solid; filter: Alpha(Opacity=30)}
		input {  font-family: "宋体"; font-size: 12px; color: #000000; border: #000000; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px}
		hr {  border: solid; border-width: 0px 0px 0px 1px; border-color: black black black #91D7FA}
		th {  font-family: "宋体"; font-size: 13px; color: #FFFFFF; background-color: #1081BA; border-color: #97C8E1 #063750 #063750 #97C8E1; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px}
		.noborder {  border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px}
		a {  font-family: "宋体"; font-size: 12px; color: #505050; text-decoration: underline}
		a:hover {  font-family: "宋体"; font-size: 12px; color: #990000; text-decoration: none}
		a:link {  font-family: "宋体"; font-size: 12px; color: #505050; text-decoration: underline}
		textarea {  border: #000000; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px}
		.hideit { display:none }
		.showit { display:block }
		.title {  font-family: "宋体"; font-size: 14px; font-weight: bold; color: #ffffff; background-color: #1081BA}
		.repeatx {  background-repeat: repeat-x; background-position: 4px; background-attachment: fixed}
		table {  background-color: #1081BA}
		.readonly {  font-family: "宋体"; font-size: 12px; color: #505050; background-color: #cecece; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px}
	-->
</style>
</head>

<body text="#000000">
<br>
<div align="center"> </div>
<table width="100%" border="0" cellspan = 0 cellpadding="4" cellspacing="1" bgcolor="#0063ce" bordercolor="#000000">

<tr>
    <td><%=info%></td>
  <tr>

</table>
<br>

</body>
</html>
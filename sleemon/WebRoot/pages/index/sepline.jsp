<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<script type="text/javascript">
function show_hide_left(){
	if(window.parent.document.getElementById("midFrame").cols=="0,7,*")
	{
		window.parent.document.getElementById("midFrame").cols="180,7,*";
		document.getElementById("imgSep").src="${ctx}/styles/${theme}/images/left/bs_left.jpg";
	}
	else
	{
		window.parent.document.getElementById("midFrame").cols="0,7,*";
		document.getElementById("imgSep").src="${ctx}/styles/${theme}/images/left/bs_right.jpg";　
	}
}
</script>
</head>
<body style="margin:0px;">
<table height="100%" border="0" cellpadding="0" cellspacing="0" style="width:10px;" background="${ctx}/styles/${theme}/images/left/sep3pt.gif">
  <tr>
    <td><img src="${ctx}/styles/${theme}/images/left/bs_left.jpg" id="imgSep" name="imgSep" onClick="show_hide_left();" style="cursor:pointer;width:6px" title="打开/关闭菜单栏" /></td>
  </tr>
</table>
</body>
</html>
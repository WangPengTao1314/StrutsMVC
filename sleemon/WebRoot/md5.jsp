<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<HTML>
<HEAD>
<META http-equiv='Content-Type' content='text/html; charset=gb2312'>
<script type="text/javascript" src="${ctx}/scripts/core/jquery-1.4.1.min.js"></script>
<script type=text/javascript src="${ctx}/scripts/core/md5.js"></script>
<script type="text/javascript">
	$(function(){
		$("#bt").click(function(){
			var pwd = $("#pwd").val();
			$("#md5pwd").val(hex_md5(pwd));
		});
	
	});
</script>
<TITLE></TITLE>
</HEAD>
<BODY>
<form action="">
<table width="500" border="1" align="center">
  <tr>
    <td align="center" colspan="2">MD5加密</td>
  </tr>
  <tr>
    <td nowrap="nowrap">明文：</td>
    <td><input id="pwd" value="" size="50"></td>
  </tr>
  <tr>
    <td nowrap>密文：</td>
    <td><input id="md5pwd" value="" size="50"></input></td>
  </tr>
  <tr>
    <td colspan="2" align="center" > <input id="bt" type=button value="MD5加密"> <input id="bt" type="reset" value="重置"></td>
  </tr>
</table>
</form>
	
</BODY></HTML>
<!-- 
/**
  *@module 系统管理 
  *@func 工作组信息
  *@version 1.1 
  *@author 吴亚林
*/
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<title>工作组信息编辑</title>
</head>
<body>	
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;工作组信息编辑</td>
	</tr>
</table>
<form method="POST" action="#"  id="mainForm">
<input type="hidden" id="status" value="${status }">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
  <tr>
	  <td class="detail2">
         <table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
           <tr>
           		<input type="hidden" id="GZZXXID" value="${rst.GZZXXID }"/>
  			    <td width="15%" align="right" class="detail_label">工作组编号：</td>
				<td width="35%" class="detail_content">
				  <c:if test="${empty rst.GZZXXID}">
				     <input json="GZZBH" id="GZZBH" name="GZZBH" type="text" autocheck="true" label="工作组编号"    inputtype="String" maxlength="50" valueType="chinese:false" mustinput="true" value="${rst.GZZBH}">
				  </c:if>
				   <c:if test="${not empty rst.GZZXXID }">
				  <input json="GZZBH" id="GZZBH" name="GZZBH" type="text" autocheck="true" label="工作组编号"  readonly  inputtype="String" valueType="chinese:false" mustinput="true" value="${rst.GZZBH}">
				  </c:if>
				</td>
				<td width="15%" align="right" class="detail_label">工作组名称：</td>
				<td width="35%" class="detail_content">
					<input json="GZZMC" name="GZZMC" type="text" autocheck="true" label="工作组名称" maxlength="50" mustinput="true" inputtype="string" value="${rst.GZZMC }">
				</td>
			</tr>
			<tr>
				 <td width="15%" height="30" align="right" class="detail_label">工作组说明：</td>
				<td width="85%" class="detail_content" colspan="3">
				    <textarea json="GZZSM" name="GZZSM"rows="5" cols="50" inputtype="string" autocheck="true" label="工作组说明" maxlength='99'><c:out value="${rst.GZZSM}"></c:out></textarea>
				</td>
			</tr>						
         </table>	
       </td>
   </tr>
</table>
</form>	
		<input type="hidden" name="ctrType" id="ctrType" value="edit">
	</body>
	<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
	<%@ include file="/commons/jslibs.jsp"%>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript" src="${ctx}/pages/sys/gzzxx/gzzxx_Edit.js"></script>
</html>

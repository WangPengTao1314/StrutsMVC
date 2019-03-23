<!-- 
/**
  *@module 系统管理 
  *@fuc 系统角色主表编辑
  *@version 1.1 
  *@author 唐赟
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
	<title>系统角色信息新增或修改</title>
</head>
<body>	
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;系统角色编辑</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm">
<input type="hidden" id="status" value="${status }">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
  <tr>
	  <td class="detail2">
         <table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
           <tr>
  			  <td width="15%" align="right" class="detail_label">角色编号：</td>
					<td width="35%" class="detail_content">
						<input json="JSBH" id="JSBH" name="JSBH" type="text" valueType="chinese:false" autocheck="true" label="角色编号" maxlength="50" inputtype="String" mustinput="true" value="${rst.JSBH}">
					</td>
					<td width="15%" align="right" class="detail_label">角色名称：</td>
					<td width="35%" class="detail_content">
						<input json="JSMC" id="JSMC" name="JSMC" type="text" autocheck="true" label="角色名称" mustinput="true" maxlength="50" inputtype="string" value="${rst.JSMC }">
					</td>
			</tr><!-- 
			<tr>
			    <td width="15%" align="right" class="detail_label">上级角色：</td>
			    <td width="35%" class="detail_content">
					<input json="SJJS" name="SJJS" type="text" inputtype="string" label="上级角色"  seltarget="selBM" value="${rst.SJJS }">														
				   <!-- 
					<img align="absmiddle" name="selPZR" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						     onClick="selCommon('0', false, 'PZRID', 'RYXXID', 'forms[0]','PZRID,PZRXM', 'RYXXID,XM', '')"> 
				</td>
			</tr> -->
			<tr>
			    <td width="15%" height="30" align="right" class="detail_label">角色说明：</td>
				<td width="85%" class="detail_content" colspan=3>
				    <textarea json="JSSM" name="JSSM"rows="5" id="JSSM" cols="50"  label="角色说明"><c:out value="${rst.JSSM}"></c:out></textarea>

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
	<script type="text/javascript" src="${ctx}/pages/sys/xtjs/xtjs_Edit.js"></script>
</html>

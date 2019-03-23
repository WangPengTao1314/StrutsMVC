
<!--  
/**
 * @module 系统管理
 * @func 区域信息
 * @version 1.1
 * @author 张忠斌
 */
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"
			src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/base/area/area_Edit.js"></script>
		<title>区域信息编辑</title>
	</head>
	<body class="">
		<!-- <div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>-->
			
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td height="20px">&nbsp;&nbsp;当前位置：系统管理>>基础信息>>区域信息</td>
					<td width="50" align="right"></td>
				</tr>
			</table>
				 
		<form name="form" id="mainForm">
		<input type="hidden" value="${isNew}" id="isNew" name="isNew">
        <input type="hidden" value="${rst.STATE}" id="STATE">
		<input type="hidden" name="selectContion" value="STATE='启用'">
		<input type="hidden" name="ryxxSelectContion" value=" ryzt='启用' ">
			 
			<input type="hidden" name="selectParams"	
			value=" <c:if test="${rst != null}"> (AREA_NO_P <> '${rst.AREA_NO}'  or AREA_NO_P is null ) and </c:if>   STATE ='启用' and DEL_FLAG='0' ">
			<table id="jsontb" width="100%" border="0" cellpadding="4"
				cellspacing="4" class="detail">
				
				<input type="hidden" json="AREA_ID" value="${rst.AREA_ID}">
				<tr>
					<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="detail3">
							<tr>
								<td width="15%" align="right" class="detail_label" nowrap>
									区域编号：
								</td>
								<td width="35%" class="detail_content">
									<input id="AREA_NO" json="AREA_NO" name="AREA_NO" type="text"    autocheck="true"
										label="区域编号" inputtype="string" mustinput="true"  valueType="chinese:false"
										maxlength="30" value="${rst.AREA_NO}">
								</td>
								<td width="15%" align="right" class="detail_label">
									区域名称：
								</td>
								<td width="35%" class="detail_content">
									<input id="AREA_NAME" json="AREA_NAME" name="AREA_NAME" type="text" autocheck="true"
										label="区域名称" inputtype="string" mustinput="true"
										maxlength="50" value="${rst.AREA_NAME}">
							</tr>
						
							<tr>
								<td width="15%" align="right" class="detail_label" nowrap>
									上级区域编号：
								</td>
								<td width="35%" class="detail_content">
									<input id="AREA_ID_P" json="AREA_ID_P" name="AREA_ID_P" type="hidden"
										inputtype="string" seltarget="selJGXX" autocheck="true"
										maxlength="32" value="${rst.AREA_ID_P}">
										
									<input id="AREA_NO_P" json="AREA_NO_P"  name="AREA_NO_P" type="text" inputtype="string"
										seltarget="selJGXX" autocheck="true" maxlength="32"
										value="${rst.AREA_NO_P}" READONLY>
										
									<img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_18', false, 'AREA_ID_P', 'AREA_ID', 'forms[0]','AREA_ID_P,AREA_NO_P,AREA_NAME_P', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">
										                                                                                                                   <!--  selectParams hidden  -->
								</td>
								<td width="15%" align="right" class="detail_label">
									上级区域名称：
								</td>
								<td width="35%" class="detail_content">
									<input id="AREA_NAME_P" json="AREA_NAME_P" name="AREA_NAME_P" type="text" inputtype="string"
										seltarget="selJGXX" label="上级区域名称" maxlength="50"
										value="${rst.AREA_NAME_P}" READONLY>
								</td>
							</tr>
							 
						</table>
					</td>
				</tr>
			</table>
		</form>
				 
	<!-- 	</div>-->
	</body>
</html>

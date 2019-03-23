<!-- 
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/sys/bmgzwh/bmgz_Edit.js"></script>
	<title>新增或修改</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>
		<c:if test="${isNew == true}">
		当前位置：系统管理&gt;&gt;基础信息&gt;&gt;新增编码规则 
		</c:if>
		<c:if test="${isNew == false}">
		当前位置：系统管理&gt;&gt;基础信息&gt;&gt;修改编码规则 
		</c:if>
		</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <input type="hidden" json="BMGZID" value="${rst.BMGZID}">
				<tr>
					<td width="15%" align="right" class="detail_label">编码编号：</td>
					<td width="35%" class="detail_content">
						    <input json="BMBH" name="BMBH" type="text" autocheck="true" label="编码编号" inputtype="string" mustinput="true"
						       readonly <c:if test="${isNew == true}"> value="自动生成"</c:if>
								<c:if test="${isNew == false}">value="${rst.BMBH}"</c:if>>
					</td>
					<td width="15%" align="right" class="detail_label">SEQUENCE名称：</td>
					<td width="35%" class="detail_content">
						<input json="SEQUENCEMC" name="SEQUENCEMC" id="SEQUENCEMC" autocheck="true" label="SEQUENCE名称" inputtype="string" mustinput="true" maxlength="30" value="${rst.SEQUENCEMC}"<c:if test="${isNew == false}">readonly</c:if>>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">编码对象：</td>
					<td width="35%" class="detail_content">
						<input json="BMDX" name="BMDX" type="text" autocheck="true" label="编码对象" inputtype="string" mustinput="true" maxlength="30" value="${rst.BMDX}">
					</td>
					<td width="15%" align="right" class="detail_label">规则名称：</td>
					<td width="35%" class="detail_content">
					    <input json="GZMC" name="GZMC" type="text" autocheck="true" label="规则名称" inputtype="string"  mustinput="true" maxlength="50" value="${rst.GZMC}">
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td colspan=4 width="35%" class="detail_content">
                       	<textarea  json="REMARK" name="REMARK" inputtype="string" maxlength="250" rows="3" cols="80%" ><c:out value="${rst.REMARK}"></c:out></textarea>
					</td>
		      	</tr>			
			</table>
		</td>
	</tr>
</table>
</form>
</body>

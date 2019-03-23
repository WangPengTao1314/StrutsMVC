<%--
/**
 * @module 质检管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author 
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sample/newmasterslave/NewCpbltzd_Edit.js"></script>
	<title>成品不良品通知单信息编辑</title>
</head>
<body class="bodycss1">	
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：质检管理&gt;&gt;质检管理&gt;&gt;成品不良品通知单编辑</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" name="tiaojian" value=" DELFLAG=0 and STATE='启用'">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right" class="detail_label">成品不良通知单编号：</td>
					<td width="35%" class="detail_content">
						<input type="hidden" id="CPBLTZDID" value="${rst.CPBLTZDID}"/>
                        <input type="text" json="CPBLTZDBH" name="CPBLTZDBH" autocheck="true" inputtype="string" label="成品不良通知单编号" maxlength="32" 
                        value='<c:if test="${rst.CPBLTZDBH==null}">自动生成</c:if><c:if test="${rst.CPBLTZDBH!=null}">${rst.CPBLTZDBH}</c:if>' 
                        mustinput='true' readonly="readonly"/>&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">成品质检单编号：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="CPZJDBH" name="CPZJDBH" autocheck="true" seltarget="selMXXX" inputtype="string" label="成品质检单编号"
                         maxlength="32" value="${rst.CPZJDBH}" mustinput='true' />
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">通知数量：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="TZSL" name="TZSL" autocheck="true" inputtype="float" label="通知数量" valueType="6,2" maxlength="8" value="${rst.TZSL}" />&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">检验日期：</td>
					<td width="35%" class="detail_content">
						<c:if test="${ empty rst.CPBLTZDID}">
                        <input type="text" json="JYSJ" name="JYSJ" autocheck="true" inputtype="string" label="检验时间" maxlength="19" onclick="SelectDate();" value="${rst.JYSJ}" />&nbsp;
                        <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(JYSJ);">
                        </c:if>
                        <c:if test="${ not empty rst.CPBLTZDID}">
                        <input type="text" json="JYSJ" name="JYSJ" autocheck="true" inputtype="string" label="检验时间" maxlength="19"  value="${rst.JYSJ}" />&nbsp;
                        </c:if>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">处理意见：</td>
					<td colspan="3" class="detail_content">
						<textarea cols="60" json="CLYJ" name="CLYJ" autocheck="true" inputtype="string" label="备注" rows="2" maxlength="160">${rst.CLYJ}</textarea>
                    </td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td colspan="3" class="detail_content">
						<textarea cols="60" json="REMARK" name="REMARK" autocheck="true" inputtype="string" label="备注" rows="2" maxlength="160">${rst.REMARK}</textarea>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>


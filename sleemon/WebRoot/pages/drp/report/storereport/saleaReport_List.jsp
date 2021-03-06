<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/report/storereport/StoreReport_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		li{
			float: left;
			width: 18%;
			font-size: 13px;
			height: 30px;
			line-height: 30px;
			text-align: center;
			border: ridge 1px white;
			font-weight: bold;
			color: #CDC8B1;
		}
		.li1{
			background-color: #F4CC87;
		}
		.li2{
			background-color: #F4CC87;
		}
	</style>
</head>
<body >
<table width="99.8%" height="20px;" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：报表中心&gt;&gt;报表管理&gt;&gt;直营办报表</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
</table>
<div style="height: 10px;width: 100%;background-color: #4F69A2;">
</div>
<div>
	<input type="hidden" id="RUNQIAN_REPORT_URL" value="${RUNQIAN_REPORT_URL}">
	<input type="hidden" id="SLEEMON_REPORT_URL" value="${SLEEMON_REPORT_URL}">
	<ul>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_SALEA_ORDER eq 1}">
			<a 
					href="#" onclick="openUrl('销售情况统计表','report/raq.shtml?action=toFrame&rptConFile=toSaleaOrderReport','0')" style="color: black;font-weight: normal;"
			>
				销售情况统计表
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_SALEA_ORDER ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_BWS_SALEA_STOREOUT eq 1}">
			<a 
					href="#" onclick="openUrl('发货情况统计表','report/raq.shtml?action=toFrame&rptConFile=toStoreoutaReport','0')" style="color: black;font-weight: normal;"
			>
				发货情况统计表
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_SALEA_STOREOUT ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_BWS_RETURNA eq 1}">
			<a 
					href="#" onclick="openUrl('退货情况统计表','report/raq.shtml?action=toFrame&rptConFile=toReturnaReport','0')" style="color: black;font-weight: normal;"
			>
				退货情况统计表
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_RETURNA ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_BWS_INVOICING eq 1}">
			<a 
					href="#" onclick="openUrl('进销存报表','report/raq.shtml?action=toFrame&rptConFile=toInvocAcctaReport','0')" style="color: black;font-weight: normal;"
			>
				进销存报表
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_INVOICING ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">&nbsp;
		</li>
		
	</ul>
	<ul>
		<li class="li1">
			&nbsp;
		</li>
		<li class="li2">&nbsp;
		</li>
		<li class="li1">&nbsp;
		</li>
		<li class="li2">&nbsp;
		</li>
		<li class="li1">&nbsp;
		</li>
	</ul>
	<ul>
		<li class="li1">&nbsp;
		</li>
		<li class="li2">&nbsp;
		</li>
		<li class="li1">&nbsp;
		</li>
		<li class="li2">&nbsp;
			&nbsp;
		</li>
		<li class="li1">&nbsp;
			&nbsp;
		</li>
	</ul>
</div>
<form action="#" method="post" id="queryForm" >
	<input type="hidden" name="S_USER_ID" id="S_USER_ID" value="${XTYHID}">
	<input type="hidden" name="S_ZTXXID" id="S_ZTXXID" value="${ZTXXID}">
	<input type="hidden" name="S_GOTO_FLG" id="S_GOTO_FLG" value="true"/>
</form>
</body>

<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<%@ include file="/pages/common/uploadfile/importfile.jsp"%>
</html>
<!--  
/**
 *@module 渠道管理-终端管理
 *@func   加盟商门店指标
 *@version 1.1
 *@author zcx
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/channTermianl/ChannTermianl_Edit.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="bodycss1">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<form method="POST" action="#" id="mainForm">
	    <input type="hidden" name="selectParams" />
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						 
						  <tr>
					<td width="15%" align="right" class="detail_label">门店指标编号：</td>
					<td width="35%" class="detail_content">
					        <input json="TERMINAL_QUOTA_ID" name="TERMINAL_QUOTA_ID" size="40"  id="TERMINAL_QUOTA_ID" type="hidden" value="${rst.TERMINAL_QUOTA_ID}"/>
						    <input json="TERMINAL_QUOTA_NO" name="TERMINAL_QUOTA_NO" size="40"  id="TERMINAL_QUOTA_NO" type="text" autocheck="true" label="加盟商门店编号" inputtype="string" mustinput="true"
						       readonly <c:if test="${not empty rst.TERMINAL_QUOTA_NO}">value="${rst.TERMINAL_QUOTA_NO}"</c:if>
									<c:if test="${empty rst.TERMINAL_QUOTA_NO}">value="自动生成"</c:if>>
					</td>
					<td width="15%" align="right" class="detail_label">区域编号：</td>
					<td width="35%" class="detail_content">
	                  	<input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" value="${rst.AREA_ID}" label="区域ID"/>
					    <input json="AREA_NO" name="AREA_NO" id="AREA_NO"
					    type="text" size="40"  value="${rst.AREA_NO}"
					    label="区域编号" readonly  autocheck="true"  inputtype="string" mustinput="true" />
					    <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
						     onClick="selCommon('BS_138', false, 'AREA_NO', 'AREA_NO', 'forms[0]','AREA_ID,AREA_NO,AREA_NAME,WAREA_ID,WAREA_NO,WAREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME,AREA_ID_P,AREA_NO_P,AREA_NAME_P', 'selectParams')">
                  	
                  	</td>
				</tr>
				<tr>
				   <td width="15%" align="right" class="detail_label">区域名称：</td>
				   <td width="35%" class="detail_content">
					  <input id="AREA_NAME" name="AREA_NAME" json="AREA_NAME" value="${rst.AREA_NAME}" size="40" readonly autocheck="true" inputtype="string" value="${rst.AREA_NAME}" />				
 					</td>
					<td width="15%" align="right" class="detail_label">战区编号：</td>
					<td width="35%" class="detail_content">
					   <input json="WAREA_ID" name="WAREA_ID" id="WAREA_ID" type="hidden" value="${rst.WAREA_ID}" label="战区ID"/>
					   <input json="WAREA_NO" name="WAREA_NO" id="WAREA_NO" type="text"
									label="战区编号" value="${rst.WAREA_NO}"
									size="40" readonly/> 
 					</td>
				</tr>
				<tr> 
			        <td width="15%" align="right" class="detail_label">战区名称：</td>
			        <td width="35%" class="detail_content">
				    <input json="WAREA_NAME" name="WAREA_NAME" id="WAREA_NAME" type="text"
								label="战区名称" value="${rst.WAREA_NAME}"
								size="40" readonly/> 
 					</td>
				    <td width="15%" align="right" class="detail_label">年份：</td>
					<td width="35%" class="detail_content">
					   <select name="YEAR" id="YEAR" json="YEAR" style="width:275px;" label="年份" value="${rst.YEAR}">
				       </select>
 					</td>
 				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">
						月份：
					</td>
					<td width="35%" class="detail_content">
						<select name="MONTH" id="MONTH" json="MONTH" style="width:275px;" label="月份" value="${rst.MONTH}">
				        </select>
					</td>
					<td width="15%" align="right" class="detail_label">
						数量：
					</td>
					<td width="35%" class="detail_content">
						<input id="QUOTA_NUM" name="QUOTA_NUM" json="QUOTA_NUM" value="${rst.QUOTA_NUM}"
							type="text" size="40"  label = "数量" autocheck="true" onchange="chk()"/>
					</td>
				   </tr>
				   <tr>
					<td width="15%" align="right" class="detail_label">
						备注：
					</td>
					<td width="35%" class="detail_content" colspan="3">
						<textarea id="REMARK" name="REMARK" json="REMARK"  
						  rows="2" cols="32%">${rst.REMARK}</textarea>
					</td>
				   </tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		SelDictShow("YEAR","89","${rst.YEAR}", "");
				//SelDictShow("YEAR", "BS_159", '${rst.YEAR}', "");
		SelDictShow("MONTH","168","${rst.MONTH}", "");
    </script>
   </div>
</body>

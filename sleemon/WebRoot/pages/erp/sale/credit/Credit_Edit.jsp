<!-- 
 /**
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 郭利伟
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
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/erp/sale/credit/Credit_Edit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>信用额度设定</title>
	<script type="text/javascript" src="brand_List.js"></script></head>
	<body>
		<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					 <!--占位用行，以免显示数据被浮动层挡住-->
			        <td height="20px">&nbsp;</td>
			    </tr>
				<tr>
					<td>
						<form name="main" id="mainForm">
							<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label">
													渠道编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" type="hidden" value="${rst.CHANN_ID}">
													<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" type="text" label="渠道编号" value="${rst.CHANN_NO}" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" label="渠道名称" value="${rst.CHANN_NAME}" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													渠道简称：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_ABBR" name="CHANN_ABBR" id="CHANN_ABBR" type="text" label="渠道简称" value="${rst.CHANN_ABBR}" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													初始信用额度：
												</td>
												<td width="35%" class="detail_content" >
													<input json="INI_CREDIT" name="INI_CREDIT" id="INI_CREDIT" type="text" label="初始信用额度" autocheck="true" inputtype="float" valueType="8,2" value="${rst.INI_CREDIT}">
													<input json="INI_CREDIT_LAST" name="INI_CREDIT_LAST" id="INI_CREDIT_LAST" type="hidden" label="初始信用额度" value="${rst.INI_CREDIT}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													渠道类别：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_TYPE" name="CHANN_TYPE" id="CHANN_TYPE" type="text" label="渠道类型" value="${rst.CHANN_TYPE}" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道级别：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHAA_LVL" name="CHAA_LVL" id="CHAA_LVL" type="text" label="渠道级别"  value="${rst.CHAA_LVL}" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													区域编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="AREA_NO" name="AREA_NO" id="AREA_NO" type="text" label="区域编号" value="${rst.AREA_NO}" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													区域名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text" label="区域名称" value="${rst.AREA_NAME}" READONLY>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>

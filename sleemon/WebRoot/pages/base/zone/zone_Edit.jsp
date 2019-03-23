
<!--  
/**
 * @module 系统管理
 * @func 行政区划
 * @version 1.1
 * @author 张涛
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
			src="${ctx}/pages/common/select/selCommJs.js">
</script>
		<script type="text/javascript"
			src="${ctx}/pages/base/zone/zone_Edit.js">
</script>
		<title>行政区划编辑</title>
	</head>
	<body class="bodycss1">
		<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)"
								title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B"
								accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>


			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<!--占位用行，以免显示数据被浮动层挡住-->
					<td height="20px">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<form name="form" id="mainForm">
							<input type="hidden" name="selectContion" value="STATE='启用'">
							<input type="hidden" name="ryxxSelectContion" value=" ryzt='启用' ">
							<input type="hidden" name="selectContion1"
								value="DELFLAG = 0 and JGZT in( '启用','制定')">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>												
												<td width="15%" align="right" class="detail_label">
													国家：
												</td>
												<td width="35%" class="detail_content">
													<input json="NATION" name="NATION" type="text" autocheck="true"
														label="国家" inputtype="string" mustinput="true" maxlength="50"
														value="${rst.NATION}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													省份：
												</td>
												<td width="35%" class="detail_content">
													<input json="PROV" name="PROV" type="text" autocheck="true"
														label="省份" inputtype="string" mustinput="true" maxlength="50"
														value="${rst.PROV}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													城市：
												</td>
												<td width="35%" class="detail_content">
													<input json="CITY" name="CITY" type="text" autocheck="true"
														label="城市" inputtype="string" mustinput="true" maxlength="50"
														value="${rst.CITY}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													区县：
												</td>
												<td width="35%" class="detail_content">
													<input json="COUNTY" name="COUNTY" type="text"
														autocheck="true" label="区县" inputtype="string"
														mustinput="true" maxlength="50" value="${rst.COUNTY}">
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

<!-- 
/**
  *@module 系统管理
  *@fuc 单位维护详细信息 
  *@version 1.0
  *@author 王栋斌
*/

 -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<title>单位维护</title>
	</head>
	<body class="bodycss1">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">
								单位编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.MEAS_UNIT_NO}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								单位名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.MEAS_UNIT_NAME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								单位英文名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.UNIT_NAME_EN}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								单位类型：
							</td>
							<td width="35%" class="detail_content">
								${rst.UNIT_TYPE}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								制单人：
							</td>
							<td width="35%" class="detail_content">
								${rst.CRE_NAME}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								制单时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.CRE_TIME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								更新人：
							</td>
							<td width="35%" class="detail_content">
								${rst.UPD_NAME}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								更新时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.UPD_TIME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								制单部门：
							</td>
							<td width="35%" class="detail_content">
								${rst.DEPT_NAME}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								制单机构名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.ORG_NAME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
								${rst.STATE}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
							</td>
							<td width="35%" class="detail_content">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

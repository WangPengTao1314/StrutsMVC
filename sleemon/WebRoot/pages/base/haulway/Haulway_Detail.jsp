<!-- 
/**
  *@module 系统管理
  *@fuc 路线信息详细信息 
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
		<title>路线信息</title>
	</head>
	<body class="bodycss1" >
		<table width="100%" border="0" cellpadding="4" cellspacing="4">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">
								路线编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.HAULWAY_NO}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								路线名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.HAULWAY_NAME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								发出城市：
							</td>
							<td width="35%" class="detail_content">
								${rst.DELV_CITY}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								到达城市：
							</td>
							<td width="35%" class="detail_content">
								${rst.ARRV_CITY}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								发货点编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.SHIP_POIT_NO}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								发货点名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.SHIP_POIT_NAME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.CHANN_NO}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.CHANN_NAME}&nbsp;
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								全程：
							</td>
							<td width="35%" class="detail_content">
								${rst.LENGTH}&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">
								天数：
							</td>
							<td width="35%" class="detail_content">
								${rst.DAYS}&nbsp;
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
								制单机构：
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
								 &nbsp;
							</td>
							 
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content"  colspan="4">
								${rst.REMARK}&nbsp;
							</td>
						</tr>
						 
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

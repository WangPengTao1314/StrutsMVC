<!-- 
 *@module 渠道管理-终端管理
 *@func   加盟商门店指标
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>加盟商门店指标</title>
	</head>
	<body class="bodycss1">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						 <tr>
							<td width="15%" align="right" class="detail_label">&nbsp; 
								门店指标编号： 
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERMINAL_QUOTA_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								战区编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.WAREA_NO}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								战区名称：
							</td>
							<td width="35%" class="detail_content">
                                 ${rst.WAREA_NAME} 							 
							</td>
							<td width="15%" align="right" class="detail_label">
								区域编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.AREA_NO}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								区域名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.AREA_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								年份：
							</td>
							<td width="35%" class="detail_content">
								${rst.YEAR}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								月份：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.MONTH}
							</td>
							<td width="15%" align="right" class="detail_label">
								门店指标数量：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.QUOTA_NUM}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								制单人：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CRE_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								制单时间：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CRE_TIME}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								${rst.REMARK}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

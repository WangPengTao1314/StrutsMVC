<!-- 
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>装修补贴标准维护详情</title>
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
								装修补贴标准编号： 
							</td>
							<td width="35%" class="detail_content">
								 ${rst.RNVTN_SUBST_STD_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								品牌：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.BRAND}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								最低面积(平米)：
							</td>
							<td width="35%" class="detail_content">
                                 ${rst.MIN_AREA} 							 
							</td>
							<td width="15%" align="right" class="detail_label">
								标准面积(平米)：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.STD_AREA}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								造价成本(元/平米)：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.BUILD_COST}
							</td>
							<td width="15%" align="right" class="detail_label">
								配饰费用(元/平米)：
							</td>
							<td width="35%" class="detail_content">
								${rst.DECORATE_COST}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								补贴标准版本：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.RNVTN_SUBST_STD_VSION}
							</td>
							<td width="15%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.STATE}
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

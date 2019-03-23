<%--
/**
 * @module 质检管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author zhuxw
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>成品不良品通知单信息详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right" class="detail_label">成品不良通知单编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.CPBLTZDBH}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">成品质检单编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.CPZJDBH}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">供应商编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.WLDWBH}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">供应商名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.WLDWMC}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">款号：</td>
					<td width="35%" class="detail_content">
                        ${rst.WLBH}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">款名：</td>
					<td width="35%" class="detail_content">
                        ${rst.WLMC}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">品牌：</td>
					<td width="35%" class="detail_content">
                        ${rst.PP}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">年份：</td>
					<td width="35%" class="detail_content">
                        ${rst.NF}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">季节：</td>
					<td width="35%" class="detail_content">
                        ${rst.JJ}&nbsp;
					</td>				
					<td width="15%" align="right" class="detail_label">颜色名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.YSMC}&nbsp;
					</td>
				</tr>
				<tr>					
					<td width="15%" align="right" class="detail_label">物料信息ID：</td>
					<td width="35%" class="detail_content">
                        ${rst.WLXXYSID}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">退回数量：</td>
					<td width="35%" class="detail_content">
                        ${rst.THSL}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">通知不合格数量：</td>
					<td width="35%" class="detail_content">
                        ${rst.TZBHGSL}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">让步接收数量：</td>
					<td width="35%" class="detail_content">
                        ${rst.RBJSSL}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">报废数量：</td>
					<td width="35%" class="detail_content">
                        ${rst.BFSL}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">修色数量：</td>
					<td width="35%" class="detail_content">
                        ${rst.XSSL}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">检验人：</td>
					<td width="35%" class="detail_content">
                        ${rst.JYRXM}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">检验时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.JYSJ}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">外部原因标记：</td>
					<td width="35%" class="detail_content">
					<c:if test="${rst.WBYYBJ==0}">否</c:if><c:if test="${rst.WBYYBJ==1}">是</c:if>
                        &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">通知数量：</td>
					<td width="35%" class="detail_content">
                        ${rst.TZSL}&nbsp;
					</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">通知合格数量：</td>
					<td colspan="3" class="detail_content">
                        ${rst.TZHGSL}&nbsp;
					</td>
      			</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">处理意见：</td>
					<td colspan="3" class="detail_content">
                        ${rst.CLYJ}&nbsp;
					</td>
      			</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td colspan="3" class="detail_content">
                        ${rst.REMARK}&nbsp;
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>


<!-- /**

 *@module 财务管理

 *@fuc 帐套信息维护详细信息

 *@version 1.1

 *@author 唐赟
*/ -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<title>帐套信息维护详细信息</title>
	</head>
	<body class="bodycss1">	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<form name="form" method="post" action="#">
						<input type="hidden" name="XTYHID" value="${rst.XTYHID}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4"
										class="">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
									
													<tr>
														<td width="15%" height="30" align="right" class="detail_label">
															帐套编号：
														</td>
														<td width="35%" class="detail_content">
															${rst.ZTBH}&nbsp;
														</td>
														<td width="15%" height="30" align="right" class="detail_label">
															帐套名称：
														</td>
														<td width="35%" class="detail_content">
															${rst.ZTMC }&nbsp;
														</td>
													</tr>
													<tr>
														<td width="15%" height="30" align="right" class="detail_label">
															月结帐标记：
														</td>
														<td width="35%" class="detail_content">
														<c:if test="${rst.YJZBJ eq 0}">否</c:if>
														<c:if test="${rst.YJZBJ eq 1}">是</c:if>
															&nbsp;
														</td>
														<td width="15%" height="30" align="right"
															class="detail_label">
															上级帐套：
														</td>
														<td width="35%" class="detail_content">
															${rst.SJZTMC}&nbsp;
														</td>
													</tr>
													<tr>	
														<td width="15%" height="30" align="right"
															class="detail_label">
															增值税号：
														</td>
														<td width="35%" class="detail_content">
															${rst.ZZSH}&nbsp;
														</td>
														<td width="15%" height="30" align="right"
															class="detail_label">
															营业执照号：
														</td>
														<td width="35%" class="detail_content">
															${rst.YYZZH}&nbsp;
														</td>
													</tr>	
													<tr>
														
														<td width="15%" height="30" align="right" class="detail_label" >
															帐套类别：
														</td>
														<td width="35%" class="detail_content" >${rst.ZTLB}&nbsp;</td>
														<td width="15%" height="30" align="right" class="detail_label">
															创建人：
														</td>
														<td width="35%" class="detail_content">
															${rst.CRENAME }&nbsp;
														</td>
														
													</tr>
													<tr>
													     <td width="15%" height="30" align="right" class="detail_label">
															创建时间：
														</td>
														<td width="35%" class="detail_content" >
															${rst.CRETIME }&nbsp;
														</td>
														<td width="15%" height="30" align="right" class="detail_label">
															状态：
														</td>
														<td width="35%" class="detail_content">
															${rst.STATE }&nbsp;
														</td>
													
													</tr>
												</table>
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
		<br>
		<input type="hidden" name="ctrType" id="ctrType" value="view">
	</body>
	<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
	<%@ include file="/commons/jslibs.jsp"%>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript" src="${ctx}/pages/erp/cw/ztxxwh/ztxxwh_Detail.js"></script>
</html>

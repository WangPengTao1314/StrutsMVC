<%-- 
  *@module 系统管理
  *@func 工作组信息
  *@version 1.1
  *@author 吴亚林
--%>
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
		<title>工作组信息详情</title>
	</head>
	<body class="bodycss1">	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<form name="form" method="post" action="#">
						<input type="hidden" name="GZZXXID" value="${rst.GZZXXID}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1" class="detail_lst"> 
							<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4"
										class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1"
													cellspacing="1" class="detail_lst">
													
													<tr>
														<td width="15%" height="30" align="right"
															class="detail_label">
															工作组编号：
														</td>
														<td width="35%" class="detail_content">
															${rst.GZZBH}&nbsp;
														</td>
														<td width="15%" height="30" align="right" class="detail_label">
															工作组名称：
														</td>
														<td width="35%" class="detail_content">
															${rst.GZZMC }&nbsp;
														</td>
													</tr>
													<tr>
														<td width="15%" height="30" align="right"
															class="detail_label">
															工作组说明：
														</td>
														<td width="35%" class="detail_content">
															${rst.GZZSM}&nbsp;
														</td>
														<td width="15%" height="30" align="right" class="detail_label" >
															状态：
														</td>
														<td width="35%" class="detail_content" >
															${rst.GZZZT }&nbsp;
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
</html>

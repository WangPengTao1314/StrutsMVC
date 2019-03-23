<%-- 
  *@module 系统管理
  *@fuc 系统角色详细信息页面
  *@version 1.1
  *@author 唐赟
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
		<title>系统角色信息详情</title>
	</head>
	<body class="bodycss1">	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<form name="form" method="post" action="#">
						<input type="hidden" name="XTJSID" value="${rst.XTJSID}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4"
										class="">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1"
													cellspacing="1" class="detail_lst">
													
													<tr>
														<td width="15%" height="30" align="right"
															class="detail_label">
															角色编号：
														</td>
														<td width="35%" class="detail_content">
															${rst.JSBH}&nbsp;
														</td>
														<td width="15%" height="30" align="right" class="detail_label">
															角色名称：
														</td>
														<td width="35%" class="detail_content">
															${rst.JSMC }&nbsp;
														</td>
													</tr>
													<tr>
														<td width="15%" height="30" align="right"
															class="detail_label">
															角色说明：
														</td>
														<td width="35%" class="detail_content" >
															${rst.JSSM}&nbsp;
														</td>
														<td width="15%" height="30" align="right" class="detail_label" >
															状态：
														</td>
														<td width="35%" class="detail_content" >
															${rst.STATE }&nbsp;
														</td> 
													</tr>
												</table>
											</td>
										</tr>
									</table><br>
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

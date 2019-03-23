<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/style.css">
		<title>查询菜单</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="10" cellpadding="0">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="34" height="27" class="wz_left">
								&nbsp;
							</td>
							<td class="wz_bg">
								当前位置：系统管理 &gt;&gt; 菜单管理 &gt;&gt; 查询菜单
							</td>
							<td width="12" class="wz_right">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post"
						action="${ctx}/system/menu.shtml?action=toList"
						onsubmit="return FormValidate(0);">
						<input type="hidden" name="SM" value="${param.SM}">
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									查询菜单
								</td>
							</tr>
							<tr>
								<td width="100%" align="center">
									<table width="70%" border="0" cellpadding="4" cellspacing="4"
										class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1"
													cellspacing="1" class="detail3">
													<tr>
														<td width="25%" height="30" align="right"
															class="detail_label">
															菜单编号：
														</td>
														<td width="75%" height="30" align="left"
															class="detail_content">
															&nbsp;
															<input name="menuId" type="text" autocheck="true"
																inputtype="string" maxlength="6" value="${param.menuId}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															菜单名称：
														</td>
														<td height="30" align="left" class="detail_content">
															&nbsp;
															<input name="menuName" type="text" inputtype="string"
																size="50" autocheck="true" maxlength="100"
																value="${param.menuName}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															上级菜单：
														</td>
														<td height="30" align="left" class="detail_content">
															&nbsp;
															<input name="parentId" type="text" inputtype="string"
																size="50" autocheck="true" maxlength="100"
																value="${param.parentId}">
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="49" align="center" valign="middle">
									<input type="submit" class="btn" value="查 询">
									&nbsp;&nbsp;
									<input type="reset" class="btn" value="重 置">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
</html>

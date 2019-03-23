<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>重构系统权限</title>
	</head>
	<body>	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						>
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：系统管理 &gt;&gt; 权限管理 &gt;&gt; 重构系统权限
							</td>
							<td width="50" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post"
						action="${ctx}/system/readxml.shtml?action=readXTMK"
						onsubmit="return doSave();">
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									重构系统权限
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
														<td width="50%" height="30" align="right"
															class="detail_label">
															选择重构的系统：
														</td>
														<td width="50%" class="detail_content">
															<select name="SYSTYPE" style="width:155px" >
																<option value="XT" >系统管理</option>
																<option value="BS" >业务系统</option>
																<option value="FX" >分销管理</option>
																</select>
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
									<input type="submit" class="btn" value="重 构">
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
	<script type="text/javascript">
		function doSave(){
			showWaitPanel();	
			setTimeout("form.submit();",100);
			return false;
		}
		
	</script>
</html>

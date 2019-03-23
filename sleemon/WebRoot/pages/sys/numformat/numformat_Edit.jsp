
<!--  
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
-->
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
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/sys/numformat/numformat_Edit.js"></script>
		<title>机构信息编辑</title>
	</head>
	<body class="bodycss1">
		<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
						</td>
					</tr>
				</table>
			</div>
		
				
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				 <tr>
					 <!--占位用行，以免显示数据被浮动层挡住-->
			        <td height="20px">&nbsp;</td>
			    </tr>
				<tr>
					<td>
						<form name="form" id='mainForm'>
							<input type="hidden" name="selectContion" value="STATE='启用'">
							<input type="hidden" name="ryxxSelectContion" value=" ryzt='启用' ">
							<input type="hidden" name="selectContion1"	value="DELFLAG = 0 and JGZT = '启用'">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													小数位格式化名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="NUMFORMATMC" json="NUMFORMATMC" name="NUMFORMATMC" type="text" autocheck="true" label="小数位格式化名称" inputtype="string" mustinput="true" maxlength="30" value="${rst.NUMFORMATMC}">
												</td>
												<td width="15%" align="right" class="detail_label">
													数字类型：
												</td>
												<td width="35%" class="detail_content">
													<input id="NUMTYPE" json="NUMTYPE" name="NUMTYPE" type="text" autocheck="true"
														label="数字类型" inputtype="string" mustinput="true"
														maxlength="100" value="${rst.NUMTYPE}">
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													小数位：
												</td>
												<td width="35%" class="detail_content">
													<input json="DECIMALS" name="DECIMALS" type="text" autocheck="true"
														label="小数位" inputtype="string" maxlength="50"
														value="${rst.DECIMALS }">
												</td>
												<td width="15%" align="right" class="detail_label">
													进位方式：
												</td>
												<td width="35%" class="detail_content">
													<input json="ROUNDTYPE" name="ROUNDTYPE" type="text" autocheck="true"
														label="进位方式" inputtype="string" maxlength="50"
														value="${rst.ROUNDTYPE }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													创建时间：
												</td>
												<td width="35%" class="detail_content">
													<input id="CRETIME" json="CRETIME" name="CRETIME" type="text"
														inputtype="string"  autocheck="true" maxlength="32" value="${rst.CRETIME }">
													
												</td>
												<td width="15%" align="right" class="detail_label">
													状态：
												</td>
												<td width="35%" class="detail_content">
													<input id="STATE" name="STATE" type="text" inputtype="string" label="状态" maxlength="32" value="${rst.STATE }" >
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
		</div>
	</body>
</html>

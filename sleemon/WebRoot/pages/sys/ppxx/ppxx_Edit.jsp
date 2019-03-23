<!-- 
 *@module 系统管理
 *@func 品牌信息
 *@version 1.1
 *@author 郭利伟
 *  -->
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
		<script type="text/javascript" src="${ctx}/pages/sys/ppxx/ppxx_Edit.js"></script>
		<title>品牌信息编辑</title>
	</head>
	<body>
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
		<!--浮动按钮层结束-->
		<!--占位用table，以免显示数据被浮动层挡住-->
		<table width="100%" height="25px">
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<form name="mainForm" id="mainForm">
						<table id="jsontb" width="100%" border="0" cellpadding="4"
							cellspacing="4" class="detail">
							<tr>
								<td class="detail2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="detail3">
										<tr>
											<td width="15%" align="right" class="detail_label">
												品牌编号：
											</td>
											<td width="35%" class="detail_content">
												<input json="BRAND_ID" name="BRAND_ID" id="BRAND_ID" type="text" autocheck="true" label="品牌编号"  maxlength="50" value="${rst.BRAND_ID}">
											</td>
											<td width="15%" align="right" class="detail_label">
												品牌名称：
											</td>
											<td width="35%" class="detail_content">
												<input json="BRAND" name="BRAND" id="BRAND" type="text" autocheck="true" label="品牌名称"   maxlength="50" value="${rst.BRAND}">
											</td>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												帐套名称：
											</td>
											<td width="35%" class="detail_content">
												<input json="LEDGER_NAME" name="LEDGER_NAME" id="LEDGER_NAME" type="text" label="帐套名称" maxlength="50" value="${rst.LEDGER_NAME}">
											</td>
										</tr>
										
										<tr>
											<td width="15%" align="right" class="detail_label">
												备注：
											</td>
											<td width="35%" class="detail_content">
												<input json="BIK" name="BIK" id="BIK" type="text" label="备注"  maxlength="50" value="${rst.BIK }">
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
	</body>
	<script language="javascript">
		$(function(){
			SelDictShow("RYJB","96","${rst.RYJB }","");
			SelDictShow("ZW","102","${rst.ZW }","");
			SelDictShow("RYLB","113","${rst.RYLB }","");
		});
	</script>
</html>

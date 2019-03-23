<!-- 
 *@module 系统管理
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
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
		<script type="text/javascript" src="${ctx}/pages/base/shippoint/ShipPoint_Edit.js"></script>
		<title>生产基地编辑</title>
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
					<form name="form" id="mainForm">
						<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
							<tr>
								<td class="detail2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="detail3">
										<tr>
											<td width="15%" align="right" class="detail_label">
												生产基地编号：
											</td>
											<td width="35%" class="detail_content">
												<c:if test="${empty rst.SHIP_POINT_NO}">
													<input id="SHIP_POINT_NO" json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="text" autocheck="true" label="生产基地编号" valueType="chinese:false" inputtype="string" mustinput="true" maxlength="30" value="${rst.SHIP_POINT_NO}">
												</c:if>
												<c:if test="${not empty rst.SHIP_POINT_NO}">
													<input json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="text" autocheck="true" label="生产基地编号" inputtype="string" mustinput="true" maxlength="30" value="${rst.SHIP_POINT_NO}" readonly="readonly">
												</c:if>
											</td>
											<td width="15%" align="right" class="detail_label">
												生产基地名称：
											</td>
											<td width="35%" class="detail_content">
												<input id="SHIP_POINT_NAME" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" type="text" autocheck="true" label="生产基地名称" inputtype="string" mustinput="true" maxlength="50" value="${rst.SHIP_POINT_NAME}">
										</tr>									
										<tr>
											<td width="15%" align="right" class="detail_label">
												详细地址：
											</td>
											<td width="50%" class="detail_content" colspan="3"> 
											    <textarea json="ADDRESS" name="ADDRESS" label="详细地址" autocheck="true" mustinput="true" inputtype="string" maxlength="250" rows="4" cols="80%"><c:out value="${rst.ADDRESS}"></c:out></textarea>
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
</html>

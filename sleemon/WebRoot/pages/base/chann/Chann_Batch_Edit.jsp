
<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author zcx
 */
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/pages/base/chann/Chann_Batch_Edit.js"></script>
	</head>
	<body class="bodycss1">
		<div style="height: 100">
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<!--占位用行，以免显示数据被浮动层挡住-->
					<td height="20px">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<form name="form" id="mainForm">
						  <input type="hidden" name="selectArea" value=" RYZT='启用' and DELFLAG=0 and JGXXID='${JGXXID}' ">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label">
													区域经理名称：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<input type="hidden" id="AREA_MANAGE_ID"   name="AREA_MANAGE_ID"   inputtype="string" autocheck="true" label="区域经理ID" json="AREA_MANAGE_ID"     value="${rst.AREA_MANAGE_ID}" />
													<input type="hidden" id="AREA_MANAGE_TEL"  name="AREA_MANAGE_TEL"  inputtype="string" autocheck="true" label="区域经理电话"  json="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}" />
													<input type="text"   id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" autocheck="true" label="区域经理名称"  inputtype="string" mustinput="true" label="区域经理名称"  json="AREA_MANAGE_NAME" value="${rst.AREA_MANAGE_NAME}" />
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_0', false, 'AREA_MANAGE_ID', 'RYXXID', 'forms[0]','AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL', 'RYXXID,XM,SJ', 'selectArea')">
											</tr>
											<tr>
												<td colspan="10" align="center" valign="middle" >
													<input id="update"   type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O"/>&nbsp;&nbsp;
													<input id="q_close"  type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X" onclick="window.close()" />&nbsp;&nbsp;
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

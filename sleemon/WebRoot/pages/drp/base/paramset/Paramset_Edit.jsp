<!--  
/**
  *@module 系统管理
  *@fuc 渠道参数设置
  *@version 1.0
  *@author 
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript"
		src="${ctx}/pages/common/select/selCommJs.js">
</script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript
		src="${ctx}/pages/drp/base/paramset/Paramset_Edit.js"></script>
	<title>新增或修改</title>
</head>
<body>
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)"
						title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B"
						accesskey="B" onclick="history.back();">
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
	<form method="POST" action="#" id="mainForm">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<!-- 
							<td width="15%" align="right" class="detail_label">
								分销数据字典ID：
							</td>
							<td width="35%" class="detail_content">
								<input json="DATA_CONF_ID" name="DATA_CONF_ID" id="DATA_CONF_ID"
									type="text" autocheck="true" valueType="chinese:false"
									label="分销数据字典ID" inputtype="string" mustinput="true" maxlength="30"
									value="${rst.DATA_CONF_ID}"/>
							</td> --> 
							<td width="15%" align="right" class="detail_label">
								参数名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="DATA_CONF_ID" name="DATA_CONF_ID" id="DATA_CONF_ID"
									type="hidden" label="参数名称" value="${rst.DATA_NAME}">
							    <input json="MAX_FREEZE_DAYS" name="MAX_FREEZE_DAYS" id="MAX_FREEZE_DAYS"
							        type="hidden" value="${freezeDays}"/>
								<input json="DATA_NAME" name="DATA_NAME" type="text"
									seltarget="selLL" autocheck="true" label="参数名称"
									inputtype="string" maxlength="50" value="${rst.DATA_NAME}" mustinput="true">
							</td>
							<td width="15%" align="right" class="detail_label">
								参数类型：
							</td>
							<td width="35%" class="detail_content">
								<input json="DATA_CONF_ID" name="DATA_CONF_ID" id="DATA_CONF_ID"
									type="hidden" label="分销数据字典ID" value="${rst.DATA_CONF_ID}">
								<input json="DATA_TYPE" name="DATA_TYPE" type="text"
									seltarget="selLL" autocheck="true" label="参数类型" READONLY
									inputtype="string" maxlength="30" value="${rst.DATA_TYPE}" mustinput="true">
							</td>
							</tr>
							<tr>
							<td width="15%" align="right" class="detail_label">
								参数值：
							</td>
							<td width="35%" class="detail_content">
								<input json="DATA_VAL" name="DATA_VAL" id="DATA_VAL" type="text"
									autocheck="true" valueType="chinese:false" label="参数值"
									inputtype="string" maxlength="30" value="${rst.DATA_VAL}" mustinput="true">
							</td>
								<td width="15%" align="right" class="detail_label">
									帐套编号：
								</td>
								<td width="35%" class="detail_content">
									<input json="LEDGER_ID" name="LEDGER_ID" id="LEDGER_ID"
										type="text" autocheck="true" valueType="chinese:false"
										label="帐套编号" inputtype="string" maxlength="30"
										value="${LEDGER_ID}" READONLY>
								</td>
							</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>




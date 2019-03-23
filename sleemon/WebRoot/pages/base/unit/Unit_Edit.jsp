<!--  
/**
  *@module 系统管理
  *@fuc 单位维护编辑
  *@version 1.0
  *@author 王栋斌
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript"
		src="${ctx}/pages/common/select/selCommJs.js">
</script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript src="${ctx}/pages/base/unit/Unit_Edit.js">
</script>
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
							<td width="15%" align="right" class="detail_label">
								单位编号：
							</td>
							<td width="35%" class="detail_content">
								<input json="MEAS_UNIT_NO" name="MEAS_UNIT_NO" id="MEAS_UNIT_NO"
									type="text" autocheck="true" valueType="chinese:false"
									label="单位ID" inputtype="string" mustinput="true" maxlength="30"
									value="${rst.MEAS_UNIT_NO}"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								单位名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="MEAS_UNIT_NAME" name="MEAS_UNIT_NAME" type="text"
									seltarget="selLL" autocheck="true" label="单位名称"
									inputtype="string" mustinput="true" maxlength="50"
									value="${rst.MEAS_UNIT_NAME}">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								单位英文名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="UNIT_NAME_EN" name="UNIT_NAME_EN" id="UNIT_NAME_EN"
									type="text" autocheck="true" valueType="chinese:false"
									label="单位英文名称" inputtype="string" maxlength="30"
									value="${rst.UNIT_NAME_EN}">
							</td>
							<td width="15%" align="right" class="detail_label">
								单位类型：
							</td>
							<td width="35%" class="detail_content">
								<select name="UNIT_TYPE" id="UNIT_TYPE" style="width: 155"
								json="UNIT_TYPE" valueType="chinese:false"
									label="单位类型" autocheck="true" inputtype="string" mustinput="true">
								</select>
							</td>
							<td width="15%" align="right" class="detail_label">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" id="MEAS_UNIT_ID" name="MEAS_UNIT_ID" value="${rst.MEAS_UNIT_ID}">
	</form>
</body>
<script type=text/javascript>
SelDictShow("UNIT_TYPE", "BS_2", "${rst.UNIT_TYPE}", "");
</script>




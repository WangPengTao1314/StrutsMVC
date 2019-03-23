<!--  
/**
  *@module 系统管理
  *@fuc 路线信息编辑
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
	<script type=text/javascript
		src="${ctx}/pages/base/haulway/Haulway_Edit.js">
</script>
	<title>新增或修改</title>
</head>
<body class="bodycss1" >
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)"
						title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B"
						accesskey="B" onclick='parent.$("#label_1").click();'>
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
								路线编号：
							</td>
							<td width="35%" class="detail_content">
								<c:if test="${empty rst.HAULWAY_NO}">
									<input json="HAULWAY_NO" name="HAULWAY_NO" id="HAULWAY_NO"
										type="text" autocheck="true" valueType="chinese:false"
										label="路线编号" inputtype="string" mustinput="true"
										maxlength="30" value="${rst.HAULWAY_NO}" />
								</c:if>
								<c:if test="${not empty rst.HAULWAY_NO}">
									<input json="HAULWAY_NO" name="HAULWAY_NO" id="HAULWAY_NO"
										type="text" autocheck="true" valueType="chinese:false"
										label="路线编号" inputtype="string" mustinput="true"
										maxlength="30" value="${rst.HAULWAY_NO}" readonly="readonly" />
								</c:if>

							</td>
							<td width="15%" align="right" class="detail_label">
								路线名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="HAULWAY_NAME" name="HAULWAY_NAME" type="text"
									id="HAULWAY_NAME" seltarget="selLL" autocheck="true"
									label="路线名称" inputtype="string" mustinput="true" maxlength="100"
									value="${rst.HAULWAY_NAME}">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								发出城市：
							</td>
							<td width="35%" class="detail_content">
								<input json="DELV_CITY" name="DELV_CITY" id="DELV_CITY"
									type="text" autocheck="true" label="发出城市" inputtype="string"
									maxlength="50" mustinput="true" value="${rst.DELV_CITY}">
							</td>
							<td width="15%" align="right" class="detail_label">
								到达城市：
							</td>
							<td width="35%" class="detail_content">
								<input json="ARRV_CITY" name="ARRV_CITY" id="ARRV_CITY"
									type="text" autocheck="true" label="到达城市" inputtype="string"
									maxlength="50" mustinput="true" value="${rst.ARRV_CITY}">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								发货点编号：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="selectParams" value="STATE = '启用'">
								<!-- 发货点ID -->
								<input type="hidden" id="SHIP_POIT_ID" name="SHIP_POIT_ID"
									json="SHIP_POIT_ID" value="${rst.SHIP_POIT_ID}">
								<input json="SHIP_POIT_NO" name="SHIP_POIT_NO" id="SHIP_POIT_NO"
									type="text" autocheck="true" valueType="chinese:false"
									label="发货点编号" inputtype="string" maxlength="30"
									value="${rst.SHIP_POIT_NO}" readonly="readonly"
									mustinput="true">
								<img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_22', false, 'SHIP_POIT_ID', 'SHIP_POINT_ID', 'forms[0]','SHIP_POIT_NO,SHIP_POIT_NAME', 'SHIP_POINT_NO,SHIP_POINT_NAME', 'selectParams')">
							</td>
							<td width="15%" align="right" class="detail_label">
								发货点名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="SHIP_POIT_NAME" name="SHIP_POIT_NAME"
									id="SHIP_POIT_NAME" type="text" autocheck="true" label="发货点名称"
									inputtype="string" maxlength="50" value="${rst.SHIP_POIT_NAME}"
									readonly="readonly">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
								<!-- 渠道ID -->
								<input type="hidden" id="CHANN_ID" name="CHANN_ID"
									json="CHANN_ID" value="${rst.CHANN_ID}">
								<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" type="text"
									autocheck="true" valueType="chinese:false" label="渠道编号"
									inputtype="string" maxlength="30" value="${rst.CHANN_NO}"
									readonly="readonly" mustinput="true">
								<img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selectParams')">
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME"
									type="text" autocheck="true" label="渠道名称" inputtype="string"
									maxlength="100" value="${rst.CHANN_NAME}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								全程：
							</td>
							<td width="35%" class="detail_content">
								<input json="LENGTH" name="LENGTH" id="LENGTH" type="text"
									autocheck="true"  label="全程" inputtype="float" 
									valueType="8,2" maxlength="10" value="${rst.LENGTH}">
							</td>
							<td width="15%" align="right" class="detail_label">
								天数：
							</td>
							<td width="35%" class="detail_content">
								<input json="DAYS" name="DAYS" id="DAYS" type="text"
									autocheck="true" label="天数" inputtype="float" 
									valueType="8,2" maxlength="10" value="${rst.DAYS}">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
						      <textarea json="REMARK" name="REMARK" inputtype="string" maxlength="250" rows="4" cols="80%">${rst.REMARK}</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" id="HAULWAY_ID" name="HAULWAY_ID"
			value="${rst.HAULWAY_ID}">
	</form>
</body>




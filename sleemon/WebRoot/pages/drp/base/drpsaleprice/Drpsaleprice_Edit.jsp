
<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author 刘曰刚
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
		<script type="text/javascript" src="${ctx}/pages/drp/base/drpsaleprice/Drpsaleprice_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>货品零售价设置</title>
	</head>
	<body class="bodycss1">
		<div style="height: 100">
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
							<input type="hidden" name=selectParams id="selectParams" value="STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${LEDGER_ID}'))   and IS_NO_STAND_FLAG=0">
							<input type="hidden" id="RETAL_PRICE_ID" value="${rst.RETAL_PRICE_ID}"/>
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													货品编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="PRD_ID" json="PRD_ID" name="PRD_ID" value="${rst.PRD_ID}" type="hidden"/>
													<input id="PRD_NO" json="PRD_NO" name="PRD_NO"
														type="text" autocheck="true" label="货品编号"
														inputtype="string" mustinput="true"
														valueType="chinese:false" maxlength="30"
														value="${rst.PRD_NO}" readonly
														>
												</td>
												<td width="15%" align="right" class="detail_label">
													货品名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="PRD_NAME" json="PRD_NAME" name="PRD_NAME" readonly
														type="text" autocheck="true" label="货品名称"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.PRD_NAME}"  >
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													规格型号：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_SPEC" name="PRD_SPEC" type="text" readonly
														autocheck="true" label="规格型号" inputtype="string"
														maxlength="100" value="${rst.PRD_SPEC }">
												</td>
												<td width="15%" align="right" class="detail_label">
													品牌：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" id="BRAND" name="BRAND" inputtype="string" autocheck="true" label="品牌"  json="BRAND"  value="${rst.BRAND}"   readonly/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													零售价：
												</td>
												<td width="35%" class="detail_content">
													<input json="FACT_PRICE" name="FACT_PRICE" type="text"
														autocheck="true" label="零售价"  mustinput="true" inputtype="float"     valueType="8,2" 
														value="${rst.FACT_PRICE }">
												</td>
												<td width="15%" align="right" class="detail_label">
												</td>
												<td width="35%" class="detail_content">
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
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script language="JavaScript">
	</script>
</html>

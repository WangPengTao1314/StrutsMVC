<!--  
/**
 * @module 系统管理
 * @func 物流供应商
 * @version 1.0
 * @author 王栋斌
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
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"
			src="${ctx}/pages/common/select/selCommJs.js">
</script>
		<script type="text/javascript"
			src="${ctx}/pages/base/logicprvd/Logicprvd_Edit.js">
</script>
		<title>物流供应商编辑</title>
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
							<input type="hidden" name="selectContion" value="STATE='启用'">
							<input type="hidden" name="ryxxSelectContion" value='DELFLAG = 0'>
							<input type="hidden" name="selectContion1"
								value="DELFLAG = 0 and JGZT = '启用'">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													供应商编号：
												</td>
												<td width="35%" class="detail_content">
													<c:if test="${empty rst.PRVD_NO}">
														<input id="PRVD_NO" json="PRVD_NO" name="PRVD_NO"
															mustinput="true" type="text" autocheck="true" label="供应商编号"
															inputtype="string" maxlength="30" value="${rst.PRVD_NO}">
													</c:if>
													<c:if test="${not empty rst.PRVD_NO}">
														<input id="PRVD_NO" json="PRVD_NO" name="PRVD_NO"
															type="text" autocheck="true" label="供应商编号" readonly
															inputtype="string" maxlength="30" value="${rst.PRVD_NO}">
													</c:if>
												</td>
												<td width="15%" align="right" class="detail_label">
													供应商名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="PRVD_NAME" json="PRVD_NAME" name="PRVD_NAME"
														type="text" autocheck="true" label="供应商名称"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.PRVD_NAME}">
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													供应商类别：
												</td>
												<td width="35%" class="detail_content">
													<select json="PRVD_TYPE" id="PRVD_TYPE" name="PRVD_TYPE" style="width: 155" inputtype="string" autocheck="true" mustinput="true" label="供应商类别">
													</select>
												</td>
												<td width="15%" align="right" class="detail_label">
													供应商级别：
												</td>
												<td width="35%" class="detail_content">
													<select json="PRVD_LVL" id="PRVD_LVL" name="PRVD_LVL" style="width: 155" inputtype="string" autocheck="true" mustinput="true" label="供应商级别">
													</select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													供应商性质：
												</td>
												<td width="35%" class="detail_content">
													<select json="PRVD_NATRUE" id="PRVD_NATRUE" autocheck="true" mustinput="true" inputtype="string" name="PRVD_NATRUE" style="width: 155" label="供应商性质">
													</select>
												</td>
												<td width="15%" align="right" class="detail_label">
													国家：
												</td>
												<td width="35%" class="detail_content">
													<input name="NATION" json="NATION" type="text" inputtype="string"
														seltarget="selDZXX" autocheck="true" maxlength="32"
														value="${rst.NATION}" readonly>
													<input json="ZONE_ID" name="ZONE_ID" type="hidden"
														seltarget="selDZXX" autocheck="true" label="国家"
														inputtype="string" maxlength="50" value="${rst.PRVD_ID}">
													<img align="absmiddle" name="selDZXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_20', false, 'ZONE_ID', 'ZONE_ID', 'forms[0]','NATION,PROV,CITY,COUNTY', 'NATION,PROV,CITY,COUNTY', '')">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													省份：
												</td>
												<td width="35%" class="detail_content">
													<input json="PROV" name="PROV" type="text" autocheck="true"
														label="省份" inputtype="string" maxlength="50"
														value="${rst.PROV}" readonly>
												</td>
												<td width="15%" align="right" class="detail_label">
													城市：
												</td>
												<td width="35%" class="detail_content">
													<input json="CITY" name="CITY" type="text" autocheck="true"
														label="城市" inputtype="string" maxlength="50"
														value="${rst.CITY }" readonly>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													区县：
												</td>
												<td width="35%" class="detail_content">
													<input json="COUNTY" name="COUNTY" type="text"
														autocheck="true" label="区县" inputtype="string"
														maxlength="50" value="${rst.COUNTY }" readonly>
												</td>
											<!--   	<td width="15%" align="right" class="detail_label">												供应周期：
												</td>
												<td width="35%" class="detail_content">
													<select json="PRVD_CY" id="PRVD_CY" name="PRVD_CY"
														style="width: 155"></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													供应能力：
												</td>
												<td width="35%" class="detail_content">
													<select json="PRVD_CAP" id="PRVD_CAP" name="PRVD_CAP"
														style="width: 155"></select>
												</td>-->
												<td width="15%" align="right" class="detail_label">
													业务员：
												</td>
												<td width="35%" class="detail_content">
													<input json="PERSON_BUSS" name="PERSON_BUSS" id="PERSON_BUSS" type="text"
														autocheck="true" label="业务员" inputtype="string"
														maxlength="30" value="${rst.PERSON_BUSS}" mustinput="true">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													联系人：
												</td>
												<td width="35%" class="detail_content">
													<input json="PERSON_CON" name="PERSON_CON" id="PERSON_CON" type="text"
														autocheck="true" label="联系人" inputtype="string"
														maxlength="30" value="${rst.PERSON_CON }" mustinput="true">
												</td>
												<td width="15%" align="right" class="detail_label">
													电话：
												</td>
												<td width="35%" class="detail_content">
													<input json="TEL" name="TEL" id="TEL" type="text" autocheck="true"
														label="电话" inputtype="string" maxlength="30"
														value="${rst.TEL }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													手机：
												</td>
												<td width="35%" class="detail_content">
													<input json="MOBILE" name="MOBILE" id="MOBILE"  type="text"
														autocheck="true" label="手机" inputtype="string"
														maxlength="30" value="${rst.MOBILE}">
												</td>
												<td width="15%" align="right" class="detail_label">
													传真：
												</td>
												<td width="35%" class="detail_content">
													<input json="TAX" name="TAX" id="TAX" type="text" autocheck="true"
														label="传真" inputtype="string" maxlength="30"
														value="${rst.TAX }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													邮编：
												</td>
												<td width="35%" class="detail_content">
													<input json="POST" name="POST" id="POST" type="text" autocheck="true"
														label="邮编" inputtype="string" maxlength="30"
														value="${rst.POST }">
												</td>
												<td width="15%" align="right" class="detail_label">
													Email：
												</td>
												<td width="35%" class="detail_content">
													<input json="EMAIL" name="EMAIL" id="EMAIL" type="text"
														autocheck="true" label="Email" inputtype="string"
														maxlength="30" value="${rst.EMAIL }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													Web：
												</td>
												<td width="35%" class="detail_content">
													<input json="WEB" name="WEB" type="text" autocheck="true"
														label="Web" inputtype="string" maxlength="30"
														value="${rst.WEB }">
												</td>
												<td width="15%" align="right" class="detail_label">
													法人代表：
												</td>
												<td width="35%" class="detail_content">
													<input json="LEGAL_REPRE" name="LEGAL_REPRE" type="text"
														autocheck="true" label="法人代表" inputtype="string"
														maxlength="30" value="${rst.LEGAL_REPRE }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													营业执照号：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_LIC" name="BUSS_LIC" type="text"
														autocheck="true" label="营业执照号" inputtype="string"
														maxlength="30" value="${rst.BUSS_LIC }">
												</td>
												<td width="15%" align="right" class="detail_label">
													发票抬头：
												</td>
												<td width="35%" class="detail_content">
													<input json="INVOICE_TI" name="INVOICE_TI" type="text"
														autocheck="true" label="发票抬头" inputtype="string"
														maxlength="100" value="${rst.INVOICE_TI }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													发票地址：
												</td>
												<td width="35%" class="detail_content">
													<input json="INVOICE_ADDR" name="INVOICE_ADDR" type="text"
														autocheck="true" label="发票地址" inputtype="string"
														maxlength="100" value="${rst.INVOICE_ADDR }">
												</td>
												<td width="15%" align="right" class="detail_label">
													开户行：
												</td>
												<td width="35%" class="detail_content">
													<input json="BANK_NAME" name="BANK_NAME" type="text"
														autocheck="true" label="开户行" inputtype="string"
														maxlength="50" value="${rst.BANK_NAME}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													银行账号：
												</td>
												<td width="35%" class="detail_content">
													<input json="BANK_ACCT" name="BANK_ACCT" type="text"
														autocheck="true" label="银行账号" inputtype="string"
														maxlength="30" value="${rst.BANK_ACCT }">
												</td>
												<td width="15%" align="right" class="detail_label">
													财务对照码：
												</td>
												<td width="35%" class="detail_content">
													<input json="FI_CMP_NO" name="FI_CMP_NO" type="text"
														autocheck="true" label="财务对照码" inputtype="string"
														maxlength="30" value="${rst.FI_CMP_NO }">
												</td>
											</tr>

											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													详细地址：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<textarea json="ADDRESS" name="ADDRESS" inputtype="string"
														maxlength="250" rows="3" cols="80%">${rst.ADDRESS }</textarea>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													备注：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<textarea json="REMARK" name="REMARK" inputtype="string"
														maxlength="250" rows="3" cols="80%">${rst.REMARK }</textarea>
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
	<script language="JavaScript">
	SelDictShow("PRVD_TYPE", "173", '${rst.PRVD_TYPE}', "");
	SelDictShow("PRVD_LVL", "181", '${rst.PRVD_LVL}', "");
	SelDictShow("PRVD_NATRUE", "182", '${rst.PRVD_NATRUE}', "");
	SelDictShow("PRVD_CY", "183", '${rst.PRVD_CY}', "");
	SelDictShow("PRVD_CAP", "184", '${rst.PRVD_CAP}', "");
	</script>
</html>


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
		<script type="text/javascript" src="${ctx}/pages/base/chann/Chann_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>渠道信息编辑</title>
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
							<input type="hidden" name="selectParams"  value=" STATE in( '启用') and DEL_FLAG='0' ">
							<input type="hidden" name="selectParamsT" value=" STATE in( '启用') and DEL_FLAG='0' AND AREA_NO_P IS NOT NULL ">
							<input type="hidden" name="selectParam" value=" STATE in( '启用') and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
							<!--  //-- 0156117--Start--刘曰刚--2014-06-16// -->
							<input type="hidden" name="selectArea" value=" RYZT='启用' and DELFLAG=0 and JGXXID='${JGXXID}' ">
							<!-- //-- 0156117--End--刘曰刚--2014-06-16// -->
							<input name="STATE" type="hidden" value="制定" />
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_NO" json="CHANN_NO" name="CHANN_NO"
														type="text" autocheck="true" label="渠道编号"
														inputtype="string" mustinput="true"
														valueType="chinese:false" maxlength="30"
														value="${rst.CHANN_NO}"
														<c:if test="${rst.CHANN_NO!=NULL}">READONLY</c:if>>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME"
														type="text" autocheck="true" label="渠道名称"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.CHANN_NAME}"  <c:if test="${rst.CHANN_NO!=NULL}">READONLY</c:if>>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道简称：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_ABBR" name="CHANN_ABBR" type="text"
														autocheck="true" label="渠道简称" inputtype="string"
														maxlength="100" value="${rst.CHANN_ABBR }">
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道类型：
												</td>
												<td width="35%" class="detail_content">
													<select label="渠道类型" name="CHANN_TYPE"
														valueType="chinese:false" inputtype="string"
														style="width: 153" id="CHANN_TYPE" json="CHANN_TYPE" 
														value="${rst.CHANN_TYPE}" mustinput="true">
													</select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													渠道级别：
												</td>
												<td width="35%" class="detail_content">
													<select label="渠道级别" name="CHAA_LVL"
														inputtype="string"  autocheck="true"
														style="width: 153" id="CHAA_LVL" json="CHAA_LVL"
														value="${rst.CHAA_LVL}" mustinput="true">
													</select>
												</td>
												<td width="15%" align="right" class="detail_label">
													加盟日期：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" json="JOIN_DATE" id="JOIN_DATE" name="JOIN_DATE" autocheck="true" inputtype="string" label="加盟日期"  value="${rst.JOIN_DATE}"  mustinput="true"   onclick="SelectDate();" readonly />
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(JOIN_DATE);">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													是否控制终端销售折扣：
												</td>
												<td width="35%" class="detail_content">
													<input type="checkbox" onclick="upFlag();" id="flag"
														<c:if test="${rst.TERM_DECT_CTR_FLAG==1}"> checked</c:if> 
													>
													<input type="hidden"  id="TERM_DECT_CTR_FLAG" json="TERM_DECT_CTR_FLAG"
														name="TERM_DECT_CTR_FLAG"  value="${rst.TERM_DECT_CTR_FLAG}">
												</td>
												<td width="15%" align="right" class="detail_label">
												渠道销售级别:
												</td>
												<td width="35%" class="detail_content">
												 
												<select  id="CHAA_SALE_LVL" json="CHAA_SALE_LVL" label="渠道销售级别" style="width: 155px;"
														name="CHAA_SALE_LVL" value="${rst.CHAA_SALE_LVL}" ></select>
												</td>
											</tr>
											<!-- //-- 0156117--Start--刘曰刚--2014-06-16// -->
											<!-- 渠道信息新增区域经理 信息-->
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													区域经理名称：
												</td>
												<td width="35%" class="detail_content">
													<input type="hidden" id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" inputtype="string" autocheck="true" label="区域经理ID" json="AREA_MANAGE_ID"  value="${rst.AREA_MANAGE_ID}"   readonly/>
													<input type="text" id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" inputtype="string" autocheck="true" label="区域经理名称" mustinput="true"  json="AREA_MANAGE_NAME"  value="${rst.AREA_MANAGE_NAME}"   readonly/>
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_0', false, 'AREA_MANAGE_ID', 'RYXXID', 'forms[0]','AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL', 'RYXXID,XM,SJ', 'selectArea')">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													区域经理电话：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" id="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" inputtype="string" autocheck="true" label="区域经理电话" mustinput="true"  json="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}"  readonly/>
												</td>
											</tr>
											<!-- //-- 0156117--End--刘曰刚--2014-06-16// -->
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													区域服务中心编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_SER_ID" json="AREA_SER_ID" name="AREA_SER_ID"
														type="hidden" inputtype="string" autocheck="true" 
														value="${rst.AREA_SER_ID}">

													<input id="AREA_SER_NO" json="AREA_SER_NO" name="AREA_SER_NO"
														type="text" inputtype="string" autocheck="true" label="区域服务中心编号"
														value="${rst.AREA_SER_NO}"  READONLY>

													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[0]','AREA_SER_ID,AREA_SER_NO,AREA_SER_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParam')">
												</td>
												<td width="15%" align="right" class="detail_label">
													区域服务中心名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_SER_NAME" name="AREA_SER_NAME" type="text"
														inputtype="string" label="区域服务中心名称" json="AREA_SER_NAME" autocheck="true"
														value="${rst.AREA_SER_NAME }"  READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													区域编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_ID" json="AREA_ID" name="AREA_ID"
														type="hidden" inputtype="string"
														value="${rst.AREA_ID}">
														
													<input id="AREA_NO" json="AREA_NO" name="AREA_NO" type="text" mustinput="true" 
													 inputtype="string" value="${rst.AREA_NO}" label="区域编号" autocheck="true"
													 READONLY >
													 
													<img align="absmiddle" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParamsT')">
												</td>
												<td width="15%" align="right" class="detail_label">
													区域名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_NAME" name="AREA_NAME" type="text" mustinput="true"  autocheck="true" 
														inputtype="string" label="区域名称" json="AREA_NAME" 
														value="${rst.AREA_NAME }" READONLY>
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													国家：
												</td>
												<td width="35%" class="detail_content">
													<input id="ZONE_ID" json="ZONE_ID" name="ZONE_ID"
														type="hidden" inputtype="string"
														value="${rst.ZONE_ID}">

													<input id="NATION" json="NATION" name="NATION" type="text" mustinput="true"
														inputtype="string"  value="${rst.NATION}" label="国家"  autocheck="true"
														READONLY>

													<img align="absmiddle" name="selZONE" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif" 
														onClick="selCommon('BS_20', false, 'ZONE_ID','ZONE_ID', 'forms[0]','ZONE_ID,NATION,PROV,CITY,COUNTY', 'ZONE_ID,NATION,PROV,CITY,COUNTY', 'selectParams')">
												</td>
												<td width="15%" align="right" class="detail_label">
													省份：
												</td>
												<td width="35%" class="detail_content">
													<input id="PROV" json="PROV" name="PROV" type="text" autocheck="true"
														mustinput="true" label="省份" inputtype="string"
														value="${rst.PROV }" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													城市：
												</td>
												<td width="35%" class="detail_content">
													<input id="CITY" json="CITY" name="CITY" type="text"   autocheck="true"
														mustinput="true" label="城市" inputtype="string"
														value="${rst.CITY }" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													区县：
												</td>
												<td width="35%" class="detail_content">
													<input id="COUNTY" json="COUNTY" name="COUNTY" type="text" 
														 mustinput="true" label="区县"  autocheck="true"
														inputtype="string" value="${rst.COUNTY }" READONLY>
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label">
													城市类型：
												</td>
												<td width="35%" class="detail_content">
													<select label="城市类型" name="CITY_TYPE"
														inputtype="string"  autocheck="true"
														style="width: 153" id="CITY_TYPE" json="CITY_TYPE"
														value="${rst.CITY_TYPE}" mustinput="true">
													</select>
												</td>
												<td width="15%" align="right" class="detail_label">
													生产基地编号:
												</td>
												<td width="35%" class="detail_content">
													<input id="SHIP_POINT_NO" autocheck="true" json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="text" label="生产基地编号" value="${rst.SHIP_POINT_NO}" inputtype="string" mustinput="true" READONLY>
													<img align="absmiddle" name="selZONE" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif" 
														onClick="selCommon('BS_22', false, 'SHIP_POINT_NO','SHIP_POINT_NO', 'forms[0]','SHIP_POINT_NO,SHIP_POINT_NAME', 'SHIP_POINT_NO,SHIP_POINT_NAME', 'selectParams')">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													生产基地名称:
												</td>
												<td width="35%" class="detail_content">
													<input id="SHIP_POINT_NAME"  autocheck="true" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" type="text" label="生产基地名称" value="${rst.SHIP_POINT_NAME}" inputtype="string" mustinput="true" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
												</td>
												<td width="35%" class="detail_content">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													税率:
												</td>
												<td width="35%" class="detail_content">
													<input id="TAX_RATE" autocheck="true" json="TAX_RATE" name="TAX_RATE" type="text" label="税率" valueType="2,2"  value="${rst.TAX_RATE}" inputtype="float" autocheck="true"   >
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													成本计算方式：
												</td>
												<td width="35%" class="detail_content">
													<select id="COST_TYPE" style="width: 153" autocheck="true" json="COST_TYPE" name="COST_TYPE"  label="成本计算方式"      ></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													初始化年份
												</td>
												<td width="35%" class="detail_content">
													<select id="INIT_YEAR" name="INIT_YEAR" style="width: 153" json="INIT_YEAR" inputtype="string"  label="初始化年份" autocheck="true" ></select>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													初始化月份
												</td>
												<td width="35%" class="detail_content">
													<select id="INIT_MONTH" name="INIT_MONTH" style="width: 153" inputtype="string" json="INIT_MONTH"  label="初始化月份" autocheck="true"></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													联系人：
												</td>
												<td width="35%" class="detail_content">
													<input json="PERSON_CON" name="PERSON_CON" type="text" mustinput="true"
														autocheck="true" label="联系人" inputtype="String"
														maxlength="30" value="${rst.PERSON_CON }">
												</td>
												<td width="15%" align="right" class="detail_label">
													电话：
												</td>
												<td width="35%" class="detail_content">
													<input name="TEL" type="text" json="TEL" id="TEL" label="电话"
														autocheck="true" inputtype="string" maxlength="30"
														value="${rst.TEL}">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													手机：
												</td>
												<td width="35%" class="detail_content">
													<input name="MOBILE" type="text" mustinput="true" autocheck="true" label="手机"
														id="MOBILE"  maxlength="30" json="MOBILE" inputtype="string"
														value="${rst.MOBILE}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													传真：
												</td>
												<td width="35%" class="detail_content">
													<input name="TAX" type="text" autocheck="true" label="传真"
														id="TAX" inputtype="string" maxlength="30" json="TAX"
														value="${rst.TAX}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													电子邮件：
												</td>
												<td width="35%" class="detail_content">
													<input id="EMAIL" json="EMAIL" name="EMAIL" type="text"
														autocheck="true" label="电子邮件" inputtype="string"
														maxlength="30" value="${rst.EMAIL }">
												</td>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													邮政编码：
												</td>
												<td width="35%" class="detail_content">
													<input name="POST" json="POST" type="text"
														inputtype="string" id="POST" autocheck="true" label="邮政编码"
														maxlength="30" value="${rst.POST }">
												</td>
											</tr>
											<tr>
												
												
												<td width="15%" align="right" class="detail_label" nowrap>
													网站：
												</td>
												<td width="35%" class="detail_content">
													<input json="WEB" name="WEB" id="WEB" type="text"
														inputtype="" autocheck="true" id="WEB" maxlength="30"
														mustinput="false" value="${rst.WEB }" label="网站">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
												</td>
												<td width="35%" class="detail_content">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													法人代表：
												</td>
												<td width="35%" class="detail_content">
													<input json="LEGAL_REPRE" name="LEGAL_REPRE" type="text"
														autocheck="true" label="法人代表" inputtype="string"
														maxlength="30" value="${rst.LEGAL_REPRE }">
												</td>
												<td width="15%" align="right" class="detail_label">
													营业执照号：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_LIC" name="BUSS_LIC" type="text"
														autocheck="true" label="营业执照号" inputtype="string"
														maxlength="30" value="${rst.BUSS_LIC }">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													税务登记号：
												</td>
												<td width="35%" class="detail_content">
													<input json="AX_BURDEN" name="AX_BURDEN" type="text"
														inputtype="string" autocheck="true" maxlength="30"
														value="${rst.AX_BURDEN }" label="税务登记号">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													组织机构代码证：
												</td>
												<td width="35%" class="detail_content">
													<input json="ORG_CODE_CERT" name="ORG_CODE_CERT"
														type="text" inputtype="string" autocheck="true"
														maxlength="30" value="${rst.ORG_CODE_CERT}"
														label="组织机构代码证">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													经营性质：
												</td>
												<td width="35%" class="detail_content">
													<select label="经营范围" name="BUSS_NATRUE"
														inputtype="string"  autocheck="true"
														style="width: 153" id="BUSS_NATRUE" json="BUSS_NATRUE"
														value="${rst.BUSS_NATRUE}">
													</select>
												</td>
												<td width="15%" align="right" class="detail_label">
													经营范围：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_SCOPE" name="BUSS_SCOPE" type="text"
														autocheck="true" label="经营范围" inputtype="string"
														maxlength="50" value="${rst.BUSS_SCOPE }">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													增值税号：
												</td>
												<td width="35%" class="detail_content">
													<input json="VAT_NO" name="VAT_NO" type="text"
														autocheck="true" label="增值税号" inputtype="string"
														maxlength="30" value="${rst.VAT_NO }">
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
												
												<td width="15%" align="right" class="detail_label" nowrap>
													发票地址：
												</td>
												<td width="35%" class="detail_content">
													<input json="INVOICE_ADDR" name="INVOICE_ADDR" type="text"
														autocheck="true" label="发票地址" inputtype="string"
														maxlength="100" value="${rst.INVOICE_ADDR }">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													开户银行：
												</td>
												<td width="35%" class="detail_content">
													<input json="BANK_NAME" name="BANK_NAME" type="text"
														autocheck="true" label="开户银行" inputtype="string"
														maxlength="50" value="${rst.BANK_NAME }">
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
												<td width="15%" align="right" class="detail_label" nowrap>
													财务对照码：
												</td>
												<td width="35%" class="detail_content">
													<input json="FI_CMP_NO" name="FI_CMP_NO" type="text"
														autocheck="true" label="财务对照码" inputtype="string"
														maxlength="30" value="${rst.FI_CMP_NO }">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label">
													保证金：
												</td>
												<td width="35%" class="detail_content">
													<input json="DEPOSIT" name="DEPOSIT" type="text"
														autocheck="true" label="保证金" inputtype="float" valueType="8,2"
														valueType="chinese:false" value="${rst.DEPOSIT }">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													欠款期限：
												</td>
												<td width="35%" class="detail_content">
													<input json="DEBT_DEF" name="DEBT_DEF" type="text" maxlength="9" 
														autocheck="true" label="欠款期限" inputtype="int"
														valueType="chinese:false" value="${rst.DEBT_DEF }">
												</td>
											</tr>
											<tr>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													营业执照附件：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_LIC_ATT" name="BUSS_LIC_ATT" type="text"
														id="BUSS_LIC_ATT" label="营业执照附件"
														inputtype="string"
														value="${rst.BUSS_LIC_ATT }">
												</td>
												<td width="15%" align="right" class="detail_label">
													税务登记附件：
												</td>
												<td width="35%" class="detail_content">
													<input json="TAX_BURDEN_ATT" name="TAX_BURDEN_ATT" id="TAX_BURDEN_ATT"
														type="text" 
														label="税务登记附件" inputtype="string"
														value="${rst.TAX_BURDEN_ATT }">
												</td>
											</tr>
											
											<tr>
												<td width="15%" align="right" class="detail_label">
													组织结构代码证附件：
												</td>
												<td width="35%" class="detail_content">
													<input json="ORG_CERT_ATT" name="ORG_CERT_ATT" id="ORG_CERT_ATT"
														type="text" id="ORG_CERT_ATT" 
														label="组织机构代码证附件" inputtype="string"
														value="${rst.ORG_CERT_ATT }">
												</td>
												<td width="15%" align="right" class="detail_label">
													付款比例：
												</td>
												<td width="35%" class="detail_content">
													<input id="PAY_RATE" json="PAY_RATE" name="PAY_RATE" type="text" 
													    label="付款比例"  autocheck="true"
														inputtype="float" valueType="8,2" value="${rst.PAY_RATE }" >
												</td>
											</tr>
											<tr>
											    <td width="15%" align="right" class="detail_label">
													是否允许修改入库数量：
												</td>
												<td width="35%" class="detail_content" colspan="3">
												    <input type="checkbox" onclick="upStoreInFlag();" id="storeInflag"
														<c:if test="${rst.IS_UPDATE_STOREIN_FLAG==1}"> checked</c:if> 
													>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
													 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
													<input type="button" value="批量修改" onclick="openWindow()"/> 
													<input type="hidden"  id="IS_UPDATE_STOREIN_FLAG" json="IS_UPDATE_STOREIN_FLAG"
														name="IS_UPDATE_STOREIN_FLAG"  value="${rst.IS_UPDATE_STOREIN_FLAG}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">价格条款：</td>
												<td width="35%" class="detail_content"  colspan="3">
												    <textarea json="PRICE_CLAUSE" name="PRICE_CLAUSE" inputtype="string" maxlength="250" rows="4" cols="80%"><c:out value="${rst.PRICE_CLAUSE}"></c:out></textarea>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													详细地址：
												</td>
												<td width="35%" class="detail_content"  colspan="3">
													<textarea json="ADDRESS" name="ADDRESS" id="ADDRESS" inputtype="string" maxlength="250" rows="4" cols="80%" mustinput="true"><c:out value="${rst.ADDRESS}"></c:out></textarea>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													备注：
												</td>
												<td width="35%" class="detail_content" colspan="3">
												    <textarea json="REMARK" name="REMARK" inputtype="string" maxlength="250" rows="4" cols="80%"><c:out value="${rst.REMARK}"></c:out></textarea>
												</td>
											</tr>
											<tr>
											
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
		SelDictShow("CHANN_TYPE", "171", '${rst.CHANN_TYPE}', "");
		SelDictShow("INIT_YEAR", "89", "${rst.INIT_YEAR}", "");
		SelDictShow("INIT_MONTH", "168", "${rst.INIT_MONTH}", "");
		//0156691--start--刘曰刚--2014-06-18//
		//修改渠道销售级别和渠道级别的下拉值
		SelDictShow("CHAA_LVL", "BS_100", '${rst.CHAA_LVL}', "");
		SelDictShow("CHAA_SALE_LVL", "169", '${rst.CHAA_SALE_LVL}', "");
		//0156691--End--刘曰刚--2014-06-18//
		SelDictShow("BUSS_NATRUE", "177", '${rst.BUSS_NATRUE}', "");
		// modify  by zzb 2014-6-17 
		SelDictShow("CITY_TYPE", "BS_101", '${rst.CITY_TYPE}', "");
		SelDictShow("COST_TYPE", "BS_131", '${rst.COST_TYPE}', "");
		
		uploadFile('TAX_BURDEN_ATT', 'SAMPLE_DIR', true);
		uploadFile('BUSS_LIC_ATT', 'SAMPLE_DIR', true);
		uploadFile('ORG_CERT_ATT', 'SAMPLE_DIR', true);
	</script>
</html>

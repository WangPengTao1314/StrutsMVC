
<!--  
/**
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.1
 * @author  gu_hongxiu
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
		<script type="text/javascript" src="${ctx}/pages/base/distributor/Distributor_Edit.js"></script>
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
							<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG='0' ">
							
							<input name="STATE" type="hidden" value="制定" />
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="DISTRIBUTOR_NO" json="DISTRIBUTOR_NO" name="DISTRIBUTOR_NO"
														type="text" autocheck="true" label="编号"
														inputtype="string" mustinput="true"	 maxlength="30"
														<c:if test="${empty rst.DISTRIBUTOR_NO}"> value="自动生成" </c:if>														
														<c:if test="${not empty rst.DISTRIBUTOR_NO}"> value="${rst.DISTRIBUTOR_NO}"</c:if>
														READONLY
													/>
												</td>
												<td width="15%" align="right" class="detail_label">
													名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="DISTRIBUTOR_NAME" json="DISTRIBUTOR_NAME" name="DISTRIBUTOR_NAME"
														type="text" autocheck="true" label="名称"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.DISTRIBUTOR_NAME}"  <c:if test="${rst.DISTRIBUTOR_NO!=NULL}">READONLY</c:if>>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													类型：
												</td>
												<td width="35%" class="detail_content">
													<select label="类型" name="DISTRIBUTOR_TYPE"
														valueType="chinese:false" inputtype="string"
														style="width: 153" id="DISTRIBUTOR_TYPE" json="DISTRIBUTOR_TYPE" name="DISTRIBUTOR_TYPE" 
														value="${rst.DISTRIBUTOR_TYPE}" mustinput="true">
													</select>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													联系电话：
												</td>
												<td width="35%" class="detail_content">
													<input json="TEL" name="TEL" type="text"
														autocheck="true" label="联系电话" inputtype="string"
														maxlength="30" value="${rst.TEL }">
												</td>
												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													所属加盟商编号：
												</td>
												<td width="35%" class="detail_content">
													<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}">
													<input type="hidden" id="AREA_ID" name="AREA_ID" json="AREA_ID"  value="${rst.AREA_ID}"/>
													<input type="hidden" id="AREA_NO" name="AREA_NO" json="AREA_NO"  value="${rst.AREA_NO}"/>
													<input type="hidden" id="AREA_NAME" name="AREA_NAME" json="AREA_NAME"  value="${rst.AREA_NAME}"/>
													<input type="hidden" id="ZONE_ID" name="ZONE_ID" json="ZONE_ID"  value="${rst.ZONE_ID}"/>
																					
													<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
														seltarget="selJGXX" autocheck="true" maxlength="32"
														value="${rst.CHANN_NO}" >
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="selCommon('BS_187', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
																'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,PROV,CITY,COUNTY,AREA_ID,AREA_NO,AREA_NAME,ZONE_ID', 
																'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,PROV,CITY,COUNTY,AREA_ID,AREA_NO,AREA_NAME,ZONE_ID', 
																'selectChann')">
												</td>
												<td width="15%" align="right" class="detail_label">
													所属加盟商名称：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" label="所属加盟商名称"  value="${rst.CHANN_NAME}" readonly />													
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													战区：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" json="AREA_NAME_P" id="AREA_NAME_P" name="AREA_NAME_P" label="战区"  value="${rst.AREA_NAME_P}" readonly />
												</td>
												<td width="15%" align="right" class="detail_label">
													营销经理:
												</td>
												<td width="35%" class="detail_content">
													<input type="hidden" id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" label="区域经理ID" json="AREA_MANAGE_ID"  value="${rst.AREA_MANAGE_ID}"/>
													<input type="hidden" id="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" label="区域经理电话" json="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}"/>
													<input type="text" id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" label="营销经理"  json="AREA_MANAGE_NAME"  value="${rst.AREA_MANAGE_NAME}"   readonly/>													
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													省份：
												</td>
												<td width="35%" class="detail_content">
													<input id="PROV" json="PROV" name="PROV" type="text" autocheck="true"
														label="省份" inputtype="string" value="${rst.PROV }" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													城市：
												</td>
												<td width="35%" class="detail_content">
													<input id="CITY" json="CITY" name="CITY" type="text"   autocheck="true"
														 inputtype="string"	value="${rst.CITY }" READONLY>
												</td>
												
											</tr>
											<tr>	
												<td width="15%" align="right" class="detail_label" nowrap>
													区县：
												</td>
												<td width="35%" class="detail_content">
													<input id="COUNTY" json="COUNTY" name="COUNTY" type="text"   autocheck="true"
														label="区县" inputtype="string"
														value="${rst.COUNTY }" READONLY>
												</td>											
												<td width="15%" align="right" class="detail_label">
													联系人：
												</td>
												<td width="35%" class="detail_content">
													<input json="PERSON_CON" name="PERSON_CON" type="text" 
														autocheck="true" label="联系人" inputtype="String"
														maxlength="30" value="${rst.PERSON_CON }">
												</td>
												
											</tr>											
											<tr>
												<td width="15%" align="right" class="detail_label">
													联系人电话：
												</td>
												<td width="35%" class="detail_content">
													<input name="MOBILE" type="text" json="MOBILE" id="MOBILE" label="联系人电话"
														autocheck="true" inputtype="string" maxlength="30"
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
												<td width="15%" align="right" class="detail_label" nowrap>
													邮箱：
												</td>
												<td width="35%" class="detail_content">
													<input id="EMAIL" json="EMAIL" name="EMAIL" type="text"
														autocheck="true" label="邮箱" inputtype="string"
														maxlength="30" value="${rst.EMAIL }">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													商场名称：
												</td>
												<td width="35%" class="detail_content">													
													<input id="SALE_STORE_NAME" json="SALE_STORE_NAME" name="SALE_STORE_NAME"
														type="text" inputtype="string" autocheck="true" maxlength="100"  label="商场名称"
														value="${rst.SALE_STORE_NAME}"  >													
												</td>												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													商场地址：
												</td>
												<td width="35%" class="detail_content">
													<input id="SALE_STORE_LOCAL" json="SALE_STORE_LOCAL" name="SALE_STORE_LOCAL"
														type="text" inputtype="string" autocheck="true" maxlength="100"  label="商场地址"
														value="${rst.SALE_STORE_LOCAL}"  >
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													经营品牌：
												</td>
												<td width="35%" class="detail_content">
													<input id="BUSS_BRAND" json="BUSS_BRAND" name="BUSS_BRAND"
														type="text" inputtype="string" autocheck="true" maxlength="100"  label="经营品牌"
														value="${rst.BUSS_BRAND}"  >
												</td>												
											</tr>
											
											<tr>
												<td width="15%" align="right" class="detail_label">
													主营品类：
												</td>
												<td width="35%" class="detail_content">
													<input id="BUSS_CLASS" name="BUSS_CLASS" type="text" autocheck="true" 
														inputtype="string" label="主营品类" json="BUSS_CLASS" 
														value="${rst.BUSS_CLASS }" >
												</td>
												<td width="15%" align="right" class="detail_label">
													合作时间：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" json="COOPER_DATE" id="COOPER_DATE" name="COOPER_DATE" autocheck="true" inputtype="string" label="合作时间"  value="${rst.COOPER_DATE}"  onclick="SelectDate();" readonly />
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(COOPER_DATE);">
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
		SelDictShow("DISTRIBUTOR_TYPE", "201", '${rst.DISTRIBUTOR_TYPE}', "");						
	</script>
</html>

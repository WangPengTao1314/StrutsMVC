<!-- 
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/decorationallo/Decorationallo_Edit.js"></script>
	<title>新增装修补贴标准维护</title>
</head>
<body class="bodycss1" onload="load()">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;装修管理&gt;装修补贴标准维护编辑</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" json="RNVTN_SUBST_STD_ID" id="RNVTN_SUBST_STD_ID" name="RNVTN_SUBST_STD_ID" value="${rst.RNVTN_SUBST_STD_ID}" />
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right" class="detail_label">装修补贴标准编号：</td>
					<td width="35%" class="detail_content">
						    <input json="RNVTN_SUBST_STD_ID" name="RNVTN_SUBST_STD_ID" size="40"  id="RNVTN_SUBST_STD_ID" type="text" autocheck="true" label="装修补贴标准编号" inputtype="string" mustinput="true"
						       readonly <c:if test="${not empty rst.RNVTN_SUBST_STD_NO}">value="${rst.RNVTN_SUBST_STD_NO}"</c:if>
									<c:if test="${empty rst.RNVTN_SUBST_STD_NO}">value="自动生成"</c:if>>
					</td>
					<td width="15%" align="right" class="detail_label">品牌：</td>
					<td width="35%" class="detail_content">
                              <select id="BRAND" name="BRAND" json="BRAND" value="${rst.BRAND}" style="width:275px;" autocheck="true" inputtype="string" mustinput="true"></select>				
                  	</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">最低面积(平米)：</td>
					<td width="35%" class="detail_content">
					   <input json="MIN_AREA" name="MIN_AREA" id="MIN_AREA" type="text"
									label="最低面积" value="${rst.MIN_AREA}"
									size="40" autocheck="true" inputtype="float" maxlength="11" valueType="8,2" mustinput="true" /> 
 					</td>
					<td width="15%" align="right" class="detail_label">标准面积(平米)：</td>
					<td width="35%" class="detail_content">
					   <input json="STD_AREA" name="STD_AREA" id="STD_AREA" type="text" value="${rst.STD_AREA}"
									label="标准面积" size="40" autocheck="true" inputtype="float" maxlength="11" valueType="8,2" />
 					</td>
				</tr>
				<tr>
							<td width="15%" align="right" class="detail_label">
								造价成本(元/平米)：
							</td>
							<td width="35%" class="detail_content">
								<input json="BUILD_COST" name="BUILD_COST" id="BUILD_COST" value="${rst.BUILD_COST}"
									 type="text" size="40" autocheck="true" inputtype="float" maxlength="11" valueType="8,2"
									  />
							</td>
							<td width="15%" align="right" class="detail_label">
								配饰费用(元/平米)：
							</td>
							<td width="35%" class="detail_content">
								<input id="DECORATE_COST" name="DECORATE_COST" json="DECORATE_COST" value="${rst.DECORATE_COST}"
									type="text" size="40"  label = "配饰费用" autocheck="true" inputtype="float" maxlength="11" valueType="8,2"  />
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								补贴标准版本：
							</td>
							<td width="35%" class="detail_content" colspan="3">
							    <input json="RNVTN_SUBST_STD_VSION" name="RNVTN_SUBST_STD_VSION" id="RNVTN_SUBST_STD_VSION"
							    type="text" size="40"  value="${rst.RNVTN_SUBST_STD_VSION}"
							    autocheck="true" inputtype="string" maxlength="8"/>
							</td>
							<td width="15%" align="right" class="detail_label">
							</td>
							<td width="35%" class="detail_content">
								<input json="STATE" name="STATE" id="STATE"  value="${rst.STATE}"
									type="hidden" label="状态" size="40" value="制定" 
									   readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								制单人：
							</td>
							<td width="35%" class="detail_content">
								<input json="CRE_NAME" name="CRE_NAME" id="CRE_NAME"
									type="text" label="制单人" size="40"
									readonly 
									<c:if test="${not empty rst.CRE_NAME}">value="${rst.CRE_NAME}"</c:if>
									<c:if test="${empty rst.RCRE_NAME}"> value="${CRE_NAME}"</c:if>
									/>
							</td>
							<td width="15%" align="right" class="detail_label">
								制单时间：
							</td>
							<td width="35%" class="detail_content">
								<input json="CRE_TIME" name="CRE_TIME" id="CRE_TIME" type="text" label="制定时间"
									size="40"  readonly
									<c:if test="${not empty rst.CRE_TIME}">value="${rst.CRE_TIME}"</c:if>
									<c:if test="${empty rst.CRE_TIME}"> value="${CRE_TIME}"</c:if>
									 />
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
							    <textarea rows="5" cols="32%" id="REMARK" name="REMARK" json="REMARK" value="${rst.REMARK}" autocheck="true" inputtype="string"   maxlength="250"></textarea>
							    <input type="hidden" id="REMARK1" name="REMARK1" value="${rst.REMARK}"/>
						    </td>
	              </tr>		
			</table>
		</td>
	</tr>
</table>
</form>
<script>
    SelDictShow("BRAND","BS_88","${rst.BRAND}","");
</script>
</body>

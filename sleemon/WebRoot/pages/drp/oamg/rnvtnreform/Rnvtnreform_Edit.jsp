<!-- 
 *@module 渠道管理-装修管理
 *@func   装修整改单维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/rnvtnreform/Rnvtnreform_Edit.js"></script>
	<title>新增或修改</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;装修管理&gt;装修整改单维护编辑</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" value="${CHANNS_CHARG}" id="CHANNS_CHARG"/>
<input type="hidden" id="selectParams" name="selectParams" value="CHANN_ID  in ${CHANNS_CHARG}"/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
							<td width="15%" align="right" class="detail_label">
								装修整改单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_REFORM_ID" name="RNVTN_REFORM_ID"
									id="RNVTN_REFORM_ID" type="hidden" seltarget="selLL"
									label="装修整改单ID" size="40" value="${rst.RNVTN_REFORM_ID}" />
								<input json="RNVTN_REFORM_NO" name="RNVTN_REFORM_NO" type="text"
									autocheck="true" label="装修整改单号" inputtype="string" mustinput="true"
									maxlength="32" size="40"
									<c:if test="${not empty rst.RNVTN_REFORM_NO}">value="${rst.RNVTN_REFORM_NO}"</c:if>
									<c:if test="${empty rst.RNVTN_REFORM_NO}">value="自动生成"</c:if>
									READONLY
									>
							</td>
							<td width="15%" align="right" class="detail_label">
								施工负责人：
							</td>
							<td width="35%" class="detail_content">
								 <input json="PROCESS_CHARGE" id="PROCESS_CHARGE"
									name="PROCESS_CHARGE" type="text"  label="施工负责人" size="40"
									value="${rst.PROCESS_CHARGE}"  autocheck="true"  inputtype="string"  mustinput="true"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								装修验收单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_CHECK_NO" name="RNVTN_CHECK_NO" id="RNVTN_CHECK_NO" type="text"
									label="装修验收单号"
									inputtype="string" size="40" value="${rst.RNVTN_CHECK_NO}" autocheck="true" inputtype="string" mustinput="true" readonly/> 
							    <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_89', false, 'RNVTN_CHECK_NO', 'RNVTN_CHECK_NO', 'forms[0]','RNVTN_CHECK_NO,RNVTN_PRINCIPAL,CHANN_RNVTN_ID,CHANN_RNVTN_NO,RNVTN_CHECK_DATE,TERM_ID,TERM_NO,TERM_NAME,RNVTN_PROP,PLAN_SBUSS_DATE,SALE_STORE_ID,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,USE_AREA', 'RNVTN_CHECK_NO,RNVTN_PRINCIPAL,CHANN_RNVTN_ID,CHANN_RNVTN_NO,RNVTN_CHECK_DATE,TERM_ID,TERM_NO,TERM_NAME,RNVTN_PROP,PLAN_SBUSS_DATE,SALE_STORE_ID,SALE_STORE_NAME,LOCAL_TYPE,BUSS_SCOPE,USE_AREA', 'selectParams')">
							</td>
							<td width="15%" align="right" class="detail_label">
								装修申请单号：
							</td>
							<td width="35%" class="detail_content">
							    <input json="CHANN_RNVTN_ID" name="CHANN_RNVTN_ID" id="CHANN_RNVTN_ID" type="hidden" />
								<input json="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO" type="text"
									label="装修申请单号" size="40" value="${rst.CHANN_RNVTN_NO}" readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								验收负责人：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHECK_CHARGE" name="RNVTN_PRINCIPAL" id="RNVTN_PRINCIPAL"
									 type="text" label="验收负责人"  value="${rst.CHECK_CHARGE}"
									 size="40" readonly/>
							</td>
							<td width="15%" align="right" class="detail_label">
								验收时间：
							</td>
							<td width="35%" class="detail_content">
								<input id="CHECK_TIME" name="RNVTN_CHECK_DATE" json="CHECK_TIME"
									type="text" size="40"  label = "验收时间"  value="${rst.CHECK_TIME}" readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
							    <input json="TERM_ID" name="TERM_ID" id="TERM_ID" type="hidden" />
							    <input json="TERM_NO" name="TERM_NO" id="TERM_NO"
							    type="text" size="40" value="${rst.TERM_NO}" readonly
							    />
							</td>
							<td width="15%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME"
									type="text" label="终端名称" size="40" value="${rst.TERM_NAME}"
									   readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_PROP" name="RNVTN_PROP" id="RNVTN_PROP"
									type="text" label="装修性质" size="40" value="${rst.RNVTN_PROP}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="35%" class="detail_content">
								<input json="PLAN_SBUSS_DATE" name="PLAN_SBUSS_DATE" id="PLAN_SBUSS_DATE" type="text" label="计划开业时间"
									size="40" value="${rst.PLAN_SBUSS_DATE}" readonly />
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="35%" class="detail_content">
							    <input json="SALE_STORE_ID"   name="SALE_STORE_ID" id="SALE_STORE_ID" type="hidden"/>
								<input json="SALE_STORE_NAME" name="SALE_STORE_NAME" id="SALE_STORE_NAME"
									type="text" label="卖场名称" size="40" value="${rst.SALE_STORE_NAME}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								卖场面积(平米)：
							</td>
							<td width="35%" class="detail_content">
								<input json="SALE_STORE_AREA" name="USE_AREA" id="SALE_STORE_AREA" type="text" label="卖场面积"
									size="40" value="${rst.SALE_STORE_AREA}" readonly />
							</td>
						</tr>
						
						
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="35%" class="detail_content">
								<input json="LOCAL_TYPE" name="LOCAL_TYPE" id="LOCAL_TYPE"
									type="text" label="商场位置类别" size="40" value="${rst.LOCAL_TYPE}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="35%" class="detail_content">
								<input json="BUSS_SCOPE" name="BUSS_SCOPE" id="BUSS_SCOPE" type="text" label="经营品牌"
									size="40" value="${rst.BUSS_SCOPE}" readonly />
							</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label">
									状态：
								</td>
								<td width="35%" class="detail_content" colspan="3">
									<input json="STATE" name="STATE" id="STATE"
										type="text" label="状态" size="40" value="${STATE}"
										readonly />
								</td>
						     </tr>
						     <tr>
								<td width="15%" align="right" class="detail_label">
									整改意见：
								</td>
								<td width="35%" class="detail_content" colspan="3">
									<textarea json="REMARK" name="REMARK" id="REMARK" label="整改意见"
										size="40" rows="5" cols="32%">${rst.REMARK}</textarea>
								</td>
							</tr>
                      </table>
                  </td>
            </tr>
       </table>
</form>
</body>
</html>

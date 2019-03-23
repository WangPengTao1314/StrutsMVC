<!-- 
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
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
	<script type=text/javascript src="${ctx}/pages/drp/oamg/rnvtncheck/Rnvtncheck_Edit.js"></script>
	<script type=text/javascript  src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>修改装修验收单维护</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;装修管理&gt;装修验收单维护编辑</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" id="selectParamsT" name="selectParamsT" />
<input type="hidden" id="selectParams" name="selectParams" value="STATE ='审核通过' and DEL_FLAG='0' and CHANN_ID in ${CHANN_ID}"/>
<input type="hidden" id="RNVTN_CHECK_ID" name="RNVTN_CHECK_ID" value="${RNVTN_CHECK_ID}" />

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <input type="hidden" json="RNVTN_CHECK_DTL_ID" value="${rst.RNVTN_CHECK_DTL_ID}">
				<tr>
							<td width="15%" align="right" class="detail_label">
								装修验收单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_CHECK_ID" name="RNVTN_CHECK_ID"
									id="RNVTN_CHECK_ID" type="hidden" seltarget="selLL"   
									label="装修验收单号" size="40" value="${rst.RNVTN_CHECK_ID}" />
									
							    <input json="RNVTN_CHECK_NO" name="RNVTN_CHECK_NO" type="text"
									autocheck="true" label="装修验收单编号" inputtype="string" mustinput="true"
									maxlength="32" size="40"
									<c:if test="${not empty rst.RNVTN_CHECK_NO}">value="${rst.RNVTN_CHECK_NO}"</c:if>
									<c:if test="${empty rst.RNVTN_CHECK_NO}">value="自动生成"</c:if>
									READONLY
									>
							</td>
							<td width="15%" align="right" class="detail_label">
								装修申请单号：
							</td>
							<td width="35%" class="detail_content">
								 <input json="CHANN_RNVTN_ID" id="CHANN_RNVTN_ID"
									json="CHANN_RNVTN_ID" type="hidden" size="40"
									value="${rst.CHANN_RNVTN_ID}" readonly/>
								 <input json="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO"
									name="CHANN_RNVTN_NO" type="text"  label="装修申请单号" size="40"
									value="${rst.CHANN_RNVTN_NO}" readonly  autocheck="true"   inputtype="string"  mustinput="true"/>
								 <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
								    onClick="selCommon('BS_90', false, 'CHANN_RNVTN_ID', 'CHANN_RNVTN_ID', 'forms[0]','CHANN_RNVTN_ID,CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,RNVTN_PROP,PLAN_SBUSS_DATE,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,ZONE_ADDR,LOCAL_TYPE,BUSS_SCOPE','CHANN_RNVTN_ID,CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,RNVTN_PROP,PLAN_SBUSS_DATE,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,ZONE_ADDR,LOCAL_TYPE,BUSS_SCOPE','selectParams')">
									
 							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								验收负责人：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_PRINCIPAL" name="XM" id="RNVTN_PRINCIPAL" type="text"
									label="验收负责人"
									inputtype="string" size="40" value="${rst.RNVTN_PRINCIPAL}" inputtype="string" readonly  mustinput="true"/> 
							    <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_0', false, 'XM', 'XM', 'forms[0]','XM','XM','selectParamsT')">
							</td>
							<td width="15%" align="right" class="detail_label">
								验收时间：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_CHECK_DATE" name="RNVTN_CHECK_DATE" id="RNVTN_CHECK_DATE" type="text"
									label="验收时间" size="40" value="${rst.RNVTN_CHECK_DATE}" onclick="SelectDate();" readonly/>
							    <img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(RNVTN_CHECK_DATE);">
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
							    <input json="TERM_ID" name="TERM_ID" id="TERM_ID" size="40"
									 type="hidden" label="终端ID"  value="${rst.TERM_ID}"
									 />
								<input json="TERM_NO" name="TERM_NO" id="TERM_NO" size="40"
									 type="text" label="终端编号"  value="${rst.TERM_NO}"
									 readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								<input id="TERM_NAME" name="TERM_NAME" json="TERM_NAME"
									type="text" size="40"  label = "终端名称"  value="${rst.TERM_NAME}"
									readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="35%" class="detail_content">
							    <input json="RNVTN_PROP" name="RNVTN_PROP" id="RNVTN_PROP"
							    type="text" size="40" value="${rst.RNVTN_PROP}"
							    readonly/>
							</td>
							<td width="15%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="35%" class="detail_content">
								<input json="PLAN_SBUSS_DATE" name="PLAN_SBUSS_DATE" id="PLAN_SBUSS_DATE"
									type="text" label="计划开业时间" size="40" value="${rst.PLAN_SBUSS_DATE}"
									   readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								所属战区编号：
							</td>
							<td width="35%" class="detail_content">
							    <input json="AREA_ID" name="AREA_ID" id="AREA_ID"
									type="hidden" label="所属战区ID" size="40" value="${rst.AREA_ID}"
									readonly />
								<input json="AREA_NO" name="AREA_NO" id="AREA_NO"
									type="text" label="所属战区编号" size="40" value="${rst.AREA_NO}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								所属战区名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text" label="所属战区名称"
									size="40" value="${rst.AREA_NAME}" readonly />
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="35%" class="detail_content">
							    <input json="AREA_MANAGE_ID"   name="AREA_MANAGE_ID"   id="AREA_MANAGE_ID"
							        value="${rst.AREA_MANAGE_ID}" type="hidden"/>
								<input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME"
									type="text" label="区域经理" size="40" value="${rst.AREA_MANAGE_NAME}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								区域经理电话：
							</td>
							<td width="35%" class="detail_content">
								<input json="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" id="AREA_MANAGE_TEL" type="text" label="区域经理电话"
									size="40" value="${rst.AREA_MANAGE_TEL}" readonly />
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="SALE_STORE_NAME" name="SALE_STORE_NAME" id="SALE_STORE_NAME"
									type="text" label="卖场名称" size="40" value="${rst.SALE_STORE_NAME}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								行政区域：
							</td>
							<td width="35%" class="detail_content">
								<input json="ZONE_ADDR" name="ZONE_ADDR" id="ZONE_ADDR" type="text" label="行政区域"
									size="40" value="${rst.ZONE_ADDR}" readonly />
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
								验收得分：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								<input json="CHECK_SCORE" name="CHECK_SCORE" id="CHECK_SCORE"
									type="text" label="验收得分" size="40" value="${rst.CHECK_SCORE}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
							</td>
							<td width="35%" class="detail_content">
								<input json="STATE" name="STATE" id="STATE" type="hidden" label="状态"
									size="40" value="${rst.STATE}" readonly />
							</td>
						   </tr>
						
							<tr>
							<td width="15%" align="right" class="detail_label">
								验收意见：
							</td>
							<td width="35%" class="detail_content">
							    <textarea rows="2" cols="32%" id="CHECK_REMARK" name="CHECK_REMARK" json="CHECK_REMARK" 
							       autocheck="true" inputtype="string"   maxlength="250"  label="验收意见" value="${rst.CHECK_REMARK}"></textarea>
							    <input type="hidden" id="CHECK_REMARK1" name="CHECK_REMARK1" value="${rst.CHECK_REMARK}"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								处罚意见：
							</td>
							<td width="35%" class="detail_content">
							    <textarea rows="2" cols="32%" id="PUNISH_REMARK" name="PUNISH_REMARK" json="PUNISH_REMARK" 
							      autocheck="true" inputtype="string"   maxlength="250"  label="处罚意见" value="${rst.PUNISH_REMARK}"></textarea>
							    <input type="hidden" id="PUNISH_REMARK1" name="PUNISH_REMARK1" value="${rst.PUNISH_REMARK}"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								附件：
							</td>
							<td width="35%" class="detail_content" colspan="3">
							   <input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH" value="${rst.ATT_PATH}"/> 
						    </td>
	                    </tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
							    <textarea rows="5" cols="32%" id="REMARK" name="REMARK" json="REMARK" ></textarea>
							    <input type="hidden" id="mark" name="mark" value="${rst.REMARK}"/>
						    </td>
	                    </tr>
                     </table>
                  </td>
            </tr>
     </table>
</form>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script>
    uploadFile('PIC_PATH','SAMPLE_DIR',true,true,true);
    var mark = $("#mark").val();
    $("#REMARK").html(mark);
    var mark1 = $("#PUNISH_REMARK1").val();
    $("#PUNISH_REMARK").html(mark1);
    var mark2  = $("#CHECK_REMARK1").val();
    $("#CHECK_REMARK").html(mark2);
</script>
</body>

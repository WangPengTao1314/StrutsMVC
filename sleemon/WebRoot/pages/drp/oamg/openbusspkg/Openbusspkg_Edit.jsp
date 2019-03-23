<!--  
/**
 * @module 渠道管理-开业礼包管理
 * @func   开业大礼包申请单维护
 * @version 1.1
 * @author ghx
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
		<script type="text/javascript" src="${ctx}/pages/drp/oamg/openbusspkg/Openbusspkg_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>开业大礼包申请单维护编辑</title>
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
							<input type="hidden" name="selectParams" value=" STATE in( '启用') and DEL_FLAG='0' and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}') ">
							<input type="hidden" name="selectParamsChann" value=" STATE in( '启用') and DEL_FLAG='0' and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}') ">
							<input type="hidden" name="selectParamsBuild" value=" STATE in( '审核通过') and DEL_FLAG='0' and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}')">					
							<input name="STATE" type="hidden" value="制定" />
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
									
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													开业大礼包申请单号：
												</td>
												<td width="35%" class="detail_content">													
													<input json="OPEN_BUSS_PKG_NO" name="OPEN_BUSS_PKG_NO" autocheck="true" label="开业大礼包申请单号" mustinput="true"  type="text"  inputtype="string"  
							                        readonly size="40"   maxlength="30" 
							                        <c:if test="${rst.OPEN_BUSS_PKG_NO==null}"> value="自动生成"</c:if>
							                     	<c:if test="${rst.OPEN_BUSS_PKG_NO!=null}">value="${rst.OPEN_BUSS_PKG_NO}"</c:if>
							                        />
													
												</td>
												<td width="15%" align="right" class="detail_label">
													申请日期：
												</td>
												<td width="35%" class="detail_content">
													<input id="REQ_DATE" json="REQ_DATE" name="REQ_DATE"
														type="text" autocheck="true" label="申请日期" size="40"
														inputtype="string" mustinput="true" maxlength="100" readonly 
														value="${rst.REQ_DATE}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													终端编号：
												</td>
												<td width="35%" class="detail_content">													
													<input json="TERM_ID" id="TERM_ID" name="TERM_ID" autocheck="true" label="终端ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/> 
							                        <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text" mustinput="true" inputtype="string"  size="40"  readonly    maxlength="30"  value="${rst.TERM_NO}"/>
							                        <img align="absmiddle" name="" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selAdvcorder()"> 																									
												</td>
												<td width="15%" align="right" class="detail_label">
													终端名称：
												</td>
												<td width="35%" class="detail_content">													
													<input json="TERM_NAME" name="TERM_NAME" autocheck="true" label="终端名称"  type="text" size="40"  inputtype="string"   readonly   maxlength="100"  value="${rst.TERM_NAME}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													终端类型：
												</td>
												<td width="35%" class="detail_content">													
													<input json="TERM_TYPE" name="TERM_TYPE" autocheck="true" label="终端类型"  type="text" size="40"  inputtype="string"   readonly    maxlength="100"  value="${rst.TERM_TYPE}"/>													
												</td>
												<td width="15%" align="right" class="detail_label">
													开店日期：
												</td>
												<td width="35%" class="detail_content">																										
													<input id="BEG_SBUSS_DATE" json="BEG_SBUSS_DATE" name="BEG_SBUSS_DATE"
														type="text" autocheck="true" label="开店日期"
														inputtype="string" mustinput="true" maxlength="100" readonly onclick="SelectDate();"
														value="${rst.BEG_SBUSS_DATE}" size="40" />
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BEG_SBUSS_DATE);"  >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													   渠道编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" autocheck="true" label="渠道ID" type="hidden" inputtype="string"   value="${rst.CHANN_ID}"/>
													<input id="CHANN_NO" json="CHANN_NO" name="CHANN_NO" readonly size="40"
														type="text" autocheck="true" label="渠道编号"
														inputtype="string" maxlength="30"
														value="${rst.CHANN_NO}"/>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" readonly 
														type="text" autocheck="true" label="渠道名称"
														inputtype="string" maxlength="100"
														value="${rst.CHANN_NAME}" size="40" />
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  渠道联系人：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_PERSON_CON" json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" readonly 
														type="text" autocheck="true" label="渠道联系人"
														inputtype="string" maxlength="100"
														value="${rst.CHANN_PERSON_CON}" size="40" />
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道联系电话：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_TEL" json="CHANN_TEL" name="CHANN_TEL" readonly 
														type="text" autocheck="true" label="渠道联系电话"
														inputtype="string" maxlength="100"
														value="${rst.CHANN_TEL}" size="40"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  所属战区编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="AREA_ID" id="AREA_ID" name="AREA_ID" autocheck="true" label="所属战区ID" type="hidden" inputtype="string"   value="${rst.AREA_ID}"/>
													<input id="AREA_NO" json="AREA_NO" name="AREA_NO" readonly 
														type="text" autocheck="true" label="所属战区编号"
														inputtype="string"  size="40"
														valueType="chinese:false" maxlength="30"
														value="${rst.AREA_NO}"/>
														
												</td>
												<td width="15%" align="right" class="detail_label">
													所属战区名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_NAME" json="AREA_NAME" name="AREA_NAME" readonly 
														type="text" autocheck="true" label="所属战区名称"
														inputtype="string" maxlength="100" size="40"
														value="${rst.AREA_NAME}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  区域经理：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" readonly 
														type="text" autocheck="true" label="区域经理"
														inputtype="string" maxlength="100" size="40"
														value="${rst.AREA_MANAGE_NAME}"/>
												</td>
												<td width="15%" align="right" class="detail_label">
													区域经理电话：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_MANAGE_TEL" json="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" readonly 
														type="text" autocheck="true" label="区域经理电话"
														inputtype="string" maxlength="100" size="40"
														value="${rst.AREA_MANAGE_TEL}"/>
												</td>
											</tr>											
											<tr>												
												<td width="15%" align="right" class="detail_label">
													卖场名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="SALE_STORE_NAME" json="SALE_STORE_NAME" name="SALE_STORE_NAME" readonly
														type="text" autocheck="true" label="卖场名称"
														inputtype="string" maxlength="100" size="40"
														value="${rst.SALE_STORE_NAME}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													  卖场位置类别：
												</td>
												<td width="35%" class="detail_content">													
													<input id="LOCAL_TYPE" json="LOCAL_TYPE" name="LOCAL_TYPE" readonly
														type="text" autocheck="true" label="卖场位置类别"
														inputtype="string" maxlength="100" size="40"
														value="${rst.LOCAL_TYPE}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													行政区域：
												</td>
												<td width="35%" class="detail_content">
													<input json="ZONE_ID" id="ZONE_ID" name="ZONE_ID" autocheck="true" label="区域ID" type="hidden" inputtype="string"   value="${rst.ZONE_ID}"/>
													<input id="ZONE_ADDR" json="ZONE_ADDR" name="ZONE_ADDR" readonly 
														type="text" autocheck="true" label="行政区域" size="40"
														inputtype="string" maxlength="100"
														value="${rst.ZONE_ADDR}">													
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													  经营品牌：
												</td>
												<td width="35%" class="detail_content">
													<input id="BUSS_SCOPE" json="BUSS_SCOPE" name="BUSS_SCOPE" readonly 
														type="text" autocheck="true" label=" 经营品牌" size="40"
														inputtype="string" maxlength="100"
														value="${rst.BUSS_SCOPE}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													面积（平米）：
												</td>
												<td width="35%" class="detail_content">
													<input id="BUSS_AREA" json="BUSS_AREA" name="BUSS_AREA"  
														type="text" autocheck="true" label="面积（平米）"
														inputtype="float" valueType="10,2" mustinput="true" size="40"
														value="${rst.BUSS_AREA}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													  申请人：
												</td>
												<td width="35%" class="detail_content">
													<input id="REQ_NAME" json="REQ_NAME" name="REQ_NAME" 
														type="text" autocheck="true" label="申请人"
														inputtype="string" maxlength="100" size="40"
														value="${rst.REQ_NAME}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 门店装修报销单：
												</td>
												<td width="35%" class="detail_content">
													<input json="RNVTN_REIT_REQ_ID" id="RNVTN_REIT_REQ_ID" name="RNVTN_REIT_REQ_ID" autocheck="true" label="门店装修报销单ID" type="hidden" inputtype="string"   value="${rst.RNVTN_REIT_REQ_ID}"/>
													<input id="RNVTN_REIT_REQ_NO" json="RNVTN_REIT_REQ_NO" name="RNVTN_REIT_REQ_NO" readonly 
														type="text" autocheck="true" label="门店装修报销单"
														inputtype="string" maxlength="100" size="40"
														value="${rst.RNVTN_REIT_REQ_NO}"/>
													<img align="absmiddle" name="" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selBuild()">
												</td>
												<td width="15%" align="right" class="detail_label">
													报销总金额：
												</td>
												<td width="35%" class="detail_content">
													<input id="TOTAL_REIT_AMOUNT" json="TOTAL_REIT_AMOUNT" name="TOTAL_REIT_AMOUNT" readonly 
															type="text" autocheck="true" label="报销总金额"
															inputtype="string" maxlength="100" size="40"
															value="${rst.TOTAL_REIT_AMOUNT}"/>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 详细地址：
												</td>
												<td width="35%" class="detail_content">
													<input id="ADDRESS" json="ADDRESS" name="ADDRESS" readonly 
														type="text" label="详细地址" 
														inputtype="string"  size="40"
														value="${rst.ADDRESS}">
												</td>
												<td width="15%" align="right" class="detail_label">
													相关附件：
												</td>
												<td width="35%" class="detail_content">
													<input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"
														type="text" autocheck="true" label="相关附件"
														inputtype="string" mustinput="true"
														value="${rst.PIC_PATH}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 详细内容：
												</td>
												<td width="35%" class="detail_content">
													<textarea  json="REQ_RSON" name="REQ_RSON" id="REQ_RSON" mustinput="true" autocheck="true" inputtype="string"   maxlength="250"   label="申报理由"    rows="4" cols="32" >${rst.REQ_RSON}</textarea>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													 备注：
												</td>
												<td width="35%" class="detail_content">													
													<textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="特殊说明"    rows="4" cols="32" >${rst.REMARK}</textarea>
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
		uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
	</script>
</html>

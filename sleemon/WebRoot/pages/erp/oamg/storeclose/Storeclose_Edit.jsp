
<!--  
/**
 * @module 渠道管理-转撤店管理
 * @func 专卖店撤店及终止申请单维护
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
		<script type="text/javascript" src="${ctx}/pages/erp/oamg/storeclose/Storeclose_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>专卖店撤店及终止申请单维护编辑</title>
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
							<input type="hidden" name="selectParams" value=" CHANN_ID in ${QUERY_CHANN_ID} and STATE in( '启用') and DEL_FLAG='0' ">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
									
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													专卖店撤店申请单号：
												</td>
												<td width="35%" class="detail_content">
													<input json="PCL_STORE_CC_REQ_NO" name="PCL_STORE_CC_REQ_NO" autocheck="true" label="专卖店撤店申请单号" mustinput="true"  type="text"  inputtype="string"  
							                        readonly    maxlength="30" size="35"
							                        <c:if test="${rst.PCL_STORE_CC_REQ_NO==null}"> value="自动生成"</c:if>
							                     	<c:if test="${rst.PCL_STORE_CC_REQ_NO!=null}">value="${rst.PCL_STORE_CC_REQ_NO}"</c:if>
							                        />
													
												</td>
												<td width="15%" align="right" class="detail_label">
													填表日期：
												</td>
												<td width="35%" class="detail_content">
													<input id="CRE_DATE" json="CRE_DATE" name="CRE_DATE"
														type="text" autocheck="true" label="填表日期"
														inputtype="string" mustinput="true" maxlength="100" readonly 
														value="${rst.CRE_DATE}" size="35" />
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													终端编号：
												</td>
												<td width="35%" class="detail_content">													
													<input json="TERM_ID" id="TERM_ID" name="TERM_ID" autocheck="true" label="终端ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/> 
							                        <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text" mustinput="true" inputtype="string"  size="35"  readonly    maxlength="30"  value="${rst.TERM_NO}"/>
							                        <img align="absmiddle" name="" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selAdvcorder()"> 																									
												</td>
												<td width="15%" align="right" class="detail_label">
													终端名称：
												</td>
												<td width="35%" class="detail_content">													
													<input json="TERM_NAME" name="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly   maxlength="100"  size="35" value="${rst.TERM_NAME}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													终端类型：
												</td>
												<td width="35%" class="detail_content">													
													<input json="TERM_TYPE" name="TERM_TYPE" autocheck="true" label="终端类型"  type="text"   inputtype="string"   readonly    maxlength="100" size="35"  value="${rst.TERM_TYPE}"/>													
												</td>
												<td width="15%" align="right" class="detail_label">
													开店日期：
												</td>
												<td width="35%" class="detail_content">																										
													<input id="BEG_SBUSS_DATE" json="BEG_SBUSS_DATE" name="BEG_SBUSS_DATE"
														type="text" autocheck="true" label="开店日期"
														inputtype="string" mustinput="true" maxlength="100" readonly onclick="SelectDate();"
														value="${rst.BEG_SBUSS_DATE}" size="35"/>
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BEG_SBUSS_DATE);"  >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													   渠道编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" autocheck="true" label="渠道ID" type="hidden" inputtype="string"   value="${rst.CHANN_ID}"/>
													<input id="CHANN_NO" json="CHANN_NO" name="CHANN_NO" readonly 
														type="text" autocheck="true" label="渠道编号" size="35"
														inputtype="string" maxlength="30"
														value="${rst.CHANN_NO}"/>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" readonly 
														type="text" autocheck="true" label="渠道名称"
														inputtype="string" maxlength="100" size="35"
														value="${rst.CHANN_NAME}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  渠道联系人：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_PERSON_CON" json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" readonly 
														type="text" autocheck="true" label="渠道联系人"
														inputtype="string" maxlength="100" size="35"
														value="${rst.CHANN_PERSON_CON}"/>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道联系电话：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_TEL" json="CHANN_TEL" name="CHANN_TEL" readonly 
														type="text" autocheck="true" label="渠道联系电话"
														inputtype="string" maxlength="100" size="35"
														value="${rst.CHANN_TEL}"/>
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
														inputtype="string" size="35"
														valueType="chinese:false" maxlength="30"
														value="${rst.AREA_NO}"/>
														
												</td>
												<td width="15%" align="right" class="detail_label">
													所属战区名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_NAME" json="AREA_NAME" name="AREA_NAME" readonly 
														type="text" autocheck="true" label="所属战区名称"
														inputtype="string" maxlength="100" size="35"
														value="${rst.AREA_NAME}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  区域经理：
												</td>
												<td width="35%" class="detail_content">
												    <input id="AREA_MANAGE_ID"   json="AREA_MANAGE_ID" name="AREA_MANAGE_ID" type="hidden" value="${rst.AREA_MANAGE_ID}"/>
													<input id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" readonly 
														type="text" autocheck="true" label="区域经理"
														inputtype="string" maxlength="100" size="35"
														value="${rst.AREA_MANAGE_NAME}"/>
												</td>
												<td width="15%" align="right" class="detail_label">
													区域经理电话：
												</td>
												<td width="35%" class="detail_content">
													<input id="AREA_MANAGE_TEL" json="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" readonly 
														type="text" autocheck="true" label="区域经理电话"
														inputtype="string" maxlength="100" size="35"
														value="${rst.AREA_MANAGE_TEL}"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  联系人：
												</td>
												<td width="35%" class="detail_content">
													<input id="PERSON_CON" json="PERSON_CON" name="PERSON_CON" readonly 
														type="text" autocheck="true" label="联系人"
														inputtype="string" maxlength="100" size="35"
														value="${rst.PERSON_CON}">														
												</td>
												<td width="15%" align="right" class="detail_label">
													联系电话（座机）：
												</td>
												<td width="35%" class="detail_content">
													<input id="TEL" json="TEL" name="TEL" readonly
														type="text" autocheck="true" label="联系电话（座机）"
														inputtype="string" maxlength="100" size="35"
														value="${rst.TEL}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  手机：
												</td>
												<td width="35%" class="detail_content">
													<input id="MOBILE" json="MOBILE" name="MOBILE" readonly
														type="text" autocheck="true" label="手机"
														inputtype="string" maxlength="100" size="35"
														value="${rst.MOBILE}"  />
												</td>
												<td width="15%" align="right" class="detail_label">
													传真：
												</td>
												<td width="35%" class="detail_content">
													<input id="TAX" json="TAX" name="TAX"
														type="text" autocheck="true" label="传真" size="35"
														inputtype="string" maxlength="100"
														value="${rst.TAX}" readonly/>
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													卖场名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="SALE_STORE_NAME" json="SALE_STORE_NAME" name="SALE_STORE_NAME" readonly
														type="text" autocheck="true" label="卖场名称"
														inputtype="string" maxlength="100" size="35"
														value="${rst.SALE_STORE_NAME}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													  卖场位置类别：
												</td>
												<td width="35%" class="detail_content">
													<input id="LOCAL_TYPE" json="LOCAL_TYPE" name="LOCAL_TYPE" readonly
														type="text" autocheck="true" label="卖场位置类别"
														inputtype="string" maxlength="100" size="35"
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
														type="text" autocheck="true" label="行政区域"
														inputtype="string" maxlength="100" size="35"
														value="${rst.ZONE_ADDR}">													
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													  经营品牌：
												</td>
												<td width="35%" class="detail_content">													
													<input id="BUSS_SCOPE" json="BUSS_SCOPE" name="BUSS_SCOPE" readonly 
														type="text" autocheck="true" label=" 经营品牌"
														inputtype="string" maxlength="100" size="35"
														value="${rst.BUSS_SCOPE}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													负责区域：
												</td>
												<td width="35%" class="detail_content">													
													<input id="CHARGE_AREA" json="CHARGE_AREA" name="CHARGE_AREA" 
														type="text" autocheck="true" label="负责区域"
														inputtype="string" maxlength="100" size="35"
														value="${rst.CHARGE_AREA}">													
												</td>
												<td width="15%" align="right" class="detail_label">
													图纸面积（平米）：
												</td>
												<td width="35%" class="detail_content">
													<input id="BUSS_AREA" json="BUSS_AREA" name="BUSS_AREA"  
														type="text" autocheck="true" label="图纸面积（平米）"
														inputtype="float" valueType="10,2" mustinput="true" 
														value="${rst.BUSS_AREA}" size="35"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 专卖店撤店日期：
												</td>
												<td width="35%" class="detail_content">													
													<input id="SPCL_STORE_CANCEL_DATE" json="SPCL_STORE_CANCEL_DATE" name="SPCL_STORE_CANCEL_DATE"
														type="text" autocheck="true" label="专卖店撤店日期"
														inputtype="string" mustinput="true" maxlength="100" readonly onclick="SelectDate();"
														value="${rst.SPCL_STORE_CANCEL_DATE}" size="35"/>
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SPCL_STORE_CANCEL_DATE);"  >
												</td>
												<td width="15%" align="right" class="detail_label">
													撤店类型：
												</td>
												<td width="35%" class="detail_content">													
													<select label="撤店类型" name="STORE_CANCEL_TYPE"
														valueType="chinese:false" inputtype="string"
														style="width: 245px;" id="STORE_CANCEL_TYPE" json="STORE_CANCEL_TYPE" 
														value="${rst.STORE_CANCEL_TYPE}" mustinput="true"/>
													</select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 原门店报销标准（元/平米）：
												</td>
												<td width="35%" class="detail_content">
													<input id="REIT_AMOUNT_PS" json="REIT_AMOUNT_PS" name="REIT_AMOUNT_PS"
														type="text" autocheck="true" label="原门店报销标准（元/平米）"
														inputtype="float" valueType="10,2" mustinput="true" 
														value="${rst.REIT_AMOUNT_PS}" size="35"/>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													 未报次数：
												</td>
												<td width="35%" class="detail_content">
													<input id="REITED_NUM" json="REITED_NUM" name="REITED_NUM"
														type="text" autocheck="true" label="未报次数"
														inputtype="float" valueType="10,2" mustinput="true" 
														value="${rst.REITED_NUM}" size="35"/>
												</td>												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													已报总金额：
												</td>
												<td width="35%" class="detail_content">
													<input id="REITED_AMOUNT" json="REITED_AMOUNT" name="REITED_AMOUNT"
														type="text" autocheck="true" label="已报总金额"
														inputtype="float" valueType="10,2" mustinput="true" 
														value="${rst.REITED_AMOUNT}" size="35"/>
												</td>
												<td width="15%" align="right" class="detail_label">
													&nbsp;
												</td>
												<td width="35%" class="detail_content">													
													&nbsp;												
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 详细地址：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<input id="ADDRESS" json="ADDRESS" name="ADDRESS" readonly 
														type="text" label="详细地址"
														inputtype="string" size="89"
														value="${rst.ADDRESS}">
												</td>
												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 撤店（终止）理由：
												</td>
												<td width="35%" class="detail_content">
													<textarea  json="REQ_RSON" name="REQ_RSON" id="REQ_RSON" mustinput="true" autocheck="true" inputtype="string"  rows="5" cols="28%" maxlength="250"  label="撤店（终止）理由" >${rst.REQ_RSON}</textarea>
												</td>
												<td width="15%" align="right" class="detail_label">
													相关附件：
												</td>
												<td width="35%" class="detail_content">
													<input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"
														type="text" autocheck="true" label="相关附件"
														inputtcheype="string" mustinput="true"
														value="${rst.PIC_PATH}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 特殊说明：
												</td>
												<td width="35%" class="detail_content" colspan="3">													
													<textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string" rows="5" cols="28%"  maxlength="250"   label="特殊说明"    rows="4" cols="80%" >${rst.REMARK}</textarea>
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
		SelDictShow("STORE_CANCEL_TYPE", "BS_91", '${rst.STORE_CANCEL_TYPE}', "");
		uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
	</script>
</html>

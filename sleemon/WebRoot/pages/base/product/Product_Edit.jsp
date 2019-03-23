
<!--  
/**
 * @module 系统管理
 * @func 货品信息编辑
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
		<script type="text/javascript" src="${ctx}/pages/base/product/Product_Edit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		</script>
		<title>货品信息编辑</title>
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
							<input type="hidden" name=selectParams value="STATE='启用'">
							<input type="hidden" name=selectFlag id="selectFalg" value="">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>												
												<td width="15%" align="right" class="detail_label">
													货品编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="PRD_NO" json="PRD_NO" name="PRD_NO" type="text" 
														autocheck="true" label="货品编号" inputtype="string" valueType="chinese:false"
														mustinput="true" maxlength="30" value="${rst.PRD_NO}"
														<c:if test="${rst.PRD_NO!=NULL}">
														READONLY
														</c:if>
														>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													货品名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_NAME" id="PRD_NAME" name="PRD_NAME" type="text" autocheck="true"
														label="货品名称" inputtype="string" mustinput="true" maxlength="100" 
														value="${rst.PRD_NAME}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													规格型号：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_SPEC" name="PRD_SPEC" type="text" autocheck="true"
														label="规格型号" inputtype="string"  maxlength="50" 
														value="${rst.PRD_SPEC}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													花号：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_COLOR" name="PRD_COLOR" type="text" autocheck="true"
														label="花号" inputtype="string" maxlength="50"
														value="${rst.PRD_COLOR}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													品牌：
												</td>
												<td width="35%" class="detail_content">
													<select id="BRAND" json="BRAND" 
													name="BRAND" style="width: 150px" inputtype="string" mustinput="true">
														<option value=""
															<c:if test="${rst.BRAND!=NULL}">
															selected="selected"
															</c:if>  
														>--请选择--</option>
														<c:forEach items="${list}" var="brand">
															<option value="${brand}"
																<c:if test="${rst.BRAND==brand}"> selected="selected" </c:if> 
															>${brand}</option>
														</c:forEach>
													</select>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													标准单位：
												</td>
												<td width="35%" class="detail_content">
													<input id="STD_UNIT" json="STD_UNIT" name="STD_UNIT" maxlength="50" mustinput="true"
														type="text" inputtype="string" autocheck="true" label="标准单位"
														value="${rst.STD_UNIT}" READONLY >
															<img align="absmiddle" name="selJGXX" style="cursor: hand"
															src="${ctx}/styles/${theme}/images/plus/select.gif"
															onClick="selCommon('BS_23', false, 'STD_UNIT', 'MEAS_UNIT_NAME', 'forms[0]','STD_UNIT', 'MEAS_UNIT_NAME', 'selectParams')">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													默认计量单位：
												</td>
												<td width="35%" class="detail_content">
													<input id="MEAS_UNIT" json="MEAS_UNIT" name="MEAS_UNIT" maxlength="50"
														type="text" inputtype="string" autocheck="true" label="默认计量单位"
														value="${rst.MEAS_UNIT}" READONLY>
														<img align="absmiddle" name="selJGXX" style="cursor: hand"
															src="${ctx}/styles/${theme}/images/plus/select.gif"
															onClick="selCommon('BS_23', false, 'MEAS_UNIT', 'MEAS_UNIT_NAME', 'forms[0]','MEAS_UNIT', 'MEAS_UNIT_NAME', 'selectParams')">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													换算关系：
												</td>
												<td width="35%" class="detail_content">
													<input json="RATIO" name="RATIO" id="RATIO" type="text" autocheck="true"
														label="换算关系" inputtype="float" valueType="4,2"
														value="${rst.RATIO}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													货品分类编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="PAR_PRD_ID" json="PAR_PRD_ID" name="PAR_PRD_ID" type="hidden" inputtype="string" maxlength="30" value="${rst.PAR_PRD_ID}">
													<input id="PAR_PRD_NO" json="PAR_PRD_NO"  name="PAR_PRD_NO" type="text" inputtype="string" maxlength="30" value="${rst.PAR_PRD_NO}" label="货品分类编号" READONLY >
													<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="selParPrdId()">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													货品分类名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="PAR_PRD_NAME" id="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text" autocheck="true"
														label="货品分类名称" inputtype="string" maxlength="100" READONLY
														value="${rst.PAR_PRD_NAME}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													材质	：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_MATL" id="PRD_MATL" name="PRD_MATL" type="text" autocheck="true"
														label="材质" inputtype="string"  maxlength="100"
														value="${rst.PRD_MATL}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													包装后体积：
												</td>
												<td width="35%" class="detail_content">
													<input json="VOLUME" name="VOLUME" id="VOLUME" type="text" autocheck="true"
														label="包装后体积" inputtype="float" valueType="4,2" 
														value="${rst.VOLUME}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													包装后长度：
												</td>
												<td width="35%" class="detail_content">
													<input id="LENGTH" json="LENGTH" name="LENGTH" type="text"
														autocheck="true" label="包装后长度" inputtype="float" valueType="4,2"  value="${rst.LENGTH}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													包装后宽度：
												</td>
												<td width="35%" class="detail_content">
													<input json="WIDTH" name="WIDTH" type="text" autocheck="true"
														label="包装后宽度" inputtype="float" valueType="4,2" 
														value="${rst.WIDTH}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													包装后高度：
												</td>
												<td width="35%" class="detail_content">
													<input id="HEIGHT" json="HEIGHT" name="HEIGHT" type="text"
														autocheck="true" label="包装后高度" inputtype="float" valueType="4,2"
														 value="${rst.HEIGHT}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													是否赠品标记
												</td>
												<td width="35%" class="detail_content">
													<input id="IS_CAN_FREE_FLAG" name="IS_CAN_FREE_FLAG" json="IS_CAN_FREE_FLAG" type="radio" value="0" <c:if test="${rst.IS_CAN_FREE_FLAG!=1 }">checked="checked"</c:if> />否
													<input id="IS_CAN_FREE_FLAG" name="IS_CAN_FREE_FLAG" json="IS_CAN_FREE_FLAG" type="radio" value="1" <c:if test="${rst.IS_CAN_FREE_FLAG==1 }">checked="checked"</c:if>/>是
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													零售价：
												</td>
												<td width="35%" class="detail_content">
													<input json="FACT_PRICE" id="FACT_PRICE"  name="FACT_PRICE" type="text" autocheck="true" 
														label="零售价" inputtype="float" valueType="8,2" 
														value="${rst.FACT_PRICE}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													供货价：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRVD_PRICE" name="PRVD_PRICE" id="PRVD_PRICE" type="text" autocheck="true"
														label="供货价" inputtype="float" valueType="8,2" mustinput="true" 
														value="${rst.PRVD_PRICE}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													最低零售价格：
												</td>
												<td width="35%" class="detail_content">
													<input id="RET_PRICE_MIN" json="RET_PRICE_MIN" name="RET_PRICE_MIN" type="text"
														autocheck="true" label="最低零售价" inputtype="float" valueType="8,2"  
														value="${rst.RET_PRICE_MIN}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													开始生产日期：
												</td>
												<td width="35%" class="detail_content">
													<input name="BEG_DATE" id="BEG_DATE" type="text"  json="BEG_DATE"	value="${rst.BEG_DATE}" onclick="SelectDate();">
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
													onclick="SelectDate(BEG_DATE);"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													财务对照码：
												</td>
												<td width="35%" class="detail_content">
													<input id="U_CMP_CODE" json="U_CMP_CODE" name="U_CMP_CODE" type="text"
														autocheck="true" label="财务对照码" inputtype="string"
														 maxlength="50" value="${rst.U_CMP_CODE}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													货品套标记：
												</td>
												<td width="35%" class="detail_content">
													<input id="PRD_SUIT_FLAG_Y" name="PRD_SUIT_FLAG_T"  type="radio" value="0" onclick="editRadio('PRD_SUIT_FLAG',0)" <c:if test="${rst.PRD_SUIT_FLAG!=1 }">checked="checked"</c:if> />否
													<input id="PRD_SUIT_FLAG_N" name="PRD_SUIT_FLAG_T" type="radio" value="1" onclick="editRadio('PRD_SUIT_FLAG',1)" <c:if test="${rst.PRD_SUIT_FLAG==1 }">checked="checked"</c:if>/>是
													<input id="PRD_SUIT_FLAG" type="hidden"ext" name="PRD_SUIT_FLAG" json="PRD_SUIT_FLAG" value="${rst.PRD_SUIT_FLAG}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													是否分销标记：
												</td>
												<td width="35%" class="detail_content">
													<input id="IS_DISTRIBUT_FLAG_Y" name="IS_DISTRIBUT_FLAG_T" onclick="editRadio('IS_DISTRIBUT_FLAG',0)" type="radio" value="0" <c:if test="${rst.IS_DISTRIBUT_FLAG!=1 }">checked="checked"</c:if> />否
													<input id="IS_DISTRIBUT_FLAG_N" name="IS_DISTRIBUT_FLAG_T" onclick="editRadio('IS_DISTRIBUT_FLAG',1)" type="radio" value="1" <c:if test="${rst.IS_DISTRIBUT_FLAG==1 }">checked="checked"</c:if>/>是
													<input id="IS_DISTRIBUT_FLAG" name="IS_DISTRIBUT_FLAG" json="IS_DISTRIBUT_FLAG" type="hidden" value="${rst.IS_DISTRIBUT_FLAG}">
												</td>
												<td width="15%" align="right" class="detail_label">
													是否返利标记：
												</td>
												<td width="35%" class="detail_content">
													<input id="IS_REBATE_FLAG_Y" name="IS_REBATE_FLAG_T" onclick="editRadio('IS_REBATE_FLAG',0)" type="radio" value="0" <c:if test="${rst.IS_REBATE_FLAG!=1 }">checked="checked"</c:if> />否
													<input id="IS_REBATE_FLAG_N" name="IS_REBATE_FLAG_T" onclick="editRadio('IS_REBATE_FLAG',1)" type="radio" value="1" <c:if test="${rst.IS_REBATE_FLAG==1 }">checked="checked"</c:if>/>是
													<input id="IS_REBATE_FLAG" name="IS_REBATE_FLAG" json="IS_REBATE_FLAG" type="hidden" value="${rst.IS_REBATE_FLAG}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													图片路径：
												</td>
												<td width="35%" class="detail_content">
													<input json="PIC_PATH" name="PIC_PATH"
														type="text" id="PIC_PATH" 
														label="图片路径" inputtype="string" 
														value="${rst.PIC_PATH }">
												</td>
												<td width="15%" align="right" class="detail_label">
													详细信息路径：
												</td>
												<td width="35%" class="detail_content">
													<input json="DTL_PATH" name="DTL_PATH"
														type="text" id="DTL_PATH" 
														label="详细信息路径" inputtype="string"
														value="${rst.DTL_PATH }">
												</td>
												<td width="15%" align="right" class="detail_label">
												</td>
											</tr>
											<tr>
							               		<td width="15%" align="right" class="detail_label">备注：</td>
											   <td width="35%" class="detail_content" colspan="3">
							                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
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
	<%@ include file="/pages/common/uploadfile/picUpdfile.jsp"%>
	<script language="JavaScript">
		SelDictShow("PRD_TYPE", "172", '${rst.PRD_TYPE}', "");
		//SelDictShow("PRD_COLOR", "BS_6", '${rst.PRD_COLOR}', "");
		uploadFile('PIC_PATH', 'PICTURE_DIR');
		uploadFile('DTL_PATH', 'PICTURE_DIR');
	</script>
</html>

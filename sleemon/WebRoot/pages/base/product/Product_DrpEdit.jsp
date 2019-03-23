
<!--  
/**
 * @module 系统管理
 * @func 分销 货品
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
		<script type="text/javascript" src="${ctx}/pages/base/product/Product_DrpEdit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
		</script>
		<title>分销 货品信息编辑</title>
		<style>
.box{ position:relative;}
.text{
 
 
float:left;
border:none;
background:none;
border:solid 1px #ccc;}
.float{
text-align:center;
float:left;
position:relative;
 
 }

</style>

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
												  <c:if test="${empty rst.PRD_ID}">
												    <input id="prefix"  type="hidden" inputtype="string" autocheck="true"   
														 value="${ZTXXID}" READONLY   size="7" class="readonly">
													<input id="suffix"   name="suffix" type="text" 
														autocheck="true" label="货品编号" inputtype="int"  valueType="chinese:false" 
														mustinput="true"  value=""  >
														
												   <input type="hidden" id="PRD_NO" name="PRD_NO" json="PRD_NO" value="${rst.PRD_NO}"/>
												   <input type="hidden" id="PRD_ID" name="PRD_ID"  value="${rst.PRD_ID}"/>
												</c:if>   
											    <c:if test="${!empty rst.PRD_ID}">
												 <input id="PRD_NO" json="PRD_NO"   name="PRD_NO" type="text" label="货品编号"
														inputtype="string" autocheck="true" mustinput="true"
														maxlength="30"  READONLY  value="${rst.PRD_NO}"  />
												</c:if>
												   
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
													规格型号：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_SPEC" name="PRD_SPEC" type="text" autocheck="true"
														label="规格型号" inputtype="string" mustinput="true" maxlength="50"
														value="${rst.PRD_SPEC}">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													标准单位：
												</td>
												<td width="35%" class="detail_content">
													<input type="hidden" name=MEAS_UNIT id="MEAS_UNIT" json="MEAS_UNIT" value="${rst.STD_UNIT}">
													<input id="STD_UNIT" json="STD_UNIT" name="STD_UNIT" maxlength="50" mustinput="true"
														type="text" inputtype="string" autocheck="true" label="标准单位"
														value="${rst.STD_UNIT}" READONLY >
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_23', false, 'STD_UNIT', 'MEAS_UNIT_NAME', 'forms[0]','STD_UNIT,MEAS_UNIT', 'MEAS_UNIT_NAME,MEAS_UNIT_NAME', 'selectParams')">
												</td>
											</tr>
											<tr>
											   <td width="15%" align="right" class="detail_label"> 采购价： </td>
												<td width="35%" class="detail_content">
													<input json="PRVD_PRICE" name="PRVD_PRICE" id="PRVD_PRICE"  type="text" autocheck="true"
														label="采购价"  inputtype="float"   valueType="8,2"   mustinput="true" maxlength="11"
														value="${rst.PRVD_PRICE}">
														
												</td>
												 <td width="15%" align="right" class="detail_label">销售价： </td>
												 <td width="35%" class="detail_content">
												 	<input json="FACT_PRICE" name="FACT_PRICE" id="FACT_PRICE" type="text"   mustinput="true"
														label="销售价"  inputtype="float"   valueType="8,2"   maxlength="11" autocheck="true"
														value="${rst.FACT_PRICE}">	
												 </td>
											</tr>
											
											<tr>
							               		<td width="15%" align="right" class="detail_label">备注：</td>
											   <td width="35%" class="detail_content" colspan="3">
							                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
											   </td>
											   <input json="PRD_COLOR" name="PRD_COLOR" type="hidden"  value="其它">
											   <input json="BRAND" name="BRAND" type="hidden"  value="其它">
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
	
</html>

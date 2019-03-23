<!--  
/**
 * @module 销售管理
 * @func 临时额度调整申请
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
		<script type="text/javascript" src="${ctx}/pages/drp/oamg/channcontractup/Channcontractup_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>渠道合同上传编辑</title>
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
							<input type="hidden" name="selectParams" value=" STATE in( '启用') and  DEL_FLAG=0 and CHANN_ID in ${CHANNS_CHARG}" >
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道合同编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_CONTRACT_NO" name="CHANN_CONTRACT_NO" type="text"
														autocheck="true" label="渠道合同编号" inputtype="string" mustinput="true"
														maxlength="32"
														<c:if test="${rst.CHANN_CONTRACT_NO!=null}">value="${rst.CHANN_CONTRACT_NO}"</c:if>
														<c:if test="${rst.CHANN_CONTRACT_NO==null}">value="自动生成"</c:if>
														READONLY
														>
												</td>
												<td width="15%" align="right" class="detail_label">
													年份：
												</td>
												<td width="35%" class="detail_content">
													<select id="YEAR" json="YEAR" name="YEAR"
														 autocheck="true" label="年份"
														inputtype="string" mustinput="true" style="width: 155px;" ></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"
														inputtype="string" seltarget="selJGXX" autocheck="true"
														maxlength="32" value="${rst.CHANN_ID}">
																					
													<input id="CHANN_NO" json="CHANN_NO" label="渠道编号" name="CHANN_NO" type="text" inputtype="string"
														seltarget="selJGXX" autocheck="true" maxlength="32" mustinput="true"
														value="${rst.CHANN_NO}" READONLY>
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME"
														type="text" autocheck="true" label="渠道名称"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.CHANN_NAME}" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													一季度指标:
												</td>
												<td width="35%" class="detail_content">
													<input id="FIRST_QUARTER_AMOUNT" json="FIRST_QUARTER_AMOUNT" name="FIRST_QUARTER_AMOUNT"
														type="text" autocheck="true" label="一季度指标"
														 	maxlength="100" onkeyup="sumAmount();"
														value="${rst.FIRST_QUARTER_AMOUNT}" >
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													二季度指标:
												</td>
												<td width="35%" class="detail_content">
													<input id="SECOND_QUARTER_AMOUNT" json="SECOND_QUARTER_AMOUNT" name="SECOND_QUARTER_AMOUNT"
														type="text" autocheck="true" label="二季度指标"
														  maxlength="100" onkeyup="sumAmount();"
														value="${rst.SECOND_QUARTER_AMOUNT}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													三季度指标:
												</td>
												<td width="35%" class="detail_content">
													<input id="THIRD_QUARTER_AMOUNT" json="THIRD_QUARTER_AMOUNT" name="THIRD_QUARTER_AMOUNT"
														type="text" autocheck="true" label="三季度指标"
															maxlength="100" onkeyup="sumAmount();"
														value="${rst.THIRD_QUARTER_AMOUNT}" >
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													四季度指标:
												</td>
												<td width="35%" class="detail_content">
													<input id="FOURTH_QUARTER_AMOUNT" json="FOURTH_QUARTER_AMOUNT" name="FOURTH_QUARTER_AMOUNT"
														type="text" autocheck="true" label="四季度指标"
														  maxlength="100" onkeyup="sumAmount();"
														value="${rst.FOURTH_QUARTER_AMOUNT}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													年度指标:
												</td>
												<td width="35%" class="detail_content">
													<input id="YEAR_PLAN_AMOUNT" json="YEAR_PLAN_AMOUNT" name="YEAR_PLAN_AMOUNT"
														type="text" autocheck="true" label="年度指标"
														  maxlength="100"
														value="${rst.YEAR_PLAN_AMOUNT}" readonly="readonly" >
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													合同附件：
												</td>
												<td width="35%" class="detail_content">
													<input json="CONTRACT_ATT" name="CONTRACT_ATT" type="text"
													 mustinput="true"
														id="CONTRACT_ATT" label="合同附件"
														inputtype="string" autocheck="true" 
														value="${rst.CONTRACT_ATT }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													备注：
												</td>
												<td width="35%" class="detail_content" colspan="3">
												    <textarea json="REMARK" name="REMARK" inputtype="string" maxlength="250" rows="3" cols="80%"><c:out value="${rst.REMARK}"></c:out></textarea>
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
		SelDictShow("YEAR", "89", "${rst.YEAR}", "");
		uploadFile('CONTRACT_ATT', 'SAMPLE_DIR', true,true,true);
	</script>
</html>

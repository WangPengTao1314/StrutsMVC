<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/erp/sale/budgetitem/Budgetitem_Edit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>编辑</title>
	</head>
	<body class="bodycss1">
		<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<!--占位用行，以免显示数据被浮动层挡住-->
					<td height="20px"> &nbsp; </td>
				</tr>
				<tr>
					<td>
						<form name="mainForm" id="mainForm">
							<input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and DEL_FLAG=0 ">
							<input type="hidden" name=selectFlag id="selectFalg" value="">
							<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>												
												<td width="15%" align="right" class="detail_label">
													预算科目编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="BUDGET_ITEM_NO" json="BUDGET_ITEM_NO" name="BUDGET_ITEM_NO" type="text" 
														autocheck="true" label="预算科目编号" inputtype="string" valueType="chinese:false"
														mustinput="true" maxlength="50" value="${rst.BUDGET_ITEM_NO}"
														<c:if test="${not empty rst.BUDGET_ITEM_ID}"> readonly</c:if>
														/>
														
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													预算科目名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" type="text" autocheck="true"
														label="预算科目名称" inputtype="string" mustinput="true" maxlength="100" 
														value="${rst.BUDGET_ITEM_NAME}" />
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													预算科目类型：
												</td>
												<td width="35%" class="detail_content">
												   <select id="BUDGET_ITEM_TYPE" json="BUDGET_ITEM_TYPE" style="width:155px;" label="预算科目类型" autocheck="true" mustinput="true" inputtype="string" ></select>
		                                           <SPAN class=validate>&nbsp;*</SPAN> 
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
												</td>
												<td width="35%" class="detail_content"></td>
											</tr>
											<tr>	
											    <td width="15%" align="right" class="detail_label" nowrap>
													上级预算科目编号：
												</td>
												<td width="35%" class="detail_content">
												    <input type="hidden" name="PAR_BUDGET_ITEM_ID" id="PAR_BUDGET_ITEM_ID" json="PAR_BUDGET_ITEM_ID" value="${rst.PAR_BUDGET_ITEM_ID}"/>
													<input json="PAR_BUDGET_ITEM_NO" name="PAR_BUDGET_ITEM_NO" id="PAR_BUDGET_ITEM_NO" type="text" autocheck="true" readonly 
														label="上级预算科目编号" inputtype="string" maxlength="50" value="${rst.PAR_BUDGET_ITEM_NO}">
												    <img align="absmiddle" name="selJGXX" style="cursor: hand"
													 src="${ctx}/styles/${theme}/images/plus/select.gif"
													 onClick="selCommon('BS_152', false, 'PAR_BUDGET_ITEM_ID', 'BUDGET_ITEM_ID', 'forms[0]','PAR_BUDGET_ITEM_NO,PAR_BUDGET_ITEM_NAME', 'BUDGET_ITEM_NO,BUDGET_ITEM_NAME', 'selectParams')">
												
												</td>											
												<td width="15%" align="right" class="detail_label">
													上级预算科目名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="PAR_BUDGET_ITEM_NAME" json="PAR_BUDGET_ITEM_NAME"  readonly
													 name="PAR_BUDGET_ITEM_NAME"  label="上级预算科目名称" inputtype="string"   value="${rst.PAR_BUDGET_ITEM_NAME}"/>
												</td>
											</tr>
											<tr>
										    	<td width="15%" align="right" class="detail_label" nowrap>
													终结点标记：
												</td>
												<td width="35%" class="detail_content">
													<input id="FINAL_NODE_FLAG_Y" name="FINAL_NODE_FLAG_T"  onclick="editRadio('FINAL_NODE_FLAG',0)"   type="radio" value="0" <c:if test="${rst.FINAL_NODE_FLAG eq 0 }">checked="checked"</c:if> />否
													<input id="FINAL_NODE_FLAG_N" name="FINAL_NODE_FLAG_T"  onclick="editRadio('FINAL_NODE_FLAG',1)"   type="radio" value="1" <c:if test="${rst.FINAL_NODE_FLAG eq 1 }">checked="checked"</c:if>/>是
												    <input id="FINAL_NODE_FLAG" name="FINAL_NODE_FLAG" json="FINAL_NODE_FLAG" type="hidden" value="${rst.FINAL_NODE_FLAG}">
												</td>
							               		<td width="15%" align="right" class="detail_label">备注：</td>
											    <td width="35%" class="detail_content"  >
							                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="40%" >${rst.REMARK}</textarea>
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
		SelDictShow("BUDGET_ITEM_TYPE", "BS_145", '${rst.BUDGET_ITEM_TYPE}', "");
	</script>
</html>

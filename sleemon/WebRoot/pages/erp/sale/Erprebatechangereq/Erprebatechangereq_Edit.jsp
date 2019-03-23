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
		<script type="text/javascript" src="${ctx}/pages/erp/sale/Erprebatechangereq/Erprebatechangereq_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>返利额度变更申请单编辑</title>
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
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<input type="hidden" id="IS_COPY" name="IS_COPY" value="${IS_COPY}"/>
								<input type="hidden" id="selectParams" name="selectParams" value=" DEL_FLAG=0 and STATE='启用' and CHANN_ID in ${CHANNS_CHARG}  "/>
								<input type="hidden" name="selectPYXXParams"	value=" RYZT in( '启用') and DELFLAG='0' and jgxxid in (select jgxxid from T_SYS_JGXX where ZTXXID='${ZTXXID}') " />
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													返利额度变更申请单编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="REBATE_CHANGE_REQ_NO" name="REBATE_CHANGE_REQ_NO" type="text"
														autocheck="true" label="返利额度变更申请单编号" inputtype="string" mustinput="true"
														maxlength="32"
														<c:if test="${rst.REBATE_CHANGE_REQ_NO!=null}">value="${rst.REBATE_CHANGE_REQ_NO}"</c:if>
														<c:if test="${rst.REBATE_CHANGE_REQ_NO==null}">value="自动生成"</c:if>
														READONLY
														>
												</td>
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
														onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE', 'CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE', 'selectParams')">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME"
														type="text" autocheck="true" label="渠道名称"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.CHANN_NAME}" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道类型：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANN_TYPE" json="CHANN_TYPE" name="CHANN_TYPE"
														type="text" autocheck="true" label="渠道类型"
														inputtype="string" mustinput="true" maxlength="100"
														value="${rst.CHANN_TYPE}" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													单据类型：
												</td>
												<td width="35%" class="detail_content">
													<select id="BILL_TYPE" json="BILL_TYPE" autocheck="true" label="单据类型"
														inputtype="string" mustinput="true" name="BILL_TYPE" style="width: 155"></select>
												</td>
												<td width="15%" align="right" class="detail_label">
													返利类别:
												</td>
												<td width="35%" class="detail_content">
													<select id="REBATE_TYPE" json="REBATE_TYPE" autocheck="true" label="返利类别"
														inputtype="string" mustinput="true" name="REBATE_TYPE" style="width: 155"></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													申请人：
												</td>
												<td width="35%" class="detail_content">
													<input id="REQ_PSON_ID" json="REQ_PSON_ID" name="REQ_PSON_ID"
														type="hidden" inputtype="string"
														value="${rst.REQ_PSON_ID}">
													<input id="REQ_PSON_NAME" json="REQ_PSON_NAME" name="REQ_PSON_NAME" type="text" mustinput="true" 
													 inputtype="string" value="${rst.REQ_PSON_NAME}" label="申请人" READONLY autocheck="true" maxlength="30"
													  >
													<img align="absmiddle" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_0', false, 'REQ_PSON_ID','RYXXID', 'forms[0]','REQ_PSON_ID,REQ_PSON_NAME','RYXXID,XM','selectPYXXParams')"
														>
												</td>
												<td width="15%" align="right" class="detail_label">
													变更额度：
												</td>
												<td width="35%" class="detail_content">
													<input id="CHANGE_REBATE" json="CHANGE_REBATE" name="CHANGE_REBATE" type="text" autocheck="true"
														mustinput="true" label="变更额度" inputtype="zffloat" valueType="8,2"
														value="${rst.CHANGE_REBATE }" >
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
	SelDictShow("BILL_TYPE","BS_152","${rst.BILL_TYPE}","");  
    SelDictShow("REBATE_TYPE","BS_167","${rst.REBATE_TYPE}","");  
	</script>
</html>

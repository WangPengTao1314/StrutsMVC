<!--  
/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript
		src="${ctx}/pages/drp/distributer/distributerendreq/DistributerEndReq_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>加盟商终止合作申请</title>
	 
</head>
<body class="bodycss1">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">					
					<input id="back" type="button" class="btn" style="margin-left: 20px;" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<form method="POST" action="#" id="mainForm">
		<input type="hidden" name="selectChann"	value="STATE in( '启用') and DEL_FLAG='0' ">		
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="15%" align="right" class="detail_label">
								申请单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHANN_END_REQ_ID" id="CHANN_END_REQ_ID"
									name="CHANN_END_REQ_ID" type="hidden"
									value="${rst.CHANN_END_REQ_ID}"   />
									
							    <input json="CHANN_END_REQ_NO" name="CHANN_END_REQ_NO" id="CHANN_END_REQ_NO"
							        type="text" autocheck="true" label="申请单号" inputtype="string" mustinput="true"
									maxlength="32" 
									<c:if test="${not empty rst.CHANN_END_REQ_NO}">value="${rst.CHANN_END_REQ_NO}"</c:if>
									<c:if test="${empty rst.CHANN_END_REQ_NO}">value="自动生成"</c:if>
									READONLY/>
							</td>
							<td width="15%" align="right" class="detail_label">
								申请时间:
							</td>
							<td width="35%" class="detail_content">
							      <input json="REQ_DATE" name="REQ_DATE" id="REQ_DATE" type="text" label="申请时间" 									 
									<c:if test="${not empty rst.CHANN_END_REQ_NO}">value="${rst.REQ_DATE}"</c:if>
									<c:if test="${empty rst.CHANN_END_REQ_NO}">value="自动生成"</c:if>
									readonly/>
								  
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}">																													
								<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
											seltarget="selJGXX" autocheck="true" mustinput="true" maxlength="32" label="渠道编号"
											value="${rst.CHANN_NO}" >
								<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_187', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE,CHANN_PERSON_CON,CHANN_MOBILE,JOIN_DATE,CHANN_TYPE,WAREA_ID,WAREA_NO,WAREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,TERM_NAMES', 
										'CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE,PERSON_CON,MOBILE,JOIN_DATE,CHANN_TYPE,AREA_ID_P,AREA_NO_P,AREA_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,TERM_NAMES', 
										'selectChann');">
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" label="渠道名称"  value="${rst.CHANN_NAME}" readonly />													
							</td>
						</tr>

						<tr>	
							<td width="15%" align="right" class="detail_label">
								渠道类型：
							</td>
							<td width="35%" class="detail_content">								
								<input type="text" json="CHANN_TYPE" id="CHANN_TYPE" name="CHANN_TYPE" label="渠道类型"  value="${rst.CHANN_TYPE}" readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								加盟日期：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="JOIN_DATE" id="JOIN_DATE" name="JOIN_DATE" autocheck="true" inputtype="string" label="加盟日期"  value="${rst.JOIN_DATE}"  readonly />								
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道联系人：
							</td>
							<td width="35%" class="detail_content">
									<input json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" type="text" 
										autocheck="true" label="渠道联系人" inputtype="String" 
										maxlength="30" value="${rst.CHANN_PERSON_CON }">
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道联系电话：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" id="CHANN_MOBILE" name="CHANN_MOBILE" json="CHANN_MOBILE"  autocheck="true"
								 value="${rst.CHANN_MOBILE}"   label="渠道联系电话"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								联系人：
							</td>
							<td width="35%" class="detail_content">
									<input json="PERSON_CON" name="PERSON_CON" type="text" 
										autocheck="true" label="联系人" inputtype="String" mustinput="true"
										maxlength="30" value="${rst.PERSON_CON }">
							</td>
							<td width="15%" align="right" class="detail_label">
								联系电话(座机)：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" id="TEL" name="TEL" json="TEL"  autocheck="true"
								 value="${rst.TEL}"   label="联系电话"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								手机：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" id="MOBILE" name="MOBILE" json="MOBILE"  autocheck="true"
								 value="${rst.MOBILE}"   label="手机"  maxlength="30"/>
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								传真：
							</td>
							<td width="35%" class="detail_content">
								<input name="TAX" type="text" autocheck="true" label="传真"
										id="TAX" inputtype="string" maxlength="30" json="TAX"
										value="${rst.TAX}">
							</td>
						</tr>
						<tr>
						
						</tr>
							<td width="15%" align="right" class="detail_label">
								所属战区编号：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="WAREA_ID" json="WAREA_ID" name="WAREA_ID" value="${rst.WAREA_ID}">		
								<input type="text" json="WAREA_NO" id="WAREA_NO" name="WAREA_NO" label="战区"  value="${rst.WAREA_NO}" readonly />								
							</td>
							<td width="15%" align="right" class="detail_label">
								所属战区名称：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="WAREA_NAME" id="WAREA_NAME" name="WAREA_NAME" label="战区"  value="${rst.WAREA_NAME}" readonly />
							</td>
						<tr>							
							<td width="15%" align="right" class="detail_label">
								营销经理:
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" label="区域经理ID" json="AREA_MANAGE_ID"  value="${rst.AREA_MANAGE_ID}"/>								
								<input type="text" id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" label="营销经理"  json="AREA_MANAGE_NAME"  value="${rst.AREA_MANAGE_NAME}"   readonly/>																					
							</td>
							<td width="15%" align="right" class="detail_label">
								营销经理电话：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="AREA_MANAGE_TEL" id="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" label="战区"  value="${rst.AREA_MANAGE_TEL}" readonly />
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								终端信息：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								<input id="TERM_NAMES" json="TERM_NAMES" name="TERM_NAMES" type="text" autocheck="true"
										label="终端信息" inputtype="string" value="${rst.TERM_NAMES }" READONLY>
							</td>																			
						</tr>
						<tr>	
							<td width="15%" align="right" class="detail_label" nowrap>
								保证金余额：
							</td>
							<td width="35%" class="detail_content">
								<input json="DEPOSIT" name="DEPOSIT" type="text"
										autocheck="true" label="保证金余额" inputtype="float" valueType="8,2" mustinput="true"
										valueType="chinese:false" value="${rst.DEPOSIT }"/>
							</td>											
							<td width="15%" align="right" class="detail_label">
								账面余额：
							</td>
							<td width="35%" class="detail_content">
								<input json="LEFT_ACCT_AMOUNT" name="LEFT_ACCT_AMOUNT" type="text"
										autocheck="true" label="账面余额" inputtype="float" valueType="8,2" mustinput="true"
										valueType="chinese:false" value="${rst.LEFT_ACCT_AMOUNT }"/>
							</td>												
						</tr>											
						<tr>
							<td width="15%" align="right" class="detail_label">
								退货金额：
							</td>
							<td width="35%" class="detail_content">
								<input json="RETURN_AMOUNT" name="RETURN_AMOUNT" type="text"
										autocheck="true" label="退货金额" inputtype="float" valueType="8,2" mustinput="true"
										valueType="chinese:false" value="${rst.RETURN_AMOUNT }"/>
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								退货清单附件：
							</td>
							<td width="35%" class="detail_content">
								<input json="RETURN_ATT" id="RETURN_ATT" name="RETURN_ATT" type="text" 
										inputtype="string" label="退货清单附件" autocheck="true" value="${rst.RETURN_ATT}" />
							</td>												
						</tr>											
						<tr>
							<td width="15%" align="right" class="detail_label">
								终止合作原因：
							</td>
							<td width="35%" class="detail_content"  colspan="3">
								<textarea json="END_RESON" name="END_RESON" id="END_RESON" inputtype="string" maxlength="250" rows="4" cols="80%" mustinput="true">
								${rst.END_RESON}
								</textarea>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								 <textarea json="REMARK" name="REMARK" inputtype="string" maxlength="250" rows="4" cols="80%">
								 ${rst.REMARK}
								 </textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
   </div>
   
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	uploadFile('RETURN_ATT', 'SAMPLE_DIR', true);
</script> 



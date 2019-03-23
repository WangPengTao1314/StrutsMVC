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
		src="${ctx}/pages/drp/distributer/distributerendreq/DistributerEndReq_Edit_Confirm.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>加盟商终止合作申请(财务确认金额)</title>
	 
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
								${rst.CHANN_END_REQ_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								申请时间:
							</td>
							<td width="35%" class="detail_content">							     
								${rst.REQ_DATE}								  
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}">																													
								${rst.CHANN_NO}								
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.CHANN_NAME}													
							</td>
						</tr>

						<tr>	
							<td width="15%" align="right" class="detail_label">
								渠道类型：
							</td>
							<td width="35%" class="detail_content">								
								${rst.CHANN_TYPE}				
							</td>
							<td width="15%" align="right" class="detail_label">
								加盟日期：
							</td>
							<td width="35%" class="detail_content">								
								${rst.JOIN_DATE}								
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道联系人：
							</td>
							<td width="35%" class="detail_content">									
								${rst.CHANN_PERSON_CON}
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道联系电话：
							</td>
							<td width="35%" class="detail_content">
								${rst.CHANN_MOBILE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								联系人：
							</td>
							<td width="35%" class="detail_content">
								${rst.PERSON_CON }
							</td>
							<td width="15%" align="right" class="detail_label">
								联系电话(座机)：
							</td>
							<td width="35%" class="detail_content">
								${rst.TEL}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								手机：
							</td>
							<td width="35%" class="detail_content">
								${rst.MOBILE}
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								传真：
							</td>
							<td width="35%" class="detail_content">
								${rst.TAX}
							</td>
						</tr>
						<tr>
						
						</tr>
							<td width="15%" align="right" class="detail_label">
								所属战区编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.WAREA_NO}							
							</td>
							<td width="15%" align="right" class="detail_label">
								所属战区名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.WAREA_NAME}
							</td>
						<tr>							
							<td width="15%" align="right" class="detail_label">
								营销经理:
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_NAME}													
							</td>
							<td width="15%" align="right" class="detail_label">
								营销经理电话：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_TEL}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								终端信息：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								${rst.TERM_NAMES }
							</td>																			
						</tr>
						<tr>	
							<td width="15%" align="right" class="detail_label" nowrap>
								保证金余额：
							</td>
							<td width="35%" class="detail_content">
								${rst.DEPOSIT }
							</td>				
							<td width="15%" align="right" class="detail_label">
								保证金余额<a style="color: red;font-weight: normal;">[实际金额]</a>：
							</td>
							<td width="35%" class="detail_content">
							    <c:choose>  
							    <c:when test="${pvg.PVG_DEPOSIT eq 1 }">
							       <input json="DEPOSIT_CONFIRM" name="DEPOSIT_CONFIRM" type="text"
										autocheck="true" label="保证金余额确认" inputtype="float" valueType="8,2" mustinput="true"
										valueType="chinese:false" value="${rst.DEPOSIT_CONFIRM }"/>
 							    </c:when>  
							    <c:otherwise>  
							       <input json="DEPOSIT_CONFIRM" name="DEPOSIT_CONFIRM" type="text"
										autocheck="true" label="保证金余额确认" inputtype="float" valueType="8,2"  
										valueType="chinese:false" value="${rst.DEPOSIT_CONFIRM }" READONLY/>
 							    </c:otherwise>  
							   </c:choose>
							</td>												
						</tr>
						<tr>	
							<td width="15%" align="right" class="detail_label" nowrap>
								账面余额：
							</td>
							<td width="35%" class="detail_content">
								${rst.LEFT_ACCT_AMOUNT }
							</td>				
							<td width="15%" align="right" class="detail_label">
								账面余额<a style="color: red;font-weight: normal;">[实际金额]</a>：
							</td>
							<td width="35%" class="detail_content">
							 <c:choose>  
							    <c:when test="${pvg.PVG_LEFT_ACCT_AMOUNT eq 1 }">
							       <input json="LEFT_ACCT_AMOUNT_CONFIRM" name="LEFT_ACCT_AMOUNT_CONFIRM" type="text"
										autocheck="true" label="账面余额确认" inputtype="float" valueType="8,2" mustinput="true"
										valueType="chinese:false" value="${rst.LEFT_ACCT_AMOUNT_CONFIRM }"/>
 							    </c:when>  
							    <c:otherwise>  
							       <input json="LEFT_ACCT_AMOUNT_CONFIRM" name="LEFT_ACCT_AMOUNT_CONFIRM" type="text"
										autocheck="true" label="账面余额确认" inputtype="float" valueType="8,2" 
										valueType="chinese:false" value="${rst.LEFT_ACCT_AMOUNT_CONFIRM }" READONLY/>
 							    </c:otherwise>  
							   </c:choose>
							</td>												
						</tr>										
						<tr>
							<td width="15%" align="right" class="detail_label">
								退货金额：
							</td>
							<td width="35%" class="detail_content">
								${rst.RETURN_AMOUNT }
							</td>
							<td width="15%" align="right" class="detail_label">
								退货金额<a style="color: red;font-weight: normal;">[实际金额]</a>：
							</td>
							<td width="35%" class="detail_content">
						      <c:choose>  
							    <c:when test="${pvg.PVG_RETURN_AMOUNT eq 1 }">
							       <input json="RETURN_AMOUNT_CONFIRM" name="RETURN_AMOUNT_CONFIRM" type="text"
										autocheck="true" label="退货金额确认" inputtype="float" valueType="8,2" mustinput="true"
										valueType="chinese:false" value="${rst.RETURN_AMOUNT_CONFIRM }"/>
 							    </c:when>  
							    <c:otherwise>  
							      <input json="RETURN_AMOUNT_CONFIRM" name="RETURN_AMOUNT_CONFIRM" type="text"
										autocheck="true" label="退货金额确认" inputtype="float" valueType="8,2"  
										valueType="chinese:false" value="${rst.RETURN_AMOUNT_CONFIRM }" READONLY/>
 							    </c:otherwise>  
							   </c:choose>
							</td>												
						</tr>
						<tr>							
							<td width="15%" align="right" class="detail_label" nowrap>
								退货清单附件：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								<input type="hidden" id ="RETURN_ATT" value='${rst.RETURN_ATT}' />
							</td>												
						</tr>											
						<tr>
							<td width="15%" align="right" class="detail_label">
								终止合作原因：
							</td>
							<td width="35%" class="detail_content"  colspan="3">
								${rst.END_RESON}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								 ${rst.REMARK}
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
	displayDownFile('RETURN_ATT','SAMPLE_DIR',true,false);
</script> 



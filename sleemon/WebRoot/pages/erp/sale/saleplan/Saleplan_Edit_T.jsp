<!--  
/**
 * @module 营销管理-销售指标管理
 * @func   渠道销售指标设定维护
 * @version 1.1
 * @author zcx
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
		<script type="text/javascript" src="${ctx}/pages/erp/sale/saleplan/Saleplan_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>渠道销售指标设定维护编辑</title>
	</head>
	<body class="bodycss1" onload="getRate()">
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
						<tr>
						<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
						<tr>
							<td width="15%" align="right" class="detail_label" nowrap>
								渠道销售指标设定编号：
							</td>
							<td width="35%" class="detail_content">	
							    <input json="SALE_PLAN_ID" name="SALE_PLAN_ID" id="SALE_PLAN_ID" type="hidden" value="${rst.SALE_PLAN_ID}" /> 												
								<input json="SALE_PLAN_NO" name="SALE_PLAN_NO" autocheck="true" label="开业大礼包申请单号" mustinput="true"  type="text"  inputtype="string"  
		                        readonly size="40"   maxlength="30" 
		                        <c:if test="${rst.SALE_PLAN_NO==null}"> value="自动生成"</c:if>
		                     	<c:if test="${rst.SALE_PLAN_NO!=null}">value="${rst.SALE_PLAN_NO}"</c:if>
		                        />
								
							</td>
							<td width="15%" align="right" class="detail_label">渠道名称：</td>
							<td width="35%" class="detail_content">
							  <input id="selectParamsTt" name="selectParamsTt" type="hidden"     value="">
						      <input id="CHANN_ID"     name="CHANN_IDT"     json="CHANN_ID"   value="${rst.CHANN_ID}"   type="hidden"/>
		                      <input id="CHANN_NO"     name="CHANN_NOT"     json="CHANN_NO"   value="${rst.CHANN_NO}"   type="hidden"/>
		                      <input id="CHANN_ABBR"   name="CHANN_ABBR"   json="CHANN_ABBR" value="${rst.CHANN_ABBR}" type="hidden" /> 
		                      <input id="CHANN_TYPE"   name="CHANN_TYPE"   json="CHANN_TYPE" value="${rst.CHANN_TYPE}" type="hidden"/>
		                      <input id="CHAA_LVL"     name="CHAA_LVL"     json="CHAA_LVL"   value="${rst.CHAA_LVL}"   type="hidden"/>
	                          <input json="CHANN_NAME" name="CHANN_NAMET" autocheck="true" label="加盟商名称"  type="text" readonly  inputtype="string"  size="40"   maxlength="100"  value="${rst.CHANN_NAME}" mustinput="true"/> 
	                          <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
								onClick="selCommon('BS_159', false, 'CHANN_IDT', 'CHANN_ID', 'forms[0]','CHANN_IDT,CHANN_NOT,CHANN_NAMET,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_IDT,AREA_NOT,AREA_NAME','CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_ID_P,AREA_NO_P,AREA_NAME_P', 'selectParamsTt')"> 				
		 					</td>
						</tr>
						<tr>
						<td width="15%" align="right" class="detail_label">
								区域经理名称：
							</td>
							<td width="35%" class="detail_content">
							    <input json="AREA_MANAGE_ID" name="AREA_MANAGE_ID" id="AREA_MANAGE_ID" type="hidden" label="区域经理ID" value="${rst.AREA_MANAGE_ID}"/>
								<input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME"
									type="text" label="区域经理名称" size="40" value="${rst.AREA_MANAGE_NAME}"  readonly/>
							</td>
		 					<td width="15%" align="right" class="detail_label">
						          战区名称
							</td>
							<td width="35%" class="detail_content">
							    <input id="AREA_ID"     name="AREA_IDT"        json="AREA_ID"   value="${rst.AREA_ID}"   type="hidden"/>
	                            <input id="AREA_NO"     name="AREA_NOT"        json="AREA_NO"   value="${rst.AREA_NO}"  type="hidden"/> 
	                            <input id="AREA_NAME"   name="AREA_NAME"      json="AREA_NAME" type="text" autocheck="true" label="战区名称"   readonly  inputtype="string" size="40"   maxlength="30"  value="${rst.AREA_NAME}"/>				
							</td>
						</tr>
					    <tr>
						<td width="15%" align="right" class="detail_label">
							计划年份：
						</td>
						<td width="35%" class="detail_content">
						   <select name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" value="${rst.PLAN_YEAR}" style="width:275px;" autocheck="true" inputtype="string" onchange="getRate()">
				           <c:forEach items="${list}" var="list">    							
					       <c:choose>
					          <c:when test="${list eq rst.PLAN_YEAR}">
					              <option selected="selected" value="${list}">${list}</option>    	
					          </c:when>
					          <c:otherwise>
					              <option value="${list}">${list}</option>    	
					          </c:otherwise>
					        </c:choose>  						
					      </c:forEach>
				       </select>	
						</td>
					<td width="15%" align="right" class="detail_label">
							年度指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="YEAR_PLAN_AMOUNTL" name="YEAR_PLAN_AMOUNT" json="YEAR_PLAN_AMOUNT" autocheck="true" inputtype="string" value="${rst.YEAR_PLAN_AMOUNT}" size="40" onblur="getRate()" />
				    </td>
				</tr>
				<tr>
				    <td width="15%" align="right" class="detail_label">
						一月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="JAN_AMOUNT" name="JAN_AMOUNT" json="JAN_AMOUNT"  autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.JAN_AMOUNT}" size="40" onblur="getFirtstAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						二月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="FEB_AMOUNT" name="FEB_AMOUNT" json="FEB_AMOUNT"  autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.FEB_AMOUNT}" size="40" onblur="getFirtstAmount()"/>
				    </td>
				</tr>
				<tr> 
				    <td width="15%" align="right" class="detail_label">
						三月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="MAR_AMOUNT" name="MAR_AMOUNT" json="MAR_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.MAR_AMOUNT}" size="40" onblur="getFirtstAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						第一季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="FIRST_QUARTER_AMOUNT" name="FIRST_QUARTER_AMOUNT" json="FIRST_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.FIRST_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
			   </tr>
			   <tr>
			        <td width="15%" align="right" class="detail_label">
						四月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="APR_AMOUNT" name="APR_AMOUNT" json="APR_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.APR_AMOUNT}" size="40" onblur="getSecondAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						五月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="MAY_AMOUNT" name="MAY_AMOUNT" json="MAY_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.MAY_AMOUNT}" size="40" onblur="getSecondAmount()"/>
				    </td>
			   </tr>
			   <tr>
			        <td width="15%" align="right" class="detail_label">
						六月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="JUN_AMOUNT" name="JUN_AMOUNT" json="JUN_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.JUN_AMOUNT}" size="40" onblur="getSecondAmount()"/>
				    </td>
			        <td width="15%" align="right" class="detail_label">
						第二季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="SECOND_QUARTER_AMOUNT" name="SECOND_QUARTER_AMOUNT" json="SECOND_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.SECOND_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
			   </tr>
			   <tr>
			        <td width="15%" align="right" class="detail_label">
						七月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="JUL_AMOUNT" name="JUL_AMOUNT" json="JUL_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.JUL_AMOUNT}" size="40" onblur="getThirdAmount()"/>
				    </td>
			       <td width="15%" align="right" class="detail_label">
						八月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="AUG_AMOUNT" name="AUG_AMOUNT" json="AUG_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.AUG_AMOUNT}" size="40" onblur="getThirdAmount()"/>
				    </td>
			   </tr>
			   <tr>
			        <td width="15%" align="right" class="detail_label">
						九月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="SEP_AMOUNT" name="SEP_AMOUNT" json="SEP_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.SEP_AMOUNT}" size="40" onblur="getThirdAmount()"/>
				    </td>
			        <td width="15%" align="right" class="detail_label">
						第三季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="THIRD_QUARTER_AMOUNT" name="THIRD_QUARTER_AMOUNT" json="THIRD_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.THIRD_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
			     </tr>
			     <tr>
			        <td width="15%" align="right" class="detail_label">
						十月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="OCT_AMOUNT" name="OCT_AMOUNT" json="OCT_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.OCT_AMOUNT}" size="40" onblur="getFourthAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						十一月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="NOV_AMOUNT" name="NOV_AMOUNT" json="NOV_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.NOV_AMOUNT}" size="40" onblur="getFourthAmount()"/>
				    </td>
			   </tr>
			   <tr>
			        <td width="15%" align="right" class="detail_label">
						十二月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="DEC_AMOUNT" name="DEC_AMOUNT" json="DEC_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.DEC_AMOUNT}" size="40" onblur="getFourthAmount()"/>
				    </td>
			        <td width="15%" align="right" class="detail_label">
						第四季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="FOURTH_QUARTER_AMOUNT" name="FOURTH_QUARTER_AMOUNT" json="FOURTH_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.FOURTH_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
			   </tr>
				<tr>
				<td width="15%" align="right" class="detail_label">
						备注：
					</td>
					<td width="35%" class="detail_content" colspan="3">
					    <textarea rows="2" cols="32%" id="REMARK" name="REMARK" json="REMARK" autocheck="true" inputtype="string"   maxlength="250">${rst.REMARK}</textarea>
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

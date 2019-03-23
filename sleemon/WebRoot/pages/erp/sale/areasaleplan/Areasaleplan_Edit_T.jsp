<!--  
/**
 * @module 营销管理-销售指标管理
 * @func   区域销售指标设定维护
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
		<script type="text/javascript" src="${ctx}/pages/erp/sale/areasaleplan/Areasaleplan_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>区域销售指标设定维护编辑</title>
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
						<input type="hidden" value="${isNew}" id="isNew" name="isNew">
						<input type="hidden" value="${rst.STATE}" id="state">
						<input type="hidden" name="selectParams" />
						<input type="hidden" name="selectArea" value=" RYZT='启用' and DELFLAG=0 and JGXXID='${JGXXID}' ">
						<input type="hidden" name="selparamsByYear"/>
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										 <table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			<tr>
				<td width="15%" align="right" class="detail_label">
					区域编号：
				</td>
				<td width="35%" class="detail_content">
				    <input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" value="${rst.AREA_ID}" label="区域ID"/>
				    <input json="AREA_NO" name="AREA_NO" id="AREA_NO"
				    type="text" size="40"  value="${rst.AREA_NO}"
				    label="区域编号" readonly  autocheck="true"  inputtype="string" mustinput="true" />
				    <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
					     onClick="selCommon('BS_138', false, 'AREA_NO', 'AREA_NO', 'forms[0]','AREA_ID,AREA_NO,AREA_NAME,WAREA_ID,WAREA_NO,WAREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME', 'AREA_ID,AREA_NO,AREA_NAME,AREA_ID_P,AREA_NO_P,AREA_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME', 'selectParams')">
				</td>
				<td width="15%" align="right" class="detail_label">
				    区域名称
				</td>
				<td width="35%" class="detail_content">
				     <input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME"  value="${rst.AREA_NAME}"
						type="text" size="40" value="区域名称" readonly/>
				</td>
			</tr>
			
			<tr>
				<td width="15%" align="right" class="detail_label">
					战区编号：
				</td>
				<td width="35%" class="detail_content">
				    <input json="WAREA_ID" name="WAREA_ID" id="WAREA_ID" type="hidden" value="${rst.WAREA_ID}" label="战区ID"/>
				    <input json="WAREA_NO" name="WAREA_NO" id="WAREA_NO"
				    type="text" size="40"  value="${rst.WAREA_NO}"
				    label="战区编号" readonly/>
				</td>
				<td width="15%" align="right" class="detail_label">
				    战区名称
				</td>
				<td width="35%" class="detail_content">
				     <input json="WAREA_NAME" name="WAREA_NAME" id="WAREA_NAME"  value="${rst.WAREA_NAME}"
						type="text" size="40" value="战区名称" readonly/>
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
			</tr>
			<tr>
			 <td width="15%" align="right" class="detail_label">
							年度指标：
					</td>
					<td width="35%" class="detail_content" >
					    <input id="YEAR_PLAN_AMOUNTL" name="YEAR_PLAN_AMOUNT" json="YEAR_PLAN_AMOUNT" autocheck="true" inputtype="string" value="${rst.YEAR_PLAN_AMOUNT}" size="40" onblur="getRate()"/>
				    </td>
			   <td width="15%" align="right" class="detail_label">
						一月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="JAN_AMOUNT" name="JAN_AMOUNT" json="JAN_AMOUNT"  autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.JAN_AMOUNT}" size="40" onblur="getFirtstAmount()"/>
				    </td>
				 
			 </tr>
			 <tr>
			    <td width="15%" align="right" class="detail_label">
						二月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="FEB_AMOUNT" name="FEB_AMOUNT" json="FEB_AMOUNT"  autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.FEB_AMOUNT}" size="40" onblur="getFirtstAmount()"/>
				    </td>
				     <td width="15%" align="right" class="detail_label">
						三月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="MAR_AMOUNT" name="MAR_AMOUNT" json="MAR_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.MAR_AMOUNT}" size="40" onblur="getFirtstAmount()"/>
				    </td>
				</tr>
				<tr> 
				 <td width="15%" align="right" class="detail_label">
						第一季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="FIRST_QUARTER_AMOUNT" name="FIRST_QUARTER_AMOUNT" json="FIRST_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.FIRST_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						四月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="APR_AMOUNT" name="APR_AMOUNT" json="APR_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.APR_AMOUNT}" size="40" onblur="getSecondAmount()"/>
				    </td>
				 </tr>
			     <tr>
			       <td width="15%" align="right" class="detail_label">
						五月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="MAY_AMOUNT" name="MAY_AMOUNT" json="MAY_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.MAY_AMOUNT}" size="40" onblur="getSecondAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						六月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="JUN_AMOUNT" name="JUN_AMOUNT" json="JUN_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.JUN_AMOUNT}" size="40" onblur="getSecondAmount()"/>
				    </td>
				 </tr>
			     <tr>
			     <td width="15%" align="right" class="detail_label">
						第二季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="SECOND_QUARTER_AMOUNT" name="SECOND_QUARTER_AMOUNT" json="SECOND_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.SECOND_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						七月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="JUL_AMOUNT" name="JUL_AMOUNT" json="JUL_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.JUL_AMOUNT}" size="40" onblur="getThirdAmount()"/>
				    </td>
			       
				  </tr>
			      <tr>
			      <td width="15%" align="right" class="detail_label">
						八月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="AUG_AMOUNT" name="AUG_AMOUNT" json="AUG_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.AUG_AMOUNT}" size="40" onblur="getThirdAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						九月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="SEP_AMOUNT" name="SEP_AMOUNT" json="SEP_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.SEP_AMOUNT}" size="40" onblur="getThirdAmount()"/>
				    </td>
				  </tr>
			      <tr>
			      <td width="15%" align="right" class="detail_label">
						第三季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="THIRD_QUARTER_AMOUNT" name="THIRD_QUARTER_AMOUNT" json="THIRD_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.THIRD_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						十月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="OCT_AMOUNT" name="OCT_AMOUNT" json="OCT_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.OCT_AMOUNT}" size="40" onblur="getFourthAmount()"/>
				    </td>
				 </tr>
			     <tr>
			     <td width="15%" align="right" class="detail_label">
						十一月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="NOV_AMOUNT" name="NOV_AMOUNT" json="NOV_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.NOV_AMOUNT}" size="40" onblur="getFourthAmount()"/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
						十二月份指标：
					</td>
					<td width="35%" class="detail_content">
					    <input id="DEC_AMOUNT" name="DEC_AMOUNT" json="DEC_AMOUNT" autocheck="true" inputtype="float" maxlength="12" valueType="10,2" value="${rst.DEC_AMOUNT}" size="40" onblur="getFourthAmount()"/>
				    </td>
			    </tr>
			    <tr>
			       <td width="15%" align="right" class="detail_label">
						第四季度累计：
					</td>
					<td width="35%" class="detail_content">
					    <input id="FOURTH_QUARTER_AMOUNT" name="FOURTH_QUARTER_AMOUNT" json="FOURTH_QUARTER_AMOUNT" autocheck="true" inputtype="string" value="${rst.FOURTH_QUARTER_AMOUNT}" size="40" READONLY/>
				    </td>
				    <td width="15%" align="right" class="detail_label">
							状态：
					</td>
					<td width="35%" class="detail_content" >
					    <input id="STATE" name="STATE" json="STATE" autocheck="true" inputtype="string" value="${STATE}" size="40" READONLY/>
				    </td>
			   </tr>
			<tr>
				<td width="15%" align="right" class="detail_label">
					备注：
				</td>
				<td width="35%" class="detail_content" colspan="3">
				    <textarea rows="5" cols="32%" id="REMARK" name="REMARK" json="REMARK" autocheck="true" inputtype="string"   maxlength="250">${rst.REMARK}</textarea>
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

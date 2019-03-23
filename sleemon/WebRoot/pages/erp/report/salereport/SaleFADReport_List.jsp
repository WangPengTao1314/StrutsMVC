<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/report/salereport/SaleReport_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		li{
			float: left;
			width: 18%;
			font-size: 13px;
			height: 30px;
			line-height: 30px;
			text-align: center;
			border: ridge 1px white;
		}
		.li1{
			background-color: #F4CC87;
		}
		.li2{
			background-color: #F1F4FB;
		}
	</style>
</head>
<body >
<table width="99.8%" height="20px;" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：报表中心&gt;&gt;报表管理&gt;&gt;财务报表</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
</table>
<div style="height: 10px;width: 100%;background-color: #4F69A2;">
</div>
<div>
	<input type="hidden" id="RUNQIAN_REPORT_URL" value="${RUNQIAN_REPORT_URL}">
	<input type="hidden" id="SLEEMON_REPORT_URL" value="${SLEEMON_REPORT_URL}">
	<ul>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_FAD eq 1}">
			<a 
					href="#" onclick="openUrl('应收余额查询','querypaymentrep.shtml?action=toFrames','0')" style="color: black;font-weight: normal;"
			>
				应收余额查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_FAD ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		
		<li class="li1">&nbsp;
			<c:if test="${pvg.PVG_CREDIT_BWS eq 1}">
		    <%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
		       <a href="#" onclick="openUrl('信用查询','report/raq.shtml?action=toFrame&rptConFile=newCreditCon&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				信用查询
			</a>
		    <%}else{ %>
		     <a href="#" onclick="openUrl('信用查询','report/raq.shtml?action=toFrame&rptConFile=creditCon&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				信用查询
			 </a>
		    <%} %>
			
			</c:if>
			<c:if test="${pvg.PVG_CREDIT_BWS ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_CONTRACT_IMPLEMENT eq 1}">
			<a href="#" onclick="openUrl('合同执行情况表','report/raq.shtml?action=toFrame&rptConFile=queryContract&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				合同执行情况表
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_CONTRACT_IMPLEMENT ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_CLAUSE eq 1}">
			<a href="#" onclick="openUrl('收款情况查询','report/raq.shtml?action=toFrame&rptConFile=queryClause&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				收款情况查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_CLAUSE ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_REBATES eq 1}">
			<a href="#" onclick="openUrl('返款情况查询','report/raq.shtml?action=toFrame&rptConFile=queryRebates&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				返款情况查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_REBATES ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		
	</ul>
		<ul>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_INVOC_ACCT eq 1}">
			<a href="#" onclick="openUrl('渠道财务进销存','report/raq.shtml?action=toFrame&rptConFile=queryInvocAcct&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				渠道财务进销存
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_INVOC_ACCT ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
		 <c:if test="${pvg.CREDIT_ACCT_OUT eq 1}">
            <a href="#" onclick="openUrl('信用流水账查询','report/raq.shtml?action=toFrame&rptConFile=toCreditAcctOutResult&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				信用流水账查询
			</a>
		 </c:if>
		 <c:if test="${pvg.CREDIT_ACCT_OUT ne 1}">
		  <a href="#">&nbsp;</a>
		 </c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.CREDIT_FREEZE eq 1}">
			   <a href="#" onclick="openUrl('冻结信用查询','report/raq.shtml?action=toFrame&rptConFile=toCreditFreeze&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
					冻结信用查询
				</a>
			</c:if>
			<c:if test="${pvg.CREDIT_FREEZE ne 1}">
			  <a href="#">&nbsp;</a>
		    </c:if>
		</li>
		<li class="li1"> 
		     <c:if test="${pvg.REBATE_FREEZE eq 1}">
			   <a href="#" onclick="openUrl('返利订单扣除信用查询','report/raq.shtml?action=toFrame&rptConFile=toRebateFreeze&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
					返利订单扣除信用查询
				</a>
			</c:if>
			<c:if test="${pvg.REBATE_FREEZE ne 1}">
			  <a href="#">&nbsp;</a>
		    </c:if>
		</li>
		<li class="li1"> 
		     <c:if test="${pvg.PVG_ACCOUNTS_BWS eq 1}">
			   <a href="#" onclick="openUrl('总部对账单查询','report/raq.shtml?action=toFrame&rptConFile=toAccountReport&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
					总部对账单查询
				</a>
			</c:if>
			<c:if test="${pvg.PVG_ACCOUNTS_BWS ne 1}">
			  <a href="#">&nbsp;</a>
		    </c:if>
		</li>
	</ul>
	<ul>
		<li class="li1"> 
		     <c:if test="${pvg.PVG_ANNUALREBATE_BWS eq 1}">
			   <a href="#" onclick="openUrl('年度返利汇总报表','report/raq.shtml?action=toFrame&rptConFile=toAnnualRebateReport&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
					年度返利汇总报表
				</a>
			</c:if>
			<c:if test="${pvg.PVG_ANNUALREBATE_BWS ne 1}">
			  <a href="#">&nbsp;</a>
		    </c:if>
		</li>
		<li class="li1"> 
			&nbsp;
		</li>
		<li class="li1"> 
			&nbsp;
		</li>
		<li class="li1"> 
			&nbsp;
		</li>
		<li class="li1"> 
			&nbsp;
		</li>
	</ul>
</div>
<form action="#" method="post" id="queryForm" >
	<input type="hidden" name="S_USER_ID" id="S_USER_ID" value="${XTYHID}">
	<input type="hidden" name="S_ZTXXID" id="S_ZTXXID" value="${ZTXXID}">
	<input type="hidden" name="S_GOTO_FLG" id="S_GOTO_FLG" value="true"/>
</form>
</body>

<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<%@ include file="/pages/common/uploadfile/importfile.jsp"%>
</html>
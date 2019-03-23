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
	<script type="text/javascript" src="${ctx}/pages/drp/report/financialList/Drp_Financial_List.js"></script>
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
			font-weight: bold;
			color: #CDC8B1;
		}
		.li1{
			background-color: #F4CC87;
		}
		.li2{
			background-color: #F4CC87;
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
			<c:if test="${pvg.PVG_CREDIT_BWS eq 1}">
			<%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
			<a href="#" onclick="openUrl('信用报表','report/raq.shtml?action=toFrame&rptConFile=newCreditCon&ZTXXID=${ZTXXID}&backPath=','0')" style="color: black;font-weight: normal;">
			   信用报表
			</a>
			<%}else{ %>
			  <a href="#" onclick="openUrl('信用报表','report/raq.shtml?action=toFrame&rptConFile=creditCon&ZTXXID=${ZTXXID}&backPath=','0')" style="color: black;font-weight: normal;">信用报表</a>
			<%} %>
				
			</c:if>
			<c:if test="${pvg.PVG_CREDIT_BWS ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_INVOC_ACCT eq 1}">
				<a href="#" onclick="openUrl('财务进销存表','report/raq.shtml?action=toFrame&rptConFile=queryInvocAcct&ZTXXID=${ZTXXID}&backPath=','0')" style="color: black;font-weight: normal;">财务进销存表</a>
			</c:if>
			<c:if test="${pvg.PVG_INVOC_ACCT ne 1}">
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
 
		<li class="li2">
			<c:if test="${pvg.PVG_BWS_REBATE eq 1}">
			<a href="#" onclick="openUrl('返利抵扣查询','report/raq.shtml?action=toFrame&rptConFile=queryRrebate&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				返利抵扣查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_REBATE ne 1}">
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
	</ul>
	<ul>
		<li class="li1">
			<c:if test="${pvg.PVG_STORE_BWS eq 1}">
			<a                  							   
					href="#" onclick="openUrl('门店销售统计表(成本)','report/raq.shtml?action=toFrame&rptConFile=FDsaleCountCon','0')" style="color: black;font-weight: normal;"
			>
				&nbsp;&nbsp;&nbsp;门店销售统计表(成本)
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_PRODUCE ne 1}">	
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			 <c:if test="${pvg.PVG_BWS_TERM_SALEOUT eq 1}">
			    <a href="#" onclick="openUrl('门店发货统计表(供货价)','report/raq.shtml?action=toFrame&rptConFile=termSaleSendcount&COST_FLAG=1','0')" style="color: black;font-weight: normal;">
					门店发货统计表(供货价)
				</a>
			 </c:if>
			 <c:if test="${pvg.PVG_BWS_TERM_SALEOUT ne 1}">
			    <a href="#">&nbsp;</a>
			 </c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_BWS_STORE eq 1}">
			<a href="#" onclick="openUrl('库存实时查询(供货价)','report/raq.shtml?action=toFrame&rptConFile=toStoreRepertoryCon&COST_FLAG=1','0')" style="color: black;font-weight: normal;">
	         	库存实时查询(供货价)
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_STORE ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_TOP_SALE eq 1}">
			<a href="#" onclick="openUrl('门店销售排名','report/raq.shtml?action=toFrame&rptConFile=toSaleRanking','0')" style="color: black;font-weight: normal;">
	         	门店销售排名
			</a>
			</c:if>
			<c:if test="${pvg.PVG_TOP_SALE ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_ANNUALREBATE_BWS eq 1}">
			   <a href="#" onclick="openUrl('年度返利汇总报表','report/raq.shtml?action=toFrame&rptConFile=toAnnualRebateReport&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
					年度返利汇总报表
				</a>
			</c:if>
			<c:if test="${pvg.PVG_ANNUALREBATE_BWS ne 1}">
			  <a href="#">&nbsp;</a>
		    </c:if>
		</li>
	</ul>
	<ul>
		<li class="li1">&nbsp;
		</li>
		<li class="li2">
			&nbsp;
		</li>
		<li class="li1">
			&nbsp;
		</li>
		<li class="li1">
          <%--<a href="#" onclick="openUrl('信用流水账对照','report/raq.shtml?action=toFrame&rptConFile=toCreditAcctResult&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				信用流水账对照
			</a>
		--%>
		<a href="#">&nbsp;</a>
		</li>
		<li class="li2"> 
		  <%--<c:if test="${pvg.PVG_UNCOMM_BWS eq 1}">	
			<a href="#" onclick="openUrl('待确认预订单','report/raq.shtml?action=toFrame&rptConFile=toUncommAdvc','0')" 
			style="color: black;font-weight: normal;" >
				&nbsp;&nbsp;&nbsp;待确认预订单
			</a>
		 </c:if> 
		<c:if test="${pvg.PVG_UNCOMM_BWS ne 1}">	
				<a href="#">&nbsp;</a>
		</c:if>
		--%>&nbsp;
		</li>
	</ul>
</div>
<form action="#" method="post" id="queryForm" >
	<input type="hidden" name="S_USER_ID" id="S_USER_ID" value="${XTYHID}">
	<input type="hidden" name="S_ZTXXID" id="S_ZTXXID" value="${ZTXXID}">
	<input type="hidden" name="S_GOTO_FLG" id="S_GOTO_FLG" value="true"/>
</form>
</body>

</html>
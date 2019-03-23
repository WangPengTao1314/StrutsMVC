<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author lyg
 * @time   2013-08-11 17:17:29 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
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
			<td height="20px">&nbsp;&nbsp;当前位置：报表中心&gt;&gt;报表管理&gt;&gt;营销报表</td>
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
			<c:if test="${pvg.SALE_PERCEN eq 1}">
			  <a href="#" onclick="openUrl('销量统计比例','report/raq.shtml?action=toFrame&rptConFile=toSalePercentage&backPath=','0')" style="color: black;font-weight: normal;">
					销量统计比例
			  </a>
			</c:if>
			<c:if test="${pvg.SALE_PERCEN ne 1}">
			  <a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_COSS eq 1}">
			  <a href="#" onclick="openUrl('分类销量统计','report/raq.shtml?action=toFrame&rptConFile=toCOSS&backPath=','0')" style="color: black;font-weight: normal;">
					分类销量统计
			  </a>
			</c:if>
			<c:if test="${pvg.PVG_COSS ne 1}">
			  <a href="#">&nbsp;</a>
			</c:if>
		</li>
		 
		<li class="li1">
		 <c:if test="${pvg.PVG_ERP_SALE eq 1}">
		   <a href="#" onclick="openUrl('总部销售报表','report/raq.shtml?action=toFrame&rptConFile=erpSaleReport','0')" 
		      style="color: black;font-weight: normal;">
					总部销售报表
		  </a>
		  </c:if>
		  <c:if test="${pvg.PVG_ERP_SALE ne 1}">
		    <a href="#">&nbsp;</a>
		  </c:if>
		</li>
		<li class="li1"> 
		  <c:if test="${pvg.PVG_DRP_SALE eq 1}">
		  <a href="#" onclick="openUrl('终端门店零售报表','report/raq.shtml?action=toFrame&rptConFile=drpSaleReport','0')" 
		      style="color: black;font-weight: normal;">
					终端门店零售报表
		  </a>
	    </c:if>
	   <c:if test="${pvg.PVG_DRP_SALE ne 1}">
		    <a href="#">&nbsp;</a>
		  </c:if>
		  
		</li>
	</ul>
	<ul>
		<li class="li1">
		 <c:if test="${pvg.PVG_SALE_PLAN_PENC eq 1}">
			<a href="#" onclick="openUrl('销售目标达成率','report/raq.shtml?action=toFrame&rptConFile=salePlanReport','0')" 
		      style="color: black;font-weight: normal;">
					销售目标达成率
		  </a>
		  </c:if>
		  <c:if test="${pvg.PVG_SALE_PLAN_PENC ne 1}">
		    <a href="#">&nbsp;</a>
		  </c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_MONTH_PLAN_PENC eq 1}">
			<a href="#" onclick="openUrl('月度拜访工作计划达成率','report/raq.shtml?action=toFrame&rptConFile=toQueryMonthvisitResult','0')" 
		      style="color: black;font-weight: normal;">
					月度拜访工作计划达成率
		  </a>
		  </c:if>
		  <c:if test="${pvg.PVG_SALE_PLAN_PENC ne 1}">
		    <a href="#">&nbsp;</a>
		  </c:if>
		</li>
		
		<li class="li1">
		   	<c:if test="${pvg.PVG_MONTH_PLAN_PENC eq 1}">
			<a href="#" onclick="openUrl('门店精致化检查分数','report/raq.shtml?action=toFrame&rptConFile=toQueryRCheckResult','0')" 
		      style="color: black;font-weight: normal;">
					门店精致化检查分数
			</a>
			</c:if>
			<c:if test="${pvg.PVG_SALE_PLAN_PENC ne 1}">
			    <a href="#">&nbsp;</a>
			</c:if>
		</li>
		
		<li class="li1">
		  <c:if test="${pvg.WAREA_QUARTEROPEN_PVG eq 1}">
			 <a href="#" onclick="openUrl('各战区有效门店达成数','report/raq.shtml?action=toFrame&rptConFile=toWareaQuarterOpenNumReport','0')" 
		      style="color: black;font-weight: normal;">
					各战区有效门店达成数
		   </a>
		  </c:if>
		  <c:if test="${pvg.WAREA_QUARTEROPEN_PVG ne 1}"> <a href="#">&nbsp;</a></c:if>
		</li>
		<!-- 
		<li class="li1">
		 	<c:if test="${pvg.PVG_MONTH_PLAN_PENC eq 1}">
			 <a href="#" onclick="openUrl('终端门店报表','report/raq.shtml?action=toFrame&rptConFile=toTerminalOpenReport','0')" 
		      style="color: black;font-weight: normal;">
					终端门店报表
		   </a>
		  </c:if>
		  <c:if test="${pvg.WAREA_QUARTEROPEN_PVG ne 1}"> <a href="#">&nbsp;</a></c:if>
		  &nbsp;
		</li>-->
		<li class="li1"> 
		  <c:if test="${pvg.VISIT_TASK_ASSESS_PVG eq 1}">
		    <a href="#" onclick="openUrl('拜访达成考核目标','report/raq.shtml?action=toFrame&rptConFile=toVisitTaskAssessReport','0')" 
		      style="color: black;font-weight: normal;">
					拜访达成考核目标
		   </a>
		   </c:if>
		   <c:if test="${pvg.VISIT_TASK_ASSESS_PVG ne 1}"><a href="#">&nbsp;</a></c:if>
		</li>
		<li class="li1"> 
		   <c:if test="${pvg.MARKET_SALE_PVG eq 1}"> 
			   <a href="#" onclick="openUrl('活动销售报表','report/raq.shtml?action=toFrame&rptConFile=toMarketSaleReport','0')" 
			      style="color: black;font-weight: normal;">
						活动销售报表
			   </a>
		  </c:if>
		  <c:if test="${pvg.MARKET_SALE_PVG ne 1}"> <a href="#">&nbsp;</a> </c:if>
		</li>
	</ul>
	
	
	<ul>
	    <li class="li1"> 
	      <c:if test="${pvg.MARKET_COMM_PVG eq 1}">
		      <a href="#" 
		         onclick="openUrl('活动提成查询报表','report/raq.shtml?action=toFrame&rptConFile=toMarketCommReport','0')" 
			     style="color: black;font-weight: normal;">
						活动提成查询报表
			   </a>
		  </c:if> 
		  <c:if test="${pvg.MARKET_COMM_PVG ne 1}"><a href="#">&nbsp;</a></c:if>
	    </li>
	    <li class="li1">
	       <c:if test="${pvg.PRMT_COST_REQ_PVG eq 1}">
		      <a href="#" 
		         onclick="openUrl('加盟商推广费用使用明细','report/raq.shtml?action=toFrame&rptConFile=toPrmtCostReqReport','0')" 
			     style="color: black;font-weight: normal;">
						加盟商推广费用使用明细
			   </a>
		  </c:if> 
		  <c:if test="${pvg.PRMT_COST_REQ_PVG ne 1}"><a href="#">&nbsp;</a></c:if>
	    </li>
	    <li class="li1"> 
	    <c:if test="${pvg.PRMT_WAREA_FREE eq 1}">
	       <a href="#" 
		         onclick="openUrl('战区推广费','report/raq.shtml?action=toFrame&rptConFile=toWareExtensionFee','0')" 
			     style="color: black;font-weight: normal;">
						战区推广费
		  </a>
		</c:if>
		<c:if test="${pvg.PRMT_WAREA_FREE ne 1}"><a href="#">&nbsp;</a></c:if>
	    </li>
 
	    <li class="li1"> 
	    <c:if test="${pvg.PRMT_AREA_FREE  eq 1}">
	        <a href="#" 
		         onclick="openUrl('区域推广费','report/raq.shtml?action=toFrame&rptConFile=toAreaExtensionFee','0')" 
			     style="color: black;font-weight: normal;">
						区域推广费
		  </a>
		  </c:if>
		  <c:if test="${pvg.PRMT_AREA_FREE  ne 1}"><a href="#">&nbsp;</a></c:if>
	    </li>
 
	    <li class="li1"> 
	       <c:if test="${pvg.PVG_PRMT_COST_REQ eq 1}">
		      <a href="#" 
		         onclick="openUrl('加盟商推广费用报表','report/raq.shtml?action=toFrame&rptConFile=toPrmtCostReport','0')" 
			     style="color: black;font-weight: normal;">
						加盟商推广费用报表
			   </a>
		  </c:if> 
		  <c:if test="${pvg.PVG_PRMT_COST_REQ ne 1}"><a href="#">&nbsp;</a></c:if>
	    </li>
	</ul>
	<ul>
	<li class="li1">
	  <c:if test="${pvg.PVG_CARD_LIST eq 1}">
	    <a href="#" 
          onclick="openUrl('卡券销售清单','report/raq.shtml?action=toFrame&rptConFile=toCardList','0')" 
	      style="color: black;font-weight: normal;">
				卡券销售清单
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_CARD_LIST ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	<li class="li1"> 
	   <c:if test="${pvg.PVG_CHILDCOMP_TRACK eq 1}">
	    <a href="#" 
          onclick="openUrl('直营办零售数据跟踪','report/raq.shtml?action=toFrame&rptConFile=toChildCompSaleTrakingReport','0')" 
	      style="color: black;font-weight: normal;">
				直营办零售数据跟踪
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_CHILDCOMP_TRACK ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	<li class="li1">
	  <c:if test="${pvg.PVG_ADVISE_STATIS eq 1}">
	    <a href="#" 
          onclick="openUrl('每月售后投诉报表','report/raq.shtml?action=toFrame&rptConFile=toAdviseMonthStatisReport','0')" 
	      style="color: black;font-weight: normal;">
				每月售后投诉报表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_ADVISE_STATIS ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	<li class="li1"> 
	   <c:if test="${pvg.PVG_CD_ADVISE_STATIS eq 1}">
	    <a href="#" 
          onclick="openUrl('床垫季/年度售后投诉报表','report/raq.shtml?action=toFrame&rptConFile=toAdviseCDMonthStatisReport','0')" 
	      style="color: black;font-weight: normal;">
				床垫季/年度售后投诉报表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_CD_ADVISE_STATIS ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	<li class="li1">
	  <c:if test="${pvg.PVG_RC_ADVISE_STATIS eq 1}">
	    <a href="#" 
          onclick="openUrl('软床季/年度售后投诉报表','report/raq.shtml?action=toFrame&rptConFile=toAdviseRCMonthStatisReport','0')" 
	      style="color: black;font-weight: normal;">
				软床季/年度售后投诉报表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_RC_ADVISE_STATIS ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	</ul>
	<ul>
	<li class="li1">  
	  <c:if test="${pvg.PVG_CTG_ADVISE_STATIS eq 1}">
	    <a href="#" 
          onclick="openUrl('床头柜季/年度售后投诉报表','report/raq.shtml?action=toFrame&rptConFile=toAdviseCTGMonthStatisReport','0')" 
	      style="color: black;font-weight: normal;">
				床头柜季/年度售后投诉报表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_CTG_ADVISE_STATIS ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
 
	<li class="li1"> 
	 <c:if test="${pvg.PVG_RPT_JOB eq 1}">
	    <a href="#" 
          onclick="openUrl('国内销售民用产品预估表','report/raq.shtml?action=toFrame&rptConFile=toForecastAdvcMonthPrdReport','0')" 
	      style="color: black;font-weight: normal;">
				国内销售民用产品预估表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_RPT_JOB ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	<li class="li1"> 
	  <c:if test="${pvg.PVG_RPT_DIFF eq 1}">
	    <a href="#" 
          onclick="openUrl('偏差率','report/raq.shtml?action=toFrame&rptConFile=toRptChannPrdDiffReport','0')" 
	      style="color: black;font-weight: normal;">
				偏差率
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_RPT_DIFF ne 1}"><a href="#">&nbsp;</a></c:if>
	
	 </li>
	<li class="li1"> 
	    <c:if test="${pvg.PVG_MKM_DAY_STATUS eq 1}">
	    <a href="#" 
          onclick="openUrl('营销经理日报表','report/raq.shtml?action=toFrame&rptConFile=toMkmDayReport','0')" 
	      style="color: black;font-weight: normal;">
				营销经理日报表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_MKM_DAY_STATUS ne 1}"><a href="#">&nbsp;</a></c:if>
	</li>
	<li class="li1"> 
       <c:if test="${pvg.PVG_CHANN_MKM_STATUS eq 1}">
	    <a href="#" 
          onclick="openUrl('加盟商营销经理评价报表','report/raq.shtml?action=toFrame&rptConFile=toChannMkmReport','0')" 
	      style="color: black;font-weight: normal;">
				加盟商营销经理评价报表
	   </a>
	  </c:if>
	  <c:if test="${pvg.PVG_CHANN_MKM_STATUS ne 1}"><a href="#">&nbsp;</a></c:if>
    </li>
   </ul>
   <ul>
	<li class="li1"> &nbsp; </li>
	<li class="li1"> &nbsp; </li>
    <li class="li1"> &nbsp; </li>
    <li class="li1"> &nbsp; </li>
    <li class="li1"> &nbsp; </li>
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
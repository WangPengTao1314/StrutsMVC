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
			<td height="20px">&nbsp;&nbsp;当前位置：报表中心&gt;&gt;报表管理&gt;&gt;销售报表</td>
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
			<c:if test="${pvg.PVG_BWS_PRODUCE eq 1}">
			<a 
					href="#" onclick="openUrl('交付计划生产状态查询','drp/report/queryProStatus.shtml?action=toFrame','0')" style="color: black;font-weight: normal;"
			>
				交付计划生产状态查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_PRODUCE ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
			<c:if test="${pvg.PVG_BWS_RETURN eq 1}">
			<a 
					href="#" onclick="openUrl('退货单跟踪查询','drp/report/queryReutrnRep.shtml?action=toFrame','0')" style="color: black;font-weight: normal;"
			>
				退货单跟踪查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_RETURN ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
		<c:if test="${pvg.PVG_BWS_ADVC_STATISTICAL eq 1}">
			<a 
					href="#" onclick="openUrl('销售订单出货明细','report/raq.shtml?action=toFrame&rptConFile=toSaleOrderSendResult&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;"
			>
				销售订单出货统计明细表
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_ADVC_STATISTICAL ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li2">
		<c:if test="${pvg.PVG_BWS_ADVC eq 1}">
			<a       
					href="#" onclick="openUrl('订货订单信息','report/raq.shtml?action=toFrame&rptConFile=toGoodsorderCon&backPath=','0')" style="color: black;font-weight: normal;"
			>
				订货订单信息
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_ADVC ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
	</ul>
	<ul>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_ADVC_STORE eq 1}">
			<a 
					href="#" onclick="openUrl('销售订单生产状态查询','drp/report/saleProStatus.shtml?action=toFrame','0')" style="color: black;font-weight: normal;"
			>
				销售订单生产状态查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_ADVC_STORE ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
	</ul>
	<ul>
		<li class="li1">
		<c:if test="${pvg.PVG_BWS_RETURN_STATISTICS eq 1}">
			<a 
					href="#" onclick="openUrl('撤店退货统计','report/raq.shtml?action=toFrame&rptConFile=toReturncountCon&backPath=','0')" style="color: black;font-weight: normal;"
			>
				撤店退货统计
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_RETURN_STATISTICS ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_DELIVER eq 1}">
				<a href="#" 
					<c:if test="${IS_NO_DELIVER_PLAN_FLAG ne 1}">
						onclick="openUrl('发货情况统计表','report/raq.shtml?action=toFrame&rptConFile=queryDeliver&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')"
					</c:if>
					 <c:if test="${IS_NO_DELIVER_PLAN_FLAG eq 1}">
						onclick="openUrl('发货情况统计表','report/raq.shtml?action=toFrame&rptConFile=queryNewDeliver&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')"
					</c:if>
					style="color: black;font-weight: normal;">
					发货情况统计表
				</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_DELIVER ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
			<c:if test="${pvg.PVG_BWS_GOODS eq 1}">
			<a href="#" onclick="openUrl('订单发货状态跟踪查询','report/raq.shtml?action=toFrame&rptConFile=queryGoods&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
				订单发货状态跟踪查询
			</a>
			</c:if>
			<c:if test="${pvg.PVG_BWS_GOODS ne 1}">
				<a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
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
			<c:if test="${pvg.CLASSIFY_SALE eq 1}">
			  <a href="#" onclick="openUrl('TOP10产品','report/raq.shtml?action=toFrame&rptConFile=toTop10Product&IS_DRP_LEDGER=${IS_DRP_LEDGER}&backPath=','0')" style="color: black;font-weight: normal;">
					TOP10产品
			  </a>
			</c:if>
			<c:if test="${pvg.CLASSIFY_SALE ne 1}">
			  <a href="#">&nbsp;</a>
			</c:if>
		</li>
	</ul>
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
			<c:if test="${pvg.PVG_OLD_DELIVERY eq 1}">
			  <a href="#" onclick="openUrl('原发运单查询','report/raq.shtml?action=toFrame&rptConFile=toOldDelivery&backPath=','0')" style="color: black;font-weight: normal;">
					原发运单查询
			  </a>
			</c:if>
			<c:if test="${pvg.PVG_OLD_DELIVERY ne 1}">
			  <a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1">
		 <c:if test="${pvg.PVG_MONTH_WORK_PALN eq 1}">
			  <a href="#" onclick="openUrl('月工作计划提交情况','report/raq.shtml?action=toFrame&rptConFile=toMonthWorkPlan&backPath=','0')" style="color: black;font-weight: normal;">
					月工作计划提交情况
			  </a>
			</c:if>
			<c:if test="${pvg.PVG_MONTH_WORK_PALN ne 1}">
			  <a href="#">&nbsp;</a>
			</c:if>
		</li>
		<li class="li1"> 
		&nbsp;
		</li>
	</ul>
	<ul>
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
		<li class="li1">&nbsp;</li>
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
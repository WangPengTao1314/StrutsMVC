<!-- 
 *@module渠道管理-工作计划管理
 *@func  工作计划维护
 *@version 1.1
 *@author zcx
 *-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript    src="${ctx}/pages/drp/oamg/workplanmage/WorkplanMage_Edit.js"></script>
    <script type=text/javascript    src="${ctx}/pages/drp/oamg/workplanmage/WorkplanMage_EditT.js"></script>
    <script type=text/javascript    src="${ctx}/pages/drp/oamg/workplanmage/WorkplanMage_Frame.js"></script>
	<title>工作计划</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>
		当前位置：渠道管理&gt;&gt;工作计划管理&gt;&gt;修改工作计划
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" name="selparamsByYear" />
<input type="hidden" name="selparamsByMonth"/>
<input type="hidden" id="SALE_PLAN_ID" name="SALE_PLAN_ID" value="${SALE_PLAN_ID}"/>
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right" class="detail_label">工作计划编号：</td>
					<td width="35%" class="detail_content">
					        <input json="WORK_PLAN_ID" name="WORK_PLAN_ID" size="40"  id="WORK_PLAN_ID" type="hidden" value="${rst.WORK_PLAN_ID}" />
						    <input json="WORK_PLAN_NO" name="WORK_PLAN_NO" size="40"  id="WORK_PLAN_NO" type="text" autocheck="true" label="工作计划编号" inputtype="string" mustinput="true"
						       readonly <c:if test="${not empty rst.WORK_PLAN_NO}">value="${rst.WORK_PLAN_NO}"</c:if>
									<c:if test="${empty rst.WORK_PLAN_NO}">value="自动生成"</c:if>>
					</td>
					<td width="15%" align="right" class="detail_label">战区编号：</td>
					<td width="35%" class="detail_content">
					        <input type="hidden" name="selectParams"     id="selectParams" />
					        <input id="WAREA_ID"     name="WAREA_ID"     json="WAREA_ID"   value="${rst.WAREA_ID}"   type="hidden"/>
                            <input id="WAREA_NO"     name="WAREA_NO"     json="WAREA_NO"   value="${rst.WAREA_NO}" size="40" label="战区编号"  autocheck="true" inputtype="string"  mustinput="true" readonly/> 
                            <img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_147', false, 'WAREA_NO', 'BMBH', 'forms[0]','WAREA_ID,WAREA_NO,WAREA_NAME', 'BMXXID,BMBH,BMMC', 'selectParams');getRRXX();">				
                  	</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">战区名称：</td>
					<td width="35%" class="detail_content">
					   <input json="WAREA_NAME" name="WAREA_NAME" id="WAREA_NAME" type="text"
									label="战区名称" value="${rst.WAREA_NAME}"
									size="40" readonly/> 
 					</td>
					<td width="15%" align="right" class="detail_label">计划年份：</td>
					<td width="35%" class="detail_content">
				       <select name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" value="${rst.PLAN_YEAR}" style="width:275px;" autocheck="true" inputtype="string" mustinput="true" label="计划年份">
				       </select>
 					</td>
				</tr>
			   <tr>
				<td width="15%" align="right" class="detail_label">
					计划月份： 
				</td>
				<td width="35%" class="detail_content">
				      <select name="PLAN_MONTH" id="PLAN_MONTH" json="PLAN_MONTH"  value="${rst.PLAN_MONTH}" style="width:275px;" autocheck="true" inputtype="string" mustinput="true" label="计划月份">
				      </select>
				</td>
				<td width="15%" align="right" class="detail_label">
					总上报份数：
				</td>
				<td width="35%" class="detail_content">
					<input id="TOTAL_UP_REPORT_NUM" name="TOTAL_UP_REPORT_NUM" json="TOTAL_UP_REPORT_NUM" value="${rst.TOTAL_UP_REPORT_NUM}"
						type="text" size="40"  label = "总上报份数" readonly/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right" class="detail_label">
					备注：
				</td>
				<td width="35%" class="detail_content" colspan="3">
				    <textarea rows="5" cols="32%" id="REMARK" name="REMARK" json="REMARK"  autocheck="true" inputtype="string"   maxlength="250">${rst.REMARK}</textarea>
			    </td>
	         </tr>		
		  </table>
		</td>
	</tr>
</table>
</form>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
<input type="hidden" id="STATE" name="STATE" value="${STATE}"/>
</body>
<script type="text/javascript">
	SelDictShow("PLAN_YEAR","89","${rst.PLAN_YEAR}","");
	SelDictShow("PLAN_MONTH","168","${rst.PLAN_MONTH}","");
</script>
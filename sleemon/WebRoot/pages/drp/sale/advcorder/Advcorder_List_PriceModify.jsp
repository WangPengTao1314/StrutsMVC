<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advcorder/Advcorder_List_PriceModify.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
			   		<input id="save" type="button" class="btn" value="保存(E)" title="Alt+E" accesskey="E">
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
		<form method="POST" action="#" id="mainForm" >
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号&nbsp;-&nbsp;特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_TYPE" >货品类型</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>-->
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊规格说明</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >应收金额</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >交货日期</th>
                     <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>  
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)"  onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));"
					>
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.ADVC_ORDER_DTL_ID}" json="ADVC_ORDER_DTL_ID" name="ADVC_ORDER_DTL_ID" onclick="setEidChecked(this);"/>
					 </td>
					 <input type="hidden" name="IS_FREEZE_FLAG" value="${rst.IS_FREEZE_FLAG}"/>
					 <input type="hidden" name="COUNT" value="${rst.COUNT}"/>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}
                     <c:if test="${rst.DM_SPCL_TECH_NO ne null}">-${rst.DM_SPCL_TECH_NO}</c:if>
                     &nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_SPEC}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>-->
                     <td nowrap="nowrap" align="left" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                     	 <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
                     	 	<c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
                     	 	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}')" />
                     	 </c:if>
                     	 <c:if test="${rst.SPCL_TECH_FLAG eq '0' || rst.SPCL_TECH_FLAG eq null}">
                     	 	无
                     	 </c:if>
                     	&nbsp;
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.PRICE}&nbsp;</td>
                     <input type="hidden" id="PRICE${rr}" value="${rst.PRICE}"/>
                     <input type="hidden" id="ORDER_NUM${rr}" value="${rst.ORDER_NUM}"/>
                     <td nowrap="nowrap" align="right" ><input type="text" value="${rst.DECT_RATE}" size="3" name="DECT_RATE" json="DECT_RATE" id="DECT_RATE${rr}" autocheck="true" label="折扣率" mustinput="true" onkeyup="countRate('${rr}')"   /></td>
                     <td nowrap="nowrap" align="right" ><input type="text" value="${rst.DECT_PRICE}" size="3" name="DECT_PRICE" json="DECT_PRICE" id="DECT_PRICE${rr}" autocheck="true" label="折后单价" mustinput="true" onkeyup="countPrice('${rr}')" /></td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" ><input type="text" value="${rst.PAYABLE_AMOUNT}" size="3" name="PAYABLE_AMOUNT" json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT${rr}" autocheck="true" label="应收金额" mustinput="true" onkeyup="countAmount('${rr}')" /></td>
                     <td nowrap="nowrap" align="center" >${rst.ORDER_RECV_DATE}&nbsp;</td>
                      <td nowrap="nowrap" align="left" >${rst.REMARK}&nbsp;</td>  
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="21" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
			</form>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="ADVC_ORDER_DTL_IDS" name="ADVC_ORDER_DTL_IDS" value=""/>
</form>
</body>
<script type="text/javascript">
	
</script>
</html>
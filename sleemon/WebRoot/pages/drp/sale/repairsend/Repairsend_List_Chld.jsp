<!--
 * @prjName:喜临门营销平台
 * @fileName:Repairsend_List
 * @author chenj
 * @time   2013-11-03 16:25:12 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/repairsend/Repairsend_List_Chld.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
 <tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				<td align="right">
				                     总数量:<span style="margin-right: 20px;" id="totalNum">${totalResult.TOTAL_REPAIR_NUM}</span>
						总金额：<span style="margin-right: 20px;" id="totalAmount">${totalResult.TOTAL_REPAIR_AMOUNT}</span>
						总体积：<span style="margin-right: 20px;" id="totalAmount">${totalResult.TOTAL_VOLUME}</span>
						
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_FLAG" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_PRICE" >供货价</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_NUM" >返修数量</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_AMOUNT" >返修金额</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >体积</th>
                    <th  nowrap="nowrap" align="center" dbname="TOTAL_VOLUME" >总体积</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_RESON" >返修原因</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_ATT" >返修附件</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.REPAIR_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
					  <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
					   	<span  style="color: red">有</span>
                     	<input type="button" class="btn" value="查看" onclick="showSpecTech('${rst.SPCL_TECH_ID}')" />
					  </c:if>
					  <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}">
					       无
					  </c:if>
					 
					 </td>
					 
                     <td nowrap="nowrap" align="right" >${rst.REPAIR_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.REPAIR_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.REPAIR_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.VOLUME} &nbsp; </td>
                     <td nowrap="nowrap" align="right" >${rst.TOTAL_VOLUME} &nbsp; </td>
                     <td nowrap="nowrap" align="left" >${rst.REPAIR_RESON}&nbsp;</td>
                     <td nowrap="nowrap" align="left" ><input type="hidden" id="REPAIR_ATT" value="${rst.REPAIR_ATT}"></td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="16" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="REPAIR_ORDER_DTL_IDS" name="REPAIR_ORDER_DTL_IDS" value=""/>
</form>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script language="JavaScript">
	displayDownFile('REPAIR_ATT','SAMPLE_DIR',true,false);
</script>
</html>
 
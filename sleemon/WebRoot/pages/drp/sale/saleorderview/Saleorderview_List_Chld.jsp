<!--
 * @prjName:喜临门营销平台
 * @fileName:明细列表
 * @author zzb
 * @time   2013-08-30 15:55:09 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/saleorderview/Saleorderview_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" id="resultSize" name="resultSize" value="${resultSize}">
				<tr class="fixedRow">
					<%--<th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>--%>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号&nbsp;-&nbsp;特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="OLD_PRICE" >原价格</th>
                    <th  nowrap="nowrap" align="center" dbname="SENDED_NUM" >已发数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >原订货数量</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  >
					 <td nowrap="nowrap" align="center" json="PRD_NO"  >${rst.PRD_NO}
					 <c:if test="${rst.DM_SPCL_TECH_NO ne null}">-${rst.DM_SPCL_TECH_NO}</c:if>
					 &nbsp;</td>
					 <td nowrap="nowrap" align="left"   json="PRD_NAME" >${rst.PRD_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center" json="PRD_SPEC" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left"   json="PRD_COLOR" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json="BRAND" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json="STD_UNIT" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                      <input type="hidden" id=""  name="IS_CANCELED_FLAG"   value="${rst.IS_CANCELED_FLAG}"/>
                      <input type="hidden" id=""  name="CANCEL_FLAG"   value="${rst.CANCEL_FLAG}"/>
                      
	                     <span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <font color="red"> 有 </font><input type="button" class="btn"  value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRICE}');"/>
	                      </c:if>
	                     </span>
                     </td>
                     <td nowrap="nowrap" align="right"  >${rst.PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right"  >${rst.DECT_RATE}&nbsp;</td>  
                     <td nowrap="nowrap" align="right" json="DECT_PRICE" id="DECT_PRICE${rr}" >${rst.DECT_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" json="ORDER_NUM" id="ORDER_NUM${rr}" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" json="ORDER_AMOUNT"  name="ORDER_AMOUNT" id="ORDER_AMOUNT${rr}" >${rst.ORDER_AMOUNT}&nbsp;</td> 
                     <td nowrap="nowrap" align="right"  id="OLD_PRICE${rr}" >${rst.OLD_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right"  >${rst.SENDED_NUM}&nbsp;</td> 
                     <td nowrap="nowrap" align="right"  >${rst.OLD_ORDER_NUM}&nbsp;</td>   
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
</body>
</html>
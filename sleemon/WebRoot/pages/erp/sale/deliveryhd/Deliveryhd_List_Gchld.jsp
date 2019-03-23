<!--
 * @prjName:喜临门营销平台
 * @fileName:  发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	 .inputText{
	 border-top:0px;border-left:0px ;border-right:0px
	 }
	</style>
</head>
<body  class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<form id="ordertbForm">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
				<tr class="">
				   <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >销售订单号</th>
				   <th  nowrap="nowrap" align="center" dbname="STOREOUT_NO" >出库单编号</th>
				   <th  nowrap="nowrap" align="center" dbname="U9_SM_NO" >U9出库单号</th>
				   <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >客户编号</th>
				   <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >客户名称</th>
				   <th  nowrap="nowrap" align="center" dbname="RECV_ADDR_NO" >收货地址编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货地址名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_FLAG" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
				   <th  nowrap="nowrap" align="center" dbname="STOREOUT_NUM" >实际数量</th>
				   <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
				   <th  nowrap="nowrap" align="center" dbname="DECT_AMOUNT" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					 <td nowrap="nowrap" align="center" >${rst.SALE_ORDER_NO}</td>
					 <td nowrap="nowrap" align="center" >${rst.STOREOUT_NO}</td>
					 <td nowrap="nowrap" align="center" >${rst.U9_SM_NO}</td>
					 <td nowrap="nowrap" align="left" >${rst.ORDER_CHANN_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="left" >${rst.ORDER_CHANN_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.RECV_ADDR_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.RECV_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
	                     <span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
	                       <input type="button" class="btn"  value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRICE}');"/>
	                      </c:if>
	                     </span>
                     </td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.STOREOUT_NUM}</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}</td>
                     <td  nowrap="nowrap" align="right">${rst.DECT_AMOUNT}</td>
                     <td nowrap="nowrap" align="left" >${rst.REMARK}</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr id="trEmpty">
						<td height="25" colspan="24" align="center" class="lst_empty" id="tdEmpty">
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
 
</body>
<script type="text/javascript">
	function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
</script>
</html>
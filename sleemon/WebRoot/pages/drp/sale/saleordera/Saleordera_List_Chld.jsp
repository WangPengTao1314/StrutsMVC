<!--
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/saleordera/Saleordera_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号&nbsp;-&nbsp;特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_AMOUNT" >订货金额</th>
                    <th  nowrap="nowrap" align="center" dbname="SENDED_NUM" >已实际发货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="CANCLE_NUM" >取消数量</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>-->
                    <th  nowrap="nowrap" align="center" dbname="PRD_TYPE" >货品类型</th>
                     <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th> 
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}
                     <c:if test="${rst.DM_SPCL_TECH_NO ne null}">-${rst.DM_SPCL_TECH_NO}</c:if>
                     &nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.IS_NO_STAND_FLAG}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                     	 <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
                     	 	<span  style="color: red">有</span>
                     	 	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}')" />
                     	 </c:if>
                     	 <c:if test="${rst.SPCL_TECH_FLAG eq '0' || rst.SPCL_TECH_FLAG eq null}">
                     	 	无
                     	 </c:if>
                     	&nbsp;
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_RATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.SENDED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.CANCLE_NUM}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center" >${rst.ADVC_SEND_DATE}&nbsp;</td>-->
                     <td nowrap="nowrap" align="center" >${rst.PRD_TYPE}&nbsp;</td>
                       <td nowrap="nowrap" align="left" >${rst.REMARK}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
	<input type="hidden" id="SALE_ORDER_DTL_IDS" name="SALE_ORDER_DTL_IDS" value=""/>
</form>
</body>
<script type="text/javascript">
	
</script>
</html>
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
	<script type="text/javascript" src="${ctx}/pages/drp/finance/advcInvoice/AdvcInvoice_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
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
                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >交货日期</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="IS_FREEZE_FLAG" >是否冻结</th>-->
<!--                    <th  nowrap="nowrap" align="center" dbname="FREEZE_NUM" >冻结数量</th>-->
<!--                    <th  nowrap="nowrap" align="center" dbname="FREEZE_STORE_NAME" >冻结库房</th>-->
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >应收金额</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_NUM" >已发数量</th>
                    <th  nowrap="nowrap" align="center" dbname="CANCLE_ORDER_NUM" >取消数量</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                     <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>  
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)"  onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));setSelOperateEx();"
					>
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.ADVC_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
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
                     <td nowrap="nowrap" align="right" >${rst.DECT_RATE}&nbsp;</td>
                     <input type="hidden" name="DECT_RATE" value="${rst.DECT_RATE}"/>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.ORDER_RECV_DATE}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center" >-->
<!--                     	<c:if test="${rst.IS_FREEZE_FLAG eq '0'}">-->
<!--                     		否-->
<!--                     	</c:if>-->
<!--                     	<c:if test="${rst.IS_FREEZE_FLAG eq '1'}">-->
<!--                     		是-->
<!--                     	</c:if>-->
<!--                     </td>-->
<!--                     <td nowrap="nowrap" align="right" >${rst.FREEZE_NUM}&nbsp;</td>-->
<!--                     <td nowrap="nowrap" align="left" >${rst.FREEZE_STORE_NAME}&nbsp;</td>-->
                     <td nowrap="nowrap" align="right" >${rst.PAYABLE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.SEND_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.CANCLE_ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.STATE}&nbsp;</td>
                     <input type="hidden" name="STATE" value="${rst.STATE}"/>
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
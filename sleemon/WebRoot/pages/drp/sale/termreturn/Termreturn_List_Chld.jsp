<!--
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_List
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/termreturn/Termreturn_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel" style="margin-top: -15px;">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="margin-left:3px; ">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
			   	   </c:if>
				   &nbsp;
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" style="margin-left:3px; " width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号&nbsp;-&nbsp;特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>-->
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_TYPE" >货品类型</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >退货单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >退货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >应退金额</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >退回原因</th>
                    <th  nowrap="nowrap" align="center" dbname="RETURN_ATT" >退货附件</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.ADVC_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}
                     <c:if test="${rst.DM_SPCL_TECH_NO ne null}">-${rst.DM_SPCL_TECH_NO}</c:if>
                     &nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;11${rst.SPCL_TECH_FLAG}</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_SPEC}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>-->
                     <td nowrap="nowrap" align="left" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_TYPE}&nbsp;</td>
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
                     <td nowrap="nowrap" align="right" >${rst.PAYABLE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.REMARK}&nbsp;</td>
                     <td nowrap="nowrap" align="left" ><input type="hidden" id ="RETURN_ATT${rr}" value="${rst.RETURN_ATT}" />&nbsp;</td>
				    </tr>
				    <script type="text/javascript">
				        displayDownFile('RETURN_ATT${rr}','SAMPLE_DIR',true,false);
				    </script>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
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
	<input type="hidden" id="ADVC_ORDER_ID" name="ADVC_ORDER_ID" value=""/>
</form>
</body>
</html>
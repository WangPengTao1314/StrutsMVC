<!--
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核 明细 列表
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleorderque/Saleorderque_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
		.inputText{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr align="center">
  <td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
			<td colspan="20">
				总数量：<input id="allNum" name="allNum" size="8"readonly value=""  class="inputText"/>
				总金额：<input id="allAmount" name="allAmount" size="15"  readonly class="inputText" />
				总体积：<input id="allVolume" name="allVolume" size="15" readonly class="inputText"/>
			</td>
		</tr>
   </table>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
		   <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" >是否是备货</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_FLAG" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_AMOUNT" >订货金额</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_AMOUNT" >体积</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.SALE_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                     <c:if test="${rst.IS_BACKUP_FLAG eq 0}">否</c:if>
                     <c:if test="${rst.IS_BACKUP_FLAG eq 1}">是</c:if>
                     &nbsp;</td>
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
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.VOLUME}&nbsp;</td>
                     <script type="text/javascript">
						  if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseFloat(${rst.ORDER_NUM},10));
						  }else{
						  	var tempNum = parseFloat($("#allNum").val(),10);
						  	var perNum = parseFloat(${rst.ORDER_NUM},10);
						  	var allNum=Math.round((isNaN(tempNum + perNum)?0:tempNum + perNum),2);
						  	$("#allNum").val(allNum);
						  }
						  
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(${rst.ORDER_AMOUNT},10)).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(${rst.ORDER_AMOUNT},10);
						  	var allAmount=Math.round((isNaN(tempAmount + perAmount)?0:tempAmount + perAmount),2);
						  	$("#allAmount").val(allAmount);
						  }
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  if($("#allVolume").val()==null || $("#allVolume").val()==""){
						  	$("#allVolume").val((parseFloat(volume,10)*${rst.ORDER_NUM}).toFixed(2));
						  }else{
						  	var tempVolume = parseFloat($("#allVolume").val(),10);
						  	var perVolume = parseFloat(volume,10);
						  	var allVolume=Math.round((isNaN(tempVolume + perVolume)?0:tempVolume + perVolume),2);
						  	$("#allVolume").val(allVolume);
						  }
						  
					   </script>
                     
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
</html>
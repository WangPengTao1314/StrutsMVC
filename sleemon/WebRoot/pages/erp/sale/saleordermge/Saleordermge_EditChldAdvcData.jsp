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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleordermge/Saleordermge_EditChldAdvcData.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	 .inputText{
	 border-top:0px;border-left:0px ;border-right:0px
	 }
	</style>
	<script type="text/javascript">
		function window.onbeforeunload(){
			var commitFlag=$("#commitFlag").val();
			if("1"==commitFlag){
				return "单据正在提交U9生产,确定关闭吗？如强行关闭需自行刷新销售订单处理页面";
			}
		}
	</script>
</head>
<body  class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr align="">
  <td height="20px" valign="top">
  <form>
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
			<td colspan="20">
				 <input id="save" name="close" type="button" class="btn" value="确定(S)" title="Alt+O" accesskey="O">
			</td>
		</tr>
   </table>
   </form>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<form id="ordertbForm" method="post">
			<input value="${SALE_ORDER_ID}" type="hidden" id="SALE_ORDER_ID">
			<input value="${SALE_ORDER_NO}" type="hidden" id="SALE_ORDER_NO">
			<input value="0" id="commitFlag" type="hidden">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
				<tr class="">
				   <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>
                   <th  nowrap="nowrap" align="center" dbname="REMARK">备注</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					 <td style="display: none;"><input type="checkbox" checked="checked"/></td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <input name="DELIVER_ORDER_DTL_ID" type="hidden" json="SALE_ORDER_DTL_ID" value="${rst.SALE_ORDER_DTL_ID}"/>
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
                     <td nowrap="nowrap" align="center" >
					 	<input type="text"  id="ADVC_SEND_DATE" size="8" name="ADVC_SEND_DATE" json="ADVC_SEND_DATE" value="${rst.ADVC_SEND_DATE}"  onclick="SelectDate();"  />&nbsp;
				    	<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE);"/>
					 </td>
                     <td nowrap="nowrap" align="left" >
                      ${rst.REMARK}
                     </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr id="trEmpty">
						<td height="25" colspan="25" align="center" class="lst_empty" id="tdEmpty">
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
</script>
</html>
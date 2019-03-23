<!--
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_Detial
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>信息详情</title>
</head>
<body style="overflow-y:scroll;" class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
                   <td width="15%" align="right" class="detail_label">订货订单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.GOODS_ORDER_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">订货日期:</td>
					<td width="35%" class="detail_content">
                       ${rst.ORDER_DATE} &nbsp;
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">订货总额:</td>
					<td width="35%" class="detail_content">
                        ${rst.TOTAL_AMOUNT}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">生产基地名称:</td>
					<td width="35%" class="detail_content" >
                         ${rst.SHIP_POINT_NAME }&nbsp;
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">订单类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.BILL_TYPE}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">是否使用返利:</td>
					<td width="35%" class="detail_content">
                        <c:if test="${rst.IS_USE_REBATE eq 0}">否 </c:if>
                        <c:if test="${rst.IS_USE_REBATE eq 1}">是 </c:if>&nbsp;
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">战区编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.WAREA_NO} &nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">战区:</td>
					<td width="35%" class="detail_content">
                        ${rst.WAREA_NAME} &nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">订货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_CHANN_NO}&nbsp;
					</td>
					 <td width="15%" align="right" class="detail_label">订货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_CHANN_NAME}&nbsp;
					</td>
			       
               </tr>
               <tr> 
			       <td width="15%" align="right" class="detail_label">发货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.SEND_CHANN_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">发货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.SEND_CHANN_NAME}&nbsp;
					</td>
			       
               </tr>
               
               
               <tr>
			        <td width="15%" align="right" class="detail_label">收货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_CHANN_NO}&nbsp;
					</td>  
			        <td width="15%" align="right" class="detail_label">收货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">联系人:</td>
					<td width="35%" class="detail_content">
                        ${rst.PERSON_CON}&nbsp;
					</td>
			         <td width="15%" align="right" class="detail_label">联系方式:</td>
					<td width="35%" class="detail_content">
                        ${rst.TEL}&nbsp;
					</td>
               </tr>
                
               <tr>
			        <td width="15%" align="right" class="detail_label">制单人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">更新人:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">处理人:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">处理时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单部门:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单机构:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
			         
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">帐套名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">收货地址:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.RECV_ADDR}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
					</td>
               </tr>
          		 <tr>
			        <td width="15%" align="right" class="detail_label">退回原因:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.RETURN_RSON}&nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
   $(function(){
	    parent.topcontent.busDisableCommit();
   });

</script>
</html>
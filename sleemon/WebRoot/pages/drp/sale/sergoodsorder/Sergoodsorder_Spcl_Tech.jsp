<!--
 * @prjName:喜临门营销平台
 * @fileName:特殊工艺要求
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/sale/sergoodsorder/Sergoodsorder_Spcl_Tech.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>订单中心>>订货订单处理</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
		<form action="">
		    <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and LEDGER_ID='${params.LEDGER_ID}' ">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
				 <td nowrap="nowrap" align="center">货品编号</td>
				 <td nowrap="nowrap" align="center">&nbsp;</td>
				 <td nowrap="nowrap" align="center">货品名称</td>
				 <td nowrap="nowrap" align="center">&nbsp;</td>
				</tr>
				<tr class="fixedRow">
				 <td nowrap="nowrap" align="center">货品类型</td>
				 <td nowrap="nowrap" align="center">&nbsp;</td>
				 <td nowrap="nowrap" align="center">规格型号</td>
				 <td nowrap="nowrap" align="center">&nbsp;</td>
				</tr>
	 
			</table>
			</form>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
			<tr>
			 <td>宽度调整:</td>
			 <td></td>
			</tr>
			<tr>
			 <td>长度调整:</td>
			 <td></td>
			</tr>
			<tr>
			 <td>整分体调整:</td>
			 <td></td>
			</tr>
			<tr>
			 <td>备注:</td>
			 <td>
			  <textarea rows="" cols=""></textarea>
			 </td>
			</tr>
		</table>
	</td>
</tr>
<tr>
 <td>
   <input type="button" value="计算"/><input type="text"  />
 </td>
</tr>
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  style="width:155" value="${params.GOODS_ORDER_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<input id="STATE" name="STATE"  style="width:155" value="${params.STATE}"/>
					</td>					
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
</script>
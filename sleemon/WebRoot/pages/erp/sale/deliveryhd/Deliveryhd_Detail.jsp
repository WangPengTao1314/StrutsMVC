<!--  
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	
	<title>发运单详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">发运单编号：</td>
					<td width="35%" class="detail_content">${rst.DELIVER_ORDER_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">预计发货日期：</td>
					<td width="35%" class="detail_content">${rst.ADVC_SEND_DATE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">单据类型：</td>
					<td width="35%" class="detail_content" colspan="3">${rst.BILL_TYPE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">发货方式：</td>
					<td width="35%" class="detail_content">${rst.DELIVER_TYPE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">车型：</td>
					<td width="35%" class="detail_content">${rst.TRUCK_TYPE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">发货类型：</td>
					<td width="35%" class="detail_content">${rst.CHANN_TYPE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >生产基地：</td>
					<td width="35%" class="detail_content">${rst.SHIP_POINT_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >代发区域服务中心编号：</td>
					<td width="35%" class="detail_content">${rst.AREA_SER_NO}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">代发区域服务中心名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_SER_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >发货方编号：</td>
					<td width="35%" class="detail_content">${rst.SEND_CHANN_NO}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">发货方名称：</td>
					<td width="35%" class="detail_content">${rst.SEND_CHANN_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >订货方编号：</td>
					<td width="35%" class="detail_content">${rst.ORDER_CHANN_NO}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">订货方名称：</td>
					<td width="35%" class="detail_content">${rst.ORDER_CHANN_NAME}&nbsp;</td>
				</tr>
			    <tr>
					<td width="15%" align="right" class="detail_label" >收货方编号：</td>
					<td width="35%" class="detail_content">${rst.RECV_CHANN_NO}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">收货方名称：</td>
					<td width="35%" class="detail_content">${rst.RECV_CHANN_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
					
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">账套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注说明：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type=text/javascript >
</script>
</html>

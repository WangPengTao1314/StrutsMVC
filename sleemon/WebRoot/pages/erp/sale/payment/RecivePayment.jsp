
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/payment/RecivePayment.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" nowrap>
			<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
		</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
			       <td width="15%" align="right" class="detail_label">渠道ID：</td>
				   <td width="35%" class="detail_content">
					  <input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" autocheck="true" label="渠道编号" type="text" mustinput="true" inputtype="string" />
				   </td>
				   <td width="15%" align="right" class="detail_label">付款金额：</td>
				   <td width="35%" class="detail_content">
					  <input json="PAY_AMONT" name="PAY_AMONT" id="PAY_AMONT" autocheck="true" label="付款金额" type="text" mustinput="true"  inputtype="string" />
				   </td>
			   </tr>
			   <tr>
			       <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content">
					  <input json="REMARK" name="REMARK" id="REMARK" autocheck="true" label="备注" type="text" mustinput="true"  inputtype="string" />
				   </td>
				   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				   </td>
			   </tr>
		    </table>
	   </td> 
	</tr>
</table>
</form>
</body>

</html>

 
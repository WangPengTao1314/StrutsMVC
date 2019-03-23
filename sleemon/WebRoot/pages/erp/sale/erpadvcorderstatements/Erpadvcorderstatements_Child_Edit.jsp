<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
 */
-->
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/erpadvcorderstatements/Erpadvcorderstatements_Child_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/sleemon/scripts/core/keyboard_ctrl.js"></script>
	<title>明细信息编辑</title>
</head>
<body class="bodycss1">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap>
			   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
			   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
			   <input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
		</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form>
	    <input type="hidden" id="selectParams" name="selectParams" value=""/>
	    <input type="hidden" id="advcIds" name="advcIds" value="${advcIds}"/>
	    <input type="hidden" id="selAdvcParams" name="selAdvcParams" value=""/>
	    <input type="hidden" id="selCardParams" name="selCardParams" value=""/>
	    
	    
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
               <th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_ORDER_NO" >预订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >电话</th> 
                    <th  nowrap="nowrap" align="center" dbname="ADVC_AMOUNT" >订金金额</th>
                    <th  nowrap="nowrap" align="center" dbname="CUR_STATEMENTS_AMOUNT" >本次结算金额</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >应收总额</th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_CARD_NO" >卡卷编号</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<input id="flag" value="1" type="hidden"> 
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.ADVC_STATEMENTS_ORDER_DTL_ID}',//0
              '${rst.ADVC_ORDER_ID}',//1
              '${rst.ADVC_ORDER_NO}',//2
              '${rst.BILL_TYPE}',//3
              '${rst.SALE_DATE}',//4
              '${rst.CUST_NAME}',//5
              '${rst.TEL}',//6
              '${rst.ADVC_AMOUNT}',//7
              '${rst.CUR_STATEMENTS_AMOUNT}',//8
              '${rst.PAYABLE_AMOUNT}',//9
              '${rst.MARKETING_CARD_ID}',//10
              '${rst.MARKETING_CARD_NO}', //11
              '${rst.GIFT_AMOUNT}'
            ];
	addRow(arrs);
	</c:forEach>
	 
</script>
</html>
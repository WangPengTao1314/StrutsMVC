<!--
/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketcardsale/MarketcardSale_Child_Edit.js"></script>
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
	    <input type="hidden" id="selCardParams" name="selCardParams" value=""/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
                <th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
                <th  nowrap="nowrap" align="center" dbname="MARKETING_CARD_NO" >卡券编号</th>
                <th  nowrap="nowrap" align="center" dbname="CARD_TYPE" >卡券类型</th>
                <th  nowrap="nowrap" align="center" dbname="CARD_VALUE" >卡券面值</th>
                <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >发放日期</th>
                <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th> 
                <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                <th  nowrap="nowrap" align="center" dbname="MOBILE_PHONE" >手机</th>
                <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >收款额</th>
                <th  nowrap="nowrap" align="center" dbname="SEX" >性别</th> 
                <th  nowrap="nowrap" align="center" dbname="ADDRESS" >住址</th>
                <th  nowrap="nowrap" align="center" dbname="REMARK" >客户需求（意向购买）</th>
                <th  nowrap="nowrap" align="center" dbname="BIRTHDAY" >生日</th>
                <th  nowrap="nowrap" align="center" dbname="HOBBY" >爱好</th>
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
		      '${rst.CARD_SALE_ORDER_DTL_ID}',//0
		      '${rst.MARKETING_CARD_ID}',//1
	          '${rst.MARKETING_CARD_NO}',//2
              '${rst.CARD_TYPE}',//3
              '${rst.CARD_VALUE}',//4
              '${rst.CRE_TIME}',//5
              '${rst.REGIST_STATE}',//6签到状态
              '${rst.CUST_NAME}',//7
              '${rst.PAYABLE_AMOUNT}',//8
              '${rst.MOBILE_PHONE}',//9
              '${rst.SEX}',//10
              '${rst.BIRTHDAY}',//11
              '${rst.HOBBY}',//12
              '${rst.ADDRESS}',//13
              '${rst.REMARK}',//14
              '${rst.STATE}'//状态
              
              
               
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
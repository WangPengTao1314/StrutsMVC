<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:预订发货取消申请
 * @author zzb
 * @time   2014-05-19  
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advccancelsapp/Advccancelsapp_Edit_Chld.js"></script>
	<%--<style type="text/css">
		.readonly{
			width:70px;
		}
	</style>
--%></head>
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
	    <form id="pageForm" method="post">
	    <input type="hidden" name=selectParams id="selectParams" value="">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center" >货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">取消数量</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">折后金额</th>
              <th nowrap align="center">通知出库数量</th>
              <th nowrap align="center">取消原因</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
		'${rst.ADVC_SEND_CANCEL_DTL_ID}',//0
		'${rst.FROM_BILL_DTL_ID}',//1
		'${rst.PRD_ID}',//2
        '${rst.PRD_NO}',//3
        '${rst.PRD_NAME}',//4
        '${rst.PRD_SPEC}',
        '${rst.PRD_COLOR}',
        '${rst.BRAND}',//7
        '${rst.STD_UNIT}',
        '${rst.SPCL_TECH_FLAG}',
        '${rst.CANCEL_NUM}',// 10 取消数量
        $.trim('${rst.PRICE}'),
        $.trim('${rst.DECT_RATE}'),
        $.trim('${rst.DECT_PRICE}'),
        '${rst.DECT_AMOUNT}',
        '${rst.CANCEL_RSON}',
        '${rst.FROM_BILL_DTL_ID}',//16
        '${rst.SPCL_TECH_ID}',
        '${rst.NOTICE_NUM}'
      
      ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
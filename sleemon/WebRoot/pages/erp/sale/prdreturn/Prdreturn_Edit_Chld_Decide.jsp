<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit_Chld
 * @author wzg
 * @time   2013-08-19 15:33:31 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/prdreturn/Prdreturn_Edit_Chld_Decide.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
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
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
			   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="parent.$('#label_1').click();">
		</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form>
		<input type="hidden" id="PRD_RETURN_ID" name="PRD_RETURN_ID">
		
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品序列号</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
 			  <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">退货单价</th>
              <th nowrap align="center">建议退货单价</th>
              <th nowrap align="center">退货数量</th>
              <th nowrap align="center">退货金额</th>
              <th nowrap align="center">原因归类</th>
              <th nowrap align="center">退货原因</th>
              <th nowrap align="center">退货附件</th>
              <th nowrap align="center">责任认定</th>
              <th nowrap align="center">核价价格</th>
              <th nowrap align="center">实际退货金额</th>
              <th nowrap align="center">核价说明</th>
              <th nowrap align="center">处理工厂</th>
              <%--<th nowrap align="center">操作</th>--%>
     	 </tr>
		</table>
		<input id="selectCondition" name="selectCondition" type="hidden" value=" STATE = '启用'"/>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.PRD_RETURN_DTL_ID}',//0
              '${rst.PRD_ID}',//1
              '${rst.PRD_NO}',//2
              '${rst.PRD_NAME}',//3
              '${rst.PRD_SPEC}',//4
              '${rst.PRD_COLOR}',//5
              '${rst.BRAND}',//6
              '${rst.STD_UNIT}',//7
              '${rst.RETURN_PRICE}',//8
              '${rst.RETURN_NUM}',//9
              '${rst.RETURN_AMOUNT}',//10
              '${rst.RETURN_RSON_TYPE}',//11
              '${rst.RETURN_RSON}',//12
              '${rst.RETURN_ATT}',//13
              '${rst.DUTY_DECIDE}',//14             
              '${rst.PRICE_DECIDE}',//15
              '${rst.REAL_RETURN_AMOUNT}',//16
              '${rst.REMARK_DECIDE}',//17
              '${rst.DEAL_FACTORY_ID}',//18
              '${rst.DEAL_FACTORY_NAME}',//19
              '${rst.SN}',//20
              '${rst.SPCL_TECH_ID}',//21
              '${rst.SPCL_TECH_FLAG}',//22
              '${rst.ADVC_RETURN_PRICE}',//23
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
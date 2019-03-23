<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advcreturn/Advcreturn_Edit_Chld.js"></script>
	<title>预订单退货处理</title>
</head>
<body class="bodycss1">
	<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
	<input id="delFlag" type="hidden" value="false"/>
	<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
		<tr>
			<form id="queryForm" method="post" action="#">
				<table width="100%" border="0"  class="detail">
					<input type="hidden" name="selectParams" id="selectParams"  value=" STATE ='启用' and DEL_FLAG=0 and LEDGER_ID='${LEDGER_ID}'">
					<input type="hidden" id="ADVC_ORDER_ID" value="${ADVC_ORDER_ID}"/>
					<tr>
						<td class="detail2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>
									<td colspan="13">
										<h2 align="center">预订单提交退货处理</h2>
									</td>
								</tr>
								<tr>
									<td colspan="13"><hr/></td>
								</tr>
								<tr>
									<td width="13%" nowrap align="right" class="detail_label" style="border: 1px solid black;">库房编号 ：</td>
									<td nowrap width="13%" class="detail_content" style="border: 1px solid black;">
										<input id="STORE_ID" json="STORE_ID" name="STORE_ID" type="hidden"  value="${rst.STORE_ID}">
										<input id="STORE_NO" json="STORE_NO"  name="STORE_NO" type="text" inputtype="string"  value="${rst.STORE_NO}" >
										<img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_35', false, 'STORE_ID', 'STORE_ID', 'forms[0]','STORE_ID,STORE_NO,STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME', 'selectParams')">
									</td>
									<td width="13%" nowrap align="right" class="detail_label" style="border: 1px solid black;">库房名称：</td>
									<td width="13%" class="detail_content" style="border: 1px solid black;">
										<input type="text" id="STORE_NAME" json="STORE_NAME" name="STORE_NAME" value="${rst.STOREOUT_STORE_NAME }"/>
									</td>
									<%--<td width="13%" align="right" class="detail_label" style="border: 1px solid black;">库位管理标记：</td>
									<td width="13%" class="detail_content" style="border: 1px solid black;">
										<input id="STORAGE_FLAG" json="STORAGE_FLAG"  name="STORAGE_FLAG" type="hidden" value="${rst.STORAGE_FLAG}" onpropertychange="acquireTab()">
										<input id="STORAGE_FLAG_YES" name="STORAGE_FLAG_TAB"  type="radio"  <c:if test="${rst.STORAGE_FLAG==1 }">checked="checked"</c:if> />是
										<input id="STORAGE_FLAG_NO" name="STORAGE_FLAG_TAB"  type="radio"  <c:if test="${rst.STORAGE_FLAG==0 }">checked="checked"</c:if> />否
									</td>
								--%></tr>
								<!-- tr>
									<td nowrap>
										<input id="select" type="button" class="btn" value="查看库存(S)" title="Alt+S" accesskey="S" >
									</td>
								</tr-->
							</table>
						</td>
					</tr>
				</table>
			</form>
		</tr>
		<!-- tr>
			<td>
				<div class="lst_area" style="height: 400px">
					<form>
						<div id="bottomdiv" style="width: 100%;position:absolute;overflow:scroll;">
							<table id="jsontb" width="100%"  class="lst">
								<input type="hidden" name="selectParam" value=" DEL_FLAG=0 and ADVC_ORDER_ID='${ADVC_ORDER_ID}'">
								<tr class="fixedRow">
									<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
									<th nowrap align="center" >货品编号</th>
									<th nowrap align="center">货品名称</th>
									<th nowrap align="center">规格型号</th>
									<th nowrap align="center">特殊工艺</th>
									<th nowrap align="center">实发货品编号</th>
									<th nowrap align="center">实发货品名称</th>
									<th nowrap align="center">实发规格型号</th>
									<th nowrap align="center">实发花号</th>
									<th nowrap align="center">实发品牌</th>
									<th nowrap align="center">实发标准单位</th>
									<th nowrap align="center">出厂单价</th>
									<th nowrap align="center">实发折扣率</th>
									<th nowrap align="center">实发单价</th>
									<th nowrap align="center">实发数量</th>
									<th nowrap align="center">实发金额</th>
									<th nowrap align="center">备注</th>
								</tr>
							</table>
						</div>
						<table width="100%" height="250px">
							<tr>
								<td>
									<form id="pageForm" action="#" method="post" name="listForm">
									<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
										<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
										<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
										<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
										<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
										<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
										<span id="hidinpDiv" name="hidinpDiv"></span>
										${paramCover.unCoveredForbidInputs }
									</form>
								</td>
								<td align="right">
									 ${page.footerHtml}${page.toolbarHtml}
								</td>
							</tr>
						</table>
						<table align="center">
							<tr>
								<td nowrap >
									<input id="create" type="button" class="btn" value="确定发货(C)" title="Alt+C" accesskey="C" >
									<input id="close" type="button" class="btn" value="关闭(N)" title="Alt+N" accesskey="N" >
								</td>
							</tr>
						</table>
					</form>
				</div>
			</td>
		</tr-->
		<tr>
			<table align="center">
				<tr>
					<td nowrap >
						<input id="create" type="button" class="btn" value="确定退货(C)" title="Alt+C" accesskey="C" >
						<input id="close" type="button" class="btn" value="关闭(N)" title="Alt+N" accesskey="N" >
					</td>
				</tr>
			</table>
		</tr>
	</table>
</body>
<script type="text/javascript">
	<c:forEach items="${page.result}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.PRD_ID}',
              '${rst.PRD_NO}',
              '${rst.PRD_NAME}',
              '${rst.PRD_SPEC}',
              '${rst.PRD_COLOR}',
              '${rst.BRAND}',
              '${rst.STD_UNIT}',
              '${rst.SPCL_TECH_FLAG}',
              '${rst.PRICE}',
              '${rst.DECT_RATE}',
              '${rst.DECT_PRICE}',
              '${rst.ORDER_NUM}',
              '${rst.PAYABLE_AMOUNT}',
              '${rst.REMARK}',
              '${rst.ADVC_ORDER_DTL_ID}'
            ];
	addRow(arrs);
	</c:forEach>
	function acquireTab(){
	   		var STORAGE_FLAG=document.getElementById("STORAGE_FLAG").value;
	   		if(STORAGE_FLAG==1){
	   			document.getElementById("STORAGE_FLAG_YES").checked=true;
	   		}else if(STORAGE_FLAG==""||STORAGE_FLAG==null){
	   			document.getElementById("STORAGE_FLAG_YES").checked=false;
	   			document.getElementById("STORAGE_FLAG_NO").checked=false;
	   		}else if(STORAGE_FLAG==0){
	   			document.getElementById("STORAGE_FLAG_NO").checked=true;
	   		}
	   }
</script>
</html>

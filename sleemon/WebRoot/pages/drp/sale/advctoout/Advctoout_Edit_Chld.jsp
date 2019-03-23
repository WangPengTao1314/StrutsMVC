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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advctoout/Advctoout_Edit_Chld.js"></script>
	<title>预订单提交发货处理</title>
</head>
<body class="bodycss1">
	<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
	<input id="delFlag" type="hidden" value="false"/>
	<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
		<tr>
			<form id="queryForm" method="post" action="#">
				<table width="100%" border="0"  class="detail">
					<input type="hidden" name="selectParams" value=" STATE ='启用' and BEL_CHANN_ID='${LEDGER_ID}'">
					<input type="hidden" id="CHANN_ID" value="${CHANN_ID}"/>
					<tr>
						<td class="detail2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1" >
								<tr>
									<td colspan="13">
										<h2 align="center">预订单提交发货处理</h2>
									</td>
								</tr>
								<tr>
									<td colspan="13"><hr/></td>
								</tr>
								<tr>
									<td width="13%" nowrap align="right" class="detail_label" >库房编号 ：</td>
									<td nowrap width="13%" class="detail_content" >
										<input id="STOREOUT_STORE_ID" json="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID" type="hidden"  value="${rst.STOREOUT_STORE_ID}">
										<input id="STOREOUT_STORE_NO" json="STOREOUT_STORE_NO"  name="STOREOUT_STORE_NO" type="text" inputtype="string"  value="${rst.STOREOUT_STORE_NO}" >
										<img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID', 'forms[0]','STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME', 'selectParams')">
									</td>
									<td width="13%" nowrap align="right" class="detail_label" >库房名称：</td>
									<td width="13%" class="detail_content" >
										<input type="text" id="STOREOUT_STORE_NAME" json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME" value="${rst.STOREOUT_STORE_NAME }"/>
									</td>
								</tr>
								<tr>
									<td width="13%" nowrap align="right"  >备注 ：</td>
									<td nowrap width="13%" class="detail_content">
										(<span style="color: red">请输入需要提醒库管员发货时应当注意的事项</span>)
									</td>
								</tr>
								<tr>
									<td></td>
									<td width="35%" class="detail_content" colspan="3">
				                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
								   </td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</tr>
		<tr>
			<td>
				<div class="lst_area" style="height: 400px">
					<form>
						<div id="bottomdiv" style="width: 100%;position:absolute;overflow:scroll;">
							<table id="jsontb" width="100%"  class="lst">
								<input type="hidden" name="selectParam" value=" DEL_FLAG=0 and ADVC_ORDER_ID='${ADVC_ORDER_ID}'">
								<input type="hidden" id="ADVC_ORDER_ID" value="${ADVC_ORDER_ID}"/>
								<tr class="fixedRow">
									<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
									<th nowrap align="center">货品编号</th>
									<th nowrap align="center">货品名称</th>
									<th nowrap align="center">规格型号</th>
									<th nowrap align="center">花号</th>
									<th nowrap align="center">品牌</th>
									<th nowrap align="center">标准单位</th>
									<th nowrap align="center">特殊工艺</th>
									<th nowrap align="center">零售单价</th>
									<th nowrap align="center">折扣率</th>
									<th nowrap align="center">折后单价</th>
									<th nowrap align="center">订货数量</th>
									<th nowrap align="center">可用库存</th>
									<th nowrap align="center">已发数量</th>
									<th nowrap align="center">实发数量</th>
									<th nowrap align="center">实发金额</th>
									<!-- th nowrap align="center">备注</th-->
								</tr>
							</table>
							<table align="center" >
							<tr>
								<td nowrap >
									<input id="create" type="button" class="btn" value="确定发货(C)" title="Alt+C" accesskey="C" >
									<input id="close" type="button" class="btn" value="关闭(N)" title="Alt+N" accesskey="N" >
								</td>
							</tr>
						</table>
						</div>
						<table>
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
						
					</form>
				</div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	<c:forEach items="${list}" var="rst" varStatus="row">
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
              '${rst.NUM}',
              '${rst.SEND_NUM}',
              '${rst.PAYABLE_AMOUNT}',
              '${rst.REMARK}',
              '${rst.ADVC_ORDER_DTL_ID}',
              '${rst.SPCL_TECH_ID}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>

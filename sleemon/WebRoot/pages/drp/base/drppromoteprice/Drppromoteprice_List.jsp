

<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author  刘曰刚
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/base/drppromoteprice/Drppromoteprice_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;活动管理&gt;&gt;渠道活动价格设置</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top" >
	   <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="margin-left:3px;">
				<tr >
				   <td nowrap >
				   	<form id="queryForm" method="post" action="#">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
									<input type="hidden" name=selectParams id="selectParams" value="STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${params.LEDGER_ID}'))   and IS_NO_STAND_FLAG=0">
									<input type="hidden" name="selectPromote" value=" DEL_FLAG=0 and LEDGER_ID=${params.LEDGER_ID} and STATE='启用' "/>
									<input type="hidden" name="selectFlag" value=" DEL_FLAG=0 and STATE='启用' and FINAL_NODE_FLAG=0 "/>
									<tr>
										<td width="8%" nowrap align="right" class="detail_label" >活动编号：</td>
										<td nowrap width="15%" class="detail_content">
											<input id="PROMOTE_ID" json="PROMOTE_ID" name="PROMOTE_ID" type="hidden"	value="${params.PROMOTE_ID}">
											<input id="PROMOTE_NO" json="PROMOTE_NO" name="PROMOTE_NO" type="text" mustinput="true" inputtype="string"  autocheck="true" label="活动编号" readonly	value="${params.PROMOTE_NO}">
											<img align="absmiddle" name="selJGXX" style="cursor: hand"
												src="${ctx}/styles/${theme}/images/plus/select.gif"       
												onClick="selCommon('BS_112', false, 'PROMOTE_ID', 'PROMOTE_ID', 'forms[0]','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME', 'PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME', 'selectPromote');submitQuery();">
										</td>
										<td width="8%" nowrap align="right" class="detail_label">活动名称：</td>
										<td width="15%" class="detail_content">
											<input type="text" id="PROMOTE_NAME" json="PROMOTE_NAME" name="PROMOTE_NAME" mustinput="true" inputtype="string"  autocheck="true" label="活动名称" readonly value="${params.PROMOTE_NAME }"/>
										</td>
										<td width="8%" nowrap align="right" class="detail_label">货品分类编号：</td>
										<td width="15%" class="detail_content">
											<input id="PAR_PRD_NO" json="PAR_PRD_NO" name="PAR_PRD_NO" type="text"
																			inputtype="string"  autocheck="true"
																			 value="${params.PAR_PRD_NO}">
											<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="selCommon('BS_21', true, 'PAR_PRD_NO', 'PRD_NO', 'forms[0]','PAR_PRD_NO,PAR_PRD_NAME', 'PRD_NO,PRD_NAME', 'selectFlag')">
										</td>
										<td width="8%" nowrap align="right" class="detail_label">货品分类名称：</td>
										<td width="15%" class="detail_content">
											<input id="PAR_PRD_NAME" json="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text"
																			inputtype="string"  autocheck="true"
																			 value="${params.PAR_PRD_NAME}">
										</td>
										
									</tr>
									<tr>
										<td width="8%" nowrap align="right" class="detail_label" >货品编号：</td>
										<td nowrap width="15%" class="detail_content">
											<input id="PRD_NO" json="PRD_NO" name="PRD_NO" type="text"	value="${params.PRD_NO}">
											<img align="absmiddle" name="selJGXX" style="cursor: hand"
												src="${ctx}/styles/${theme}/images/plus/select.gif"       
												onClick="selCommon('BS_21', true, 'PRD_NO', 'PRD_NO', 'forms[0]','PRD_NO,PRD_NAME', 'PRD_NO,PRD_NAME', 'selectParams')">
										</td>
										<td width="8%" nowrap align="right" class="detail_label">货品名称：</td>
										<td width="15%" class="detail_content">
											<input type="text" id="PRD_NAME" json="PRD_NAME" name="PRD_NAME" value="${params.PRD_NAME }"/>
										</td>
										<td width="8%" align="right" class="detail_label">是否分配：</td>
										<td width="15%" class="detail_content" colspan="3">
											<input type="radio" name="allot" value="isallot" <c:if test="${params.allot eq 'isallot'}"> checked="checked" </c:if>/>已分配
											<input type="radio" name="allot" value="notallot" <c:if test="${params.allot eq 'notallot'}"> checked="checked" </c:if> />未分配
											<input type="radio" name="allot" id="allot" value="all" <c:if test="${params.allot eq ''}"> checked="checked" </c:if> />全部
											
										</td>
									</tr>
									<tr>
										<td colspan="10" align="center" valign="middle" >
											<input  type="button" class="btn" onclick="submitQuery();" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
											<input  type="button" onclick="resetBtn()" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
											<input type="button" value="保存(S)" id="save" class="btn" title="Alt+S" accesskey="S"/>
										</td>
									</tr>
								</table>
						</form>
					</td>
				</tr>
			</table>
			</div>
	</td>
</tr>
<tr>
	<td >
	<form id="paramsForm">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1"  cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"><input type="checkbox" style="height:12px;valign:middle" id="allChecked" /></th>
					<th nowrap align="center" dbname="PRD_NO">货品编号</th>
					<th nowrap align="center" dbname="PRD_NAME">货品名称</th>
					<th nowrap align="center" dbname="PRD_SPEC">规格型号</th>
					<th nowrap align="center" dbname="STD_UNIT">标准单位</th>
					<th nowrap align="center" dbname="BRAND">品牌</th>
					<th nowrap align="center" dbname="PRICE">单价</th>
					<th nowrap align="center" dbname="DECT_RATE">折扣率</th>
					<th nowrap align="center" dbname="DECT_PRICE">折后单价</th>
					<th nowrap align="center" dbname="UPD_NAME">分配人</th>
					<th nowrap align="center" dbname="UPD_TIME">分配时间</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  >
						<td width="1%"align='center' >
							<input type="checkbox" style="valign:middle" name="eid" id="eid${rr}" value="${rst.RETAL_PRICE_ID}"/>
						</td>
                        <td nowrap align="left">${rst.PRD_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.PRD_NAME}&nbsp;</td>
                        <td nowrap align="left" >${rst.PRD_SPEC}&nbsp;</td>
                        <td nowrap align="left" >${rst.STD_UNIT}&nbsp;</td>
                        <td nowrap align="left" >${rst.BRAND}&nbsp;</td>
                        <td nowrap align="left"><input type="text" name="PRICE" id="PRICE${rr}" mustinput="true" inputtype="string"  autocheck="true"  json="PRICE"  size="5" readonly value="${rst.PRICE}"></td>
                        <td nowrap align="left" id="dect${rr}"><input type="text" name="DECT_RATE" id="DECT_RATE${rr}" mustinput="true" inputtype="string"  autocheck="true"  readonly json="DECT_RATE" size="5" value="${rst.DECT_RATE}"></td>
                        <td nowrap align="center">
                        	<input type="text" value="${rst.DECT_PRICE}" id="DECT_PRICE${rr}" onkeyup="countPrice('${rr}')" mustinput="true"   autocheck="true" onclick="$('#eid${rr}').attr('checked','true');" size="5" name="DECT_PRICE"  json="DECT_PRICE"/>
                        	<input type="hidden" name="PROMOTE_PRICE_ID" json="PROMOTE_PRICE_ID" value="${rst.PROMOTE_PRICE_ID}">
                        	<input type="hidden" name="PRD_ID" json="PRD_ID" value="${rst.PRD_ID}">
                        </td>
                        <td nowrap align="left" >${rst.UPD_NAME}&nbsp;</td>
                        <td nowrap align="left" >${rst.UPD_TIME}&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
		</form>
	</td>
</tr>
<tr>
			<td height="12px" align="center">
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
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
			</td>
		</tr>
</table>
<div id="querydiv" class="query_div">

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">

	<tr>
		<td class="detail2">
			
		</td>
	</tr>
</table>
</div>
</body>

<script language="JavaScript">

</script>
</html>

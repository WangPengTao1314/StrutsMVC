

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
	<script type="text/javascript" src="${ctx}/pages/drp/base/drpsaleprice/Drpsaleprice_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#upFile {
			margin: 0px auto; 
			width:400px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:100px;
			left:230px;
			top:110px;
			text-align:center;
			line-height: 100px;
			display: none;
		}
	</style>
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;渠道销售价格设置</td>
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
									<tr>
										<td width="8%" nowrap align="right" class="detail_label" >货品编号：</td>
										<td nowrap width="15%" class="detail_content">
											<input id="PRD_NO" json="PRD_NO" name="PRD_NO" type="text"	value="${params.PRD_NO}">
											<img align="absmiddle" name="selJGXX" style="cursor: hand"
												src="${ctx}/styles/${theme}/images/plus/select.gif"       
												onClick="selCommon('BS_21', true, 'PRD_NO', 'PRD_NO', 'forms[0]','PRD_NO,PRD_NAME,PRD_SPEC,BRAND,PAR_PRD_NAME', 'PRD_NO,PRD_NAME,PRD_SPEC,BRAND,PAR_PRD_NAME', 'selectParams')">
										</td>
										<td width="8%" nowrap align="right" class="detail_label">货品名称：</td>
										<td width="15%" class="detail_content">
											<input type="text" id="PRD_NAME" json="PRD_NAME" name="PRD_NAME" value="${params.PRD_NAME }"/>
										</td>
										<td width="8%" nowrap align="right" class="detail_label">规格型号：</td>
										<td width="15%" class="detail_content">
											<input id="PRD_SPEC" json="PRD_SPEC" name="PRD_SPEC" type="text"
																			inputtype="string"  autocheck="true"
																			 value="${params.PRD_SPEC}">
										</td>
										<td width="8%" align="right" class="detail_label">品牌：</td>
										<td width="15%" class="detail_content">
											<input id="BRAND" json="BRAND" name="BRAND" type="text"
																			inputtype="string"  autocheck="true"
																			value="${params.BRAND}">
										</td>
									</tr>
									<tr>
										<td width="8%" nowrap align="right" class="detail_label">货品分类：</td>
										<td width="15%" class="detail_content">
											<input id="PAR_PRD_NAME" json="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text"
																			inputtype="string"  autocheck="true"
																			 value="${params.PAR_PRD_NAME}">
										</td>
										<td width="8%" nowrap align="right" class="detail_label">制单时间从：</td>
										<td width="15%" class="detail_content">
											<input type="text" json="BEGIN_CRE_TIME" id="BEGIN_CRE_TIME"name="BEGIN_CRE_TIME" value="${params.BEGIN_CRE_TIME}" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
											<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);"/>
										</td>
										<td width="8%" nowrap align="right" class="detail_label">制单时间到：</td>
										<td width="15%" class="detail_content">
											<input type="text" json="END_CRE_TIME" id="END_CRE_TIME" value="${params.END_CRE_TIME }" name="END_CRE_TIME" autocheck="true" inputtype="string" label="制单时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
											<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);"/>
										</td>
										
										<td width="8%" nowrap align="right" class="detail_label"></td>
										<td width="15%" class="detail_content">
										</td>
									</tr>
									<tr>
										<td colspan="10" align="center" valign="middle" >
											<input id="q_search" type="button" class="btn" onclick="$('#queryForm').submit()" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
											<c:if test="${pvg.PVG_IMPORT eq 1  }">
											<input id="up" type="button" class="btn" value="导入(U)" title="Alt+U" accesskey="U">
											<input id="down" type="button" class="btn" value="导出(D)" title="Alt+D" accesskey="D">
											</c:if>
											<input  type="button" onclick="resetBtn()" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
											<input type="button" value="保存(S)" id="save" class="btn" title="Alt+S" accesskey="S"/>
											<div id="upFile">
										   		<input id="upInfo" value=""/>
										   		<p>
										   		<input value="关闭" type="button" class="btn" onclick="$('#upFile').hide();" id="close" style="margin-right: 10px;">
										   		<input id="tempUp" type="button" class="btn"  value="模版下载(T)" title="Alt+T" accesskey="T">
										   		<input id="type" value="" type="hidden">
										   </div>
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
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1"  cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"><input type="checkbox" style="height:12px;valign:middle" id="allChecked" /></th>
					<th nowrap align="center" dbname="PRD_NO">货品编号</th>
					<th nowrap align="center" dbname="PRD_NAME">货品名称</th>
					<th nowrap align="center" dbname="PRD_SPEC">规格型号</th>
					<th nowrap align="center" dbname="BRAND">品牌</th>
					<th nowrap align="center" dbname="FACT_PRICE">折扣价</th>
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
                        <td nowrap align="left" >${rst.BRAND}&nbsp;</td>
                        <td nowrap align="center">
                        	<input type="text" value="${rst.DECT_PRICE}" onclick="$('#eid${rr}').attr('checked','true');" size="5" name="DECT_PRICE"  json="DECT_PRICE"/>
                        	<input type="hidden" name="SALE_PRICE_ID" json="SALE_PRICE_ID" value="${rst.SALE_PRICE_ID}">
                        	<input type="hidden" name="PRD_ID" json="PRD_ID" value="${rst.PRD_ID}">
                        </td>
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

<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<%@ include file="/pages/common/uploadfile/importfile.jsp"%>
<script language="JavaScript">  
		SelDictShow("STATE","32","${params.STATE}","");
		uploadFile('upInfo', 'SAMPLE_DIR',true,function(){
	   		uploading();
	   });

</script>
</html>

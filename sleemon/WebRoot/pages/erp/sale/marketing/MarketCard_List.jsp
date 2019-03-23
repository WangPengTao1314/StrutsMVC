<!--
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10  
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>卡券</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketing/MarketCard_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		.opendiv{
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
	  <div class="tablayer" >
		<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                	<td nowrap>
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
					    <input id="add" type="button" class="btn" value="新增(A)" title="Alt+A" accesskey="A">
					    <%--
				   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
				   	   --%>
				   	   </c:if>
				   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
				   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
				   		<input id="start" type="button" class="btn" value="开启(S)" title="Alt+S" accesskey="S">
				   		<input id="stop" type="button" class="btn" value="关闭(E)" title="Alt+E" accesskey="E">
				   		<input id="exportExcel" type="button" class="btn" value="导出(D)" title="Alt+E" accesskey="D">
				   	   </c:if>
					   &nbsp;
					</td>
				</tr>
			</table>
		</div>
		<div id="testDiv" >
		 <div id="selectDiv" class="opendiv" style="border-style:solid;">
	   		<form action="#" id="selectForm">
	   		 <table id="selecttb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3" >
	   		    <tr>
	   		        <td width="10%" align="right" class="detail_label" colspan="6">
	   		         &nbsp;
	   				</td>
	   		    </tr>
	   		    <tr>
	   		        <td width="10%" align="right" class="detail_label" colspan="6">
	   		         &nbsp;
	   				</td>
	   		    </tr>
	   			<tr>
	   				<td width="10%" align="right" class="detail_label">
	   					<span style="font-size:14px;" >卡券类型:</span>
	   				</td>
	   				<td width="20%" class="detail_content">
	   					<select  id="CARD_TYPE" name="CARD_TYPE" json="CARD_TYPE" mustinput="true" inputtype="string" autocheck="true" label="卡券类型" ></select>
	   				</td>
	   				<td width="10%" align="right" class="detail_label">
	   					<span style="font-size: 14px;" >卡券面值:</span>
	   				</td>
	   				<td width="20%" class="detail_content">
	   					<input type="text" id="CARD_VALUE" name="CARD_VALUE" json="CARD_VALUE" mustinput="true" inputtype="float" valuetype="8,2" autocheck="true" label="卡券面值" style="width: 122px;"  value="${params.CARD_VALUE}" />
	   				</td>
	   				<td width="10%" align="right" class="detail_label">
	   					<span style="font-size: 14px;" >卡券数量:</span>
	   				</td>
	   				<td width="20%" class="detail_content">
	   					<input type="text" id="CARD_NUM" name="CARD_NUM" json="CARD_NUM"  mustinput="true" inputtype="int" autocheck="true" label="卡券数量"  maxlength="6" style="width: 122px;"   value="${params.CARD_NUM}" />
	   				</td>
	   			</tr>
	   			  <tr>
	   		        <td width="10%" align="right" class="detail_label" colspan="6">
	   		         &nbsp;
	   				</td>
	   		    </tr>
	   			<tr height="20px;" align="center">
	   				<td  align="center" colspan="6" class="detail_content">
	   				 <input class="btn" id="dosave" type="button" value="确定" onclick="dosaveCard();"/>  
	   				 <input onclick="closePage();" class="btn"  type="button"  style="margin-left: 20px;" value="关闭"/>
	   				</td>
	   			</tr>
	   		</table>
	   		</form>
	   </div>
	</div>			   
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
				    <th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_CARD_NO" >营销卡券编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_TYPE" >卡券类型</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_VALUE" >卡券面值</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >创建时间</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_SEQ_NO" >卡券序号</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th> 
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					 <tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle"  id="eid${rr}" value="${rst.MARKETING_CARD_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.MARKETING_CARD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_VALUE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_SEQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp;</td>
                     <input type="hidden" id="" name="state" value="${rst.STATE}"/>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
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
<form id="form1" action="#" method="post" >
	<input type="hidden" id="MARKETING_CARD_IDS" name="MARKETING_CARD_IDS" value=""/>
</form>
</body>
<script type="text/javascript">
 SelDictShow("CARD_TYPE","BS_144","${params.CARD_TYPE}","");
</script>
</html>
 
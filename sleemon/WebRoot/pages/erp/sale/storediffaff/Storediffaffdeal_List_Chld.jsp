<!--
 * @prjName:喜临门营销平台
 * @fileName:Storediffdeal_List
 * @author wzg
 * @time   2013-08-30 14:17:25 
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
	<title>浏览</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/storediffaff/Storediffaffdeal_List_Chld.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
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
			   		<input id="save" type="button" class="btn" value="保存(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
				   &nbsp;
				 </td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>	
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="DIFF_RSON" >差异类型</th>
                    <th  nowrap="nowrap" align="center" dbname="DIFF_NUM" >差异数量</th>
                    <th  nowrap="nowrap" align="center" dbname="DEAL_WAY" >处理方案</th>
                    <th  nowrap="nowrap" align="center" dbname="DIFF_ATT" >附件</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
				</tr>
				
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle" json="DIFF_DEAL_DTL_ID"  id="eid${rr}" value="${rst.DIFF_DEAL_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
					 <td nowrap="nowrap" align="right" >${rst.PRICE}&nbsp;</td>
					 <td nowrap="nowrap" align="left" >
						<select id="DIFF_RSON${rr}" name="DIFF_RSON${rr}" onchange="changeDealway('${rr}')" json="DIFF_RSON" autocheck="true" label="差异类型" type="text" inputtype="string" mustinput="true" maxlength="30" disabled="disabled"   value="${rst.DIFF_RSON}"></select>
					 </td>			 
                     <td nowrap="nowrap" align="right" >${rst.DIFF_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >
						<select id="DEAL_WAY${rr}" name="DEAL_WAY${rr}" json="DEAL_WAY" autocheck="true" label="差异数量" type="text" inputtype="string" mustinput="true" maxlength="30" disabled="disabled"  value="${rst.DEAL_WAY}"></select>
					 </td>
                     <td nowrap="nowrap" align="left" ><input type="hidden" id ="DIFF_ATT${rr}" value="${rst.DIFF_ATT}" />&nbsp;&nbsp;</td>
                     <td nowrap="nowrap" align="left" ><input type="text" id="REMARK${rr}" json="REMARK" name="REMARK" maxlength="120" value="${rst.REMARK}"/>&nbsp;</td>
				    </tr>
				    <script type="text/javascript">
				        displayDownFile('DIFF_ATT${rr}','SAMPLE_DIR',true,false);
				        SelDictShow("DIFF_RSON${rr}","BS_30",'${rst.DIFF_RSON}',"");
				        SelDictShow("DEAL_WAY${rr}","BS_31",'${rst.DEAL_WAY}',"");
				    </script>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="11" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="DIFF_DEAL_DTL_IDS" name="DIFF_DEAL_DTL_IDS" value=""/>
</form>
</body>
</html>
<!--
 * @prjName:喜临门营销平台
 * @fileName:Trainresult_List
 * @author ghx
 * @time   2014-07-10 
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/oamg/trainresult/Trainresult_List_Chld.js"></script>
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
			   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
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
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>                    
                    <th nowrap align="center" dbname="TRAIN_PER_NO">培训人员编号</th>
	                <th nowrap align="center" dbname="TRAIN_PER_NAME">培训人员姓名</th>
	                <th nowrap align="center" dbname="THE_ORGAN">所在部门/渠道/终端</th>
	                <th nowrap align="center" dbname="THE_POST">岗位</th>
	                <th nowrap align="center" dbname="SIGN_STATE">培训签到情况</th>
	                <th nowrap align="center" dbname="TRAIN_PER">培训过程表现情况</th>
	                <th nowrap align="center" dbname="TRAIN_ASSES">培训考核情况</th>
	                <th nowrap align="center" dbname="TRAIN_OVERALL">该次培训总体情况</th>
		            <th nowrap align="center" dbname="STATE">备注</th>              
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.TRAIN_RESULT_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" json='TRAIN_PER_NO' >${rst.TRAIN_PER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='TRAIN_PER_NAME' >${rst.TRAIN_PER_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='THE_ORGAN' >${rst.THE_ORGAN}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='THE_POST' >${rst.THE_POST}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='SIGN_STATE' >${rst.SIGN_STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='TRAIN_PER' >${rst.TRAIN_PER}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='TRAIN_ASSES' >${rst.TRAIN_ASSES}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='TRAIN_OVERALL' >${rst.TRAIN_OVERALL}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='REMARK' >${rst.REMARK}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="10" align="center" class="lst_empty">
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
	<input type="hidden" id="TRAIN_RESULT_DTL_IDS" name="TRAIN_RESULT_DTL_IDS" value=""/>
</form>
</body>
</html>
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">	
	<title>培训对象明细</title>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>                    
                    <th nowrap align="center" dbname="STATE">培训类型</th>
		            <th nowrap align="center" dbname="STATE">培训对象编号</th>
		            <th nowrap align="center" dbname="STATE">培训对象名称</th>
		            <th nowrap align="center" dbname="STATE">备注</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.TRAIN_REQ_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" json='TRAIN_TYPE' >${rst.TRAIN_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='TRAIN_OBJECT_NO' >${rst.TRAIN_OBJECT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='TRAIN_OBJECT_NAME' >${rst.TRAIN_OBJECT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='REMARK' >${rst.REMARK}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="5" align="center" class="lst_empty">
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
	<input type="hidden" id="TRAIN_REQ_DTL_IDS" name="TRAIN_REQ_DTL_IDS" value=""/>
</form>
</body>
</html>
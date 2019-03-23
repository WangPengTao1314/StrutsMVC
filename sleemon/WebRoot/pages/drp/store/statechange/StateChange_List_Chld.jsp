<!--
 * @prjName:喜临门营销平台
 * @fileName:形态转换
 * @author zzb
 * @time   2013-09-05 14:00:34 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/statechange/StateChange_List_Chld.js"></script>
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
                    <th  nowrap="nowrap" align="center" dbname="STORE_NO" >库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NAME" >库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_CHANGE_PRD_NO" >转换前货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_CHANGE_PRD_NAME" >转换前货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_DM_SPCL_TECH_NO" >转换前特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_CHANGE_SPCL_TECH_ID" >转换前订单特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_PRD_NO" >转换后货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_PRD_NAME" >转换后货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_DM_SPCL_TECH_NO" >转换后特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_SPCL_TECH_ID" >转换后订单特殊工艺</th>
                    
                    <th  nowrap="nowrap" align="center" dbname="CHANGE_NUM" >转换数量</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.STATE_CHANGE_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.STORE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PAR_CHANGE_PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_CHANGE_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PAR_DM_SPCL_TECH_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                        <c:if test="${empty rst.PAR_SPCL_TECH_FLAG || rst.PAR_SPCL_TECH_FLAG eq 0}">
                                                                             无
                        </c:if>
					 	<c:if test="${rst.PAR_SPCL_TECH_FLAG ge 1}">
					 		<font color="red"> 有 </font>
                       		<input type="button" class="btn" value="查看" onclick="url('${rst.PAR_CHANGE_SPCL_TECH_ID}')"/>
					 	</c:if>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.CHANGED_PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.CHANGED_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANGED_DM_SPCL_TECH_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                        <c:if test="${empty rst.CHANGED_SPCL_TECH_FLAG || rst.CHANGED_SPCL_TECH_FLAG eq 0}">
                                                                             无
                        </c:if>
					 	<c:if test="${rst.CHANGED_SPCL_TECH_FLAG ge 1}">
					 		<font color="red"> 有 </font>
                       		<input type="button" class="btn" value="查看" onclick="url('${rst.CHANGED_SPCL_TECH_ID}')"/>
					 	</c:if>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.CHANGE_NUM}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="12" align="center" class="lst_empty">
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
	<input type="hidden" id="STATE_CHANGE_DTL_IDS" name="STATE_CHANGE_DTL_IDS" value=""/>
</form>
</body>
</html>
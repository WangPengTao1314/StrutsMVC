<!--
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_List
 * @author lyg
 * @time   2014-01-26 14:46:31 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/termrefinecheck/Termrefinecheck_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/termrefinecheck/Termrefinecheck_Edit_T.js"></script>
</head>
<body>
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
			   	   <c:if test="${'sh' eq module}">
			   	     <c:if test="${pvg.PVG_SAVE_AUDIT eq 1 }">
			   	      <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			   	     </c:if> 
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
		   <form action="" id="childForm" name="childForm">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRJ_TYPE" >门店精致化检查项目类型</th>
                    <th  nowrap="nowrap" align="center" dbname="PRJ_CODE" >检查项目编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRJ_NAME" >检查项目名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRJ_SCORE" >项目分值</th>
                    <th  nowrap="nowrap" align="center" dbname="CHECK_REMARK" >扣分原因</th>
                    <th  nowrap="nowrap" align="center" dbname="CHECK_SCORE" >检查结果得分</th>
                    <c:if test="${'sh' eq module}">
                      <th nowrap="nowrap" align="center"  dbname="CHECK_FINISH_SCORE" >最终得分</th>
                    </c:if>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.TERM_REFINE_CHECK_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PRJ_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRJ_CODE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRJ_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRJ_SCORE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHECK_REMARK}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHECK_SCORE}&nbsp;</td>
                     <c:if test="${'sh' eq module}">
	                     <td>
	                      <c:if test="${pvg.PVG_SAVE_AUDIT ne 1}">
	                        ${rst.CHECK_FINISH_SCORE}
	                      </c:if>
	                      <c:if test="${pvg.PVG_SAVE_AUDIT eq 1}">
	                      <input type="hidden" json="TERM_REFINE_CHECK_DTL_ID" value="${rst.TERM_REFINE_CHECK_DTL_ID}"/>
	                      <input type="text" name="CHECK_FINISH_SCORE" json="CHECK_FINISH_SCORE" 
	                       autocheck="true" mustinput="true" inputtype="float" valuetype="8,2" label="最终得分"
	                      value="${rst.CHECK_FINISH_SCORE}"/>
	                      </c:if>
	                     </td>
	                 </c:if>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="11" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
			</form>
			 
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="TERM_REFINE_CHECK_DTL_IDS" name="TERM_REFINE_CHECK_DTL_IDS" value=""/>
</form>
</body>
</html>
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/oamg/trainresult/Trainresult_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;渠道培训管理&gt;&gt;渠道培训结果反馈</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>				   
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>				   
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
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="TRAIN_REQ_NO" >培训申请单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >申请加盟商编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >申请加盟商名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_COURSE_NO" >培训课程编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_COURSE_NAME" >培训课程名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_TYPE" >培训类型</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_TIME_BEG" >培训时间从</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_TIME_END" >培训时间到</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_ADDR" >培训地点</th>
                    <th  nowrap="nowrap" align="center" dbname="REQ_JOIN_NUM" >申请参加人数</th>
                    <th  nowrap="nowrap" align="center" dbname="REQ_REASON" >申请理由</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.TRAIN_REQ_ID}"/>
					 </td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_REQ_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_COURSE_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_COURSE_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_TYPE}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_TIME_BEG}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_TIME_END}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REQ_JOIN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REQ_REASON}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.TRAIN_REQ_ID}" type="hidden"  value="${rst.STATE}" />
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
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
                    <td width="8%" nowrap align="right" class="detail_label">培训申请单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TRAIN_REQ_NO" name="TRAIN_REQ_NO"  style="width:155" value="${params.TRAIN_REQ_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">培训课程:</td>
					<td width="15%" class="detail_content">
						<input id="TRAIN_COURSE_NAME" name="TRAIN_COURSE_NAME"  style="width:155" value="${params.TRAIN_COURSE_NAME}"/>
					</td>                    
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">培训类型:</td>
					<td width="15%" class="detail_content">	   					
	   					<select id="TRAIN_TYPE" name="TRAIN_TYPE"  style="width: 155px;" ></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">培训地点:</td>
					<td width="15%" class="detail_content">
					    <input id="TRAIN_ADDR" name="TRAIN_ADDR"  style="width:155" value="${params.TRAIN_ADDR}"/>
					</td>            
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">申请理由:</td>
					<td width="15%" class="detail_content">
	   					<input id="REQ_REASON" name="REQ_REASON"  style="width:155" value="${params.REQ_REASON}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">&nbsp;</td>
					<td width="15%" class="detail_content">
	   					&nbsp;
					</td>            
               </tr>
               <tr>
					<td colspan="4" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script type="text/javascript">	
    SelDictShow("TRAIN_TYPE", "BS_103", '${params.TRAIN_TYPE}', "");
</script>
</html>
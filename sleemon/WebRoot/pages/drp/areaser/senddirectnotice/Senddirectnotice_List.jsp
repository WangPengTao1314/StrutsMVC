<!--
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_List
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/senddirectnotice/Senddirectnotice_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：区域服务中心管理&gt;&gt;发货管理&gt;&gt;总部直发通知单</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
                   <c:if test="${pvg.PVG_FINISH_CANCLE eq 1 }">
				    <input id="audit" type="button" class="btn" value="处理(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
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
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="BASE_DELIVER_NOTICE_NO" >直发通知单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >发货时间</th>
                    <th  nowrap="nowrap" align="center" dbname="PRVD_NAME" >物流商名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TRUCK_TYPE" >车型</th>
                    <th  nowrap="nowrap" align="center" dbname="TRUCK_NO" >车牌号</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.BASE_DELIVER_NOTICE_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.BASE_DELIVER_NOTICE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADVC_SEND_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRVD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRUCK_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TRUCK_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.BASE_DELIVER_NOTICE_ID}" type="hidden"  value="${rst.STATE}" />
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
<form id="queryForm" name="queryForm" method="post" action="#">
<input id="selectNoticeParam" name="selectNoticeParam" type="hidden" value=" DEL_FLAG=0 and ${params.QXJBCON} "/>
<input id="selectChann" name="selectChann" type="hidden" value=" DEL_FLAG=0 and STATE in('启用','停用') and AREA_SER_ID='${params.AREA_SER_ID}' "/>
<input id="selectDELIVERParam" name="selectDELIVERParam" type="hidden" value=" DEL_FLAG=0 and RECV_CHANN_ID in (select CHANN_ID from BASE_CHANN where DEL_FLAG=0 and AREA_SER_ID='${params.AREA_SER_ID}') and (BILL_TYPE='销售发运' or BILL_TYPE='促销品发运') "/>


<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">直发通知单编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="BASE_DELIVER_NOTICE_ID" name="BASE_DELIVER_NOTICE_ID"/>
	   					<input id="BASE_DELIVER_NOTICE_NO" name="BASE_DELIVER_NOTICE_NO"  style="width:155" value="${params.BASE_DELIVER_NOTICE_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     		onclick="selCommon('BS_92', false, 'BASE_DELIVER_NOTICE_ID', 'BASE_DELIVER_NOTICE_ID', 'queryForm','BASE_DELIVER_NOTICE_NO,RECV_CHANN_NO,RECV_CHANN_NAME', 'BASE_DELIVER_NOTICE_NO,RECV_CHANN_NO,RECV_CHANN_NAME','selectNoticeParam');"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
						<input id="RECV_CHANN_ID" type="hidden" name="RECV_CHANN_ID"  style="width:155" value=""/>
	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     		onclick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'queryForm','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectChann');"/>&nbsp;
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  style="width:155" value="${params.RECV_CHANN_NAME}"/>
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>				
                    					
               </tr>
              
               <tr>
                   <td width="8%" nowrap align="right" class="detail_label">来源单据编号:</td>
				  <td width="15%" class="detail_content">
					    <input type="hidden" id="DELIVER_ORDER_ID" name="DELIVER_ORDER_ID"/>
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     		onclick="selCommon('BS_68', false, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'queryForm','FROM_BILL_NO', 'DELIVER_ORDER_NO','selectDELIVERParam');"/>
					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">发货时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="ADVC_SEND_DATE_BEGIN" name="ADVC_SEND_DATE_BEGIN" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.ADVC_SEND_DATE_BEGIN }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(ADVC_SEND_DATE_BEGIN);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">发货时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="ADVC_SEND_DATE_END" name="ADVC_SEND_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.ADVC_SEND_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(ADVC_SEND_DATE_END);">
					</td>
                     
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
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
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
	 SelDictShow("STATE","BS_27","${params.STATE}","");
</script>
</html>

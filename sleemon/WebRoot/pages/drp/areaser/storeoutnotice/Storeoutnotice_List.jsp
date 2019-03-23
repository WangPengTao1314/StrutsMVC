<!--
 * @prjName:喜临门营销平台
 * @fileName:Storeounotice_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/storeoutnotice/Storeoutnotice_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：区域服务中心管理&gt;&gt;发货管理&gt;&gt;销售订单发货通知</td>
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
                  	<c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
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
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_NOTICE_NO" >销售出库通知单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_STORE_NAME" >出库库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_AMOUNT" >出库总金额</th>
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
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREOUT_NOTICE_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREOUT_NOTICE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SEND_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" id="STOREOUT_AMOUNT${rst.SALE_ORDER_ID}">${rst.STOREOUT_AMOUNT}</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <input id="state${rst.STOREOUT_NOTICE_ID}" type="hidden"  value="${rst.STATE}" />
                     <input id="from${rst.STOREOUT_NOTICE_ID}" type="hidden"  value="${rst.FROM_BILL_ID}" />
                     <input id="STORE_ID${rst.STOREOUT_NOTICE_ID}" type="hidden"  value="${rst.STOREOUT_STORE_ID}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<!--endprint1--> 
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
<form id="queryForm" method="post" action="#">
	<input type="hidden" name="selectFrom" value=" DEL_FLAG=0 and LEDGER_ID='${LEDGER_ID}' and STATE='审核通过' and SALE_ORDER_ID in (select SALE_ORDER_ID from DRP_SALE_ORDER_DTL where (ORDER_NUM-NVL(SENDED_NUM,0))>0) ">
	<input type="hidden" name=selectReceiveChann value=" STATE in ('启用','停用') and DEL_FLAG=0  and AREA_SER_ID='${LEDGER_ID}' ">
	<input type="hidden" name="selectChann" value=" STATE in ('启用','停用') and DEL_FLAG=0  and CHANN_ID='${LEDGER_ID}' ">
	<input type="hidden" name="selectParam" value=" DEL_FLAG=0 and LEDGER_ID='${LEDGER_ID}'  ">
	<input type="hidden" name="SelectStore" value=" STATE in ('启用','停用') and DEL_FLAG=0 and BEL_CHANN_ID='${LEDGER_ID}' ">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">销售出库通知单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREOUT_NOTICE_NO" name="STOREOUT_NOTICE_NO"  style="width:155" value="${params.STOREOUT_NOTICE_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_79', false, 'STOREOUT_NOTICE_NO', 'STOREOUT_NOTICE_NO', 'forms[1]','STOREOUT_NOTICE_NO,FROM_BILL_NO,SEND_CHANN_NAME,RECV_CHANN_NAME,STOREOUT_STORE_NAME', 'TOREOUT_NOTICE_NO,FROM_BILL_NO,SEND_CHANN_NAME,RECV_CHANN_NAME,STOREOUT_STORE_NAME', 'selectParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">来源单据编号:</td>
					<td width="15%" class="detail_content">
	   					<input json="FROM_BILL_NO" name="FROM_BILL_NO" autocheck="true" label="来源单据编号" type="text" inputtype="string"   value="${params.FROM_BILL_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_80', false, 'FROM_BILL_NO', 'SALE_ORDER_NO', 'forms[1]','FROM_BILL_NO', 'SALE_ORDER_NO', 'selectFrom')">
					</td>					
<!--                    <td width="8%" nowrap align="right" class="detail_label">发货方编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="SEND_CHANN_NO" name="SEND_CHANN_NO"  style="width:155" value="${params.SEND_CHANN_NO}"/>-->
<!--					</td>-->
					<td width="8%" nowrap align="right" class="detail_label">发货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  style="width:155" value="${params.SEND_CHANN_NAME}"/>
	   					<img align="absmiddle"  style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'SEND_CHANN_NAME', 'CHANN_NAME', 'forms[1]','SEND_CHANN_NAME', 'CHANN_NAME','selectChann')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME" autocheck="true" label="收货方名称" type="text" inputtype="string"   value="${params.RECV_CHANN_NAME}"/>
	   					<img align="absmiddle"  style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'RECV_CHANN_NAME', 'CHANN_NAME', 'forms[1]','RECV_CHANN_NAME', 'CHANN_NAME','selectReceiveChann')">
					</td>	
               </tr>
               <tr>
<!--                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>-->
<!--					</td>					-->
                    				
<!--                    <td width="8%" nowrap align="right" class="detail_label">出库库房编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO"  style="width:155" value="${params.STOREOUT_STORE_NO}"/>-->
<!--					</td>-->
					<td width="8%" nowrap align="right" class="detail_label">出库库房名称:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="STORE_ID" name="STORE_ID" />
	   					<input id="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  style="width:155" value="${params.STOREOUT_STORE_NAME}"/>
	   					<img align="absmiddle"  style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_35', false, 'STOREOUT_STORE_NAME', 'STORE_NAME', 'forms[1]','STOREOUT_STORE_NAME', 'STORE_NAME','SelectStore')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" readonly onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEGIN}"    />
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);" >
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly style="width:155" onclick="SelectTime();" value="${params.CRE_TIME_END}"/>
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
					</td>
               </tr>
<!--               <tr>-->
<!--               		<td width="8%" nowrap align="right" class="detail_label">销售日期从:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="SALE_DATE_BEG" name="SALE_DATE_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.SALE_DATE_BEG }">-->
<!--	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_BEG);"  >-->
<!--					</td>-->
<!--					<td width="8%" nowrap align="right" class="detail_label">销售日期到:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="SALE_DATE_END" name="SALE_DATE_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.SALE_DATE_END }">-->
<!--	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_END);" >-->
<!--					</td>-->
<!--               </tr>-->
               <tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
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
<%@ include file="/pages/common/uploadfile/importfile.jsp"%>
</html>
<script type="text/javascript">
//初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
       SelDictShow("STATE","196","${params.STATE}","");   
</script>
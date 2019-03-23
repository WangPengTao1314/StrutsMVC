<!--
 * @prjName:喜临门营销平台
 * @fileName:Promoexpen_List
 * @author chenj
 * @time   2014-01-24 10:59:55 
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
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				 
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
                    <th  nowrap="nowrap" align="center" >推广费用申请单编号</th>
                    <th  nowrap="nowrap" align="center"  >加盟商名称</th>
                    <th  nowrap="nowrap" align="center"  >加盟商联系人</th>
                    <th  nowrap="nowrap" align="center"  >加盟商联系电话</th>
                    <th  nowrap="nowrap" align="center"  >所属战区</th>
                    <th  nowrap="nowrap" align="center"  >区域经理</th>
                    <th  nowrap="nowrap" align="center"  >申请报销金额</th>
                    <th  nowrap="nowrap" align="center" >费用类别</th>
                    <th  nowrap="nowrap" align="center"  >申请人姓名</th>
                    <th  nowrap="nowrap" align="center"  >申请时间</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRMT_COST_REQ_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PRMT_COST_REQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.REIT_REQ_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.COST_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.REQ_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.REQ_DATE}&nbsp;</td>
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
                    <td width="8%" nowrap align="right" class="detail_label">推广费用申请单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="PRMT_COST_REQ_NO" name="PRMT_COST_REQ_NO"  style="width:155" value="${params.PRMT_COST_REQ_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">申请人姓名:</td>
					<td width="15%" class="detail_content">
	   					<input id="REQ_NAME" name="REQ_NAME"  style="width:155" value="${params.REQ_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CHANN_NAME" name="CHANN_NAME"  style="width:155" value="${params.CHANN_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">区域经理名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME"  style="width:155" value="${params.AREA_MANAGE_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">费用类别:</td>
					<td width="15%" class="detail_content">
	   					<select id="COST_TYPE" name="COST_TYPE" json="COST_TYPE" style="width:155" value="${params.COST_TYPE}" />
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select name=STATE id="STATE" json="STATE" style="width: 155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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
</html>
<script type="text/javascript">
    
    SelDictShow("COST_TYPE","BS_92","${params.COST_TYPE}","");
   
	//如果是审核界面
	if($("#audit").val()=="1"){
		SelDictShow("STATE","199","${params.STATE}","");
	}else{
		SelDictShow("STATE","189","${params.STATE}","");
	}
    //初始化 审批流程
    spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
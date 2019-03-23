<!-- 
 *@module 营销管理-广告投放管理
 *@func   广告投放报销申请单维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>广告投放报销申请单维护列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/advreit/Advreit_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
		<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
			   
			   <td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0" >
				<tr>			
					<c:if test="${empty module ||module eq 'wh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：营销管理>>广告投放管理>>广告投放报销申请单维护</td>
					</c:if>
					<c:if test="${ module eq 'sh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：营销管理>>广告投放管理>>广告投放报销申请单审核</td>
					</c:if>			
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
						   <td id="qxBtnTb" nowrap>
						   	    <c:if test="${pvg.PVG_EDIT eq 1 }">
						   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
							    <input id="modifyT" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
							   </c:if>
							   <c:if test="${pvg.PVG_DELETE eq 1 }">
						   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
							   </c:if>
							   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
								<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
								<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
							   </c:if>
							   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
							    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
							   </c:if>
							   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
							    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
							   </c:if>
							   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
						   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
							   </c:if>
						    </td>
						 </tr>
					</table>
				</div>
			</td>
		</tr>	
		<tr>
	     <td valign="middle">
		   <div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="ERP_ADV_REIT_NO" >广告投放报销申请单号</th>
					<th nowrap align="center" dbname="ERP_ADV_REQ_NO" >关联广告投放申请单号</th>
					<th nowrap align="center" dbname="CHANN_NO" >渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME" >渠道名称</th>
					<th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属战区</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_MANAGE_NAME" >区域经理</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_CONTENT" >广告投放内容</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_ADDR" >广告投放地点</th>
					<th  nowrap="nowrap" align="center" dbname="REIT_AMOUNT" >申请报销金额</th>		
					<th  nowrap="nowrap" align="center" dbname="RNVTN_REQ_NAME" >申请人</th>
					<th  nowrap="nowrap" align="center" dbname="RNVTN_REQ_DATE" >申请时间</th>		
					<th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ERP_ADV_REIT_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
							<input type="hidden" name="ERP_ADV_REIT_ID" id="ERP_ADV_REIT_ID" value="${rst.ERP_ADV_REIT_ID}"/>
							<input type="hidden" id="state${rst.ERP_ADV_REIT_ID}" value="${rst.STATE}"/>
						</td>
						<td align="center">${rst.ERP_ADV_REIT_NO}&nbsp;</td>
						<td align="center">${rst.ERP_ADV_REQ_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME}&nbsp;</td>
						<td align="center">${rst.AREA_NAME}&nbsp;</td>
						<td align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
						<td align="center">${rst.ADV_CONTENT}&nbsp;</td>
						<td align="center">${rst.ADV_ADDR}&nbsp;</td>
						<td align="center">${rst.REIT_AMOUNT}&nbsp;</td>
						<td align="center">${rst.RNVTN_REQ_NAME}&nbsp;</td>
						<td align="center">${rst.RNVTN_REQ_DATE}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.ERP_ADV_REIT_ID}'));
						   });
					    </script>
						
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
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
									<input type="hidden" id="pageSize" name="pageSize"
										value='${page.pageSize}' />
									<input type="hidden" id="pageNo" name="pageNo"
										value='${page.currentPageNo}' />
									<input type="hidden" id="orderType" name="orderType"
										value='${orderType}' />
									<input type="hidden" id="orderId" name="orderId"
										value='${orderId}' />
									<input type="hidden" id="selRowId" name="selRowId"
										value="${selRowId}">
									&nbsp;
									<input type="hidden" id="paramUrl" name="paramUrl"
										value="${paramCover.coveredUrl}">
									<span id="hidinpDiv" name="hidinpDiv"></span>
									${paramCover.unCoveredForbidInputs}
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
					<td nowrap align="right" class="detail_label">广告投放报销申请单号：</td>
					<td class="detail_content">
						<input name="ERP_ADV_REIT_NO" id="ERP_ADV_REIT_NO" json="ERP_ADV_REIT_NO"  type="text"  value="${params.ERP_ADV_REIT_NO}"/>
					</td>
					 <td nowrap align="right" class="detail_label">关联广告投放申请单号：</td>
		      		<td nowrap class="detail_content">
		                <input id="ERP_ADV_REQ_NO" name="ERP_ADV_REQ_NO" json="ERP_ADV_REQ_NO" style="width: 155px;" value="${params.ERP_ADV_REQ_NO}">
		      		</td>
		      	   </tr>
		      	   <tr>
		      		<td nowrap align="right" class="detail_label">渠道编号：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="CHANN_NO" id="CHANN_NO" json="CHANN_NO" value="${params.CHANN_NO}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">渠道名称：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="CHANN_NAME" id="CHANN_NAME" json="CHANN_NAME" value="${params.CHANN_NAME}"/>
		      		</td>	
				  </tr>
				  <tr>
		      		<td nowrap align="right" class="detail_label">所属战区：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="AREA_NAME" id="AREA_NAME" json="AREA_NAME" value="${params.AREA_NAME}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">区域经理：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" value="${params.AREA_MANAGE_NAME}"/>
		      		</td>	
				</tr>
				
				<tr>
		      		<td nowrap align="right" class="detail_label">申请人</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="RNVTN_REQ_NAME" id="RNVTN_REQ_NAME" json="RNVTN_REQ_NAME" value="${params.RNVTN_REQ_NAME}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content">
		                <select style="width:155px;" name="STATE" id="STATE" json="STATE" value="${params.STATE}"></select>
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
<input type="hidden" name="ORDER_CHANN_ID" json="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="${rst.CHANN_ID}"/>
<input type="hidden" name="ORDER_CHANN_NO" json="ORDER_CHANN_NO" id="ORDER_CHANN_NO" value="${rst.CHANN_NO}"/>
<input type="hidden" name="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME" value="${rst.CHANN_NAME}"/>
<input type="hidden" name="AREA_ID" json="AREA_ID" id="AREA_ID" value="${rst.AREA_ID}"/>
<input type="hidden" name="AREA_NO" json="AREA_NO" id="AREA_NO" value="${rst.AREA_NO}"/>
<input type="hidden" name="AREA_NAME" json="AREA_NAME" id="AREA_NAME" value="${rst.AREA_NAME}"/>
<input type="hidden" name="CHANN_ID" id="CHANN_ID" value="${rst.CHANN_ID}"/>
<input type="hidden"   name="module" id="module" value="${module}"/>
<input type="hidden" name="PVG_EDIT_AUDIT" id="PVG_EDIT_AUDIT" value="${pvg.PVG_EDIT_AUDIT}"/>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script language="JavaScript">
   SelDictShow("STATE","33","${params.STATE}","");  
   SelDictShow("RNVTN_PROP","BS_87","${params.RNVTN_PROP}","");
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
 
</html>
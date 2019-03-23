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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/promoexpen/Promoexpen_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${empty module || module eq 'wh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：推广费用管理&gt;&gt;推广费用申请单维护</td>
			</c:if>
			<c:if test="${module eq 'sh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：推广费用管理&gt;&gt;推广费用申请单审核</td>
			</c:if>	
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<input id="audit1" value="${audit}" type="hidden"/>
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
                   <c:if test="${module eq 'wh'}">
                          <c:if test="${pvg.PVG_EDIT eq 1}">
                              <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
                              <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U" />
                          </c:if>
                   </c:if>
                   <c:if test="${module eq 'sh'}">
                        <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">
                              <input id="modifyT" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U" />
                          </c:if>
                   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
				   	<c:if test="${audit ne '1'}">
			   			<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
			   		</c:if>
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
				   <c:if test="${pvg.PVG_BWS eq 1 || pvg.PVG_BWS_AUDIT eq 1}">
				    <input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				    <input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">
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
                    <th  nowrap="nowrap" align="center"  >推广费用申请单编号</th>
                    <th  nowrap="nowrap" align="center"  >加盟商名称</th>
                    <th  nowrap="nowrap" align="center"  >加盟商联系人</th>
                    <th  nowrap="nowrap" align="center"  >加盟商联系电话</th>
                    <th  nowrap="nowrap" align="center"  >所属战区</th>
                    <th  nowrap="nowrap" align="center"  >区域经理</th>
                    <th  nowrap="nowrap" align="center"  >费用类别</th>
                    <th  nowrap="nowrap" align="center"  >申请人</th>
                    <th  nowrap="nowrap" align="center"  >申请时间</th>
                    <th  nowrap="nowrap" align="center"  >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
					<td width="1%" align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRMT_COST_REQ_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
						<input type="hidden" name="PRMT_COST_REQ_ID" id="PRMT_COST_REQ_ID" value="${rst.PRMT_COST_REQ_ID}"/>
						<input type="hidden" id="state${rst.PRMT_COST_REQ_ID}" value="${rst.STATE}"/>
					</td>
                     <td nowrap="nowrap" align="center" >${rst.PRMT_COST_REQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.COST_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.REQ_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.REQ_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp;<input type="hidden" name="state" id="state${rst.STATE}" value="${rst.STATE}"/></td>
                     <script type="text/javascript">
					   $("#tr${rr}").click(function(){
					      selectThis(this);
					      setSelEid(document.getElementById('eid${rr}'));
					      state(document.getElementById('state${rst.PRMT_COST_REQ_ID}'));
					   });
					 </script>
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
						<input type="hidden" id="search" name="search" value="${search}" />
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
	   					<input id="PRMT_COST_REQ_NO" name="PRMT_COST_REQ_NO" json="PRMT_COST_REQ_NO" style="width:155" value="${params.PRMT_COST_REQ_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select name=STATE id="STATE" json="STATE" style="width: 155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">申请人:</td>
					<td width="15%" class="detail_content">
					    <input name="REQ_NAME" id="REQ_NAME" json="REQ_NAME"  value="${params.REQ_NAME}"/>
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">战区名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NAME" name="AREA_NAME" json="AREA_NAME" style="width:155" value="${params.AREA_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">加盟商名称:</td>
					<td width="15%" class="detail_content" cols="7">
					    <input name="CHANN_NAME" id="CHANN_NAME" json="CHANN_NAME"  value="${params.CHANN_NAME}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">申请日期:</td>
					<td width="15%" class="detail_content">
					    <input name="REQ_DATE" id="REQ_DATE" json="REQ_DATE"  value="${params.REQ_DATE}" onclick="SelectDate()"/>
					    <img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(REQ_DATE);">
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
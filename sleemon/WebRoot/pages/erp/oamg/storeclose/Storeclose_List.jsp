<!--
 * @prjName:喜临门营销平台
 * @fileName:Storeclose_Frame
 * @author ghx
 * @time   2014-05-30 
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
	<script type="text/javascript" src="${ctx}/pages/erp/oamg/storeclose/Storeclose_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>			
			<c:if test="${module eq 'wh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;转撤店管理&gt;&gt;专卖店撤店及终止申请单维护</td>
			</c:if>
			<c:if test="${empty module || module eq 'sh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;转撤店管理&gt;&gt;专卖店撤店及终止申请单审核</td>
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
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
				    <input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
			   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
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
					<th  nowrap="nowrap" align="center" dbname="PCL_STORE_CC_REQ_NO" >专卖店撤店及申请单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_CANCEL_TYPE" >撤店类型</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属战区名称</th>
                    <th  nowrap="nowrap" align="center" dbname="BEG_SBUSS_DATE" >开店日期</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_MANAGE_NAME" >区域经理</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_PERSON_CON" >渠道联系人</th>
                    <!--  
                    <th  nowrap="nowrap" align="center" dbname="CHANN_TEL" >渠道联系电话</th>
                    -->
                    <th  nowrap="nowrap" align="center" dbname="BUSS_SCOPE" >经营品牌</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="SPCL_STORE_CANCEL_DATE" >专卖店撤店日期</th>-->
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >申请时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.SPCL_STORE_CC_REQ_ID}"/>
					 </td>
					 <td nowrap="nowrap" align="center">${rst.PCL_STORE_CC_REQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STORE_CANCEL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BEG_SBUSS_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_PERSON_CON}&nbsp;</td>
                     <!--  
                     <td nowrap="nowrap" align="center">${rst.CHANN_TEL}&nbsp;</td>
                     -->
                     <td nowrap="nowrap" align="center">${rst.BUSS_SCOPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
<!--                 <td nowrap="nowrap" align="center">${rst.SPCL_STORE_CANCEL_DATE}&nbsp;</td>-->
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.SPCL_STORE_CC_REQ_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
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
            <input type="hidden" name=selectTERM value="STATE in ('启用') and DEL_FLAG=0 and CHANN_ID_P in ${CHANNS_CHARG}">
			<input type="hidden" name="selectParamsChann" value="STATE in( '启用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG}">
			<input type="hidden" name="selectParamsArea" value="STATE in( '启用') and DEL_FLAG='0'">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">专卖店撤店及申请单号:</td>
					<td width="15%" class="detail_content">
	   					<input id="PCL_STORE_CC_REQ_NO" name="PCL_STORE_CC_REQ_NO" json="PCL_STORE_CC_REQ_NO" style="width:155" value="${params.PCL_STORE_CC_REQ_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道编号:</td>
					<td width="15%" class="detail_content">
					    <input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden" style="width:155" value="${params.CHANN_ID}"/>
	   					<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" style="width:155" value="${params.CHANN_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_NO', 'CHANN_NO', 'forms[1]',
										'CHANN_ID,CHANN_NO', 
										'CHANN_ID,CHANN_NO', 
										'selectParamsChann');">  
					</td>                    		
               </tr>
               <tr>
              	    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
						<input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${params.TERM_ID}"/>
	   					<input id="TERM_NO" name="TERM_NO" json="TERM_NO" style="width:155" value="${params.TERM_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_NO', 'TERM_NO', 'forms[1]','TERM_ID,TERM_NO,TERM_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectTERM')"> 
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NAME" name="TERM_NAME" json="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
					</td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">所属战区编号:</td>
					<td width="15%" class="detail_content">
						<input id="AREA_ID" name="AREA_ID"  json="AREA_ID" type="hidden" style="width:155" value="${params.AREA_ID}"/>
						<input id="AREA_NO" name="AREA_NO"  json="AREA_NO" style="width:155" value="${params.AREA_NO}"/>	   					
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_26', false, 'AREA_NO', 'AREA_NO', 'forms[1]',
										'AREA_ID,AREA_NO', 
										'AREA_ID,AREA_NO', 
										'selectParamsArea');">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">区域经理:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" style="width:155" value="${params.AREA_MANAGE_NAME}"/>
	   					<input id="AREA_MANAGE_ID"   name="AREA_MANAGE_ID"   json="AREA_MANAGE_ID"   style="width:155" value="${params.AREA_MANAGE_ID}" type="hidden"/>	   					
					</td>
               </tr>
               <tr>                    
					<td width="8%" nowrap align="right" class="detail_label">经营品牌:</td>
					<td width="15%" class="detail_content">
	   					<select id="BUSS_SCOPE" json="BUSS_SCOPE" name="BUSS_SCOPE" style="width: 155px" inputtype="string" mustinput="true" label="经营品牌"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
	   				</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">专卖店撤店日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="SPCL_STORE_CANCEL_DATE_BEG" name="SPCL_STORE_CANCEL_DATE_BEG" json="SPCL_STORE_CANCEL_DATE_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.SPCL_STORE_CANCEL_DATE_BEG}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(SPCL_STORE_CANCEL_DATE_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">专卖店撤店日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="SPCL_STORE_CANCEL_DATE_END" name="SPCL_STORE_CANCEL_DATE_END" json="SPCL_STORE_CANCEL_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.SPCL_STORE_CANCEL_DATE_END}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(SPCL_STORE_CANCEL_DATE_END);">
					</td>
               </tr>
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
<script type="text/javascript">
    SelDictShow("BUSS_SCOPE","BS_88","${paramsT.BUSS_SCOPE}","");
    SelDictShow("STATE","33","${params.STATE}","");  
	spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");    
</script>
</html>
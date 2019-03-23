<!--
 * @prjName:喜临门营销平台
 * @fileName:投诉与建议处理
 * @author wdb
 * @time   2013-08-17 10:29:35 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/advisehd/Advisehd_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#reverseDiv{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
			left:230px;
			top:50px;
			text-align:center;
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：营销管理>>投诉与建议>>投诉与建议处理</td>
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
                  <c:if test="${pvg.PVG_EDIT eq 1 || pvg.PVG_DEAL eq 1}">
                  <input id="todeal" type="button" class="btn" value="处理(D)" title="Alt+D" accesskey="D">&nbsp;
                  </c:if>
                   <c:if test="${pvg.PVG_RETURN eq 1 }">
                  <input id="retu" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">&nbsp;
                  </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">&nbsp;
			   		<input id="import" type="button" class="btn" value="导出(I)" title="Alt+I" accesskey="I">&nbsp;
				   </c:if>
				   
				   <!--	<input id="deal" type="button" class="btn" value="指派处理人(S)" title="Alt+S" accesskey="S"/>&nbsp; -->
				   <!--<input id="dealhd" type="button" class="btn" value="处理提交(H)" title="Alt+H" accesskey="H"> -->
				   <div id="reverseDiv">
				<table width="100%" height="100%">
					<tr >
						<td align="right"><span style="font-size: 15px;">退回原因：</span></td>
						<td align="left">
							<textarea  id="REMARK"  cols="60" rows="5" inputtype="string"  maxlength="250"    ></textarea>
							<input  type="hidden" id="CMPL_ADVS_ID"  >  
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
						<input type="button" id="revAudit" value="退回" class="btn" style="margin-right: 10px"/>
						<input type="button" id="close" value="关闭" class="btn"/>
						</td>
						
					</tr>
				</table>
			</div>
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
                    <th  nowrap="nowrap" align="center" dbname="CMPL_ADVS_NO" >编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CMPL_ADVS_TITLE" >标题</th>
                    <th  nowrap="nowrap" align="center" dbname="CMPL_ADVS_TYPE" >类型</th>
                    <th  nowrap="nowrap" align="center" dbname="EMG_LVL" >紧急程度</th>
                    <th  nowrap="nowrap" align="center" dbname="RAISE_TIME" >时间</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >区域</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_MANAGE_NAME" >区域经理</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_CHIEF_NAME" >总监</th>
                    <th  nowrap="nowrap" align="center" dbname="RAISE_PSON_NAME" >提出人</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVS_SOURCE" >投诉来源</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >加盟商编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >加盟商名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >加盟商电话</th>
                    <th  nowrap="nowrap" align="center" dbname="CUSTOMER_NAME" >消费者姓名</th>
                    <th  nowrap="nowrap" align="center" dbname="CUSTOMER_TEL" >消费者电话</th>
                    <th  nowrap="nowrap" align="center" dbname="DEAL_PSON_NAME" >处理人</th>
                    <th  nowrap="nowrap" align="center" dbname="DEAL_TIME" >处理时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="pagechange('${rst.CMPL_ADVS_TYPE}');selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.CMPL_ADVS_ID}"/>
						<input type="hidden" id="FEEDBACK_INFO${rst.CMPL_ADVS_ID}" name="FEEDBACK_INFO" value="${rst.FEEDBACK_INFO}">
					 </td>
                     <td nowrap="nowrap" align="center">${rst.CMPL_ADVS_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CMPL_ADVS_TITLE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CMPL_ADVS_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.EMG_LVL}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RAISE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_CHIEF_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RAISE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ADVS_SOURCE}&nbsp;</td>
                     
                     <td nowrap="nowrap" align="left">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CHANN_NAME}&nbsp;</td>
                     
                     <td nowrap="nowrap" align="left">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CUSTOMER_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CUSTOMER_TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left" id="DEAL_PSON_NAME${rst.CMPL_ADVS_ID}">${rst.DEAL_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" id="DEAL_TIME${rst.CMPL_ADVS_ID}">${rst.DEAL_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" id="${rst.CMPL_ADVS_ID}">${rst.STATE}&nbsp;</td>
                     
                     <input id="XTYHID" type="hidden"  value="${XTYHID}" label="当前登录人ID" />
                     <input id="QX" type="hidden"  value="${QX}" label="当前登录人权限级别" />
                     <input id="state${rst.CMPL_ADVS_ID}" type="hidden"  value="${rst.STATE}" />
                     <input id="APPOINT_PSON_ID${rst.CMPL_ADVS_ID}" type="hidden"  value="${rst.APPOINT_PSON_ID}"  label="指派处理人ID" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="19" align="center" class="lst_empty">
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
<input type="hidden" name=selectPrd value="STATE in ('启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 ">
<input type="hidden" id="search" name="search" value='${search}'/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="CMPL_ADVS_NO" name="CMPL_ADVS_NO"  style="width:155" value="${params.CMPL_ADVS_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">标题:</td>
					<td width="15%" class="detail_content">
						<input id="CMPL_ADVS_TITLE" name="CMPL_ADVS_TITLE"  style="width:155" value="${params.CMPL_ADVS_TITLE}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">投诉时间从:</td>
					<td width="15%" class="detail_content">
						<input id="RAISE_TIME_FROM" name="RAISE_TIME_FROM"  style="width:155" value="${params.RAISE_TIME_FROM}" onclick="SelectTime();" readonly="readonly"/>
						&nbsp;
						<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(RAISE_TIME_FROM);" />
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">投诉时间到:</td>
					<td width="15%" class="detail_content">
						<input id="RAISE_TIME_TO" name="RAISE_TIME_TO"  style="width:155" value="${params.RAISE_TIME_TO}" onclick="SelectTime();" readonly="readonly"/>
						&nbsp;
						<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(RAISE_TIME_TO);" />
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">所属区域:</td>
					<td width="15%" class="detail_content">
						<input id="selectParams" name="selectParams" type="hidden"  value="STATE in ('启用','停用') and DEL_FLAG=0 ">
						<input type="hidden" id="AREA_ID" name="AREA_ID" json="AREA_ID"/>
	   					<input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_NAME','AREA_NAME', 'selectParams')"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">所属渠道:</td>
					<td width="15%" class="detail_content">
						<input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID"/>
						<input id="CHANN_NAME" name="CHANN_NAME"  style="width:155" value="${params.CHANN_NAME}"/>
						<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NAME','CHANN_NAME', 'selectParams')"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">处理人：</td>
					<td width="15%" class="detail_content">
						<input type="hidden" id="DEAL_PSON_ID" json="DEAL_PSON_ID" name="DEAL_PSON_ID" value="${params.DEAL_PSON_ID}"/>
						<input type="text" id="DEAL_PSON_NAME" name="DEAL_PSON_NAME" value="${params.DEAL_PSON_NAME}"/>
						<img align="absmiddle" name="selAREA" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
						onClick="selCommon('BS_7',false,'DEAL_PSON_ID','XTYHID','forms[1]','DEAL_PSON_NAME,APPOINT_PSON_ZW','XM,ZW','')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">处理人职务：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="APPOINT_PSON_ZW" name="APPOINT_PSON_ZW" value="${params.APPOINT_PSON_ZW}"/>
					</td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">类型:</td>
               		<td width="15%" class="detail_content" colspan="3">
               			<input type="hidden" value="${params.CMPL_ADVS_TYPE}" id="CAType" name="CAType">
               			<c:if test="${params.CMPL_ADVS_TYPE eq '产品投诉'}">
               				<input type="radio" id="prdcmpl" name="CMPL_ADVS_TYPE" value="产品投诉" checked="checked">产品投诉&nbsp;
               				<input type="radio" id="srvcmpl" name="CMPL_ADVS_TYPE" value="服务投诉">服务投诉&nbsp;
               				<input type="radio" id="advs" name="CMPL_ADVS_TYPE" value="建议">建议&nbsp;
               			</c:if>
               			<c:if test="${params.CMPL_ADVS_TYPE eq '服务投诉'}">
               				<input type="radio" id="prdcmpl" name="CMPL_ADVS_TYPE" value="产品投诉">产品投诉&nbsp;
               				<input type="radio" id="srvcmpl" name="CMPL_ADVS_TYPE" value="服务投诉" checked="checked">服务投诉&nbsp;
               				<input type="radio" id="advs" name="CMPL_ADVS_TYPE" value="建议">建议&nbsp;
               			</c:if>
               			<c:if test="${params.CMPL_ADVS_TYPE eq '建议'}">
               				<input type="radio" id="prdcmpl" name="CMPL_ADVS_TYPE" value="产品投诉">产品投诉&nbsp;
               				<input type="radio" id="srvcmpl" name="CMPL_ADVS_TYPE" value="服务投诉">服务投诉&nbsp;
               				<input type="radio" id="advs" name="CMPL_ADVS_TYPE" value="建议" checked="checked">建议&nbsp;
               			</c:if>
               			<c:if test="${empty params.CMPL_ADVS_TYPE}">
               				<input type="radio" id="prdcmpl" name="CMPL_ADVS_TYPE" value="产品投诉">产品投诉&nbsp;
               				<input type="radio" id="srvcmpl" name="CMPL_ADVS_TYPE" value="服务投诉">服务投诉&nbsp;
               				<input type="radio" id="advs" name="CMPL_ADVS_TYPE" value="建议">建议&nbsp;
               			</c:if>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
						<select json="STATE" id="STATE"  name="STATE" style="width:155px"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr id="sercmplselect">
               		<td width="8%" nowrap align="right" class="detail_label">投诉对象:</td>
               		<td width="15%" class="detail_content">
               			<select id="CMPL_OBJ" name="CMPL_OBJ" json="CMPL_OBJ" style="width:155px"></select>
               		</td>
               		<td width="8%" nowrap align="right" class="detail_label">投诉人员:</td>
               		<td width="15%" class="detail_content">
               			<input type="text" id="CMPL_TO_PSON" name="CMPL_TO_PSON" style="width:155" value="${params.CMPL_TO_PSON}">
               		</td>
               		<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr id="prdselect">
               		<td width="8%" nowrap align="right" class="detail_label">货品编号:</td>
					<td width="15%" class="detail_content">
						<input id="PRD_ID" name="PRD_ID"  type="hidden" value="${params.PRD_ID}"/>
	   					<input id="PRD_NO" name="PRD_NO"  style="width:155" value="${params.PRD_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_21', false, 'PRD_ID', 'PRD_ID', 'forms[1]','PRD_ID,PRD_NO,PRD_NAME', 'PRD_ID,PRD_NO,PRD_NAME', 'selectPrd')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="PRD_NAME" name="PRD_NAME"  style="width:155" value="${params.PRD_NAME}"/>
					</td>	
               		<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr id="advsselect">
               		<td width="8%" nowrap align="right" class="detail_label">建议类型:</td>
               		<td width="15%" class="detail_content">
               			<select id="ADVS_TYPE" name="ADVS_TYPE" json="ADVS_TYPE" style="width:155px"></select>
               		</td>
               		<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
	SelDictShow("STATE", "BS_13", "${params.STATE}", "");
	SelDictShow("CMPL_OBJ", "BS_15", "${params.CMPL_OBJ}", "");
	SelDictShow("ADVS_TYPE", "BS_16", "${params.ADVS_TYPE}", ""); 
</script>
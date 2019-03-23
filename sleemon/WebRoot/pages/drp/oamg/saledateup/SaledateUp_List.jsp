<!--
 * @prjName:喜临门营销平台
 * @fileName:分销 -销售数据上报
 * @author zzb
 * @time   2013-09-15 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/saledateup/SaledateUp_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	   	#editInfo{
			margin: 0px auto; 
			width:450px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:20px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
		#upFile {
			margin: 0px auto; 
			width:400px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:100px;
			left:230px;
			top:110px;
			text-align:center;
			line-height: 100px;
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${module eq 'sh'}">
			  <td height="20px">&nbsp;&nbsp;当前位置：营销管理>>销售数据上报管理>>销售数据上报审核</td>
			</c:if>
			<c:if test="${module eq 'wh' || empty module}">
			  <td height="20px">&nbsp;&nbsp;当前位置：销售管理>>销售数据上报管理>>销售数据上报维护</td>
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
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
					 <input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if> 
                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_RETURN_AUDIT eq 1 }">
				   	<input id="retuAudit" type="button" class="btn" value="反审核(E)" title="Alt+E" accesskey="E">
				   </c:if>
				  <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				   <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T"> 
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <c:if test="${pvg.PVG_IMPORT eq 1 }">
				   	<input id="export" type="button" class="btn" value="导出维护(X)" title="导出分管终端信息" accesskey="X">
				   	<input id="up" type="button" class="btn" value="导入(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <div id="editInfo" style="display: none;">
				    	<table style="">
							<tr>
				    			<td class="" > 
				    				维护年份：
				    			</td>
				    			<td class="">
				    				<select name="EDIT_YEAR" id="EDIT_YEAR" label="年"  style="width: 155px;"   ></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="" > 
				    				维护月份:
				    			</td>
				    			<td class="">
				    				<select name="EDIT_MONTH" id="EDIT_MONTH" label="月份"  style="width: 155px;"    ></select>
				    			</td>
				    		</tr>
				    		 
				    	</table>
				    	<input style="margin-top: 10px;margin-right: 10px;" id="confirm" class="btn" type="button" value="确定" onclick="modifyImport();"/>
				    	<input style="margin-top: 10px" id="close" class="btn" type="button" value="关闭" onclick="closePage();"/>
				   </div>
				   <div id="upFile">
				   		<input id="upInfo" value=""/>
				   		<p>
				   		<input value="关闭" type="button" class="btn" onclick="$('#upFile').hide();" id="close" style="margin-right: 10px;">
				   		<input id="type" value="" type="hidden">
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
                    <th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >销售数据上报编号</th>
                    <th  nowrap="nowrap" align="center" dbname="WAREA_NO" >战区编号</th>
                    <th  nowrap="nowrap" align="center" dbname="WAREA_NAME" >战区名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NO" >区域编号</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >区域名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="TOTAL_NUM" >总数量</th>-->
                    <th  nowrap="nowrap" align="center" dbname="TOTAL_AMOUNT" >总金额</th>
                    <th  nowrap="nowrap" align="center" dbname="YEAR" >年份</th>
                    <th  nowrap="nowrap" align="center" dbname="MONTH" >月份</th>
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
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.SALE_DATE_UP_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE_UP_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.WAREA_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.WAREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TERM_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="right"> ${rst.TOTAL_NUM}</td>-->
                     <td nowrap="nowrap" align="right"> ${rst.TOTAL_AMOUNT}</td>
                     <td nowrap="nowrap" align="right"> ${rst.YEAR}年</td>
                     <td nowrap="nowrap" align="right"> ${rst.MONTH}月</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center"> ${rst.DEPT_NAME}&nbsp; </td>
                     <td nowrap="nowrap" align="center">  ${rst.STATE}&nbsp;
                     <input id="state${rst.SALE_DATE_UP_ID}" type="hidden"  value="${rst.STATE}" />
                     </td>
                   
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
 <input type="hidden" id="selParam" name="selParam" value=" STATE in('启用','停用')"/>
 <input type="hidden" id="AREA_SER_ID" name="AREA_SER_ID" value="${AREA_SER_ID}"/>
 <c:if test="${IS_DRP_LEDGER eq '1'}">
     <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE in('启用','停用') and CHANN_ID='${ZTXXID}' or AREA_SER_ID='${ZTXXID}' "/>
	 <input type="hidden" id="selAreaParam" name="selAreaParam" value=" STATE in('启用','停用') and AREA_ID='${AREA_ID}'  "/>
	 <input type="hidden" id="selWareParam" name="selWareParam" value=" STATE in('启用','停用') and WAREA_ID='${WAREA_ID}'  "/>
 </c:if>
 <c:if test="${empty IS_DRP_LEDGER or IS_DRP_LEDGER ne '1'}">
     <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE in('启用','停用') and CHANN_ID in(${params.channChrg}) "/>
	 <input type="hidden" id="selAreaParam" name="selAreaParam" value=" STATE in('启用','停用') "/>
	 <input type="hidden" id="selWareParam" name="selWareParam" value=" STATE in('启用','停用') "/>
 </c:if>
 <input type="hidden" id="selTermParam" name="selTermParam" value=" STATE in('启用','停用') "/>
 <input type="hidden" id="flag" name="flag" value=""/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">销售数据上报编号:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="SALE_DATE_UP_NO" name="SALE_DATE_UP_NO"  value="${params.SALE_DATE_UP_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">战区编号:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="WAREA_NO" name="WAREA_NO"   value="${params.WAREA_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_137', true, 'WAREA_ID', 'WAREA_ID', 'forms[1]','WAREA_NO,WAREA_NAME', 'WAREA_NO,WAREA_NAME', 'selWareParam')">
						 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">战区名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="WAREA_ID" id="WAREA_ID" value="" />
	   				   <input type="text" id="WAREA_NAME" name="WAREA_NAME"    value="${params.WAREA_NAME}"/>
					</td>					
                    			
                     <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
					     <select id="STATE" name="STATE" style="width:155" ></select>
					 </td>	
										
               </tr>
               
                <tr>
                    <td width="8%" nowrap align="right" class="detail_label">渠道编号:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CHANN_NO" name="CHANN_NO"   value="${params.CHANN_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="CHANN_ID" id="CHANN_ID" value="" />
	   				  <input type="text"  id="CHANN_NAME" name="CHANN_NAME"    value="${params.CHANN_NAME}"/>
					</td>
					
					<td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
					   <input type="text" id="AREA_NO" name="AREA_NO"   value="${params.AREA_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_18', true, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAreaParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
	   				   <input type="text" id="AREA_NAME" name="AREA_NAME"    value="${params.AREA_NAME}"/>
					</td>	
                 				
								
               </tr>
               <tr>
               
					<td width="8%" nowrap align="right" class="detail_label">年：</td>
					<td width="15%" class="detail_content">
					  <select  id="YEAR" name="YEAR" style="width: 155px;"   value="${params.YEAR}" ></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">月份从：</td>
					<td width="15%" class="detail_content">
						<select  id="MONTH_BEG" name="MONTH_BEG" style="width: 155px;"    value="${params.MONTH_BEG}"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">月份到：</td>
					<td width="15%" class="detail_content">
						<select  id="MONTH_END" name="MONTH_END" style="width: 155px;"    value="${params.MONTH_END}"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
               </tr>
               <tr>
                    <td width="8%" align="right" class="detail_label">终端编号：</td>
				    <td width="15%" class="detail_content">
				        <input type="hidden" json="TERM_ID"  id="TERM_ID" name="TERM_ID" value="${params.TERM_ID}" />
	                    <input json="TERM_NO" name="TERM_NO" id="TERM_NO"        type="text" value="${params.TERM_NO}"/> 
		               <img align="absmiddle" name="selJGXX" style="cursor: hand"
					   src="${ctx}/styles/${theme}/images/plus/select.gif"       
					   onClick="selectTerm();"/>
				   </td>
	               <td width="8%" align="right" class="detail_label">终端名称：</td>
				   <td width="15%" class="detail_content">
	                    <input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME"    type="text"     value="${params.TERM_NAME}"/> 
				   </td>
			   
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" onclick="SelectTime();" size="20" value="${params.CRE_TIME_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_END" name="CRE_TIME_END" onclick="SelectTime();" size="20" value="${params.CRE_TIME_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);">
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
				    	<input id="q_reset" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
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
<script type="text/javascript">
  SelDictShow("STATE","33","${params.STATE}","");
  SelDictShow("YEAR","89","${params.YEAR}","");
  SelDictShow("EDIT_YEAR","89","","");
  SelDictShow("MONTH_BEG","168","${params.MONTH_BEG}","");
  SelDictShow("MONTH_END","168","${params.MONTH_END}","");
  SelDictShow("EDIT_MONTH","168","","");
  uploadFile('upInfo', 'SAMPLE_DIR',true,function(){
	   		uploading();
	   });
  //初始化 审批流程
 spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>

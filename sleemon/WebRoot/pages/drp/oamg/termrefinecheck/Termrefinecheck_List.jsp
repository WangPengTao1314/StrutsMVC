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
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/termrefinecheck/Termrefinecheck_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/termrefinecheck/Termrefinecheck_Edit_T.js"></script>
	<style type="text/css">
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
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;门店稽查管理&gt;&gt;${menu}</td>
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
				   <c:if test="${pvg.PVG_BWS eq 1 || pvg.PVG_BWS_AUDIT eq 1}">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <c:if test="${pvg.PVG_UPLOADING eq 1}">
			   		<input id="uploading" type="button" class="btn" value="导入(U)" title="Alt+U" accesskey="U"/>
			   		</c:if>
				</td>
				</tr>
			</table>
		</div>
		<div id="upFile">
	   		<input id="up" value=""/>
	   		<input value="关闭" type="button" class="btn" id="close">
	   </div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_REFINE_CHECK_NO" >门店精致化检查编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_REFINE_TASK_NO" >检查任务号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="BUSS_SCOPE" >品牌类型</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属战区</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_MANAGE_NAME" >区域经理名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHECK_TOTAL_SCORE" >精致化得分</th>
                    <th  nowrap="nowrap" align="center" dbname="CHECK_ORG_NAME" >检查机构名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHECK_DATE" >检查日期</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    <!--  
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
                    -->
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.TERM_REFINE_CHECK_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.TERM_REFINE_CHECK_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_REFINE_TASK_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BUSS_SCOPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHECK_TOTAL_SCORE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHECK_ORG_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHECK_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REMARK}&nbsp;</td>
                    <input id="state${rst.TERM_REFINE_CHECK_ID}" type="hidden"  value="${rst.STATE}" />
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
 <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
 <input type="hidden" name=selectTERM value="STATE in ('启用') and DEL_FLAG=0 and CHANN_ID_P in ${CHANNS_CHARG}">
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">门店精致化检查编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_REFINE_CHECK_NO" name="TERM_REFINE_CHECK_NO" json="TERM_REFINE_CHECK_NO" style="width:155" value="${params.TERM_REFINE_CHECK_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
					    <input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${params.TERM_ID}"/>
	   					<input id="TERM_NO" name="TERM_NO" json="TERM_NO"  style="width:155" value="${params.TERM_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_NO', 'TERM_NO', 'forms[1]','TERM_ID,TERM_NO,TERM_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectTERM')"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NAME" name="TERM_NAME" json="TERM_NAME" style="width:155" value="${params.TERM_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NAME" name="AREA_NAME" json="AREA_NAME" style="width:155" value="${params.AREA_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">区域经理名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME"  style="width:155" value="${params.AREA_MANAGE_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">检查机构名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CHECK_ORG_NAME" name="CHECK_ORG_NAME" json="CHECK_ORG_NAME"  style="width:155" value="${params.CHECK_ORG_NAME}"/>
					</td>	
			        <td width="8%" nowrap align="right" class="detail_label">终端版本:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_VERSION" name="TERM_VERSION" json="TERM_VERSION" style="width:155" value="${params.TERM_VERSION}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CHANN_NAME" name="CHANN_NAME" json="CHANN_NAME" style="width:155" value="${params.CHANN_NAME}"/>
					</td>	
			    </tr>
			    <tr>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content" >
                         <select json="STATE" id="STATE"  name="STATE" style="width:155px" ></select>
					</td>
					<td width="10%" align="right" class="detail_label"> 战区编号： </td>
					<td width="15%" class="detail_content">
						<input id="WAREA_ID" name="WAREA_ID"  json="WAREA_ID" type="hidden"  />
						<input id="WAREA_NO" name="WAREA_NO"  json="WAREA_NO" type="text" value="${params.WAREA_NO}" style="width:155"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_26', false, 'WAREA_NO', 'AREA_NO', 'forms[1]',
										'WAREA_NO,WAREA_NAME', 
										'AREA_NO,AREA_NAME', 
										'selectParamsArea');">
					 
					</td>
					<td width="10%" nowrap align="right" class="detail_label">战区名称：</td>
					<td width="15%" class="detail_content">
	   					<input id="WAREA_NAME" name="WAREA_NAME" type="text"  value="${params.WAREA_NAME}" style="width:155"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">终端品牌：</td>
					<td width="15%" class="detail_content">
	   					<input id="BUSS_SCOPE" name="BUSS_SCOPE" type="text"  value="${params.BUSS_SCOPE}" style="width:155"/>
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
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
	   SelDictShow("STATE","33","${params.STATE}","");
//初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
       uploadFile('up', 'SAMPLE_DIR',true,function(){
			uploading();
	   });	   
</script>
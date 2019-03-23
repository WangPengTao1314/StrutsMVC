<!--
 * @prjName:喜临门营销平台
 * @fileName:Inventory_List
 * @author lyg
 * @time   2013-09-07 09:54:59 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/store/inventory/Inventory_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
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
			<c:if test="${params.module=='wh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;盘点管理&gt;&gt;盘点单维护</td>
			</c:if>
			<c:if test="${params.module=='sh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;盘点管理&gt;&gt;盘点单审核</td>
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
				   <c:if test="${pvg.PVG_BEGIN_CHECK eq 1 }">
			   		<input id="begin" type="button" class="btn"  value="开始盘点(B)" title="Alt+B" accesskey="B">
				   </c:if>
			   		<c:if test="${pvg.PVG_END_CHECK eq 1 }">
			   		<input id="end" type="button" class="btn" value="结束盘点(E)" title="Alt+E" accesskey="E">
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
				   <c:if test="${pvg.PVG_INI_UP eq 1  }">
				   	<input id="iniup" type="button" class="btn"  value="初始化盘点(I)" title="Alt+I" accesskey="I">
				   </c:if>
					<div id="upFile">
				   		<input id="up" value=""/>
				   		<p>
				   		<input value="关闭" type="button" class="btn" id="close" style="margin-right: 10px;">
				   		<input id="tempUp" type="button" class="btn"  value="模版下载(T)" title="Alt+T" accesskey="T">
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
                    <th  nowrap="nowrap" align="center" dbname="PRD_INV_NO" >盘点单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="INV_TYPE" >盘点类型</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NO" >库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NAME" >库房名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG" >库位管理标记</th>-->
                    <th  nowrap="nowrap" align="center" dbname="INV_RANGE" >盘点范围</th>
                    <th  nowrap="nowrap" align="center" dbname="INV_PSON_NAME" >监盘人名称</th>
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
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRD_INV_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.PRD_INV_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.INV_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STORE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STORE_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">-->
<!--                     	<c:if test="${rst.STORAGE_FLAG==0}">否</c:if>-->
<!--                     	<c:if test="${rst.STORAGE_FLAG==1}">是</c:if>-->
<!--                     &nbsp;</td>-->
                     <td nowrap="nowrap" align="center" >${rst.INV_RANGE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.INV_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.PRD_INV_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="type${rst.PRD_INV_ID}" type="hidden"  value="${rst.INV_TYPE}" />
                    <input id="store${rst.PRD_INV_ID}" type="hidden"  value="${rst.STORE_ID}" />
                    <input id="storg${rst.PRD_INV_ID}" type="hidden"  value="${rst.STORG_ID}" />
                    <input id="range${rst.PRD_INV_ID}" type="hidden"  value="${rst.INV_RANGE}" />
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
				<input type="hidden" name=selectParams value="STATE in ('启用','停用') and DEL_FLAG='0'">
				<input type="hidden" name="selectStore"  ID="selectStore" value=" ">
				<input type="hidden" id="TERM_ID" value="${TERM_ID}"/>
                <input type="hidden" id="ZTXXID" value="${ZTXXID}"/>
                <input type="hidden" id="TERM_CHARGE" value="${TERM_CHARGE}"/>
				
				<!-- 0156593--start--刘曰刚--2014-06-18 -->
				<!-- 修改盘点单查询通用选取查询条件,修改货品查询过滤货品分类 -->
				<input type="hidden" name=selectPrd value="STATE in ('启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1">
				<input type="hidden" name=selectParam value=" DEL_FLAG='0' and ${sql}">
				<!-- 0156593--End--刘曰刚--2014-06-18 -->
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">盘点单编号:</td>
					<td width="15%" class="detail_content">
						<input id="PRD_INV_ID" name="PRD_INV_ID"  type="hidden" value="${params.PRD_INV_ID}"/>
	   					<input id="PRD_INV_NO" name="PRD_INV_NO"  style="width:155" value="${params.PRD_INV_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_50', false, 'PRD_INV_ID', 'PRD_INV_ID', 'forms[1]','PRD_INV_ID,PRD_INV_NO','PRD_INV_ID,PRD_INV_NO','selectParam')">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">盘点类型:</td>
					<td width="15%" class="detail_content">
                         <select json="INV_TYPE" id="INV_TYPE"  name="INV_TYPE" style="width:155px" ></select>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">库房编号:</td>
					<td width="15%" class="detail_content">
						<input id="STORE_ID" name="STORE_ID" type="hidden" value="${params.STORE_ID}"/>
	   					<input id="STORE_NO" name="STORE_NO"  style="width:155" value="${params.STORE_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="storeSelCommon();"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STORE_NAME" name="STORE_NAME"  style="width:155" value="${params.STORE_NAME}"/>
					</td>					
               </tr>
               <!-- 0156610--start--刘曰刚--2014-06-18 -->
				<!-- 去掉库位查询，修改库房查询条件 -->
               <tr>
<!--                    <td width="8%" nowrap align="right" class="detail_label">库位编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--						<input id="STORG_ID" name="STORG_ID"  type="hidden" value="${params.STORG_ID}"/>-->
<!--	   					<input id="STORG_NO" name="STORG_NO"  style="width:155" value="${params.STORG_NO}"/>-->
<!--	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"-->
<!--							onClick="selCommon('BS_42', false, 'STORG_ID', 'STORG_ID', 'forms[1]','STORG_ID,STORG_NO,STORG_NAME','STORG_ID,STORG_NO,STORG_NAME', 'selectParams')">-->
<!--					</td>					-->
<!--                    <td width="8%" nowrap align="right" class="detail_label">库位名称:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="STORG_NAME" name="STORG_NAME"  style="width:155" value="${params.STORG_NAME}"/>-->
<!--					</td>					-->
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
					<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td>		
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">盘点范围:</td>
					<td width="15%" class="detail_content">
                         <select json="INV_RANGE" id="INV_RANGE"  name="INV_RANGE" style="width:155px" ></select>
					</td>
                    					
                    
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width: 155"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <!-- 0156610--End--刘曰刚--2014-06-18 -->
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
	   SelDictShow("INV_TYPE","66","${params.INV_TYPE}","");
	   SelDictShow("INV_RANGE","BS_33","${params.INV_RANGE}","");
	   SelDictShow("STATE","BS_34","${param.STATE}","");
	   uploadFile('up', 'SAMPLE_DIR',true,function(){
	   		var type=$("#type").val();
	   		if("init"==type){
	   			parent.showConfirm("警告：您确定需要做初始化吗？初始化会清空掉您的当前库存以及成本","topcontent.uploading();","n");
	   		}else{
	   			uploading();
	   		}
	   });
//初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
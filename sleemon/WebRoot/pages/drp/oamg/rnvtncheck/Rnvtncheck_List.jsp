<!-- 
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
 *@version 1.1
 *@author zcx
 *  -->

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>装修验收单维护列表页面</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/rnvtncheck/Rnvtncheck_List.js"></script>
</head>

<body>
<table  width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td><span id="pageTitle">当前位置：渠道管理&gt;&gt;;装修管理 &gt;&gt;装修验收单维护</span></td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top">
	    <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
					<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
			           <tr>
						   <td id="qxBtnTb" nowrap>
						      <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
						      <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
					   		  <input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
					   		  <input id="commRecv" type="button" class="btn" value="确认(O)" title="Alt+O" accesskey="O"/>
					   		  <input id="generate" type="button" class="btn" value="生成整改单(G)" title="Alt+G" accesskey="G"/>
					   		  <input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
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
					<th nowrap align="center" dbname="RNVTN_CHECK_NO" >装修验收单号</th>
					<th nowrap align="center" dbname="CHANN_RNVTN_NO" >装修申请单号</th>
					<th nowrap align="center" dbname="TERM_NO" >终端编号</th>
					<th  nowrap="nowrap" align="center" dbname="TERM_NAME">终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RNVTN_PROP" >装修性质</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_SBUSS_DATE" >计划开业时间</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属战区名称</th>
					<th nowrap align="center" dbname="LOCAL_TYPE" >商场位置类别</th>	
					<th  nowrap="nowrap" align="center" dbname="BUSS_SCOPE" >经营品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="BUILD_COST" >验收负责人</th>
                    <th  nowrap="nowrap" align="center" dbname="RNVTN_SUBST_STD_VSION" >验收时间</th>
					<th nowrap align="center" dbname="STATE" >状态</th>	
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.RNVTN_CHECK_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
							<input type="hidden" id="state${rst.RNVTN_CHECK_ID}" value="${rst.STATE}"/>
						</td>
						<td align="center">${rst.RNVTN_CHECK_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_RNVTN_NO}&nbsp;</td>
						<td align="center">${rst.TERM_NO}&nbsp;</td>
						<td align="center">${rst.TERM_NAME}&nbsp;</td>
						<td align="center">${rst.RNVTN_PROP}&nbsp;</td>
						<td align="center">${rst.PLAN_SBUSS_DATE}&nbsp;</td>
						<td align="center">${rst.AREA_NAME}&nbsp;</td>
						<td align="center">${rst.LOCAL_TYPE}&nbsp;</td>
						<td align="center">${rst.BUSS_SCOPE}&nbsp;</td>
						<td align="center">${rst.RNVTN_PRINCIPAL}&nbsp;</td>
						<td align="center">${rst.RNVTN_CHECK_DATE}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.RNVTN_CHECK_ID}'));
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
	<td height="8px">
		<table width="100%" border="0" cellpadding="0"cellspacing="0" class="lst_toolbar">
		<tr>
			<td>
				<form id="pageForm" action="#" method="post" name="listForm">
				<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
					<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
					<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
					<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
					<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
					<input type="hidden" id="brand" name="brand"/>
					<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
					<span id="hidinpDiv" name="hidinpDiv"></span>
					${paramCover.unCoveredForbidInputs } 
				</form>
			</td>
			<td align="right">${page.footerHtml}${page.toolbarHtml}</td>
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
					<td nowrap align="right" class="detail_label">装修验收单号：</td>
					<td class="detail_content">
						<input name="RNVTN_CHECK_NO" id="RNVTN_CHECK_NO" json="RNVTN_CHECK_NO"  type="text"  value="${params.RNVTN_CHECK_NO}"/>
					</td>
					 <td nowrap align="right" class="detail_label">装修申请单号：</td>
		      		<td nowrap class="detail_content">
		                        <input type="text" json="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO"   value="${params.CHANN_RNVTN_NO}"/>
		      		</td>
		      	   </tr>
		      	   <tr>
		      		<td nowrap align="right" class="detail_label">终端编号：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="TERM_NO" id="TERM_NO" json="TERM_NO" value="${params.TERM_NO}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">终端名称：</td>
		      		<td nowrap class="detail_content">
		                <input name="TERM_NAME" id="TERM_NAME" json="TERM_NAME" value="${params.TERM_NAME}"/>
		      		</td>	
				  </tr>
				   <tr>
		      		<td nowrap align="right" class="detail_label">所属战区名称：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="AREA_NAME" id="AREA_NAME" json="AREA_NAME" value="${params.AREA_NAME}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">区域经理：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" value="${params.AREA_MANAGE_NAME}"/>
		      		</td>	
				  </tr>
				   <tr>
		      		<td nowrap align="right" class="detail_label">装修性质：</td>
		      		<td nowrap class="detail_content">
		                <select style="width:155px;" name="RNVTN_PROP" id="RNVTN_PROP" json="RNVTN_PROP"  value="${paramsT.RNVTN_PROP}">
						</select>
		      		</td>
		      		<td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content">
		                <select style="width:155px;" name="STATE"      id="STATE" json="STATE" value="${params.STATE}"></select>
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
<script language="JavaScript">
       SelDictShow("STATE","197","${params.STATE}","");  
       SelDictShow("RNVTN_PROP","BS_87","${paramsT.RNVTN_PROP}","");
</script>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
<input type="hidden" id="STATE" name="STATE" value="${STATE}"/>
</html>



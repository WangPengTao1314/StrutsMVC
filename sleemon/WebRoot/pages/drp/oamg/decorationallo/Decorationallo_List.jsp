<!-- 
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
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
	<title></title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/decorationallo/Decorationallo_List.js"></script>
	<title>装修补贴标准维护</title>
</head>

<body>
<table  width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td><span id="pageTitle">当前位置：渠道管理&gt;&gt;;装修管理 &gt;&gt;装修补贴标准维护</span></td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top">
	     <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
		    <table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
			  <tr>
			     <td id="qxBtnTb" nowrap>
						   <c:if test="${pvg.PVG_EDIT eq 1 }">
						   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
								<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
						   </c:if>
						   <c:if test="${pvg.PVG_DELETE eq 1 }">
						   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
						   </c:if>
						   <c:if test="${pvg.PVG_START_STOP eq 1 }">
						   		<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">		
			   		            <input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
			   		       </c:if>
			   		       <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1}">
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
					<th nowrap align="center" dbname="RNVTN_SUBST_STD_NO" >装修补贴标准编号</th>
					<th nowrap align="center" dbname="BRAND" >品牌</th>
					<th nowrap align="center" dbname="MIN_AREA" >最低面积(平米)</th>
					<th  nowrap="nowrap" align="center" dbname="STD_AREA" >标准面积(平米)</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="BUILD_COST" >造价成本(元/平米)</th>
                    <th  nowrap="nowrap" align="center" dbname="RNVTN_SUBST_STD_VSION" >补贴标准版本</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}"  >
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" json="RNVTN_SUBST_STD_ID" name="eid" id="eid${rr}" value="${rst.RNVTN_SUBST_STD_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
						    <input type="hidden" id="state${rst.RNVTN_SUBST_STD_ID}" value="${rst.STATE}"/>
						    <input type="hidden" id="brand${rst.RNVTN_SUBST_STD_ID}" value="${rst.BRAND}"/>
						</td>
						<td align="center">${rst.RNVTN_SUBST_STD_NO}&nbsp;</td>
						<td align="center">${rst.BRAND}&nbsp;</td>
						<td align="center">${rst.MIN_AREA}&nbsp;</td>
						<td align="center">${rst.STD_AREA}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<td align="center">${rst.BUILD_COST}&nbsp;</td>
						<td align="center">${rst.RNVTN_SUBST_STD_VSION}&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.RNVTN_SUBST_STD_ID}'));
						      document.getElementById("brand").value=document.getElementById('brand${rst.RNVTN_SUBST_STD_ID}').value;
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
					<td width="8%" nowrap align="right" class="detail_label">装修补贴标准编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" name="selectParamsT" id="selectParamsT" value="DEL_FLAG='0'" />
	   					<input id="RNVTN_SUBST_STD_ID" name="RNVTN_SUBST_STD_ID"  json="RNVTN_SUBST_STD_ID"  type="hidden"  value="${params.RNVTN_SUBST_STD_ID}"  />
						<input id="RNVTN_SUBST_STD_NO" name="RNVTN_SUBST_STD_NO"  json="RNVTN_SUBST_STD_NO"  value="${params.RNVTN_SUBST_STD_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_93', false, 'RNVTN_SUBST_STD_NO', 'RNVTN_SUBST_STD_NO', 'forms[1]','RNVTN_SUBST_STD_ID,RNVTN_SUBST_STD_NO', 'RNVTN_SUBST_STD_ID,RNVTN_SUBST_STD_NO', 'selectParamsT');">  
					</td>                    		
					<td nowrap align="right" class="detail_label">补贴标准版本：</td>
		      		<td nowrap class="detail_content">
		                        <input type="text" json="RNVTN_SUBST_STD_VSION" id="RNVTN_SUBST_STD_VSION" name="RNVTN_SUBST_STD_VSION"   value="${params.RNVTN_SUBST_STD_VSION}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">品牌：</td>
		      		<td nowrap class="detail_content">
		                <select style="width:155px;" name="BRAND" id="BRAND" json="BRAND" value="${params.BRAND}"></select>
		      		</td>
		      		<td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content">
		                <select style="width:155px;" name="STATE" id="STATE" json="STATE" value="${params.STATE}"></select>
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
<script language="JavaScript">
   SelDictShow("STATE","32","${params.STATE}","");  
   SelDictShow("BRAND","BS_88","${params.BRAND}","");
</script>
</body>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
</html>


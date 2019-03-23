<!-- /**

 *@module 财务管理

 *@fuc 帐套信息维护

 *@version 1.1

 *@author 唐赟
*/ -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>帐套信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sys/ztxxwh/ztxxwh_List.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body>
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;帐套信息维护</td>
			<td width="50" align="right"><br></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td id="qxBtnTb" nowrap>
			      <c:if test="${qxcom.XT0011602 eq 1 }">
			   	        <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
				        <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
			   	   </c:if>	
			   	   <c:if test="${qxcom.XT0011602 eq 1 }">
			   	        <input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
			   	   </c:if>	
			   	   
			   	   <c:if test="${qxcom.XT0011601 eq 1 }">
			   	        <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F" >
			   	   </c:if>	
			   	   <c:if test="${qxcom.XT0011603 eq 1 }">
			   	        <input id="begin" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">
			   	        <input id="end" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">			   	        
			   	   </c:if>
			   	  <input id="personal" type="button" class="btn" value="个性化设置" >
				   </td>
				</tr>
			</table>
		</div>
	</td>
</tr> 
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="ZTBH" >帐套编号</th>
					<th nowrap align="center" dbname="ZTMC" >帐套名称</th>
					<th nowrap align="center" dbname="YJZBJ" >月结帐标记</th>
					<th nowrap align="center" dbname="SJZT" >上级帐套</th>
					<th nowrap align="center" dbname="ZZSH" >增值税号</th>
					<th nowrap align="center" dbname="YYZZH" >营业执照号</th>
					<th nowrap align="center" dbname="ZTLB" >帐套类别</th>	
					<th nowrap align="center" dbname="CREATER" >创建人</th>
					<th nowrap align="center" dbname="CRETIME" >创建时间</th>	
					<th nowrap align="center" dbname="STATE" >状态</th>																														
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));changeButton('${rst.STATE}')">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ZTXXID}"/>
						</td>
						<td nowrap align="left">${rst.ZTBH }&nbsp;</td>
						<td nowrap align="left">${rst.ZTMC}&nbsp;</td>
						<td nowrap align="center"><c:if test="${rst.YJZBJ eq 0}">否</c:if>
														<c:if test="${rst.YJZBJ eq 1}">是</c:if>&nbsp;</td>
						<td nowrap align="left">${rst.SJZTMC}&nbsp;</td>
						<td nowrap align="left">${rst.ZZSH}&nbsp;</td>
						<td nowrap align="left">${rst.YYZZH}&nbsp;</td>
						<td nowrap align="center">${rst.ZTLB}&nbsp;</td>
						
						<td nowrap align="left">${rst.CRENAME }&nbsp;</td>
						<td nowrap align="center">${rst.CRETIME }&nbsp;</td>
						<td nowrap align="center">${rst.STATE }&nbsp;</td>
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
					${page.toolbarHtml}
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
					<td width="10%" align="right" class="detail_label">帐套编号：</td>
					<td width="15%" class="detail_content">
						<input name="ZTBH" type="text" value="${params.ZTBH }">
					</td>
					<td width="10%" align="right" class="detail_label">帐套名称：</td>
					<td width="15%" class="detail_content">
						<input name="ZTMC" type="text" value="${params.ZTMC }">
					</td>
					<td width="10%" align="right" class="detail_label">上级帐套：</td>
					<td width="15%" class="detail_content">
						<input name="SJZT" type="text" seltarget="selZTXX" value="${params.SJZT }">
						<input json="ZTXXID" name="ZTXXID1" type="hidden" seltarget="selZTXX" value="${params.ZTXXID }">
		                <img align="absmiddle" name="selZTXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif"  onClick="selCommon('System_5', false, 'SJZT', 'ZTXXID', 'forms[1]','SJZT', 'ZTMC', '')">
					</td>
					
					<td width="10%" align="right" class="detail_label">增值税号：</td>
					<td width="15%" class="detail_content">
						<input name="ZZSH" type="text" value="${params.ZZSH }">
					</td>
				</tr>
				<tr>
				    <td width="10%" align="right" class="detail_label">帐套类别：</td>
					<td width="15%" class="detail_content">
						
						<select json="ZTLB" name="ZTLB" id="ZTLB" mustinput="true" style="width:155px" >
						</select>
					</td>
					<td width="10%" align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="ZT" name="ZT" style="width:155px">
						</select>									
					</td>
					<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
					<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确 定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关 闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
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
<script type="text/javascript">
	   SelDictShow("ZTLB","0","${params.ZTLB}","");	
	   SelDictShow("ZT","32","${params.ZT}","");	
	</script>
</html>

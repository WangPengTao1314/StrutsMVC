<!-- 
 *@module 系统管理
 *@func 部门信息
 *@version 1.1
 *@author 吴亚林
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
	<title>机构信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/sys/bmxx/bmxxwh_List.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;部门信息</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td id="qxBtnTb" nowrap>
				   
				  <c:if test="${qxcom.XT0010402 eq 1}">
				    <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
			   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				  </c:if>
				  <c:if test="${qxcom.XT0010403 eq 1}">
				    <input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
				  </c:if>
				  <c:if test="${qxcom.XT0010404 eq 1}">
				    <input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">	
				   	<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">	
				  </c:if>
				  <c:if test="${qxcom.XT0010401 eq 1}">
				    <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				  </c:if>
				   	 <%--<input id="personal" type="button" class="btn" value="个性化设置" >--%>		
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
					<th nowrap align="center" dbname="U.BMBH" >部门编号</th>
					<th nowrap align="center" dbname="U.BMMC" >部门名称</th>
					<th nowrap align="center" dbname="U.BMJC" >部门简称</th>
					<!-- <th nowrap align="center" dbname="SJBM" >上级部门编号</th> -->
					<th nowrap align="center" dbname="K.BMMC" >上级部门名称</th>
					<!-- <th nowrap align="center" dbname="JGXXID" >所属机构编号</th> -->
					<th nowrap align="center" dbname="JGMC" >所属机构名称</th>
					<th nowrap align="center" dbname="U.DH" >电话</th>
					<th nowrap align="center" dbname="U.BMZT" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.BMXXID}"/>
						</td>
						
						<td align="left">${rst.BMBH }&nbsp;</td>
						<td align="left">${rst.BMMC }&nbsp;</td>
						<td align="left">${rst.BMJC}&nbsp;</td>
						<!-- <td align="left">${rst.SJBM }&nbsp;</td> -->
						<td align="left">${rst.SJBMMC }&nbsp;</td>
						<!-- <td align="left">${rst.JGXXID }&nbsp;</td> -->
						<td align="left">${rst.JGMC }&nbsp;</td>
						<td align="left">${rst.DH}&nbsp;</td>
						<td nowrap align="center" id="state${rst.BMXXID}" value="${rst.BMZT}">${rst.BMZT}&nbsp;</td>
						
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      var val = $.trim($("#state${rst.BMXXID}").text());
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      changeButton(val);
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
<input name="conJGXX"  type="hidden" value=" JGZT in ('启用','停用')">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">部门编号：</td>
					<td width="15%" class="detail_content">
						<input name="BMBH" type="text" seltarget="selJGXX" value="${params.BMBH }">
						<img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_9', false, 'BMXXID1', 'BMXXID', 'forms[1]','BMXXID1,BMBH,BMMC,BMJC', 'BMXXID,BMBH,BMMC,BMJC', '')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">部门名称：</td>
					<td width="15%" class="detail_content">
						<input name="BMXXID1" type="hidden" seltarget="selJGXX" value="${params.BMXXID }">
						<input name="BMMC" type="text" seltarget="selJGXX" value="${params.BMMC }">
						
					</td>
					<td width="8%" nowrap align="right" class="detail_label">部门简称：</td>
					<td width="15%" class="detail_content">
						<input name="BMJC" type="text" value="${params.BMJC }">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">所属机构：</td>
					<td width="15%" class="detail_content">
					    <input name="JG" type="hidden" value=" jgzt='启用' ">
						<input name="JGXXID1"  type="hidden" seltarget="selJG" value="${params.JGXXID }">
						<input name="JGMC" type="text" seltarget="selJG" value="${params.JGMC }">
						<img align="absmiddle" name="selJG" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_2', false, 'JGXXID1', 'JGXXID', 'forms[1]','JGXXID1,JGMC', 'JGXXID,JGMC', 'conJGXX')">
				   </td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">上级部门</td>
					<td width="15%" class="detail_content">
						<input name="SJBMMC" type="text" seltarget="selJGXX" value="${params.SJBMMC }">
						<img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_9', true, 'SJBMMC', 'BMMC', 'forms[1]','SJBMMC', 'BMMC', '')">
					</td>
				
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="BMZT" name="BMZT" style="width:155px" >
					    </select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
				</tr>
				<tr>
					<td colspan="8" align="center" valign="middle">
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
<script language="JavaScript">  
		SelDictShow("BMZT","32","${params.BMZT }","");	
</script>
</body>
</html>

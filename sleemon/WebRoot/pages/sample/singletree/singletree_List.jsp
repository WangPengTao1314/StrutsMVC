
<!--  
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
-->
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sample/singletree/singletree_List.js"></script>
</head>
<body >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;基础资料&gt;&gt;机构信息维护</td>
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
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
			   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
			   		<input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">	
			   		<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">		
			   		<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
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
					<th nowrap align="center" dbname="JGBH" >机构编号</th>
					<th nowrap align="center" dbname="JGMC" >机构名称</th>
					<th nowrap align="center" dbname="JGJC" >机构简称</th>
					<th nowrap align="center" dbname="SJJGBH" >上级机构编号</th>
					<th nowrap align="center" dbname="GJ" >国家</th>
					<th nowrap align="center" dbname="SF" >省份</th>
					<th nowrap align="center" dbname="CS" >城市</th>
					<th nowrap align="center" dbname="ZTMC" >机构帐套</th>
					<th nowrap align="center" dbname="JGZT" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.JGXXID}"/>
						</td>
						<td nowrap align="left"> 
									${rst.JGBH }&nbsp; 
						</td> 
						<td nowrap align="left"> 
									${rst.JGMC }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.JGJC }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.SJJGBH }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.GJ }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.SF }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.CS }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.ZTMC }&nbsp; 
						</td>
						<td nowrap align="left"> 
									${rst.JGZT }&nbsp; 
						</td>
				</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
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
<input type="hidden" name="selectContion" value=" DELFLAG = 0" />
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label" >机构编号：</td>
					<td width="15%" class="detail_content">
						<input type="text" name="JGBH" autocheck="true" seltarget="selJGXX" inputtype="string" label="机构名称" maxlength="100"
							value="${params.JGBH}" />
                        <input id="JGXXID" name="JGXXID" type="hidden" seltarget="selJGXX" autocheck="true" inputtype="string"  >
						 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('3', false, 'JGXXID', 'JGXXID', 'forms[1]','JGBH,JGMC', 'JGBH,JGMC', 'selectContion');">  								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">机构名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="JGMC" name="JGMC" autocheck="true" 
						value="${params.JGMC}"
						seltarget="selJGXX" inputtype="string" label="机构名称" maxlength="100" />
					</td>
					<td width="8%" nowrap align="right" class="detail_label">机构简称：</td>
					<td width="15%" class="detail_content">
						<input name="JGJC" type="text" value="${params.JGJC}">
					</td>
					<td width="8%" nowrap align="right" class="detail_label" >上级机构编号：</td>
					<td width="15%" class="detail_content">
						<input type="text" name="SJJG" autocheck="true" seltarget="selSJJG" inputtype="string" label="机构名称" 
						value="${params.SJJG}" maxlength="100" />
                        <input id="JGXXID2" name="JGXXID2" type="hidden" seltarget="selSJJG" autocheck="true" inputtype="string"  >
						 <img align="absmiddle" name="selSJJG" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('2', false, 'JGXXID2', 'JGXXID', 'forms[1]','SJJG', 'JGBH', 'selectContion');">  								   		
					</td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
	   					<select id="JGZT" name="JGZT" style="width:155" ></select>
					</td>
				</tr>
				<tr><td colspan="10">&nbsp;</td></tr>
				<tr>
					<td colspan="10" align="center" valign="middle" class="detail_btn">
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
<script language="JavaScript">
	$(function(){
		SelDictShow("JGZT","32","${params.JGZT}","");
	});
</script>
</html>

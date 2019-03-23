<!-- 
 *@module 系统管理
 *@func 人员信息
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
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/sys/ryxx/ryxxwh_List.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;人员信息</td>
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
				   <td id="qxBtnTb" nowrap><!-- 
				    <c:if test="${empty page.result}">
					    <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
				   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U" disabled="disabled">
				   		<input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R" disabled="disabled">
				   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">	
				   		<input id="start" type="button" class="btn" value="启用(T)" title="Alt+T" accesskey="T" disabled="disabled">	
					   	<input id="stop" type="button" class="btn" value="停用(P)" title="Alt+P" accesskey="P" disabled="disabled">
				    </c:if>
				    <c:if test="${not empty page.result}">
				   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
				   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   		<input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
				   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">	
				   		<input id="start" type="button" class="btn" value="启用(T)" title="Alt+T" accesskey="T">	
					   	<input id="stop" type="button" class="btn" value="停用(P)" title="Alt+P" accesskey="P">
				   	</c:if>	
				   	 -->
				   	
				   	<c:if test="${qxcom.XT0010502 eq 1 }">
				   	   <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
				   	   <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   	</c:if>
				   	<c:if test="${qxcom.XT0010503 eq 1 }">
				   	   <input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
				   	</c:if>
				   	<c:if test="${qxcom.XT0010504 eq 1 }">
				   	   <input id="start" type="button" class="btn" value="启用(T)" title="Alt+T" accesskey="T">	
					   	<input id="stop" type="button" class="btn" value="停用(P)" title="Alt+P" accesskey="P">
				   	</c:if>
				   	<c:if test="${qxcom.XT0010501 eq 1 }">
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
					<th nowrap align="center" dbname="RYBH" >人员编号</th>
					<th nowrap align="center" dbname="XM" >姓名</th>
					<th nowrap align="center" dbname="XB" >性别</th>
					<th nowrap align="center" dbname="SJ" >手机</th>
					<th nowrap align="center" dbname="SFZH" >身份证号</th>
					<!-- <th nowrap align="center" dbname="BMXXID" >所属部门编号</th> -->
					<th nowrap align="center" dbname="BMMC" >所属部门</th>
					<!-- <th nowrap align="center" dbname="JGXXID" >所属机构编号</th> -->
					<th nowrap align="center" dbname="JGMC" >所属机构</th>
					<th nowrap align="center" dbname="ZW" >职务</th>							
					<th nowrap align="center" dbname="RYZT" >状态</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.RYXXID}"/>
						</td>
						
						<td align="center">${rst.RYBH }&nbsp;</td>
						<td align="left">${rst.XM}&nbsp;</td>
						<td align="center">${rst.XB}&nbsp;</td>
						<td align="center">${rst.SJ}&nbsp;</td>
						<td align="center">${rst.SFZH}&nbsp;</td>
						<!-- <td align="left">${rst.BMXXID}&nbsp;</td> -->
						<td align="left">${rst.BMMC}&nbsp;</td>
						<!-- <td align="left">${rst.JGXXID}&nbsp;</td> -->
						<td align="left">${rst.JGMC}&nbsp;</td>
						<td align="left">${rst.ZW }&nbsp;</td>						
						<td nowrap align="center" json='STATE' id="state${rst.RYXXID}" value="${rst.RYZT}">${rst.RYZT}&nbsp;</td>
						
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
<input type="hidden" name="selectContion2" value=" DELFLAG = 0 and bmzt = '启用'" />
<input type="hidden" name="selectContion3" value=" DELFLAG = 0 and jgzt = '启用'" />

	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap align="right" class="detail_label">人员编号：</td>
					<td class="detail_content">
						<input name="RYXXID" type="hidden" seltarget="selRYXX" value="${params.RYXXID }">
						<input name="RYBH"  type="text" seltarget="selRYXX" value="${params.RYBH }">
						<img align="absmiddle" name="selRYXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_0', true, 'RYXXID', 'RYXXID', 'forms[1]','RYXXID,RYBH,XM', 'RYXXID,RYBH,XM', '')">
					</td>
					<td nowrap align="right" class="detail_label">姓名：</td>
					<td class="detail_content">
						<input name="XM"  type="text" value="${params.XM }"/>
					</td>
					 <td nowrap align="right" class="detail_label">所属机构：</td>
		      		<td nowrap class="detail_content">
		                        <input type="text" json="JGMC" name="JGMC" seltarget="selJGXX" value="${params.JGMC }"/>
		                        <input id="JGXXID" name="JGXXID1" type="hidden" seltarget="selJGXX" value="${params.JGXXID }"/>
								 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
										   		onClick="selCommon('System_2', true, 'JGXXID', 'JGXXID', 'forms[1]','JGMC', 'JGMC', '');">                        
		      		</td>
		      		<td nowrap align="right" class="detail_label">所属部门：</td>
		      		<td nowrap class="detail_content">
		                        <input type="text" name="BMMC" autocheck="true" seltarget="selBmXX" value="${params.BMMC }"/>
		                        <input id="BMXXID" name="BMXXID1" type="hidden" seltarget="selBmXX" value="${params.BMXXID }"/>
								 <img align="absmiddle" name="selBmXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
										   		onClick="selCommon('System_1', true, 'BMXXID', 'BMXXID', 'forms[1]','BMMC', 'BMMC', '');">
		      		</td>	
				</tr>
				<tr>
		      		<td nowrap align="right" class="detail_label">状态：</td>
					<td class="detail_content">
						<select id="RYZT" name="RYZT" style="width:155" ></select>
					</td>
		      		<td nowrap align="right" class="detail_label"></td>
					<td class="detail_content"></td>
					<td nowrap align="right" class="detail_label"></td>
					<td class="detail_content"></td>	
					<td nowrap align="right" class="detail_label"></td>
					<td class="detail_content"></td>		
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
		SelDictShow("RYZT","32","${params.RYZT}","");
</script>
</html>
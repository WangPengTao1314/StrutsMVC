
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
	<script type="text/javascript" src="${ctx}/pages/sys/numformat/numformat_List.js"></script>
</head>
<body >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;小数位设置</td>
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
			   <c:if test="${qxcom.XT0010302 eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
			   </c:if>
			   <c:if test="${qxcom.XT0010302 eq 1 }">
			   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
			   </c:if>
			   <c:if test="${qxcom.XT0010303 eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
			   </c:if>
			   <c:if test="${qxcom.XT0010301 eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">	
			   </c:if>
			   <c:if test="${qxcom.XT0010304 eq 1 }">
			   		<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">		
			   </c:if>
			   <c:if test="${qxcom.XT0010304 eq 1 }">
			   		<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
			   </c:if>	
				   
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
					<th nowrap align="center" dbname="NUMFORMATMC" >小数格式化名称</th>
					<th nowrap align="center" dbname="NUMTYPE" >数字类型</th>
					<th nowrap align="center" dbname="DECIMALS" >小数位</th>
					<th nowrap align="center" dbname="ROUNDTYPE" >进位方式</th>
					<th nowrap align="center" dbname="CRETIME" >创建时间</th>
					<th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.NUMFORMATID}"/>
						</td>						
						<td align="center">${rst.NUMFORMATMC }&nbsp;</td>
						<td align="center">${rst.NUMTYPE }&nbsp;</td>
						<td align="center">${rst.DECIMALS }&nbsp;</td>
						<td align="center">${rst.ROUNDTYPE }&nbsp;</td>
						<td align="center">${rst.CRETIME }&nbsp;</td>
						<td align="center">${rst.STATE }&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      var val = $("#state${rst.NUMFORMATID}").val();
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						     // changeButton(val);
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
<input type="hidden" name="selectContion" value=" jgzt='启用'" />
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label" >小数格式化名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" name="NUMFORMATMC" value="${params.NUMFORMATMC }" />                       						   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">数字类型：</td>
					<td width="15%" class="detail_content">
						<input type="text" name="NUMTYPE" value="${params.NUMTYPE }" />
					</td>
					<td width="8%" nowrap align="right" class="detail_label">进位方式：</td>
					<td width="15%" class="detail_content">
						<input name="ROUNDTYPE" type="text" value="${params.ROUNDTYPE }" >
					</td>
					
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width:155" ></select>
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
</body>
<script language="JavaScript">

		SelDictShow("STATE","32","${params.STATE}","");

</script>
</html>

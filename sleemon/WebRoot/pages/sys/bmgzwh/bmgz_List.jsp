<!-- 
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>编码规则维护列表页面</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/sys/bmgzwh/bmgz_List.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>

<body>
<table  width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td><span id="pageTitle">当前位置：系统管理&gt;&gt;;基础信息 &gt;&gt;编码规则维护</span></td>
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
			   <td nowrap>
				  <c:if test="${qxcom.XT0010202 eq 1}"> 
				    <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
			   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				  </c:if>
				  <c:if test="${qxcom.XT0010203 eq 1}"> 
				    <input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
				  </c:if>
				  <c:if test="${qxcom.XT0010201 eq 1}">
				    <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				  </c:if>
				  <c:if test="${qxcom.XT0010204 eq 1}">
				    <input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">	
				   	<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">	
				  </c:if>
				  <input id="personal" type="button" class="btn" value="个性化设置">
			 </td>
			</tr>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td>
		<div class="lst_area">
		<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
			<tr  class="fixedRow">
				<th nowrap align="center" width="1%" ></th>
				<th nowrap align="center" dbname="BMBH">编码编号</th>
				<th nowrap align="center" dbname="BMDX">编码对象</th>
				<th nowrap align="center" dbname="GZMC">规则名称</th>
				<th nowrap align="center" dbname="ZCD">总长度</th>
				<th nowrap align="center" dbname="STATE">状态</th>
				
			</tr>
			<c:forEach items="${page.result}" var="rst" varStatus="row">
			<c:set var="r" value="${row.count % 2}"></c:set>
			<c:set var="rr" value="${row.count}"></c:set>
			<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
				<td  align='center' >
					<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.BMGZID}">
				</td>
				<td  align="center">${rst.BMBH}&nbsp;</td>
				<td  align="left">${rst.BMDX}&nbsp;</td>
				<td  align="left">${rst.GZMC}&nbsp;</td>
				<td  align="right" id="zcd${rst.BMGZID}">${rst.ZCD}&nbsp;</td>
				<td  align="center" id="state${rst.BMGZID}" value="${rst.STATE}">${rst.STATE}&nbsp;</td>
				
				<script type="text/javascript">
				   $("#tr${rr}").click(function(){
				      var val = $.trim($("#state${rst.BMGZID}").text());
				      selectThis(this);
				      setSelEid(document.getElementById('eid${rr}'));
				      changeButton(val);
				   });
			    </script>
			</tr>
			</c:forEach>
			<c:if test="${empty page.result}">
			<tr>
				<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
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
<input type="hidden" name="condition1" id="condition1" value=" RYZT = '启用' and delflag=0">
<input type="hidden" name="condition" id="condition" value=" delflag=0">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
				    <td nowrap width="10%" align="right" class="detail_label">编码编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input name="BMBH" type="text" seltarget="selBM" value="${params.BMBH }">
						<input type="hidden" name="BMGZID" seltarget="selBM" value="${params.BMGZID }">
						<img align="absmiddle" name="selBM" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						   onClick="selCommon('System_14', false, 'BMBH', 'BMBH', 'forms[1]','BMBH,BMDX,GZMC', 'BMBH,BMDX,GZMC', 'condition')">
					</td>
					<td width="10%" align="right" class="detail_label">编码对象：</td>
					<td width="15%" class="detail_content">
						<input name="BMDX" type="text" value="${params.BMDX }">
					</td>
					<td width="10%" align="right" class="detail_label">规则名称：</td>
					<td width="15%" class="detail_content">
						<input name="GZMC" type="text" value="${params.GZMC }">
					</td>	
					<td width="10%" align="right" class="detail_label">总长度：</td>
					<td width="15%" class="detail_content">
						<input name="ZCD" type="text" value="${params.ZCD }">
					</td>	
				</tr>
				<tr>
				    <td nowrap width="10%" align="right" class="detail_label">创建人：</td>
					<td nowrap width="15%" class="detail_content">
						<input name="CREATER" type="text" seltarget="selRYXX" value="${params.CREATER }">
						<input name="creId" id="creId" type="hidden" seltarget="selRYXX" value="${params.creId }">
						<img align="absmiddle" name="selRYXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						   onClick="selCommon('System_3', false, 'creId', 'XTYHID', 'forms[1]','CREATER', 'YHBH', '')">
					</td>
					<td width="10%" align="right" class="detail_label" >状态：</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width:155px" >
					    </select>
					</td>
					<td width="10%" align="right" class="detail_label" ></td>
					<td width="15%" class="detail_content"></td>
					<td width="10%" align="right" class="detail_label" ></td>
					<td width="15%" class="detail_content"></td>
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
  
		SelDictShow("STATE","32","${params.STATE }","");		

</script>


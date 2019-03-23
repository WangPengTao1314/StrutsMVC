<!-- /**

  *@module 系统管理

  *@fuc 系统用户列表

  *@version 1.1

  *@author 朱晓文
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
	<title>系统用户信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sys/xtyh/xtyh_List.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body>
<table width="99.9%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;权限管理&gt;&gt;用户信息维护</td>
			<td width="50" align="right"><br></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="25">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td id="qxBtnTb" nowrap>
			   	   
			   	   <c:if test="${qxcom.XT0010602 eq 1 }">
			   	        <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
				        <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">				        
			   	   </c:if>
			   	   	
			   	   <c:if test="${qxcom.XT0010603 eq 1 }">
			   	        <input id="delete" type="button" class="btn" value="删除(L)" title="Alt+L" accesskey="L">
			   	   </c:if>	
			   	   <c:if test="${qxcom.XT0010601 eq 1 }">			   	       
				        <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
			   	   </c:if>
			   	   <c:if test="${qxcom.XT0010604 eq 1 }">
			   	        <input id="begin" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">
			   	        <input id="end" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
			   	   </c:if>
			   	        <input id="defaultP" type="button" class="btn" value="设为默认密码(P)" title="Alt+P" accesskey="P">
			   	  
			   	   <c:if test="${qxcom.XT0010605 eq 1 }">
			   	        <input id="userRight" type="button" class="btn" value="用户权限(K)" title="Alt+K" accesskey="K">
			   	        <input id="AuthorizeList" type="button" class="btn" value="查看授权信息(A)" title="Alt+A" accesskey="A">
			   	   </c:if>
			   	    <c:if test="${qxcom.XT0010606 eq 1 }">
			   	        <input id="systemAuthorize" type="button" class="btn" value="系统授权(R)" title="Alt+R" accesskey="R">
			   	   </c:if>
			   	   <c:if test="${qxcom.XT0010607 eq 1 }">
			   	     <input id="stepUser" type="button" class="btn" value="分管用户关系(G)" title="Alt+G" accesskey="G" onclick="openWindow();">
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
					<th nowrap align="center" dbname="YHBH" >用户编号</th>
					<th nowrap align="center" dbname="YHM" >用户名称</th>
					<th nowrap align="center" dbname="RYBH" >人员编号</th>
					<th nowrap align="center" dbname="XM" >人员名称</th>
					<th nowrap align="center" dbname="BMMC" >部门名称</th>
					<th nowrap align="center" dbname="JGMC" >机构名称</th>
					<th nowrap align="center" dbname="YHLB" >用户类别</th>
					<th nowrap align="center" dbname="YHZT" >状态</th>
					<th nowrap align="center" dbname="" >是否分管所有渠道</th>																																	
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);selectThis(this);setSelEid(document.getElementById('eid${rr}'));changeButton('${rst.YHZT}')">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.XTYHID}"/>
						</td>
						<td align="left">${rst.YHBH }&nbsp;</td>
						<td align="left">${rst.YHM}&nbsp;</td>
						<td align="left">${rst.RYBH}&nbsp;</td>
						<td align="left">${rst.XM}&nbsp;</td>
						<td align="left">${rst.BMMC}&nbsp;</td>
						<td align="left">${rst.JGMC}&nbsp;</td>						
						<td align="left">${rst.YHLB }&nbsp;</td>
						<td align="center">${rst.YHZT }&nbsp;</td>
						<td align="center">
                          <input type="radio" id="chrg0${rr}" name="chrg${rr}" value="1" onclick="beforeChangeChrg(this,'${rst.IS_FG_ALL_CHANN}');"
                           <c:if test="${1 eq rst.IS_FG_ALL_CHANN }">checked="checked" </c:if>
                          />分管所有
                          <input type="radio" id="chrg1${rr}" name="chrg${rr}" value="0" onclick="beforeChangeChrg(this,'${rst.IS_FG_ALL_CHANN}');"
                            <c:if test="${empty rst.IS_FG_ALL_CHANN or 0 eq rst.IS_FG_ALL_CHANN }"> checked="checked"  </c:if>
                          />否 
                        </td>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="10" align="center" class="lst_empty">
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
						<input type="hidden" id="selYhzt" name="selYhzt" value="${rst.YHZT}">
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
<input type="hidden" name="selectContion2" value=" DELFLAG = 0 and (bmzt = '启用' or bmzt = '停用' )" />
<input type="hidden" name="selectContion3" value=" DELFLAG = 0 and (jgzt = '启用' or jgzt = '停用' )" />

<input type="hidden" name="selectContion4" value=" ryzt = '启用'" />
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" align="right" class="detail_label">用户编号：</td>
					<td width="15%" class="detail_content">
						<input name="YHBH" type="text" seltarget="selYHBH" value="${params.YHBH }">
						<img align="absmiddle" name="selYHBH" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"
						onClick="selCommon('System_3', false, 'YHBH', 'YHBH', 'forms[1]','YHBH,YHMC', 'YHBH,YHM', '')">
					</td>
					<td width="10%" align="right" class="detail_label">用户名称：</td>
					<td width="15%" class="detail_content">
						<input name="YHMC" type="text" seltarget="selYHBH" value="${params.YHMC }">
					</td>
					<td width="10%" align="right" class="detail_label">人员编号：</td>
					<td nowrap width="15%" class="detail_content"  >
						<input json="RYBH" name="RYBH" type="text" seltarget="selKFXX" value="${params.RYBH }">
                        <img align="absmiddle" name="selKFXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"
						onClick="selCommon('System_0', false, 'RYBH', 'RYBH', 'forms[1]','RYBH,RYMC', 'RYBH,XM', '')">														
					</td>
					<td width="10%" align="right" class="detail_label">人员名称：</td>
					<td width="15%" class="detail_content">
						<input name="RYMC" type="text" seltarget="selKFXX" value="${params.RYMC }">
					</td>
					
				</tr>
				<tr>
					
				    <td nowrap align="right" class="detail_label">机构名称：</td>
		      		<td nowrap class="detail_content">
		                        <input type="text" json="JGMC" name="JGMC" seltarget="selJGXX" value="${params.JGMC }" />
		                        <input id="JGXXID" name="JGXXID" type="hidden" seltarget="selJGXX" value="${params.JGXXID }" />
								 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
										   		onClick="selCommon('System_2', false, 'JGXXID', 'JGXXID', 'forms[1]','JGMC', 'JGMC', 'selectContion3');">                        
		      		</td>
		      		<td nowrap align="right" class="detail_label">部门名称：</td>
		      		<td nowrap class="detail_content">
		                        <input type="text" name="BMMC" seltarget="selBmXX" value="${params.BMMC }" />
		                        <input id="BMXXID" name="BMXXID" type="hidden" seltarget="selBmXX" value="${params.BMXXID }" />
								 <img align="absmiddle" name="selBmXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
										   		onClick="selCommon('System_1', false, 'BMXXID', 'BMXXID', 'forms[1]','BMMC', 'BMMC', 'selectContion2');">
		      		</td>	
		      		<td width="10%" align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="YHZT" name="YHZT" style="width:155px">
						</select>									
					</td>
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
    //$(function(){
	   SelDictShow("YHZT","32","${params.YHZT}","");	
   // });
</script>
</html>

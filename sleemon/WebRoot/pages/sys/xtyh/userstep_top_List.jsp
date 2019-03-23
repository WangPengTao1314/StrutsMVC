
<!--  
/**
 * @module 系统管理
 * @func 渠道分管
 * @version 1.1
 * @author 张忠斌
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
	<title>上下级关系</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sys/xtyh/userstep_top_List.js"></script>
	<style type="text/css">
	  .query_div_1{
		display:none;
		width:100%;
		background-color:#e0edf6;
		filter:alpha(opacity=90);
		position:absolute;
		left:0;
		top:20;
		right:100%;
		bottom:100%;
	}
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr align="center"> 
			<td height="20px"><h3>分管用户关系</h3></td>
		</tr>
	  </table>  
	</td>
</tr>
 
</table>

<div id="querydiv" class="query_div_1" >
<form id="mainForm" method="post" action="#">
 <input type="hidden" id="selectXTYH" name="selectXTYH" value=" YHZT='启用' " />
 <input type="hidden" name="selectParams" value=" STATE in('启用') " />
 <input type="hidden" name="selectContion2" value=" DELFLAG = 0 and (bmzt = '启用' or bmzt = '停用' )" />
 <input type="hidden" name="selectContion3" value=" DELFLAG = 0 and (jgzt = '启用' or jgzt = '停用' )" />
 
 <input type="hidden" name="search" id="search" value="true" />
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2" valign="top">
			<table id="querytb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
					<td width="8%" nowrap align="right" class="detail_label" >分管人用户名称：</td>
					<td nowrap width="15%" class="detail_content">
					 <input type="hidden" name="PAR_USER_ID" id="PAR_USER_ID" value="${user.XTYHID}" />
					  ${user.YHM}
				 	</td>
					<td width="8%" nowrap align="right" class="detail_label">分管人姓名：</td>
					<td width="15%" class="detail_content">
					  ${user.XM}
					</td>
				</tr>
				
				<tr name="tr_ry">
					<td width="8%" nowrap align="right" class="detail_label" >用户编号：</td>
					<td nowrap width="15%" class="detail_content">
					    <input type="hidden" name="XTYHID" id="XTYHID" json="XTYHID"    value="${params.XTYHID}">
						<input type="text" name="YHBH" id="YHBH" label="用户编号" value="${params.YHBH}" inputtype="string"  autocheck="true"    />
						<img align="absmiddle" name="selXTYH" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_7', false, 'XTYHID', 'XTYHID', 'forms[0]','YHBH,YHM', 'YHBH,YHM', 'selectXTYH')"> 								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">用户名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="YHM" name="YHM" id="YHM" label="用户名称"   value="${params.YHM}"  />
					</td>
					</tr>
					<tr>
				    <%--
				    <td nowrap align="right" class="detail_label">机构名称：</td>
		      		<td nowrap class="detail_content">
                        <input type="text" id="JGMC" json="JGMC" name="JGMC" seltarget="selJGXX" value="${params.JGMC }" />
                        <input id="JGXXID" name="JGXXID" type="hidden" seltarget="selJGXX" value="${params.JGXXID }" />
						 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('System_2', false, 'JGXXID', 'JGXXID', 'forms[0]','JGMC', 'JGMC', 'selectContion3');">                        
		      		</td>
		      		--%><td nowrap align="right" class="detail_label">部门名称：</td>
		      		<td nowrap class="detail_content">
                        <input type="text" id="BMMC" name="BMMC" seltarget="selBmXX" value="${params.BMMC }" />
                        <input id="BMXXID" name="BMXXID" type="hidden" seltarget="selBmXX" value="${params.BMXXID }" />
						 <img align="absmiddle" name="selBmXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('System_1', false, 'BMXXID', 'BMXXID', 'forms[0]','BMMC', 'BMMC', 'selectContion2');">
		      		</td>
		      		<td width="8%" nowrap align="right" class="detail_label">分管结果显示：</td>
					<td width="15%" class="detail_content" >
					   <input type="radio" name="notcharg" id="notcharg_0"  checked="checked" /> 显示全部
					   <input type="radio" name="notcharg" id="notcharg_1"   /> 显示未分管
					   <input type="radio" name="notcharg" id="notcharg_2"   /> 显示已分管
					</td>
						
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle" style="background-color:rgb(255, 255, 255);">
						<input id="query" type="button" class="btn" value="查询(Q)" title="Alt+Q" accesskey="Q">&nbsp;&nbsp;
						<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">&nbsp;&nbsp;
						<input id="calcel" type="button" class="btn" value="取消分管(C)" title="Alt+C" accesskey="C">&nbsp;&nbsp;
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
	SelDictShow("CHANN_TYPE", "171", '${params.CHANN_TYPE}', "");
    SelDictShow("CHAA_LVL", "169", '${params.CHAA_LVL}', "");
</script>
</html>

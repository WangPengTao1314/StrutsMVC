
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
	<title>渠道分管</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/channchrg/Channchrg_List.js"></script>
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
		<tr> 
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;渠道分管设置</td>
		</tr>
	  </table>  
	</td>
</tr>
 
</table>

<div id="querydiv" class="query_div_1" >
<form id="mainForm" method="post" action="#">
 <input type="hidden" id="selectXTYH" name="selectXTYH" value=" YHZT='启用' " />
 <input type="hidden" name="selectParams" value=" STATE in('启用','停用') " />
 <input type="hidden" name="selectContion2" value=" DELFLAG = 0 and (bmzt = '启用' or bmzt = '停用' )" />
 <input type="hidden" name="selectContion3" value=" DELFLAG = 0 and (jgzt = '启用' or jgzt = '停用' )" />
 
 <input type="hidden" name="search" id="search" value="true" />
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2" valign="top">
			<table id="querytb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr> 
				    <td width="8%" nowrap align="right" class="detail_label">分配维度选择：</td>
					<td width="15%" class="detail_content" colspan="7">
					   <input type="radio" name="dimension" id="dimension_0"  checked="checked" value="0"/> 按人员维度
					   <input type="radio" name="dimension" id="dimension_1"  value="1" /> 按渠道维度
					</td>
				</tr>
				<!-- 按照人员维度的查询条件   start -->
				<tr name="tr_ry">
					<td width="8%" nowrap align="right" class="detail_label" >用户编号：</td>
					<td nowrap width="15%" class="detail_content">
					    <input type="hidden" name="XTYHID" id="XTYHID" json="XTYHID"    value="${params.XTYHID}">
						<input type="text" name="YHBH" id="YHBH" label="用户编号" value="${params.YHBH}" inputtype="string"  autocheck="true" mustinput="true" readonly />
						<img align="absmiddle" name="selXTYH" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_7', false, 'XTYHID', 'XTYHID', 'forms[0]','YHBH,YHM', 'YHBH,YHM', 'selectXTYH')"> 								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">用户名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="YHM" name="YHM" id="YHM" label="用户名称"   value="${params.YHM}" readonly/>
					</td>
			 
			     	<td width="8%" nowrap align="right" class="detail_label" >区域编号：</td>
					<td nowrap width="15%" class="detail_content" >
						<input type="text"  id="AREA_NO"  json="AREA_NO" name="AREA_NO" seltarget="selAREA_P" value="${params.AREA_NO}"/>
                        <input type="hidden" id="AREA_ID" name="AREA_ID"  seltarget="selAREA_P" value="${params.AREA_ID}">
						 <img align="absmiddle" name="selSJJG" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		  								   		
					</td> 
					<td width="8%" nowrap align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content">
						<input type="text"  id="AREA_NAME" json="AREA_NAME" name="AREA_NAME" seltarget="selAREA_P" value="${params.AREA_NAME}"/>
					</td>
					
				</tr>
				<tr name="tr_ry">
					<td width="8%" nowrap align="right" class="detail_label">渠道类型：</td>
					<td width="15%" class="detail_content">
	   					<select id="CHANN_TYPE" name="CHANN_TYPE" style="width:155" ></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道级别：</td>
					<td width="15%" class="detail_content">
					 <select id="CHAA_LVL" name="CHAA_LVL" style="width:155" ></select>
					</td>
				    <td width="8%" nowrap align="right" class="detail_label" >渠道编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"
							inputtype="string" seltarget="selJGXX" autocheck="true" 
							maxlength="100" value="${params.CHANN_ID}">
														
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
							seltarget="selJGXX" autocheck="true" maxlength="32"
							value="${params.CHANN_NO}" >
							
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>
				</tr>
				<tr name="tr_ry">
			      <td width="8%" nowrap align="right" class="detail_label">省份:</td>
				  <td width="15%" class="detail_content">
					    <input type="hidden" id="ZONE_ID" name="ZONE_ID"    value=""/>
	   					<input type="text" id="PROV" name="PROV"  json="PROV"  value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', false, 'PROV', 'PROV', 'forms[0]','PROV', 'PROV', '')">
					
				  </td>
				  <td width="8%" nowrap align="right" class="detail_label">城市</td>
				  <td width="15%" class="detail_content">
						<input name="CITY" id="CITY" json="CITY" type="text" value="${params.CITY}">
				  </td>
				  <td width="8%" nowrap align="right" class="detail_label"></td>
				  <td width="15%" class="detail_content"></td>
				  <td width="8%" nowrap align="right" class="detail_label"></td>
				  <td width="15%" class="detail_content"></td>
				</tr>
				<!-- 按照人员维度的查询条件   end -->
				
				<!-- 按照渠道维度的查询条件  start -->
				<tr name="tr_chann">
					<td width="8%" nowrap align="right" class="detail_label" >渠道编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input id="CHANN_ID_1" json="CHANN_ID_1" name="CHANN_ID_1" type="hidden" value="${params.CHANN_ID_1}">
						<input id="CHANN_NO_1" json="CHANN_NO_1"  name="CHANN_NO_1" type="text" inputtype="string" autocheck="true" mustinput="true"  maxlength="32" readonly
							value="${params.CHANN_NO_1}" >
							
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID_1', 'CHANN_ID', 'forms[0]','CHANN_ID_1,CHANN_NO_1,CHANN_NAME_1', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NAME_1" json="CHANN_NAME_1" name="CHANN_NAME_1" mustinput="true"  readonly value="${params.CHANN_NAME_1}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label" >用户编号：</td>
					<td nowrap width="15%" class="detail_content">
					    <input type="hidden" name="XTYHID_1" id="XTYHID_1" json="XTYHID_1"    value="${params.XTYHID_1}">
						<input type="text" name="YHBH_1" id="YHBH_1" label="用户编号" value="${params.YHBH_1}"    />
						<img align="absmiddle" name="selXTYH" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_7', false, 'XTYHID_1', 'XTYHID', 'forms[0]','YHBH_1,YHM_1', 'YHBH,YHM', 'selectXTYH')"> 								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">用户名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="YHM_1" name="YHM_1" id="YHM_1" label="用户名称"   value="${params.YHM_1}" />
					</td>
					
				</tr>
				<tr name="tr_chann">
					
				    <td nowrap align="right" class="detail_label">机构名称：</td>
		      		<td nowrap class="detail_content">
                        <input type="text" json="JGMC" name="JGMC" seltarget="selJGXX" value="${params.JGMC }" />
                        <input id="JGXXID" name="JGXXID" type="hidden" seltarget="selJGXX" value="${params.JGXXID }" />
						 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('System_2', false, 'JGXXID', 'JGXXID', 'forms[0]','JGMC', 'JGMC', 'selectContion3');">                        
		      		</td>
		      		<td nowrap align="right" class="detail_label">部门名称：</td>
		      		<td nowrap class="detail_content">
                        <input type="text" id="BMMC" name="BMMC" seltarget="selBmXX" value="${params.BMMC }" />
                        <input id="BMXXID" name="BMXXID" type="hidden" seltarget="selBmXX" value="${params.BMXXID }" />
						 <img align="absmiddle" name="selBmXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('System_1', false, 'BMXXID', 'BMXXID', 'forms[0]','BMMC', 'BMMC', 'selectContion2');">
		      		</td>	
		      		<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"> </td>
		      		<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>				
				</tr>
				<!-- 按照渠道维度的查询条件  end -->
				
				<tr > 
				    <td width="8%" nowrap align="right" class="detail_label">分管结果显示：</td>
					<td width="15%" class="detail_content" colspan="7">
					   <input type="radio" name="notcharg" id="notcharg_0"  checked="checked" /> 显示全部
					   <input type="radio" name="notcharg" id="notcharg_1"   /> 显示未分管
					   <input type="radio" name="notcharg" id="notcharg_2"   /> 显示已分管
					</td>
				</tr>
				
				
				<tr>
					<td colspan="10" align="center" valign="middle" style="background-color:rgb(255, 255, 255);">
					   <c:if test="${pvg.PVG_BWS eq 1 }">
						<input id="query" type="button" class="btn" value="查询(Q)" title="Alt+Q" accesskey="Q">&nbsp;&nbsp;
						</c:if>
						<c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">&nbsp;&nbsp;
						</c:if>
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

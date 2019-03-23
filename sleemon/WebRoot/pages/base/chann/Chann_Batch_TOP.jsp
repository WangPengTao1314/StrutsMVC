
<!--  
/**
 * @module 系统管理
 * @func 批量维护区域经理
 * @version 1.1
 * @author zcx
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
	<title>批量维护区域经理</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/chann/Chann_Batch_TOP.js"></script>
</head>
<body >
<div id="querydiv">
<form id="mainForm" method="post" action="#">
 <input type="hidden" name="search" id="search" value="true" />
 <input type="hidden" name="selectParams" value=" STATE in('启用') " />
 <input type="hidden" name="selectArea" value=" RYZT='启用' and DELFLAG=0 and JGXXID='${JGXXID}' ">
 <input type="hidden" name="selectSerParam" value=" STATE in( '启用') and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
 <input type="hidden" name="selectPointParam" value=" STATE in( '启用') and DEL_FLAG='0' ">
 <input type="hidden" name="selectChannParams" value="STATE in( '启用','停用') and DEL_FLAG=0 ">
 
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2" valign="top">
			<table id="querytb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
			     <td  colspan="4" class="detail_content" >
			      <span   class="label_down" style="margin-top: 2px;">&nbsp;查询条件&nbsp;</span>
			     </td>
			    </tr>
			    <tr> 
				    <td width="8%" nowrap align="right" class="detail_label">区域编号：</td>
					<td width="15%" class="detail_content"  >
					    <input type="text"    id="AREA_NO"   name="AREA_NO"  value="${params.AREA_NO}" inputtype="string"  autocheck="true" mustinput="true" readonly/>
                        <input type="hidden"  id="AREA_ID"   name="AREA_ID"   value="${params.AREA_ID}">
						<img align="absmiddle" name="selSJJG" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		  								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content"  >
					    <input type="text" id="AREA_NAME" name="AREA_NAME"  value="${params.AREA_NAME}"/>
					</td>
				</tr>
				<tr>
				    <td width="8%" nowrap align="right" class="detail_label">渠道编号：</td>
					<td width="15%" class="detail_content"  >
					   <input type="hidden" id="CHANN_ID"   value="${params.CHANN_ID}"/>
					   <input type="text" id="CHANN_NO"   value="${params.CHANN_NO}"/>
					   <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectChannParams')">
				
					</td>
				    <td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content"  >
					    <input type="text" id="CHANN_NAME" name="CHANN_NAME"   value="${params.CHANN_NAME}"/>
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label">省份：</td>
					 <td width="15%" class="detail_content">
	   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[0]','PROV,CITY', 'PROV,CITY', '')">
					 </td>
					<td width="10%" nowrap align="right" class="detail_label">城市：</td>
					<td width="15%" class="detail_content">
						<input name="CITY" id="CITY" json="CITY" type="text" value="${params.CITY}">
					</td>
				</tr>
				<tr>
					<td colspan="20" align="center" valign="middle" style="background-color:rgb(255, 255, 255);">
						<c:if test="${pvg.PVG_BWS eq 1 }">
						 <input id="query" type="button" class="btn" value="查询(Q)" title="Alt+Q" accesskey="Q">&nbsp;&nbsp; 
						</c:if>
					</td>
				</tr>
				
				<tr>
			     <td  colspan="4" class="detail_content" >
			       <span   class="label_down" style="margin-top: 2px;">&nbsp;批量维护&nbsp;</span>
			     </td>
			    </tr>
				<tr> 
					<td width="8%" nowrap align="right" class="detail_label">区域经理名称：</td>
					<td width="15%" class="detail_content"  >
					    <input type="hidden" id="AREA_MANAGE_ID"   name="AREA_MANAGE_ID"   inputtype="string" autocheck="true" label="区域经理ID" json="AREA_MANAGE_ID"     value="${rst.AREA_MANAGE_ID}" />
						<input type="hidden" id="AREA_MANAGE_TEL"  name="AREA_MANAGE_TEL"  inputtype="string" autocheck="true" label="区域经理电话"  json="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}" />
						<input type="text"   id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" autocheck="true" label="区域经理名称"  inputtype="string" mustinput="true" label="区域经理名称"  json="AREA_MANAGE_NAME" value="${rst.AREA_MANAGE_NAME}" READONLY />
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('System_0', false, 'AREA_MANAGE_ID', 'RYXXID', 'forms[0]','AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL', 'RYXXID,XM,SJ', 'selectArea')">
						<c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="" type="button" class="btn" value="保存区域经理 " onclick="saveChann(1);"/>
						</c:if>
					</td>
				    <td width="8%" nowrap align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content"  >
					    <input type="hidden"  id="AREA_NO_S"   name="AREA_NO_S"  json="AREA_NO"   value="${params.AREA_NO}" />
                        <input type="hidden"  id="AREA_ID_S"   name="AREA_ID_S"  json="AREA_ID"  value="${params.AREA_ID}" />
                        <input type="text"    id="AREA_NAME_S" name="AREA_NAME_S"  json="AREA_NAME" label="区域名称"  value="${params.AREA_NAME}" inputtype="string" mustinput="true" autocheck="true" READONLY/>
						<img align="absmiddle" name="selSJJG" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_18', false, 'AREA_ID_S', 'AREA_ID', 'forms[0]','AREA_NO_S,AREA_NAME_S', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		  								   		
						<c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="" type="button" class="btn" value="保存区域 " onclick="saveChann(2);"/>
						</c:if>
					</td>
				</tr>
				<tr> 
					<td width="8%" nowrap align="right" class="detail_label">生产基地：</td>
					<td width="15%" class="detail_content" >
					    <input id="SHIP_POINT_ID"   json="SHIP_POINT_ID" name="SHIP_POINT_ID" type="hidden"   value="${rst.SHIP_POINT_ID}" />
						<input id="SHIP_POINT_NO"   json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="hidden" label="生产基地编号" value="${rst.SHIP_POINT_NO}" />
						<input id="SHIP_POINT_NAME"  autocheck="true" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" type="text" label="生产基地名称" value="${rst.SHIP_POINT_NAME}" inputtype="string"   mustinput="true"    READONLY>
						<img align="absmiddle" name="selZONE" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" 
							onClick="selCommon('BS_22', false, 'SHIP_POINT_ID','SHIP_POINT_ID', 'forms[0]','SHIP_POINT_NO,SHIP_POINT_NAME', 'SHIP_POINT_NO,SHIP_POINT_NAME', 'selectPointParam')">
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="" type="button" class="btn" value="保存生产基地 " onclick="saveChann(3);"/>
						</c:if>							
				    </td>
					<td width="8%" nowrap align="right" class="detail_label">区域服务中心：</td>
					<td width="15%" class="detail_content">
					<input id="AREA_SER_ID" json="AREA_SER_ID" name="AREA_SER_ID" type="hidden"  value="${rst.AREA_SER_ID}"  />
					<input id="AREA_SER_NO" json="AREA_SER_NO" name="AREA_SER_NO" type="hidden"  label="区域服务中心编号" value="${rst.AREA_SER_NO}"  />
	                  <input id="AREA_SER_NAME" name="AREA_SER_NAME" type="text"
								inputtype="string" label="区域服务中心名称" json="AREA_SER_NAME"  inputtype="string" mustinput="true" autocheck="true" 
								value="${rst.AREA_SER_NAME }"  READONLY>
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[0]','AREA_SER_ID,AREA_SER_NO,AREA_SER_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectSerParam')">
						<c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="" type="button" class="btn" value="保存区服 " onclick="saveChann(4);"/>
						</c:if>			
					</td>
				</tr>
				<%--<tr>
					<td colspan="20" align="center" valign="middle" style="background-color:rgb(255, 255, 255);">
						<c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">&nbsp;&nbsp;
						</c:if>
					</td>
				</tr>
			--%></table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>

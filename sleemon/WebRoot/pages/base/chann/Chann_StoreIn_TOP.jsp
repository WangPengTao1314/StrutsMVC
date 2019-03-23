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
	<title></title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/chann/Chann_StoreIn_TOP.js"></script>
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
					    <input type="text"  label="区域编号"   id="AREA_NO"   name="AREA_NO"  value="${params.AREA_NO}" inputtype="string"  autocheck="true" readonly/>
                        <input type="hidden"  id="AREA_ID"    name="AREA_ID"   value="${params.AREA_ID}">
						<img align="absmiddle" name="selSJJG" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_18', true, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		  								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content"  >
					    <input type="text" id="AREA_NAME" name="AREA_NAME"  value="${params.AREA_NAME}" READONLY/>
					</td>
				</tr>
				<tr>
				    <td width="8%" nowrap align="right" class="detail_label">渠道编号：</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" id="CHANN_ID"   value="${params.CHANN_ID}"/>
 					   <input type="text" id="CHANN_NO" name="CHANN_NO" json="CHANN_NO" autocheck="true" inputtype="string" readonly />
					   <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectChannParams')">
				
					</td>
				    <td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content"  >
					    <input type="text" id="CHANN_NAME" name="CHANN_NAME"   value="${params.CHANN_NAME}" READONLY/>
					</td>
				</tr>
				<tr>
				   <td width="8%" nowrap align="right" class="detail_label">渠道类型：</td>
					<td width="15%" class="detail_content"  >
			        	<select name="CHANN_TYPE" id="CHANN_TYPE" json="CHANN_TYPE" style="width: 155"></select>
					</td>
				    <td width="8%" nowrap align="right" class="detail_label">渠道级别：</td>
					<td width="15%" class="detail_content"  >
					    <select label="渠道级别" name="CHAA_LVL" json="CHAA_LVL"
							valueType="chinese:false" inputtype="string"
							style="width: 155" id="CHAA_LVL" json="CHAA_LVL"
							value="${params.CHAA_LVL}">
						</select>
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
					<td width="15%" nowrap align="center" class="detail_label">是否修改入库数量：</td>
					<td width="20%" class="detail_content" colspan="3">
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				     
					    <input type="checkbox" onclick="upStoreInFlag();" id="storeInflag" <c:if test="${rst.IS_UPDATE_STOREIN_FLAG==1}"> checked</c:if> >
						<input type="hidden"  id="IS_UPDATE_STOREIN_FLAG" json="IS_UPDATE_STOREIN_FLAG"
														name="IS_UPDATE_STOREIN_FLAG"  value="${rst.IS_UPDATE_STOREIN_FLAG}">
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <input id="" type="button" class="btn" value="保存(S)" onclick="saveChann(1);"/>
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
	//$(function(){
		SelDictShow("CHANN_TYPE", "171", '${params.CHANN_TYPE}', "");
		//SelDictShow("CHAA_LVL", "169", '${params.CHAA_LVL}', "");
		SelDictShow("CHAA_LVL", "BS_100", '${params.CHAA_LVL}', "");
	//});
</script>
</html>



<!--  
/**
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.1
 * @author  zcx
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>系统业务表一览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/base/table/Table_View.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前表英文名称&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;${TABLE_NAME_EN}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中文名称&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;${map.TABLE_NAME_ZH}</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" dbname="TABLE_COL_EN">英文字段名</th>
					<th nowrap align="center" dbname="TABLE_COL_ZH">中文字段名</th>
					<th nowrap align="center" dbname="COL_TYPE">字段类型</th>
					<th nowrap align="center" dbname="COL_LENGTH">字段长度</th>
					<th nowrap align="center" dbname="TABLE_NAME_EN">关联表英文名</th>
					<th nowrap align="center" dbname="TABLE_NAME_ZH">关联表中文名</th>
				</tr>
				<c:forEach items="${result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" onclick="setSelEid(document.getElementById('eid${rr}'));">
                        <td nowrap align="center">${rst.TABLE_COL_EN}&nbsp;</td>
                        <td nowrap align="center">${rst.TABLE_COL_ZH}&nbsp;</td>
                        <td nowrap align="center">${rst.COL_TYPE}&nbsp;</td>
                        <td nowrap align="center">${rst.COL_LENGTH}&nbsp;</td>
                        <td nowrap align="center"><a href="#" onclick="orpenWindow('${rst.RELATE_TABLE_NAME_EN}');">${rst.RELATE_TABLE_NAME_EN}</a></td>
                        <td nowrap align="center"><a href="#" onclick="orpenWindow('${rst.RELATE_TABLE_NAME_EN}');">${rst.RELATE_TABLE_NAME_ZH}</a></td>
                        <script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.TABLE_COL_EN}'));
						   });
					    </script>
					</tr>
				</c:forEach>
				<c:if test="${empty result}">
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
					<input type="hidden" id="TABLE_COL_EN" name="TABLE_COL_EN" value="${TABLE_COL_EN}" />
					<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
					<span id="hidinpDiv" name="hidinpDiv"></span>
					${paramCover.unCoveredForbidInputs}
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
<div id="querydiv" class="query_div" >
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG='0' ">
	<input id="selcondition" type="hidden" name="selcondition" value=" 1=1 " />
	<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is not null ">
	<input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >编号：</td>
					<td nowrap width="15%" class="detail_content">														
						<input id="DISTRIBUTOR_NO" json="DISTRIBUTOR_NO"  name="DISTRIBUTOR_NO" type="text" inputtype="string"
							  value="${params.DISTRIBUTOR_NO}" >	
					</td>
					<td width="10%" nowrap align="right" class="detail_label">名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="DISTRIBUTOR_NAME" name="DISTRIBUTOR_NAME" id="DISTRIBUTOR_NAME" value="${params.DISTRIBUTOR_NAME }"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">类型：</td>
					<td width="15%" class="detail_content">
						<select name="DISTRIBUTOR_TYPE" id="DISTRIBUTOR_TYPE" json="DISTRIBUTOR_TYPE" style="width: 155"></select>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">战区：</td>
					<td width="15%" class="detail_content">
						<input name="AREA_NAME_P" id="AREA_NAME_P" json="AREA_NAME_P" type="text" value="${params.AREA_NAME_P}">
						<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_26', false, 'AREA_NAME_P', 'AREA_NAME_P', 'forms[1]',
										'AREA_NAME_P', 
										'AREA_NAME_P', 
										'selectParamsArea');">
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >所属加盟商渠道编号：</td>
					<td nowrap width="15%" class="detail_content" >
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
								 autocheck="true" maxlength="30" value="${params.CHANN_NO}">
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_187', false, 'CHANN_NO', 'CHANN_NO', 'forms[1]',
																'CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_NAME,PROV,CITY,COUNTY', 
																'CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_NAME,PROV,CITY,COUNTY', 
																'selectChann')">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">所属加盟商渠道名称：</td>
					<td width="15%" class="detail_content">
						<input id="CHANN_NAME" name="CHANN_NAME" type="text" inputtype="string"
								 label="所属加盟商渠道名称"  json="CHANN_NAME" value="${params.CHANN_NAME }">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">制单时间从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_CRE_TIME" id="BEGIN_CRE_TIME"name="BEGIN_CRE_TIME" value="${params.BEGIN_CRE_TIME}" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">制单时间到：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="END_CRE_TIME" id="END_CRE_TIME" value="${params.END_CRE_TIME }" name="END_CRE_TIME" autocheck="true" inputtype="string" label="制单时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);"/>
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label">营销经理：</td>
					<td width="15%" class="detail_content">
					    <input name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" type="text"  value="${params.AREA_MANAGE_NAME}">
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[1]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
					</td>
					<td width="10%" nowrap align="right" class="detail_label">省份：</td>
					 <td width="15%" class="detail_content">
	   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[1]','PROV,CITY,COUNTY', 'PROV,CITY,COUNTY', '')">
					 </td>
					<td width="10%" nowrap align="right" class="detail_label">城市：</td>
					<td width="15%" class="detail_content">
						<input name="CITY" id="CITY" json="CITY" type="text" value="${params.CITY}">
					</td>
					
					<td width="10%" nowrap align="right" class="detail_label">区县：</td>
					<td width="15%" class="detail_content">
						<input name="COUNTY" id="COUNTY" json="COUNTY" type="text" value="${params.COUNTY}">
					</td>					
				</tr>
				<tr>
				    
				    <td width="10%" nowrap align="right" class="detail_label">联系人：</td>
					<td width="15%" class="detail_content">
						<input name="PERSON_CON" id="PERSON_CON" json="PERSON_CON" type="text"  value="${params.PERSON_CON}">
					</td>										
				    <td width="10%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select name=STATE id="STATE" json="STATE" style="width: 155"></select>
					</td>
					<td width="10%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="10%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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
<script type="text/javascript">
    function orpenWindow(TABLE_NAME_EN){
		window.showModalDialog("table.shtml?action=toView&TABLE_NAME_EN="+TABLE_NAME_EN,window,"dialogwidth=1000px; dialogheight=800px; status=no");
	 }
</script>

<script language="JavaScript">
	SelDictShow("STATE","32","${params.STATE}","");
	SelDictShow("DISTRIBUTOR_TYPE", "201", '${params.DISTRIBUTOR_TYPE}', "");	
</script>
</html>

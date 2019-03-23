<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护 
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>未排货品查看</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/turnoverhd/Turnoverhd_Unplan_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	   .lst_div_{
			width: 100%;
			overflow-x: auto; 
			overflow-y: auto; 
			 
			background-color: #fff;
		}
	   
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td>当前位置：销售管理>>发运管理>>未排货物查看</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;height: 80%">
			<table id="ordertb" width="100%" border="0"   cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属区域</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_FLAG" >特殊工艺</th>
					<th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="PLANED_NUM" >排车数量</th>
                    <th  nowrap="nowrap" align="center" dbname="NO_PLAND_NUM" >未排车数量</th>
                    <th  nowrap="nowrap" align="center" dbname="SENDED_NUM" >已发数量</th>
                    
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">

                     <td nowrap="nowrap" align="center">${rst.SALE_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                     <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}">无</c:if>
                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}"><font color="red">有</font>
                       <input type="button" class="btn" value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRICE}');"/>
                     </c:if>
                      &nbsp;
                     </td>
                     <td  nowrap="nowrap" align="center">
                       <c:if test="${rst.IS_NO_STAND_FLAG eq 1}">是</c:if>
                        <c:if test="${rst.IS_NO_STAND_FLAG ne 1}">否</c:if>
                     </td>
                     <td  nowrap="nowrap" align="right">${rst.ORDER_NUM}&nbsp;</td>
                     <td  nowrap="nowrap" align="right">${rst.PLANED_NUM}&nbsp;</td>
                     <td  nowrap="nowrap" align="right">${rst.NO_PLAND_NUM} &nbsp; </td>
                     <td  nowrap="nowrap" align="right">${rst.SENDED_NUM}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="19" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
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
<tr  >
<td height="20"></td>
</tr>
 
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<input type="hidden" id="selParam" name="selParam" value=" STATE in('启用','停用')"/>
<input type="hidden" name="selectParam" value=" STATE ='启用' and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
			      <td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
	   				  <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
					</td>
					
					<td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[1]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="" />
	   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>
			   </tr>
			   
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">是否有区域服务中心:</td>
					<td width="15%" class="detail_content">
					     <select name="IS_HAVE_AREA_SER" id="IS_HAVE_AREA_SER"  style="width: 155"></select>
				   </td>
				   
				   <td width="8%" nowrap align="right" class="detail_label">所属区域服务中心:</td>
					<td width="15%" class="detail_content">
						 <input id="AREA_SER_ID" json="AREA_SER_ID" name="AREA_SER_ID" type="hidden" >
				     <input id="AREA_SER_NAME" json="AREA_SER_NAME" name="AREA_SER_NAME" type="text"   value="${param.AREA_SER_NAME}"  >
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					  src="${ctx}/styles/${theme}/images/plus/select.gif"
					   onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[1]','AREA_SER_ID,AREA_SER_NAME', 'CHANN_ID,CHANN_NAME', 'selectParam')">
				   </td>
				   <td width="8%" nowrap align="right" class="detail_label">未排数量小于等于:</td>
					<td width="15%" class="detail_content">
						 <input id="UN_PLAN_NUM" json="UN_PLAN_NUM" name="UN_PLAN_NUM" type="text" value="${params.UN_PLAN_NUM}" label="未排数量" autocheck="true" inputtype="int">
				   </td>
				   <td width="8%" nowrap align="right" class="detail_label">是否非标:</td>
					<td width="15%" class="detail_content">
	   					 <select name="IS_NO_STAND_FLAG" id="IS_NO_STAND_FLAG"  style="width: 155"></select>
					</td>	
			   
				</tr>  
				<tr>
                   <td width="8%" nowrap align="right" class="detail_label">订货日期早于:</td>
				   <td width="15%" class="detail_content">
				    <input type="text"  id="ORDER_DATE" name="ORDER_DATE" value="${params.ORDER_DATE}"  onclick="SelectDate();"  />&nbsp;
				    <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE);"/>
				   </td>					
               </tr>
               
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input id="q_rest" type="reset" class="btn" value="重置(R)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
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
    SelDictShow("IS_HAVE_AREA_SER","194","${params.IS_HAVE_AREA_SER}","");
    SelDictShow("IS_NO_STAND_FLAG","194","${params.IS_NO_STAND_FLAG}","");
</script>
</html>

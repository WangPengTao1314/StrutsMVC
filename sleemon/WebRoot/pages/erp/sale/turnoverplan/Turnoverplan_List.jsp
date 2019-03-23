<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 制定交付计划
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
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/turnoverplan/Turnoverplan_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	
	   	#edit_remark{
			margin: 0px auto; 
			width:450px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:20px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td>当前位置：销售管理>>发运管理>>制定交付计划</td>
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
                   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
				   </c:if>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="return" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   
				   <div id="edit_remark" style="display: none;">
				    	<h4 align="center" style="margin-top: 10px">填写备注</h4>
				    	<table style="margin-left: 2px;">
				    		<tr>
				    			<td class="wenben" > 
				    				备注:
				    			</td>
				    			<td class="">
				    			  <textarea id="REMARK" name="REMARK" inputtype="string"  oVal="" onkeyup="changeTextAreaLength(this);"
				    			   autocheck="true" maxlength="250" rows="4"
				    			    cols="50"></textarea>
				    			</td>
				    		</tr>
				    		 
				    	</table>
				    	<input style="margin-top: 10px;margin-right: 10px;" id="close" class="btn" type="button" value="确定" onclick="modifyRemark();"/>
				    	<input style="margin-top: 10px" id="close" class="btn" type="button" value="关闭" onclick="closePage();"/>
				   </div>
				   
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap="nowrap" align="center">
					 <input type="checkbox" name="allChecked" id="allChecked"/>选择
					</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >订单类型</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR_NO" >收货地址编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货方地址</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属区域</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_SER_NAME" >所属区域服务中心</th>
                    <th  nowrap="nowrap" align="center" dbname="PROV" >省份</th>
                    <th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="TOTAL_VOLUME" >容积(立方)</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);checkStart();">
					 <td width="1%"align='center' >
						<input type="checkbox" style="valign:middle" name="eid" id="eid${rr}" value="${rst.SALE_ORDER_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.SALE_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_ADDR_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_SER_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PROV}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TOTAL_VOLUME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <input id="state${rst.SALE_ORDER_NO}" type="hidden" name="starts"  value="${rst.STATE}" />
                     <input type="hidden" id="RECV_ADDR${rst.SALE_ORDER_ID}"  lable="收货地址" value="${rst.RECV_ADDR}" />
                     
                     <input type="hidden" id="" name="RECV_CHANN_ID" json="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}" />
                     <input type="hidden" id="" name="RECV_CHANN_NO" json="RECV_CHANN_NO" value="${rst.RECV_CHANN_NO}" />
                     <input type="hidden" id="" name="RECV_CHANN_NAME" json="RECV_CHANN_NAME" value="${rst.RECV_CHANN_NAME}" />
                     
                     <input type="hidden" id="" name="ORDER_CHANN_ID" json="ORDER_CHANN_ID" value="${rst.ORDER_CHANN_ID}" />
                     <input type="hidden" id="" name="ORDER_CHANN_NO" json="ORDER_CHANN_NO" value="${rst.ORDER_CHANN_NO}" />
                     <input type="hidden" id="" name="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" value="${rst.ORDER_CHANN_NAME}" />
                     
                     
                     <input type="hidden" id="" name="RECV_ADDR_NO" json="RECV_ADDR_NO" value="${rst.RECV_ADDR_NO}" />
                     <input type="hidden" id="" name="RECV_ADDR" json="RECV_ADDR" value="${rst.RECV_ADDR}" />
                     
                     <input type="hidden" id="" name="CHANN_TYPE" json="CHANN_TYPE" value="${rst.CHANN_TYPE}" />
                     
                     <input type="hidden" id="" name="AREA_SER_ID" json="AREA_SER_ID" value="${rst.AREA_SER_ID}" />
                     <input type="hidden" id="" name="AREA_SER_NO" json="AREA_SER_NO" value="${rst.AREA_SER_NO}" />
                     <input type="hidden" id="" name="AREA_SER_NAME" json="AREA_SER_NAME" value="${rst.AREA_SER_NAME}" />
                     <input type="hidden" id="" name="SHIP_POINT_ID" json="SHIP_POINT_ID" value="${rst.SHIP_POINT_ID}" />
                     <input type="hidden" id="" name="SHIP_POINT_NAME" json="SHIP_POINT_NAME" value="${rst.SHIP_POINT_NAME}" />
                     <input type="hidden" id="cuur_id" name="cuur_id"  value="" />
                     <input id="" name="DATE_DIFF" type="hidden" value="${rst.DATE_DIFF}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<input type="hidden" id="selAREAParam" name="selAREAParam" value=" STATE in('启用','停用')"/>
<input type="hidden" name="selectParam" value=" STATE ='启用' and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用')  and CHANN_TYPE !='总部' "/>
<input type="hidden" id="selSaleOrderParam" name="selSaleOrderParam" value=" STATE in('审核通过','退回')"/>
<input type="hidden" id="selZONE" name="selZONE" value=" STATE in('启用','停用')"/>



<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
			       <td width="8%" nowrap align="right" class="detail_label">订单编号:</td>
				   <td width="15%" class="detail_content">
				    <input  type="hidden" id="SALE_ORDER_ID" name="SALE_ORDER_ID"    value=""/>
   					<input  type="text" id="SALE_ORDER_NO" name="SALE_ORDER_NO"    value="${params.SALE_ORDER_NO}"/>
   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_70', false, 'SALE_ORDER_ID', 'SALE_ORDER_ID', 'forms[1]','SALE_ORDER_NO', 'SALE_ORDER_NO', 'selSaleOrderParam')">
				
				    </td>	
				    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[1]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="" />
	   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>	
					
					<td width="8%" nowrap align="right" class="detail_label">所属区域服务中心:</td>
					<td width="15%" class="detail_content">
 					 <input id="AREA_SER_ID" json="AREA_SER_ID" name="AREA_SER_ID" type="hidden" >
                     <input id="AREA_SER_NO" json="AREA_SER_NO" name="AREA_SER_NO" type="hidden" >
				     <input id="AREA_SER_NAME" json="AREA_SER_NAME" name="AREA_SER_NAME" type="text"   value="${param.AREA_SER_NAME}"  >
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					     src="${ctx}/styles/${theme}/images/plus/select.gif"
					     onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[1]','AREA_SER_ID,AREA_SER_NO,AREA_SER_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParam')">
				     </td>	
			   </tr>
               
                 <tr>
					<td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[1]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="RECV_CHANN_ID" id="RECV_CHANN_ID" value="" />
	   				   <input type="text" id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  style="width:155" value="${params.RECV_CHANN_NAME}"/>
					</td>
                   <td width="8%" nowrap align="right" class="detail_label">所属区域编号:</td>
				   <td width="15%" class="detail_content">
   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAREAParam')">
				
				    </td>					
                    <td width="8%" nowrap align="right" class="detail_label">所属区域名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
	   				   <input id="AREA_NAME" name="AREA_NAME"  value="${params.AREA_NAME}"/>
					
					</td>			
                  
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">货品编号</td>
					<td width="15%" class="detail_content">
						<input id="PRD_NO" json="PRD_NO"  name="PRD_NO" type="text" inputtype="string"   value="${params.PRD_NO}" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称</td>
					<td width="15%" class="detail_content">
						<input name="PRD_NAME" type="text" value="${params.PRD_NAME}" id="PRD_NAME" json="PRD_NAME">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">规格型号：</td>
					<td width="15%" class="detail_content">
						<input type="text" value="${params.PRD_SPEC}" id="PRD_SPEC" name="PRD_SPEC"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">省份:</td>
					 <td width="15%" class="detail_content">
					    <input type="hidden" id="ZONE_ID" name="ZONE_ID"    value=""/>
	   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', false, 'PROV', 'PROV', 'forms[1]','PROV', 'PROV', '')">
					
					 </td>	
               </tr>
               
               <tr>
               		 <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
	   					 <select name="STATE" id="STATE"  style="width: 155"></select>
					 </td>	
					 
					 <td width="8%" nowrap align="right" class="detail_label"></td>
					 <td width="15%" class="detail_content">
					 </td>
					 <td width="8%" nowrap align="right" class="detail_label"></td>
					 <td width="15%" class="detail_content">
					 </td>
					 <td width="8%" nowrap align="right" class="detail_label"></td>
					 <td width="15%" class="detail_content">
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
    SelDictShow("STATE","BS_60","${params.STATE}","");
</script>
</html>

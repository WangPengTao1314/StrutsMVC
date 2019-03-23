<!--
 * @prjName:喜临门营销平台
 * @fileName:订货订单处理
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/goodsorder/Goodsorder_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
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
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>订单中心>>订货订单处理</td>
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
				   <c:if test="${pvg.PVG_RETURN eq 1 }">
				    <input  id="return" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">
				   </c:if><%--
				   <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		<input id="disposed" type="button" class="btn" value="处理(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   --%>
				   <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		 <input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
 				     <%--<%if("0".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
                       <input id="mergeCommit" type="button" class="btn" value="合并提交(M)" title="Alt+M" accesskey="M">
                     <%} %>
				   --%></c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
				     <input id="editRemark" type="button" class="btn" value="填写备注(R)" title="Alt+R" accesskey="R" onclick="showPage();">
				   </c:if> 
				    <!-- 
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="save" type="button" class="btn" value="保存(S)" title="Alt+F" accesskey="F">
				   </c:if>
				  
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="产能情况(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   
				  <input id="personal" type="button" class="btn" value="个性化设置" >--> 
				  
				  <div id="edit_remark" style="display: none;">
				    	<h4 align="center" style="margin-top: 10px">填写备注</h4>
				    	<table style="margin-left: 2px;">
				    		<tr>
				    			<td class="wenben" > 
				    				备注:
				    			</td>
				    			<td class="">
				    			  <textarea id="REMARK" name="REMARK" inputtype="string" oVal=""  onkeyup="changeTextArea(this);"
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
		<form action="">
		    <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and LEDGER_ID='${params.LEDGER_ID}' ">
		    <input type="hidden" name="XTYHID" id="XTYHID" value="${XTYHID}">
		    <input type="hidden" name="JGXXID" id="JGXXID" value="${JGXXID}">
		    <input type="hidden" name="BMXXID" id="BMXXID" value="${BMXXID}">
		    <input type="hidden" name="MERGER_GOODS_ORDER_IDS"   abel="合并提交反填的主表ID" id="MERGER_GOODS_ORDER_IDS" value="">
		    <input type="hidden" name="childJson" id="childJson" label="合并提交反填的子表数据" value="">
		    <input type="hidden" name="MERGER_REMARKS" id="MERGER_REMARKS" label="合并提交合并的备注" value="">
		    
			<input type="hidden" name="msg" id="msg" lable="反填的退回原因" value="">
			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
					<th  nowrap="nowrap" align="center" dbname="TOTAL_AMOUNT" >订货总额</th>
					<th  nowrap="nowrap" align="center" dbname="IS_USE_REBATE" >是否使用返利</th>
					<!--	0156414 -start-刘曰刚-2014-06-17				-->
					<!-- 隐藏要求到货日期 -->
<!--					<th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>-->
					<th  nowrap="nowrap" align="center" dbname="WAREA_NAME" >战区</th>
                    <th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AUDIT_NAME" >处理人</th>
                    <th  nowrap="nowrap" align="center" dbname="AUDIT_TIME" >处理时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.GOODS_ORDER_ID}"/>
					 </td>
					 
					 <td nowrap="nowrap" align="center" id="NO_${rst.GOODS_ORDER_ID}">${rst.GOODS_ORDER_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right" id="TOTAL_AMOUNT${rst.GOODS_ORDER_ID}">${rst.TOTAL_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                      <c:if test="${rst.IS_USE_REBATE eq 1}">
                                                                       是
                      </c:if>
                      <c:if test="${rst.IS_USE_REBATE ne 1}">
                                                                       否
                      </c:if>
                      &nbsp;
                     </td>
<!--                     <td nowrap="nowrap" align="center" >${rst.ORDER_RECV_DATE}</td>-->
 
                     <td nowrap="nowrap" align="left">${rst.WAREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TEL}&nbsp;</td> 
                     <td nowrap="nowrap" align="left"> ${rst.SHIP_POINT_NAME}</td>
                     <td nowrap="nowrap" align="left">${rst.AUDIT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AUDIT_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp; 
                       <input id="state${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                       <input id="REMARK${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.REMARK}" />
                       <input id="CREATOR${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.CREATOR}" />
                       <input id="GOODS_ORDER_ID${rst.GOODS_ORDER_ID}" json="GOODS_ORDER_ID"type="hidden"  value="${rst.GOODS_ORDER_ID}" />
                       <input id="GOODS_ORDER_NO${rst.GOODS_ORDER_ID}" json="GOODS_ORDER_NO" type="hidden"  value="${rst.GOODS_ORDER_NO}" />
                       
                       <input id="ORDER_CHANN_ID${rst.GOODS_ORDER_ID}"  json="ORDER_CHANN_ID"  name="ORDER_CHANN_ID" label="订货方ID"      type="hidden"  value="${rst.ORDER_CHANN_ID}" />
                       <input id="ORDER_CHANN_NO${rst.GOODS_ORDER_ID}"  json="ORDER_CHANN_NO"  name="ORDER_CHANN_NO" label="订货方编号"      type="hidden"  value="${rst.ORDER_CHANN_NO}" />
                       <input id="ORDER_CHANN_NAME${rst.GOODS_ORDER_ID}"  json="ORDER_CHANN_NAME"  name="ORDER_CHANN_NAME" label="订货方名称"      type="hidden"  value="${rst.ORDER_CHANN_NAME}" />
                       
                       <input id="RECV_CHANN_ID${rst.GOODS_ORDER_ID}"   json="RECV_CHANN_ID"   name="RECV_CHANN_ID"  label="收货方ID"      type="hidden"  value="${rst.RECV_CHANN_ID}" />
                       <input id="RECV_CHANN_NO${rst.GOODS_ORDER_ID}"   json="RECV_CHANN_NO"   name="RECV_CHANN_NO"  label="收货方编号"      type="hidden"  value="${rst.RECV_CHANN_NO}" />
                       <input id="RECV_CHANN_NAME${rst.GOODS_ORDER_ID}"   json="RECV_CHANN_NAME"   name="RECV_CHANN_NAME"  label="收货方名称"      type="hidden"  value="${rst.RECV_CHANN_NAME}" />
                       
                       <input id="BILL_TYPE${rst.GOODS_ORDER_ID}"       json="BILL_TYPE"       name="BILL_TYPE"      label="订单类型"       type="hidden"  value="${rst.BILL_TYPE}" />
                       <input id="AREA_ID${rst.GOODS_ORDER_ID}"         json="AREA_ID"         name="AREA_ID"      label="区域ID"           type="hidden"  value="${rst.AREA_ID}" />
                       <input id="SHIP_POINT_ID${rst.GOODS_ORDER_ID}"   json="SHIP_POINT_ID"   name="SHIP_POINT_ID"  label="生产基地"      type="hidden"  value="${rst.SHIP_POINT_ID}" />
                       <input id="DATE_DIFF${rst.GOODS_ORDER_ID}" name="DATE_DIFF"  type="hidden"  value="${rst.DATE_DIFF}" />
                        
                       
                     </td>
                   
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
			</form>
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
<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用') and CHANN_TYPE !='总部' "/>
<input type="hidden" id="selGoodOrderParam" name="selGoodOrderParam" value=" STATE in('未处理','总部退回','已处理') and DEL_FLAG=0 and ORDER_CHANN_ID in ${params.CHANNS_CHARG} "/>

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">订货订单编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="GOODS_ORDER_ID" name="GOODS_ORDER_ID" />
	   					<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO" value="${params.GOODS_ORDER_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_56', false, 'GOODS_ORDER_ID', 'GOODS_ORDER_ID', 'forms[2]','GOODS_ORDER_NO', 'GOODS_ORDER_NO', 'selGoodOrderParam')">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  type="text" style="width:155" value="${params.ORDER_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[2]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="" />
	   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                   					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
					    <select id="STATE" name="STATE" style="width:155"></select>
					</td>					
               </tr>
               
                 <tr>
                    <td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[2]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAREAParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
	   				  <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
					
					</td>					
                   					
                    <td width="8%" nowrap align="right" class="detail_label">订货日期从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="ORDER_DATE_BEGIN" name="ORDER_DATE_BEGIN" onclick="SelectDate();" size="20" value="${params.ORDER_DATE_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">订货日期到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="ORDER_DATE_END" name="ORDER_DATE_END" onclick="SelectDate();" size="20" value="${params.ORDER_DATE_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_END);">
					</td>						
               </tr>
               <tr>
                <td width="8%" nowrap align="right" class="detail_label">单据类型:</td>
				<td width="15%" class="detail_content">
				    <select id="BILL_TYPE" name="BILL_TYPE" style="width:155"></select>
				</td>
				<td width="8%" nowrap align="right" class="detail_label">货品编号:</td>
				<td width="15%" class="detail_content">
				    <input id="PRD_NO" name="PRD_NO"   value="${params.PRD_NO}"/>
				</td>
				<td width="8%" nowrap align="right" class="detail_label">货品名称:</td>
				<td width="15%" class="detail_content">
				    <input id="PRD_NAME" name="PRD_NAME"   value="${params.PRD_NAME}"/>
				</td>
				<td width="8%" nowrap align="right" class="detail_label">规格型号:</td>
				<td width="15%" class="detail_content">
				    <input id="PRD_SPEC" name="PRD_SPEC"   value="${params.PRD_SPEC}"/>
				</td>				
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input id="q_rest" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
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
  SelDictShow("STATE","BS_47","${params.STATE}","");
  SelDictShow("BILL_TYPE","BS_93","${params.BILL_TYPE}","");
  
</script>
</html>

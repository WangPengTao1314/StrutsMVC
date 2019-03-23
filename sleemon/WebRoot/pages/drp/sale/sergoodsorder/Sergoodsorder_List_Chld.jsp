<!--
 * @prjName:喜临门营销平台
 * @fileName:明细列表
 * @author zzb
 * @time   2013-08-30 15:55:09 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/sergoodsorder/Sergoodsorder_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="10">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
			  <tr>
			    <c:if test="${pvg.PVG_CAL_MX eq 1 }">
			     <%--<input id="bussClose" type="button" class="btn" value="取消(D)" title="Alt+D" accesskey="D" >--%>
			   </c:if>
			  </tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" id="resultSize" name="resultSize" value="${resultSize}">
				<tr class="fixedRow">
					<%--<th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>--%>
					<th  nowrap="nowrap" align="center" dbname="" >&nbsp;是否非标&nbsp;</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center" dbname="OLD_PRICE" >原价格</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="CANCEL_FLAG" >状态</th>
                    
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  ><%--
					 <td width="1%" align='center' >
						<input type="checkbox" name="mx"  style="valign:middle"  id="eid${rr}" value="${rst.GOODS_ORDER_DTL_ID}" />
					 </td>
					 --%>
					 <td align="center" id="IS_NO_STAND_FLAG_TD${rr}" >
					    <c:if test="${rst.IS_NO_STAND_FLAG eq 1}" >
					                是
					    </c:if>
					     <c:if test="${empty rst.IS_NO_STAND_FLAG  || rst.IS_NO_STAND_FLAG eq 0}" >
					                否
					    </c:if>
						<%--<input type="checkbox" label="非标"  name="IS_NO_STAND_FLAG_CHECKBOX" id="IS_NO_STAND_FLAG_CHECKBOX${rr}" <c:if test="${rst.IS_NO_STAND_FLAG eq 1}">checked="checked"</c:if>  disabled="disabled" />--%>
						
					 </td>
					 <td nowrap="nowrap" align="center" json="PRD_NO"  >${rst.PRD_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="left"   json="PRD_NAME" >${rst.PRD_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center" json="PRD_SPEC" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left"   json="PRD_COLOR" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json="BRAND" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json="STD_UNIT" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
	                  <c:if test="${rst.STATE eq '未处理'}" >
	                     <span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <font color="red"> 有 </font>
	                      </c:if>
	                     </span>
	                     <input type="button" class="btn"  value="编辑" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRD_ID}','${rr}');"/>
	                  </c:if>
	                  <c:if test="${rst.STATE ne '未处理'}" >
	                     <span id="SPECIAL_FLAG${rr}" >
	                     <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <font color="red"> 有 </font>
	                       <input type="button" class="btn" value="查看" onclick="showTechPage('${rst.SPCL_TECH_ID}');"/>
	                     </c:if>
	                    </span>
	                  </c:if>
	                  
	                     <input type="hidden"  json="PRD_ID"  name="PRD_ID"  lable="货品ID" value="${rst.PRD_ID}"/> 
	                     <input type="hidden"  json="VOLUME" name="VOLUME"  label="体积" value="${rst.VOLUME}"/>
	                     <input type="hidden"  json="SPCL_TECH_ID" id="SPCL_TECH_ID${rr}"  label="特殊工艺ID" value="${rst.SPCL_TECH_ID}"/>
	                     <input type="hidden"  json="PRICE" id="PRICE${rr}"  label="单价" value="${rst.PRICE}"/>
	                     <input type="hidden"  json="DECT_RATE" id="DECT_RATE${rr}"  label="折扣率" value="${rst.DECT_RATE}"/>
	                     <input type="hidden"  json="CREDIT_FREEZE_PRICE" id="CREDIT_FREEZE_PRICE${rr}"  label="信用冻结单价" value="${rst.CREDIT_FREEZE_PRICE}"/>
	                     <input type="hidden"  json="GOODS_ORDER_DTL_ID"  label="明细ID" value="${rst.GOODS_ORDER_DTL_ID}"/>
	                     <input type="hidden"  json="IS_NO_STAND_FLAG" id="IS_NO_STAND_FLAG${rr}"  label="是否非标" value="${rst.IS_NO_STAND_FLAG}"/>
	                     
                     </td>
                     <td nowrap="nowrap" align="right" json="DECT_PRICE" id="DECT_PRICE${rr}" >${rst.DECT_PRICE}&nbsp;</td> 
                     <td nowrap="nowrap" align="right"  id="OLD_PRICE${rr}" >${rst.OLD_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" json="ORDER_NUM" id="ORDER_NUM${rr}" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" json="ORDER_AMOUNT"  name="ORDER_AMOUNT" id="ORDER_AMOUNT${rr}" >${rst.ORDER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.ORDER_RECV_DATE}</td>
                     <td nowrap="nowrap" align="center" >
                       <c:if test="${rst.CANCEL_FLAG eq 1}">总部取消</c:if>
                        <c:if test="${empty rst.CANCEL_FLAG || rst.CANCEL_FLAG eq 0}">正常</c:if>
                     </td>
                     
                     <input type="hidden" id="CANCEL_FLAG${rr}" name="CANCEL_FLAG" value="${rst.CANCEL_FLAG}"/>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="GOODS_ORDER_DTL_IDS" name="GOODS_ORDER_DTL_IDS" value=""/>
</form>
</body>
</html>
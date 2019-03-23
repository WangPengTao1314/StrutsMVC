<!--
 * @prjName:喜临门营销平台
 * @fileName:整车计算
 * @author zzb
 * @time   2013-09-15 10:35:10 
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
	<title>整车计算</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/main/carcalculate/Carcalculate_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body onload="tt()" style="overflow: auto;overflow-x;auto;">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" 
  class="panel" style="">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		    <td>当前位置：整车计算</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20px;">
		<div id="querydiv" class="" >
		<form id="queryForm" action="" method="post">
		    <input type="hidden" name="" id="VOLUME" value="${params.VOLUME}"/>
			<table   cellSpacing=0 cellPadding=0 border=0 width="100%" class="detail">
			<tr>
		     <td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="">
					<tr>
					 <td width="3%" nowrap align="right" class="">渠道名称:</td>
					 <td width="8%" class="detail_content">
		   					<input id="CHANN_NAME" name="CHANN_NAME"  value="${params.CHANN_NAME}"/>
					 </td>
	                 <td width="3%" nowrap align="right" class="">订货订单编号:</td>
					 <td width="8%" class="detail_content">
		   				<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  style="width:155" value="${params.GOODS_ORDER_NO}"/>
					 </td>
					 <td></td>
					 <td></td>
					</tr>
					<tr>
	                 <td width="3%" nowrap align="right" class="">车型:</td>
					 <td width="8%" class="detail_content">
		   				<select style="width:155" name="carType" id="carType" onchange="getVolum(this);">
		   				</select>
					 </td>
					 <td width="3%" nowrap align="right" class="">包含:</td>
					 <td width="8%" class="detail_content">
					   
		   			   <input type="checkbox" name="notSend" id="notSend" value="总部未发" <c:if test="${params.notSend eq '总部未发'}">checked="checked"</c:if>  /> 总部未发
		   			   <input type="checkbox" name="planOrder" id="planOrder" value="计划订货" <c:if test="${params.planOrder eq '计划订货'}">checked="checked"</c:if>/> 计划订货
					 </td>
					 <td></td>
					 <td><input id="q_search" type="button" class="btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;</td>
					</tr>
					<tr>
			     </tr>
			     <tr>
			     <td >
						<input id="q_add" type="button" class="btn" value="新增(A)" title="Alt+A" accesskey="A">&nbsp;&nbsp;
					</td>
			     </tr>
			     
				</table>
			  </td>
			</tr>
		</table>
		</form>
		</div>
	</td>
</tr>
<tr >
	<td vAlign="top" style="height: 300px">
	<div   style="overflow: auto;height: 280px" class="lst_area">
	<form id="sendForm">
	 <input type="hidden" id="selectOrder" name="selectOrder" value="STATE in ('制作','撤销') and DEL_FLAG=0 and ORDER_CHANN_ID='${params.ORDER_CHANN_ID}'">
	  <input type="hidden" id="selectParams" name="selectParams" value="STATE='启用'">
			<table id="jsontb"  border="0" cellpadding="1" cellspacing="1" class="lst" width="100%" >
				<tr>
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_TYPE" >发货类型</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="HAVE_NUM" >未发数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ADJUST_NUM" >调整</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >体积</th>
                    <th  nowrap="nowrap" align="center" dbname="" >操作</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid('eid${rr}');">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.GOODS_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.GOODS_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SEND_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STD_UNIT}</td>
                     <td nowrap="nowrap" align="right">${rst.PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.HAVE_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                       <c:if test="${rst.SEND_TYPE eq '总部未发'}">
                         <input type="text" name="ADJUST_NUM" value="${rst.HAVE_NUM}" style="width: 100px;" onchange="countCar();"  readonly="readonly"/>
                       </c:if>
                       <c:if test="${rst.SEND_TYPE ne '总部未发'}">
                         <input type="text" name="ADJUST_NUM" value="${rst.HAVE_NUM}" style="width: 100px;" onchange="countCar();" />
                       </c:if>
                     </td>
                     <td nowrap="nowrap" align="right" name="cuur_VOLUME">${rst.VOLUME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                       <c:if test="${rst.SEND_TYPE eq '计划订货'}">
                          <input name="q_save" type="button" class="btn" value="保存" title="Alt+S" accesskey="S" onclick="savePrd(this);">
                       </c:if>
                       <c:if test="${rst.SEND_TYPE eq '新增'}">
                        <input name="q_save" type="button" class="btn" value="保存" title="Alt+S" accesskey="S" onclick="savePrd(this);">
                        <input name="q_delete" type="button" class="btn" value="删除" title="Alt+D" accesskey="D" onclick="deletePrd(this);">
                      </c:if>
                      </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
			</form>
			</div>
			<div align="right" style="height: 40px;"><font size="2" style="font-weight: bold" >共需要<font id="car"></font>车</font></div>
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
 <tr></tr>
 <tr></tr>
</table>
 
</body>
 
<script type="text/javascript">
  
  
  function tt(){
	  if(null =="${params.carType}" || ""=="${params.carType}"){
	     SelDictShow("carType","BS_109","5吨","");
      }else{
    	  SelDictShow("carType","BS_109","${params.carType}","");
      }
  }
  function _a(page){
	listForm.pageNo.value = page;
	showWaitPanel('');
	setTimeout('listForm.submit()',100);
}
  
</script>

</html>

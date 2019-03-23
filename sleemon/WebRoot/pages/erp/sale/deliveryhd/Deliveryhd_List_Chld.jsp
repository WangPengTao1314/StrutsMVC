<!--
 * @prjName:喜临门营销平台
 * @fileName:  发运管理 - 交付计划维护
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliveryhd/Deliveryhd_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	 .inputText{
	 border-top:0px;border-left:0px ;border-right:0px
	 }
	</style>
</head>
<body  class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr align="">
  <td height="20px" valign="top">
  <form>
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
      	<input id="CHANNS_CHARG" value="${CHANNS_CHARG}" type="hidden"/>
      	<input name="selectAddParams" id="selectAdd" value="" type="hidden"/>
      	<input name="SALE_ORDER_DTL_ID" id="SALE_ORDER_DTL_ID" type="hidden"/>
		<tr>
			<td colspan="20">
			<c:if test="${pvg.PVG_EDIT eq 1}"> 
			   <input id="add" type="button" class="btn" value="新增(A)" onclick="openAdd();" title="Alt+A" accesskey="A">
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			</c:if>
			<c:if test="${pvg.PVG_DELETE eq 1}"> 
			   <input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
			</c:if>
			   <c:if test="${pvg.PVG_ROW_CLOSE eq 1}"> 
				 <input id="close" name="close" type="button" class="btn" value="行关闭(O)" title="Alt+O" accesskey="O">
				</c:if>
				<span style="margin-left: 100px;">
					总数量：<input id="allNum" name="allNum" size="8"readonly value=""  class="inputText"/>
					总金额：<input id="allAmount" name="allAmount" size="13"  readonly class="inputText" />
					总体积：<input id="allVolume" name="allVolume" size="13" readonly class="inputText"/>
					总冻结金额：<input id="allFreeAmount" name="allFreeAmount" size="13" readonly class="inputText"/>
					
					已发数量：<input id="sendNum" name="allFreeAmount" size="13" readonly class="inputText"/>
					已发体积：<input id="sendVolume" name="allFreeAmount" size="13" readonly class="inputText"/>
					已发金额：<input id="sendAmount" name="allFreeAmount" size="13" readonly class="inputText"/>
				</span>
			</td>
		</tr>
   </table>
   </form>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<form id="ordertbForm" method="post">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
		    <input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
			<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
				<tr class="">
				   <th nowrap align="center">
					<input type="checkbox" style="valign:middle" id="allChecked" >
				   </th>
				   <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
				   <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >客户编号</th>
				   <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >客户名称</th>
				   <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>
				   <th  nowrap="nowrap" align="center" dbname="RECV_ADDR_NO" >收货地址编号</th>
				   <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货地址</th>
				   <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
				   <th  nowrap="nowrap" align="center" dbname="PLAN_NUM" >计划发运数量</th>
				   <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
				   <th  nowrap="nowrap" align="center" dbname="SENDED_NUM" >已发数量</th>
				   <th  nowrap="nowrap" align="center" dbname="NO_SEND_NUM" >未发数量</th>
				   <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
				   <th  nowrap="nowrap" align="center" dbname="DECT_PRICE">金额</th>
				   <th  nowrap="nowrap" align="center" dbname="U9_SALE_ORDER_NO" >U9订单编号</th>
<!--				   <th  nowrap="nowrap" align="center"  >U9订单行编号</th>-->
				   <th  nowrap="nowrap" align="center" dbname="IS_SEND_FIN">状态</th>
                   <th  nowrap="nowrap" align="center" dbname="REMARK">备注</th>
                   <th  nowrap="nowrap" align="center" dbname="EXPAND_REMARK">排骨架</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					<td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle" name="DELIVER_ORDER_DTL_ID" id="eid${rr}" json="DELIVER_ORDER_DTL_ID" value="${rst.DELIVER_ORDER_DTL_ID}" onclick="checkClick('eid${rr}')"/>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.SALE_ORDER_NO}</td>
					 <td nowrap="nowrap" align="left" >${rst.ORDER_CHANN_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="left" >${rst.ORDER_CHANN_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.ADVC_SEND_DATE}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.RECV_ADDR_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center" >${rst.RECV_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
	                     <span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
	                       <input type="button" class="btn"  value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRICE}');"/>
	                      </c:if>
	                     </span>
                     </td>
                     <td nowrap="nowrap" align="right" >
                     	<input type="text" value="${rst.PLAN_NUM}" onkeyup="countAmount('${rr}')" size="5" label="计划发运数量" onclick="$('#eid${rr}').attr('checked','checked');"  name="PLAN_NUM" json="PLAN_NUM" id="PLAN_NUM${rr}" />&nbsp;
                     	<input type="hidden" name="VOLUME" value="${rst.VOLUME}"/>
                     	<input type="hidden" name="CREDIT_FREEZE_PRICE" value="${rst.CREDIT_FREEZE_PRICE}"/>
                     	<input type="hidden" name="SALE_ORDER_DTL_ID" json="SALE_ORDER_DTL_ID" value="${rst.SALE_ORDER_DTL_ID}"/>
                     	<input type="hidden" name="SENDED_NUM" json="SENDED_NUM" value="${rst.SENDED_NUM}"/>
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}</td>
                     <td nowrap="nowrap" align="right" >${rst.SENDED_NUM}</td>
                     <td nowrap="nowrap" align="right" >${rst.NO_SEND_NUM}</td>
                     <td nowrap="nowrap" align="right" id="DECT_PRICE${rr}" name="DECT_PRICE">${rst.DECT_PRICE}</td>
                     <td  nowrap="nowrap" align="right" id="amount${rr}" name="price">${rst.DECT_PRICE * rst.PLAN_NUM}</td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_NO}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_DTL_NO}&nbsp;</td>-->
                     <td nowrap="nowrap" align="center" >
                          <c:if test="${3 eq rst.IS_SEND_FIN}">关闭</c:if>
	                      <c:if test="${empty rst.IS_SEND_FIN or 0 eq rst.IS_SEND_FIN}">未处理</c:if>
	                      <c:if test="${1 eq rst.IS_SEND_FIN}">已处理</c:if>
	                      <input type="hidden" name="IS_SEND_FIN" value="${rst.IS_SEND_FIN}"/>
                     </td>
                     <td nowrap="nowrap" align="left" >
                      <input type="text" name="REMARK" json="REMARK" onclick="$('#eid${rr}').attr('checked','checked');" maxlength="200" value="${rst.REMARK}"/>
                     </td>
                     <td nowrap="nowrap" align="left" >
                       ${rst.EXPAND_REMARK }
                     </td>
                     <%--
                     <script type="text/javascript">
                       
						  if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseInt(${rst.PLAN_NUM},10));
						  }else{
						  	var tempNum = parseInt($("#allNum").val(),10);
						  	var perNum = parseInt(${rst.PLAN_NUM},10);
						  	$("#allNum").val(tempNum + perNum);
						  }
						  
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(${rst.DECT_PRICE},10)*${rst.PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(${rst.DECT_PRICE},10)*${rst.PLAN_NUM};
						  	$("#allAmount").val((tempAmount + perAmount).toFixed(2));
						  }
						  
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  if($("#allVolume").val()==null || $("#allVolume").val()==""){
						  	$("#allVolume").val((parseFloat(volume,10)*${rst.PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempVolume = parseFloat($("#allVolume").val(),10);
						  	var perVolume = parseFloat(volume,10)*${rst.PLAN_NUM};

						  	$("#allVolume").val((tempVolume + perVolume).toFixed(2));
						  }
						  
						  var CREDIT_FREEZE_PRICE='${rst.CREDIT_FREEZE_PRICE}';
						  if(""==CREDIT_FREEZE_PRICE||null==CREDIT_FREEZE_PRICE){
						  	CREDIT_FREEZE_PRICE=0;
						  }
						  if($("#allFreeAmount").val()==null || $("#allFreeAmount").val()==""){
						  	$("#allFreeAmount").val((parseFloat(CREDIT_FREEZE_PRICE,10)*${rst.PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempFreeAmount = parseFloat($("#allFreeAmount").val(),10);
						  	var perFreeAmount = parseFloat(CREDIT_FREEZE_PRICE,10)*${rst.PLAN_NUM};

						  	$("#allFreeAmount").val((tempFreeAmount + perFreeAmount).toFixed(2));
						  }
						  
					   </script>
				    --%>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr id="trEmpty">
						<td height="25" colspan="25" align="center" class="lst_empty" id="tdEmpty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
			</form>
		</div>
	</td>
</tr>
</table>
 
</body>
<script type="text/javascript">
  SelDictShow("TRUCK_TYPE","BS_109","","");
</script>
</html>
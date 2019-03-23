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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/turnoverhd/Turnoverhd_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	 .inputText{
	 border-top:0px;border-left:0px ;border-right:0px
	 }
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr align="">
  <td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
			<td colspan="20">
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			    <c:if test="${pvg.PVG_ROW_CLOSE eq 1}"> 
				 <input id="close" type="button" class="btn" value="行撤销(O)" title="Alt+O" accesskey="O">
				</c:if>
				<span style="margin-left: 300px;">
					总数量：<input id="allNum" name="allNum" size="8"readonly value=""  class="inputText"/>
					总金额：<input id="allAmount" name="allAmount" size="15"  readonly class="inputText" />
					总体积：<input id="allVolume" name="allVolume" size="15" readonly class="inputText"/>
				</span>
			</td>
		</tr>
   </table>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<form id="ordertbForm">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
				<tr class="fixedRow">
				   <th nowrap align="center">
					<input type="checkbox" style="valign:middle" id="allChecked" >
				   </th>
				   <th  nowrap="nowrap" align="center" dbname="IS_FREE_FLAG" >是否赠品</th>
					<th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" >是否抵库</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新规格</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新花号</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_PLAN_NUM" >预排发运数量</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center"  >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货地址</th>
                    <th  nowrap="nowrap" align="center" >备注</th>
                    <th  nowrap="nowrap" align="center" >状态</th>
                    <th  nowrap="nowrap" align="center" >U9订单编号</th>
                    <th  nowrap="nowrap" align="center" >U9订单行编号</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}')); ">
					<td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle" name="mx_checkbox" id="eid${rr}" json="DELIVER_ORDER_DTL_ID" value="${rst.DELIVER_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					<td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_FREE_FLAG}"> 是  </c:if>
					    <c:if test="${'1' ne rst.IS_FREE_FLAG}"> 否 </c:if>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.SALE_ORDER_NO}</td>
					 <td nowrap="nowrap" align="left" >${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
	                     <%--<c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}">无</c:if>
	                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
	                        <font color="red">有</font>
	                        <input type="button" class="btn" value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}')" />
	                     </c:if>
                     --%>
                     
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
                     <td  nowrap="nowrap" align="center">
                       <c:if test="${rst.IS_NO_STAND_FLAG eq 1}">是</c:if>
                       <c:if test="${rst.IS_NO_STAND_FLAG ne 1}">否</c:if>
                     </td>
                     <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_BACKUP_FLAG}">  是 </c:if>
					    <c:if test="${'1' ne rst.IS_BACKUP_FLAG}"> 否 </c:if>
					 </td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" id="" name="SPCL_TECH_FLAG"  value="${rst.SPCL_TECH_FLAG}" />
                       <input type="text" name="NEW_SPEC" lable="新规格" json="NEW_SPEC" inputtype="string" autocheck="true" maxlength="50" value="${rst.NEW_SPEC}" onkeyup="checkline(this);"/><%--
                        <c:if test="${1 eq rst.SPCL_TECH_FLAG}"><span class=validate>&nbsp;*</span></c:if>
                     --%>
                     </td>
                     <td nowrap="nowrap" align="right" >
                     <input type="text" name="NEW_COLOR" lable="新花号" json="NEW_COLOR" inputtype="string" autocheck="true" maxlength="50" value="${rst.NEW_COLOR}" onkeyup="checkline(this);"/><%--
                      <c:if test="${1 eq rst.SPCL_TECH_FLAG}"><span class=validate>&nbsp;*</span></c:if>
                     --%>
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.ADVC_PLAN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     <td  nowrap="nowrap" align="right">${rst.DECT_PRICE * rst.ADVC_PLAN_NUM}</td>
                     <td nowrap="nowrap" align="left" >${rst.RECV_ADDR_SHORT}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.REMARK}</td>
                     <td nowrap="nowrap" align="center" >
                          <c:if test="${3 eq rst.IS_SEND_FIN}">关闭</c:if>
	                      <c:if test="${empty rst.IS_SEND_FIN or 0 eq rst.IS_SEND_FIN}">未处理</c:if>
	                      <c:if test="${1 eq rst.IS_SEND_FIN}">已处理</c:if>
                     </td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_NO}</td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_DTL_NO}</td>
                     
                     <script type="text/javascript">
                       
						  if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseInt(${rst.ADVC_PLAN_NUM},10));
						  }else{
						  	var tempNum = parseInt($("#allNum").val(),10);
						  	var perNum = parseInt(${rst.ADVC_PLAN_NUM},10);
						  	$("#allNum").val(tempNum + perNum);
						  }
						  
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(${rst.DECT_PRICE},10)*${rst.ADVC_PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(${rst.DECT_PRICE},10)*${rst.ADVC_PLAN_NUM};
						  	$("#allAmount").val((tempAmount + perAmount).toFixed(2));
						  }
						  
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  if($("#allVolume").val()==null || $("#allVolume").val()==""){
						  	$("#allVolume").val((parseFloat(volume,10)*${rst.ADVC_PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempVolume = parseFloat($("#allVolume").val(),10);
						  	var perVolume = parseFloat(volume,10)*${rst.ADVC_PLAN_NUM};

						  	$("#allVolume").val((tempVolume + perVolume).toFixed(2));
						  }
					   </script>
                     
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="24" align="center" class="lst_empty">
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
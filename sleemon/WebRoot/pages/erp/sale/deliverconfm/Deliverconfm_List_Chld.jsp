<!--
 * @prjName:喜临门营销平台
 * @fileName:  发运管理 - 发运确认
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliverconfm/Deliverconfm_List_Chld.js"></script>
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
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                	<td nowrap>
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
				   	   </c:if>
					</td>
					<td colspan="11" align="center">
						计划发运数量：<input id="planAllNum" name="planAllNum" size="8"readonly value=""  class="inputText"/>
						计划发运金额：<input id="planAllAmount" name="planAllAmount" size="15"  readonly class="inputText" />
						计划发运体积：<input id="planAllVolume" name="planAllVolume" size="15" readonly class="inputText"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 
						实际发运数量：<input id="realAllNum" name="realAllNum" size="8"readonly value=""  class="inputText"/>
						实际发运金额：<input id="realAllAmount" name="realAllAmount" size="15"  readonly class="inputText" />
						实际发运体积：<input id="realAllVolume" name="realAllVolume" size="15" readonly class="inputText"/>
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
 
<tr>
	<td valign="top">
		<div class="lst_area">
			<form id="ordertbForm" method="post">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
		    <input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
			<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" style="margin-right: 40px;" >
				<tr class="">
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
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货地址</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_PLAN_NUM" >预排发运数量</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_NUM" >计划发运数量</th>
                    <th  nowrap="nowrap" align="center" dbname="REAL_SEND_NUM" >实际发运数量</th>
                    <th  nowrap="nowrap" align="center"  >差异数量</th>
                    <th  nowrap="nowrap" align="center" dbname="NO_SEND_DEAL_TYPE" >剩余货品处理方式</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_NUM" >计发金额</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_NUM" >计发体积</th>
                    <th  nowrap="nowrap" align="center" >实发金额</th>
                    <th  nowrap="nowrap" align="center" >实发体积</th>
                    <th  nowrap="nowrap" align="center" >备注</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_SEND_FIN" >状态</th>
                    <th  nowrap="nowrap" align="center" >U9订单编号</th>
                    <th  nowrap="nowrap" align="center" >U9订单行编号</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}')); ">
					  <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle" name="mx_checkbox" id="eid${rr}" value="${rst.DELIVER_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 
					  <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_FREE_FLAG}">  是 </c:if>
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
                     <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}">无</c:if>
                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
                       <font color="red">有</font>
                       <input type="button" class="btn" value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRICE}')" />
                     </c:if>
                      &nbsp;
                     </td>
                     <td  nowrap="nowrap" align="center">
                       <c:if test="${rst.IS_NO_STAND_FLAG eq 1}">是</c:if>
                        <c:if test="${rst.IS_NO_STAND_FLAG ne 1}">否</c:if>
                     </td>
                     <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_BACKUP_FLAG}">   是 </c:if>
					    <c:if test="${'1' ne rst.IS_BACKUP_FLAG}"> 否  </c:if>
					 </td>
                     <td nowrap="nowrap" align="left" >${rst.RECV_ADDR_SHORT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="ADVC_PLAN_NUM">${rst.ADVC_PLAN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="PLAN_NUM">${rst.PLAN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" name="DELIVER_ORDER_DTL_ID" json="DELIVER_ORDER_DTL_ID" value="${rst.DELIVER_ORDER_DTL_ID}"/>
                       <input type="hidden" name="SALE_ORDER_ID" json="SALE_ORDER_ID" value="${rst.SALE_ORDER_ID}"/>
                       <input type="hidden" name="SALE_ORDER_DTL_ID" json="SALE_ORDER_DTL_ID" value="${rst.SALE_ORDER_DTL_ID}"/>
                       <input type="hidden" name="NO_SEND_NUM" json="NO_SEND_NUM" value="${rst.PLAN_NUM-rst.REAL_SEND_NUM}"/>
                       <input type="hidden" name="REAL_SEND_NUM" json="REAL_SEND_NUM" value="${rst.REAL_SEND_NUM}"/>
                       <input type="hidden" name="PLAN_NUM" json="PLAN_NUM" value="${rst.PLAN_NUM}"/>
                       <input type="hidden" name="ADVC_PLAN_NUM" json="ADVC_PLAN_NUM" value="${rst.ADVC_PLAN_NUM}"/>
                        ${rst.REAL_SEND_NUM}
                     </td>
                     <td nowrap="nowrap" align="right" name="NO_SEND_NUM">
                       ${rst.PLAN_NUM-rst.REAL_SEND_NUM}
                     </td>
                     <td nowrap="nowrap" align="center" >
                      ${rst.NO_SEND_DEAL_TYPE}
                       <%--<select id="NO_SEND_DEAL_TYPE${rr}"  style="width:125px;" name="NO_SEND_DEAL_TYPE" json="NO_SEND_DEAL_TYPE" 
                         <c:if test="${rst.PLAN_NUM == rst.REAL_SEND_NUM}"> disabled="disabled" </c:if>
                        ></select>
                       
                       <script type="text/javascript">
                           var selRowId = parent.document.getElementById("selRowId").value;
	                       var vstate = parent.topcontent.document.getElementById("state" + selRowId).value;
	                       var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
	                       if("已发货" == vstate){
	                    	     if("返修发运" == BILL_TYPE){
									  SelDictShow("NO_SEND_DEAL_TYPE${rr}","BS_78","","");
								  }else{
									  SelDictShow("NO_SEND_DEAL_TYPE${rr}","BS_56","","");
								  }
	                       }else{
	                    	     if("返修发运" == BILL_TYPE){
									  SelDictShow("NO_SEND_DEAL_TYPE${rr}","BS_78","${rst.NO_SEND_DEAL_TYPE}","");
								  }else{
									  SelDictShow("NO_SEND_DEAL_TYPE${rr}","BS_56","${rst.NO_SEND_DEAL_TYPE}","");
								  }
	                       }
						  
						</script>
                     --%>
                     </td>
                      <td nowrap="nowrap" align="right" name="PLAN_AMOUNT">${rst.PLAN_NUM * rst.DECT_PRICE}</td>
                     <td nowrap="nowrap" align="right" name="PLAN_VOLUME">${rst.PLAN_NUM * rst.VOLUME}</td>
                     <td nowrap="nowrap" align="right" name="REAL_AMOUNT">${rst.REAL_SEND_NUM * rst.DECT_PRICE}</td>
                     <td nowrap="nowrap" align="right" name="REAL_VOLUME">${rst.REAL_SEND_NUM * rst.VOLUME}</td>
                     <td nowrap="nowrap" align="left" >${rst.REMARK}</td>
                     <td nowrap="nowrap" align="center" >
	                      <c:if test="${3 eq rst.IS_SEND_FIN}">关闭</c:if>
	                      <c:if test="${empty rst.IS_SEND_FIN or 0 eq rst.IS_SEND_FIN}">未处理</c:if>
	                      <c:if test="${1 eq rst.IS_SEND_FIN}">已处理</c:if>
	                  </td>
                      <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_NO}</td>
                      <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_DTL_NO}</td>
                      <script type="text/javascript">
                       
                       //计划发运
						  if($("#planAllNum").val()==null || $("#planAllNum").val()==""){
						  	$("#planAllNum").val(parseInt(${rst.PLAN_NUM},10));
						  }else{
						  	var tempNum = parseInt($("#planAllNum").val(),10);
						  	var perNum = parseInt(${rst.ADVC_PLAN_NUM},10);
						  	$("#planAllNum").val(tempNum + perNum);
						  }
						  
						  if($("#planAllAmount").val()==null || $("#planAllAmount").val()==""){
						  	$("#planAllAmount").val((parseFloat(${rst.DECT_PRICE},10)*${rst.PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#planAllAmount").val(),10);
						  	var perAmount = parseFloat(${rst.DECT_PRICE},10)*${rst.PLAN_NUM};
						  	$("#planAllAmount").val((tempAmount + perAmount).toFixed(2));
						  }
						  
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  if($("#planAllVolume").val()==null || $("#planAllVolume").val()==""){
						  	$("#planAllVolume").val((parseFloat(volume,10)*${rst.PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempVolume = parseFloat($("#planAllVolume").val(),10);
						  	var perVolume = parseFloat(volume,10)*${rst.PLAN_NUM};
						  	$("#planAllVolume").val((tempVolume + perVolume).toFixed(2));
						  }
						  
						  
						//实际发运  
						  if($("#realAllNum").val()==null || $("#realAllNum").val()==""){
						  	$("#realAllNum").val(parseInt(${rst.REAL_SEND_NUM},10));
						  }else{
						  	var realtempNum = parseInt($("#realAllNum").val(),10);
						  	var realperNum = parseInt(${rst.REAL_SEND_NUM},10);
						  	$("#realAllNum").val(realtempNum + realperNum);
						  }
						  
						  if($("#realAllAmount").val()==null || $("#realAllAmount").val()==""){
						  	$("#realAllAmount").val((parseFloat(${rst.DECT_PRICE},10)*${rst.REAL_SEND_NUM}).toFixed(2));
						  }else{
						  	var realtempAmount = parseFloat($("#realAllAmount").val(),10);
						  	var realperAmount = parseFloat(${rst.DECT_PRICE},10)*${rst.REAL_SEND_NUM};
						  	$("#realAllAmount").val((realtempAmount + realperAmount).toFixed(2));
						  }
						  
						  if($("#realAllVolume").val()==null || $("#realAllVolume").val()==""){
						  	$("#realAllVolume").val((parseFloat(volume,10)*${rst.REAL_SEND_NUM}).toFixed(2));
						  }else{
						  	var realtempVolume = parseFloat($("#realAllVolume").val(),10);
						  	var realperVolume = parseFloat(volume,10)*${rst.REAL_SEND_NUM};
						  	$("#realAllVolume").val((realtempVolume + realperVolume).toFixed(2));
						  }
					   </script>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="27" align="center" class="lst_empty">
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

<form id="form1" action="#" method="post" name="form1">
	<input type="hidden" id="DELIVER_ORDER_DTL_IDS" name="DELIVER_ORDER_DTL_IDS" value=""/>
</form>

</body>

</html>
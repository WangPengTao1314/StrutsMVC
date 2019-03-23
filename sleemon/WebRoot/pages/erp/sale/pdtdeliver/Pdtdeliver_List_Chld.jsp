<!--
 * @prjName:喜临门营销平台
 * @fileName:  发运管理 - 货品发运
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/pdtdeliver/Pdtdeliver_List_Chld.js"></script>
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
 <tr align="center">
  <td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr align="left">
			<td colspan="20">
			    <c:if test="${pvg.PVG_ROW_CLOSE eq 1}"> 
				 <input id="close" name="close" type="button" class="btn" value="行撤销(O)" title="Alt+O" accesskey="O">
				</c:if>
				<span >
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
                    <th  nowrap="nowrap" align="center" dbname="ADVC_PLAN_NUM" >计划发运数量</th>
                    <th  nowrap="nowrap" align="center"  >剩余数量</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >单个体积</th>
                    <th  nowrap="nowrap" align="center" >体积</th>
                    <th  nowrap="nowrap" align="center" dbname="NO_SEND_DEAL_TYPE" >剩余货品处理方式</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_SEND_FIN" >状态</th>
                    <th  nowrap="nowrap" align="center" >U9订单编号</th>
                    <th  nowrap="nowrap" align="center" >U9订单行编号</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick=""> 
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
					    <c:if test="${'1' eq rst.IS_BACKUP_FLAG}">  是  </c:if>
					    <c:if test="${'1' ne rst.IS_BACKUP_FLAG}"> 否 </c:if>
					 </td>
                     <td nowrap="nowrap" align="left" >${rst.RECV_ADDR_SHORT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="ADVC_PLAN_NUM">${rst.ADVC_PLAN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" name="DELIVER_ORDER_DTL_ID" json="DELIVER_ORDER_DTL_ID" value="${rst.DELIVER_ORDER_DTL_ID}"/>
                       <input type="hidden" name="SALE_ORDER_ID" json="SALE_ORDER_ID" value="${rst.SALE_ORDER_ID}"/>
                       <input type="hidden" name="SALE_ORDER_DTL_ID" json="SALE_ORDER_DTL_ID" value="${rst.SALE_ORDER_DTL_ID}"/>
                       <input type="hidden" id="VOLUME" name="VOLUME" json="VOLUME" lable="体积" value="${rst.VOLUME}"/>
                       <input type="hidden" id="DECT_PRICE${rr}" name="DECT_PRICE" json="DECT_PRICE" lable="体积" value="${rst.DECT_PRICE}"/>
                       <input type="hidden" id="VOLUME${rr}" name="VOLUME" json="VOLUME" lable="体积" value="${rst.VOLUME}"/>
                       
                       <input type="hidden" name="ADVC_PLAN_NUM" json="ADVC_PLAN_NUM" value="${rst.ADVC_PLAN_NUM}"/>
                       <input type="text" name="PLAN_NUM" json="PLAN_NUM" id="PLAN_NUM${rr}" value="${rst.PLAN_NUM}" size="5" label="计划发运数量" autocheck="true"  maxlength="11"   mustinput="true" onkeyup="countNotSend(this,'${rr}');"  /> 
                      
                       <%--<c:if test="${empty rst.PLAN_NUM || rst.PLAN_NUM eq 0}">
                         <input type="text" name="PLAN_NUM" json="PLAN_NUM" id="PLAN_NUM${rr}" value="${rst.ADVC_PLAN_NUM}" size="5" label="计划发运数量" autocheck="true"  maxlength="11"  mustinput="true" onkeyup="countNotSend(this,'${rr}');" />
                       </c:if>
                        
                       --%><input type="hidden" id="NO_SEND_NUM${rr}" name="NO_SEND_NUM" json="NO_SEND_NUM" value="${rst.NO_SEND_NUM}"/>
                     </td>
                     
                     <td nowrap="nowrap" align="right" name="NO_SEND_NUM">
                        
                       ${rst.ADVC_PLAN_NUM-rst.PLAN_NUM}
                     </td>
                     
                     <td  nowrap="nowrap" align="center">${rst.DECT_PRICE}</td>
                     <td  nowrap="nowrap" align="center" id="allPrice${rr}">${rst.DECT_PRICE * rst.PLAN_NUM}</td>
                     <td  nowrap="nowrap" align="center">${rst.VOLUME}</td>
                     <td  nowrap="nowrap" align="center" id="allVol${rr}">${rst.VOLUME * rst.PLAN_NUM}</td>
                     
                     
                     <td nowrap="nowrap" align="left" >
                     <select id="NO_SEND_DEAL_TYPE${rr}"  name="NO_SEND_DEAL_TYPE" 
                     json="NO_SEND_DEAL_TYPE"  onmousewheel="return false" onchange="saveNextType(this);"></select>
                       <script type="text/javascript">
                       
						  var selRowId = parent.document.getElementById("selRowId").value;
						  var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
						  var vstate = parent.topcontent.$("#state" + selRowId).val();
						  if("返修发运" == BILL_TYPE){
							  SelDictShow("NO_SEND_DEAL_TYPE${rr}","BS_78","${rst.NO_SEND_DEAL_TYPE}","");
						  }else{
							  SelDictShow("NO_SEND_DEAL_TYPE${rr}","BS_56","${rst.NO_SEND_DEAL_TYPE}","");
						  }
						  if(vstate == "已提交生产"){
							  var NO_SEND_NUM = $("#NO_SEND_NUM${rr}").val();
							  if(NO_SEND_NUM == "0"){
								  disSelect("${rr}");
							  }
						  }
						  
						  if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseInt(${rst.PLAN_NUM},10));
						  }else{
						  	var tempNum = parseInt($("#allNum").val(),10);
						  	var perNum = parseInt(${rst.PLAN_NUM},10);
						  	$("#allNum").val(tempNum + perNum);
						  }
						  
						  
						  var amount = '${rst.DECT_PRICE}';
						  if(amount=="" || amount==null){
						  	amount = 0;
						  }
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(amount,10)*${rst.PLAN_NUM}).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(amount,10)*${rst.PLAN_NUM};
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
					   </script>
                     </td>
                     <td>
                      <input type="text"  name="REMARK" id="REMARK" json="REMARK" lable="备注"  inputtype="string"   maxlength="250" autocheck="true" onblur="saveInfo(this);"  value="${rst.REMARK}"/>
                     </td>
                     <td nowrap="nowrap" align="center" >
                        <c:if test="${3 eq rst.IS_SEND_FIN}">关闭</c:if>
	                    <c:if test="${empty rst.IS_SEND_FIN or 0 eq rst.IS_SEND_FIN}">未处理</c:if>
	                    <c:if test="${1 eq rst.IS_SEND_FIN}">已处理</c:if>
                        <input type="hidden" name="IS_SEND_FIN" value="${rst.IS_SEND_FIN}"/>
                     </td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_NO}</td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_DTL_NO}</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="29" colspan="27" align="center" class="lst_empty">
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

</html>
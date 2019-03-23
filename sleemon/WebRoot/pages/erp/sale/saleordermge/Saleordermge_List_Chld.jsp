<!--
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核 明细 列表
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleordermge/Saleordermge_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
		}
		.showDiv{
			margin: 0px auto; 
			width:150px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			background-color: #F1F4FB;
			border:1px solid black;
			height:30px;
		 
			text-align:center;
			display: block;
		}
		.div_border{
		   border:1px solid #F00;
		}
		
		#mycredit_show{
			margin: 0px auto; 
			width:400px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:120px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
		
	</style>
	
	
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
		<form>
		  <input type="hidden" id="selectContion" name="selectContion" value=""/>
		  <input type="hidden" id="SALE_ORDER_DTL_ID" name="SALE_ORDER_DTL_ID" value=""/>
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
			   	   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<%--
				   		<input id="cancel" type="button" class="btn" value="取消预订(Q)" title="Alt+Q" accesskey="Q">
				   		--%>
			   		   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			   		 </c:if>
			   		 <c:if test="${pvg.PVG_ROW_CLOSE eq 1 }">
			   		  <input id="close" type="button" class="btn" value="行关闭(C)" title="Alt+C" accesskey="C">
			   	     </c:if>
			   	     <c:if test="${pvg.PVG_ROW_MUST_CLOSE eq 1 }">
			   		  <input id="forceclose" type="button" class="btn" value="强制关闭(H)" title="Alt+H" accesskey="H">
			   	     </c:if>
			   	     <c:if test="${IS_NO_ADVC_DATE_FLAG ne 1}">
			   	     	<input id="batchModify" type="button" class="btn" value="批量修改发货日期(G)" title="Alt+G" accesskey="G">
			   	     </c:if>
			   	     
			   	    <span style="margin-left: 150px;">
				   		   总数量：<input id="allNum" name="allNum" readonly value="" class="text_underline"/>
						 总金额：<input id="allAmount" name="allAmount" readonly value="" class="text_underline"/>
						 总体积：<input id="allVolume" name="allVolume" readonly value="" class="text_underline"/>
			   		 </span>
			   		 <div id="mycredit_show" style="display: none;">
				    	<h4 align="center" style="margin-top: 10px">修改发货日期</h4>
				    	<table>
				    		<tr>
				    			<td class="wenben" > 
				    				预计发货日期:
				    			</td>
				    			<td class="">
				    				<input type="text" name="TEMP_DATE" id="TEMP_DATE" readonly="readonly" onclick="SelectDate();"/>
				    				 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(TEMP_DATE);"/>
				    			</td>
				    		</tr>
				    		 
				    	</table>
				    	<input style="margin-top: 10px" id="close" class="btn" type="button" value="确定" onclick="batchModifyFn();"/>
				    	<input style="margin-top: 10px" id="close" class="btn" type="button" value="关闭" onclick="closePage();"/>
				   </div> 
				</td>
				</tr>
			</table>
			</form>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-right: 20px;">
		 <form id="ordertbForm" name="ordertbForm" method="post">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
		    <input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
			<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
			<input type="hidden" id="selShipParam" name="selShipParam" value=" DEL_FLAG=0 and STATE='启用' "/>
			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
					<th  nowrap="nowrap" align="center" dbname="ROW_NO" >行号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_CAN_PRD_FLAG" >是否可生产</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_FREE_FLAG" >是否赠品</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" >是否抵库</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊规格说明</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新规格</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新花号</th>
                    <th  nowrap="nowrap" align="center" dbname="" >生产相关描述</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_AMOUNT" >订货金额</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >单个体积</th>
                    <%--
                    <th  nowrap="nowrap" align="center" dbname="PLANED_NUM" >已排车</th>
                    <th  nowrap="nowrap" align="center" dbname="NO_PLANED_NUM" >未排车</th>
                    --%>
                    <th  nowrap="nowrap" align="center" dbname="SENDED_NUM" >实发数量</th>
                    <th  nowrap="nowrap" align="center" dbname="NO_SENDED_NUM" >未发数量</th>
                    <th  nowrap="nowrap" align="center" dbname="CANCEL_NUM" >取消数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >备注</th>
                    <th  nowrap="nowrap" align="center" dbname="U9_SALE_ORDER_NO" >U9订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="U9_SALE_ORDER_DTL_NO" >U9订单行编号</th>
<!--                    <th nowrap align="center">&nbsp;原货品编号&nbsp;</th>-->
<!--                    <th nowrap align="center">&nbsp;原货品名称&nbsp;</th>-->
<!--		            <th nowrap align="center">&nbsp;原货品规格型号&nbsp;</th>-->
<!--		            <th nowrap align="center">&nbsp;原单价&nbsp;</th>-->
<!--		            <th  nowrap="nowrap" align="center" dbname="OLD_ORDER_NUM" >原订货数量</th>-->
		            <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
		            
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)"  onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle" name="mx_checkbox" id="eid${rr}" value="${rst.SALE_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.ROW_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                   <c:if test="${IS_NO_ADVC_DATE_FLAG ne 1}">
                     <td nowrap="nowrap" align="center" >
	                     <div style="">
	                      <input json="ADVC_SEND_DATE" id="ADVC_SEND_DATE${rr}" name="ADVC_SEND_DATE" readonly size="10" label="预计交货日期"  type="text" autocheck="true"  maxlength="32" inputtype="string"   onclick="SelectDate();checkline(this);justMX()"  value="${rst.ADVC_SEND_DATE}"/>
	                       <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE${rr});"/>
	                     </div>
	                     </td>
                     </c:if>
                     <c:if test="${IS_NO_ADVC_DATE_FLAG eq 1}">
                     	<td nowrap="nowrap" align="center" >${rst.ADVC_SEND_DATE}&nbsp;</td>
                     </c:if>
                     <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_CAN_PRD_FLAG}">  是 </c:if>
					    <c:if test="${'1' ne rst.IS_CAN_PRD_FLAG}">  否  </c:if>
					 </td>
                     <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_FREE_FLAG}">  是 </c:if>
					    <c:if test="${'1' ne rst.IS_FREE_FLAG}">  否  </c:if>
					 </td>
                     <td nowrap="nowrap" align="center" style="width: 100px;background-color: #F4CC87;" >
                     	<input type="checkbox" label="是否抵库"  name="IS_BACKUP_FLAG" json="IS_BACKUP_FLAG" id="IS_BACKUP_FLAG${rr}"  onclick="checkIsFB(this,'${rst.IS_NO_STAND_FLAG}','eid${rr}');" />
						<input type="hidden" id="HID_IS_BACKUP_FLAG${rr}" value="${rst.IS_BACKUP_FLAG}"/>
						<input type="hidden" id="STATE${rr}"  name="STATE" json="STATE"  value="${rst.STATE}"/>
						<input type="hidden" id="STATE${rst.SALE_ORDER_DTL_ID}"   value="${rst.STATE}"/>
						<a style="text-decoration: underline;color: blue;" id="" href="javascript:void(0)" onclick="queryStore('${rst.PRD_NO}','${rst.SPCL_TECH_ID}','${rr}');" >查看库存</a>
						<div id="showDiv${rr}" name="showDiv" class="showDiv" style="display:none;background-color: #F1F4FB;" ondblclick="hidIself(this);">
						 <span id=""  style="background-color: #F1F4FB;" class="">  U9可用库存：  </span>
				         <span id="showSpan${rr}"></span>
						</div>
                      </td>
                     <td nowrap="nowrap" align="center" >
                      <c:if test="${rst.IS_NO_STAND_FLAG ne 1}">否</c:if>
                      <c:if test="${rst.IS_NO_STAND_FLAG eq 1}">是</c:if>
                     </td>
                     <td nowrap="nowrap" align="center" >
                        <input type="hidden" id="" name="SPCL_TECH_FLAG" value="${rst.SPCL_TECH_FLAG}" />
                        <span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
	                       <input type="button" class="btn"  value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.SALE_ORDER_DTL_ID}','${rst.PRICE}');"/>
	                      </c:if>
	                     </span>
                     </td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" id="" name="SPCL_TECH_FLAG"  value="${rst.SPCL_TECH_FLAG}" />
                       <input type="text" name="NEW_SPEC" lable="新规格" id="NEW_SPEC${rst.SALE_ORDER_DTL_ID}" json="NEW_SPEC" inputtype="string" autocheck="true" maxlength="50" value="${rst.NEW_SPEC}" onkeyup="checkline(this);"/><%--
                        <c:if test="${1 eq rst.SPCL_TECH_FLAG}"><span class=validate>&nbsp;*</span></c:if>
                     --%>
                     </td>
                     <td nowrap="nowrap" align="right" >
                     <input type="text" name="NEW_COLOR" lable="新花号" json="NEW_COLOR" inputtype="string" id="NEW_COLOR${rst.SALE_ORDER_DTL_ID}" autocheck="true" maxlength="50" value="${rst.NEW_COLOR}" onkeyup="checkline(this);"/><%--
                      <c:if test="${1 eq rst.SPCL_TECH_FLAG}"><span class=validate>&nbsp;*</span></c:if>
                     --%>
                     </td>
                      <td nowrap="nowrap" align="right" >
                     <input type="text" name="PRODUCT_REMARK" lable="生产相关描述" json="PRODUCT_REMARK" inputtype="string" id="PRODUCT_REMARK${rst.SALE_ORDER_DTL_ID}" autocheck="true" maxlength="50" value="${rst.PRODUCT_REMARK}" onkeyup="checkline(this);"/>
                     </td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" id="PRDC_POINT_ID${rr}" name="PRDC_POINT_ID" json="PRDC_POINT_ID" value="${rst.PRDC_POINT_ID}"/>
                       <input type="text"   id="PRDC_POINT_NAME${rr}" name="PRDC_POINT_NAME" lable="生产基地" json="PRDC_POINT_NAME" inputtype="string" autocheck="true" maxlength="50" value="${rst.PRDC_POINT_NAME}" onkeyup="checkline(this);" disabled/>
                       <c:if test="${1 eq rst.IS_TOSS_FLAG}">
	                       <img align="absmiddle" name="selJGXX" style="cursor: hand" id="selImg${rr}"
					       src="${ctx}/styles/${theme}/images/plus/select.gif"
					       onClick="selCommon('BS_22', false, 'PRDC_POINT_ID${rr}', 'SHIP_POINT_ID', 'ordertbForm','PRDC_POINT_NAME${rr}', 'SHIP_POINT_NAME', 'selShipParam')">
				       </c:if>
				       	<script type="text/javascript">
						    var v = $("#HID_IS_BACKUP_FLAG${rr}").val();
						    var state = $("#STATE${rr}").val();
						    if("关闭" == state){
						    	$("#IS_BACKUP_FLAG${rr}").attr("disabled","disabled");
						    	$("#selImg${rr}").attr("disabled","disabled");
						    	
						    }
						    if(1 == v || "1" == v){
						    	$("#IS_BACKUP_FLAG${rr}").attr("checked","checked");
						    }
						</script>
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.VOLUME}&nbsp;</td>
                     <%--
                     <td nowrap="nowrap" align="right" >${rst.PLANED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM-rst.PLANED_NUM}&nbsp;</td>   
                     --%>
                     <td nowrap="nowrap" align="right" >${rst.SENDED_NUM}</td> 
                     <td nowrap="nowrap" align="right" >${rst.NO_SENDED_NUM}</td> 
                     <td nowrap="nowrap" align="right" >${rst.CANCEL_NUM}</td> 
                                      
                     <td nowrap="nowrap" align="left" >
                     	<input type="text" id="REMARK${rr}" maxlength="120" name="REMARK" json="REMARK" value="${rst.REMARK}" onkeyup="checkline(this);" />
                     	<input type="hidden" id="SALE_ORDER_DTL_ID${rr}"  name="SALE_ORDER_DTL_ID" json="SALE_ORDER_DTL_ID" value="${rst.SALE_ORDER_DTL_ID}"/>
                     	<input type="hidden" id=""  name="IS_CANCELED_FLAG"   value="${rst.IS_CANCELED_FLAG}"/>
                     	<input type="hidden" id=""  name="CANCEL_FLAG"   value="${rst.CANCEL_FLAG}"/>
                     </td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_NO}</td>
                     <td nowrap="nowrap" align="center" >${rst.U9_SALE_ORDER_DTL_NO}</td>
<!--                     <td nowrap="nowrap" align="right" >&nbsp;</td>-->
<!--                     <td nowrap="nowrap" align="right" >&nbsp;</td>-->
<!--                     <td nowrap="nowrap" align="right" >&nbsp;</td>-->
<!--                     <td nowrap="nowrap" align="right" >&nbsp;</td>-->
<!--                     <td nowrap="nowrap" align="right" >${rst.OLD_ORDER_NUM}&nbsp;</td>-->
                     <td nowrap="nowrap" align="center" >
                     	<c:if test="${rst.STATE ne '关闭'}">
	                     	<c:if test="${rst.ORDER_NUM>rst.SENDED_NUM}">正常</c:if>
	                     	<c:if test="${rst.ORDER_NUM eq rst.SENDED_NUM}">已发货</c:if>
                     	</c:if>
                     	<c:if test="${rst.STATE eq '关闭'}">关闭</c:if>
                     &nbsp;</td>
                     
                     <script type="text/javascript">
						  if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseFloat(${rst.ORDER_NUM},10));
						  }else{
						  	var tempNum = parseFloat($("#allNum").val(),10);
						  	var perNum = parseFloat(${rst.ORDER_NUM},10);
						  	$("#allNum").val(tempNum + perNum);
						  }
						  
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(${rst.ORDER_AMOUNT},10)).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(${rst.ORDER_AMOUNT},10);
						  	$("#allAmount").val((tempAmount + perAmount).toFixed(2));
						  }
						  
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  if($("#allVolume").val()==null || $("#allVolume").val()==""){
						  	$("#allVolume").val((parseFloat(volume,10)*${rst.ORDER_NUM}).toFixed(2));
						  }else{
						  	var tempVolume = parseFloat($("#allVolume").val(),10);
						  	var perVolume = (parseFloat(volume,10)*${rst.ORDER_NUM});
						  	$("#allVolume").val((tempVolume + perVolume).toFixed(2));
						  }
					   </script>
                     
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="32" align="center" class="lst_empty">
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
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="SALE_ORDER_DTL_IDS" name="SALE_ORDER_DTL_IDS" value=""/>
</form>
</body>
</html>
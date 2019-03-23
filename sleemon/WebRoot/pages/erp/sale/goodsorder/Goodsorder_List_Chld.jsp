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
<%@page import="com.hoperun.commons.model.Consts"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/goodsorder/Goodsorder_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	  		.showStore{
			margin: 0px auto; 
			width:150px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:30px;
			left:200px;
			top:50px;
			text-align:center;
			display: block;
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
		
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:150px;
		}
		.wenben{
			width: 65px;
			text-align: right;
		}
		.neirong{
			width:150px;
			text-align:left;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="10">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0>
			  <tr>
			    <c:if test="${pvg.PVG_CAL_MX eq 1 }">
			     <%--<input id="bussClose" type="button" class="btn" value="取消(D)" title="Alt+D" accesskey="D" >--%>
			   </c:if>
			   	<td colspan="20"> 
			   	  <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		<input id="save" type="button" class="btn" value="保存(D)" title="Alt+D" accesskey="D" >
			   	  </c:if>
			   	  <span style="margin-left: 20px;">
			   		总数量：<input id="allNum" name="allNum" readonly value="" class="text_underline"/>
					总金额：<input id="allAmount" name="allAmount" readonly value="" class="text_underline"/>
					总体积：<input id="allVolume" name="allVolume" readonly value="" class="text_underline"/>
				 </span>
				</td>
			   </tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" id="lst_area" onclick="hideShowDiv();">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" id="resultSize" name="resultSize" value="${resultSize}">
			<input type="hidden" id="mergeFlage" name="mergeFlage" lable="合并提交页面的标记"  value="${mergeFlage}">
			
				<tr class="fixedRow">
					<%--<th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>--%>
					<th  nowrap="nowrap" align="center" dbname="ROW_NO" >行号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <%--
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    --%>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="" >&nbsp;是否非标&nbsp;</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊规格说明</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新规格</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新花号</th>
                    <th  nowrap="nowrap" align="center" dbname="" >生产相关描述</th>
                    <%if("0".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
                    <th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" style="width: 100px;">是否抵库</th>
                    <%} %>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <%--<c:if test="${empty mergeFlage}"><!-- 合并提交页面不用显示下面的列 -->
	                    <th  nowrap="nowrap" align="center" dbname="OLD_PRICE" >原价格</th>
	                    <th  nowrap="nowrap" align="center" dbname="OLD_ORDER_NUM" >原订货数量</th>
                    </c:if>
                    --%>
                    <%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
                    <c:if test="${IS_NO_ADVC_DATE_FLAG ne 1}">
                    	<th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >预计交货日期</th>
                    </c:if>
                    <%} %>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="" >体积</th>
                    <%--
                    0156414 -start-刘曰刚-2014-06-17	
                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>
                    --%><th  nowrap="nowrap" align="center" dbname="CANCEL_FLAG" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
                    
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  >
					<%--
					 <td width="1%" align='center' >
						<input type="checkbox" name="mx"  style="valign:middle"  id="eid${rr}" value="${rst.GOODS_ORDER_DTL_ID}" />
					 </td>
					 --%>
					 <td nowrap="nowrap" align="center" json="ROW_NO"  >${rst.ROW_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center" json="PRD_NO"  >${rst.PRD_NO}
					 	<c:if test="${not empty rst.DM_SPCL_TECH_NO}">
					 		-${rst.DM_SPCL_TECH_NO}
					 	</c:if>
					 &nbsp;</td>
					 <td nowrap="nowrap" align="left"   json="PRD_NAME" >${rst.PRD_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="left">${rst.PAR_PRD_NAME}&nbsp;</td>
					 
					 <td nowrap="nowrap" align="center" json="PRD_SPEC" >${rst.PRD_SPEC}&nbsp;</td><%--
                     <td nowrap="nowrap" align="left"   json="PRD_COLOR" >${rst.PRD_COLOR}&nbsp;</td>
                     --%><td nowrap="nowrap" align="center" json="BRAND" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json="STD_UNIT" >${rst.STD_UNIT}&nbsp;</td>
                     
                     <td align="center" id="IS_NO_STAND_FLAG_TD${rr}" >
					    <c:if test="${rst.IS_NO_STAND_FLAG eq 1}" >
					                是
					    </c:if>
					     <c:if test="${empty rst.IS_NO_STAND_FLAG  || rst.IS_NO_STAND_FLAG eq 0}" >
					                否
					    </c:if>
					 </td>
					 
					 
                     <td nowrap="nowrap" align="center" >
	                	<span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
	                       <input type="button" class="btn"  value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.GOODS_ORDER_DTL_ID}','${rst.PRICE}');"/>
	                      </c:if>
	                     </span>
	                     
	                
	                  <%--
	                  <c:if test="${rst.STATE ne '未处理'}" >
	                     <span id="SPECIAL_FLAG${rr}" >
	                     <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
	                       <input type="button" class="btn" value="查看" onclick="showTechPage('${rst.SPCL_TECH_ID}');"/>
	                     </c:if>
	                    </span>
	                  </c:if>
	                  
	                     --%>
	                     <input type="hidden"  json="PRD_ID"  name="PRD_ID"  lable="货品ID" value="${rst.PRD_ID}"/> 
	                     <input type="hidden"  json="VOLUME" name="VOLUME"  label="体积" value="${rst.VOLUME}"/>
	                     <input type="hidden"  json="SPCL_TECH_ID" id="SPCL_TECH_ID${rr}"  label="特殊工艺ID" value="${rst.SPCL_TECH_ID}"/>
	                     <input type="hidden"  json="PRICE" id="PRICE${rr}"  label="单价" value="${rst.PRICE}"/>
	                     <input type="hidden"  json="DECT_RATE" id="DECT_RATE${rr}"  label="折扣率" value="${rst.DECT_RATE}"/>
	                     <input type="hidden"  json="REBATE_PRICE" id="REBATE_PRICE${rr}"  label="返利单价" value="${rst.REBATE_PRICE}"/>
	                     <input type="hidden"  json="CREDIT_FREEZE_PRICE" id="CREDIT_FREEZE_PRICE${rr}"  label="信用冻结单价" value="${rst.CREDIT_FREEZE_PRICE}"/>
	                     <input type="hidden"  json="GOODS_ORDER_DTL_ID"  label="明细ID" value="${rst.GOODS_ORDER_DTL_ID}"/>
	                     <input type="hidden"  json="IS_NO_STAND_FLAG" id="IS_NO_STAND_FLAG${rr}"  label="是否非标" value="${rst.IS_NO_STAND_FLAG}"/>
	                     <input type="hidden"  json="PRD_SUIT_FLAG" id=""  label="是否套件" value="${rst.PRD_SUIT_FLAG}"/>
	                     <input type="hidden"  json="AUTO_BACKUP_FLG" id=""  label="自动抵库标记" value="${rst.AUTO_BACKUP_FLG}"/>
	                      
                     </td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" id="" name="SPCL_TECH_FLAG"  value="${rst.SPCL_TECH_FLAG}" />
                       <input type="text" name="NEW_SPEC" lable="新规格" id="NEW_SPEC${rst.GOODS_ORDER_DTL_ID}" json="NEW_SPEC" inputtype="string" autocheck="true" style="width: 80px;" maxlength="50" value="${rst.NEW_SPEC}" />
                     </td>
                     <td nowrap="nowrap" align="right" >
                     <input type="text" name="NEW_COLOR" lable="新花号" json="NEW_COLOR" inputtype="string" id="NEW_COLOR${rst.GOODS_ORDER_DTL_ID}" autocheck="true" style="width: 105px;" maxlength="50" value="${rst.NEW_COLOR}" />
                     </td>
                      <td nowrap="nowrap" align="right" >
                     <input type="text" name="PRODUCT_REMARK" lable="生产相关描述" json="PRODUCT_REMARK" inputtype="string" id="PRODUCT_REMARK${rst.GOODS_ORDER_DTL_ID}" autocheck="true" style="width: 105px;" maxlength="50" value="${rst.PRODUCT_REMARK}" />
                     </td>
                     <%if("0".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
                      <td align="center" style="width: 100px;background-color: #F4CC87;">
						<input type="checkbox" label="是否抵库"  name="IS_BACKUP_FLAG" json="IS_BACKUP_FLAG" id="IS_BACKUP_FLAG${rr}"  onclick="checkIsFB(this,'${rst.IS_NO_STAND_FLAG}');" />
						<input type="hidden" id="HID_IS_BACKUP_FLAG${rr}" value="${rst.IS_BACKUP_FLAG}"/>
						<script type="text/javascript">
						    var v = $("#HID_IS_BACKUP_FLAG${rr}").val();
						    if(1 == v || "1" == v){
						    	$("#IS_BACKUP_FLAG${rr}").attr("checked","checked");
						    }
						</script>
						<a style="text-decoration: underline;color: blue;" id="" href="javascript:void(0)" onclick="queryStore('${rst.PRD_NO}','${rst.SPCL_TECH_ID}','${rr}');" >查看库存</a>
						<div id="showDiv${rr}" name="showDiv" class="showDiv" style="display:none;background-color: #F1F4FB;" ondblclick="hidIself(this);">
							<span id="" style="background-color: #F1F4FB;">可用库存：</span>
				            <span id="showSpan${rr}"></span><br/>
				            <span id="" style="background-color: #F1F4FB;">  U9库存：  </span>
							<span id="showSpanU9${rr}"></span><br/>	
							<span id="" style="background-color: #F1F4FB;">  DM抵库：  </span>
						    <span id="showSpanDm${rr}"></span>	
						</div>
					 </td>
					 <%} %> <!-- rst.IS_USE_REBATE 是否使用返利  1->使用返利  返利订单不可以编辑单价 -->
                     <td nowrap="nowrap" align="right" >
                       <c:if test="${rst.STATE eq '未处理' && rst.BILL_TYPE ne '赠品订货' && rst.BILL_TYPE ne '返利订货'}" >
	                     <input type="text" name="DECT_PRICE" json="DECT_PRICE"   label="折后单价"
	                        id="DECT_PRICE${rr}"  
	                        value="${rst.DECT_PRICE}"  textType="float"    valueType="8,2" size="4"
	                        <c:if test="${rst.PRD_SUIT_FLAG eq 1 }">
	                        	readonly="readonly" onclick="parent.showErrorMsg('货品套货品不能修改价格！');";
	                        </c:if>
	                        maxlength="11" onkeyup="changeOrderAmount(this);"/>
	                        <input type="hidden" id="OLD_DECT_PRICE" name="OLD_DECT_PRICE" json="OLD_DECT_PRICE" value="${rst.DECT_PRICE}"/>
                       </c:if>
                      <c:if test="${rst.STATE ne '未处理' || rst.BILL_TYPE eq '赠品订货' || rst.BILL_TYPE eq '返利订货' }" >
                        ${rst.DECT_PRICE}
                      </c:if>
                        
                     </td>
                     <%-- 
                     
                     <c:if test="${empty mergeFlage}"><!-- 合并提交页面不用显示下面的列 -->
	                     <td nowrap="nowrap" align="right"  id="OLD_PRICE${rr}" >${rst.OLD_PRICE}&nbsp;</td>
	                     <td nowrap="nowrap" align="right" >${rst.OLD_ORDER_NUM}&nbsp;</td>
                     </c:if>
                     
                     --%>
                     <%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
                     <c:if test="${IS_NO_ADVC_DATE_FLAG ne 1}">
	                     <td nowrap="nowrap" align="center" >
	                      <input json="ADVC_SEND_DATE" id="ADVC_SEND_DATE${rr}" name="ADVC_SEND_DATE" readonly  label="预计交货日期"  type="text" autocheck="true"  maxlength="32" inputtype="string"  style="width: 90px;" onclick="SelectDate();"  value="${rst.ADVC_SEND_DATE}"/>
	                       <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE${rr});"/>
	                     </td>
                     </c:if>
                     <%} %>
                     <td nowrap="nowrap" align="right" json="ORDER_NUM" id="ORDER_NUM${rr}" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" json="ORDER_AMOUNT"  name="ORDER_AMOUNT" id="ORDER_AMOUNT${rr}" >${rst.ORDER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap"  >${rst.VOLUME}&nbsp;</td>
                     
                     <%-- 
                     <td nowrap="nowrap" align="center" >${rst.ORDER_RECV_DATE}</td>
                     --%>
                     <td nowrap="nowrap" align="center" >
                       <c:if test="${rst.CANCEL_FLAG eq 1}">总部取消</c:if>
                        <c:if test="${empty rst.CANCEL_FLAG || rst.CANCEL_FLAG eq 0}">正常</c:if>
                     </td>
                     <td nowrap="nowrap" align="center">
                      <c:if test="${rst.STATE eq '未处理'}" >
	                      <input type="text" name="REMARK" json="REMARK"  id="REMARK${rr}" oVal="" value="${rst.REMARK}" style="width:200px;" maxlength="250" onkeyup="changeTextArea(this);"/>
                      </c:if>
                      <c:if test="${rst.STATE ne '未处理'}" >
                        ${rst.REMARK}
                      </c:if>  
                    </td>
                     <input type="hidden" id="CANCEL_FLAG${rr}" name="CANCEL_FLAG" value="${rst.CANCEL_FLAG}"/>
                     <script type="text/javascript">
                     	if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseFloat(${rst.ORDER_NUM},10));
						  }else{
						  	var tempNum = parseFloat($("#allNum").val(),10);
						  	var perNum = parseFloat(${rst.ORDER_NUM},10);
						  	$("#allNum").val(tempNum + perNum);
						  }
						  
						  var amount = '${rst.ORDER_AMOUNT}';
						  if(amount=="" || amount==null){
						  	amount = 0;
						  }
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(amount,10)).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(amount,10);
						  	$("#allAmount").val((tempAmount + perAmount).toFixed(2));
						  }
						  
						  
						  var volume='${rst.TOTAL_VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  if($("#allVolume").val()==null || $("#allVolume").val()==""){
						  	$("#allVolume").val((parseFloat(volume,10)).toFixed(2));
						  }else{
						  	var tempVolume = parseFloat($("#allVolume").val(),10);
						  	var perVolume = parseFloat(volume,10);
						  	$("#allVolume").val((tempVolume + perVolume).toFixed(2));
						  }
                     </script>
                     
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="26" colspan="19" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="listForm" action="#" method="post" name="listForm">
	<input type="hidden" id="GOODS_ORDER_DTL_IDS" name="GOODS_ORDER_DTL_IDS" value=""/>
	<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
    <input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
</form>
</body>
</html>
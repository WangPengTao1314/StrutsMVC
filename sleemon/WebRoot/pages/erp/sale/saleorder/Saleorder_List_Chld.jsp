<!--
 * @prjName:喜临门营销平台
 * @fileName:标准订单明细 列表
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleorder/Saleorder_List_Chld.js"></script>
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
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
		<form>
		  <input type="hidden" id="selectContion" name="selectContion" value=""/>
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td nowrap>
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
				   	   </c:if>
				   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
				   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
				   	   </c:if>
					   &nbsp;
					</td>
					<td align="right">
						总数量：<input id="allNum" name="allNum" readonly value="" class="text_underline" />
						总金额：<input id="allAmount" name="allAmount" readonly value="" class="text_underline"/>
						总体积：<input id="allVolume" name="allVolume" readonly value="" class="text_underline"/>
					</td>
				</tr>
				<%--<tr>
                  <td nowrap>
                  <c:if test="${module ne 'all'}">
                     <c:if test="${pvg.PVG_CONVERT eq 1 }">
			   		<input id="convert" type="button" class="btn" value="转非标订单(Z)" title="Alt+Z" accesskey="Z">
			   	   </c:if>
                  </c:if>
				</td>
				</tr>--%>
			</table>
			</form>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
					<th  nowrap="nowrap" align="center" dbname="ROW_NO" >行号</th>
					<th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" >是否抵库</th>
					<th  nowrap="nowrap" align="center" dbname="IS_FREE_FLAG" >是否赠品</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>-->
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <%--<th  nowrap="nowrap" align="center" dbname="OLD_PRICE" >原价格</th>
                    --%>
                    <th  nowrap="nowrap" align="center" dbname="OLD_ORDER_NUM" >原订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_AMOUNT" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >体积</th>
                    <th  nowrap="nowrap" align="center" dbname="" >已排车</th>
                    <th  nowrap="nowrap" align="center" dbname="" >未排车</th>
                    <th  nowrap="nowrap" align="center" dbname="" >已实际发货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >备注</th>
                    
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle"  id="eid${rr}" value="${rst.SALE_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 <td nowrap="nowrap" align="center">${rst.ROW_NO}</td>
                     <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_BACKUP_FLAG}">  是 </c:if>
					    <c:if test="${'1' ne rst.IS_BACKUP_FLAG}">  否  </c:if>
					 </td>
					  <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_FREE_FLAG}">  是 </c:if>
					    <c:if test="${'1' ne rst.IS_FREE_FLAG}">  否  </c:if>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center" >-->
<!--                     <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}">无</c:if>-->
<!--                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}">-->
<!--                        <font color="red">有</font>-->
<!--                         <input type="button" class="btn" value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}');"/>-->
<!--                      </c:if>-->
<!--                      &nbsp;-->
<!--                     </td>-->
                     <td nowrap="nowrap" align="right" >${rst.PRICE}</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_RATE}</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     <%--
                     <td nowrap="nowrap" align="right" >&nbsp;</td>
                     --%>
                     <td nowrap="nowrap" align="right" >${rst.OLD_ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden"  name="HID_ORDER_AMOUNT" value="${rst.ORDER_AMOUNT}"/>
                       ${rst.ORDER_AMOUNT}&nbsp;
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.VOLUME}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.PLANED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM-rst.PLANED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.SENDED_NUM}</td>
                     <td nowrap="nowrap" align="left" >${rst.REMARK}</td>
                     
                     <script type="text/javascript">
						  if($("#allNum").val()==null || $("#allNum").val()==""){
						  	$("#allNum").val(parseFloat(${rst.ORDER_NUM},10));
						  }else{
						  	var tempNum = parseFloat($("#allNum").val(),10);
						  	var perNum = parseFloat(${rst.ORDER_NUM},10);
						  	var allNum=Math.round((isNaN(tempNum + perNum)?0:tempNum + perNum),2);
						  	$("#allNum").val(allNum);
						  }
						  
						  if($("#allAmount").val()==null || $("#allAmount").val()==""){
						  	$("#allAmount").val((parseFloat(${rst.ORDER_AMOUNT},10)).toFixed(2));
						  }else{
						  	var tempAmount = parseFloat($("#allAmount").val(),10);
						  	var perAmount = parseFloat(${rst.ORDER_AMOUNT},10);
						  	var allAmount=Math.round((isNaN(tempAmount + perAmount)?0:tempAmount + perAmount),2);
						  	$("#allAmount").val(allAmount);
						  }
						  var volume='${rst.VOLUME}';
						  if(""==volume||null==volume){
						  	volume=0;
						  }
						  volume = parseFloat(volume);
						  var ORDER_NUM='${rst.ORDER_NUM}';
						  if(""==ORDER_NUM||null==ORDER_NUM){
						  	ORDER_NUM=0;
						  }
						  var allVolume = $("#allVolume").val();
						  var perVolume = parseFloat(volume*ORDER_NUM,10);
						  if(allVolume ==null || allVolume==""){
						  	$("#allVolume").val(perVolume);
						  }else{
							  allVolume = parseFloat(allVolume);
							  var tempVolumn = allVolume+perVolume;
							  tempVolumn = tempVolumn.toFixed(2);
							  //tempVolumn =Math.round((isNaN(tempVolumn)?0:tempVolumn),2);
						      $("#allVolume").val(tempVolumn);
						  }
						  
						
						  
					   </script>
                     
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="26" colspan="22" align="center" class="lst_empty">
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
	<input type="hidden" id="SALE_ORDER_DTL_IDS" name="SALE_ORDER_DTL_IDS" value=""/>
</form>
</body>
</html>
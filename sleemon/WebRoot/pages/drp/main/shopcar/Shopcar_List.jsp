<!--
 * @prjName:喜临门营销平台
 * @fileName:Paymentup_Detial
 * @author lyg
 * @time   2013-08-23 10:25:58 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" import="com.hoperun.commons.util.StringUtil" %>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/main/shopcar/Shopcar_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/pages/drp/main/myorder/js/uuid.js"></script>
	<style type="text/css">
		a {
			cursor: hand ;
		}
	</style>
</head>
<body class="bodycss1">
 <input id="delFlag" type="hidden" value="false"/>
 <input id="REBATEDSCT" name="REBATEDSCT" label="返利折扣" type="hidden" value="${REBATEDSCT}">
 <input id="LARGESSDSCT" name="LARGESSDSCT" label="赠品折扣" type="hidden" value="${LARGESSDSCT}">
 <input id="REBATE_TOTAL" name="REBATE_TOTAL" label="返利总金额" type="hidden" value="${REBATE_TOTAL}">
 <input id="REBATE_CURRT" name="REBATE_CURRT" label="当前返利" type="hidden" value="${REBATE_CURRT-REBATE_FREEZE}">
 
 <input id="DECT_RATE" type="hidden" value="${params.DECT_RATE}">
	<div style="overflow-x: auto; overflow-y: auto; height: 95%; width:100%">
	<table width="99.8%" height="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="panel">
		<tr>
			<td height="20px" valign="top">
		      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0 style="margin-top: 3px;" width="100%">
				<tr style="padding-bottom: 5px;">
				   <td nowrap style="padding-left: 3px; width: 10%">
<!--				   	<c:if test="${pvg.PVG_EDIT eq 1 }">-->
<!--				   	   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >-->
<!--				   	</c:if>-->
				   	<c:if test="${pvg.PVG_DELETE eq 1 }">
					   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
					 </c:if>
					</td>
					<td align="right" style="width: 70%">
					<span style="font-weight: bolder;margin-right: 10px;">
					   类型： <select  name="SHOP_CART_TYPE_" id="SHOP_CART_TYPE_" style="width: 110px;" label="购物车类型"  onchange="changeListPage(this);"></select>
					  </span>
					 <span style="font-weight: bolder;">总金额：</span><font id="total">0</font>
					 <span style="font-weight: bolder;">总数量：</span><font id="allNum">0</font>
					 <span  style="font-weight: bold">&nbsp;&nbsp;总体积：</span>
					 <font id="allvol" size="2">0</font>&nbsp;&nbsp;&nbsp;
					 <span  style="font-weight: bold"> 共需 </span>
					 <font id="car" size="2"></font>&nbsp;
					 <span  style="font-weight: bold">车</span>
					 &nbsp;&nbsp;&nbsp;
					 	<c:if test="${pvg.PVG_EDIT eq 1 }">
					 <input id="addPrd" style="margin-left: 5px" type="button" class="btn" value="生成订货订单(I)" title="Alt+I" accesskey="I" >
					 <input id="commit" style="margin-left: 5px" type="button" class="btn" value="下单并提交(T)" title="Alt+T" accesskey="T" >
					 </c:if>
					</td>
					<td width="3%" align="right">
						<input type="button" value="↑" style="margin-right: 10px;" onclick="hide()" id="showOrHide" class="btn" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top" colspan="2" >
				<div class="lst_area" style="margin-left:3px;">
					<form onsubmit="return false;">
					   <input type="hidden" id="CHANN_ID"  value="${CHANN_ID}"/>
					   <input type="hidden" id="picture_url" name="" value="${picture_url}" />
						<table width="100%" border="0" cellpadding="1" cellspacing="1" id="jsontb" class="lst">
							<tr class="fixedRow">
								<th nowrap="nowrap" align="center" ><input type="checkbox" style="height:12px;valign:middle" id="allChecked" /></th>
								<th nowrap="nowrap" align="center" >图片</th>
								<th nowrap="nowrap" align="center" >货品信息</th>
								<th nowrap="nowrap" align="center" dbname="SHOP_CART_TYPE">类型</th>
<!--								<th nowrap="nowrap" align="center">花号</th>-->
								<th nowrap="nowrap" align="center" dbname="PRICE">单价</th>
								<th nowrap="nowrap" align="center" dbname="DECT_RATE">折扣率</th>
								<th nowrap="nowrap" align="center" dbname="DECT_PRICE">折扣价</th>
								<th nowrap="nowrap" align="center" dbname="ORDER_NUM">订货数量</th>
								<th nowrap="nowrap" align="center" dbname="VOLUME">体积</th>
								<th nowrap="nowrap" align="center" dbname="ORDER_AMOUNT">金额</th>
								<c:if test="${REBATEFLAG ne 1 && LARGESSFLAG ne 1}">
									<th nowrap="nowrap" align="center" dbname="">特殊规格说明</th>
								</c:if>
								<th nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE">顾客交货日期</th>
								<th nowrap="nowrap" align="center" dbname="REMARK">备注</th>
								 
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
								 <td width="1%"align='center' >
									<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.SHOP_CART_ID}" onclick="getVolumFrom();"  />
								 </td>
				                  <td nowrap="nowrap" align="center" style="width: 100px;">
				                  	<c:if test="${rst.PIC_PATH==null}">
				                  		<img src="${picture_url}dafult.jpg" width="100px" height="100px" style="vertical-align:middle;" />
				                  	</c:if>
				                  	<c:if test="${rst.PIC_PATH!=null}">
				                  		<a href="#"><img class="pimg" id="img${rr}"  name="prd_img" src="${picture_url}${rst.PIC_PATH}" width="100px" height="100px" 
				                  		         onclick="showImg(this,'img${rr}')"  /></a>
				                  	</c:if>
								  </td>
								  <td nowrap="nowrap" align="center">
								  	<table style="height: 4%">
								  		<tr>
								  			<td style="height: 1%;" align="right">编号：</td>
								  			<td align="left">${rst.PRD_NO}</td>
								  		</tr>
								  		<tr>
								  			<td style="height: 1%" align="right">名称：</td>
								  			<td align="left">${rst.PRD_NAME}</td>
								  		</tr>
								  		<tr>
								  			<td style="height: 1%" align="right">品牌：</td>
								  			<td align="left">${rst.BRAND}</td>
								  		</tr>
								  		<tr>
								  			<td style="height: 1%" align="right">规格型号：</td>
								  			<td align="left">${rst.PRD_SPEC}</td>
								  		</tr>
								  	</table>
								  </td>
								  <td nowrap="nowrap" align="center" name="type">${rst.SHOP_CART_TYPE}</td>
<!--				                  <td nowrap="nowrap" align="left">${rst.PRD_COLOR}&nbsp;</td>-->
				                  <td nowrap="nowrap" align="right"  id="BASE_PRICE${rr}" name="BASE_PRICE">
				                  	${rst.PRICE}
				                  </td>
				                  <td nowrap="nowrap" align="right" name="RATE" id="RATE${rr}">
				                  	<c:if test="${REBATEFLAG eq 1}">
				                  		${REBATEDSCT}
				                  	</c:if>
				                  	<c:if test="${REBATEFLAG ne 1&&LARGESSFLAG ne 1}">
				                  		${rst.DECT_RATE}
				                  	</c:if>
				                  	<c:if test="${LARGESSFLAG eq 1}">
				                  		${LARGESSDSCT}
				                  	</c:if>
				                  </td>
				                  <td nowrap="nowrap" align="right" id="PRICES${rr}" name="PRICES" >
				                  	<c:if test="${REBATEFLAG eq 1}">
				                  		${REBATEDSCT*rst.PRICE}
				                  	</c:if>
				                  	<c:if test="${LARGESSFLAG eq 1}">
				                  		${LARGESSDSCT*rst.PRICE}
				                  	</c:if>
				                  	<c:if test="${REBATEFLAG ne 1&&LARGESSFLAG ne 1}">
				                  		${rst.DECT_PRICE}
				                  	</c:if>
				                  </td>
				                  <td nowrap="nowrap" align="center">
				                  <input  json="ORDER_NUM" id="num${rr}"  onkeyup="countAmount('${rr}');" onblur="getVolumFrom();" onclick="pitchChecked('${rr}')"  type="text" label="订货数量"  name="ORDER_NUM" value="${rst.ORDER_NUM}"  style="width: 50px;text-align: right" />&nbsp;
				                  </td>
				                  <td nowrap="nowrap" align="right" name="vol">
				                  	${rst.VOLUME}
				                  </td>
				                  <td nowrap="nowrap" align="right" id="amount${rr}">
				                  		<c:if test="${REBATEFLAG eq 1}">
				                  		${REBATEDSCT*rst.PRICE*rst.ORDER_NUM}
				                  	</c:if>
				                  	<c:if test="${LARGESSFLAG eq 1}">
				                  		${LARGESSDSCT*rst.PRICE*rst.ORDER_NUM}
				                  	</c:if>
				                  	<c:if test="${REBATEFLAG ne 1&&LARGESSFLAG ne 1}">
				                  		${rst.ORDER_AMOUNT}
				                  	</c:if>
				                  </td>
				                  <c:if test="${REBATEFLAG ne 1 && LARGESSFLAG ne 1}">
					                  <td nowrap="nowrap" align="center">
					                  	<font id="SPECIAL_FLAG${rr}">
					                  		<c:if test="${rst.SPCL_TECH_FLAG eq 0|| rst.SPCL_TECH_FLAG eq null}">无</c:if>
					                  		<c:if test="${rst.SPCL_TECH_FLAG ge 1}">
					                  			<c:if test="${!empty rst.SPCL_SPEC_REMARK }">
						                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
						                     	</c:if>
					                  		</c:if>
					                  	</font>
					                  		<c:if test="${rst.SHOP_CART_TYPE eq '预订单转订货'&&rst.SPCL_TECH_FLAG ge 1}">
					                  			
					                  			<input type="button" class="btn" value="查看" onclick="urlshow('${rst.SPCL_TECH_ID}','${rst.PRICE}')"/>
					                  		</c:if>
					                  		<c:if test="${rst.SHOP_CART_TYPE eq '手工新增'}">
					                  			<input type="button" class="btn" value="编辑" 
					                  			  <c:if test="${1 eq rst.PRD_SUIT_FLAG}"> disabled="disabled"   </c:if>    
					                  			onclick="url('${rr}')"/>
					                  		</c:if>
					                  </td>
				                  </c:if>
				                  <td align="center">
								<!-- 修改 交货日期为只读属性，并且去掉必填校验和必须在当前日期之前校验-->
								${rst.ORDER_RECV_DATE}
<!--						            <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE${rr});">-->
				                  </td align="center">
				                  <td>
				                      <textarea  json="REMARK" name="REMARK" id="REMARK" 
				                      autocheck="true" inputtype="string"   
				                      maxlength="250"   label="备注"    onkeyup="changeTextArea(this);" oVal=""
				                      rows="4" cols="20" >${rst.REMARK}</textarea>
				                  </td>
				                  <input type="hidden" id="SPCL_TECH_ID${rr}" json="SPCL_TECH_ID" name="SPCL_TECH_ID" value="${rst.SPCL_TECH_ID}"/>
						          <input type="hidden" id="sessionId${rr}" json="SESSION_ID" name="SESSION_ID" value="${rst.SESSION_ID}"/>
						          <input type="hidden" id="SHOP_CART_ID${rr}" json="SHOP_CART_ID" name="SHOP_CART_ID" value="${rst.SHOP_CART_ID}"/>
						          <input type="hidden" id="PRD_ID${rr}" json="PRD_ID" name="PRD_ID" value="${rst.PRD_ID}"/>
						          <input type="hidden" id="PRD_NO${rr}" json="PRD_NO" name="PRD_NO" value="${rst.PRD_NO}"/>
						          <input type="hidden" id="PRD_NAME${rr}" json="PRD_NAME" name="PRD_NAME" value="${rst.PRD_NAME}"/>
						          <input type="hidden" id="PRD_SPEC${rr}" json="PRD_SPEC" name="PRD_SPEC" value="${rst.PRD_SPEC}"/>
						          <input type="hidden" id="PRD_COLOR${rr}" json="PRD_COLOR" name="PRD_COLOR" value="${rst.PRD_COLOR}"/>
						          <input type="hidden" id="BRAND${rr}" json="BRAND" name="BRAND" value="${rst.BRAND}"/>
						          <input type="hidden" id="STD_UNIT${rr}" json="STD_UNIT" name="STD_UNIT" value="${rst.STD_UNIT}"/>
						          
						          <input type="hidden" id="DECT_RATE${rr}" json="DECT_RATE" name="DECT_RATE" value="${rst.DECT_RATE}"/>
						          <input type="hidden" id="DECT_PRICE${rr}" json="DECT_PRICE" name="DECT_PRICE" value="${rst.DECT_PRICE}"/>
						          
						          <input type="hidden" id="BRAND${rr}" json="BRAND" name="BRAND" value="${rst.BRAND}"/>
						          <input type="hidden" id="PRICE${rr}" json="PRICE" name="PRICE" value="${rst.PRICE}"/>
						          <input type="hidden" id="ORDER_AMOUNT${rr}" json="ORDER_AMOUNT" name="ORDER_AMOUNT" value="${rst.ORDER_AMOUNT}"/>
						          <input type="hidden" id="VOLUME${rr}" json="VOLUME" name="VOLUME" value="${rst.VOLUME}"/>
						          
						          <input type="hidden" id="TECH_AMOUNT${rr}" name="TECH_AMOUNT" json="TECH_AMOUNT"  value="${rst.TECH_AMOUNT}"/>
						          <input type="hidden" id="TECH_MULT${rr}" name="TECH_MULT" json="TECH_MULT"  value="${rst.TECH_MULT}"/>
						          <input type="hidden" id="SPCL_TECH_FLAG${rr}" name="SPCL_TECH_FLAG" json="SPCL_TECH_FLAG"  value="${rst.SPCL_TECH_FLAG}"/>
							    </tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="16" align="center" class="lst_empty">
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
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
					<tr>
						<td>
							<form id="listForm" action="#" method="post" name="listForm">
								<input id="REBATEFLAG" name="REBATEFLAG" value="${REBATEFLAG}" type="hidden"/>
								<input id="LARGESSFLAG" name="LARGESSFLAG" value="${LARGESSFLAG}" type="hidden"/>
								<input id="SHOP_CART_TYPE" name="SHOP_CART_TYPE" value="${params.SHOP_CART_TYPE}" type="hidden"/>
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
							 ${page.footerHtml}${html}
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
   SelDictShow("SHOP_CART_TYPE_","BS_110","${params.SHOP_CART_TYPE}","");
   //初始化 审批流程
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
</script>
</html>
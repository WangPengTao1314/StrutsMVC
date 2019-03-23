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
	<script type="text/javascript" src="${ctx}/pages/drp/main/myorder/Myorder_List.js"></script>
	<script src="${ctx}/pages/drp/main/myorder/js/uuid.js"></script>
	<style type="text/css">
		a {
			cursor: hand ;
		}
	</style>
</head>
<body>
	<div style="overflow-x: auto; overflow-y: auto; height: 95%; width:100%">
	<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
		<div id="innerdiv" style="position:absolute;">
			<img id="bigimg" style="border:5px solid #fff;" src="" />
		</div>
	</div>
	<table width="99.8%" height="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="panel">
		<tr>
			<td valign="top">
				<div class="lst_area" style="margin-left:3px;">
					<form>
						<table width="100%" border="0" cellpadding="1" cellspacing="1" id="jsontb" class="lst">
						  <input type="hidden" id="CHANN_ID" value="${CHANN_ID}"/>
						  <input type="hidden" id="picture_url" name="" value="${picture_url}" />
							<tr class="fixedRow">
								<th nowrap="nowrap" align="center" ><input type="checkbox" style="height:12px;valign:middle" id="allChecked" /></th>
								<th nowrap="nowrap" align="center">图片</th>
								<th nowrap="nowrap" align="center">货品信息</th>
<!--								<th nowrap="nowrap" align="center">花号</th>-->
								<th nowrap="nowrap" align="center" dbname="PRVD_PRICE">单价</th>
								<th nowrap="nowrap" align="center" dbname="DECT_RATE">折扣率</th>
								<th nowrap="nowrap" align="center" dbname="DECT_PRICE">折扣价</th>
								<th nowrap="nowrap" align="center" >订货数量</th>
								<th nowrap="nowrap" align="center">特殊工艺</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
								 <td width="1%"align='center' >
									<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRD_ID}"  onclick="setEidChecked(this);"
										<c:forEach items="${SHOPIDLIST}" var="shopidlist" >
											<c:if test="${rst.PRD_ID eq shopidlist}">
												 checked="checked"
												 <c:out value="#{shopidlist.contactMethod.contactNum }"></c:out> 
											</c:if>
										</c:forEach>
									/>
								 </td>
				                  <td nowrap="nowrap" align="center" style="width: 100px;">
				                  	<c:if test="${rst.PIC_PATH==null}">
				                  		<img src="${picture_url}dafult.jpg" width="100px" height="100px" />
				                  	</c:if>
				                  	<c:if test="${rst.PIC_PATH!=null}">
				                  		<a href="#"><img class="pimg" id="img${r}" src="${picture_url}${rst.PIC_PATH}" width="100px" height="100px" 
				                  		 style="vertical-align:middle;"  onclick="showImg(this,'img${r}')"   /></a>
				                  	</c:if>
				                  </td>
				                  <td nowrap="nowrap" align="center">
								  	<table style="height: 4%">
								  		<tr>
								  			<td style="height: 1%;" align="right">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
								  			<td align="left">${rst.PRD_NO}</td>
								  		</tr>
								  		<tr>
								  			<td style="height: 1%" align="right">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
								  			<td align="left">${rst.PRD_NAME}</td>
								  		</tr>
								  		<tr>
								  			<td style="height: 1%" align="right">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：</td>
								  			<td align="left">${rst.BRAND}</td>
								  		</tr>
								  		<tr>
								  			<td style="height: 1%" align="right">规格型号：</td>
								  			<td align="left">${rst.PRD_SPEC}</td>
								  		</tr>
								  	</table>
								  </td>
<!--				                  <td nowrap="nowrap" align="left">${rst.PRD_COLOR}&nbsp;</td>-->
				                  <td nowrap="nowrap" align="right" id="BASE_PRICE${rr}" name="BASE_PRICE">
				                  	${rst.PRVD_PRICE}
				                  </td>
				                  <td nowrap="nowrap" align="right" name="RATE" id="RATE${rr}">
				                  	${rst.DECT_RATE}
				                  </td>
				                  <td nowrap="nowrap" align="right" name="PRICES"  id="PRICES${rr}">
				                  	${rst.DECT_PRICE}
				                  </td>
				                  <td nowrap="nowrap" align="center">
				                  	<input json="ORDER_NUM" type="text" name="ORDER_NUM" value="" onclick="pitchChecked('${rr}')"  style="text-align:right;width: 80px;" />&nbsp;
				                  </td>
				                  <td nowrap="nowrap" align="center">
				                  	<font id="SPECIAL_FLAG${rr}">
					                  	<c:if test="${rst.SPCL_TECH_FLAG ne 0 && rst.SPCL_TECH_FLAG ne null}">
				                     	 	<span  style="color: red">有</span>
				                     	 </c:if>
				                     	 <c:if test="${rst.SPCL_TECH_FLAG ne '1' && rst.SPCL_TECH_FLAG ne '2'}">
				                     	 	无
				                     	 </c:if>
			                     	 </font>
			                     	
			                     	 <input type="button" value="编辑" class="btn" onclick="url('${rr}')"   
			                     	 <c:if test="${1 eq rst.PRD_SUIT_FLAG}">disabled="disabled"</c:if>
			                     	 />
				                  </td>
						          <input type="hidden" id="sessionId${rr}" json="SESSION_ID" name="SESSION_ID" value="<%=StringUtil.uuid32len()%>"/>
						          <input type="hidden" json="PRD_ID" name="PRD_ID" value="${rst.PRD_ID}"/>
						          <input type="hidden" json="PRD_NO" name="PRD_NO" value="${rst.PRD_NO}"/>
						          <input type="hidden" json="PRD_NAME" name="PRD_NAME" value="${rst.PRD_NAME}"/>
						          <input type="hidden" json="PRD_SPEC" name="PRD_SPEC" value="${rst.PRD_SPEC}"/>
						          <input type="hidden" json="PRD_COLOR" name="PRD_COLOR" value="${rst.PRD_COLOR}"/>
						          <input type="hidden" json="STD_UNIT" name="STD_UNIT" value="${rst.STD_UNIT}"/>
						          <input type="hidden" json="BRAND" name="BRAND" value="${rst.BRAND}"/>
						          <input type="hidden" id="PRICE${rr}" json="PRICE" name="PRICE" value="${rst.BASE_PRICE}"/>
						          <input type="hidden" id="DECT_RATE${rr}" json="DECT_RATE" name="DECT_RATE" value="${rst.DECT_RATE}"/>
						          <input type="hidden" id="DECT_PRICE${rr}" json="DECT_PRICE" name="DECT_PRICE" value="${rst.DECT_PRICE}"/>
						          <input type="hidden" id="SPCL_TECH_ID${rr}" json="SPCL_TECH_ID" name="SPCL_TECH_ID" value="${rst.SPCL_TECH_ID}"/>
							    </tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="13" align="center" class="lst_empty">
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
			<td height="12px"  >
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
					<tr>
						<td>
							<form id="pageForm"  action="#" method="post" name="listForm">
							<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
								<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
								<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
								<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
								<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
								<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
								<input type="hidden" id="oldPageNo" name="oldPageNo" value="${page.currentPageNo}" >
								<input type="hidden" id="PRDIDS" name="PRDIDS"> 
								<span id="hidinpDiv" name="hidinpDiv"></span>
								${paramCover.unCoveredForbidInputs }
							</form>
						</td>
						<td align="left">
							 ${page.footerHtml}${html}
						</td>
						<td align="right"><input id="addPrd" type="button" value="加入购物车" class="btn"/><input id="selShopCar" type="button" value="查看购物车" class="btn"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
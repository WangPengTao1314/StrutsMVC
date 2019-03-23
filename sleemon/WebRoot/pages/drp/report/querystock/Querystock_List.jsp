<!--
 * @prjName:喜临门营销平台
 * @fileName:Querypaymentrep_List
 * @author zhu_changxia
 * @time   2014-05-14 10:29:35 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/report/querystock/Querystock_List.js"></script>
</head>

<body>
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
		<table>
							<tr>
								<td align="left" >库存总计:</td>
								<td align="left">${SUM_STORE_NUM}</td>	
								<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;体积总计:</td>
								<td align="left">${SUM_TOTAL_VOLUME}</td>	
							</tr>				
		</table>
			 <table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">			
				<tr class="fixedRow">
				               
								<th nowrap align="center">
									商品编号
								</th>  
								<th nowrap align="center">
									商品名称
								</th>
								<th nowrap align="center">
									规格型号
								</th>
								<th nowrap align="center">
									库房编号
								</th>
								<th nowrap align="center">
									库房名称
								</th>
								<th nowrap align="center">
									库存单价
								</th>
								<th nowrap align="center">
									库存数量
								</th>
								<th nowrap align="center">
									库存金额
								</th>
								<!-- 
								<th nowrap align="center">
									供货价
								</th>
								<th nowrap align="center">
									单位体积
								</th>
								 -->
								 <th nowrap align="center">
									库存体积 
								</th>
								 
								<th nowrap align="center">
									客户编号
								</th>
								<th nowrap align="center">
									客户名称
								</th>
							 
								<th nowrap align="center">
									送货详细地址
								</th> 
								 
								<th nowrap align="center">
									描述
								</th>
								<th nowrap align="center">
									帐套名称
								</th>
				            </tr> 
				            <c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:if test="${empty page.errorMsg}">
									<c:set var="r" value="${row.count % 2}"></c:set>
									<c:set var="rr" value="${row.count}"></c:set>
									<tr class="list_row${r}" onmouseover="mover(this)"
										onmouseout="mout(this)" id="tr${rr}">
										<td align="center">
											${rst.PrdNo} 
										</td>
										<td align="center">
											${rst.PrdName} 
										</td>
										<td align="center">
											${rst.PrdSpec} 
										</td>
										<td align="center">
											${rst.StoreNo} 
										</td>
										<td align="center">
											${rst.StoreName} 
										</td>
										<td align="center">
											${rst.StorePrice} 
										</td>
										<td align="center">
											${rst.StoreNum} 
										</td>
										<td align="center">
											${rst.StoreAmount} 
										</td>
										<!--  
										<td align="center">
										    ${rst.PrvdPrice} 
										</td>
										<td align="center">
										    ${rst.Unit_Volume} 
										</td>
										-->
										<td align="center">
										    ${rst.Total_Volume}  
										</td>
										<td align="center">
											${rst.CustNo} 
										</td> 
										<td align="center">
											${rst.CustName} 
										</td>
										<td align="center">
											${rst.DeliverAddr} 
										</td> 
										<td align="center">
											${rst.Remark} 
										</td>
										<td align="center">
											${rst.LedgerName} 
										</td>
									</tr>
								</c:if>
							</c:forEach>
							<c:if test="${empty page.result}" >
								<tr>
									<td height="25" colspan="16" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
							<c:if test="${not empty page.errorMsg}">
								<tr>
									<td nowrap height="25" colspan="14" align="center"
										class="lst_empty">
										&nbsp;${page.errorMsg}&nbsp;
									</td>
								</tr>
							</c:if>
			        </table>
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
</table>  
</body>
</html>

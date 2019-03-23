<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/cusregist/CusRegist_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			 <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>销售管理>>顾客现场签到</td>
			 <td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="签到(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="registout" type="button" class="btn" value="签出(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				</td>
				</tr>
			</table>
		</div>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_CARD_NO" >卡券编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_TYPE" >卡券类型</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_VALUE" >卡券面值</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >顾客名称</th>
                    <th  nowrap="nowrap" align="center" dbname="MOBILE_PHONE" >顾客手机</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="REGIST_TIME" >签到时间</th>
                    <th  nowrap="nowrap" align="center" dbname="REGIST_STATE" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.CARD_SALE_ORDER_DTL_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.MARKETING_CARD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CARD_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CARD_VALUE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.MOBILE_PHONE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REGIST_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                       ${rst.REGIST_STATE}&nbsp;
                      <input id="state${rst.CARD_SALE_ORDER_DTL_ID}" type="hidden"  value="${rst.REGIST_STATE}" />
                     </td>
                     <td nowrap="nowrap" align="center"> ${rst.TERM_NO} </td>
                     <td nowrap="nowrap" align="center"> ${rst.TERM_NAME} </td>
                     <td nowrap="nowrap" align="center"> ${rst.CHANN_NO} </td>
                     <td nowrap="nowrap" align="center"> ${rst.CHANN_NAME} </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
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
 
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
  <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' and CHANN_ID in(${CHANNS_CHARG}) "/>
  <input type="hidden" id="selectActParams" name="selectActParams" value=" STATE ='审核通过' "/>
 <input type="hidden" id="flag" name="flag" value=""/>
 
  <input type="hidden" id="selTermParam" name="selTermParam" 
  <c:if test="${empty IS_DRP_LEDGER or IS_DRP_LEDGER eq 0}">
    value=" STATE ='启用' and DEL_FLAG=0 "
  </c:if>
  <c:if test="${1 eq IS_DRP_LEDGER}">
     value=" STATE ='启用' and CHANN_ID_P='${CHANN_ID}' and DEL_FLAG=0 "
  </c:if>
 
  />
  
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="8%" nowrap align="right" class="detail_label">卡券编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="MARKETING_CARD_NO" name="MARKETING_CARD_NO" value="${params.MARKETING_CARD_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">卡券类型:</td>
					<td width="15%" class="detail_content">
	   				 <select  id="CARD_TYPE" name="CARD_TYPE" json="CARD_TYPE" style="width: 155px;" ></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">顾客名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="CUST_NAME" name="CUST_NAME"   value="${params.CUST_NAME}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">顾客手机:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="MOBILE_PHONE" name="MOBILE_PHONE"   value="${params.MOBILE_PHONE}"/>
					</td>	
               </tr>
               
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">销售日期从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="SALE_DATE_BEGIN" name="SALE_DATE_BEGIN" onclick="SelectDate();" size="20" value="${params.SALE_DATE_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">销售日期到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="SALE_DATE_END" name="SALE_DATE_END" onclick="SelectDate();" size="20" value="${params.SALE_DATE_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_END);">
					</td>
			 
                     <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
					     <select id="REGIST_STATE" name="REGIST_STATE" style="width:155" ></select>
					 </td>
					 <td width="8%" nowrap align="right" class="detail_label"> </td>
					 <td width="15%" class="detail_content"></td>
               </tr>
                <tr>
                    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" id="TERM_ID" name="TERM_ID" value="${params.TERM_ID}"/>
	   				   <input type="text" id="TERM_NO" name="TERM_NO" value="${params.TERM_NO}"/>
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[1]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam');"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="TERM_NAME" name="TERM_NAME" value="${params.TERM_NAME}"/>
					</td>
		 
                    <td width="8%" nowrap align="right" class="detail_label">渠道编号:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" id="CHANN_ID" name="CHANN_ID" value="${params.CHANN_ID}"/>
	   				   <input type="text" id="CHANN_NO" name="CHANN_NO" value="${params.CHANN_NO}"/>
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam');"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME}"/>
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
				    	<input id="q_reset" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script type="text/javascript">
   SelDictShow("REGIST_STATE","BS_162","${params.REGIST_STATE}","");
  SelDictShow("CARD_TYPE","BS_144","${params.CARD_TYPE}","");
</script>
</html>

<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_List
 * @author lyg
 * @time   2013-11-02 18:55:53 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//advcgoodsapp/Advcgoodsapp_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#reverseDiv{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
			left:230px;
			top:50px;
			text-align:center;
			display: none;
		}
		#upDateDiv{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
			left:230px;
			top:50px;
			text-align:center;
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;${menu}</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
				   </c:if>
				   
				   <c:if test="${pvg.PVG_COMMIT_SCANCLE eq 1 }">
					<input id="audit" type="button" class="btn" value="提交发货(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_EDIT_DATE eq 1 }">
					<input id="upDate" type="button" class="btn" value="修改(M)" title="Alt+M" accesskey="M">
				   </c:if>
				   <c:if test="${pvg.PVG_REVERSE eq 1 }">
					<input id="reverse" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				    <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_SBWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <div id="upDateDiv">
					   		<form action="#">
					   		<table width="100%">
					   			<tr>
					   				<td align="center" height="80px;">
					   					要求到货日期：<input type="text" json="ORDER_RECV_DATE" id="ORDER_RECV_DATE" name="ORDER_RECV_DATE"  autocheck="true" inputtype="string" label="要求到货日期"  mustinput="true" onclick="SelectDate();" readonly />&nbsp;
										<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE);"/>
					   				</td>
					   				<td align="center" height="80px;">
					   					备注：<input type="text" json="REMARK" id="REMARK" name="REMARK"  autocheck="true" inputtype="string" label="备注"   />&nbsp;
					   				</td>
					   			</tr>
					   			<tr>
					   				<td align="center" height="80px;" colspan="2">
					   					<input type="button" class="btn" value="确定" onclick="upDate()" style="margin-right: 50px;" /><input type="button" class="btn" value="关闭" onclick="closeUpDateDiv()" />
					   				</td>
					   			</tr>
					   		</table>
					   		</form>
					   </div>
				</td>
				</tr>
			</table>
		</div>
		 <div id="reverseDiv">
				<table width="100%" height="100%">
					<tr >
						<td align="right"><span style="font-size: 15px;">退回原因：</span></td>
						<td align="left">
							<textarea  id="RETURN_RSON"  cols="60" rows="5" inputtype="string"  maxlength="250"    ></textarea>
							<input  type="hidden" id="ADVC_SEND_REQ_ID"  >  
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
						<input type="button" id="revAudit" value="退回" class="btn" style="margin-right: 10px"/>
						<input type="button" id="close" value="关闭" class="btn"/>
						</td>
						
					</tr>
				</table>
			</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_REQ_NO" >发货申请单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CONTRACT_NO" >合同编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_TYPE" >发货方类型</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_STORE_NAME" >出库库房名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG" >库位管理标记</th>-->
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >电话</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ADVC_SEND_REQ_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ADVC_SEND_REQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_RECV_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CONTRACT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SEND_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SEND_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_STORE_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">-->
<!--                     	<c:if test="${rst.STORAGE_FLAG eq 1}">-->
<!--                     		是-->
<!--                     	</c:if>-->
<!--                     	<c:if test="${rst.STORAGE_FLAG eq 0}">-->
<!--                     		否-->
<!--                     	</c:if>-->
<!--                     &nbsp;</td>-->
                     <td nowrap="nowrap" align="left">${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.ADVC_SEND_REQ_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="from${rst.ADVC_SEND_REQ_ID}" type="hidden"  value="${rst.FROM_BILL_ID}" />
                    <input id="storeout${rst.ADVC_SEND_REQ_ID}" type="hidden"  value="${rst.STOREOUT_STORE_ID}" />
                    <input id="SEND_TYPE${rst.ADVC_SEND_REQ_ID}" type="hidden"  value="${rst.SEND_TYPE}" />
                    <input id="date${rst.ADVC_SEND_REQ_ID}" type="hidden"  value="${rst.ORDER_RECV_DATE}" />
                    <input id="REMARK${rst.ADVC_SEND_REQ_ID}" type="hidden"  value="${rst.REMARK}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			<!--				//--start --0159799--刘曰刚-- 2014-07-28//-->
			<!--				增加通用选取查询发货申请单的过滤条件，只能查询本渠道和当前页面可看到的状态的数据	-->
				<input type="hidden" name="selectParams" value=" DEL_FLAG=0 and STATE in (${params.allSTATE}) and LEDGER_ID ='${params.LEDGER_ID}'">
<!--				//--end --0159799--刘曰刚-- 2014-07-28//-->
				<input id="selectSTORECondition" name="selectSTORECondition" type="hidden" value=" STATE in ( '启用','停用' ) and  DEL_FLAG=0 and BEL_CHANN_ID='${LEDGER_ID}' "/>
				<input  name="selectTERM" type="hidden" value=" STATE in ( '启用','停用' ) and  DEL_FLAG=0 and CHANN_ID_P='${LEDGER_ID}' "/>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">发货申请单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ADVC_SEND_REQ_NO" name="ADVC_SEND_REQ_NO"  style="width:155" value="${params.ADVC_SEND_REQ_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_66', false, 'ADVC_SEND_REQ_NO', 'ADVC_SEND_REQ_NO', 'forms[2]','ADVC_SEND_REQ_NO', 'ADVC_SEND_REQ_NO', 'selectParams')"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">来源单据号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NO" name="TERM_NO"  style="width:155" value="${params.TERM_NO}"/>
	   					<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_NO', 'TERM_NO', 'forms[2]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selectTERM')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NAME" name="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
					</td>					
                    					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">出库库房编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO"  style="width:155" value="${params.STOREOUT_STORE_NO}"/>
	   					<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				         onclick="selCommon('BS_35', false, 'STOREOUT_STORE_NO', 'STORE_NO', 'forms[2]','STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_NO,STORE_NAME','selectSTORECondition');"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">出库库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  style="width:155" value="${params.STOREOUT_STORE_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155" value="${params.STATE}"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">合同编号：</td>
					<td width="15%" class="detail_content">
					<input id="CONTRACT_NO" name="CONTRACT_NO"     value="${params.CONTRACT_NO}">
					</td>				
                    <td width="8%" nowrap align="right" class="detail_label">交货日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_RECV_DATE_BEG" name="ORDER_RECV_DATE_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.ORDER_RECV_DATE_BEG}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(ORDER_RECV_DATE_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">交货日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_RECV_DATE_END" name="ORDER_RECV_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.ORDER_RECV_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(ORDER_RECV_DATE_END);">
					</td>  
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">客户姓名:</td>
					<td width="15%" class="detail_content">
	   					<input id="CUST_NAME" name="CUST_NAME"     value="${params.CUST_NAME}">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">联系方式：</td>
					<td width="15%" class="detail_content">
					  <input id="TEL" name="TEL"     value="${params.TEL}">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"> </td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"> </td>					
               </tr>
               
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
	SelDictShow("STATE","BS_69","${params.STATE}","");
</script>
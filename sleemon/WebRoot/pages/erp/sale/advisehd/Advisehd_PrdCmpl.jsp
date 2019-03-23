<!--
 * @prjName:喜临门营销平台
 * @fileName:Advise_List
 * @author wdb
 * @time   2013-08-17 10:29:35 
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
	<title>产品投诉回馈信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/advisehd/Advisehd_PrdCmpl.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			   <input id="XTYHID" type="hidden"  value="${XTYHID}" label="当前登录人ID" />
               <input id="QX" type="hidden"  value="${QX}" label="当前登录人权限级别" />
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_TYPE" >产品类型</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_PROBLEM_TYPE" >产品问题类型</th>
                    <th  nowrap="nowrap" align="center" dbname="USE_TIME" >消费者使用时间</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地</th>
                    <th  nowrap="nowrap" align="center" dbname="PRDC_DATE" >生产日期</th>
                    <th  nowrap="nowrap" align="center" dbname="BUY_DATE" >购买日期</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NUM" >产品数量</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_ATT" >附件</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注说明</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_PROBLEM_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.USE_TIME}&nbsp;(天)</td>
                     <td nowrap="nowrap" align="center" >${rst.SHIP_POINT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRDC_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BUY_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="left" ><input type="hidden" id="PRD_ATT" value="${rst.PRD_ATT}"></td>
                     <td nowrap="nowrap" align="left" title="${rst.REMARK}">&nbsp;
                       <c:if test="${fn:length(rst.REMARK)>20}">
                         ${fn:substring(rst.REMARK, 0, 20)}
                       </c:if>
                       <c:if test="${fn:length(rst.REMARK)<=20}">
                         ${rst.REMARK}
                       </c:if>
                     </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
	
	<td valign="top" style="float: right;">
		<div  style="float:right;" class="lst_area" >
		<form>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th  nowrap="nowrap" align="center" dbname="CMPL_PRD_DTL_IDS">回馈信息</th>
				</tr>
				<tr>
					<td nowrap="nowrap" align="left" >
						<textarea style="width: 100%;" maxlength="1000" id="FEEDBACK_INFO" name="FEEDBACK_INFO"  rows="15" cols="24" style="hight:100%" readonly="readonly">
						</textarea>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="CMPL_PRD_DTL_IDS" name="CMPL_PRD_DTL_IDS" value=""/>
</form>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script language="JavaScript">
	displayDownFile('PRD_ATT','SAMPLE_DIR',true,false);
</script>
</html>
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
	<script type="text/javascript" src="${ctx}\pages\erp\sale\channdiscount\Channdiscount_List.js"></script>
	<style type="text/css">
		a {
			cursor: hand ;
		}
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
			BACKGROUND-COLOR: transparent;
		}
	</style>
</head>
<body>
	<div style="overflow-x: auto; overflow-y: auto; height: 99.9%; width:100%">
	<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
		<div id="innerdiv" style="position:absolute;">
			<img id="bigimg" style="border:5px solid #fff;" src="" />
		</div>
	</div>
	<table width="99.8%" height="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="30px;">
					折扣批量修改为:<input type="text" id="dect"  class="text_underline" onkeyup="upDect()"/> 
				</td>
				<td align="center"><input id="add" type="button" class="btn" style="width: 100px;" value="保存"></td>
			</tr>
			<tr>
				<td valign="top" colspan="2">
					<div class="lst_area" style="margin-left:3px;">
						<form id="queryForm" method="post" action="#">
						<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked" value=""></th>
			                    <th  nowrap="nowrap" align="center" dbname="DECT_TYPE" >折扣类别</th>
			                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
			                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
			                    <th  nowrap="nowrap" align="center" dbname="CHANN_TYPE" >渠道类别</th>
			                    <th  nowrap="nowrap" align="center" dbname="CHAA_LVL" >渠道级别</th>
			                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
							</tr>
							<c:forEach items="${rst}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
								 <td width="1%"align='center' >
									<input type="checkbox" style="height:12px;valign:middle" json="CHANN_DSCT_ID" id="eid${rr}" name="CHANN_DSCT_ID"  value="${rst.CHANN_DSCT_ID}" >
								 </td>
								 <td nowrap="nowrap" align="center">${rst.DECT_TYPE}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
			                     <td nowrap="nowrap" align="left">${rst.CHANN_TYPE}&nbsp;</td>
			                     <td nowrap="nowrap" align="left">${rst.CHAA_LVL}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">
			                     	<input type="text"  value="${rst.DECT_RATE}" name="DECT_RATE" id="DECT_RATE" json="DECT_RATE" onclick="setCheck('eid${rr}')"   mustinput="true" label="折扣率"  autocheck="true" inputtype="float" />
			                     	<input type="hidden" value="${rst.CHANN_ID}" name="CHANN_ID" id="CHANN_ID" json="CHANN_ID"/>
			                     	<input type="hidden" value="${rst.CHANN_NO}" name="CHANN_NO" id="CHANN_NO" json="CHANN_NO"/>
			                     	<input type="hidden" value="${rst.CHANN_NAME}" name="CHANN_NAME" id="CHANN_NAME" json="CHANN_NAME"/>
			                     	<input type="hidden" value="${rst.CHANN_TYPE}" name="CHANN_TYPE" id="CHANN_TYPE" json="CHANN_TYPE"/>
			                     	<input type="hidden" value="${rst.CHAA_LVL}" name="CHAA_LVL" id="CHAA_LVL" json="CHAA_LVL"/>
			                     </td>
							    </tr>
							</c:forEach>
							<c:if test="${empty rst}">
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
	</table>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
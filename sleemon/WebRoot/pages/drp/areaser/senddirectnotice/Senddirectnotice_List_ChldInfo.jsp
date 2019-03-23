 
<!--
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_List
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/senddirectnotice/Senddirectnotice_List_ChldInfo.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >

<table width="99.8%" height="70%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="height: 90%">
			<table id="ordertb" width="100%"  border="0" cellpadding="1" cellspacing="1" class="lst">
				<input type="hidden" id="error" value="${error}"/>
				<input type="hidden" id="BASE_DELIVER_NOTICE_ID" value="${BASE_DELIVER_NOTICE_ID}"/>
				<input type="hidden" id="totalNum" value="${totalNum}"/>
				<input type="hidden" id="sessionId" value="${sessionId}"/>
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_FLAG" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="REAL_SEND_NUM" >发货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >分配总数量</th>
				</tr>
				<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>	
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}</td>
                     <td nowrap="nowrap" align="center" >${rst.BRAND}</td>
                     <td nowrap="nowrap" align="center" >
                     	<c:if test="${rst.IS_NO_STAND_FLAG eq 1}">
                     		是
                     	</c:if>
                     	<c:if test="${rst.IS_NO_STAND_FLAG eq 0}">
                     		否
                     	</c:if>
					 </td>
                     <td nowrap="nowrap" align="center" >
                     	 <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
                     	 	<span  style="color: red">有</span>
                     	 	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}')" />
                     	 </c:if>
                     	 <c:if test="${rst.SPCL_TECH_FLAG eq '0' || rst.SPCL_TECH_FLAG eq null}">
                     	 	无
                     	 </c:if>
                     	&nbsp;
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.REAL_SEND_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center" ><input type="text" readonly="readonly" name="allotNum" id="allotNum${rr}"  onclick="allotNum('${rst.PRD_ID}','${rst.SPCL_TECH_ID}','${rst.REAL_SEND_NUM}','${rr}')" /></td>
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
</tr>
</table>
<div><center><input type="button" value="处理" id="dispose" style="width: 50px;" class="btn"/><input type="button" onclick="windowClose()" class="btn" style="margin-left: 10px;width: 50px;"  value="关闭" /></center></div>
</body>

</html>
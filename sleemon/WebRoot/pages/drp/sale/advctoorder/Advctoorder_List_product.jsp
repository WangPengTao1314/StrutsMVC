<!--
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="PRD_ID" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="MEAS_UNIT" >计量单位</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NUM" >标准单位数量</th>
                    <th  nowrap="nowrap" align="center" dbname="FREEZE_NUM" >冻结数量</th>
                    <th  nowrap="nowrap" align="center" dbname="MEAS_STORE_NUM" >计量单位数量</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<tr>
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
                     <td nowrap="nowrap" align="center">${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.MEAS_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STORE_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.FREEZE_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.MEAS_STORE_NUM}&nbsp;</td>
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
	</td>
</tr>
	<tr>
		<td colspan="10" align="center" valign="middle" >
			<input id="close" type="button" class="btn" value="关闭(C)" title="Alt+C" onclick="next()" accesskey="C">&nbsp;&nbsp;
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	function next(){
		window.close();
	}
</script>
</html>


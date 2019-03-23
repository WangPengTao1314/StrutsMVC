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
	<title>查看库存</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">

<tr>
	<td height="20px" valign="top" colspan="3">
	<p align="center" style="font-size: 20px;font-weight: bold;">查看库存</p>
	</td>
</tr>

<tr>
	<td valign="top"  align="center">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="95%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="PRD_ID" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NO" >库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NAME" >库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NUM" >库存数量</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<tr>
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
                     <td nowrap="nowrap" align="center">&nbsp;${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="center">&nbsp;${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center">&nbsp;${rst.STORE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;${rst.STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right">&nbsp;${rst.STORE_NUM}&nbsp;</td>
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
			</div>
	</td>
</tr>
	<tr style="height: 40px; margin-bottom: 20px;">
		<td colspan="10" align="center" valign="middle" style="background: #EEEEFF;">
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


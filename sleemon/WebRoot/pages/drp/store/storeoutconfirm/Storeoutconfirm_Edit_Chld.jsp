<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/store/storeoutconfirm/Storeoutconfirm_Edit_Chld.js"></script>
	<style type="text/css">
	</style>
	<title>收货确认</title>
</head>
<body class="bodycss1">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
   	<tr>
   		<td align="center" colspan="2"  style="height: 10px;"><h3>收货信息</h3></td>
   	</tr>
   	<tr>
   		<td width="100px" style="height: 10px;">请上传附件：</td>
   		<td><input id="up"/></td>
   	</tr>
   	<tr>
   		<td height="50px;">
   			备注：
   		</td>
   		<td>
   			<textarea rows="4" cols="40" id="REMARK"></textarea>
   		</td>
   	</tr>
   	<tr>
   		<td height="10px;" colspan="2"><span style="font-size: 15px;">货品明细：</span></td>
   	</tr>
   	<tr >
   		<td colspan="2">
   			<div class="" style="width: 100%;overflow-x: auto;  height: 100%;background-color: #fff;">
   			<form id="ChldForm">
   				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
   					<input type="hidden" value="${STOREOUT_ID}" id="STOREOUT_ID"/>
   					<tr >
					<th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="NOTICE_NUM" >通知出库数量</th>
                    <th  nowrap="nowrap" align="center" dbname="REAL_NUM" >实际出库数量</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >收货数量</th>
					</tr>
				</table>
			</form>
			</div>
   		</td>
   	</tr>
   	<tr>
   		<td colspan="4" align="center" height="10%">
   			<input type="button" value="确定" onclick="toVerify()" class="btn" style="margin-right: 10px;"/> 
   			<input type="button" value="关闭" id="close" class="btn" />
   		</td>
   	</tr>
</table>
</body>
<%@ include file="/pages/common/uploadfile/picUpdfile.jsp"%>
<script type="text/javascript">
   		uploadFile('up', 'SAMPLE_DIR');
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
			'${rst.PRD_NO}',//0
			'${rst.PRD_NAME}',//1
			'${rst.PRD_SPEC}',//2
			'${rst.PRD_COLOR}',//3
			'${rst.BRAND}',//4
			'${rst.STD_UNIT}',//5
			'${rst.NOTICE_NUM}',//6
			'${rst.REAL_NUM}',//7
			'${rst.RECV_NUM}',//8
			'${rst.STOREOUT_DTL_ID}',//9
			'${rst.REAL_NUM}',//10
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
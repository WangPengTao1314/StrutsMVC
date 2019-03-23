<!--
/**
 * @prjName:供应链_贵人鸟
 * @fileName:Grant_Edit
 * @author zhuxw
 * @time   2013-05-15 10:35:31 
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
	<script type="text/javascript" src="${ctx}/pages/sample/grant/Grant_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：库存管理-质检管理-不良通知单编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                    <input json="WLDWXXID" name="WLDWXXID" autocheck="true" label="往来单位信息ID" type="hidden" inputtype="string"   value="${rst.WLDWXXID}"/> 
               <tr>
                   <td width="15%" align="right" class="detail_label">成品不良通知单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="CPBLTZDBH" name="CPBLTZDBH" autocheck="true" label="成品不良通知单编号"  type="text"   inputtype="string"         maxlength="32"  value="${rst.CPBLTZDBH}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">流程号：</td>
				   <td width="35%" class="detail_content">
                     <input json="LCH" name="LCH" autocheck="true" label="流程号"  type="text"   inputtype="string"         maxlength="30"  value="${rst.LCH}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">往来单位编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="WLDWBH" name="WLDWBH" autocheck="true" label="往来单位编号"  type="text"   inputtype="string"         maxlength="30"  value="${rst.WLDWBH}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">往来单位名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="WLDWMC" name="WLDWMC" autocheck="true" label="往来单位名称"  type="text"   inputtype="string"         maxlength="100"  value="${rst.WLDWMC}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">退回数量：</td>
				   <td width="35%" class="detail_content">
                     <input json="THSL" name="THSL" autocheck="true" label="退回数量"  type="text"   inputtype="int"      maxlength="22"  value="${rst.THSL}"/>
				   </td>
                     <td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
                       &nbsp;  
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
</script>
 
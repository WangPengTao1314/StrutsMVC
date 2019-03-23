<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Resvstore_Edit
 * @author zzb
 * @time   2013-09-07 14:13:12 
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/base/resvstore/Resvstore_Edit.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<div class="buttonlayer" id="floatDiv">
      <table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td align="left" nowrap>
		   	<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
			</td>
		</tr>
	</table>
</div>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住-->
<table width="100%" height="25px">
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<form name="form" id="mainForm">
		<input type="hidden" name="selectContion" id="selectContion" value=" STATE='启用' and DEL_FLAG=0  and FINAL_NODE_FLAG=1 "/>
		<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">货品编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="PRD_ID" name="PRD_ID" id="PRD_ID" value="${rst.PRD_ID}"/>
                     <input json="PRD_NO" name="PRD_NO" autocheck="true" label="货品编号"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="30"  value="${rst.PRD_NO}"/> 
				     <img align="absmiddle" name="selStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_21', false, 'PRD_ID', 'PRD_ID','forms[0]','PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT', 'PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT', 'selectContion')">
				  
				   </td>
				  
                   <td width="15%" align="right" class="detail_label">货品名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRD_NAME" name="PRD_NAME" autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="100"  value="${rst.PRD_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">规格型号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRD_SPEC" name="PRD_SPEC" autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="50"  value="${rst.PRD_SPEC}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">花号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRD_COLOR" name="PRD_COLOR" autocheck="true" label="花号"  type="text"   inputtype="string"   readonly      maxlength="50"  value="${rst.PRD_COLOR}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">品牌：</td>
				   <td width="35%" class="detail_content">
                     <input json="BRAND" name="BRAND" autocheck="true" label="品牌"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="50"  value="${rst.BRAND}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">标准单位：</td>
				   <td width="35%" class="detail_content">
                     <input json="STD_UNIT" name="STD_UNIT" autocheck="true" label="标准单位"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50"  value="${rst.STD_UNIT}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">安全库存数量：</td>
				   <td width="35%" class="detail_content">
                     <input json="SAFE_STORE_NUM" name="SAFE_STORE_NUM" autocheck="true" label="安全库存数量"  type="text"   inputtype="int"     mustinput="true"     maxlength="10"  value="${rst.SAFE_STORE_NUM}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">最低库存数量：</td>
				   <td width="35%" class="detail_content">
                     <input json="MIN_STORE_NUM" name="MIN_STORE_NUM" autocheck="true" label="最低库存数量"  type="text"   inputtype="int"     mustinput="true"     maxlength="10"  value="${rst.MIN_STORE_NUM}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea json="REMARK" name="REMARK" autocheck="true" label="备注"   inputtype="string"    rows="6" cols="80%"  maxlength="250" ><c:out value="${rst.remark}"></c:out> </textarea>
                  </td>
               </tr>
			</table>
			</td>
		</tr>
		</table>
		</form>
	</td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
</script>
 
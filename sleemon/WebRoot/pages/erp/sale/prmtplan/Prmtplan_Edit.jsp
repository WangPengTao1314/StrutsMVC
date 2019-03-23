<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtplan_Edit
 * @author zzb
 * @time   2013-08-23 16:04:28 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/prmtplan/Prmtplan_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;促销管理&gt;&gt;推广促销方案维护编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                <tr>
                   <td width="15%" align="right" class="detail_label">促销方案编号：</td>
				   <td width="35%" class="detail_content">
				    <input json="PRMT_PLAN_NO" name="PRMT_PLAN_NO" type="text" autocheck="true"  inputtype="string"  mustinput="true" 
				       readonly <c:if test="${isNew == true}"> value="自动生成"</c:if>
						<c:if test="${isNew == false}">value="${rst.PRMT_PLAN_NO}"</c:if>>
			       </td>
                   <td width="15%" align="right" class="detail_label">促销方案名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRMT_PLAN_NAME" name="PRMT_PLAN_NAME"  id="PRMT_PLAN_NAME" autocheck="true" label="促销方案名称"  type="text"   inputtype="string"     mustinput="true"     maxlength="150"  value="${rst.PRMT_PLAN_NAME}"/> 
				   </td>
               </tr>
               
               <tr>
                   <td width="15%" align="right" class="detail_label">促销类型：</td>
				   <td width="35%" class="detail_content">
				    <select name="PRMT_TYPE" id="PRMT_TYPE"  json="PRMT_TYPE"  style="width:155" autocheck="true" label="促销类型" inputtype="string"     mustinput="true"  >
				    </select>
				   </td>
				   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content"></td>
               </tr>
               
               	<tr>
					<td width="15%" align="right" class="detail_label">生效日期从：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="EFFCT_DATE_BEG" id="EFFCT_DATE_BEG"  name="EFFCT_DATE_BEG" autocheck="true" inputtype="string" label="生效日期从" value="${rst.EFFCT_DATE_BEG}" mustinput="true" onclick="SelectDate();" readonly="readonly" />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(EFFCT_DATE_BEG);">
					</td>
					<td width="15%" align="right" class="detail_label">生效日期到：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="EFFCT_DATE_END" id="EFFCT_DATE_END" name="EFFCT_DATE_END" autocheck="true" inputtype="string" label="生效日期到"  value="${rst.EFFCT_DATE_END}" mustinput="true" onclick="SelectDate();" readonly="readonly" />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(EFFCT_DATE_END);">
					</td>
				</tr>
				
             
               <tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="4">
       				<textarea  json="REMARK" name="REMARK" id="REMARK"  maxlength="1000" label="备注"  autocheck="true" rows="6" cols="80%" ><c:out value="${rst.NOTICE}"></c:out></textarea>
       				</td>
      			</tr>
      			
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script language="JavaScript">
	 
	SelDictShow("PRMT_TYPE","BS_23","${rst.PRMT_TYPE}","");
	 
</script>
 
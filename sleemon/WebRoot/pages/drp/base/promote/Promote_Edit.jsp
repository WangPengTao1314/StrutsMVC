<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:活动
 * @author zzb
 * @time    
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
	<script type="text/javascript" src="${ctx}/pages/drp/base/promote/Promote_Edit.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<div class="buttonlayer" id="floatDiv">
      <table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td align="left" nowrap>
		   	<input type="button" id="save"  class="btn" value="保存(S)" title="Alt+S" accesskey="S">
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
		<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">渠道活动编号：</td>
				   <td width="35%" class="detail_content">
				   <c:if test="${not empty rst.PROMOTE_NO}">
				       <input id="PROMOTE_NO" json="PROMOTE_NO" name="PROMOTE_NO" autocheck="true" label="渠道活动编号"  type="text"   
				       inputtype="string"      mustinput="true"     maxlength="30"   READONLY   value="${rst.PROMOTE_NO}"/>
				   
				   </c:if>
				   <c:if test="${empty rst.PROMOTE_NO}">
				         <input id="prefix"  type="text" inputtype="string"    
							 value="${CHANN_NO}" READONLY   size="7"  style="background-color:#eef;border: 0 solid #DFDFDF;height: 22px;">
							 
						<input id="suffix"   name="suffix" type="text"  style="margin-left: -4px;"
							autocheck="true" label="渠道活动编号" inputtype="int"  valueType="chinese:false" onkeyup="putPromotrNo(this);"
							mustinput="true" maxlength="20" value=""  size="4" style="width:100px;">
					   
					    <input   type="hidden" json="PROMOTE_NO" id="PROMOTE_NO" name="PROMOTE_NO" autocheck="true" label="渠道活动编号"    />
					    
				
				   </c:if>
				   </td>
				   
				   <%--<td width="35%" class="detail_content">
						<input json="PROMOTE_NO" name="PROMOTE_NO" type="text" json="PROMOTE_NO"  
							autocheck="true" label="渠道活动编号" inputtype="string" mustinput="true" 
							maxlength="32"
							<c:if test="${not empty rst.PROMOTE_NO}">value="${rst.PROMOTE_NO}"</c:if>
							<c:if test="${empty rst.PROMOTE_NO}">value="自动生成"</c:if>
							READONLY
							>
				  </td>
											
                   --%><td width="15%" align="right" class="detail_label">渠道活动名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="PROMOTE_NAME" name="PROMOTE_NAME"  label="渠道活动名称"  
                     type="text"   inputtype="string"     autocheck="true" mustinput="true"     maxlength="250"  style="width: 350px;" value="${rst.PROMOTE_NAME}"/> 
				   </td>
               </tr>
              
               <tr>
                   <td width="15%" align="right" class="detail_label">活动开始日期：</td>
				   <td width="35%" class="detail_content">
                      <input id="BEG_DATE" json="BEG_DATE"  name="BEG_DATE"  label="活动开始日期" autocheck="true" mustinput="true"  inputtype="string"    readonly="readonly"  onclick="SelectDate();"   value="${rst.BEG_DATE}">
	   				  <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BEG_DATE);"  >
				   </td>
                   <td width="15%" align="right" class="detail_label">活动结束日期：</td>
				   <td width="35%" class="detail_content">
				        <input id="END_DATE" json="END_DATE"  name="END_DATE"  label="活动结束日期"  autocheck="true" mustinput="true"   inputtype="string"  readonly="readonly"  onclick="SelectDate();"   value="${rst.END_DATE}">
	   				  <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_DATE);"  >
				  
                   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">活动描述：</td>
				   <td width="35%" class="detail_content" colspan="4">
                      <textarea id="PROMOTE_DESC" json="PROMOTE_DESC"  name="PROMOTE_DESC"   label="活动描述"  
                      autocheck="true" mustinput="true"  inputtype="string"   rows="4" cols="60"  maxlength="500" > ${rst.PROMOTE_DESC} </textarea>
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea json="REMARK" json="REMARK"  name="REMARK" autocheck="true" label="备注"   
				     inputtype="string"    rows="4" cols="60"  maxlength="250" >${rst.remark} </textarea>
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
 
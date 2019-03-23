<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Edit
 * @author lyg
 * @time   2014-01-26 14:46:31 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/plancheck/Palncheck_Edit.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;门店稽查管理&gt;检查维护编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
       <input type="hidden" name="selectParams" value=" STATE in( '启用') and DEL_FLAG='0' and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}')">
       <input type="hidden" name="selectParamsT"/>
       <input type="hidden" name="selectOrg" value="  DELFLAG='0' and ZTXXID='${LEDGER_ID}'">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">渠道化检查方案编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_CHECK_PLAN_NO" name="CHANN_CHECK_PLAN_NO" autocheck="true" label="渠道化检查方案编号"  type="text"   inputtype="string"   readonly    mustinput="true"  size="40"   maxlength="30"
                     <c:if test="${rst.CHANN_CHECK_PLAN_NO==null}"> value="自动生成"</c:if>
                     <c:if test="${rst.CHANN_CHECK_PLAN_NO!=null}">value="${rst.CHANN_CHECK_PLAN_NO}"</c:if>  
                    /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">渠道化检查方案名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_CHECK_PLAN_NAME" name="CHANN_CHECK_PLAN_NAME" id="CHANN_CHECK_PLAN_NAME" autocheck="true" label="渠道化检查方案名称"  type="text"   inputtype="string"  size="40"   mustinput="true"     maxlength="30"  value="${rst.CHANN_CHECK_PLAN_NAME}"/>
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">方案类型：</td>
				   <td width="35%" class="detail_content">
				   	 <select json="PLAN_TYPE" name="PLAN_TYPE" id="PLAN_TYPE" style="width:275px;" autocheck="true" label="方案类型"  type="text"  inputtype="string" maxlength="100"  value="${rst.PLAN_TYPE}"/>
				   </td>
				   <td width="15%" align="right" class="detail_label">状态：</td>
				   <td width="35%" class="detail_content">
				   	 <input  json="STATE" name="STATE" id="STATE" autocheck="true" label="状态"  type="text"  size="40" inputtype="string" maxlength="100"  value="${STATE}" READONLY/>
				   </td>
               </tr>
               <!--  
               <tr>
               	<td width="15%" align="right" class="detail_label">检查开始日期：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="CHECK_DATE_BEG" name="CHECK_DATE_BEG" id="CHECK_DATE_BEG" autocheck="true" label="检查开始日期" type="text" inputtype="string"  size="40"  value="${rst.CHECK_DATE_BEG}" onclick="SelectDate()"/>
  				     <img align="absmiddle" style="cursor:hand"  src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CHECK_DATE_BEG);">
  				   </td>
                   <td width="15%" align="right" class="detail_label">检查结束日期：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHECK_DATE_END" name="CHECK_DATE_END" id="CHECK_DATE_END" autocheck="true" label="检查结束日期"  type="text"   inputtype="string" size="40" maxlength="50"  value="${rst.CHECK_DATE_END}" onclick="SelectDate()"/> 
				     <img align="absmiddle" style="cursor:hand"  src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CHECK_DATE_END);">
				   </td>
               </tr>
              -->
               <tr>
                  <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content">
				   	 <textarea json="REMARK" name="REMARK" id="REMARK"  label="备注"  autocheck="true"  inputtype="string" maxLength="250" rows="4" cols="32%">${rst.REMARK}</textarea>
  				   </td>
  				   <td width="17%" align="right" class="detail_label">相关附件：</td>
				   <td width="33%" class="detail_content">
			         <input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"  type="hidden"   value="${rst.PIC_PATH}"/> 
				   </td> 
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
     SelDictShow("PLAN_TYPE","BS_88","${rst.PLAN_TYPE}","");
     uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
</script>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
 
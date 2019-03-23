<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advreq_Edit
 * @author ghx
 * @time   2014-07-15 
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
	<script type="text/javascript" src="${ctx}/pages/drp/adv/advreq/Advreq_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：营销管理&gt;&gt;广告投放管理&gt;&gt;广告投放申请单维护编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<input type="hidden" name="selectParamsChann" value="STATE in( '启用') and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}') " />
				<tr>
                   <td width="15%" align="right" class="detail_label">广告投放申请单号：</td>
				   <td width="35%" class="detail_content">
					   <c:if test="${empty rst.ERP_ADV_REQ_NO}">
	                     <input json="ERP_ADV_REQ_NO" name="ERP_ADV_REQ_NO" autocheck="true" label="广告投放申请单号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="自动生成" size="40"/> 
				   	   </c:if>
				   	   <c:if test="${not empty rst.ERP_ADV_REQ_NO}">
	                     <input json="ERP_ADV_REQ_NO" name="ERP_ADV_REQ_NO" autocheck="true" label="广告投放申请单号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.ERP_ADV_REQ_NO}" size="40"/> 
				   	   </c:if>
				   </td>
                   <td width="15%" align="right" class="detail_label">广告投放类型：</td>
				   <td width="35%" class="detail_content">
				  	   <c:if test="${empty rst.ERP_ADV_REQ_NO}">
	                     <input json="ADV_TYPE" name="ADV_TYPE" autocheck="true" label="广告投放类型"  type="text"   inputtype="string"   readonly  maxlength="30"  value="联合投放" size="40"/> 
				   	   </c:if>
				   	   <c:if test="${not empty rst.ERP_ADV_REQ_NO}">
	                     <input json="ADV_TYPE" name="ADV_TYPE" autocheck="true" label="广告投放类型"  type="text"   inputtype="string"   readonly  maxlength="30"  value="${rst.ADV_TYPE}" size="40"/> 
				   	   </c:if>					    
				   </td>
                </tr>
                <tr>               	   
                   <td width="15%" align="right" class="detail_label">渠道编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="CHANN_ID" name="CHANN_ID" autocheck="true" label="渠道ID" type="hidden" inputtype="string"   value="${rst.CHANN_ID}"/> 
                     <input json="CHANN_NO" name="CHANN_NO" autocheck="true" label="渠道编号"  type="text"   inputtype="string"   readonly mustinput="true"  maxlength="30"  value="${rst.CHANN_NO}" size="40"/> 
                     <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
						     onclick="selCommon('BS_107', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME', 
										'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME', 
										'selectParamsChann');"/>&nbsp;
					     
				   </td>
				   <td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME" autocheck="true" label="渠道名称"  type="text"   inputtype="string"   readonly    maxlength="100"  value="${rst.CHANN_NAME}" size="40"/> 
				   </td>                 
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">所属战区：</td>
				   <td width="35%" class="detail_content">
				     <input json="AREA_ID" name="AREA_ID" autocheck="true" label="区域ID" type="hidden" inputtype="string"   value="${rst.AREA_ID}"/>
				     <input json="AREA_NO" name="AREA_NO" autocheck="true" label="区域编号" type="hidden" inputtype="string"   value="${rst.AREA_NO}"/>
                     <input json="AREA_NAME" name="AREA_NAME" autocheck="true" label="所属战区"  type="text"   inputtype="string"   readonly    maxlength="100"  value="${rst.CHANN_NAME}" size="40"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">区域经理：</td>
				   <td width="35%" class="detail_content">
				     <input json="AREA_MANAGE_ID" name="AREA_MANAGE_ID" autocheck="true" label="区域经理ID" type="hidden" inputtype="string"   value="${rst.AREA_MANAGE_ID}"/>
                     <input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" autocheck="true" label="区域经理"  type="text"   inputtype="string"   readonly    maxlength="100"  value="${rst.AREA_MANAGE_NAME}" size="40"/> 
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">渠道排名：</td>
				   <td width="35%" class="detail_content"> 
				     <input json="RANK_SCALE" name="RANK_SCALE" id="RANK_SCALE" type="hidden" value="${rst.RANK_SCALE}"/> 
				     <input json="CHANN_RANK" name="CHANN_RANK" id="CHANN_RANK" type="hidden" value="${rst.CHANN_RANK}"/>                  
                     <select name="SEL_RANK" id="SEL_RANK" label="渠道排名" style="width: 275px;" onchange="channRankSel();" ></select> 
				   </td>
				   <td width="15%" align="right" class="detail_label">广告公司名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADV_CO_NAME" name="ADV_CO_NAME" autocheck="true" mustinput="true" label="广告公司名称"  type="text"   inputtype="string"  maxlength="100"  value="${rst.ADV_CO_NAME}" size="40"/> 
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">广告公司联系人：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADV_CO_CONTACT" name="ADV_CO_CONTACT" autocheck="true" mustinput="true" label="广告公司联系人"  type="text"   inputtype="string"  maxlength="30"  value="${rst.ADV_CO_CONTACT}" size="40"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">广告公司联系电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADV_CO_TEL" name="ADV_CO_TEL" autocheck="true" mustinput="true" label="广告公司联系电话"  type="text"   inputtype="string"  maxlength="30"  value="${rst.ADV_CO_TEL}" size="40"/> 
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">广告投放内容：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADV_CONTENT" name="ADV_CONTENT" autocheck="true" mustinput="true" label="广告投放内容"  type="text"   inputtype="string"  maxlength="250"  value="${rst.ADV_CONTENT}" size="40"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">广告投放地点：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADV_ADDR" name="ADV_ADDR" autocheck="true" mustinput="true" label="广告投放地点"  type="text"   inputtype="string"  maxlength="50"  value="${rst.ADV_ADDR}" size="40"/> 
				   </td>
                </tr>
                <tr>
				   <td width="15%" align="right" class="detail_label" nowrap>广告投放开始时间：</td>
				   <td width="35%" class="detail_content">
				   	 <input id="ADV_START_DATE" json="ADV_START_DATE" name="ADV_START_DATE"
														type="text" autocheck="true" label="广告投放开始时间"
														inputtype="string" mustinput="true"  readonly onclick="SelectDate();"
														value="${rst.ADV_START_DATE}" size="40" onblur="CompareSDate()"/>
				     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADV_START_DATE);"  >
				   </td>
				   <td width="15%" align="right" class="detail_label">广告投放结束时间：</td>
				   <td width="35%" class="detail_content">																										
					 <input id="ADV_END_DATE" json="ADV_END_DATE" name="ADV_END_DATE"
														type="text" autocheck="true" label="广告投放结束时间"
														inputtype="string" mustinput="true"  readonly onclick="SelectDate();"
														value="${rst.ADV_END_DATE}" size="40" onblur="CompareEDate()"/>
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADV_END_DATE);"  >
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">广告投放总预算金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADV_TOTAL_AMOUNT" name="ADV_TOTAL_AMOUNT" id="ADV_TOTAL_AMOUNT" autocheck="true" mustinput="true"  label="广告投放总预算金额"  type="text"   inputtype="float" onkeyup="countApply();"  value="${rst.ADV_TOTAL_AMOUNT}" size="40"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">向总部申请支持费用：</td>
				   <td width="35%" class="detail_content">
                     <input json="HEAD_SUP_AMOUNT" name="HEAD_SUP_AMOUNT" id="HEAD_SUP_AMOUNT" autocheck="true" label="向总部申请支持费用"  type="text"   inputtype="float"  value="${rst.HEAD_SUP_AMOUNT}" size="40"/> 
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">广告投放附件：</td>
				   <td width="35%" class="detail_content">
                     <input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"
														type="text" autocheck="true" label="广告投放附件"
														inputtype="string" 
														value="${rst.PIC_PATH}"> 
				   </td>
				   <td width="15%" align="right" class="detail_label">状态：</td>
				   <td width="35%" class="detail_content">
                     <input id="STATE" json="STATE" name="STATE"
														type="text" label="状态" readonly 														 
														value="${rst.STATE}" size="40"/> 
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">申请人：</td>
				   <td width="35%" class="detail_content">
                     <input json="CRE_NAME" name="CRE_NAME" autocheck="true" label="申请人"  type="text"   inputtype="string"   readonly     maxlength="30"  value="${rst.CRE_NAME}" size="40"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">申请时间：</td>
				   <td width="35%" class="detail_content">
                     <input json="CRE_TIME" name="CRE_TIME" autocheck="true" label="申请时间"  type="text"   inputtype="string"   readonly    maxlength="100"  value="${rst.CRE_TIME}" size="40"/> 
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content">
                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" 
																inputtype="string" maxlength="250"   label="REMARK"    
																rows="5" cols="32%">${rst.REMARK}</textarea> 
				   </td>
				   <td width="15%" align="right" class="detail_label">&nbsp;</td>
				   <td width="35%" class="detail_content">
                     &nbsp; 
				   </td>                	
                </tr>
				</table>
			</td>
		</tr>
		</table>
		<input json="ADV_TOTAL_AMOUNT" name="ADV_TOTAL_AMOUNT" id="ADV_TOTAL_AMOUNT" type="hidden"  value="${rst.ADV_TOTAL_AMOUNT}" />
	</form>
</body>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script language="JavaScript">
		SelDictShow("SEL_RANK", "BS_112", '${rst.RANK_SCALE}', "");
		uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
	</script>
</html> 
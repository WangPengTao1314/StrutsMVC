<!--
/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketing/MarketActivitiy_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>基础数据>>营销活动维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE ='启用' and (CHANN_ID='${ZTXXID}' or AREA_SER_ID='${ZTXXID}') "/>
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
        <tr>
      
        </tr>
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                    <td width="18%" align="right" class="detail_label">营销活动编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="MARKETING_ACT_NO" name="MARKETING_ACT_NO" type="text"
							autocheck="true" label="营销活动编号" inputtype="string" mustinput="true"
							maxlength="32"
							<c:if test="${not empty rst.MARKETING_ACT_NO}">value="${rst.MARKETING_ACT_NO}"</c:if>
							<c:if test="${empty rst.MARKETING_ACT_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
					<td width="18%" align="right" class="detail_label"></td>
				   	<td width="35%" class="detail_content"></td>
				 </tr>
				 <tr>								
                   <td width="15%" align="right" class="detail_label">营销活动名称：</td>
				   <td width="35%" class="detail_content" colspan="4">
                     <input json="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" json="MARKETING_ACT_NAME"   label="营销活动名称"  type="text"  autocheck="true" inputtype="string"   mustinput="true"  style="width: 600px;"   maxlength="100"  value="${rst.MARKETING_ACT_NAME}"/> 
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">发起人名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SPONSOR_NAME" name="SPONSOR_NAME" id="SPONSOR_NAME" label="发起人名称" mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.SPONSOR_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">提成比例：</td>
				   <td width="35%" class="detail_content">
                     <input json="COMMISSION_PERCENTAGE" name="COMMISSION_PERCENTAGE" id="COMMISSION_PERCENTAGE"  label="提成比例"  type="text" mustinput="true" inputtype="float"  valuetype="4,4" autocheck="true"   maxlength="9"  value="${rst.COMMISSION_PERCENTAGE}"/> 
                     <span style="color: red;">(填小数)</span>
				   </td>
                </tr>
           
                <tr>
                   <td width="18%" align="right" class="detail_label">开始日期：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="START_DATE" name="START_DATE" json="START_DATE" onclick="SelectDate();" label="开始日期" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.START_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(START_DATE);">
				   </td>
                   <td width="15%" align="right" class="detail_label">结束日期：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="END_DATE" name="END_DATE"  json="END_DATE" onclick="SelectDate();" label="结束日期" mustinput="true" inputtype="string" autocheck="true"   size="20" value="${rst.END_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_DATE);">
				   </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">活动内容：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="REMARK" name="REMARK" id="REMARK" mustinput="true" inputtype="string" autocheck="true" 
				      maxlength="250"   label="活动内容"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
              </tr>
			</table>
		</td>
	</tr>
	<tr>  </tr>
</table>
</form>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("carType","185","${params.carType}","");
     //初始化 审批流程
     spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>

 
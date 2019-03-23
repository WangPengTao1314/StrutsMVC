<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 发运确认
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliverconfm/Deliverconfm_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>当前位置：销售管理>>发运管理>>发运单确认</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" id="selParam" name="selParam" value=" STATE ='启用' and DEL_FLAG=0 "/>
       <input type="hidden" name="selectParam" value=" STATE ='启用' and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
		     <table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
                   <td width="18%" align="right" class="detail_label">发运单号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="DELIVER_ORDER_ID" id="DELIVER_ORDER_ID" json="DELIVER_ORDER_ID" value="${rst.DELIVER_ORDER_ID}"/>
                    <input json="" name="DELIVER_ORDER_NO"   label="发运单号"  readonly="readonly" autocheck="true"  maxlength="32" inputtype="string" type="text" value="${rst.DELIVER_ORDER_NO}"/> 
		          </td>
                  <td width="15%" align="right" class="detail_label">预计发货日期：</td>
			      <td width="35%" class="detail_content">
                    <input json="" name="ADVC_SEND_DATE" readonly  label="预计发货日期"  type="text" autocheck="true"  maxlength="32" inputtype="string"   onclick="SelectDate();"  value="${rst.ADVC_SEND_DATE}"/> 
			     </td>
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">发货方式：</td>
				   <td width="35%" class="detail_content" >
                    <input json="" name="DELIVER_TYPE"   label="发货方式"  readonly="readonly"  autocheck="true"  maxlength="20" inputtype="string"  type="text" value="${rst.DELIVER_TYPE}"/> 
		          </td>
		          <td width="15%" align="right" class="detail_label">进场时间：</td>
			      <td width="35%" class="detail_content">
                     <input json="" name="APPCH_TIME"  label="进场时间"   type="text" readonly  autocheck="true"  maxlength="32" inputtype="string"  value="${rst.APPCH_TIME}" onclick="SelectTime();"/> 
			     </td>
                  
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">物流公司编号：</td>
				   <td width="35%" class="detail_content">
                    <input json="" name="PRVD_NO" id="PRVD_NO"  label="物流公司编号"  readonly="readonly"  autocheck="true"  maxlength="30" inputtype="string"  type="text" value="${rst.PRVD_NO}"/> 
                  <td width="15%" align="right" class="detail_label">物流公司名称：</td>
			      <td width="35%" class="detail_content">
                    <input json="" name="PRVD_NAME" id="PRVD_NAME"  label="物流公司名称" readonly="readonly"  type="text"  autocheck="true"  maxlength="100"  mustinput="true"    value="${rst.PRVD_NAME}"/> 
			     </td>
               </tr>
               
               <tr>
                  <td width="18%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
                    <input json="" name="RECV_CHANN_NO"   label="收货方编号 "  readonly="readonly"  autocheck="true"  maxlength="32" inputtype="string"   type="text" value="${rst.RECV_CHANN_NO}"/> 
		           </td>
                  <td width="15%" align="right" class="detail_label">收货方名称：</td>
			      <td width="35%" class="detail_content">
                    <input json="" name="RECV_CHANN_NAME"  label="收货方名称" readonly="readonly"  type="text"  autocheck="true"  maxlength="100" inputtype="string" value="${rst.RECV_CHANN_NAME}"/> 
			     </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">托运单号：</td>
			      <td width="35%" class="detail_content">
                    <input json="CONSAR_NO" name="CONSAR_NO"  label="托运单号"   type="text" autocheck="true"  maxlength="30" inputtype="string"       value="${rst.CONSAR_NO}"/> 
			       </td>
                   <td width="18%" align="right" class="detail_label">司机手机号：</td>
				   <td width="35%" class="detail_content">
                     <input json="DRV_MOB_NO" name="DRV_MOB_NO"  label="司机手机号"   type="text"  autocheck="true"  maxlength="30" inputtype="string" value="${rst.DRV_MOB_NO}"/>
		          </td>
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">备注说明：</td>
				   <td width="35%" class="detail_content" colspan="3">
				    <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"  
				       maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
		          </td>
               </tr>
               
			 </table>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
   SelDictShow("CHANN_TYPE","BS_58","${rst.CHANN_TYPE}","");
</script>
</html>

 
<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:预订发货取消申请
 * @author zzb
 * @time   2013-09-15 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advccancelsapp/Advccancelsapp_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>终端销售&gt;&gt;预订单发货取消申请</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" name="selectParamsADVCReq" id="selectParamsADVCReq" value=" DEL_FLAG=0 and STATE !='未提交' and STATE !='已取消' and SEND_TYPE = '渠道发货'  and LEDGER_ID='${LEDGER_ID}' ">
       <input type="hidden" name="selectParamsStore" id="selectParamsStore" value=""/>
       <input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用' and CHANN_ID='${rst.RECV_CHANN_ID}' ">
       
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			 <tr>
                   <td width="15%" align="right" class="detail_label">预订单发货取消编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="ADVC_SEND_CANCEL_NO" name="ADVC_SEND_CANCEL_NO" type="text"
							autocheck="true" label="临时信用申请单号" inputtype="string" mustinput="true"
							maxlength="32"
							<c:if test="${not empty rst.ADVC_SEND_CANCEL_NO}">value="${rst.ADVC_SEND_CANCEL_NO}"</c:if>
							<c:if test="${empty rst.ADVC_SEND_CANCEL_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
				   <td width="18%" align="right" class="detail_label">预订单发货申请编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden"  id="ADVC_SEND_REQ_ID_OLD" name="ADVC_SEND_REQ_ID_OLD" value="${rst.ADVC_SEND_REQ_ID}" />
				    <input type="hidden" json="ADVC_SEND_REQ_ID"  id="ADVC_SEND_REQ_ID" name="ADVC_SEND_REQ_ID" value="${rst.ADVC_SEND_REQ_ID}" />
                    <input json="ADVC_SEND_REQ_NO" id="ADVC_SEND_REQ_NO" name="ADVC_SEND_REQ_NO"   label="预订单发货申请编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.ADVC_SEND_REQ_NO}"/> 
			        <img align="absmiddle" name="selADVC" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_66', false, 'ADVC_SEND_REQ_ID', 'ADVC_SEND_REQ_ID', 'forms[0]','ADVC_SEND_REQ_NO,STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_ID,SALE_PSON_NAME,CUST_NAME,TEL', 'ADVC_SEND_REQ_NO,STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_ID,SALE_PSON_NAME,CUST_NAME,TEL', 'selectParamsADVCReq');clearPrd();"> 
						
                     
				   </td>
               </tr>
			    <tr>
                   <td width="18%" align="right" class="detail_label">出库库房编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" json="STOREOUT_STORE_ID"  id="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID" value="${rst.STOREOUT_STORE_ID}" />
                    <input json="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO"   label="出库库房编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.STOREOUT_STORE_NO}"/> 
		            <%--<img align="absmiddle" name="selStore" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID', 'forms[0]','STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_NO,STORE_NAME', 'selectParamsStore');">--%> 
				   </td>
                   <td width="15%" align="right" class="detail_label">出库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  label="出库库房名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.STOREOUT_STORE_NAME}"/> 
				   </td>
               </tr>
                  <tr>
                   <td width="18%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" json="TERM_ID"  id="TERM_ID" name="TERM_ID" value="${rst.TERM_ID}" />
                    <input json="TERM_NO" name="TERM_NO"   label="终端编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.TERM_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME"  label="终端名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
               </tr>
               <tr>
                  <td width="15%" align="right" class="detail_label">销售日期：</td>
				   <td width="35%" class="detail_content">
                     <input json="SALE_DATE" name="SALE_DATE"  label="终端名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.SALE_DATE}"/> 
				   </td>
				   
                   <td width="18%" align="right" class="detail_label">业务员名称：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" json="SALE_PSON_ID"  id="SALE_PSON_ID" name="SALE_PSON_ID" value="${rst.SALE_PSON_ID}" />
                    <input json="SALE_PSON_NAME" name="SALE_PSON_NAME"   label="业务员名称"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.SALE_PSON_NAME}"/> 
		            <%--<img align="absmiddle" name="selJGXX" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_NO,RECV_CHANN_NAME,PERSON_CON,TEL', 'CHANN_NO,CHANN_NAME,PERSON_CON,TEL', 'selectParams');">--%> 
				   </td>
               </tr>
               
                <tr>
                   <td width="18%" align="right" class="detail_label">客户名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" id="CUST_NAME" label="客户名称" readonly="readonly"  type="text" value="${rst.CUST_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" id="TEL"  label="电话"  type="text"   readonly="readonly"    maxlength="30"  value="${rst.TEL}"/> 
				   </td>
                </tr>
                 <tr>
                   <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
                      <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
                </tr>
                 
			</table>
		</td>
	</tr>
</table>
</form>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("carType","BS_109","${params.carType}","");
     //初始化 审批流程
     spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>

 
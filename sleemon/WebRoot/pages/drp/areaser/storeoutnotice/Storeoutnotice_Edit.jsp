<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/storeoutnotice/Storeoutnotice_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：区域服务中心&gt;出库通知管理&gt;销售出库通知单编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
       <input type="hidden" id="selectParams" name="selectParams" value="">
       <input type="hidden" id="chann" value="${LEDGER_ID}"/>
       <input type="hidden" name="selectFrom" value=" DEL_FLAG=0 and LEDGER_ID='${LEDGER_ID}' and STATE='审核通过' and SALE_ORDER_ID in (select SALE_ORDER_ID from DRP_SALE_ORDER_DTL where (NVL(ORDER_NUM,0)-NVL(SENDED_NUM,0))>0) ">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="19%" align="right" class="detail_label">销售出库通知单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_NOTICE_NO" name="STOREOUT_NOTICE_NO" autocheck="true" label="销售出库通知单编号"  type="text"   inputtype="string"   readonly    mustinput="true"  
                     	<c:if test="${rst.STOREOUT_NOTICE_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.STOREOUT_NOTICE_NO!=null}">value="${rst.STOREOUT_NOTICE_NO}"</c:if>
                     	/> 
				   </td>
                    <td width="15%" align="right" class="detail_label">来源单据编号：</td>
					<td width="35%" class="detail_content">
                       <input json="FROM_BILL_ID" id="FROM_BILL_ID" name="FROM_BILL_ID" autocheck="true" label="来源单据ID" type="hidden" inputtype="string"   value="${rst.FROM_BILL_ID}"/>
                       <input json="FROM_BILL_NO" name="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text"  inputtype="string"  mustinput="true"  readonly   value="${rst.FROM_BILL_NO}"/>
                       <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selFrom()">
					</td>
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">发货方编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="SEND_CHANN_ID" id="SEND_CHANN_ID" name="SEND_CHANN_ID" value="${rst.SEND_CHANN_ID}" />
                     <input json="SEND_CHANN_NO" id="SEND_CHANN_NO" name="SEND_CHANN_NO"  label="发货方编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.SEND_CHANN_NO}" /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">发货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NAME" id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  label="发货方名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.SEND_CHANN_NAME}"/> 
				   </td>
                </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="RECV_CHANN_ID" id="RECV_CHANN_ID" name="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}" />
                     <input json="RECV_CHANN_NO" name="RECV_CHANN_NO" id="RECV_CHANN_NO"  label="收货方编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.RECV_CHANN_NO}" /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  id="RECV_CHANN_NAME" label="收货方名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.RECV_CHANN_NAME}"/> 
				   </td>
                </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">出库库房编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="STOREOUT_STORE_ID" id="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID" value="${rst.STOREOUT_STORE_ID}" />
                     <input json="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO" id="STOREOUT_STORE_NO"  label="出库库房编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.STOREOUT_STORE_NO}" />
                      <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="storeSelCommon();">
				   </td>
                   <td width="15%" align="right" class="detail_label">出库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME" id="STOREOUT_STORE_NAME"  label="出库库房名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.STOREOUT_STORE_NAME}"/> 
				   </td>
                </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">出库总金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_AMOUNT" name="STOREOUT_AMOUNT" id="STOREOUT_AMOUNT" autocheck="true" label="出库总金额"  type="text"   inputtype="string"   readonly    mustinput="true"   value="${rst.STOREOUT_AMOUNT}"/> 
				   </td>
                    <td width="15%" align="right" class="detail_label">销售日期：</td>
					<td width="35%" class="detail_content">
						<input type="text" json="SALE_DATE" id="SALE_DATE" name="SALE_DATE" autocheck="true" inputtype="string" label="销售日期"  value="${rst.SALE_DATE}"  mustinput="true"   readonly />
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">附加费：</td>
				   <td width="35%" class="detail_content">
				   		<input type="text" json="OTHER_COST" id="OTHER_COST" name="OTHER_COST" autocheck="true" inputtype="float" label="附加费"  value="${rst.OTHER_COST}"   />
				   </td>
                    <td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">收货地址：</td>
					<td width="35%" colspan="3" class="detail_content">
                   <input type="hidden" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value=""/>
                    <textarea  json="RECV_ADDR" name="RECV_ADDR" id="RECV_ADDR" autocheck="true" inputtype="string" disabled="disabled" mustinput="true"  maxlength="250"   label="详细地址"    rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
                    </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">备注：</td>
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
</html>
<script type="text/javascript">
</script>
 
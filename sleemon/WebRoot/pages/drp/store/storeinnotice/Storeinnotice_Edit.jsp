<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit
 * @author glw
 * @time   2013-08-17 17:07:03 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storeinnotice/Storeinnotice_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;入库管理&gt;&gt;入库通知单维护编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" name="mainForm" >
   	   <input type="hidden" name="selectParams" value=" STATE in( '启用') and BEL_CHANN_ID='${rst.RECV_CHANN_ID}'">
   	   <input type="hidden" name="selectParam" value=" STATE in( '启用')">
   	   <input type="hidden" name="selectGoodParam" value=" STATE ='已处理' and LEDGER_ID='${ZTXXID}'">
   	   <input type="hidden" name="selectDeliverParam" value="  STATE in ('部分发货','已发货','已收货') and DEL_FLAG=0 and ORDER_CHANN_ID='${ZTXXID}' ">
   	   
   	   
   	   <input type="hidden" id="state" value="${rst.STATE}">
   	   <input type="hidden" id="selRowId" value=""/> 
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">入库通知单编号：</td>
				   <td width="35%" class="detail_content">
				   	  <input json="STOREIN_NOTICE_ID" name="STOREIN_NOTICE_ID" label="入库通知单ID" type="hidden" value="${rst.STOREIN_NOTICE_ID}"/> 
                      <input json="STOREIN_NOTICE_NO" name="STOREIN_NOTICE_NO" autocheck="true" label="入库通知单编号"  type="text"   inputtype="string" readonly mustinput="true"     maxlength="30"
                      <c:if test="${rst.STOREIN_NOTICE_NO==null}">value="系统自动生成"</c:if>
                      <c:if test="${rst.STOREIN_NOTICE_NO!=null}">value="${rst.STOREIN_NOTICE_NO}"</c:if> 
                       /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content">
                     <input json="BILL_TYPE" id="billtype" name="BILL_TYPE" autocheck="true" label="单据类型"  type="text"   inputtype="string"     mustinput="true"     maxlength="30" readonly value="<c:out value="${rst.BILL_TYPE}">手工新增</c:out>"/> 
				   </td>
               </tr>
               <c:if test="${!empty rst.BILL_TYPE && rst.BILL_TYPE ne rst.HAND_AND }">
               <tr>
                   <td width="15%" align="right" class="detail_label">来源单据编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="FROM_BILL_ID" name="FROM_BILL_ID" label="来源单据ID" type="hidden" value="${rst.FROM_BILL_ID}"/> 
                     <input json="FROM_BILL_NO" name="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text"   inputtype="string"  mustinput="true" readonly maxlength="30"  value="${rst.FROM_BILL_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">&nbsp;</td>
               </tr>
               </c:if>
               <tr>
                   <td width="15%" align="right" class="detail_label">供货方编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="SEND_CHANN_ID" name="SEND_CHANN_ID" label="发货方ID" type="hidden" value="${rst.SEND_CHANN_ID}"/> 
                     <input json="SEND_CHANN_NO" name="SEND_CHANN_NO" autocheck="true" label="供货方编号"  type="text"   inputtype="string" readonly  maxlength="30"  value="${rst.SEND_CHANN_NO}"/> 
			   	     <c:if test="${rst.editType ne 'update' }">
			   	     <img align="absmiddle" name="selDEF_STORE_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
						onClick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID','mainForm','SEND_CHANN_ID,SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME','selectParam')">
				   	</c:if>
				   </td>
                   <td width="15%" align="right" class="detail_label">供货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NAME" name="SEND_CHANN_NAME" autocheck="true" label="发货方名称"  type="text"   inputtype="string" readonly  maxlength="100"  value="${rst.SEND_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="RECV_CHANN_ID" name="RECV_CHANN_ID" id="RECV_CHANN_ID" label="收货方ID" type="hidden" value="${rst.RECV_CHANN_ID}"/> 
                     <input json="RECV_CHANN_NO" name="RECV_CHANN_NO" autocheck="true" label="收货方编号"  type="text"   inputtype="string" mustinput="true" readonly maxlength="30"  value="${rst.RECV_CHANN_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME" autocheck="true" label="收货方名称"  type="text"   inputtype="string" mustinput="true" readonly maxlength="100"  value="${rst.RECV_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
               	 
                   <td width="15%" align="right" class="detail_label">来源类型：</td>
                   <td width="35%" class="detail_content">
                    
                     <input type="radio" name="FROM_TYPE" json="FROM_TYPE" id="FROM_TYPE_"  value="初始化"  <c:if test="${!empty rst.STOREIN_NOTICE_ID}">disabled="disabled" </c:if>   />   初始化
                     <input type="radio" name="FROM_TYPE" json="FROM_TYPE" id="FROM_TYPE_O"  value="总部订货" <c:if test="${!empty rst.STOREIN_NOTICE_ID}">disabled="disabled"  </c:if> />   总部订货
                      <input json="a" name="a" autocheck="true" label="来源类型"  type="hidden" id="checkFromType"   inputtype="string" mustinput="true" readonly  value="${rst.FROM_TYPE}"/> 
                    <input type="hidden" id="FROM_TYPE_VAL" value="${rst.FROM_TYPE}" />
                   </td>
                   <td width="15%" align="right" class="detail_label">
                     <span name="form_good_span" >来源单据编号：</span>
                   </td>
                   <td width="35%" class="detail_content">
                     <span name="form_good_span"  >
                        <input json="FROM_BILL_ID" id="FROM_BILL_ID" name="FROM_BILL_ID" label="订货订单ID" type="hidden" value="${rst.FROM_BILL_ID}"/> 
                        <input json="FROM_BILL_NO" id="FROM_BILL_NO"  name="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text"   inputtype="string" readonly   maxlength="30"  value="${rst.FROM_BILL_NO}"/> 
                        <span class="validate">*</span> 
                          <c:if test="${empty rst.STOREIN_NOTICE_ID}">
	                        <img align="absmiddle" name="selDEF_STORE_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_68', false, 'FROM_BILL_ID', 'DELIVER_ORDER_ID','mainForm','FROM_BILL_NO', 'DELIVER_ORDER_NO','selectDeliverParam')">
						</c:if>
				    </span>
                   </td>
               </tr>
              
               <tr>
                   <td width="15%" align="right" class="detail_label">默认入库库房编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="DEF_STORE_NO" id="DEF_STORE_NO" name="DEF_STORE_NO" autocheck="true" label="默认入库库房编号"  type="text"  inputtype="string" readonly maxlength="30"  value="${rst.DEF_STORE_NO}"/>
                     <input json="DEF_STORE_ID" id="DEF_STORE_ID" name="DEF_STORE_ID" label="默认入库库房ID" type="hidden" value="${rst.DEF_STORE_ID}" /> 
				     <img align="absmiddle" name="selDEF_STORE_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_35', false, 'DEF_STORE_ID', 'STORE_ID','mainForm',
												 'DEF_STORE_NO,DEF_STORE_NAME', 'STORE_NO,STORE_NAME','selectParams');changeChildStore();">
				   </td>
                   <td width="15%" align="right" class="detail_label">默认入库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="DEF_STORE_NAME" id="DEF_STORE_NAME" name="DEF_STORE_NAME" autocheck="true" label="默认入库库房名称"  type="text" inputtype="string"   readonly maxlength="50"  value="${rst.DEF_STORE_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		 <td width="15%" align="right" class="detail_label">入库时间：</td>
					<td width="35%" class="detail_content">
						<input type="text" json="STOREIN_TIME" id="STOREIN_TIME" name="STOREIN_TIME" autocheck="true" inputtype="string" label="入库时间"  value="${rst.STOREIN_TIME}" onclick="SelectTime();" readonly />
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(STOREIN_TIME);">
					</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label" >备注：</td>
					<td width="50%" class="detail_content" colspan="3">
						<textarea json="REMARK" name="REMARK" inputtype="string"
							maxlength="250" rows="3" cols="70%" allowHTML="true" ><c:out value="${rst.REMARK}"></c:out></textarea>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
 
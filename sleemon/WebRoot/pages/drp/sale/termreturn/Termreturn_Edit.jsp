<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/termreturn/Termreturn_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>终端退货录入明细</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;终端销售&gt;&gt;终端退货录入编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
					<!-- 0157676--Start--刘曰刚--2014-06-26 -->
					<!-- 修改来源单据过滤条件，状态not in 提交，未提交，退回，待核价 -->
                    <input type="hidden" name=selectParams value="STATE NOT IN ('未提交','退回','待核价') and DEL_FLAG=0 and BILL_TYPE='终端销售' and LEDGER_ID='${rst.LEDGER_ID}'">
                    <input type="hidden" name=selectTerm value="STATE='启用' and DEL_FLAG=0 and CHANN_ID_P='${rst.LEDGER_ID}'">
                    <!-- 0157676--End--刘曰刚--2014-06-26 -->
               <tr>
                    <td width="20%" align="right" class="detail_label">终端退货单编号：</td>
					<td width="35%" class="detail_content">
                       <input json="ADVC_ORDER_NO" name="ADVC_ORDER_NO" autocheck="true" label="终端退货单编号" mustinput="true"  type="text"  inputtype="string"  
                        readonly    maxlength="30" 
                        <c:if test="${rst.ADVC_ORDER_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.ADVC_ORDER_NO!=null}">value="${rst.ADVC_ORDER_NO}"</c:if>
                        /> 
					</td>
                    <td width="15%" align="right" class="detail_label">是否有来源：</td>
					<td width="35%" class="detail_content">
						有<input type="radio" name="fromFlag" id="fromFlagYes" value="1">
						无<input type="radio" name="fromFlag" id="fromFlagNo" value="0">
						<input type="hidden" id="fromFlag"
							<c:if test="${rst.ADVC_ORDER_NO==null|| rst.FROM_BILL_ID!=null}">  value="1" </c:if>
							<c:if test="${rst.FROM_BILL_ID==null&&rst.ADVC_ORDER_NO!=null}">  value="0" </c:if>
						/>
					</td>
               </tr>
               <tr>
               		<td width="20%" align="right" class="detail_label">来源单据编号：</td>
					<td width="35%" class="detail_content">
						<input json="FROM_BILL_ID" id="FROM_BILL_ID" name="FROM_BILL_ID" autocheck="true" label="来源单据ID" type="hidden" inputtype="string"   value="${rst.FROM_BILL_ID}"/> 
                        <input json="FROM_BILL_NO" name="FROM_BILL_NO" id="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text" mustinput="true" inputtype="string"   readonly    maxlength="30"  value="${rst.FROM_BILL_NO}"/>
                        <img align="absmiddle" name="" style="cursor: hand" id="selAdvc"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selAdvcorder()">  
					</td>
                    <td width="15%" align="right" class="detail_label">终端编号：</td>
					<td width="35%" class="detail_content">
						<input json="TERM_ID" id="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/>
                        <input json="TERM_NO" id="TERM_NO" name="TERM_NO" id="TERM_NO" autocheck="true" label="终端编号"  type="text" mustinput="true"  inputtype="string"   readonly    maxlength="30"  value="${rst.TERM_NO}"/>
                        <img align="absmiddle" name="" style="cursor: hand" id="selTerm"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectTerm')">
                        <input id="tempTERM_ID" value="${tempTERM_ID}" type="hidden">
                        <input id="tempTERM_NO" value="${tempTERM_NO}" type="hidden"> 
                        <input id="tempTERM_NAME" value="${tempTERM_NAME}" type="hidden">  
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">客户姓名：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" id="CUST_NAME" autocheck="true" label="客户姓名"  type="text" readonly  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">合同编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="CONTRACT_NO" name="CONTRACT_NO" id="CONTRACT_NO" autocheck="true" label="合同编号"  type="text"   inputtype="string"   readonly      maxlength="100"  value="${rst.CONTRACT_NO}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">退货日期：</td>
					<td width="35%" class="detail_content">
						<input type="text" json="RETURN_STATEMENT_DATE" id="RETURN_STATEMENT_DATE" name="RETURN_STATEMENT_DATE" autocheck="true" inputtype="string" label="退货日期"  value="${rst.RETURN_STATEMENT_DATE}" onclick="SelectDate();"  mustinput="true"  readonly />
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(RETURN_STATEMENT_DATE);">
					</td>
				</tr>
				<tr>
               		<td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" id="TEL" autocheck="true" label="电话"  type="text"   inputtype="string" readonly    mustinput="true"     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
                    <td width="15%" align="right" class="detail_label">业务员：</td>
					<td width="35%" class="detail_content">
						<input json="SALE_PSON_ID" name="SALE_PSON_ID" autocheck="true" label="业务员ID" type="hidden" inputtype="string"   value="${rst.SALE_PSON_ID}"/> 
                        <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" id="SALE_PSON_NAME" autocheck="true" label="业务员"  type="text" mustinput="true" inputtype="string"   readonly    maxlength="30"  value="${rst.SALE_PSON_NAME}"/> 
					</td>
               </tr>
               		
					<%--<td width="15%" align="right" class="detail_label">退货扣款金额：</td>
					<td width="35%" class="detail_content">
						<input json="RETURN_DEDUCT_AMOUNT" id="RETURN_DEDUCT_AMOUNT" name="RETURN_DEDUCT_AMOUNT" autocheck="true" label="退货扣款金额"  type="text"   inputtype="float"   valueType="12,2"  value="${rst.RETURN_DEDUCT_AMOUNT}"/>
					</td>
               --%>
              
               <tr>
                    <td width="15%" align="right" class="detail_label">收货地址：</td>
					<td width="35%" class="detail_content" colspan="3">
                         <textarea  json="RECV_ADDR" name="RECV_ADDR" id="RECV_ADDR" autocheck="true" inputtype="string"   maxlength="250"   label="收货地址"    mustinput="true"  disabled="disabled" rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
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
 
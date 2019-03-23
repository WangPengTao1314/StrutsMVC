<!--
/**
 * @prjName：喜临门营销平台
 * @fileName：Promoexpen_Edit
 * @author chenj
 * @time   2014-01-24 10：59：55 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/promoexpen/Promoexpen_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<div class="buttonlayer" id="floatDiv">
      <table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td align="left" nowrap>
		   	<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
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
			<input id="ZTXX_ID" name="ZTXX_ID" json="ZTXX_ID" type='hidden' value="${rst.LEDGER_ID}">
			<input id="CHANN_ID" name="CHANN_ID" json="CHANN_ID" type='hidden' value="${rst.CHANN_ID}">
			<input id="ZONE_ID" name="ZONE_ID" json="ZONE_ID" type='hidden' value="${rst.ZONE_ID}">
			<input id="REQ_ID" name="REQ_ID" json="REQ_ID" type='hidden' value="${rst.REQ_ID}">
			<input id="PRMT_COST_REQ_ID" name="PRMT_COST_REQ_ID" json="PRMT_COST_REQ_ID" type='hidden' value="${rst.PRMT_COST_REQ_ID}">
			<input id="AREA_ID" name="AREA_ID" json="AREA_ID" type='hidden' value="${rst.AREA_ID}">
			<input id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" json="AREA_MANAGE_ID" type='hidden' value="${rst.AREA_MANAGE_ID}">
			<input type="hidden" name="selectParams" value=" DEL_FLAG='0' and STATE='启用' and CHANN_ID in ${CHANN_ID}">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				   <tr>
	                   <td width="15%" align="right" class="detail_label">推广费用申请单号：</td>
					   <td width="35%" class="detail_content">
	                     <input json="PRMT_COST_REQ_NO" name="PRMT_COST_REQ_NO" autocheck="true" readonly label="推广费用申请单号"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.PRMT_COST_REQ_NO}" size="40" /> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">申请人：</td>
					   <td width="35%" class="detail_content">
	                     <input json="REQ_NAME" name="REQ_NAME" autocheck="true" label="申请人"  type="text" readonly  inputtype="string"     mustinput="true" size="40"   value="${rst.REQ_NAME}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商编号：</td>
					   <td width="35%" class="detail_content">
	                     <input json="CHANN_NO" name="CHANN_NO" autocheck="true" label="加盟商编号"  type="text" readonly  inputtype="string"     mustinput="true"  size="40"   maxlength="30"  value="${rst.CHANN_NO}"/>
	                     <input id="con" json="con" name="con" type="hidden">
	                      <img align="absmiddle" style="cursor： hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryChannel();">  
					   </td>
	                   <td width="15%" align="right" class="detail_label">申请日期：</td>
					   <td width="35%" class="detail_content">
	                     <input json="REQ_DATE" name="REQ_DATE" autocheck="true" label="申请日期"  type="text"  readonly inputtype="string"  size="40"   mustinput="true" value="${rst.REQ_DATE}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商名称：</td>
					   <td width="35%" class="detail_content">
	                     <input json="CHANN_NAME" name="CHANN_NAME" autocheck="true" label="渠道名称"  type="text" readonly  inputtype="string"  size="40"      maxlength="100"  value="${rst.CHANN_NAME}"/> 
					   </td>
					   <td width="15%" align="right" class="detail_label">城市名称：</td>
					   <td width="35%" class="detail_content">
	                     <input json="ZONE_ADDR" name="ZONE_ADDR" autocheck="true" label="城市名称"  type="text" readonly  inputtype="string"  size="40"    mustinput="true"  maxlength="250"  value="${rst.ZONE_ADDR}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商联系人：</td>
					   <td width="35%" class="detail_content">
	                     <input json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" autocheck="true" label="加盟商联系人" readonly type="text" size="40"  inputtype="string"     mustinput="true"     maxlength="22"  value="${rst.CHANN_PERSON_CON}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">加盟商联系电话：</td>
					   <td width="35%" class="detail_content">
	                     <input json="CHANN_TEL" name="CHANN_TEL" autocheck="true" label="加盟商联系电话"  type="text"  readonly inputtype="string"   size="40"     maxlength="22"  value="${rst.CHANN_TEL}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">所属战区编号：</td>
					   <td width="35%" class="detail_content">
	                     <input json="AREA_NO" name="AREA_NO" autocheck="true" label="所属战区编号"  type="text" readonly  inputtype="string"  size="40"   mustinput="true"     maxlength="22"  value="${rst.AREA_NO}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">所属战区名称：</td>
					   <td width="35%" class="detail_content">
	                     <input json="AREA_NAME" name="AREA_NAME" autocheck="true" label="所属战区名称"  type="text" readonly  inputtype="string" size="40"       maxlength="22"  value="${rst.AREA_NAME}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">区域经理：</td>
					   <td width="35%" class="detail_content">
	                     <input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" autocheck="true" label="区域经理" readonly type="text" size="40"  inputtype="string"     mustinput="true"     maxlength="22"  value="${rst.AREA_MANAGE_NAME}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">区域经理电话：</td>
					   <td width="35%" class="detail_content">
	                     <input json="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" autocheck="true" label="区域经理电话" readonly type="text" size="40"  inputtype="string"        maxlength="22"  value="${rst.AREA_MANAGE_TEL}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">申请原因：</td>
					   <td width="35%" class="detail_content" colspan="3">
	                     <textarea <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">disabled="disabled"</c:if>  json="REQ_REMARK" name="REQ_REMARK" id="REQ_REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="申请原因"    rows="5" cols="32%" >${rst.REQ_REMARK}</textarea>
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">此次推广费用共计：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> json="TOTAL_PRMT_COST" name="TOTAL_PRMT_COST" autocheck="true" label="此次推广费用共计"  type="text"   autocheck="true" inputtype="float" valueType="8,2" maxlength="8"     mustinput="true"  size="40" value="${rst.TOTAL_PRMT_COST}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">申请报销金额：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> json="REIT_REQ_AMOUNT" name="REIT_REQ_AMOUNT" autocheck="true" label="申请报销金额"  type="text"  size="40"   autocheck="true" inputtype="float" valueType="8,2" maxlength="8" value="${rst.REIT_REQ_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">费用类别：</td>
					   <td width="35%" class="detail_content">
	                     <select name=COST_TYPE id="COST_TYPE" json="COST_TYPE" style="width: 275px;"  label="费用类别" inputtype="string"  mustinput="true"></select> 
					   </td>
					   <td width="15%" align="right" class="detail_label">状态：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> json="STATE" name="STATE" autocheck="true" label="状态"  type="text"   inputtype="string"  size="40"  readonly maxlength="22"  value="${rst.STATE}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">附件：</td>
					   <td width="35%" class="detail_content" colspan="3">
					   		<input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="STATENEBTS_ATT" json="STATENEBTS_ATT" name="STATENEBTS_ATT" autocheck="true" type="text" size="40"  value="${rst.STATENEBTS_ATT}"/>
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度累计已发货：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="TOTAL_SEND_AMOUNT" json="TOTAL_SEND_AMOUNT" name="TOTAL_SEND_AMOUNT"  label="加盟商本季度累计已发货"  type="text"  size="40"  autocheck="true" inputtype="float" valueType="8,2" maxlength="8"  value="${rst.TOTAL_SEND_AMOUNT}"
	                     	onkeyup="jsOne()" onblur="jsOne()"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度累计预算：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="TOTAL_BUDGET_AMOUNT" onkeyup="jsOne()" onblur="jsOne()" json="TOTAL_BUDGET_AMOUNT" name="TOTAL_BUDGET_AMOUNT"  label="加盟商本季度累计预算"  size="40" type="text"  autocheck="true"   inputtype="float"   valueType="8,2"  mustinput="true"  maxlength="8"  value="${rst.TOTAL_BUDGET_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度累计已发货费用：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="TOTAL_SEND_COST_AMOUNT" json="TOTAL_SEND_COST_AMOUNT" name="TOTAL_SEND_COST_AMOUNT" readonly autocheck="true" label="加盟商本季度累计已发货费用" size="40"  type="text"  mustinput="true"  value="${rst.TOTAL_SEND_COST_AMOUNT}"/> 
					   </td>
					   <td width="15%" align="right" class="detail_label">加盟商本季度累计发货预算费用：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="TOTAL_SEND_BUDGET_AMOUNT" json="TOTAL_SEND_BUDGET_AMOUNT" name="TOTAL_SEND_BUDGET_AMOUNT" readonly autocheck="true" label="加盟商本季度累计发货预算费用"  size="40" type="text" mustinput="true" value="${rst.TOTAL_SEND_BUDGET_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度申请费用：</td>
					   <td width="35%" class="detail_content" colspan="3">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="REQ_COST_AMOUNT" json="REQ_COST_AMOUNT" onkeyup="jsOne()" onblur="jsOne()" name="REQ_COST_AMOUNT"  label="加盟商本季度申请费用"  type="text"  size="40"  autocheck="true" inputtype="float"   valueType="8,2"    mustinput="true"  maxlength="8"  value="${rst.REQ_COST_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度发货剩余费用：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="TOTAL_SEND_LEFT_AMOUNT" json="TOTAL_SEND_LEFT_AMOUNT" name="TOTAL_SEND_LEFT_AMOUNT" label="加盟商本季度发货剩余费用"  type="text"  autocheck="true" size="40"   mustinput="true" value="${rst.TOTAL_SEND_LEFT_AMOUNT}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度预算剩余费用：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="TOTAL_BUDGET_LEFT_AMOUNT" json="TOTAL_BUDGET_LEFT_AMOUNT" name="TOTAL_BUDGET_LEFT_AMOUNT"  label="加盟商本季度预算剩余费用"  type="text" size="40"  mustinput="true" value="${rst.TOTAL_BUDGET_LEFT_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度发货费效比：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="SEND_COST_RATE" json="SEND_COST_RATE" name="SEND_COST_RATE" readonly label="加盟商本季度发货费效比"  type="text"  autocheck="true" size="40"  mustinput="true" value="${rst.SEND_COST_RATE}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">加盟商本季度预算费效比：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="SEND_BUDGET_RATE" json="SEND_BUDGET_RATE" name="SEND_BUDGET_RATE" readonly label="加盟商本季度预算费效比"  type="text" size="40"  mustinput="true" value="${rst.SEND_BUDGET_RATE}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度累计已发货：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="TOTAL_AREA_SEND_AMOUNT" json="TOTAL_AREA_SEND_AMOUNT" label="所属战区本季度累计已发货" name="TOTAL_AREA_SEND_AMOUNT" type="text"   autocheck="true"  inputtype="float" valueType="8,2" maxlength="8"  size="40" value="${rst.TOTAL_AREA_SEND_AMOUNT}"  onkeyup="jsTwo()" onblur="jsTwo()"/>
	                     
					   </td>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度累计预算：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="TOTAL_AREA_BUDGET_AMOUNT" onkeyup="jsTwo()"  onblur="jsTwo()" json="TOTAL_AREA_BUDGET_AMOUNT" name="TOTAL_AREA_BUDGET_AMOUNT" label="所属战区本季度累计预算"  size="40" type="text"  autocheck="true"  inputtype="float" valueType="8,2" maxlength="8"  value="${rst.TOTAL_AREA_BUDGET_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度累计已发货费用：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="TOTAL_AREA_SEND_COST_AMOUNT" json="TOTAL_AREA_SEND_COST_AMOUNT" name="TOTAL_AREA_SEND_COST_AMOUNT" label="所属战区本季度累计已发货费用"  type="text"  size="40"   mustinput="true"    maxlength="22"  value="${rst.TOTAL_AREA_SEND_COST_AMOUNT}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度累计发货预算费用：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="TOTAL_AREA_SEND_BUDGET_AMOUNT" json="TOTAL_AREA_SEND_BUDGET_AMOUNT" name="TOTAL_AREA_SEND_BUDGET_AMOUNT"  label="所属战区本季度累计发货预算费用"  type="text" size="40"  mustinput="true"    maxlength="22"  value="${rst.TOTAL_AREA_SEND_BUDGET_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度申请费用：</td>
					   <td width="35%" class="detail_content" colspan="3">
	                     <input <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">readonly</c:if> id="AREA_REQ_COST_AMOUNT" json="AREA_REQ_COST_AMOUNT" name="AREA_REQ_COST_AMOUNT"  onkeyup="jsOne();jsTwo();" onblur="jsOne();jsTwo();" label="所属战区本季度申请费用"  size="40" type="text" autocheck="true"  inputtype="float"   valueType="10,2"    mustinput="true"   maxlength="10"  value="${rst.AREA_REQ_COST_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	               	   <td width="15%" align="right" class="detail_label">所属战区本季度发货剩余费用：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="TOTAL_AREA_SEND_LEFT_AMOUNT" json="TOTAL_AREA_SEND_LEFT_AMOUNT" name="TOTAL_AREA_SEND_LEFT_AMOUNT"  label="所属战区本季度发货剩余费用"  type="text"  size="40"   mustinput="true" value="${rst.TOTAL_AREA_SEND_LEFT_AMOUNT}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度预算剩余费用：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="TOTAL_AREA_BUDGET_LEFT_AMOUNT" json="TOTAL_AREA_BUDGET_LEFT_AMOUNT" name="TOTAL_AREA_BUDGET_LEFT_AMOUNT" label="所属战区本季度预算剩余费用"  type="text" size="40"  value="${rst.TOTAL_AREA_BUDGET_LEFT_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	               	   <td width="15%" align="right" class="detail_label">所属战区本季度发货费效比：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="AREA_SEND_COST_RATE" json="AREA_SEND_COST_RATE" name="AREA_SEND_COST_RATE" label="所属战区本季度发货费效比" type="text" size="40" value="${rst.AREA_SEND_COST_RATE}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">所属战区本季度预算费效比：</td>
					   <td width="35%" class="detail_content">
	                     <input readonly id="AREA_SEND_BUDGET_RATE" json="AREA_SEND_BUDGET_RATE" name="AREA_SEND_BUDGET_RATE"  label="所属战区本季度预算费效比" type="text" size="40" value="${rst.AREA_SEND_BUDGET_RATE}"/> 
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
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	SelDictShow("COST_TYPE","BS_92","${rst.COST_TYPE}","");
	uploadFile('STATENEBTS_ATT',"SAMPLE_DIR", true,true,true);
</script>
 
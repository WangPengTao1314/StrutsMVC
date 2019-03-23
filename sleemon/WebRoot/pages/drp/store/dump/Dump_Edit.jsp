<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:转储单
 * @author zzb
 * @time   2013-09-05 14:00:34 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/dump/Dump_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理>>转储管理>>转储单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" name="tellOutparams" id="tellOutparams" value=""/>
       <input type="hidden" name="tellInparams" id="tellInparams" value="" />
       <input type="hidden" name="TERM_ID" id="TERM_ID" value="${TERM_ID}" />
       <input type="hidden" name="ZTXXID" id="ZTXXID" value="${ZTXXID}" />
       <input type="hidden" name="TERM_CHARGE" id="TERM_CHARGE" value="${TERM_CHARGE}" />
        
        <input type="hidden" name="selFromBill" id="selFromBill" value="  DEL_FLAG=0 and LEDGER_ID='${ZTXXID}' and STATE='审核通过' " />
       <input type="hidden" id="TERM_ID" name="TERM_ID" value="${TERM_ID}"/>
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                    
                <tr>
                   <td width="15%" align="right" class="detail_label">转储单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="DUMP_NO" name="DUMP_NO" autocheck="true" label="转储单编号"  
                      type="text"   inputtype="string"   readonly    mustinput="true"   
                         <c:if test="${isNew == true}"> value="自动生成"</c:if>
						 <c:if test="${isNew == false}">value="${rst.DUMP_NO}"</c:if> /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content">
				     <select json="BILL_TYPE" name="BILL_TYPE" id="BILL_TYPE" autocheck="true" label="单据类型" inputtype="string"   mustinput="true" style="width:155" onchange="changeBillType(this);"></select>
				   </td>
               </tr>
               
               <tr>
                   <td width="15%" align="right" class="detail_label">转出库房编号：</td>
				   <td width="35%" class="detail_content">
				     <input  id="DUMP_OUT_STORE_ID_OLD" label="转出库房ID_OLD" type="hidden"   value="${rst.DUMP_OUT_STORE_ID}"/>
				     <input json="DUMP_OUT_STORE_ID" name="DUMP_OUT_STORE_ID" id="DUMP_OUT_STORE_ID" label="转出库房ID" type="hidden"   value="${rst.DUMP_OUT_STORE_ID}"/>
<!--				     <input json="DUMP_OUT_STORAGE_FLAG" name="DUMP_OUT_STORAGE_FLAG" id="DUMP_OUT_STORAGE_FLAG"  label="转出库位管理标记" type="hidden"   value="${rst.DUMP_OUT_STORAGE_FLAG}" onPropertyChange="checkOutFlag(this);"/>-->
                     <input json="DUMP_OUT_STORE_NO" name="DUMP_OUT_STORE_NO" id="DUMP_OUT_STORE_NO" autocheck="true" label="转出库房编号"  type="text"   inputtype="string"   mustinput="true"  readonly   maxlength="30"  value="${rst.DUMP_OUT_STORE_NO}" /> 
				     <img align="absmiddle" name="selStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selTellOutStore();setInstoreVal();clearBottmOutKUWEIval();">
				   </td>
                   <td width="15%" align="right" class="detail_label">转出库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="DUMP_OUT_STORE_NAME" name="DUMP_OUT_STORE_NAME" id="DUMP_OUT_STORE_NAME" autocheck="true" label="转出库房名称"  type="text"   inputtype="string" readonly    mustinput="true"     maxlength="100"  value="${rst.DUMP_OUT_STORE_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">转入库房编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="STORAGE_FLAG2" name="STORAGE_FLAG2" id="STORAGE_FLAG2" label="转入库位管理标记" type="hidden" value="${rst.STORAGE_FLAG2}" onPropertyChange="checkINFlag(this);"/>
				     <input json="DUMP_IN_STORE_ID" name="DUMP_IN_STORE_ID" id="DUMP_IN_STORE_ID" label="转入库房ID" type="hidden" value="${rst.DUMP_IN_STORE_ID}"/> 
                     <input json="DUMP_IN_STORE_NO" name="DUMP_IN_STORE_NO" id="DUMP_IN_STORE_NO"  autocheck="true" label="转入库房编号"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="30"  value="${rst.DUMP_IN_STORE_NO}"/> 
				     <img align="absmiddle" name="selInStore" id="selInStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selTellInStore();inStoreChange();">
				   
				   </td>
				   <td width="15%" align="right" class="detail_label">转入库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="DUMP_IN_STORE_NAME" name="DUMP_IN_STORE_NAME" id="DUMP_IN_STORE_NAME" autocheck="true" label="转入库房名称"  type="text"   inputtype="string"     mustinput="true"   readonly  maxlength="100"  value="${rst.DUMP_IN_STORE_NAME}"/> 
				   </td>
               </tr>
<!--               <tr>-->
<!--                   <td width="15%" align="right" class="detail_label">转出库位管理标记：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input type="radio"  id="OUT_FLAG_YES" name="OUT_FLAG" label="转出库位管理标记" disabled <c:if test="${rst.DUMP_OUT_STORAGE_FLAG eq 1}"> checked </c:if> value="1" /> 是-->
<!--                     <input type="radio"  id="OUT_FLAG_NO"  name="OUT_FLAG" label="转出库位管理标记" disabled <c:if test="${rst.DUMP_OUT_STORAGE_FLAG eq 0}"> checked </c:if> value="0" /> 否-->
<!--				      0宽度input标签，用来显示单选按钮后的必选星号 -->
<!--					 <input mustinput="true" inputtype="string" name="tab"  style="width: 0px;"  type="text" > -->
<!--				   </td>-->
<!--                   <td width="15%" align="right" class="detail_label">转入库位管理标记：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input type="radio" name="IN_FLAG" id="IN_FLAG_YES" label="转入库位管理标记"  disabled <c:if test="${rst.STORAGE_FLAG2 eq 1}"> checked</c:if> value="1"/> 是-->
<!--				     <input type="radio" name="IN_FLAG" id="IN_FLAG_NO"  label="转入库位管理标记"  disabled  <c:if test="${rst.STORAGE_FLAG2 eq 0}"> checked</c:if> value="0"/> 否-->
<!--				      0宽度input标签，用来显示单选按钮后的必选星号 -->
<!--					 <input mustinput="true" inputtype="string" name="tab"  style="width: 0px;"  type="text" > -->
<!--				   </td>-->
<!--               </tr>-->
			   <tr>
			   		 <td width="15%" align="right" class="detail_label">转储日期：</td>
				   <td width="35%" class="detail_content">
				   		<input type="text" json="DUMP_DATE" id="DUMP_DATE" name="DUMP_DATE" autocheck="true" inputtype="string" onclick="SelectDate();"  label="转储日期"  value="${rst.DUMP_DATE}" mustinput="true"  readonly />
				   		<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(DUMP_DATE);">
				   </td>
                   <td width="15%" align="right" class="detail_label">关联单据号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" name="RELT_BILL_NO_OLD" id="RELT_BILL_NO_OLD" value="${rst.RELT_BILL_NO}"/>
				     <input type="hidden" name="MX_IDS" id="MX_IDS" value="${MX_IDS}"/>
                     <input json="RELT_BILL_NO" name="RELT_BILL_NO" id="RELT_BILL_NO" autocheck="true" label="关联单据号"  type="text"   inputtype="string" readonly         maxlength="100"  value="${rst.RELT_BILL_NO}"/>
                     <img align="absmiddle" name="selInStore" id="selInStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_133', false, 'RELT_BILL_NO', 'DUMP_NO', 'forms[0]','RELT_BILL_NO,OLD_IN_STORE_ID,OLD_OUT_STORE_ID', 'DUMP_NO,DUMP_IN_STORE_ID,DUMP_OUT_STORE_ID', 'selFromBill');addChild();">
					 <input type="hidden" id="OLD_IN_STORE_ID" name="OLD_IN_STORE_ID"> 
					 <input type="hidden" id="OLD_OUT_STORE_ID" .name="OLD_OUT_STORE_ID"> 
				   </td>
			   </tr>
               <tr>
				   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
                     <textarea json="REMARK" name="REMARK" autocheck="true" label="备注"   inputtype="string"    rows="6" cols="80%"  maxlength="250" ><c:out value="${rst.remark}"></c:out> </textarea>
				   </td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
       SelDictShow("BILL_TYPE","64","${rst.BILL_TYPE}","");
       
</script>

    
</html>

 
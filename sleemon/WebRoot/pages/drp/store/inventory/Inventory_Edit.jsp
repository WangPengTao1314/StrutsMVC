<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_Edit
 * @author lyg
 * @time   2013-09-07 09:54:59 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/inventory/Inventory_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	</script>
	<title>信息编辑</title>
</head>
<body class="bodycss1" onload="rangeSelect(2)">
<div style="height: 100">
		<div class="buttonlayer" id="floatDiv">
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td align="left" nowrap>
						<input id="save" type="button" class="btn" value="保存(S)"
							title="Alt+S" accesskey="S">
						<input type="button" class="btn" value="返回(B)" title="Alt+B"
							accesskey="B" onclick='parent.$("#label_1").click();'>
					</td>
				</tr>
			</table>
		</div>
       <table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
       	<tr>
					<!--占位用行，以免显示数据被浮动层挡住-->
					<td height="20px">
						&nbsp;
					</td>
				</tr>
		<tr>
			<td class="detail2">
				<form name="form" id="mainForm">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                    	<input type="hidden" name="selectParams" id="selectParams" value="">
                    	<input type="hidden" name="selectPrd" id="selectPrd" value="">
                    	<input type="hidden" id="range" value="${rst.INV_RANGE}"/>
                    	<input type="hidden" id="BMXXID" value="${BMXXID}"/>
                    	<input type="hidden" id="LEDGER_ID" value="${JGXXID}"/>
                    	<input type="hidden" id="TERM_ID" value="${TERM_ID}"/>
                    	<input type="hidden" id="ZTXXID" value="${ZTXXID}"/>
                    	<input type="hidden" id="TERM_CHARGE" value="${TERM_CHARGE}"/>
                    	<!-- //--0156477--start--刘曰刚--2014-06-17  -->
                    	<!-- 监盘人查询过滤增加人员级别过滤，不能选取门店人员-->
                    	<input type="hidden" name="selectParam" value="RYZT ='启用' and JGXXID='${JGXXID}' and RYLB!='门店' ">
                    	<!-- //--0156477--End--刘曰刚--2014-06-17  -->
               	<tr>
                   <td width="15%" align="right" class="detail_label">盘点单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRD_INV_NO" name="PRD_INV_NO" autocheck="true" label="盘点单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"
                     	<c:if test="${rst.PRD_INV_NO==null}">value="自动生成"</c:if> 
                     	<c:if test="${rst.PRD_INV_NO!=null}">value="${rst.PRD_INV_NO}"</c:if> 
                      /> 
				   </td>
				   
					<td width="15%" align="right" class="detail_label">盘点类型：</td>
					<td width="35%" class="detail_content">
                         <select json="INV_TYPE" id="INV_TYPE"  name="INV_TYPE" inputtype="string" style="width:155px"  mustinput="true"  ></select>
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">库房编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="STORE_ID" id="STORE_ID" name="STORE_ID" autocheck="true" label="库房ID" type="hidden" inputtype="string"   value="${rst.STORE_ID}" /> 
                     <input json="STORE_NO" name="STORE_NO" autocheck="true" label="库房编号"  type="text"   inputtype="string"   readonly    mustinput="true"    maxlength="30"  value="${rst.STORE_NO}"/>
                     <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="storeSelCommon()"> 
				   </td>
				    <td width="15%" align="right" class="detail_label">库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="STORE_NAME" name="STORE_NAME" autocheck="true" label="库房名称"  type="text"   inputtype="string"   readonly    mustinput="true"      maxlength="100"  value="${rst.STORE_NAME}"/>
				   </td>
               </tr>
               <tr>
<!--                   <td width="15%" align="right" class="detail_label">库位管理标记：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input id="STORAGE_FLAG" json="STORAGE_FLAG"  name="STORAGE_FLAG" type="hidden" value="${rst.STORAGE_FLAG}" onpropertychange="acquireTab()">-->
<!--                     <input id="STORAGE_FLAG_YES" name="STORAGE_FLAG_TAB"  type="radio"  disabled="disabled" <c:if test="${rst.STORAGE_FLAG==1 }">checked="checked"</c:if> />是-->
<!--                     <input id="STORAGE_FLAG_NO" name="STORAGE_FLAG_TAB"  type="radio" disabled="disabled" <c:if test="${rst.STORAGE_FLAG==0 }">checked="checked"</c:if> />否-->
<!--					  0宽度input标签，用来显示单选按钮后的必选星号 -->
<!--					 <input mustinput="true" inputtype="string" name="tab"  style="width: 0px;"  type="text" >-->
<!--				   </td>-->
				   <td width="15%" align="right" class="detail_label">盘点范围：</td>
					<td width="35%" class="detail_content">
                         <select json="INV_RANGE" id="INV_RANGE"  name="INV_RANGE" style="width:155px"  inputtype="string" mustinput="true" onchange="rangeSelect()" ></select>
					</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
<!--               <tr id="STORG_tr">-->
<!--                   <td width="15%" align="right" class="detail_label">库位编号：</td>-->
<!--				   <td width="35%" class="detail_content" >-->
<!--				   	 <input json="STORG_ID" name="STORG_ID" id="STORG_ID" autocheck="true" label="库位信息ID" type="hidden" inputtype="string"   value="${rst.STORG_ID}"/> -->
<!--                     <input json="STORG_NO" name="STORG_NO" id="STORG_NO" autocheck="true" label="库位编号"  type="text"   inputtype="string"   readonly    mustinput="true"    value="${rst.STORG_NO}"/>-->
<!--                     <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="storgSelCommon()">-->
<!--				   </td>-->
<!--				   <td width="15%" align="right" class="detail_label">库位名称：</td>-->
<!--				   <td width="35%" class="detail_content" >-->
<!--                     <input json="STORG_NAME"  name="STORG_NAME" id="STORG_NAME" autocheck="true" label="库位名称"  type="text"   inputtype="string"   readonly    mustinput="true"    value="${rst.STORG_NAME}"/> -->
<!--				   </td>-->
<!--               </tr>-->
               <tr id="PRD_NO_tr">
               		<td width="15%" align="right" class="detail_label">货品编号：</td>
				   <td width="60%" class="detail_content" colspan="3">
				   	  <input json="PRD_ID" id="PRD_ID" name="PRD_ID" autocheck="true" label="货品ID" type="hidden" inputtype="string"   value="${rst.PRD_ID}"/> 
                     <input json="PRD_NO" name="PRD_NO" id="PRD_NO" style="width: 81%" autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="1000"  value="${rst.PRD_NO}"/>
                      <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="prdSelCommon()">
				   </td>
               </tr>
               <tr id="PRD_NAME_tr">
				   <td width="15%" align="right" class="detail_label">货品名称：</td>
				   <td width="60%" class="detail_content" colspan="3">
                     <input json="PRD_NAME" name="PRD_NAME" id="PRD_NAME" style="width: 81%" autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="1000"  value="${rst.PRD_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">监盘人编号：</td>
				   <td width="35%" class="detail_content" >
				   	 <input json="INV_PSON_ID" name="INV_PSON_ID" id="INV_PSON_ID" autocheck="true" label="监盘人ID" type="hidden" inputtype="string"   value="${rst.INV_PSON_ID}"/> 
                     <input json="INV_PSON_NO" name="INV_PSON_NO" id="INV_PSON_NO" autocheck="true" label="监盘人编号"  type="text"   inputtype="string"   readonly    mustinput="true"    value="${rst.INV_PSON_NO}"/>
                     <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
                     onClick="selCommon('System_0', false, 'INV_PSON_ID', 'RYXXID', 'forms[0]','INV_PSON_ID,INV_PSON_NO,INV_PSON_NAME', 'RYXXID,RYBH,XM', 'selectParam')">
				   </td>
				   <td width="15%" align="right" class="detail_label">监盘人名称：</td>
				   <td width="35%" class="detail_content" >
                     <input json="INV_PSON_NAME"  name="INV_PSON_NAME" id="INV_PSON_NAME" autocheck="true" label="监盘人名称"  type="text"   inputtype="string"   readonly    mustinput="true"    value="${rst.INV_PSON_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3">
                         <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
					</td>
               </tr>
			</table>
			</form>
		</td>
	</tr>
</table>
</div>
</body>
</html>
<script type="text/javascript">
	   SelDictShow("INV_TYPE","66","${rst.INV_TYPE}","");
	   SelDictShow("INV_RANGE","BS_33","${rst.INV_RANGE}","");
//	    function acquireTab(){
//	   		var STORAGE_FLAG=document.getElementById("STORAGE_FLAG").value;
//	   		if(STORAGE_FLAG==1){
//	   			document.getElementById("STORAGE_FLAG_YES").checked=true;
//	   			SelDictShow("INV_RANGE","BS_33","${rst.INV_RANGE}","");
//	   		}else if(STORAGE_FLAG==""||STORAGE_FLAG==null){
//	   			document.getElementById("STORAGE_FLAG_YES").checked=false;
//	   			document.getElementById("STORAGE_FLAG_NO").checked=false;
//	   			SelDictShow("INV_RANGE","BS_33","${rst.INV_RANGE}","");
//	   		}else if(STORAGE_FLAG==0){
//	   			document.getElementById("STORAGE_FLAG_NO").checked=true;
//	   			SelDictShow("INV_RANGE","BS_36","${rst.INV_RANGE}","");
//	   		}
//	 }
	 var INV_RANGE='${rst.INV_RANGE}';
	if(null!=INV_RANGE||""!=INV_RANGE){
		$("#INV_RANGE").val(INV_RANGE);
	}
</script>
 
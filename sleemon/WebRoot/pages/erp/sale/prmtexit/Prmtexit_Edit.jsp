<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtexit_Edit
 * @author chenj
 * @time   2013-10-19 16:54:28 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale//prmtexit/Prmtexit_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;促销管理&gt;&gt;促销品发放编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
    
	   <table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst" >
               <tr>
                   <td width="15%" align="right" class="detail_label">促销品发放编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRMT_GOODS_EXTD_NO" name="PRMT_GOODS_EXTD_NO" autocheck="true" label="促销品发放编号"  type="text"   inputtype="string"     mustinput="true"  
                        maxlength="30" readonly <c:if test="${isNew == true}"> value="自动生成"</c:if>
						<c:if test="${isNew == false}">value="${rst.PRMT_GOODS_EXTD_NO}"</c:if>>
				  
				   </td>
                   
                   <td width="50%" align="right" class="detail_content" colspan=2></td>
				  
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">促销方案编号：</td>
				   <td width="35%" class="detail_content">
				   
				   	  	 <input type="hidden" name="selectParams3"  value=" DEL_FLAG=0 and state='已发布' " >
					    <input json="PRMT_PLAN_ID" type="hidden"  id="PRMT_PLAN_ID" name="PRMT_PLAN_ID"  style="width:155" value="${rst.PRMT_PLAN_ID}"/>
	   					<input json="PRMT_PLAN_NO" type="text" id="PRMT_PLAN_NO" name="PRMT_PLAN_NO" readonly style="width:155"  mustinput="true" inputtype="string" value="${rst.PRMT_PLAN_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_47', false, 'PRMT_PLAN_ID', 'PRMT_PLAN_ID', 'forms[0]','PRMT_PLAN_NO,PRMT_PLAN_NAME,COUNT_DATE_BEG,COUNT_DATE_END','PRMT_PLAN_NO,PRMT_PLAN_NAME,EFFCT_DATE_BEG,EFFCT_DATE_END', 'selectParams3')">
				   
				   </td>
				   <td width="15%" align="right" class="detail_label">促销方案名称：</td>
                    <td width="35%" class="detail_content">
                     <input json="PRMT_PLAN_NAME" name="PRMT_PLAN_NAME" autocheck="true" label="促销方案名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="150"  value="${rst.PRMT_PLAN_NAME}"/> 
				   </td>
                   
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">统计日期从：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="COUNT_DATE_BEG"  id="COUNT_DATE_BEG" name="COUNT_DATE_BEG" readonly="readonly" mustinput="true" inputtype="string" onclick="SelectDate();"  style="width:155" value="${rst.COUNT_DATE_BEG }">
	   				 	<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(COUNT_DATE_BEG);"  >
				   </td>
                   <td width="15%" align="right" class="detail_label">统计日期到：</td>
				   <td width="35%" class="detail_content">
				   	 <input  json="COUNT_DATE_END" id="COUNT_DATE_END" name="COUNT_DATE_END" readonly="readonly" mustinput="true" inputtype="string" onclick="SelectDate();"  style="width:155" value="${rst.COUNT_DATE_END }">
	   				 	<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(COUNT_DATE_END);"  >
				   
				   </td>
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">渠道编号：</td>
				   <td width="35%" class="detail_content">
                    	
	   					<input type="hidden" id="selectParams"  value="" />
					    <input json="CHANN_ID" type="hidden"  id="CHANN_ID" name="CHANN_ID"  style="width:155" value="${rst.CHANN_ID}"/>
	   					<input json="CHANN_NO"  type="text" id="CHANN_NO" name="CHANN_NO"  style="width:155"  readonly  mustinput="true" inputtype="string" value="${rst.CHANN_NO}" />
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"   
							onClick="countSum();"  >  
				   </td>
                   <td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME" autocheck="true" label="渠道名称"  type="text"   inputtype="string"    readonly   mustinput="true"   maxlength="100"  value="${rst.CHANN_NAME}"/> 
				   </td>
                   
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">销售金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="SALE_AMOUNT" id="SALE_AMOUNT" json="SALE_AMOUNT" name="SALE_AMOUNT" autocheck="true" label="销售金额"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="${rst.SALE_AMOUNT}"/> 
				   </td>
				   
                   <td width="15%" align="right" class="detail_label">发放人名称：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="EXTD_PSON_ID"  type="hidden"   id="EXTD_PSON_ID" name="EXTD_PSON_ID"  style="width:155"
				   	    <c:if test="${isNew == true}"> value="${userNum}"</c:if>
						<c:if test="${isNew == false}">value="${rst.EXTD_PSON_ID}"</c:if>/>
                     <input json="EXTD_PSON_NAME" name="EXTD_PSON_NAME" autocheck="true" label="发放人名称"  type="text"   mustinput="true" inputtype="string"   readonly       maxlength="30"  
                     	<c:if test="${isNew == true}"> value="${userName}"</c:if>
						<c:if test="${isNew == false}">value="${rst.EXTD_PSON_NAME}"</c:if>/> 
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_0', false, 'EXTD_PSON_ID', 'RYXXID', 'forms[0]','EXTD_PSON_ID,EXTD_PSON_NAME','RYBH,XM', '')">
				   
				   </td>
               </tr>
			</table>
</form>
</body>
</html>
<script type="text/javascript">
 
	
	function countSum(){
		var sqlValue =  "area_id in (select area_id from erp_prmt_effct_area  where prmt_plan_id=(select prmt_plan_id from erp_prmt_plan where prmt_plan_no='" + document.getElementById("PRMT_PLAN_NO").value +"'))";
		document.getElementById("selectParams").value=sqlValue;
		var sel = selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME','CHANN_NO,CHANN_NAME', 'selectParams');
		var flg = sel[1];
		if($.trim(flg)=="true"){
			var deptId = $("#CHANN_ID").val();
			callSum(deptId);
		}
	}
	
	function callSum(deptId){
		$.ajax({
		url: "prmtexit.shtml?action=getCounSum&deptId="+deptId,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success==true){
				$("#SALE_AMOUNT").val(jsonResult.messages);
			}else{
				$("#SALE_AMOUNT").val("0");
			}
		}
	});
	}
	
</script>
 
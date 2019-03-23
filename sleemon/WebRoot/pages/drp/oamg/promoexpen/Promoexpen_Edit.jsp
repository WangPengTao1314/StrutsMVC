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
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/promoexpen/Promoexpen_Edit.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	    function getPath(){
	       var path = $("#ATT_PATH").val();
	       var array = path.split(";");
	       $("#ACTION_PATH").val(array[0]);
	       uploadFile('ACTION_PATH', 'SAMPLE_DIR', true,true,true);
	       $("#BUDGET_PATH").val(array[1]);
 	       uploadFile('BUDGET_PATH', 'SAMPLE_DIR', true,true,true);
 	       $("#AGREE_PATH").val(array[2]);
 	       uploadFile('AGREE_PATH', 'SAMPLE_DIR', true,true,true);
 	       
 	       var PRO_SCREEN = $("#PRO_SCREEN_HID").val();
 	       var PRO_SCREENs = document.getElementsByName("PRO_SCREENs");
 	       for(var i=0;i<PRO_SCREENs.length;i++){
 	          if(PRO_SCREENs[i].value==PRO_SCREEN){
 	              PRO_SCREENs[i].checked=true;
 	          }
 	       }
	    }
	    
	    function  getItems(){
	      var CHANN_NAME = $("#CHANN_NAME").val();
	      if(CHANN_NAME!=""){
		      //var params = "STATE = '启用' and DEL_FLAG='0' and QUARTER=to_char(SYSDATE,'q') and CHANN_ID IN ${CHANN_ID} and CHANN_ID='"+$("#CHANN_IDT").val()+"'";
		      //var params = "STATE = '启用' and DEL_FLAG='0' and QUARTER=to_char(SYSDATE,'q') and CHANN_NO ='"+${LEDGER_ID}+"'";
		      var params = "";
		      
		      var DRP_LEDGER = document.getElementById("DRP_LEDGER").value;
 		      if(DRP_LEDGER=="1"){
		          params = "STATE = '启用' and DEL_FLAG='0' and QUARTER=to_char(SYSDATE,'q') and CHANN_ID = '${LEDGER_ID}' ";
		      }else{
		          params = "STATE = '启用' and DEL_FLAG='0' and QUARTER=to_char(SYSDATE,'q') and CHANN_ID IN ${CHANN_ID} and CHANN_ID='"+$("#CHANN_IDT").val()+"'";
		      } 
		    
		      document.getElementById("selectParamsByItem").value = params;
		      selCommon('BS_156', false, 'BUDGET_ITEM_NAME', 'BUDGET_ITEM_NAME', 'forms[0]','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_TYPE,BUDGET_ITEM_NAME,BUDGET_QUOTA_ID,QUARTER,YEAR,RELATE_AREA_ID','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_TYPE,BUDGET_ITEM_NAME,BUDGET_QUOTA_ID,QUARTER,YEAR,RELATE_AREA_ID', 'selectParamsByItem')
	          
	          var CHANN_ID   = $("#CHANN_IDT").val();
	          var QUARTER    = $("#QUARTER").val();
	          var YEAR       = $("#YEAR").val();
	          var WAREA_ID   = $("#AREA_IDT").val();
	          var RELATE_AREA_ID = $("#RELATE_AREA_ID").val();
		      $.ajax({
				url: "promoexpen.shtml?action=loadModel",
				type:"POST",
				dataType:"text",
				data:{"CHANN_ID":CHANN_ID,"QUARTER":QUARTER,"YEAR":YEAR,"RELATE_AREA_ID":RELATE_AREA_ID,"WAREA_ID":WAREA_ID},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
					     var result = jsonResult.data;
					     if(result.CHUHUO==null){
					          result.CHUHUO = 0;
					     }
					     if(result.YUPI==null){
					          result.YUPI = 0;
					     }
					     if(result.AREAYUSUAN==null){
					          result.AREAYUSUAN=0;
					     }
					     if(result.AREAYUPI==null){
					          result.AREAYUPI = 0;
					     }
					     if(result.WAREYUSUAN==null){
					          result.WAREYUSUAN = 0;
					     }
					     if(result.WAREYUPI==null){
					          result.WAREYUPI = 0;
					     }
					     if(result.CHUHUO !=0){
						     var enable = result.YUPI/result.CHUHUO;
						     if(isNaN(enable)){
						         enable = 0;
						     }
					     }else{
					          enable = 0;
					     }
					     $("#TOTAL_REAL_AMOUNT").val(result.CHUHUO);
					     $("#TOTAL_ADVC_AMOUNT").val(result.YUPI);
					     $("#TOTAL_RATE").val(enable);
					     $("#AREA_TOTAL_BUDGET_AMOUNT").val(result.AREAYUSUAN);
					     $("#AREA_TOTAL_PRE_AMOUNT").val(result.AREAYUPI);
					     $("#AREA_TOTAL_AVALIABLE_AMOUNT").val(result.AREAYUSUAN-result.AREAYUPI);
					     $("#WARE_TOTAL_BUDGET_AMOUNT").val(result.WAREYUSUAN);
					     $("#WARE_TOTAL_PRE_AMOUNT").val(result.WAREYUPI);
					     $("#WARE_TOTAL_AVALIABLE_AMOUNT").val(result.WAREYUSUAN-result.WAREYUPI);
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			}); 
	      } else {
	          parent.showErrorMsg("请先选择加盟商名称");
	      } 
	    }
	</script>
	<title>信息编辑</title>
</head>
<body class="bodycss1" onload="getPath()">
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
			<input id="CHANN_ID" name="CHANN_ID"  type='hidden' value="${rst.CHANN_ID}">
			<input id="ZONE_ID" name="ZONE_ID" json="ZONE_ID" type='hidden' value="${rst.ZONE_ID}">
			<input id="REQ_ID" name="REQ_ID" json="REQ_ID" type='hidden' value="${rst.REQ_ID}">
			<input id="PRMT_COST_REQ_ID" name="PRMT_COST_REQ_ID" json="PRMT_COST_REQ_ID" type='hidden' value="${rst.PRMT_COST_REQ_ID}">
			<input id="AREA_ID" name="AREA_ID" json="AREA_ID" type='hidden' value="${rst.AREA_ID}">
			<input id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" json="AREA_MANAGE_ID" type='hidden' value="${rst.AREA_MANAGE_ID}">
			<input type="hidden" name="selectParams" value=" DEL_FLAG='0' and STATE='启用' and CHANN_ID in ${CHANN_ID} and CHANN_ID=">
			<input type="hidden" name="selectParamsByPrmt"  id="selectParamsByPrmt"/>
			<input type="hidden" name="selectParamsByItem"  id="selectParamsByItem" value=""/>
		    <input type="hidden" name="PRO_SCREEN_HID" id="PRO_SCREEN_HID" json="PRO_SCREEN_HID" value="${rst.PRO_SCREEN}" />
		    <input type="hidden" name="LEDGER_ID"  id="LEDGER_ID"  json="LEDGER_ID"  value="${LEDGER_ID}" />
		    <input type="hidden" name="CHANN_ID_T" id="CHANN_ID_T"  value="${CHANN_ID}" />
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				   <tr>
	                   <td width="15%" align="left" class="detail_label">申请人：</td>
					   <td width="35%" class="detail_content">
	                     <input json="REQ_NAME" name="REQ_NAME" autocheck="true" label="申请人"  type="text" readonly  inputtype="string"     mustinput="true" size="40"   value="${rst.LEDGER_NAME}" maxLength="100"/> 
					     <input type="hidden" id="ATT_PATH" name="ATT_PATH" value="${rst.ATT_PATH}"/>
					   </td>
					   <td width="15%" align="left" class="detail_label">申请日期：</td>
					   <td width="35%" class="detail_content">
	                     <input json="REQ_DATE" name="REQ_DATE" autocheck="true" label="申请日期"  type="text" readonly  inputtype="string"     mustinput="true" size="40"   value="${rst.REQ_DATE}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">加盟商名称：</td>
					   <td width="35%" class="detail_content">
					        <input id="selectParamsTt" name="selectParamsTt" type="hidden"   value="">
					        <input id="CHANN_IDT"     name="CHANN_IDT"     json="CHANN_ID"   value="${rst.CHANN_ID}"   type="hidden"/>
                            <input id="CHANN_NO"      name="CHANN_NOT"     json="CHANN_NO"   value="${rst.CHANN_NO}"   type="hidden"/> 
                            <input id="CHANN_PERSON_CON"   name="CHANN_PERSON_CON"    json="CHANN_PERSON_CON" value="${rst.CHANN_PERSON_CON}" type="hidden" />
                            <input id="CHANN_TEL"        name="CHANN_TEL"  json="CHANN_TEL" value="${rst.CHANN_TEL}" type="hidden" />
                            <input id="AREA_MANAGE_ID"   name="AREA_MANAGE_IDT"   json="AREA_MANAGE_ID"   value="${rst.AREA_MANAGE_ID}" type="hidden" />
                            <input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAMET" json="AREA_MANAGE_NAME" value="${rst.AREA_MANAGE_NAME}" type="hidden" />
                            <input json="CHANN_NAME" name="CHANN_NAMET" id="CHANN_NAME" autocheck="true" label="加盟商名称"  type="text" readonly  inputtype="string"  size="40"   maxlength="100"  value="${rst.CHANN_NAME}" mustinput="true"/> 
                            <c:if test="${module ne 'sh'}">
                            <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="getChann()"> 	
							</c:if>			
					   </td>
					   <td width="15%" align="right" class="detail_label">战区名称：</td>
					   <td width="35%" class="detail_content">
					   		<input type="hidden"    name="selectParamsT"  id="selectParamsT" value="STATE = '启用' and DEL_FLAG=0 " />
					        <input id="AREA_IDT"     name="AREA_IDT"        json="AREA_ID"   value="${rst.AREA_ID}"   type="hidden"/>
                            <input id="AREA_NO"     name="AREA_NOT"         json="AREA_NO"   value="${rst.AREA_NO}"  type="hidden"/> 
                            <input id="AREA_NAME"   name="AREA_NAME"        json="AREA_NAME" type="text" autocheck="true" label="战区名称"   readonly  inputtype="string" size="40"   maxlength="30"  value="${rst.AREA_NAME}"/>
					        <input id="AREA_NAME_SUB" name="AREA_NAME_SUB"  json="AREA_NAME_SUB" type="hidden" autocheck=true value="${rst.AREA_NAME_SUB}" />
					   </td> 
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">城市名称：</td>
					   <td width="35%" class="detail_content">
	                     <input <c:if test="${module eq 'sh'}">readonly</c:if> json="CITY_NAME" name="CITY_NAME" id="CITY_NAME" autocheck="true" label="城市名称"  type="text" inputtype="string"  size="40" value="${rst.CITY_NAME}" READONLY /> 
					   </td>
					   <td width="15%" align="right" class="detail_label">城市类型：</td>
					   <td width="35%" class="detail_content">
	                     <input json="CITY_LVL" name="CITY_LVL" autocheck="true" label="城市级别"  type="text" inputtype="string"  size="40"  maxlength="250"  value="${rst.CITY_LVL}" READONLY /> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">申请原因：</td>
					   <td width="35%" class="detail_content">
					     <textarea <c:if test="${module eq 'sh'}">disabled</c:if>  json="REQ_REMARK" name="REQ_REMARK" id="REQ_REMARK" label="申请原因"
								   rows="2" cols="32%" autocheck="true" inputtype="string"   maxLength="250">${rst.REQ_REMARK}</textarea>
 					   </td>
	                   <td width="15%" align="right" class="detail_label">申请编码：</td>
					   <td width="35%" class="detail_content">
 					     <input json="REQ_MAKE" name="REQ_MAKE" type="text" id="REQ_MAKE"
									autocheck="true" label="申请编码" inputtype="string" mustinput="true"
									maxlength="32" size="40"
									<c:if test="${not empty rst.REQ_MAKE}">value="${rst.REQ_MAKE}"</c:if>
									<c:if test="${empty rst.REQ_MAKE}">value="自动生成"</c:if>
									READONLY
									>
 					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">预算科目：</td>
					   <td width="35%" class="detail_content">
					     <input json="DRP_LEDGER"       name="DRP_LEDGER"       id="DRP_LEDGER"       type="hidden" value="${DRP_LEDGER}" />
					     <input json="BUDGET_QUOTA_ID"  name="BUDGET_QUOTA_ID"  id="BUDGET_QUOTA_ID"  type="hidden" value="${rst.BUDGET_QUOTA_ID}">
					     <input json="BUDGET_ITEM_ID"   name="BUDGET_ITEM_ID"   id="BUDGET_ITEM_ID"   type="hidden" value="${rst.BUDGET_ITEM_ID}"/>
   					     <input json="BUDGET_ITEM_NO"   name="BUDGET_ITEM_NO"   id="BUDGET_ITEM_NO"   type="hidden" value="${rst.BUDGET_ITEM_NO}"/>
   					     <input json="BUDGET_ITEM_TYPE" name="BUDGET_ITEM_TYPE" id="BUDGET_ITEM_TYPE" type="hidden" value="${rst.BUDGET_ITEM_TYPE}" />
					     <input json="QUARTER"  name="QUARTER" id="QUARTER" type="hidden" value="${rst.QUARTER}"/>
					     <input json="YEAR"  name="YEAR" id="YEAR" type="hidden" value="${rst.YEAR}" />
					     <input json="RELATE_AREA_ID" name="RELATE_AREA_ID" id="RELATE_AREA_ID" type="hidden" value="${rst.RELATE_AREA_ID}"/>
					     <input json="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" label="预算科目"
								autocheck="true" inputtype="string" size="40"  value="${rst.BUDGET_ITEM_NAME}" readonly mustinput="true"/> 
					     <c:if test="${module ne 'sh'}">
					     <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="getItems()">
					     </c:if> 				
 					   </td>
	                   <td width="15%" align="right" class="detail_label">促销方案：</td>
					   <td width="35%" class="detail_content">
					     <input json="PRMT_PLAN_ID"   name="PRMT_PLAN_ID"   id="PRMT_PLAN_ID" type="hidden" value="${rst.PRMT_PLAN_ID}" />
					     <input json="PRMT_PLAN_NO"   name="PRMT_PLAN_NO"   id="PRMT_PLAN_NO" type="hidden" value="${rst.PRMT_PLAN_NO}" />
					     <input json="PRMT_TYPE"   name="PRMT_TYPE"   id="PRMT_TYPE" type="hidden" value="${rst.PRMT_TYPE}" />
					     <input json="PRMT_PLAN_NAME" name="PRMT_PLAN_NAME" id="PRMT_PLAN_NAME" label="促销方案"
								autocheck="true" inputtype="string" size="40"  value="${rst.PRMT_PLAN_NAME}" readonly />
						 <c:if test="${module ne 'sh'}">
						 <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_157', false, 'PRMT_PLAN_NAME', 'PRMT_PLAN_NAME', 'forms[0]','PRMT_PLAN_ID,PRMT_PLAN_NO,PRMT_TYPE,PRMT_PLAN_NAME','PRMT_PLAN_ID,PRMT_PLAN_NO,PRMT_TYPE,PRMT_PLAN_NAME', 'selectParamsByPrmt')"> 				
 					     </c:if>
 					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="3">申请事项：</td>
					   <td width="15%" align="left" class="detail_content">
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此次费用共计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					         <input <c:if test="${module eq 'sh'}">readonly</c:if> type="text" size="20" name="TOTAL_PRMT_COST" label="此次费用共计" maxlength="8" type="text" autocheck="true" mustinput="true"  json="TOTAL_PRMT_COST" id="TOTAL_PRMT_COST" value="${rst.TOTAL_PRMT_COST}"/><font color='red'>*</font>(元)
 					   </td>
					   <td width="15%" align="right" class="detail_label">预批金额：</td>
 					   <td width="35%" class="detail_content">
					     <input <c:if test="${module eq 'sh'}">readonly</c:if> json="BUDGET_AMOUNT" name="BUDGET_AMOUNT" id="BUDGET_AMOUNT" 
						        autocheck="true" label="预批金额"  type="text"  maxlength="8" mustinput="true" size="40" value="${rst.BUDGET_AMOUNT}"/><font color='red'>*</font>(元)
 					   </td>
	               </tr>
	               <tr>
					   <td width="15%" align="left" class="detail_content">
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费用类别
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					         <select <c:if test="${module eq 'sh'}">disabled</c:if> name=COST_TYPE id="COST_TYPE" json="COST_TYPE" style="width: 155px;"  label="费用类别" inputtype="string"  mustinput="true"></select> 
 					   </td>
 					   <td width="15%" align="right" class="detail_label">附活动方案：</td>
 					   <td width="35%" class="detail_content">
					     <input json="ACTION_PATH" name="ACTION_PATH" id="ACTION_PATH" label="附活动方案"
					     autocheck="true" inputtype="string"
						        type="hidden"/>
 					   </td>
	               </tr>
	               <tr> 
					   <td width="15%" align="left" class="detail_content">
					      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附费用预算
					      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					      <input id="BUDGET_PATH" json="BUDGET_PATH" name="BUDGET_PATH" label="附费用预算"
					     autocheck="true" inputtype="string"  type="hidden"   value="${rst.PIC_PATH}"/>
 					   </td>
	                   <td width="15%" align="right" class="detail_label">附合同：</td>
 					   <td width="35%" class="detail_content">
					     <input json="AGREE_PATH" name="AGREE_PATH" id="AGREE_PATH" label="附合同" autocheck="true" inputtype="string" 
						        type="hidden"/>
 					   </td>
 					 </tr>
 					 <tr>
	                   <td width="15%" align="right" class="detail_label">计划零售额：</td>
					   <td width="35%" class="detail_content">
 					   	  <input <c:if test="${module eq 'sh'}">readonly</c:if> json="RETAIL_AMOUNT" name="RETAIL_AMOUNT" id="RETAIL_AMOUNT" autocheck="true"  maxlength="8"  label="计划零售额"  type="text" size="40" value="${rst.RETAIL_AMOUNT}"/>&nbsp;&nbsp;(元) 
 					   </td>
	                   <td width="15%" align="right" class="detail_label">
	                            计划开单数
	                   </td>
					   <td width="35%" class="detail_content">
					      <input <c:if test="${module eq 'sh'}">readonly</c:if> type="text" name="BILL_AMOUNT" id="BILL_AMOUNT" json="BILL_AMOUNT" value="${rst.BILL_AMOUNT}" autocheck="true"  maxlength="10" size="40" />
 					   </td>
	                  </tr>
	                  <tr>
	                   <td width="15%" align="right" class="detail_label">是否制作VI画面：</td>
					   <td width="35%" class="detail_content" colspan="3">
					       <c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                   <input type="hidden" name="PRO_SCREEN"  id="PRO_SCREEN"  json="PRO_SCREEN" value="${rst.PRO_SCREEN}" />
							       <input type="radio" name="PRO_SCREENs" value="1" onclick="chkProScreen()" disabled>&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="PRO_SCREENs" value="0" onclick="chkProScreen()" disabled>&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;
				                </c:when>
				                <c:otherwise>
				                   <input type="hidden" name="PRO_SCREEN"  id="PRO_SCREEN"  json="PRO_SCREEN" value="${rst.PRO_SCREEN}" />
							       <input type="radio" name="PRO_SCREENs" value="1" onclick="chkProScreen()">&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="PRO_SCREENs" value="0" onclick="chkProScreen()">&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;
				                </c:otherwise>
							  </c:choose>&nbsp;&nbsp;<font color='red'>*</font>
  					   </td> 
	                  </tr>
	                   <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="2">加盟商版块：</td>
					   <td width="15%" align="left" class="detail_content">
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计实际收入
					        <input type="text" name="TOTAL_REAL_AMOUNT" id="TOTAL_REAL_AMOUNT" json="TOTAL_REAL_AMOUNT" value="${rst.TOTAL_REAL_AMOUNT}" readonly/>
 					   </td>
	                   <td width="15%" align="right" class="detail_label">
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预批费用
	                   </td>
					   <td width="35%" class="detail_content">
					        <input type="text" name="TOTAL_ADVC_AMOUNT" id="TOTAL_ADVC_AMOUNT" json="TOTAL_ADVC_AMOUNT" size="40" value="${rst.TOTAL_ADVC_AMOUNT}" readonly/>
 					   </td>
	                  </tr>
	                  <tr>
	                   <td width="15%" align="left" class="detail_content" colspan="6">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计费效比
					       &nbsp;&nbsp;&nbsp;
					       <input type="text" name="TOTAL_RATE" id="TOTAL_RATE" json="TOTAL_RATE" value="${rst.TOTAL_RATE}" readonly/>
 					   </td>
	                  </tr>
	                   <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="2">区域版块：</td>
					   <td width="15%" align="left" class="detail_content">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算费用
					       <input type="text" name="AREA_TOTAL_BUDGET_AMOUNT" json="AREA_TOTAL_BUDGET_AMOUNT" id="AREA_TOTAL_BUDGET_AMOUNT" value="${rst.AREA_TOTAL_BUDGET_AMOUNT}" value="0" readonly/>
 					   </td>
 					   <td width="15%" align="right" class="detail_label">
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预批费用
	                   </td>
					   <td width="35%" class="detail_content">
					        <input type="text" name="AREA_TOTAL_PRE_AMOUNT" id="AREA_TOTAL_PRE_AMOUNT" json="AREA_TOTAL_PRE_AMOUNT" size="40" value="${rst.AREA_TOTAL_PRE_AMOUNT}" value="0" readonly/>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="6">
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算可用
					        <input type="text" name="AREA_TOTAL_AVALIABLE_AMOUNT" json="AREA_TOTAL_AVALIABLE_AMOUNT" id="AREA_TOTAL_AVALIABLE_AMOUNT" value="${rst.AREA_TOTAL_AVALIABLE_AMOUNT}" value="0" readonly/>
 					   </td>
	                  </tr>
	                  <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="2">战区版块：</td>
					   <td width="15%" align="left" class="detail_content">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算费用
					        <input type="text" name="WARE_TOTAL_BUDGET_AMOUNT" id="WARE_TOTAL_BUDGET_AMOUNT" json="WARE_TOTAL_BUDGET_AMOUNT" value="${rst.WARE_TOTAL_BUDGET_AMOUNT}" readonly/>
 					   </td>
 					   <td width="15%" align="right" class="detail_label">
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预批费用
	                   </td>
					   <td width="35%" class="detail_content">
					        <input type="text" name="WARE_TOTAL_PRE_AMOUNT" id="WARE_TOTAL_PRE_AMOUNT" json="WARE_TOTAL_PRE_AMOUNT" size="40" value="${rst.WARE_TOTAL_PRE_AMOUNT}" readonly/>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="6">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算可用
					       <input type="text" name="WARE_TOTAL_AVALIABLE_AMOUNT" json="WARE_TOTAL_AVALIABLE_AMOUNT" id="WARE_TOTAL_AVALIABLE_AMOUNT" value="${rst.WARE_TOTAL_AVALIABLE_AMOUNT}" readonly/>
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
    var COST_TYPE='${rst.COST_TYPE}';
	if(""==COST_TYPE||null==COST_TYPE){
		COST_TYPE="战区费用";
	}
	SelDictShow("COST_TYPE","BS_92",COST_TYPE,"");
</script>

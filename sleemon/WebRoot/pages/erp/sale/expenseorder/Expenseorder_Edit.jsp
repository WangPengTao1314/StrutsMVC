<!--
/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/expenseorder/Expenseorder_Edit.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	     function load(){
	       var path   = $("#ATT_PATH").val();
	       if (typeof(path) != "undefined") { 
		       var array = path.split(";");
		       $("#EXPENSE_ATT").val(array[0]);
			   uploadFile('EXPENSE_ATT', 'SAMPLE_DIR', true,true,true);
			   $("#EXPENSE_ATT_PIC").val(array[1]);
			   uploadFile('EXPENSE_ATT_PIC','SAMPLE_DIR',true,true,true);
		   }
		   
		   var CHANN_ID = $("#CHANN_ID").val();
		   if(CHANN_ID !=""){
		    $.ajax({
					url: "expenseorder.shtml?action=loadModelT",
					type:"POST",
					dataType:"text",
					data:{"CHANN_ID":CHANN_ID},
					complete: function(xhr){
						eval("jsonResult = "+xhr.responseText);
						if(jsonResult.success===true){
						     var result = jsonResult.data;
						     if(typeof(result.DECT_AMOUNT) == "undefined"){
						        $("#YEAR_GOODS_AMOUNT").val("0");
						     }else{
						        $("#YEAR_GOODS_AMOUNT").val(result.DECT_AMOUNT);
						     }
						     $("#YEAR_CHANN_EXPENSE_AMOUNT").val(result.EXPENSE_AMOUNT);
						}else{
							parent.showErrorMsg(utf8Decode(jsonResult.messages));
						}
					}
				});
		   }
		   
		        /*
		       $("#EXPENSE_ATT").val(array[0]);
		     
		       if(module=="sh"){
		          var path = $("#EXPENSE_ATT").val();
			      var leg = path.split(",").length ;
			      var folder = "";
			      if(leg>1){
		    	  for(var i=0;i<leg;i++){
			    	folder = folder+"SAMPLE_DIR,";
			      }
			      folder = folder.substring(0,folder.length-1);
			      }else{
			    	folder = "SAMPLE_DIR";
			      }
		       }else{
		          uploadFile('EXPENSE_ATT', 'SAMPLE_DIR', true,true,true);
		       }*/
		       /*
		       $("#EXPENSE_ATT_PIC").val(array[1]);
		       if(module=="sh"){
		          var path = $("#EXPENSE_ATT_PIC").val();
			      var leg = path.split(",").length ;
			      var folder = "";
			      if(leg>1){
		    	  for(var i=0;i<leg;i++){
			    	folder = folder+"SAMPLE_DIR,";
			      }
			      folder = folder.substring(0,folder.length-1);
			      }else{
			    	folder = "SAMPLE_DIR";
			      }
		       } else{
		         uploadFile('EXPENSE_ATT_PIC', 'SAMPLE_DIR', true,true,true);
		       } */
	     }
	</script>
	<title>信息编辑</title>
</head>
<body class="bodycss1" onload="load()">
<table width="100%" cellspacing="0" cellpadding="0"  >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：营销管理>>推广费报销管理>>推广费用报销单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
     <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and DEL_FLAG=0 ">
	 <input type="hidden" name=selectDeptParams id="selectDeptParams" value=" BMZT='启用' and JGXXID='00'">
	 <input type="hidden" name="DRP_LEDGER" id="DRP_LEDGER" json="DRP_LEDGER" value="${DRP_LEDGER}" />
     <input type="hidden" name="CHANN_ID_T" id="CHANN_ID_T" json="CHANN_ID_T" value="${CHANN_ID}" />
     <input type="hidden" name="LEDGER_ID"  id="LEDGER_ID"  json="LEDGER_ID"  value="${LEDGER_ID}"/>
     <input type="hidden" name="selectRYXXParams" id="selectRYXXParams" value="" />
     <input type="hidden" name="selectReqParams" id="selectReqParams" value="" />
	 <!--   
	 <input type="hidden" name="selectRYXXParams" id="selectRYXXParams" value="">
	 <c:if test="${empty rst.RELATE_ORDER_NOS}">
	    <input type="hidden" name="selectReqParams" id="selectReqParams" 
	   value=" temp.CHANN_NO='${LEDGER_ID}' and temp.ORG_ID = '${LEDGER_ID}'"/> 
	 </c:if>
	 <c:if test="${not empty rst.RELATE_ORDER_NOS}">
	     <input type="hidden" name="selectReqParams" id="selectReqParams" 
	 value=" temp.CHANN_NO='${LEDGER_ID}' and temp.ORG_ID = '${LEDGER_ID}' "/> 
	 </c:if>
	 -->
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail" >
        <tr>
      
        </tr>
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                    <td width="18%" align="right" class="detail_label">报销单编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="EXPENSE_ORDER_NO" name="EXPENSE_ORDER_NO" type="text"
							autocheck="true" label="报销单编号" inputtype="string" mustinput="true"
							maxlength="35" size="35"
							<c:if test="${not empty rst.EXPENSE_ORDER_NO}">value="${rst.EXPENSE_ORDER_NO}"</c:if>
							<c:if test="${empty rst.EXPENSE_ORDER_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
                   <td width="15%" align="right" class="detail_label">报销单类别：</td>
				   <td width="35%" class="detail_content" >
                     <select json="EXPENSE_TYPE" name="EXPENSE_TYPE" id="EXPENSE_TYPE"   style="width:245px;" label="报销单类别"> </select>
                      <SPAN class="validate">&nbsp;*</SPAN> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">申请单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="RELATE_ORDER_NOS" name="RELATE_ORDER_NOS" id="RELATE_ORDER_NOS"  label="申请单编号" rows="4" size="35"
                     maxlength="300" readonly value="${rst.RELATE_ORDER_NOS}" autocheck="true" mustinput="true" inputtype="string"/> 
                     <img align="absmiddle" name="selJGXX" style="cursor: hand"
						 src="${ctx}/styles/${theme}/images/plus/select.gif"
						 onClick="getPrommexpen()">
				   </td>
                   <td width="18%" align="right" class="detail_label">申请金额：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="PRMT_COST_REQ_ID"  id="PRMT_COST_REQ_ID"  json="PRMT_COST_REQ_ID" value="" value="${rst.PRMT_COST_REQ_ID}"/>
				    <input type="hidden" name="REIT_REQ_AMOUNT"   id="REIT_REQ_AMOUNT"   json="REIT_REQ_AMOUNT"  value="" value="${rst.REIT_REQ_AMOUNT}"/>
				    <input type="text"   name="RELATE_AMOUNT_REQ" id="RELATE_AMOUNT_REQ" json="RELATE_AMOUNT_REQ" readonly  inputtype="float" valuetype="8,2" autocheck="true"  label="申请金额"  value="${rst.RELATE_AMOUNT_REQ}" size="35"/>
				   </td>
                </tr>
                
                <tr>
                   <td width="18%" align="right" class="detail_label">预算科目编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" id="BUDGET_ITEM_ID" name="BUDGET_ITEM_ID" json="BUDGET_ITEM_ID" value="${rst.BUDGET_ITEM_ID}" />
				     <input type="hidden" id="BUDGET_QUOTA_ID" name="BUDGET_QUOTA_ID" json="BUDGET_QUOTA_ID" value="${rst.BUDGET_QUOTA_ID}" />
				     <input type="hidden" id="BUDGET_QUOTA_ID_OLD"   value="${rst.BUDGET_QUOTA_ID}" />
				     
                     <input json="BUDGET_ITEM_NO" name="BUDGET_ITEM_NO" id="BUDGET_ITEM_NO" label="预算科目编号" readonly mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.BUDGET_ITEM_NO}" size="35"/>
                     <%-- 
                  	 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('BS_165', false, 'BUDGET_QUOTA_ID', 'BUDGET_QUOTA_ID', 'forms[0]','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,EXPENSE_DEPT_ID,EXPENSE_DEPT_NO,EXPENSE_DEPT_NAME,YEAR,QUARTER,MONTH', 'BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,BUDGET_DEPT_ID,BUDGET_DEPT_NO,BUDGET_DEPT_NAME,YEAR,QUARTER,MONTH', 'selectParams');changeReqParams();">
									
				   --%>
				   </td>
                   <td width="15%" align="right" class="detail_label">预算科目名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME"  readonly label="预算科目名称"  type="text" mustinput="true" inputtype="string" autocheck="true"    value="${rst.BUDGET_ITEM_NAME}" size="35"/> 
				   </td>
                </tr>
           
                <tr>
                  <td width="15%" align="right" class="detail_label">预算科目类型：</td>
				   <td width="35%" class="detail_content" >
                     <span onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();">
					   <%--<select id="BUDGET_ITEM_TYPE" json="BUDGET_ITEM_TYPE" style="width:155px;background-color: rgb(238, 238, 255);" label="预算科目类型" readonly  ></select>
                      <SPAN class=validate>&nbsp;*</SPAN> 
                      --%>
                      <input json="BUDGET_ITEM_TYPE" name="BUDGET_ITEM_TYPE" id="BUDGET_ITEM_TYPE"  readonly label="预算科目类型"  type="text"   value="${rst.BUDGET_ITEM_TYPE}" size="35"/> 
                     </span>
				   </td>
                   <td width="15%" align="right" class="detail_label" nowrap> 年份： </td>
					<td width="35%" class="detail_content"><%--
						<select json="YEAR" name="YEAR" id="YEAR" style="width:155px;"  label="年份"  ></select>
						 <SPAN class="validate">&nbsp;*</SPAN> 
						--%>
						<input json="YEAR" name="YEAR" id="YEAR"  readonly label="年份"  type="text" mustinput="true" inputtype="string" autocheck="true"  size="35"  value="${rst.YEAR}"/> 
						
					</td>	
                </tr>
               <tr>	
				   										
					<td width="15%" align="right" class="detail_label"> 季度： </td>
					<td width="35%" class="detail_content"><%--
						<select id="QUARTER" json="QUARTER" style="width:155px;"  name="QUARTER"  label="季度"   ></select>
						--%>
						<input json="QUARTER" name="QUARTER" id="QUARTER"  readonly label="季度"  type="text" size="35"  value="${rst.QUARTER}"/> 
					</td>
					 <td width="15%" align="right" class="detail_label" nowrap> 月份： </td>
					<td width="35%" class="detail_content">
					 <input json="MONTH" name="MONTH" id="MONTH"  readonly label="月份"  type="text" size="35"  value="${rst.MONTH}"/> 
					</td>	
				</tr>
			   <tr>	
			  	   <td width="18%" align="right" class="detail_label">报销人：</td>
				   <td width="35%" class="detail_content">
				    <input  type="hidden"  name="EXPENSE_PSON_ID" id="EXPENSE_PSON_ID" json="EXPENSE_PSON_ID" value="${rst.EXPENSE_PSON_ID}"/>
                    <c:if test="${DRP_LEDGER eq 1}">
                       <input json="EXPENSE_PSON_NAME" name="EXPENSE_PSON_NAME" id="EXPENSE_PSON_NAME" label="报销人姓名" inputtype="string" autocheck="true"  type="text" value="${rst.EXPENSE_PSON_NAME}" size="35" READONLY/> 
                    </c:if>
                    <c:if test="${DRP_LEDGER eq 0}">
                      <input json="EXPENSE_PSON_NAME" name="EXPENSE_PSON_NAME" id="EXPENSE_PSON_NAME" label="报销人姓名" inputtype="string" autocheck="true"  type="text" value="${rst.EXPENSE_PSON_NAME}" size="35" READONLY/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
						 src="${ctx}/styles/${theme}/images/plus/select.gif"
						 onClick="selCommon('System_10', false, 'EXPENSE_PSON_NAME', 'XM', 'forms[0]','EXPENSE_PSON_ID,EXPENSE_PSON_NAME', 'RYXXID,XM', 'selectRYXXParams')">
				    </c:if>    
				   </td>
				    <td width="18%" align="right" class="detail_label">报销日期：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="EXPENSE_DATE" name="EXPENSE_DATE" READONLY json="EXPENSE_DATE" onclick="SelectDate();" label="报销日期" mustinput="true" inputtype="string" autocheck="true" size="35" value="${rst.EXPENSE_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(EXPENSE_DATE);">
				   </td>									
			   </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">报销部门编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="EXPENSE_DEPT_ID" name="EXPENSE_DEPT_ID" id="EXPENSE_DEPT_ID"   type="hidden"   value="${rst.EXPENSE_DEPT_ID}"/> 
                     <input json="EXPENSE_DEPT_NO" name="EXPENSE_DEPT_NO" id="EXPENSE_DEPT_NO" readonly label="报销部门编号"  type="text" mustinput="true" inputtype="string" autocheck="true"  size="35"  value="${rst.EXPENSE_DEPT_NO}"/> 
                  	 <%--<img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('1', false, 'EXPENSE_DEPT_ID', 'BMXXID', 'forms[0]','EXPENSE_DEPT_NO,EXPENSE_DEPT_NAME', 'BMBH,BMMC', 'selectDeptParams')">
									
				   --%>
				   </td>
                   <td width="18%" align="right" class="detail_label">报销部门名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="EXPENSE_DEPT_NAME" name="EXPENSE_DEPT_NAME" id="EXPENSE_DEPT_NAME"  readonly label="报销部门名称" mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.EXPENSE_DEPT_NAME}" size="35"/> 
				   </td>
                </tr>
                <tr>
                
                	<td width="15%" align="right" class="detail_label"> 报销金额： </td>
					<td width="35%" class="detail_content">
					  <input id="EXPENSE_AMOUNT" json="EXPENSE_AMOUNT"   name="EXPENSE_AMOUNT"  readonly inputtype="float" valuetype="8,2" autocheck="true"
					  label="报销金额"  value="${rst.EXPENSE_AMOUNT}" size="35"/>
					</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content"></td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">渠道编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_ID"   name="CHANN_ID"   id="CHANN_ID"   type="hidden" value="${rst.CHANN_ID}" size="35"/>
                     <input json="CHANN_NO"   name="CHANN_NO"   id="CHANN_NO"   type="text" value="${rst.CHANN_NO}" READONLY  size="35"/>
				   </td>
                   <td width="18%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
				       <input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" value="${rst.CHANN_NAME}" READONLY size="35"/>
				   </td>
                </tr>
                    
                  <tr>
                   <td width="15%" align="right" class="detail_label">本年渠道出货(万元)：</td>
				   <td width="35%" class="detail_content">
                      <input json="YEAR_GOODS_AMOUNT"   name="YEAR_GOODS_AMOUNT"   id="YEAR_GOODS_AMOUNT"   type="text" value="${rst.YEAR_GOODS_AMOUNT}" READONLY  size="35"/>
				   </td>
                   <td width="18%" align="right" class="detail_label">本年渠道已申报推广(万元)：</td>
				   <td width="35%" class="detail_content">
				       <input json="YEAR_CHANN_EXPENSE_AMOUNT" name="YEAR_CHANN_EXPENSE_AMOUNT" id="YEAR_CHANN_EXPENSE_AMOUNT" type="text" value="${rst.YEAR_CHANN_EXPENSE_AMOUNT}" READONLY size="35"/>
				   </td>
                </tr>
                
                <tr>
				   <td width="18%" align="right" class="detail_label">附件：</td>
				   <td width="35%" class="detail_content"  >
				         <input type="hidden" name="ATT_PATH" id="ATT_PATH" json="ATT_PATH" value="${rst.ATT_PATH}" />
				    	 <input  json="EXPENSE_ATT" id="EXPENSE_ATT" name="EXPENSE_ATT"  autocheck="true" label="附件"   type="text"   inputtype="string"  maxlength="250"  value="${rst.EXPENSE_ATT}"/>
				   </td>
				   <td width="18%" align="right" class="detail_label">效果图：</td>
				   <td width="35%" class="detail_content"  >
				    	 <input  json="EXPENSE_ATT_PIC" id="EXPENSE_ATT_PIC" name="EXPENSE_ATT_PIC"  autocheck="true" label="效果图"   type="text"   inputtype="string"  maxlength="250"  value="${rst.EXPENSE_ATT_PIC}"/>
				   </td>
              </tr>
              <tr> 
                  <td width="18%" align="right" class="detail_label">备注：</td>
				  <td width="35%" class="detail_content"  colspan="3">
				     <textarea  json="REMARK" name="REMARK" id="REMARK"  inputtype="string" autocheck="true" 
				      maxlength="250"   label="备注"    rows="4" cols="40%" >${rst.REMARK}</textarea>
				  </td>
              </tr>
			</table>
		</td>
	</tr>
	<tr>  </tr>
</table>
</form>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
</body>
<script type="text/javascript">
    SelDictShow_Default("EXPENSE_TYPE","BS_150","${rst.EXPENSE_TYPE}","推广费");
    //SelDictShow("BUDGET_ITEM_TYPE", "BS_145", '${rst.BUDGET_ITEM_TYPE}', "");
	//SelDictShow("YEAR", "89", '${rst.YEAR}', "");
	//SelDictShow("MONTH", "168", '${rst.MONTH}', "");
	//SelDictShow("QUARTER", "BS_148", '${rst.QUARTER}', "");
</script>
</html>

 
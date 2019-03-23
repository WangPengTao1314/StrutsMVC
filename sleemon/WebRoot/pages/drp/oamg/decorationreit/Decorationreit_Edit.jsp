<!--  
/**
 *@module 渠道管理-装修管理
 *@fuc    装修报销申请单维护
 *@version 1.0
 *@author zzb
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/decorationreit/Decorationreit_Edit.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	   .inputStyle {
	        color : #000;
	    }
	    
	   .textReadonly{
	        color: #555;
			background-color: #eef;
			border: 1 solid #DFDFDF;
			padding: 2px;
	   }
	</style>
	<script type="text/javascript">
	    function  sel(){
 	       selCommon('BS_171', false, 'CHANN_RNVTN_NO', 'CHANN_RNVTN_NO', 'forms[0]','ADDRESS,REIT_AMOUNT_PSt,CHANN_RNVTN_ID,CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,USE_AREA,BUSS_SCOPET,LOCAL_TYPET,RNVTN_PROP,PLAN_SBUSS_DATE,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,SALE_STORE_ID,SALE_STORE_NAMET', 'ADDRESS,REIT_AMOUNT_PS,CHANN_RNVTN_ID,CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,USE_AREA,BUSS_SCOPE,LOCAL_TYPE,RNVTN_PROP,PLAN_SBUSS_DATE,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,SALE_STORE_ID,SALE_STORE_NAME', 'selectParams');
	       var LOCAL_TYPET = $("#LOCAL_TYPET").val();
	       var LOCAL_TYPE = document.getElementsByName("LOCAL_TYPEs");
	       for(var i=0;i<LOCAL_TYPE.length;i++){
	          if(LOCAL_TYPE[i].value==LOCAL_TYPET)
	          { 
	             $("#LOCAL_TYPE").val(LOCAL_TYPET);
	             LOCAL_TYPE[i].checked=true;
	          }
	       }
	       var BUSS_SCOPET = $("#BUSS_SCOPET").val();
	       var BUSS_SCOPE  = document.getElementsByName("BUSS_SCOPEs");
	       for(var j=0;j<BUSS_SCOPE.length;j++){
	          if(BUSS_SCOPE[j].value==BUSS_SCOPET){
	              $("#BUSS_SCOPE").val(BUSS_SCOPET);
	              BUSS_SCOPE[j].checked=true;
	          }
	       }
	       
	       var RNVTN_PROP = $("#RNVTN_PROP").val();
	       var RNVTN_PROPs  = document.getElementsByName("RNVTN_PROPs");
	       for(var j=0;j<RNVTN_PROPs.length;j++){
	          if(RNVTN_PROPs[j].value==RNVTN_PROP){
	              RNVTN_PROPs[j].checked=true;
	          }
	       }
	       
	       var PLAN_SBUSS_DATE = $("#PLAN_SBUSS_DATE").val();
	       var strPlanDate = PLAN_SBUSS_DATE.split("-");
	       $("#OPEN_SALE_YEAR").val(strPlanDate[0]);
	       var AMOUNT = $("#REIT_AMOUNT_PS").val();
	       var USE_AREA    = $("#USE_AREA").val();
	       //$("#REIT_AMOUNT").val(AMOUNT*USE_AREA);
	       $("#TOTAL_REIT_AMOUNT").val(AMOUNT*USE_AREA)
	       
	       var CHANN_ID = $("#CHANN_ID").val();
	       var AREA_ID  = $("#ARAE_ID").val();
	       var AREA_NAME= $("#AREA_NAME").val();
	       $.ajax({
				url: "decorationreit.shtml?action=loadModelT",
				type:"POST",
				dataType:"text",
				data:{"CHANN_ID":CHANN_ID,"AREA_ID":AREA_ID,"AREA_NAME":AREA_NAME},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
					     var result = jsonResult.data;
					     $("#YEAR_GOODS_AMOUNT").val(result.DECT_AMOUNT);
				         /*
				         if(typeof(result.FNSH_RATE) !="undefined"){
					       $("#QUARTER_RATE").val(result.FNSH_RATE*100+'%');
					     }*/
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});
	    }
	    
	    function  load(){
	      var count = $("#count").val();
	      if(count==0){
	         document.getElementById("showTd").colSpan="3";
	      }
	      var BUSS_SCOPE = $("#BUSS_SCOPE").val();
	      if(BUSS_SCOPE !=""){
	          var BUSS_SCOPES = BUSS_SCOPE.split(",");
	          var BUSS_SCOPEs= document.getElementsByName("BUSS_SCOPEs");
		      for(var i=0;i<BUSS_SCOPEs.length;i++){
	            for(var j=0;j<BUSS_SCOPES.length;j++){
	              if(BUSS_SCOPEs[i].value==BUSS_SCOPES[j]){
	              BUSS_SCOPEs[i].checked=true;
	           }
	            }
	         }
	      }
	      var LOCAL_TYPE = $("#LOCAL_TYPE").val();
	      if(LOCAL_TYPE !=""){
	          var LOCAL_TYPES = LOCAL_TYPE.split(",");
              var LOCAL_TYPEs= document.getElementsByName("LOCAL_TYPEs");
	          for(var i=0;i<LOCAL_TYPEs.length;i++){
	            for(var j=0;j<LOCAL_TYPES.length;j++){
		            if(LOCAL_TYPEs[i].value==LOCAL_TYPES[j]){
		                 LOCAL_TYPEs[i].checked=true;
		            }
	            }
             } 
 	      }
	      
	      var RNVTN_PROP = $("#RNVTN_PROP").val();
	      if(RNVTN_PROP !=""){
		      var RNVTN_PROPs= document.getElementsByName("RNVTN_PROPs");
		      for(var i=0;i<RNVTN_PROPs.length;i++){
		         if(RNVTN_PROPs[i].value==RNVTN_PROP){
		           RNVTN_PROPs[i].checked=true;
		         }
		      }
	      }
	      
	      var IS_NORTH = $("#IS_NORTH").val();
	      var IS_NORTHs= document.getElementsByName("IS_NORTHs");
	      if(IS_NORTH !=""){
		      for(var i=0;i<IS_NORTHs.length;i++){
		          if(IS_NORTHs[i].value==IS_NORTH){
		             IS_NORTHs[i].checked=true;
		          }
		      }
	      }
	      
	      var TOTAL_REITED_NUM = $("#TOTAL_REITED_NUM").val();
	      var TOTAL_REITED_NUMs= document.getElementsByName("TOTAL_REITED_NUMs");
	      if(TOTAL_REITED_NUM !=""){
		      for(var i=0;i<TOTAL_REITED_NUMs.length;i++){
		          if(TOTAL_REITED_NUMs[i].value==TOTAL_REITED_NUM){
		             TOTAL_REITED_NUMs[i].checked=true;
		          }
		      }
	      }
	      
	      var REITED_NUM = $("#REITED_NUM").val();
	      var REITED_NUMs= document.getElementsByName("REITED_NUMs");
	      if(REITED_NUM !=""){
		      for(var i=0;i<REITED_NUMs.length;i++){
		          if(REITED_NUMs[i].value==REITED_NUM){
		             REITED_NUMs[i].checked=true;
		          }
		       }
	      }
	       var counts = $("#counts").val();
	       var path   = $("#ATT_PATH").val();
	       var module = $("#module").val();
	       if (typeof(path) != "undefined") { 
		       var array = path.split(";");
		       $("#PIC").val(array[0]);
		       if(module=="sh"){
		          var path = $("#PIC").val();
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
			      if(counts==1){
			         uploadFile('PIC', 'SAMPLE_DIR', true,true,true);
			      }else{
		             displayDownFile('PIC',folder,false,false);
		          }
		       }else{
		          uploadFile('PIC', 'SAMPLE_DIR', true,true,true);
		       }
		       $("#ZHUANGXIUSQ").val(array[1]);
		       if(module=="sh"){
		          var path = $("#ZHUANGXIUSQ").val();
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
			      if(counts==1){
			         uploadFile('ZHUANGXIUSQ', 'SAMPLE_DIR', true,true,true);
			      }else{
		             displayDownFile('ZHUANGXIUSQ',folder,false,false);
		          }
		       } else{
		         uploadFile('ZHUANGXIUSQ', 'SAMPLE_DIR', true,true,true);
		       }
		       $("#YBXIANG").val(array[2]);
		       if(module=="sh"){
		          var path = $("#YBXIANG").val();
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
			      if(counts==1){
			        uploadFile('YBXIANG', 'SAMPLE_DIR', true,true,true);
			      }else{
		            displayDownFile('YBXIANG',folder,false,false);
		          }
		       } else{
		         uploadFile('YBXIANG', 'SAMPLE_DIR', true,true,true);
		       }
		       
		       $("#MCYSTAB").val(array[3]);
		       if(module=="sh"){
		          var path = $("#MCYSTAB").val();
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
			      if(counts==1){
			         uploadFile('MCYSTAB', 'SAMPLE_DIR', true,true,true);
			      }else{
		             displayDownFile('MCYSTAB',folder,false,false);
		          }
		       } else{
		         uploadFile('MCYSTAB', 'SAMPLE_DIR', true,true,true);
		       }
		       
		       $("#ZGYANSHOU").val(array[4]);
		       if(module=="sh"){
		          var path = $("#ZGYANSHOU").val();
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
			      if(counts==1){
			        uploadFile('ZGYANSHOU', 'SAMPLE_DIR', true,true,true);
			      }else{
			        displayDownFile('ZGYANSHOU',folder,false,false);
			      }
		       } else{
		          uploadFile('ZGYANSHOU', 'SAMPLE_DIR', true,true,true);
		       }
		     
		       $("#ZXPIC").val(array[5]);
		       if(module=="sh"){
		          var path = $("#ZXPIC").val();
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
			      if(counts==1){
			        uploadFile('ZXPIC', 'SAMPLE_DIR', true,true,true);
			      }else{
		            displayDownFile('ZXPIC',folder,false,false);
		          }
		       } else{
		          uploadFile('ZXPIC', 'SAMPLE_DIR', true,true,true);
		       }
		       
		       $("#ZXFAPIAO").val(array[6]);
		       if(module=="sh"){
		          var path = $("#ZXFAPIAO").val();
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
			      if(counts==1){
			        uploadFile('ZXFAPIAO', 'SAMPLE_DIR', true,true,true);
		          }else{
		             displayDownFile('ZXFAPIAO',folder,false,false);
		          }
		       } else{
		          uploadFile('ZXFAPIAO', 'SAMPLE_DIR', true,true,true);
		       }
		       
		       $("#OLD_OA_ORDER_PIC").val(array[7]);
		       if(module=="sh"){
		          var path = $("#OLD_OA_ORDER_PIC").val();
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
			      if(counts==1){
			         uploadFile('OLD_OA_ORDER_PIC', 'SAMPLE_DIR', true,true,true);
			      }else{
		             displayDownFile('OLD_OA_ORDER_PIC',folder,false,false);
		          }
		       }else{
		          uploadFile('OLD_OA_ORDER_PIC', 'SAMPLE_DIR', true,true,true);
		       }
		     }
	    }
 
		  function  getItems(){
		      var CHANN_NAME = $("#CHANN_NAME").val();
		      if(CHANN_NAME!=""){
			      var params = "STATE = '启用' and DEL_FLAG='0' and  CHANN_ID IN ${CHANN_ID} and CHANN_ID='"+$("#CHANN_ID").val()+"'";
			      document.getElementById("selectParamsByItem").value = params;
			      selCommon('BS_197', false, 'BUDGET_ITEM_NAME', 'BUDGET_ITEM_NAME', 'forms[0]','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_QUOTA_ID','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_QUOTA_ID', 'selectParamsByItem')
			   } else{
			      parent.showErrorMsg("请先选择加盟商!"); 
			      return;
			   }
		 }
	</script>
	<title>新增或修改</title>
</head>
<body class="bodycss1" onload="load()">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr> <td> &nbsp; </td> </tr>
	</table>
	<form method="POST"   id="mainForm">
		<input type="hidden" id="selectParams" name="selectParams" value="">
		<input type="hidden" id="selectParamsT" name="selectParamsT" />
		<input type="hidden" id="RNVTN_REIT_REQ_ID" name="RNVTN_REIT_REQ_ID" value="${rst.RNVTN_REIT_REQ_ID}">
	    <input type="hidden" name="selectParamsByItem"  id="selectParamsByItem" value=""/>
	    <input type="hidden" name="count" id="count" value="${count}" />
	    <input type="hidden" name="selChannParam"  id="selChannParam"  value=" STATE ='启用' and DEL_FLAG=0 and CHANN_ID IN ${CHANN_ID}"/>
	    <input type="hidden" name="selectTERMParams"  id="selectTERMParams" value=" STATE='启用' and DEL_FLAG=0"/>
	    <input type="hidden" id="CHANNS_CHARG" name="CHANNS_CHARG" value="${CHANN_ID}">
	    <input type="hidden" id="selTermParam" name="selTermParam" />
		<input type="hidden" id="isHaveOver" name="isHaveOver" value="">
		<input type="hidden" id="module" name="module" value="${module}" />
		<input type="hidden" id="counts" name="counts" value="${counts}" />
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="18%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="25%" class="detail_content" colspan="3">
							     <c:choose>
							        <c:when test="${module eq 'sh'}">
							          <input json="REQ_NAME" name="REQ_NAME"
										id="REQ_NAME" type="text" seltarget="selLL" label="申请人"
										size="30" value="${rst.REQ_NAME}" autocheck="true" inputtype="string"  disabled/>
							        </c:when>
							        <c:otherwise>
							           <input json="REQ_ID" name="REQ_ID" id="REQ_ID" value="${rst.REQ_ID}" type="hidden">
							           <input json="REQ_NAME" name="REQ_NAME"
											id="REQ_NAME" type="text" seltarget="selLL" label="申请人"
											size="30" value="${rst.REQ_NAME}" autocheck="true" inputtype="string"  readonly/>
							        </c:otherwise>
							     </c:choose>
							</td>
							<td width="18%" align="right" class="detail_label">
								填报日期：
							</td>
							<td width="25%" class="detail_content">
							    <c:choose>
							        <c:when test="${module eq 'sh'}">
							           <input json="REQ_DATE" name="REQ_DATE" json="REQ_DATE" size="30" type="text" value="${rst.REQ_DATE}" disabled/>
							        </c:when>
							        <c:otherwise>
							           <input json="REQ_DATE" name="REQ_DATE" json="REQ_DATE" size="30" type="text" value="${rst.REQ_DATE}" readonly/>
							        </c:otherwise>
							    </c:choose>
							</td>
						</tr>
					
						<tr>
						<td width="15%" align="right" class="detail_label" >
								是否有DM装修申请单${rst.FROM_BILL_FLAG}： 
							</td>
							<td width="25%" class="detail_content"   name="OLD_OA_TD_L">
							   <input type="hidden" json="FROM_BILL_FLAG" id="FROM_BILL_FLAGA" name="FROM_BILL_FLAG" value="${rst.FROM_BILL_FLAG}" />
							   <input type="hidden" json="FORM_BILL_FLAG_T" name="FORM_BILL_FLAG_T" id="FORM_BILL_FLAG_T" /> 
							   <c:choose>
							        <c:when test="${module eq 'sh'}">
							           <input type="radio" name="FROM_BILL_FLAGT" json="FROM_BILL_FLAGT" 
									   <c:if test="${empty rst.FROM_BILL_FLAG or 0 eq rst.FROM_BILL_FLAG}">checked</c:if>
									   onclick="checkMust(this);" value="0" disabled/>有
									   <input type="radio" name="FROM_BILL_FLAGT" json="FROM_BILL_FLAGT"
									   <c:if test="${1 eq rst.FROM_BILL_FLAG}">checked</c:if>
									   onclick="checkMust(this);" value="1" disabled/>没有
									   <input type="hidden" id="FROM_BILL_FLAG" value="${rst.FROM_BILL_FLAG}"/>
 							        </c:when>
							        <c:otherwise>
							           <input type="radio" name="FROM_BILL_FLAGT" json="FROM_BILL_FLAGT" 
									   <c:if test="${empty rst.FROM_BILL_FLAG or 0 eq rst.FROM_BILL_FLAG}">checked</c:if>
									   onclick="checkMust(this);" value="0"/>有
									   <input type="radio" name="FROM_BILL_FLAGT" json="FROM_BILL_FLAGT"
									   <c:if test="${1 eq rst.FROM_BILL_FLAG}">checked</c:if>
									   onclick="checkMust(this);" value="1"/>没有
									   <input type="hidden" id="FROM_BILL_FLAG" value="${rst.FROM_BILL_FLAG}"/>
							        </c:otherwise>
							     </c:choose>
							</td>
						   <td width="15%" align="right" class="detail_label" name="OLD_OA_TD">
								老OA走的申请单：
							</td>
							<td width="25%" class="detail_content" colspan="3" name="OLD_OA_TD">
							   <input type="hidden" label="老OA走的申请单"  name="OLD_OA_ORDER_PIC" 
							   id="OLD_OA_ORDER_PIC" json="OLD_OA_ORDER_PIC" value="${rst.OLD_OA_ORDER_PIC}" />
 							</td>
						</tr> 
						<tr >
							<td width="15%" align="right" class="detail_label" id="showChannRnvtnNO">
								原申请单号：
							</td>
							<td width="25%" class="detail_content" id="showChannRnvtnNO1">
					            <input json="CHANN_RNVTN_ID" name="CHANN_RNVTN_ID" id="CHANN_RNVTN_ID" value="${rst.CHANN_RNVTN_ID}" type="hidden" />
								<c:choose>
				                   <c:when test="${module eq 'sh'}">
				                      <input json="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO" autocheck="true" inputtype="string" size="30"  value="${rst.CHANN_RNVTN_NO}"  disabled>
 				                   </c:when>
				                   <c:otherwise>
				                      <input json="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO"  mustinput="true" autocheck="true" inputtype="string" size="30"  value="${rst.CHANN_RNVTN_NO}"  readonly>
								      <img align="absmiddle" name="" style="cursor: hand"
									                     src="${ctx}/styles/${theme}/images/plus/select.gif"
								                         onClick="sel()">
				                   </c:otherwise>
				                 </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								加盟商：
							</td>
							<td width="23%" class="detail_content">
				                <c:choose>
				                   <c:when test="${module eq 'sh'}">
				                      <input type="text"   json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" value="${rst.CHANN_NAME}" size="30" autocheck="true" inputtype="string" disabled />
				                   </c:when>
				                   <c:otherwise>
				                      <input type="hidden" json="CHANN_ID"   id="CHANN_ID"   name="CHANN_ID" value="${rst.CHANN_ID}" />
				                      <input type="hidden" json="CHANN_NO"   id="CHANN_NO"   name="CHANN_NO" value="${rst.CHANN_NO}" /> 
				                      <input type="text"   json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" value="${rst.CHANN_NAME}" size="30" mustinput="true" autocheck="true" inputtype="string" readonly  />
					                  <img align="absmiddle" name="selJGXX" style="cursor: hand;" id="showChannName"
								           src="${ctx}/styles/${theme}/images/plus/select.gif"   
								           onClick="queryChannName()"> 
				                   </c:otherwise>
				                </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								设计师：
							</td>
							<td width="23%" class="detail_content"  id="showTd">
								<c:choose>
				                   <c:when test="${module eq 'sh'}">
				                        <input json="DESIGN_PERSON" name="DESIGN_PERSON" id="DESIGN_PERSON" type="text" autocheck="true" inputtype="string"
									     label="设计师" size="30" value="${rst.DESIGN_PERSON}" disabled>
				                   </c:when>
				                   <c:otherwise>
				                       <input json="DESIGN_ID" name="DESIGN_ID" id="DESIGN_ID" type="hidden" value="${rst.DESIGN_ID}" />
				                       <input json="DESIGN_PERSON" name="DESIGN_PERSON" id="DESIGN_PERSON" type="text" mustinput="true" autocheck="true" inputtype="string"
									   label="设计师" size="30" value="${rst.DESIGN_PERSON}" readonly>
								       <img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('BS_170', false, 'DESIGN_PERSON', 'XM', 'forms[0]','DESIGN_PERSON,DESIGN_ID', 'XM,RYXXID', 'selectParamsT')">
				                   </c:otherwise>
				                </c:choose>
							</td>
						</tr> 
						<tr>
						    <td width="15%" align="right" class="detail_label">
								门店编号：
							</td>
							<td width="23%" class="detail_content">
							    <c:choose>
				                   <c:when test="${module eq 'sh'}">
				                      <input json="TERM_NO" id="TERM_NO" name="TERM_NO" type="text" value="${rst.TERM_NO}" size="30" label="门店编号" autocheck="true" inputtype="string" disabled/>
				                   </c:when>
				                   <c:otherwise>
				                      <input json="TERM_ID" id="TERM_ID" name="TERM_ID" type="hidden" value="${rst.TERM_ID}" />
				                      <input json="TERM_NO" id="TERM_NO" name="TERM_NO" type="text" value="${rst.TERM_NO}" size="30" label="门店编号" mustinput="true" autocheck="true" inputtype="string" readonly/>
								      <img align="absmiddle" name="" style="cursor: hand;display:none;" id="showTermNo" 
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selectTerm()">
				                   </c:otherwise>
				                </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								门店名称：
							</td>
							<td width="23%" class="detail_content">
							<c:choose>
				                   <c:when test="${module eq 'sh'}">
				                     <input json="TERM_NAME" name="TERM_NAME" json="TERM_NAME" type="text" size="30" value="${rst.TERM_NAME}" disabled/>
				                   </c:when>
				                   <c:otherwise>
				                     <input json="TERM_NAME" name="TERM_NAME" json="TERM_NAME" type="text" size="30" value="${rst.TERM_NAME}" readonly/>
				                   </c:otherwise>
				            </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								开店年份：
							</td>
							<td width="23%" class="detail_content">
							<c:choose>
				                   <c:when test="${module eq 'sh'}">
				                     <input json="OPEN_SALE_YEAR" name="OPEN_SALE_YEAR"
										id="OPEN_SALE_YEAR" type="text" seltarget="selLL" label="开店年份"
										size="30" value="${rst.OPEN_SALE_YEAR}" disabled="disabled">
				                   </c:when>
				                   <c:otherwise>
				                    <input json="PLAN_SBUSS_DATE" name="PLAN_SBUSS_DATE" id="PLAN_SBUSS_DATE" value="${rst.PLAN_SBUSS_DATE}" type="hidden"/>
									<input josn="BEG_SBUSS_DATE"  name="BEG_SBUSS_DATE"  id="BEG_SBUSS_DATE"  value="${rst.BEG_SBUSS_DATE}"  type="hidden"/>
									<input json="OPEN_SALE_YEAR" name="OPEN_SALE_YEAR"
										id="OPEN_SALE_YEAR" type="text" seltarget="selLL" label="开店年份"
										size="30" value="${rst.OPEN_SALE_YEAR}" autocheck="true" inputtype="string" readonly>
							      </c:otherwise>
							 </c:choose>
							</td>
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="25%" class="detail_content">
							     <c:choose>
				                   <c:when test="${module eq 'sh'}"> 
				                      <input json="AREA_NAME" name="AREA_NAME"
										id="AREA_NAME" type="text" seltarget="selLL" label="所属战区"
										size="30" value="${rst.AREA_NAME}" autocheck="true" inputtype="string" disabled/>
				                   </c:when>
				                   <c:otherwise>
								    <input json="AREA_ID" name="AREA_ID" id="AREA_ID" value="${rst.AREA_ID}" type="hidden"/>
								    <input json="AREA_NO" name="AREA_NO" id="AREA_NO" value="${rst.AREA_NO}" type="hidden"/>
								    <!--  
								    <input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" value="${rst.AREA_NAME}" type="hidden" />
								    -->
								    <input json="REIT_AMOUNT_PS" name="REIT_AMOUNT_PSt" id="REIT_AMOUNT_PS" type="hidden" value="${rst.REIT_AMOUNT_PS}"/>
									<input type="hidden" name="AREA_MANAGE_ID"  id="AREA_MANAGE_ID"   json="AREA_MANAGE_ID"   value="${rst.AREA_MANAGE_ID}" />
							        <input type="hidden" name="AREA_MANAGE_TEL" id="AREA_MANAGE_TEL"  json="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}" />
							        <input type="hidden" name="AREA_MANAGE_NAME"  id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" value="${rst.AREA_MANAGE_NAME}" size="30" READONLY/>
									<input json="AREA_NAME" name="AREA_NAME"
										id="AREA_NAME" type="text" seltarget="selLL" label="所属战区"
										size="30" value="${rst.AREA_NAME}" autocheck="true" inputtype="string" readonly>
							        </c:otherwise>
							     </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								商场名称：
							</td>
							<td width="23%" class="detail_content">
							 <input json="SALE_STORE_ID"   name="SALE_STORE_ID" id="SALE_STORE_ID" value="${rst.SALE_STORE_ID}" type="hidden">
							 <c:choose>
				                   <c:when test="${module eq 'sh'}"> 
				                     <input json="SALE_STORE_NAME" name="SALE_STORE_NAME"
										id="SALE_STORE_NAME" type="text" label="商场名称"
										size="30" value="${rst.SALE_STORE_NAME}" autocheck="true" inputtype="string" disabled="true">
				                   </c:when>
				                   <c:otherwise>
									<input json="SALE_STORE_NAME" name="SALE_STORE_NAMET"
										id="SALE_STORE_NAME" type="text" label="商场名称"
										size="30" value="${rst.SALE_STORE_NAME}" autocheck="true" inputtype="string" READONLY>
								   </c:otherwise>
							 </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								商场地址：
							</td>
							<td width="23%" class="detail_content">
							<c:choose>
				                   <c:when test="${module eq 'sh'}"> 
				                     <input json="ADDRESS" name="ADDRESS"
										id="ADDRESS" type="text" seltarget="selLL" label="商场地址"
										size="30" autocheck="true" inputtype="string" value="${rst.ADDRESS}" disabled>
				                   </c:when>
				                   <c:otherwise>
									<input json="ADDRESS" name="ADDRESS"
										id="ADDRESS" type="text" seltarget="selLL" label="商场地址"
										size="30" autocheck="true" inputtype="string" value="${rst.ADDRESS}" readonly>
								   </c:otherwise>
							 </c:choose>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="23%" class="detail_content" colspan="3">
							<c:choose>
				                   <c:when test="${module eq 'sh'}"> 
				                     <input type="hidden" name="BUSS_SCOPET" id="BUSS_SCOPET" />
									 <input type="hidden" name="BUSS_SCOPE"  id="BUSS_SCOPE" json="BUSS_SCOPE" value="${rst.BUSS_SCOPE}"/>
				                     <c:forEach items="${result}" var="rst" varStatus="row">
								      <input type="checkbox" name="BUSS_SCOPEs" value="${rst.DATA_DTL_CODE}" onclick="getBussScope()" disabled/>&nbsp;&nbsp;${rst.BUSS_SCOPE}&nbsp;&nbsp;&nbsp;&nbsp;
								    </c:forEach>
				                   </c:when>
				                   <c:otherwise>
				                    <input type="hidden" name="BUSS_SCOPET" id="BUSS_SCOPET" />
									<input type="hidden" name="BUSS_SCOPE"  id="BUSS_SCOPE" json="BUSS_SCOPE" value="${rst.BUSS_SCOPE}"/>
									<c:forEach items="${result}" var="rst" varStatus="row">
								      <input type="checkbox" name="BUSS_SCOPEs" value="${rst.DATA_DTL_CODE}" onclick="getBussScope()"/>&nbsp;&nbsp;${rst.BUSS_SCOPE}&nbsp;&nbsp;&nbsp;&nbsp;
								    </c:forEach>&nbsp;&nbsp;<font color='red'>*</font>
				                   </c:otherwise>
				             </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								门店使用面积：
							</td>
							<td width="23%" class="detail_content" >
							<c:choose>
				                   <c:when test="${pvg.PVG_USE_AREA eq 1}"> 
				                      <input type="text" json="USE_AREA" name="USE_AREA" id="USE_AREA" label="门店使用面积" size="30" autocheck="true" inputtype="string" value="${rst.USE_AREA}"/>
 				                   </c:when>
				                   <c:otherwise>
				                     <input json="USE_AREA" name="USE_AREA"
									        id="USE_AREA" type="text" label="门店使用面积"
									        size="30" autocheck="true" inputtype="string" value="${rst.USE_AREA}" readonly="true" />
				                   </c:otherwise>
				            </c:choose>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								商场位置：
							</td>
							<td width="23%" class="detail_content" colspan="5">
							<c:choose>
				                   <c:when test="${module eq 'sh'}"> 
				                     <input type="hidden"     name="LOCAL_TYPE"   id="LOCAL_TYPE"  json="LOCAL_TYPE" value="${rst.LOCAL_TYPE}"/>
									 <input type="hidden"   name="LOCAL_TYPETt" id="LOCAL_TYPETt"  />
									 <input type="hidden"   name="LOCAL_TYPET"  id="LOCAL_TYPET"  />
									 <c:forEach items="${result2}" var="rst2" varStatus="row">
								      <input type="radio" name="LOCAL_TYPEs" value="${rst2.DATA_DTL_CODE}" onclick="getLocalType()" disabled/>&nbsp;&nbsp;${rst2.LOCAL_TYPE}&nbsp;&nbsp;&nbsp;&nbsp;
								     </c:forEach> 
				                   </c:when>
				                   <c:otherwise>
									 <input type="hidden"     name="LOCAL_TYPE"   id="LOCAL_TYPE"  json="LOCAL_TYPE" value="${rst.LOCAL_TYPE}"/>
									 <input type="hidden"   name="LOCAL_TYPETt" id="LOCAL_TYPETt"  />
									 <input type="hidden"   name="LOCAL_TYPET"  id="LOCAL_TYPET"  />
									 <c:forEach items="${result2}" var="rst2" varStatus="row">
								      <input type="radio" name="LOCAL_TYPEs" value="${rst2.DATA_DTL_CODE}" onclick="getLocalType()"/>&nbsp;&nbsp;${rst2.LOCAL_TYPE}&nbsp;&nbsp;&nbsp;&nbsp;
								     </c:forEach>&nbsp;&nbsp;<font color='red'>*</font>
						      </c:otherwise>
						      </c:choose>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="23%" class="detail_content" colspan="3">
							<c:choose>
				                   <c:when test="${module eq 'sh'}"> 
									 <input type="hidden"  name="RNVTN_PROPT" id="RNVTN_PROPT"/>
									 <input type="hidden" name="RNVTN_PROP"  id="RNVTN_PROP"  json="RNVTN_PROP" value="${rst.RNVTN_PROP}" />
									 <c:forEach items="${result1}" var="rst1" varStatus="row">
								      <input type="radio" name="RNVTN_PROPs" value="${rst1.DATA_DTL_CODE}" onclick="getRnvtnProp()" disabled/>&nbsp;&nbsp;${rst1.RNVTN_PROP}&nbsp;&nbsp;&nbsp;&nbsp;
								     </c:forEach> 
								   </c:when>
								   <c:otherwise>
								     <input type="hidden"  name="RNVTN_PROPT" id="RNVTN_PROPT"/>
									 <input type="hidden" name="RNVTN_PROP"  id="RNVTN_PROP"  json="RNVTN_PROP" value="${rst.RNVTN_PROP}" />
									 <c:forEach items="${result1}" var="rst1" varStatus="row">
								      <input type="radio" name="RNVTN_PROPs" value="${rst1.DATA_DTL_CODE}" onclick="getRnvtnProp()"/>&nbsp;&nbsp;${rst1.RNVTN_PROP}&nbsp;&nbsp;&nbsp;&nbsp;
								     </c:forEach>&nbsp;&nbsp;<font color='red'>*</font>
								   </c:otherwise>
							   </c:choose>
							</td>
							<td width="15%" align="right" class="detail_label">
								是否北方公司：
							</td>
							<td width="23%" class="detail_content" >
							<c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                   <input type="hidden" name="IS_NORTH"  id="IS_NORTH"  json="IS_NORTH" value="${rst.IS_NORTH}" />
							       <input type="radio" name="IS_NORTHs" value="1" onclick="chkIsNorth()" disabled>&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="IS_NORTHs" value="0" onclick="chkIsNorth()" disabled>&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;
				                </c:when>
				                <c:otherwise>
				                   <input type="hidden" name="IS_NORTH"  id="IS_NORTH"  json="IS_NORTH" value="${rst.IS_NORTH}" />
							       <input type="radio" name="IS_NORTHs" value="1" onclick="chkIsNorth()">&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="IS_NORTHs" value="0" onclick="chkIsNorth()">&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;
				                </c:otherwise>
							  </c:choose>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								预算科目：
							</td>
							<td width="23%" class="detail_content" colspan="5">
							<c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                <input json="BUDGET_QUOTA_ID"  name="BUDGET_QUOTA_ID"  id="BUDGET_QUOTA_ID"  type="hidden" value="${rst.BUDGET_QUOTA_ID}">
							    <input json="BUDGET_ITEM_ID"   name="BUDGET_ITEM_ID"   id="BUDGET_ITEM_ID"   type="hidden" value="${rst.BUDGET_ITEM_ID}"/>
		   					    <input json="BUDGET_ITEM_NO"   name="BUDGET_ITEM_NO"   id="BUDGET_ITEM_NO"   type="hidden" value="${rst.BUDGET_ITEM_NO}"/>
   					            <input json="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" label="预算科目"  
								    autocheck="true" inputtype="string" size="30"  value="${rst.BUDGET_ITEM_NAME}" disabled /> 
				                </c:when>
				                <c:otherwise>
				                <input json="BUDGET_QUOTA_ID"  name="BUDGET_QUOTA_ID"  id="BUDGET_QUOTA_ID"  type="hidden" value="${rst.BUDGET_QUOTA_ID}">
							    <input json="BUDGET_ITEM_ID"   name="BUDGET_ITEM_ID"   id="BUDGET_ITEM_ID"   type="hidden" value="${rst.BUDGET_ITEM_ID}"/>
		   					    <input json="BUDGET_ITEM_NO"   name="BUDGET_ITEM_NO"   id="BUDGET_ITEM_NO"   type="hidden" value="${rst.BUDGET_ITEM_NO}"/>
   					            <input json="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" label="预算科目" mustinput="true"
								    autocheck="true" inputtype="string" size="30"  value="${rst.BUDGET_ITEM_NAME}" readonly /> 
							    <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="getItems()">
				                </c:otherwise>
				              </c:choose>
  							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >
								附照片：
							</td>
							<td width="23%" class="detail_content" colspan="5">
							   <input type="hidden" name="ATT_PATH" id="ATT_PATH" json="ATT_PATH" value="${rst.ATT_PATH}" />
							   <input type="hidden" name="PIC"  id="PIC" json="PIC" value="${rst.PIC}" />
 							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >
								报销次数：
							</td>
							<td width="23%" class="detail_content" colspan="5">
							<c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                <input type="hidden" name="TOTAL_REITED_NUM" id="TOTAL_REITED_NUM" json="TOTAL_REITED_NUM" value="${rst.TOTAL_REITED_NUM}" />
							    <input type="radio" name="TOTAL_REITED_NUMs"  value="1" onclick="chkTotalNum()" disabled>&nbsp;&nbsp;一次性100%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="TOTAL_REITED_NUMs"  value="2" onclick="chkTotalNum()" disabled>&nbsp;&nbsp;二次50%、50%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="TOTAL_REITED_NUMs" value="3" onclick="chkTotalNum()" disabled>&nbsp;&nbsp;三次50%、25%、25%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                </c:when>
				                <c:otherwise>
				                <input type="hidden" name="TOTAL_REITED_NUM" id="TOTAL_REITED_NUM" json="TOTAL_REITED_NUM" value="${rst.TOTAL_REITED_NUM}" />
							    <input type="radio" name="TOTAL_REITED_NUMs"  value="1" onclick="chkTotalNum()">&nbsp;&nbsp;一次性100%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="TOTAL_REITED_NUMs"  value="2" onclick="chkTotalNum()">&nbsp;&nbsp;二次50%、50%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="TOTAL_REITED_NUMs" value="3" onclick="chkTotalNum()">&nbsp;&nbsp;三次50%、25%、25%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>
				                </c:otherwise>
				             </c:choose>
 							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >
								第几次报销：
							</td>
							<td width="23%" class="detail_content" colspan="5">
							<c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                 <input type="hidden" name="REITED_NUM" id="REITED_NUM" json="REITED_NUM" value="${rst.REITED_NUM}" />
							   <input type="radio" name="REITED_NUMs"  value="1" onclick="chkReitedNum()" disabled>&nbsp;&nbsp;第一次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="REITED_NUMs"  value="2" onclick="chkReitedNum()" disabled>&nbsp;&nbsp;第二次
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="REITED_NUMs" value="3" onclick="chkReitedNum()" disabled>&nbsp;&nbsp;第三次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                </c:when>
				                <c:otherwise>
				                 <input type="hidden" name="REITED_NUM" id="REITED_NUM" json="REITED_NUM" value="${rst.REITED_NUM}" />
							   <input type="radio" name="REITED_NUMs"  value="1" onclick="chkReitedNum()">&nbsp;&nbsp;第一次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="REITED_NUMs"  value="2" onclick="chkReitedNum()">&nbsp;&nbsp;第二次
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="REITED_NUMs" value="3" onclick="chkReitedNum()">&nbsp;&nbsp;第三次&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>
				                </c:otherwise>
				              </c:choose>
 							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label" >
								总报销金额：
							</td>
						    <td width="23%" class="detail_content"> 
								 <input type="text" name="TOTAL_REIT_AMOUNT"  id="TOTAL_REIT_AMOUNT" json="TOTAL_REIT_AMOUNT" value="${rst.TOTAL_REIT_AMOUNT}" size="30" label="总报销金额" autocheck="true" inputtype="float" valueType="8,2" maxlength="10" onblur="checkAmount(this,'总报销金额')"  READONLY/>
 							</td>
 							<td width="15%" align="center" class="detail_label" >
 							     本次报销金额:
 							</td>
 							<td width="23%" class="detail_content">
 							   <c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                  <input type="text" name="REIT_AMOUNT"  id="REIT_AMOUNT" json="REIT_AMOUNT" value="${rst.REIT_AMOUNT}" size="30" label="本次报销金额" onchange="chkReitedNum()" disabled/>
				                </c:when>
				                <c:otherwise>
 				                  <input type="text" name="REIT_AMOUNT"  id="REIT_AMOUNT" json="REIT_AMOUNT" value="${rst.REIT_AMOUNT}" size="30" label="本次报销金额" mustinput="true" autocheck="true" inputtype="number"  valueType="number" maxlength="8" />
				                </c:otherwise>
				             </c:choose>
 							</td>
 							<td width="15%" align="center" class="detail_label" >
								 实际报销金额：
 							</td>
 							<td width="23%" class="detail_content">
 							   <c:choose>
 							     <c:when test="${module eq 'sh'}">
 							         <c:if test="${pvg.PVG_REAL_REIT_AMOUNT eq 1 }">
 							           <input type="text" name="REAL_REIT_AMOUNT"  id="REAL_REIT_AMOUNT" json="REAL_REIT_AMOUNT" value="${rst.REAL_REIT_AMOUNT}" size="30" autocheck="true" inputtype="number"  valueType="number" maxlength="8" onblur="getReitAmount(this)"/>
 							         </c:if>
 							         <c:if test="${pvg.PVG_REAL_REIT_AMOUNT ne 1}">
 							           <input type="text" name="REAL_REIT_AMOUNT"  id="REAL_REIT_AMOUNT" json="REAL_REIT_AMOUNT" value="${rst.REAL_REIT_AMOUNT}" size="30" autocheck="true" inputtype="number"  valueType="number" maxlength="8" disabled="true"/>
 							         </c:if>
 							     </c:when>
 							     <c:otherwise>
 							         <input type="text" name="REAL_REIT_AMOUNT"  id="REAL_REIT_AMOUNT" json="REAL_REIT_AMOUNT" value="${rst.REAL_REIT_AMOUNT}" size="30" autocheck="true" inputtype="number"  valueType="number" maxlength="8" onblur="getReitAmount(this)"/>
 							     </c:otherwise>
 							   </c:choose>
 							</td>
					  </tr>
					  <tr>
					      <td width="15%" align="right" class="detail_label" >
								金额说明：
							</td>
							<td width="23%" class="detail_content" colspan="6">
							<c:choose>
				                <c:when test="${pvg.PVG_AMOUNT_DESC eq 1}"> 
				                   <textarea name="AMOUNT_DESC"  id="AMOUNT_DESC" json="AMOUNT_DESC" rows="2" cols="30" autocheck="true" inputtype="string"   maxlength="250" >${rst.AMOUNT_DESC}</textarea>
				                </c:when>
				                <c:otherwise>
				                   <textarea type="text" name="AMOUNT_DESC"  id="AMOUNT_DESC" json="AMOUNT_DESC" READONLY>${rst.AMOUNT_DESC}</textarea>
				                </c:otherwise>
				            </c:choose>
 							</td> 
					  </tr>
					  <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="4">关联附件：</td>
					   <td width="15%" align="left" class="detail_content" colspan="3">
					         装修申请单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="ZHUANGXIUSQ" name="ZHUANGXIUSQ" json="ZHUANGXIUSQ"/>
 					   </td>
					   <td width="15%" align="right" class="detail_label">关联已报项：</td>
 					   <td width="35%" class="detail_content">
					     <input json="YBXIANG" name="YBXIANG" id="YBXIANG" label="关联已报项"  type="hidden" />
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="8">
					         卖场装修验收表<input type="hidden"  name="MCYSTAB" label="卖场装修验收表"  json="MCYSTAB" id="MCYSTAB" value="${rst.MCYSTAB}"/>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="8">
					         整改验收表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" size="20" name="ZGYANSHOU" label="整改验收表" json="ZGYANSHOU" id="ZGYANSHOU" value="${rst.MCYSTAB}"/>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="3">
					        装修平面图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" size="20" name="ZXPIC" label="装修平面图"  type="text" json="ZXPIC" id="ZXPIC" value="${rst.ZXPIC}"/>
 					   </td>
 					   <td width="15%" align="right" class="detail_label">装修发票：</td>
 					   <td width="35%" class="detail_content">
					     <input json="ZXFAPIAO" name="ZXFAPIAO" id="ZXFAPIAO" 
						        autocheck="true" label="装修平面图"  type="hidden" value="${rst.ZXFAPIAO}"/>
 					   </td>
	                  </tr>
					  <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="4">销售营运部：</td>
					   <td width="15%" align="left" class="detail_content">
					         销售达成情况
 					   </td>
 					   <td width="15%" align="right" class="detail_content">
					         合同金额(万元)
 					   </td>
 					   <td width="15%" align="left" class="detail_content">
 					      <c:choose>
 						    <c:when test="${module eq 'sh'}">
 					         <input id="SALE_COMPACT_AMOUNT" name="SALE_COMPACT_AMOUNT" json="SALE_COMPACT_AMOUNT" type="text" value="${rst.SALE_COMPACT_AMOUNT}" label="合同金额" size="30" disabled/>
  					        </c:when>
  					        <c:otherwise>
 					        <input id="SALE_COMPACT_AMOUNT" name="SALE_COMPACT_AMOUNT" json="SALE_COMPACT_AMOUNT" type="text" value="${rst.SALE_COMPACT_AMOUNT}" label="合同金额"  mustinput="true" autocheck="true" inputtype="string"  size="30" onblur="getRate()"/>
  					        </c:otherwise>
  					      </c:choose>
  					   </td>
					   <td width="15%" align="right" class="detail_label">本年度至今发货金额(万元)：</td>
 					   <td width="35%" class="detail_content">
 					       <c:choose>
			                <c:when test="${module eq 'sh'}"> 
			                  <input json="YEAR_GOODS_AMOUNT" name="YEAR_GOODS_AMOUNT" id="YEAR_GOODS_AMOUNT" 
					        autocheck="true" label="本年度至今发货金额"  type="text" size="30" value="${rst.YEAR_GOODS_AMOUNT}" disabled/>
			                </c:when>
			                <c:otherwise>
			                <input json="YEAR_GOODS_AMOUNT" name="YEAR_GOODS_AMOUNT" id="YEAR_GOODS_AMOUNT" 
					        autocheck="true" label="本年度至今发货金额"  type="text" size="30" value="${rst.YEAR_GOODS_AMOUNT}" READONLY/>
			                </c:otherwise>
					       </c:choose>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" cols="3">
					         累计四个季度实际完成(超过80%)&nbsp;
					          <c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                  <input id="QUARTER_RATE" name="QUARTER_RATE" json="QUARTER_RATE" type="text" value="${rst.QUARTER_RATE}" size="5" disabled/>
				                </c:when>
				                <c:otherwise>
				                  <input id="QUARTER_RATE" name="QUARTER_RATE" json="QUARTER_RATE" type="text" value="${rst.QUARTER_RATE}" size="5" READONLY/>
				                </c:otherwise>
				              </c:choose>
 					   </td>
 					   <td width="15%" align="right" class="detail_label">有无违反现象：</td>
 					   <td width="35%" class="detail_content" colspan="3">
 					   <c:choose>
				                <c:when test="${module eq 'sh'}"> 
				                   <input json="IS_VIOLATE_REMARK" name="IS_VIOLATE_REMARK" id="IS_VIOLATE_REMARK" 
						        autocheck="true" label="有无违反现象"  type="text" size="30" value="${rst.IS_VIOLATE_REMARK}" disabled/>
				                </c:when>
				                <c:otherwise>
				                   <input json="IS_VIOLATE_REMARK" name="IS_VIOLATE_REMARK" id="IS_VIOLATE_REMARK" 
						        autocheck="true" label="有无违反现象"  type="text" size="30" value="${rst.IS_VIOLATE_REMARK}"/>
				                </c:otherwise>
				       </c:choose>
 					   </td>
	                  </tr>  
					</table>
				</td>
			</tr>
		</table>
	</form>
   </div>
   <%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
</body>
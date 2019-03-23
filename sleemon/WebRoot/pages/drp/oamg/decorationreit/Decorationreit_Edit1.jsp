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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
    <script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/decorationreit/Decorationreit_Edit.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	    function  sel(){
	       selCommon('BS_169', false, 'CHANN_RNVTN_NO', 'CHANN_RNVTN_NO', 'forms[0]','CHANN_RNVTN_ID,CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,DESIGN_PERSON,CHANN_ID,CHANN_NO,CHANN_NAME,SALE_STORE_ID,SALE_STORE_NAME,ADDRESS,USE_AREA,BUSS_SCOPET,LOCAL_TYPET,RNVTN_PROP,PLAN_SBUSS_DATE,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME', 'CHANN_RNVTN_ID,CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,DESIGN_PERSON,CHANN_ID,CHANN_NO,CHANN_NAME,SALE_STORE_ID,SALE_STORE_NAME,ADDRESS,USE_AREA,BUSS_SCOPE,LOCAL_TYPE,RNVTN_PROP,PLAN_SBUSS_DATE,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME', 'selectParams');
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
	       /*
	       $.ajax({
		    url: "decorationapp.shtml?action=toQuery",
			type:"POST",
			dataType:"text",
			data:{"RNVTN_PROP":RNVTN_PROPT},
			complete: function (xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					  var result = jsonResult.data;
					  $("#RNVTN_PROP").val(result.RNVTN_PROP);
					  var RNVTN_PROP = document.getElementsByName("RNVTN_PROPs");
					  for(var i=0;i<RNVTN_PROP.length;i++){
					     if(RNVTN_PROP[i].value==result.RNVTN_PROP){
					        RNVTN_PROP[i].checked=true;
					     }
					  }
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		 });*/
	    }
	</script>
	<title>新增或修改</title>
</head>
<body class="bodycss1">
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
		<input type="hidden" id="selectParams" name="selectParams" value=" CHANN_ID in  ${CHANN_ID} ">
		<input type="hidden" id="RNVTN_REIT_REQ_ID" name="RNVTN_REIT_REQ_ID" value="${rst.RNVTN_REIT_REQ_ID}">
		<input type="hidden" id="isHaveOver" name="isHaveOver" value="">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="20%" align="right" class="detail_label">
								装修报销申请单编号22：
							</td>
							<td width="35%" class="detail_content">
							<input  name="RNVTN_REIT_REQ_NO" id="RNVTN_REIT_REQ_NO" type="text" size="35"
								autocheck="true" label="装修报销申请单编号" inputtype="string" mustinput="true"
								maxlength="32"
								<c:if test="${not empty rst.RNVTN_REIT_REQ_NO}">value="${rst.RNVTN_REIT_REQ_NO}"</c:if>
								<c:if test="${empty rst.RNVTN_REIT_REQ_NO}">value="自动生成"</c:if>
								READONLY
								>
							</td>
							<td width="20%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
								<input  name="STATE" id="STATE" type="text" size="35"
									label="状态"  value="${rst.STATE}" readonly>
						</tr>
						<tr>
						   <td width="20%" align="right" class="detail_label">
								装修申请单编号：
							</td>
							<td width="35%" class="detail_content">
							    <input type="hidden" id="CHANN_RNVTN_ID" name="CHANN_RNVTN_ID" json="CHANN_RNVTN_ID" value="${rst.CHANN_RNVTN_ID}"/>
								<input json="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO"
									id="CHANN_RNVTN_NO" type="text" seltarget="selLL" label="装修申请单编号" size="35"
									autocheck="true" mustinput="true" inputtype="string" value="${rst.CHANN_RNVTN_NO}" mustinput="true" readonly>
							   <c:if test="${empty rst.RNVTN_REIT_REQ_ID}">
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selectChannRnvtn();">
							  </c:if>
							</td>
							<td width="20%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="35%" class="detail_content">
							 <c:if test="${empty pvg.PVG_AUDIT_EDIT || pvg.PVG_AUDIT_EDIT ne 1}">
							    <select id="RNVTN_PROP" name="RNVTN_PROP" json="RNVTN_PROP" label="装修性质" style="width:245px;" value="${rst.RNVTN_PROP}" autocheck="true" mustinput="true"  inputtype="string" disabled="disabled"></select>
							 </c:if>
							 <c:if test="${pvg.PVG_AUDIT_EDIT eq 1}">
							    <select id="RNVTN_PROP" name="RNVTN_PROP" json="RNVTN_PROP" label="装修性质" style="width:245px;" value="${rst.RNVTN_PROP}" autocheck="true" mustinput="true"  inputtype="string" disabled="disabled"></select>
						     </c:if>	
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
								<input json="TERM_ID" name="TERM_ID" id="TERM_ID"
									  label="终端信息ID" type="hidden"
									  value="${rst.TERM_ID}" size="35"/>
								<input json="TERM_NO" name="TERM_NO" id="TERM_NO"
									autocheck="true" label="终端编号" type="text" inputtype="string"
									 value="${rst.TERM_NO}" readonly size="35"/>
							</td>
							<td width="20%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								<input id="TERM_NAME" name="TERM_NAME" json="TERM_NAME"
									type="text"  value="${rst.TERM_NAME}" readonly size="35"/>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
							    <input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID"
							    type="hidden"  value="${rst.CHANN_ID}" size="35"/>
								<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO"
									type="text"  value="${rst.CHANN_NO}" readonly size="35"/>
							</td>
							<td width="20%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME"
									type="text" label="渠道名称"  value="${rst.CHANN_NAME}"
									readonly size="35"/>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								渠道联系人：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" id="CHANN_PERSON_CON"
									type="text" label="渠道联系人"  value="${rst.CHANN_PERSON_CON}"
									readonly size="35"/>
							</td>
							<td width="20%" align="right" class="detail_label">
								渠道联系电话：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHANN_TEL" name="CHANN_TEL" id="CHANN_TEL" 
								     type="text" label="渠道联系电话" value="${rst.CHANN_TEL}" 
								    readonly size="35"/>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								所属战区编号：
							</td>
							<td width="35%" class="detail_content">
							    <input type="hidden" id="AREA_ID" name=""AREA_ID json="AREA_ID" value="${rst.AREA_ID}" size="35"/>
								<input json="AREA_NO" name="AREA_NO" id="AREA_NO" type="text"
									label="所属战区编号"  value="${rst.AREA_NO}" readonly size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								所属战区名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME"
									type="text" label="所属战区名称"  value="${rst.AREA_NAME}"
									readonly size="35" />
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="35%" class="detail_content">
							<input type="hidden" id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" json="AREA_MANAGE_ID" value="${rst.AREA_MANAGE_ID}" size="35" />
							
								<input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" type="text"
									label="区域经理"  value="${rst.AREA_MANAGE_NAME}" readonly size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								区域经理电话：
							</td>
							<td width="35%" class="detail_content">
								<input json="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" id="AREA_MANAGE_TEL"
									type="text" label="区域经理电话"  value="${rst.AREA_MANAGE_TEL}"
									readonly size="35" />
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								每平米标准参考报销金额（元）：
							</td>
							<td width="35%" class="detail_content">
								<input json="REIT_AMOUNT_PS" name="REIT_AMOUNT_PS" id="REIT_AMOUNT_PS"  inputtype="float"   valueType="8,2" autocheck="true"
									id="REIT_AMOUNT_PS" type="text" label="每平米标准参考报销金额（元）" readonly  value="${rst.REIT_AMOUNT_PS}" size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								图纸面积（平米）：
							</td>
							<td width="35%" class="detail_content">
								<input json="DRAW_AREA" name="DRAW_AREA" id="DRAW_AREA" type="text" autocheck="true"  inputtype="float"   valueType="8,2"    mustinput="true"  maxlength="11" size="35"
								<c:if test="${pvg.PVG_EDIT ne 1 and pvg.PVG_AUDIT_EDIT_DRAW_AREA ne 1 }"> readonly </c:if>	label="图纸面积（平米）"  value="${rst.DRAW_AREA}"  onkeyup="countDrawReitAmountPs(this);" />
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								按图纸面积标准参考报销金额：
							</td>
							<td width="35%" class="detail_content">
								<input json="DRAW_REIT_AMOUNT_PS" name="DRAW_REIT_AMOUNT_PS" id="DRAW_REIT_AMOUNT_PS" type="text" inputtype="float"   valueType="8,2" autocheck="true"
									label="按图纸面积标准参考报销金额"  value="${rst.DRAW_REIT_AMOUNT_PS}" readonly size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								图纸设计完成时间：
							</td>
							<td width="35%" class="detail_content">
							   <c:if test="${pvg.PVG_EDIT eq 1 || pvg.PVG_AUDIT_EDIT_DRAW_FISH_DATE eq 1}">
								 <input id="DRAW_FISH_DATE" name="DRAW_FISH_DATE" json="DRAW_FISH_DATE" onclick="SelectDate();" readonly="readonly" autocheck="true" mustinput="true" inputtype="string" 
								     label="图纸设计完成时间"	  value="${rst.DRAW_FISH_DATE}" onblur="getReturnAmount()" size="35" />
								  <img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(DRAW_FISH_DATE);">
							  </c:if>
							  <c:if test="${pvg.PVG_EDIT ne 1 && pvg.PVG_AUDIT_EDIT_DRAW_FISH_DATE ne 1 }"> 
							    <input id="DRAW_FISH_DATE" name="DRAW_FISH_DATE" json="DRAW_FISH_DATE"  readonly="readonly" autocheck="true" mustinput="true" inputtype="string" 
								     label="图纸设计完成时间"	  value="${rst.DRAW_FISH_DATE}" onblur="getReturnAmount()" size="35" />
							  </c:if>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								装修完成时间：
							</td>
							<td width="35%" class="detail_content">
							<c:if test="${pvg.PVG_EDIT eq 1 || pvg.PVG_AUDIT_EDIT_RNVTN_FISH_DATE eq 1 }"> 
							    <input id="RNVTN_FISH_DATE" name="RNVTN_FISH_DATE" json="RNVTN_FISH_DATE" onclick="SelectDate();setRNVTN_DAYS();" readonly="readonly" autocheck="true" mustinput="true" inputtype="string" 
								label="装修完成时间" value="${rst.RNVTN_FISH_DATE}" onblur="getReturnAmount()" size="35" />
								<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(RNVTN_FISH_DATE);setRNVTN_DAYS();">
							 </c:if>
							 <c:if test="${pvg.PVG_EDIT ne 1 && pvg.PVG_AUDIT_EDIT_RNVTN_FISH_DATE ne 1 }"> 
							  <input id="RNVTN_FISH_DATE" name="RNVTN_FISH_DATE" json="RNVTN_FISH_DATE"  readonly="readonly" autocheck="true" mustinput="true" inputtype="string"  
								label="装修完成时间"	value="${rst.RNVTN_FISH_DATE}" size="35" />
							 </c:if>
							</td>
							<td width="20%" align="right" class="detail_label">
								处罚意见：
							</td>
							<td width="35%" class="detail_content">
							     <c:choose>
							        <c:when test="${pvg.PVG_AUDIT_EDIT_DPUNISH_REMARK eq 0}">
							            <input id="PUNISH_REMARK" name="PUNISH_REMARK" json="PUNISH_REMARK"  inputtype="string" autocheck="true"  size="35"  label="处罚意见" value="${rst.PUNISH_REMARK}" maxlength="250" readonly/>
							        </c:when>
							        <c:otherwise>
							            <input id="PUNISH_REMARK" name="PUNISH_REMARK" json="PUNISH_REMARK"  inputtype="string" autocheck="true"  size="35"  label="处罚意见"  value="${rst.PUNISH_REMARK}" maxlength="250"/>
							        </c:otherwise>
							     </c:choose>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								保证金：
							</td>
							<td width="35%" class="detail_content"> 
								<input type="text" id="DEPOSIT" name="DEPOSIT" json="DEPOSIT"  label="保证金"   maxlength="11"  inputtype="float"   valueType="8,2"  autocheck="true" size="35"
								<c:if test="${pvg.PVG_EDIT ne 1 and pvg.PVG_AUDIT_EDIT_DDEPOSIT ne 1 }"> readonly </c:if>  autocheck="true" value="${rst.DEPOSIT}" onblur="getReturnAmount()">
							</td>
							<td width="20%" align="right" class="detail_label">
								装修天数：
							</td>
							<td width="35%" class="detail_content">
								 <input type="text" id="RNVTN_DAYS" name="RNVTN_DAYS"  readonly json="RNVTN_DAYS"  label="装修天数" inputtype="string" maxlength="11"
								autocheck="true" maxvalue="${rst.RNVTN_DAYS}" size="35" >
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								保证金返还金额：
							</td>
							<td width="35%" class="detail_content">
								<input json="DEPOSIT_RETURN_AMOUNT" name="DEPOSIT_RETURN_AMOUNT" id="DEPOSIT_RETURN_AMOUNT" type="text"  inputtype="float"   valueType="8,2"  autocheck="true"
								readonly 	label="保证金返还金额"  value="${rst.DEPOSIT_RETURN_AMOUNT}" size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								实际报销金额：
							</td>
							<td width="35%" class="detail_content">
								 <input json="REAL_REIT_AMOUNT" name="REAL_REIT_AMOUNT" id="REAL_REIT_AMOUNT" type="text" inputtype="float"   valueType="8,2"  inputtype="string" mustinput="true" autocheck="true" maxlength="11" size="35"
									 <c:if test="${pvg.PVG_EDIT ne 1 and pvg.PVG_AUDIT_EDIT_DREAL_REIT_AMOUNT ne 1 }"> readonly </c:if>  label="实际报销金额"  value="${rst.REAL_REIT_AMOUNT}" onkeyup="countCanReturnAmount();" onblur="countCanReturnAmount();"/>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								报销政策：
							</td>
							<td width="35%" class="detail_content">
								<input json="REIT_POLICY" name="REIT_POLICY" id="REIT_POLICY" maxlength="50"
								readonly 	type="text" label="报销政策"  value="${rst.REIT_POLICY}" size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								共需报销次数：
							</td>
							<td width="35%" class="detail_content">
								<input json="TOTAL_REITED_NUM" name="TOTAL_REITED_NUM" id="TOTAL_REITED_NUM"
									readonly type="text" label="共需报销次数"  value="${rst.TOTAL_REITED_NUM}" size="35" />
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								当前报销次数：
								<input type="hidden" name="REITED_NUM" id="REITED_NUM" json="REITED_NUM" label="当前报销次数"  size="35"  value="${rst.REITED_NUM}"  /> 
							</td>
							<td width="35%" class="detail_content" id="reitedNumTd"  <c:if test="${pvg.PVG_EDIT ne 1}">  disabled   </c:if> ><br><br></td>
							<td width="20%" align="right" class="detail_label">
								当前报销次数报销比例：
							</td>
							<td width="35%" class="detail_content">
								<input json="REITED_RATE" name="REITED_RATE" id="REITED_RATE"
									type="text" label="当前报销次数报销比例"  readonly size="35"
									value="<c:if test="${rst.REITED_RATE eq 0}">${rst.REITED_RATE}</c:if> <c:if test="${rst.REITED_RATE ge 0}">${rst.REITED_RATE}%</c:if>" />
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								当前可报销金额：
							</td>
							<td width="35%" class="detail_content">
								<input json="CAN_RETURN_AMOUNT" name="CAN_RETURN_AMOUNT" id="CAN_RETURN_AMOUNT" inputtype="float"   valueType="8,2"  autocheck="true"
									type="text" readonly  label="当前可报销金额"  value="${rst.CAN_RETURN_AMOUNT}" size="35" />
							</td>
						 
							<td width="18%" align="right" class="detail_label">
							  累计已经报销金额：
							</td>
							<td width="35%" class="detail_content">
			                     <input id="TOTAL_RETURN_AMOUNT" json="TOTAL_RETURN_AMOUNT" name="TOTAL_RETURN_AMOUNT" label="累计已经报销金额"  readonly inputtype="float"   valueType="8,2"  autocheck="true" value="${rst.TOTAL_RETURN_AMOUNT}" size="35" /> 
							</td> 
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label">
								剩余可报销金额：
							</td>
							<td width="35%" class="detail_content">
							<input json="LEFT_CAN_RETURN_AMOUNT" name="LEFT_CAN_RETURN_AMOUNT"
									id="LEFT_CAN_RETURN_AMOUNT" type="text" label="剩余可报销金额"  inputtype="float"  readonly  valueType="8,2"  autocheck="true"
									value="${rst.LEFT_CAN_RETURN_AMOUNT}" size="35" />
							</td>
							<td width="20%" align="right" class="detail_label">
								合同销售金额（万元）：
							</td>
							<td width="35%" class="detail_content">
								<input json="SALE_COMPACT_AMOUNT" name="COMPACT_AMOUNT" inputtype="float"   valueType="8,2"  autocheck="true"
									readonly id="SALE_COMPACT_AMOUNT" type="text" label="合同销售金额（万元）"  
									value="${rst.SALE_COMPACT_AMOUNT}" size="35" />
							</td>
						</tr>
                        <tr id="ATT_PATH_TR">
							<td width="20%" align="right" class="detail_label"> 报销相关附件： </td>
							<td width="35%" class="detail_content" colspan="3" id="ATT_PATH_TD">
						       <input type="hidden" json="ATT_ID" id="ATT_ID" name="ATT_ID" value="${rst.ATT_ID}"/>
						    	 <c:if test="${pvg.PVG_EDIT ne 1 and pvg.PVG_AUDIT_EDIT_DATT_PATH ne 1 }"> 
    	                          <input type="hidden" id="ATT_PATH" name="ATT_PATH" value="${rst.ATT_PATH}" size="35" />
						    	 </c:if> 
						    	 <c:if test="${pvg.PVG_EDIT eq 1 or pvg.PVG_AUDIT_EDIT_DATT_PATH eq 1}"> 
						    	     <input type="hidden" json="ATT_PATH" name="ATT_PATH" id="ATT_PATH" type="text" label="报销相关附件"  value="${rst.ATT_PATH}" size="35" /> 
						    	 </c:if> 
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label"> 备注： </td>
							<td width="35%" class="detail_content" colspan="3" <c:if test="${pvg.PVG_EDIT ne 1}">  disabled   </c:if>  >
								<textarea json="REMARK" name="REMARK" id="REMARK" label="备注"  autocheck="true" inputtype="string"   maxlength="250"   maxlength="250" 
									rows="4" cols="28%" >${rst.REMARK}</textarea>
							</td>
						</tr>
						<tr>
							<td width="20%" align="right" class="detail_label"> 申请人： </td>
							<td width="35%" class="detail_content" >
							<input json="REQ_ID" name="REQ_ID" id="REQ_ID" type="hidden" label="申请人ID"  value="${rst.REQ_ID}" size="35" />
							<input json="REQ_NAME" name="REQ_NAME" id="REQ_NAME" type="text" label="申请人"  autocheck="true" mustinput="true" inputtype="string" readonly value="${rst.REQ_NAME}" size="35" />
							</td>
							<td width="20%" align="right" class="detail_label"> 申请时间： </td>
							<td width="35%" class="detail_content"  >
							<input json="REQ_DATE" name="REQ_DATE" id="REQ_DATE" type="text" label="申请时间" mustinput="true" inputtype="string"  readonly="readonly"   value="${rst.REQ_DATE}" size="35" />
							</td>
						</tr> 
					</table>
				</td>
			</tr>
		</table>
	</form>
   </div>
   <%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
   <script type="text/javascript">
        uploadFile('PIC', 'SAMPLE_DIR', true,true,true);
        uploadFile('ZHUANGXIUSQ', 'SAMPLE_DIR', true,true,true);
        uploadFile('YBXIANG', 'SAMPLE_DIR', true,true,true);
        uploadFile('ZXPIC', 'SAMPLE_DIR', true,true,true);
        uploadFile('MCYSTAB', 'SAMPLE_DIR', true,true,true);
        uploadFile('ZGYANSHOU', 'SAMPLE_DIR', true,true,true);
        uploadFile('ZXFAPIAO', 'SAMPLE_DIR', true,true,true);
   </script>
</body>
	
	



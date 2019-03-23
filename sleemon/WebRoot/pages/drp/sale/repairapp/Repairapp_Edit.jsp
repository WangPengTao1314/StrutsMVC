<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_Edit
 * @author chenj
 * @time   2013-11-03 16:25:12 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//repairapp/Repairapp_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"></td>
		<td>当前位置：销售管理>>返修管理>>返修申请</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
          <input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用'">
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
               		<td width="15%" align="right" class="detail_label">返修单编号：</td>
				    <td width="35%" class="detail_content">
				  	<input json="ERP_REPAIR_ORDER_NO" name="ERP_REPAIR_ORDER_NO" autocheck="true" label="返修单编号"  type="text"   inputtype="string"     mustinput="true"  
                        maxlength="30" readonly <c:if test="${isNew == true}"> value="自动生成"</c:if>
						<c:if test="${isNew == false}">value="${rst.ERP_REPAIR_ORDER_NO}"</c:if>>
				   </td>
                   
                   <td width="15%" align="right" class="detail_label">预计返修日期：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="REQ_FINISH_DATE"  id="REQ_FINISH_DATE" name="REQ_FINISH_DATE" label="预计返修日期" readonly="readonly" mustinput="true" inputtype="string" autocheck="true" onclick="SelectDate();"   value="${rst.REQ_FINISH_DATE}">
	   				 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(REQ_FINISH_DATE);"  >
				   </td>
               </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">出库库房编号：</td>
                   
				   <td width="35%" class="detail_content">
				   	<input type="hidden" id="selectParams1"  value="  DEL_FLAG=0 and state='启用' and BEL_CHANN_ID='${ZTXXID}'" />
				    <input json="STOREOUT_STORE_ID" type="hidden"  id="STOREOUT_STORE_ID"  name="STOREOUT_STORE_ID"   
				    	value="${rst.STOREOUT_STORE_ID}"/>
				    <input  type="hidden"  id="STOREOUT_STORE_ID_OLD"  name="STOREOUT_STORE_ID_OLD" value="${rst.STOREOUT_STORE_ID}"/>
				    <input json="STORAGE_FLAG" type="hidden"  id="STORAGE_FLAG"  name="STORAGE_FLAG"   
				    	value="${rst.STORAGE_FLAG}"/>
				    		
   					<input json="STOREOUT_STORE_NO" type="text" id="STOREOUT_STORE_NO" label="出库库房编号" name="STOREOUT_STORE_NO"  
	   					readonly  mustinput="true" inputtype="string" autocheck="true" value="${rst.STOREOUT_STORE_NO}"/>
   					
   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"   
						onClick="selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID', 'forms[0]','STOREOUT_STORE_NO,STOREOUT_STORE_NAME','STORE_NO,STORE_NAME', 'selectParams1');clearChild();"  >
				   </td>
                  <td width="15%" align="right" class="detail_label">出库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME" autocheck="true" label="出库库房名称"  type="text"  inputtype="string"  
                     	readonly  mustinput="true" maxlength="100"   value="${rst.STOREOUT_STORE_NAME}"/>
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">返修方编号：</td>
                   
				   <td width="35%" class="detail_content">
				   	<input type="hidden" id="selectParams"  value="" />
				    <input json="REPAIR_CHANN_ID" type="hidden"  id="REPAIR_CHANN_ID"  name="REPAIR_CHANN_ID"   
				    	<c:if test="${isNew == true}"> value="${area.CHANN_ID}"</c:if><c:if test="${isNew == false}">value="${rst.REPAIR_CHANN_ID}"</c:if>/>
   					<input json="REPAIR_CHANN_NO" type="text" id="REPAIR_CHANN_NO" label="返修方编号" name="REPAIR_CHANN_NO"  readonly autocheck="true" mustinput="true" inputtype="string" 
   						<c:if test="${isNew == true}"> value="${area.CHANN_NO}"</c:if><c:if test="${isNew == false}">value="${rst.REPAIR_CHANN_NO}"</c:if>/>
   					
   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"   
						onClick="selCommon('BS_19', false, 'REPAIR_CHANN_ID', 'CHANN_ID', 'forms[0]','REPAIR_CHANN_NO,REPAIR_CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME','CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME', '');"  >
				   </td>
                  <td width="15%" align="right" class="detail_label">返修方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="REPAIR_CHANN_NAME" name="REPAIR_CHANN_NAME" autocheck="true" label="返修方名称"  type="text"  inputtype="string"  readonly  mustinput="true" maxlength="100"  
                     	<c:if test="${isNew == true}"> value="${area.CHANN_NAME}"</c:if><c:if test="${isNew == false}">value="${rst.REPAIR_CHANN_NAME}"</c:if>/>
				   </td>
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">返修方收货地址编号：</td>
				   <td width="35%" class="detail_content" >
				      <input type="hidden" id="DELIVER_ADDR_ID" json="DELIVER_ADDR_ID" name="DELIVER_ADDR_ID"  value="${rst.DELIVER_ADDR_ID}"/>
				      <input type="text" id="DELIVER_ADDR_NO" json="DELIVER_ADDR_NO" name="DELIVER_ADDR_NO" label="返修方收货地址编号"   autocheck="true" inputtype="string" mustinput="true"   value="${rst.DELIVER_ADDR_NO}" readonly />
                      <img align="absmiddle" name="selAddr" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="setAddrParams();selCommon('BS_76', false, 'DELIVER_ADDR_ID', 'DELIVER_ADDR_ID', 'forms[0]','DELIVER_ADDR_ID,DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'DELIVER_ADDR_ID,DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams')"> 
				   </td>
				   
				   <td width="18%" align="right" class="detail_label">详细地址：</td>
				   <td width="35%" class="detail_content" >
				     <input type="text"  id="DELIVER_DTL_ADDR" json="DELIVER_DTL_ADDR" name="DELIVER_DTL_ADDR"  autocheck="true" inputtype="string" mustinput="true"   maxlength="250" readonly="readonly"  label="详细地址"  value="${rst.DELIVER_DTL_ADDR}" style="width: 270px;"/>
				   </td>
               
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注" rows="4" cols="100%" >${rst.REMARK}</textarea>
				   
                   
                   
                    <input json="AREA_ID" type="hidden"  id="AREA_ID"  name="AREA_ID"   
			    		<c:if test="${isNew == true}"> value="${area.AREA_ID}"</c:if><c:if test="${isNew == false}">value="${rst.AREA_ID}"</c:if>/>
			    	<input json="AREA_NO" type="hidden"  id="AREA_NO"  name="AREA_NO"   
			    		<c:if test="${isNew == true}"> value="${area.AREA_NO}"</c:if><c:if test="${isNew == false}">value="${rst.AREA_NO}"</c:if>/>
			    	<input json="AREA_NAME" type="hidden" id="AREA_NAME"  name="AREA_NAME"   
			    		<c:if test="${isNew == true}"> value="${area.AREA_NAME}"</c:if><c:if test="${isNew == false}">value="${rst.AREA_NAME}"</c:if>/>
			    		
			    		
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
 
<!--
 * @prjName: 信用额度变更申请
 * @fileName:Grant_Frame
 * @author zhang_zhongbin
 * @version 1.1
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/creditreq/CreditReq_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
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

    <!--占位用table，以免显示数据被浮动层挡住-->
 <table width="100%" height="25px">
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
</table>	
		
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
      <td>
         <form method="POST" action="#" id="mainForm" >
	       <table id="jsontb" width="100%" border="0" cellpadding="4"
							cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
	                  <input type="hidden" name="selectParams"	value=" STATE = '启用' and DEL_FLAG='0' " />
	                  <input type="hidden" name="selectPYXXParams"	value=" RYZT in( '启用') and DELFLAG='0' and jgxxid in (select jgxxid from T_SYS_JGXX where ZTXXID='${ZTXXID}') " />
		               <tr>
		                   <td width="15%" align="right" class="detail_label">信用变更申请单号：</td>
						   <td width="35%" class="detail_content">
						    <input json="CREDIT_REQ_ID" name="CREDIT_REQ_ID" type="text" autocheck="true" label="信用变更申请单号" 
						       inputtype="string" mustinput="true"
						       readonly <c:if test="${isNew == true}"> value="自动生成"</c:if>
								<c:if test="${isNew == false}">value="${rst.CREDIT_REQ_ID}"</c:if>>
					       </td>
					
		                   <td width="15%" align="right" class="detail_label">渠道编号：</td>
						   <td width="35%" class="detail_content">
		                     <input  type="text" name="CHANN_NO" json="CHANN_NO"  autocheck="true" label="渠道编号"    
		                       inputtype="string"  readonly maxlength="30" mustinput="true" value="${rst.CHANN_NO}"/> 
		                       
		                     <input type="hidden" name="CHANN_ID" id="CHANN_ID"  json="CHANN_ID" value="${rst.CHANN_ID}" />  
		                     
<!--						  	<img align="absmiddle" name="selJGXX" style="cursor: hand"-->
<!--								src="${ctx}/styles/${theme}/images/plus/select.gif"       -->
<!--								onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME,AREA_NO,AREA_NAME', 'CHANN_NO,CHANN_NAME,AREA_NO,AREA_NAME', 'selectParams')">-->
						   </td>
		               </tr>
		               <tr>
		                   <td width="15%" align="right" class="detail_label">渠道名称：</td>
						   <td width="35%" class="detail_content">
		                     <input type="text" name="CHANN_NAME" json="CHANN_NAME"  autocheck="true" label="渠道名称" 
		                      inputtype="string" readonly  mustinput="true"  maxlength="100"  value="${rst.CHANN_NAME}"/> 
						   </td>
		                   <td width="15%" align="right" class="detail_label">区域编号：</td>
						   <td width="35%" class="detail_content">
		                    
		                     <input type="text"  name="AREA_NO"  json="AREA_NO" autocheck="true" label="区域编号"    
		                         inputtype="string"  readonly       maxlength="30"  value="${rst.AREA_NO}"/> 
		                         
		                     <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />  
		                   <!--  
						    <img align="absmiddle" name="selJGXX" style="cursor: hand"
								src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">
						  -->  
						   </td>
		               </tr>
		               <tr>
		                   <td width="15%" align="right" class="detail_label">区域名称：</td>
						   <td width="35%" class="detail_content">
		                     <input json="AREA_NAME" name="AREA_NAME" autocheck="true" label="区域名称"  type="text"   
		                      inputtype="string"   readonly   maxlength="22"  value="${rst.AREA_NAME}"/>
						   </td>
		                   <td width="15%" align="right" class="detail_label">原信用额度：</td>
						   <td width="35%" class="detail_content">
		                     <input json="OLD_CREDIT_TOTAL" name="OLD_CREDIT_TOTAL" autocheck="true"
		                       label="原信用额度"  type="text" readonly  inputtype="float" valueType="8,2"  maxlength="11"  value="${rst.OLD_CREDIT_TOTAL}"/> 
						   </td>
		               </tr>
		                <tr>
		                   <td width="15%" align="right" class="detail_label">变更后信用额度：</td>
						   <td width="35%" class="detail_content">
		                     <input json="NEW_CREDIT_TOTAL" name="NEW_CREDIT_TOTAL" autocheck="true" 
		                        label="变更后信用额度"  mustinput="true" type="text"   inputtype="float" valueType="8,2"  maxlength="11"  value="${rst.NEW_CREDIT_TOTAL}"/> 
						   </td>
						   
						   <td width="15%" align="right" class="detail_label">申请人：</td>
						   <td width="35%" class="detail_content">
		                    
		                     <input type="hidden" name="REQ_PSON_ID" id="REQ_PSON_ID" json="REQ_PSON_ID" value="${rst.REQ_PSON_ID}"/>
		                     <input type="text"  json="REQ_PSON_NAME" name="REQ_PSON_NAME" autocheck="true" label="申请人"  
		                         inputtype="string"     mustinput="true"  readonly  maxlength="30"  value="${rst.REQ_PSON_NAME}"/> 
		                       <img align="absmiddle" name="selJGXX" style="cursor: hand"
								src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selCommon('BS_0', false, 'REQ_PSON_ID', 'RYXXID', 'forms[0]','REQ_PSON_NAME', 'XM', 'selectPYXXParams')">
						   </td>
		               </tr>
		               
		                <tr>
		                   <td width="15%" align="right" class="detail_label">备注：</td>
						   <td width="35%" class="detail_content" colspan="3">
		                     <textarea cols="60" json="REMARK" name="REMARK" autocheck="true" 
								inputtype="string" label="备注" rows="2" maxlength="250">${rst.REMARK}</textarea>
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
</div>
</body>
</html>
 
 
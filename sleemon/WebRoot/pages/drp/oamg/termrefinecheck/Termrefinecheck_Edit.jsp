<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Edit
 * @author lyg
 * @time   2014-01-26 14:46:31 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/termrefinecheck/Termrefinecheck_Edit.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/termrefinecheck/Termrefinecheck_Edit_T.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;门店稽查管理&gt;门店精致化检查结果维护编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
       <input type="hidden" name="selectParams" value=" STATE in( '启用') and DEL_FLAG='0' and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}')">
       <input type="hidden" name="selectOrg" value="  DELFLAG='0' and ZTXXID='${LEDGER_ID}'">
       <input type="hidden" name="selectParamsT" id="selectParamsT" value=""/>
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">门店精致化检查单号：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_REFINE_CHECK_NO" name="TERM_REFINE_CHECK_NO" autocheck="true" label="门店精致化检查单号"  type="text"   inputtype="string"   readonly    mustinput="true"  size="40"   maxlength="30"
                     <c:if test="${rst.TERM_REFINE_CHECK_NO==null}"> value="自动生成"</c:if>
                     <c:if test="${rst.TERM_REFINE_CHECK_NO!=null}">value="${rst.TERM_REFINE_CHECK_NO}"</c:if>  
                    /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
				   	  <input json="TERM_ID" id="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/> 
                     <input json="TERM_NO" name="TERM_NO" id="TERM_NO" autocheck="true" label="终端编号"  type="text"   inputtype="string"  size="40"   readonly    mustinput="true"     maxlength="30"  value="${rst.TERM_NO}"/>
                      <img align="absmiddle" name="" style="cursor: hand"	src="${ctx}/styles/${theme}/images/plus/select.gif"	onClick="selAdvcorder()"> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" autocheck="true" label="终端名称"  type="text"  size="40"   inputtype="string"   readonly  maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">品牌类型：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="BUSS_SCOPE" name="BUSS_SCOPE" id="BUSS_SCOPE" autocheck="true" label="品牌类型"  type="text"  size="40"   inputtype="string"   readonly maxlength="100"  value="${rst.BUSS_SCOPE}"/>
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" autocheck="true" label="渠道ID" type="hidden" inputtype="string"   value="${rst.CHANN_ID}"/>
				   	 <input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" autocheck="true" label="渠道编号" type="hidden" inputtype="string"   value="${rst.CHANN_NO}"/>
                     <input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" autocheck="true" label="渠道名称"  type="text"   inputtype="string"  size="40"   readonly  maxlength="50"  value="${rst.CHANN_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">渠道电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_TEL" name="CHANN_TEL" id="CHANN_TEL" autocheck="true" label="渠道电话"  type="text"   inputtype="string"  size="40"   readonly maxlength="50"  value="${rst.CHANN_TEL}"/> 
				   </td>
               </tr>
                
               <tr>
                   <td width="15%" align="right" class="detail_label">终端版本：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_VERSION" name="TERM_VERSION" id="TERM_VERSION" autocheck="true" label="终端版本"  type="text"   inputtype="string"  size="40"   readonly maxlength="50"  value="${rst.TERM_VERSION}"/> 
				   </td>
               
                    <td width="15%" align="right" class="detail_label">所属战区：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="AREA_ID" name="AREA_ID" id="AREA_ID" autocheck="true" label="区域ID" type="hidden" inputtype="string"   value="${rst.AREA_ID}"/>
				   	 <input json="AREA_NO" name="AREA_NO" id="AREA_NO" autocheck="true" label="区域编号" type="hidden" inputtype="string"   value="${rst.AREA_NO}"/>
                     <input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" autocheck="true" label="所属战区"  type="text"   inputtype="string"  size="40"   readonly maxlength="50"  value="${rst.AREA_NAME}"/> 
				   </td>
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">区域经理：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="AREA_MANAGE_ID" name="AREA_MANAGE_ID" id="AREA_MANAGE_ID" autocheck="true" label="区域经理ID" type="hidden" inputtype="string"   value="${rst.AREA_MANAGE_ID}"/>
                     <input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" autocheck="true" label="区域经理名称"  size="40"   type="text"   inputtype="string"   readonly         maxlength="30"  value="${rst.AREA_MANAGE_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">精致化得分：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="CHECK_TOTAL_SCORE" name="CHECK_TOTAL_SCORE" id="CHECK_TOTAL_SCORE" type="text"   inputtype="string"  size="40"   readonly     maxlength="50"  value="${rst.CHECK_TOTAL_SCORE}"/>
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">检查机构：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="CHECK_ORG_ID" name="CHECK_ORG_ID" id="CHECK_ORG_ID" autocheck="true" label="检查机构ID" type="hidden" inputtype="string"   value="${rst.CHECK_ORG_ID}"/>
                     <input json="CHECK_ORG_NAME" name="CHECK_ORG_NAME" id="CHECK_ORG_NAME" autocheck="true" label="检查机构名称"  size="40"  type="text"   inputtype="string"   readonly    mustinput="true" value="${rst.CHECK_ORG_NAME}"/>
                     <img align="absmiddle" name="" style="cursor: hand"	src="${ctx}/styles/${theme}/images/plus/select.gif"	onClick="selOrg()"> 
				   </td>
                   
                   <td width="15%" align="right" class="detail_label">检查任务号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="TERM_REFINE_TASK_NO" name="TERM_REFINE_TASK_NO"  size="40"  id="TERM_REFINE_TASK_NO" autocheck="true" label="检查任务号"  type="text"   inputtype="string" mustinput="true"     maxlength="50"  value="${rst.TERM_REFINE_TASK_NO}"/>
				   </td>
			   </tr>
			   <tr>
               		<td width="15%" align="right" class="detail_label">检查日期：</td>
				    <td width="35%" class="detail_content">
	                    <input type="text" json="CHECK_DATE"  size="40"  id="CHECK_DATE"name="CHECK_DATE" autocheck="true" inputtype="string" label="检查日期"  value="${rst.CHECK_DATE}"  mustinput="true"   onclick="SelectTime();" readonly="readonly" />
						<img align="absmiddle" style="cursor:hand"  src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CHECK_DATE);">
				    </td>
				    <td width="15%" align="right" class="detail_label">检查方案编号：</td>
				    <td width="35%" class="detail_content">
	                    <input type="text" json="CHECK_PROGRAM_NO"  size="40"  id="CHANN_CHECK_PLAN_NO" name="CHANN_CHECK_PLAN_NO" autocheck="true" inputtype="string" label="检查方案编号"  value="${rst.CHANN_CHECK_PLAN_NO}"  mustinput="true" readonly="readonly" />
 				        <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCheckPlanNo();getChild();">
 				    </td>
			   </tr>
			   <tr>
			        <td width="15%" align="right" class="detail_label">神秘人评语：</td>
					<td width="35%" class="detail_content">
                         <textarea  json="MYSTIC_CMNR" name="MYSTIC_CMNR" id="MYSTIC_CMNR" autocheck="true" inputtype="string"   maxlength="250"   label="神秘人评语"    rows="4" cols="32%" >${rst.MYSTIC_CMNR}</textarea>
					</td>
			       
					<td width="15%" align="right" class="detail_label">主要扣分问题：</td>
					<td width="35%" class="detail_content">
                         <textarea  json="MAIN_DEDUCT_SCORE_REMARK" name="MAIN_DEDUCT_SCORE_REMARK" id="MAIN_DEDUCT_SCORE_REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="主要扣分问题"    rows="4" cols="32%" >${rst.MAIN_DEDUCT_SCORE_REMARK}</textarea>
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content">
                         <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="32%" >${rst.REMARK}</textarea>
					</td>
					<td width="15%" align="right" class="detail_label">相关资料：</td>
					<td width="35%" class="detail_content">
				         <input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"  type="hidden"   value="${rst.ATT_PATH}"/> 
  				    </td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
    uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
</script>
 
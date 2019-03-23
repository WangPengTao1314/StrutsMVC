<!--
/**
 * @prjName：喜临门营销平台
 * @fileName：Promoreim_Edit
 * @author chenj
 * @time   2014-01-25 19：49：05 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/promoreim/Promoreim_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
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
			<input id="CHANN_ID" name="CHANN_ID" json="CHANN_ID" type='hidden' value="${rst.CHANN_ID}">
			<input id="ZONE_ID" name="ZONE_ID" json="ZONE_ID" type='hidden' value="${rst.ZONE_ID}">
			<input id="REQ_ID" name="REQ_ID" json="REQ_ID" type='hidden' value="${rst.REQ_ID}">
			<input id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" json="AREA_MANAGE_ID" type='hidden' value="${rst.AREA_MANAGE_ID}">
			<input id="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" json="AREA_MANAGE_TEL" type='hidden' value="${rst.AREA_MANAGE_TEL}">
			<input id="PRMT_COST_REQ_ID" name="PRMT_COST_REQ_ID" json="PRMT_COST_REQ_ID" type='hidden' value="${rst.PRMT_COST_REQ_ID}">
		
		<tr>
			<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
                   <td width="15%" align="right" class="detail_label">推广费用报销单号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PRMT_COST_REIT_NO" name="PRMT_COST_REIT_NO" readonly autocheck="true" label="推广费用报销单号"  type="text"   inputtype="string"   size="40"       maxlength="30"  value="${rst.PRMT_COST_REIT_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">关联推广费用申请单号：</td>
				   <td width="35%" class="detail_content">
				   	 <input id="con" json="con" name="con" type="hidden">
                     <input json="PRMT_COST_REQ_NO" name="PRMT_COST_REQ_NO" readonly autocheck="true" label="关联推广费用申请单号"  type="text"   inputtype="string"     size="40"     maxlength="100"  value="${rst.PRMT_COST_REQ_NO}"/>
                     <img align="absmiddle" style="cursor： hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryPromoexpen();">   
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">加盟商编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NO" name="CHANN_NO" autocheck="true" readonly label="加盟商编号"  type="text"   inputtype="string"     size="40"     maxlength="30"  value="${rst.CHANN_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">加盟商名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME" autocheck="true" readonly label="加盟商名称"  type="text"   inputtype="string"    size="40"      maxlength="100"  value="${rst.CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">加盟商联系人：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" readonly autocheck="true" label="加盟商联系人"  type="text"   inputtype="string"   size="40"       maxlength="30"  value="${rst.CHANN_PERSON_CON}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">加盟商联系电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_TEL" name="CHANN_TEL" autocheck="true" readonly label="加盟商联系电话"  type="text"   inputtype="string"   size="40"       maxlength="30"  value="${rst.CHANN_TEL}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">所属战区编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="AREA_NO" name="AREA_NO" autocheck="true" readonly label="所属战区编号"  type="text" readonly  inputtype="string"  size="40"     mustinput="true"     maxlength="22"  value="${rst.AREA_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">所属战区名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="AREA_NAME" name="AREA_NAME" autocheck="true" readonly label="所属战区名称"  type="text" readonly  inputtype="string"   size="40"      maxlength="22"  value="${rst.AREA_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">城市名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="ZONE_ADDR" name="ZONE_ADDR" autocheck="true" readonly label="城市名称"  type="text" readonly  inputtype="string"   size="40"     mustinput="true"  maxlength="250"  value="${rst.ZONE_ADDR}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">区域经理：</td>
				   <td width="35%" class="detail_content">
                     <input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" readonly autocheck="true" label="区域经理" readonly type="text"   inputtype="string"  size="40"    mustinput="true"     maxlength="22"  value="${rst.AREA_MANAGE_NAME}"/> 
				   </td>
               </tr>
               
               <tr>
                   <td width="15%" align="right" class="detail_label">申请人：</td>
				   <td width="35%" class="detail_content">
                     <input json="REQ_NAME" name="REQ_NAME" autocheck="true" readonly label="申请人姓名"  type="text"   inputtype="string"  size="40"     mustinput="true"     maxlength="30"  value="${rst.REQ_NAME}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">申请日期：</td>
				   <td width="35%" class="detail_content">
                     <input json="REQ_DATE" name="REQ_DATE" autocheck="true" readonly label="申请日期"  type="text"  readonly inputtype="string"   size="40"    mustinput="true" value="${rst.REQ_DATE}"/> 
				   </td>
               </tr>
               <tr>
	               <td width="15%" align="right" class="detail_label">执行总结：</td>
				   <td width="35%" class="detail_content" colspan="3">
                     <textarea  json="EXCT_SMRZ" name="EXCT_SMRZ" id="EXCT_SMRZ" autocheck="true" inputtype="string"   maxlength="250"   label="执行总结"   rows="5" cols="32">${rst.EXCT_SMRZ}</textarea> 
				   </td>
	           </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">此次推广费用共计：</td>
				   <td width="35%" class="detail_content">
                     <input id="TOTAL_PRMT_COST" json="TOTAL_PRMT_COST" name="TOTAL_PRMT_COST" label="此次推广费用共计"  type="text"  size="40"  autocheck="true"  inputtype="float" valueType="10,2" maxlength="10"  mustinput="true"     maxlength="22"  value="${rst.TOTAL_PRMT_COST}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">申请报销金额：</td>
				   <td width="35%" class="detail_content">
                     <input id="REIT_REQ_AMOUNT" json="REIT_REQ_AMOUNT" name="REIT_REQ_AMOUNT" label="申请报销金额"      type="text"  size="40"  autocheck="true"  inputtype="float" valueType="10,2" maxlength="10"  value="${rst.REIT_REQ_AMOUNT}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">费用类别：</td>
				   <td width="35%" class="detail_content">
                     <select name=COST_TYPE id="COST_TYPE" json="COST_TYPE" style="width: 275px;"  label="费用类别" inputtype="string"  mustinput="true" ></select> 
				   </td>
                   <td width="15%" align="right" class="detail_label">状态：</td>
				   <td width="35%" class="detail_content">
                     <input json="STATE" name="STATE" autocheck="true" label="状态"  type="text"  size="40"   inputtype="string"   readonly maxlength="22"  value="${rst.STATE}"/> 
				   </td>
               </tr>
               <tr>
		           <td width="15%" align="right" class="detail_label">附件：</td>
				   <td width="35%" class="detail_content" colspan="3">
				   		<input id="STATENEBTS_ATT" json="STATENEBTS_ATT" name="STATENEBTS_ATT" autocheck="true" type="text"   value="${rst.STATENEBTS_ATT}"/>
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
	SelDictShow("COST_TYPE","BS_92","${rst.COST_TYPE}","");
	
	
	/*var path = $("#STATENEBTS_ATT").val();
    var leg = path.split(",").length ;
    var folder = "";
    if(leg>1){
    	for(var i=0;i<leg;i++){
	    	folder = folder+"SAMPLE_DIR,";
	    }
    	folder = folder.substring(0,folder.length-1);
    }else{
    	folder = "SAMPLE_DIR";
    }*/
	
	
	uploadFile('STATENEBTS_ATT',"SAMPLE_DIR", true,true,true);
</script>
 
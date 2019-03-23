<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.jsp"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>退货统计</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;退货统计</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toReturnPageResult" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsTerm" value=" STATE = '启用' and DEL_FLAG = 0  and CHANN_ID_P='${ZTXXID}' ">
						<input type="hidden" name="selectParamsOrder" value=" bill_type='终端退货' and  del_flag='0' and  LEDGER_ID='${ZTXXID}'">
						<input type="hidden" name="selectParamsProduct" value=" STATE in('启用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1  ">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<tr>
							<td width="6%" align="right" class="detail_label">
								终端门店编号:
							</td>
							<td width="10%" class="detail_content">
								<input id="TERM_NO" name="TERM_NO"  json="TERM_NO" style="width:155" value="${params.TERM_NO}"/>
									   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_27', false, 'TERM_NO', 'TERM_NO', 'forms[0]',
												'TERM_NO','TERM_NO','selectParamsTerm');">
							</td>
							<td width="6%" nowrap align="right" class="detail_label">终端门店名称:</td>
							<td width="10%" class="detail_content">
								<input id="TERM_NAME" name="TERM_NAME"  json="TERM_NAME" style="width:155" value="${params.TERM_NAME}"/>
							</td>
							<td width="6%" align="right" class="detail_label">
								销售单号:
							</td>
							<td width="10%" class="detail_content">
								<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  json="ADVC_ORDER_NO" style="width:155" value="${params.ADVC_ORDER_NO}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_38', false, 'ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'forms[0]',
												'ADVC_ORDER_NO','ADVC_ORDER_NO','selectParamsOrder');">
							</td>
							<td width="6%" nowrap align="right" class="detail_label">货品编号:</td>
							<td width="10%" class="detail_content">
								<input json="PRD_NO" name="PRD_NO" id="PRD_NO" type="text"   value="" label="货品编号" value="${params.PRD_NO}">	
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_21', false, 'PRD_NO', 'PRD_NO', 'forms[0]',
												'PRD_NAME','PRD_NAME','selectParamsProduct');">
							</td>
						</tr>
						<tr>
						    <td width="6%" align="right" class="detail_label"nowrap="nowrap">
								销售日期从:
							</td>
							<td width="10%" class="detail_content">
								<input json="START_SALE_DATE" name="START_SALE_DATE" id="START_SALE_DATE" type="text" value="${params.START_SALE_DATE}" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(START_SALE_DATE);">
							</td>
							<td width="6%" align="right" class="detail_label"nowrap="nowrap">
								销售日期到:
							 </td>
							 <td width="10%" class="detail_content">
							    <input json="END_SALE_DATE" name="END_SALE_DATE" id="END_SALE_DATE" type="text" value="${params.END_SALE_DATE}" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_SALE_DATE);">
							 </td>
							<td width="6%" align="right" class="detail_label">退货日期从:</td>
							<td width="10%" class="detail_content">
								<input json="START_RT_DATE" name="START_RT_DATE" id="START_RT_DATE" type="text" value="${params.START_RT_DATE}" onclick="SelectDate()">
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(START_RT_DATE);">
							</td>
							<td width="6%" align="right" class="detail_label">退货日期到:</td>
							<td width="10%" class="detail_content"> 
							   <input json="END_RT_DATE" name="END_RT_DATE" id="END_RT_DATE" type="text" value="${params.END_RT_DATE}" onclick="SelectDate()">
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_RT_DATE);">
							</td>
						</tr>
						<tr>
						    <td width="6%" nowrap align="right" class="detail_label">客户名称:</td>
							<td width="10%" class="detail_content">
								<input id="CUST_NAME" name="CUST_NAME"  json="CUST_NAME" style="width:155" value="${params.CUST_NAME}"/>
							</td>
							<td width="6%" align="right" class="detail_label"nowrap="nowrap">电话:</td>
							<td width="10%" class="detail_content">
								<input id="TEL" name="TEL"  json="TEL" style="width:155" value="${params.TEL}"/>
							</td>
						    <td width="6%" align="right" class="detail_label"nowrap="nowrap">货品名称:</td>
							<td width="10%" class="detail_content">
								<input json="PRD_NAME" name="PRD_NAME" id="PRD_NAME" type="text"   value="${params.PRD_NAME}" value="${params.PRD_NAME}">	
							</td>
							<td width="6%" align="right" class="detail_label"nowrap="nowrap">
								货品分类编号:
							</td>
							<td width="10%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PAR_PRD_NO" name="PAR_PRD_NO" type="text"  value="${params.PAR_PRD_NO}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_NO,PAR_PRD_NAME','PRD_NO,PRD_NAME','selectParamsParPro');"/>
							</td>
						   </tr>
						   <!--  
						   <tr>
							<td width="6%" align="right" class="detail_label">
								货品分类名称：
							</td>
							<td width="10%" class="detail_content">
								<input id="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text"  value="${params.PAR_PRD_NAME}"/>
							</td>
						</tr>-->
						<tr>
							<td colspan="10" align="center" valign="middle"
								class="detail_btn">
								<input id="qurey" type="button" class="btn" value="查 询(Q)" title="Alt+O" accesskey="O">
								&nbsp;&nbsp;
								<input id="reset" type="reset" class="btn" value="重 置(R)" title="Alt+R" accesskey="R">
							</td>	
							<td style="display:none;">
								<input id="conDition" name="conDition" type="hidden" value="" >
								<input id="rptModel" name="rptModel" type="hidden" value="" >
								<input id="cutSql" name="cutSql" type="hidden" value="" >
							</td>												
						</tr>
					</table>
			</td>
		</tr>
	</table>
</form>

</body>
<script language="JavaScript">
	//SelDictShow("PRD_TYPE","BS_171","${params.PRD_TYPE}","");
													
$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		var obj = parent.document.getElementById("butHidTop");
	 	$(obj).trigger("click");
		$("#queryForm").submit();
	});	
});
	 
  
  function siglePackParamData(tableid){
		if(null == tableid){
			tableid = "jsontb";
		}
		var paramData = "";
		var inputs = $("#"+tableid+" :input");
		inputs.each(function(){
			if(null != $(this).attr("json")){
				var key;
				var value; 
				var type = $(this).attr("type");
				if("checkbox" == type){
					key = $(this).attr("json");
					if($(this).attr("checked")){
						value= 1;
					}else{
						value= 0;
					}
				}else if("radio" == type){
					if($(this).attr("checked")){
						key = $(this).attr("json");
						value= $(this).attr("value");
					}
				}else{
					key = $(this).attr("json");
					value= $(this).attr("value");
				}
				var inputtype = $(this).attr("inputtype");
				paramData = paramData+ "" + key + "=" + inputtypeFilter(inputtype,value) +"!";
			}
		});
		return 	paramData = paramData.substr(0,paramData.length-1);
	}
</script>




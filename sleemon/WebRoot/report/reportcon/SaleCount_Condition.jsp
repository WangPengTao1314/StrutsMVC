<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	 
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>门店销售统计</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;门店销售统计</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toReportPageResult" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsArea" value="bill_type='终端销售' and del_flag='0' and LEDGER_ID='${ZTXXID}' and TERM_ID in ${TERM_CHARGE}" >
						<input type="hidden" name="selectMoteParams" value=" DEL_FLAG='0' and STATE='启用' and LEDGER_ID = '${ZTXXID}'">
						<input type="hidden" name="selectParamsTerm" value=" del_flag='0' and TERM_ID in ${TERM_CHARGE} and STATE='启用' ">
						<input type="hidden" name="selectParamsPrd" value=" STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${ZTXXID}'))   and IS_NO_STAND_FLAG=0 ">
						<input type="hidden" name="selectParamsParPrd" value=" STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=0  ">
						<input type="hidden" name="selectParamsCreName" value=" DELFLAG=0 and BMXXID in  ${TERM_CHARGE} and RYJB in ('门店_导购员','门店_店长') and RYZT='启用' ">
						<tr>
							<td width="10%" align="right" class="detail_label">
								订单号：
							</td>
							<td width="15%" class="detail_content">
								<input id="ADVC_ORDER_ID" name="ADVC_ORDER_ID"  json="ADVC_ORDER_ID" type="hidden" style="width:155" value="${params.ADVC_ORDER_ID}"/>
								<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  json="ADVC_ORDER_NO" style="width:155" value="${params.ADVC_ORDER_NO}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_55', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[0]',
												'ADVC_ORDER_NO','ADVC_ORDER_NO','selectParamsArea');">
							</td>
							<td width="10%" align="right" class="detail_label">
								门店名称：
							</td>
							<td width="15%" class="detail_content">
								<input id="TERM_ID" name="TERM_ID"  json="TERM_ID" style="width:155" value="${params.TERM_ID}" type="hidden"/>
								<input id="TERM_NAME" name="TERM_NAME"  json="TERM_NAME" style="width:155" value="${params.TERM_NAME}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_27', true, 'TERM_ID', 'TERM_ID', 'forms[0]',
												'TERM_NAME','TERM_NAME','selectParamsTerm');">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">销售日期从：</td>
							<td width="20%" class="detail_content">
								<input json="START_SALE_DATE" name="START_SALE_DATE" id="START_SALE_DATE" type="text"  autocheck="true" inputtype="date"   value="" label="销售日期从" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(START_SALE_DATE);">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">销售日期到：</td>
							<td width="15%" class="detail_content">
								<input json="END_SALE_DATE" name="END_SALE_DATE" id="END_SALE_DATE" type="text"  autocheck="true"  inputtype="date" value="" label="销售日期到" onclick="SelectDate()">
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_SALE_DATE);">
							</td>	
						</tr>
						
						<tr>
							<td width="10%" align="right" class="detail_label">
								货品编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" style="width:155" value="${params.PRD_NO}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_21', true, 'PRD_NO', 'PRD_NO', 'forms[0]',
												'PRD_NO,PRD_NAME','PRD_NO,PRD_NAME','selectParamsPrd');">
							</td>
							<td width="10%" align="right" class="detail_label">
								货品名称：
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_NAME" name="PRD_NAME"  json="PRD_NAME" style="width:155" value="${params.PRD_NAME}"/>	   					
							</td>
							<td width="10%" align="right" class="detail_label">
								产品类别：
							</td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID" name="PAR_PRD_ID"  json="PAR_PRD_ID" type="hidden" style="width:155" value="${params.PAR_PRD_ID}"/>
								<input id="PAR_PRD_NAME" name="PAR_PRD_NAME"  json="PAR_PRD_NAME" style="width:155" value="${params.PAR_PRD_NAME}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]',
												'PAR_PRD_NAME','PRD_NAME','selectParamsParPrd');">
							</td>
							
							
							<td width="10%" align="right" class="detail_label">
								导购员：
							</td>
							<td width="15%" class="detail_content">
								<input id="CRE_NAME" name="CRE_NAME"  json="CRE_NAME" style="width:155" value="${params.CRE_NAME}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_0', true, 'CRE_NAME', 'XM', 'forms[0]',
												'CRE_NAME','XM','selectParamsCreName');">
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">发货日期从：</td>
							<td width="20%" class="detail_content">
								<input json="ORDER_RECV_DATE_BEGIN" name="ORDER_RECV_DATE_BEGIN" id="ORDER_RECV_DATE_BEGIN" type="text"  autocheck="true" inputtype="date"   value="" label="发货日期从" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE_BEGIN);">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">发货日期到：</td>
							<td width="15%" class="detail_content">
								<input json="ORDER_RECV_DATE_END" name="ORDER_RECV_DATE_END" id="ORDER_RECV_DATE_END" type="text"  autocheck="true"  inputtype="date" value=""  label="发货日期到" onclick="SelectDate()">
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE_END);">
							</td>	
							<td width="10%" nowrap align="right" class="detail_label">查询纬度：</td>
							<td width="20%" class="detail_content">
								 <input type="hidden" name="QUERYFLAG" id="QUERYFLAG"/>
			                     <input type="radio" name="radio" onclick="upFlag('1')" checked="checked">货品
			                     <input type="radio" name="radio" onclick="upFlag('2')">导购
			                     <input type="radio" name="radio" onclick="upFlag('3')">品类
			                     <input type="radio" name="radio" onclick="upFlag('4')">门店
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">活动名称：</td>
							<td width="15%" class="detail_content">
								<input json="PROMOTE_NAME" name="PROMOTE_NAME" autocheck="true" label="活动名称" type="text" inputtype="string"  />
								<input json="PROMOTE_NO" name="PROMOTE_NO" autocheck="true" label="活动编号"  type="hidden"   inputtype="string"   />
								<img align="absmiddle" style="cursor： hand" src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" 
			                     	onClick="selCommon('BS_112', true, 'PROMOTE_NO', 'PROMOTE_NO', 'forms[0]','PROMOTE_NO,PROMOTE_NAME','PROMOTE_NO,PROMOTE_NAME','selectMoteParams')">
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">合同编号：</td>
							<td width="20%" class="detail_content">
			                     <input json="CONTRACT_NO" name="CONTRACT_NO" autocheck="true" label="合同编号"  type="text"   inputtype="string"   />
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">客户名称：</td>
							<td width="15%" class="detail_content">
								<input json="CUST_NAME" name="CUST_NAME" autocheck="true" label="客户名称" type="text" inputtype="string"  />
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">发货状态：</td>
							<td width="15%" class="detail_content">
								<select json="SEND_STATE" id="SEND_STATE" name="SEND_STATE" style="width:155" autocheck="true"  inputtype="string"></select>
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">门店分类：</td>
							<td width="15%" class="detail_content">
								<select json="TERM_CLASS" id="TERM_CLASS" name="TERM_CLASS" style="width:155" autocheck="true"  inputtype="string"></select>
							</td>	
						</tr>
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
	SelDictShow("TERM_CLASS","BS_160","","");
	SelDictShow("SEND_STATE","BS_161","","");
$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
//		var ORDER_RECV_DATE_BEGIN=$("#ORDER_RECV_DATE_BEGIN").val();
//		var ORDER_RECV_DATE_END=$("#ORDER_RECV_DATE_END").val();
//		var START_SALE_DATE=$("#START_SALE_DATE").val();
//		var END_SALE_DATE=$("#END_SALE_DATE").val();
//		var flag=true;
//		if(""==ORDER_RECV_DATE_BEGIN||""==ORDER_RECV_DATE_END){
//			flag=false;
//		}
//		if((""==START_SALE_DATE||""==END_SALE_DATE)&&!flag){
//			alert("发货日期和销售日期至少选择一种");
//			return;
//		}
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
	function upFlag(flag){
		$("#QUERYFLAG").val(flag);
	}
</script>




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
	<title>待确认预订单统计</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;门店报表&gt;&gt;待确认预订单统计 </td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toQueryUncomm" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
					<input type="hidden" name=selectParamsTerm value="STATE in ('启用','停用') and DEL_FLAG=0 and CHANN_ID_P='${ZTXXID}'">
					<input type="hidden" name="selectParam" value="RYZT in ('启用','停用') and DELFLAG=0 and JGXXID in (select JGXXID from T_SYS_JGXX where ZTXXID ='${ZTXXID}' ) ">
					<input type="hidden" name="selectMoteParams" value=" DEL_FLAG='0' and STATE='启用' and LEDGER_ID = '${ZTXXID}'">
						<tr>
							<td width="8%" nowrap align="right" class="detail_label">预订单编号:</td>
							<td width="15%" class="detail_content">
			   					<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"   />
							</td>
							<td width="10%" nowrap align="right" class="detail_label">终端编号:</td>
							<td width="15%" class="detail_content">
								<input id="TERM_ID" name="TERM_ID"  json="TERM_ID" type="hidden" readonly style="width:155"  />
								<input id="TERM_NO" name="TERM_NO"  json="TERM_NO"  type="text" style="width:155"  />
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_27', true, 'TERM_ID', 'TERM_ID', 'forms[0]',
										'TERM_ID,TERM_NO,TERM_NAME', 
										'TERM_ID,TERM_NO,TERM_NAME',
										'selectParamsTerm');"> 
							</td>
							<td width="10%" nowrap align="right" class="detail_label">终端名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="TERM_NAME" name="TERM_NAME"  json="TERM_NAME" type="text" style="width:155" />
							</td>
							<td width="8%" nowrap align="right" class="detail_label">业务员:</td>
							<td width="15%" class="detail_content">
			   					<input id="SALE_PSON_NAME" name="SALE_PSON_NAME"  style="width:155" value="${params.SALE_PSON_NAME}"/>
			   					<input id="SALE_PSON_ID" name="SALE_PSON_ID"  style="width:155" value="${params.SALE_PSON_ID}" type="hidden"/>
			   					<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('System_0', true, 'SALE_PSON_ID','RYXXID', 'forms[0]','SALE_PSON_ID,SALE_PSON_NAME','RYXXID,XM', 'selectParam')"
									>
							</td>
						</tr>
						<tr>
							<!-- 
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								销售日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="SAEL_DATE_BENG" name="SAEL_DATE_BENG" id="SAEL_DATE_BENG" type="text" inputtype="date" value="" label="销售日期从" mustinput="true" autocheck="true" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SAEL_DATE_BENG);">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								销售日期到：
							</td>
							<td width="15%" class="detail_content">
								<input json="SAEL_DATE_END" name="SAEL_DATE_END" id="SAEL_DATE_END" type="text"  inputtype="date" value="" label="销售日期到"  mustinput="true" autocheck="true" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SAEL_DATE_END);">
							</td>
							-->
							<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
							<td width="15%" class="detail_content">
			   					<input id="BEGIN_CRE_TIME" name="BEGIN_CRE_TIME" readonly="readonly" onclick="SelectTime();" style="width:155" value="${params.BEGIN_CRE_TIME}"    />
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);" >
							</td>	
							<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
							<td width="15%" class="detail_content">
			   					<input id="END_CRE_TIME" name="END_CRE_TIME" readonly="readonly" style="width:155" onclick="SelectTime();" value="${params.END_CRE_TIME}"/>
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);" >
							</td>
							<td width="8%" nowrap align="right" class="detail_label">活动编号：</td>
							<td width="15%" class="detail_content">
								<input id="PROMOTE_ID" json="PROMOTE_ID" name="PROMOTE_ID" type="hidden" value="${params.PROMOTE_ID}">
		                     <input json="PROMOTE_NO" name="PROMOTE_NO" autocheck="true" label="活动编号"  type="text"   inputtype="string"   value="${params.PROMOTE_NO}"/>
		                     <img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
		                     	onClick="selCommon('BS_112', true, 'PROMOTE_ID', 'PROMOTE_ID', 'forms[0]','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','selectMoteParams')">
							</td>
							<td width="8%" nowrap align="right" class="detail_label">活动名称：</td>
							<td width="15%" class="detail_content">
								<input json="PROMOTE_NAME" name="PROMOTE_NAME"  label="活动名称" type="text"  value="${params.PROMOTE_NAME}"/>
							</td>
						</tr>
						<tr>
							<td width="8%" nowrap align="right" class="detail_label">客户姓名:</td>
							<td width="15%" class="detail_content">
			   					<input id="CUST_NAME" name="CUST_NAME"  style="width:155" value="${params.CUST_NAME}" />
							</td>
							<td width="8%" nowrap align="right" class="detail_label">电话:</td>
							<td width="15%" class="detail_content">
								<input id="TEL" name="TEL"  style="width:155" value="${params.TEL}" />
							</td>
		                   				
		                    <td width="8%" nowrap align="right" class="detail_label">合同编号:</td>
							<td width="15%" class="detail_content">
								<input id="CONTRACT_NO" name="CONTRACT_NO"  style="width:155" value="${params.CONTRACT_NO}" />
							</td>
							<td width="8%" nowrap align="right" class="detail_label"></td>
							<td width="15%" class="detail_content">
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




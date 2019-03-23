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
	<title>收款情况查询</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;财务报表&gt;&gt;收款情况查询</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toQueryClause" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
						<input id="selectDELIVERParam" name="selectDELIVERParam" type="hidden" value=" DEL_FLAG=0 and state != '未提交' "/>
						<input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG} "	>
						<c:if test="${IS_DRP_LEDGER eq 1}"> 
							<input type="hidden" name="selectParamsTerm"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID_P='${ZTXXID}' "	>
						</c:if>
						<c:if test="${IS_DRP_LEDGER eq 0}"> 
							<input type="hidden" name="selectParamsTerm"	value="STATE in( '启用','停用') and DEL_FLAG='0'  "	>
						</c:if>
						<input type="hidden" name="selectAddrParams" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectMoteParams" id="selectMoteParams" value=" DEL_FLAG='0' and STATE='启用' ">
							
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">渠道编号:</td>
							<td width="15%" class="detail_content">
								<input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${ZTXXID}"</c:if> />
								<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" type="text" mustinput="true" autocheck="true" label="渠道编号" inputtype="string" readonly style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${CHANN_NO}"</c:if> />
								<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_ID,CHANN_NO,CHANN_NAME', 
										'CHANN_ID,CHANN_NO,CHANN_NAME',
										'selectParamsChann');changePromote();"> 
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">渠道名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="CHANN_NAME" name="CHANN_NAME" readonly json="CHANN_NAME" mustinput="true" autocheck="true" label="渠道名称" inputtype="string" style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${LEDGER_NAME}"</c:if> />
							</td>
							
							
							
							<td width="10%" nowrap align="right" class="detail_label">终端编号:</td>
							<td width="15%" class="detail_content">
								<input id="TERM_ID" name="TERM_ID"  json="TERM_ID" type="hidden" readonly style="width:155"  />
								<input id="TERM_NO" name="TERM_NO"  json="TERM_NO" readonly type="text" style="width:155"  />
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_27', true, 'TERM_ID', 'TERM_ID', 'forms[0]',
										'TERM_ID,TERM_NO,TERM_NAME', 
										'TERM_ID,TERM_NO,TERM_NAME',
										'selectParamsTerm');"> 
							</td>
							<td width="10%" nowrap align="right" class="detail_label">终端名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="TERM_NAME" name="TERM_NAME" readonly json="TERM_NAME" type="text" style="width:155" />
							</td>
						</tr>
						<tr>
		               		<td width="8%" nowrap align="right" class="detail_label">活动名称：</td>
							<td width="15%" class="detail_content">
								<input id="PROMOTE_ID" json="PROMOTE_ID" name="PROMOTE_ID" type="hidden" value="${params.PROMOTE_ID}">
		                     <input json="PROMOTE_NAME" name="PROMOTE_NAME" autocheck="true" label="活动名称"  type="text"   inputtype="string"   value="${params.PROMOTE_NAME}"/>
		                     <img align="absmiddle" style="cursor： hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
		                     	onClick="selCommon('BS_112', true, 'PROMOTE_ID', 'PROMOTE_ID', 'forms[0]','PROMOTE_ID,PROMOTE_NAME','PROMOTE_ID,PROMOTE_NAME','selectMoteParams')">
							</td>
		                    <td width="8%" nowrap align="right" class="detail_label">顾客名称：</td>
							<td width="15%" class="detail_content">
							  <input id="CUST_NAME" name="CUST_NAME" json="CUST_NAME" type="text"   />
							</td>
							<td width="8%" nowrap align="right" class="detail_label">电话：</td>
							<td width="15%" class="detail_content">
							  <input id="TEL" name="TEL" json="TEL" type="text"   />
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
							</td>
							<td width="15%" class="detail_content">
							</td>
		               </tr>
						<tr>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								收款日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="STATEMENT_DATE_BENG" name="STATEMENT_DATE_BENG" id="STATEMENT_DATE_BENG" type="text" inputtype="date" value="" label="销售日期从"  autocheck="true" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(STATEMENT_DATE_BENG);">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								收款日期到：
							</td>
							<td width="15%" class="detail_content">
								<input json="STATEMENT_DATE_END" name="STATEMENT_DATE_END" id="STATEMENT_DATE_END" type="text"  inputtype="date" value="" label="销售日期到"  autocheck="true" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(STATEMENT_DATE_END);">
							</td>
							
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
							</td>
							<td width="15%" class="detail_content">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
							</td>
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

//渠道下的活动
function changePromote(){
	var CHANN_ID = $("#CHANN_ID").val();
	if(null != CHANN_ID && "" != CHANN_ID){
		CHANN_ID = CHANN_ID.replace(/,/gm,"','");
		var params = " STATE='启用' and DEL_FLAG=0 and LEDGER_ID in('"+CHANN_ID+"') ";
	    $("#selectMoteParams").val(params);
	}
}
  
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




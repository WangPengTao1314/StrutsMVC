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
	<title>
		<c:if test="${IS_DRP_LEDGER ne 1}">渠道</c:if>财务进销存表
	</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;财务报表&gt;&gt;<c:if test="${IS_DRP_LEDGER ne 1}">渠道</c:if>财务进销存表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toInvocAcct" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
						<input id="selectDELIVERParam" name="selectDELIVERParam" type="hidden" value=" DEL_FLAG=0 and state != '未提交' "/>
						<input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG='0' "	>
						<input type="hidden" name="selectParamsTerm"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID_P='${ZTXXID}' "	>
						<input type="hidden" name="selectAddrParams" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${params.LEDGER_ID}'))">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selectParamsStore"    id="selectParamsStore"  value="STATE in( '启用','停用') and LEDGER_ID='${ZTXXID}'  ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selectParamsStore"    id="selectParamsStore"  value="STATE in( '启用','停用') and LEDGER_ID in ${CHANNS_CHARG}  ">
						</c:if>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">客户编号:</td>
							<td width="15%" class="detail_content">
								<input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${ZTXXID}"</c:if> />
								<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" type="text" mustinput="true" inputtype="string" autocheck="true" label="客户编号" readonly style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${CHANN_NO}"</c:if> />
								<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_ID,CHANN_NO,CHANN_NAME', 
										'CHANN_ID,CHANN_NO,CHANN_NAME',
										'selectParamsChann');"> 
								</c:if>
							</td>
							<td  width="10%" nowrap align="right" class="detail_label">客户名称:</td>
							<td width="15%"  class="detail_content">
			   					<input id="CHANN_NAME" name="CHANN_NAME" readonly json="CHANN_NAME" style="width:155"  mustinput="true" inputtype="string" autocheck="true" label="客户名称" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${LEDGER_NAME}"</c:if> />
							</td>
							<td width="10%" name="dateTd" nowrap align="right" class="detail_label" >日期从:</td>
							<td width="15%" name="dateTd" class="detail_content">
								<input json="StartDate" name="StartDate" id="StartDate" type="text"  inputtype="date" value="" label="日期从"     mustinput="true"    onclick="SelectDate1()" READONLY /> 
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate1(StartDate);">
							</td>
							<td width="10%" name="dateTd" nowrap align="right" class="detail_label">日期到:</td>
							<td width="15%" name="dateTd" class="detail_content">
								<input json="EndDate" name="EndDate" id="EndDate" type="text"  inputtype="date" value="" label="日期到"   mustinput="true"    onclick="SelectDate1()" READONLY /> 
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate1(EndDate);">
							</td>
							<td width="10%" name="daysTd" nowrap align="right" class="detail_label" >年份:</td>
							<td width="15%" name="daysTd" class="detail_content">
								<select id="YEAR" name="YEAR"  json="YEAR"  style="width: 155px;"></select><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >
							</td>
							<td width="10%" name="daysTd" nowrap align="right" class="detail_label">月份:</td>
							<td width="15%" name="daysTd" class="detail_content">
			   					<select id="MONTH" name="MONTH" style="width: 155px;"></select><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label">
								货品编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_ID" name="PRD_ID"  json="PRD_ID" type="hidden" style="width:155" value="${params.PRD_ID}"/>
								<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" style="width:155" value="${params.PRD_NO}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif"
			   						onclick="selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_NO,PRD_NAME','PRD_NO,PRD_NAME','selectParamsPro');"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">货品名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="PRD_NAME" name="PRD_NAME"  style="width:155" value="${params.PRD_NAME}"/>
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								货品分类：
							</td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text"  value="${params.PAR_PRD_NAME}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_NAME','PRD_NAME','selectParamsParPro');"/>
							</td>
							<input type="hidden" name="getherFlag" id="getherFlag" value="1"/>
							<td width="10%" nowrap align="right" class="detail_label"></td>
							<td width="15%" class="detail_content">
			   					<input type="radio" name="flag" onclick="upFlag('1')" checked="checked">汇总&nbsp;&nbsp;&nbsp;
			   					<input type="radio" name="flag" onclick="upFlag('2')">详细
			   					<input type="radio" name="flag" onclick="upFlag('3')">流水
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label">
								库房编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="STORE_ID" name="STORE_ID"  json="STORE_ID" type="hidden"  value=""/>
								<input id="STORE_NO" name="STORE_NO"  json="STORE_NO" type="text"  value="${params.STORE_NO}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_35', true, 'STORE_ID', 'STORE_ID', 'forms[0]',
												'STORE_NO,STORE_NAME', 
												'STORE_NO,STORE_NAME', 
												'selectParamsStore');">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">库房名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="STORE_NAME" name="STORE_NAME" type="text"   value="${params.STORE_NAME}"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label"></td>
							<td width="15%" class="detail_content">
							</td>
							<td width="10%" nowrap align="right" class="detail_label"></td>
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
SelDictShow("YEAR","89","${params.YEAR}","");
SelDictShow("MONTH","168","${params.MONTH}","");
$(function(){	
//form校验设置
	InitFormValidator(0);
	$("table tr td[name='dateTd']").hide();
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		var getherFlag=$("#getherFlag").val();
		if("3"==getherFlag){
			if($("#StartDate").val()==""){
				alert("请选择查询日期开始时间");
				return;
			}
			if($("#StartDate").val()==""){
				alert("请选择查询日期结束时间");
				return;
			}
		}else{
			if($("#YEAR").val()==""){
				alert("请选择年份");
				return;
			}
			if($("#MONTH").val()==""){
				alert("请选择月份");
				return;
			}
		}
		var obj = parent.document.getElementById("butHidTop");
	 	$(obj).trigger("click");
		$("#queryForm").submit();
	});	
});
	 function upFlag(flag){
	 	$("#getherFlag").val(flag);
	 	if("3"==flag){
	 		$("table tr td[name='daysTd']").hide();
	 		$("table tr td[name='dateTd']").show();
	 		$("#YEAR").val("");
	 		$("#MONTH").val("");
	 		
	 	}else{
	 		$("table tr td[name='dateTd']").hide();
	 		$("table tr td[name='daysTd']").show();
	 		$("#StartDate").val("");
	 		$("#EndDate").val("");
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




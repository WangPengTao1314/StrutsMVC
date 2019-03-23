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
	<title>订单发货状态跟踪查询</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;订单发货状态跟踪查询</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toQueryGoods" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
						<input id="selectDELIVERParam" name="selectDELIVERParam" type="hidden" value=" DEL_FLAG=0 and state != '未提交' "/>
						<input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG} "	>
						<input type="hidden" name="selectAddrParams"    value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectParamsGooder" id="selectParamsGooder"  value="  DEL_FLAG='0' ">
						
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">客户编号:</td>
							<td width="15%" class="detail_content">
									<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" readonly style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${CHANN_NO}"</c:if> />
								<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', false, 'CHANN_NO', 'CHANN_NO', 'forms[0]',
										'CHANN_NO,CHANN_NAME', 
										'CHANN_NO,CHANN_NAME',
										'selectParamsChann');"> 
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">客户名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="CHANN_NAME" name="CHANN_NAME" readonly json="CHANN_NAME" style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${LEDGER_NAME}"</c:if> />
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								销售日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="SAEL_DATE_BENG" name="SAEL_DATE_BENG" id="SAEL_DATE_BENG" type="text" inputtype="date" value="" mustinput="true" autocheck="true" label="销售日期从" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SAEL_DATE_BENG);">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								销售日期到：
							</td>
							<td width="15%" class="detail_content">
								<input json="SAEL_DATE_END" name="SAEL_DATE_END" id="SAEL_DATE_END" type="text"  inputtype="date" value="" mustinput="true" autocheck="true" label="销售日期到" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SAEL_DATE_END);">
							</td>
						</tr>
						<tr>
						 <td width="10%" align="right" class="detail_label">
								订货订单编号：
						 </td>
						 <td width="15%" class="detail_content">
							<input id="GOODS_ORDER_ID" name="GOODS_ORDER_ID"  json="GOODS_ORDER_ID" type="hidden"   value="${params.GOODS_ORDER_ID}"/>
							<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  json="GOODS_ORDER_NO"   value="${params.GOODS_ORDER_NO}"/>	   					
		   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
		   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
		   						onclick="selCommon('BS_56', true, 'GOODS_ORDER_ID', 'GOODS_ORDER_ID', 'forms[0]','GOODS_ORDER_NO','GOODS_ORDER_NO','selectParamsGooder');"/>
						 </td>
						 <td width="10%" align="right" class="detail_label">
								状态：
						 </td>
						 <td width="15%" class="detail_content">
						   <select id="STATE" name="STATE" style="width: 155px;" ></select>
						 </td>
						 <td width="10%" align="right" class="detail_label">
						 </td>
						 <td width="15%" class="detail_content">
						 </td>
						 <td width="10%" align="right" class="detail_label">
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
SelDictShow("STATE","BS_128","${params.STATE}","");

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




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
	<title>总部对账单查询</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表中心&gt;&gt;报表管理&gt;&gt;总部对账单查询</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toAccountReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsTerm" value=" STATE = '启用' and DEL_FLAG = 0 ">
						<input type="hidden" name="selectParamsOrder" value=" bill_type='终端退货' and  del_flag='0' ">
						<input type="hidden" name="selectParamsProduct" value=" STATE in('启用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
						<input type="hidden" name="selectShip" id="selectShip" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<tr>
							<td width="10%" align="right" class="detail_label">
									渠道编号：
							</td>
							<td width="15%" class="detail_content">
									<input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${ZTXXID}"</c:if>/>
									<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" label="渠道编号" inputtype="string"  mustinput="true" autocheck="true"   readonly <c:if test="${IS_DRP_LEDGER eq 1}"> value="${CHANN_NO}"</c:if> />
								 <c:if test="${IS_DRP_LEDGER ne 1}">
			   						<img align="absmiddle" name="selJGXX" style="cursor:hand" 
				   						src="${ctx}/styles/${theme}/images/plus/select.gif"
				   						onclick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
												'CHANN_NO,CHANN_NAME','CHANN_NO,CHANN_NAME','selectParamsTerm');"/>
								 </c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">
									渠道名称：
							</td>
							<td width="15%" class="detail_content">
									<input id="CHANN_NAME" name="CHANN_NAME" readonly json="CHANN_NAME" style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}"> value="${LEDGER_NAME}"</c:if> />
							</td>
							 <td width="10%" nowrap align="right" class="detail_label">组织编号：</td>
							<td width="15%" class="detail_content">
							   <input type="hidden" id="SHIP_POINT_ID" name="SHIP_POINT_ID"   />
								<input id="SHIP_POINT_NO"    name="SHIP_POINT_NO"   json="SHIP_POINT_NO" type="text"  />
							    <img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_22', true,  'SHIP_POINT_ID', 'SHIP_POINT_ID','forms[0]','SHIP_POINT_NO','SHIP_POINT_NO','selectShip');"/>
							  
							</td>
							<%--
							<td width="10%" nowrap align="right" class="detail_label">
									记账年份：
							</td>
							<td width="15%" class="detail_content">
								<select id="YEAR" json="YEAR" name="YEAR" style="width: 155px;" ></select>
							</td>
							<td width="10%" nowrap align="right" class="detail_label" >
									记账月份：
							</td>
							<td width="20%" class="detail_content">
							    <select id="MONTH" name="MONTH" style="width: 155px" ></select>
							</td>
						--%>
						
						<td width="10%" nowrap align="right" class="detail_label"> 
							记账期间： 
						</td> 
						<td width="20%" class="detail_content"> 
						    <input json="MonthStartDate" name="MonthStartDate" id="MonthStartDate" type="text"  inputtype="date" value="" label="记账期间"  autocheck="true"   mustinput="true"    onclick="SelectDate1()" READONLY /> 
							<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate1(MonthStartDate);"> 
						</td> 

						</tr>
 
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
	SelDictShow("YEAR","89","${params.CUR_YEAR}","");
    SelDictShow("MONTH","168","${params.CUR_MONTH}","");
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




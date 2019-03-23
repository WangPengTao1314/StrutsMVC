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
	<title>退货情况统计表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表中心&gt;&gt;报表管理&gt;&gt;直营办报表&gt;&gt;退货情况统计表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toReturnaReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${params.LEDGER_ID}'))">
		<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
		<input type="hidden" name="selectParamsTerm"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID_P='${ZTXXID}' "	>
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<tr>
							<td width="10%" align="right" class="detail_label">退货日期从：</td>
							<td width="15%" class="detail_content">
								<input json="CRE_TIME_BENG" name="CRE_TIME_BENG" id="CRE_TIME_BENG" type="text" inputtype="date" value="" label="退货日期从" autocheck="true" onclick="SelectDate()" READONLY />
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_BENG);">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">退货日期到：</td>
							<td width="15%" class="detail_content">
								<input json="CRE_TIME_END" name="CRE_TIME_END" id="CRE_TIME_END" type="text" inputtype="date" value="" label="退货日期到" autocheck="true" onclick="SelectDate()" READONLY />
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_END);">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">客户编号:</td>
							<td width="15%" class="detail_content">
								<input id="RETURN_CHANN_ID" name="RETURN_CHANN_ID"  json="RETURN_CHANN_ID" type="hidden"  style="width:155"  />
								<input id="RETURN_CHANN_NO" name="RETURN_CHANN_NO"  json="RETURN_CHANN_NO"  type="text" style="width:155"  />
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_185', true, 'RETURN_CHANN_ID', 'TERM_ID', 'forms[0]',
										'RETURN_CHANN_ID,RETURN_CHANN_NO,RECV_CHANN_NAME', 
										'TERM_ID,TERM_NO,CUST_NAME',
										'selectParamsTerm');"> 
							</td>
							<td width="10%" nowrap align="right" class="detail_label">客户名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  json="RECV_CHANN_NAME" type="text" style="width:155" />
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
								货品分类编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PAR_PRD_NO" name="PAR_PRD_NO" type="text"  value="${params.PAR_PRD_NO}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_NO,PAR_PRD_NAME','PRD_NO,PRD_NAME','selectParamsParPro');"/>
							</td>
							<td width="10%" align="right" class="detail_label">
								货品分类名称：
							</td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text"  value="${params.PAR_PRD_NAME}"/>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label">
								单据编号:
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_RETURN_NO" name="PRD_RETURN_NO" type="text"  value="${params.PRD_RETURN_NO}"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label"></td>
							<td width="15%" class="detail_content">
							</td>							
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
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




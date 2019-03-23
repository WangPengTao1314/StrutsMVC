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
	<title>销售订单出货明细</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;财务报表&gt;&gt;待确认预订单</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toUncommAdvc" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<input type="hidden" name=selectTERM value="STATE in ('启用','停用') and DEL_FLAG=0 and CHANN_ID_P='${ZTXXID}'">
	                    <input type="hidden" name="selectParam" value="RYZT in ('启用','停用') and DELFLAG=0 and JGXXID in (select JGXXID from T_SYS_JGXX where ZTXXID ='${ZTXXID}' ) ">
						 
						<tr>
							<td width="10%" align="right" class="detail_label">
								预订单编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  json="ADVC_ORDER_NO" type="text"  />
							</td>
							<td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
							<td width="15%" class="detail_content">
			   					<input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${params.TERM_ID}"/>
		                       <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text"  inputtype="string"   maxlength="30"  value="${params.TERM_NO}"/>
		                       <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectTERM')"> 
							</td>					
		                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="TERM_NAME" name="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
							</td>
							<td width="8%" nowrap align="right" class="detail_label">业务员:</td>
							<td width="15%" class="detail_content">
			   					<input id="SALE_PSON_NAME" name="SALE_PSON_NAME"  style="width:155" value="${params.SALE_PSON_NAME}"/>
			   					<input id="SALE_PSON_ID" name="SALE_PSON_ID"  style="width:155" value="${params.SALE_PSON_ID}" type="hidden"/>
			   					<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('System_0', false, 'SALE_PSON_ID','RYXXID', 'forms[0]','SALE_PSON_ID,SALE_PSON_NAME','RYXXID,XM', 'selectParam')"
									>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								销售日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="SALE_DATE_BEG" name="SALE_DATE_BEG" id="SALE_DATE_BEG" type="text"  
								inputtype="date" value="" label="销售日期从" onclick="SelectDate()" value="${params.SALE_DATE_BEG}">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_BEG);">
							</td>
							<td width="10%" align="right" class="detail_label" nowrap="nowrap">
								销售日期到：
							</td>	
							<td width="15%" class="detail_content">
								<input json="JZRQ" name="SALE_DATE_END" id="SALE_DATE_END" type="text"  inputtype="date"  
								label="销售日期到"  onclick="SelectDate()"  value="${params.SALE_DATE_END}" >
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_END);">
							</td>	
						  <td width="10%" align="right" class="detail_label"nowrap="nowrap">
								发货日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="ORDER_RECV_DATE_BEG" name="ORDER_RECV_DATE_BEG" id="ORDER_RECV_DATE_BEG" type="text"   
								inputtype="date" value="" label="发货日期从" onclick="SelectDate()" value="${params.ORDER_RECV_DATE_BEG}">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE_BEG);">
							</td>
							<td width="10%" align="right" class="detail_label" nowrap="nowrap">
								发货日期到：
							</td>
							<td width="15%" class="detail_content">
								<input json="JZRQ" name="ORDER_RECV_DATE_END" id="ORDER_RECV_DATE_END" type="text"    inputtype="date"  
								label="发货日期到"  onclick="SelectDate()"  value="${params.ORDER_RECV_DATE_END}" >
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE_END);">
							</td>	
						</tr>
						<tr>
						    <td width="10%" align="right" class="detail_label"> 客户姓名： </td>
							<td width="15%" class="detail_content">
							   <input id="CUST_NAME" name="CUST_NAME" type="text" />
			   				</td>
			   				<td width="10%" align="right" class="detail_label">电话：</td>
							<td width="15%" class="detail_content">
							   <input id="TEL" name="TEL" type="text" />
							</td>
							<td width="10%" align="right" class="detail_label"> 合同号：</td>
							<td width="15%" class="detail_content">
							  <input id="CONTRACT_NO" name="CONTRACT_NO" type="text" />
							</td>
							<td width="10%" align="right" class="detail_label">  </td>
							<td width="15%" class="detail_content"> </td>
							 
						</tr>
						<tr>	
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								制单日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="CREATE_DATE_BEG" name="CREATE_DATE_BEG" id="CREATE_DATE_BEG" type="text"   
								inputtype="date" value="" label="制单日期从" onclick="SelectDate()" value="${params.CREATE_DATE_BEG}">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CREATE_DATE_BEG);">
							</td>
							<td width="10%" align="right" class="detail_label" nowrap="nowrap">
								制单日期到：
							</td>	
							<td width="15%" class="detail_content">
								<input json="JZRQ" name="CREATE_DATE_END" id="CREATE_DATE_END" type="text"    inputtype="date"  
								label="制单日期到"  onclick="SelectDate()"  value="${params.CREATE_DATE_END}" >
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CREATE_DATE_END);">
							</td>	
							<td width="10%" align="right" class="detail_label">  </td>
							<td width="15%" class="detail_content"> </td>
							<td width="10%" align="right" class="detail_label">  </td>
							<td width="15%" class="detail_content"> </td>
						
						</tr>
						<%--
						<tr>							
							<td width="10%" align="right" class="detail_label">
								货品编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_ID" name="PRD_ID"  json="PRD_ID" type="hidden"  value="${params.PRD_ID}"/>
								<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" type="text"  value="${params.PRD_NO}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selPrd();"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">货品名称：</td>
							<td width="15%" class="detail_content">
			   					<input id="PRD_NAME" name="PRD_NAME"  type="text"  value="${params.PRD_NAME}"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">货品分类： </td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PRD_TYPE_NO" name="PRD_TYPE_NO" type="text"  value="${params.PRD_TYPE_NO}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selPrdParent();"/>
							</td>
						</tr>
					 
						--%>
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
	//SelDictShow("STATE","BS_126","${params.STATE}","");
													
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
	 

  
//货品
function selPrd(){
  var rtnArr = selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_NO,PRD_NAME','PRD_NO,PRD_NAME','selectParamsPro');
 // multiSelCommonSet(rtnArr,"PRD_ID,PRD_NO,PRD_NAME",1);
    
}

//货品分类
function selPrdParent(){
	selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PRD_TYPE_NO','PRD_NO','selectParamsParPro');
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




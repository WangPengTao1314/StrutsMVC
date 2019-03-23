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
	<title>分销商购货月报</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;分销商购货月报</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toDistributorSaleRpt" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
	   <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null "/>
	   <input type="hidden" name="selectParamsManage" id="selectParamsManage" />
	   <input type="hidden" name="selectDistributer" value=" STATE in( '启用','停用') and DEL_FLAG='0' ">
	   <input type="hidden" name="selectProduct" value=" STATE in( '启用','停用') and DEL_FLAG = '0' and IS_DISTRIBUT_FLAG =1 " />
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsChann" id="selectParamsChann"  value="  CHANN_TYPE = '直营办' and DEL_FLAG='0' ">
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">战区:</td>
							<td width="15%" class="detail_content">
								<input id="WAREA_NAME" name="WAREA_NAME"  json="WAREA_NAME" value="${params.WAREA_NAME}" readonly />
								<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_175', true, 'WAREA_NAME', 'AREA_NAME', 'forms[0]',
												'WAREA_NAME', 
												'AREA_NAME', 
												'selectParamsArea');">
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">营销经理:</td>
							<td width="15%" class="detail_content">
			   					<input id="MKM_NAME" name="MKM_NAME" readonly json="MKM_NAME" value="${params.MKM_NAME}" />
							    <img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="getMkmName();" /> 
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">分销商渠道编号：</td>
							<td width="15%" class="detail_content">
								<input id="DISTRIBUTOR_NO" name="DISTRIBUTOR_NO"  json="DISTRIBUTOR_NO" value="${params.DISTRIBUTOR_NO}" readonly />
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selDistributer();">
							</td>
							<td width="10%"  class="detail_label" >分销商渠道名称：</td>
							<td width="15%" class="detail_content">
							   <input json="DISTRIBUTOR_NAME" name="DISTRIBUTOR_NAME" id="DISTRIBUTOR_NAME" type="text" />
							</td>
						</tr>
						<tr>							
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">提报时间从：</td>
							<td width="15%" class="detail_content">
							   <input json="CRE_DATE_BEG" readonly name="CRE_DATE_BEG" id="CRE_DATE_BEG" type="text"  label="提报时间从" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_DATE_BEG);">
							</td>
							<td width="10%"  class="detail_label" >提报时间至：</td>
							<td width="15%" class="detail_content">
							   <input json="CRE_DATE_END" readonly name="CRE_DATE_END" id="CRE_DATE_END" type="text"  label="提报时间至" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_DATE_END);">
							</td>							
							<td width="8%" align="right" class="detail_label">
								月报时间：
							</td>
							<td width="24%" class="detail_content">
								&nbsp;<select json="YEAR" id="YEAR" name="YEAR" style="width: 80px" ></select>年
				   				&nbsp;<select json="MONTH" id="MONTH" name="MONTH" style="width: 80px" ></select>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">分销类型:</td>
							<td width="15%" class="detail_content">								
								<select json="DISTRIBUTOR_TYPE" id="DISTRIBUTOR_TYPE" name="DISTRIBUTOR_TYPE" style="width:155px;" 
									inputtype="string" label="分销类型" >
								</select>
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">产品编号:</td>
							<td width="15%" class="detail_content">
			   					<input id="PRD_NO" name="PRD_NO" readonly json="PRD_NO" value="${params.PRD_NO}" />
							    <img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selProduct();" /> 
							</td>
							<td width="10%" class="detail_label">产品名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="PRD_NAME" name="PRD_NAME" json="PRD_NAME" value="${params.PRD_NAME}" />							    
							</td>
														
							<td width="10%"  class="detail_label" ></td>
							<td width="15%" class="detail_content"></td>
							
							<td width="10%" class="detail_label"></td>
							<td width="15%" class="detail_content"></td>
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
SelDictShow("DISTRIBUTOR_TYPE", "201", '${params.DISTRIBUTOR_TYPE}', "");
SelDictShow("YEAR","89","${params.YEAR}","");
SelDictShow("MONTH","168","${params.MONTH}","");

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
 
function  getMkmName(){
	//战区
	var WAREA_NAME_Str = getQueryStr("WAREA_NAME","AREA_NAME_P");
	if("" != WAREA_NAME_Str){		
		$("#selectParamsManage").val(" AREA_MANAGE_NAME IS NOT NULL and "+WAREA_NAME_Str);
		selCommon('BS_189', true, 'MKM_NAME', 'AREA_MANAGE_NAME', 'forms[0]','MKM_NAME', 'AREA_MANAGE_NAME', 'selectParamsManage');
	}else{
		parent.showErrorMsg("请先选择战区!");
	}
}
  
//选择分销商
function selDistributer(){	
	//战区
	var WAREA_NAME_Str = getQueryStr("WAREA_NAME","AREA_NAME_P");
	if("" != WAREA_NAME_Str){
		var defCon = $("#selectDistributer").val();
		$("#selectDistributer").val(defCon+ " and "+WAREA_NAME_Str);
	}
	
	//营销经理
	var MKM_NAME_Str = getQueryStr("MKM_NAME","AREA_MANAGE_NAME");
	if("" != MKM_NAME_Str){
		var defCon = $("#selectDistributer").val();
		$("#selectDistributer").val(defCon+ " and "+MKM_NAME_Str);
	}
	      
	selCommon('BS_188', true, 'DISTRIBUTOR_NO', 'DISTRIBUTOR_NO', 'forms[0]',
						'DISTRIBUTOR_NO,DISTRIBUTOR_NAME,DISTRIBUTOR_TYPE,WAREA_NAME', 
						'DISTRIBUTOR_NO,DISTRIBUTOR_NAME,DISTRIBUTOR_TYPE,AREA_NAME_P', 
						'selectDistributer');
}

//选择货品
function selProduct(){
	selCommon("BS_21", true, "PRD_NO", "PRD_NO", "forms[0]",
		"PRD_NO,PRD_NAME",
		"PRD_NO,PRD_NAME","selectProduct");		
}

//获取查询条件
function getQueryStr(id,queryKey){
	var currValue = $("#"+id).val();
	if(null == currValue || "" == currValue){
		return "";
	}
	
	var valArr = currValue.split(",");
	var len = valArr.length;	
	if(len == 1){
		return queryKey + " like '%"+valArr[0]+ "%' ";
	}
	
	var rstStr = queryKey + " in (";
	for(var i = 0 ; i < len; i++){
		rstStr = rstStr + "'" + valArr[i] + "',";
	}	
	return rstStr.substr(0,rstStr.length-1) + ")";
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




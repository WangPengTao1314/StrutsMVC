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
	<title>总部销售报表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;终端门店零售报表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toDrpSaleReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
						<input type="hidden" name="selectParamsChann" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<input type="hidden" name="selectPrdGroup" id="selectPrdGroup" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectShip" id="selectShip" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" id="selAREAParam" name="selAREAParam" value=" STATE in('启用','停用') and AREA_ID_P is not null "/>
						<input type="hidden" id="selectBrand" name="selectBrand" value=" STATE in('启用','停用') and DEL_FLAG=0 "/>
						<input type="hidden" id="selectCITY" name="selectCITY" value=""/>
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID='${ZTXXID}' ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID in ${CHANNS_CHARG} ">
						</c:if>
						<tr>
						  <td width="10%" align="right" class="detail_label"> 显示字段： </td>
						  <td width="15%" class="detail_content" colspan="7">
						    <input type="checkbox" id="WAREA_ID_SHOW" name="WAREA_ID_SHOW" value="WAREA_ID" >战区</input>
						    <input type="checkbox" id="PROV_SHOW" name="PROV_SHOW" value="PROV" >省份</input>
						    <input type="checkbox" id="CITY_SHOW" name="CITY_SHOW" value="CITY_SHOW" >城市</input>
						    <input type="checkbox" id="AREA_ID_SHOW" name="AREA_ID_SHOW" value="AREA_ID" >区域</input>
						    <input type="checkbox" id="CHANN_ID_SHOW" name="CHANN_ID_SHOW" value="CHANN_ID" >渠道</input>
						    <input type="checkbox" id="BRAND_SHOW" name="BRAND_SHOW" value="BRAND" >品牌</input>
						    <!-- 
						    <input type="checkbox" id="PAR_PRD_NAME_SHOW" name="PAR_PRD_NAME_SHOW" value="PAR_PRD_NAME" >货品分类</input>
						     -->
						    <input type="checkbox" id="YEAR_SHOW" name="YEAR_SHOW" value="YEAR_SHOW" >查询年份</input>
						    <input type="checkbox" id="MONTH_SHOW" name="MONTH_SHOW" value="MONTH_SHOW" >查询月份</input>
						    
						  </td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label">
								战区编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="WAREA_ID" name="WAREA_ID"  json="WAREA_ID" type="hidden"  />
								<input id="WAREA_NO" name="WAREA_NO"  json="WAREA_NO" type="text"  />
								<c:if test="${IS_DRP_LEDGER ne 1}">
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_26', true, 'WAREA_ID', 'AREA_ID', 'forms[0]',
												'WAREA_NO,WAREA_NAME', 
												'AREA_NO,AREA_NAME', 
												'selectParamsArea');">
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">战区名称：</td>
							<td width="15%" class="detail_content">
			   					<input id="WAREA_NAME" name="WAREA_NAME" type="text"  />
							</td>
							<td width="10%" align="right" class="detail_label">
									省份：
							</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="PROV" name="PROV" json="PROV"   value="${params.PROV}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[0]','PROV', 'PROV', '')">						   
							</td>
							<td width="10%" align="right" class="detail_label">
									城市：
							</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="CITY" name="CITY" json="CITY"   value="${params.CITY}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									 onclick="selectCity();">						   
							</td>
														
									
						</tr>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">渠道编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden"  <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_ID}"  </c:if> />
				   				<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" type="text"  <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NO}" readonly </c:if>/>
				   				<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_NO,CHANN_NAME', 
										'CHANN_NO,CHANN_NAME', 
										'selectParamsChann');"> 
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">渠道名称：</td>
							<td width="15%" class="detail_content">
			   					<input id="CHANN_NAME" name="CHANN_NAME" json="CHANN_NAME" type="text"   <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NAME}" readonly </c:if>/>
							</td>
						    <td width="10%" nowrap align="right" class="detail_label">货品分类： </td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PRD_TYPE_NO" name="PRD_TYPE_NO" type="text"  value="${params.PRD_TYPE_NO}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selPrdParent();"/>
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								年份：
							</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="YEAR" name="YEAR" json="YEAR"   value="${params.YEAR}"/>
			   					<input type="hidden" id="YEAR_ID" name="YEAR_ID" json="YEAR_ID"   value="${params.YEAR_ID}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_139', true, 'YEAR_ID', 'SJXMC', 'forms[0]','YEAR', 'SJXZ', '')">						   
							</td>	
						</tr>
						 
						<tr>
					 		<td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
							<td width="15%" class="detail_content">
			   					<input id="AREA_NO" name="AREA_NO"    value="${params.AREA_NO}"/>
			   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_18', true, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAREAParam')">
							
							</td>					
		                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
							<td width="15%" class="detail_content">
							   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
			   				  <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
							
							</td>	
						    <td width="10%" nowrap align="right" class="detail_label">品牌：</td>
							<td width="15%" class="detail_content">
							   <input type="hidden" id="BRAND_ID" name="BRAND_ID" />
								<input id="BRAND"    name="BRAND"   json="BRAND" type="text"  >
							    <img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_142', true,  'BRAND_ID', 'ID','forms[0]','BRAND','BRNAME','selectBrand');"/>
							  
							</td>
							<td width="10%" align="right" class="detail_label" nowrap="nowrap">
								月份：
							</td>	
						   <td width="15%" class="detail_content">
			   					<input type="text" id="MONTH" name="MONTH" json="MONTH"   value="${params.MONTH}"/>
			   					<input type="hidden" id="MONTH_ID" name="MONTH_ID" json="MONTH_ID"   value="${params.MONTH_ID}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_140', true, 'MONTH_ID', 'SJXMC', 'forms[0]','MONTH', 'SJXZ', '')">						   
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
 	//添加默认当前年份
 	if($("#YEAR").val() == null || $("#YEAR").val() == ""){
 		var myDate = new Date(); 
 		$("#YEAR").val(myDate.getFullYear());
 	}
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
//选择城市
function selectCity(){
	var PROV = $("#PROV").val();
	if(null == PROV || "" == PROV){
		parent.showErrorMsg("请先选择'省份''"); 
	    return;
	}
	PROV = "'"+PROV.replace(/\,/g,"','")+"'";
	//alert(PROV);
	$("#selectCITY").val(" PROV in("+PROV+") and DEL_FLAG=0 ");
	selCommon('BS_141', true, 'CITY', 'CITY', 'forms[0]','CITY', 'CITY', 'selectCITY');
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




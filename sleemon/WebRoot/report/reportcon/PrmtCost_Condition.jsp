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
	<title>加盟商推广费用报表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;加盟商推广费用报表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toPrmtCostReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" id="selectPrmtCostReq" name="selectPrmtCostReq" value=""/>
					    <input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
 						<input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG}"	>
						<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<input type="hidden" name="selectPrdGroup" id="selectPrdGroup" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectShip" id="selectShip" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" id="selAREAParam" name="selAREAParam" value=" STATE in('启用','停用') and AREA_ID_P is not null "/>
						<input type="hidden" id="selectBrand" name="selectBrand" value=" STATE in('启用','停用') and DEL_FLAG=0 "/>
						<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
						
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID='${ZTXXID}' ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID in ${CHANNS_CHARG} ">
						</c:if>
						<tr>
						    <td width="10%" nowrap align="right" class="detail_label">战区：</td>
							<td width="15%" class="detail_content">
			   					<input id="WAREA_NAME" name="WAREA_NAME" type="text" json="WAREA_NAME" value="${params.WAREA_NAME}" label="战区" READONLY/>
							    <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_182', true, 'WAREA_NAME', 'AREA_NAME', 'forms[0]',
												'WAREA_NAME', 
												'AREA_NAME', 
												'selectParamsArea');">
							</td>
							<td width="10%" align="right" class="detail_label">
								区域经理
							</td>
							<td width="15%" class="detail_content">
								<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME"  json="AREA_MANAGE_NAME" label="区域经理" type="text" value="${params.AREA_MANAGE_NAME}" READONLY/>
 							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[0]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
 							</td>
							<td width="10%" align="right" class="detail_label">
								加盟商名称
							</td>
							<td width="15%" class="detail_content">
								<input id="CHANN_NAME" name="CHANN_NAME"  json="CHANN_NAME" label="加盟商名称" type="text" value="${params.CHANN_NAME}" READONLY/>
								<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', false, 'CHANN_NAME', 'CHANN_NAME', 'forms[0]',
										'CHANN_NAME', 
										'CHANN_NAME', 
										'selectParamsChann');"> 
 							</td>
					   </tr>
					   <tr>
					      	<td width="10%" nowrap align="right" class="detail_label">年份</td>
							<td width="15%" class="detail_content">
								<select id="YEAR" name="YEAR" json="YEAR" inputtype="string" mustinput="true" label="年份" autocheck="true" style="width:155px;"></select>
 							</td>
					 		<td width="8%" nowrap align="right" class="detail_label">季度</td>
							<td width="15%" class="detail_content" colspan="5">
			   					<select id="QUARTER" name="QUARTER" json="QUARTER" label="季度" value="${params.QUARTER}" inputtype="string" mustinput="true" autocheck="true" style="width:155px;"> 
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

var myDate = new Date(); 
myDate.getYear(); //获取当前年份(2位) 
SelDictShow_Default("YEAR", "89", '${rst.YEAR}', myDate.getFullYear());
SelDictShow("QUARTER", "BS_148", '${params.QUARTER}', "");
/*
if(QUARTER==""){
   parent.showErrorMsg("请先选择'季度'"); 
   return;
}*/

$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		if(null == $("#QUARTER").val() || "" == $("#QUARTER").val()){
		  parent.showErrorMsg("请先选择'季度'"); 
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
//选择城市
function selectCity(){
	var PROV = $("#PROV").val();
	if(null == PROV || "" == PROV){
		parent.showErrorMsg("请先选择'省份''"); 
	    return;
	}
	PROV = "'"+PROV.replace(/\,/g,"','")+"'";
	alert(PROV);
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




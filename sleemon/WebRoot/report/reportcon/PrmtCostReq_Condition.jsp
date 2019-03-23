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
	<title>加盟商推广费用使用明细</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;加盟商推广费用使用明细</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toPrmtCostReqReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" id="selectPrmtCostReq" name="selectPrmtCostReq" value=""/>
					    <input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
					    <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
 						<input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG}"	>
						<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<input type="hidden" name="selectPrdGroup" id="selectPrdGroup" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectShip" id="selectShip" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" id="selAREAParam" name="selAREAParam" value=" STATE in('启用','停用') and AREA_ID_P is not null "/>
						<input type="hidden" id="selectBrand" name="selectBrand" value=" STATE in('启用','停用') and DEL_FLAG=0 "/>
						<!--  
						<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
						-->
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID='${ZTXXID}' ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID in ${CHANNS_CHARG} ">
						</c:if>
						<tr>
						    <td width="10%" nowrap align="right" class="detail_label">推广费编号：</td>
							<td width="15%" class="detail_content">
			   					<input id="PRMT_COST_REQ_NO" name="PRMT_COST_REQ_NO" type="text" json="PRMT_COST_REQ_NO" value="${params.PRMT_COST_REQ_NO}" READONLY/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_180', false, 'PRMT_COST_REQ_NO', 'PRMT_COST_REQ_NO', 'forms[0]','PRMT_COST_REQ_NO', 'PRMT_COST_REQ_NO', 'selectPrmtCostReq')">						   
							</td>
							<td width="10%" align="right" class="detail_label">
								推广费申请时间从
							</td>
							<td width="15%" class="detail_content">
								<input id="REQ_DATE_BEG" name="REQ_DATE_BEG"  json="REQ_DATE_BEG" label="推广费申请时间从" type="text" value="${params.REQ_DATE_BEG}" onclick="SelectDate()" mustinput="true"  inputtype="date" autocheck="true" READONLY/>
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(REQ_DATE_BEG);">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">推广费申请时间到：</td>
							<td width="15%" class="detail_content">
			   					<input id="REQ_DATE_END" name="REQ_DATE_END" label="推广费申请时间到" type="text" json="REQ_DATE_END" value="${params.REQ_DATE_END}" onclick="SelectDate()" mustinput="true"  inputtype="date" autocheck="true" READONLY/>
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(REQ_DATE_END);">
							</td>
						</tr>
						 
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
					 		<td width="8%" nowrap align="right" class="detail_label">业务员:</td>
							<td width="15%" class="detail_content" colspan="3">
			   					<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME"  value="${params.AREA_MANAGE_NAME}"  READONLY/>
			   					<img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[0]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
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
	var myDate = new Date(); 
	myDate.getYear(); //获取当前年份(2位) 
	SelDictShow_Default("YEAR", "89", '${rst.YEAR}', myDate.getFullYear());
	var COST_TYPE='${rst.COST_TYPE}';
	if(""==COST_TYPE||null==COST_TYPE){
		COST_TYPE="战区费用";
	}
	SelDictShow("COST_TYPE","BS_92",COST_TYPE,"");
													
$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		var d1 = $("#REQ_DATE_BEG").val();
		var d2 = $("#REQ_DATE_END").val();
		var tmp = d1.split("-");
		var date1 = new Date(tmp[0],tmp[1]-1,tmp[2]);
		tmp = d2.split("-");
		var date2 = new Date(tmp[0],tmp[1]-2,tmp[2]);
		if(date2.getTime() - date1.getTime() >= 365 * 24 * 60 * 60 * 1000){        
		    parent.showErrorMsg("推广日期区间不得超过一年"); 
		    return false;
		} else{
			var obj = parent.document.getElementById("butHidTop");
		 	$(obj).trigger("click");
			$("#queryForm").submit();
		}
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




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
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;销售订单出货统计明细表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toSaleOrderSendResult" target="bottomcontent${rptConFile}">
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
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID='${ZTXXID}' ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID in ${CHANNS_CHARG} ">
						</c:if>
						<tr>
							<td width="6%" align="right" class="detail_label">
								战区编号：
							</td>
							<td width="10%" class="detail_content">
								<input id="AREA_ID" name="AREA_ID"  json="AREA_ID" type="hidden" />
<!--									<c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.AREA_ID_P}"</c:if>-->
								
								<input id="AREA_NO" name="AREA_NO"  json="AREA_NO" type="text"  
									<c:if test="${IS_DRP_LEDGER eq 1}">	readonly </c:if>/>
<!--									value="${channInfo.AREA_NO_P}" -->
									
									
								
								<c:if test="${IS_DRP_LEDGER ne 1}">
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_26', true, 'AREA_ID', 'AREA_ID', 'forms[0]',
												'AREA_NO', 
												'AREA_NO', 
												'selectParamsArea');">
								</c:if>
							</td>
							<!--  
							<td width="6%" nowrap align="right" class="detail_label">战区名称：</td>
							<td width="10%" class="detail_content">
			   					<input id="AREA_NAME" name="AREA_NAME" type="text"   <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.AREA_NAME_P}" readonly </c:if>/>
							</td>
							-->							
							<td width="6%" align="right" class="detail_label"nowrap="nowrap">
								业务日期从：
							</td>
							<td width="10%" class="detail_content">
								<input json="ORDER_DATE_BEG" name="ORDER_DATE_BEG" id="ORDER_DATE_BEG" type="text"  mustinput="true" autocheck="true" 
								inputtype="date" value="" label="业务日期从" onclick="SelectDate()" value="${params.ORDER_DATE_BEG}">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_BEG);">
							</td>	
							<td width="6%" align="right" class="detail_label" nowrap="nowrap">
							    业务日期到：
							</td>	
							<td width="10%" class="detail_content">
								<input json="JZRQ" name="ORDER_DATE_END" id="ORDER_DATE_END" type="text"  mustinput="true" autocheck="true" inputtype="date"  
								label="业务日期到"  onclick="SelectDate()"  value="${params.ORDER_DATE_END}" >
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_END);">
							</td>	
							<td width="6%" nowrap align="right" class="detail_label">渠道编号：
							</td>
							<td width="10%" class="detail_content">
								<input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden"  <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_ID}"  </c:if> />
				   				<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" type="text"  <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NO}" readonly </c:if>/>
				   				<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_NO', 
										'CHANN_NO', 
										'selectParamsChann');"> 
								</c:if>
							</td>
						</tr>
						<tr>
							<!--  
							<td width="6%" nowrap align="right" class="detail_label">渠道名称：</td>
							<td width="10%" class="detail_content">
			   					<input id="CHANN_NAME" name="CHANN_NAME" json="CHANN_NAME" type="text"   <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NAME}" readonly </c:if>/>
							</td>
							-->
							<td width="6%" align="right" class="detail_label">
								货品编号：
							</td>
							<td width="10%" class="detail_content">
								<input id="PRD_ID" name="PRD_ID"  json="PRD_ID" type="hidden"  value="${params.PRD_ID}"/>
								<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" type="text"  value="${params.PRD_NO}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selPrd();"/>
							</td>
							<!--  
							<td width="6%" nowrap align="right" class="detail_label">货品名称：</td>
							<td width="10%" class="detail_content">
			   					<input id="PRD_NAME" name="PRD_NAME"  type="text"  value="${params.PRD_NAME}"/>
							</td>
							-->
							<td width="6%" nowrap align="right" class="detail_label">货品组名称：</td>
							<td width="10%" class="detail_content"> 
							   <input id="PRD_GROUP_ID" name="PRD_GROUP_ID"  json="PRD_GROUP_ID" type="hidden"  value="${params.PRD_GROUP_ID}"/>
								<input id="PRD_GROUP_NAME" name="PRD_GROUP_NAME"  json="PRD_GROUP_NAME" type="text"  value="${params.PRD_GROUP_NAME}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_29', true, 'PRD_GROUP_ID', 'PRD_GROUP_ID', 'forms[0]','PRD_GROUP_NAME','PRD_GROUP_NAME','selectPrdGroup');"/>

                             </td>
							<td width="6%" nowrap align="right" class="detail_label">货品分类： </td>
							<td width="10%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PRD_TYPE_NO" name="PRD_TYPE_NO" type="text"  value="${params.PRD_TYPE_NO}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selPrdParent();"/>
							</td>
							<td width="6%" nowrap align="right" class="detail_label">订单状态：
							</td>
							<td width="10%" class="detail_content">
							<c:if test="${IS_NO_DELIVER_PLAN_FLAG eq 1}">
								<input id="STATE"    name="STATE"   json="STATE" type="text" readonly="readonly" value="审核通过,已发货,待发货">
								<img align="absmiddle" name="selJGXX" style="cursor:hand" 
				   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
				   						onclick="selCommon('BS_127', true,  'STATE', 'SJXMC','forms[0]','STATE','SJXMC','');"/>
							</c:if>
							<c:if test="${IS_NO_DELIVER_PLAN_FLAG ne 1}">
								<input id="STATE"    name="STATE"   json="STATE" type="text" readonly="readonly" value="审核通过">
								<img align="absmiddle" name="selJGXX" style="cursor:hand" 
				   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
				   						onclick="selCommon('BS_114', true,  'STATE', 'SJXMC','forms[0]','STATE','SJXMC','');"/>
			   				</c:if>
							</td>	
							</tr>
							<tr>						
							<td width="6%" nowrap align="right" class="detail_label">销售订单号：</td>
							<td width="10%" class="detail_content">
								<input type="text" id="SALE_ORDER_NO" name="SALE_ORDER_NO" json="SALE_ORDER_NO" value="${params.SALE_ORDER_NO}">
								<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_70', true,  'SALE_ORDER_NO', 'SALE_ORDER_NO','forms[0]','SALE_ORDER_NO','SALE_ORDER_NO','selErpSale');"/>
							</td>
						    <td width="10%" nowrap align="right" class="detail_label">生产基地编号：</td>
							<td width="15%" class="detail_content">
							   <input type="hidden" id="SHIP_POINT_ID" name="SHIP_POINT_ID" />
<!--							   <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.SHIP_POINT_ID}" readonly </c:if>-->
<!--	value="${channInfo.SHIP_POINT_NO}"-->
								<input id="SHIP_POINT_NO"    name="SHIP_POINT_NO"   json="SHIP_POINT_NO" type="text"  <c:if test="${IS_DRP_LEDGER eq 1}"> readonly </c:if>>
								<c:if test="${IS_DRP_LEDGER ne 1}">
							    <img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_22', true,  'SHIP_POINT_ID', 'SHIP_POINT_ID','forms[0]','SHIP_POINT_NO','SHIP_POINT_NO','selectShip');"/>
							 	</c:if>
							</td>
							<!--  
							<td width="10%" nowrap align="right" class="detail_label">生产基地名称：</td>
							<td width="15%" class="detail_content">
							 <input id="SHIP_POINT_NAME"    name="SHIP_POINT_NAME"   json="SHIP_POINT_NAME" type="text"  <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.SHIP_POINT_NAME}" readonly </c:if> >
							 <c:if test="${IS_DRP_LEDGER ne 1}">
							    <img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_22', true,  'SHIP_POINT_ID', 'SHIP_POINT_ID','forms[0]','SHIP_POINT_NO,SHIP_POINT_NAME','SHIP_POINT_NO,SHIP_POINT_NAME','selectShip');"/>
							 	</c:if>
							</td>
							-->
								<td width="10%" align="right" class="detail_label">
									省份：
								</td>
								<td width="15%" class="detail_content">
				   					<input type="text" id="PROV" name="PROV" json="PROV"   value="${params.PROV}"/>
								    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[0]','PROV', 'PROV', '')">						   
								</td>
								<c:if test="${IS_DRP_LEDGER ne 1}">
							    <td width="10%" align="right" class="detail_label"> 分管设置： </td>
								<td width="15%" class="detail_content">
								  <input type="checkbox" id="ISAllCHANN" name="ISAllCHANN" value="1"/>显示全部
				   			  	</td>
			   				    </c:if>
			   				    <c:if test="${IS_DRP_LEDGER eq 1}">
							 	<td width="10%" align="right" class="detail_label"> </td>
								<td width="15%" class="detail_content"></td>
							    </c:if>
						    </tr>
						<!--   
						<tr>
			   				<td width="10%" align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"></td>
							<td width="10%" align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"></td>
							 
						</tr>-->
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
  var rtnArr = selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_NO','PRD_NO','selectParamsPro');
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




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
	<title>发货情况统计表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;发货情况统计表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toQueryDeliver" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
						<input id="selectDELIVERParam" name="selectDELIVERParam" type="hidden" value=" DEL_FLAG=0 and state != '未提交' "/>
						<input type="hidden" name="selectParamsChann" value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG} ">
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selectAddrParams" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selectAddrParams" value="STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID='${channInfo.CHANN_ID}' ">
						</c:if>
						
						
						<input type="hidden" id="selSaleOrderParam" name="selSaleOrderParam" value=" STATE in('审核通过','退回') and ORDER_CHANN_ID in ${CHANNS_CHARG}"/>
						<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
						<input type="hidden" name="selectShip" id="selectShip" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectPrdGroup" id="selectPrdGroup" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
						<tr>
							<td width="10%" align="right" class="detail_label">
								战区：
							</td>
							<td width="15%" class="detail_content">
								<input id="AREA_NAME" name="AREA_NAME"  json="AREA_NAME" type="text"  
									<c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.AREA_NAME_P}" readonly="readonly"</c:if>
								/>
								<c:if test="${IS_DRP_LEDGER ne 1}">
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_26', true, 'AREA_NAME', 'AREA_NAME', 'forms[0]',
												'AREA_NAME', 
												'AREA_NAME', 
												'selectParamsArea');">
								</c:if>						   
							</td>
							<td width="8%" nowrap align="right" class="detail_label">
								所属生产基地:
							</td>
							<td width="15%" class="detail_content">
								 <input type="hidden" id="SHIP_POINT_ID" name="SHIP_POINT_ID" <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.SHIP_POINT_ID}" readonly </c:if>/>
								<input id="SHIP_POINT_NAME"    name="SHIP_POINT_NAME"   json="SHIP_POINT_NAME" type="text"  <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.SHIP_POINT_NAME}" readonly </c:if>>
								<c:if test="${IS_DRP_LEDGER ne 1}">
							    <img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_22', false,  'SHIP_POINT_ID', 'SHIP_POINT_ID','forms[0]','SHIP_POINT_ID,SHIP_POINT_NAME','SHIP_POINT_ID,SHIP_POINT_NAME','selectShip');"/>
							 	</c:if>
							</td>
							<c:if test="${IS_DRP_LEDGER ne 1}">
							<td width="10%" align="right" class="detail_label">
								省份：
							</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="PROV" name="PROV" json="PROV"   value="${params.PROV}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[0]','PROV', 'PROV', '')">						   
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								业务员：
							</td>
							<td width="15%" class="detail_content">
								<input name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" type="text"   value="${params.AREA_MANAGE_NAME}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
							     		onclick="selCommon('BS_116', false, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[0]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME','selectParamsChann');"/>
							</td>
							</c:if>
							<c:if test="${IS_DRP_LEDGER eq 1}">
								<td width="10%" nowrap align="right" class="detail_label"></td>
								<td width="15%" class="detail_content">
								<td width="10%" nowrap align="right" class="detail_label"></td>
								<td width="15%" class="detail_content">
								</td>
							</c:if>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								货品分类：
							</td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PRD_TYPE_NAME" name="PRD_TYPE_NAME" type="text" readonly  value="${params.PAR_PRD_NAME}"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PRD_TYPE_NAME','PRD_NAME','selectParamsParPro');"/>
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								货品组:
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_GROUP_ID" name="PRD_GROUP_ID"  json="PRD_GROUP_ID" type="hidden"  value="${params.PRD_GROUP_ID}"/>
								<input id="PRD_GROUP_NAME" name="PRD_GROUP_NAME"  json="PRD_GROUP_NAME" type="text"  value="${params.PRD_GROUP_NAME}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_29', true, 'PRD_GROUP_ID', 'PRD_GROUP_ID', 'forms[0]','PRD_GROUP_NAME','PRD_GROUP_NAME','selectPrdGroup');"/>
							</td>
							<td width="8%" nowrap align="right" class="detail_label">
								货品编号:
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_ID" name="PRD_ID"  json="PRD_ID" type="hidden"  value="${params.PRD_ID}"/>
								<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" type="text"  value="${params.PRD_NO}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_NO,PRD_NAME','PRD_NO,PRD_NAME','selectParamsPro');"/>
							</td>
							<td width="10%" align="right" class="detail_label">
								货品名称：
							</td>
							<td width="15%" class="detail_content">
								<input name="PRD_NAME" id="PRD_NAME" type="text"   value="${params.PRD_NAME}"/>
							</td>
						
						</tr>
						<tr>
							
								<td width="10%" align="right" class="detail_label">
									发运单号：
								</td>
								<td width="15%" class="detail_content">
									<input name="DELIVER_ORDER_NO" id="DELIVER_ORDER_NO" type="text"   value="${params.DELIVER_ORDER_NO}"/>
									<input type="hidden" id="DELIVER_ORDER_ID" name="DELIVER_ORDER_ID"/>
									<c:if test="${IS_DRP_LEDGER ne 1}">
					   				<img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								     		onclick="selCommon('BS_68', false, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[0]','DELIVER_ORDER_NO', 'DELIVER_ORDER_NO','selectDELIVERParam');"/>
									&nbsp;&nbsp;	</c:if>					   
								</td>
								
								<td width="10%" align="right" class="detail_label"nowrap="nowrap">
									发货日期从：
								</td>
								<td width="15%" class="detail_content">
									<input json="ADVC_SEND_DATE_BENG" name="ADVC_SEND_DATE_BENG" id="ADVC_SEND_DATE_BENG" type="text" inputtype="date" autocheck="true" mustinput="true" value="" label="发货日期从" onclick="SelectDate()">	
									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_BENG);">
								</td>
								<td width="10%" align="right" class="detail_label"nowrap="nowrap">
									发货日期到：
								</td>
								<td width="15%" class="detail_content">
									<input json="ADVC_SEND_DATE_END" name="ADVC_SEND_DATE_END" id="ADVC_SEND_DATE_END" type="text"  inputtype="date" autocheck="true" value="" mustinput="true" label="发货日期到" onclick="SelectDate()">	
									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_END);">
								</td>
								<td width="8%" nowrap align="right" class="detail_label">
									发货方式:
								</td>
								<td width="15%" class="detail_content">
								 <select name=DELIVER_TYPE id="DELIVER_TYPE"  style="width: 155"></select>
								</td>
						</tr>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">订货客户编号:</td>
							<td width="15%" class="detail_content">
				   				<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  json="ORDER_CHANN_NO"   <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NO}" readonly </c:if>/>
				   				<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'ORDER_CHANN_NO', 'CHANN_NO', 'forms[0]',
										'ORDER_CHANN_NO,ORDER_CHANN_NAME', 
										'CHANN_NO,CHANN_NAME',
										'selectParamsChann');"> 
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">订货客户名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME"    <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NAME}" readonly </c:if>/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">出货计划号</td>
							<td width="15%" class="detail_content">
								<input type="text" id="JOIN_DELIVER_ORDER_NO" name="JOIN_DELIVER_ORDER_NO" />
							</td>
							<td width="10%" nowrap align="right" class="detail_label">订单单号查询</td>
							<td width="15%" class="detail_content">
								<input type="text" id="SALE_ORDER_NO" name="SALE_ORDER_NO"  />
								<c:if test="${IS_DRP_LEDGER ne 1}">
								<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_70', true, 'SALE_ORDER_NO', 'SALE_ORDER_NO', 'forms[0]','SALE_ORDER_NO', 'SALE_ORDER_NO', 'selSaleOrderParam')">
						</c:if>
							</td>
						</tr>
						<tr>
						<td width="10%" nowrap align="right" class="detail_label">收货客户编号:</td>
							<td width="15%" class="detail_content">
				   				<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  json="RECV_CHANN_NO" style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NO}" readonly </c:if>/>
				   				<c:if test="${IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'RECV_CHANN_NO', 'CHANN_NO', 'forms[0]',
										'RECV_CHANN_NO,RECV_CHANN_NAME', 
										'CHANN_NO,CHANN_NAME',
										'selectParamsChann');"> 
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">收货客户名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME" json="RECV_CHANN_NAME"  style="width:155" <c:if test="${IS_DRP_LEDGER eq 1}">	value="${channInfo.CHANN_NAME}" readonly </c:if>/>
							</td>
							<td width="8%" nowrap align="right" class="detail_label">收货地址编号：</td>
							<td width="15%" class="detail_content">
							   <input type="text" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value=""/>
		                       <img align="absmiddle" name="selAddr" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
		                       onclick="selCommon('BS_76', false, 'RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'forms[0]','RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'selectAddrParams');">
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
	SelDictShow("DELIVER_TYPE","BS_56","${params.DELIVER_TYPE}","");												
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




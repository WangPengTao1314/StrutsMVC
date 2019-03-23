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
	<title>门店销售跟踪表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;门店销售跟踪表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="${actionPath}.shtml?action=toReportPageResult" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsBrand" value=" STATE in( '启用','停用') and DEL_FLAG='0' ">
						<input type="hidden" name="selectADC" value=" STATE='提交' and DEL_FLAG=0 and LEDGER_ID='${params.LEDGER_ID}' and BILL_TYPE='终端销售' "/>
						<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${params.LEDGER_ID}'))">
						<tr>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								销售日期：
							</td>
							<td width="15%" class="detail_content">
								<input json="sale_date" name="sale_date" id="sale_date" type="text" autocheck="true" inputtype="date" value="" label="销售日期" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(sale_date);">
							</td>
							<td width="10%" align="right" class="detail_label">
								品牌：
							</td>
							<td width="15%" class="detail_content">
								<input id="BRAND_ID" name="BRAND_ID"  json="BRAND_ID" type="hidden" style="width:155" value="${params.BRAND_ID}"/>
								<input id="BRAND" name="BRAND"  json="BRAND" style="width:155" value="${params.BRAND}"/>	   					
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_24', false, 'BRAND_ID', 'BRAND_ID', 'forms[0]',
												'BRAND_ID,BRAND', 
												'BRAND_ID,BRAND', 
												'selectParamsBrand');">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">订单编号:
							</td>
							<td width="15%" class="detail_content">
								<input id="ADVC_ORDER_ID" name="ADVC_ORDER_ID"  json="ADVC_ORDER_ID" type="hidden" style="width:155" value="${params.ADVC_ORDER_ID}"/>
				   				<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  json="ADVC_ORDER_NO" style="width:155" value="${params.ADVC_ORDER_NO}"/>
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_55', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[0]',
										'ADVC_ORDER_ID,ADVC_ORDER_NO', 
										'ADVC_ORDER_ID,ADVC_ORDER_NO', 
										'selectADC');"> 
							</td>								
						</tr>						
						<tr>							
							<td width="10%" align="right" class="detail_label">
								产品编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="PRD_ID" name="PRD_ID"  json="PRD_ID" type="hidden" style="width:155" value="${params.PRD_ID}"/>
								<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" style="width:155" value="${params.PRD_NO}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif"
			   						onclick="selCommon('BS_21', false, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_NO,PRD_NAME','PRD_NO,PRD_NAME','selectParamsPro');"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">产品名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="PRD_NAME" name="PRD_NAME"  style="width:155" value="${params.PRD_NAME}"/>
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								购买日期：
							</td>	
							<td width="15%" class="detail_content">
								<input json="buyDate" name="buyDate" id="buyDate" type="text" autocheck="true" inputtype="date" value="" label="购买日期"  onclick="SelectDate()">
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(buyDate);">
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




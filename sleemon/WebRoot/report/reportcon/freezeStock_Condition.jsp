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
	<title>冻结库存明细统计</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;销售报表&gt;&gt;冻结库存明细统计</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toFreezeStockDtlResult" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<tr>
							<td width="10%" align="right" class="detail_label">
								销售日期从：
							</td>
							<td width="15%" class="detail_content">
								<input json="START_SALE_DATE" name="START_SALE_DATE" id="START_SALE_DATE" type="text"  value="${params.START_SALE_DATE}" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(START_SALE_DATE);">
							</td>
							<td width="10%" align="right" class="detail_label">
								销售日期到：
							</td>
							<td width="15%" class="detail_content">
								<input json="END_SALE_DATE" name="END_SALE_DATE" id="END_SALE_DATE" type="text"  value="${params.END_SALE_DATE}" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_SALE_DATE);">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">货品编号：</td>
							<td width="15%" class="detail_content">
							 <input type="text" id="PRD_NO" name="PRD_NO" />
							</td>
							<td width="10%" nowrap align="right" class="detail_label">货品名称：</td>
							<td width="15%" class="detail_content">
							 <input type="text" id="PRD_NAME" name="PRD_NAME"/>
							</td>
						</tr>
						<tr>
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




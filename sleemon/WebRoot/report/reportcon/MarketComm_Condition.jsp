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
	<title>活动提成查询报表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;活动提成查询报表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toMarketCommReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsChann" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
					    <input type="hidden" name="selectActParams" id="selectActParams" value=" STATE='审核通过' and DEL_FLAG=0 ">
					    <input type="hidden" name="selectRYXXParams" id="selectRYXXParams" value=" RYZT='启用' and DELFLAG=0 ">
					    
						<tr>
		                   <td width="10%" align="right" class="detail_label">活动编号：</td>
						   <td width="15%" class="detail_content">
						     <input type="hidden" id="MARKETING_ACT_ID" name="MARKETING_ACT_ID" json="MARKETING_ACT_ID" value="${rst.MARKETING_ACT_ID}"/>
		                     <input json="MARKETING_ACT_NO" name="MARKETING_ACT_NO" id="MARKETING_ACT_NO" label="活动编号" 
		                      inputtype="string"  autocheck="true"  
		                      type="text" value="${rst.MARKETING_ACT_NO}"/> 
		                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
									 src="${ctx}/styles/${theme}/images/plus/select.gif"
									 onClick="selCommon('BS_149', false, 'MARKETING_ACT_ID', 'MARKETING_ACT_ID', 'forms[0]','MARKETING_ACT_NO,MARKETING_ACT_NAME', 'MARKETING_ACT_NO,MARKETING_ACT_NAME', 'selectActParams');">
						   </td>
						   <td width="10%" align="right" class="detail_label"> 活动名称： </td>
							<td width="15%" class="detail_content">
							  <input id="MARKETING_ACT_NAME" json="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" 
							  inputtype="string"   autocheck="true"  
							  label="活动名称"  value="${rst.MARKETING_ACT_NAME}" />
							</td>
					         <td width="10%" align="right" class="detail_label">业务员：</td>
							 <td width="15%" class="detail_content">
							    <input  type="hidden"  name="SALE_PSON_ID" id="SALE_PSON_ID" json="SALE_PSON_ID" value="${rst.SALE_PSON_ID}"/>
			                     <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" id="SALE_PSON_NAME" label="业务员"   type="text" value="${rst.SALE_PSON_NAME}"/> 
			                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
										 src="${ctx}/styles/${theme}/images/plus/select.gif"
										 onClick="selCommon('0', false, 'SALE_PSON_ID', 'RYXXID', 'forms[0]','SALE_PSON_NAME', 'XM', 'selectRYXXParams')">
											
							   </td>
							
						</tr>
						
						<tr>							
						   <td width="10%" nowrap align="right" class="detail_label">渠道编号:
							</td>
							<td width="15%" class="detail_content">
								<input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden" style="width:155" value="${params.CHANN_ID}"/>
				   				<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" style="width:155" value="${params.CHANN_NO}"/>
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_NO,CHANN_NAME', 
										'CHANN_NO,CHANN_NAME', 
										'selectParamsChann');"> 
							</td>
							<td width="10%" nowrap align="right" class="detail_label">渠道名称:</td>
							<td width="15%" class="detail_content">
			   					<input id="CHANN_NAME" name="CHANN_NAME" json="CHANN_NAME"  style="width:155" value="${params.CHANN_NAME}"/>
							</td> 
							
							 <td width="10%" align="right" class="detail_label"></td>
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
	SelDictShow("STATE","BS_127","${params.STATE}","");
													
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




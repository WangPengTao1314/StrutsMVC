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
	<title>销量统计比例</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;销量统计比例</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toSalePercentage" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">货品分类编号: </td>
							<td width="15%" class="detail_content">
								<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
				   				<input id="PRD_TYPE_NO" name="PRD_TYPE_NO" type="text"  value="${params.PRD_TYPE_NO}" label="货品分类编号" autocheck="true" inputtype="string"  mustinput="true"/>
				   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selPrdParent();"/>
							</td>
							<td width="10%" align="right" class="detail_label">货品分类名称： </td>
							<td width="15%" class="detail_content">
							  <input id="PRD_TYPE_NAME" name="PRD_TYPE_NAME" type="text"  value="${params.PRD_TYPE_NAME}" label="货品分类名称" autocheck="true" inputtype="string"  mustinput="true"/>
							</td>
							<td width="10%" align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"></td>
							<td width="10%" align="right" class="detail_label"> </td>
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
	//SelDictShow("STATE","BS_126","${params.STATE}","");
													
$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		var PRD_TYPE_NO = $.trim($("#PRD_TYPE_NO").val());
		var PRD_TYPE_NAME = $.trim($("#PRD_TYPE_NAME").val());
		/*
		if((null == PRD_TYPE_NO || "" == PRD_TYPE_NO) && (null== PRD_TYPE_NAME || "" == PRD_TYPE_NAME)){
			parent.showErrorMsg("请选择'货品分类编号'或者'货品分类名称'!");
			return;
		}*/
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
	selCommon('BS_21', false, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PRD_TYPE_NO,PRD_TYPE_NAME','PRD_NO,PRD_NAME','selectParamsParPro');
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




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
	<title>加盟商营销经理评分表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;加盟商营销经理评分表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toChannMkmReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
	   <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null "/>
	   <input type="hidden" name="selectParamsManage" id="selectParamsManage" />
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
									onClick="selCommon('BS_175', false, 'WAREA_NAME', 'AREA_NAME', 'forms[0]',
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
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								评价日期从：
							</td>
							<td width="15%" class="detail_content">
							   <input json="SCORE_DATE_BEG" readonly name="SCORE_DATE_BEG" id="SCORE_DATE_BEG" type="text"  label="评价日期从" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SCORE_DATE_BEG);">
							</td>
							<td width="10%"  class="detail_label" >评价日期至：</td>
							<td width="15%" class="detail_content">
							   <input json="SCORE_DATE_END" readonly name="SCORE_DATE_END" id="SCORE_DATE_END" type="text"  label="评价日期至" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SCORE_DATE_END);">
							</td>
						</tr>
						<tr id="showTr">
						  <td width="10%" align="right" class="detail_label"> 显示模块： </td>
						  <td width="15%" class="detail_content" colspan="5">
						    <input type="checkbox" id="WORK_VISIT_SHOW" name="WORK_VISIT_SHOW" value="WORK_VISIT_SHOW" >工作拜访频率</input>
						    <input type="checkbox" id="OFFICE_SHOW" name="OFFICE_SHOW" value="OFFICE_SHOW" >公司政策传达</input>
						    <input type="checkbox" id="TERM_CHECK_SHOW" name="TERM_CHECK_SHOW" value="TERM_CHECK_SHOW" >门店精致化</input>
						    <input type="checkbox" id="SHOP_GUIDE_SHOW" name="SHOP_GUIDE_SHOW" value="SHOP_GUIDE_SHOW" >导购员培训</input>
						    <input type="checkbox" id="PRMIT_SHOW" name="PRMIT_SHOW" value="PRMIT_SHOW" >店外推广执行</input>
						    <input type="checkbox" id="DETRIBUTE_VISIT_SHOW" name="DETRIBUTE_VISIT_SHOW" value="DETRIBUTE_VISIT_SHOW" >分销商开发与维护</input>
						    <input type="checkbox" id="QUESTION_VISIT_SHOW" name="QUESTION_VISIT_SHOW" value="QUESTION_VISIT_SHOW" >问题分析解决</input>
						  </td>
						  <td width="10%" align="right" class="detail_label"> 是否显示分数:</td>
						  <td width="15%" class="detail_content" colspan="5">
						     <input type="checkbox" id="SHOW_SCORE_SHOW" name="SHOW_SCORE_SHOW" value="SHOW_SCORE_SHOW" />
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
//SelDictShow("QUERY_STYLE","BS_180","${rst.QUERY_STYLE}","");
SelDictShow("STATE","33","${params.STATE}","");  

$(function(){	
	var myDate = new Date(); 
	//myDate.getYear(); //获取当前年份(2位) 
	$("#YEAR").val(myDate.getFullYear());
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
      
      //selCommon('BS_189', true, 'MKM_NAME', 'AREA_MANAGE_NAME', 'forms[0]','MKM_NAME', 'AREA_MANAGE_NAME', 'selectParamsManage');
	  var WAREA_NAME = $("#WAREA_NAME").val();
	  if(WAREA_NAME == ""){
	      parent.showErrorMsg("请先选择战区!");
	  } else{
		  $("#selectParamsManage").val(" AREA_NAME_P like '%"+WAREA_NAME+"%' AND AREA_MANAGE_NAME IS NOT NULL ")
		  selCommon('BS_189', true, 'MKM_NAME', 'AREA_MANAGE_NAME', 'forms[0]','MKM_NAME', 'AREA_MANAGE_NAME', 'selectParamsManage');
	  } 
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




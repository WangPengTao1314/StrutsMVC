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
	<title>营销经理日报表</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;营销经理日报表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toMkmDayReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
	   <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null "/>
	   <input type="hidden" name="selectParamsManage" id="selectParamsManage" />
	   <input type="hidden" name="selectDeptParamsT" id="selectDeptParamsT" value=" BMZT='启用' and JGXXID='00'">
 		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsChann" id="selectParamsChann"  value="  CHANN_TYPE = '直营办' and DEL_FLAG='0' ">
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">部门名称:</td>
							<td width="15%" class="detail_content">
								<input id="WAREA_NAME" name="WAREA_NAME"  json="WAREA_NAME" value="${params.WAREA_NAME}" readonly />
				   				<img align="absmiddle" name="selJGXX" style="cursor: hand"
									 src="${ctx}/styles/${theme}/images/plus/select.gif"
									 onClick="selCommon('BS_1', true, 'WAREA_NAME', 'BMMC', 'forms[0]','WAREA_NAME', 'BMMC', 'selectDeptParamsT')">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">营销经理:</td>
							<td width="15%" class="detail_content">
			   					<input id="MKM_NAME" name="MKM_NAME" readonly json="MKM_NAME" value="${params.MKM_NAME}" />
							    <img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="getMkmName();" /> 
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								填写日期从：
							</td>
							<td width="15%" class="detail_content">
							   <input json="REPORT_DATE_BEG" readonly name="REPORT_DATE_BEG" id="REPORT_DATE_BEG" type="text"  label="填写日期从" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(REPORT_DATE_BEG);">
							</td>
							<td width="10%"  class="detail_label" >填写日期至：</td>
							<td width="15%" class="detail_content">
							   <input json="REPORT_DATE_END" readonly name="REPORT_DATE_END" id="REPORT_DATE_END" type="text"  label="填写日期至" onclick="SelectDate()" onblur="checkReportDate(this)" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(REPORT_DATE_END);">
							</td>
						</tr>
						<tr>
							<td width="10%" nowrap align="right" class="detail_label">拜访日期从:</td>
							<td width="15%" class="detail_content">
							   <input json="VST_DATE_BEG" readonly name="VST_DATE_BEG" id="VST_DATE_BEG" type="text" label="拜访日期从" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(VST_DATE_BEG);">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">拜访日期至:</td>
							<td width="15%" class="detail_content">
			   				   <input json="VST_DATE_END" readonly name="VST_DATE_END" id="VST_DATE_END" type="text" label="拜访日期至" onclick="SelectDate()" ONREADONLY="true" />	
							   <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(VST_DATE_END);">
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								查看方式：
							</td>
							<td width="15%" class="detail_content">
							   <SELECT json="QUERY_STYLE" name="QUERY_STYLE" id="QUERY_STYLE" label="查看方式"  style="width:155px;"></SELECT>	
 							</td>
 							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								状态：
							</td>
							<td width="15%" class="detail_content">
							   <SELECT json="STATE" name="STATE" id="STATE" label="状态"  style="width:155px;"></SELECT>	
 							</td>
						</tr>
						<tr id="showTr">
						  <td width="10%" align="right" class="detail_label"> 显示模块： </td>
						  <td width="15%" class="detail_content" colspan="7">
						    <input type="checkbox" id="CHANN_VISIT_SHOW" name="CHANN_VISIT_SHOW" value="CHANN_VISIT_SHOW" >加盟商拜访</input>
						    <input type="checkbox" id="PROMOTION_ACTV_SHOW" name="PROMOTION_ACTV_SHOW" value="PROMOTION_ACTV_SHOW" >推广活动推进</input>
						    <input type="checkbox" id="DISTRIBUTE_SHOW" name="DISTRIBUTE_SHOW" value="DISTRIBUTE_SHOW" >分销商拜访与开发</input>
						    <input type="checkbox" id="COOP_VISIT_SHOW" name="COOP_VISIT_SHOW" value="COOP_VISIT_SHOW" >"1+N"合作客户开发与拜访</input>
						    <input type="checkbox" id="SHOP_GUIDE_SHOW" name="SHOP_GUIDE_SHOW" value="SHOP_GUIDE_SHOW" >导购员培训</input>
						    <input type="checkbox" id="STORE_VISIT_SHOW" name="STORE_VISIT_SHOW" value="STORE_VISIT_SHOW" >门店拜访</input>
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

var STATE = '${rst.STATE }';
if(""==STATE||null==STATE){
	STATE = "审核通过";
}
SelDictShow("STATE","33",STATE,"");  

var QUERY_STYLE='${rst.QUERY_STYLE }';
if(""==QUERY_STYLE||null==QUERY_STYLE){
	QUERY_STYLE="报表形式";
}
SelDictShow("QUERY_STYLE","BS_180",QUERY_STYLE,"");

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
	  var WAREA_NAME = $("#WAREA_NAME").val();
	  var strNames = "";
	  if(WAREA_NAME == ""){
	      parent.showErrorMsg("请先选择部门信息!");
	  } else{
	      var arr = new Array();
	      arr = WAREA_NAME.split(",");
	      for(var i=0;i<arr.length;i++){
	        strNames += "'"+arr[i]+"'"+',';
	      }
	      strNames = strNames.substr(0,strNames.length-1);
		  $("#selectParamsManage").val(" BMMC  IN ("+strNames+") AND XM IS NOT NULL ")
		  selCommon('BS_189', true, 'MKM_NAME', 'XM', 'forms[0]','MKM_NAME', 'XM', 'selectParamsManage');
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
	
	function  checkReportDate(param){
	    
	   var REPORT_DATE_BEG = $("#REPORT_DATE_BEG").val();
	   var arr = REPORT_DATE_BEG.split("-");
	   var REPORT_DATE_BEG_CHECK = arr[1];
	   var REPORT_DATE_END  = param.value;
	   var arrt = REPORT_DATE_END.split("-");
	   var REPORT_DATE_END_CHECK = arrt[1];
	   if(eval(REPORT_DATE_BEG_CHECK)!=eval(REPORT_DATE_END_CHECK)){
	      parent.showErrorMsg("请选择与【填写日期从】同月");
	      document.getElementById("REPORT_DATE_END_CHECK").value="";
	   }
	}
</script>




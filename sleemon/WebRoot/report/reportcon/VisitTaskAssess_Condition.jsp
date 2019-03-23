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
	<title>拜访达成考核目标</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;营销报表&gt;&gt;拜访达成考核目标</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toVisitTaskAssessReport" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
						<input type="hidden" id="selAREAParam" name="selAREAParam" value=" STATE in('启用','停用') and AREA_ID_P is not null "/>
						<input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
					 
						 
						<tr>
							<td width="10%" align="right" class="detail_label">
								战区编号：
							</td>
							<td width="15%" class="detail_content">
								<input id="WAREA_ID" name="WAREA_ID"  json="WAREA_ID" type="hidden"  />
								<input id="WAREA_NO" name="WAREA_NO"  json="WAREA_NO" type="text"  />
								<c:if test="${IS_DRP_LEDGER ne 1}">
			   					<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_26', false, 'WAREA_ID', 'AREA_ID', 'forms[0]',
												'WAREA_NO,WAREA_NAME', 
												'AREA_NO,AREA_NAME', 
												'selectParamsArea');changeAreaManager();">
								</c:if>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">战区名称：</td>
							<td width="15%" class="detail_content">
			   					<input id="WAREA_NAME" name="WAREA_NAME" type="text"  />
							</td>
							<td width="10%" align="right" class="detail_label">
									省份：
							</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="PROV" name="PROV" json="PROV"   value="${params.PROV}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[0]','PROV', 'PROV', '')">						   
							</td>
							<td width="10%" align="right" class="detail_label">
									区域经理：
							</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME"   value="${params.AREA_MANAGE_NAME}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[0]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
							</td> 
						</tr>
					   <tr>
					        <td width="10%" align="right" class="detail_label"nowrap="nowrap"> 年份： </td>
							<td width="15%" class="detail_content">
							  <select json="YEAR" name="YEAR" id="YEAR" style="width:155px;"  label="年份"  ></select>
							   <SPAN class="validate">&nbsp;*</SPAN> 
							</td>
							<td width="10%" align="right" class="detail_label"> 拜访类型： </td>
							<td width="15%" class="detail_content">
							 <select id="VISIT_OBJ_TYPE" name="VISIT_OBJ_TYPE" style="width:155px;"  ></select>
							</td> 
							<td width="10%" align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"> </td> 
							<td width="10%" align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"> </td> 			
					    
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
	SelDictShow("VISIT_OBJ_TYPE","BS_159","${params.VISIT_OBJ_TYPE}","");
	var myDate = new Date(); 
	myDate.getYear(); //获取当前年份(2位) 
	SelDictShow_Default("YEAR", "89", '${rst.YEAR}', myDate.getFullYear());
	 
$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		
		if(null == $("#YEAR").val() || "" == $("#YEAR").val()){
			parent.showErrorMsg("请先选择'年份'"); 
			return;
		}
		var obj = parent.document.getElementById("butHidTop");
	 	$(obj).trigger("click");
		$("#queryForm").submit();
	});	
});
	 

  
 
 
//选择 
function changeAreaManager(){
	var WAREA_NAME = $("#WAREA_NAME").val();
	var WAREA_NO = $("#WAREA_NO").val();
	var param = " 1=1 ";
	if(null != WAREA_NAME && "" != WAREA_NAME){
		param = param+  " and AREA_NAME_P like '%"+WAREA_NAME+"%' "
	}
	if(null != WAREA_NO && "" != WAREA_NO){
		param = param + " and AREA_NO_P like '%"+WAREA_NO+"%' "
	}
	$("#selectAreaManager").val(param);
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




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
	<title>月度拜访工作计划达成率表</title>
	<script type="text/javascript">
	     function  checkT1(){
	        document.getElementById("WAREA_ID_SHOW").checked=true;
	        document.getElementById("RYXX_SHOW").checked=false;
	        document.getElementById("RYMC").value="";
	     }
	     
	     function  checkT(){
	        document.getElementById("WAREA_ID_SHOW").checked=false;
	        document.getElementById("RYXX_SHOW").checked=true;
	        document.getElementById("WAREA_NAME").value= "";
	     }
	     function getVisit(){
	        var str = "";
	        var VISIT_OBJECT = document.getElementsByName("VISIT_OBJECT");
	        for(var i=0;i<VISIT_OBJECT.length;i++){
	            if(VISIT_OBJECT[i].checked){
	                str +=VISIT_OBJECT[i].value+",";
	            }
	        }
	        document.getElementById("VISIT").value = str;
	     }
	     
	     function checkTt(){
	            selCommon('BS_0', true, 'RYBH', 'RYBH', 'forms[0]','RYBH,RYMC', 'RYBH,XM', 'selectParams');
	     }
	     
	     function checkT1t(){
	           selCommon('BS_147', true, 'WAREA_ID', 'BMBH', 'forms[0]','WAREA_ID,WAREA_NAME', 'BMXXID,BMMC', 'selectParamsT');
	     }
	</script>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表中心&gt;&gt;报表管理&gt;&gt;营销报表&gt;&gt;月度拜访工作计划达成率表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toMonthvisitReport" target="bottomcontent${rptConFile}">
    <input type="hidden"  name="seleyearparams" />
    <input type="hidden"  name="selemonthparams"/>
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<c:if test="${IS_DRP_LEDGER eq 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID='${ZTXXID}' ">
						</c:if>
						<c:if test="${IS_DRP_LEDGER ne 1}">
							<input type="hidden" name="selErpSale" id="selErpSale" value=" DEL_FLAG='0' and ORDER_CHANN_ID in ${CHANNS_CHARG} ">
						</c:if>
						<tr>
							<td width="10%" align="right" class="detail_label">
								员工：
							</td>
							<td width="15%" class="detail_content">
								<input name="selectParams"  id="selectParams" type="hidden"/>
						    <input json="RYBH"  id="RYBH"  name="RYBH" type="hidden"  value="${params.RYBH}" />
								 <input type="text" name="RYMC" id="RYMC" json="RYMC" />
							    <img align="absmiddle" name="selJGXX" style="cursor: hand"
											src="${ctx}/styles/${theme}/images/plus/select.gif"       
											onClick="checkTt();">			
							</td>
							<td width="10%" align="right" class="detail_label">
								战区：
							</td>
							<td width="15%" class="detail_content">
								 <input type="hidden" name="selectParamsT"  id="selectParamsT" value="${params.selectParamsT}" />
						     <input type="hidden" name="WAREA_ID"   id="WAREA_ID"   json="WAREA_ID" type="hidden" value="${params.WAREA_ID}" />
						     <input type="text"   name="WAREA_NAME" id="WAREA_NAME" json="WAREA_NAME" />
						     <img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="checkT1t();">				
							</td>
							<td width="10%" nowrap align="right" class="detail_label">年份：</td>
							<td width="15%" class="detail_content">
								<select name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" style="width:180px;" value="${rst.PLAN_YEAR}" autocheck="true" inputtype="string" mustinput="true" readonly>
						         </select>		
							</td>
							<td width="10%" nowrap align="right" class="detail_label">月份：</td>
							<td width="15%" class="detail_content">
								<select name="PLAN_MONTH" id="PLAN_MONTH" json="PLAN_MONTH" style="width:180px;" value="${rst.PLAN_MONTH}" autocheck="true" inputtype="string" mustinput="true"  readonly>
						         </select>		
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
	SelDictShow("PLAN_YEAR","89","${rst.PLAN_YEAR}","");
	SelDictShow("PLAN_MONTH","168","${rst.PLAN_MONTH}","");
$(function(){	
//form校验设置
	InitFormValidator(0);
 	//查询！！！！！！
	$("#qurey").click(function(){
		if(!formChecked('queryForm')){
			return;
		}
		var  PLAN_YEAR  = $("#PLAN_YEAR").val();
		var  PLAN_MONTH = $("#PLAN_MONTH").val();
		if(PLAN_YEAR==""){
		   parent.showErrorMsg("请先选择'年份''"); 
	       return;
		}
		if(PLAN_MONTH==""){
		   parent.showErrorMsg("请先选择'月份''"); 
	       return;
		}
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




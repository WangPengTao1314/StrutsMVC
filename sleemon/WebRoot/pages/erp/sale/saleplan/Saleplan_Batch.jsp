<!-- 
 *@module 营销管理-销售指标管理
 *@func   渠道销售指标设定维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道销售指标批量维护</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleplan/Saleplan_Batch.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function batch(){
		     var PLAN_YEAR = $("#PLAN_YEAR").val();
		     var WAREA_NO  = $("#WAREA_NO").val();
		     var WAREA_NAME = $("#WAREA_NAME").val();
		     var CHANN_NO   = $("#CHANN_NO").val();
		     var CHANN_NAME = $("#CHANN_NAME").val();
			 var url="saleplan.shtml?action=toBatchList&PLAN_YEAR="+PLAN_YEAR+"&AREA_NO="+WAREA_NO+"&AREA_NAME="+utf8(WAREA_NAME)+"&CHANN_NO="+CHANN_NO+"&CHANN_NAME="+utf8(CHANN_NAME);
	         $("#topcontent").attr("src",url);
		}
	</script>
</head>
  
<body class="bodycss1"> 
  <form method="POST" action="#" id="queryForm" >
  <table  width="100%" border="0" cellpadding="4" cellspacing="4">
  <tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
                       年份:
				    <select name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" value="${rst.PLAN_YEAR}" style="width:100px;" autocheck="true" inputtype="string">
				           <c:forEach items="${list}" var="list">    							
					       <c:choose>
					          <c:when test="${list eq year}">
					              <option selected="selected" value="${list}">${list}</option>    	
					          </c:when>
					          <c:otherwise>
					              <option value="${list}">${list}</option>    	
					          </c:otherwise>
					        </c:choose>  						
					      </c:forEach>
				   </td>
				   <td nowrap>
				     渠道编号:
				     <input id="selectParamsT" name="selectParamsT" type="hidden"  value="STATE = '启用' and DEL_FLAG=0 ">
					 <input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}"/>
                     <input type="text"   json="CHANN_NO" id="CHANN_NO" name="CHANN_NO" autocheck="true" inputtype="string" label="渠道编号" value="${rst.CHANN_NO}" mustinput="true" readonly/>
                     <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
						onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME','CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParamsT')"> 
				   </td>
				   <td nowrap>
				     渠道名称：
				      <input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text"
									label="渠道名称" value="${rst.CHANN_NAME}"
									size="20" readonly/> 
				   </td>
				   <td nowrap>
				      战区编号:
				      <input type="hidden" name="selectParams"     id="selectParams" />
			          <input id="WAREA_ID"     name="WAREA_ID"     json="AREA_ID"   value="${rst.WAREA_ID}"   type="hidden"/>
                      <input id="WAREA_NO"     name="WAREA_NO"     json="AREA_NO"   value="${rst.WAREA_NO}"   size="20" label="战区编号"  autocheck="true" inputtype="string"  mustinput="true" readonly/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
					       src="${ctx}/styles/${theme}/images/plus/select.gif"       
					       onClick="selCommon('BS_160', true, 'WAREA_NO', 'AREA_NO_P', 'forms[0]','WAREA_ID,WAREA_NO,WAREA_NAME', 'AREA_ID_P,AREA_NO_P,AREA_NAME_P', 'selectParams');">				
				   </td>
				   <td nowrap>
				     战区名称：
				      <input json="AREA_NAME" name="WAREA_NAME" id="WAREA_NAME" type="text"
									label="战区名称" value="${rst.WAREA_NAME}"
									size="20" readonly/> 
				   </td>
				</tr>
				<tr>
				  <td colspan="10" align="center" valign="middle" >
				     <input id="queryBatch" type="button" class="btn"   value="查询(F)" title="Alt+F" accesskey="F" onclick="batch()"/>
				     <input id="saveBatch"  type="button" class="btn"   value="保存(S)" title="Alt+S" accesskey="F" onclick="saveBatchT()"/>
				  </td>
				</tr>
			</table>
		</div>
	</td>
</tr>
</table>
<div style="width:1350px;height:400px;margin-left: 5px;margin-bottom: 50px;float: left">
	  <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
</div>
</form>
</body>
</html>

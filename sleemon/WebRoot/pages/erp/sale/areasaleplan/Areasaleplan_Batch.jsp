<!-- 
 *@module 营销管理-销售指标管理
 *@func   区域销售指标设定维护
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
	<title>区域销售指标批量维护</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/areasaleplan/Areasaleplan_Batch.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function batch(){
		     var PLAN_YEAR = $("#PLAN_YEAR").val();
		     var AREA_NO   = $("#AREA_NO").val();
		     var AREA_NAME = $("#AREA_NAME").val();
			 var url="areasaleplan.shtml?action=toBatchList&PLAN_YEAR="+PLAN_YEAR+"&AREA_NO="+AREA_NO+"&AREA_NAME="+utf8(AREA_NAME);
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
				      区域编号:
				      <input type="hidden" name="selectParams"     id="selectParams" />
			          <input id="AREA_ID"     name="AREA_ID"     json="AREA_ID"   value="${rst.AREA_ID}"   type="hidden"/>
                      <input id="AREA_NO"     name="AREA_NO"     json="AREA_NO"   value="${rst.AREA_NO}"   size="20" label="区域编号"  autocheck="true" inputtype="string"  mustinput="true" /> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
					       src="${ctx}/styles/${theme}/images/plus/select.gif"       
					       onClick="selCommon('BS_138', true, 'AREA_NO', 'AREA_NO', 'forms[0]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams');">				
				   </td>
				   <td nowrap>
				      区域名称：
				      <input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text"
									label="区域名称" value="${rst.AREA_NAME}"
									size="20" /> 
				   </td>
				</tr>
				<tr>
				  <td colspan="10" align="center" valign="middle" >
				     <input id="queryBatch" type="button" class="btn"   value="查询(F)" title="Alt+F" accesskey="F" onclick="batch()"/>
				     <input type="button" class="btn"   value="保存(S)" title="Alt+S" accesskey="F" onclick="saveBatchT()"/>
				  </td>
				</tr>
			</table>
		</div>
	</td>
</tr>
</table>
<div style="width:1350px;height:400px">
	  <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
</div>
</form>
</body>
</html>

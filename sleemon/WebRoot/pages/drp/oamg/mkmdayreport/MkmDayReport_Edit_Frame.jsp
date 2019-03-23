<!--  
/**
 *@module 渠道管理-上报管理
 *@func   营销经理上报维护
 *@version 1.1
 *@author zcx
*/
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/mkmdayreport/MkmDayReport_Edit_Frame.js"></script>
	<title>营销经理上报维护</title>
</head>
<body>
<table width="99.9%" height="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <div id="topdiv" style="height:100%;width:100%">
	    <!-- 上帧 -->
	    <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>
	  <!--  
	  <div class="tablayer tabBackground">
	           <table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td class="label_line" width="8px">&nbsp;</td>
					<td id="label" nowrap="nowrap">
						<span id="label_1" class="label_down" style="margin-top:2px;">&nbsp;终端申请单评估表&nbsp;</span>
						<input type="hidden" id="showLabel" value="label_1"/>
					</td>
					<td class="label_line" align="right" width="150px">
						<input type="button" id="butHidTop" class="button" value="↑" title="Alt+↑" >
						<input type="button" id="butHidBottom" class="button" value="↓" title="Alt+↓">
					</td>
				</tr>
			</table>  
	  </div>
	  <div id="bottomdiv" style="height:50%;width:100%">
	  <iframe id="bottomcontent" name="bottomcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div> 
	  -->
    </td>
  </tr>
</table>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
<input type="hidden" id="EDIT_FLAG" value="${EDIT_FLAG}"/>
</body>


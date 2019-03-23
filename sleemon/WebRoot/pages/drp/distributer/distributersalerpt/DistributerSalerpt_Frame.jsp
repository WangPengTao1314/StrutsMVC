<!--  
/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
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
	<script type=text/javascript src="${ctx}/pages/drp/distributer/distributersalerpt/DistributerSalerpt_Frame.js"></script>
	<title>分销商购货月报</title>
</head>
<body>
<table width="99.9%" height="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <div id="topdiv" style="height:98%;width:100%">
	    <!-- 上帧 -->
	    <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>	  
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
<input type="hidden" id="audit" value="${audit}"/>
</body>


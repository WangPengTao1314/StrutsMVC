
<!--  
/**
 * @module 系统管理
 * @func  系统业务表一览
 * @version 1.1
 * @author  zcx
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/base/table/Table_Detail.js"></script>
	<title>详细信息</title>
</head>
<table width="99.9%" height="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <div id="topdiv" style="height:65%;width:100%">
	    <!-- 上帧 -->
	    <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>
	  <div class="tablayer tabBackground">
	           <table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td class="label_line" width="8px">&nbsp;</td>
					<td id="label" nowrap="nowrap">
						<span id="label_1" class="label_down" style="margin-top:2px;">&nbsp;关联表信息&nbsp;</span>
						<input type="hidden" id="showLabel" value="label_1"/>
					</td>
					<td class="label_line" align="right" width="150px">
						<input type="button" id="butHidTop" class="button" value="↑" title="Alt+↑" >
						<input type="button" id="butHidBottom" class="button" value="↓" title="Alt+↓">
					</td>
				</tr>
			</table>  
	  </div>
	  <div id="bottomdiv" style="height:25%;width:100%">
	  <iframe id="bottomcontent" name="bottomcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
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
<input type="hidden" id="TABLE_NAME_EN" value="${TABLE_NAME_EN}" />
</body>
</html>

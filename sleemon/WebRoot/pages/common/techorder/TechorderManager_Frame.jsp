<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>框架信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/common/techorder/TechorderManager_Frame.js"></script>
	<style type="text/css">
		li{
			width:200px;
			float:left;
			list-style:none;
			height:20px;
			line-height:20px;
		}
		.rightLi{
			margin: 0px 0px;
			padding: 0px 0px;
			width: 90px;
		}
	</style>
</head>
  <body>
  <input type="hidden" value="${params.SPCL_TECH_ID}" id="SPCL_TECH_ID" />
  <input type="hidden" value="${params.PRD_ID}" id="PRD_ID" />
  <input type="hidden" value="${params.CHANN_ID}" id="CHANN_ID" />
  <input type="hidden" value="${params.USE_FLAG}" id="USE_FLAG" />
  <input type="hidden" value="${params.TABLE}" id="TABLE" />
  <input type="hidden" value="${params.BUSSID}" id="BUSSID" />
  <input type="hidden" value="${params.check}" id="check" />
  <input type="hidden" value="${params.citeUrl}" id="citeUrl" />
  <input type="hidden" value="${params.acqModel}" id="acqModel"/>
  <input type="hidden" value="${params.PRICE}" id="PRICE" />
  <input type="hidden" value="${params.optOldFlag}" id="optOldFlag" />
  <input type="hidden" value="${params.DECT_PRICE}" id="DECT_PRICE"/>
  <input type="hidden" value="${params.filterSpclFlag}" id="filterSpclFlag"/>
  <input type="hidden" id="flag" value="${flag}">
		<h2 align="center">货品特殊工艺<font id="prompt" color="red"></font></h2>
		<span style="float: right;" id="historySpan">
			<input type="button" class="btn" id="history" value="选择已有特殊工艺" onclick="historyWindow()" />
			<input type="button" class="btn" id="resetting" value="DIY特殊工艺" onclick="resetting()" />
		</span>
   	<div style="border:solid 2px #999;width: 99%;height: 90%;margin-left: 5px;margin-bottom: 50px;float: left" id="leftDiv">
   		<iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
   	</div>
  </body>

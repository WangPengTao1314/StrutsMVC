<!--
 * @prjName:喜临门营销平台
 * @fileName:Adviseinit_Frame
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>框架信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/drp/sale/adviseinit/Adviseinit_Frame.js"></script>
</head>
<BODY>
<table cellspacing="0" cellpadding="0" width="99.9%" height="98%">
		<tr>
	    <td height="20px" valign="top">
		 <table width="100%" cellspacing="0" cellpadding="0">
		  <tr>
			<td height="20px">&nbsp;&nbsp;当前位置：投诉与建议>>投诉与建议管理>>投诉与建议</td>
			<td width="50" align="right"></td>
		   </tr>
	     </table>
	   </td>
     </tr>
	 <tr>
			<td >
			   
				<div class="tablayer tabBackground" style="height: 20px; width: 100%;">
					<table cellSpacing=0 cellPadding=0 border=0 width="100%">
						<tr>
						   <!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
							<td id="label" nowrap="nowrap">
								<span id="label_1" class="label_down" style="margin-top:2px;">&nbsp;产品投诉&nbsp;</span>
								<span id="label_2" class="label_down" style="margin-top:2px;">&nbsp;服务投诉&nbsp;</span>
								<span id="label_3" class="label_down" style="margin-top:2px;">&nbsp;建议&nbsp;</span>
						        <input type="hidden" id="showLabel" value="label_1"/>
							</td>
							<!--标签页添加完毕-->
						</tr>
					</table>
				</div>
				<div id="bottomdiv" style="height: 90%; width: 100%">
					<!-- 下帧 -->
					<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
				</div>
				<input type="hidden" value="" id="flag" name="flag">
			</td>
		</tr>
	</table>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="actionType" value="list"/>
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
</body>
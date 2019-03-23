<%--
/**
 * @author 朱晓文
 * @function 月结
 * @version 
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>月结信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/drp/finance/account/Act_Frame.js"></script>
</head>
<BODY>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
<table width="100%" height="98%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>                
    <div id="topdiv" style="height:100%;width:100%">
	    <!-- 上帧 -->
	    <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>
	  <div class="tablayer"><table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td class="label_line" width="8px">&nbsp;</td>
					<!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
					<td id="label" nowrap="nowrap">
						<span id="label_1" class="label_down" style="margin-top:2px;">&nbsp;月结明细&nbsp;</span>
						<input type="hidden" id="showLabel" value="label_1"/>
					</td>
				</tr>
			</table>
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
</body>


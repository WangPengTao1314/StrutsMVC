<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<title>报表查询</title>
</head>
<body >
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
     <div id="topdiv" style="height:32%;width:100%">
	    <!-- 上帧 -->
	    <iframe id="topcontent" name="topcontent" src="raq.shtml?action=toConDition&rptConFile=${rptConFile}&reportID=${reportID}&backPath=${backPath}&sjcd=${sjcd}&COST_FLAG=${COST_FLAG}"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>
	  <div class="tablayer tabBackground"><table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td class="label_line" width="8px">&nbsp;</td>
					<!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
					<td id="label" nowrap="nowrap" width="80%">
					</td>
					<!--标签页添加完毕-->
					<td class="label_line" align="right" width="150px" nowrap>
						<input type="button" id="butHidTop" class="button" value="↑" title="Alt+↑">
						<input type="button" id="butHidBottom" class="button" value="↓" title="Alt+↓">
					</td>
				</tr>
			</table>
	  </div>
	  <div id="bottomdiv" style="height:65%;width:100%">
	  <!-- 下帧 -->
	  <iframe id="bottomcontent" name="bottomcontent${rptConFile}"   frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>
    </td>
  </tr>
</table>
<script type="text/javascript">
$(function(){
	//框架页面初始化
	browserinfo();
	setUpDown();
	divShowSwitch(32,60);
	wait();
});


//add 等待窗口 2015-4-1
function wait(){
	$("#butHidTop").click(function(){
//		window.frames[1].parent.showWaitPanel();
	});
}
</script>
</body>


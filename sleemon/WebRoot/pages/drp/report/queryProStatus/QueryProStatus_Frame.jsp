<!-- 
 *@module 销售报表
 *@func 生产情况查询
 *@version 1.1
 *@author ghx
 *  -->

 <%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>生产情况查询</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.jsp"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/report/queryProStatus/QueryProStatus_Frame.js"></script>
</head>
<BODY>
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：报表中心&gt;&gt;销售报表&gt;&gt;交付计划生产状态查询</td>							
	</tr>
</table>
<form id="queryForm" method="post" action="queryProStatus.shtml?action=listFromU9" target="bottomcontent">
	<table cellspacing="0" cellpadding="0" width="99.9%" height="98%">
		<tr>						
			<td >
				<div style="overflow-x: auto; overflow-y: auto; height: 100%;">	
					<div id="divId" style="height: 10%">
						<input id="selectDELIVERParam" name="selectDELIVERParam" type="hidden" value=" DEL_FLAG=0 and state != '未提交' "/>
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="detail3" id="jsontb">					
							<tr>
								<td width="10%" align="right" class="detail_label">
									发运单号：
								</td>
								<td width="15%" class="detail_content">
									<input name="DeliverPlanNo" id="DeliverPlanNo" type="text" style="width: 150px;"  value="${params.DeliverPlanNo}"/>
									<input type="hidden" id="DELIVER_ORDER_ID" name="DELIVER_ORDER_ID"/>
					   				<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
								     		onclick="selCommon('BS_68', false, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[0]','DeliverPlanNo', 'DELIVER_ORDER_NO','selectDELIVERParam');"/>
									&nbsp;&nbsp;						   
								</td>
								<td width="10%" align="right" class="detail_label">
								</td>
								<td width="15%" class="detail_content">
								</td>
								<td width="10%" align="right" class="detail_label">
								</td>
								<td width="15%" class="detail_content">
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle"
									class="detail_btn" colspan="6">
									<input type="button" class="btn" id="btn_query" value="查 询(Q)" title="Alt+O" accesskey="O" onclick="clickBut()"/>&nbsp;&nbsp;
									<input type="button" class="btn" id="btn_reset" value="重置(R)" title="Alt+R" accesskey="R" onclick="reset()"/>&nbsp;&nbsp;
									<input type="button" class="btn" id="up" value="导出(U)" title="Alt+U" accesskey="U" />
								</td>
							</tr>
						</table>
					</div>
					<div class="tablayer tabBackground" style="height: 20px; width: 100%;">
						<table width="100%">
							<tr>
								<td align="right"   >
									<input type="button"  id="butHidTop" class="button" value="↑" title="Alt+↑">
									<input type="button"  id="butHidBottom" class="button" value="↓" title="Alt+↓">
								</td>
								<td width="10%">&nbsp;</td>
							</tr>
						</table>
					</div>
					<div id="bottomdiv" style="height:83%;width:100%">
						<!-- 下帧 -->
						<iframe id="bottomcontent" name="bottomcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO" ></iframe>
					</div>			
				</div>
			</td>
		</tr>
	</table>
</form>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="actionType" value="list"/>
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
<script type="text/javascript">
$(function(){
	//框架页面初始化
	browserinfo();
	setUpDown();
	divShowSwitch(30,65);
});
</script>

</body>
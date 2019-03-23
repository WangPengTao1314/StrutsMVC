<!-- 
 *@module 报表管理
 *@func   月度拜访工作计划达成率
 *@version 1.1
 *@author zcx
 *  -->

 <%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>月度拜访工作计划达成率</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.jsp"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/erp/oamg/monthvisitreport/Monthvisitrep_List.js"></script>
</head>
<BODY>
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：报表中心&gt;&gt;报表管理&gt;&gt;月度拜访工作计划达成率1234</td>							
	</tr>
</table> 
<form id="queryForm" method="post" action="queryReutrnRep.shtml?action=listFromU9" target="bottomcontent">
	<input id="ruturnParams" name="ruturnParams" type="hidden" value=" DEL_FLAG=0 "/>
	<table cellspacing="0" cellpadding="0" width="99.9%" height="98%">
		<tr>						
			<td >
				<div style="overflow-x: auto; overflow-y: auto; height: 100%;">	
						<div id="divId" style="height: 10%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">					
						<tr>
						  <td width="10%" align="right" class="detail_label"> 统计方式： </td>
						  <td width="15%" class="detail_content" colspan="7">
						    <input type="radio" id="WAREA_ID_SHOW" name="WAREA_ID_SHOW" value="WAREA_ID" >战区</input>
						    <input type="radio" id="PROV_SHOW" name="PROV_SHOW" value="PROV" >员工</input>
						  </td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label">
								年份：
							</td>
							<td width="15%" class="detail_content">
								<input id="PLAY_YEAR"  name="PLAY_YEAR"  json="PLAY_YEAR"  type="text"  value="${params.PLAY_YEAR}"/>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">月份：</td>
							<td width="15%" class="detail_content">
			   					<input id="PLAY_MONTH" name="PLAY_MONTH" json="PLAY_MONTH" type="text"  value="${params.PLAY_MONTH}"/>
							</td>
						</tr>
						<tr>
							<td colspan="10" align="center" valign="middle"
								class="detail_btn">
								<input id="qurey" type="button" class="btn" value="查 询(Q)" title="Alt+O" accesskey="O">
								&nbsp;&nbsp;
								<input id="reset" type="reset" class="btn" value="重 置(R)" title="Alt+R" accesskey="R">
							</td>	
							<td style="display:none;">
								<input id="conDition" name="conDition" type="hidden" value="" >
								<input id="rptModel" name="rptModel" type="hidden" value="" >
								<input id="cutSql" name="cutSql" type="hidden" value="" >
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
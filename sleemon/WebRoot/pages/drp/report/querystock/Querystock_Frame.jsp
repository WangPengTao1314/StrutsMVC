<!--
 * @prjName:喜临门营销平台
 * @fileName:Querypaymentrep_Frame
 * @author zhu_changxia
 * @time   2013-05-15 13:18:52 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>报表信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/report/querystock/Querystock_Frame.js"></script>
  </head>
  <body> 
  <table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置： 报表中心>>库存报表>>库存生产状态查询</td>							
	</tr>
</table>
<form id="queryForm" method="post" action="querystock.shtml?action=listFromU9" target="bottomcontent">
	<table cellspacing="0" cellpadding="0" width="99.9%" height="98%">
		<tr>						
			<td >
				<div style="overflow-x: auto; overflow-y: auto; height: 100%;">	
					<div id="divId" style="height: 10%">
						<input id="selectParam" name="selectParam" type="hidden" value="DEL_FLAG='0'" />
						   <input id="selectParamT" name="selectParamT" type="hidden" value=" DEL_FLAG='0' and STATE in ('启用','停用') and FINAL_NODE_FLAG=1 and COMM_FLAG=1 "/>
						   <input id="selectParamZT" name="selectParamZT" type="hidden" />
						   <input type="hidden" name="selectParams" value=" STATE in( '启用','停用') and DEL_FLAG='0' ">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="detail3" id="jsontb">					
							<tr>
								<td width="4%" nowrap align="right" class="detail_label">客户编号:</td>
								<td width="8%" class="detail_content">
								    <input  id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"   value="${params.CHANN_ID}"/>
									<input type="text" json="CHANN_NO" id="CHANN_NO"   name="CHANN_NO" value="${CustNo}"/>
								    <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO', 'CHANN_ID,CHANN_NO', 'selectParam')">
								</td>
			                    <td width="4%" nowrap align="right" class="detail_label">商品编号:</td>
								<td width="8%" class="detail_content">
								    <input type="hidden" id="PRD_ID" name="PRD_ID" json="PRD_ID" value="${params.PRD_ID}"/>
									<input type="text" id="PRD_NO"  name="PRD_NO" json="PRD_NO" value="${PrdNo}"/>
									<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_ID,PRD_NO', 'PRD_ID,PRD_NO', 'selectParamT')">
								</td>
								 <td width="8%" nowrap align="right" class="detail_label">组织编码:</td>
								<td width="15%" class="detail_content">
								 <input id="SHIP_POINT_NO" autocheck="true" json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="text" label="生产基地编号"  >
								 <img align="absmiddle" name="selZONE" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif" 
									onClick="selCommon('BS_22', false, 'SHIP_POINT_NO','SHIP_POINT_NO', 'forms[0]','SHIP_POINT_NO', 'SHIP_POINT_NO', 'selectParams')">
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle"
									class="detail_btn" colspan="6">
									<input type="button" class="btn" id="query" value="查 询(Q)" title="Alt+O" accesskey="O" />&nbsp;&nbsp;
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
<input type="hidden" id="CMPL_ADVS_TYPE" value=""/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="actionType" value="list"/>
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
<input type="hidden" id="CMPL_ADVS_TYPE1" value="${CMPL_ADVS_TYPE}"/>
<script type="text/javascript">
$(function(){
	//框架页面初始化
	browserinfo();
	setUpDown();
	divShowSwitch(30,65);
});
</script>
</body>
  
  

<!--  
/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript
		src="${ctx}/pages/drp/distributer/distributersalerpt/DistributerSalerpt_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>分销商购货月报</title>
	 
</head>
<body class="bodycss1">

	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">					
					<input id="back" type="button" class="btn" style="margin-left: 20px;" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<form method="POST" action="#" id="mainForm">
		<input type="hidden" name="selectDistributer" value=" STATE in( '启用') and DEL_FLAG='0' ">
		<input type="hidden" name="selectProduct" value=" STATE = '启用' and DEL_FLAG = '0' and IS_DISTRIBUT_FLAG =1 " />
		<table width="100%" border="0" cellpadding="4" cellspacing="1" >
			<tr>
				<td valign="top" height="95%">
					<div id="topdiv" style="height: 40px; width: 100%;">
					<table id="jsontbMain" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
						<tr>
							<input type="hidden" id="DISTRIBUTOR_SALE_RPT_ID" json="DISTRIBUTOR_SALE_RPT_ID" name="DISTRIBUTOR_SALE_RPT_ID" value="${rst.DISTRIBUTOR_SALE_RPT_ID}">
							<td width="12%" align="right" class="detail_label">
								提报人渠道名称：
							</td>
							<td width="24%" class="detail_content">
								<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}">
								<input type="hidden" id="CHANN_NO" json="CHANN_NO" name="CHANN_ID" value="${rst.CHANN_NO}">
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text"  label="提报人渠道名称"
									value="${rst.CHANN_NAME}" readonly/>
							</td>
							<td width="8%" align="right" class="detail_label">
								提报时间：
							</td>
							<td width="24%" class="detail_content">
							      <input json="CRE_TIME" name="CRE_TIME" id="CRE_TIME" type="text" label="提报时间" 									 
									value="${rst.CRE_TIME}" readonly/>							  
							</td>
							<td width="8%" align="right" class="detail_label">
								月报时间：
							</td>
							<td width="24%" class="detail_content">
								&nbsp;<select json="YEAR" id="YEAR" name="YEAR" style="width: 80px" ></select>年
				   				&nbsp;<select json="MONTH" id="MONTH" name="MONTH" style="width: 80px" ></select>
							</td>
						</tr>						
					</table>
					</div>
					<div class="tablayer tabBackground" style="height: 20px; width: 100%;">
						<table cellSpacing=0 cellPadding=0 border=0 width="100%">
							<tr>							
								<!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
								<td id="label" nowrap="nowrap">
									<span id="label_1" class="label_down" style="margin-top: 2px;">&nbsp;明细信息&nbsp;</span>								
									
								</td>
								<td class="label_line" align="right" width="80px">
									<input id="add" type="button" class="btn" value="添加明细(I)" title="Alt+I" accesskey="I" >
								</td>
							</tr>
						</table>
					</div>
					<div id="bottomdiv" style="height: 650px; width: 100%; overflow-y:auto;">
						<table id="jsontbChild" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow">
				              <th nowrap align="center" >分销商编号</th>
				              <th nowrap align="center">分销商名称</th>
				              <th nowrap align="center">分销类型</th>
				              <th nowrap align="center">货品编号</th>
				              <th nowrap align="center">货品名称</th>
				              <th nowrap align="center">规格型号</th>
				              <th nowrap align="center">提货数量</th>
				              <th nowrap align="center">提货金额</th>
				              <th nowrap align="center"></th>
				            </tr>			            
							<c:if test="${empty rst.childList}">							
								<tr id="empty">
									<td height="25" colspan="15" align="center" class="lst_empty">
						                &nbsp;无相关记录&nbsp;
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</td>
			</tr>			
		</table>
	</form>   
</body>
<script type="text/javascript">
	SelDictShow("YEAR","89","${rst.YEAR}","");
    SelDictShow("MONTH","168","${rst.MONTH}","");
    <c:forEach items="${rst.childList}" var="rstChild" varStatus="row">
	var arrs = [				
            	'${rstChild.DISTRIBUTOR_SALE_RPT_DTL_ID}',
				'${rstChild.DISTRIBUTOR_ID}',
				'${rstChild.DISTRIBUTOR_NO}',
				'${rstChild.DISTRIBUTOR_NAME}',
				'${rstChild.DISTRIBUTOR_TYPE}',
				'${rstChild.PRD_ID}',
				'${rstChild.PRD_NO}',
				'${rstChild.PRD_NAME}',
				'${rstChild.PRD_SPEC}',
				'${rstChild.STOREOUT_NUM}',
				'${rstChild.STOREOUT_AMOUNT}'
           		];
    addRow(arrs);
	</c:forEach>
</script> 



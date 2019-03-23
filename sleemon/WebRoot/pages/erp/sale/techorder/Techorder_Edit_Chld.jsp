<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Techorder_Edit_Chld
 * @author lyg
 * @time   2013-10-12 17:37:51 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale//techorder/Techorder_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>明细信息编辑</title>
</head>
<body >
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
	<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
     <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
	      <tr>
			<td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;工艺单管理&gt;&gt;工艺单审核</td>
					<td width="50" align="right"></td>
				</tr>
			  </table>
			</td>
		</tr>

		<tr>
		   <td nowrap>
		   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
<!--			   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">-->
			 <input id="audit" type="button" class="btn" value="审核完成(A)" title="Alt+A" accesskey="A">
			 <input id="cancel" type="button" class="btn" value="撤销(C)" title="Alt+A" accesskey="A">
		   	 <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
		 </td>
		</tr>
	</table>
	</div>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area" style="80%">
	    <form>
		<input type="hidden" id="TECH_ORDER_ID" name="TECH_ORDER_ID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" name=selectParam value=" STATE='启用' and DEL_FLAG='0' and FINAL_NODE_FLAG='1' and COMM_FLAG=1 ">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">工艺单编号</th>
              <th nowrap align="center">销售订单编号</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">是否可生产</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">新货品编号</th>
              <th nowrap align="center">新货品名称</th>
              <th nowrap align="center">新规格型号</th>
              <th nowrap align="center">渠道名称</th>
              <th nowrap align="center">订单审核人</th>
              <th nowrap align="center">备注</th>
              <th nowrap align="center" >附件</th>
              <th nowrap align="center" >状态</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
			<tr>
				<td>
					<form id="pageForm" action="#" method="post" name="listForm">
					<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
						<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
						<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
						<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
						<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
						<span id="hidinpDiv" name="hidinpDiv"></span>
						${paramCover.unCoveredForbidInputs }
					</form>
				</td>
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用')  and CHANN_TYPE !='总部' and DEL_FLAG=0 "/>
			<input type="hidden" id="selTech" name="selTech" value=" DEL_FLAG=0 "/>
               <tr>
                  <%--
                    <td width="8%" nowrap align="right" class="detail_label">工艺单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TECH_ORDER_NO" name="TECH_ORDER_NO"  style="width:155" value="${params.TECH_ORDER_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_60', false, 'TECH_ORDER_NO', 'TECH_ORDER_NO', 'forms[1]','TECH_ORDER_NO','TECH_ORDER_NO','selTech')">
					</td>					
                    --%>
                    <td width="8%" nowrap align="right" class="detail_label">工艺单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TECH_ORDER_NO" name="TECH_ORDER_NO"  style="width:155" value="${params.TECH_ORDER_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">销售订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
	   					<img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_NO', 'CHANN_NO', 'forms[1]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">货品编号</td>
					<td width="15%" class="detail_content">
						<input id="PRD_NO" json="PRD_NO"  name="PRD_NO" type="text" inputtype="string"   value="${params.PRD_NO}" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称</td>
					<td width="15%" class="detail_content">
						<input name="PRD_NAME" type="text" value="${params.PRD_NAME}" id="PRD_NAME" json="PRD_NAME">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">规格型号：</td>
					<td width="15%" class="detail_content">
						<input type="text" value="${params.PRD_SPEC}" id="PRD_SPEC" name="PRD_SPEC"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">订单审核人:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_NAME" name="CRE_NAME"   value="${params.CRE_NAME}"/>
					</td>
               </tr><%--
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">订货日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_DATE_BEG" name="ORDER_DATE_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.ORDER_DATE_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_BEG);"  >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">订货日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_DATE_END" name="ORDER_DATE_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.ORDER_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_END);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               --%>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width: 155"></select>
					</td>	
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>	
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>	
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>	
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>

</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	SelDictShow("STATE","BS_43","${params.STATE}","");   
	<c:forEach items="${page.result}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.TECH_ORDER_DTL_ID}',//0
              '${rst.PRD_NO}',//1
              '${rst.PRD_NAME}',//2
              '${rst.PRD_SPEC}',//3
              '${rst.PRD_COLOR}',//4
              '${rst.BRAND}',//5
              '${rst.STD_UNIT}',//6
              '${rst.SPCL_TECH_FLAG}',//7
              '${rst.IS_CAN_PRD_FLAG}',//8
              '${rst.PRICE}',//9
              '${rst.NEW_PRD_ID}',//10
              '${rst.NEW_PRD_NO}',//11
              '${rst.NEW_PRD_NAME}',//12
              '${rst.NEW_PRD_SPEC}',//13
              '${rst.REMARK}',//14
              '${rst.SPCL_TECH_ID}',//15
              '${rst.ATT_PATH}',//16
              '${rst.STATE}',//17
              '${rst.CRE_NAME}',//18
              '${rst.ORDER_CHANN_NAME}',//19
              '${rst.FROM_BILL_NO}',//20
              '${rst.TECH_ORDER_NO}'
            ];
	addRow(arrs);
	</c:forEach>
</script>

</html>
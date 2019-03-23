<!-- 
 *@module渠道管理-拜访管理
 *@func  月度拜访计划维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript    src="${ctx}/pages/erp/oamg/monthVisit/Monthvisit_Edit.js"></script>
	<script type=text/javascript    src="${ctx}/pages/erp/oamg/monthVisit/Monthvisit_EditT.js"></script>
	<script type=text/javascript    src="${ctx}/pages/erp/oamg/monthVisit/Monthvisit_Mx_Edit.js"></script>
    <script type="text/javascript"	src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
    
	<title>工作计划维护</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>
		当前位置：渠道管理&gt;&gt;拜访管理&gt;&gt;新增月度拜访计划
		</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" json="RNVTN_SUBST_STD_ID" id="RNVTN_SUBST_STD_ID" name="RNVTN_SUBST_STD_ID" value="${rst.RNVTN_SUBST_STD_ID}" />
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right" class="detail_label">月度拜访计划编号：</td>
					<td width="35%" class="detail_content">
						    <input json="MONTH_VISIT_PLAN_NO" name="MONTH_VISIT_PLAN_NO" size="40"  id="MONTH_VISIT_PLAN_NO" type="text" label="月度拜访计划编号" autocheck="true"  inputtype="string" mustinput="true"
						       readonly <c:if test="${not empty rst.MONTH_VISIT_PLAN_NO}">value="${rst.MONTH_VISIT_PLAN_NO}"</c:if>
									<c:if test="${empty rst.MONTH_VISIT_PLAN_NO}">value="自动生成"</c:if>>
					</td>
					<td width="15%" align="right" class="detail_label">人员编号：</td>
					<td width="35%" class="detail_content">
					        <input type="hidden" name="selectParams"     id="selectParams" />
					        <input id="RYXXID"     name="RYXXID"     json="RYXXID"   value="${rst.RYXXID}"   type="hidden"/>
                            <input id="RYBH"       name="RYBH"       json="RYBH"   value="${rst.RYBH}" size="40" label="人员编号"  autocheck="true" inputtype="string"  mustinput="true" readonly/> 
                            <img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_0', false, 'RYBH', 'RYBH', 'forms[0]','RYXXID,RYBH,RYMC', 'RYXXID,RYBH,XM', 'selectParams');">				
                  	</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">姓名：</td>
					<td width="35%" class="detail_content">
					   <input json="RYMC" name="RYMC" id="RYMC" type="text"
									label="姓名" value="${rst.RYMC}"
									size="40" readonly/> 
 					</td>
					<td width="15%" align="right" class="detail_label">计划年份：</td>
					<td width="35%" class="detail_content">
					   <select name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" value="${rst.PLAN_YEAR}" style="width:275px;" autocheck="true" inputtype="string"  mustinput="true">
				       </select>
 					</td>
				</tr>
				<tr>
				<td width="15%" align="right" class="detail_label">
					计划月份：
				</td>
				<td width="35%" class="detail_content">
				    <select name="PLAN_MONTH" id="PLAN_MONTH" json="PLAN_MONTH" value="${rst.PLAN_MONTH}" style="width:275px;" autocheck="true" inputtype="string"  mustinput="true">
				    </select>
				</td>
				<td width="15%" align="right" class="detail_label">
					计划拜访次数：
				</td>
				<td width="35%" class="detail_content">
					<input id="PLAN_VISIT_NUM_TOTAL" name="PLAN_VISIT_NUM_TOTAL" json="PLAN_VISIT_NUM_TOTAL" value="${rst.PLAN_VISIT_NUM_TOTAL}"
						type="text" size="40"  label = "计划拜访次数" readonly/>
				</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">
						备注：
					</td>
					<td width="35%" class="detail_content" colspan="3">
					    <textarea rows="5" cols="32%" id="REMARK" name="REMARK" json="REMARK" autocheck="true" inputtype="string"   maxlength="250">${rst.REMARK}</textarea>
				    </td>
		         </tr>		
			  </table>
			</td>
	     </tr>
</table>
</form>
</body>
<script type="text/javascript">
	SelDictShow("PLAN_YEAR","89","${rst.PLAN_YEAR}","");
	SelDictShow("PLAN_MONTH","168","${rst.PLAN_MONTH}","");
</script>
<!--  
/**
 *@module 渠到管理-拜访管理
 *@func   加盟商拜访表维护
 *@version 1.1
 *@author zcx
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
    <script type=text/javascript  src="${ctx}/pages/drp/visit/channvisit/Channvisit_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>加盟商拜访表维护</title>
</head>
<body class="bodycss1">

<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
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
		<input type="hidden" id="selectParams" name="selectParams"
			value="CHANN_ID in ${CHANN_ID}">
		<input type="hidden" id="selectParamsT" name="selectParamsT" />
	    <input type="hidden" id="selectParamsTt" name="selectParamsTt" />
	    <input type="hidden" name="selectParamsChann" value="STATE in( '启用') and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}') " />
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
						    <!-- 
						    <td width="15%" align="right" class="detail_label">
								标题：
							</td>
							<td width="20%" class="detail_content">
								<input json="TITLE" name="TITLE" id="TITLE" type="text" label="标题" value="${rst.TITLE}" autocheck="true" size="30"  inputtype="string" />
							</td>
							 -->
							<td width="15%" align="right" class="detail_label">
								流程编号：
							</td>
							<td width="20%" class="detail_content">
							     <input json="CHANN_VISIT_ID" name="CHANN_VISIT_ID"
									id="CHANN_VISIT_ID" type="hidden" seltarget="selLL" size="30" 
									label="流程ID"  value="${rst.CHANN_VISIT_ID}" />
								<input json="CHANN_VISIT_NO" name="CHANN_VISIT_NO" type="text" size="30" 
									id="CHANN_VISIT_NO" autocheck="true" label="流程编号" inputtype="string"
									mustinput="true" maxlength="32"  
									<c:if test="${not empty rst.CHANN_VISIT_NO}">value="${rst.CHANN_VISIT_NO}"</c:if>
									<c:if test="${empty rst.CHANN_VISIT_NO}">value="自动生成"</c:if>
									READONLY>
							</td>
							<td width="15%" align="right" class="detail_label">
								紧急程度：
							</td>
							<td width="20%" class="detail_content">
							     <input type="radio" name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" checked="true" value="正常"/>正常 
							     <input type="radio" name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" value="重要"/>重要 
							     <input type="radio" name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" value="紧急"/>紧急 
							</td>
							<td width="15%"  class="detail_label">
								拜访人：
							</td>
							<td width="20%" class="detail_content">
								<input json="VISIT_PEOPLE" name="XM" 
									id="XM" type="text" label="拜访人" size="30" 
									value="${LOGIN_NAME}" autocheck="true"  inputtype="string" readonly/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DEP" name="APPLY_DEP" id="APPLY_DEP" type="text" size="30" 
									label="申请部门" value="${BM_NAME}"  autocheck="true"  inputtype="string" READONLY  />
							</td>
							<td width="15%"   class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DATE" name="APPLY_DATE" id="APPLY_DATE" type="text" size="30" 
									label="申请日期" value="${APPLY_DATE}"   autocheck="true"  inputtype="string" READONLY />
							</td>
							<td width="15%"   class="detail_label">
								拜访时间：
							</td>
							<td width="20%" class="detail_content">
							    <input json="VISIT_DATE" name="VISIT_DATE" id="VISIT_DATE" type="text" size="30" 
									label="申请时间" value="${APPLY_DATE}"  autocheck="true"  inputtype="string" mustinput="true" 
									onclick="SelectDate()" READONLY/>
								<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(VISIT_DATE);">
							</td>
						</tr> 
						<tr>
							
							<td width="15%"   class="detail_label">
								拜访方式：
							</td>
							<td width="20%" class="detail_content">
							    <select id="VISIT_TYPE" name="VISIT_TYPE"  json="VISIT_TYPE" style="width: 215px;" autocheck="true" inputtype="string" mustinput="true" autocheck="true" label="拜访方式"  value="${rst.VISIT_TYPE}">
								</select>
							</td>
							<td width="15%"   class="detail_label">
								客户名称：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input json="CHANN_ID"  name="CHANN_ID" id="CHANN_ID" type="hidden" value="${rst.CHANN_ID}" />
							    <input json="CHANN_NO"  name="CHANN_NO" id="CHANN_NO" type="hidden" value="${rst.CHANN_NO}"/>
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" size="30" 
									label="客户名称" value="${rst.CHANN_NAME}"  autocheck="true"  inputtype="string" mustinput="true" readonly />
								<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
						                onclick="selCommon('BS_107', false, 'CHANN_NAME', 'CHANN_NAME', 'forms[0]',
										'CHANN_ID,CHANN_NO,CHANN_NAME', 
										'CHANN_ID,CHANN_NO,CHANN_NAME', 
										'selectParamsChann');"/>&nbsp;
							</td>
						</tr> 
						<tr style="padding-left:50px;">
						    <th align="center">
						       库存信息  
						    </th>
						</tr>
						<tr>
							<td width="15%"  class="detail_label">
								床垫：
							</td>
							<td width="20%" class="detail_content">
								<input json="MATTRESS_STOCK" name="MATTRESS_STOCK" id="MATTRESS_STOCK" type="text" size="30" maxlength="20" 
									label="床垫" value="${rst.MATTRESS_STOCK}"  autocheck="true"  inputtype="string" onblur="getTotal()"/>
							</td>
							<td width="15%"   class="detail_label">
								软床：
							</td>
							<td width="20%" class="detail_content">
							    <input json="BED_STOCK" name="BED_STOCK" id="BED_STOCK" type="text" size="30" maxlength="20" 
									label="软床" value="${rst.BED_STOCK}"  autocheck="true"  inputtype="string" onblur="getTotal()"/>
							</td>
							<td width="15%"   class="detail_label">
								床头柜：
							</td>
							<td width="20%" class="detail_content">
							    <input json="BEDSIDE_STOCK" name="BEDSIDE_STOCK" id="BEDSIDE_STOCK" type="text" size="30" maxlength="20" 
									label="床头柜" value="${rst.BEDSIDE_STOCK}"   autocheck="true"  inputtype="string" onblur="getTotal()"/>
							</td>
						</tr> 
						<tr>
							<td width="15%"   class="detail_label">
								床品：
							</td>
							<td width="20%" class="detail_content">
								<input json="BEDDING_STOCK" name="BEDDING_STOCK" id="BEDDING_STOCK" type="text" size="30" maxlength="20" 
									label="床品" value="${rst.BEDDING_STOCK}"  autocheck="true"  inputtype="string" onblur="getTotal()"/>
							</td>
							<td width="15%"  class="detail_label">
								其他：
							</td>
							<td width="20%" class="detail_content">
							    <input json="OTHER_STOCK" name="OTHER_STOCK" id="OTHER_STOCK" type="text" size="30" maxlength="20" 
									label="其他" value="${rst.OTHER_STOCK}"  autocheck="true"  inputtype="string" onblur="getTotal()"/>
							</td>
							<td width="15%"   class="detail_label">
								合计：
							</td>
							<td width="20%" class="detail_content">
							    <input json="TOTAL_STOCK" name="TOTAL_STOCK" id="TOTAL_STOCK" type="text" size="30" maxlength="30" 
									label="合计" value="${rst.TOTAL_STOCK}"   autocheck="true"  inputtype="string" readonly/>
							</td>
						</tr>
						<tr style="padding-left:50px;">
						    <th align="center">
						       销售达成  
						    </th>
						</tr>
					    <tr>
							<td width="15%"  class="detail_label">
								本月销售目标：
							</td>
							<td width="20%" class="detail_content">
							    <input json="MONTH_ORDER_NUM" name="MONTH_ORDER_NUM" id="MONTH_ORDER_NUM" label="销售本月目标" maxlength="20" 
									size="30" autocheck="true" inputtype="string"   maxlength="250">${rst.MONTH_ORDER_NUM}</input>
							</td>
							<td width="15%"  class="detail_label">
								本季销售目标：
							</td>
							<td width="20%" class="detail_content">
							    <input json="SEASON_ORDER_NUM" name="SEASON_ORDER_NUM" id="SEASON_ORDER_NUM" label="销售本季目标" maxlength="20" 
									size="30" autocheck="true" inputtype="string"   maxlength="250"  value="${rst.SEASON_ORDER_NUM}"/>
							</td>
							<td width="15%"  class="detail_label" rowspan="3">
								销售改善计划：
							</td>
							<td width="20%" class="detail_content" rowspan="3">
							     <textarea json="SALES_IMP_PLAN" name="SALES_IMP_PLAN" id="SALES_IMP_PLAN" label="销售改善计划" 
									                  rows="4" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.SALES_IMP_PLAN}</textarea>
							</td>
				         </tr>
				         <tr>
							<td width="15%" class="detail_label">
								本月实际销售：
							</td>
							<td width="20%" class="detail_content">
								<input json="MONTH_ORDER_REALITY_RATE" name="MONTH_ORDER_REALITY_RATE" id="MONTH_ORDER_REALITY_RATE" type="text" size="30" 
									label="销售本月实际达成" value="${rst.MONTH_ORDER_REALITY_RATE}"  autocheck="true"  inputtype="string" maxlength="20" />
							</td>
							<td width="15%" class="detail_label">
								 本季实际销售：
							</td>
							<td width="20%" class="detail_content">
								<input json="SEASON_ORDER_REALITY_RATE" name="SEASON_ORDER_REALITY_RATE" id="SEASON_ORDER_REALITY_RATE" type="text" size="30" 
									label="销售本季实际达成" value="${rst.SEASON_ORDER_REALITY_RATE}"  autocheck="true"  inputtype="string" maxlength="20" />
							</td>
						   </tr>
						   <tr>
							<td width="15%"  class="detail_label">
								 月销售达成率：
							</td>
							<td width="20%" class="detail_content">
							    <input json="MONTH_ORDER_RATE" name="MONTH_ORDER_RATE" id="MONTH_ORDER_RATE" type="text" size="30" 
									label="销售本月达成率" value="${rst.MONTH_ORDER_RATE}"  autocheck="true"  inputtype="string" maxlength="20" />
							</td>
						    <td width="15%" class="detail_label">
								 季销售达成率：
							</td>
							<td width="20%" class="detail_content">
							    <input json="SEASON_ORDER_RATE" name="SEASON_ORDER_RATE" id="SEASON_ORDER_RATE" type="text" size="30" 
									label="销售本季达成率" value="${rst.SEASON_ORDER_RATE}"  autocheck="true"  inputtype="string" maxlength="20" />
							</td>
						   </tr>
						<tr style="padding-left:50px;">
						    <th  align="center">
						         季度工作重点
						    </th>
						</tr>
						<tr>
							<td width="15%"  class="detail_label">
								季度目标：
							</td>
							<td width="20%" class="detail_content">
								<input json="SEASON_GOALS" name="SEASON_GOALS" id="SEASON_GOALS" type="text"
									label="季度目标" value="${rst.SEASON_GOALS}"  autocheck="true"  inputtype="string"  size="30"  maxlength="200" />
							</td>
							<td width="15%"   class="detail_label">
								目前达成：
							</td>
							<td width="20%" class="detail_content">
							    <input json="CURRENT_REALITY_RATE" name="CURRENT_REALITY_RATE" id="CURRENT_REALITY_RATE" type="text" size="30" 
									label="目前达成" value="${rst.CURRENT_REALITY_RATE}"  autocheck="true"  inputtype="string" maxlength="20" />
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    <input json="CURRENT_RATE" name="CURRENT_RATE" id="CURRENT_RATE" type="text" size="30" 
									label="达成率" value="${rst.CURRENT_RATE}"   autocheck="true"  inputtype="string" maxlength="20" />
							</td>
						</tr>
						<tr>
						   <td width="15%"  class="detail_label">
								季度改善计划：
							</td>
						   <td width="20%"  class="detail_content">
							    <textarea json="SEASON_IMPROVE_PLAN" name="SEASON_IMPROVE_PLAN" id="SEASON_IMPROVE_PLAN" type="text" maxlength="200" 
									 rows="3" cols="24%" label="季度改善计划" value="${rst.SEASON_IMPROVE_PLAN}"  autocheck="true"  inputtype="string">${rst.SEASON_IMPROVE_PLAN}</textarea>
							</td>
							<td width="15%" class="detail_label">
								加盟商问题：
							</td>
							<td width="20%" class="detail_content" >
								 <textarea json="CHANN_QUESTION" name="CHANN_QUESTION" id="CHANN_QUESTION" label="加盟商问题" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.CHANN_QUESTION}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								主要行动：
							</td>
							<td width="20%" class="detail_content" >
								 <textarea json="MAIN_ACTION" name="MAIN_ACTION" id="MAIN_ACTION" label="主要行动" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.MAIN_ACTION}</textarea>
							</td>
						</tr>
						<tr>
							<td width="15%"   class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="COMPETITION_INFO" name="COMPETITION_INFO" id="COMPETITION_INFO" label="竞品信息" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.COMPETITION_INFO}</textarea>
							</td>
							<td width="15%"   class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="SUPPORT_DEMAND" name="SUPPORT_DEMAND" id="SUPPORT_DEMAND" label="支持需求" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.SUPPORT_DEMAND}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">相关附件：</td>
							<td width="20%" class="detail_content" colspan="5"><input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"  type="hidden"   value="${rst.ATT_PATH}"/> 
							</td> 
						</tr> 
					</table>
				</td>
			</tr>
		</table> 
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	   SelDictShow("VISIT_TYPE","BS_156","${rst.VISIT_TYPE}","");
	   uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
	</script>
   </div>
</body>



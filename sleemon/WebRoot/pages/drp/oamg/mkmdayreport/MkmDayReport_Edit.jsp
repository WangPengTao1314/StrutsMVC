<!--  
/**
 *@module 渠到管理-上报管理
 *@func   营销经理上报维护
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
	<script language="JavaScript"
		src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript
		src="${ctx}/pages/drp/oamg/mkmdayreport/MkmDayReport_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>营销经理日报维护</title>
</head>

<body class="bodycss1" onload="load()">
	<div style="height: 100">
		<div class="buttonlayer" id="floatDiv">
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td align="left" nowrap>
						<input id="save" type="button" class="btn" value="保存(S)"
							title="Alt+S" accesskey="S">
						<input type="button" class="btn" value="返回(B)" title="Alt+B"
							accesskey="B" onclick="history.back();">
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
			<input type="hidden" id="selChannParam" name="selChannParam" />
			<input type="hidden" id="selectParamsT" name="selectParamsT" />
			<input type="hidden" id="selectParamsTt" name="selectParamsTt" />
		    <input type="hidden" id="selectParamsByTerm" name="selectParamsByTerm" value="CHANN_ID in ${CHANN_ID}"/>
			<input type="hidden" name="selectParamsChann"
				value="STATE in( '启用') and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}') " />
			<table width="100%" border="0" cellpadding="4" cellspacing="4"
				class="detail">
				<tr>
					<td class="detail2">
						<table id="jsontb" width="100%" border="0" cellpadding="1"
							cellspacing="1" class="detail3">
							<tr>
								<td border="blod;">
									基础信息
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label">
									流程编号：
								</td>
								<td width="20%" class="detail_content">
									<input json="MKM_DAY_RPT_ID" name="MKM_DAY_RPT_ID"
										id="MKM_DAY_RPT_ID" type="hidden" seltarget="selLL" size="30"
										label="流程ID" value="${rst.MKM_DAY_RPT_ID}" />
									<input json="MKM_DAY_RPT_NO" name="MKM_DAY_RPT_NO" type="text"
										size="30" id="MKM_DAY_RPT_NO" autocheck="true" label="流程编号"
										inputtype="string" mustinput="true" maxlength="32"
										<c:if test="${not empty rst.MKM_DAY_RPT_NO}">value="${rst.MKM_DAY_RPT_NO}"</c:if>
										<c:if test="${empty rst.MKM_DAY_RPT_NO}">value="自动生成"</c:if>
										READONLY>
								</td>
								<td width="15%" align="right" class="detail_label">
								</td>
								<td width="20%" class="detail_content">
								</td>
								<td width="15%" class="detail_label">
									拜访人：
								</td>
								<td width="20%" class="detail_content">
									<input json="MKM_ID" name="MKM_ID" id="MKM_ID" type="hidden"
										label="拜访人ID" size="30" value="${rst.MKM_ID}" autocheck="true"
										inputtype="string" />
									<input json="MKM_NAME" name="MKM_NAME" id="MKM_NAME"
										type="text" label="拜访人" size="30" value="${rst.MKM_NAME}"
										autocheck="true" inputtype="string" readonly />
								</td>
							</tr>
							<tr>
								<td width="15%" class="detail_label">
									部门名称：
								</td>
								<td width="20%" class="detail_content">
									<input json="WAREA_ID" name="WAREA_ID" id="WAREA_ID"
										type="hidden" value="${rst.WAREA_ID}" />
									<input json="WAREA_NO" name="WAREA_NO" id="WAREA_NO"
										type="hidden" value="${rst.WAREA_NO}" />
									<input json="WAREA_NAME" name="WAREA_NAME" id="WAREA_NAME"
										type="text" size="30" label="战区" value="${rst.WAREA_NAME}"
										autocheck="true" inputtype="string" READONLY />
								</td>
								<td width="15%" class="detail_label">
									上报日期：
								</td>
								<td width="20%" class="detail_content">
									<input json="REPORT_DATE" name="REPORT_DATE" id="REPORT_DATE"
										type="text" size="30" label="上报日期" value="${rst.REPORT_DATE}"
										autocheck="true" inputtype="string" READONLY />
								</td>
								<td width="15%" class="detail_label">
									拜访日期：
								</td>
								<td width="20%" class="detail_content">
									<input json="VST_DATE" name="VST_DATE" id="VST_DATE"
										type="text" size="30" label="拜访日期" value="${rst.VST_DATE}"
										autocheck="true" inputtype="string" mustinput="true"
										onclick="SelectDate()" READONLY onblur="checkVstDate(this)"/>
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(VST_DATE);">
								</td>
							</tr>
							<tr>
								<td width="15%" class="detail_label">
									出差途中：
								</td>
								<td width="20%" class="detail_content" colspan="5">
									<textarea json="ON_TRIP" name="ON_TRIP" id="ON_TRIP"
										label="出差途中" style="width: 100%" value="${rst.ON_TRIP}"
										rows="1" cols="100%" maxLength="2" maxLength="250">${rst.ON_TRIP}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" class="detail_label">
									公司总部公务处理：
								</td>
								<td width="20%" class="detail_content" colspan="5">
									<textarea json="CORP_BUSS_DEAL" name="CORP_BUSS_DEAL"
										id="CORP_BUSS_DEAL" label="公司总部公务处理" style="width: 100%"
										rows="1" cols="100%" maxLength="250">${rst.CORP_BUSS_DEAL}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" class="detail_label">
									调休：
								</td>
								<td width="20%" class="detail_content" colspan="5">
									<textarea json="WAKE_OFF" name="WAKE_OFF" id="WAKE_OFF"
										label="调休" style="width: 100%" rows="1" cols="100%"
										maxLength="250">${rst.WAKE_OFF}</textarea>
								</td>
							</tr>
								<tr>
									<td border="blod;">
										加盟商拜访
									</td>
								</tr>
								<tr>
									<td width="15%" class="detail_label">
										加盟商编号:
									</td>
									<td width="20%" class="detail_content" colspan="2">
										<input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID"
											type="hidden" value="${rstcv.CHANN_ID}" />
										<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO"
											type="text" size="30" label="加盟商编号" value="${rstcv.CHANN_NO}"
										    autocheck="true" inputtype="string" READONLY />
										<img align="absmiddle" name="selJGXX" style="cursor: hand"
											src="${ctx}/styles/${theme}/images/plus/select.gif"
											onClick="selCommon('BS_136', false, 'CHANN_NO', 'CHANN_NO', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selChannParam');">
									</td>
									<td width="15%" class="detail_label">
										加盟商名称:
									</td>
									<td width="20%" class="detail_content" colspan="2">
										<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME"
											type="text" size="30" label="加盟商名称"
											value="${rstcv.CHANN_NAME}" autocheck="true"
											inputtype="string" READONLY />
									</td>
								</tr>
								<tr>
									<td width="15%" align="right" class="detail_label" rowspan="2">
										库存信息(单位:万元)
									</td>
									<td width="15%" align="left" class="detail_content">
										&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										床垫: &nbsp;&nbsp;&nbsp;
										<input json="MATT_AMOUNT" name="MATT_AMOUNT" id="MATT_AMOUNT"
											type="text" size="13" label="床垫" value="${rstcv.MATT_AMOUNT}"
											autocheck="true" inputtype="int" onblur="getTotal()" />(万元)
									</td>
									<td width="15%" align="right" class="detail_content">
										软床:
									</td>
									<td width="15%" align="left" class="detail_content">
										<input type="text" name="SOFT_BED_AMOUNT" id="SOFT_BED_AMOUNT"
											json="SOFT_BED_AMOUNT" size="13" autocheck="true"  inputtype="int"
											value="${rstcv.SOFT_BED_AMOUNT}" onblur="getTotal()" label="软床"/>(万元)
										&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
									</td>
									<td width="28%" align="left" class="detail_content">
										其他:<input type="text" name="OTHER_AMOUNT" id="OTHER_AMOUNT" label="其他"
											json="OTHER_AMOUNT" size="11" value="${rstcv.OTHER_AMOUNT}"
											onblur="getTotal()" autocheck="true"  inputtype="int"/>(万元)
										&nbsp;&nbsp;
									</td>
									<td width="20%" class="detail_content" align="center">
										合计
										<input json="TOTAL_AMOUNT" name="TOTAL_AMOUNT"
											id="TOTAL_AMOUNT" type="text" size="15" label="合计"
											value="${rstcv.TOTAL_AMOUNT}" autocheck="true"
											inputtype="string" READONLY />(万元)
									</td>
								</tr>
								<tr>
									<td width="15%" align="left" class="detail_content" colspan="8">
										&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										附件: &nbsp;&nbsp;&nbsp;
										<input type="hidden" name="STORE_ATT" id="STORE_ATT"
											json="STORE_ATT" value="${rstcv.STORE_ATT}" />
									</td>
								</tr>
								<tr>
									<td width="15%" align="right" class="detail_label">
										加盟商问题
									</td>
									<td width="20%" class="detail_content" colspan="8">
										<textarea json="CHANN_PROBLEM" name="CHANN_PROBLEM"
											id="CHANN_PROBLEM" label="加盟商问题" style="width: 100%" rows="1"
											cols="100%" maxLength="250">${rstcv.CHANN_PROBLEM}</textarea>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right" class="detail_label">
										改善计划
									</td>
									<td width="20%" class="detail_content" colspan="8">
										<textarea json="IMPRV_PLAN" name="IMPRV_PLAN" id="IMPRV_PLAN"
											label="改善计划" style="width: 100%" rows="1" cols="100%"
											maxLength="250">${rstcv.IMPRV_PLAN}</textarea>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right" class="detail_label">
										竞品信息
									</td>
									<td width="20%" class="detail_content" colspan="8">
										<textarea json="COMPET_PRODUCT" name="COMPET_PRODUCT"
											id="COMPET_PRODUCT" label="竞品信息" style="width: 100%" rows="1"
											cols="100%" maxLength="250">${rstcv.COMPET_PRODUCT}</textarea>
									</td>
								</tr>
								<tr>
									<td width="15%" align="right" class="detail_label">
										支持需求
									</td>
									<td width="20%" class="detail_content" colspan="8">
										<textarea json="SUPPORT_REQ" name="SUPPORT_REQ"
											id="SUPPORT_REQ" label="支持需求" style="width: 100%" rows="1"
											cols="100%" maxLength="250">${rstcv.SUPPORT_REQ}</textarea>
									</td>
								</tr>
							<tr>
								<td border="blod;">
									推广活动推进
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="4">
									活动前期准备
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 启动时间:
									<input json="BEG_DATE" name="BEG_DATE" id="BEG_DATE"
										type="text" size="16" label="启动时间" value="${rstact.BEG_DATE}"
										autocheck="true" inputtype="string" onclick="SelectDate()"
										READONLY />
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(BEG_DATE);">
								</td>
								<td width="15%" align="left" class="detail_label">
									活动形式:
								</td>
								<td width="15%" align="left" class="detail_content">
									<SELECT name="ACTV_STYLE" id="ACTV_STYLE" json="ACTV_STYLE"s
										value="${rstact.ACTV_STYLE}" style="width: 155px"></SELECT>
								</td>
								<td width="15%" align="left" class="detail_label">
									活动主题:
								</td>
								<td width="15%" align="left" class="detail_content">
									<textarea name="ACTV_TITLE" id="ACTV_TITLE" json="ACTV_TITLE"
										value="${rstact.ACTV_TITLE}" rows="2" cols="20%">${rstact.ACTV_TITLE}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 落地时间:
									<input type="text" size="16" name="AGREE_DATE" id="AGREE_DATE"
										json="AGREE_DATE" value="${rstact.AGREE_DATE}"
										onclick="SelectDate()" READONLY />
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(AGREE_DATE);">
								</td>
								<td width="15%" align="left" class="detail_label">
									落地地点:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="AGREE_ADDR" id="AGREE_ADDR" json="AGREE_ADDR"
										value="${rstact.AGREE_ADDR}" size="23" />
								</td>
								<td width="15%" align="left" class="detail_label">
									活动预算费用:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="ACTV_PRMT_COST" id="ACTV_PRMT_COST"
										json="ACTV_PRMT_COST" value="${rstact.ACTV_PRMT_COST}"
										size="23" label="活动预算费用" autocheck="true" inputtype="number"  valueType="number"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 预计成交单数:
									<input type="text" size="13" name="ADVC_DEAL_BILL_NUM"
										id="ADVC_DEAL_BILL_NUM" json="ADVC_DEAL_BILL_NUM"
										value="${rstact.ADVC_DEAL_BILL_NUM}" label="预计成交单数"
										autocheck="true" inputtype="int" />
								</td>
								<td width="15%" align="left" class="detail_label">
									预计成交额:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="5">
									<input name="ADVC_DEAL_BILL_AMOUNT" id="ADVC_DEAL_BILL_AMOUNT"
										json="ADVC_DEAL_BILL_AMOUNT" 
										value="${rstact.ADVC_DEAL_BILL_AMOUNT}" size="23"
										label="预计成交额" autocheck="true" inputtype="number"  valueType="number"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="8">
									&nbsp;&nbsp; 附件: &nbsp;&nbsp;&nbsp;
									<input type="hidden" name="ACTV_PRMT_ATT" id="ACTV_PRMT_ATT"
										json="ACTV_PRMT_ATT" value="${rstact.ACTV_PRMT_ATT}" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="center" class="detail_label">
									活动推进
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;推进日期:
									<input json="FORWORD_DATE" name="FORWORD_DATE"
										id="FORWORD_DATE" type="text" size="16" label="推进日期"
										value="${rstact.FORWORD_DATE}" autocheck="true"
										inputtype="string" onclick="SelectDate()" READONLY />
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(FORWORD_DATE);">
								</td>
								<td width="15%" align="left" class="detail_label">
									售卡数量:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="SALE_CARD_NUM" id="SALE_CARD_NUM"
										json="SALE_CARD_NUM" value="${rstact.SALE_CARD_NUM}" 
										label="售卡数量" autocheck="true" inputtype="int"/>
								</td>
								<td width="15%" align="left" class="detail_label">
									其他:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="OTHER_REMARK" id="OTHER_REMARK"
										json="OTHER_REMARK" value="${rstact.OTHER_REMARK}" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="center" class="detail_label" rowspan="2">
									活动结果
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 售卡数量: &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
									<input json="SALE_CARD_NUM_END" name="SALE_CARD_NUM_END"
										id="SALE_CARD_NUM_END" type="text" size="13" label="售卡数量"
										value="${rstact.SALE_CARD_NUM_END}" autocheck="true"
										inputtype="int" />
								</td>
								<td width="15%" align="left" class="detail_label">
									签到卡数:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="CHECK_CARD_NUM" id="CHECK_CARD_NUM"
										json="CHECK_CARD_NUM" value="${rstact.CHECK_CARD_NUM}" 
										label="签到卡数" autocheck="true" inputtype="int" />
								</td>
								<td width="15%" align="left" class="detail_label">
									成交单数:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="DEAL_BILL_NUM" id="DEAL_BILL_NUM"
										json="DEAL_BILL_NUM" value="${rstact.DEAL_BILL_NUM}" 
										label="成交单数" autocheck="true" inputtype="int"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 预计成交金额:
									<input type="text" size="13" name="ADVC_DEAL_AMOUNT"
										id="ADVC_DEAL_AMOUNT" json="ADVC_DEAL_AMOUNT"
										value="${rstact.ADVC_DEAL_AMOUNT}" onblur="getRate()" 
										label="预计成交额" autocheck="true" inputtype="number"  valueType="number"/>
								</td>
								<td width="15%" align="left" class="detail_label">
									实际投入费用:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="COST_AMOUNT" id="COST_AMOUNT" json="COST_AMOUNT"
										value="${rstact.COST_AMOUNT}" onblur="getRate()" 
										label="实际投入费用" autocheck="true" inputtype="number"  valueType="number"/>
								</td>
								<td width="15%" align="left" class="detail_label">
									费效比:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input name="RATE_AMOUNT" id="RATE_AMOUNT" json="RATE_AMOUNT"
										value="${rstact.RATE_AMOUNT}" READONLY />
								</td>
							</tr>
							 
							<tr>
								<td border="blod;">
									分销商开发与拜访
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="3">
									合作分销商
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;分销商编号:
									 &nbsp;&nbsp;&nbsp;&nbsp;<input json="DISTRIBUTOR_ID_T" name="DISTRIBUTOR_ID_T"
										id="DISTRIBUTOR_ID" type="hidden"
										value="${rstdis.DISTRIBUTOR_ID}" />
									<input json="DISTRIBUTOR_NO_T" name="DISTRIBUTOR_NO_T"
										id="DISTRIBUTOR_NO" type="text" size="14" label="分销渠道编号"
										value="${rstdis.DISTRIBUTOR_NO}" autocheck="true"
										inputtype="string" READONLY />
									<img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_192', false, 'DISTRIBUTOR_NO_T', 'DISTRIBUTOR_NO', 'forms[0]','DISTRIBUTOR_ID_T,DISTRIBUTOR_NO_T,DISTRIBUTOR_NAME_T', 'DISTRIBUTOR_ID,DISTRIBUTOR_NO,DISTRIBUTOR_NAME', 'selChannParam');">
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商名称:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									<input name="DISTRIBUTOR_NAME_T" id="DISTRIBUTOR_NAME"
										json="DISTRIBUTOR_NAME_T" value="${rstdis.DISTRIBUTOR_NAME}"
										size="20" autocheck="true" inputtype="string" READONLY />
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;分销主要问题:&nbsp;&nbsp;&nbsp;
									<textarea json="MAIN_PROBLEM_T" name="MAIN_PROBLEM_T"
										id="MAIN_PROBLEM_T" label="分销商主要问题" style="width: 90%;"
										value="${rstdis.MAIN_PROBLEM}" rows="1" maxLength="2" maxLength="250">${rstdis.MAIN_PROBLEM}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;解决方案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<textarea json="SOLUTION_T" name="SOLUTION_T" id="SOLUTION_T"
										label="解决方案" style="width: 90%;" value="${rstdis.SOLUTION}"
										rows="1" maxLength="2" maxLength="250">${rstdis.SOLUTION}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="3">
									意向分销商
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;分销商类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<select name="WILL_DISTRIBUTOR_TYPE"
										json="WILL_DISTRIBUTOR_TYPE" id="WILL_DISTRIBUTOR_TYPE"
										value="${rstdis.WILL_DISTRIBUTOR_TYPE}" style="width: 120px;"></select>
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商场名称:
								</td>
								<td width="20%" class="detail_content">
									<input name="WILL_SALE_STORE_NAME_T" id="WILL_SALE_STORE_NAME_T"
										json="WILL_SALE_STORE_NAME_T" value="${rstdis.WILL_SALE_STORE_NAME}"  size="20" />
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商姓名:
								</td>
								<td width="20%" class="detail_content">
									<input name="WILL_DISTRIBUTOR_NAME" id="WILL_DISTRIBUTOR_NAME"
										json="WILL_DISTRIBUTOR_NAME"
										value="${rstdis.WILL_DISTRIBUTOR_NAME}" size="20" />
								</td>
						     </tr>
						     <tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;分销商电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="WILL_DISTRIBUTOR_TEL" json="WILL_DISTRIBUTOR_TEL"
										id="WILL_DISTRIBUTOR_TEL"
										value="${rstdis.WILL_DISTRIBUTOR_TEL}" size="14" />
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商地址:
								</td>
								<td width="20%" class="detail_content">
									<input name="WILL_DISTRIBUTOR_ADDRESS"
										id="WILL_DISTRIBUTOR_ADDRESS" json="WILL_DISTRIBUTOR_ADDRESS"
										value="${rstdis.WILL_DISTRIBUTOR_ADDRESS}" size="20" />
								</td>
								<td width="15%" align="left" class="detail_label">
									经营品牌:
								</td>
								<td width="20%" class="detail_content">
									<input name="WILL_DISTRIBUTOR_BUSS_BRAND"
										id="WILL_DISTRIBUTOR_BUSS_BRAND"
										json="WILL_DISTRIBUTOR_BUSS_BRAND"
										value="${rstdis.WILL_DISTRIBUTOR_BUSS_BRAND}" size="20" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;主营品类:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="WILL_DISTRIBUTOR_BUSS_CLASS"
										json="WILL_DISTRIBUTOR_BUSS_CLASS"
										id="WILL_DISTRIBUTOR_BUSS_CLASS"
										value="${rstdis.WILL_DISTRIBUTOR_BUSS_CLASS}" size="14" />
								</td>
								<td width="15%" align="left" class="detail_label">
									意向程度:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									<SELECT name="WILL_DEGREE_T" id="WILL_DEGREE_T" json="WILL_DEGREE_T"
										value="${rstdis.WILL_DEGREE}" style="width: 155px;" /></SELECT>
								</td>
							</tr>
							<tr>
								<td border="blod;">
									"1+N"合作商开发与拜访
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="3">
									合作客户
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;合作渠道编号:
									<input json="DISTRIBUTOR_ID" name="DISTRIBUTOR_ID_N"
										id="DISTRIBUTOR_ID" type="hidden"
										value="${rstcoo.DISTRIBUTOR_ID}" />
									<input json="DISTRIBUTOR_NO" name="DISTRIBUTOR_NO_N"
										id="DISTRIBUTOR_NO" type="text" size="14" label="分销渠道编号"
										value="${rstcoo.DISTRIBUTOR_NO}" autocheck="true"
										inputtype="string" READONLY />
									<img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_190', false, 'DISTRIBUTOR_NO_N', 'DISTRIBUTOR_NO', 'forms[0]','DISTRIBUTOR_ID_N,DISTRIBUTOR_NO_N,DISTRIBUTOR_NAME_N', 'DISTRIBUTOR_ID,DISTRIBUTOR_NO,DISTRIBUTOR_NAME', 'selectParamsT');">
								</td>
								<td width="15%" align="left" class="detail_label">
									合作渠道名称:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									<input name="DISTRIBUTOR_NAME_N" id="DISTRIBUTOR_NAME"
										json="DISTRIBUTOR_NAME" value="${rstcoo.DISTRIBUTOR_NAME}"
										size="20" READONLY />
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;合作主要问题:&nbsp;&nbsp;&nbsp;
									<textarea json="MAIN_PROBLEM" name="MAIN_PROBLEM"
										id="MAIN_PROBLEM" label="合作主要问题" style="width: 90%;"
										value="${rstcoo.MAIN_PROBLEM}" rows="1" maxLength="2">${rstcoo.MAIN_PROBLEM}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;解决方案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<textarea json="SOLUTION" name="SOLUTION" id="SOLUTION"
										label="解决方案" style="width: 90%;" value="${rstcoo.SOLUTION}"
										rows="1" maxLength="2" maxLength="250">${rstcoo.SOLUTION}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="4">
									意向合作客户
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;合作商场类型:&nbsp;&nbsp;
									<select name="MARKET_TYPE"
										json="MARKET_TYPE" id="MARKET_TYPE"
										value="${rstcoo.MARKET_TYPE}" style="width: 120px;"></select>
								</td>
								<td width="15%" align="left" class="detail_label">
									合作商场名称:
								</td>
								<td width="20%" class="detail_content">
									<input name="WILL_SALE_STORE_NAME" id="WILL_SALE_STORE_NAME"
										json="WILL_SALE_STORE_NAME"
										value="${rstcoo.WILL_SALE_STORE_NAME}" size="20" />
								</td>
								<td width="15%" align="left" class="detail_label">
									合作客户姓名:
								</td>
								<td width="20%" class="detail_content">
									<input name="CUST_NAME" id="CUST_NAME" json="CUST_NAME" value="${rstcoo.CUST_NAME}" size="20" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;合作客户电话:&nbsp;&nbsp;&nbsp;<input name="CUST_TEL" json="CUST_TEL" id="CUST_TEL" value="${rstcoo.CUST_TEL}" size="14" />
								</td>
								<td width="15%" align="left" class="detail_label">
									商场地址:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									<input name="MARKET_ADDRESS"
										id="MARKET_ADDRESS" json="MARKET_ADDRESS"
										value="${rstcoo.MARKET_ADDRESS}" size="20" />
								</td>
							</tr>
							  
							<tr>
							    <td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;经营品牌:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									<input name="MARKET_BUSS_BRAND" json="MARKET_BUSS_BRAND"
										id="MARKET_BUSS_BRAND"
										value="${rstcoo.MARKET_BUSS_BRAND}" size="14" />
								</td>
								<td width="15%" align="left" class="detail_label">
									主营品类:
								</td>
								<td width="20%" class="detail_content">
									<input name="MARKET_BUSS_CLASS"
										json="MARKET_BUSS_CLASS"
										id="MARKET_BUSS_CLASS"
										value="${rstcoo.MARKET_BUSS_CLASS}" size="20" />
								</td>
								<td width="15%" align="left" class="detail_label">
									意向程度:
								</td>
								<td width="20%" class="detail_content" >
									<SELECT name="WILL_DEGREE" id="WILL_DEGREE" json="WILL_DEGREE"
										value="${rstcoo.WILL_DEGREE}" style="width: 155px;" /></SELECT>
								</td> 
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;无意向原因:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<textarea json="NO_WILL_RESON" name="NO_WILL_RESON" id="NO_WILL_RESON"
										label="无意向原因" style="width: 90%;" value="${rstdis.NO_WILL_RESON}"
										rows="1" maxLength="2">${rstcoo.NO_WILL_RESON}</textarea>
								</td>
							</tr>  
							 
							<tr>
								<td border="blod;">
									导购员培训
								</td>
							</tr>
							<tr>
							  <td width="15%" align="right" class="detail_label">
									导购员培训人数
								</td>
								<td width="15%" align="left" class="detail_content">
									<input type="text" name="TRAN_NUM" id="TRAN_NUM" json="TRAN_NUM" value="${rstshop.TRAN_NUM}" label="导购员培训人数"
									autocheck="true" inputtype="int"/>
								</td>
								<td width="15%" align="left" class="detail_label">
									培训主题:
								</td>
								<td width="15%" align="left" class="detail_content">
									<SELECT name="TRAN_TITLE" id="TRAN_TITLE" json="TRAN_TITLE"
										value="${rstshop.TRAN_TITLE}" style="width: 155px"></SELECT>
								</td>
								<td width="15%" align="left" class="detail_label">
									培训形式:
								</td>
								<td width="15%" align="left" class="detail_content">
									<SELECT name="TRAN_TYPE" id="TRAN_TYPE" json="TRAN_TYPE"
										value="${rstshop.TRAN_TYPE}" style="width:155px;"></SELECT>
								</td>
							</tr>
							<tr>
							  <td width="15%" align="right" class="detail_label">
									培训时间
								</td>
								<td width="15%" align="left" class="detail_content">
									<input type="text" name="TRAN_DATE" id="TRAN_DATE" json="TRAN_DATE" value="${rstshop.TRAN_DATE}" label="培训时间" onclick="SelectDate()" READONLY/>
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(TRAN_DATE);">
								</td>
								<td width="15%" align="left" class="detail_label">
									培训照片:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									<input name="TRAN_PIC" id="TRAN_PIC" json="TRAN_PIC"
										value="${rstshop.TRAN_PIC}" type="hidden" /> 
								</td>
							</tr>  
							 
					        <tr>
								<td border="blod;">
									门店拜访表
								</td>
							</tr>
							<tr>
							  <td width="15%" align="right" class="detail_label">
									门店编号:
								</td>
								<td width="15%" align="left" class="detail_content">
									<input type="hidden" name="TERM_ID" id="TERM_ID" json="TERM_ID" value="${rsterm.TERM_ID}" />
									<input type="text" name="TERM_NO" id="TERM_NO" json="TERM_NO" value="${rsterm.TERM_NO}"   label="门店编号" READONLY/>
									<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_82', false, 'TERM_NO', 'TERM_NO', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME,CHANN_ID_T,CHANN_NO_T,CHANN_NAME_T', 'TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParamsByTerm')">
								</td>
								<td width="15%" align="right" class="detail_label">
									门店名称:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									<input type="text" name="TERM_NAME" id="TERM_NAME" json="TERM_NAME" value="${rsterm.TERM_NAME}" label="门店名称" READONLY/>
								</td>
							</tr>
							<tr>
							  <td width="15%" align="left" class="detail_label">
									渠道编号:
								</td>
								<td width="15%" align="left" class="detail_content">
								    <input name="CHANN_ID_T" id="CHANN_ID_T" json="CHANN_ID_T" value="${rsterm.CHANN_ID}" type="hidden" />
									<input name="CHANN_NO_T" id="CHANN_NO_T" json="CHANN_NO_T"
										value="${rsterm.CHANN_NO}"  READONLY/>
								</td>
								<td width="15%" align="left" class="detail_label">
									渠道名称:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									<input name="CHANN_NAME_T" id="CHANN_NAME_T" json="CHANN_NAME_T"
										value="${rsterm.CHANN_NAME}" READONLY/>
								</td>
							</tr>
							<tr> 
							   <td width="15%" align="left" class="detail_label">
									硬装:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
									<textarea name="HARD_DECORATE" id="HARD_DECORATE" json="HARD_DECORATE" rows="1" cols="100%" style="width:100%;" maxLength="250">${rsterm.HARD_DECORATE}</textarea>
								</td>
						    </tr>
						    <tr>
								<td width="15%" align="left" class="detail_label">
									软装:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
									<textarea name="SOFT_DECORATE" id="SOFT_DECORATE" json="SOFT_DECORATE" rows="1" cols="100%" style="width:100%;" maxLength="250">${rsterm.SOFT_DECORATE}</textarea>
								</td>
						    </tr>
						    <tr>
								<td width="15%" align="left" class="detail_label">
									导购员:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
									<textarea name="SHOPP_GUIDE" id="SHOPP_GUIDE" json="SHOPP_GUIDE" rows="1" cols="100%" style="width:100%;" maxLength="250">${rsterm.SHOPP_GUIDE}</textarea>
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_label">
									主要问题:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
									<textarea name="MAIN_PROBLEM" id="MAIN_PROBLEM" json="MAIN_PROBLEM_TERM" rows="1" cols="100%" style="width:100%;" maxLength="250">${rsterm.MAIN_PROBLEM}</textarea>
								</td>
						    </tr>
						    <tr>
								<td width="15%" align="left" class="detail_label">
									解决方案:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
									<textarea name="SOLUTION" id="SOLUTION" json="SOLUTION_TERM" rows="1" cols="100%" style="width:100%;" maxLength="250">${rsterm.SOLUTION}</textarea>
								</td>
							</tr> 
						</table>
						</form>
						<form action="mkmdayreport.shtml?action=toFrames" name="pageForm"
							id="pageForm" method="post"></form>
						</div>
						<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
						<script type="text/javascript">
	   SelDictShow("VISIT_TYPE","BS_156","${rst.VISIT_TYPE}","");
	   SelDictShow("ACTV_STYLE","BS_179","${rstact.ACTV_STYLE}","");
	   SelDictShow("WILL_DISTRIBUTOR_TYPE","BS_174","${rstdis.WILL_DISTRIBUTOR_TYPE}","");
	   SelDictShow("WILL_DEGREE_T","BS_175","${rstdis.WILL_DEGREE}","");
	   SelDictShow("WILL_DEGREE","BS_175","${rstcoo.WILL_DEGREE}","");
	   SelDictShow("TRAN_TITLE","BS_176","${rstshop.TRAN_TITLE}","");
	   SelDictShow("TRAN_TYPE","BS_177","${rstshop.TRAN_TYPE}","");
	   SelDictShow("MARKET_TYPE","BS_178","${rstcoo.MARKET_TYPE}","");
	   
	   uploadFile('STORE_ATT', 'SAMPLE_DIR', true,true,true);
	   uploadFile('ACTV_PRMT_ATT','SAMPLE_DIR', true,true,true);
	   uploadFile('TRAN_PIC','SAMPLE_DIR', true,true,true);
	  
	   var t = document.getElementById('ON_TRIP');    
	   if(t.value == ""){
	      t.innerHTML = '从XXX省份XXX市XXX区县 至 XXX省份XXX市XXX区县';
	      t.style.color="gray";
	   }
	   t.onfocus = function(){   
	      if(this.innerHTML == '从XXX省份XXX市XXX区县 至 XXX省份XXX市XXX区县'){this.value = '';t.style.color="black";}};        
	   t.onblur = function(){        
	      if(this.value == ''){this.innerHTML = '从XXX省份XXX市XXX区县 至 XXX省份XXX市XXX区县';t.style.color="gray"; }};
	      
	   var t1 = document.getElementById('CORP_BUSS_DEAL');    
	   if(t1.value == ""){
	      t1.innerHTML = '具体处理内容:1、...... 2、...... 3、......';
	      t1.style.color="gray";
	   }
	   t1.onfocus = function(){   
	      if(this.innerHTML == '具体处理内容:1、...... 2、...... 3、......'){this.value = '';t1.style.color="black";}};        
	   t1.onblur = function(){        
	      if(this.value == ''){this.innerHTML = '具体处理内容:1、...... 2、...... 3、......';t1.style.color="gray"; }};
	      
	   var t2 = document.getElementById('WAKE_OFF');    
	   if(t2.value == ""){
	      t2.innerHTML = '从XXXX年XX月XX日 至 XXXX年XX月XX日';
	      t2.style.color="gray";
	   }
	   t2.onfocus = function(){   
	      if(this.innerHTML == '从XXXX年XX月XX日 至 XXXX年XX月XX日'){this.value = '';t2.style.color="black";}};        
	   t2.onblur = function(){        
	      if(this.value == ''){this.innerHTML = '从XXXX年XX月XX日 至 XXXX年XX月XX日'; t2.style.color="gray"; }};
	   
	</script>
	<script type="text/javascript">
	     function load(){
	         
	         var MATT_AMOUNT = $("#MATT_AMOUNT").val();
	         if(MATT_AMOUNT !=""){
	           MATT_AMOUNT = parseInt(MATT_AMOUNT);
	         } else{
	           MATT_AMOUNT = 0;
	         }
	         
	         var SOFT_BED_AMOUNT = $("#SOFT_BED_AMOUNT").val();
	         if(SOFT_BED_AMOUNT !=""){
	           SOFT_BED_AMOUNT = parseInt(SOFT_BED_AMOUNT);
	         }else{
	           SOFT_BED_AMOUNT = 0;
	         }
	         
	         var OTHER_AMOUNT = $("#OTHER_AMOUNT").val();
	         if(OTHER_AMOUNT !=""){
	            OTHER_AMOUNT = parseInt(OTHER_AMOUNT);
	         }else{
	            OTHER_AMOUNT = 0
	         }
	         var TOTAL_AMOUNT = MATT_AMOUNT+SOFT_BED_AMOUNT+OTHER_AMOUNT;
	         document.getElementById("TOTAL_AMOUNT").value=TOTAL_AMOUNT;
	         
	         var ADVC_DEAL_AMOUNT = $("#ADVC_DEAL_AMOUNT").val();
			 if(ADVC_DEAL_AMOUNT ==""){
			     ADVC_DEAL_AMOUNT = 0;
			 }
			 var COST_AMOUNT = $("#COST_AMOUNT").val();
			 if(COST_AMOUNT ==""){
			     COST_AMOUNT  = 0;
			 }
			 if(ADVC_DEAL_AMOUNT !="" && COST_AMOUNT !=""){
			    var RATE_AMOUNT = (ADVC_DEAL_AMOUNT/COST_AMOUNT).toFixed(2);
			    $("#RATE_AMOUNT").val(RATE_AMOUNT);
			 } 
	     }
	</script>
</body>

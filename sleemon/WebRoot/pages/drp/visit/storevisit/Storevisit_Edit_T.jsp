<!--  
/**
 *@module 渠道管理-拜访管理
 *@func   门店拜访表维护
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
	<script type=text/javascript src="${ctx}/pages/drp/visit/storevisit/Storevisit_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>门店拜访表维护</title>
	 
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
			value="CHANN_ID in ${CHANN_ID}" />
		<input type="hidden" id="selectParamsT" name="selectParamsT" />
	    <input type="hidden" id="selectParamsTt" name="selectParamsTt" />
	    
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="15%" class="detail_label">
								流程编号：
							</td>
							<td width="20%" class="detail_content">
							     <input json="STORE_VISIT_ID" name="STORE_VISIT_ID"
									id="STORE_VISIT_ID" type="hidden" seltarget="selLL" size="30" 
									label="流程ID"  value="${rst.STORE_VISIT_ID}" />
								<input json="STORE_VISIT_NO" name="STORE_VISIT_NO" type="text" size="30" 
									id="STORE_VISIT_NO" autocheck="true" label="流程编号" inputtype="string"
									mustinput="true" maxlength="32"  
									<c:if test="${not empty rst.STORE_VISIT_NO}">value="${rst.STORE_VISIT_NO}"</c:if>
									<c:if test="${empty rst.STORE_VISIT_NO}">value="自动生成"</c:if>
									READONLY>
							</td>
							<td width="15%" class="detail_label">
								紧急程度：
							</td>
							<td width="20%" class="detail_content">
							     <input type="radio"  name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" checked="true" value="正常"/>正常 
							     <input type="radio"  name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" value="重要"/>重要 
							     <input type="radio"  name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" value="紧急"/>紧急 
							     <input type="hidden" name="EME_DEGREET" id="EME_DEGREET" json="EME_DEGREET" value="${rst.EME_DEGREE}"/>
							</td>
							<td width="15%" class="detail_label">
								拜访人：
							</td>
							<td width="20%" class="detail_content">
							    <input name="RYXXID" id="RYXXID" json="RYXXID" value="${params.RYXXID}" type="hidden" />
								<input json="APPLY_PERSON" name="APPLY_PERSON" id="APPLY_PERSON" type="text" size="30" 
									label="申请人" value="${rst.APPLY_PERSON}"  autocheck="true"  inputtype="string"  mustinput="true" readonly />
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DEP" name="APPLY_DEP" id="APPLY_DEP" type="text" size="30" 
									label="申请部门" value="${rst.APPLY_DEP}"  autocheck="true"  inputtype="string" readonly/>
							</td>
							<td width="15%" class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DATE" name="APPLY_DATE" id="APPLY_DATE" type="text" size="30" 
									label="申请日期" value="${rst.APPLY_DATE}"   autocheck="true"  inputtype="string" READONLY />
							</td>
							<td width="15%" class="detail_label">
								拜访时间：
							</td>
							<td width="20%" class="detail_content">
							    <input json="VISIT_DATE" name="VISIT_DATE" id="VISIT_DATE" type="text" size="30" 
									label="申请时间" value="${rst.VISIT_DATE}"  autocheck="true"  inputtype="string" 
									mustinput="true"   onclick="SelectDate()" READONLY/>
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
							    <select id="VISIT_TYPE" name="VISIT_TYPE"  json="VISIT_TYPE" style="width: 215px;" inputtype="string" mustinput="true" autocheck="true" label="拜访方式"  value="${rst.VISIT_TYPE}">
								</select>
							</td>
							<td width="15%" class="detail_label">
								终端名称：
							</td>
							<td width="20%" class="detail_content">
							    <input json="TERM_ID" name="TERM_ID" id="TERM_ID" size="30" 
									autocheck="true" label="门店ID" type="hidden"
									inputtype="string" value="${rst.TERM_ID}" />
								<input json="TERM_NO" name="TERM_NO" id="TERM_NO" size="30" 
									autocheck="true" label="门店编号" type="hidden"
									inputtype="string" value="${rst.TERM_NO}" />
							    <input json="STORE_NAME" name="TERM_NAME" id="STORE_NAME" type="text" size="30" 
									label="门店名称" value="${rst.STORE_NAME}"   autocheck="true"  inputtype="string" READONLY />
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_82', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,ZONE_ADDR', 'TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,ZONE_ADDR', 'selectParams')">
							</td>
							<td width="15%" class="detail_label">
								所属加盟商：
							</td>
							<td width="20%" class="detail_content">
							    <input json="CHANN_ID"  name="CHANN_ID" id="CHANN_ID" type="hidden" value="${rst.CHANN_ID}" />
							    <input json="CHANN_NO"  name="CHANN_NO" id="CHANN_NO" type="hidden" value="${rst.CHANN_NO}"/>
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" size="30" 
									label="所属加盟商" value="${rst.CHANN_NAME}"  autocheck="true"  inputtype="string" READONLY/>
							</td>
						</tr> 
						<tr>
							<td width="15%"  class="detail_label">
								城市：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input json="CITY" name="ZONE_ADDR" id="CITY" type="text" size="30" 
									label="城市" value="${rst.CITY}"  autocheck="true"  inputtype="string" READONLY/>
							</td>
						</tr> 
						<tr  style="padding-left:50px;">
						    <th align="center">
						        门店拜访  
						    </th>
						</tr>
						<tr>
							<td width="15%"  class="detail_label">
								店招：
							</td>
							<td width="20%" class="detail_content">
								<input json="STORE_STROKES" name="STORE_STROKES" id="STORE_STROKES" type="text" size="30" 
									label="店招" value="${rst.STORE_STROKES}"  autocheck="true"  inputtype="string" maxlength="100"/>
							</td>
							<td width="15%" class="detail_label">
								店内灯箱：
							</td>
							<td width="20%" class="detail_content">
							    <input json="LIGHT_BOX" name="LIGHT_BOX" id="LIGHT_BOX" type="text" size="30" maxlength="20"
									label="店内灯箱" value="${rst.LIGHT_BOX}"  autocheck="true"  inputtype="string"/>
							</td>
							<td width="15%" class="detail_label">
								地面：
							</td>
							<td width="20%" class="detail_content">
							    <input json="GROUND" name="GROUND" id="GROUND" type="text" size="30" maxlength="20"
									label="地面" value="${rst.GROUND}"   autocheck="true"  inputtype="string"/>
							</td>
						</tr> 
						<tr>
							<td width="15%" class="detail_label">
								产品：
							</td>
							<td width="20%" class="detail_content">
								<input json="PRODUCTS" name="PRODUCTS" id="PRODUCTS" type="text" size="30" maxlength="20"
									label="产品" value="${rst.PRODUCTS}"  autocheck="true"  inputtype="string" />
							</td>
							<td width="15%" class="detail_label">
								道具：
							</td>
							<td width="20%" class="detail_content">
							    <input json="PROPERTIES" name="PROPERTIES" id="PROPERTIES" type="text" size="30" maxlength="20"
									label="道具" value="${rst.PROPERTIES}"  autocheck="true"  inputtype="string"/>
							</td>
							<td width="15%" class="detail_label">
								软饰：
							</td>
							<td width="20%" class="detail_content">
							    <input json="SOFT_DECORATION" name="SOFT_DECORATION" id="SOFT_DECORATION" type="text" size="30" 
									label="软饰" value="${rst.SOFT_DECORATION}"   autocheck="true"  inputtype="string" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								电视：
							</td>
							<td width="20%" class="detail_content">
								<input json="TELEVISION" name="TELEVISION" id="TELEVISION" type="text" size="30" maxlength="20"
									label="电视" value="${rst.TELEVISION}"  autocheck="true"  inputtype="string" />
							</td>
							<td width="15%" class="detail_label">
								灯光：
							</td>
							<td width="20%" class="detail_content">
							    <input json="LIGHT_LAMP" name="LIGHT_LAMP" id="LIGHT_LAMP" type="text" size="30" maxlength="20"
									label="灯光" value="${rst.LIGHT_LAMP}"  autocheck="true"  inputtype="string"/>
							</td>
							<td width="15%" class="detail_label">
								物料：
							</td>
							<td width="20%" class="detail_content">
							    <input json="MATERIALS" name="MATERIALS" id="MATERIALS" type="text" size="30" maxlength="20"
									label="物料" value="${rst.MATERIALS}"   autocheck="true"  inputtype="string"/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								导购形象：
							</td>
							<td width="20%" class="detail_content">
								<input json="FIGURE" name="FIGURE" id="FIGURE" type="text" size="30" maxlength="100"
									label="导购形象" value="${rst.FIGURE}"  autocheck="true"  inputtype="string" />
							</td>
							<td width="15%" class="detail_label">
								导购态度：
							</td>
							<td width="20%" class="detail_content">
							    <input json="MANNER" name="MANNER" id="MANNER" type="text" size="30" maxlength="100"
									label="导购态度" value="${rst.MANNER}"  autocheck="true"  inputtype="string"/>
							</td>
							<td width="15%" class="detail_label">
								导购技能：
							</td>
							<td width="20%" class="detail_content">
							    <input json="TECHNICAL" name="TECHNICAL" id="TECHNICAL" type="text" size="30" maxlength="100"
									label="导购技能" value="${rst.TECHNICAL}"   autocheck="true"  inputtype="string"/>
							</td>
						</tr>
						 <tr>
							<td width="15%" class="detail_label">
								饮水机：
							</td>
							<td width="20%" class="detail_content">
							     <input json="WATER_MACHINE" name="WATER_MACHINE" id="WATER_MACHINE" label="饮水机" maxlength="20"
									    size="30"    autocheck="true" inputtype="string" value="${rst.WATER_MACHINE}"/> 
							</td>
							<td width="15%"  class="detail_label">
								块毯：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							    <input json="BLANKETS" name="BLANKETS" id="BLANKETS" label="块毯" maxlength="20"
									 size="30"   autocheck="true" inputtype="string" value="${rst.BLANKETS}"/>
							</td>
						</tr> 
						
						<tr style="padding-left:50px;">
						    <th align="center">
						       爆破活动总结
						    </th>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								执行活动主题：
							</td>
							<td width="20%" class="detail_content">
								<input json="EXECUTE_ACTION_TOPIC" name="EXECUTE_ACTION_TOPIC" id="EXECUTE_ACTION_TOPIC" type="text" maxlength="100"
									label="执行活动主题" value="${rst.EXECUTE_ACTION_TOPIC}"  autocheck="true"  inputtype="string"  size="30"  />
							</td>
							<td width="15%" class="detail_label">
								执行活动时间：
							</td>
							<td width="20%" class="detail_content">
							    <input json="EXECUTE_ACTION_DATE" name="EXECUTE_ACTION_DATE" id="EXECUTE_ACTION_DATE" type="text" size="30" 
									label="执行活动时间" value="${rst.EXECUTE_ACTION_DATE}"  autocheck="true"  inputtype="string" onclick="SelectDate()" READONLY/>
							    <img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(EXECUTE_ACTION_DATE);">
							</td>
							<td width="15%" class="detail_label">
								执行活动地点：
							</td>
							<td width="20%" class="detail_content">
							    <input json="EXECUTE_ACTION_ADDR" name="EXECUTE_ACTION_ADDR" id="EXECUTE_ACTION_ADDR" type="text" size="30" 
									label="执行活动地点" value="${rst.EXECUTE_ACTION_ADDR}"   autocheck="true"  inputtype="string" maxlength="200"/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								活动目标：
							</td>
							<td width="20%" class="detail_content">
								<input json="ACTION_PLAN" name="ACTION_PLAN" id="ACTION_PLAN" type="text" size="30" maxlength="100"
									label="活动目标" value="${rst.ACTION_PLAN}"  autocheck="true"  inputtype="string" />
							</td>
							<td width="15%" class="detail_label">
								实际达成：
							</td>
							<td width="20%" class="detail_content">
							    <input json="ACTION_REALITY_RATE" name="ACTION_REALITY_RATE" id="ACTION_REALITY_RATE" type="text" maxlength="20"
									label="实际达成" value="${rst.ACTION_REALITY_RATE}"  autocheck="true"  inputtype="string" size="30" />
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    <input json="ACTION_RATE" name="ACTION_RATE" id="ACTION_RATE" type="text" size="30" maxlength="20"
									label="达成率" value="${rst.ACTION_RATE}"   autocheck="true"  inputtype="string"/>
							</td>
						</tr>
						<tr>
						  <td width="15%" class="detail_label">
								实际投入：
							</td>
							<td width="20%" class="detail_content" colspan="5">
								<input json="ACTUAL_INVESTMENT" name="ACTUAL_INVESTMENT" id="ACTUAL_INVESTMENT" type="text" size="30" 
									label="实际投入" value="${rst.ACTUAL_INVESTMENT}"  autocheck="true"  inputtype="string" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								活动总结：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <table>
							        <tr>
							          <td>
							              亮点 
									        <textarea json="ACTION_RIGHT" name="ACTION_RIGHT" id="ACTION_RIGHT" label="亮点" 
									                  rows="2" cols="20%" autocheck="true" inputtype="string"   maxlength="200">${rst.ACTION_RIGHT}</textarea>
									  </td>
									  <td> 
							              不足 
									        <textarea json="ACTION_BAD" name="ACTION_BAD" id="ACTION_BAD" label="不足" 
									                  rows="2" cols="20%" autocheck="true" inputtype="string"   maxlength="200">${rst.ACTION_BAD}</textarea>
							           </td>
							        </tr>
							    </table>
							</td>
						</tr> 
						
						<tr style="padding-left:50px;">
						    <th align="center">
						       零售达成
						    </th>
						</tr>
						
						<tr>
							<td width="15%" class="detail_label">
								本月计划：
							</td>
							<td width="20%" class="detail_content">
								<input json="MONTH_ORDER_NUM" name="MONTH_ORDER_NUM" id="MONTH_ORDER_NUM" type="text" size="30" 
									label="本月计划" value="${rst.MONTH_ORDER_NUM}"  autocheck="true"  inputtype="string" maxlength="20"/>
							</td>
							<td width="15%" class="detail_label">
								实际达成：
							</td>
							<td width="20%" class="detail_content">
							    <input json="MONTH_REALITY_RATE" name="MONTH_REALITY_RATE" id="MONTH_REALITY_RATE" type="text" size="30" 
									label="实际达成" value="${rst.MONTH_REALITY_RATE}"  autocheck="true"  inputtype="string" maxlength="20"/>
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    <input json="MONTH_RATE" name="MONTH_RATE" id="MONTH_RATE" type="text" size="30" maxlength="20"
									label="达成率" value="${rst.MONTH_RATE}"   autocheck="true"  inputtype="string"/>
							</td>
						</tr>
						
						<tr>
							<td width="15%" class="detail_label">
								本季目标：
							</td>
							<td width="20%" class="detail_content">
								<input json="SEASON_ORDER_NUM" name="SEASON_ORDER_NUM" id="SEASON_ORDER_NUM" type="text" maxlength="20"
									label="本季目标" value="${rst.SEASON_ORDER_NUM}"  autocheck="true"  inputtype="string" size="30"  />
							</td>
							<td width="15%"  class="detail_label">
								实际达成：
							</td>
							<td width="20%" class="detail_content">
							    <input json="SEASON_REALITY_RATE" name="SEASON_REALITY_RATE" id="SEASON_REALITY_RATE" type="text" maxlength="20"
									label="实际达成" value="${rst.SEASON_REALITY_RATE}"  autocheck="true"  inputtype="string" size="30" />
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    <input json="SEASON_RATE" name="SEASON_RATE" id="SEASON_RATE" type="text" size="30" maxlength="20"
									label="达成率" value="${rst.SEASON_RATE}"   autocheck="true"  inputtype="string"/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								月度评效：
							</td>
							<td width="20%" class="detail_content">
								<input json="EVALUATION_MONTH" name="EVALUATION_MONTH" id="EVALUATION_MONTH" type="text" size="30" 
									label="月度评效" value="${rst.EVALUATION_MONTH}"  autocheck="true"  inputtype="string" maxlength="100"/>
							</td>
							<td width="15%" class="detail_label">
								本季评效：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							    <input json="EVALUATION_SEASON" name="EVALUATION_SEASON" id="EVALUATION_SEASON" type="text" size="30" 
									label="本季评效" value="${rst.EVALUATION_SEASON}"  autocheck="true"  inputtype="string" maxlength="100"/>
							</td>
						</tr>
						
						<tr style="padding-left:50px;">
						    <th align="center">
						         下一步计划
						    </th>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								活动计划主题：
							</td>
							<td width="20%" class="detail_content">
								<input json="ACTION_PLAN_TOPIC" name="ACTION_PLAN_TOPIC" id="ACTION_PLAN_TOPIC" type="text" size="30" 
									label="活动计划主题" value="${rst.ACTION_PLAN_TOPIC}"  autocheck="true"  inputtype="string" maxlength="100"/>
							</td>
							<td width="15%" class="detail_label">
								活动计划时间：
							</td>
							<td width="20%" class="detail_content">
							    <input json="ACTION_PLAN_DATE" name="ACTION_PLAN_DATE" id="ACTION_PLAN_DATE" type="text" size="30" 
									label="活动计划时间" value="${rst.ACTION_PLAN_DATE}"  autocheck="true"  inputtype="string" onclick="SelectDate()" READONLY/>
								<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(ACTION_PLAN_DATE);">
							</td>
							<td width="15%" class="detail_label">
								活动计划地点：
							</td>
							<td width="20%" class="detail_content">
							    <input json="ACTION_PLAN_ADDR" name="ACTION_PLAN_ADDR" id="ACTION_PLAN_ADDR" type="text" size="30" 
									label="活动计划地点" value="${rst.ACTION_PLAN_ADDR}"   autocheck="true"  inputtype="string" maxlength="100"/>
							</td>
						</tr>
						<tr>
						   <td width="15%" class="detail_label">
								   预计投入：
							</td>
							<td width="20%" class="detail_content" colspan="5">
								<input json="FORECAST_INVESTMENT" name="FORECAST_INVESTMENT" id="FORECAST_INVESTMENT" type="text" size="30" 
									label="预计投入" value="${rst.FORECAST_INVESTMENT}"  autocheck="true"  inputtype="string" maxlength="30"/>
							</td>
						</tr>
						 <tr>
							<td width="15%" class="detail_label">
								预计达成目标：
							</td>
							<td width="20%" class="detail_content" >
								 <textarea json="EXPECTED_GOAL" name="EXPECTED_GOAL" id="EXPECTED_GOAL" label="预计达成目标" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.EXPECTED_GOAL}</textarea>
							</td>
							<td width="15%" class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="COMPETITION_INFO" name="COMPETITION_INFO" id="COMPETITION_INFO" label="竞品信息" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.COMPETITION_INFO}</textarea>
							</td>
							<td width="15%" class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="SUPPORT_DEMAND" name="SUPPORT_DEMAND" id="SUPPORT_DEMAND" label="支持需求" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.SUPPORT_DEMAND}</textarea>
							</td>
						</tr> 
						<tr>
							<td width="15%" class="detail_label">相关附件：</td>
							<td width="20%" class="detail_content" colspan="5">
			                     <input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"  type="hidden"   value="${rst.ATT_PATH}"/> 
							</td> 
						</tr>
					</table>
				</td>
			</tr>
						
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
       //SelDictShow("LOCAL_TYPE","BS_86","${params.LOCAL_TYPE}","");
       SelDictShow("VISIT_TYPE","BS_146","${rst.VISIT_TYPE}","");
       SelDictShow("RNVTN_PROP","BS_87","${rst.RNVTN_PROP}","");
       SelDictShow("BUSS_SCOPE","BS_88","${rst.BUSS_SCOPE}","");
       SelDictShow("TERM_WHICH_NUM","BS_89","${rst.TERM_WHICH_NUM}","");
       SelDictShow("REIT_POLICY","BS_90","${params.REIT_POLICY}","");
	   uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
	   
	   var EME_DEGREET =  document.getElementsByName("EME_DEGREE");
	   for(var i=0;i<EME_DEGREET.length;i++){
	       if(EME_DEGREET[i].value==$("#EME_DEGREET").val()){
	           EME_DEGREET[i].checked=true;
	       }
	   }
	</script>
   </div>
</body>



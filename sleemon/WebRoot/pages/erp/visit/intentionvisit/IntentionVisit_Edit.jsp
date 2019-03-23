<!--  
/**
 *@module 渠道管理-拜访管理
 *@func  意向客户拜访评估单
 *@version 1.1
 *@author zzb
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
    <script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/erp/visit/intentionvisit/IntentionVisit_Edit.js"></script>
	<script type=text/javascript src="${ctx}/pages/erp/visit/intentionvisit/dyTable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	<!--.
		 meun{position:absolute;width:auto;height:auto;z-index:1;left: 200px;top: 150px}
		.meun td{text-align:center;}
	-->
	</style>	
	<title>意向客户拜访评估单</title>
	 
</head>
<script type="text/javascript">
  function init(){
	  var sp = "@@";
	  var existName = "${rst.EXIST_STORE_NAME}";
      var existAddr = "${rst.EXIST_STORE_ADDR}";
      var existArea = "${rst.EXIST_STORE_AREA}";
      var existRange= "${rst.EXIST_STORE_RANGE}";
      var existComp = "${rst.EXIST_STORE_COMPETITION}";
      
	  var existNames = existName.split(sp);
	  var existAddrs = existAddr.split(sp);
	  var existAreas = existArea.split(sp);
	  var existRanges = existRange.split(sp);
	  var existComps = existComp.split(sp);
	 
	  for(var i=0;i<existNames.length;i++){
		  var arrs = [];
		  pushArry(arrs,existNames[i]);
		  pushArry(arrs,existAddrs[i]);
		  pushArry(arrs,existAreas[i]);
		  pushArry(arrs,existRanges[i]);
		  pushArry(arrs,existComps[i]);
		  addRow(arrs);
	  }
	 
	  var NEW_STORE_NAME = "${rst.NEW_STORE_NAME}";
	  var NEW_STORE_ADDR = "${rst.NEW_STORE_ADDR}";
	  var NEW_STORE_AREA = "${rst.NEW_STORE_AREA}";
	  var NEW_STORE_RANGE = "${rst.NEW_STORE_RANGE}";
	  var NEW_STORE_DATE = "${rst.NEW_STORE_DATE}";
	  
	  var NEW_STORE_NAMES = NEW_STORE_NAME.split(sp);
	  var NEW_STORE_ADDRS = NEW_STORE_ADDR.split(sp);
	  var NEW_STORE_AREAS = NEW_STORE_AREA.split(sp);
	  var NEW_STORE_RANGES = NEW_STORE_RANGE.split(sp);
	  var NEW_STORE_DATES = NEW_STORE_DATE.split(sp);
	  for(var i=0;i<NEW_STORE_NAMES.length;i++){
		  var arrs = [];
		  pushArry(arrs,NEW_STORE_NAMES[i]);
		  pushArry(arrs,NEW_STORE_ADDRS[i]);
		  pushArry(arrs,NEW_STORE_AREAS[i]);
		  pushArry(arrs,NEW_STORE_RANGES[i]);
		  pushArry(arrs,NEW_STORE_DATES[i]);
		  addRow1(arrs);
	 }
	  
	  var VISIT_TYPE = "${rst.VISIT_TYPE}";
	  var VISIT_REMARK = "${rst.VISIT_REMARK}";
	  var VISIT_TYPES = VISIT_TYPE.split(sp);
	  var VISIT_REMARKS = VISIT_REMARK.split(sp);
	  for(var i=0;i<VISIT_TYPES.length;i++){
		  var arrs = [];
		  pushArry(arrs,VISIT_TYPES[i]);
		  pushArry(arrs,VISIT_REMARKS[i]);
		  addRow2(arrs);
	 }
	  
  }
</script>
<body class="bodycss1" onload="init();">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S"  id="save">
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
		<input type="hidden" id="selectParams" name="selectParams" value="CHANN_ID in ${CHANN_ID}" />
	    <input type="hidden" name="selectRYXXParams" id="selectRYXXParams" value="">
	    <input type="hidden" name="selectPointParams" id="selectPointParams" value=" STATE='启用' and DEL_FLAG=0">
	    <input type="hidden" name="selectChannParams" id="selectChannParams" value=" STATE='启用' and DEL_FLAG=0 and CHANN_TYPE='区域服务中心' ">
	    <input type="hidden" name="selectBrandParams" id="selectBrandParams" value=" STATE='启用' and DEL_FLAG=0  ">
	    
	    
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
						<tr>
							<td width="15%" align="right" class="detail_label"> 单据编号： </td>
							<td width="20%" class="detail_content" colspan="5">
								<input   name="INTE_CHANN_NO" type="text"   
									id="INTE_CHANN_NO" autocheck="true" label="单据编号" inputtype="string"
									mustinput="true" maxlength="32"  
									<c:if test="${not empty rst.INTE_CHANN_NO}">value="${rst.INTE_CHANN_NO}"</c:if>
									<c:if test="${empty rst.INTE_CHANN_NO}">value="自动生成"</c:if>
									READONLY>
							</td>
							
						</tr>
						<tr>
					     	<td width="15%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="20%" class="detail_content">
									
							 <input  type="hidden"  name="APPLY_PERSON_ID" id="APPLY_PERSON_ID" json="APPLY_PERSON_ID" value="${rst.APPLY_PERSON_ID}"/>
		                     <input json="APPLY_PERSON_NAME" name="APPLY_PERSON_NAME" id="APPLY_PERSON_NAME" label="申请人" mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.APPLY_PERSON_NAME}"/> 
		                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('BS_10', false, 'APPLY_PERSON_ID', 'RYXXID', 'forms[0]','APPLY_PERSON_ID,APPLY_PERSON_NAME,APPLY_DEP', 'RYXXID,XM,BMMC', 'selectRYXXParams')">
								
							</td>
							
							<td width="15%" align="right" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DEP" name="APPLY_DEP" id="APPLY_DEP" type="text" maxlength="30" 
									label="申请部门" value="${rst.APPLY_DEP}"  autocheck="true"  inputtype="string"  READONLY/>
							</td>
							<td width="15%" align="right" class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DATE" name="APPLY_DATE" id="APPLY_DATE" type="text" maxlength="30" onclick="SelectDate();"
									label="申请日期" value="${rst.APPLY_DATE}"   autocheck="true"  inputtype="string" mustinput="true" READONLY />
									<img align="absmiddle" style="cursor: hand"src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(APPLY_DATE);">
							</td>
						 
					    </tr>
					     <tr>
							<td width="15%" align="right" class="detail_label">
								城市名称：
							</td>
							<td width="20%" class="detail_content">
							    <input json="CITY" name="CITY" id="CITY" type="text" maxlength="50" 
									label="城市名称" value="${rst.CITY}" mustinput="true" autocheck="true"  inputtype="string"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								区号：
							</td>
							<td width="20%" class="detail_content">
							    <input json="AREA_CODE" name="AREA_CODE" id="AREA_CODE" type="text" maxlength="30" 
									label="区号" value="${rst.AREA_CODE}"   autocheck="true"  inputtype="string"  />
							</td>
							<td colspan="2">
							 <table  cellpadding="1" cellspacing="0" class="detail3">
							   <td width="9%" align="right" class="detail_label">
								城市类型：
							   </td>
							   <td width="13%" class="detail_content" >
								 <select label="城市类型" name="CITY_TYPE" id="CITY_TYPE" json="CITY_TYPE" value="${rst.CITY_TYPE}" 
								  > </select><span class="validate">*</span>
								</td>
								<td width="10%" align="right" class="detail_label">
									城市级别：
								</td>
								<td width="10%" class="detail_content" >
								<input json="CITY_LEVEL" name="CITY_LEVEL" id="CITY_LEVEL" type="text" maxlength="30" size="7"
										label="城市级别" value="${rst.CITY_LEVEL}" mustinput="true"  autocheck="true"  inputtype="string"  />
								</td>
							 </table>
							</td>
							
					    </tr> 
					    <tr>
							<td width="15%" align="right" class="detail_label">
								意向加盟商开户名称：
							</td>
							<td width="20%" class="detail_content">
							    <input json="INTE_CHANN_NAME" name="INTE_CHANN_NAME" id="INTE_CHANN_NAME" type="text" maxlength="100" 
									label="意向加盟商开户名称" value="${rst.INTE_CHANN_NAME}"  autocheck="true"  inputtype="string"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								意向加盟商姓名：
							</td>
							<td width="20%" class="detail_content">
							    <input json="INTE_CUSTOMER_NAME" name="INTE_CUSTOMER_NAME" id="INTE_CUSTOMER_NAME" type="text" maxlength="50" 
									label="意向加盟商姓名" value="${rst.INTE_CUSTOMER_NAME}" mustinput="true"  autocheck="true"  inputtype="string"  />
							</td>
							<td width="15%" align="right" class="detail_label">
								联系电话：
							</td>
							<td width="20%" class="detail_content" >
							<input json="TEL" name="TEL" id="TEL" type="text" maxlength="30"  label="联系电话" value="${rst.TEL}"  mustinput="true" autocheck="true"  inputtype="string"  />
							</td>
					    </tr> 
					    <tr>
							<td width="15%" align="right" class="detail_label"> 性别：</td>
							<td width="20%" class="detail_content">
								<input id="SEX" name="SEX" json="SEX" type="radio" value="男"  />男
								<input id="SEX" name="SEX" json="SEX" type="radio" value="女" />女
								<script type="text/javascript">
								 var sex = '${rst.SEX}';
								 if("男" == sex){
									 $("#jsontb input[json='SEX'][value='男']").attr("checked","checked");
								 }else{
									 $("#jsontb input[json='SEX'][value='女']").attr("checked","checked");
								 }
								</script>
							</td>
							<td width="15%" align="right" class="detail_label"> 年龄： </td>
							<td width="20%" class="detail_content">
							    <input json="AGE" name="AGE" id="AGE" type="text" maxlength="10" label="年龄" value="${rst.AGE}"  mustinput="true" autocheck="true"  inputtype="int"  />
							</td>
							<td width="15%" align="right" class="detail_label"> 学历： </td>
							<td width="20%" class="detail_content" >
							  <input json="EDUCATION" name="EDUCATION" id="EDUCATION" type="text" maxlength="50"  label="学历" value="${rst.EDUCATION}"  mustinput="true" autocheck="true"  inputtype="string"  />
							</td>
					    </tr> 
					    <tr>
							<td width="15%" align="right" class="detail_label"> 地址：</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input json="ADDRESS" name="ADDRESS" id=ADDRESS type="ADDRESS" maxlength="250" style="width: 569px;"  label="地址" value="${rst.ADDRESS}"  mustinput="true" autocheck="true"  inputtype="string"/>
							</td>
					    </tr> 					    
					    
						<tr>
						  <th colspan="4" align="center">
						         现有卖场信息调查
						  </th>
						  <td style="text-align: right;" colspan="2">
						      <input onclick="addRow()"  type="button" name="Submit2" class="btn"  value="添加" />
                              <input onclick="delRow()"  type="button" name="Submit3" class="btn"  value="删除" />
						  </td>
						</tr>
						
						<tr>
						   <td align="left" colspan="6"> 
						     <div id="tabdiv" class="meun">
						       <table id="myTable"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="left">
						             <tr class="detail_label" id="tr1">
						              <th width="5%" align="center" id="th1">
						                 <input type="checkbox" name="checkAll"   onclick="chkAll(this)"/>
						              </th>
						              <th width="19%" align="center">
						                   名称
						              </th>
						              <th width="19%" align="center">
						                   地址
						              </th>
						              <th width="19%" align="center">
						                   面积(平)
						              </th>
						              <th width="19%" align="center">
						                   档次排名
						              </th>
						              <th width="19%" align="center">
						                   进驻主竞品
						              </th>
						            </tr>
						              <tbody id="tbody" class="detail_content">
						          
			                          </tbody>
							  </table>
							 </div>
						    </td>
						</tr>
 
						<tr>
						  <th colspan="4" align="center">
						        近期新开卖场信息调查
						  </th>
						  <td colspan="2" align="right" >
			                  <input onclick="addRow1()"  type="button" name="Submit2" class="btn" value="添加" />
                              <input onclick="delRow('myTable1')"  type="button" name="Submit3"  class="btn" value="删除" />
			              </td>
						</tr>
						
							<tr>
						   <td align="center" colspan="6"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable1"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="center">
						            <tr align="left" class="detail_label">
						              <th width="5%" align="center">
						                 <input type="checkbox" name="checkAll"   onclick="chkAll(this)"/>
						              </th>
						              <th width="19%" align="center">
						                   名称
						              </th>
						              <th width="19%" align="center">
						                   地址
						              </th>
						              <th width="19%" align="center">
						                   面积(平)
						              </th>
						              <th width="19%" align="center">
						                   档次排名
						              </th>
						              <th width="19%" align="center">
						                   开始时间
						              </th>
						            </tr>
						            <tbody id="tbody1" class="detail_content">
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label"> 发货工厂：</td>
							<td width="20%" class="detail_content">
							    <input json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" id="SHIP_POINT_NAME" type="text" maxlength="50"  label="发货工厂" value="${rst.SHIP_POINT_NAME}"  autocheck="true"  inputtype="string"/>
							    <input id="SHIP_POINT_ID"   json="SHIP_POINT_ID" name="SHIP_POINT_ID" type="hidden"  value="${rst.SHIP_POINT_ID}" />
								<img align="absmiddle" name="selZONE" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif" 
									onClick="selCommon('BS_22', false, 'SHIP_POINT_ID','SHIP_POINT_ID', 'forms[0]','SHIP_POINT_NAME', 'SHIP_POINT_NAME', 'selectPointParams')">
							</td>
							<td width="20%" align="right" class="detail_label"> 是否区域服务中心下辖加盟商： </td>
							<td width="20%" class="detail_content">
							    <input id="" name="AREA_SER_FLG" json="AREA_SER_FLG" type="radio" value="1"   />是
								<input id="" name="AREA_SER_FLG" json="AREA_SER_FLG" type="radio" value="0"   />否
								<script type="text/javascript">
								 var AREA_SER_FLG = '${rst.AREA_SER_FLG}';
								 if("1" == AREA_SER_FLG){
									 $("#jsontb input[json='AREA_SER_FLG'][value='1']").attr("checked","checked");
								 }else{
									 $("#jsontb input[json='AREA_SER_FLG'][value='0']").attr("checked","checked");
								 }
								</script>
							</td>
							<td width="15%" align="right" class="detail_label"> 区域服务中心： </td>
							<td width="20%" class="detail_content" >
							  <input type="hidden" id="AREA_SER_ID" json="AREA_SER_ID" name="AREA_SER_ID" value="${rst.AREA_SER_ID}" />
							  <input type="hidden" id="AREA_SER_NO" json="AREA_SER_NO" name="AREA_SER_NO" value="${rst.AREA_SER_NO}" />
							  <input json="AREA_SER_NAME" name="AREA_SER_NAME" id="AREA_SER_NAME" type="text" maxlength="100"  
							       label="区域服务中心" value="${rst.AREA_SER_NAME}"   autocheck="true"  inputtype="string"  />
						      <img align="absmiddle" name="selJGXX" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[0]','AREA_SER_ID,AREA_SER_NO,AREA_SER_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectChannParams')">
												
							</td>
					    </tr> 
					    
					 	<tr>
							<td width="15%" align="right" class="detail_label" colspan="2"> 相关附件（与意向客户合影，客户名片，商场等）：</td>
							<td width="20%" class="detail_content" colspan="4">
							    <input json="DOC_PATH" name="DOC_PATH" id="DOC_PATH" type="hidden" 
							      label="相关附件（与意向客户合影，客户名片，商场等）" value="${rst.DOC_PATH}" />
							</td>
					    </tr>    
						<tr>
						  <th colspan="4" align="center">
						        第一次拜访
						  </th>
						  <td colspan="2" align="right" >
                              <input onclick="addRow2()"  type="button" name="Submit2" class="btn" value="添加" />
                              <input onclick="delRow('myTable2')"  type="button" name="Submit3" class="btn"  value="删除" />
			              </td>
						</tr>
						
						<tr>
						   <td align="center" colspan="6"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable2"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="center"><%--
						        <c:if test="${empty rst.INTE_CHANN_ID}">
						            <tr align="left" class="detail_label">
						              <th width="5%" align="center">
						                 <input type="checkbox" name="checkAll"   onclick="chkAll(this)" />
						              </th>
						              <th width="19%" align="center">   第一次拜访方式  </th>
						              <th width="19%" align="center">
						                  <select id="VISIT_TYPE" name="VISIT_TYPE" style="width: 155px;" json="VISIT_TYPE" label="第一次拜访方式"></select>  
						              </th>
						              <th width="19%" align="center">    第一次拜访/跟进情况说明  </th>
						              <th width="19%" align="center" colspan="3">  <input type="text" name="VISIT_REMARK" json="VISIT_REMARK" value=""/>  </th>
						            </tr>
						      </c:if>      
						            --%><tbody id="tbody2" class="detail_content">
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr> 
						<tr>
						  <td width="15%" align="right" class="detail_label"> 意向经营品牌： </td>
						  <td width="20%" class="detail_content" >
						    <input type="text" name="BUSS_SCOPE" id="BUSS_SCOPE" json="BUSS_SCOPE" autocheck="true" maxlength="200" value="${rst.BUSS_SCOPE}"/>
						    <img align="absmiddle" name="selJGXX" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_142', true, 'BUSS_SCOPE', 'BRNAME', 'forms[0]','BUSS_SCOPE', 'BRNAME', 'selectBrandParams')">
										
						  </td>
						  <td width="15%" align="right" class="detail_label"> 意向进驻卖场： </td>
						  <td width="20%" class="detail_content" >
						    <input type="text" name="STORE_NAME" id="STORE_NAME" json="STORE_NAME" autocheck="true" maxlength="200" value="${rst.STORE_NAME}"/>
						  </td>
						  <td width="15%" align="right" class="detail_label"> 计划开店版本： </td>
						  <td width="20%" class="detail_content" >
						    <select  name="INTE_STORE_VSION" id="INTE_STORE_VSION" json="INTE_STORE_VSION" 
						   value="${rst.INTE_STORE_VSION}"></select>
						  </td>
						</tr> 
						<tr>
						  <td width="15%" align="right" class="detail_label"> 计划开店面积： </td>
						  <td width="20%" class="detail_content" >
						    <input type="text" name="INTE_STORE_AREA" id="INTE_STORE_AREA" json="INTE_STORE_AREA" autocheck="true"  inputtype="int"  label="计划开店面积" value="${rst.INTE_STORE_AREA}"/>
						  </td>
						  <td width="15%" align="right" class="detail_label"> 计划开店时间： </td>
						  <td width="20%" class="detail_content" >
						    <input type="text" name="INTE_STORE_DATE" id="INTE_STORE_DATE" json="INTE_STORE_DATE" autocheck="true" maxlength="200" value="${rst.INTE_STORE_DATE}" onclick="SelectDate();" readonly="readonly"/>
						     <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(INTE_STORE_DATE);">
						  </td>
						  <td width="15%" align="right" class="detail_label">  </td>
						  <td width="20%" class="detail_content" ></td>
						 
						</tr>
						<tr>
						  <td width="15%" align="right" class="detail_label"> 是否签署合同： </td>
						  <td width="20%" class="detail_content" >
						    <input type="checkbox" name="IS_CONTRACT" id="IS_CONTRACT" json="IS_CONTRACT" autocheck="true"  value="${rst.IS_CONTRACT}"  />
						    <script type="text/javascript">
						       var IS_CONTRACT = $("#IS_CONTRACT").val();
						       if(1 == IS_CONTRACT){
						    	   $("#IS_CONTRACT").attr("checked","checked");
						       }
						    </script>
						  </td>
						  <td width="15%" align="right" class="detail_label"> 是否缴纳意向金： </td>
						  <td width="20%" class="detail_content" >
						    <input type="checkbox" name="IS_PAY_INTEAMOUNT" id="IS_PAY_INTEAMOUNT" json="IS_PAY_INTEAMOUNT"  value="${rst.IS_PAY_INTEAMOUNT}"/>
						     <script type="text/javascript">
						       var IS_PAY_INTEAMOUNT = $("#IS_PAY_INTEAMOUNT").val();
						       if(1 == IS_PAY_INTEAMOUNT){
						    	   $("#IS_PAY_INTEAMOUNT").attr("checked","checked");
						       }
						    </script>
						  </td>
						  <td width="15%" align="right" class="detail_label"> 是否已首次发货： </td>
						  <td width="20%" class="detail_content" >
						    <input type="checkbox" name="IS_FIRST_SENTPDT" id="IS_FIRST_SENTPDT" json="IS_FIRST_SENTPDT"  value="${rst.IS_FIRST_SENTPDT}"/>
						     <script type="text/javascript">
						       var IS_FIRST_SENTPDT = $("#IS_FIRST_SENTPDT").val();
						       if(1 == IS_FIRST_SENTPDT){
						    	   $("#IS_FIRST_SENTPDT").attr("checked","checked");
						       }
						    </script>
						  </td>
						  
						</tr>
						
						
						 <tr>
							<td width="15%" align="right" class="detail_label"> 加盟商异议： </td>
							<td width="20%" class="detail_content" >
								 <textarea json="CHANN_REMARK" name="CHANN_REMARK" id="CHANN_REMARK" label="加盟商异议" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="250">${rst.CHANN_REMARK}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								解决方案：
							</td>
							<td width="20%" class="detail_content" >
								 <textarea json="SOLUTION" name="SOLUTION" id="SOLUTION" label="解决方案" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="250">${rst.SOLUTION}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="SUPPORT_DEMAND" name="SUPPORT_DEMAND" id="SUPPORT_DEMAND" label="支持需求" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.SUPPORT_DEMAND}</textarea>
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="COMPETITION_INFO" name="COMPETITION_INFO" id="COMPETITION_INFO" label="竞品信息" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="200">${rst.COMPETITION_INFO}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								现状及优势：
							</td>
							<td width="20%" class="detail_content" colspan="3">
								 <textarea json="ADVANTAGES" name="ADVANTAGES" id="ADVANTAGES" label="现状及优势"
								           rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="300">${rst.ADVANTAGES}</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
						
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
     //  SelDictShow("VISIT_TYPE","BS_154","${rst.VISIT_TYPE}","");
	   uploadFile('DOC_PATH', 'SAMPLE_DIR', true,true,true);
	   SelDictShow("CITY_TYPE", "BS_101", '${rst.CITY_TYPE}', "");
	   SelDictShow("INTE_STORE_VSION","BS_134","${rst.INTE_STORE_VSION}","");
	</script>
   </div>
</body>



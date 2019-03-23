<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/erp/sale/budgetquota/Budgetquota_Batch_Edit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>批量编辑</title>
	</head>
<body class="bodycss1">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
				</td>
			</tr>
		</table>
	</div>


<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<!--占位用行，以免显示数据被浮动层挡住-->
		<td height="20px"> &nbsp; </td>
	</tr>
	<tr>
		<td>
			<form name="mainForm" id="mainForm">
				<input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1">
				<input type="hidden" name=selectDeptParams id="selectDeptParams" value=" BMZT='启用' and JGXXID='00'">
				<input type="hidden" name=selectAreaParams id="selectAreaParams" value=" STATE='启用' and DEL_FLAG=0 ">
				
				<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
					<tr>
						<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
							<tr>												
									<td width="15%" align="right" class="detail_label">
										预算部门编号：
									</td>
									<td width="35%" class="detail_content">
									    <input type="hidden" name="BUDGET_DEPT_ID" id="BUDGET_DEPT_ID" json="BUDGET_DEPT_ID" value="${rst.BUDGET_DEPT_ID}"/>
										<input id="BUDGET_DEPT_NO" json="BUDGET_DEPT_NO" name="BUDGET_DEPT_NO" type="text" 
											autocheck="true" label="预算部门编号" inputtype="string" valueType="chinese:false"
											mustinput="true" maxlength="50" value="${rst.BUDGET_DEPT_NO}" readonly />
									   <img align="absmiddle" name="selJGXX" style="cursor: hand"
										 src="${ctx}/styles/${theme}/images/plus/select.gif"
										 onClick="selCommon('1', false, 'BUDGET_DEPT_ID', 'BMXXID', 'forms[0]','BUDGET_DEPT_NO,BUDGET_DEPT_NAME', 'BMBH,BMMC', 'selectDeptParams')">
									</td>
									<td width="15%" align="right" class="detail_label" nowrap>
										预算部门名称：
									</td>
									<td width="35%" class="detail_content">
										<input json="BUDGET_DEPT_NAME" id="BUDGET_DEPT_NAME" name="BUDGET_DEPT_NAME" type="text" autocheck="true"
											label="预算部门名称" inputtype="string" mustinput="true" maxlength="100" readonly
											value="${rst.BUDGET_DEPT_NAME}" />
									</td>
								</tr>
								<tr>
								<td width="15%" align="right" class="detail_label" nowrap>
									关联区域编号：
								</td>
								<td width="35%" class="detail_content">
									<input id="RELATE_AREA_ID" json="RELATE_AREA_ID" name="RELATE_AREA_ID" type="hidden"
										  value="${rst.RELATE_AREA_ID}">
										
									<input id="RELATE_AREA_NO" json="RELATE_AREA_NO"  name="RELATE_AREA_NO" type="text" inputtype="string"
										label="关联区域编号"  autocheck="true" maxlength="32"
										value="${rst.RELATE_AREA_NO}" READONLY>
										
									<img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_18', false, 'RELATE_AREA_ID', 'AREA_ID', 'forms[0]','RELATE_AREA_NO,RELATE_AREA_NAME', 'AREA_NO,AREA_NAME', 'selectAreaParams')">
										                                                                                                                   <!--  selectParams hidden  -->
								</td>
								<td width="15%" align="right" class="detail_label">
									关联区域名称：
								</td>
								<td width="35%" class="detail_content">
									<input id="RELATE_AREA_NAME" json="RELATE_AREA_NAME" name="RELATE_AREA_NAME" type="text" inputtype="string"
										  label="关联区域名称" maxlength="50"
										value="${rst.RELATE_AREA_NAME}" READONLY>
								</td>
							</tr>
								<tr>												
									<td width="15%" align="right" class="detail_label">
										预算科目编号：
									</td>
									<td width="35%" class="detail_content">
									    <input type="hidden" name="BUDGET_ITEM_ID" id="BUDGET_ITEM_ID" json="BUDGET_ITEM_ID" value="${rst.BUDGET_ITEM_ID}"/>
										<input id="BUDGET_ITEM_NO" json="BUDGET_ITEM_NO" name="BUDGET_ITEM_NO" type="text" 
											autocheck="true" label="预算科目编号" inputtype="string" valueType="chinese:false"
											mustinput="true" maxlength="50" value="${rst.BUDGET_ITEM_NO}" readonly />
									   <img align="absmiddle" name="selJGXX" style="cursor: hand"
										 src="${ctx}/styles/${theme}/images/plus/select.gif"
										 onClick="selCommon('BS_155', false, 'BUDGET_ITEM_ID', 'BUDGET_ITEM_ID', 'forms[0]','BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,PAR_BUDGET_ITEM_ID,PAR_BUDGET_ITEM_NO,PAR_BUDGET_ITEM_NAME', 'BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,PAR_BUDGET_ITEM_ID,PAR_BUDGET_ITEM_NO,PAR_BUDGET_ITEM_NAME', 'selectParams');changeBatchEdit();">
									</td>
									<td width="15%" align="right" class="detail_label" nowrap>
										预算科目名称：
									</td>
									<td width="35%" class="detail_content">
										<input json="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" type="text" autocheck="true"
											label="预算科目名称" inputtype="string" mustinput="true" maxlength="100" readonly
											value="${rst.BUDGET_ITEM_NAME}" />
									</td>
								</tr>
								<tr>	
								    <td width="15%" align="right" class="detail_label" nowrap>
										上级预算科目编号：
									</td>
									<td width="35%" class="detail_content">
									    <input type="hidden" name="PAR_BUDGET_ITEM_ID" id="PAR_BUDGET_ITEM_ID" json="PAR_BUDGET_ITEM_ID" value="${rst.PAR_BUDGET_ITEM_ID}"/>
										<input json="PAR_BUDGET_ITEM_NO" name="PAR_BUDGET_ITEM_NO" id="PAR_BUDGET_ITEM_NO" type="text" autocheck="true" readonly 
											label="上级预算科目编号" inputtype="string" maxlength="50" value="${rst.PAR_BUDGET_ITEM_NO}">
											
											</td>											
											<td width="15%" align="right" class="detail_label">
												上级预算科目名称：
											</td>
											<td width="35%" class="detail_content">
												<input id="PAR_BUDGET_ITEM_NAME" json="PAR_BUDGET_ITEM_NAME"  readonly
												 name="PAR_BUDGET_ITEM_NAME"  label="上级预算科目名称" inputtype="string" value="${rst.PAR_BUDGET_ITEM_NAME}" />
											</td>
										</tr>
										<tr>												
											<td width="15%" align="right" class="detail_label">
												预算科目类型：
											</td>
											<td width="35%" class="detail_content">
											<span onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();">
											   <select id="BUDGET_ITEM_TYPE" json="BUDGET_ITEM_TYPE" style="width:155px;background-color: rgb(238, 238, 255);" label="预算科目类型" readonly  ></select>
		                                          <SPAN class=validate>&nbsp;*</SPAN> 
		                                          </span>
											</td>
											 <td width="15%" align="right" class="detail_label" nowrap>
												年份：
											 </td>
											 <td width="35%" class="detail_content">
												<select json="YEAR" name="YEAR" id="YEAR" style="width:155px;"  label="年份"  ></select>
												 <SPAN class="validate">&nbsp;*</SPAN> 
											 </td>	
										</tr>
										
										<tr name="year_tr">	
										    <td width="15%" align="right" class="detail_label"> 预算额度：</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_YEAR" inputtype="float" valuetype="8,2" autocheck="true"  
											  label="预算额度"  value="${rst.BUDGET_QUOTA}" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap> </td>
											<td width="35%" class="detail_content"></td>
										</tr>
										<script>
										  var BUDGET_ITEM_TYPE = "${rst.BUDGET_ITEM_TYPE}";
										  if("按年" == BUDGET_ITEM_TYPE){
											  var BUDGET_QUOTA = "${rst.QUARTER_BATCH}";
											  var arry = BUDGET_QUOTA.split(",");
											  var arr = arry[0].split("-");
											  var quarter = arr[0];//年份
											  var quarter_v = arr[3];
												  
											  $("#jsontb input[name='BUDGET_QUOTA_YEAR']").each(function(){
													 $(this).val(quarter_v);
											  });
										  }
										  
																				
									    </script>
										<input type="hidden" id="BUDGET_QUOTA" json="BUDGET_QUOTA" id="BUDGET_QUOTA"   value="${rst.BUDGET_QUOTA}" />
										<!-- ======================= 季度开始==================== -->
										<tr name="quarter_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												一季度：
											</td>
											<td width="35%" class="detail_content">
											  <input   name="BUDGET_QUOTA_QUARTER" inputtype="float" valuetype="8,2" autocheck="true" tagtext="一季度" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												二季度：
											</td>
											<td width="35%" class="detail_content">
											  <input   name="BUDGET_QUOTA_QUARTER" inputtype="float" valuetype="8,2" autocheck="true" tagtext="二季度" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr>
										<tr name="quarter_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												三季度：
											</td>
											<td width="35%" class="detail_content">
											  <input   name="BUDGET_QUOTA_QUARTER" inputtype="float" valuetype="8,2" autocheck="true" tagtext="三季度" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												四季度：
											</td>
											<td width="35%" class="detail_content">
											  <input   name="BUDGET_QUOTA_QUARTER" inputtype="float" valuetype="8,2" autocheck="true" tagtext="四季度" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr> 
										<!-- ======================= 季度结束==================== --> 
										 <script>
										  var BUDGET_ITEM_TYPE = "${rst.BUDGET_ITEM_TYPE}";
										  if("按季" == BUDGET_ITEM_TYPE){
											  var BUDGET_QUOTA = "${rst.QUARTER_BATCH}";
											  var arry = BUDGET_QUOTA.split(",");
											  for(var i=0;i<arry.length;i++){
												  var arr = arry[i].split("-");
												  var quarter = arr[1];//季度
												  var quarter_v = arr[3];
												  var BUDGET_QUOTA_ID = arr[4];//表ID
												  $("#jsontb input[name='BUDGET_QUOTA_QUARTER']").each(function(){
													    var tagtext = $(this).attr("tagtext");
													    if(tagtext == quarter){
													    	$(this).val(quarter_v);
													    	$(this).attr("recardid",BUDGET_QUOTA_ID);
													    }
											      });
											  }
											  
										  }
										 
																				
									    </script>
										<!-- ======================= 月份开始==================== -->
										 <tr name="month_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												1月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="01"  recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												2月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""    name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="02" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr>

                                        <tr name="month_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												3月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""    name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="03" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												4月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""    name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="04" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr>
                                        <tr name="month_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												5月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="05" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												6月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="06" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr> 
										<tr name="month_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												7月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true" tagtext="07" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												8月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="08" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr> 
										<tr name="month_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												9月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="09" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												10月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""   name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="10" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr> 
										<tr name="month_tr">	
										    <td width="15%" align="right" class="detail_label" nowrap>
												11月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""    name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="11" recardid=""
											  label="预算额度"  value="" />
											</td>
											<td width="15%" align="right" class="detail_label" nowrap>
												12月份：
											</td>
											<td width="35%" class="detail_content">
											  <input id=""    name="BUDGET_QUOTA_MONTH" inputtype="float" valuetype="8,2" autocheck="true"  tagtext="12" recardid=""
											  label="预算额度"  value="" />
											</td>
										</tr> 
										<script>
										  var BUDGET_ITEM_TYPE = "${rst.BUDGET_ITEM_TYPE}";
										  if("按月" == BUDGET_ITEM_TYPE){
											  var BUDGET_QUOTA = "${rst.QUARTER_BATCH}";
											  var arry = BUDGET_QUOTA.split(",");
											  for(var i=0;i<arry.length;i++){
												  var arr = arry[i].split("-");
												  var quarter = arr[2];//月份
												  var quarter_v = arr[3];
												  var BUDGET_QUOTA_ID = arr[4];//表ID
												  $("#jsontb input[name='BUDGET_QUOTA_MONTH']").each(function(){
													    var tagtext = $(this).attr("tagtext");
													    if(tagtext == quarter){
													    	$(this).val(quarter_v);
													    	$(this).attr("recardid",BUDGET_QUOTA_ID);
													    }
											      });
											  }
										  }
										  
																				
									    </script>
									    
										<tr></tr>
									 </table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
<script language="JavaScript">
   hideObject();
   changeBatchEdit("${rst.BUDGET_ITEM_TYPE}");
   SelDictShow("BUDGET_ITEM_TYPE", "BS_145", '${rst.BUDGET_ITEM_TYPE}', "");
	SelDictShow("YEAR", "89", '${rst.YEAR}', "");
	SelDictShow("MONTH", "168", '${rst.MONTH}', "");
	SelDictShow("QUARTER", "BS_148", '${rst.QUARTER}', "");
</script>
</html>

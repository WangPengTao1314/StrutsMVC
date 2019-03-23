<!--  
/**
 *@module 渠道管理-分销商管理
 *@func   分销渠道信息登记
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
		src="${ctx}/pages/drp/distributer/distributerreqt/DistributerReqT_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>分销渠道信息登记</title>
	 
</head>
<body class="bodycss1">
<div style="height: 100">
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
		<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG='0' ">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="15%" align="right" class="detail_label">
								申请单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="DISTRIBUTOR_REQ_ID" id="DISTRIBUTOR_REQ_ID"
									name="DISTRIBUTOR_REQ_ID" type="hidden"
									value="${rst.DISTRIBUTOR_REQ_ID}"   />
									
							    <input json="DISTRIBUTOR_REQ_NO" name="DISTRIBUTOR_REQ_NO" id="DISTRIBUTOR_REQ_NO"
							        type="text" autocheck="true" label="申请单号" inputtype="string" mustinput="true"
									maxlength="32" 
									<c:if test="${not empty rst.DISTRIBUTOR_REQ_NO}">value="${rst.DISTRIBUTOR_REQ_NO}"</c:if>
									<c:if test="${empty rst.DISTRIBUTOR_REQ_NO}">value="自动生成"</c:if>
									READONLY/>
							</td>
							<td width="15%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="35%" class="detail_content">
								<input json="REQ_NAME" name="REQ_NAME" id="REQ_NAME" type="text"  label="申请人"									 
									 <c:if test="${not empty rst.DISTRIBUTOR_REQ_NO}">value="${rst.REQ_NAME}"</c:if>
									<c:if test="${empty rst.DISTRIBUTOR_REQ_NO}">value="自动生成"</c:if>
									readonly/>
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								申请时间:
							</td>
							<td width="35%" class="detail_content">
							      <input json="BEG_SBUSS_DATE" name="BEG_SBUSS_DATE" id="BEG_SBUSS_DATE" type="text" label="申请时间" 									 
									<c:if test="${not empty rst.DISTRIBUTOR_REQ_NO}">value="${rst.BEG_SBUSS_DATE}"</c:if>
									<c:if test="${empty rst.DISTRIBUTOR_REQ_NO}">value="自动生成"</c:if>
									readonly/>
								  
							</td>
							<td width="15%" align="right" class="detail_label">
								分销渠道名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="DISTRIBUTOR_NAME" name="DISTRIBUTOR_NAME" id="DISTRIBUTOR_NAME" type="text" maxlength="100"
									label="分销渠道名称" value="${rst.DISTRIBUTOR_NAME}"  autocheck="true" mustinput="true" inputtype="string"/><font color='red'>&nbsp;输入:&nbsp;城市+商场名+分销商姓名</font>
							</td>
						</tr>

						<tr>						    
							<td width="15%" align="right" class="detail_label">
								联系电话：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" id="TEL" name="TEL" json="TEL"  autocheck="true"
								 value="${rst.TEL}"   label="联系电话"  maxlength="30"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								分销类型：
							</td>
							<td width="35%" class="detail_content">
								<select json="DISTRIBUTOR_TYPE" id="DISTRIBUTOR_TYPE" name="DISTRIBUTOR_TYPE" style="width:155px;" 
									inputtype="string" autocheck="true" mustinput="true"  label="分销类型" onchange="showTr(this);">
								</select>
								
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								所属加盟商编号：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}">
								<input type="hidden" id="AREA_ID" name="AREA_ID" json="AREA_ID"  value="${rst.AREA_ID}"/>
								<input type="hidden" id="AREA_NO" name="AREA_NO" json="AREA_NO"  value="${rst.AREA_NO}"/>
								<input type="hidden" id="AREA_NAME" name="AREA_NAME" json="AREA_NAME"  value="${rst.AREA_NAME}"/>
								<input type="hidden" id="ZONE_ID" name="ZONE_ID" json="ZONE_ID"  value="${rst.ZONE_ID}"/>
																					
								<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
											seltarget="selJGXX" autocheck="true" mustinput="true" maxlength="32" label="所属加盟商编号"
											value="${rst.CHANN_NO}" READONLY/>
								<!--  
								<img align="absmiddle" name="selJGXX" style="cursor: hand"
											src="${ctx}/styles/${theme}/images/plus/select.gif"       
											onClick="selCommon('BS_187', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
																'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,PROV,CITY,COUNTY,AREA_ID,AREA_NO,AREA_NAME,ZONE_ID', 
																'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,PROV,CITY,COUNTY,AREA_ID,AREA_NO,AREA_NAME,ZONE_ID', 
																'selectChann')">
								-->
							</td>
							<td width="15%" align="right" class="detail_label">
								所属加盟商名称：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" label="所属加盟商名称"  value="${rst.CHANN_NAME}" readonly />													
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								战区：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="AREA_NAME_P" id="AREA_NAME_P" name="AREA_NAME_P" label="战区"  value="${rst.AREA_NAME_P}" readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								营销经理:
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" label="区域经理ID" json="AREA_MANAGE_ID"  value="${rst.AREA_MANAGE_ID}"/>
								<input type="hidden" id="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" label="区域经理电话" json="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}"/>
								<input type="text" id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" label="营销经理"  json="AREA_MANAGE_NAME"  value="${rst.AREA_MANAGE_NAME}"   readonly/>													
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								省份：
							</td>
							<td width="35%" class="detail_content">
								<input id="PROV" json="PROV" name="PROV" type="text" autocheck="true"
										label="省份" inputtype="string" value="${rst.PROV }" READONLY>
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								城市：
							</td>
							<td width="35%" class="detail_content">
								<input id="CITY" json="CITY" name="CITY" type="text"   autocheck="true"
										inputtype="string"	value="${rst.CITY }" READONLY>
							</td>
												
						</tr>
						<tr>	
							<td width="15%" align="right" class="detail_label" nowrap>
								区县：
							</td>
							<td width="35%" class="detail_content">
								<input id="COUNTY" json="COUNTY" name="COUNTY" type="text"   autocheck="true"
										label="区县" inputtype="string"
										value="${rst.COUNTY }" READONLY>
							</td>											
							<td width="15%" align="right" class="detail_label">
								联系人：
							</td>
							<td width="35%" class="detail_content">
									<input json="PERSON_CON" name="PERSON_CON" type="text" 
										autocheck="true" label="联系人" inputtype="String"
										maxlength="30" value="${rst.PERSON_CON }">
							</td>												
						</tr>											
						<tr>
							<td width="15%" align="right" class="detail_label">
								联系人电话：
							</td>
							<td width="35%" class="detail_content">
								<input name="MOBILE" type="text" json="MOBILE" id="MOBILE" label="联系人电话"
										autocheck="true" inputtype="string" maxlength="30"
										value="${rst.MOBILE}">
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								传真：
							</td>
							<td width="35%" class="detail_content">
								<input name="TAX" type="text" autocheck="true" label="传真"
										id="TAX" inputtype="string" maxlength="30" json="TAX"
										value="${rst.TAX}">
							</td>												
						</tr>											
						<tr>
							<td width="15%" align="right" class="detail_label" nowrap>
								邮箱：
							</td>
							<td width="35%" class="detail_content">
								<input id="EMAIL" json="EMAIL" name="EMAIL" type="text"
										autocheck="true" label="邮箱" inputtype="string"
										maxlength="30" value="${rst.EMAIL }">
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								商场名称：
							</td>
							<td width="35%" class="detail_content">													
								<input id="SALE_STORE_NAME" json="SALE_STORE_NAME" name="SALE_STORE_NAME"
										type="text" inputtype="string" autocheck="true" maxlength="100"  label="商场名称"
										value="${rst.SALE_STORE_NAME}"  >													
							</td>												
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								商场地址：
							</td>
							<td width="35%" class="detail_content">
								<input id="SALE_STORE_LOCAL" json="SALE_STORE_LOCAL" name="SALE_STORE_LOCAL"
										type="text" inputtype="string" autocheck="true" maxlength="100"  label="商场地址"
										value="${rst.SALE_STORE_LOCAL}"  >
							</td>
							<td width="15%" align="right" class="detail_label" nowrap>
								经营品牌：
							</td>
							<td width="35%" class="detail_content">
								<input id="BUSS_BRAND" json="BUSS_BRAND" name="BUSS_BRAND"
									type="text" inputtype="string" autocheck="true" maxlength="100"  label="经营品牌"
									value="${rst.BUSS_BRAND}"  >
							</td>												
						</tr>											
						<tr>
							<td width="15%" align="right" class="detail_label">
								主营品类：
							</td>
							<td width="35%" class="detail_content">
								<input id="BUSS_CLASS" name="BUSS_CLASS" type="text" autocheck="true" 
										inputtype="string" label="主营品类" json="BUSS_CLASS" 
										value="${rst.BUSS_CLASS }" >
							</td>
							<td width="15%" align="right" class="detail_label">
								合作时间：
							</td>
							<td width="35%" class="detail_content">
								<input type="text" json="COOPER_DATE" id="COOPER_DATE" name="COOPER_DATE" autocheck="true" inputtype="string" label="合作时间"  value="${rst.COOPER_DATE}"  onclick="SelectDate();" readonly />
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(COOPER_DATE);">
							</td>												
						</tr>
						<tr id="tr_cpatt" <c:if test="${nFlag == false}"> style="display:none" </c:if> >
								<td width="15%" align="right" class="detail_label">
									合作方案：
								</td>
								<td width="35%" class="detail_content" colspan="3">
									<input id="COOPER_PLAN_ATT" name="COOPER_PLAN_ATT" type="text" autocheck="true" 
											inputtype="string" label="合作方案" json="COOPER_PLAN_ATT" 
											value="${rst.COOPER_PLAN_ATT }" >
								</td>																		
						</tr>						
						<tr id="tr_att" <c:if test="${nFlag == true}"> style="display:none" </c:if> >
								<td width="15%" align="right" class="detail_label" >
									附件：
								</td>
								<td width="35%" class="detail_content" colspan="3">
									<table id="attTable" width="100%">
										<tr>
											<td>
												<font color='red'>1、&lt;&lt;2015年喜临门家具分销协议&gt;&gt;；2、分销床垫陈列照片（每款一张）</font>											
											</td>
										</tr>
										<tr>
											<td>											
												<input json="ATT" name="ATT" id="ATT" type="text"
															label="附件" inputtype="string"
															value="${rst.ATT }">
											</td>										
										</tr>
									</table>
								</td>														
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
   </div>
   
   <form action="openterminal.shtml?action=toFrames" name="pageForm" id="pageForm" method="post"></form>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
    //$("#image").hide();
    SelDictShow("DISTRIBUTOR_TYPE", "201", '${rst.DISTRIBUTOR_TYPE}', "");
    
	uploadFile('COOPER_PLAN_ATT', 'SAMPLE_DIR', true);
	uploadFile('ATT', 'SAMPLE_DIR', true);
</script> 



<!--  
/**
 *@module 渠道管理-终端管理
 *@func   门店变更申请单维护
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
	<script type=text/javascript
		src="${ctx}/pages/drp/oamg/terminalchange/TerminalChange_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>门店变更申请单</title>
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
			value="CHANN_ID in ${CHANN_ID} and STATE in('启用') and DEL_FLAG='0' ">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="17%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="TERM_ID" name="TERM_ID" id="TERM_ID" type="hidden" value="${rst.TERM_ID}" />
								<input json="TERM_NO" name="TERM_NO" id="TERM_NO" type="text" label="终端编号"
								    size="35" inputtype="string" autocheck="true"   mustinput="true"  value="${rst.TERM_NO}" readonly/>
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_134', false, 'TERM_NO', 'TERM_NO', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME,TERM_ABBR,TERM_TYPE,TERM_LVL,CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P,BUSS_NATRUE,PLAY_BANK_NAME,PLAY_BANK_ACCT,LOGIC_TYPE,AREA_ID,AREA_NO,AREA_NAME,NATION,PROV,CITY,COUNTY,CITY_TYPE,BEG_BUSS_TYPE,ZONE_ID,SALE_STORE_ID,SALE_STORE_NAME,LOCAL_TYPE,BEG_SBUSS_DATE,PERSON_CON,TEL,MOBILE,TAX,POST,EMAIL,WEB,LEGAL_REPRE,BUSS_LIC,AX_BURDEN,ORG_CODE_CERT,BUSS_SCOPE,FI_CMP_NO,BUSS_AREA,STOR_NO,LAST_DECOR_TIME,ADDRESS', 
									                                                                     'TERM_ID,TERM_NO,TERM_NAME,TERM_ABBR,TERM_TYPE,TERM_LVL,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_NATRUE,PLAY_BANK_NAME,PLAY_BANK_ACCT,LOGIC_TYPE,AREA_ID,AREA_NO,AREA_NAME,NATION,PROV,CITY,COUNTY,CITY_TYPE,BEG_BUSS_TYPE,ZONE_ID,SALE_STORE_ID,SALE_STORE_NAME,LOCAL_TYPE,BEG_SBUSS_DATE,PERSON_CON,TEL,MOBILE,TAX,POST,EMAIL,WEB,LEGAL_REPRE,BUSS_LIC,AX_BURDEN,ORG_CODE_CERT,BUSS_SCOPE,FI_CMP_NO,BUSS_AREA,STOR_NO,LAST_DECOR_TIME,ADDRESS', 'selectParams')">
							</td>
							<td width="17%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" type="text"
									label="终端名称" size="35" value="${rst.TERM_NAME}"  autocheck="true" mustinput="true" inputtype="string"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								终端简称：
							</td>
							<td width="33%" class="detail_content">
								<input json="TERM_ABBR" name="TERM_ABBR" id="TERM_ABBR"
									autocheck="true" label="终端简称" type="text" inputtype="string"
									size="35" value="${rst.TERM_ABBR}"   />
							</td>
							<td width="17%" align="right" class="detail_label">
								终端类型：
							</td>
							<td width="33%" class="detail_content">
								<select json="TERM_TYPE" id="TERM_TYPE" name="TERM_TYPE" style="width:245px;" autocheck="true" mustinput="true" inputtype="string" label="终端类型" >
								</select>
								
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								终端级别：
							</td>
							<td width="33%" class="detail_content">
							    <select json="TERM_LVL" id="TERM_LVL" name="TERM_LVL" style="width:245px;" autocheck="true" mustinput="true" inputtype="string" label="终端级别"></select>
							    
							</td>
							<td width="17%" align="right" class="detail_label">
								上级渠道编号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="CHANN_NO_P" name="CHANN_NO_P" id="CHANN_NO_P" type="text" label="上级渠道编号" size="35" inputtype="string"  autocheck="true" mustinput="true" value="${rst.CHANN_NO_P}" />
								<input json="CHANN_ID_P" name="CHANN_ID_P" id="CHANN_ID_P" type="hidden" label="上级渠道ID"  value="${rst.CHANN_ID_P}"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								上级渠道名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="CHANN_NAME_P" name="CHANN_NAME_P" id="CHANN_NAME_P" autocheck="true" mustinput="true" inputtype="string"
									type="text" label="上级渠道名称" size="35" value="${rst.CHANN_NAME_P}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								营业性质：
							</td>
							<td width="33%" class="detail_content">
								<select json="BUSS_NATRUE" id="BUSS_NATRUE" name="BUSS_NATRUE" style="width:245px;" label="营业性质" ></select>
							</td>
						</tr>

                        <tr>
						   <td width="17%" align="right" class="detail_label">
							     打款开户银行：
							</td>
							<td width="33%" class="detail_content">
							      <input id="PLAY_BANK_NAME" json="PLAY_BANK_NAME" name="PLAY_BANK_NAME"  type="text"   value="${rst.PLAY_BANK_NAME}"
			                                size="35" label="打款开户银行" autocheck="true" mustinput="true" inputtype="string"/>
							</td> 
							<td width="17%" align="right" class="detail_label">
								打款银行账号:
							</td>
							<td width="33%" class="detail_content">
							      <input json="PLAY_BANK_ACCT" name="PLAY_BANK_ACCT"
											id="PLAY_BANK_ACCT" type="text" label="打款银行账号" size="35"
											value="${rst.PLAY_BANK_ACCT}" autocheck="true" mustinput="true" inputtype="string"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								物流方式：
							</td>
							<td width="33%" class="detail_content">
								<select json="LOGIC_TYPE" id="LOGIC_TYPE" name="LOGIC_TYPE" style="width:245px;" label="物流方式"></select>
							</td>
							<td width="17%" align="right" class="detail_label">
								区域编号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" value="${rst.AREA_ID}" />
								<input json="AREA_NO" name="AREA_NO" id="AREA_NO" autocheck="true" mustinput="true" inputtype="string"
									type="text" label="区域编号" size="35" value="${rst.AREA_NO}" />
							</td>
						</tr>
					    <tr>
							<td width="17%" align="right" class="detail_label">
								区域名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text"  
									label="区域名称" size="35" value="${rst.AREA_NAME}" autocheck="true" mustinput="true" inputtype="string"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								国家：
							</td>
							<td width="33%" class="detail_content">
								<input json="NATION" name="NATION"
									id="NATION" type="text" label="国家"
									 size="35" value="${rst.NATION}" readonly/>
							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								省份：
							</td>
							<td width="33%" class="detail_content">
								<input json="PROV" name="PROV" id="PROV" type="text"
									label="省份" size="35" value="${rst.PROV}"  readonly/>
							</td>
							<td width="17%" align="right" class="detail_label">
								城市：
							</td>
							<td width="33%" class="detail_content">
								<input json="CITY" name="CITY" id="CITY" type="text"
									label="城市" size="35" value="${rst.CITY}" readonly/>
							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								区县：
							</td>
							<td width="33%" class="detail_content">
								<input json="COUNTY" name="COUNTY" id="COUNTY" type="text"
									label="区县" size="35" value="${rst.COUNTY}"  readonly/>
							</td>
							<td width="17%" align="right" class="detail_label">
								城市类型：
							</td>
							<td width="33%" class="detail_content">
								<select json="CITY_TYPE" id="CITY_TYPE" name="CITY_TYPE" style="width:245px;" readonly></select>
							</td> 
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
							     营业状态：
							</td>
							<td width="33%" class="detail_content">
			                      <select json="BUSS_STATE" id="BUSS_STATE" name="BUSS_STATE" style="width:245px;" label="营业状态" autocheck="true" mustinput="true" inputtype="string"></select>
							</td> 
						    <td width="17%" align="right" class="detail_label">
							     开店类型：
							</td>
							<td width="33%" class="detail_content">
			                      <select json="BEG_BUSS_TYPE" id="BEG_BUSS_TYPE"  autocheck="true" mustinput="true"  name="BEG_BUSS_TYPE" style="width:245px;" label="开店类型"></select>
							</td> 
						</tr>
						  <tr>
							<td width="17%" align="right" class="detail_label">
								行政区划ID：
							</td>
							<td width="33%" class="detail_content">
								<input json="ZONE_ID" name="ZONE_ID" id="ZONE_ID"
									type="text" label="行政区划ID" size="35" value="${rst.ZONE_ID}"/>
							</td>
						    <td width="17%" align="right" class="detail_label">
							     卖场ID：
							</td>
							<td width="33%" class="detail_content">
							   <input json="SALE_STORE_ID" name="SALE_STORE_ID" id="SALE_STORE_ID" type="text" label="卖场ID" inputtype="string" size="35" maxlength="32" value="${rst.SALE_STORE_ID}" >
 							</td> 
 						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								卖场名称:
							</td>
							<td width="33%" class="detail_content">
							      <input json="SALE_STORE_NAME" name="SALE_STORE_NAME"
											id="SALE_STORE_NAME" type="text" label="卖场名称" size="35"
											value="${rst.SALE_STORE_NAME}" />
							</td>
							<td width="17%" align="right" class="detail_label">
							     商场位置类别：
							</td>
							<td width="33%" class="detail_content">
								  <select json="LOCAL_TYPE" id="LOCAL_TYPE" name="LOCAL_TYPE" style="width:245px;"  label="商场位置类别"  autocheck="true" mustinput="true" inputtype="string"></select>
							</td> 
						   </tr>
							<tr>
							<td width="17%" align="right" class="detail_label">
								开业时间:
							</td>
							<td width="33%" class="detail_content">
							      <input json="BEG_SBUSS_DATE" name="BEG_SBUSS_DATE"
											id="BEG_SBUSS_DATE" type="text" label="开业时间" size="35"
											value="${rst.BEG_SBUSS_DATE}" onclick="SelectDate()" autocheck="true" mustinput="true" inputtype="string"/>
								  <img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(BEG_SBUSS_DATE);">
							</td>
							<td width="17%" align="right" class="detail_label">
								联系人：
							</td>
							<td width="33%" class="detail_content">
								<input id="PERSON_CON" name="PERSON_CON" json="PERSON_CON" size="35"
								 	label="联系人"  value="${rst.PERSON_CON}" />
							</td>
						</tr>
						 <tr>
							<td width="17%" align="right" class="detail_label">
								联系电话：
							</td>
							<td width="33%" class="detail_content">
								<input type="text" id="TEL" name="TEL" json="TEL"  autocheck="true"
								 value="${rst.TEL}"   label="联系电话" size="35" />
							</td>
							<td width="17%" align="right" class="detail_label">
								手机：
							</td>
							<td width="33%" class="detail_content">
								<input type="text" id="MOBILE" name="MOBILE" json="MOBILE"  
									size="35" value="${rst.MOBILE}" label="手机" />
							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								传真：
							</td>
							<td width="33%" class="detail_content">
								<input json="TAX" name="TAX" id="TAX" type="text" autocheck="true"  
								 label="传真" size="35" value="${rst.TAX}" />
							</td>
							<td width="17%" align="right" class="detail_label">
								邮政编码：
							</td>
							<td width="33%" class="detail_content">
								<input id="POST" name="POST" json="POST" style="width: 245px;" value="${rst.POST}" label="邮政编号" />
							</td>
					    </tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								电子邮件：
							</td>
							<td width="33%" class="detail_content">
						       <input json="EMAIL" name="EMAIL" id="EMAIL" type="text" label="电子邮件" size="35"
							      value="${rst.EMAIL}" autocheck="true" />
							</td>
							<td width="17%" align="right" class="detail_label">
								网站：
							</td>
							<td width="33%" class="detail_content">
						        <input json="WEB" name="WEB" id="WEB"
									      type="text" label="网站" size="35" value="${rst.WEB}" />
							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								法人代表：
							</td>
							<td width="33%" class="detail_content">
					            <input id="LEGAL_REPRE" name="LEGAL_REPRE" json="LEGAL_REPRE"  
								value="${rst.LEGAL_REPRE}" label="法人代表" size="35" />
								 
							</td>
						<td width="17%" align="right" class="detail_label">
								 营业执照号 ：
							</td>
							<td width="33%" class="detail_content">
						       <input id="BUSS_LIC" name="BUSS_LIC" json="BUSS_LIC"
								       value="${rst.BUSS_LIC}" size="35" type="text" />
							</td> 
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
							      税务登记号：
							</td>
							<td width="33%" class="detail_content">
							   <input type="text" name="AX_BURDEN" size="35" json="AX_BURDEN" id="AX_BURDEN" label="税务登记号" value="${rst.AX_BURDEN}" /> 
							</td> 
							<td width="17%" align="right" class="detail_label">
								组织机构代码证：
							</td>
							<td width="33%" class="detail_content">
								<input json="ORG_CODE_CERT" name="ORG_CODE_CERT" size="35"
									id="ORG_CODE_CERT" label="组织机构代码证" value="${rst.ORG_CODE_CERT}" />
							</td> 
						</tr>
						<tr>
						  
							<td width="17%" align="right" class="detail_label">
							     经营范围：
							</td>
							<td width="33%" class="detail_content">
			                     <input id="BUSS_SCOPE" json="BUSS_SCOPE" name="BUSS_SCOPE" size="35" type="text" value="${rst.BUSS_SCOPE}" /> 
							</td> 
							<td width="17%" align="right" class="detail_label">
								财务对照码:
							</td>
							<td width="33%" class="detail_content">
							      <input json="FI_CMP_NO" name="FI_CMP_NO"
											id="FI_CMP_NO" type="text" label="财务对照码" size="35"
											value="${rst.FI_CMP_NO}" />
							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
							     营业面积：
							</td>
							<td width="33%" class="detail_content">
							      <input id="BUSS_AREA" json="BUSS_AREA" name="BUSS_AREA"  type="text"   value="${rst.BUSS_AREA}"
			                                size="35" label="营业面积" autocheck="true" mustinput="true" inputtype="string"/>
							</td> 
							<td width="17%" align="right" class="detail_label">
								楼层数:
							</td>
							<td width="33%" class="detail_content">
							      <input json="STOR_NO" name="STOR_NO"
											id="STOR_NO" type="text" label="楼层数" size="35"
											value="${rst.STOR_NO}" />
							</td>
						</tr>
					    <tr>
							<td width="17%" align="right" class="detail_label">
							     最后装潢时间：
							</td>
							<td width="33%" class="detail_content" colspan="3">
								  <input type="text" json="LAST_DECOR_TIME" id="LAST_DECOR_TIME" name="LAST_DECOR_TIME" autocheck="true" inputtype="string" 
														label="最后装潢时间"   onclick="SelectTime();" value="${rst.LAST_DECOR_TIME}" size="35"/>&nbsp;
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
														onclick="SelectTime(LAST_DECOR_TIME);"/>
							</td> 
						</tr>
							
						<tr>
							<td width="17%" align="right" class="detail_label">
								详细地址：
							</td>
							<td width="33%" class="detail_content">
								<textarea json="ADDRESS" name="ADDRESS" id="ADDRESS" type="text" label="详细地址"  rows="4" cols="28%" 
								          mustinput="true" autocheck="true" inputtype="string"   maxlength="250" >${rst.ADDRESS}</textarea>
							</td>
							<td width="17%" align="right" class="detail_label">
								备注：
							</td>
							<td width="33%" class="detail_content" >
								<textarea json="REMARK" name="REMARK" id="REMARK" label="备注"
									      rows="4" cols="28%" autocheck="true" inputtype="string"   maxlength="250">${rst.REMARK}</textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
   </div>
</body>
<script type="text/javascript">
    SelDictShow("TERM_TYPE","175","${rst.TERM_TYPE }","");
    SelDictShow("TERM_LVL","176","${rst.TERM_LVL }","");
    SelDictShow("BUSS_NATRUE","177","${rst.BUSS_NATRUE }","");
    SelDictShow("LOGIC_TYPE","178","${rst.LOGIC_TYPE }","");
    SelDictShow("CITY_TYPE","BS_101","${rst.CITY_TYPE }","");
    SelDictShow("BEG_BUSS_TYPE","BS_136","${rst.BEG_BUSS_TYPE}","");
    SelDictShow("LOCAL_TYPE","BS_86","${rst.LOCAL_TYPE}","");
    SelDictShow("TERM_VERSION","BS_134","${rst.TERM_VERSION}","");
    
    var BUSS_STATE='${rst.BUSS_STATE}';
	if(""==BUSS_STATE||null==BUSS_STATE){
		BUSS_STATE="正常营业";
	}
	SelDictShow("BUSS_STATE","BS_181",BUSS_STATE,"");
	
	//SelDictShow("BUSS_STATE","BS_135","${rst.BUSS_STATE}","");
</script> 



<!-- 
 /**
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 郭利伟
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
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/base/terminal/Terminal_Edit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>终端信息编辑</title>
	<script type="text/javascript" src="brand_List.js"></script></head>
	<body STYLE='OVERFLOW-Y:SCROLL;'>
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
			        <td height="20px">&nbsp;</td>
			    </tr>
				<tr>
					<td>
						<form name="main" id="mainForm">
							<input type="hidden" name="selectParams" value=" STATE in( '启用')">
							<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label">
													终端编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="TERM_ID" name="TERM_ID" id="TERM_ID" type="hidden" value="${rst.TERM_ID}">
													<input json="TERM_NO" name="TERM_NO" id="TERM_NO"
												        type="text" autocheck="true" label="终端编号" inputtype="string" mustinput="true"
														maxlength="32"  
														<c:if test="${not empty rst.TERM_NO}">value="${rst.TERM_NO}"</c:if>
														<c:if test="${empty rst.TERM_NO}">value="自动生成"</c:if>
														READONLY
														>
												</td>
												<td width="15%" align="right" class="detail_label">
													终端名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" type="text" autocheck="true" label="终端名称" inputtype="string" mustinput="true"  maxlength="100" value="${rst.TERM_NAME}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													终端简称：
												</td>
												<td width="35%" class="detail_content">
													<input json="TERM_ABBR" name="TERM_ABBR" id="TERM_ABBR" type="text" autocheck="true" label="终端简称" inputtype="string"  maxlength="100" value="${rst.TERM_ABBR}">
												</td>
												<td width="15%" align="right" class="detail_label">
													终端类型：
												</td>
												<td width="35%" class="detail_content" >
													<select json="TERM_TYPE" id="TERM_TYPE" name="TERM_TYPE" style="width:155" autocheck="true" mustinput="true" inputtype="string"></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													终端级别：
												</td>
												<td width="35%" class="detail_content">
													<select json="TERM_LVL" id="TERM_LVL" name="TERM_LVL" style="width:155" autocheck="true" mustinput="true" inputtype="string"></select>
												</td>
												<td width="15%" align="right" class="detail_label">
													营业性质：
												</td>
												<td width="35%" class="detail_content">
													<select json="BUSS_NATRUE" id="BUSS_NATRUE" name="BUSS_NATRUE" style="width:155" ></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													门店分类：
												</td>
												<td width="35%" class="detail_content">
													<select json="TERM_CLASS" id="TERM_CLASS" name="TERM_CLASS" style="width:155" autocheck="true" inputtype="string"></select>
												</td>
												<td width="15%" align="right" class="detail_label"> 负责人：</td>
												<td width="35%" class="detail_content">
												  <input json="CUST_NAME" name="CUST_NAME" id="CUST_NAME" type="text" label="负责人" maxlength="100"  autocheck="true" inputtype="string" value="${rst.CUST_NAME}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													渠道编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_NO_P" name="CHANN_NO_P" id="CHANN_NO_P" type="text" label="渠道编号" inputtype="string"  autocheck="true" mustinput="true" value="${rst.CHANN_NO_P}" READONLY>
													<input json="CHANN_ID_P" name="CHANN_ID_P" id="CHANN_ID_P" type="hidden" label="渠道ID" value="${rst.CHANN_ID_P}">
													<img align="absmiddle" name="selCHANN_NO_P" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_19', false, 'CHANN_ID_P', 'CHANN_ID', 'forms[0]','CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P,AREA_ID,AREA_NO,AREA_NAME,ZONE_ID,NATION,PROV,CITY,COUNTY', 'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,ZONE_ID,NATION,PROV,CITY,COUNTY', 'selectParams')">
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="CHANN_NAME_P" name="CHANN_NAME_P" id="CHANN_NAME_P" type="text" label="渠道名称" inputtype="string"  autocheck="true" mustinput="true" value="${rst.CHANN_NAME_P}" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													打款开户银行：
												</td>
												<td width="35%" class="detail_content">
													<input json="PLAY_BANK_NAME" name="PLAY_BANK_NAME" id="PLAY_BANK_NAME"  type="text" label="打款开户银行" inputtype="string"  autocheck="true" mustinput="true" value="${rst.PLAY_BANK_NAME}" >
												</td>
												<td width="15%" align="right" class="detail_label">
													打款银行账号：
												</td>
												<td width="35%" class="detail_content">
													<input json="PLAY_BANK_ACCT" name="PLAY_BANK_ACCT" id="PLAY_BANK_ACCT"  type="text" label="打款银行账号" inputtype="string"  autocheck="true" mustinput="true" value="${rst.PLAY_BANK_ACCT}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													物流方式：
												</td>
												<td width="35%" class="detail_content">
													<select json="LOGIC_TYPE" id="LOGIC_TYPE" name="LOGIC_TYPE" style="width:155" ></select>
												</td>
												<td width="15%" align="right" class="detail_label">
													终端版本：
												</td>
												<td width="35%" class="detail_content">
													<select json="TERM_VERSION"  autocheck="true" mustinput="true" id="TERM_VERSION" name="TERM_VERSION" style="width:155"  inputtype="string" ></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													区域编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" label="区域ID" value="${rst.AREA_ID}" >
													<input json="AREA_NO" name="AREA_NO" id="AREA_NO" type="text" label="区域编号" inputtype="string"  autocheck="true" mustinput="true" value="${rst.AREA_NO}" READONLY>
													<%--<img align="absmiddle" name="selAREA_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">--%>
												</td>
												<td width="15%" align="right" class="detail_label">
													区域名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text" label="区域名称" inputtype="string"  autocheck="true" mustinput="true" value="${rst.AREA_NAME}" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													国家：
												</td>
												<td width="35%" class="detail_content">
													<input json="ZONE_ID" name="ZONE_ID" id="ZONE_ID" type="hidden" label="行政区划ID">
													<input json="NATION" name="NATION" id="NATION" type="text" label="国家" inputtype="string" value="${rst.NATION}" READONLY>
													<%--<img align="absmiddle" name="selNATION" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_20', false, 'ZONE_ID', 'ZONE_ID', 'forms[0]','NATION,PROV,CITY,COUNTY', 'NATION,PROV,CITY,COUNTY', 'selectParams')">--%>
												</td>
												<td width="15%" align="right" class="detail_label">
													省份：
												</td>
												<td width="35%" class="detail_content">
													<input json="PROV" name="PROV" id="PROV" type="text" label="省份" inputtype="string" value="${rst.PROV}" READONLY>
												</td>
												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													城市：
												</td>
												<td width="35%" class="detail_content">
													<input json="CITY" name="CITY" id="CITY" type="text" label="城市" inputtype="string" value="${rst.CITY}" READONLY>
												</td>
												<td width="15%" align="right" class="detail_label">
													区县：
												</td>
												<td width="35%" class="detail_content">
													<input json="COUNTY" name="COUNTY" id="COUNTY" type="text" label="区县" inputtype="string" value="${rst.COUNTY}" READONLY>
												</td>
												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													城市类型：
												</td>
												<td width="35%" class="detail_content">
													<select json="CITY_TYPE" id="CITY_TYPE" name="CITY_TYPE" style="width:155"></select>
												</td>
												<td width="15%" align="right" class="detail_label">营业状态:
												</td>
												<td width="35%" class="detail_content">
													<select json="BUSS_STATE" id="BUSS_STATE" name="BUSS_STATE" style="width:155"></select>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													开店类型：
												</td>
												<td width="35%" class="detail_content">
													<select json="BEG_BUSS_TYPE" id="BEG_BUSS_TYPE"  autocheck="true" mustinput="true" inputtype="string" name="BEG_BUSS_TYPE" style="width:155"></select>
												</td>
												<td width="15%" align="right" class="detail_label">
												</td>
												<td width="35%" class="detail_content">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													卖场ID：
												</td>
												<td width="35%" class="detail_content">
													<input json="SALE_STORE_ID" name="SALE_STORE_ID" id="SALE_STORE_ID" type="text" label="卖场ID" inputtype="string" maxlength="32" value="${rst.SALE_STORE_ID}" >
												</td>
												<td width="15%" align="right" class="detail_label">
													卖场名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="SALE_STORE_NAME" name="SALE_STORE_NAME" id="SALE_STORE_NAME" type="text" label="卖场名称" inputtype="string" maxlength="100" value="${rst.SALE_STORE_NAME}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													商场位置类别：
												</td>
												<td width="35%" class="detail_content">
													<select json="LOCAL_TYPE" id="LOCAL_TYPE" name="LOCAL_TYPE" style="width:155" inputtype="string" label="商场位置类别"  mustinput="true"></select>
												</td>
												<td width="15%" align="right" class="detail_label">
													开业时间：
												</td>
												<td width="35%" class="detail_content">
													<input json="BEG_SBUSS_DATE"  name="BEG_SBUSS_DATE" id="BEG_SBUSS_DATE" type="text" label="开业时间"  autocheck="true" mustinput="true"  readonly inputtype="string" onclick="SelectDate();"  value="${rst.BEG_SBUSS_DATE}" >
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
														onclick="SelectDate(BEG_SBUSS_DATE);"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													联系人：
												</td>
												<td width="35%" class="detail_content">
													<input json="PERSON_CON" name="PERSON_CON" id="PERSON_CON" type="text" label="联系人" inputtype="string"  maxlength="30" value="${rst.PERSON_CON}">
												</td>
												<td width="15%" align="right" class="detail_label">
													电话：
												</td>
												<td width="35%" class="detail_content">
													<input json="TEL" name="TEL" id="TEL" type="text" label="电话" inputtype="string"  maxlength="30" value="${rst.TEL}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													手机：
												</td>
												<td width="35%" class="detail_content">
													<input json="MOBILE_PHONE" name="MOBILE_PHONE" id="MOBILE_PHONE" type="text" label="手机" inputtype="string"  maxlength="30" value="${rst.MOBILE_PHONE}" >
												</td>
												<td width="15%" align="right" class="detail_label">
													传真：
												</td>
												<td width="35%" class="detail_content">
													<input json="TAX" name="TAX" id="TAX" type="text" label="传真" inputtype="string"  maxlength="30" value="${rst.TAX}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													邮政编码：
												</td>
												<td width="35%" class="detail_content">
													<input json="POST" name="POST" id="POST" type="text" label="邮政编码" inputtype="string"  maxlength="30" value="${rst.POST}">
												</td>
												<td width="15%" align="right" class="detail_label">
													电子邮件：
												</td>
												<td width="35%" class="detail_content">
													<input json="EMAIL" name="EMAIL" id="EMAIL" type="text" label="电子邮件" inputtype="string"  maxlength="30" value="${rst.EMAIL}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													网站：
												</td>
												<td width="35%" class="detail_content">
													<input json="WEB" name="WEB" id="WEB" type="text" label="网站" inputtype="string"  maxlength="30" value="${rst.WEB}">
												</td>
												<td width="15%" align="right" class="detail_label">
													法人代表：
												</td>
												<td width="35%" class="detail_content">
													<input json="LEGAL_REPRE" name="LEGAL_REPRE" id="LEGAL_REPRE" type="text" label="法人代表" inputtype="string"  maxlength="30" value="${rst.LEGAL_REPRE}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													营业执照号：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_LIC" name="BUSS_LIC" id="BUSS_LIC" type="text" label="营业执照号" inputtype="string"  maxlength="30" value="${rst.BUSS_LIC}">
												</td>
												<td width="15%" align="right" class="detail_label">
													税务登记号：
												</td>
												<td width="35%" class="detail_content">
													<input json="AX_BURDEN" name="AX_BURDEN" id="AX_BURDEN" type="text" label="税务登记号" inputtype="string"  maxlength="30" value="${rst.AX_BURDEN}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													组织结构代码证：
												</td>
												<td width="35%" class="detail_content">
													<input json="ORG_CODE_CERT" name="ORG_CODE_CERT" id="ORG_CODE_CERT" type="text" label="组织结构代码证" inputtype="string"  maxlength="30" value="${rst.ORG_CODE_CERT}">
												</td>
												<td width="15%" align="right" class="detail_label">
													经营范围：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_SCOPE" name="BUSS_SCOPE" id="BUSS_SCOPE" type="text" label="经营范围" inputtype="string"  maxlength="50" value="${rst.BUSS_SCOPE}" >
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													财务对照码：
												</td>
												<td width="35%" class="detail_content">
													<input json="FI_CMP_NO" name="FI_CMP_NO" id="FI_CMP_NO" type="text" label="财务对照码" inputtype="string"  maxlength="10" value="${rst.FI_CMP_NO}">
												</td>
												<td width="15%" align="right" class="detail_label">
													营业面积：
												</td>
												<td width="35%" class="detail_content">
													<input json="BUSS_AREA" name="BUSS_AREA" id="BUSS_AREA" type="text" label="营业面积" autocheck="true" inputtype="float" valueType="8,2" mustinput="true"  value="${rst.BUSS_AREA}">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													楼层数：
												</td>
												<td width="35%" class="detail_content">
													<input json="STOR_NO" name="STOR_NO" id="STOR_NO" type="text" label="楼层数" autocheck="true" inputtype="number"  valueType="number" maxlength="50" value="${rst.STOR_NO}">
												</td>
												<td width="15%" align="right" class="detail_label">
													装修完成时间：
												</td>
												<td width="35%" class="detail_content">
													<input type="text" json="LAST_DECOR_TIME" id="LAST_DECOR_TIME" name="LAST_DECOR_TIME" autocheck="true" inputtype="string" 
														label="装修完成时间"   onclick="SelectDate();" readonly value="${rst.LAST_DECOR_TIME}" />&nbsp;
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
														onclick="SelectDate(LAST_DECOR_TIME);"/>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" >详细地址：</td>
												<td width="50%" class="detail_content" colspan="3" >
													<textarea json="ADDRESS" name="ADDRESS" inputtype="string" label="详细地址" id="ADDRESS"  mustinput ="true"
														maxlength="250" rows="3" cols="70%" allowHTML="true" ><c:out value="${rst.ADDRESS}"></c:out></textarea>
												</td>
											</tr>
											
											<tr>
												<td width="15%" align="right" class="detail_label" >备注：</td>
												<td width="50%" class="detail_content" colspan="3">
													<textarea json="REMARK" name="REMARK" inputtype="string"
														maxlength="250" rows="3" cols="70%" allowHTML="true" ><c:out value="${rst.REMARK}"></c:out></textarea>
												</td>
											</tr>
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
	<script language="javascript">
		$(function(){
			SelDictShow("TERM_TYPE","175","${rst.TERM_TYPE }","");
			SelDictShow("TERM_LVL","176","${rst.TERM_LVL }","");
			SelDictShow("BUSS_NATRUE","177","${rst.BUSS_NATRUE }","");
			SelDictShow("LOGIC_TYPE","178","${rst.LOGIC_TYPE }","");
			SelDictShow("CITY_TYPE","BS_101","${rst.CITY_TYPE }","");
			SelDictShow("LOCAL_TYPE","BS_86","${rst.LOCAL_TYPE}","");
			SelDictShow("TERM_VERSION","BS_134","${rst.TERM_VERSION}","");
			SelDictShow("TERM_CLASS","BS_160","${rst.TERM_CLASS}","");
			var BUSS_STATE='${rst.BUSS_STATE }';
			if(""==BUSS_STATE||null==BUSS_STATE){
				BUSS_STATE="正常营业";
			}
			SelDictShow("BUSS_STATE","BS_181",BUSS_STATE,"");
			SelDictShow("BEG_BUSS_TYPE","BS_136","${rst.BEG_BUSS_TYPE}","");
			
		});
	</script>
</html>

<!-- 
 *@module 渠道管理-终端管理
 *@func   门店变更申请单维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>门店变更—终端历史信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/terminalchange/TerminalChange_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="bodycss1">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>   
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
                                 ${rst.TERM_NO} 							 
							</td>
							<td width="15%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERM_NAME}
							</td>
						 </tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								终端简称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERM_ABBR}
							</td>
							<td width="15%" align="right" class="detail_label">
								终端类型：
							</td>
							<td width="35%" class="detail_content">
								${rst.TERM_TYPE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								终端级别：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERM_LVL}
							</td>
							<td width="15%" align="right" class="detail_label">
								行政区划ID：
							</td>
							<td width="35%" class="detail_content">
								${rst.ZONE_ID}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								上级渠道编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NO_P}
							</td>
							<td width="15%" align="right" class="detail_label">
								上级渠道名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NAME_P}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								营业性质：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.BUSS_NATRUE}
							</td>
							<td width="15%" align="right" class="detail_label">
								物流方式：
							</td>
							<td width="35%" class="detail_content">
								${rst.LOGIC_TYPE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								区域编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								区域名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NAME}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								国家：
							</td>
							<td width="35%" class="detail_content">
								${rst.NATION}
							</td>
						    <td width="15%" align="right" class="detail_label">
								省份：
							</td>
							<td width="35%" class="detail_content">
								${rst.PROV}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								城市：
							</td>
							<td width="35%" class="detail_content">
								${rst.CITY}
							</td>
						    <td width="15%" align="right" class="detail_label">
								区县：
							</td>
							<td width="35%" class="detail_content">
								${rst.COUNTY}
							</td>
						</tr>
						<tr>
                            <td width="15%" align="right" class="detail_label">
								城市类型：
							</td>
							<td width="35%" class="detail_content">
								${rst.CITY_TYPE}
							</td>
							<td width="15%" align="right" class="detail_label">
								邮政编码：
							</td>
							<td width="35%" class="detail_content">
								${rst.POST}
							</td>
						</tr>
						<tr>
 	                        <td width="15%" align="right" class="detail_label">
								联系人：
							</td>
							<td width="35%" class="detail_content">
								${rst.PERSON_CON}
							</td>
							<td width="15%" align="right" class="detail_label">
								联系电话：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.TEL}
							</td>
						</tr>
                        <tr>
							<td width="15%" align="right" class="detail_label">
								手机：
							</td>
							<td width="35%" class="detail_content">
								${rst.MOBILE}
							</td>
							<td width="15%" align="right" class="detail_label">
								传真：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.TAX}
							</td>
						</tr>
						<tr>
                            <td width="15%" align="right" class="detail_label">
								详细地址：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								${rst.ADDRESS}
							</td>
						 </tr>
                        <tr>
							<td width="15%" align="right" class="detail_label">
								电子邮件：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.EMAIL}
							</td>
							<td width="15%" align="right" class="detail_label">
								网站：
							</td>
							<td width="35%" class="detail_content">
								${rst.WEB}
							</td>
						 </tr>
                        <tr>
							<td width="15%" align="right" class="detail_label">
								法人代表：
							</td>
							<td width="35%" class="detail_content">
								${rst.LEGAL_REPRE}
							</td>
							<td width="15%" align="right" class="detail_label">
								营业执照号：
							</td>
							<td width="35%" class="detail_content">
							   ${rst.BUSS_LIC}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								税务登记号：
							</td>
							<td width="35%" class="detail_content">
								${rst.AX_BURDEN}
							</td>
							<td width="15%" align="right" class="detail_label">
								组织机构代码证：
							</td>
							<td width="35%" class="detail_content">
								${rst.ORG_CODE_CERT}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								经营范围：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.BUSS_SCOPE}
							</td>
							<td width="15%" align="right" class="detail_label">
								财务对照码：
							</td>
							<td width="35%" class="detail_content">
								${rst.FI_CMP_NO}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								营业面积：
							</td>
							<td width="35%" class="detail_content">
								${rst.BUSS_AREA}
							</td>
							<td width="15%" align="right" class="detail_label">
								楼层数：
							</td>
							<td width="35%" class="detail_content">
								${rst.STOR_NO}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								最后装潢时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.LAST_DECOR_TIME}
							</td>
							<td width="15%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.SALE_STORE_NAME}
							</td>
						 </tr>
					     <tr>
							<td width="15%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="35%" class="detail_content">
								${rst.LOCAL_TYPE}
							</td>
						   <td width="15%" align="right" class="detail_label">
								开业时间：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.BEG_SBUSS_DATE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								打款开户银行：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.PLAY_BANK_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								打款银行账号：
							</td>
							<td width="35%" class="detail_content">
								${rst.PLAY_BANK_ACCT}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								营业状态：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.BUSS_STATE}
							</td>
							<td width="15%" align="right" class="detail_label">
								开店类型：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.BEG_BUSS_TYPE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								${rst.REMARK}
							</td>
						</tr>  
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
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
	<title>终端信息详情</title>
</head>
<body STYLE='OVERFLOW-Y:SCROLL;' class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">终端编号：</td>
					<td width="35%" class="detail_content">${rst.TERM_NO }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">终端名称：</td>
					<td width="35%" class="detail_content">${rst.TERM_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">终端简称：</td>
					<td width="35%" class="detail_content">${rst.TERM_ABBR }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">终端类型：</td>
					<td width="35%" class="detail_content">${rst.TERM_TYPE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">终端级别：</td>
					<td width="35%" class="detail_content">${rst.TERM_LVL}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">营业性质：</td>
					<td width="35%" class="detail_content">${rst.BUSS_NATRUE }</td> 
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">门店分类：</td>
					<td width="35%" class="detail_content">${rst.TERM_CLASS}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">负责人：</td>
					<td width="35%" class="detail_content">${rst.CUST_NAME }</td> 
				</tr>		
				<tr>
					<td width="15%" align="right" class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NO_P }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NAME_P }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">打款开户银行：</td>
					<td width="35%" class="detail_content">${rst.PLAY_BANK_NAME }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">打款银行账号：</td>
					<td width="35%" class="detail_content">${rst.PLAY_BANK_ACCT }</td> 
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >物流方式：</td>
					<td width="35%" class="detail_content">${rst.LOGIC_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >终端版本：</td>
					<td width="35%" class="detail_content">${rst.TERM_VERSION }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >区域编号：</td>
					<td width="35%" class="detail_content">${rst.AREA_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >区域名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_NAME }&nbsp;</td>
				</tr>
				<tr>
					
					<td width="15%" align="right" class="detail_label" >国家：</td>
					<td width="35%" class="detail_content">${rst.NATION }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >省份：</td>
					<td width="35%" class="detail_content">${rst.PROV }&nbsp;</td>
				</tr>
				<tr>
					
					<td width="15%" align="right" class="detail_label" >城市：</td>
					<td width="35%" class="detail_content">${rst.CITY }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >区县：</td>
					<td width="35%" class="detail_content">${rst.COUNTY }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >城市类型：</td>
					<td width="35%" class="detail_content">${rst.CITY_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >营业状态:</td>
					<td width="35%" class="detail_content">${rst.BUSS_STATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >开店类型：</td>
					<td width="35%" class="detail_content">${rst.BEG_BUSS_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" ></td>
					<td width="35%" class="detail_content"></td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >卖场ID：</td>
					<td width="35%" class="detail_content">${rst.SALE_STORE_ID}&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >卖场名称：</td>
					<td width="35%" class="detail_content">${rst.SALE_STORE_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >商场位置类别：</td>
					<td width="35%" class="detail_content">${rst.LOCAL_TYPE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >开业时间：</td>
					<td width="35%" class="detail_content">${rst.BEG_SBUSS_DATE}&nbsp;</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label" >联系人：</td>
					<td width="35%" class="detail_content">${rst.PERSON_CON }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >电话：</td>
					<td width="35%" class="detail_content">${rst.TEL }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >手机：</td>
					<td width="35%" class="detail_content">${rst.MOBILE_PHONE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >传真：</td>
					<td width="35%" class="detail_content">${rst.TAX }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >邮政编码：</td>
					<td width="35%" class="detail_content">${rst.POST }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >电子邮件：</td>
					<td width="35%" class="detail_content">${rst.EMAIL }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >网站：</td>
					<td width="35%" class="detail_content">${rst.WEB }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >法人代表：</td>
					<td width="35%" class="detail_content">${rst.LEGAL_REPRE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >营业执照号：</td>
					<td width="35%" class="detail_content">${rst.BUSS_LIC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >税务登记号：</td>
					<td width="35%" class="detail_content">${rst.AX_BURDEN }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >组织结构代码证：</td>
					<td width="35%" class="detail_content">${rst.ORG_CODE_CERT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >经营范围：</td>
					<td width="35%" class="detail_content">${rst.BUSS_SCOPE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >财务对照码：</td>
					<td width="35%" class="detail_content">${rst.FI_CMP_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >营业面积：</td>
					<td width="35%" class="detail_content">${rst.BUSS_AREA }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >楼层数：</td>
					<td width="35%" class="detail_content">${rst.STOR_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >装修完成时间：</td>
					<td width="35%" class="detail_content">${rst.LAST_DECOR_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >账套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">详细地址：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.ADDRESS }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>

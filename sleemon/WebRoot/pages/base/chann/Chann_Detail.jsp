
<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author 刘曰刚
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	
	<title>渠道信息详情</title>
</head>
<body class="bodycss1">	
<table width="100%"  border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道简称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_ABBR }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">渠道类型：</td>
					<td width="35%" class="detail_content">${rst.CHANN_TYPE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道级别：</td>
					<td width="35%" class="detail_content">${rst.CHAA_LVL }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">加盟日期：</td>
					<td width="35%" class="detail_content">${rst.JOIN_DATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">是否控制终端销售折扣：</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.TERM_DECT_CTR_FLAG==1}">是</c:if>
						 <c:if test="${rst.TERM_DECT_CTR_FLAG==0}">否</c:if>
					&nbsp;</td>
					<td width="15%" align="right" class="detail_label">渠道销售级别:</td>
					<td width="35%" class="detail_content">${rst.CHAA_SALE_LVL}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >区域服务中心编号：</td>
					<td width="35%" class="detail_content">${rst.AREA_SER_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">区域服务中心名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_SER_NAME }&nbsp;</td>
				</tr>
				<!--  //-- 0156117--Start--刘曰刚--2014-06-16// -->
				<tr>
					<td width="15%" align="right" class="detail_label" >区域经理名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_MANAGE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">区域经理电话：</td>
					<td width="35%" class="detail_content">${rst.AREA_MANAGE_TEL }&nbsp;</td>
				</tr>
				<!-- //-- 0156117--End--刘曰刚--2014-06-16// -->
				<tr>
					<td width="15%" align="right" class="detail_label">区域编号：</td>
					<td width="35%" class="detail_content">${rst.AREA_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">区域名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">税率：</td>
					<td width="35%" class="detail_content">${rst.TAX_RATE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">成本计算方式：</td>
					<td width="35%" class="detail_content">${rst.COST_TYPE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">初始化年份：</td>
					<td width="35%" class="detail_content">${rst.INIT_YEAR }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">初始化月份：</td>
					<td width="35%" class="detail_content">${rst.INIT_MONTH }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">国家：</td>
					<td width="35%" class="detail_content">${rst.NATION }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">省份：</td>
					<td width="35%" class="detail_content">${rst.PROV }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">城市：</td>
					<td width="35%" class="detail_content">${rst.CITY }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">区县：</td>
					<td width="35%" class="detail_content">${rst.COUNTY}&nbsp;</td>
					
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">城市类型：</td>
					<td width="35%" class="detail_content">${rst.CITY_TYPE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">联系人：</td>
					<td width="35%" class="detail_content">${rst.PERSON_CON}&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">电话：</td>
					<td width="35%" class="detail_content">${rst.TEL}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">手机：</td>
					<td width="35%" class="detail_content">${rst.MOBILE }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">传真：</td>
					<td width="35%" class="detail_content">${rst.TAX }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">邮政编码：</td>
					<td width="35%" class="detail_content">${rst.POST}&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">电子邮件：</td>
					<td width="35%" class="detail_content">${rst.EMAIL}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">网站：</td>
					<td width="35%" class="detail_content">${rst.WEB }&nbsp;</td>
					
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">法人代表：</td>
					<td width="35%" class="detail_content">${rst.LEGAL_REPRE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">营业执照号：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.BUSS_LIC }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">税务登记号：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.AX_BURDEN }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">组织机构代码证：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.ORG_CODE_CERT }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">经营性质：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.BUSS_NATRUE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">经营范围：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.BUSS_SCOPE }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">增值税号：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.VAT_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">发票抬头：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.INVOICE_TI }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">发票地址：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.INVOICE_ADDR}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">开户银行：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.BANK_NAME }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">银行账号：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.BANK_ACCT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">财务对照码：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.FI_CMP_NO}&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">保证金：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.DEPOSIT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">欠款期限：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.DEBT_DEF }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">营业执照附件：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">
						<input type="hidden" id ="BUSS_LIC_ATT" value='${rst.BUSS_LIC_ATT}' />
					</td>
					<td width="15%" align="right" class="detail_label">税务登记附件：</td>
					<td width="35%" class="detail_content" >
						<input type="hidden" id ="TAX_BURDEN_ATT" value='${rst.TAX_BURDEN_ATT}' />
					</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">组织机构代码证附件：</td>
					<td width="35%" class="detail_content" >
						<input type="hidden" id ="ORG_CERT_ATT" value='${rst.ORG_CERT_ATT}' />
					</td>
					<td width="15%" align="right" class="detail_label">付款比例：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.PAY_RATE }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">生产基地：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.SHIP_POINT_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content" style="word-break:break-all">&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.UPD_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.ORG_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.DEPT_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.LEDGER_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.STATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">是否允许修改入库数量：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">
					     <c:if test="${rst.IS_UPDATE_STOREIN_FLAG==1}">是</c:if>
						 <c:if test="${rst.IS_UPDATE_STOREIN_FLAG==0}">否</c:if>
					&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">价格条款：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.PRICE_CLAUSE }&nbsp;</td>
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
<script type=text/javascript >
	displayDownFile('BUSS_LIC_ATT','SAMPLE_DIR',true,false);
	displayDownFile('ORG_CERT_ATT','SAMPLE_DIR',true,false);
	displayDownFile('TAX_BURDEN_ATT','SAMPLE_DIR',true,false);
</script>
</html>

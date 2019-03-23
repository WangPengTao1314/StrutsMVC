<!--  
/**
 * @module Sample
 * @func 单表示例
 * @version 1.1
 * @author zhuxw
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
	<title>机构信息详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">机构编号：</td>
					<td width="35%" class="detail_content">${rst.JGBH}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">机构名称：</td>
					<td width="35%" class="detail_content">${rst.JGMC}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">机构简称：</td>
					<td width="35%" class="detail_content">${rst.JGJC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">法人代表：</td>
					<td width="35%" class="detail_content">${rst.FRDB }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >上级机构编号：</td>
					<td width="35%" class="detail_content">${rst.SJJGBH }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >上级机构名称：</td>
					<td width="35%" class="detail_content">${rst.SJJGMC }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">传真：</td>
					<td width="35%" class="detail_content">${rst.CZ }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">电子邮件：</td>
					<td width="35%" class="detail_content">${rst.DZYJ }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">主页地址：</td>
					<td width="35%" class="detail_content">${rst.ZYDZ }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">联系电话：</td>
					<td width="35%" class="detail_content">${rst.DH }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">国家：</td>
					<td width="35%" class="detail_content">${rst.GJ}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">省份：</td>
					<td width="35%" class="detail_content">${rst.SF}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">城市：</td>
					<td width="35%" class="detail_content">${rst.CS}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">邮政编码：</td>
					<td width="35%" class="detail_content">${rst.YZBM }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">机构帐套名称：</td>
					<td width="35%" class="detail_content">${rst.ZTMC}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">排序号：</td>
					<td width="35%" class="detail_content">${rst.XH }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">英文名称：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.JGYWMC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">英文简称：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.JGYWJC }&nbsp;</td>
				</tr>			

			</table>
		</td>
	</tr>
</table>
</body>
</html>

<!-- 
 *@module 系统管理
 *@func 部门信息
 *@version 1.1
 *@author 吴亚林
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>部门信息详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">部门编号：</td>
					<td width="35%" class="detail_content">${rst.BMBH}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">部门名称：</td>
					<td width="35%" class="detail_content">${rst.BMMC}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">部门简称：</td>
					<td width="35%" class="detail_content">${rst.BMJC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">上级部门名称：</td>
					<td width="35%" class="detail_content">${rst.SJBMMC}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">所属机构名称：</td>
					<td width="35%" class="detail_content">${rst.JGMC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">电话：</td>
					<td width="35%" class="detail_content">${rst.DH}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >传真：</td>
					<td width="35%" class="detail_content">${rst.CZ }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >电子邮件：</td>
					<td width="35%" class="detail_content">${rst.DZYJ }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">主页地址：</td>
					<td width="35%" class="detail_content">${rst.ZYDZ }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">邮政编码：</td>
					<td width="35%" class="detail_content">${rst.YZBM }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">其它说明：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.QTSM }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">详细地址：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.XXDZ}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">序号：</td>
					<td width="35%" class="detail_content">${rst.XH }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.BMZT }&nbsp;</td>
				</tr>
				
				<!-- <tr>
					<td width="15%" align="right" class="detail_label">机构帐套名称：</td>
					<td width="35%" class="detail_content" colspan="3">${rst.ZTMC}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">创建人：</td>
					<td width="35%" class="detail_content">${rst.CRENAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">创建时间：</td>
					<td width="35%" class="detail_content">${rst.CRETIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">英文名称：</td>
					<td width="35%" class="detail_content">${rst.JGYWMC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">英文简称：</td>
					<td width="35%" class="detail_content">${rst.JGYWJC }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">详细地址：</td>
					<td width="35%" class="detail_content" colspan="3">${rst.XXDZ }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">英文地址：</td>
					<td width="35%" class="detail_content" colspan="3">${rst.JGYWXXDZ }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">排序号：</td>
					<td width="35%" class="detail_content" colspan="3">${rst.XH }&nbsp;</td>
				</tr> -->
			</table>
		</td>
	</tr>
</table>
</body>
</html>

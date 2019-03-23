<!-- 
 *@module 系统管理
 *@func 人员信息
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
	<title>人员信息详情</title>
</head>
<body class="bodycss1" >	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">人员编号：</td>
					<td width="35%" class="detail_content">${rst.RYBH}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">姓名：</td>
					<td width="35%" class="detail_content">${rst.XM }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">性别：</td>
					<td width="35%" class="detail_content">${rst.XB}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">身份证号：</td>
					<td width="35%" class="detail_content">${rst.SFZH }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">人员类别：</td>
					<td width="35%" class="detail_content">${rst.RYLB}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">社保基数：</td>
					<td width="35%" class="detail_content">${rst.SBJS }&nbsp;</td>
				</tr>				
				<tr>
					<td width="15%" align="right" class="detail_label">所属部门：</td>
					<td width="35%" class="detail_content">${rst.BMMC }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">所属机构：</td>
					<td width="35%" class="detail_content">${rst.JGMC }</td> 
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >职务：</td>
					<td width="35%" class="detail_content">${rst.ZW }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >公司电话：</td>
					<td width="35%" class="detail_content">${rst.GSDH }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">手机：</td>
					<td width="35%" class="detail_content">${rst.SJ }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">电子邮件：</td>
					<td width="35%" class="detail_content">${rst.DZYJ }&nbsp;</td>
				</tr>
				<tr>
				    <td width="15%" align="right" class="detail_label">人员级别：</td>
					<td width="35%" class="detail_content">${rst.RYJB }&nbsp;</td>					
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.RYZT }&nbsp;</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>
</body>
</html>

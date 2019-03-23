<!-- 
 *@module 分销业务
 *@func 人员信息
 *@version 1.1
 *@author lyg
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
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">人员编号：</td>
					<td width="35%" class="detail_content">${rst.RYBH}&nbsp;</td>
					<td width="15%" align="right"class="detail_label"></td>
					<td width="35%" class="detail_content"></td>
				</tr>
				<!-- 0156572--start--刘曰刚--2014-06-18 -->
				<!-- 详细页面增加用户名 -->
				<tr>
					<td width="15%" align="right"class="detail_label">姓名：</td>
					<td width="35%" class="detail_content">${rst.XM }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">用户名：</td>
					<td width="35%" class="detail_content">${rst.YHM}&nbsp;</td>
				</tr>
				<!-- 0156572--End--刘曰刚--2014-06-18 -->
				<tr>
					<td width="15%" align="right" class="detail_label">性别：</td>
					<td width="35%" class="detail_content">${rst.XB}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">手机：</td>
					<td width="35%" class="detail_content">${rst.SJ }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">部门编号：</td>
					<td width="35%" class="detail_content">${rst.BMBH}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">部门名称：</td>
					<td width="35%" class="detail_content">${rst.BMMC}&nbsp;</td>
				</tr>				
				<tr>
					<td width="15%" align="right"class="detail_label">机构编号：</td>
					<td width="35%" class="detail_content">${rst.JGBH }</td> 
					<td width="15%" align="right"class="detail_label">机构名称：</td>
					<td width="35%" class="detail_content">${rst.JGMC }</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >公司电话：</td>
					<td width="35%" class="detail_content">${rst.GSDH }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">电子邮件：</td>
					<td width="35%" class="detail_content">${rst.DZYJ }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >人员类别：</td>
					<td width="35%" class="detail_content">${rst.RYLB }&nbsp;</td>
					
					<td width="15%" align="right" class="detail_label">人员岗位：</td>
					<td width="35%" class="detail_content">${rst.RYJB }&nbsp;</td>
				</tr>
				<c:if test="${TERM_DECT_CTR_FLAG==1}">
					<tr>
					<td width="15%" align="right"class="detail_label">可改终端最低销售折扣：</td>
					<td width="35%" class="detail_content">${rst.TERM_DECT_FROM}&nbsp;</td>
					<td width="15%" align="right"class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				</c:if>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRENAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRETIME}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">帐套：</td>
					<td width="35%" class="detail_content">${rst.ZTMC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.RYZT }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>

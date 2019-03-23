<!-- 
 *@module 系统管理
 *@func 部门信息
 *@version 1.1
 *@author 吴亚林
 *  -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/zTreeStyle3.css" />
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree.core-3.0.min.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree.excheck-3.0.min.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree.exedit-3.0.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	</head>
	<body class="bodycss1">	
	<table width="100%" hight="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
	<tr>
		<td height="25" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" class="wz">
			<tr>
				<td align="left">
					&nbsp;<input id="refresh" type="button" title="刷新" class="refreshbtn" value="刷新"/>
					&nbsp;<input id="open" type="button" title="展开" class="openbtn" value="展开"/>
					&nbsp;<input id="close" type="button" title="收缩" class="closebtn" value="收缩"/>
			    </td>
			</tr>
		  </table>  
		</td>
	</tr>
	<tr>
		<td valign="top">
			<div class="zTreeDemoBackground left" style="height:100%;width:100%;overflow:auto">
				<ul id="tree" class="ztree"></ul>
			</div>		
		</td>
	</tr>
	</table>
	</body>
</html>
<script type=text/javascript >
var zNodes =${tree}
</script>
<script type=text/javascript src="${ctx}/scripts/core/Jscore-tree.js"></script>
<script type="text/javascript" src="${ctx}/pages/sys/bmxx/bmxx_Tree.js"></script>
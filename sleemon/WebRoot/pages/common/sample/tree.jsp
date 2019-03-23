
<!--  
/**
 * @module 系统管理
 * @fuc 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree-2.6.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/zTreeStyle.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	</head>
	<body style="overflow-x:auto; margin: 0, 0, 0, 0">
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
	<tr>
		<td height="25" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" class="wz">
			<tr>
				<td align="left">
					&nbsp;&nbsp;<input id="refresh" type="button" title="刷新" class="refreshbtn" value="刷新"/>
					&nbsp;<input id="open" type="button" title="展开" class="openbtn" value="展开"/>
					&nbsp;<input id="close" type="button" title="收缩" class="closebtn" value="收缩"/>
			    </td>
			</tr>
		  </table>  
		</td>
	</tr>
	<tr>
		<td valign="top">
			<div class="zTreeDemoBackground">
				<ul id="treeDemo" class="tree"></ul>
			</div>		
		</td>
	</tr>
	<tr>
		<td>以下方法做参考模拟用，正式树中删除<br/>
		  <input id="addChildNode" type="button" title="增加子节点" class="refreshbtn" value="增加子节点"/>
		  <input id="addParentNode" type="button" title="增加父节点" class="refreshbtn" value="增加父节点"/>
		  <input id="removeChildNode" type="button" title="删除子节点" class="refreshbtn" value="删除子节点"/>
		  <input id="removeParentNode" type="button" title="删除父节点" class="refreshbtn" value="删除父节点"/>
		</td>
	</tr>
	</table>
	</body>
</html>
<script type=text/javascript >
var zNodes =${tree}
</script>
<script type="text/javascript" src="${ctx}/pages/common/sample/tree.js"></script>
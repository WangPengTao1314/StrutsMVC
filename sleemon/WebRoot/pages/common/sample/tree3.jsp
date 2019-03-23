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
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/zTreeStyle3.css" />
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree.core-3.0.min.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree.excheck-3.0.min.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree.exedit-3.0.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	</head>
	<body >
	<table width="180" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
	<tr>
		<td height="25" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" class="wz">
			<tr>
				<td align="left">
					&nbsp;<input id="open" type="button" title="展开" class="openbtn" value="展开"/>
					&nbsp;<input id="close" type="button" title="收缩" class="closebtn" value="收缩"/>
			    </td>
			</tr>
		  </table>  
		</td>
	</tr>
	<tr>
		<td height="25" >以下方法做参考模拟用，正式树中删除<br/>
		  <input id="addChildNode" type="button" title="增加子节点" class="refreshbtn" value="增加子节点"/>
		  <input id="addParentNode" type="button" title="增加父节点" class="refreshbtn" value="增加父节点"/>
		  <input id="removeNode" type="button" title="删除节点" class="refreshbtn" value="删除节点"/>
		  <input id="removeChilds" type="button" title="删除子节点" class="refreshbtn" value="删除子节点"/>
		  <input id="modify" type="button" title="修改名称" class="refreshbtn" value="修改名称"/>
		</td>
	</tr>
	<tr>
		<td valign="top" height="90%">
			<div class="zTreeDemoBackground left" style="width:100%;height:100%;overflow-y: auto;padding:2px 5px;">
				<ul id="tree" class="ztree"></ul>
			</div>
		</td>
	</tr>
	
	</table>
	</body>
</html>
<script type=text/javascript >
		var zNodes =${tree};
</script>
<script type="text/javascript" src="${ctx}/pages/common/sample/tree3.js"></script>
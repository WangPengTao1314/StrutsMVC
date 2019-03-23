<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 
 *@module 系统管理
 *@func 文件上传下载 
 *@version 1.1
 *@author zhuxw
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<title>fileupload</title>
</head>

<body>
</body>
<script type="text/javascript">
if(null != "${msg}" && "" != "${msg}"){
	//parent.parent.showErrorMsg("${msg}");
	//modify by zzb 2014-7-21 
	if(isExitsFunction("parent.parent.showErrorMsg")){
		parent.parent.showErrorMsg("${msg}");
	}else{
		parent.showErrorMsg("${msg}");
	}
	
}

</script>

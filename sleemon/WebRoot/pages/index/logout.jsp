<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>退出系统</title>
		<link rel="shortcut icon" href="favicon.ico">
		<meta http-equiv="pargma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<body>
		<%
			if (session.getAttribute("UserBean") != null) {
				session.removeAttribute("UserBean");
			}
			// 复位会话
			session.invalidate();
		%>
		<script language="javascript">
			if(${not empty param.portal}){
				if(${not empty param.portCtx}){			
    				location.href='${param.portCtx}';
    			}else{		
					history.go(-2);
				}
			}else if(window.dialogArguments){ 
    			window.dialogArguments.location.href='${ctx}';	
				window.close();			
			}else if(parent){
    			parent.location.href='${ctx}';				
			}else{
    			location.href='${ctx}';		
			}	
   		</script>
	</body>
</html>

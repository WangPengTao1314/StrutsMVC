<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 1.1
  *@author zhuxw  
 -->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<c:if test="${empty theme }">
<c:set var="theme" value="newTheme"></c:set>
</c:if>
<html>
	<head>
		<title>操作失败页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/msg.css"> 
	</head>
	<body>
		<table width="100%" height="90%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="120">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" height="100px">
					<div style="width: 350px">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" background="${ctx}/styles/${theme}/images/plus/msg_bg.png">
							<tr>
								<td width="15" height="35" >
									&nbsp;
								</td>
								<td valign="bottom" >
									<table width="100%" border="0" cellspacing="0" cellpadding="0" >
										<tr>
											<td height="30">
												<strong>系统警示</strong>
											</td>
											<td width="16"></td>
										</tr>
									</table>
								</td>
								<td >
									&nbsp;
								</td>
							</tr>
							<tr>
								<td >
									&nbsp;
								</td>
								<td>
									<table width="100%" height="40" border="0" align="center"
										cellpadding="0" cellspacing="15">
										<tr>
											<td width="8%">
												<img src="${ctx}/styles/${theme}/images/plus/icon-warning.gif" width="32"
													height="32" />
											</td>
											<td width="92%">
												${msg}
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<input id="ok" type="button" class="btn1" style="background:url(${ctx}/styles/${theme}/images/main/button_bg.gif)" onclick="closeWindow()" value="确 定" />
											</td>
										</tr>
									</table>
								</td>
								<td >
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="16" >
									&nbsp;
								</td>
								<td >
									&nbsp;
								</td>
								<td width="15" >
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
	
		var _submit = false;
		function closeWindow(){
			if(_submit)return;
			_submit=true;
			 
			if(window.dialogArguments){
			    try{window.dialogArguments.closeWindow(); }catch(e){}
				window.close();
			}else{
				history.back();
			}
		}		
		function isOk(){
			return false;
		}
		
/**	
		function getMessage(){
		  return "${msg}";
		}
		
**/		

		var btn=document.getElementById("ok");
		if(btn){
			try{ btn.focus(); }catch(e){}
		}
	</script>
</html>


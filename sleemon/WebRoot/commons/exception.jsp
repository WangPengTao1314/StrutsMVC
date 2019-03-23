<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 1.1
  *@author zhuxw  
 -->
<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>系统异常</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/msg.css">
	</head>
	<body>
		<table width="100%" height="90%" border="0" cellpadding="0" cellspacing="0" class="gridTitle">
			<tr>
				<td height="120">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" height="100px">
					<div style="width: 350px">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15" height="35" class="msg-mb-top-l">
									&nbsp;
								</td>
								<td valign="bottom" class="msg-mb-top-c">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="30">
												<strong>系统异常</strong>
											</td>
											<td width="16"></td>
										</tr>
									</table>
								</td>
								<td class="msg-mb-top-r">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="msg-mb-l">
									&nbsp;
								</td>
								<td class="msg-mb-c">
									<table width="100%" height="40" border="0" align="center"
										cellpadding="0" cellspacing="15">
										<tr>
											<td width="8%">
												<img src="${ctx}/styles/${theme}/images/plus/icon-error.gif" width="32"
													height="32" />
											</td>
											<td width="92%">
												系统发生异常！<br>
												${fn:escapeXml(msg)}
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<input id="ok" type="button" class="btn" onclick="closeWindow()"
													value="确 定" />
											</td>
										</tr>
									</table>
								</td>
								<td class="msg-mb-r">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="16" class="msg-mb-bot-l">
									&nbsp;
								</td>
								<td class="msg-mb-bot-c">
									&nbsp;
								</td>
								<td width="15" class="msg-mb-bot-r">
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
			// 防止重複提交
			if(_submit == true)return;
			_submit=true;
			 
			if(window.dialogArguments){
	    		window.dialogArguments.closeWindow();
				window.close();
			}else{
				history.back();
			}
		}		
		function isOk(){
			return false;
		}
		function getMessage(){
			return "执行错误!";
		}
		var btn=document.getElementById("ok");
		if(btn){
			try{btn.focus();}catch(e){}
		}
	</script>
</html>


<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 1.1
  *@author zhuxw  
 -->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<title>系统提示</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/msg.css"> 
	</head>
	<body>
		<table align="center"  class="panel" width="300px" height="250px" cellpadding="0" cellspacing="0">
			<tr>
				<td height="10%">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td align="center" height="20%">
					<div style="width: 100%">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" background="${ctx}/styles/${theme}/images/index/jsbg.png">
							<tr>
								<td width="15" height="35" >&nbsp;
									
								</td>
								<td valign="bottom" >
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="30">
												<strong>系统提示</strong>
											</td>
											<td width="16"></td>
										</tr>
									</table>
								</td>
								<td >&nbsp;
									
								</td>
							</tr>
							<tr>
								<td  colspan='3'>
									<table width="100%" height="30%" border="0" align="center"
										cellpadding="0" cellspacing="15">
										<tr>
											<td width="8%">
												<img src="${ctx}/styles/${theme}/images/plus/icon-info.gif" width="32"
													height="32" />
											</td>
											<td width="92%">
												${msg}
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<input id="ok" type="button" class="btn" onClick="closeWindow()"
													value="确 定" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="16" >&nbsp;
									
								</td>
								<td >&nbsp;
									
								</td>
								<td width="15">&nbsp;
									
								</td>
							</tr>
						</table>
					</div>
			</tr>
			<tr>
				<td> 
					<form name="form" method="post" action="${paramCover._backUrl }">
					<!-- 审核通过的时候点击确认，form提交虽然是post方式但是后台还要解码  -->
					 <input type="hidden" id="doGet" name="doGet" value="doGet" label="解码标识"/>
						${paramCover.decodeInputs}
						&nbsp;
					</form> 
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
		var _submit = false;
		function closeWindow(){
			// 防止重複提交
			if(_submit)return;
			_submit=true;
			 
			if(window.dialogArguments){
	    		window.dialogArguments.closeWindow();
				window.close();
			}else if(${empty paramCover._backUrl}){
				history.back();
			}else {
			    form.action=utf8(form.action);
				form.submit();
			}
		}		
		function isOk(){
			return true;
		}
		function getMessage(){
			return "${msg}";
		}
		var btn=document.getElementById("ok");
		if(btn){
			try{ btn.focus(); }catch(e){}
		}
	</script>
</html>


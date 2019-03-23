<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>货品图片</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		body{ 
			margin:0; 
			padding:0; 
		}	
		div{ 
			width:100%; 
			height:100%; 
			margin:0 auto; 
		}/*这里的width height 大于图片的宽高*/	
		table{ 
			height:100%; 
			width:100%; 
			text-align:center;
		} 
	</style>
  </head>
  
  <body>
    <div>
		<table>
			<tr>
				<td>
					<img src='${fileName }'>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>

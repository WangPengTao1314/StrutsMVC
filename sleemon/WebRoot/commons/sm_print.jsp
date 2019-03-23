<!-- 
 * @module 报表管理
 * @fuc 报表打印
 * @version 1.0
 * @author 邢克罚
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqian/runqianReport4.tld" prefix="report"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>
<link rel="stylesheet" type="text/css" href="${ctx}/report/reportcomm/css/style.css">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>
		</title>
	
		<%
 			//报表路径名称 如【../reportFile/*.raq】
 			String reportFileName = request.getAttribute("reportFileName")
 					.toString();
 			//要传递的宏参数！！！
 			String params = request.getAttribute("params").toString();
 		%>
	</head>
	<body > 
		<script>
			var width = '90px';
		</script>
		<div style="width:100%;height:100%">
		<table align="center" width="100%" height="100%">
			<tr>
				<td width="100%" align="center">
					<report:html  name="report1"
					    reportFileName="<%=reportFileName%>"
		                funcBarLocation="top"
			            needPageMark="yes"
			            generateParamForm="no"
			            params="<%=params%>"
			            needSaveAsExcel="yes"
			            excelLabel="<span style='height:40px'></span><input type='button' class='btn' value='导出EXCEL'>"
			            needPrint="yes" 
			            printLabel="<span style='height:40px'></span><input type='button' class='btn' value='打  印'>"
			            exceptionPage="/report/reportcomm/reportError.jsp"
			            appletJarName="runqianReport4Applet.jar,dmGraphApplet.jar"
			            needScroll="yes" 
						scrollWidth="1024px" 
						scrollHeight="90%"
                       />
				</td>
			</tr>
		</table>
		</div>
	</body>
	<script type="text/javascript">
<!--  needPrint="yes" printLabel="<span style='height:40px'></span><input type='button' class='btn' value='打  印'>"-->
		//打印页面需要创建countPrint方法
//		function countPrint(){
//			$("#queryForm").submit();
//		}		
	function window.onbeforeunload(){
		    window.opener.countPrint();
		}
</script>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.hoperun.commons.util.StringUtil"%>

<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqian/runqianReport4.tld" prefix="report"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>

<html>
<head><link type="text/css" href="css/style.css" rel="stylesheet"/></head>
<link rel="stylesheet" type="text/css" href="${ctx}/report/reportcomm/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0 style="overflow:hidden;">
<%
	request.setCharacterEncoding( "UTF-8" );
	String action = request.getParameter("action");
	if(null == request.getAttribute("raq")){
		try {
			request.getRequestDispatcher("/intentionvisit.shtml?action=toChild").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	String report = StringUtil.nullToSring(request.getAttribute("raq"));
	String param = StringUtil.nullToSring(request.getAttribute("params"));
    String STATE = StringUtil.nullToSring(request.getAttribute("STATE"));
	//以下代码是检测这个报表是否有相应的参数模板
	//String reportFileHome = Context.getInitCtx().getMainDir();
	//String paramFile = report;
	//File f = new File(application.getRealPath(reportFileHome+ File.separator +paramFile));

%>
 
<div style="overflow:auto;height:99%;">
<table id="rpt" align="left" ><tr><td>
<%--<%	//如果参数模板存在，则显示参数模板
	if( f.exists() ) {
		System.out.println("--------");
	%>
	<table id="param_tbl" width="100%" height="100%"><tr><td>
 
		<report:param name="form1" paramFileName="<%=paramFile%>"
			needSubmit="no"
			params="<%=param.toString()%>"
			
		/>
	</td>
	<td><a href="javascript:_submit( form1 )"><img src="../images/search.gif" border=no style="vertical-align:middle"></a></td>
	</tr></table>
	<% }
%>

--%><table align="center" width="100%" height="100%" >
	<tr><td>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation=""
			needPageMark="yes"
			generateParamForm="no"
			params="<%=param.toString()%>"
			needPivot="yes"
			pivotLabel=""
			exceptionPage="/reportJsp/myError2.jsp"
			appletJarName="runqianReport4Applet.jar,dmGraphApplet.jar"
			pageMarkLabel=""
			firstPageLabel=""
            prevPageLabel=""
            nextPageLabel=""
            lastPageLabel=""

		/>
	</td></tr>
	<%
	if("未提交".equals(STATE)){
	%>
	   <input id="query" type="button" class="btn" value="提交" style="width: 50px;"  onclick="saveReport();"> 
  <% 
	}
  %>
</table>
</div>
<script language="javascript">
	//设置分页显示值
	//document.getElementById( "t_page_span" ).innerHTML=report1_getTotalPage();
	//document.getElementById( "c_page_span" ).innerHTML=report1_getCurrPage();

	  /*
	   *提交
	   */
	  function saveReport(){
			report1_save();
			return false;
	  }
</script>
</body>
</html>

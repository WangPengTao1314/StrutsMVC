
<%@ page language="java"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
//@ page import="foundation.util.*,butone.common.privilege.util.QXComm,butone.common.privilege.ui.UserBean" 
%>
<%
    String XMLType  = "";
    //TranCodeBean.tranCodeG(request.getParameter("type"));
    
    String waitTime = "1000";
  //  waitTime = TranCodeBean.tranCodeG(request.getParameter("waitTime"));
    if (XMLType.equals("wl")) {
      waitTime = "1000";
    }
    
    if (XMLType.equals("oa")) {
      waitTime = "400";
    }
    int waitTimeNumber = Integer.parseInt(waitTime);
    //waitTimeNumber = waitTimeNumber;
    
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重构权限</title>
</head>

<body leftmargin="0" topmargin="0">
<div id="showLayer" style="display:true">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td><form name=loading>
                    　
                    <p align=center> <font color="#0066ff" size="2">载入中</font><font color="#0066ff" size="2" face="Arial">...</font>　 
                      <input type=text name=chart size=46 style="font-family:Arial; font-weight:bolder; color:#0066ff; background-color:#fef4d9; padding:0px; border-style:none;">
                      　　 　　
                      <input type=text name=percent size=47 style="color:#0066ff; text-align:center; border-width:medium; border-style:none;">
                      　　
                      <script>　 
var bar=0　 
var line="||"　 
var amount="||"　 
count()　 
function count(){　 
bar=bar+2　 
amount =amount + line　 
document.loading.chart.value=amount　 
document.loading.percent.value=bar+"%"　 
if (bar<99)　 
{setTimeout("count()",<%=waitTimeNumber%>);}　 
else　 
{ 
   showLayer.style.display = "none";
   document.all['topcontent'].style.display='inline';

 
}　 
}</script>
                      　</p>
                  </form></td>
              </tr>
            </table>
            </div>
<iframe  name="topcontent" id="topcontent" width="100%" height="80%" frameborder="0" src="readxmlcommon.jsp?SYSID=<%=XMLType%>" style="display:none"></iframe>
</body>
</html>

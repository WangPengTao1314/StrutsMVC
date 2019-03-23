<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="com.hoperun.commons.util.StringUtil,java.util.Vector,java.util.Hashtable,com.hoperun.commons.model.*, com.hoperun.commons.select.object.*"%>

<%
	TableObject tableObject = (TableObject)request.getAttribute("TableObject");
	Vector vec = tableObject.getValues();
	PageInfoVO aPageInfoVO = (PageInfoVO)vec.get(vec.size()-1);
	
%>
<script language="JavaScript">
top.returnValue = new Array();
var isChanged = false;
<%if(vec.size()!=2){%>
	top.returnValue[0]=0;
	top.returnValue[1]=false;
	top.close();
<%}else{
	Hashtable ht = (Hashtable)vec.get(0);
	String frmName = request.getParameter("frmName");
	String keyElems = request.getParameter("keyElems");
	String keyNames = request.getParameter("keyNames");
	String selCommElems = request.getParameter("selCommElems");
	String selCommFields = request.getParameter("selCommFields");
	String elems = keyElems + "," + selCommElems;
	String fields = keyNames + "," + selCommFields;
	java.util.StringTokenizer stk1 = new java.util.StringTokenizer(elems,",",false);
	java.util.StringTokenizer stk2 = new java.util.StringTokenizer(fields,",",false);
	while(stk1.hasMoreTokens()){
		String elem = stk1.nextToken();
		String tarStr = StringUtil.replace((String)ht.get(stk2.nextToken()), "\\", "\\\\");
		tarStr = StringUtil.replace(tarStr, "\"", "\\\"");
%>
		parent.obj.document.<%=frmName%>.<%=elem%>.value="<%=tarStr%>";
		if("<%=tarStr%>"!=parent.obj.document.<%=frmName%>.<%=elem%>.oldValue){
				isChanged = true;
		}
		parent.obj.document.<%=frmName%>.<%=elem%>.oldValue="<%=tarStr%>";
	<%}%>
	top.returnValue[0]=1;
	top.returnValue[1]=isChanged;
	top.close();
<%}%>
</script>

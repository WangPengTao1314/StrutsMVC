<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ page import="com.hoperun.commons.util.StringUtil,com.hoperun.commons.select.object.*" %>
<%
	TableObject tableObject = (TableObject)request.getAttribute("TableObject");
	String showQYTree = StringUtil.tranCodeP(request.getParameter("showQYTree"));
%>
<html>
<head>
<title>通用选择 -- <%=tableObject.getTableLgName()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
<script language="JavaScript">
	var obj = dialogArguments;
	function countSelected(){
		document.all("selectMsg").innerHTML="现有<b style='color:red'>"+keyElemValues[0].length+"</b>条记录被选择";
		if(keyElemValues[0].length>0){
			document.all("selectMsg").innerHTML+="<a href=\"#implied\" onClick=\"clearAll()\">清空</a>";
		}
	}

	function clearAll(){
	    if(!confirm("确定要清空所有已选择的记录吗？")){
	    	return;
	    }
		for(var i=0;i<keyElemValues.length;i++){
			keyElemValues[i] = new Array();
		}
		for(var i=0;i<selCommElemValues.length;i++){
			selCommElemValues[i] = new Array();
		}
		countSelected();
		document.forms[0].submit();
	}
	
	function eventSubmit(){
		var tmp = obj.selCommArgs;
		document.forms[0].selCommId.value     = obj.selCommArgs[0];
		document.forms[0].tableId.value       = obj.selCommArgs[1];
		document.forms[0].selectType.value    = obj.selCommArgs[2];
		//document.forms[0].keyElems.value    	= obj.selCommArgs[3];
		document.forms[0].keyNames.value      = obj.selCommArgs[4];
		//document.forms[0].frmName.value     = obj.selCommArgs[5];
		//document.forms[0].selCommElems.value	= obj.selCommArgs[6];
		document.forms[0].selCommFields.value = obj.selCommArgs[7];
		document.forms[0].oldCndt.value       = obj.selCommArgs[8];
		document.forms[0].specialTable.value  = obj.selCommArgs[9];
		document.forms[0].isShowSearchLayer.value  = obj.selCommArgs[10];
		document.forms[0].specialAsTable.value  = obj.selCommArgs[11];
		if(obj.selCommArgs[12]!=null&&<%=tableObject.getSelectFlag()%>){
			document.forms[0].searchCondition.value  = obj.selCommArgs[12];
		}
		document.forms[0].showQYTree.value  = '<%=showQYTree%>';
		countSelected();
		top.returnValue = new Array();
		top.returnValue[0] = keyElemValues[0].length;
		top.returnValue[1] = false;
		mins = 0;
		secs = 0;
		document.all('waitlayer').style.display="inline";
		document.all('selComm').style.display="none";
		document.forms[0].submit();
	}

	var keyValues = new Array();
	var frmName = obj.selCommArgs[5];
	var keyElems = obj.selCommArgs[3];
	var keyElemValues = new Array();
	var selCommElems = obj.selCommArgs[6];
	var selCommElemValues = new Array();

	var arrKeyElems = keyElems.split(",");
	var arrSelCommElems = selCommElems.split(",");
	for(var i=0;i<arrKeyElems.length;i++){
		keyElemValues[i] = new Array();
		var tmpobj = eval("obj.document."+frmName+"."+arrKeyElems[i]);
		var tmp = "";
		if(tmpobj.className==null||tmpobj.className.indexOf("selcomm")==-1){
			tmp = tmpobj.value;
		}else{
			tmp = tmpobj.oldValue;
		}
		tmp=tmp+"";
		if(tmp!=''){
			keyElemValues[i] = tmp.split(",");
		}
	}

	for(var i=0;i<arrSelCommElems.length;i++){
		selCommElemValues[i] = new Array();
		var tmpobj = eval("obj.document."+frmName+"."+arrSelCommElems[i]);
		var tmp = "";
		if(tmpobj.className==null||tmpobj.className.indexOf("selcomm")==-1){
			tmp = tmpobj.value;
		}else{
			tmp = tmpobj.oldValue;
		}
		tmp=tmp+"";
		if(tmp!=''){
			selCommElemValues[i] = tmp.split(",");
		}
	}

	function copyToParent(){
		var isChanged = false;
		for(var i=0;i<arrKeyElems.length;i++){
			var tmp = tranArrToStr(keyElemValues[i]);
			var tmpobj = eval("obj.document."+frmName+"."+arrKeyElems[i]);
			if(tmpobj.value != tmp) isChanged = true;
			tmpobj.value = tmp;
			if(tmpobj.seltarget!=""&&tmpobj.seltarget!=null)
			{
			  tmpobj.oldValue = tmp;
			}
			//if(tmpobj.className!=null&&tmpobj.className.indexOf("selcomm")>=0){
			//	tmpobj.oldValue = tmp;
			//}
		}
		for(var i=0;i<arrSelCommElems.length;i++){
			var tmp = tranArrToStr(selCommElemValues[i]);
			var tmpobj = eval("obj.document."+frmName+"."+arrSelCommElems[i]);
			tmpobj.value = tmp;
			if(tmpobj.seltarget!=""&&tmpobj.seltarget!=null)
			{
			  tmpobj.oldValue = tmp;
			}
			
			//if(tmpobj.className!=null&&tmpobj.className.indexOf("selcomm")>=0){
			//	tmpobj.oldValue = tmp;
			//}
		}
		top.returnValue[0] = keyElemValues[0].length;
		top.returnValue[1] = isChanged;
		top.close();
	}

	function tranArrToStr(arr){
		if(arr==null||arr.length==0) return "";
		var tmpstr = arr[0];
		for(var i=1;i<arr.length;i++){
			tmpstr = tmpstr + "," + arr[i];
		}
		return tmpstr;
	}

	function switchView(obj){
		var cmdForm = selComm.MainList.document.forms[0];
		selComm.MainList.document.forms[0].target="MainList";
		if(cmdForm.cmdFlag.value=="MainList"){
			cmdForm.cmdFlag.value="ShowTree";
		}else	if(cmdForm.cmdFlag.value=="ShowTree"){
			cmdForm.cmdFlag.value="MainList";
		}
		cmdForm.submit();
		obj.disabled=true;
		mins = 0;
		secs = 0;
		document.all('waitlayer').style.display="inline";
		document.all('selComm').style.display="none";
	}

	function searchProp(){
		var propSearchCon = window.showModalDialog("butone.common.groupware.plugin.ui.PropSearchServlet?cmdFlag=ShowFrame",window,"dialogHeight:600px;dialogWidth:800px;help:no;scroll:no;status:no;center:yes;resizable:no");
		if(propSearchCon!=null&&propSearchCon!=""){
			document.forms[0].searchCondition.value=propSearchCon;
			document.forms[0].cmdFlag.value="MainFrame";
			mins = 0;
			secs = 0;
			document.all('waitlayer').style.display="inline";
			document.all('selComm').style.display="none";
			document.forms[0].submit();
		}
	}
	try{
		top.dialogHeight="600px";
		top.dialogWidth="800px";
		top.dialogTop = screen.height/2-600/2+"px";
		top.dialogLeft = screen.width/2-800/2+"px";
	}catch(e){}
</script>
</head>
<body style="margin:0px 0px 0px 0px;overflow:hidden" onLoad="eventSubmit()">
<form target="selComm" method="post" action="${ctx}/common/select.shtml?action=doPost" style="display:none">
<input type="hidden" name="cmdFlag" value="MainFrame">
<input type="hidden" name="selCommId">
<input type="hidden" name="tableId">
<input type="hidden" name="selectType">
<input type="hidden" name="keyNames">
<input type="hidden" name="selCommFields">
<input type="hidden" name="oldCndt">
<input type="hidden" name="specialTable">
<input type="hidden" name="specialAsTable">
<input type="hidden" name="isShowSearchLayer">
<input type="hidden" name="searchCondition" value="">
<input type="hidden" name="showQYTree" value="">
</form>
<div class="mdiv" style="width:790px;height:540px" id="waitlayer">
	<table width="100%" height="100%">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center" style="font-family:宋体; font-size:15px;font-weight:bold">
				正在加载......(如果长时间没有响应，请与系统管理员联系！)<br>
				已用时间：<span id="elapsedtime">00:00</span>
				<script language="Javascript">
					var mins = 0;
					var secs = 0;
					function drawtime(){
						secs++;
						if(secs==60){
							mins ++;
							secs = 0;
						}
						elapsedtime.innerHTML=(mins<10?"0"+mins:mins)+":"+(secs<10?"0"+secs:secs);
					}
					setInterval('drawtime()',1000);
				</script>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</div>
<iframe src="" name="selComm" id="selComm" width="790" height="540" frameborder="0" style="display:none"></iframe>
<table width="100%" border="0" align="center">
  <tr>
  	<td align="left" id="selectMsg" style="font-size:12px;height:10px" width="140"></td>
    <td align="center">
      <input type="button" class="button" name="butOk" value=" 确定(O) " title="Alt+O" accesskey="O" onclick="copyToParent()">
      <%if(tableObject.getIsWLXX()){%>
      <input type="button" class="button" name="butPro" value=" 配件属性查询(P) " title="Alt+P" accesskey="P" onclick="searchProp()">
      <%}%>
      <%if(tableObject.getIsTree()||tableObject.getIsWLDWXX()){%>
      <input type="button" class="button" name="butView" disabled value=" 树型(T) " title="Alt+T" accesskey="T" onclick="switchView(this)">
      <%}%>
      <input type="button" class="button" name="butCancel" value=" 取消(C) " title="Alt+C" accesskey="C" onclick="top.close()">
    </td>
    <td width="120">&nbsp;</td>
  </tr>
</table>
</body>
</html>

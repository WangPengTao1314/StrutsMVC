<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
<head>
<title>等待</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
<script language="JavaScript">
	var obj = dialogArguments;
	function eventSubmit(){
		var tmp = obj.selCommArgs;
		document.forms[0].selCommId.value     = obj.selCommArgs[0];
		document.forms[0].tableId.value       = obj.selCommArgs[1];
		document.forms[0].selectType.value    = obj.selCommArgs[2];
		document.forms[0].keyElems.value    = obj.selCommArgs[3];
		document.forms[0].keyNames.value      = obj.selCommArgs[4];
		document.forms[0].frmName.value     = obj.selCommArgs[5];
		document.forms[0].selCommElems.value	= obj.selCommArgs[6];
		document.forms[0].selCommFields.value = obj.selCommArgs[7];
		document.forms[0].oldCndt.value       = obj.selCommArgs[8];
		document.forms[0].specialTable.value  = obj.selCommArgs[9];
		document.forms[0].isShowSearchLayer.value  = obj.selCommArgs[10];
		document.forms[0].specialAsTable.value  = obj.selCommArgs[11];
		if(obj.selCommArgs[12]!=null){
			document.forms[0].searchCondition.value  = obj.selCommArgs[12];
		}
		document.forms[0].submit();
	}
</script>
</head>
<body style="margin-top:0px; margin-left:0px;margin-right:0px;overflow:hidden" onLoad="eventSubmit()">
<br>
<br>
<p align="center">请稍候......</p>
<br>
<form target="selComm" method="post" action="${ctx}/common/select.shtml?action=doPost" style="display:none">
<input type="hidden" name="cmdFlag" value="MainList">
<input type="hidden" name="preSearch" value="T">
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
<input type="hidden" name="frmName">
<input type="hidden" name="keyElems" value="">
<input type="hidden" name="selCommElems" value="">
</form>
<iframe src="" name="selComm" id="selComm" frameborder="0" style="display:none"></iframe>
</body>
</html>

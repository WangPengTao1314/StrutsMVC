<%@ page errorPage="/commons/failure.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ page language="java"%>

<%
//    String MKBHWH = "601010302";
//    QXBean.checkMKQX(session, MKBHWH, response);
String Close = request.getParameter("Close");
 //  String CloseBJ = TranCodeBean.tranCodeG(request.getParameter("CloseBJ"));
   String flowModelId =request.getParameter("flowModelId");
 //  //// ("flowModelId="+flowModelId);
    ////// ("CloseBJ="+CloseBJ);
//   String flowModelId1=request.getParameter("flowModelId");

//   String COL = TranCodeBean.tranCodeG(request.getParameter("COL"));
%>

<html>
<head>
<title>新增模板通用查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
     function addLoad(COL){
	 

         if("<%=Close%>" == "T") {
             alert('新增记录成功！');
             alert(opener);
			opener.refreshSelf(COL);
             window.opener=null;
             window.close();
         }
         else
             document.forms[0].CXMC.focus();
     }

     function checkInput() {
		if (!CheckIsNull("insertForm","CXMC", "通用查询名称不可为空！")){
			document.insertForm.CXMC.focus;
			return false;
		}
		if (!CheckIsNull("insertForm","MC", "名称不可为空！")){
			document.insertForm.MC.focus;
			return false;
		}
		
/*
     	if(!CheckIsNumber("forms[0]","SJSM","输入的数据项编号应为数字！")){
			document.forms[0].SJSM.focus();
			return false;
     	}
*/
        
         document.insertForm.submit();
     }
/**
	function CheckChar(formName, itemName, itemMessage){
	  eval('var temp=document.'+formName+'.'+itemName+'.value');

	  tempStr = temp;
	  var flag = 0;

	  for(var i=0;i<tempStr.length;i++){
			var tempSub=tempStr.substring(i,i+1);
			var compareStr="!@$^&*%/*+'\"";
			if (compareStr.indexOf(tempSub)>=0){
					flag=1;
					break;
			}
		}

	  if(flag==0){
	    return true;
	  }else{
	    alert(itemMessage);
	    return false;
	  }
	}
	*/
</script>
</head>

<body onload="addLoad('<%=flowModelId%>')">
<form name="insertForm" method="post" action="../../../servlet/butone.oa.model.FlowModeServlet?oprationType=1">
<input type="hidden" name="COL" value="">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
<input type="hidden" name="sel_condition1" value=" 1=1">
<input type="hidden" name="FLOWMODELID" value="<%=flowModelId%>">
 <tr>
    <th colspan="4">新增</th>
  </tr>
  <tr>
  <tr>
    <td colspan="4" height="10"></td>
  </tr>
  <tr>
    <td style="color:red" align="right">模块查询编号：</td>
    <td><input name="FLOWMODELID" type="text" maxlength="15" size="36" value="系统自动生成" disabled></td>
  </tr>
   <tr>
    <td colspan="4" height="10"></td>
  </tr>
  <tr>
     <td bgcolor="#f3f3f3" align="right" style="color:red">通用查询名称：</td>
     <td>
	 <input type="hidden"   name="TYCXID"  size="20" value="" readonly>
	<input type="text"   name="CXMC"  size="20" value="" readonly>
        <img align="absmiddle" style="cursor:hand" src="../../../images/select.gif" name="selJGXX"
         onClick="selCommon('76',false,'TYCXID','TYCXID','insertForm','TYCXID,CXMC','TYCXID,CXMC','sel_condition1');"> 
	 </td>
  </tr>
  <tr>
    <td colspan="4" height="10"></td>
  </tr>
  <tr>
    <td bgcolor="#f3f3f3" align="right" style="color:red">名称：</td>
    <td><input name="MC" type="text"  id="MC" size="36" maxlength="15"></td>
  </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td width="35%"></td>
    <td>
      <input type="button" name="button1" value="保存(S)" class="button" onClick="return checkInput();" accessKey="s">
      <input type="reset" name="button2" value="重置(R)" accessKey="r"class="button">
     </td>
  </tr>
</table>
</form>
</body>
</html>
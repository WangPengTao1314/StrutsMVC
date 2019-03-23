<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
<title>审签</title>
 <link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
</head>
<body style="background:url(${ctx}/styles/${theme}/images/index/shbg.png)" bgcolor="#FFFFFF" text="#000000" leftMargin=0 topMargin=0 rightMargin=0 bottomMargin=0 >
<form name="updform" method="post" action="${ctx}/system/flow.shtml?action=Auditing">
  <table width="98%" cellspacing="0" cellpadding="0">
  <tr>
    <td height="52"></td>
  </tr>
  <tr>
    <th height="12">审签</th>
  </tr>
  <tr>
    <td height="20"></td>
  </tr>
</table>
  <table width="98%" border="0" cellspacing="0" cellpadding="0" >
    <tr>
      <td width="80" align="right" >审签意见：</td>
      <td  colspan="3"><textarea style="background:url(${ctx}/styles/${theme}/images/index/yjk.png)" cols=56 rows=8 name="operationContent" id="operationContent"></textarea></td>
    </tr>
    <tr>
     <td width="80" align="right" ></td>
     <td><td><font color='red'>提示&nbsp;&nbsp;:&nbsp;&nbsp;签批意见不得超过400字节</font></td></td>
    </tr>
    <tr >
      <td align="right" >审签结果：</td>
      <td  colspan="3">
				<table border="0" cellpadding="0" cellspacing="0" id="operationTb">
<!---1:通过；-1：否决； 2：回退； 3：跳签；----------->
					<tr height="30">
						<td width="60" align="center" >通过<input type="radio" class="noborder" style="border:0px" name="operationFlag" value="1" checked></td>
						<%
							//if (proNode.getCancleFlag().equals("1")) {
						%>
						<td width="140"  >&nbsp;&nbsp;&nbsp;&nbsp;否决（退至申请人）<input type="radio" class="noborder" style="border:0px" name="operationFlag" ${cancleDis}  value="-1"></td>
						<%//}
							//if (proNode.getBackFlag().equals("1")) {
						%>
						<td width="80"  >&nbsp;&nbsp;&nbsp;&nbsp;回退<input type="radio" class="noborder" style="border:0px" name="operationFlag" ${backDis}  value="2" onClick="selNode(2)"></td>
						<%
							//}
							//if (proNode.getJumpFlag().equals("1")) {
						%>
						<td width="90" style='display:none'>&nbsp;&nbsp;&nbsp;&nbsp;跳签<input type="radio" class="noborder" style="border:0px" name="operationFlag"  ${jumpDis}  value="3" onClick="selNode(3)"></td>
						<%
							//}
						%>
					</tr>
				</table>
			</td>
		</tr>
		<!--  这个功能暂时先不做，以后等有时间再说-->
		
		<!--  tr ${expandstyle} height="30"-->
		
		<tr style='display:none'  height="30">
			<td align="right">加&nbsp;&nbsp;&nbsp;&nbsp;签：</td>
			<td colspan='3'>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="90" align="center">人员<input type="radio" class="noborder" style="border:0px" name="operationFlag" value="4" onClick="addnode(0)"></td>
						<td width="90">&nbsp;&nbsp;&nbsp;&nbsp;部门<input type="radio" class="noborder" style="border:0px" name="operationFlag" value="5" onClick="addnode(1)"></td>
						<td width="90">&nbsp;&nbsp;&nbsp;&nbsp;角色<input type="radio" class="noborder" style="border:0px" name="operationFlag" value="6" onClick="addnode(2)"></td>
						<td width="90">&nbsp;&nbsp;&nbsp;&nbsp;工作组<input type="radio" class="noborder" style="border:0px" name="operationFlag" value="7" onClick="addnode(3)"></td>
					</tr>
				</table>
			</td>
    </tr>
		<tr height="30">
			<td align="right">是否提醒：</td>
			<td colspan='3'>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="90">&nbsp;&nbsp;&nbsp;&nbsp;提醒<input type="checkbox" checked="checked" class="noborder" style="border:0px" name="remind" value="0" ></td>
						<!-- td width="90">&nbsp;&nbsp;&nbsp;&nbsp;提醒<input type="checkbox" class="noborder" style="border:0px" name="remind" value="1" checked></td-->
					</tr>
				</table>
			</td>
    </tr>
  </table>
	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	  <tr height="20"><td></td>
	  </tr>
	  <tr>
	    <td align="center">
	    <c:if test="${canAuditing}">
	      <input type="button" style="background:url('${ctx}/styles/${theme}/images/index/dlbt.png')" name="Submit1" value=" 确定(S) " accesskey="s"  onclick="beforeSubmit(); if (submitresult == true) document.updform.Submit1.disabled=true;">&nbsp;
	    </c:if>
	      <input type="button" style="background:url('${ctx}/styles/${theme}/images/index/dlbt.png')" name="Submit2" value=" 关闭(X) " accesskey="x"  onclick="top.window.opener=null;top.window.close();">
	    </td>
	  </tr>
	</table>
<input name="flowInstanceId" type="hidden" value="${flowInstanceId}">
<input name="instanceNodeId" type="hidden" value="${instanceNodeId}">
<input name="flowInterfaceName" type="hidden" value="${flowInterfaceName}">
<input name="ztbgFieldName" type="hidden" value="${ztbgFieldName}">
<input name="businessType" type="hidden" value="${businessType}">
<input name="nextNodeId" type="hidden">
<input name="nextNodeType" type="hidden">
<input type="hidden" name="OperID">
<input type="hidden" name="BackFlag">
<input type="hidden" name="JumpFlag">
<input type="hidden" name="ExpandFlag">
<input type="hidden" name="EditFlag">
<input type="hidden" name="CancleFlag">
<input type="hidden" name="AgentFlag">
<input type="hidden" name="DelayFlag">
<input type="hidden" name="AllFlag">
<input type="hidden" name="DelayDay">
<input type="hidden" name="nodeOperationId">
<input type="hidden" name="OperType">
<input type="hidden" name="zwtzm">
<input type="hidden" name="zwverify" value="false">
<input type="hidden" name="operateFlag" value="auditing">
</form>
<script language="JavaScript">
    //点击审签结果的时候 在审签意见添加意见  add by zzb 2015-02-11
    $(function(){
       var defaultM = $("#operationTb input[name='operationFlag']:checked");
       clickAdvise(defaultM);
       $("#operationTb input[name='operationFlag']").click(function(){
    	   clickAdvise(this);
       });
    });
    
    function clickAdvise(obj){
       if(null == obj){
    	   return;
       }  
   	   var v = $(obj).val();
   	   var array = ["同意","否决","回退"];
   	   var con = "";
   	   if(1 == v){
   		   con = "同意";
   	   }
   	   if(-1 == v){
   		   con = "否决";
   	   }
   	   if(2 == v){
   		   con = "回退";
   	   }
   	   
   	   var oldV = $("#operationContent").val();
   	   oldV = $.trim(oldV);
   	   var index = 0;
   	   for(var i=0;i<array.length;i++){
   		   //没有或者意见内容当中含有关键字
   		    if(oldV.indexOf(array[i]) == -1 || oldV.indexOf(array[i])>1){
    		   index = index+1;
    	    } 
   	   }
   	   if(index == 3){
   		   $("#operationContent").val(con+" "+oldV);
   	   }else{
   		   oldV = oldV.substring(2,oldV.length);
   	   }
   	   oldV = $.trim(oldV);
   	   $("#operationContent").val(con+" "+oldV); 
    }
    //=========================================
    
    
	var submitresult = false;
	function beforeSubmit() {
		var v = $("#operationContent").val();
		v = $.trim(v);
		if(null == v || "" == v){
			alert("请填写审签意见！");
			return false;
		}
		var operaName = '${operName}';
		if (document.updform.operationFlag[4].checked == true
			|| document.updform.operationFlag[5].checked == true
			|| document.updform.operationFlag[6].checked == true
			|| document.updform.operationFlag[7].checked == true) {
			if (document.updform.OperID.value == "" || document.updform.nodeOperationId.value == "") {
				alert("没有选择加签对象！");
				return false;
			}
		}
		if (document.updform.operationFlag[2].checked == true) {
			if (document.updform.nextNodeId.value == "") {
				alert("请选择回退节点！");
				selNode(2);
				return false;
			}
		}
		if (document.updform.operationFlag[3].checked == true) {
			if (document.updform.nextNodeId.value == "") {
				alert("请选择跳签节点！");
				selNode(3);
				return false;
			}
		}
		var operationFlag;
		for (i=0; i<8; i++) {
			if (document.updform.operationFlag[i].checked == true) {
				operationFlag = document.updform.operationFlag[i].value;
			}
		}
		if (operationFlag == null) {
			alert("请选择审签结果！");
			return false;
		}
		if (operationFlag == null) {
			alert("请选择审签结果！");
			return false;
		}
		var content = document.updform.operationContent.value;
		content = repaceSpecChar(content);
		if (stringLength(content)  > 400) {
			 alert("签批意见不得超过400字节！");
			 return false;
		}
		content = charFilter(content);

		for (i=0; i<document.updform.operationFlag.length; i++) {
			if (document.updform.operationFlag[i].checked == true) {
				if (document.updform.operationFlag[i].value > 3) {
					document.updform.operateFlag.value = "addNode";
				}
			}
		}
		if( document.updform.remind.checked==true)
		{
		 document.updform.remind.value='1';
		}
		submitresult = true;
		document.updform.operationContent.value = content;
		document.updform.submit();
	}

	function selNode(flag) {
		var resu;
		document.updform.nextNodeId.value = "";
		var flowInstanceId = document.updform.flowInstanceId.value;
		var instanceNodeId = document.updform.instanceNodeId.value;
		if (flag == 2) {
			if (document.updform.operationFlag[2].checked == true) {
				resu = window.showModalDialog('${ctx}/system/flow.shtml?action=toselNode&flowInstanceId='+flowInstanceId+'&instanceNodeId='+instanceNodeId+'&flag='+flag,'','dialogwidth=400px; dialogheight=200px; status=no; scrollbar=yes');
			
			
			}
		} else if (flag == 3) {
			if (document.updform.operationFlag[3].checked == true) {
				resu = window.showModalDialog('${ctx}/system/flow.shtml?action=toselNode&flowInstanceId='+flowInstanceId+'&instanceNodeId='+instanceNodeId+'&flag='+flag,'','dialogwidth=400px; dialogheight=200px; status=no; scrollbar=yes');
			}
		}
		if (resu != null) {
			document.updform.nextNodeId.value = resu[0];
			document.updform.nextNodeType.value = resu[1];
		}
		return false;
	}

	function addnode(fakeType) {
		updform.OperID.value = "";
		updform.nodeOperationId.value = "";
		var choose=window.showModalDialog('add_modelNode.jsp?event=SelectNodeEvent&OperType='+fakeType,'','dialogwidth=450px; dialogheight=260px; status=no');
		if (choose!=null) {
			updform.OperID.value =  choose[0];
			updform.BackFlag.value = choose[2];
			updform.JumpFlag.value =  choose[5];
			updform.ExpandFlag.value = choose[8];
			updform.EditFlag.value = choose[3];
			updform.CancleFlag.value = choose[4];
			updform.AgentFlag.value = choose[6];
			updform.DelayFlag.value = choose[9];
			updform.AllFlag.value = choose[7];
			updform.DelayDay.value = choose[10];
			updform.nodeOperationId.value = choose[11];
			updform.OperType.value = fakeType;
		} else {
			window.status="没有选择任何节点操作者，不能加签";
		}

	}


	function submitRegister()
	{
		if (zkonline.Register()){

    		 document.detailForm.ZKKey.value = zkonline.RegisterTemplate;
    		 document.detailForm.cmdFlag.value = "RecodeZK";
    		 return true;
		 }else {
		   alert("采集该用户的指纹失败！");
		   return false;
		 }
	}

	function submitVerify()
	{
		if (zkonline.Verify()){
		   alert("Verify success!");
		}else{
		   alert("Verify Failed!");
		}
	}

	function submitGetVerTpl()
	{
		if (zkonline.Register()) {
			alert(zkonline.RegisterTemplate);
		}
		/**if (zkonline.GetVerTemplate()){
		   alert(zkonline.VerifyTemplate);
		}*/
		if (zkonline.Verify()) {
			alert("指纹验证通过!");
		} else {
			alert("指纹验证失败!");
		}
	}
	function repaceSpecChar(content){
		content = content.replaceAll("\"","&quot;");
		content = content.replaceAll("'","&apos;");
		return content;
	}
</script>
<script language="JavaScript">
	var argus = window.dialogArguments;
	if ((argus[0] != "T") && (argus[1] == "编号校稿")) {
		try {
			document.updform.Submit1.style.display = "none";
		} catch (Exception) {}
	}
	
	
	
</script>
</body>
</html>

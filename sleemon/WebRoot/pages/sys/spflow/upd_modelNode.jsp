<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<title>新增信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
<%@ include file="/commons/jslibs.jsp"%>

<style type="text/css">
	.checkbox1 {border:0}
</style>
</head>

<body bgcolor="#d4d0c8" >
<form name="addform" method="post" action="">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th colspan="4">修改节点</th>
    </tr>
    <tr>
      <td colspan="4" height="10"></td>
    </tr>
    <tr>
      <td height="22"  align="right">操作者：</td>
      <input type="hidden" name="operatorId" value="${rst.OPERATIONERID}"> 
      <input type="hidden" name="selConditionOper" value="">
      <td >
        <input name="operatorName" type="text" readOnly value="${name1}">
        <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" name="selOper" onclick="selOperator('${operType}')">
      </td>
      <td align="right" >&nbsp;&nbsp;操作：</td>
      <td >
			<select name="nodeOperationId" style="width='132'" onchange="operChange()">
				<%//=sysInNode.returnSpecial("T_SYS_NODEOPERATION", "nodeOperationId,operationName", " operationName not in ('start','end')", "nodeOperationId", "", "shenqian")%>
			<option value='shenqian'>审核
			</select>
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" >属&nbsp;&nbsp;性：</td>
      <td colspan="3">
          <input name="xmsm" type="hidden">
					<fieldset>
          <legend>选择操作属性</legend>
					<table >
						<tr>
							<td><input type="checkbox" class="checkbox1" name="flag"  <c:if test="${rst.BACKFLAG eq '1'}">checked</c:if> value="backFlag">退签</td>
							<td  style="display:none" ><input type="checkbox" class="checkbox1" name="flag"  value="editFlag">修改内容</td>
							<td><input type="checkbox" class="checkbox1" name="flag" <c:if test="${rst.CANCLEFLAG eq '1'}">checked</c:if> value="cancleFlag">否决</td>
							<td></td>
						</tr>
						<tr  style="display:none">
							<td><input type="checkbox" class="checkbox1" name="flag" value="jumpFlag">跳签</td>
							<td><input type="checkbox" class="checkbox1" name="flag" ${agentStyle} value="agentFlag">节点代理</td>
							<td><input type="checkbox" class="checkbox1" name="flag" ${allStyle} value="allFlag">多人</td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox" class="checkbox1" name="flag" <c:if test="${rst.EXPANDFLAG eq '1'}">checked</c:if>  value="expandFlag">加签</td>
							<td><input type="checkbox" class="checkbox1" name="flag" <c:if test="${rst.DELAYFLAG eq '1'}">checked</c:if> value="delayFlag" onclick="showDelay()">是否启用上级审批过滤</td>
							<td id="delayLayer" style="display: none"><input type="text" name="delayTime"  onkeyup="this.value=this.value.replace(/[^\d]/g,'')"  size="4" value='${rst.DELAYTIME}'>时</td>
							<td></td>
						</tr>
					</table>
					</fieldset>
        </td>
    </tr>
    <tr>
      <td colspan="4" height="20"></td>
    </tr>
    <tr align="center" >
     <td align="center" colspan="4" valign="middle" nowrap >
        <input type="button" name="button1" value=" 保存 " accessKey="s"  ${btnStyle} class="btn" onClick="beforeSubmit()">
        <input type="button" name="button2" value=" 关闭 " accessKey="x"  class="btn" onclick="self.close();">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
<script language="JavaScript">
  function beforeSubmit(){
		if (document.addform.operatorId.value == "") {
			alert("请选择节点操作者！");
			return false;
		}
		var resultArray = new Array();
		resultArray[0] = document.addform.operatorId.value;
		resultArray[1] = '${operType}';
		for (var i=0; i<document.addform.flag.length; i++) {
			var boolTemp = eval("document.addform.flag["+i+"].checked");
			if (boolTemp == true)
				resultArray[i+2] = 1;
			else
				resultArray[i+2] = 0;
		}
		if (document.addform.flag[7].checked == true) {
		   
			//	if (document.addform.delayTime.value <= 0) {
			//		alert("提醒期限必须为正整数！");
			//		return false;
			//	}
		}
		
		
		resultArray[10] = document.addform.delayTime.value;
		resultArray[11] = document.addform.nodeOperationId.value;
		window.returnValue = resultArray;
		window.close();

  }


  function showDelay() {
  	//if (document.addform.flag[7].checked == true) {
  	//	delayLayer.style.display = "";
  	//} else {
  	//	delayLayer.style.display = "none";
  	//}
  }

  /*function doKeyPress() {
		if(event.keyCode == 13) {
			beforeSubmit();
		}

  }

  document.onkeypress = doKeyPress;*/
   function selOperator(nodeType) {
   
  	if (nodeType == 0) {
			//用户
			document.addform.selConditionOper.value = "yhzt not in ('停用','撤销')";
			var listname = "";
			<c:if test="${xmoryhm eq '0'}">
				listname = "XM";
			</c:if>
			<c:if test="${xmoryhm ne '0'}">
				listname = "YHM";
			</c:if>
  		   selCommon('4',false,'operatorId','XTYHID','addform','operatorId,operatorName','XTYHID,'+listname,'selConditionOper');
  	} else if (nodeType == 1) {
			//部门
			document.addform.selConditionOper.value = "bmzt not in ('停用','撤销')";
  		   selCommon('1',false,'operatorId','BMXXID','addform','operatorId,operatorName','BMXXID,BMMC','selConditionOper');
  	} else if (nodeType == 2) {
			//角色
			document.addform.selConditionOper.value = "";
  		    selCommon('8',false,'operatorId','XTJSID','addform','operatorId,operatorName','XTJSID,JSMC','selConditionOper');
  	} else if (nodeType == 3) {
			//工作组
			document.addform.selConditionOper.value = "gzzzt not in ('停用','撤销')";
			selCommon('33',false,'operatorId','GZZXXID','addform','operatorId,operatorName','GZZXXID,GZZMC','selConditionOper');
  	}
  }
	function operChange() {
		var nodeType = '${operType}';
		if (nodeType == 0) {
			document.addform.flag[4].disabled = false;
			document.addform.flag[5].disabled = true;
		} else {
			document.addform.flag[4].disabled = true;
			document.addform.flag[5].disabled = false;
		}
	}

</script>
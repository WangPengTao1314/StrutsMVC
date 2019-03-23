
<!--  
/**
 * @module 系统管理
 * @func 特殊工艺维护  
 * @version 1.1
 * @author 黄如
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/product/Prd_Spcl_Tech.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px;">
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<input id="PRD_ID"  type="hidden" name="PRD_ID" value="${PRD_ID}" />
		 
			<table width="80%" border="0" cellpadding="0" cellspacing="0"  >
				<tr>
				  <td ><font color="" size="3"><b>尺寸调整</b></font></td>
                  <td align="right" colspan=10>
                  <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input  type="button" id="SizeResetAdd" class="btn" value="新增(N)"  onclick="partAdd('SizeReset')" />	
			   		<input  type="button" id="SizeResetEdit" class="btn" value="修改(E)"  onclick="partEdit('SizeReset');" />
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input  type="button" id="SizeResetDelete" class="btn" value="删除(G)"  onclick="partDelete('SizeReset');">
			   	   </c:if>
				</td>
				</tr>
			</table>
			<table id="SizeReset" width="80%" border="0" cellpadding="1" cellspacing="1" class="lst">
			
				<tr>
					<td  nowrap="nowrap" align="center" dbname="RATIO" ></td>
                    <td  nowrap="nowrap" align="center" dbname="SPCL_TECH_NAME" >工艺名称</td>
                    <%--<td  nowrap="nowrap" align="center" dbname="TURN_TYPE" >尺寸调整类型</td>--%>
                    <td  nowrap="nowrap" align="center" dbname="CURRT_VAL" >当前规格</td>
                    <td  nowrap="nowrap" align="center" dbname="CURRT_VAL_TURN_BEG" >调整范围从</td>
                    <td  nowrap="nowrap" align="center" dbname="CURRT_VAL_TURN_END" >调整范围到</td>
                    <td  nowrap="nowrap" align="center" dbname="REMARK" >调整说明</td>
                    <td  nowrap="nowrap" align="center" dbname="PRICE_TURN_TYPE" >调价类型</td>
                    <td  nowrap="nowrap" align="center" dbname="PRICE_TURN_VAL" >调价值</td>
				</tr>
				<c:forEach items="${resultSizereset}" var="rst1" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this);" onclick="setChecked('eid1${rr}');">
									<td width="1%" align='center'>
										<input type="radio" style="height: 12px; valign: middle"
											name="radio1" id="eid1${rr}" value="${rst1.PRD_SPCL_TECH_ID}" />
									</td>									
									<td nowrap align="left">${rst1.SPCL_TECH_NAME}&nbsp;</td>
									<%--<td nowrap align="center">${rst1.TURN_TYPE}&nbsp;</td>--%>
									<td nowrap align="center">
										<script type="text/javascript">
											document.write((parent.topcontent.document.getElementById("spec"+document.getElementById("PRD_ID").value).value));
										</script>
									</td>
									<td nowrap align="right">${rst1.CURRT_VAL_TURN_BEG}&nbsp;</td>
									<td nowrap align="right">${rst1.CURRT_VAL_TURN_END}&nbsp;</td>
									<td nowrap align="left">${rst1.REMARK}&nbsp;</td>
									<td nowrap align="center">${rst1.PRICE_TURN_TYPE}&nbsp;</td>
									<td nowrap align="right">${rst1.PRICE_TURN_VAL}&nbsp;</td>
									 
								</tr>
				</c:forEach>
							<c:if test="${empty resultSizereset}">
								<tr>
									<td height="25" colspan="14" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
			</table>
			<br>
			
			<table width="60%" border="0" cellpadding="0" cellspacing="0"  >
				<tr>
				  <td ><font color="" size="3"><b>部件替换</b></font></td>
                  <td align="right" colspan="10">
                  <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		 	
			   		<input  type="button" id="PartReplaceAdd" class="btn" value="新增(N)"  onclick="partAdd('PartReplace')" />
			   		<input  type="button" id="PartReplaceEdit"  class="btn" value="修改(E)"  onclick="partEdit('PartReplace');" />
			   		
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   	   	<input  type="button" id="PartReplaceDelete" class="btn" value="删除(G)"  onclick="partDelete('PartReplace');">
			   	   </c:if>
				</td>
				</tr>
			</table>
			<table id="PartReplace" name="PartReplace" width="60%" border="0" cellpadding="1" cellspacing="1" class="lst">
			
				<tr>
					<td  nowrap="nowrap" align="center" dbname="RATIO" ></td>
                    <td  nowrap="nowrap" align="center" dbname="SPCL_TECH_NAME" >工艺名称</td>
                    <%--<td  nowrap="nowrap" align="center" dbname="TURN_TYPE" >部件类型</td>--%>
                    <td  nowrap="nowrap" align="center" dbname="CURRT_VAL" >当前部件</td>
                    <td  nowrap="nowrap" align="center" dbname="TUENED_VALS" >调整后部件</td>
                    <td  nowrap="nowrap" align="center" dbname="PRICE_TURN_TYPE" >调价类型</td>
                    <td  nowrap="nowrap" align="center" dbname="PRICE_TURN_VAL" >调价值</td>
				</tr>
				<c:forEach items="${resultPartReplace}" var="rst2" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this);" onclick="setChecked('eid2${rr}');">
									<td width="1%" align='center'>
										<input type="radio" style="height: 12px; valign: middle"
											name="radio2" id="eid2${rr}" value="${rst2.PRD_SPCL_TECH_ID}"  />
									</td>									
									<td nowrap align="left">${rst2.SPCL_TECH_NAME}&nbsp;</td><%--
									<td nowrap align="center">${rst2.TURN_TYPE}&nbsp;</td>
									--%><td nowrap align="left">${rst2.CURRT_VAL}&nbsp;</td>
									<td nowrap align="left">${rst2.TUENED_VALS}&nbsp;</td>
									<td nowrap align="center">${rst2.PRICE_TURN_TYPE}&nbsp;</td>
									<td nowrap align="right">${rst2.PRICE_TURN_VAL}&nbsp;</td>
									
								</tr>
							</c:forEach>
							<c:if test="${empty resultPartReplace}">
								<tr>
									<td height="25" colspan="14" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
			</table>
			<br>
			<table width="40%" border="0" cellpadding="0" cellspacing="0"  >
				<tr>
				  <td ><font color="" size="3"><b>部件新增</b></font></td>
                  <td align="right" colspan="10">
                  <c:if test="${pvg.PVG_EDIT eq 1 }">
                  	
			   		<input  type="button" id="partAddAdd" class="btn" value="新增(N)"  onclick="partAdd('PartAdd')" />	
			   		<input  type="button" id="PartAddEdit" class="btn" value="修改(E)"  onclick="partEdit('PartAdd');" />
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input  type="button" id="PartAddDelete"  class="btn" value="删除(G)"  onclick="partDelete('PartAdd');">
			   	   </c:if>
				</td>
				</tr>
			</table>
			<table id="PartAdd" name="PartAdd11" width="40%" border="0" cellpadding="1" cellspacing="1" class="lst">
			
				<tr>
					<td  nowrap="nowrap" align="center" dbname="RATIO" ></td>
                    <td  nowrap="nowrap" align="center" dbname="SPCL_TECH_NAME" >工艺名称</td>
                    <td  nowrap="nowrap" align="center" dbname="TURN_TYPE" >新增部件</td>
                    <td  nowrap="nowrap" align="center" dbname="PRICE_TURN_TYPE" >调价类型</td>
                    <td  nowrap="nowrap" align="center" dbname="PRICE_TURN_VAL" >调价值</td>
				</tr>
				<c:forEach items="${resultPartAdd}" var="rst3" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)" onclick="setChecked('eid3${rr}');"">
									<td width="1%" align='center'>
										<input type="radio" style="height: 12px; valign: middle"
											name="radio3" id="eid3${rr}" value="${rst3.PRD_SPCL_TECH_ID}" />
									</td>									
									<td nowrap align="left">${rst3.SPCL_TECH_NAME}&nbsp;</td>
									<td nowrap align="center">${rst3.TURN_TYPE}&nbsp;</td>
									<td nowrap align="center">${rst3.PRICE_TURN_TYPE}&nbsp;</td>
									<td nowrap align="right">${rst3.PRICE_TURN_VAL}&nbsp;</td>
									
								</tr>
				</c:forEach>
							<c:if test="${empty resultPartAdd}">
								<tr>
									<td height="25" colspan="14" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
			</table>
			<br>
			<table width="40%" border="0" cellpadding="0" cellspacing="0"  >
				<tr>
				  <td ><font color="" size="3"><b>可订制特殊非标工艺说明</b></font></td>
				  <td align="right" colspan="10">
                  <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input  type="button" id="FBSave" class="btn" value="保存(S)"  onclick="FBSave();" />
			   	   </c:if>
			   	   <input  type="hidden" id="FBSaveId" class="btn" value="${rst4.PRD_SPCL_TECH_ID}"  onclick="FBSave();" />
				</td>
				</tr>
			</table>
			<table id="FBAdd" name="FBAdd" width="40%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr>
					<td nowrap align="left">
						 <textarea onpropertychange="changeTextArea(document.getElementById('REMARK'));" rows="4"  json="REMARK" label="可订制特殊非标工艺说明"
			              		 id="REMARK" name ="REMARK" inputtype="string"  maxlength="250"   autocheck="true" cols="73%"/>${rst4.REMARK}</textarea>
		            </td>
					
				</tr>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="PRD_UNIT_IDS" name="PRD_UNIT_IDS" value=""/>
</form>
</body>
<script type="text/javascript">
	
	var PartAddradios = $("table#PartAdd").find("input:radio");
	if(PartAddradios!=null && PartAddradios.length!=0){
		$("table#PartAdd").find("input:radio:first").attr("checked", "checked");
	}else{
		$("#PartAddEdit").attr("disabled","disabled");
		$("#PartAddDelete").attr("disabled","disabled");
	}
	var PartReplaceradios = $("table#PartReplace").find("input:radio");
	if(PartReplaceradios!=null && PartReplaceradios.length!=0){
		$("table#PartReplace").find("input:radio:first").attr("checked", "checked");
	}else{
		$("#PartReplaceEdit").attr("disabled","disabled");
		$("#PartReplaceDelete").attr("disabled","disabled");
	}
	var SizeResetradios = $("table#SizeReset").find("input:radio");
	if(SizeResetradios!=null && SizeResetradios.length!=0){
		$("table#SizeReset").find("input:radio:first").attr("checked", "checked");
	}else{
		$("#SizeResetEdit").attr("disabled","disabled");
		$("#SizeResetDelete").attr("disabled","disabled");
	}
	var prdId=document.getElementById("PRD_ID").value;
	function partAdd(num){
		if(""==prdId||null==prdId){
			parent.showErrorMsg(utf8Decode("请选择一条记录！"));
			return;
		}
		window.showModalDialog('product.shtml?action=toPrdPartAddEdit&PRD_ID='+prdId+'&num='+num,window,'dialogHeight=320px, dialogWidth=490px, dialogTop=500px, dialogLeft=700px, toolbar=no, menubar=no, scroll=yes, resizable=no,location=no, status=no');
	}
	function partEdit(num){
		var inputs = $("table#"+num).find("input:radio:checked");
		var PRD_SPCL_TECH_ID ;
		inputs.each(function(){
		    PRD_SPCL_TECH_ID = $(this).val();
		  });
		window.showModalDialog('product.shtml?action=toPrdPartAddEdit&PRD_ID='+prdId+'&PRD_SPCL_TECH_ID='+PRD_SPCL_TECH_ID+'&num='+num,window,'dialogHeight=320px, dialogWidth=490px, dialogTop=500px, dialogLeft=700px, toolbar=no, menubar=no, scroll=yes, resizable=no,location=no, status=no');
	}
	
	function partDelete(num){
		var inputs = $("table#"+num).find("input:radio:checked");
		var PRD_SPCL_TECH_ID ;
		inputs.each(function(){
			PRD_SPCL_TECH_ID = $(this).val();
		});
		
		if(PRD_SPCL_TECH_ID!=null && PRD_SPCL_TECH_ID!=""){
			parent.showConfirm("您确认要删除吗?","mtbDelConfirm('product.shtml?action=deletePrdSpclTech','PRD_SPCL_TECH_ID','"+PRD_SPCL_TECH_ID+"');");
		}else{
			parent.showMsgPanel("请先选择一条记录");
			return;
		}
	}

	function mtbDelConfirm(actionUrl,pkId,selRowId){
		 
		 $.ajax({
		 	url: actionUrl+"&"+pkId+"="+selRowId,
			type:"POST",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("删除成功");
					parent.window.gotoBottomPage("label_2");
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	}
	
	function setChecked(id){
		$("#"+id).attr("checked", "checked");
	}
	function FBSave(){
	 	var PRD_ID = $("#PRD_ID").val();
	 	var PRD_SPCL_TECH_ID = $("#FBSaveId").val();
		//var REMARK = $.trim($("#REMARK").val());
		var REMARK = $("#REMARK").val();
		//alert(PRD_SPCL_TECH_ID);
	 	//return;
	 	//var jsonData =siglePackJsonData();
	 	var jsonData = "{'REMARK':'"+REMARK+"'}";
			 $.ajax({
				url: "product.shtml?action=toPrdEdit",
				type:"POST",
				dataType:"text",
				data:{"jsonData":jsonData,"SPCL_TECH_TYPE":"非标工艺说明","PRD_ID":PRD_ID,"PRD_SPCL_TECH_ID":PRD_SPCL_TECH_ID},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						//parent.showMsgPanel("保存成功");
						parent.showMsgPanel(utf8Decode(jsonResult.messages));
						parent.window.gotoBottomPage("label_2");
						
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
						
					}
				}
			 });
	 }
</script>
</html>



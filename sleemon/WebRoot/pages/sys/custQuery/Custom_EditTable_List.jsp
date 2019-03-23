<%-- 
  *@module 系统管理
  *@func 自定义查询
  *@version 1.1
  *@author zhuxw
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">  
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/pages/sys/custQuery/Custom_EditTable_List.js"></script> 
		<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>自定义查询</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="20" valign="top">
					<table width="100%" cellspacing="0" cellpadding="0"  height="20">
					<tr>
						<td width="28" align="center"><label class="wz_img"></label></td>
						<td>当前位置：系统管理&gt;&gt;查询管理&gt;&gt;自定义修改</td>
						<td width="50" align="right"></td>
					</tr>
				  </table>  
				</td>
			</tr> 
			<form id="queryForm" action="customQuery.shtml?action=saveTableData" method="post" name="queryForm">
			     <tr>
					<input type="hidden" id = "selPzr" name="selPzr" value=""/>
					<input type="hidden" id = "messageInfo" name="messageInfo" value="${messageInfo}"/>
					<input type="hidden" id = "hidTableName" name="hidTableName" value="${hidTableName}"/>
					<td class="detail2" valign="top">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
							<tr>
								<td width="20%" nowrap align="right" class="detail_label">数据表：</td>
								<td width="80%" class="detail_content" >  
									<input name="tableName" json = "tableName" id ="tableName" size="16" type="text" value="${tableName}" readonly seltarget="whereSql" autocheck="true" inputtype="string" label="数据表" mustinput="true"/>
		                        	<input id="selectUserId" type="button" class="btn" value="选择" onclick="selectTable();" >&nbsp;
								</td>
							</tr>
							<tr>
								<td width="20%" nowrap align="right"class="detail_label">查询SQL：</td>
								<td width="80%" class="detail_content">
									<textarea rows="5" cols="65%" id = "whereSql" seltarget="whereSql" name="whereSql" autocheck="true" inputtype="string" label="查询条件" ><c:out value="${whereSql}"></c:out></textarea>
								</td> 
							</tr>
			
							<tr><td colspan="10">&nbsp;</td></tr>
							<tr>
								<td colspan="10" align="center" valign="middle">
									<input id="q_search" type="button" class="btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
									<input id="q_close" type="button" class="btn" value="重置(X)" title="Alt+C" accesskey="X">
									<input id="q_save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr> 
					<td valign="top" height="100%">    
							<input type="hidden" name = "colName" id = "colName" value = "${colName}"/>
							<input type="hidden" name = "prekey" id = "prekey" value = "${prekey}"/>
							<div class="lst_area" id = "upDiv">
								<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
									${listHtml}
								</table>   
							</div>
					</td>
				</tr>
		  <!--  /form-->
			<tr id="pageTr">
				<td height="12px" align="center">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form id="pageForm" action="#" method="post" name="listForm">
								<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
									<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
									<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
									<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
									<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
									<span id="hidinpDiv" name="hidinpDiv"></span>
									${paramCover.unCoveredForbidInputs }
								</form>
							</td>
							<td align="right">
								 ${page.footerHtml}${page.toolbarHtml}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
		InitFormValidator(0);
		
		var messageInfo = $("#messageInfo").val();
		if(messageInfo == 'sucess'){ 
			showMsgPanel("保存成功");
		}else if(messageInfo == 'fail'){
			showErrorMsg("保存失败");
		}else if(messageInfo == 'key'){ 
			showErrorMsg("此表无主键,不允许修改");
		}else if(messageInfo =='error'){
			showErrorMsg("SQL错误");
		}
	</script>
</html>

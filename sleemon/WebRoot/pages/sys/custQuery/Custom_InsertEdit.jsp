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
		<script type="text/javascript" src="${ctx}/pages/sys/custQuery/Custom_InsertEdit.js"></script> 
		<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>自定义设置</title>
	</head>
	<body>
		<table width="100%" height="20" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="20">
					<table width="100%" cellspacing="0" cellpadding="0" class="wz">
					<tr>
						<td width="28" align="center"><label class="wz_img"></label></td>
						<td>当前位置：系统管理&gt;&gt;查询管理&gt;&gt;
							<c:if test="${hidRptPk !='' }">
								自定义查询设置编辑
							</c:if>
							<c:if test="${hidRptPk =='' }">
								自定义查询设置新增
							</c:if>
						</td>
						<td width="50" align="right"></td>
					</tr>
				  </table>  
				</td>
			</tr>
		</table> 
		<form name="qryForm" action="customQuery.shtml?action=nextAddEditRpt" method="post" id = "qryForm">
			<input type="hidden" name = "hidRptPk" id = "hidRptPk" value="${hidRptPk}"/>
			<input type="hidden" name = "selPzr" id = "selPzr" value=""/>
			
			<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail" id="jsontb">
				<tr>
					<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
							<tr>
								<td width="30%" align="right" class="detail_label">报表编号：</td>
								<td width="70%" class="detail_content">
			                       <input json="rptCode" id = "rptCode" name="rptCode" type="text" readonly label="报表编号" inputtype="string" maxlength="32" value="${resultBean.rptCode}"/>
								</td> 
							</tr> 
							<tr>
								<td width="30%" align="right" class="detail_label">报表名称：</td>
								<td width="70%" class="detail_content">
			                        <input json="rptName" id = "rptName" name="rptName" type="text" autocheck="true" label="报表名称" inputtype="string" mustinput="true" maxlength="64" value="${resultBean.rptName}"/>
								</td> 
							</tr>
							<tr>
								<td width="30%" align="right" class="detail_label">报表SQL：</td>
								<td width="70%" class="detail_label">
									<table width="100%" border="0" cellpadding="1" cellspacing="1" >
										<tr>
											<td width="65%" align="left" class="detail_content">
												<div align="left"/>
													<textarea rows="8" cols="65%" id = "rptSql" json ="rptSql" name="rptSql"  inputtype="string" label="报表SQL" mustinput="true" maxlength="4000"><c:out value="${resultBean.rptSql}"></c:out></textarea>
												</div>
											</td>
											<td width="35%" align="left" class="detail_content" valign="bottom">
												<div align="left" />
							                        <span style="color:red"/> 
							                        	系统常量: 本人员@USERID@；本部门@DEPTID@;<br>
							                        	本机构@ORGANID@;多部门@DEPTIDS@;<br>
							                        	多机构@ORGANIDS@;<br>
							                        </span>
							                        <input id="check" type="button" class="btn" value="检查正确性(C)" title="Alt+C" accesskey="C">
												</div>
											</td>
										</tr>
									</table>
								</td> 
							</tr>
							<tr>
								<td width="30%" align="right" class="detail_label" rowspan="4">设置权限：</td>
								<td width="70%" class="detail_content">
									<input type="hidden" id ="roleUser"  name="roleUser" type="text" value="${resultBean.roleUser}" readonly autocheck="true" label="选择用户" inputtype="string" maxlength="250"/>
									<input name="roleUserName" id ="roleUserName" size="60%" type="text" value="${resultBean.roleUserName}" readonly />
			                        <input id="selectUserId" type="button" class="btn" value="选择用户" onclick="selectUser();" >&nbsp;
								</td> 
							</tr>
							<tr> 
								<td width="70%" class="detail_content">
									<input type="hidden" id ="roleCode" name="roleCode" type="text" value="${resultBean.roleCode}" readonly autocheck="true" label="选择角色" inputtype="string" maxlength="250"/>
									<input name="roleName" id="roleName" type="text" size="60%" value="${resultBean.roleName}" readonly />
			                        <input id="selectRoleId" type="button" class="btn" value="选择角色" onclick="selectRole();" >&nbsp;
								</td> 
							</tr>
							<tr> 
								<td width="70%" class="detail_content">
									<input type="hidden" id ="workGroupCode" name="workGroupCode" type="text" value="${resultBean.workGroupCode}" readonly autocheck="true" label="选择工作组" inputtype="string" maxlength="250"/>
									<input name="workGroupName" id="workGroupName" type="text" size="60%" value="${resultBean.workGroupName}" readonly/>
			                        <input id="selectWorkId" type="button" class="btn" value="选择工作组" onclick="selectWorkGroup();" >&nbsp;
								</td> 
							</tr> 
							<tr> 
								<td width="70%" class="detail_content">
			                        <input type="radio" name = "isRole" id = "isRole" value = "0" onclick="change();" <c:if test="${resultBean.isRole == 0}"> checked</c:if>/>任何人
			                        <input type="radio" name = "isRole" id = "isRole" value = "1" onclick="change();" <c:if test="${resultBean.isRole == 1}"> checked</c:if>/>设置权限
								</td> 
							</tr> 
							<tr>
								<td width="30%" align="right" class="detail_label">说明：</td>
								<td width="70%" class="detail_content">
			                        <textarea rows="4" cols="65%" id = "remark" json ="remark" name="remark" autocheck="true" inputtype="string" label="说明" maxlength="250">${resultBean.remark}</textarea>
								</td> 
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<br>
		<div class="tablayer" align="center">
	      	<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
			   		<td align="center" nowrap>
			   			<input id="next" type="button" class="btn" value="下一步(N)" title="Alt+N" accesskey="N">
			   			<input id="res" type="button" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
						<input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" >
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript">
			var isRole = $(':input:radio[name="isRole"]:checked').val();
			if(isRole =='0'){
				btnDisable(["selectUserId"]);
				btnDisable(["selectRoleId"]);
				btnDisable(["selectWorkId"]);
			}
			
			//根据权限设置页面
			function change(){
				var isRole = $(':input:radio[name="isRole"]:checked').val(); 
				if(isRole =='0'){
					btnDisable(["selectUserId"]);
					btnDisable(["selectRoleId"]);
					btnDisable(["selectWorkId"]);
				}else{ 
					$("#jsontb :input['disabled'=true]").removeAttr("disabled");
				}
			}
		</script>
	</body>
</html>

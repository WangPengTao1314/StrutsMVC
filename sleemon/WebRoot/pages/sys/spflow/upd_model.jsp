<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>修改信息</title>
	</head>
	<body>	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" valign="middle">&nbsp;&nbsp;
					<input type="submit" class="btn" value="保存(S)" title="Alt+S" accesskey="S" onclick="doSave();">
					&nbsp;&nbsp;
					<c:if test="${empty param._backUrl}">&nbsp;&nbsp;
						<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post" action="${ctx}/system/flow.shtml?action=update" nsubmit="return doSave();">
						<input type="hidden" name="FLOWMODELID" value="${rst.FLOWMODELID}">						
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4"
										class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1"
													cellspacing="1" class="detail3">
													<tr>
														<td width="15%" height="30" nowrap="nowrap" align="right"
															class="detail_label">
															模板编号：
														</td>
														<td width="35%" class="detail_content">
															<input name="MODELNUMBER" id="MODELNUMBER" type="text" autocheck="true" label="模板编号" inputtype="string"  readonly mustinput="true" value="${rst.MODELNUMBER}">
														</td>
														<td width="15%" height="30" nowrap="nowrap" align="right" class="detail_label">
															模板名称：
														</td>
														<td width="35%" class="detail_content">
															<input name="MODELNAME" id="MODELNAME"  type="text" autocheck="true" label="模板名称" inputtype="string" value="${rst.MODELNAME}">
														</td>
													</tr>
													<tr>
														<td width="15%" height="30" nowrap="nowrap" align="right"
															class="detail_label">
															业务类型：
														</td>
														<td width="35%" class="detail_content">
															<input name="BUSINESSTYPE" id="BUSINESSTYPE" type="text" autocheck="true" label="业务类型"
																inputtype="string" mustinput="true" value="${rst.BUSINESSTYPE}">
															 <%
			                                                      if(Consts.DBTYPE!=null&& "MSSQL".equals(Consts.DBTYPE)){
		                                                      %>
														     <img align="absmiddle" name="selBUS" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								                              onClick="selCommon('System_16', true, 'BUSINESSTYPE', 'BUSINESSTYPE', 'forms[0]','BUSINESSTYPE', 'BUSINESSTYPE', '')">
														     <%}else{%>
														     <img align="absmiddle" name="selBUS" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								                              onClick="selCommon('System_15', true, 'BUSINESSTYPE', 'BUSINESSTYPE', 'forms[0]','BUSINESSTYPE', 'BUSINESSTYPE', '')">
														     <%}%>
														</td>
														<td width="15%" height="20" align="right" class="detail_label">
															机构:
														</td>
														<td  width="35%" class="detail_content">
															<input name="JGMC" seltarget="selJGMC" id="JGMC" type="text" autocheck="true" readonly label="机构名称" inputtype="string"  value="${rst.JGMC}">
								                            <input name="JGXXID" seltarget="selJGMC" id="JGXXID" type="hidden" value="${rst.JGXXID}">								                            
					                                        <%
			                                                  if(!com.hoperun.commons.util.StringUtil.isEmpty(Consts.ACCOUNT_DISPLAY)&& "false".equals(Consts.ACCOUNT_DISPLAY)){
		                                                     %>
					                                          <input type="hidden" id="jgFilter" value=" DELFLAG=0 AND JGZT='启用' ">
					                                         <%}else{ %>
					                                            <input type="hidden" id="jgFilter" value=" DELFLAG=0 AND JGZT='启用' AND ZTXXID ='${ADDLCZTXXID}'">
					                                         <%}%>
								                            <img align="absmiddle" name="selJGMC" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								                              onClick="selCommon('System_11', false, 'JGXXID', 'JGXXID', 'forms[0]','JGMC', 'JGMC', 'jgFilter')">
														</td>
													</tr>
													<tr>
														<td width="15%" height="30" align="right" nowrap="nowrap" class="detail_label" >
															提交跳签：
														</td>
														<td width="35%" class="detail_content" >
															<select name="AFFIRMJUMP" style="width:155px" " >
																<option value="0" <c:if test="${rst.AFFIRMJUMP eq '0' }">selected</c:if>>禁止</option>
																<option value="2" <c:if test="${rst.AFFIRMJUMP eq '2' }">selected</c:if>>高级人员</option>
																<option value="3" <c:if test="${rst.AFFIRMJUMP eq '3' }">selected</c:if>>中高级人员</option>
																<option value="4" <c:if test="${rst.AFFIRMJUMP eq '4' }">selected</c:if>>所有人员</option>
															</select>
															
														</td>
														<td width="15%" height="20" align="right" class="detail_label"></td>
														<td  width="35%" class="detail_content">
														</td>
													</tr>
													<tr>
                                                        <td nowrap bgcolor="#f3f3f3" width="15%"  height="20" align="right" class="detail_label" >使用条件：</td>
                                                        <td colspan="3" class="detail_content">
                                                        <textarea name="MODELTYPE" cols="50" inputtype="string" maxlength="200"  rows="2"><c:out value="${rst.MODELTYPE}"></c:out></textarea>
                                                        </td>
													</tr>	
													 <tr>
                                                         <td  width="15%" height="30" align="right" class="detail_label">备注：</td>
                                                         <td colspan="3" class="detail_content">
                                                         <textarea name="REMARK" id="REMARK" inputtype="string" maxlength="250" cols="60" rows="2"><c:out value="${rst.REMARK}"></c:out></textarea>
                                                         </td>
                                                    </tr>
												</table>
											</td>
										</tr>
									</table><br>
								</td>
							</tr>
							
						</table>
					</form>
				</td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="ctrType" id="ctrType" value="edit">
	</body>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
	   function doSave(){
		if($("#MODELNAME").val()==null || $("#MODELNAME").val()==""){
	        parent.showMsgPanel("请输入模板名称");
	        return false;
	    }
	    
	    if($("#BUSINESSTYPE").val()==null || $("#BUSINESSTYPE").val()==""){
	        parent.showMsgPanel("请输入业务类型");
	        return false;
	    }
	    
	      if(FormValidate(0)){
				setTimeout("form.submit();",100);
	      }
		  return false;
		}
	</script>
</html>

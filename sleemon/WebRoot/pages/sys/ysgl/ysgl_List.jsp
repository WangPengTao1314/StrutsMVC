<%--
/**
 * @author 葛俊卿
 * @function 
 * @version 2012年2月14日 
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ page import="com.hoperun.sys.model.UserBean,com.hoperun.sys.model.YsglModel,java.util.List"%>
<%
	UserBean userBean = (UserBean) session.getAttribute("UserBean");
	String xtyhid=userBean.getXTYHID();
 %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sys/ysgl/ysgl_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.9%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;样式管理&gt;&gt;用户风格设置</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td nowrap>
			   		<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
		<input type="hidden" id="XTYHID" name="XTYHID" value="<%=xtyhid%>">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<% 
					List<YsglModel> result = (List<YsglModel>)request.getAttribute("rst");
					YsglModel  model = null;
					int index =0;
					int rows = 0;
					if(result!=null && result.size()>0){
						rows = (result.size()+1)/2;
						for(int i=0;i<rows;i++){
							if(result.size()%2==0 || i<rows-1){
							%>
								<tr>
									<% 
										for(int j=0;j<2;j++){
											model = result.get(index);
											%>
												<td>
													<table>
														<tr class="list_row1" onmouseover="mover(this)" onmouseout="mout(this)">
															<td width="1%"align='center' >
																<b ><%=model.getCSSMC() %></b>
															</td>
														</tr>
														<tr>
															<td align='center' >
																<img height='180' width='350' src="${ctx}/pages/sys/ysgl/<%=model.getCSSPATH() %>" alt="图片">
															</td>
														</tr> 
														<tr>
															<td width="1%"align='center' >
																<input type="radio" style="height:12px;valign:middle" name="cid" id="<%=model.getCSSKEY()%>" value="<%=model.getCSSKEY()%>" <%if(model.getMRBJ()=="1"){%>checked="checked"<%}%>/>选中
															</td>
														</tr>
													</table>
												</td>
											<%
											++index;
										}
										
									 %>
								</tr>
							<%
							}
							if(result.size()%2!=0 && i==rows-1 && (index+1)==result.size()){
								model = result.get(index);
								%>
									<tr>
										<td>
											<table>
												<tr class="list_row1" onmouseover="mover(this)" onmouseout="mout(this)">
													<td width="1%"align='center' >
														<b ><%=model.getCSSMC() %></b>
													</td>
												</tr>
												<tr>
													<td align='center'>
														<img height='180' width='350' src="${ctx}/pages/sys/ysgl/<%=model.getCSSPATH() %>" alt="图片">
													</td>
												</tr> 
												<tr>
													<td width="1%"align='center' >
														<input type="radio" style="height:12px;valign:middle" name="cid" id="<%=model.getCSSKEY()%>" value="<%=model.getCSSKEY()%>" <%if(model.getMRBJ()=="1"){%>checked="checked"<%}%>/>选中
													</td>
												</tr>
											</table>
										</td>
										<td>&nbsp;</td>
									</tr>
								<%
							}
						}
					}else{
						%>
							<tr>
								<td height="25" colspan="11" align="center" class="lst_empty">
									&nbsp;无相关信息&nbsp;
								</td>
							</tr>
						<%
					}
					
				%>
				
			</table>
		</div>
	</td>
</tr>
</table>

</body>
</html>

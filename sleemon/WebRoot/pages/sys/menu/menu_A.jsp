<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%> 
<%@ include file="/commons/jslibs.jsp"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>查询菜单</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" >
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>  
								当前位置：系统管理 &gt;&gt; 菜单管理 &gt;&gt; 系统菜单维护
							</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr> 
			<tr>
				<td>
					<form name="form" method="post"	action="${ctx}/system/menu.shtml?action=add" onsubmit="return doSave();">
					${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									菜单信息
								</td>
							</tr>
							<tr>
								<td width="100%" align="center">
									<table width="70%" border="0" cellpadding="4" cellspacing="4" class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
													<tr>
														<td width="25%" height="30" class="detail_label">
															菜单编号：
														</td>
														<td width="75%" class="detail_content"> 
															<input name="menuId" id="menuId" type="text" autocheck="true" label="菜单编号" valueType="chinese:false" inputtype="string" mustinput="true" maxlength="6" value="">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单名称：
														</td>
														<td class="detail_content">
															<input name="menuName" id="menuName" type="text" autocheck="true" inputtype="string" label="菜单名称" maxlength="100" mustinput="true" value="">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															上级菜单：
														</td>
														<td class="detail_content"> 
															<input name="menuParId" id="menuParId" type="text" inputtype="string" mustinput="true" label="上级菜单" 	autocheck="true" maxlength="6" value="">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															排序编号：
														</td>
														<td class="detail_content">
															<input name="menuSort" id="menuSort" type="text" inputtype="int" label="排序编号" mustinput="true" autocheck="true" maxlength="2" value="">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															权限Code：
														</td>
														<td class="detail_content">
															<input name="menuQxCode" id="menuQxCode" type="text" inputtype="string" label="权限Code" mustinput="true" autocheck="true" maxlength="15" value="">
														    &nbsp;跟权限绑定，用来过滤菜单
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															业务类型：
														</td>
														<td class="detail_content">
															<input name="BUSINESSTYPE" id="BUSINESSTYPE" type="text" inputtype="string" label="业务类型" autocheck="true" maxlength="50" >
															&nbsp;跟审批绑定，即审批流程的业务类型
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单链接：
														</td>
														<td class="detail_content">
															<input name="menuUrl" type="text" size="50" label="菜单链接" value="" inputtype="string" maxlength="100">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单图片：
														</td>
														<td class="detail_content">
															<input name="menuImg" type="text" size="50" label="菜单图片"	value="" inputtype="string" maxlength="100">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单注释：
														</td>
														<td class="detail_content">
															<input name="menuDesc" type="text" size="50" label="菜单注释" value="" inputtype="string" maxlength="100">
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="49" align="center" valign="middle">
									<input type="button" id="save" class="btn" value="新增(N)" title="Alt+N" accesskey="N">&nbsp;&nbsp;
									<input type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
									<c:if test="${not empty param._backUrl}">&nbsp;&nbsp;
									    <input type="button" class="btn" value="返 回(B)" title="Alt+B" accesskey="B" onclick="goBack()">
									</c:if>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
	
	<script type="text/javascript">
	    $(function(){
	      
	       InitFormValidator(0);
	       if('${msg}'!=null&&'${msg}'!=''){
              showMsgPanel(utf8Decode('${msg}'));
	        }
	      
	    });
	    
	    $("#save").click(function(){
	        if($("#menuId").val() == null || $("#menuId").val()==""){
              showErrorMsg(utf8Decode("请输入菜单编号!"));
	          return false;
	        }
	        
	        if($("#menuName").val() == null || $("#menuName").val()==""){
	           showErrorMsg(utf8Decode("请输入菜单名称!"));
	          return false;
	        }
	        
	        if($("#menuSort").val() == null || $("#menuSort").val()==""){
	          showErrorMsg(utf8Decode("请输入排序号!"));
	          return false;
	        }
	        
	        if($("#menuParId").val() == null || $("#menuParId").val()==""){
	           showErrorMsg(utf8Decode("请输入上级菜单!"));
	          return false;
	        }
	        
	        if($("#menuQxCode").val() == null || $("#menuQxCode").val()==""){
	          showErrorMsg(utf8Decode("请输入权限Code!"));
	          return false;
	        }
	        
	        if(isNaN($("#menuSort").val())){
	            showErrorMsg(utf8Decode("排序号请输入数字!"));
	            return false;
	        }
	        
	        if(FormValidate(0)){
				setTimeout("form.submit();",100);
			}
	    });
		function doSave(){
		    if($("#menuId").val() == null || $("#menuId").val()==""){

	          showErrorMsg(utf8Decode("请输入菜单编号!"));
	          return false;
	        }
	        
	        if($("#menuName").val() == null || $("#menuName").val()==""){
	          showErrorMsg(utf8Decode("请输入菜单名称!"));
	          return false;
	        }
	        
	        if($("#menuSort").val() == null || $("#menuSort").val()==""){
	          showErrorMsg(utf8Decode("请输入排序号!"));
	          return false;
	        }
	        
	        if($("#menuParId").val() == null || $("#menuParId").val()==""){
	          showErrorMsg(utf8Decode("请输入上级菜单!"));
	          return false;
	        }
	        
	        if($("#menuQxCode").val() == null || $("#menuQxCode").val()==""){
	          showErrorMsg(utf8Decode("请输入权限Code!"));
	          return false;
	        }
	        
	       
			if(FormValidate(0)){
				setTimeout("form.submit();",100);
			}
			return false;
		}
		
		//edit by zhuxw 
		function goBack(){
		   window.location="menu.shtml?action=toQuery";
		}
		
	</script>
</html>

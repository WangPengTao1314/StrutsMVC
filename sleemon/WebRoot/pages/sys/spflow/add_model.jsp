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
		<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
    	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>新增模板</title>
	</head>
	<body class="bodycss1">	
	    <div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
						    <input type="button" id="save" class="btn" value="保存(S)"	title="Alt+S" accesskey="S">&nbsp;&nbsp;
							<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
						</td>
					</tr>
				</table>
		   </div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		     <tr>
					 <!--占位用行，以免显示数据被浮动层挡住-->
			        <td height="20px">&nbsp;</td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post" action="${ctx}/system/flow.shtml?action=add" onsubmit="return doSave();">
					 <%
			          if(!com.hoperun.commons.util.StringUtil.isEmpty(Consts.ACCOUNT_DISPLAY)&& "false".equals(Consts.ACCOUNT_DISPLAY)){
		             %>
					 <input type="hidden" id="jgFilter" value=" DELFLAG=0 AND JGZT='启用' ">
					<%}else{ %>
					  <input type="hidden" id="jgFilter" value=" DELFLAG=0 AND JGZT='启用' AND ZTXXID ='${ADDLCZTXXID}'">
					<%}%>
						${paramCover.unCovered_Inputs }
						<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">							
								<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4"	class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
													<tr>
														<td width="25%" height="20" align="right" class="detail_label">
															模板编号：
														</td>
														<td width="25%" class="detail_content">
															<input name="modelNumber"  readonly type="text" autocheck="true" label="编号" inputtype="string" mustinput="true" value="系统自动生成">
														</td>
														<td width="25%" height="20" align="right" class="detail_label">
															机构:
														</td>
														<td  width="25%" class="detail_content">
															<input name="jgmc" seltarget="selJGMC" id="JGMC" type="text" autocheck="true" readonly label="机构名称" inputtype="string"  value="">
								                            <input name="jgxxid" seltarget="selJGMC" id="JGXXID" type="hidden" >
								                            <img align="absmiddle" name="selJGMC" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								                              onClick="selCommon('System_11',false,'JGXXID', 'JGXXID', 'forms[0]','JGMC', 'JGMC', 'jgFilter')">
														</td>
													</tr>
													<tr>
														<td width="25%" height="20" align="right"
															class="detail_label">
															模板名称：
														</td>
														<td width="25%" class="detail_content">
															<input name="modelName" id="modelName" type="text" autocheck="true" label="名称" inputtype="string" mustinput="true" >
														</td>
														<td width="25%" height="20" align="right" class="detail_label" nowrap="nowrap">
															业务类型:
														</td>
														<td  width="25%" class="detail_content">
															<input name="businessType" id="businessType" seltarget="selBUS" type="text" autocheck="true" label="业务类型" inputtype="string" mustinput="true">
														   
														</td>
													</tr>
													<tr>
														<td width="25%" height="20" align="right"
															class="detail_label" nowrap="nowrap">
															提交跳签：
														</td>
														<td width="25%" class="detail_content" >
															<select name="affirmJump" style="width:155px">
        	                                                <option value=0 >禁止</option>        	                                                 
        	                                                <option value=2>高级人员</option>
        	                                                <option value=3>中高级人员</option>
        	                                                <option value=4>所有人员</option>        	                                                
                                                            </select>
														</td>
														<td width="25%" height="20" align="right" class="detail_label">
															
														</td>
														<td  width="25%" class="detail_content">
														</td>
													</tr>
													<tr>
                                                        <td nowrap bgcolor="#f3f3f3" width="25%"  height="20" align="right" class="detail_label" >使用条件：</td>
                                                        <td colspan="3" class="detail_content">
                                                        <textarea json="modelType" name="modelType" inputtype="string" maxlength="200" cols="50" rows="2" ><c:out value="${rst.MODELTYPE}"></c:out></textarea>
                                                        </td>
													</tr>	
													<tr>
														
                                                         <td nowrap bgcolor="#f3f3f3" width="25%"  height="20" align="right" class="detail_label" >备注：</td>
                                                        <td colspan="3" class="detail_content">
                                                        <textarea json="remark" name="remark" inputtype="string" maxlength="250" cols="50" rows="2" ><c:out value="${rst.QTSM}"></c:out></textarea>
                                                        </td>
													</tr>
														
																									
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>					
						</table>
					</form>
					
				</td>
				
			</tr>
			
		</table>
		
		</div>
		
	</body>

	<script type="text/javascript">
	$(function(){
	InitFormValidator(0);
	})
	$("#save").click(function(){
	  
	    if($("#modelName").val()==null || $("#modelName").val()==""){
	        parent.showMsgPanel("请输入模板名称");
	        return false;
	    }
	    
	    if($("#businessType").val()==null || $("#businessType").val()==""){
	        parent.showMsgPanel("请输入业务类型");
	        return false;
	    }
	    
	    setTimeout("form.submit();",100);
	});
		function doSave(){
			if(FormValidate(0)){
		    //var resultArray = new Array();
		   // resultArray[0] = document.addform.modelNumber.value;
		   // resultArray[1] = document.addform.modelName.value;
		   // resultArray[2] = document.addform.modelType.value;
		   // resultArray[3] = document.addform.businessType.value;
		   // resultArray[4] = document.addform.remark.value;
		   // resultArray[5] = document.addform.jgxxid.value;
		   // resultArray[6] = document.addform.affirmJump.value;
		   // window.returnValue = resultArray;
		  //  window.close();
			setTimeout("form.submit();",100);
			}
			return false;
		}
		function select_orgPar(btn){			
			var nameTarget=form.orgParName;
			var srcTarget=form.orgId;
			var sUrl="${ctx}/hisrep/lscxmb.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
	</script>
</html>

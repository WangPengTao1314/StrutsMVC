<!-- /**

 *@module 财务管理

 *@fuc 帐套信息维护编缉页面

 *@version 1.1

 *@author 唐赟
*/ -->
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
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>帐套信息维护新增或修改</title>
	</head>
	<body>	
	<input type="hidden" id="status" value="${status }"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
					<td align="left" nowrap>
						<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
						<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
					</td>
				</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post"	 id="mainForm"><!--  action="${ctx}/system/xtyh.shtml?action=toSave"	onsubmit="return doSave();">  -->
					    
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="REPMOD_NO" value="${param.REPMOD_NO}">
						${paramCover.unCovered_Inputs }
						<table id="jsontb" width="100%" border="0" cellspacing="1">
						    <tr><td><input type="hidden" json="ctrType" name="ctrType" id="ctrType" value="${status }"></td></tr>
							<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1"	cellspacing="1" class="detail3">
													
										<tr>
											<td class="detail2">
												<table width="100%" id="detail" border="0" cellpadding="1"
													cellspacing="1" class="detail3">
													<tr>
														<td width="15%" height="30" align="right"
															class="detail_label">
															帐套编号：
														</td>
														<td width="35%" class="detail_content">
															
															<input json="ZTBH" id="ZTBH" name="ZTBH" type="text" autocheck="true" valueType="chinese:false" label="帐套编号" maxlength="30"	inputtype="string" mustinput="true" value="${rst.ZTBH}">
														
														</td>
														
														<td width="15%" height="30" align="right" class="detail_label">
															帐套名称：
														</td>
														<td width="35%" class="detail_content">
															<input json="ZTMC" name="ZTMC" id="ZTMC" type="text" autocheck="true" label="帐套名称" maxlength=100
																inputtype="string" mustinput="true" value="${rst.ZTMC }">
														</td>
													</tr>
													<tr>
														<td width="15%" height="30" align="right" class="detail_label">
															月结帐标记：
														</td>
														<td width="35%" class="detail_content">
														    <input json="YJZBJ" id="YJZBJ" name="YJZBJ" type="checkbox" value="${rst.YJZBJ }">
														</td>
														
														<td width="15%" height="30" align="right"
															class="detail_label">
															上级帐套：
														</td>
														<td width="35%" class="detail_content">
														    <!--  <input id="selcondition" type="hidden" name="selcondition"	value="state = '启用'" />-->
														    <input id="selcondition" type="hidden" name="selcondition"	value=" state in( '启用','制定')" />
															<input type="hidden" json="SJZT" id="SJZT" name="SJZT" type="text" autocheck="true" label="上级帐套" maxlength=30 	inputtype="string"  value="${rst.SJZT}">
															<input id="SJZTMC" name="SJZTMC" seltarget="selZTXX"  label="上级帐套名称" autocheck="true" inputtype="string" value="${rst.SJZTMC}" readonly>
<img align="absmiddle" name="selZTXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						   onClick="selCommon('System_5', false, 'SJZT', 'ZTXXID', 'forms[0]','SJZT,SJZTMC', 'ZTBH,ZTMC', 'selcondition')">
						  
														</td>
													</tr>
													
													<tr>
														<td width="15%" height="30" align="right"
															class="detail_label">
															增值税号：
														</td>
														<td width="35%" class="detail_content">
															<input json="ZZSH" name="ZZSH" id="ZZSH" type="text" autocheck="true" label="增值税号" maxlength=20
																inputtype="string"  value="${rst.ZZSH}">
														</td>
														<td width="15%" height="30" align="right" class="detail_label">
															营业执照号：
														</td>
														<td width="35%" class="detail_content">
															<input json="YYZZH" name="YYZZH" id="YYZZH" type="text" autocheck="true" label="营业执照号" maxlength=50
																inputtype="string" value="${rst.YYZZH }">
														</td>
													</tr>
													
													<tr>
														<td width="15%" height="30" align="right" class="detail_label">
															帐套类别：
														</td>
														<td width="85%" class="detail_content" colspan=3>
														    <input id="ZTLB1" type="hidden" value="${rst.ZTLB}" mustinput="true" autocheck="true" label="帐套类别">
															<select json="ZTLB" name="ZTLB" id="ZTLB" inputtype="string" label="帐套类别" mustinput="true" autocheck="true" style="width:155px" value="${rst.ZTLB }" onchange= "changeValue()">
															    <option value="">--请选择--</option>
															    <option value="独立帐套">独立帐套</option>
															    <option value="内部帐套">内部帐套</option>
															</select>
														</td>
													</tr>
												</table>
									</table><br>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>
			</form>
		</td>
		</tr>
	</body>

	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript" src="${ctx}/pages/sys/ztxxwh/ztxxwh_Edit.js"></script>
	<script type="text/javascript">
	   //SelDictShow("ZTLB","0","${rst.ZTLB }","");	
	   
	   var a = $("#YJZBJ").val();
	   if( null != a ){
	      if( a == "1"){
	        $("#YJZBJ").attr("checked","true");
	      }
	      if(a == "0"){
	        $("#YJZBJ").removeAttr("checked");
	      }
	   }
	   
	   function changeValue()
       {
          $("#ZTLB1").attr("value",$("#ZTLB").val());
	   }
	</script>
</html>

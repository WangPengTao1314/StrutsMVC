<!-- 
 /**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
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
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/erp/sale/forecasttask/ForecastTask_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>上报任务编辑</title>
	<body>
		<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>

		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				 <!--占位用行，以免显示数据被浮动层挡住-->
		        <td height="20px">&nbsp;</td>
		    </tr>
			<tr>
				<td>
					<form name="main" id="mainForm">
						<input type="hidden" name="selectParams" value=" STATE in( '启用')">
						<input type="hidden" name="selectPYXXParams" value=" YHZT = '启用' " />
						<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
							<tr>
								<td class="detail2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
										<tr>
										    <td width="16%" align="right" class="detail_label">
												 上报任务编号：
											</td>
											<td width="35%" class="detail_content">
												<input json="ADVC_RPT_JOB_NO" name="ADVC_RPT_JOB_NO" id="ADVC_RPT_JOB_NO" 
												autocheck="true"  inputtype="string" mustinput="true"  maxlength="30"
												 type="text" label=" 上报任务编号"  readonly
												  <c:if test="${empty rst.ADVC_RPT_JOB_NO}"> value="自动生成"</c:if>
												  <c:if test="${not empty rst.ADVC_RPT_JOB_NO}"> value="${rst.ADVC_RPT_JOB_NO}"</c:if>
												 >
											</td>
											<td width="15%" align="right" class="detail_label">发布人名称：</td>
											<td width="35%" class="detail_content">
											   <input type="hidden" id="SENDER_ID" name="SENDER_ID" json="SENDER_ID" value="${rst.SENDER_ID}"  />
						                       <input type="text"  id="SENDER_NAME" name="SENDER_NAME" json="SENDER_NAME" maxlength="30"    autocheck="true" inputtype="string" label="发布人名称" value="${rst.SENDER_NAME}" mustinput="true"  />&nbsp;
						                       <img align="absmiddle" name="selJGXX"  
												 src="${ctx}/styles/${theme}/images/plus/select.gif"       
												 onClick="selCommon('System_7', false, 'SENDER_ID', 'XTYHID', 'forms[0]','SENDER_NAME', 'XM', 'selectPYXXParams')">
											</td>
											
										</tr>
										<tr>
										    <td width="16%" align="right" class="detail_label"> 年份： </td>
											<td width="35%" class="detail_content">
											  <select json="YEAR" name="YEAR" id="YEAR" style="width:155px;"  label="年份"  ></select>
											  <SPAN class="validate">&nbsp;*</SPAN> 
											</td>
										    <td width="16%" align="right" class="detail_label"> 月份： </td>
											<td width="35%" class="detail_content">
											  <select json="MONTH" name="MONTH" style="width:155px;"  id="MONTH"  label="MONTH"  ></select><SPAN class="validate">&nbsp;*</SPAN> 
											</td>
										</tr>
											<%--<td width="16%" align="right" class="detail_label"> 发布时间：</td>
											<td width="35%" class="detail_content">
						                     <input type="text" id="SENDER_TIME" name="SENDER_TIME" json="SENDER_TIME" onclick="SelectTime();" label="发布时间" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.SENDER_TIME}" >
											 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(SENDER_TIME);">
										     </td>
										--%> 
										
										<tr>
						               	   <td width="15%" align="right" class="detail_label">备注：</td>
										   <td width="35%" class="detail_content"  colspan="3">
						                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%">${rst.REMARK}</textarea>
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
</html>

<script language="JavaScript">
	SelDictShow("YEAR", "89", '${rst.YEAR}', "");
	SelDictShow("MONTH", "168", '${rst.MONTH}', "");
</script>
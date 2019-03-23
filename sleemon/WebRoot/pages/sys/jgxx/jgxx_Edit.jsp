
<!--  
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
-->
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
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"
			src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/sys/jgxx/jgxx_Edit.js"></script>
		<title>机构信息编辑</title>
	</head>
	<body class="bodycss1">
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
		
				
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				 <tr>
					 <!--占位用行，以免显示数据被浮动层挡住-->
			        <td height="20px">&nbsp;</td>
			    </tr>
				<tr>
					<td>
						<form name="form" id="mainForm">
							<input type="hidden" name="selectContion" value="STATE='启用'">
							<input type="hidden" name="ryxxSelectContion" value=" ryzt='启用' ">
							<input type="hidden" name="selectContion1"	value="DELFLAG = 0 and JGZT in( '启用','制定')">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													机构编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="JGBH" json="JGBH" name="JGBH" type="text"    autocheck="true"
														label="机构编号" inputtype="string" mustinput="true"  valueType="chinese:false"
														maxlength="30" value="${rst.JGBH}">
												</td>
												<td width="15%" align="right" class="detail_label">
													机构名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="JGMC" json="JGMC" name="JGMC" type="text" autocheck="true"
														label="机构名称" inputtype="string" mustinput="true"
														maxlength="100" value="${rst.JGMC}">
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													机构简称：
												</td>
												<td width="35%" class="detail_content">
													<input json="JGJC" name="JGJC" type="text" autocheck="true"
														label="机构简称" inputtype="string" maxlength="50"
														value="${rst.JGJC }">
												</td>
												<td width="15%" align="right" class="detail_label">
													法人代表：
												</td>
												<td width="35%" class="detail_content">
													<input json="FRDB" name="FRDB" type="text" autocheck="true"
														label="法人代表" inputtype="string" maxlength="30"
														value="${rst.FRDB }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													上级机构编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="SJJGID" json="SJJG" name="JGXXID" type="hidden"
														inputtype="string" seltarget="selJGXX" autocheck="true"
														maxlength="32" value="${rst.SJJG }">
													<input id="SJJGBH" name="SJJG" type="text" inputtype="string"
														seltarget="selJGXX" autocheck="true" maxlength="32"
														value="${rst.SJJGBH }" READONLY>
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_2', false, 'JGXXID', 'JGXXID', 'forms[0]','JGXXID,SJJG,SJJGMC', 'JGXXID,JGBH,JGMC', 'selectContion1')">
												</td>
												<td width="15%" align="right" class="detail_label">
													上级机构名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="SJJGMC" name="SJJGMC" type="text" inputtype="string"
														seltarget="selJGXX" label="上级机构名称" maxlength="32"
														value="${rst.SJJGMC }" READONLY>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													传真：
												</td>
												<td width="35%" class="detail_content">
													<input json="CZ" name="CZ" type="text" autocheck="true" id="CZ"
														label="传真" inputtype="string" maxlength="20" valueType="chinese:false"
														value="${rst.CZ }">
												</td>
												<td width="15%" align="right" class="detail_label">
													电子邮件：
												</td>
												<td width="35%" class="detail_content">
													<input json="DZYJ" name="DZYJ" type="text" autocheck="true" id="DZYJ"
														label="电子邮件" inputtype="string" maxlength="30" valueType="chinese:false"
														value="${rst.DZYJ }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													主页地址：
												</td>
												<td width="35%" class="detail_content">
													<input json="ZYDZ" name="ZYDZ" type="text"
														label="主页地址" inputtype="string" maxlength="30" valueType="chinese:false"
														value="${rst.ZYDZ }">
												</td>
												<td width="15%" align="right" class="detail_label">
													联系电话：
												</td>
												<td width="35%" class="detail_content">
													<input json="DH" id ="DH" name="DH" type="text" autocheck="true"
														label="联系电话" inputtype="string" maxlength="20"
														value="${rst.DH }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													国家：
												</td>
												<td width="35%" class="detail_content">
													<input name="GJ" type="text" inputtype="string"
														seltarget="selDZXX" autocheck="true" maxlength="32"
														value="${rst.GJ }" readonly>
													<input json="DZXXID" name="DZXXID" type="hidden"
														seltarget="selDZXX" autocheck="true" label="地址"
														inputtype="string" maxlength="50" value="${rst.DZXXID}">
													<img align="absmiddle" name="selDZXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_8', false, 'DZXXID', 'DZXXID', 'forms[0]','GJ,SF,CS', 'GJ,SF,CS', '')">
												</td>
												<td width="15%" align="right" class="detail_label">
													省/直辖市：
												</td>
												<td width="35%" class="detail_content">
													<input name="SF" type="text" seltarget="selDZXX"
														autocheck="true" inputtype="string" maxlength="250"
														value="${rst.SF}" readonly>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													城市：
												</td>
												<td width="35%" class="detail_content">
													<input name="CS" type="text" autocheck="true"
														seltarget="selDZXX" inputtype="string" maxlength="250"
														value="${rst.CS}" readonly>
												</td>
												<td width="15%" align="right" class="detail_label">
													邮政编码：
												</td>
												<td width="35%" class="detail_content">
													<input json="YZBM" name="YZBM" type="text" autocheck="true"
														label="邮政编码" inputtype="string" maxlength="10" valueType="chinese:false"
														value="${rst.YZBM }">
												</td>
											</tr>
											
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													分管财务人员：
												</td>
												<td width="35%" class="detail_content">
													<input json="FGCWRYXM" name="FGCWRYXM" id="FGCWRYXM" type="text" inputtype="string" autocheck="true"
														seltarget="selRYXX" maxlength="50" mustinput="false" value="${rst.FGCWRYXM }" label="分管财务人员" readonly>
													<input json="FGCWRYID" name="FGCWRYID" id="FGCWRYID" type="hidden"
														inputtype="string" seltarget="selRYXX" maxlength="50" value="${rst.FGCWRYID}" >
													<img align="absmiddle" name="selRYXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_0', false, 'FGCWRYID', 'RYXXID', 'forms[0]','FGCWRYXM', 'XM', 'ryxxSelectContion')">
												</td>
												
												<td width="15%" align="right" class="detail_label" nowrap>
													排序号：
												</td>
												<td width="35%" class="detail_content">
													<input json="XH" name="XH" type="text" autocheck="true"
														label="排序号" inputtype="int" mustinput="true" maxlength="9"
														value="${rst.XH }">
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													英文名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="JGYWMC" name="JGYWMC" type="text"
														autocheck="true" label="英文名称" inputtype="string" valueType="chinese:false"
														maxlength="50" value="${rst.JGYWMC }">
												</td>
												<td width="15%" align="right" class="detail_label">
													英文简称：
												</td>
												<td width="35%" class="detail_content">
													<input json="JGYWJC" name="JGYWJC" type="text"
														autocheck="true" label="英文简称" inputtype="string" valueType="chinese:false" 
														maxlength="50" value="${rst.JGYWJC }">
												</td>
											</tr>
											 <%
			                                  if(!com.hoperun.commons.util.StringUtil.isEmpty(Consts.ACCOUNT_DISPLAY)&& "true".equals(Consts.ACCOUNT_DISPLAY)){
		                                     %>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													机构帐套名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="ZTMC" name="ZTMC" type="text" inputtype="string" autocheck="true"
														seltarget="selZTXX" maxlength="100" mustinput="true" value="${rst.ZTMC }"
														label="帐套名称" readonly>
													<input json="ZTXXID" name="ZTXXID" type="hidden"
														inputtype="string" seltarget="selZTXX" maxlength="50"
														value="${rst.ZTXXID}" >
													<img align="absmiddle" name="selZTXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('System_5', false, 'ZTXXID', 'ZTXXID', 'forms[0]','ZTMC', 'ZTMC', 'selectContion')">
												</td>
												
												<td width="15%" align="right" class="detail_label" nowrap></td>
												<td width="35%" class="detail_content"></td>
											</tr>
											<%}else{ %>
					                           <tr style="display:none">
												<td width="15%" align="right" class="detail_label" nowrap>
													机构帐套名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="ZTMC" name="ZTMC" type="text" inputtype="string" autocheck="true"
														seltarget="selZTXX" maxlength="100" mustinput="true" value="<%=Consts.ACCOUNT_DISPLAY_NAME%>"
														label="帐套名称" readonly>
													<input json="ZTXXID" name="ZTXXID" type="hidden"
														inputtype="string" seltarget="selZTXX" maxlength="50"
														value="<%=Consts.ACCOUNT_CODE%>" >
														</td>
												
												<td width="15%" align="right" class="detail_label" nowrap></td>
												<td width="35%" class="detail_content"></td>
											</tr>
					                        <%}%>
											
																					
											<tr>
												<td width="15%" align="right" class="detail_label" >
													详细地址：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<textarea json="XXDZ" name="XXDZ" inputtype="string"
														maxlength="250" rows="3" cols="80%" allowHTML="true" ><c:out value="${rst.XXDZ}"></c:out></textarea>
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" >
													英文地址：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<textarea json="JGYWXXDZ" name="JGYWXXDZ" autocheck="true" label="英文地址" inputtype="string" 
														maxlength="250" rows="3" cols="80%" valueType="chinese:false"><c:out value="${rst.JGYWXXDZ}"></c:out></textarea>
												</td>
											</tr>
											<!--<tr>

												  <td width="15%" align="right" class="detail_label">
													当前状态：
												</td>
												<td width="35%" class="detail_content">
													<input json="JGZT" name="JGZT" type="text" autocheck="true"
														label="当前状态" maxlength="11" READONLY
														<c:if test="${empty rst.JGZT}"> value="制定"</c:if>
														<c:if test="${not empty rst.JGZT}">value="${rst.JGZT}"</c:if>>
												</td>
											</tr>-->
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

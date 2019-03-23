<!-- 
 *@module 系统管理
 *@func 部门信息
 *@version 1.1
 *@author 吴亚林
 *  -->
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
		<script type="text/javascript" src="${ctx}/pages/sys/bmxx/bmxxwh_Edit.js"></script>
		<title>部门信息编辑</title>
	</head>
	<body class="bodycss1">
		<div style="height: 100px">
		<div class="buttonlayer" id="floatDiv">
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td align="left" nowrap>
						<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
						<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
					</td>
				</tr>
			</table>
		</div>
		<!--浮动按钮层结束-->
		<!--占位用table，以免显示数据被浮动层挡住-->
		<table width="100%" height="25px">
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<form name="form" id="mainForm">
					   <input  id="BMXXID" name="BMXXID" type="hidden" value="${rst.BMXXID}" >
						<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
							<tr>
								<td class="detail2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="detail3">
										<tr>
											<td width="15%" align="right" class="detail_label">
												部门编号：
											</td>
											<td width="35%" class="detail_content">
												<c:if test="${empty rst.BMBH}">
													<input id="BMBH" json="BMBH" name="BMBH" type="text" autocheck="true" label="部门编号" valueType="chinese:false" inputtype="string" mustinput="true" maxlength="30" value="${rst.BMBH}">
												</c:if>
												<c:if test="${not empty rst.BMBH}">
													<input id="BMBH" json="BMBH" name="BMBH" type="text" autocheck="true" label="部门编号" inputtype="string" mustinput="true" maxlength="30" value="${rst.BMBH}" readonly="readonly">
												</c:if>
											</td>
											<td width="15%" align="right" class="detail_label">
												部门名称：
											</td>
											<td width="35%" class="detail_content">
												<input id="BMMC" json="BMMC" name="BMMC" type="text" autocheck="true" label="部门名称" inputtype="string" mustinput="true" maxlength="100" value="${rst.BMMC}">
										</tr>
										<tr>
										   <td width="15%" align="right" class="detail_label"> 部门简称： </td>
										   <td width="35%" class="detail_content">
												<input json="BMJC" name="BMJC" type="text" autocheck="true" label="部门简称" inputtype="string" mustinput="true" maxlength="50" value="${rst.BMJC }">
											</td>									
											<td width="15%" align="right" class="detail_label">
												所属机构：
											</td>
											<td width="35%" class="detail_content">
												<input type="hidden" id="JGZT" name="JGZT" value=" JGZT ='启用' and DELFlag =0 ">
												<input json="ZTXXID" id="ZTXXID" name="ZTXXID" type="hidden" seltarget="selSSJGXX" value="${rst.ZTXXID }">
												<input json="JGXXID" id="JGXXID" name="JGXXID" type="hidden" seltarget="selSSJGXX" value="${rst.JGXXID }" >
												
												<input json="JGMC" id="JGMC" name="JGMC" type="text" seltarget="selSSJGXX" autocheck="true" label="所属机构" inputtype="string" mustinput="true" maxlength="100" value="${rst.JGMC }" READONLY>
												<img align="absmiddle" name="selSSJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selSSJG();">
											</td>
											
										</tr>
										
										<tr>
											<td width="15%" align="right" class="detail_label">
												上级部门：
											</td>
											<td width="35%" class="detail_content" colspan="3">
												<input json="SJBM" id="SJBM" name="SJBM" type="hidden" seltarget="selSJBMXX" value="${rst.SJBM }" >
												<input json="SJBMMC" name="SJBMMC" id="SJBMMC" type="text" autocheck="true" seltarget="selSJBMXX" label="上级部门名称" inputtype="string" maxlength="50" value="${rst.SJBMMC }" READONLY>
												<input type='hidden' id='con' />
												<img align="absmiddle" name="selSJBMXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selSJBM();">
											</td>											
											
										</tr>										
										<tr>
										   <td width="15%" align="right" class="detail_label">
												排序号：
											</td>
											<td width="35%" class="detail_content">
												<input json="XH" name="XH" type="text" autocheck="true" label="排序号" inputtype="int" mustinput="true" maxlength="5" value="${rst.XH }">
											</td>
											<td width="15%" align="right" class="detail_label">
												电子邮件：
											</td>
											<td width="35%" class="detail_content">
												<input json="DZYJ" name="DZYJ" id="DZYJ" type="text" autocheck="true" label="电子邮件" inputtype="string" maxlength="50" value="${rst.DZYJ }">
											</td>
											
										</tr>										
										<tr>
										 <td width="15%" align="right" class="detail_label">
												主页地址：
											</td>
											<td width="35%" class="detail_content">
												<input json="ZYDZ" name="ZYDZ" type="text" autocheck="true" label="主页地址" inputtype="string" maxlength="50" value="${rst.ZYDZ }">
											</td>									
											<td width="15%" align="right" class="detail_label">
												邮政编码：
											</td>
											<td width="35%" class="detail_content">
												<input json="YZBM" name="YZBM" id="YZBM" type="text" autocheck="true" label="邮政编码" inputtype="string" maxlength="10" value="${rst.YZBM }">
											</td>
										</tr>										
										<tr>
										 <td width="15%" align="right" class="detail_label">
												电话：
											</td>
											<td width="35%" class="detail_content">
												<input json="DH" name="DH" id="DH" type="text" autocheck="true" label="电话" inputtype="string" maxlength="50" value="${rst.DH }">
											</td>
											<td width="15%" align="right" class="detail_label">
												传真：
											</td>
											<td width="35%" class="detail_content">
												<input json="CZ" id="CZ" name="CZ" type="text" autocheck="true" label="传真" inputtype="string" maxlength="50" value="${rst.CZ }">
											</td>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												其它说明：
											</td>
											<td width="35%" class="detail_content"  colspan="3">
												<textarea json="QTSM" name="QTSM" inputtype="string" maxlength="250" rows="3" cols="80%"><c:out value="${rst.QTSM}"></c:out></textarea>
											</td>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												详细地址：
											</td>
											<td width="35%" class="detail_content" colspan="3">
											    <textarea json="XXDZ" name="XXDZ" inputtype="string" maxlength="250" rows="3" cols="80%"><c:out value="${rst.XXDZ}"></c:out></textarea>
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

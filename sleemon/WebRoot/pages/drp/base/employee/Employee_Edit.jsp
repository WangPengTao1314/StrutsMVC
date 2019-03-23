<!-- 
 *@module 分销业务
 *@func 人员信息维护
 *@version 1.1
 *@author lyg
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
		<script type="text/javascript" src="${ctx}/pages/drp/base/employee/Employee_Edit.js"></script>
		<title>人员信息编辑</title>
	</head>
	<body  class="bodycss1">
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
					<form name="mainForm" id="mainForm">
						<table id="jsontb" width="100%" border="0" cellpadding="4"
							cellspacing="4" class="detail">
							<tr>
								<td class="detail2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
										<input type="hidden" name="selectParams" value="BMZT='启用'  and  BMXXID = '${rst.ZTXXID}'  ">
										<input type="hidden" name=selectTerminal value="STATE='启用' and DEL_FLAG=0 and CHANN_ID_P='${rst.ZTXXID}'">
										<input type="hidden" name="topTab" id="topTab" value="${rst.topTab}"/>
										<tr>
											<td width="15%" align="right" class="detail_label">
												人员编号：
											</td>
											<td width="35%" class="detail_content">
												<input json="RYBH" name="RYBH" type="text" json="RYBH"  
													autocheck="true" label="人员编号" inputtype="string" mustinput="true" 
													maxlength="32"
													<c:if test="${rst.RYBH !='' &&rst.RYBH !=null}">value="${rst.RYBH}"</c:if>
													<c:if test="${empty rst.RYBH}">value="自动生成"</c:if>
													READONLY
													>
											</td>
											<td width="15%" align="right" class="detail_label">
											</td>
											<td width="35%" class="detail_content">
											</td>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												姓名：
											</td>
											<td width="35%" class="detail_content">
												<input json="XM" name="XM" id="XM" type="text" autocheck="true" label="姓名" inputtype="string" mustinput="true"  maxlength="30" value="${rst.XM}">
											</td>
											<td width="15%" align="right" class="detail_label">
												用户名：
											</td>
											<td width="35%" class="detail_content">
												<input json="YHM" name="YHM" id="YHM" type="text" autocheck="true" label="用户名"  inputtype="string" mustinput="true"  maxlength="30" value="${rst.YHM}"
													<c:if test="${rst.RYBH !='' &&rst.RYBH !=null}"> READONLY </c:if>
												>
											</td>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												性别：
											</td>
											<td width="35%" class="detail_content">
												<input id="XB" name="XB" json="XB" type="radio" value="男" <c:if test="${rst.XB!='女' }">checked="checked"</c:if> />男
												<input id="XB" name="XB" json="XB" type="radio" value="女" <c:if test="${rst.XB=='女' }">checked="checked"</c:if>/>女
												
											</td>
											<td width="15%" align="right" class="detail_label">
												手机：
											</td>
											<td width="35%" class="detail_content">
												<input json="SJ" name="SJ" id="SJ" type="text" autocheck="true" label="手机" inputtype="string" maxlength="50" value="${rst.SJ }">
											</td>
										</tr>
										<tr >
											 <td width="15%" align="right" class="detail_label">
										    	人员类别:
										    </td>
										    <td width="35%" class="detail_content">
										    	<select label="人员类别" name="RYLB"
													inputtype="string"  autocheck="true" onchange="upType()"
													style="width: 153" id="RYLB" json="RYLB"
													value="${rst.RYLB}" mustinput="true">
												</select>
										    </td>
											 <td width="15%" align="right" class="detail_label" id="tdNameAddValue">
										    	人员岗位:
										    </td>
										    <td width="35%" class="detail_content" id="tdValueAddValue">
										    	<select label="人员岗位" name="RYJB"
													inputtype="string"  autocheck="true"
													style="width: 153" id="RYJB" json="RYJB"
													value="${rst.RYJB}"  mustinput="true">
												</select>
										    </td>
										    <td width="15%" align="right" class="detail_label" id="tdName"></td>
										    <td width="35%" class="detail_content" id="tdValue">
										    </td>
										</tr>
										<script language="javascript">
											upType('${rst.RYLB}');
										</script>
										<tr>
											<td width="15%" align="right" class="detail_label">
												所属部门编号：
											</td>
											<td width="35%" class="detail_content">
												<input json="BMXXID" name="BMXXID" id="BMXXID" type="hidden" autocheck="true" seltarget="selJGXX" label="部门id"  value="${rst.BMXXID }" READONLY>
												<input json="BMBH" name="BMBH" id="BMBH" type="text" autocheck="true"  label="所属部门编号" mustinput="true" inputtype="string"  value="${rst.BMBH }" readonly>
												<img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selBMXX()">
											</td>
											<td width="15%" align="right" class="detail_label">
												所属部门名称：
											</td>
											<td width="35%" class="detail_content">
												<input json="BMMC" name="BMMC" id="BMMC" type="text" autocheck="true" inputtype="string"  label="所属部门名称"  mustinput="true"  value="${rst.BMMC }" readonly>
											</td>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												所属机构编号：
											</td>
											<td width="35%" class="detail_content">
												<input json="JGXXID" name="JGXXID" id="JGXXID" type="hidden" inputtype="string" autocheck="true"  maxlength="52" value="${rst.JGXXID }" READONLY>
												<input json="JGBH" name="JGBH" id="JGBH" type="text" inputtype="string"  autocheck="true" mustinput="true" label="所属机构编号" maxlength="52" value="${rst.JGBH }" READONLY>
											</td>
											<td width="15%" align="right" class="detail_label">
												所属机构名称：
											</td>
											<td width="35%" class="detail_content">
												<input json="JGMC" name="JGMC" id="JGMC" type="text" inputtype="string"  autocheck="true" mustinput="true" label="所属机构名称" value="${rst.JGMC }" READONLY>
											</td>
										</tr>
										<c:if test="${TERM_DECT_CTR_FLAG==1}">
										<tr >
											
												<td width="15%" align="right" class="detail_label">可改终端最低销售折扣：</td>
											    <td width="35%" class="detail_content">
							                    	<input json="TERM_DECT_FROM"  name="TERM_DECT_FROM" id="TERM_DECT_FROM" autocheck="true" label="可改终端最低销售折扣"  type="text"   inputtype="float"     mustinput="true"   valueType="8,2"  value="${rst.TERM_DECT_FROM}"/>
											    </td>
										    <td width="15%" align="right" class="detail_label">
										    </td>
										    <td width="35%" class="detail_content"><br><br></td>
										</tr>
										</c:if>
										<tr>
											<td width="15%" align="right" class="detail_label">
												公司电话：
											</td>
											<td width="35%" class="detail_content">
												<input json="GSDH" name="GSDH"  id="GSDH" type="text" autocheck="true" label="公司电话" inputtype="string" maxlength="50" value="${rst.GSDH }">
											</td>
											<td width="15%" align="right" class="detail_label">
												电子邮件：
											</td>
											<td width="35%" class="detail_content">
												<input json="DZYJ" name="DZYJ" id="DZYJ"  type="text" autocheck="true" label="电子邮件" inputtype="string" maxlength="50" value="${rst.DZYJ }">
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
	</body>
	<script language="javascript">
		SelDictShow("RYLB", "BS_80", '${rst.RYLB}', "");
		SelDictShow("RYJB", "BS_81", '${rst.RYJB}', "");
	</script>
</html>

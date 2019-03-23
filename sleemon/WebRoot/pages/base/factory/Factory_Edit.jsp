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
		<script type="text/javascript" src="${ctx}/pages/base/factory/Factory_Edit.js"></script>
		<title>生产工厂编辑</title>
	</head>
	<body class="bodycss1">
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
						<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
							<input type="hidden" name=selectParams value=" DEL_FLAG=0 and STATE='启用' ">
							<tr>
								<td class="detail2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="detail3">
										<tr>
											<td width="15%" align="right" class="detail_label">
												生产工厂编号：
											</td>
											<td width="35%" class="detail_content">
												<c:if test="${empty rst.FACTORY_NO}">
													<input id="FACTORY_NO" json="FACTORY_NO" name="FACTORY_NO" type="text" autocheck="true" label="生产工厂编号" valueType="chinese:false" inputtype="string" mustinput="true" maxlength="30" value="${rst.FACTORY_NO}"></input>
												</c:if>
												<c:if test="${not empty rst.FACTORY_NO}">
													<input json="FACTORY_NO" name="FACTORY_NO" type="text" autocheck="true" label="生产工厂编号" inputtype="string" mustinput="true" maxlength="30" value="${rst.FACTORY_NO}" readonly="readonly"></input>
												</c:if>
											</td>
											<td width="15%" align="right" class="detail_label">
												生产工厂名称：
											</td>
											<td width="35%" class="detail_content">
												<input id="FACTORY_NAME" json="FACTORY_NAME" name="FACTORY_NAME" type="text" autocheck="true" label="生产工厂名称" inputtype="string" mustinput="true" maxlength="50" value="${rst.FACTORY_NAME}"></input>
										</tr>
										<tr>
											<td width="15%" align="right" class="detail_label">
												生产基地编号：
											</td>
											<td width="35%" class="detail_content">
												<input id="SHIP_POINT_ID" json="SHIP_POINT_ID" name="SHIP_POINT_ID" type="hidden"  value="${rst.SHIP_POINT_ID}"  />
												<input id="SHIP_POINT_NO" json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="text" autocheck="true" label="生产基地编号"  inputtype="string" mustinput="true" maxlength="30" value="${rst.FACTORY_NO}" readonly />
												<img align="absmiddle" name="" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"
														onClick="selCommon('BS_22', false, 'SHIP_POINT_ID', 'SHIP_POINT_ID', 'forms[0]','SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME', 'SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME', 'selectParams')">
											</td>
											<td width="15%" align="right" class="detail_label">
												生产基地名称：
											</td>
											<td width="35%" class="detail_content">
												<input id="SHIP_POINT_NAME" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" type="text" autocheck="true" label="生产基地名称" inputtype="string" mustinput="true" maxlength="50" value="${rst.SHIP_POINT_NAME}" readonly></input>
										</tr>
										<tr>										
											<td width="15%" align="right" class="detail_label">
												联系人：
											</td>
											<td width="35%" class="detail_content">
												<input json="PERSON_CON" id="PERSON_CON" name="PERSON_CON" type="text" label="联系人" inputtype="string"  maxlength="30" value="${rst.PERSON_CON }" ></input>
											</td>
											<td width="15%" align="right" class="detail_label">
												手机：
											</td>
											<td width="35%" class="detail_content">
												<input json="MOBILE_NO" name="MOBILE_NO" id="MOBILE_NO" type="text" autocheck="true" label="手机" inputtype="string" maxlength="11" value="${rst.MOBILE_NO }"></input>
											</td>
										</tr>
										
										<tr>
											<td width="15%" align="right" class="detail_label">
												电话：
											</td>
											<td width="35%" class="detail_content">
												<input json="TEL" name="TEL" id="TEL" type="text" autocheck="true" label="电话" inputtype="string" maxlength="30" value="${rst.TEL }"></input>
											</td>											
											<td width="15%" align="right" class="detail_label">
												邮政编号：
											</td>
											<td width="35%" class="detail_content">
												<input json="POST" name="POST" id="POST" type="text" autocheck="true" label="邮政编号" inputtype="string" maxlength="30" value="${rst.POST }"></input>
											</td>
										</tr>										
										<tr>
										   <td width="15%" align="right" class="detail_label">
												传真：
											</td>
											<td width="35%" class="detail_content">
												<input json="TAX" name="TAX" id="TAX" type="text" autocheck="true" label="传真" inputtype="string" maxlength="30" value="${rst.TAX }"></input>
											</td>
											<td width="15%" align="right" class="detail_label">
											</td>
											<td width="35%" class="detail_content">
											</td>
										</tr>	
									 										
										<tr>
											<td width="15%" align="right" class="detail_label">
												详细地址：
											</td>
											<td width="50%" class="detail_content" colspan="3"> 
											    <textarea json="ADDRESS" name="ADDRESS" label="详细地址" inputtype="string" autocheck="true" mustinput="true" maxlength="250" rows="4" cols="80%"><c:out value="${rst.ADDRESS}"></c:out></textarea>
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
</html>

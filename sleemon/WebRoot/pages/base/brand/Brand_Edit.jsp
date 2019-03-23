<!-- 
 /**
 * @module 系统管理
 * @func 品牌信息
 * @version 1.1
 * @author 郭利伟
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
		<script type="text/javascript" src="${ctx}/pages/base/brand/Brand_Edit.js"></script>
		<title>品牌信息编辑</title>
	<script type="text/javascript" src="brand_List.js"></script></head>
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
							<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
											<tr>
											    <td width="16%" align="right" class="detail_label">
													品牌ID：
												</td>
												<td width="35%" class="detail_content">
													<input json="BRAND_ID" name="BRAND_ID" id="BRAND_ID" 
													autocheck="true"  inputtype="string" mustinput="true"  maxlength="30"
													 <c:if test="${not empty rst.BRAND_ID}">readonly</c:if>
													 type="text" label="品牌ID"  value="${rst.BRAND_ID}">
												</td>
												
												<td width="16%" align="right" class="detail_label">
													品牌名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="BRAND" name="BRAND" id="BRAND" type="text" autocheck="true" label="品牌名称" inputtype="string" mustinput="true"  maxlength="50" value="${rst.BRAND}">
												</td>
											</tr>
											<tr>
											    <td width="16%" align="right" class="detail_label">
													英文名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="BRAND_EN" name="BRAND_EN" id="BRAND_EN" type="text" autocheck="true" label="英文名称" inputtype="string"   valueType="chinese:false" mustinput="true"  maxlength="50" value="${rst.BRAND_EN}">
												</td>
												<td width="16%" align="right" class="detail_label">
												</td>
												<td width="35%" class="detail_content">
												</td>
											</tr>
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

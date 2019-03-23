
<!--  
/**
 * @module 系统管理
 * @func 货品信息编辑
 * @version 1.1
 * @author 刘曰刚
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
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/base/pdtmenu/Pdtmenu_Edit.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>货品信息编辑</title>
	</head>
	<body class="bodycss1">
		<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)"
								title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B"
								accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>


			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<!--占位用行，以免显示数据被浮动层挡住-->
					<td height="20px">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<form name="form" id="mainForm">
							<input type="hidden" name=selectFlag value=" STATE='启用' and FINAL_NODE_FLAG=0 ">
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
											<tr>												
												<td width="15%" align="right" class="detail_label">
													货品分类编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="PRD_NO" json="PRD_NO" name="PRD_NO" type="text"
														autocheck="true" label="货品分类编号" inputtype="string" 
														mustinput="true" maxlength="30" 
														value="${rst.PRD_NO}"
														<c:if test="${rst.PRD_NO!=NULL}">READONLY</c:if>
														>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													货品分类名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="PRD_NAME" id="PRD_NAME" name="PRD_NAME" type="text" autocheck="true"
														label="货品分类名称" inputtype="string" mustinput="true" maxlength="100"
														value="${rst.PRD_NAME}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													上级货品分类编号：
												</td>
												<td width="35%" class="detail_content">
													<input id="PAR_PRD_ID" json="PAR_PRD_ID" name="PAR_PRD_ID" type="hidden" inputtype="string" maxlength="30" value="${rst.PAR_PRD_ID}">
													<input id="PAR_PRD_NO" json="PAR_PRD_NO"  name="PAR_PRD_NO" type="text" inputtype="string" maxlength="30" value="${rst.PAR_PRD_NO}" label="上级货品分类编号" READONLY >
													<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="selCommon('BS_21', false, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_ID,PAR_PRD_NO,PAR_PRD_NAME', 'PRD_ID,PRD_NO,PRD_NAME', 'selectFlag')">
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													上级货品分类名称：
												</td>
												<td width="35%" class="detail_content">
													<input json="PAR_PRD_NAME" id="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text" autocheck="true"
														label="上级货品分类名称" inputtype="string" maxlength="100" READONLY
														value="${rst.PAR_PRD_NAME}">
												</td>
											</tr>
											<tr>												
												<td width="15%" align="right" class="detail_label">
													品牌：
												</td>
												<td width="35%" class="detail_content">
													<select id="BRAND" json="BRAND" name="BRAND" style="width: 150px" >
														<option value=""
															<c:if test="${rst.BRAND==null}"> selected="selected" </c:if>  
														>--请选择--</option>
														<c:forEach items="${list}" var="brand">
															<option value="${brand}"
																<c:if test="${rst.BRAND==brand}"> selected="selected" </c:if> 
															>${brand}</option>
														</c:forEach>
													</select>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													默认交期：
												</td>
												<td width="35%" class="detail_content">
													<input json="DEAFULT_ADVC_SEND_DATE" id="DEAFULT_ADVC_SEND_DATE" name="DEAFULT_ADVC_SEND_DATE" autocheck="true" label="默认交期"    type="text"   inputtype="int"  value="${rst.DEAFULT_ADVC_SEND_DATE}"/>
												</td>
											</tr>
											<tr>
							               		<td width="15%" align="right" class="detail_label">备注：</td>
											   <td width="35%" class="detail_content" colspan="3">
							                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
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
	<%@ include file="/pages/common/uploadfile/picUpdfile.jsp"%>
	<script language="JavaScript">
		SelDictShow("PRD_TYPE", "172", '${rst.PRD_TYPE}', "");
		//SelDictShow("PRD_COLOR", "BS_6", '${rst.PRD_COLOR}', "");
		uploadFile('PIC_PATH', 'PICTURE_DIR');
		uploadFile('DTL_PATH', 'PICTURE_DIR');
	</script>
</html>

<!-- 
 *@module 渠道管理-分销商管理
 *@function   分销渠道信息登记
 *@version 1.1
 *@author gu_hx
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>分销渠道信息登记详情</title>
	</head>
	<body class="bodycss1" >
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" style="margin-top: -20px;">
			<tr>   
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">申请单编号：</td>
							<td width="35%" class="detail_content">${rst.DISTRIBUTOR_REQ_NO}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">申请人：</td>
							<td width="35%" class="detail_content">${rst.REQ_NAME}&nbsp;</td>
					     </tr>
					     <tr>
							<td width="15%" align="right" class="detail_label">申请时间：</td>
							<td width="35%" class="detail_content">${rst.REQ_DATE}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">分销渠道名称：</td>
							<td width="35%" class="detail_content">${rst.DISTRIBUTOR_NAME}&nbsp;</td>
					    </tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">联系电话：</td>
							<td width="35%" class="detail_content">${rst.TEL}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">分销类型：</td>
							<td width="35%" class="detail_content">${rst.DISTRIBUTOR_TYPE}&nbsp;</td>
						 </tr>
						 <tr>
						    <td width="15%" align="right" class="detail_label">所属加盟商渠道编号：</td>
							<td width="35%" class="detail_content">${rst.CHANN_NO}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">所属加盟商渠道名称：</td>
							<td width="35%" class="detail_content">${rst.CHANN_NAME}&nbsp;</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">战区：</td>
							<td width="35%" class="detail_content">${rst.AREA_NAME_P}&nbsp;</td>
							<td width="15%" align="right"class="detail_label">营销经理：</td>
							<td width="35%" class="detail_content">${rst.AREA_MANAGE_NAME}&nbsp;</td>
						</tr>
						<tr>
						    <td width="15%" align="right"class="detail_label">省份：</td>
							<td width="35%" class="detail_content">${rst.PROV }&nbsp;</td>
							<td width="15%" align="right" class="detail_label">城市：</td>
							<td width="35%" class="detail_content">${rst.CITY }&nbsp;</td>
						</tr>						
						<tr>
						    <td width="15%" align="right" class="detail_label">区县：</td>
							<td width="35%" class="detail_content">${rst.COUNTY}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">联系人：</td>
							<td width="35%" class="detail_content">${rst.PERSON_CON}&nbsp;</td>
						</tr>						
						<tr>							
							<td width="15%" align="right" class="detail_label">联系人电话：</td>
							<td width="35%" class="detail_content">${rst.MOBILE}&nbsp;</td>
							<td width="15%" align="right"class="detail_label">传真：</td>
							<td width="35%" class="detail_content">${rst.TAX }&nbsp;</td>
						</tr>
						<tr>							
							<td width="15%" align="right" class="detail_label">邮箱：</td>
							<td width="35%" class="detail_content">${rst.EMAIL}&nbsp;</td>
							<td width="15%" align="right" class="detail_label" >商场名称：</td>
							<td width="35%" class="detail_content">${rst.SALE_STORE_NAME }&nbsp;</td>					
						</tr>
						<tr>							
							<td width="15%" align="right" class="detail_label">商场地址：</td>
							<td width="35%" class="detail_content">${rst.SALE_STORE_LOCAL }&nbsp;</td>
							<td width="15%" align="right" class="detail_label">经营品牌：</td>
							<td width="35%" class="detail_content">${rst.BUSS_BRAND }&nbsp;</td>
						</tr>
						
						<tr>							
							<td width="15%" align="right" class="detail_label">主营品类：</td>
							<td width="35%" class="detail_content">${rst.BUSS_CLASS }&nbsp;</td>
							<td width="15%" align="right" class="detail_label">合作时间：</td>
							<td width="35%" class="detail_content">${rst.COOPER_DATE }&nbsp;</td>
						</tr>
						<c:if test="${nFlag == true}">
							<tr>
								<td width="15%" align="right" class="detail_label">合作方案：</td>
								<td width="35%" class="detail_content" colspan="3">
									<input type="hidden" id ="COOPER_PLAN_ATT" value='${rst.COOPER_PLAN_ATT}' />
								</td>							
							</tr>
						</c:if>
						<c:if test="${nFlag == false}">
							<tr>
							    <td width="15%" align="right" class="detail_label">附件：</td>
								<td width="35%" class="detail_content" colspan="3">
									<input type="hidden" id ="ATT" value='${rst.ATT}' />
								</td>							
							</tr>
						</c:if>						
												
						<tr>
		                   <td  class="detail_label">
		                                                         流程跟踪：
		                   </td>
		                   <td colspan="8" class="detail2">
		                     <table id="ordertb" width="99.98%" border="0" cellpadding="1" cellspacing="1" class="lst" >
								<tr  >
									<th nowrap align="center">序号</th>
									<th nowrap align="center" >操作者</th>
									<th nowrap align="center" >操作</th>
									<th nowrap align="center" >时间</th>
									<th nowrap align="center" >意见</th>
									<th nowrap align="center" >下一操作者</th>
								</tr>
								<c:forEach items="${flowInfoList}" var="flow" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
									<td nowrap align="center" width="20%">${rr}&nbsp;</td>
									<td nowrap align="center" width="20%">${flow.OPERATORNAME}&nbsp;</td>
									<td nowrap align="center" width="20%">${flow.OPERATION}&nbsp;</td>
									<td nowrap align="center" width="20%">${flow.OPERATETIME}&nbsp;</td>
									<td nowrap align="center" width="400px;" style="table-layout:fixed;word-break:break-all; word-wrap:break-word;" >${flow.REMARK}&nbsp;</td>
									<td nowrap align="center" width="20%">${flow.NEXTOPERATORNAME}&nbsp;</td>
								</tr>
								</c:forEach>
								<c:if test="${empty flowInfoList}">
								<tr>
									<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;草拟，尚未进入审核流程!&nbsp;</td>
								</tr>
								</c:if>
							</table>
		                   </td>
		               </tr>
					</table>
				</td>
			</tr>			
		</table>
	</body>
	<script type=text/javascript >
	displayDownFile('COOPER_PLAN_ATT','SAMPLE_DIR',true,false);
	displayDownFile('ATT','SAMPLE_DIR',true,false);
</script>
</html>

<!-- 
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
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
							<td width="35%" class="detail_content">${rst.CHANN_END_REQ_NO}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">申请时间：</td>
							<td width="35%" class="detail_content">${rst.REQ_DATE}&nbsp;</td>
					     </tr>
					     <tr>
							<td width="15%" align="right" class="detail_label">渠道编号：</td>
							<td width="35%" class="detail_content">${rst.CHANN_NO}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">渠道名称：</td>
							<td width="35%" class="detail_content">${rst.CHANN_NAME}&nbsp;</td>
					    </tr>
						<tr>						    
							<td width="15%" align="right" class="detail_label">渠道类型：</td>
							<td width="35%" class="detail_content">${rst.CHANN_TYPE}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">加盟日期：</td>
							<td width="35%" class="detail_content">${rst.JOIN_DATE}&nbsp;</td>
						 </tr>
						 <tr>
						    <td width="15%" align="right" class="detail_label">渠道联系人：</td>
							<td width="35%" class="detail_content">${rst.CHANN_PERSON_CON}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">渠道联系人电话：</td>
							<td width="35%" class="detail_content">${rst.CHANN_MOBILE}&nbsp;</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">联系人：</td>
							<td width="35%" class="detail_content">${rst.PERSON_CON}&nbsp;</td>
							<td width="15%" align="right"class="detail_label">公司电话(座机)：</td>
							<td width="35%" class="detail_content">${rst.TEL}&nbsp;</td>
						</tr>
						<tr>							
							<td width="15%" align="right" class="detail_label">手机：</td>
							<td width="35%" class="detail_content">${rst.MOBILE}&nbsp;</td>
							<td width="15%" align="right"class="detail_label">传真：</td>
							<td width="35%" class="detail_content">${rst.TAX }&nbsp;</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">所属战区编号：</td>
							<td width="35%" class="detail_content">${rst.WAREA_NO}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">所属战区名称：</td>
							<td width="35%" class="detail_content">${rst.WAREA_NAME}&nbsp;</td>
						</tr>	
						<tr>
						    <td width="15%" align="right"class="detail_label">营销经理：</td>
							<td width="35%" class="detail_content">${rst.AREA_MANAGE_NAME}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">营销经理电话：</td>
							<td width="35%" class="detail_content">${rst.AREA_MANAGE_TEL }&nbsp;</td>
						</tr>						
						<tr>
						    <td width="15%" align="right" class="detail_label">终端信息：</td>
							<td width="35%" class="detail_content" colspan="3">${rst.TERM_NAMES}&nbsp;</td>							
						</tr>	
						<tr>
						    <td width="15%" align="right" class="detail_label">保证金：</td>
							<td width="35%" class="detail_content">${rst.DEPOSIT}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">保证金<a style="color: red;font-weight: normal;">[实际金额]</a>：</td>
							<td width="35%" class="detail_content">${rst.DEPOSIT_CONFIRM}&nbsp;</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">账面余额：</td>
							<td width="35%" class="detail_content">${rst.LEFT_ACCT_AMOUNT}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">账面余额<a style="color: red;font-weight: normal;">[实际金额]</a>：</td>
							<td width="35%" class="detail_content">${rst.LEFT_ACCT_AMOUNT_CONFIRM}&nbsp;</td>
						</tr>						
						
						<tr>							
							<td width="15%" align="right" class="detail_label">退货金额：</td>
							<td width="35%" class="detail_content">${rst.RETURN_AMOUNT}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">退货金额<a style="color: red;font-weight: normal;">[实际金额]</a>：</td>
							<td width="35%" class="detail_content">${rst.RETURN_AMOUNT_CONFIRM}&nbsp;</td>					
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >退货清单附件：</td>
							<td width="35%" class="detail_content" colspan="3" >
								<input type="hidden" id ="RETURN_ATT" value='${rst.RETURN_ATT}' />
							</td>					
						</tr>
						<tr>							
							<td width="15%" align="right" class="detail_label">终止合作原因：</td>
							<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.END_RESON }</td>							
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">制单人：</td>
							<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
							<td width="15%" align="right" class="detail_label">制单时间：</td>
							<td width="35%" class="detail_content">${rst.CRE_TIME}&nbsp;</td>
						</tr>
						<tr>
							
							<td width="15%" align="right" class="detail_label">制单部门：</td>
							<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
							<td width="15%" align="right" class="detail_label">制单机构：</td>
							<td width="35%" class="detail_content">${rst.ORG_NAME }&nbsp;</td>
						</tr>				
						<tr>
							
							<td width="15%" align="right"class="detail_label">更新人：</td>
							<td width="35%" class="detail_content">${rst.UPD_NAME }</td> 
							<td width="15%" align="right" class="detail_label" >更新时间：</td>
							<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >状态：</td>
							<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
							<td width="15%" align="right" class="detail_label" ></td>
							<td width="35%" class="detail_content">&nbsp;</td>
						</tr>
						
						<tr>							
							<td width="15%" align="right" class="detail_label">备注：</td>
							<td width="35%" class="detail_content" colspan="3" style="word-break:break-all"> ${rst.REMARK} </td>
						</tr>				
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
	displayDownFile('RETURN_ATT','SAMPLE_DIR',true,false);
</script>
</html>

<!-- 
 *@module 渠道管理-终端管理
 *@func   新开门店申请单维护
 *@version 1.1
 *@author zcx
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
		<title>新开门店申请单详情</title>
	</head>
	<body class="bodycss1" >
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" style="margin-top: -20px;">
			<tr>   
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">
								新增终端申请单编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.OPEN_TERMINAL_REQ_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CRE_NAME}
							</td>
					     </tr>
					     <tr>
							<td width="15%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="35%" class="detail_content">
								${rstT.RNVTN_PROP}
							</td>
							<td width="15%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
						    <c:choose>
		   						<c:when test="${empty rst.TERM_NO}">
		 						     待办生成
		   						</c:when>
		   						<c:otherwise>
		   							 ${rst.TERM_NO}
		   						</c:otherwise>
							</c:choose>   
							</td>
					    </tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERM_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								终端类型：
							</td>
							<td width="35%" class="detail_content">
								${rst.TERM_TYPE}
							</td>
						 </tr>
						 <tr>
						    <td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NO_P}
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NAME_P}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								营业性质：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.BUSS_NATRUE}
							</td>
							<td width="15%" align="right" class="detail_label">
								打款开户银行：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.PLAY_BANK_NAME}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								打款银行账号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.PLAY_BANK_ACCT}
							</td>
							<td width="15%" align="right" class="detail_label">
								物流方式：
							</td>
							<td width="35%" class="detail_content">
								${rst.LOGIC_TYPE}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								终端版本：
							</td>
							<td width="35%" class="detail_content">
								${rst.TERM_VERSION}
							</td>
							<td width="15%" align="right" class="detail_label">
								区域编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NO}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								区域名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								国家：
							</td>
							<td width="35%" class="detail_content">
								${rst.NATION}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								省份：
							</td>
							<td width="35%" class="detail_content">
								${rst.PROV}
							</td>
							<td width="15%" align="right" class="detail_label">
								城市：
							</td>
							<td width="35%" class="detail_content">
								${rst.CITY}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								区县：
							</td>
							<td width="35%" class="detail_content">
								${rst.COUNTY}
							</td>
                            <td width="15%" align="right" class="detail_label">
								城市类型：
							</td>
							<td width="35%" class="detail_content">
								${rst.CITY_TYPE}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								营业状态：
							</td>
							<td width="35%" class="detail_content">
								${rst.BUSS_STATE}
							</td>
						    <td width="15%" align="right" class="detail_label">
								开店类型：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.BEG_BUSS_TYPE}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.SALE_STORE_NAME}
							</td>
						    <td width="15%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="35%" class="detail_content">
							    ${rstTt.LOCAL_TYPE}
							</td>
						</tr>
                        <tr>
                            <td width="15%" align="right" class="detail_label">
								开业时间：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.BEG_SBUSS_DATE}
							</td>
 	                        <td width="15%" align="right" class="detail_label">
								联系人：
							</td>
							<td width="35%" class="detail_content">
								${rst.PERSON_CON}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								联系电话：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.TEL}
							</td>
							<td width="15%" align="right" class="detail_label">
								手机：
							</td>
							<td width="35%" class="detail_content">
								${rst.MOBILE}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								传真：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.TAX}
							</td>
							<td width="15%" align="right" class="detail_label">
								邮政编码：
							</td>
							<td width="35%" class="detail_content">
								${rst.POST}
							</td>
                        </tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								电子邮件：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.EMAIL}
							</td>
						    <td width="15%" align="right" class="detail_label">
								网站：
							</td>
							<td width="35%" class="detail_content">
								${rst.WEB}
							</td>
						 </tr>
						 <tr>
						    <td width="15%" align="right" class="detail_label">
								法人代表：
							</td>
							<td width="35%" class="detail_content">
								${rst.LEGAL_REPRE}
							</td>
							<td width="15%" align="right" class="detail_label">
								营业执照号：
							</td>
							<td width="35%" class="detail_content">
							   ${rst.BUSS_LIC}
							</td>
						</tr>
						<tr> 
						    <td width="15%" align="right" class="detail_label">
								税务登记号：
							</td>
							<td width="35%" class="detail_content">
								${rst.AX_BURDEN}
							</td>
							<td width="15%" align="right" class="detail_label">
								组织机构代码证：
							</td>
							<td width="35%" class="detail_content">
								${rst.ORG_CODE_CERT}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								经营范围：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.BUSS_SCOPE}
							</td>
							<td width="15%" align="right" class="detail_label">
								财务对照码：
							</td>
							<td width="35%" class="detail_content">
								${rst.FI_CMP_NO}
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								实际营业面积：
							</td>
							<td width="35%" class="detail_content">
								${rst.BUSS_AREA}
							</td>
							<td width="15%" align="right" class="detail_label">
								楼层数：
							</td>
							<td width="35%" class="detail_content">
								${rst.STOR_NO}
							</td>
						 </tr>
						 <tr>
							    <td width="15%" align="right" class="detail_label">
									最后装潢时间：
								</td>
								<td width="35%" class="detail_content">
									${rst.LAST_DECOR_TIME}
								</td>
							    <td width="15%" align="right" class="detail_label">
									制单人：
								</td>
								<td width="35%" class="detail_content">
									 ${rst.CRE_NAME}
								</td>
						  </tr>
						  <tr>
						    <td width="15%" align="right" class="detail_label">
									制单时间：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CRE_TIME}
							</td>
							<td width="15%" align="right" class="detail_label">
								详细地址：
							</td>
							<td width="35%" class="detail_content">
								${rst.ADDRESS}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								${rst.REMARK}
							</td>
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
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		</table>
	</body>
</html>

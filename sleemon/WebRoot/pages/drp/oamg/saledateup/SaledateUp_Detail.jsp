<!--
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
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
	<title>信息详情</title>
</head>
<body style="overflow: auto;" class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" scrolling="AUTO">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">销售数据上报编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.SALE_DATE_UP_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                      ${rst.STATE} &nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">渠道编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">渠道名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NAME}&nbsp;
					</td>
               </tr>
                <tr>
			        <td width="15%" align="right" class="detail_label">终端编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">终端名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_NAME}&nbsp;
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">战区编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.WAREA_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">战区名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.WAREA_NAME}&nbsp;
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">区域编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">区域名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">年份:</td>
				   <td width="35%" class="detail_content" >
                        ${rst.YEAR}&nbsp;
				  </td>
				  <td width="15%" align="right" class="detail_label">月份:</td>
				   <td width="35%" class="detail_content" >
                        ${rst.MONTH}&nbsp;
				  </td>
               </tr>
               
               <tr>
<!--			        <td width="15%" align="right" class="detail_label">总数量:</td>-->
<!--					<td width="35%" class="detail_content">-->
<!--                        ${rst.TOTAL_NUM}&nbsp;-->
<!--					</td>-->
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
			        <td width="15%" align="right" class="detail_label">总金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.TOTAL_AMOUNT}&nbsp;
					</td>
               </tr>
               
               <tr>
                  <td width="15%" align="right" class="detail_label">制单人:</td>
				  <td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
				  </td>
                  <td width="15%" align="right" class="detail_label">制单时间:</td>
				  <td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
				  </td>
                   
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">更新人:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">审核人:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">审核时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
					</td>
               </tr>
                <tr>
			        <td width="15%" align="right" class="detail_label">制单部门:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单机构:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
               </tr>
               <!--   
               <tr>
			        <td width="15%" align="right" class="detail_label">帐套名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
			       
               </tr>-->
                <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
					</td>
               </tr>
               <tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="3" class="detail2">
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
								<td nowrap align="center" width="10%">${rr}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATORNAME}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATETIME}&nbsp;</td>
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
</html>
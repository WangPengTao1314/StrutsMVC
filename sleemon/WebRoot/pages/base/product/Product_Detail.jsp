<!--  
/**
 * @module 系统管理
 * @func 货品信息详细信息页面
 * @version 1.1
 * @author 刘曰刚
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
	<title>货品详情</title>
</head>
<body class="">	
<div class="lst_area">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">货品编号：</td>
					<td width="35%" class="detail_content">${rst.PRD_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">货品名称：</td>
					<td width="35%" class="detail_content">${rst.PRD_NAME}&nbsp;</td>
				</tr>
				<tr   >
					<td width="15%" align="right" class="detail_label">规格型号：</td>
					<td width="35%" class="detail_content" >${rst.PRD_SPEC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">花号：</td>
					<td width="35%" class="detail_content">${rst.PRD_COLOR }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">品牌：</td>
					<td width="35%" class="detail_content">${rst.BRAND }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">标准单位：</td>
					<td width="35%" class="detail_content" >${rst.STD_UNIT }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">默认计量单位：</td>
					<td width="35%" class="detail_content">${rst.MEAS_UNIT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">换算关系：</td>
					<td width="35%" class="detail_content">${rst.RATIO }&nbsp;</td>
					
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">货品分类编号：</td>
					<td width="35%" class="detail_content">${rst.PAR_PRD_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">货品分类名称：</td>
					<td width="35%" class="detail_content">${rst.PAR_PRD_NAME }&nbsp;</td>
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">材质：</td>
					<td width="35%" class="detail_content">${rst.PRD_MATL }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">包装后体积：</td>
					<td width="35%" class="detail_content">${rst.VOLUME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">包装后长度：</td>
					<td width="35%" class="detail_content">${rst.LENGTH}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">包装后宽度：</td>
					<td width="35%" class="detail_content">${rst.WIDTH }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">包装后高度：</td>
					<td width="35%" class="detail_content">${rst.HEIGHT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">是否赠品标记</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.IS_CAN_FREE_FLAG==1}">是</c:if>
						<c:if test="${rst.IS_CAN_FREE_FLAG!=1}">否</c:if>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">零售价：</td>
					<td width="35%" class="detail_content">${rst.FACT_PRICE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">供货价格：</td>
					<td width="35%" class="detail_content">${rst.PRVD_PRICE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">最低零售价格：</td>
					<td width="35%" class="detail_content">${rst.RET_PRICE_MIN }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">开始生产日期：</td>
					<td width="35%" class="detail_content">${rst.BEG_DATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">财务对照码：</td>
					<td width="35%" class="detail_content">${rst.U_CMP_CODE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">货品套标记：</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.PRD_SUIT_FLAG==0}">否</c:if>
						<c:if test="${rst.PRD_SUIT_FLAG!=0}">是</c:if>
						&nbsp;
					</td>
				</tr>
				<tr>
				   	<td width="15%" align="right" class="detail_label">是否扫码：</td>
				    <td width="35%" class="detail_content">
						<c:if test="${1 eq rst.IS_SCAN}">不扫码</c:if>
						<c:if test="${empty rst.IS_SCAN or 0 eq rst.IS_SCAN}">扫码</c:if>
					</td>
					<td width="15%" align="right" class="detail_label"> 是否抵库：</td>
					<td width="35%" class="detail_content">
					    <c:if test="${1 eq rst.AUTO_BACKUP_FLG}">抵库</c:if>
						<c:if test="${0 eq rst.AUTO_BACKUP_FLG}">不抵库</c:if>
					</td>
				</tr>
				
				<tr>
				   	<td width="15%" align="right" class="detail_label">是否上报：</td>
				    <td width="35%" class="detail_content">
				    	<c:if test="${rst.IS_REPORT_FLAG==0}">否</c:if>
						<c:if test="${rst.IS_REPORT_FLAG!=0}">是</c:if>
						&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">是否分销：</td>
					<td width="35%" class="detail_content">
					   <c:if test="${rst.IS_DISTRIBUT_FLAG==0}">否</c:if>
						<c:if test="${rst.IS_DISTRIBUT_FLAG!=0}">是</c:if>
						&nbsp;
					</td>
				</tr>
				
				<tr>	
				<td width="15%" align="right" class="detail_label">图片路径：</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.PIC_PATH==null}">
							<img src="${fileName}dafult.jpg" width="50px" height="50px" />
				        </c:if>
				        <c:if test="${rst.PIC_PATH!=null}">
							<img src="${PIC_PATH}" height='100px' width='150px' />
						</c:if>
						<input type="hidden" id ="PIC_PATH" value='${rst.PIC_PATH}' />
					</td>
				<td width="15%" align="right" class="detail_label" >详细信息路径：</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.DTL_PATH==null}">
							<img src="${fileName}dafult.jpg" width="50px" height="50px" />
				        </c:if>
				        <c:if test="${rst.DTL_PATH!=null}">
							<img src="${DTL_PATH}" height='100px' width='150px' />
						</c:if>
						<input type="hidden" id ="DTL_PATH" value='${rst.DTL_PATH}' />
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
				</tr>
				<tr>
					
					<td width="15%" align="right"class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</div>
</body>
<%@ include file="/pages/common/uploadfile/picUpdfile.jsp"%>
<script type=text/javascript >
</script>
</html>

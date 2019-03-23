<%--
/**
 * @author zhuxw
 * @function 
 * @version 2011年11月16日 11时23分14秒
 */
--%>
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
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4"  >
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			    <tr>
					<td width="15%" align="right" class="detail_label">公告标题：</td>
					<td width="35%" class="detail_content" colspan="4">
                        ${rst.NOTICE_TITLE}&nbsp;
					</td>
					
				</tr>
				<tr>
				   <td width="15%" align="right" class="detail_label">公告类型：</td>
					<td width="35%" class="detail_content" colspan="4">
                        ${rst.NOTICE_TYPE}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">公告对象：</td>
					<td width="35%" class="detail_content" >
                        ${rst.NOTICE_OBJ}&nbsp;
					</td>
				   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content" ></td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">开始时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.STATIME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">结束时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.ENDTIME}&nbsp;
					</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">创建人：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRENAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">创建时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRETIME}&nbsp;
					</td>
				</tr>
				<tr>
				   <td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPDNAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPDTIME}&nbsp;
					</td>
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">发布人：</td>
					<td width="35%" class="detail_content">
                        ${rst.ISSUER_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">发布时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRETIME}&nbsp;
					</td>
				</tr>
				
				<tr>
				   <td width="15%" align="right" class="detail_label">审核人：</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">审核时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
					</td>
				</tr>
				
				<tr>
					<td width="15%" align="right"  class="detail_label">公告内容：</td>
					<td width="35%" class="detail_content" colspan="4">${rst.NOTICE}</td>
				</tr>
				<tr>
					<td width="15%" align="right"  class="detail_label">公告附件：</td>
					<td width="35%" class="detail_content" colspan="4">
					  <input type="hidden"  json="ATT_PATH" name="ATT_PATH" id="ATT_PATH" label="公告附件" value="${rst.ATT_PATH}" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
 	displayDownFile('ATT_PATH','SAMPLE_DIR',false,false);
</script>

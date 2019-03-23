﻿<!--
 * @module 渠道管理-开业礼包管理
 * @func   开业大礼包申请单维护
 * @version 1.1
 * @author ghx
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
<body class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						开业大礼包申请单号：
					</td>
					<td width="35%" class="detail_content">
						${rst.OPEN_BUSS_PKG_NO} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						申请日期：
					</td>
					<td width="35%" class="detail_content">
						${rst.REQ_DATE} &nbsp;
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						终端编号：
					</td>
					<td width="35%" class="detail_content">
						${rst.TERM_NO} &nbsp;												
					</td>
					<td width="15%" align="right" class="detail_label">
						终端名称：
					</td>
					<td width="35%" class="detail_content">
						${rst.TERM_NAME} &nbsp;	
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						终端类型：
					</td>
					<td width="35%" class="detail_content">
						${rst.TERM_TYPE} &nbsp;													
					</td>
					<td width="15%" align="right" class="detail_label">
						开店日期：
					</td>
					<td width="35%" class="detail_content">
						${rst.BEG_SBUSS_DATE} &nbsp;
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						   渠道编号：
					</td>
					<td width="35%" class="detail_content">
						${rst.CHANN_NO} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						渠道名称：
					</td>
					<td width="35%" class="detail_content">
						${rst.CHANN_NAME} &nbsp;
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						  渠道联系人：
					</td>
					<td width="35%" class="detail_content">
						${rst.CHANN_PERSON_CON} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						渠道联系电话：
					</td>
					<td width="35%" class="detail_content">
						${rst.CHANN_TEL} &nbsp;
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						  所属战区编号：
					</td>
					<td width="35%" class="detail_content">
						${rst.AREA_NO} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						所属战区名称：
					</td>
					<td width="35%" class="detail_content">
						${rst.AREA_NAME} &nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						  区域经理：
					</td>
					<td width="35%" class="detail_content">
						${rst.AREA_MANAGE_NAME} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						区域经理电话：
					</td>
					<td width="35%" class="detail_content">
						${rst.AREA_MANAGE_TEL} &nbsp;
					</td>
				</tr>
				
				<tr>												
					<td width="15%" align="right" class="detail_label">
						卖场名称：
					</td>
					<td width="35%" class="detail_content">
						${rst.SALE_STORE_NAME} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label" nowrap>
						  卖场位置类别：
					</td>
					<td width="35%" class="detail_content">												
						${rst.LOCAL_TYPE} &nbsp;
					</td>
				</tr>
				<tr>												
					<td width="15%" align="right" class="detail_label">
						行政区域：
					</td>
					<td width="35%" class="detail_content">
						${rst.ZONE_ADDR} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label" nowrap>
						  经营品牌：
					</td>
					<td width="35%" class="detail_content">
						${rst.BUSS_SCOPE} &nbsp;
					</td>
				</tr>
				<tr>												
					<td width="15%" align="right" class="detail_label">
						面积（平米）：
					</td>
					<td width="35%" class="detail_content">
						${rst.BUSS_AREA} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						申请人：
					</td>
					<td width="35%" class="detail_content">
						${rst.REQ_NAME} &nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						 门店装修报销单：
					</td>
					<td width="35%" class="detail_content">
						${rst.RNVTN_REIT_REQ_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						报销总金额：
					</td>
					<td width="35%" class="detail_content">
						${rst.TOTAL_REIT_AMOUNT} &nbsp;
				</tr>											
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						 状态：
					</td>
					<td width="35%" class="detail_content">
						${rst.STATE} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">
						相关附件：
					</td>
					<td width="35%" class="detail_content">
						<input type="hidden" id="ATT_PATH" name="ATT_PATH" value="${rst.ATT_PATH}"/>
						 &nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						 详细地址：
					</td>
					<td width="35%" class="detail_content" colspan="3">
						${rst.ADDRESS} &nbsp;
					</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" nowrap>
						 详细内容：
					</td>
					<td width="35%" class="detail_content">
						${rst.REQ_RSON} &nbsp;
					</td>
					<td width="15%" align="right" class="detail_label" nowrap>
						 备注：
					</td>
					<td width="35%" class="detail_content" colspan="3">
						${rst.REMARK} &nbsp;
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
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	    var path = $("#ATT_PATH").val();
	    var leg = path.split(",").length ;
	    var folder = "";
	    if(leg>1){
	    	for(var i=0;i<leg;i++){
		    	folder = folder+"SAMPLE_DIR,";
		    }
	    	folder = folder.substring(0,folder.length-1);
	    }else{
	    	folder = "SAMPLE_DIR";
	    }
	    
        //参考pages\common\sample\samplePlus.jsp
        displayDownFile('ATT_PATH',folder,false,false);
       
       
       
   </script>
</body>
</html>
<!-- 
 *@module 营销管理-广告投放管理
 *@func   广告投放报销申请单维护
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
		<title>广告投放报销申请单详情</title>
	</head>
	<body class="bodycss1">

		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">
								广告投放报销申请单号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.ERP_ADV_REIT_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								关联广告投放申请单号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.ERP_ADV_REQ_NO}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
                                 ${rst.CHANN_NO} 							 
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NAME}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.AREA_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_NAME}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道排名：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_RANK}
							</td>
							<td width="15%" align="right" class="detail_label">
								广告公司名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.ADV_CO_NAME}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								广告公司联系人：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.ADV_CO_CONTACT}
							</td>
							<td width="15%" align="right" class="detail_label">
								广告公司联系电话：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.ADV_CO_TEL}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								广告投放内容：
							</td>
							<td width="35%" class="detail_content">
								${rst.ADV_CONTENT}
							</td>
							<td width="15%" align="right" class="detail_label">
								广告投放地点：
							</td>
							<td width="35%" class="detail_content">
								${rst.ADV_ADDR}
							</td>
						</tr>


						<tr>
							<td width="15%" align="right" class="detail_label">
								投放申请单附件：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" name="REQPATH" id="REQPATH" json="REQPATH" value="${rstT.REQPATH}"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								申请报销附件：
							</td>
							<td width="35%" class="detail_content">
								<input type="hidden" name="REQREITPATH" id="REQREITPATH" json="REQREITPATH" value="${rst.REQREITPATH}"/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								申请报销原因：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.REIT_REASON}
							</td>
							<td width="15%" align="right" class="detail_label">
								申请报销金额：
							</td>
							<td width="35%" class="detail_content">
								${rst.REIT_AMOUNT}
							</td>
						</tr>


						<tr>
							<td width="15%" align="right" class="detail_label">
								该广告申请共需总部报销金额：
							</td>
							<td width="35%" class="detail_content">
								${rst.ADV_TOTAL_AMOUNT}
							</td>
							<td width="15%" align="right" class="detail_label">
								该广告申请单已报销金额：
							</td>
							<td width="35%" class="detail_content">
								${rst.HAS_REIM_AMOUNT}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="35%" class="detail_content">
							   ${rst.RNVTN_REQ_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								申请时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.RNVTN_REQ_DATE}
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
	<script type="text/javascript">
       SelDictShow("LOCAL_TYPE","BS_86","${params.LOCAL_TYPE}","");
       SelDictShow("LEDGER_ID1 ","BS_87","${params.LEDGER_ID1}","");
       SelDictShow("BUSS_SCOPE ","BS_88","${params.BUSS_SCOPE}","");
       SelDictShow("TERM_WHICH_NUM ","BS_89","${params.TERM_WHICH_NUM}","");
       var path = $("#REQREITPATH").val();
       var strs= new Array();   //定义一数组
       strs=path.split(",");    //字符分割      
       var str="";
       if(strs.length>1){
	       for(var i=0;i<strs.length;i++){
	          str+="SAMPLE_DIR,";
	       }
	       var str = str.substring(0, str.lastIndexOf(','));
	    } else {
	          str="SAMPLE_DIR";
	    }
       displayDownFile('REQREITPATH',str,false,false);
       
       var pathT = $("#REQPATH").val();
       var strsT= new Array();   //定义一数组
       strsT=pathT.split(",");    //字符分割      
       var strT="";
       if(strsT.length>1){
	       for(var i=0;i<strsT.length;i++){
	          strT+="SAMPLE_DIR,";
	       }
	       var strT = strT.substring(0, strT.lastIndexOf(','));
	    } else {
	          strT="SAMPLE_DIR";
	    }
       displayDownFile('REQPATH',strT,false,false);
   </script>
	</body>
</html>

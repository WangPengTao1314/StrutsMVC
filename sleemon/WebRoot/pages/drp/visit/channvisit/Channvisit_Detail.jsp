<!-- 
 *@module 渠道管理-拜访管理
 *@func   加盟商拜访表维护
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
		<title>加盟商拜访表详情</title>
	</head>
	<body class="bodycss1">

		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3">
						 <tr>
							<td width="15%" align="right" class="detail_label">
								流程编号：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.CHANN_VISIT_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								紧急程度：
							</td>
							<td width="20%" class="detail_content">
							     ${rst.EME_DEGREE}
							</td>
							<td width="15%"  class="detail_label">
								拜访人：
							</td>
							<td width="20%" class="detail_content">
								${rst.VISIT_PEOPLE}
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.APPLY_DEP}
							</td>
							<td width="15%"   class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.APPLY_DATE}
							</td>
							<td width="15%"   class="detail_label">
								拜访时间：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.VISIT_DATE}
							</td>
						</tr> 
						<tr>
							<td width="15%"   class="detail_label">
								拜访形式：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.VISIT_TYPE}
							</td>
							<td width="15%"   class="detail_label">
								客户名称：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							     ${rst.CHANN_NAME}
							</td>
						</tr> 
						<tr>
						</tr> 
						<tr style="padding-left:50px;">
						    <th  align="center">
						       库存信息  
						    </th>
						</tr>
						<tr>
							<td width="15%"  class="detail_label">
								床垫：
							</td>
							<td width="20%" class="detail_content">
								${rst.MATTRESS_STOCK}
							</td>
							<td width="15%"   class="detail_label">
								软床：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.BED_STOCK}
							</td>
							<td width="15%"   class="detail_label">
								床头柜：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.BEDSIDE_STOCK}
							</td>
						</tr> 
						<tr>
							<td width="15%"   class="detail_label">
								床品：
							</td>
							<td width="20%" class="detail_content">
								${rst.BEDDING_STOCK}
							</td>
							<td width="15%"  class="detail_label">
								其他：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.OTHER_STOCK}
							</td>
							<td width="15%"   class="detail_label">
								合计：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.TOTAL_STOCK}
							</td>
						</tr>
						<tr style="padding-left:50px;">
						    <th align="center">
						       销售达成  
						    </th>
						</tr>
					    <tr>
							<td width="15%"  class="detail_label">
								本月销售目标：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.MONTH_ORDER_NUM}
							</td>
							<td width="15%"  class="detail_label">
								本季销售目标：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.SEASON_ORDER_NUM}
							</td>
							<td width="15%"  class="detail_label" rowspan="3">
								销售改善计划：
							</td>
							<td width="20%" class="detail_content" rowspan="3">
							    ${rst.SALES_IMP_PLAN}
							</td>
				         </tr>
				         <tr>
							<td width="15%" class="detail_label">
								本月实际销售：
							</td>
							<td width="20%" class="detail_content">
								${rst.MONTH_ORDER_REALITY_RATE}
							</td>
							<td width="15%" class="detail_label">
								 本季实际销售：
							</td>
							<td width="20%" class="detail_content">
								${rst.SEASON_ORDER_REALITY_RATE}
							</td>
						   </tr>
						   <tr>
							<td width="15%"  class="detail_label">
								 月销售达成率：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.MONTH_ORDER_RATE}
							</td>
						    <td width="15%" class="detail_label">
								 季销售达成率：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.SEASON_ORDER_RATE}
							</td>
						   </tr>
						<tr style="padding-left:50px;">
						    <th  align="center">
						       季度工作重点
						    </th>
						</tr>
						<tr>
							<td width="15%"  class="detail_label">
								季度目标：
							</td>
							<td width="20%" class="detail_content">
								${rst.SEASON_GOALS}
							</td>
							<td width="15%"   class="detail_label">
								目前达成：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.CURRENT_REALITY_RATE}
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.CURRENT_RATE}
							</td>
						</tr>
						<tr>
						   <td width="15%"  class="detail_label">
								季度改善计划：
							</td>
						   <td width="20%"  class="detail_content">
								${rst.SEASON_IMPROVE_PLAN}	 
							</td>
							<td width="15%" class="detail_label">
								加盟商问题：
							</td>
							<td width="20%" class="detail_content" >
								${rst.CHANN_QUESTION}
							</td>
							<td width="15%" align="right" class="detail_label">
								主要行动：
							</td>
							<td width="20%" class="detail_content" >
								 ${rst.MAIN_ACTION}
							</td>
						</tr>
						 <tr>
							
							<td width="15%"   class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								${rst.COMPETITION_INFO}
							</td>
							<td width="15%"   class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								 ${rst.SUPPORT_DEMAND}
							</td>
                            <td width="15%"   class="detail_label">相关附件：</td>
							<td width="20%" class="detail_content">
			                     <input id="ATT_PATH" json="ATT_PATH" name="ATT_PATH"  type="hidden"   value="${rst.ATT_PATH}"/> 
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
			<!--   
			<tr>
	                   <td colspan="4" class="detail2">
	                     <table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
							<tr  >
								<th nowrap align="center">序号</th>
								<th nowrap align="center" >操作</th>
								<th nowrap align="center" >时间</th>
								<th nowrap align="center" >意见</th>
								<th nowrap align="center" >下一操作者</th>
							</tr>
							<c:forEach items="${flowInfoList}" var="flow" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
								<td nowrap align="center">${rr}&nbsp;</td>
								<td nowrap align="center">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center">${flow.OPERATETIME}&nbsp;</td>
								<td nowrap align="center">${flow.REMARK}&nbsp;</td>
								<td nowrap align="center">${flow.NEXTOPERATORNAME}&nbsp;</td>
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
	                 -->
		</table>
	<script type="text/javascript">
       SelDictShow("LOCAL_TYPE","BS_86","${params.LOCAL_TYPE}","");
       SelDictShow("LEDGER_ID1 ","BS_87","${params.LEDGER_ID1}","");
       SelDictShow("BUSS_SCOPE ","BS_88","${params.BUSS_SCOPE}","");
       SelDictShow("TERM_WHICH_NUM ","BS_89","${params.TERM_WHICH_NUM}","");
       var path = $("#ATT_PATH").val();
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
       displayDownFile('ATT_PATH',str,false,false);
   </script>
	</body>
</html>

<!-- 
 *@module 渠道管理-拜访管理
 *@func   门店拜访表维护
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
		<title>门店拜访表详情</title>
	</head>
	<body class="bodycss1">

		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3">
						 	 <tr>
							<td width="15%" class="detail_label">
								流程编号：
							</td>
							<td width="20%" class="detail_content">
							  ${rst.STORE_VISIT_NO}
							</td>
							<td width="15%" class="detail_label">
								紧急程度：
							</td>
							<td width="20%" class="detail_content">
							  ${rst.EME_DEGREE}
							</td>
							<td width="15%" class="detail_label">
								拜访人：
							</td>
							<td width="20%" class="detail_content">
								${rst.APPLY_PERSON}
							</td>
							</tr>
						    <tr>
							<td width="15%" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.APPLY_DEP}
							</td>
							<td width="15%" class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.APPLY_DATE}
							</td>
							<td width="15%" class="detail_label">
								拜访时间：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.VISIT_DATE}
							</td>
							</tr> 
						    <tr>
							<td width="15%" align="right" class="detail_label">
								拜访方式：
							</td>
							<td width="20%" class="detail_content">
							     ${rst.VISIT_TYPE}
							</td>
							<td width="15%" class="detail_label">
								终端名称：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.STORE_NAME}
							</td>
							<td width="15%" class="detail_label">
								所属加盟商：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.CHANN_NAME}
							</td>
							</tr> 
						    <tr>
							<td width="15%"  class="detail_label">
								城市：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    ${rst.CITY}
							</td>
						</tr> 
						<tr  style="padding-left:50px;">
						    <th align="center">
						        门店拜访  
						    </th>
						</tr>
						<tr>
							<td width="15%"  class="detail_label">
								店招：
							</td>
							<td width="20%" class="detail_content">
								${rst.STORE_STROKES}
							</td>
							<td width="15%" class="detail_label">
								店内灯箱：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.LIGHT_BOX}
							</td>
							<td width="15%" class="detail_label">
								地面：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.GROUND}
							</td>
						</tr> 
						<tr>
							<td width="15%" class="detail_label">
								产品：
							</td>
							<td width="20%" class="detail_content">
								${rst.PRODUCTS}
							</td>
							<td width="15%" class="detail_label">
								道具：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.PROPERTIES}
							</td>
							<td width="15%" class="detail_label">
								软饰：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.SOFT_DECORATION}
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								电视：
							</td>
							<td width="20%" class="detail_content">
								${rst.TELEVISION}
							</td>
							<td width="15%" class="detail_label">
								灯光：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.LIGHT_LAMP}
							</td>
							<td width="15%" class="detail_label">
								物料：
							</td>
							<td width="20%" class="detail_content">
							   ${rst.MATERIALS}
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								导购形象：
							</td>
							<td width="20%" class="detail_content">
								${rst.FIGURE}
							</td>
							<td width="15%" class="detail_label">
								导购态度：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.MANNER}
							</td>
							<td width="15%" class="detail_label">
								导购技能：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.TECHNICAL}
							</td>
						</tr>
						 <tr>
							<td width="15%" class="detail_label">
								饮水机：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.WATER_MACHINE}
							</td>
							<td width="15%"  class="detail_label">
								块毯：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							    ${rst.BLANKETS}
							</td>
						</tr> 
						
						<tr style="padding-left:50px;">
						    <th align="center">
						       爆破活动总结
						    </th>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								执行活动主题：
							</td>
							<td width="20%" class="detail_content">
								${rst.EXECUTE_ACTION_TOPIC}
							</td>
							<td width="15%" class="detail_label">
								执行活动时间：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.EXECUTE_ACTION_DATE}
							</td>
							<td width="15%" class="detail_label">
								执行活动地点：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.EXECUTE_ACTION_ADDR}
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								活动目标：
							</td>
							<td width="20%" class="detail_content">
								${rst.ACTION_PLAN}
							</td>
							<td width="15%" class="detail_label">
								实际达成：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.ACTION_REALITY_RATE}
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.ACTION_RATE}
							</td>
						</tr>
						<tr>
						   <td width="15%" class="detail_label">
						                       实际投入
						   </td>
						   <td width="20%" class="detail_content" colspan="5">
						     ${rst.ACTUAL_INVESTMENT}
						   </td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								活动总结：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <table>
							        <tr>
							          <td>
							              亮点 
									     ${rst.ACTION_RIGHT}
									  </td>
									  <td> 
							              不足 
									        ${rst.ACTION_BAD}
							           </td>
							        </tr>
							    </table>
							</td>
						</tr> 
						
						<tr style="padding-left:50px;">
						    <th align="center">
						       零售达成
						    </th>
						</tr>
						
						<tr>
							<td width="15%" class="detail_label">
								本月计划：
							</td>
							<td width="20%" class="detail_content">
								${rst.MONTH_ORDER_NUM}
							</td>
							<td width="15%" class="detail_label">
								实际达成：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.MONTH_REALITY_RATE}
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.MONTH_RATE}
							</td>
						</tr>
						
						<tr>
							<td width="15%" class="detail_label">
								本季目标：
							</td>
							<td width="20%" class="detail_content">
								${rst.SEASON_ORDER_NUM}
							</td>
							<td width="15%"  class="detail_label">
								实际达成：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.SEASON_REALITY_RATE}
							</td>
							<td width="15%" class="detail_label">
								达成率：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.SEASON_RATE}
							</td>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								月度评效：
							</td>
							<td width="20%" class="detail_content">
								${rst.EVALUATION_MONTH}
							</td>
							<td width="15%" class="detail_label">
								本季评效：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							    ${rst.EVALUATION_SEASON}
							</td>
						</tr>
						
						<tr style="padding-left:50px;">
						    <th align="center">
						         下一步计划
						    </th>
						</tr>
						<tr>
							<td width="15%" class="detail_label">
								活动计划主题：
							</td>
							<td width="20%" class="detail_content">
								${rst.ACTION_PLAN_TOPIC}
							</td>
							<td width="15%" class="detail_label">
								活动计划时间：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.ACTION_PLAN_DATE}
							</td>
							<td width="15%" class="detail_label">
								活动计划地点：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.ACTION_PLAN_ADDR}
							</td>
						</tr>
						<tr>
						  <td width="15%" class="detail_label">
								预计投入：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    ${rst.FORECAST_INVESTMENT}
							</td>
						</tr>
						 <tr>
							<td width="15%" class="detail_label">
								预计达成目标：
							</td>
							<td width="20%" class="detail_content" >
								${rst.EXPECTED_GOAL}
							</td>
							<td width="15%" class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								 ${rst.COMPETITION_INFO}
							</td>
							<td width="15%" class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								${rst.SUPPORT_DEMAND}
							</td>
						</tr> 
						<tr>
							<td width="15%" class="detail_label">相关附件：</td>
							<td width="20%" class="detail_content" colspan="5">
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

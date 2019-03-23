<!--
 * @module 渠道培训管理
 * @func 培训课程维护
 * @version 1.1
 * @time   2014-07-04 
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
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/oamg/traincourse/Traincourse_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>			
			<c:if test="${module eq 'wh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;渠道培训管理&gt;&gt;培训课程维护</td>
			</c:if>
			<c:if test="${empty module || module eq 'sh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;渠道培训管理&gt;&gt;培训课程审核</td>
			</c:if>			
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
				    <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <!--<c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   -->
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="TRAIN_COURSE_NO" >培训课程编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_COURSE_NAME" >培训课程名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_TYPE" >培训类型</th>
                    <th  nowrap="nowrap" align="center" dbname="FIXED_PSON_NUM" >额定人数</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_TIME_BEG" >培训时间从</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_TIME_END" >培训时间到</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_ADDR" >培训地点</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_GOAL" >培训目的</th>
                    <th  nowrap="nowrap" align="center" dbname="TRAIN_CONTENT" >培训内容</th>
                    <th  nowrap="nowrap" align="center" dbname="ATT_PATH" >相关附件</th>                    
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.TRAIN_COURSE_ID}"/>
					 </td>
					 <td nowrap="nowrap" align="center">${rst.TRAIN_COURSE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_COURSE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FIXED_PSON_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_TIME_BEG}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_TIME_END}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_GOAL}</td>
                     <td nowrap="nowrap" align="center">${rst.TRAIN_CONTENT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                     	<input type="hidden" id="ATT_PATH${rr}" name="ATT_PATH" value="${rst.ATT_PATH}"/>
                     	&nbsp;
					 </td>                     
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.TRAIN_COURSE_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
			<tr>
				<td>
					<form id="pageForm" action="#" method="post" name="listForm">
					<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
						<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
						<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
						<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
						<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
						<span id="hidinpDiv" name="hidinpDiv"></span>
						${paramCover.unCoveredForbidInputs }
					</form>
				</td>
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">			
			<input type="hidden" name=selectTERM value="STATE in ('启用') and DEL_FLAG=0 and area_id in (select distinct area_id from BASE_AREA_CHRG_FLAT where chrg_pson_id='${params.XTYH_ID}')">
			<input type="hidden" name="selectParamsChann" value="STATE in( '启用') and DEL_FLAG='0' and area_id in (select distinct area_id from BASE_AREA_CHRG_FLAT where chrg_pson_id='${params.XTYH_ID}')">
			<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0'">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">培训课程编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TRAIN_COURSE_NO" name="TRAIN_COURSE_NO"  style="width:155" value="${params.TRAIN_COURSE_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">培训课程名称:</td>
					<td width="15%" class="detail_content">
					    <input id="TRAIN_COURSE_NAME" name="TRAIN_COURSE_NAME"  style="width:155" value="${params.TRAIN_COURSE_NAME}"/>  
					</td>                    		
               </tr>
               <tr>
              	    <td width="8%" nowrap align="right" class="detail_label">培训类型:</td>
					<td width="15%" class="detail_content">
						<select id="TRAIN_TYPE" name="TRAIN_TYPE" style="width: 155"></select>
	   				</td>
                    <td width="8%" nowrap align="right" class="detail_label">培训地点:</td>
					<td width="15%" class="detail_content">
	   					<input id="TRAIN_ADDR" name="TRAIN_ADDR"  style="width:155" value="${params.TRAIN_ADDR}"/>
					</td>
               </tr>              
               <tr>                    
					<td width="8%" nowrap align="right" class="detail_label">培训目的:</td>
					<td width="15%" class="detail_content">
	   					<input id="TRAIN_GOAL" name="TRAIN_GOAL"  style="width:155" value="${params.TRAIN_GOAL}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
	   				</td>
               </tr>
               
               <tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");    
    SelDictShow("STATE","System_33","${params.STATE}",""); 
    SelDictShow("TRAIN_TYPE", "BS_103", '${params.TRAIN_TYPE}', "");
    
    var path = $("#ATT_PATH").val();
    $("input[name='ATT_PATH']").each(function(){    	
	    var path = $(this).val();
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
	    
        displayDownFile($(this).attr("id"),folder,false,false);
	});
    
      
</script>
</html>
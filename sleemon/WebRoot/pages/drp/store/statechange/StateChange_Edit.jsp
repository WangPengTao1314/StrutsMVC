<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:形态转换
 * @author zzb
 * @time   2013-09-05 14:00:34 
 * @version 1.1
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/store/statechange/StateChange_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理>>形态转换>>形态转换维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" name="tellOutparams" id="tellOutparams" value=""/>
       <input type="hidden" name="tellInparams" id="tellInparams" value="" />
       <input type="hidden" name="TERM_ID" id="TERM_ID" value="${TERM_ID}" />
       <input type="hidden" name="ZTXXID" id="ZTXXID" value="${ZTXXID}" />
       <input type="hidden" name="TERM_CHARGE" id="TERM_CHARGE" value="${TERM_CHARGE}" />
        
       <input type="hidden" id="TERM_ID" name="TERM_ID" value="${TERM_ID}"/>
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                    
                <tr>
                   <td width="15%" align="right" class="detail_label">形态转换单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="STATE_CHANGE_NO" name="STATE_CHANGE_NO" autocheck="true" label="形态转换单编号"  
                      type="text"   inputtype="string"   readonly    mustinput="true"   
                         <c:if test="${empty rst.STATE_CHANGE_NO}"> value="自动生成"</c:if>
						 <c:if test="${not empty rst.STATE_CHANGE_NO}">value="${rst.STATE_CHANGE_NO}"</c:if> /> 
				   </td>
                   <td width="15%" align="right" class="detail_label"> </td>
				   <td width="35%" class="detail_content">
				   </td>
               </tr>
                
               <tr>
				   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4"><%--
                     <textarea json="REMARK" name="REMARK" autocheck="true" label="备注"   inputtype="string"    rows="6" cols="80%"  maxlength="250" ><c:out value="${rst.remark}"></c:out> </textarea>
                     --%><textarea id="REMARK" name="REMARK" json="REMARK"  inputtype="string"  oVal="" onkeyup="changeTextAreaLength(this);"
				    			   autocheck="true" maxlength="250" rows="6" cols="80%" ><c:out value="${rst.REMARK}"></c:out></textarea>
				   </td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
</script>

    
</html>

 
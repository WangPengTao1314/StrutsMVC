<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Trainresult_Edit_Chld
 * @author ghx
 * @time   2014-07-10 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/oamg/trainresult/Trainresult_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>明细信息编辑</title>
</head>
<body class="bodycss1">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap>
			   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
			   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
			   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
		</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form>	    
	    <input type="hidden" name="selectEmployee" value=" 1=1   and  JGXXID in  (select JGXXID from T_SYS_JGXX where ZTXXID = '${ZTXXID}' ) ">
		<input type="hidden" id="TRAIN_REQ_ID" name="TRAIN_REQ_ID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">培训人员编号</th>
              <th nowrap align="center">培训人员姓名</th>
              <th nowrap align="center">所在部门/渠道/终端</th>
              <th nowrap align="center">岗位</th>
              <th nowrap align="center">培训签到情况</th>
              <th nowrap align="center">培训过程表现情况</th>
              <th nowrap align="center">培训考核情况</th>
              <th nowrap align="center">该次培训总体情况</th>
              <th nowrap align="center">备注</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.TRAIN_RESULT_DTL_ID}',
	           '${rst.TRAIN_PER_NO}',
	           '${rst.TRAIN_PER_NAME}',
	           '${rst.THE_ORGAN}',
	           '${rst.THE_POST}',
	           '${rst.SIGN_STATE}',
	           '${rst.TRAIN_PER}',
	           '${rst.TRAIN_ASSES}',
	           '${rst.TRAIN_OVERALL}',
	           '${rst.REMARK}',
            ];
	addRow(arrs);
	</c:forEach>
	
</script>
</html>
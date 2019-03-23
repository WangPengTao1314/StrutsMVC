<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:形态转换单明细
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/store/statechange/StateChange_Edit_Chld.js"></script>
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
		<input type="hidden" name="TERM_ID" id="TERM_ID" value="${TERM_ID}" />
        <input type="hidden" name="ZTXXID" id="ZTXXID" value="${ZTXXID}" />
        <input type="hidden" name="TERM_CHARGE" id="TERM_CHARGE" value="${TERM_CHARGE}" />
		<input type="hidden" id="selParam" name="selParam" value=" ">
		<input type="hidden" name="selectStoreParams" id="selectStoreParams" value=""/>
		<input type="hidden" name="selectPrdParams" id="selectPrdParams" value=""/>
		<input type="hidden" name="selectChangePrdParams" id="selectChangePrdParams" value=""/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle;" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NO" >库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NAME" >库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_CHANGE_PRD_NO" >转换前货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_CHANGE_PRD_NAME" >转换前货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_DM_SPCL_TECH_NO" >转换前特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_CHANGE_SPCL_TECH_ID" >转换前订单特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_PRD_NO" >转换后货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_PRD_NAME" >转换后货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_DM_SPCL_TECH_NO" >转换后特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGED_SPCL_TECH_ID" >转换后订单特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANGE_NUM" >转换数量</th>
                    <th  nowrap="nowrap" align="center" dbname="" >库存数量</th>
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
	          '${rst.STATE_CHANGE_DTL_ID}',//0
              '${rst.STORE_ID}',//1
              '${rst.STORE_NO}',//2
              '${rst.STORE_NAME}',//3
              '${rst.PAR_CHANGE_PRD_ID}',//4
              '${rst.PAR_CHANGE_PRD_NO}',//5
              '${rst.PAR_CHANGE_PRD_NAME}',//6
              '${rst.PAR_CHANGE_SPCL_TECH_ID}',//7
              '${rst.CHANGED_PRD_ID}',//8
              '${rst.CHANGED_PRD_NO}',//9
              '${rst.CHANGED_PRD_NAME}',//10
              '${rst.CHANGED_SPCL_TECH_ID}',//11
              '${rst.CHANGE_NUM}',//12
              '${rst.PAR_SPCL_TECH_FLAG}',//13
              '${rst.CHANGED_SPCL_TECH_FLAG}',//14
              '${rst.SAFE_NUM}',//15
              '${rst.PAR_DM_SPCL_TECH_NO}',//16
              '${rst.CHANGED_DM_SPCL_TECH_NO}'//17
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
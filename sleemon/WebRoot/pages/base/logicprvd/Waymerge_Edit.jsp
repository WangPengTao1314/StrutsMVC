<!--  
/**
  *@module 系统管理
  *@fuc 物流供应商管理
  *@version 1.0
  *@author 王栋斌
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type="text/javascript"
		src="${ctx}/pages/base/logicprvd/Waymerge_Edit.js">
</script>
	<script language="JavaScript"
		src="${ctx}/pages/common/select/selCommJs.js">
</script>
	<title>合并路线维护</title>
</head>
<body class="bodycss1">
	<table width="99.8%" height="95%" border="0" cellSpacing=0
		cellPadding=0>
		<tr id="btntr">
			<td height="20px" valign="top">
				<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0>
					<tr>
						<td nowrap>
							<input id="add" type="button" class="btn" value="新增(I)"
								title="Alt+I" accesskey="I">
							<input id="delete" type="button" class="btn" value="删除(G)"
								title="Alt+G" accesskey="G">
							<input id="save" type="button" class="btn" value="保存(S)"
								title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="返回(B)" title="Alt+B"
								accesskey="B" onclick="gobacknew();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div class="lst_area">
					<form>
						<table id="jsontb" width="100%" border="0" cellpadding="1"
							cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th nowrap align="center">
									<input type="checkbox" style="height: 12px; valign: middle"
										id="allChecked">
								</th>
								<th nowrap align="center">
									合并路线编号
								</th>
								<th nowrap align="center">
									合并路线名称
								</th>
								<th nowrap align="center">
									发货点编号
								</th>
								<th nowrap align="center">
									发货点名称
								</th>
								<th nowrap align="center">
									发出城市
								</th>
								<th nowrap align="center">
									到达城市
								</th>
								<th nowrap align="center">
									全程
								</th>
								<th nowrap align="center">
									天数
								</th>
								<th nowrap align="center">
									合并路线
								</th>
							</tr>
						</table>
					</form>
				</div>
				<input type="hidden"  name="prvdId" id="prvdId" value="${result[0].PRVD_ID}">
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
<c:forEach items="${result}" var="rst" varStatus="row">
	var arrs = ['${rst.WAY_MERGE_ID}',
	            '${rst.PRVD_ID}',
				'${rst.WAY_MERGE_NO}',
				'${rst.WAY_MERGE_NAME}',
				'${rst.SHIP_POINT_ID}',
				'${rst.SHIP_POINT_NO}',
				'${rst.SHIP_POINT_NAME}',
				'${rst.DELV_CITY}',
				'${rst.ARRV_CITY}',
				'${rst.LENGTH}',
				'${rst.DAYS}'
				];
	addRow(arrs);
	</c:forEach>
</script>



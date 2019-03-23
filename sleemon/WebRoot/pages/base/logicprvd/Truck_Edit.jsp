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
		src="${ctx}/pages/base/logicprvd/Truck_Edit.js">
</script>
	<script language="JavaScript"
		src="${ctx}/pages/common/select/selCommJs.js">
</script>
	<title>车辆信息维护</title>
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
									<input type="checkbox" style="valign: middle"
										id="allChecked">
								</th>
								<th nowrap align="center">
									车型
								</th>
								<th nowrap align="center">
									最小容积
								</th>
								<th nowrap align="center">
									最大容积
								</th>
								<th nowrap align="center">
									长度
								</th>
								<th nowrap align="center">
									宽度
								</th>
								<th nowrap align="center">
									高度
								</th>
							</tr>
						</table>
						<input type="hidden" id="chistate" value="${chistate}" />
					</form>
				</div>
				<input type="hidden" name="prvdId" id="prvdId" value="${result[0].PRVD_ID}">
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
<c:forEach items="${result}" var="rst" varStatus="row">
	var arrs = ['${rst.TRUCK_ID}',
	            '${rst.PRVD_ID}',
				'${rst.TRUCK_TYPE}',
				'${rst.MIN_VOLUME}',
				'${rst.MAX_VOLUME}',
				'${rst.LENGTH}',
				'${rst.WIDTH}',
				'${rst.HEIGHT}'
				];
	addRow(arrs);
	</c:forEach>
</script>



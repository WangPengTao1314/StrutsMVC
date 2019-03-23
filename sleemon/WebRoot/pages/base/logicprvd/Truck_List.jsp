<!--  
/**
 * @module 系统管理
 * @func 物流供应商
 * @version 1.0
 * @author 王栋斌
 */
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>车辆信息</title>
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script type="text/javascript"
			src="${ctx}/pages/base/logicprvd/Truck_List.js">
</script>
		<script type=text/javascript
			src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js">
</script>
	</head>
	<body>
		<table width="99.8%" height="100%" border="0" cellspacing="0"
			cellpadding="0" class="panel">
			<tr>
				<td height="20">
					<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
							<tr>
								<td nowrap>
									<c:if test="${pvg.PVG_EDIT eq 1 }">
										<input id="edit" type="button" class="btn" value="编辑(E)"
											title="Alt+E" accesskey="E">
									</c:if>
									<c:if test="${pvg.PVG_DELETE eq 1 }">
										<input id="delete" type="button" class="btn" value="删除(G)"
											title="Alt+G" accesskey="G">
									</c:if>
									<c:if test="${pvg.PVG_START_STOP eq 1 }">
										<input id="start" type="button" class="btn" value="启用(S)"
											title="Alt+S" accesskey="S">
										<input id="stop" type="button" class="btn" value="停用(W)"
											title="Alt+W" accesskey="W">
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
						<table id="ordertb" width="100%" border="0" cellpadding="1"
							cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th nowrap align="center">
									<input type="checkbox" style="valign: middle"
										id="allChecked">
								</th>
								<th nowrap="nowrap" align="center" dbname="TRUCK_TYPE">
									车型
								</th>
								<th nowrap="nowrap" align="center" dbname="VOLUME">
									最小容积
								</th>
								<th nowrap="nowrap" align="center" dbname="VOLUME">
									最大容积
								</th>
								<th nowrap="nowrap" align="center" dbname="LENGTH">
									长度
								</th>
								<th nowrap="nowrap" align="center" dbname="WIDTH">
									宽度
								</th>
								<th nowrap="nowrap" align="center" dbname="HEIGHT">
									高度
								</th>
								<th nowrap="nowrap" align="center" dbname="STATE">
									状态
								</th>
							</tr>
							<c:forEach items="${result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)"
									onclick="setDtlId('${rst.TRUCK_ID}');selectThis(this);setEidChecked(document.getElementById('eid${rr}'));getID('${rst.TRUCK_ID}');setBtStates();">
									<td width="1%" align='center'>
										<input type="checkbox" style="valign: middle"
											id="eid${rr}" name="eid" value="${rst.TRUCK_ID}"
											onclick="setEidChecked(this);" />
									</td>
									<td nowrap="nowrap" align="left">
										${rst.TRUCK_TYPE}&nbsp;
									</td>
									<td nowrap="nowrap" align="right">
										${rst.MIN_VOLUME}&nbsp;
									</td>
									<td nowrap="nowrap" align="right">
										${rst.MAX_VOLUME}&nbsp;
									</td>
									<td nowrap="nowrap" align="right">
										${rst.LENGTH}&nbsp;
									</td>
									<td nowrap="nowrap" align="right">
										${rst.WIDTH}&nbsp;
									</td>
									<td nowrap="nowrap" align="right">
										${rst.HEIGHT}&nbsp;
									</td>
									<td nowrap="nowrap" align="center" id="state${rst.TRUCK_ID}">
										${rst.STATE}
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty result}">
								<tr>
									<td height="25" colspan="10" align="center" class="lst_empty">
										&nbsp;无相关记录&nbsp;
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<form id="form1" action="#" method="post" name="listForm">
			<input type="hidden" id="TRUCK_ID" name="TRUCK_ID" value="" />
			<input type="hidden" id="PRVD_ID" name="PRVD_ID" value="${PRVD_ID}">
		</form>
	</body>
</html>

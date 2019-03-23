<!--
 * @prjName:喜临门营销平台
 * @fileName:Storeout_Detial
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
	<script type="text/javascript" src="${ctx}/pages/drp/store/storein/Storein_Scan.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>货品扫码</title>
</head>
<body class="bodycss1">
	<form id="queryForm" method="post" action="#" >
		<div align="center">
			<h3>
				货品扫码
			</h3>
		</div>
		序列号：
		<input id='SN_TEMP' type="text"
			style="border-width: 0 0 1 0; border-style: solid; border-color: #000000;">
		<table width="99.8%"  border="0" cellSpacing=0
			cellPadding=0>
			<tr>
				<td>
					<div class="lst_area">
						<input type="hidden" id="STOREIN_ID" name="STOREIN_ID" value="${STOREIN_ID}">
						<table id="jsontb" width="100%" border="0" cellpadding="1"
							cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th nowrap align="center">
									<input type="checkbox" style="height: 12px; valign: middle"
										id="allChecked">
								</th>
								<th nowrap align="center">
									货品编号
								</th>
								<th nowrap align="center">
									货品名称
								</th>
								<th nowrap align="center">
									规格型号
								</th>
								<th nowrap align="center">
									花号
								</th>
								<th nowrap align="center">
									品牌
								</th>
								<th nowrap align="center">
									标准单位
								</th>
								<th nowrap align="center">
									特殊工艺
								</th>
								<th nowrap align="center">
									通知入库数量
								</th>
								<th nowrap align="center">
									扫码数量
								</th>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input id="q_commit" type="button" class="btn" value="确定(O)"
										title="Alt+O" accesskey="O">
				 	<input id="q_close" type="button" class="btn" value="关闭(X)"
										title="Alt+X" accesskey="X">
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
			  '${rst.STOREIN_DTL_ID}',
              '${rst.STOREIN_ID}',
              '${rst.SN}',
              '${rst.PRD_ID}',
	          '${rst.PRD_NO}',
	          '${rst.PRD_NAME}',
	          '${rst.PRD_SPEC}',
	          '${rst.PRD_COLOR}',
              '${rst.BRAND}',
              '${rst.STD_UNIT}',
              '${rst.NOTICE_NUM}',
              '${rst.REAL_NUM}',//扫描数量
              '${rst.SPCL_TECH_ID}',
              '${rst.SPCL_TECH_FLAG}',
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
</body>


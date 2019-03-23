<%--
/**
 * @module 质检管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author zhuxw
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>成品不良品通知单信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/sample/newmasterslave/NewCpbltzd_List.js"></script>
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>
			<span id="pageTitle">当前位置：质检管理&gt;&gt;质检管理&gt;&gt;成品不良通知单维护</span>
			</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td nowrap>
				      <c:if test="${pvg.PVG_EDIT eq 1 }">
						<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					    <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
					  </c:if>
					  <c:if test="${pvg.PVG_DELETE eq 1 }">
						<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
					  </c:if>
					  <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1}">
						<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
					  </c:if>
					  <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
						<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
						<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
					  </c:if>
					  <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
						<input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
					  </c:if>
					  <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
						<input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
					  </c:if>	
					  <input id="personal" type="button" class="btn" value="个性化设置">
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="CPBLTZDBH" >成品不良通知单编号</th>
					<th nowrap align="center" dbname="CPZJDBH" >成品质检单编号</th>
					<th nowrap align="center" dbname="TZSL" >通知数量</th>
					<th nowrap align="center" dbname="JYSJ" >检验日期</th>
					<th nowrap align="center" dbname="CLYJ" >处理意见</th>
					<th nowrap align="center" dbname="BMMC" >部门信息</th>
					<th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setCPZJTZDID('${rst.CPZJTZDID}');setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.CPBLTZDID}"/>
						</td>
						<td nowrap align="left">${rst.CPBLTZDBH }&nbsp;</td>
						<td nowrap align="left">${rst.CPZJDBH }&nbsp;</td>
						<td nowrap align="left">${rst.TZSL }&nbsp;</td>
						<td nowrap align="left">${rst.JYSJ }&nbsp;</td>
						<td nowrap align="left">${rst.CLYJ }&nbsp;</td>
						<td nowrap align="left">${rst.BMMC }&nbsp;</td>
						<td nowrap align="left" id="state${rst.CPBLTZDID}" value="${rst.STATE}">${rst.STATE }&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="19" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
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
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">
						<input type="hidden" id="CPZJTZDID" name="CPZJTZDID">&nbsp;
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
<input type="hidden" name="condition" value=" DELFLAG=0">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="detail3">
							<tr>
								<td nowrap align="right" class="detail_label">
									成品不良通知单编号：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="CPBLTZDBH" value="${params.CPBLTZDBH}" name="CPBLTZDBH" size="10" seltarget="selBH" inputtype="string" class="uppercase"/>
									<input json="CPBLTZDID" name="CPBLTZDID" value="${params.CPBLTZDID}" type="hidden">
									<img align="absmiddle" name="selBH" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
									     onClick="selCommon('134', false, 'CPBLTZDID', 'CPBLTZDID', 'forms[1]','CPBLTZDID,CPBLTZDBH',
                                         'CPBLTZDID,CPBLTZDBH', 'condition')">
								</td>
								<td nowrap align="right" class="detail_label">
									成品质检单编号：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="CPZJDBH" name="CPZJDBH" value="${params.CPZJDBH}" size="10" seltarget="selMXXX" inputtype="string" class="uppercase"/>
									<input type="hidden" json="CPZJTZDID" name="CPZJTZDID" value="${params.CPZJTZDID}" />
									<img align="absmiddle" name="selMXXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif"  
									onClick="selCommon('127', false, 'CPZJTZDID', 'CPZJTZDID', 'forms[1]','CPZJTZDID,CPZJDBH,WLDWXXID,WLXXID,WLDWBH,WLDWMC,WLBH,WLMC,PP,NF,JJ,YSBH,YSMC,WLXXYSID,JYRID,JYRXM', 'CPZJTZDID,CPZJDBH,WLDWXXID,WLXXID,WLDWBH,WLDWMC,WLBH,WLMC,PP,NF,JJ,YSBH,YSMC,WLXXYSID,JYRID,JYRXM', '');">
								</td>
								<td nowrap align="right" class="detail_label">
									供应商编号：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="WLDWBH" name="WLDWBH" value="${params.WLDWBH}" size="10" seltarget="selMXXX" inputtype="string" class="uppercase"/>
									<input type="hidden" json="WLXXID" name="WLXXID" value="${params.WLXXID}"  size="10"/>
									<input type="hidden" json="WLDWXXID" name="WLDWXXID" value="${params.WLDWXXID}" size="10"/>
								</td>
								<td nowrap align="right" class="detail_label">
									供应商名称：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="WLDWMC" name="WLDWMC" value="${params.WLDWMC}" size="10" seltarget="selMXXX"/>
								</td>
							</tr>		
							<tr>
								<td nowrap align="right" class="detail_label">
									款号：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="WLBH" name="WLBH" value="${params.WLBH}" size="10" seltarget="selMXXX" inputtype="string" class="uppercase"/>
								</td>
								<td nowrap align="right" class="detail_label">
									款名：
								</td>
								<td nowrap class="detail_content">
									 <input type="text" json="WLMC" name="WLMC" value="${params.WLMC}" size="10" seltarget="selMXXX" inputtype="string" class="uppercase"/>
								</td>
								<td nowrap align="right" class="detail_label">
									品牌：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="PP" name="PP" size="10" value="${params.PP}" seltarget="selMXXX" inputtype="string" class="uppercase"/>
								</td>
								<td nowrap align="right" class="detail_label">
									年份：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="NF" name="NF" value="${params.NF}" size="10" seltarget="selMXXX"/>
								</td>
							</tr>
							
							<tr>
								<td nowrap align="right" class="detail_label">
									季节：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="JJ" name="JJ" value="${params.JJ}"  size="10" seltarget="selMXXX"/>
								</td>
								<td nowrap align="right" class="detail_label">
									颜色：
								</td>
								<td nowrap class="detail_content">
									 <input type="hidden" json="YSBH" name="YSBH" value="${params.YSBH}" size="10" />
									 <input type="text" json="YSMC" name="YSMC" value="${params.YSMC}" size="10" seltarget="selMXXX"/>
								</td>
								<td nowrap align="right" class="detail_label">
									款色号：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="WLXXYSID" name="WLXXYSID" value="${params.WLXXYSID}" size="10" seltarget="selMXXX" inputtype="string" class="uppercase"/>
								</td>
								<td nowrap align="right" class="detail_label">
									通知数量：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="TZSL" name="TZSL" value="${params.TZSL}"  size="10"/>
								</td>
							</tr>
							
							<tr>
								<td nowrap align="right" class="detail_label">
									退回数量：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="THSL" name="THSL" value="${params.THSL}" size="10"/>
								</td>
								<td nowrap align="right" class="detail_label">
									通知不合格数量：
								</td>
								<td nowrap class="detail_content">
									 <input type="text" json="TZBHGSL" name="TZBHGSL" value="${params.TZBHGSL}" size="10"/>
								</td>
								<td nowrap align="right" class="detail_label">
									让步接收数量：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="RBJSSL" name="RBJSSL" value="${params.RBJSSL}"  size="10"/>
								</td>
								<td nowrap align="right" class="detail_label">
									报废数量：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="BFSL" name="BFSL" value="${params.BFSL}" size="10"/>
								</td>
							</tr>
							
							<tr>
								<td nowrap align="right" class="detail_label">
									修色数量：
								</td>
								<td nowrap class="detail_content">
									<input type="text" json="XSSL" name="XSSL" value="${params.XSSL}" size="10"/>
								</td>
								<td nowrap align="right" class="detail_label">
									检验人：
								</td>
								<td nowrap class="detail_content">
									 <input type="hidden" json="JYRID" name="JYRID" value="${params.JYRID}" size="10" />
									 <input type="text" json="JYRXM" name="JYRXM" value="${params.JYRXM}" size="10" seltarget="selMXXX"/>
								</td>
								<td nowrap align="right" class="detail_label">
									状态：
								</td>
								<td nowrap class="detail_content">
									<select json="STATE" name="STATE" id="STATE" value="${params.STATE}" style="width: 95px"></select>
								</td>
								<td width="8%" nowrap align="right"class="detail_label">制单人：</td>
								<td width="15%" class="detail_content">
									<input name="CRENAME" size="10" type="text" >
				   				 </td>
							</tr>
							<tr>
								<td width="8%" nowrap align="right" class="detail_label">
										制单部门：
								</td>
								<td width="15%" class="detail_content">
									<input name="BMMC" size="10" type="text">
								</td>	
								<td width="8%" nowrap align="right" class="detail_label">制单日期从：</td>
								<td width="15%" class="detail_content">
									<input name="frCRETIME" onclick="SelectDate();" size="10" value="${params.frCRETIME}" type="text" id="frCRETIME">
									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(frCRETIME);">
								</td>
								<td width="8%" nowrap align="right" class="detail_label">制单日期到：</td>
								<td width="15%" class="detail_content">
									<input name="toCRETIME" onclick="SelectDate();" value="${params.toCRETIME}"  size="10" type="text" id="toCRETIME">
									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(toCRETIME);">
								</td>
							</tr>
				<tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="detail_btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="detail_btn" value="关闭(X)" title="Alt+X" accesskey="X">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
</form>
</div>
</body>
<script language="JavaScript">
	SelDictShow("STATE", "97", "${params.STATE}", "");
</script>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
</html>

<%-- 
  *@module 系统管理
  *@func 自定义查询
  *@version 1.1
  *@author zhuxw
  *@edit 朱晓文
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">  
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/pages/sys/custQuery/Custom_Display.js"></script>  
		<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		
		<title>自定义展示</title>
		<style>
			.aa { position:absolute; bottom:0; width:100%; height:30px; background:#f3f3f3;}
		</style>
	</head>
	<body>
		<div id = "buttonDiv" style = "height:40px">
			<table width="100%" height="20" border="0" cellspacing="0" cellpadding="0" class="panel"><tr>
			<td height="20"><table width="100%" cellspacing="0" cellpadding="0" class="wz"><tr>
			<td width="28" align="center"><label class="wz_img"></label></td><td>
			当前位置：系统管理&gt;&gt;查询管理&gt;&gt;自定义报表查询</td><td width="50" align="right"></td></tr></table>
			</td></tr></table><div class="tablayer" ><table cellSpacing=0 cellPadding=0 border=0 width="100%"><tr>
			<td align="left" nowrap>
			<input id="b_query" type="button" class="btn" value="查询(Q)" title="Alt+Q" onclick="doQuery();" accesskey="Q">
			<input id="b_reset" type="button" class="btn" value="重置(R)" title="Alt+R" onclick="doReset();" accesskey="R">
			<input id="b_export" type="button" class="btn" value="导出Exl(E)" title="Alt+E" onclick="doExport();" accesskey="E">
			<input id="b_print" type="button" class="btn" value="打印(P)" title="Alt+P" onclick="doPrint();" accesskey="P">
			<input id="b_back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="doBack();"></td></tr>
			</table></div>

		</div>
		<div id = "queryArea" style = "height:15%">
			${queryHtml}
		</div> 
		
		<table width="100%" height="70%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="12px" align="center">
					<div >
						<table width="100%" height="100%" border="0" cellpadding="0"
							cellspacing="0" class="lst_toolbar">
							<tr><td><div align="right">
								<input type="button" id="butHidTop" class="button" value="&nbsp;&nbsp;&nbsp;&nbsp;" title="Alt+↑" >
								<input type="button" id="butHidBottom" class="button" value="&nbsp;&nbsp;&nbsp;&nbsp;" title="Alt+↓">
							</div></td></tr> 
							<tr id="buttonNo1" style="display:none" align="center"><td> <span style="font-size:16px;font-Weight:bold">${topRtpName}</span></td></tr>
							<tr id="buttonNo2" style="display:none" align="left"><td align="left">${listOpen}</td></tr> 
						</table>
					</div>
				</td>
			</tr> 
			<tr> 
				<td valign="top">  
					<div class="lst_area" id = "upDiv" style="display:none">
						<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							${listHtml}
						</table>
					</div> 
				</td>
			</tr>
		</table>
		<div class="aa"> 
			<table width="100%" height="30px" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
				<tr>
					<td>
						<form id="pageForm" action="#" method="post" name="listForm">
						<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
							<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
							<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
							<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
							<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
							<span id="hidinpDiv" name="hidinpDiv"></span>
							${paramCover.unCoveredForbidInputs }
						</form>
					</td>
					<td align="right">
						 ${page.footerHtml}${page.toolbarHtml}
					</td>
				</tr>
			</table> 
		</div>
	</body>
	<script type="text/javascript" >
		$("#butHidTop").css('background-image','url("${ctx}/styles/${theme}/images/main/frame_top.gif")');
		$("#butHidBottom").css('background-image','url("${ctx}/styles/${theme}/images/main/frame_down.gif")');
		
		$("#butHidTop").click(function(){
			$("#queryArea").css('display','none');
			$("#buttonDiv").css('display','none');
		});
		$("#butHidBottom").click(function(){
			$("#queryArea").css('display','block');
			$("#buttonDiv").css('display','block');
			//$("#buttonNo1").css('display','none');
			//$("#buttonNo2").css('display','none');
		}); 
		if($("#queryType").val() == 1){
			//$("#queryArea").css('display','none'); 
			$("#buttonNo1").css('display','block');
			$("#buttonNo2").css('display','block');
			$("#upDiv").css('display','block');
		}
	</script>
</html>

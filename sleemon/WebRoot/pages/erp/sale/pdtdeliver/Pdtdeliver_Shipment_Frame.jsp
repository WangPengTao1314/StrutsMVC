﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 货品发运
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>框架信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/erp/sale/pdtdeliver/Pdtdeliver_Frame.js"></script>
</head>
<BODY>
<table cellspacing="0" cellpadding="0" width="99.9%" height="98%">
		<tr>
			<td >
				<div id="topdiv" style="height: 50%; width: 100%">
				  <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" ></iframe>
				</div>
				<div class="tablayer tabBackground" style="height: 20px; width: 100%;" id="tablayer" >
					<table cellSpacing=0 cellPadding=0 border=0 width="100%">
						<tr>
						   <!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
							<td id="label" nowrap="nowrap">
								<span id="label_1" class="label_down" style="margin-top:2px;">&nbsp;货品发运明细&nbsp;</span>
						        <input type="hidden" id="showLabel" value="label_1"/>
							</td>
							 <td  align="center">
							  <input id="confirm" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
							  <input id="rest" type="button" class="btn" value="取消(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
							 </td>
 
							<!--标签页添加完毕-->
							<td class="label_line" align="right" width="150px" nowrap>
								<input type="button" id="butHidTop" class="button" value="↑" title="Alt+↑">
								<input type="button" id="butHidBottom" class="button" value="↓" title="Alt+↓">
							</td>
						</tr>
					</table>
				</div>
				<div id="bottomdiv" style="height: 40%; width: 100%;">
					<!-- 下帧 -->
					<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
				</div>
			</td>
		</tr>
	</table>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="actionType" value="list"/>
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
</body>

<script type="text/javascript">
$(function(){
	//框架页面初始化
	framePageInit("pdtdeliver.shtml?action=toList&module=&forWard=shipment");
	$("#confirm").click(function(){
		setFatherValue();
		window.close();
    	
    });
    
    $("#rest").click(function(){
    	rest();
    	window.close();
    });
    
    
});

function setFatherValue(){
    window.dialogArguments.$("#SELECT_DELIVER_ORDER_ID").val($("#selRowId").val());
	window.close();
}

function rest(){
	$("#selRowId").val("");
	topcontent.$("#selRowId").val("");
}
    
//bottomcontent页面跳转
function gotoBottomPage(DELIVER_ORDER_IDS){
	if(null == DELIVER_ORDER_IDS){
		DELIVER_ORDER_IDS = "";
	}
	var url = "pdtdeliver.shtml?action=shipmentChildList";
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&DELIVER_ORDER_IDS="+DELIVER_ORDER_IDS);
}
    
</script>


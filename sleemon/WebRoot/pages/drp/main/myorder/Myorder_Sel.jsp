<!-- 
 *@module 分销业务
 *@func 人员信息维护
 *@version 1.1
 *@author lyg
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>我要订货</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/main/myorder/Myorder_Sel.js"></script>
	<style type="text/css">
		span{
			font-size: 12px;
			font-weight: bold;
		}
		.tdTop{
			width: 80px;
		}
	</style>
</head>
<body class="bodycss1">
	<div style="overflow-x: auto; overflow-y: auto; height: 20%;width: 100%" id="topDiv">
	<form>
		<input type="hidden" name="selPrd" value="  "/>
		<input type="hidden" id="LEDGER_ID" value="${LEDGER_ID}"/>
		<input type="hidden" name="selPrdType" value=" STATE='启用' and FINAL_NODE_FLAG=0 and DEL_FLAG=0  "/>
		<table width="90%" id="jsontb">
		  <tr>
		    <td width="80px" align="right" ><span>货品：</span></td>
		    <td>
		    	<input  type="text" value="" id="prd_Info" style="width: 280px;" />
		    	<input value="查询" type="button" class="btn" id="prd_name" style="margin-left: 30px;"  onclick="clickBut()"/>
		    	
		    	<c:if test="${0 ne havaAreaSerId}">
			    	<input type="checkbox" id="rebateQuery" onclick="clickBut()" style="margin-left: 5px;">返利商品
			     
			    	<c:if test="${pvg.PVG_BWS_GIFT eq 1 }">
			    	 <input type="checkbox" id="lassQuery" onclick="clickBut()">赠品
			    	</c:if>
			    	
		    	</c:if>
		    	
		    </td>
		  </tr>
		  <tr>
		    <td class="tdTop" align="right" ><span>品牌：</span></td>
		    <td>
			    <c:forEach items="${BRANDS}" var="brand" varStatus="row">
			    	<input type="checkbox"  name="brand" value="${brand.BRAND}" onclick="clickBut()" />${brand.BRAND}&nbsp;&nbsp;&nbsp;
		   		 </c:forEach>
		   		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   		 <span style="margin-left: 40px;">分类：</span>
		   		 <select style="width: 130px;margin-right: 10px;" id="PRD_TYPE" onchange="clickBut()">
					<option value="">--请选择--</option>
					<c:forEach items="${typeList}" var="type">
						<option value="${type}">${type}</option>
					</c:forEach>
				</select>
				<span>规格：</span>
				<!--Start-- 刘曰刚--2014-07-07-->
				<!-- 修改文本框长度，默认长度会使通用选取按钮换行 -->
				<input id="PRD_SPEC" type="text" name="PRD_SPEC" style="width: 120px;" json="PRD_SPEC" />
				<!--End-- 刘曰刚--2014-07-07-->
		    	<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selectSpec()">
		   	</td>
		  <tr>
		  	<td class="tdTop" align="right" ><span>促销：</span></td>
		  	<td>
		  	<c:if test="${fn:length(PRMT_PLANS)==0}">
		  		<span style="color: red;">当前没有促销活动</span>
		  	</c:if>
		  	<c:forEach items="${PRMT_PLANS}" var="prmt" varStatus="row">
		    	<input type="checkbox"  name="prmt" value="${prmt.PRMT_PLAN_ID}" onclick="clickBut()" />${prmt.PRMT_PLAN_NAME}&nbsp;&nbsp;&nbsp;
		    </c:forEach>
		    </td>
		  </tr>
		</table>
		</form>
	</div>
	<div id="bottomdiv" style="height: 78%; width: 100%;z-index:-1;position:absolute;">
		<div style="margin-bottom: 2px;">
			<span style="font-size: 14px;font-weight: bold;width: 90%;margin-left: 3px;">货品信息</span>
			<span style="">
			  <input type="button" value="↑" style="margin-right: 50px;" onclick="hide()" id="showOrHide" class="btn" />
			</span>
		</div>
			<!-- 下帧 -->
				<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="94%" style="z-index:-1;position:absolute;" frameborder="0" scrolling="AUTO"></iframe>
	</div>
</body>
<script language="JavaScript">
</script>
</html>
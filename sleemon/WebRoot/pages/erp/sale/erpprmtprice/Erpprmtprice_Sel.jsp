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
	<title>总部促销价格设定</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/erpprmtprice/Erpprmtprice_Sel.js"></script>
	<style type="text/css">
	</style>
</head>
<body class="bodycss1" >
	<div style="overflow-x: auto; overflow-y: auto; height: 23%;width: 100%" id="topDiv">
		<form method="POST" action="#" id="mainForm" >
			<input type="hidden" name="selectParams" value=" STATE ='审核通过' or STATE='已终止' and DEL_FLAG='0' ">
			<input type="hidden" name="selectPrd" value=" STATE ='启用' and COMM_FLAG=1 and DEL_FLAG='0' ">
			<input type="hidden" name="selectPrdGroup" id="selectPrdGroup" value="STATE = '启用' and DEL_FLAG='0' ">
			<table width="100%" id="jsontb" >
			<!--//-- 0156393--Start--刘曰刚--2014-06-17//-->
			<!--修改页面框架，增加头部 当前地址，修改查询条件显示背景，修改查询重置按钮位置 -->
				<tr>
					<td height="20px" valign="top" colspan="8">
						<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;价格管理&gt;&gt;总部促销价格设定</td>
							<td width="50" align="right"></td>
						</tr>
					  </table>
					</td>
				</tr>
				<tr>
					
					<td width="8%" nowrap align="right" class="detail_label">
						促销活动编号：
					</td>
					<td width="13%" class="detail_content">
						<input id="PRMT_PLAN_NO" json="PRMT_PLAN_NO" readonly name="PRMT_PLAN_NO" label="促销活动编号" type="text" inputtype="string" mustinput="true"  autocheck="true" value="${param.PRMT_PLAN_NO}"  >
						<input id="PRMT_PLAN_ID" json="PRMT_PLAN_ID"  name="PRMT_PLAN_ID" type="hidden" inputtupe="string" value="${param.PRMT_PLAN_ID}"/>
						<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_47', false, 'PRMT_PLAN_ID', 'PRMT_PLAN_ID', 'forms[0]','PRMT_PLAN_ID,PRMT_PLAN_NO,PRMT_PLAN_NAME', 'PRMT_PLAN_ID,PRMT_PLAN_NO,PRMT_PLAN_NAME', 'selectParams');clickBut();">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						促销活动名称：
					</td>
					<td width="13%" class="detail_content">
						<input id="PRMT_PLAN_NAME" name="PRMT_PLAN_NAME" readonly type="text" label="促销活动名称" autocheck="true" mustinput="true"  value="${param.PRMT_PLAN_NAME}" 	inputtype="string"  json="PRMT_PLAN_NAME" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						价格设定：
					</td>
					<td width="18%" class="detail_content">
						<input type="radio" name="set" value="未设置">未设置 <input type="radio" name="set" value="已设置">已设置 <input type="radio" checked="checked" value="全部" name="set">全部 
					</td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">
						货品分类：
					</td>
					<td width="13%" class="detail_content">
						<input  name="PAR_PRD_NAME" value="${params.PAR_PRD_NAME }" type="text" id="PAR_PRD_NAME" json="PAR_PRD_NAME"   ></input>
						
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						货品编号：
					</td>
					<td width="13%" class="detail_content">
						<input  name="PRD_NO" inputtype="string" value="${params.PRD_NO}" type="text"  autocheck="true"  id="PRD_NO" json="PRD_NO" ></input>
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_21', true, 'PRD_NO', 'PRD_NO', 'forms[0]','PRD_NO,PRD_NAME', 'PRD_NO,PRD_NAME', 'selectPrd')">
					</td> 
					<td width="8%" nowrap align="right" class="detail_label">
						货品名称：
					</td>
					<td width="13%" class="detail_content">
						<input id="PRD_NAME" json="PRD_NAME"  name="PRD_NAME" type="text"   value="${param.PRD_NAME}" inputtype="string" seltarget="selJGXX" autocheck="true" >
						
					</td>
				</tr>
				<tr>
						<td width="8%" nowrap align="right" class="detail_label">
							状态：
						</td>
						<td width="13%" class="detail_content">
							<select id="STATE" name="STATE" style="width: 155"></select>
						</td>
						<td width="6%" nowrap align="right" class="detail_label">货品组名称：</td>
							<td width="10%" class="detail_content"> 
							   <input id="PRD_GROUP_ID" name="PRD_GROUP_ID"  json="PRD_GROUP_ID" type="hidden"  value="${params.PRD_GROUP_ID}"/>
								<input id="PRD_GROUP_NAME" name="PRD_GROUP_NAME"  json="PRD_GROUP_NAME" type="text"  value="${params.PRD_GROUP_NAME}"/>	   					
			   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_29', true, 'PRD_GROUP_ID', 'PRD_GROUP_ID', 'forms[0]','PRD_GROUP_NAME','PRD_GROUP_NAME','selectPrdGroup');"/>

                             </td>
					<td align="center" colspan="5">
						<input type="button" onclick="clickBut()" class="btn" value="查询"/>
						<input type="button" id="res" class="btn" value="重置"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="bottomdiv" style="height: 77%; width: 100%;z-index:-1;position:absolute;">
		<!-- 下帧 -->
			<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="94%" style="z-index:-1;position:absolute;" frameborder="0" scrolling="AUTO"></iframe>
	</div>
	<!--//-- 0156393--End--刘曰刚--2014-06-17//-->
</body>
<script language="JavaScript">
	SelDictShow("STATE","BS_143","${params.STATE}","");
</script>
</html>
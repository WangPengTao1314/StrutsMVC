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
	<title>销售折扣设置</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/channdiscount/Channdiscount_Sel.js"></script>
	<style type="text/css">
	</style>
</head>
<body class="bodycss1" >
	<div style="overflow-x: auto; overflow-y: auto; height: 23%;width: 100%" id="topDiv">
		<form method="POST" action="#" id="mainForm" >
			<input type="hidden" name="selectParams" value=" STATE in( '启用') and DEL_FLAG='0' ">
			<table width="100%" id="jsontb" >
			<!--//-- 0156393--Start--刘曰刚--2014-06-17//-->
			<!--修改页面框架，增加头部 当前地址，修改查询条件显示背景，修改查询重置按钮位置 -->
				<tr>
					<td height="20px" valign="top" colspan="8">
						<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;价格管理&gt;&gt;销售折扣设置</td>
							<td width="50" align="right"></td>
						</tr>
					  </table>
					</td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">
						折扣类别：
					</td>
					<td width="13%" class="detail_content">
						<select id="DECT_TYPE" name="DECT_TYPE"  onchange="clickBut()" json="DECT_TYPE" style="width: 110"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						区域编号：
					</td>
					<td width="13%" class="detail_content">
						<input id="AREA_NO" json="AREA_NO" name="AREA_NO" type="text" inputtype="string" style="width: 110"  autocheck="true" value="${param.AREA_NO}"  >
						<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_18', false, 'AREA_NO', 'AREA_NO', 'forms[0]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						区域名称：
					</td>
					<td width="13%" class="detail_content">
						<input id="AREA_NAME" name="AREA_NAME" type="text"  autocheck="true"  value="${param.AREA_NAME}" style="width: 110"	inputtype="string"  json="AREA_NAME" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						折扣设置：
					</td>
					<td width="18%" class="detail_content">
						<input type="radio" name="set" checked="checked" value="未设置">未设置 <input type="radio" name="set" value="已设置">已设置 <input type="radio"  value="全部" name="set">全部 
					</td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">
						渠道类别：
					</td>
					<td width="13%" class="detail_content">
						<select  name="CHANN_TYPE" style="width: 110" id="CHANN_TYPE" json="CHANN_TYPE"  ></select>
						
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						渠道级别：
					</td>
					<td width="13%" class="detail_content">
						<select  name="CHAA_LVL" inputtype="string"  autocheck="true" style="width: 110" id="CHAA_LVL" json="CHAA_LVL" ></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						渠道编号：
					</td>
					<td width="13%" class="detail_content">
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" style="width: 110"  value="${param.CHANN_NO}" inputtype="string" seltarget="selJGXX" autocheck="true" >
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_NO', 'CHANN_NO', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						渠道名称
					</td>
					<td width="18%" class="detail_content">
						<input id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" type="text" style="width: 110" autocheck="true" inputtype="string" >
					</td>
				</tr>
				<tr>
					<td align="center" colspan="6">
						<input type="button" onclick="clickBut()" class="btn" value="查询"/>
						<input type="button" id="res" class="btn" value="重置"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="bottomdiv" style="height: 75%; width: 100%;z-index:-1;position:absolute;">
		<!-- 下帧 -->
			<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="94%" style="z-index:-1;position:absolute;" frameborder="0" scrolling="AUTO"></iframe>
	</div>
	<!--//-- 0156393--End--刘曰刚--2014-06-17//-->
</body>
<script language="JavaScript">
	var DECT_TYPE='${param.DECT_TYPE}';
	if(""==DECT_TYPE||null==DECT_TYPE){
		DECT_TYPE="正常折扣";
	}
	SelDictShow("DECT_TYPE","BS_7",DECT_TYPE,"");
	SelDictShow("CHANN_TYPE", "171", '${param.CHANN_TYPE}', "");
	SelDictShow("CHAA_LVL", "169", '${param.CHAA_LVL}', "");
	//0156890--start--刘曰刚--2014-06-19//
	//页面加载时删除下拉框--请选择--
	setTimeout("delSel()",100);
	function delSel(){
		$("#DECT_TYPE option[text='--请选择--']").remove();
	}
	//0156890--start--刘曰刚--2014-06-19//
</script>
</html>
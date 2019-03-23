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

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/sample/newmasterslave/NewCpbltzd_Cpbltzdmx_Mx_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>成品不良品通知单维护</title>
</head>
<body>
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap>
			   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
			   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
			   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
		</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form><input type="hidden" id="CPZJTZDID" name="CPZJTZDID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
				<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
				<th nowrap align="center">成品质检项目编号</th>
				<th nowrap align="center">成品质检项目名称</th>
				<th nowrap align="center">质检项目类别</th>
				<th nowrap align="center">应用标准</th>
				<th nowrap align="center">检验参数</th>
				<th nowrap align="center">合格标准</th>
				<th nowrap align="center">检验值</th>
				<th nowrap align="center">让步接收数量</th>
				<th nowrap align="center">报废数量</th>
				<th nowrap align="center">退回数量</th>
				<th nowrap align="center">修色数量</th>
				<th nowrap align="center">备注</th>
			</tr>
			<c:forEach items="${rst}" var="rst" varStatus="row">
			 <tr class="list_row${row}" onmouseover="mover(this)" onmouseout="mout(this)">
			 <td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='CPBLTZDMXID' name='CPBLTZDMXID' value='${rst.CPBLTZDMXID}'/></td> 
		     <td nowrap align="left" style="display:none;"><input type="text" name="CPZJTZDMXID" seltarget="selMXXX" autocheck="true" inputtype="string" label="" maxlength="32" value="${rst.CPBLTZDID}"size="1" />&nbsp;</td> 
		     <td nowrap align="left" style="display:none;"><input type="text" json="CPZJXMID" id="CPZJXMID" name="CPZJXMID'+rownum+'" seltarget="selMXXX" autocheck="true" inputtype="string" label="成品质检项目ID" maxlength="32" value="${rst.CPZJXMID}"size="10" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="CPZJXMBH" id="CPZJXMBH" name="CPZJXMBH" seltarget="selMXXX" autocheck="true" inputtype="string" label="成品质检项目编号" maxlength="30" value="${rst.CPZJXMBH}"size="9"/> </td> 
		     <td nowrap align="left"><input type="text" json="CPZJXMMC" id="CPZJXMMC" name="CPZJXMMC" seltarget="selMXXX" autocheck="true" inputtype="string" label="成品质检项目名称" maxlength="100" value="${rst.CPZJXMMC}"size="10" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="ZJXMLB" id="ZJXMLB" name="ZJXMLB" seltarget="selMXXX" autocheck="true" inputtype="string" label="质检项目类别" maxlength="30" value="${rst.ZJXMLB}"size="8" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="YYBZ" id="YYBZ" name="YYBZ" seltarget="selMXXX" autocheck="true" inputtype="string" label="应用标准" maxlength="30" value="${rst.YYBZ}"size="6" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="JYCS" id="JYCS" name="JYCS" seltarget="selMXXX" autocheck="true" inputtype="string" label="检验参数" maxlength="50" value="${rst.JYCS}"size="6" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="HGBZ" id="HGBZ" name="HGBZ" seltarget="selMXXX" autocheck="true" inputtype="string" label="合格标准" maxlength="50" value="${rst.HGBZ}"size="6" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="JYZ" id="JYZ" name="JYZ" seltarget="selMXXX" autocheck="true" inputtype="string" label="检验值" maxlength="50" value="${rst.JYZ}"size="6" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="RBJSSL" name="RBJSSL" autocheck="true" inputtype="float" label="让步接收数量" maxlength="8" value="${rst.RBJSSL}"size="3" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="BFSL" name="BFSL" autocheck="true" inputtype="float" label="报废数量" maxlength="8" value="${rst.BFSL}"size="3" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="THSL" name="THSL" autocheck="true" inputtype="float" label="退回数量" maxlength="8" value="${rst.THSL}"size="3" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="XSSL" name="XSSL" autocheck="true" inputtype="float" label="修色数量" maxlength="8" value="${rst.XSSL}"size="3" />&nbsp;</td> 
		     <td nowrap align="left"><input type="text" json="REMARK" name="REMARK" autocheck="true" inputtype="string" label="备注" maxlength="250" value="${rst.REMARK}"size="10" />&nbsp;</td> 
		 	</tr>
			</c:forEach>
		</table>
		</form>
		</div>
	</td>
</tr>
</table>
</body>



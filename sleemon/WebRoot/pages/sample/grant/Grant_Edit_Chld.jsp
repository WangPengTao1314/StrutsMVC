<!--
/**
 * @prjName:供应链_贵人鸟
 * @fileName:Grant_Edit_Chld
 * @author zhuxw
 * @time   2013-05-15 10:35:31 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sample/grant/Grant_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>明细信息编辑</title>
</head>
<body class="bodycss1">
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
	    <form>
		<input type="hidden" id="CPBLTZDID" name="CPBLTZDID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">成品质检项目名称</th>
              <th nowrap align="center">质检项目类别</th>
              <th nowrap align="center">应用标准</th>
              <th nowrap align="center">检验参数</th>
              <th nowrap align="center">合格标准</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.CPBLTZDMXID}',
              '${rst.CPZJXMMC}',
              '${rst.ZJXMLB}',
              '${rst.YYBZ}',
              '${rst.JYCS}',
              '${rst.HGBZ}',
            ];
	addRow(arrs);
	</c:forEach>
// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              '',
              '',
              '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='CPBLTZDMXID' name='CPBLTZDMXID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="CPZJXMMC" id="CPZJXMMC'+rownum+'" name="CPZJXMMC'+rownum+'"  autocheck="true" label="成品质检项目名称"  type="text"   inputtype="string"      mustinput="true"     maxlength="50"  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ZJXMLB" id="ZJXMLB'+rownum+'" name="ZJXMLB'+rownum+'"  autocheck="true" label="质检项目类别"  type="text"   inputtype="string"      mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="YYBZ" id="YYBZ'+rownum+'" name="YYBZ'+rownum+'"  autocheck="true" label="应用标准"  type="text"   inputtype="string"         maxlength="30"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="JYCS" id="JYCS'+rownum+'" name="JYCS'+rownum+'"  autocheck="true" label="检验参数"  type="text"   inputtype="string"         maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="HGBZ" id="HGBZ'+rownum+'" name="HGBZ'+rownum+'"  autocheck="true" label="合格标准"  type="text"   inputtype="string"         maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
              ;
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}	
</script>
</html>

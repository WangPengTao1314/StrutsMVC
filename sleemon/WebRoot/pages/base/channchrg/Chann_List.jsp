

<!--  
/**
 * @module 系统管理
 * @func 渠道分管
 * @version 1.1
 * @author 张忠斌
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/channchrg/Chann_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
 
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%">
					 <input type="checkbox" id="allChecked"    style="valign:middle" />
					</th>
					<th nowrap align="center" dbname="">序号</th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">渠道类型</th>
					<th nowrap align="center" dbname="CHAA_LVL">渠道级别</th>
					<th nowrap align="center" dbname="CHANN_NAME_P">上级渠道</th>
					<th nowrap align="center" dbname="PROV">省份</th>
					<th nowrap align="center" dbname="CITY">城市</th>
					<th nowrap align="center" dbname="AREA_NO">区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME">区域名称</th>
					<c:if test="${pvg.PVG_DELETE eq 1 }">
					 <th nowrap align="center" dbname="">操作</th>
					 </c:if>
				</tr>
				<c:forEach items="${result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));" >
						<td width="1%"align='center' >
							<input type="checkbox" style="valign:middle"  name="eid" id="eid${rr}"  value="${rst.CHANN_ID}"
						   onclick="setEidChecked(this);"/>
						</td>
						<script language="JavaScript">
						  <c:if test="${not empty params.XTYHID && not empty rst.USER_CHANN_CHRG_ID}">
						    $("#eid${rr}").attr("checked","checked");
						  </c:if>
						</script>
						 <td nowrap align="center">${rr}</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHAA_LVL}&nbsp;</td>
                        <td nowrap align="left">${rst.CHANN_NAME_P}&nbsp;</td>
                        <td nowrap align="center">${rst.PROV}&nbsp;</td>
                        <td nowrap align="center">${rst.CITY}&nbsp;</td>
                        <td nowrap align="center">${rst.AREA_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NAME}&nbsp;</td>
                        <c:if test="${pvg.PVG_DELETE eq 1 }">
                          <td nowrap align="center">
						    <input id="cancel" type="button" onclick="listDelConfirm(this,'eid${rr}');" class="btn" value="取消分管" <c:if test="${empty params.XTYHID ||  empty rst.USER_CHANN_CHRG_ID}"> disabled </c:if> >&nbsp;&nbsp;
                           </td>
                        </c:if>
                        <input type="hidden" id=""  name="USER_CHANN_CHRG_ID" value="${rst.USER_CHANN_CHRG_ID}"/>
					</tr>
				</c:forEach>
				<c:if test="${empty result}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
 
</table> 
 <form  id="queryForm" name="queryForm" action="" method="post">
       <input type="hidden" name="search" id="search" value="true" />
       <input type="hidden" name="XTYHID" id="XTYHID"  >
	   <input type="hidden" name="YHBH" id="YHBH"  />
	   <input type="hidden" name="YHM"  id="YHBH" json="YHM" />
	   <input type="hidden" name="AREA_NO" id="AREA_NO"  />
       <input type="hidden" name="AREA_ID" id="AREA_ID"    >
	   <input type="hidden" name="AREA_NAME"  id="AREA_NAME"    />
       <input type="hidden" name="CHANN_TYPE" id="CHANN_TYPE"/>
       <input type="hidden" name="CHAA_LVL" id="CHAA_LVL" />
       <input type="hidden" name="CHANN_ID" id="CHANN_ID"  value="">
	   <input type="hidden" name="CHANN_NO"  id="CHANN_NO"     value="" >
	   <input type="hidden" name="CHANN_NAME" id="CHANN_NAME" value=""/>
	   <input type="hidden" name="BMXXID" id="BMXXID" value=""/>
	   <input type="hidden" name="BMMC" id="BMMC" value=""/>
       <input type="hidden" name="notcharg" id="notcharg"  />
       <input type="hidden" name="PROV" id="PROV"  />
        <input type="hidden" name="CITY" id="CITY"  />
        
 </form>
</body>

</html>

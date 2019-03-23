<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/sotoadvorder/Sotoadvorder_Edit_Chld.js"></script>
	<style type="text/css">
		.readonly{
			width:70px;
		}
		.text_underline{
			border-bottom-width:0px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
		}
	</style>
	<title>销售订单转订货处理</title>
</head>
<body class="bodycss1" onload="selectCarType();">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="90%" border="0" cellSpacing=0 cellPadding=0 id="firstTable">
<tr>
	<td height="20px" valign="top" colspan="3">
	<p align="center" style="font-size: 20px;font-weight: bold;">销售订单转订货处理</p>
	</td>
</tr>
<tr>
	<td height="50px;" >
		<input type="button" id="select" class="btn" value="查看库存(S)" title="Alt+S" accesskey="S" />
		<input type="button" id="delete" class="btn" style="margin-right: 50px;" value="删除(D)" title="Alt+D" accesskey="D" />
	</td>
	<td align="right" id="carTd">
		<input type="checkbox" id="rebate" onclick="rebOp()" ><span style="font-weight: bold">是否使用返利</span>
		<span style="margin-left: 15px;font-weight: bold;">车型：</span>
		<span>
			<input type="hidden" name="MIN_VOLUME" id="MIN_VOLUME"/>
			<input type="hidden" name="MAX_VOLUME" id="MAX_VOLUME"/>
			
			<select  name="carType" id="carType" style="width: 100px;" onchange="changeVolumn(this);"></select>
			<span style="margin-left: 2px;text-align: center">
			 <span name="car_VOLUMN" id="car_VOLUMN" class="text_underline" style="line-height:28px;height:30px;">
			</span>
		</span>
		<span style="font-weight: bold;margin-left: 10px;text-align: center;">总金额:</span><span id="total">0</span>
		<span style="font-weight: bold;margin-left: 10px;text-align: center;">总体积：</span><span id="allvol" >0</span>
		<span style="font-weight: bold;margin-left: 10px;text-align: center;">共需</span><span id="car">&nbsp;&nbsp;</span><span style="font-weight: bold;">车</span>
	</td>
</tr>
<tr>
<td colspan="3">
	<div class="lst_area">
	    <form id="pageForm" method="post">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
		<input type="hidden" name="SALE_ORDER_IDS" id="SALE_ORDER_IDS" value="${SALE_ORDER_IDS}"/>
		<input type="hidden" name="flag" id="flag" value="${flag}"/>
		
		<input type="hidden" id="ORDER_CHANN_ID" value="${params.ORDER_CHANN_ID}"/>
		<input type="hidden" id="ORDER_CHANN_NO" value="${params.ORDER_CHANN_NO}"/>
		<input type="hidden" id="ORDER_CHANN_NAME" value="${params.ORDER_CHANN_NAME}"/>
		
		<input type="hidden" id="RECV_CHANN_ID" value="${params.RECV_CHANN_ID}"/>
		<input type="hidden" id="RECV_CHANN_NO" value="${params.RECV_CHANN_NO}"/>
		<input type="hidden" id="RECV_CHANN_NAME" value="${params.RECV_CHANN_NAME}"/>
		
		<input type="hidden" id="RECV_ADDR" value="${params.RECV_ADDR}"/>
		<input type="hidden" id="RECV_ADDR_NO" value="${params.RECV_ADDR_NO}"/>
		
		 <input id="REBATEDSCT" name="REBATEDSCT" label="返利折扣" type="hidden" value="${REBATEDSCT}">
		 <input id="REBATE_TOTAL" name="REBATE_TOTAL" label="返利总金额" type="hidden" value="${REBATE_TOTAL}">
		 <input id="REBATE_CURRT" name="REBATE_CURRT" label="当前返利" type="hidden" value="${REBATE_CURRT-REBATE_FREEZE}">
		 
		 <input type="hidden" id="REBATEFLAG" value="${REBATEFLAG}" name="REBATEFLAG"/>
		
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center" >货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">可用库存</th>
              <th nowrap align="center">抵库数量</th>
              <th nowrap align="center">实际订货数量</th>
              <th nowrap align="center">订货金额</th>
              <th nowrap align="center">备注</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<table cellSpacing=0 height="20px;" cellPadding=0 border=0 width="100%">
		<tr>
		   <td nowrap align="center">
			   <input id="create" type="button" class="btn" value="生成订货订单(C)" title="Alt+C" accesskey="C" >
			   <input id="close" type="button" class="btn" value="关闭(N)" title="Alt+N" accesskey="N" >
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
 function selectCarType(){
	  if(null =="${params.carType}" || ""=="${params.carType}"){
	     SelDictShow("carType","BS_109","5吨(49-53)","");//185
      }else{
    	  SelDictShow("carType","BS_109","${params.carType}","");
      }
	  
  }
 
 getVolumFrom("init");
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.PRD_ID}',//0
              '${rst.PRD_NO}',//1
              '${rst.PRD_NAME}',//2
              '${rst.PRD_SPEC}',//3
              '${rst.PRD_COLOR}',//4
              '${rst.BRAND}',//5
              '${rst.STD_UNIT}',//6
              '${rst.SPCL_TECH_FLAG}',//7
              '${rst.PRICE}',//8
              '${rst.DECT_RATE}',//9
              '${rst.DECT_PRICE}',//10
              '${rst.ORDER_NUM}',//11
              '${rst.ADVC_AMOUNT}',//12
              '${rst.REMARK}',//13
              '${rst.SALE_ORDER_DTL_ID}',//14
              '${rst.NUM}',//15
              '${rst.SPCL_TECH_ID}',//16
              '${rst.IS_NO_STAND_FLAG}',//17
              '${rst.VOLUME}',//18
              '${rst.CHANGE_NUM}',//19
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
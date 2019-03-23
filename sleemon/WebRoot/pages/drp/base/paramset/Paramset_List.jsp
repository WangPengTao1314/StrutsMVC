<!-- 
 *@module 分销业务
 *@func 渠道参数设置
 *@version 1.1
 *@author 
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道参数设置</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/base/paramset/Paramset_List.js"></script>
</head>
<body class="bodycss1">
	<table width="100%" border="1px" rules="none" id="jsontb">
			<tr>
				<td style="width: 110px;" >
					<font style="font-size: 12;font-weight: bolder; padding-bottom: 5px;">&nbsp;&nbsp;渠道信息：</font>
				</td>
			</tr>
		    <tr> 
			<td height="20" width="100%">
			<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			 <tr>
				<td colspan="4" class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
							<tr>
								<td width="8%" nowrap align="right" class="detail_label" >渠道名称：</td>
								<td  width="15%" class="detail_content">
									<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${params.CHANN_ID}"/>
				                   ${rst.CHANN_NAME} 
								</td>
								<td width="8%" nowrap align="right" class="detail_label">渠道类型：</td>
								<td nowrap width="15%" class="detail_content"> ${rst.CHANN_TYPE }  </td>
								<td width="8%" nowrap align="right" class="detail_label">渠道级别：</td>
								<td nowrap width="15%" class="detail_content"> ${rst.rst.CHAA_LVL} </td>
								<td width="8%" nowrap align="right" class="detail_label">联系人：</td>
								<td nowrap width="15%" class="detail_content">  ${rst.rst.PERSON_CON} </td>
							</tr>
							<tr>
								<td width="8%" nowrap align="right" class="detail_label">所属区域编号：</td>
								<td nowrap width="15%" class="detail_content"> ${rst.AREA_NO} </td>
								<td width="8%" nowrap align="right" class="detail_label">所属区域名称：</td>
								<td nowrap width="15%" class="detail_content"> ${rst.AREA_NAME} </td>
								<td width="8%" nowrap align="right" class="detail_label">最大冻结天数：</td>
								<td nowrap width="15%" class="detail_content">
								   <input type="text" name="MAX_FREEZE_DAYS" id="MAX_FREEZE_DAYS" value="${rst.MAX_FREEZE_DAYS}" autocheck="true" inputtype="int" maxlength="5" style="width: 100px;"/>
								   <font style="color: red;font-size: 12px;">(ps:空或者零表示不限天数!)</font>
								</td>
								<td width="8%" nowrap align="right" class="detail_label">销售退款是否审核：</td>
								<td nowrap width="15%" class="detail_content"> 
									<input type="checkbox" id="IS_RETURN_SALE_AUD_FLAG_CHECK" onclick="checkFlag();"  >
									<input type="hidden" id="IS_RETURN_SALE_AUD_FLAG" value="${rst.IS_RETURN_SALE_AUD_FLAG}" name="IS_RETURN_SALE_AUD_FLAG"/>
								</td>
							  </tr>
							  <tr>
							  	<td width="8%" nowrap align="right" class="detail_label">初始化年份：</td>
								<td nowrap width="15%" class="detail_content">
									<select id="INIT_YEAR" name="INIT_YEAR" style="width: 153" json="INIT_YEAR" inputtype="string" mustinput="true" label="初始化年份" autocheck="true" ></select>
								</td>
								<td width="8%" nowrap align="right" class="detail_label">初始化月份：</td>
								<td nowrap width="15%" class="detail_content">
									<select id="INIT_MONTH" name="INIT_MONTH" style="width: 153" inputtype="string" json="INIT_MONTH" mustinput="true" label="初始化月份" autocheck="true"></select> 
								</td>
								
								<td width="8%" nowrap align="right" class="detail_label">税率：</td>
								<td nowrap width="15%" class="detail_content">
									<input id="TAX_RATE" autocheck="true" json="TAX_RATE" name="TAX_RATE" type="text" label="税率" valueType="2,2"  value="${rst.TAX_RATE}" inputtype="float" autocheck="true" style="width: 100px;"  >
								</td>
								<td width="8%" nowrap align="right" class="detail_label">成本计算方式：</td>
								<td nowrap width="15%" class="detail_content">
									<select id="COST_TYPE" style="width: 153" autocheck="true" json="COST_TYPE" name="COST_TYPE"  label="成本计算方式"   ></select>
								</td>
							  </tr>
							  <tr>
							    <td width="8%" nowrap align="right" class="detail_label">渠道销售加价倍数：</td>
								<td nowrap width="15%" class="detail_content">
								  <input name="CHANN_SALE_RATE" id="CHANN_SALE_RATE" json="CHANN_SALE_RATE" label="渠道销售加价倍数" value="${rst.CHANN_SALE_RATE}" valueType="12,2" inputtype="float" autocheck="true"  />
								</td>
								<c:if test="${pvg.PVG_RECV_DATE eq 1 }">
									<td width="8%" nowrap align="right" class="detail_label">转订货范围天数：</td>
									<td nowrap width="15%" class="detail_content">
										<input id="ADVC_SCOPE_DAYS" name="ADVC_SCOPE_DAYS" style="width:155" value="${rst.ADVC_SCOPE_DAYS }"  >
									</td>
								</c:if>
								<c:if test="${pvg.PVG_SPEC_ENABLE eq 1 }">
									<td width="8%" nowrap align="right" class="detail_label">出库特殊工艺管控：</td>
									<td nowrap width="15%" class="detail_content">
										否<input name="specEnable" value="1" type="radio" <c:if test="${rst.IS_SPEC_TECH_ENABLE eq 1}">checked="checked"</c:if> >
										是<input name="specEnable" value="0" type="radio" <c:if test="${rst.IS_SPEC_TECH_ENABLE eq 0 or empty ret.IS_SPEC_TECH_ENABLE}">checked="checked"</c:if> >
									</td>
								</c:if>
							    <c:if test="${pvg.PVG_RECV_DATE ne 1 }">
									<td width="8%" nowrap align="right" class="detail_label"></td>
									<td nowrap width="15%" class="detail_content"> </td>
							    </c:if>
							    <c:if test="${pvg.PVG_SPEC_ENABLE eq 1 }">
									<td width="8%" nowrap align="right" class="detail_label"></td>
									<td nowrap width="15%" class="detail_content">
									</td>
								</c:if>
								<td width="8%" nowrap align="right" class="detail_label"></td>
								<td nowrap width="15%" class="detail_content"> </td>
								<td width="8%" nowrap align="right" class="detail_label"></td>
									<td nowrap width="15%" class="detail_content"> </td>
							  </tr>
							  <tr>
							  	<td nowrap align="center" class="detail_content" colspan="8"> 
								 <c:if test="${pvg.PVG_EDIT eq 1 }">
								   <input id="save" type="button" name="btn" value="保存(S)" onclick="saveOrUpdate()" class="btn" title="Alt+S" accesskey="S" />
								  </c:if>
								</td>
							  </tr>
						</table>
					 
				</td>
			 </tr>
			</table>
			</div>
			</td>
		</tr>	
		
		
	<tr>
	<td valign="middle">
	<div class="" style="width: 100%;overflow-x: auto;  height: 100%;background-color: #fff;">
		<form  id="orderForm" name="orderForm" method="post">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			   <tr>
			    <td id="qxBtnTb" nowrap colspan="5"  >
			     <span style="margin-left: 100px;text-align:center;height:20px;line-height:20px;">
			          <span style="text-align: center;height:22px;"> 参数类型: </span>
			          <span style="margin-left: 8px;">
			           <input type="hidden" name="search" id="search" value="true"/>
			           <select  id="DATA_TYPE" name="DATA_TYPE"  style="width: 155px;" onchange="changePage();"></select>
			         </span>
			       </span>
			       <span style="width: 400px;"></span>
			       <c:if test="${pvg.PVG_EDIT eq 1 }">
			   	    <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N" >
			   	    <input id="baocun" type="button" class="btn" value="保存(B)" title="Alt+B" accesskey="B">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   	     <input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
			   	   </c:if>
			   </td>
			    
			   </tr>
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="DATA_TYPE" >参数类型</th>
					<th nowrap align="center" dbname="DATA_NAME" >参数名称</th>
					<th nowrap align="center" dbname="DATA_VAL" >参数值</th>
					<th nowrap align="center" dbname="IS_DEAL_FLAG" >是否核销</th>
				</tr>
				<c:if test="${empty result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			 </table>
			</form>
		</div>
	</td>
</tr>
 
</table>
 
 
</body>
<script language="JavaScript">
	SelDictShow("INIT_YEAR", "89", "${rst.INIT_YEAR}", "");
	SelDictShow("INIT_MONTH", "168", "${rst.INIT_MONTH}", "");
    SelDictShow("DATA_TYPE","BS_99","${params.DATA_TYPE}","");
    SelDictShow("COST_TYPE", "BS_131", '${rst.COST_TYPE}', "");
  	<c:forEach items="${result}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.DATA_CONF_ID}', 
              '${rst.DATA_TYPE}', 
              '${rst.DATA_NAME}', 
              '${rst.DATA_VAL}' ,
              '${rst.IS_DEAL_FLAG}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
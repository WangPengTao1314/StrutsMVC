<!--  
/**
 *@module 营销管理-广告投放管理
 *@func   广告投放报销申请单维护
 *@version 1.1
 *@author zcx
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript   src="${ctx}/pages/drp/oamg/advreit/Advreit_Edit.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>广告投放报销申请单维护</title>
	 
</head>
<body class="bodycss1">

<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.back();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<form method="POST" action="#" id="mainForm">
		<input type="hidden" id="selectParams" name="selectParams" value="CHANN_ID in (${CHANN_ID}) "/>
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="17%" align="right" class="detail_label">
								广告投放报销申请单号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="ERP_ADV_REIT_NO" name="ERP_ADV_REIT_NO" type="text"
									autocheck="true" label="广告投放报销申请单号" inputtype="string" mustinput="true"
									maxlength="32" size="35"
									<c:if test="${not empty rst.ERP_ADV_REIT_NO}">value="${rst.ERP_ADV_REIT_NO}"</c:if>
									<c:if test="${empty rst.ERP_ADV_REIT_NO}">value="自动生成"</c:if>
									READONLY
									>
							</td>
							<td width="17%" align="right" class="detail_label">
								关联广告投放申请单号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="ERP_ADV_REQ_ID" name="ERP_ADV_REQ_ID" id="ERP_ADV_REQ_ID" type="hidden"/>
								<input json="ERP_ADV_REQ_NO" name="ERP_ADV_REQ_NO"
									id="ERP_ADV_REQ_NO" type="text" seltarget="selLL" label="关联广告投放申请单号"
									size="35" autocheck="true" inputtype="string"  mustinput="true"  readonly>
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selAdvcorder()" /> 	
 							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" type="hidden" />
								<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" type="text"  label="渠道编号" size="35"  readonly />  
							</td>
							<td width="17%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" label="渠道名称" size="35" readonly>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="33%" class="detail_content">
							    <input json="AREA_ID"   name="AREA_ID"   id="AREA_ID" type="hidden"/> 
							    <input json="AREA_NO"   name="AREA_NO"   id="AREA_NO" type="hidden"/> 
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" autocheck="true" label="所属战区" type="text" inputtype="string" size="35"  readonly/>
							</td>
							<td width="17%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="33%" class="detail_content">
								<input id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" json="AREA_MANAGE_ID" type="hidden"/>
								<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" type="text" size="35"  label="区域经理" readonly />
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								渠道排名：
							</td>
							<td width="33%" class="detail_content">
							    <input json="CHANN_RANK" name="CHANN_RANK" id="CHANN_RANK" type="text" size="35" label="渠道排名" readonly />
							</td>
							<td width="17%" align="right" class="detail_label">
								广告公司名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CO_NAME" name="ADV_CO_NAME" id="ADV_CO_NAME" type="text" label="广告公司名称" size="35" readonly />
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								广告公司联系人：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CO_CONTACT" name="ADV_CO_CONTACT" id="ADV_CO_CONTACT" type="text" label="广告公司联系人" size="35"  readonly />
							</td>
							<td width="17%" align="right" class="detail_label">
								广告公司联系电话：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CO_TEL" name="ADV_CO_TEL" id="ADV_CO_TEL" type="text" label="广告公司联系电话" size="35" readonly />
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								广告投放内容：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CONTENT" name="ADV_CONTENT" id="ADV_CONTENT" type="text" label="广告投放内容" size="35"  readonly />
							</td>
							<td width="17%" align="right" class="detail_label">
								广告投放地点：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_ADDR" name="ADV_ADDR" id="ADV_ADDR" type="text" label="广告投放地点" size="35"  readonly />
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								投放申请单附件：
							</td>
							<td width="33%" class="detail_content">
							    <input id="ATT_PATH" json="ATT_PATH" name="ATT_PATH"  type="hidden"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								申请报销附件：
							</td>
							<td width="33%" class="detail_content"><input id="REQREITPATH" json="REQREITPATH" name="REQREITPATH"  type="hidden"   value="${rst.REQREITPATH}"/> 
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								申请报销原因：
							</td>
							<td width="33%" class="detail_content">
							    <textarea label="申请报销原因"  json="REIT_REASON" name="REIT_REASON" id="REIT_REASON" rows="1" cols="28%" inputtype="string" autocheck="true" mustinput="true"  maxlength="250"></textarea>
							</td>
							<td width="17%" align="right" class="detail_label">
								申请报销金额：
							</td>
							<td width="33%" class="detail_content">
								<input json="REIT_AMOUNT" name="REIT_AMOUNT" id="REIT_AMOUNT" type="text" label="申请报销金额" size="35" inputtype="string" autocheck="true" mustinput="true"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								该广告申请单共审批报销金额：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_TOTAL_AMOUNT" name="ADV_TOTAL_AMOUNT" id="ADV_TOTAL_AMOUNT" type="text" label="该广告申请单共审批报销金额" size="35" readonly />
							</td>
							<td width="17%" align="right" class="detail_label">
								该广告申请单已报销金额：
							</td>
							<td width="33%" class="detail_content">
								<input json="HAS_REIM_AMOUNT" name="HAS_REIM_AMOUNT" id="HAS_REIM_AMOUNT" type="text" label="该广告申请单已报销金额" size="35" readonly />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	   uploadFile('REQREITPATH', 'SAMPLE_DIR', true,true,true);
	</script>
   </div>
</body>



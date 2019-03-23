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
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" 	href="${ctx}/styles/${theme}/css/style.css" />
	<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/advreit/Advreit_Edit_T.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>修改广告投放报销申请单</title>
	<style type="text/css">
	    .query_divT{
			display:none;
			width:50%;
			filter:alpha(opacity=90);
			position:absolute;
			left:700;
			top:200;
			right:20%;
			bottom:45%;
		}
	</style>
</head>
<body class="bodycss1">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)"
						title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B"
						accesskey="B" onclick="history.back();">
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
							     <input json="ERP_ADV_REIT_ID" name="ERP_ADV_REIT_ID" type="hidden" value="${rst.ERP_ADV_REIT_ID}"/>
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
							    <input json="ERP_ADV_REQ_ID" name="ERP_ADV_REQ_ID" id="ERP_ADV_REQ_ID" type="hidden" value="${rst.ERP_ADV_REQ_ID}"/>
								<input json="ERP_ADV_REQ_NO" name="ERP_ADV_REQ_NO"
									id="ERP_ADV_REQ_NO" type="text" seltarget="selLL" label="关联广告投放申请单号"
									size="35" autocheck="true" inputtype="string"  mustinput="true"  readonly value="${rst.ERP_ADV_REQ_NO}">
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_109', false, 'ERP_ADV_REQ_ID', 'ERP_ADV_REQ_ID', 'forms[0]','ERP_ADV_REQ_ID,ERP_ADV_REQ_NO,CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,CHANN_RANK,ADV_CO_NAME,ADV_CO_CONTACT,ADV_CO_TEL,ADV_CONTENT,ADV_ADDR,ATT_PATH', 'ERP_ADV_REQ_ID,ERP_ADV_REQ_NO,CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,CHANN_RANK,ADV_CO_NAME,ADV_CO_CONTACT,ADV_CO_TEL,ADV_CONTENT,ADV_ADDR,ATT_PATH','selectParams');selAdvcorderT();" /> 	
 							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" type="hidden" value="${rst.CHANN_ID}"/>
								<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" type="text"  label="渠道编号" size="35"  readonly value="${rst.CHANN_NO}"/>  
							</td>
							<td width="17%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" label="渠道名称" size="35" readonly value="${rst.CHANN_NAME}" />
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="33%" class="detail_content">
							    <input json="AREA_ID"   name="AREA_ID"   id="AREA_ID" type="hidden" value="${rst.AREA_ID}"/> 
							    <input json="AREA_NO"   name="AREA_NO"   id="AREA_NO" type="hidden" value="${rst.AREA_NO}"/> 
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" autocheck="true" label="所属战区" type="text" inputtype="string" size="35"  readonly value="${rst.AREA_NAME}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="33%" class="detail_content">
								<input id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" json="AREA_MANAGE_ID" type="hidden" value="${rst.AREA_MANAGE_ID}"/>
								<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" type="text" size="35"  label="区域经理" readonly value="${rst.AREA_MANAGE_NAME}"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								渠道排名：
							</td>
							<td width="33%" class="detail_content">
							    <input json="CHANN_RANK" name="CHANN_RANK" id="CHANN_RANK" type="text" size="35" label="渠道排名" readonly value="${rst.CHANN_RANK}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								广告公司名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CO_NAME" name="ADV_CO_NAME" id="ADV_CO_NAME" type="text" label="广告公司名称" size="35" readonly value="${rst.ADV_CO_NAME}"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								广告公司联系人：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CO_CONTACT" name="ADV_CO_CONTACT" id="ADV_CO_CONTACT" type="text" label="广告公司联系人" size="35"  readonly value="${rst.ADV_CO_CONTACT}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								广告公司联系电话：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CO_TEL" name="ADV_CO_TEL" id="ADV_CO_TEL" type="text" label="广告公司联系电话" size="35" readonly value="${rst.ADV_CO_TEL}"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								广告投放内容：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_CONTENT" name="ADV_CONTENT" id="ADV_CONTENT" type="text" label="广告投放内容" size="35"  readonly value="${rst.ADV_CONTENT}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								广告投放地点：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_ADDR" name="ADV_ADDR" id="ADV_ADDR" type="text" label="广告投放地点" size="35"  readonly value="${rst.ADV_ADDR}"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								投放申请单附件：
							</td>
							<td width="33%" class="detail_content">
							    <input id="ATT_PATH" json="REQPATH" name="ATT_PATH"  type="hidden" value="${rstT.REQPATH}"/>
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
							    <textarea label="申请报销原因"  json="REIT_REASON" name="REIT_REASON" id="REIT_REASON" rows="1" cols="28%" inputtype="string" autocheck="true" mustinput="true"  maxlength="250" value="${rst.REIT_REASON}">${rst.REIT_REASON}</textarea>
							</td>
							<td width="17%" align="right" class="detail_label">
								申请报销金额：
							</td>
							<td width="33%" class="detail_content">
								<input json="REIT_AMOUNT" name="REIT_AMOUNT" id="REIT_AMOUNT" type="text" label="申请报销金额" size="35" inputtype="string" autocheck="true" mustinput="true" value="${rst.REIT_AMOUNT}"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								该广告申请单共审批报销金额：
							</td>
							<td width="33%" class="detail_content">
								<input json="ADV_TOTAL_AMOUNT" name="ADV_TOTAL_AMOUNT" id="ADV_TOTAL_AMOUNT" type="text" label="该广告申请单共审批报销金额" size="35" readonly value="${rst.ADV_TOTAL_AMOUNT}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								该广告申请单已报销金额：
							</td>
							<td width="33%" class="detail_content">
								<input json="HAS_REIM_AMOUNT" name="HAS_REIM_AMOUNT" id="HAS_REIM_AMOUNT" type="text" label="该广告申请单已报销金额" size="35" readonly value="${rst.HAS_REIM_AMOUNT}"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	   //uploadFile('REQREITPATH', 'SAMPLE_DIR', true,true,true);
	   
	   var path = $("#ATT_PATH").val();
       var strs= new Array();   //定义一数组
       strs=path.split(",");    //字符分割      
       var str="";
       if(strs.length>1){
        for(var i=0;i<strs.length;i++){
          str+="SAMPLE_DIR,";
        }
       var str = str.substring(0, str.lastIndexOf(','));
       } else {
          str="SAMPLE_DIR";
       }
       displayDownFile('ATT_PATH',str,true,false);	
       uploadFile('REQREITPATH', 'SAMPLE_DIR', true,true,true);
	</script>
  </div>
</body>




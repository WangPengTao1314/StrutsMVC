<!-- 
 *@module渠道管理-装修管理
 *@func  装修整改验收单维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/rnvtnreformcheck/Rnvtnreformcheck_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>装修整改验收单维护</title>
</head>
<body class="bodycss1">
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" id="selectParams" name="selectParams" />

<div style="height: 100">
			<div class="buttonlayer" id="floatDiv">
				<table cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr>
						<td align="left" nowrap>
							<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
							<input type="button" class="btn" value="取消(B)" title="Alt+B" accesskey="B" onclick="history.back();">
							<input id="commitOk" type="button" class="btn" value="验收通过(P)" title="Alt+P" accesskey="P"/>
							<input id="reCheck"  type="button" class="btn" value="重新整改(R)" title="Alt+R" accesskey="R" />
						</td>
					</tr>
				</table>
			</div>
			<br/>
			<br/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
							<td width="15%" align="right" class="detail_label">
								装修整改单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_REFORM_ID" name="RNVTN_REFORM_ID"
									id="RNVTN_REFORM_ID" type="hidden" seltarget="selLL"
									label="装修整改单号" size="40" value="${rst.RNVTN_REFORM_ID}" readonly/>
							
							    <input json="RNVTN_REFORM_NO" id="RNVTN_REFORM_NO"
									name="RNVTN_REFORM_NO" type="text"  label="装修整改单号" size="40"
									value="${rst.RNVTN_REFORM_NO}" readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								施工负责人：
							</td>
							<td width="35%" class="detail_content">
								 <input json="PROCESS_CHARGE" id="PROCESS_CHARGE"
									name="PROCESS_CHARGE" type="text"  label="施工负责人" size="40"
									value="${rst.PROCESS_CHARGE}" readonly />
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								装修验收单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_CHECK_NO" name="RNVTN_CHECK_NO" id="RNVTN_CHECK_NO" type="text"
									label="装修验收单号"
									inputtype="string" size="40" value="${rst.RNVTN_CHECK_NO}" readonly/> 
							</td>
							<td width="15%" align="right" class="detail_label">
								装修申请单号：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO" type="text"
									label="装修申请单号" size="40" value="${rst.CHANN_RNVTN_NO}" readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								验收负责人：
							</td>
							<td width="35%" class="detail_content">
								<input json="CHECK_CHARGE" name="RNVTN_PRINCIPAL" id="RNVTN_PRINCIPAL"
									 type="text" label="验收负责人" size="40" value="${rst.CHECK_CHARGE}"
									  readonly/>
							</td>
							<td width="15%" align="right" class="detail_label">
								验收时间：
							</td>
							<td width="35%" class="detail_content">
								<input id="CHECK_TIME" name="RNVTN_CHECK_DATE" json="RNVTN_CHECK_DATE"
									type="text" size="40"  label = "验收时间"  value="${rst.CHECK_TIME}" readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
							    <input json="TERM_NO" name="TERM_NO" id="TERM_NO"
							    type="text" size="40" value="${rst.TERM_NO}" readonly
							    />
							</td>
							<td width="15%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME"
									type="text" label="终端名称" size="40" value="${rst.TERM_NAME}"
									   readonly/>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_PROP" name="RNVTN_PROP" id="RNVTN_PROP"
									type="text" label="装修性质" size="40" value="${rst.RNVTN_PROP}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="35%" class="detail_content">
								<input json="PLAN_SBUSS_DATE" name="PLAN_SBUSS_DATE" id="PLAN_SBUSS_DATE" type="text" label="计划开业时间"
									size="40" value="${rst.PLAN_SBUSS_DATE}" readonly />
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="35%" class="detail_content">
								<input json="SALE_STORE_NAME" name="SALE_STORE_NAME" id="SALE_STORE_NAME"
									type="text" label="卖场名称" size="40" value="${rst.SALE_STORE_NAME}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								卖场面积(平米)：
							</td>
							<td width="35%" class="detail_content">
								<input json="SALE_STORE_AREA" name="SALE_STORE_AREA" id="SALE_STORE_AREA" type="text" label="卖场面积"
									size="40" value="${rst.SALE_STORE_AREA}" readonly size="40"/>
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="35%" class="detail_content">
								<input json="LOCAL_TYPE" name="LOCAL_TYPE" id="LOCAL_TYPE"
									type="text" label="商场位置类别" size="40" value="${rst.LOCAL_TYPE}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="35%" class="detail_content">
								<input json="BUSS_SCOPE" name="BUSS_SCOPE" id="BUSS_SCOPE" type="text" label="经营品牌"
									size="40" value="${rst.BUSS_SCOPE}" readonly />
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
								<input json="STATE" name="STATE" id="STATE"
									type="text" label="状态" size="40" value="${rst.STATE}"
									readonly />
							</td>
							<td width="15%" align="right" class="detail_label">
								整改意见：
							</td>
							<td width="35%" class="detail_content">
								<input json="REMARK" name="REMARK" id="REMARK" type="text" label="整改意见"
									size="40" value="${rst.REMARK}" readonly />
							</td>
						</tr>
						<tr>
						   <td width="15%" align="right" class="detail_label">
								整改前图片：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								<input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH" type="hidden"  value="${rst.ATT_PATH}"/>
							</td>
						</tr>
						<tr>
						   <td width="15%" align="right" class="detail_label">
								整改后图片：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								<input id="ATTMEMT_PATH" json="ATTMEMT_PATH" name="ATTMEMT_PATH" value="${rstT.ATT_PATH}"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								设计师：
							</td>
							<td width="35%" class="detail_content">
								<input json="DESIGNER" name="DESIGNER" id="DESIGNER"
									type="text" label="状态" size="40" value="${rst.DESIGNER}"
									 />
							</td>
							<td width="15%" align="right" class="detail_label">
								整改验收意见：
							</td>
							<td width="35%" class="detail_content">
								<input json="RNVTN_REFORM_REMARK" name="RNVTN_REFORM_REMARK" id="RNVTN_REFORM_REMARK" type="text" label="整改验收意见"
									size="40" value="${rst.RNVTN_REFORM_REMARK}"   />
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								处罚意见：
							</td>
							<td width="35%" class="detail_content">
								<input json="PUNISH_REMARK" name="PUNISH_REMARK" id="PUNISH_REMARK"
									type="text" label="处罚意见" size="40" value="${rst.PUNISH_REMARK}"
									 />
							</td>
							<td width="15%" align="right" class="detail_label">
							</td>
							<td width="35%" class="detail_content">
							</td>
						</tr>
				</table>
		     </td>
		  </tr>
     </table>
</form>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
    var path = $("#PIC_PATH").val();
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
    displayDownFile('PIC_PATH',str,false,false);
    uploadFile('ATTMEMT_PATH','SAMPLE_DIR',true,true,true);
</script>
</body>

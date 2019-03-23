<!--
 * @prjName:喜临门营销平台
 * @fileName:Adviseinit_ProCmpl
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/adviseinit/Adviseinit_ProCmpl.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>产品投诉</title>
</head>
<body class="bodycss1">

<table width="99.8%" border="0" cellpadding="4" cellspacing="4" class="detail" >
	
	<tr>
		<td style="height: 15px">
			<input type="button" id="check" name="check" class="btn" value="查看我的投诉">
		</td>
	</tr>
	<tr>
		<td class="detail2">
		<form id="mainForm">
			<table width="80%" border="0" cellpadding="1" cellspacing="1" class="detail3" id="maintable">
				
               <tr>
               		<td width="15%" align="right" class="detail_label">投诉编号:</td>
					<td width="35%" class="detail_content">
                        <input type="text" value="系统自动生成" autocheck="true" inputtype="string"" label="投诉编号" id="CMPL_ADVS_ID" name="CMPL_ADVS_ID" readonly/>
					</td>
					<td width="15%" align="right" class="detail_label">紧急程度:</td>
					<td width="35%" class="detail_content">
                      <select id="EMG_LVL" name="EMG_LVL" json="EMG_LVL" style="width:155px"></select>
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">标题:</td>
					<td width="35%" class="detail_content" colspan="3">
                        <input type="text" json="CMPL_ADVS_TITLE" size="97" id="CMPL_ADVS_TITLE" name="CMPL_ADVS_TITLE" mustinput="true" inputtype="string" autocheck="true" label="标题" maxlength="100"/>
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">投诉来源:</td>
					<td width="35%" class="detail_content">
						<select label="投诉来源" name="ADVS_SOURCE" mustinput="true" inputtype="string" onchange="checkSelTel();" style="width: 153" id="ADVS_SOURCE" json="ADVS_SOURCE"></select>
					</td>
               		<td width="15%" align="right" class="detail_label">省份:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="PROV" id="PROV" name="PROV" value="${channInfo.PROV}" readonly/>
					</td>
					
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">渠道编号:</td>
					<td width="35%" class="detail_content">
						<input id="selectParams" name="selectParams" type="hidden"  value="STATE = '启用' and DEL_FLAG=0 ">
						<input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" value="${channInfo.CHANN_ID}"/>
                        <input type="text" json="CHANN_NO" id="CHANN_NO" name="CHANN_NO" autocheck="true" inputtype="string" label="渠道编号" value="${channInfo.CHANN_NO}" mustinput="true" readonly/>
                        <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,PROV','CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME,PROV', 'selectParams')"> 
					</td>
					<td width="15%" align="right" class="detail_label">渠道名称:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" value="${channInfo.CHANN_NAME}" readonly/>
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">区域编号:</td>
					<td width="35%" class="detail_content">
						<input type="hidden" id="AREA_ID" name="AREA_ID" json="AREA_ID" value="${channInfo.AREA_ID}"/>
                        <input type="text" json="AREA_NO" id="AREA_NO" name="AREA_NO" readonly value="${channInfo.AREA_NO}"/>
<!--                        <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"-->
<!--							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME','AREA_NO,AREA_NAME', 'selectParams')"> -->
					</td>
					<td width="15%" align="right" class="detail_label">区域名称:</td>
					<td width="35%" class="detail_content">
						<input type="text" json="AREA_NAME" id="AREA_NAME" name="AREA_NAME" value="${channInfo.AREA_NAME}" readonly/>
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">投诉人:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="RAISE_PSON_NAME" id="RAISE_PSON_NAME" name="RAISE_PSON_NAME" value="${rst}" readonly/>
					</td>
			        <td width="15%" align="right" class="detail_label">加盟商电话:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="TEL" name="TEL" autocheck="true" maxlength="30" inputtype="string" mustinput="true" label="加盟商电话" id="TEL"/>
					</td>
               </tr>
               <tr id="checkTr">
			        <td width="15%" align="right" class="detail_label">消费者姓名:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="CUSTOMER_NAME" id="CUSTOMER_NAME" name="CUSTOMER_NAME" autocheck="true" maxlength="30" inputtype="string" label="消费者姓名" />
                        <input type="text" name="checkInput" style="width: 0px;" id="checkName"   mustinput="true" inputtype="string" />
					</td>
			        <td width="15%" align="right" class="detail_label">消费者电话:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="CUSTOMER_TEL" name="CUSTOMER_TEL" autocheck="true" maxlength="30" inputtype="string" label="消费者电话" id="CUSTOMER_TEL"/>
                        <input type="text" name="checkInput" id="checkTel" style="width: 0px;"   mustinput="true" inputtype="string" />
					</td>
               </tr>
               <tr>
			        
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
               
			</table>
			</form>
		</td>
	</tr>
	<tr>
	<td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap>
			   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
			   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
		</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<form id="childForm">
	<input id="selectPrdParams" name="selectPrdParams" type="hidden"  value="STATE = '启用' and DEL_FLAG=0 and COMM_FLAG=1 and FINAL_NODE_FLAG=1 ">
	<div class="lst_area" style="overflow: auto;">
		<input type="hidden" id="STORE_ID" name="STORE_ID">
		<input type="hidden" id="selectParams" name="selectParams" value=" STATE ='启用' and DEL_FLAG=0">
		
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">产品类型</th>
              <th nowrap align="center">产品问题类型</th>
              <th nowrap align="center">消费者使用时间</th>
              <th nowrap align="center">生产基地</th>
              <th nowrap align="center">生产日期</th>
              <th nowrap align="center">购买时间</th>
              <th nowrap align="center">产品数量</th>
              <th nowrap align="center">附件</th>
              <th nowrap align="center">备注说明</th>
            </tr>
            
		</table>
	
	 </div>
	</form>
</td>
</tr>
<tr>
	<td align="right" nowrap>
		<input type="button" class="btn" id="save" name="save" value="提交(S)" title="Alt+S" accesskey="S" >
	</td>
</tr>
</table>

<script type="text/javascript">
	SelDictShow("EMG_LVL", "BS_76", "${params.EMG_LVL}", "");
	SelDictShow("ADVS_SOURCE", "BS_169", '', "");
	
</script>
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
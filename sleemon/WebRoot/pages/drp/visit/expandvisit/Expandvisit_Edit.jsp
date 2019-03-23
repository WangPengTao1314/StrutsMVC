<!--  
/**
 *@module 渠道管理-拜访管理
 *@func   拓展拜访表维护
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
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
    <script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/visit/expandvisit/Expandvisit_Edit.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/visit/expandvisit/dyTable.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	<!--.
		 meun{position:absolute;width:auto;height:auto;z-index:1;left: 200px;top: 150px}
		.meun td{text-align:center;}
	-->
	</style>	
	<title>拓展拜访表维护</title>
	 
</head>
<body class="bodycss1">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" onclick="saveT();">
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
		<input type="hidden" id="selectParams" name="selectParams"
			value="CHANN_ID in ${CHANN_ID}" />
	    <input type="hidden" id="selectParamsT"  name="selectParamsT"  />
	    <input type="hidden" id="selectParamsTt" name="selectParamsTt" />
	    <input type="hidden" id="selectParamst"  name="selectParamst"  />
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="15%" align="right" class="detail_label">
								流程编号：
							</td>
							<td width="20%" class="detail_content">
							     <input json="EXPAND_VISIT_ID" name="EXPAND_VISIT_ID"
									id="EXPAND_VISIT_ID" type="hidden" seltarget="selLL" size="30" 
									label="流程ID"  value="${rst.EXPAND_VISIT_ID}" />
								<input json="EXPAND_VISIT_NO" name="EXPAND_VISIT_NO" type="text" size="30" 
									id="EXPAND_VISIT_NO" autocheck="true" label="流程编号" inputtype="string"
									mustinput="true" maxlength="32"  
									<c:if test="${not empty rst.EXPAND_VISIT_NO}">value="${rst.EXPAND_VISIT_NO}"</c:if>
									<c:if test="${empty rst.EXPAND_VISIT_NO}">value="自动生成"</c:if>
									READONLY>
							</td>
							<td width="15%" align="right" class="detail_label">
								紧急程度：
							</td>
							<td width="20%" class="detail_content">
							     <input type="radio" name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" checked="true" value="正常"/>正常 
							     <input type="radio" name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" value="重要"/>重要 
							     <input type="radio" name="EME_DEGREE" id="EME_DEGREE" json="EME_DEGREE" value="紧急"/>紧急 
							</td> 
							<td width="15%" align="right" class="detail_label">
								拜访人：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_ID"     name="APPLY_ID"     id="APPLY_ID"     type="hidden"    value="${rst.APPLY_ID}" />
								<input json="APPLY_PERSON" name="APPLY_PERSON" id="APPLY_PERSON" type="text" size="30" 
									label="拜访人" value="${rst.APPLY_PERSON}"  autocheck="true"  inputtype="string" mustinput="true" readonly />
							</td>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DEP" name="APPLY_DEP" id="APPLY_DEP" type="text" size="30" 
									label="申请部门" value="${BM_NAME}"  autocheck="true"  inputtype="string"  READONLY/>
							</td>
							<td width="15%" align="right" class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    <input json="APPLY_DATE" name="APPLY_DATE" id="APPLY_DATE" type="text" size="30" 
									label="申请日期" value="${APPLY_DATE}"   autocheck="true"  inputtype="string" READONLY />
							</td>
							<td width="15%" align="right" class="detail_label">
								拜访时间：
							</td>
							<td width="20%" class="detail_content" >
							    <input json="VISIT_DATE" name="VISIT_DATE" id="VISIT_DATE" type="text" size="30" 
									label="申请时间" value="${APPLY_DATE}" autocheck="true"  inputtype="string" mustinput="true" 
									onclick="SelectDate()" READONLY/>
								<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(VISIT_DATE);">
							</td>
					    </tr> 
						<tr>
							<td width="15%"   class="detail_label">
								拜访方式：
							</td>
							<td width="20%" class="detail_content" colspan="5">
								<select id="VISIT_TYPE" name="VISIT_TYPE"  json="VISIT_TYPE" style="width: 215px;" autocheck="true" inputtype="string" mustinput="true" autocheck="true"  label="拜访方式"  value="${rst.VISIT_TYPE}">
								</select>
							</td>
						</tr> 
						<tr>
						<input type="hidden" name="VISIT_TYPE" id="VISIT_TYPE" json="VISIT_TYPE" value="实地拜访" />
			              <td colspan="6" align="right"  class="detail_label2">
			                  <input onclick="addRow()"  type="button" name="Submit2" value="添加" />
                              <input onclick="delRow()"  type="button" name="Submit3" value="删除" />
			              </td>
						</tr>
						<tr>
						  <th colspan="6" align="center">
						    现有卖场信息调查
						  </th>
						</tr>
						
						<tr>
						   <td align="left" colspan="6"> 
						     <div id="tabdiv" class="meun">
						       <table id="myTable"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="left">
						             <tr class="detail_label">
						              <th width="5%" align="center">
						                 <input type="checkbox" name="radio" id="radio" onclick="chkAll()"/>
						              </th>
						              <th width="19%" align="center">
						                   名称
						              </th>
						              <th width="19%" align="center">
						                   地址
						              </th>
						              <th width="19%" align="center">
						                   面积(平)
						              </th>
						              <th width="19%" align="center">
						                   档次排名
						              </th>
						              <th width="19%" align="center">
						                   进驻主竞品
						              </th>
						            </tr>
						              <tbody id="tbody" class="detail_content">
						                 <script type="text/javascript">
						                    var tab = document.getElementById("tbody");
											//添加行
											var newTr = document.createElement("tr");
											//添加列
											var newTd0 = document.createElement("td");
											var newTd1 = document.createElement("td");
											var newTd2 = document.createElement("td");
											var newTd3 = document.createElement("td");
											var newTd4 = document.createElement("td");
										    var newTd5 = document.createElement("td");
											var newTd6 = document.createElement("td");
											//设置单元格内容
											newTd0.innerHTML = '<input type=checkbox name="checkbox" id="box" >';
											
											newTd1.innerHTML = '<input type="text" name="EXIST_STORE_NAME"  id="EXIST_STORE_NAME"  json="EXIST_STORE_NAME"  style="align:center;"/><font color="red">*</font>';
											newTd2.innerHTML = '<input type="text" name="EXIST_STORE_ADDR"  id="EXIST_STORE_ADDR"  json="EXIST_STORE_ADDR"  style="align:center;"/><font color="red">*</font>';
											newTd3.innerHTML = '<input type="text" name="EXIST_STORE_AREA"  id="EXIST_STORE_AREA"  json="EXIST_STORE_AREA"  style="align:center;"/><font color="red">*</font>';
											newTd4.innerHTML = '<input type="text" name="EXIST_STORE_RANGE" id="EXIST_STORE_RANGE" json="EXIST_STORE_RANGE" style="align:center;"/><font color="red">*</font>';
											newTd5.innerHTML = '<input type="text" name="EXIST_STORE_COMPETITION" id="EXIST_STORE_COMPETITION" json="EXIST_STORE_COMPETITION" style="align:center;">';
											//添加进表格
											newTr.appendChild(newTd0);
											newTr.appendChild(newTd1);
											newTr.appendChild(newTd2);
											newTr.appendChild(newTd3);
											newTr.appendChild(newTd4);
											newTr.appendChild(newTd5);
											tab.appendChild(newTr);
						                 </script>
			                          </tbody>
							  </table>
							 </div>
						    </td>
						</tr>
 
					    <tr>
			              <td colspan="6" align="right"  class="detail_label2">
			                  <input onclick="addRowT()"  type="button" name="Submit2" value="添加" />
                              <input onclick="delRowT()"  type="button" name="Submit3" value="删除" />
			              </td>
						</tr>
					
						<tr>
						  <th colspan="6" align="center">
						    近期新开卖场信息调查
						  </th>
						</tr>
						
							<tr>
						   <td align="center" colspan="6"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable1"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="center">
						            <tr align="left" class="detail_label">
						              <th width="5%" align="center">
						                 <input type="checkbox" name="radioT" id="radioT" onclick="chkAllT()"/>
						              </th>
						              <th width="19%" align="center">
						                   名称
						              </th>
						              <th width="19%" align="center">
						                   地址
						              </th>
						              <th width="19%" align="center">
						                   面积(平)
						              </th>
						              <th width="19%" align="center">
						                   档次排名
						              </th>
						              <th width="19%" align="center">
						                   开业时间
						              </th>
						            </tr>
						            <tbody id="tbodyT" class="detail_content">
						            <script type="text/javascript">
						                    var tab = document.getElementById("tbodyT");
											var len = tab.rows.length+1;
											//添加行
											var newTr = document.createElement("tr");
											//添加列
											var newTd0 = document.createElement("td");
											var newTd1 = document.createElement("td");
											var newTd2 = document.createElement("td");
											var newTd3 = document.createElement("td");
											var newTd4 = document.createElement("td");
										    var newTd5 = document.createElement("td");
											var newTd6 = document.createElement("td");
											//设置单元格内容
											newTd0.innerHTML = '<input type=checkbox name="checkboxT" id="box" >';
											
											newTd1.innerHTML = '<input type="text" name="NEW_STORE_NAME"  id="NEW_STORE_NAME"  json="NEW_STORE_NAME"  style="align:center;"/>';
											newTd2.innerHTML = '<input type="text" name="NEW_STORE_ADDR"  id="NEW_STORE_ADDR"  json="NEW_STORE_ADDR"  style="align:center;"/>';
											newTd3.innerHTML = '<input type="text" name="NEW_STORE_AREA"  id="NEW_STORE_AREA"  json="NEW_STORE_AREA"  style="align:center;">';
											newTd4.innerHTML = '<input type="text" name="NEW_STORE_RANGE" id="NEW_STORE_RANGE" json="NEW_STORE_RANGE" style="align:center;">';
											for(var i=0;i<len;i++){
											    newTd5.innerHTML = '<input type="text" name="NEW_STORE_DATE"  id="NEW_STORE_DATE'+i+'"  json="NEW_STORE_DATE'+i+'"  style="align:center;" onclick="SelectDate();" onchange="CompareDate(NEW_STORE_DATE'+i+','+i+');" >' +
											                       '<img align="absmiddle" style="cursor: hand"src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(NEW_STORE_DATE'+i+');">';
											}
											//添加进表格
											newTr.appendChild(newTd0);
											newTr.appendChild(newTd1);
											newTr.appendChild(newTd2);
											newTr.appendChild(newTd3);
											newTr.appendChild(newTd4);
											newTr.appendChild(newTd5);
											tab.appendChild(newTr);
						                 </script>
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr>
					    <tr>
			              <td colspan="6" align="right" class="detail_label2">
			                  <input onclick="addRowTt()"  type="button" name="Submit2" value="添加" />
                              <input onclick="delRowTt()"  type="button" name="Submit3" value="删除" />
			              </td>
						</tr>
						<tr>
						  <th colspan="6" align="center">
						    意向加盟商拜访
						  </th>
						</tr>
						<tr>
						   <td align="center" colspan="6"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable2"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="center">
						            <tr align="left" class="detail_label">
						              <th width="5%" align="center">
						                 <input type="checkbox" name="radioTt" id="radioTt" onclick="chkAllTt()" />
						              </th>
						              <th width="19%" align="center">
						                   客户姓名
						              </th>
						              <th width="19%" align="center">
						                   经营品牌
						              </th>
						              <th width="19%" align="center">
						                   进驻卖场
						              </th>
						              <th width="19%" align="center">
						                   意向门店
						              </th>
						              <th width="19%" align="center">
						                   联系电话
						              </th>
						            </tr>
						            <tbody id="tbodyTt" class="detail_content">
						                <script type="text/javascript">
						                        var tab = document.getElementById("tbodyTt");
												var len = tab.rows.length+1;
												//添加行
												var newTr = document.createElement("tr");
												//添加列
												var newTd0 = document.createElement("td");
												var newTd1 = document.createElement("td");
												var newTd2 = document.createElement("td");
												var newTd3 = document.createElement("td");
												var newTd4 = document.createElement("td");
											    var newTd5 = document.createElement("td");
												var newTd6 = document.createElement("td");
												//设置单元格内容
												newTd0.innerHTML = '<input type=checkbox name="checkboxTt" id="box" >';
												newTd1.innerHTML = '<input type="text" name="CHANN_NAME"  id="CHANN_NAME'+len+'" json="CHANN_NAME" style="align:center;"/>';  
 											    newTd2.innerHTML = '<input type="text" name="BUSS_SCOPE"  id="BUSS_SCOPE'+len+'" json="BUSS_SCOPE'+len+'" style="align:center;" />';
												newTd3.innerHTML = '<input type="text" name="STORE_NAME"  id="STORE_NAME" json="STORE_NAME" style="align:center;">';
												newTd4.innerHTML = '<input type="text" name="PURPOSE_STORE_NAME" id="PURPOSE_STORE_NAME" json="PURPOSE_STORE_NAME" style="align:center;">';
												newTd5.innerHTML = '<input type="text" name="TEL"  id="TEL" json="TEL" style="align:center;">';
												//添加进表格
												newTr.appendChild(newTd0);
												newTr.appendChild(newTd1);
												newTr.appendChild(newTd2);
												newTr.appendChild(newTd3);
												newTr.appendChild(newTd4);
												newTr.appendChild(newTd5);
												tab.appendChild(newTr);
						                 </script>
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr> 
						
						  <tr>
							<td width="15%" align="right" class="detail_label">
								加盟商异议：
							</td>
							<td width="20%" class="detail_content" >
								 <textarea json="CHANN_REMARK" name="CHANN_REMARK" id="CHANN_REMARK" label="加盟商异议" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="250">${rst.CHANN_REMARK}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								解决方案：
							</td>
							<td width="20%" class="detail_content" >
								 <textarea json="SOLUTION" name="SOLUTION" id="SOLUTION" label="解决方案" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="250">${rst.SOLUTION}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="SUPPORT_DEMAND" name="SUPPORT_DEMAND" id="SUPPORT_DEMAND" label="支持需求" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="250">${rst.SUPPORT_DEMAND}</textarea>
							</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								 <textarea json="COMPETITION_INFO" name="COMPETITION_INFO" id="COMPETITION_INFO" label="竞品信息" 
									                  rows="3" cols="24%" autocheck="true" inputtype="string"   maxlength="250">${rst.COMPETITION_INFO}</textarea>
							</td>
							<td width="15%" align="right" class="detail_label">
								现状及优势：
							</td>
							<td width="20%" class="detail_content" colspan="3">
								 <textarea json="ADVANTAGES" name="ADVANTAGES" id="ADVANTAGES" label="现状及优势"
								           rows="3" cols="24%" autocheck="true" inputtype="string" size="30" maxlength="250">${rst.ADVANTAGES}</textarea>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">相关附件：</td>
							<td width="20%" class="detail_content" colspan="5"><input id="ATT_PATH" json="ATT_PATH" name="ATT_PATH"  type="hidden"   value="${rst.ATT_PATH}"/> 
							</td> 
						</tr>
					</table>
				</td>
			</tr>
						
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
       SelDictShow("VISIT_TYPE","BS_154","${rst.VISIT_TYPE}","");
	   uploadFile('ATT_PATH', 'SAMPLE_DIR', true,true,true);
	</script>
   </div>
</body>



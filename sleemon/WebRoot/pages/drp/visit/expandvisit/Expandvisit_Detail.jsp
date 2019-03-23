<!-- 
 *@module 渠道管理-拜访管理
 *@func   拓展拜访表维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	    <script type="text/javascript">
             function  init(){
                var existName = "${rst.EXIST_STORE_NAME}";
                var existAddr = "${rst.EXIST_STORE_ADDR}";
                var existArea = "${rst.EXIST_STORE_AREA}";
                var existRange= "${rst.EXIST_STORE_RANGE}";
                var existComp = "${rst.EXIST_STORE_COMPETITION}";
                
                
                var existNames  = existName.split("|");
                var existAddrs  = existAddr.split("|");
                var existAreas  = existArea.split("|");
                var existRanges = existRange.split("|");
                var existComps  = existComp.split("|");
                 
                var tab = document.getElementById("tbody");
                for(var i=0;i<existNames.length;i++){
					//添加行
					var newTr = document.createElement("tr");
					//添加列
					var newTd0 = document.createElement("td");
					var newTd1 = document.createElement("td");
					var newTd2 = document.createElement("td");
					var newTd3 = document.createElement("td");
				    var newTd4 = document.createElement("td");
					var newTd5 = document.createElement("td");
					//设置单元格内容
					newTd0.innerHTML = existNames[i];
					newTd1.innerHTML = existAddrs[i];
					newTd2.innerHTML = existAreas[i];
					newTd3.innerHTML = existRanges[i];
					newTd4.innerHTML = existComps[i];
					//添加进表格
					newTr.appendChild(newTd0);
					newTr.appendChild(newTd1);
					newTr.appendChild(newTd2);
					newTr.appendChild(newTd3);
					newTr.appendChild(newTd4);
					tab.appendChild(newTr);
				}
				
				
				var newName  = "${rst.NEW_STORE_NAME}";
                var newAddr  = "${rst.NEW_STORE_ADDR}";
                var newArea  = "${rst.NEW_STORE_AREA}";
                var newRange = "${rst.NEW_STORE_RANGE}";
                var newDates = "${rst.NEW_STORE_DATE}";
                
                var newNames  = newName.split("|");
                var newAddrs  = newAddr.split("|");
                var newAreas  = newArea.split("|");
                var newRanges = newRange.split("|");
                var newDates = newDates.split("|");
                 
                var tab = document.getElementById("tbodyT");
                for(var i=0;i<newNames.length;i++){
					//添加行
					var newTr = document.createElement("tr");
					//添加列
					var newTd0 = document.createElement("td");
					var newTd1 = document.createElement("td");
					var newTd2 = document.createElement("td");
					var newTd3 = document.createElement("td");
				    var newTd4 = document.createElement("td");
					var newTd5 = document.createElement("td");
					//设置单元格内容
					newTd0.innerHTML = newNames[i];
					newTd1.innerHTML = newAddrs[i];
					newTd2.innerHTML = newAreas[i];
					newTd3.innerHTML = newRanges[i];
					newTd4.innerHTML = newDates[i];
					//添加进表格
					newTr.appendChild(newTd0);
					newTr.appendChild(newTd1);
					newTr.appendChild(newTd2);
					newTr.appendChild(newTd3);
					newTr.appendChild(newTd4);
					tab.appendChild(newTr);
				}
				var channName   = "${rst.CHANN_NAME}";
                var bussScope   = "${rst.BUSS_SCOPE}";
                var storeName   = "${rst.STORE_NAME}";
                var purposeName = "${rst.PURPOSE_STORE_NAME}";
                var tel         = "${rst.TEL}";
                
                var channNames   = channName.split("|");
                var bussScopes   = bussScope.split("|");
                var storeNames   = storeName.split("|");
                var purposeNames = purposeName.split("|");
                var tels         = tel.split("|");
                 
                var tab = document.getElementById("tbodyTt");
                for(var i=0;i<channNames.length;i++){
					//添加行
					var newTr = document.createElement("tr");
					//添加列
					var newTd0 = document.createElement("td");
					var newTd1 = document.createElement("td");
					var newTd2 = document.createElement("td");
					var newTd3 = document.createElement("td");
				    var newTd4 = document.createElement("td");
					var newTd5 = document.createElement("td");
					//设置单元格内容
					newTd0.innerHTML = channNames[i];
					newTd1.innerHTML = bussScopes[i];
					newTd2.innerHTML = storeNames[i];
					newTd3.innerHTML = purposeNames[i];
					newTd4.innerHTML = tels[i];
					//添加进表格
					newTr.appendChild(newTd0);
					newTr.appendChild(newTd1);
					newTr.appendChild(newTd2);
					newTr.appendChild(newTd3);
					newTr.appendChild(newTd4);
					tab.appendChild(newTr);
				}
             }
        </script>
	    
		<title>拓展拜访表详情</title>
	</head>
	<body class="bodycss1" onload="init()">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3">
						 	<tr>
							<td width="15%" align="right" class="detail_label">
								流程编号：
							</td>
							<td width="20%" class="detail_content">
							     ${rst.EXPAND_VISIT_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								紧急程度：
							</td>
							<td width="20%" class="detail_content" >
							      ${rst.EME_DEGREE} 
							</td>
							<td width="15%" align="right" class="detail_label">
								拜访人：
							</td>
							<td width="20%" class="detail_content">
								${rst.VISIT_PEOPLE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								申请部门：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.APPLY_DEP}
							</td>
							<td width="15%" align="right" class="detail_label">
								申请日期：
							</td>
							<td width="20%" class="detail_content">
							    ${rst.APPLY_DATE}
							</td>
							<td width="15%" align="right" class="detail_label">
								拜访时间：
							</td>
							<td width="20%" class="detail_content">
							     ${rst.VISIT_DATE}
							</td>
						</tr> 
						<tr>
							<td width="15%" align="right" class="detail_label">
								拜访方式：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							     ${rst.VISIT_TYPE}
							</td>
						</tr> 
						
						<tr>
						  <th colspan="6" align="center">
						    现有卖场信息调查
						  </th>
						</tr>
						
						<tr>
						   <td align="center" colspan="6"> 
						     <div id="tabdiv" class="meun">
						       <table id="myTable"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="center">
						             <tr class="detail_label">
						              <th width="19%" align="center">
						                   名称
						              </th>
						              <th width="19%" align="center">
						                   地址
						              </th>
						              <th width="19%" align="center">
						                   面积
						              </th>
						              <th width="19%" align="center">
						                   档次排名
						              </th>
						              <th width="19%" align="center">
						                   进驻主竞品
						              </th>
						            </tr>
						            <tbody id="tbody" style="text-align: center;" class="detail_content">
			                        </tbody>
							  </table>
							 </div>
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
						              <th width="19%" align="center">
						                   名称
						              </th>
						              <th width="19%" align="center">
						                   地址
						              </th>
						              <th width="19%" align="center">
						                   面积
						              </th>
						              <th width="19%" align="center">
						                   档次排名
						              </th>
						              <th width="19%" align="center">
						                   开始时间
						              </th>
						            </tr>
						            <tbody id="tbodyT" style="text-align: center;" class="detail_content">
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr>
						<tr>
						  <th colspan="6" align="center">
						    目标加盟商拜访
						  </th>
						</tr>
						<tr>
						   <td align="center" colspan="6"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable2"  width="100%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="center">
						            <tr align="left" class="detail_label">
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
						            <tbody id="tbodyTt" style="text-align: center;" class="detail_content">
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
								 ${rst.CHANN_REMARK}
							</td>
							<td width="15%" align="right" class="detail_label">
								解决方案：
							</td>
							<td width="20%" class="detail_content">
								 ${rst.SOLUTION}
							</td>
							<td width="15%" align="right" class="detail_label">
								支持需求：
							</td>
							<td width="20%" class="detail_content">
								${rst.SUPPORT_DEMAND}
							</td>
						</tr> 
						<tr>
						    <td width="15%" align="right" class="detail_label">
								竞品信息：
							</td>
							<td width="20%" class="detail_content">
								${rst.COMPETITION_INFO}
							</td>
							<td width="15%" align="right" class="detail_label">现状及优势：</td>
							<td width="20%" class="detail_content" colspan="3">
			                    ${rst.ADVANTAGES} 
							</td> 
						</tr> 
						<tr>
						   <td width="15%" align="right" class="detail_label">相关附件：</td>
							<td width="20%" class="detail_content" colspan="5">
			                      <input id="ATT_PATH" json="ATT_PATH" name="ATT_PATH"  type="hidden"   value="${rst.ATT_PATH}"/> 
							</td> 
						</tr>
						 <tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="8" class="detail2">
	                     <table id="ordertb" width="99.98%" border="0" cellpadding="1" cellspacing="1" class="lst" >
							<tr  >
								<th nowrap align="center">序号</th>
								<th nowrap align="center" >操作者</th>
								<th nowrap align="center" >操作</th>
								<th nowrap align="center" >时间</th>
								<th nowrap align="center" >意见</th>
								<th nowrap align="center" >下一操作者</th>
							</tr>
							<c:forEach items="${flowInfoList}" var="flow" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
								<td nowrap align="center" width="20%">${rr}&nbsp;</td>
								<td nowrap align="center" width="20%">${flow.OPERATORNAME}&nbsp;</td>
								<td nowrap align="center" width="20%">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center" width="20%">${flow.OPERATETIME}&nbsp;</td>
								<td nowrap align="center" width="400px;" style="table-layout:fixed;word-break:break-all; word-wrap:break-word;" >${flow.REMARK}&nbsp;</td>
								<td nowrap align="center" width="20%">${flow.NEXTOPERATORNAME}&nbsp;</td>
							</tr>
							</c:forEach>
							<c:if test="${empty flowInfoList}">
							<tr>
								<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;草拟，尚未进入审核流程!&nbsp;</td>
							</tr>
							</c:if>
						</table>
	                   </td>
	                 </tr>
					</table>
				</td>
			</tr>
					</table>
				</td>
			</tr>
		</table>
	<script type="text/javascript">
       SelDictShow("LOCAL_TYPE","BS_86","${params.LOCAL_TYPE}","");
       SelDictShow("LEDGER_ID1 ","BS_87","${params.LEDGER_ID1}","");
       SelDictShow("BUSS_SCOPE ","BS_88","${params.BUSS_SCOPE}","");
       SelDictShow("TERM_WHICH_NUM ","BS_89","${params.TERM_WHICH_NUM}","");
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
       displayDownFile('ATT_PATH',str,false,false);
   </script>
	</body>
</html>

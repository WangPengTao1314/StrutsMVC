<!-- 
 *@module 渠道管理-意向客户拜访
 *@func    
 *@version 1.1
 *@author 
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
            	var sp = "@@";
                var existName = "${rst.EXIST_STORE_NAME}";
                var existAddr = "${rst.EXIST_STORE_ADDR}";
                var existArea = "${rst.EXIST_STORE_AREA}";
                var existRange= "${rst.EXIST_STORE_RANGE}";
                var existComp = "${rst.EXIST_STORE_COMPETITION}";
                
                
                var existNames  = existName.split(sp);
                var existAddrs  = existAddr.split(sp);
                var existAreas  = existArea.split(sp);
                var existRanges = existRange.split(sp);
                var existComps  = existComp.split(sp);
                 
                var tab = document.getElementById("tbody");
                for(var i=0;i<existNames.length;i++){
					//添加行
					var newTr = document.createElement("tr");
					newTr.className = "detail_label";
					//添加列
					var newTd0 = document.createElement("td");
					var newTd1 = document.createElement("td");
					var newTd2 = document.createElement("td");
					var newTd3 = document.createElement("td");
				    var newTd4 = document.createElement("td");
					var newTd5 = document.createElement("td");
					newTd0.className = "detail_content";
					newTd1.className = "detail_content";
					newTd2.className = "detail_content";
					newTd3.className = "detail_content";
					newTd4.className = "detail_content";
					newTd5.className = "detail_content";
					
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
                
                var newNames  = newName.split(sp);
                var newAddrs  = newAddr.split(sp);
                var newAreas  = newArea.split(sp);
                var newRanges = newRange.split(sp);
                var newDates = newDates.split(sp);
                 
                var tab = document.getElementById("tbody1");
                for(var i=0;i<newNames.length;i++){
					//添加行
					var newTr = document.createElement("tr");
					newTr.className = "detail_label";
					//添加列
					var newTd0 = document.createElement("td");
					var newTd1 = document.createElement("td");
					var newTd2 = document.createElement("td");
					var newTd3 = document.createElement("td");
				    var newTd4 = document.createElement("td");
					var newTd5 = document.createElement("td");
					
					newTd0.className = "detail_content";
					newTd1.className = "detail_content";
					newTd2.className = "detail_content";
					newTd3.className = "detail_content";
					newTd4.className = "detail_content";
					newTd5.className = "detail_content";
					
					
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
				var VISIT_TYPE = "${rst.VISIT_TYPE}";
                var VISIT_REMARK = "${rst.VISIT_REMARK}";
                var VISIT_TYPES = VISIT_TYPE.split(sp);
                var VISIT_REMARKS = VISIT_REMARK.split(sp);
                
                 
                var tab = document.getElementById("tbody2");
                for(var i=0;i<VISIT_TYPES.length;i++){
                	var cs = convertToChinese(i+1);
					//添加行
					var newTr = document.createElement("tr");
					newTr.className = "detail_label";
					//添加列
					var newTd0 = document.createElement("td");
					var newTd1 = document.createElement("td");
					var newTd2 = document.createElement("td");
					var newTd3 = document.createElement("td");
				    var newTd4 = document.createElement("td");
				    var newTd5 = document.createElement("td");
				    
				    newTd0.style.width="15%";
				    newTd1.style.width="20%";
				    newTd2.style.width="20%";
				    newTd3.style.width="45%";
				    
					newTd1.className = "detail_content";
					newTd3.className = "detail_content";
					newTd4.className = "detail_content";
					newTd5.className = "detail_content";
					
				    newTd0.setAttribute("align","center");  
				    newTd2.setAttribute("align","center");  
					//设置单元格内容
					newTd0.innerHTML = "第"+cs+"次拜访方式";
					newTd1.innerHTML = VISIT_TYPES[i];
					newTd2.innerHTML = "第"+cs+"次拜访/跟进情况说明";
					newTd3.innerHTML = VISIT_REMARKS[i];
				 
					//添加进表格
					newTr.appendChild(newTd0);
					newTr.appendChild(newTd1);
					newTr.appendChild(newTd2);
					newTr.appendChild(newTd3);
					tab.appendChild(newTr);
				}
             }
        </script>
	    
		<title>拓展拜访表详情</title>
	</head>
	<body class="bodycss1" onload="init();">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" style="margin-top: -20px;">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
						 	<tr>
							<td width="15%" align="right" class="detail_label"> 单据编号： </td>
							<td width="20%" class="detail_content" >  ${rst.INTE_CHANN_NO} </td>
							<td width="15%" align="right" class="detail_label"> 申请人： </td>
							<td width="20%" class="detail_content"> ${rst.APPLY_PERSON_NAME} </td>
							<td width="15%" align="right" class="detail_label"> 申请部门： </td>
							<td width="20%" class="detail_content"> ${rst.APPLY_DEP} </td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 申请日期： </td>
							<td width="20%" class="detail_content">  ${rst.APPLY_DATE} </td>
							<td width="15%" align="right" class="detail_label"> 城市名称： </td>
							<td width="20%" class="detail_content"> ${rst.CITY} </td>
							<td width="15%" align="right" class="detail_label"> 区号： </td>
							<td width="20%" class="detail_content"> ${rst.AREA_CODE} </td>
						</tr> 
						<tr>
						   <td width="10%" align="right" class="detail_label" > 城市类型：  </td>
						   <td width="10%" class="detail_content" > ${rst.CITY_TYPE}</td>
						   <td width="10%" align="right" class="detail_label"> 城市级别：</td>
						   <td width="10%" class="detail_content" > ${rst.CITY_LEVEL}</td>
						   <td width="10%" align="right" class="detail_label"> </td>
						   <td width="10%" class="detail_content" > </td>
						</tr>
						<%--<tr>
							<td width="15%" align="right" class="detail_label"> 城市名称： </td>
							<td width="20%" class="detail_content"> ${rst.CITY} </td>
							<td width="15%" align="right" class="detail_label"> 区号： </td>
							<td width="20%" class="detail_content"> ${rst.AREA_CODE} </td>
							<td colspan="2" class="detail_content">
							 <table border="0" cellpadding="1" cellspacing="0" class="detail_lst"  >
							   <td width="10%" align="right" class="detail_label" style="border-right: 1px solid gray;"> 城市类型：  </td>
							   <td width="10%" class="detail_content" style="border-right: 1px solid gray;"> ${rst.CITY_TYPE}</td>
							   <td width="10%" align="right" class="detail_label" style="border-right: 1px solid gray;"> 城市级别：</td>
							   <td width="10%" class="detail_content" > ${rst.CITY_LEVEL}</td>
							 </table>
							</td> 
						</tr> 
						--%><tr>
							<td width="15%" align="right" class="detail_label">  意向加盟商开户名称： </td>
							<td width="20%" class="detail_content">${rst.INTE_CHANN_NAME}</td>
							<td width="15%" align="right" class="detail_label"> 意向加盟商姓名： </td>
							<td width="20%" class="detail_content">${rst.INTE_CUSTOMER_NAME}</td>
							<td width="15%" align="right" class="detail_label"> 联系电话： </td>
							<td width="20%" class="detail_content" > ${rst.TEL}</td>
					    </tr> 
					    <tr>
							<td width="15%" align="right" class="detail_label"> 性别：</td>
							<td width="20%" class="detail_content">${rst.SEX}</td>
							<td width="15%" align="right" class="detail_label"> 年龄： </td>
							<td width="20%" class="detail_content">${rst.AGE}</td>
							<td width="15%" align="right" class="detail_label"> 学历： </td>
							<td width="20%" class="detail_content" >${rst.EDUCATION}</td>
					    </tr> 
					    <tr>
							<td width="15%" align="right" class="detail_label"> 地址：</td>
							<td width="20%" class="detail_content" colspan="5"> ${rst.ADDRESS}</td>
					    </tr> 	
					    
						
						<tr>
						  <th colspan="6" align="center" class="detail_label" style="font-size: 18"> 现有卖场信息调查 </th>
						</tr>
						
						<tr>
						   <td align="center" colspan="6" class="detail2"> 
						     <div id="tabdiv" class="meun">
						       <table id="myTable"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst" align="center">
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
						  <th colspan="6" align="center" class="detail_label" style="font-size: 18">
						    近期新开卖场信息调查
						  </th>
						</tr>
						<tr>
						   <td align="center" colspan="6" class="detail2"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable1"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst" align="center">
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
						            <tbody id="tbody1" style="text-align: center;" class="detail_content">
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr>
						 
						<tr>
							<td width="15%" align="right" class="detail_label"> 发货工厂：</td>
							<td width="20%" class="detail_content">${rst.SHIP_POINT_NAME}</td>
							<td width="20%" align="right" class="detail_label"> 是否区域服务中心下辖加盟商： </td>
							<td width="20%" class="detail_content">
							  <c:if test="${rst.AREA_SER_FLG eq 1}">是</c:if>
							  <c:if test="${rst.AREA_SER_FLG ne 1}">否</c:if>
							</td>
							<td width="15%" align="right" class="detail_label"> 区域服务中心： </td>
							<td width="20%" class="detail_content" >${rst.AREA_SER_NAME}</td>
					    </tr> 
					    
					 	<tr>
							<td width="15%" align="right" class="detail_label"> 相关附件（与意向客户合影，客户名片，商场等）：</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input json="DOC_PATH" name="DOC_PATH" id="DOC_PATH" type="hidden"   label="相关附件（与意向客户合影，客户名片，商场等）" value="${rst.DOC_PATH}" />
							</td>
					    </tr>    
						<tr>
						  <th colspan="6" align="center" class="detail_label" style="font-size: 18">   第一次拜访 </th>
						</tr>
						
						<tr>
						   <td align="center" colspan="6" class="detail2"> 
						    <div id="tabdiv" class="meun">
						      <table id="myTable2"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst" align="center">
						            <tbody id="tbody2" class="detail_content">
			                        </tbody>
							  </table>
							 </div>
						    </td>
						</tr> 
						
						<tr>
						  <td width="15%" align="right" class="detail_label"> 意向经营品牌： </td>
						  <td width="20%" class="detail_content" >${rst.BUSS_SCOPE}</td>
						  <td width="15%" align="right" class="detail_label"> 意向进驻卖场： </td>
						  <td width="20%" class="detail_content" > ${rst.STORE_NAME} </td>
						  <td width="15%" align="right" class="detail_label"> 计划开店版本： </td>
						  <td width="20%" class="detail_content" > ${rst.INTE_STORE_VSION}</td>
						</tr> 
						<tr>
						  <td width="15%" align="right" class="detail_label"> 计划开店面积： </td>
						  <td width="20%" class="detail_content" > ${rst.INTE_STORE_AREA} </td>
						  <td width="15%" align="right" class="detail_label"> 计划开店时间： </td>
						  <td width="20%" class="detail_content" > ${rst.INTE_STORE_DATE} </td>
						  <td width="15%" align="right" class="detail_label">  </td>
						  <td width="20%" class="detail_content" ></td>
						 
						</tr>
						<tr>
						  <td width="15%" align="right" class="detail_label"> 是否签署合同： </td>
						  <td width="20%" class="detail_content" >
						     <c:if test="${1 eq rst.IS_CONTRACT}">是</c:if>
						     <c:if test="${1 ne rst.IS_CONTRACT}">否</c:if>
						  </td>
						  <td width="15%" align="right" class="detail_label"> 是否缴纳意向金： </td>
						  <td width="20%" class="detail_content" >
						    <c:if test="${1 eq rst.IS_PAY_INTEAMOUNT}">是</c:if>
						    <c:if test="${1 ne rst.IS_PAY_INTEAMOUNT}">否</c:if>
						  </td>
						  <td width="15%" align="right" class="detail_label"> 是否已首次发货： </td>
						  <td width="20%" class="detail_content" >
						    <c:if test="${1 eq rst.IS_FIRST_SENTPDT}">是</c:if>
						    <c:if test="${1 ne rst.IS_FIRST_SENTPDT}">否</c:if>
						  </td>
						  
						</tr>
						
					    <tr>
							<td width="15%" align="right" class="detail_label"> 加盟商异议： </td>
							<td width="20%" class="detail_content" >${rst.CHANN_REMARK}</td>
							<td width="15%" align="right" class="detail_label"> 	解决方案： </td>
							<td width="20%" class="detail_content" >${rst.SOLUTION}</td>
							<td width="15%" align="right" class="detail_label"> 支持需求： 	</td>
							<td width="20%" class="detail_content">${rst.SUPPORT_DEMAND}</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label"> 竞品信息： </td>
							<td width="20%" class="detail_content">${rst.COMPETITION_INFO}</td>
							<td width="15%" align="right" class="detail_label"> 现状及优势： 	</td>
							<td width="20%" class="detail_content" colspan="3">${rst.ADVANTAGES}</td>
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
       displayDownFile('DOC_PATH','SAMPLE_DIR',false,false);
   </script>
	</body>
</html>

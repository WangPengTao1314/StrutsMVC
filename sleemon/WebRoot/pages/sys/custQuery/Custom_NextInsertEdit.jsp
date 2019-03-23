<%-- 
  *@module 系统管理
  *@func 自定义查询
  *@version 1.1
  *@author zhuxw
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">  
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/pages/sys/custQuery/Custom_NextInsertEdit.js"></script> 
		<title>自定义设置最后一步</title>
	</head>
	<body>
		<table width="100%" height="20" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="20">
					<table width="100%" cellspacing="0" cellpadding="0" class="wz">
					<tr>
						<td width="28" align="center"><label class="wz_img"></label></td>
						<td>当前位置：系统管理&gt;&gt;查询管理&gt;&gt;自定义报表字段设置</td>
						<td width="50" align="right"></td>
					</tr>
				  </table>  
				</td>
			</tr>
		</table> 
		<form name="qryForm" action="#" method="post" id = "qryForm">
		<input type="hidden" name = "hidRptPk" id = "hidRptPk" value="${hidRptPk}"/>
		<input type="hidden" name = "messageInfo" id = "messageInfo" value="${messageInfo}"/>
		<input type="hidden" name = "rptSql" id = "rptSql" value="${rptSql}"/>
		<table width="100%" height="75%" border="0" cellpadding="4" cellspacing="4" class="detail" id = "jsontb" name = "jsontb">
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow"> 
								<th nowrap align="center" dbname="RPT_CODE" width="5%">选择</th>
								<th nowrap align="center" dbname="RPT_CODE" width="10%">物理名称</th>
								<th nowrap align="center" dbname="RPT_NAME" width="10%">逻辑名称</th>
								<th nowrap align="center" width="10%">列表显示</th>	
								<th nowrap align="center" width="10%">表头显示</th>		
								<th nowrap align="center" dbname="CRENAME" width="10%" >字段类型</th>
								<th nowrap align="center" dbname="CRETIME" width="10%">查询条件</th>
								<th nowrap align="center" dbname="RPT_SQL" width="10%">必输</th>
								<th nowrap align="center" dbname="REMARK" width="10%">匹配模式</th>
								<th nowrap align="center" dbname="STATE" width="10%">组件名称</th>		
								<th nowrap align="center" dbname="STATUS" width="10%">组件条件</th>
								<th nowrap align="center" width="5%" >排序号</th>																														
							</tr>
							<c:forEach items="${page}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="setSelEid(document.getElementById('eid${rr}'));">  
									<td nowrap align="center" width="5%">
										<input name="eid${rr}" type="checkbox" />
										<input type="hidden" json="rptColPk" name = "rptColPk" id = "rptColPk" value = "${rst.rptColPk}"/>
									</td>
									<td nowrap align="center" width="10%">
										<input type="hidden" json="physicName" name = "physicName" id = "physicName" value = "${rst.physicName}"/>
										${rst.physicName}&nbsp;
									</td>
									<td nowrap align="center" width="10%">
										<input type="text" name = "logicName" autocheck="true" label="逻辑名称" inputtype="string" mustinput="true" maxlength="100" id = "logicName" json="logicName" value = "${rst.logicName}"/> 
									</td>
									<td nowrap align="center" width="10%">
										<select id="headDisplay" style="width: 70px" name="headDisplay" json = "headDisplay">
											<option value ="1" <c:if test="${rst.headDisplay==1}">selected="selected"</c:if>>是</option>
			 								<option value ="0" <c:if test="${rst.headDisplay==0}">selected="selected"</c:if>>否</option>  
										</select>    
									</td>	
									<td nowrap align="center" width="10%">
										<select id="display" style="width: 70px" name="display" json = "display">
			 								<option value ="0" <c:if test="${rst.display==0}">selected="selected"</c:if>>否</option>  
											<option value ="1" <c:if test="${rst.display==1}">selected="selected"</c:if>>是</option>
										</select>    
									</td>	
									<td nowrap align="center" width="10%">
										<select id="colType" style="width: 70px" name="colType" json = "colType"> 							 
			 								 <option value ="0" <c:if test="${rst.colType==0}">selected="selected"</c:if>>字符串</option>
			 								 <option value ="1" <c:if test="${rst.colType==1}">selected="selected"</c:if>>数字</option> 
			 								 <option value ="2" <c:if test="${rst.colType==2}">selected="selected"</c:if>>日期</option> 
										</select>
									</td>
									<td nowrap align="center" width="10%">
										<select id="isCondition" style="width: 70px" name="isCondition" json = "isCondition">
			 								 <option value ="0" <c:if test="${rst.isCondition==0}">selected="selected"</c:if>>否</option>  
											 <option value ="1" <c:if test="${rst.isCondition==1}">selected="selected"</c:if>>是</option>
										</select> 
									</td> 	
									<td nowrap align="center" width="10%">
										<select id="mustInput" style="width: 70px" name="mustInput" json = "mustInput">
			 								<option value ="0" <c:if test="${rst.mustInput==0}">selected="selected"</c:if>>否</option>  
											<option value ="1" <c:if test="${rst.mustInput==1}">selected="selected"</c:if>>是</option>
										</select>  
									</td>
									<td nowrap align="center" width="10%">
										<select id="matchModel" style="width: 70px" name="matchModel" json = "matchModel">
											<option value ="0" <c:if test="${rst.matchModel==0}">selected="selected"</c:if>>等于</option>
			 								<option value ="1" <c:if test="${rst.matchModel==1}">selected="selected"</c:if>>左匹配</option> 
			 								<option value ="2" <c:if test="${rst.matchModel==2}">selected="selected"</c:if>>右匹配</option>
			 								<option value ="3" <c:if test="${rst.matchModel==3}">selected="selected"</c:if>>模糊匹配</option>  
										</select>   
									</td>
									<td nowrap align="center" width="10%">
										<select id="compoentType" style="width: 70px" name="compoentType" json = "compoentType">
			 								<option value ="7" <c:if test="${rst.compoentType==7}">selected="selected"</c:if>>文本框</option> 
											<option value ="0" <c:if test="${rst.compoentType==0}">selected="selected"</c:if>>日期</option>
			 								<option value ="1" <c:if test="${rst.compoentType==1}">selected="selected"</c:if>>机构选取</option> 
			 								<option value ="2" <c:if test="${rst.compoentType==2}">selected="selected"</c:if>>库房选取</option>
			 								<option value ="3" <c:if test="${rst.compoentType==3}">selected="selected"</c:if>>数据字典</option> 
			 								<option value ="4" <c:if test="${rst.compoentType==4}">selected="selected"</c:if>>客户选取</option> 
			 								<option value ="5" <c:if test="${rst.compoentType==5}">selected="selected"</c:if>>款号选取</option> 
			 								<option value ="6" <c:if test="${rst.compoentType==6}">selected="selected"</c:if>>款色号选取</option> 
										</select>    
									</td>
									<td nowrap align="center" width="10%">
										<input type="text" name = "componentCondition" id = "componentCondition" json="componentCondition" value = "${rst.componentCondition}"/>
									</td>
									<td nowrap align="center" width="5%">
										<input type="text" name = "colIndex" size = "6" id = "colIndex" autocheck="true" label="排序号" inputtype="number" mustinput="true" maxlength="2" json="colIndex" value = "${rst.colIndex}"/> 
									</td>		
								</tr>
							</c:forEach>
							<c:if test="${empty page}">
								<tr>
									<td height="25" colspan="13" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</td>
			</tr> 
		</table>
		</form>
		<br>
		<div class="tablayer" align="center">
	      	<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
			   		<td align="center" nowrap>
			   			<input id="previous" type="button" class="btn" value="上一步(P)" title="Alt+P" accesskey="P">
			   			<input id="commit_b" type="submit" class="btn" value="完成(R)" title="Alt+R" accesskey="R">  
					</td>
				</tr>
			</table>
		</div>
	</body>
	<script type="text/javascript">
		var messageInfo =$("#messageInfo").val();
		if(messageInfo!=''){
			if(messageInfo =="success"){
				showMsgPanel("保存成功");
			}else{
				showMsgPanel("保存失败");
			}
		}
	</script>
</html>

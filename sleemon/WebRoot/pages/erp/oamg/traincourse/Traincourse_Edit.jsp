
<!--  
 * @module 渠道培训管理
 * @func 培训课程维护
 * @version 1.1
 * @time   2014-07-04 
 * @author ghx
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<script type="text/javascript" src="${ctx}/pages/erp/oamg/traincourse/Traincourse_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>培训课程维护编辑</title>
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
								accesskey="B" onclick='parent.$("#label_1").click();'>
						</td>
					</tr>
				</table>
			</div>


			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<!--占位用行，以免显示数据被浮动层挡住-->
					<td height="20px">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<form name="form" id="mainForm">
							<input type="hidden" name="selectParams" value=" CHANN_ID in ${QUERY_CHANN_ID} and STATE in( '启用') and DEL_FLAG='0' ">
							<input json="TRAIN_COURSE_ID" name="TRAIN_COURSE_ID"
									id="TRAIN_COURSE_ID" type="hidden" seltarget="selLL"
									label="培训课程表ID" size="40" value="${rst.TRAIN_COURSE_ID}" />
							<table id="jsontb" width="100%" border="0" cellpadding="4"
								cellspacing="4" class="detail">
								<tr>
									<td class="detail2">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="detail3">
									
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													培训课程编号：
												</td>
												<td width="35%" class="detail_content">
													<input json="TRAIN_COURSE_NO" name="TRAIN_COURSE_NO" autocheck="true" label="培训课程编号" mustinput="true"  type="text"  inputtype="string"  
							                        readonly    maxlength="30" size="40"
							                        <c:if test="${rst.TRAIN_COURSE_NO==null}"> value="自动生成"</c:if>
							                     	<c:if test="${rst.TRAIN_COURSE_NO!=null}">value="${rst.TRAIN_COURSE_NO}"</c:if>
							                        />
													
												</td>
												<td width="15%" align="right" class="detail_label">
													培训课程名称：
												</td>
												<td width="35%" class="detail_content">
													<input id="TRAIN_COURSE_NAME" json="TRAIN_COURSE_NAME" name="TRAIN_COURSE_NAME"
														type="text" autocheck="true" label="培训课程名称"
														inputtype="string" mustinput="true" maxlength="100"  
														value="${rst.TRAIN_COURSE_NAME}" size="40" />
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													培训类型：
												</td>
												<td width="35%" class="detail_content">													
													<select json="TRAIN_TYPE" id="TRAIN_TYPE" name="TRAIN_TYPE" mustinput="true" autocheck="true" label="培训类型" style="width: 273px;" ></select>																									
												</td>
												<td width="15%" align="right" class="detail_label">
													额度人数：
												</td>
												<td width="35%" class="detail_content">													
													<input json="FIXED_PSON_NUM" name="FIXED_PSON_NUM" autocheck="true" inputtype="int" label="额度人数"  type="text"  value="${rst.FIXED_PSON_NUM}" size="40" />
												</td>
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													培训时间从：
												</td>
												<td width="35%" class="detail_content">													
													<input id="TRAIN_TIME_BEG" json="TRAIN_TIME_BEG" name="TRAIN_TIME_BEG"
														type="text" autocheck="true" label="培训时间从"
														inputtype="string" mustinput="true" maxlength="100" readonly onclick="SelectTime1();"
														value="${rst.TRAIN_TIME_BEG}" size="40"/>
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime1(TRAIN_TIME_BEG);"  >													
												</td>
												<td width="15%" align="right" class="detail_label">
													培训时间到：
												</td>
												<td width="35%" class="detail_content">																										
													<input id="TRAIN_TIME_END" json="TRAIN_TIME_END" name="TRAIN_TIME_END"
														type="text" autocheck="true" label="培训时间到"
														inputtype="string" mustinput="true" maxlength="100" readonly onclick="SelectTime1();"
														value="${rst.TRAIN_TIME_END}" size="40"/>
													<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime1(TRAIN_TIME_END);"  >
												</td>
											</tr>
											<!--  
											<tr>																							
												<td width="15%" align="right" class="detail_label">
													状态：
												</td>
												<td width="35%" class="detail_content">
													<input id="STATE" json="STATE" name="STATE"
														type="text" label="状态" readonly 														 
														value="${rst.STATE}" size="40" />
												</td>												
												<td width="15%" align="right" class="detail_label" nowrap>
													&nbsp;
												</td>
												<td width="35%" class="detail_content" >
													&nbsp;
												</td>
											</tr>
											-->
											<tr>	
												<td width="15%" align="right" class="detail_label" nowrap>
													   培训地点：
												</td>
												<td width="35%" class="detail_content" colspan="3">													
													<input id="TRAIN_ADDR" json="TRAIN_ADDR" name="TRAIN_ADDR"
														type="text" autocheck="true" label="培训地点"
														inputtype="string" mustinput="true" maxlength="250"  
														value="${rst.TRAIN_ADDR}" size="40"/>
												</td>										
												
											</tr>											
																						
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  培训目的：
												</td>
												<td width="35%" class="detail_content" >
													<textarea  json="TRAIN_GOAL" name="TRAIN_GOAL" id="TRAIN_GOAL" autocheck="true" 
																inputtype="string" maxlength="250"   label="培训目的"    
																rows="5" cols="32%">${rst.TRAIN_GOAL}</textarea>
												</td>
												<td width="15%" align="right" class="detail_label" nowrap>
													 培训内容：
												</td>
												<td width="35%" class="detail_content" >
													<textarea  json="TRAIN_CONTENT" name="TRAIN_CONTENT" id="TRAIN_CONTENT" autocheck="true" 
																inputtype="string" maxlength="250"   label="培训内容"    
																rows="5" cols="32%">${rst.TRAIN_CONTENT}</textarea>
												</td>												
											</tr>																						
											<tr>
												<td width="15%" align="right" class="detail_label">
													相关附件：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"
														type="text" autocheck="true" label="相关附件"
														inputtype="string" 
														value="${rst.PIC_PATH}">
												</td>												
											</tr>
											
										</table>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</div>
	</body>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script language="JavaScript">
		SelDictShow("TRAIN_TYPE", "BS_103", '${rst.TRAIN_TYPE}', "");
		uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
	</script>
</html>

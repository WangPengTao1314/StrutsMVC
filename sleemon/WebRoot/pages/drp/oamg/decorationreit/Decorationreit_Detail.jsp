<!-- 
 *@module 渠道管理-装修管理
 *@fuc    装修报销申请单维护
 *@version 1.0
 *@author zzb
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>详情信息</title>
		<script  type="text/javascript">
	    function getPath(){
	       var path = $("#ATT_PATH").val();
	       var array = path.split(";");
	       $("#PIC").val(array[0]);
	       displayDownFile('PIC','SAMPLE_DIR',false,false);
	       $("#ZHUANGXIUSQ").val(array[1]);
	       displayDownFile('ZHUANGXIUSQ','SAMPLE_DIR',false,false);
	       $("#YBXIANG").val(array[2]);
	       displayDownFile('YBXIANG','SAMPLE_DIR',false,false);
	       $("#MCYSTAB").val(array[3]);
	       displayDownFile('MCYSTAB','SAMPLE_DIR',false,false);
	       $("#ZGYANSHOU").val(array[4]);
	       displayDownFile('ZGYANSHOU','SAMPLE_DIR',false,false);
	       $("#ZXPIC").val(array[5]);
	       displayDownFile('ZXPIC','SAMPLE_DIR',false,false);
	       $("#ZXFAPIAO").val(array[6]);
	       displayDownFile('ZXFAPIAO','SAMPLE_DIR',false,false);
	       $("#OLD_OA_ORDER_PIC").val(array[7]);
	       displayDownFile('OLD_OA_ORDER_PIC', 'SAMPLE_DIR', false,false);
	    }
	</script>
	</head>
	<body class="bodycss1" onload="getPath()">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label"> 装修报销申请单编号：</td>
							<td width="15%" class="detail_content"> 
							<input type="hidden" name="BUSS_SCOPE" id="BUSS_SCOPE" json="BUSS_SCOPE" value="${rst.BUSS_SCOPE}" />
						    <input type="hidden" name="LOCAL_TYPE" id="LOCAL_TYPE" json="LOCAL_TYPE" value="${rst.LOCAL_TYPE}" />
						    <input type="hidden" name="RNVTN_PROP" id="RNVTN_PROP" json="RNVTN_PROP" value="${rst.RNVTN_PROP}" />
							${rst.RNVTN_REIT_REQ_NO}</td>
							<td width="15%" align="right" class="detail_label"> 状态： </td>
							<td width="15%" class="detail_content">${rst.STATE}</td>
						    <td width="15%" align="right" class="detail_label"> 所属战区：</td>
							<td width="15%" class="detail_content"> ${rst.AREA_NAME}</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 是否有DM装修申请单：</td>
							<td width="15%" class="detail_content"> 
							 <c:if test="${1 eq rst.FROM_BILL_FLAG}">没有</c:if>
							 <c:if test="${empty rst.FROM_BILL_FLAG or 0 eq rst.FROM_BILL_FLAG}">有</c:if>
							</td>
							<td width="15%" align="right" class="detail_label"> 老OA走的申请单： </td>
							<td width="15%" class="detail_content" colspan="3">
							  <input type="hidden" name="OLD_OA_ORDER_PIC" id="OLD_OA_ORDER_PIC" json="OLD_OA_ORDER_PIC" value="${rst.OLD_OA_ORDER_PIC}"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 装修申请单编号： </td>
						    <td width="20%" class="detail_content"> ${rst.CHANN_RNVTN_NO} </td>
							<td width="15%" align="right" class="detail_label"> 门店编号： </td>
							<td width="15%" class="detail_content">${rst.TERM_NO} </td>
							<td width="15%" align="right" class="detail_label"> 门店名称： </td>
							<td width="20%" class="detail_content">${rst.TERM_NAME}</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 开店年份： </td>
							<td width="15%" class="detail_content">${rst.OPEN_SALE_YEAR}</td>
							<td width="15%" align="right" class="detail_label"> 设计师：</td>
							<td width="15%" class="detail_content">${rst.DESIGN_PERSON}</td>
							<td width="15%" align="right" class="detail_label"> 加盟商： </td>
							<td width="20%" class="detail_content">${rst.CHANN_NAME}</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 商场名称： </td>
							<td width="15%" class="detail_content">${rst.SALE_STORE_NAME}</td>
						    <td width="15%" align="right" class="detail_label"> 商场地址： </td>
							<td width="15%" class="detail_content">${rst.ADDRESS}</td>
							<td width="15%" align="right" class="detail_label"> 门店使用面积：</td>
							<td width="20%" class="detail_content">${rst.USE_AREA}</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 经营品牌： </td>
							<td width="15%" class="detail_content">${rst.BUSS_SCOPE}</td>
							<td width="15%" align="right" class="detail_label"> 商场位置类别： </td>
							<td width="15%" class="detail_content">${rst.LOCAL_TYPE}</td>
							<td width="15%" align="right" class="detail_label"> 装修性质： </td>
							<td width="20%" class="detail_content">${rst.RNVTN_PROP}</td>
						</tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">  是否北方公司： </td>
							<td width="15%" class="detail_content">
							    <c:if test="${rst.IS_NORTH eq 1}">
							       是
							    </c:if>
							    <c:if test="${rst.IS_NORTH eq 0}">
							       否
							    </c:if>
							</td>
							<td width="15%" align="right" class="detail_label"> 附照片： </td>
							<td width="20%" class="detail_content" colspan="3">  
							  <input type="hidden" name="ATT_PATH" id="ATT_PATH" json="ATT_PATH" value="${rst.ATT_PATH}"/>
							  <input type="hidden" name="PIC" id="PIC" json="PIC" value="${rst.PIC}">
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >
								报销次数：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <c:if test="${rst.TOTAL_REITED_NUM eq 1}">
							        一次性100%
							    </c:if>
							    <c:if test="${rst.TOTAL_REITED_NUM eq 2}">
							        二次50%、50%
							    </c:if>
							    <c:if test="${rst.TOTAL_REITED_NUM eq 3}">
							        三次50%、25%、25%
							    </c:if>
 							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label" >
								第几次报销：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							   <c:if test="${rst.REITED_NUM eq 1}">
							     第一次
							   </c:if>
							   <c:if test="${rst.REITED_NUM eq 2}">
							     第二次
							   </c:if>
							   <c:if test="${rst.REITED_NUM eq 3}">
							     第三次
							   </c:if>
 							</td>
						</tr>
				    <tr> 
				        <td width="15%" align="right" class="detail_label">
							总报销金额：
						</td>
			            <td width="15%" class="detail_content">
						  ${rst.TOTAL_REIT_AMOUNT} 
						</td>
						 <td width="15%" align="center" class="detail_content">
							本次报销金额：${rst.REIT_AMOUNT} 
						</td>
						<td td width="15%" align="center" class="detail_content">
						     实际报销金额：${rst.REAL_REIT_AMOUNT}
						</td>
						<td width="15%" align="right" class="detail_label">
							金额说明：
						</td>
						<td width="20%" class="detail_content">
					        ${rst.AMOUNT_DESC}
						</td>
				    </tr>
					<tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="4">关联附件：</td>
					   <td width="15%" align="left" class="detail_content" colspan="3">
					         装修申请单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="ZHUANGXIUSQ" name="ZHUANGXIUSQ" json="ZHUANGXIUSQ"/>
 					   </td>
					   <td width="15%" align="right" class="detail_label">关联已报项：</td>
 					   <td width="35%" class="detail_content">
					     <input json="YBXIANG" name="YBXIANG" id="YBXIANG" label="关联已报项"  type="hidden" />
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="8">
					         卖场装修验收表<input type="hidden"  name="MCYSTAB" label="卖场装修验收表"  json="MCYSTAB" id="MCYSTAB" value="${rst.MCYSTAB}"/>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="8">
					         整改验收表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" size="20" name="ZGYANSHOU" label="整改验收表" json="ZGYANSHOU" id="ZGYANSHOU" value="${rst.MCYSTAB}"/>
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" colspan="3">
					        装修平面图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" size="20" name="ZXPIC" label="装修平面图"  type="text" json="ZXPIC" id="ZXPIC" value="${rst.ZXPIC}"/>
 					   </td>
 					   <td width="15%" align="right" class="detail_label">装修发票：</td>
 					   <td width="35%" class="detail_content">
					     <input json="ZXFAPIAO" name="ZXFAPIAO" id="ZXFAPIAO" 
						        autocheck="true" label="装修平面图"  type="hidden" value="${rst.ZXFAPIAO}"/>
 					   </td>
	                  </tr>
						<tr>
							<td width="15%" align="right" class="detail_label"> 申请人： </td>
							<td width="20%" class="detail_content"  >
							 ${rst.REQ_NAME} 
							</td>
							<td width="20%" align="right" class="detail_label"> 申请时间： </td>
							<td width="35%" class="detail_content"  colspan="3">
							 ${rst.REQ_DATE} 
							</td>
						</tr>
						 <tr>
	                   <td width="15%" align="right" class="detail_label" rowspan="2">销售营运部：</td>
					   <td width="15%" align="left" class="detail_content">
					         销售达成情况
 					   </td>
 					   <td width="15%" align="right" class="detail_content">
					         合同金额(万元)
 					   </td>
 					   <td width="15%" align="left" class="detail_content">
 					        ${rst.SALE_COMPACT_AMOUNT}
   					   </td>
					   <td width="15%" align="right" class="detail_label">本年度至今发货金额(万元)：</td>
 					   <td width="35%" class="detail_content">
 					        ${rst.YEAR_GOODS_AMOUNT}
 					   </td>
	                  </tr>
	                  <tr>
					   <td width="15%" align="left" class="detail_content" cols="3">
					         累计四个季度实际完成 ${rst.QUARTER_RATE} 
 					   </td>
 					   <td width="15%" align="right" class="detail_label">有无违反现象：</td>
 					   <td width="35%" class="detail_content" colspan="3">
 					        ${rst.IS_VIOLATE_REMARK}
 					   </td>
	                  </tr>  
	                  <tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="10" class="detail2">
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
								<td nowrap align="center" width="10%">${rr}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATORNAME}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATETIME}&nbsp;</td>
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
		<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
		<script type="text/javascript">
		   /*
		    var path = $("#ATT_PATH").val();
		    var leg = path.split(",").length ;
		    var folder = "";
		    if(leg>1){
		    	for(var i=0;i<leg;i++){
			    	folder = folder+"SAMPLE_DIR,";
			    }
		    	folder = folder.substring(0,folder.length-1);
		    }else{
		    	folder = "SAMPLE_DIR";
		    }
	        displayDownFile('ATT_PATH',folder,false,false);
	        */
	   </script>
	</body>
</html>

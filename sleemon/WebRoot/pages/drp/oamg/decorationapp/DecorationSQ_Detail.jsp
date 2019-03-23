<!-- 
 *@module 渠道管理-装修管理
 *@func   装修申请单维护
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
         function load(){
           var BEAR_WAY = $("#BEAR_WAY").val();
           if(BEAR_WAY=="1"){
             $("#showTr").show(); 
             $("#showTr1").hide(); 
           }
           if(BEAR_WAY=="2"){
             $("#showTr").hide(); 
             $("#showTr1").show(); 
           }
           
           var path = $("#ATT_PATH").val();
	       var array = path.split(";");
	       $("#SALE_STORE_ANALYSE").val(array[0]);
	       displayDownFile('SALE_STORE_ANALYSE','SAMPLE_DIR',false,false);
	       $("#XIANCHANGPIC").val(array[1]);
	       displayDownFile('XIANCHANGPIC','SAMPLE_DIR',false,false);
	       $("#MARKETPIC").val(array[2]);
	       displayDownFile('MARKETPIC','SAMPLE_DIR',false,false);
         }
	    </script>
		<title>装修申请单详情</title>
	</head>
  
	<body class="bodycss1" onload="load()">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">
								装修申请单号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_RNVTN_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.RNVTN_REQ_NAME}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								申请日期：
							</td>
							<td width="35%" class="detail_content">
                                 ${rst.RNVTN_REQ_DATE} 							 
							</td>
							<td width="15%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.STATE}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								评估单号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.OPEN_TERMINAL_REQ_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NAME}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								渠道名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHANN_NAME}
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERM_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.TERM_NAME}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								设计师：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.DESIGN_PERSON}
							</td>
							<td width="15%" align="right" class="detail_label">
								商场名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.SALE_STORE_NAME}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="35%" class="detail_content">
							    ${BUSS_SCOPE_NAME}
 							</td>
							<td width="15%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="35%" class="detail_content">
								${rst.RNVTN_PROP}
							</td>
						</tr>


						<tr>
							<td width="15%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.PLAN_SBUSS_DATE}
							</td>
							<td width="15%" align="right" class="detail_label">
								实际使用面积(平米)：
							</td>
							<td width="35%" class="detail_content">
								${rst.USE_AREA}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_NAME}
							</td>
							<!--  
							<td width="15%" align="right" class="detail_label">
								区域经理电话：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_TEL}
							</td>
							-->
							<td width="15%" align="right" class="detail_label">
								详细地址：
							</td>
							<td width="35%" class="detail_content">
								${rst.ADDRESS}
							</td>
						</tr>
						<!--
						<tr style="display:none;">
						   <td width="15%" align="right" class="detail_label">
								要求出图时间：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.DMD_DRAW_DATE}
							</td>
							<td width="15%" align="right" class="detail_label">
								一场多店信息：
							</td>
							<td width="35%" class="detail_content">
								 <c:if test="${rst.TERM_WHICH_NUM eq 1}">单一商场内新增第一家品牌店</c:if>
                                 <c:if test="${rst.TERM_WHICH_NUM eq 2}">单一商场内新增第二家品牌店</c:if>
                                 <c:if test="${rst.TERM_WHICH_NUM eq 3}">单一商场内新增第三家品牌店</c:if>
                                 <c:if test="${rst.TERM_WHICH_NUM eq 4}">单一商场内新增第四家及以上品牌店</c:if>
							</td>
						</tr>
						<tr style="display:none;">
							<td width="15%" align="right" class="detail_label">
								商场租金(元/平米)：
							</td>
							<td width="35%" class="detail_content">
								${rst.MARKET_RENT}
							</td>
							<td width="15%" align="right" class="detail_label">
								预计年销量(万元)：
							</td>
							<td width="35%" class="detail_content">
								${rst.EXP_YEAR_SALE}
							</td>
						</tr>
						<tr style="display:none;">
							<td width="15%" align="right" class="detail_label">
								合同签订金额(万元)：
							</td>
							<td width="35%" class="detail_content">
								${rst.COMPACT_AMOUNT}
							</td>
							<td width="15%" align="right" class="detail_label">
								报销频次：
							</td>
							<td width="35%" class="detail_content">
								${rst.REIT_POLICY}
							</td>
						</tr>
						  -->
						<tr>
						   <td width="15%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.LOCAL_TYPE}
							</td>
							<td width="15%" align="right" class="detail_label">
								报销标准：
							</td>
							<td width="35%" class="detail_content">
							    <c:if test="${rst.IS_STAD_FLAG eq 1}">
							        一、二季度
							    </c:if>
							    <c:if test="${rst.IS_STAD_FLAG eq 2}">
							        三、四季度
							    </c:if>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								商场综合情况分析：
							</td>
							<td width="35%" class="detail_content">
							    <input id="ATT_PATH" name="ATT_PATH" json="ATT_PATH" value="${rst.ATT_PATH}" type="hidden"/>
							    <input id="SALE_STORE_ANALYSE" json="SALE_STORE_ANALYSE" size="120" name="SALE_STORE_ANALYSE"  type="hidden"   value="${rst.SALE_STORE_ANALYSE}"/>
							</td>
							<td width="15%" align="right" class="detail_label">
								现场照片：
							</td>
							<td width="35%" class="detail_content">
							     <input id="XIANCHANGPIC" json="XIANCHANGPIC" size="120" name="XIANCHANGPIC"  type="hidden"   value="${rst.XIANCHANGPIC}"/>
							</td>
						</tr>
						<tr>
						  <td width="15%" align="right" class="detail_label">
								商场综合平面图：
							</td>
							<td width="35%" class="detail_content">
							     <input id="MARKETPIC" json="MARKETPIC" size="120" name="MARKETPIC"  type="hidden"   value="${rst.MARKETPIC}"/>
							</td>
							<td width="15%" align="right" class="detail_label">
							    总额(元)：
							</td>
							<td width="35%" class="detail_content">
								${rst.REIT_AMOUNT}
							</td>
						</tr>

						<tr>
							<td width="15%" align="right" class="detail_label">
								报销金额(元/平米)：
							</td>
							<td width="35%" class="detail_content">
								${rst.REIT_AMOUNT_PS}
							</td>
							<td width="15%" align="right" class="detail_label">
								金额说明：
							</td>
							<td width="35%" class="detail_content">
								${rst.AMOUNT_DESC}
							</td>
						</tr>
                        <tr>
							<td width="15%" align="right" class="detail_label">
								报销承担方式：<input type="hidden" id="BEAR_WAY" value="${rst.BEAR_WAY}"/>
							</td>
							<td width="35%" class="detail_content">
							     <c:if test="${rst.BEAR_WAY eq 1}">
							        三次报销
							    </c:if>
							    <c:if test="${rst.BEAR_WAY eq 2}">
							        特殊政策
							    </c:if>
							</td>
							<td width="15%" align="right" class="detail_label">
							    预算科目名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.BUDGET_ITEM_NAME}
							</td>
						</tr>
						<tr id="showTr" style="display:none;">
						    <td width="17%" align="right" class="detail_label">
							</td>
							<td width="63%" class="detail_content" colspan="3">
							   装修完成验收合格报50%，满1年25%，满2年25%
							</td>
						</tr>
						<tr id="showTr1" style="display:none;">
						    <td width="17%" align="right" class="detail_label">
							</td>
							<td width="63%" class="detail_content" colspan="3">
							     ${rst.SPEC_CONTENT}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								特殊说明：
							</td>
							<td width="35%" class="detail_content" colspan="3">
								${rst.REMARK}
							</td>
						</tr>
						<tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="3" class="detail2">
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
	<script type="text/javascript">
       SelDictShow("LOCAL_TYPE","BS_86","${params.LOCAL_TYPE}","");
       SelDictShow("LEDGER_ID1 ","BS_87","${params.LEDGER_ID1}","");
       SelDictShow("BUSS_SCOPE ","BS_88","${params.BUSS_SCOPE}","");
       SelDictShow("TERM_WHICH_NUM ","BS_89","${params.TERM_WHICH_NUM}","");
       var path = $("#XIANCHANGPIC").val();
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
       displayDownFile('XIANCHANGPIC',str,false,false);
   </script>
	</body>
</html>

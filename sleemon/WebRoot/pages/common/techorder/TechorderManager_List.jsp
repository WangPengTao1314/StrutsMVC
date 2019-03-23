<!--
 * @prjName:喜临门营销平台
 * @fileName:Spcl_Tech_List.jsp
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/techorder/TechorderManager_List.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style>
		body{
			font-size:12px;
		}
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
		}
		li{
			width:200px;
			float:left;
			list-style:none;
			height:20px;
			line-height:20px;
		}
		div{
			width:700px;
			float:left;
			border-top: dashed 2px #999;
			margin-bottom:10px;
			padding:20px 10px;
			clear:both;
		}
		span{
			 color:#F00;
			 font-weight:bold;
		}
		td{
			width:100px;
			height:30px;
		}
		.check{
			width:20px;
		}
		table{
			width:100%;
			height:100%;
		}
	</style>
</head>
<body class="bodycss1">
    <div style="border:solid 2px #999;width: auto;height: auto;margin-left: 5px;margin-bottom: 50px;">
		<h2 align="center">货品特殊工艺</h2>
		<div>
	    	<span>主要参数：</span>
	    	<ul>
	        	<li>货品编号：${rst.PRD_NO}</li>
	            <li>货品名称：${rst.PRD_NAME}</li>
	            <li>规格型号：${rst.PRD_SPEC}</li>
	            <li>品牌：${rst.BRAND}</li>
	            <li>花号：${rst.PRD_COLOR}</li>
	            <li>材质：${rst.PRD_MATL}</li>
	             <input type="hidden" id="BASE_PRICE" value="${rst.PRICE}"/>
	            <input type="hidden" id="DECT_RATE" value="${rst.DECT_RATE}"/>
	            <input type="hidden" id="PRD_ID" value="${PRD_ID}"/>
	            <input type="hidden" id="SPCL_TECH_ID" value="${rst.SPCL_TECH_ID}"/>
	        </ul>
	    </div>
	    <div>
	    	<span>尺寸调整：</span>
	    	<table id="adjustSize">
	    		<tr></tr>
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '尺寸调整'}">
		    			<tr>
			 				<td class="check">
			 					<input type="checkbox" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}"
			 						<c:if test="${list.SPCL_TECH_DTL_ID!=null}">
			 							 checked="checked" 
			 						</c:if> 
			 					 />
			 				</td>
			 				<td>
			 					<input type="hidden" json="PRD_SPCL_TECH_ID" id="PRD_SPCL_TECH_ID${statu.index}" name="PRD_SPCL_TECH_ID"   value="${list.PRD_SPCL_TECH_ID}" >
			 					<input type="hidden" json="PRICE_TURN_TYPE" id="PRICE_TURN_TYPE${statu.index}" name="PRICE_TURN_TYPE" value="${list.PRICE_TURN_TYPE}"/>
			 					<input type="hidden" json="PRICE_TURN_VAL" id="PRICE_TURN_VAL${statu.index}" name="PRICE_TURN_VAL"  value="${list.PRICE_TURN_VAL}"/>
			 					<input type="hidden" json="CURRT_VAL_TURN_BEG" id="CURRT_VAL_TURN_BEG${statu.index}" name="CURRT_VAL_TURN_BEG"   value="${list.CURRT_VAL_TURN_BEG}">
			 					<input type="hidden" json="CURRT_VAL_TURN_END" id="CURRT_VAL_TURN_END${statu.index}" name="CURRT_VAL_TURN_END"   value="${list.CURRT_VAL_TURN_END}">
			 					<input type="hidden" json="CURRT_VAL" id="CURRT_VAL${statu.index}" name="CURRT_VAL"   value="${list.CURRT_VAL}">
			 					<input type="hidden" json="SPCL_TECH_NAME" id="SPCL_TECH_NAME${statu.index}" name="SPCL_TECH_NAME"   value="${list.SPCL_TECH_NAME}">
			 					<input type="hidden" json="SPCL_TECH_TYPE" id="SPCL_TECH_TYPE${statu.index}" name="SPCL_TECH_TYPE"   value="${list.SPCL_TECH_TYPE}">
			 					${list.SPCL_TECH_NAME }为:
			 				</td>
			 				<td>
			 					<input type="text" class="text_underline" id="CUST_TURN_VAL${statu.index}" name="CUST_TURN_VAL" json="CUST_TURN_VAL"
			 					<c:if test="${list.CUST_TURN_VAL eq null}">
			 						value="${list.CURRT_VAL}"
			 					</c:if> 
			 					<c:if test="${list.CUST_TURN_VAL !=null}">
			 						value="${list.CUST_TURN_VAL}"
			 					</c:if>
			 					/>
			 				 </td>
			 				<td>范围：${list.CURRT_VAL_TURN_BEG}----${list.CURRT_VAL_TURN_END}</td>
		 				</tr>
	 				</c:if>
	    		</c:forEach>
	        </table>
	    </div>
	    <div>
	    	<span>替换部件：</span>
	    	<table id="unitReplace">
	    		<tr></tr>
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '部件替换'}">
			        	<tr>
			         		<td class="check">
			 					<input type="checkbox" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}"
			 						<c:if test="${list.SPCL_TECH_DTL_ID!=null}">
			 							 checked="checked" 
			 						</c:if> 
			 					 />
			 				</td>
			                <td align="right">
			                	<input type="hidden" json="PRD_SPCL_TECH_ID" id="PRD_SPCL_TECH_ID${statu.index}" name="PRD_SPCL_TECH_ID"   value="${list.PRD_SPCL_TECH_ID}" >
			 					<input type="hidden" json="PRICE_TURN_TYPE" id="PRICE_TURN_TYPE${statu.index}" name="PRICE_TURN_TYPE" value="${list.PRICE_TURN_TYPE}"/>
			 					<input type="hidden" json="PRICE_TURN_VAL" id="PRICE_TURN_VAL${statu.index}" name="PRICE_TURN_VAL"  value="${list.PRICE_TURN_VAL}"/>
			 					<input type="hidden" json="CURRT_VAL_TURN_BEG" id="CURRT_VAL_TURN_BEG${statu.index}" name="CURRT_VAL_TURN_BEG"   value="${list.CURRT_VAL_TURN_BEG}">
			 					<input type="hidden" json="CURRT_VAL_TURN_END" id="CURRT_VAL_TURN_END${statu.index}" name="CURRT_VAL_TURN_END"   value="${list.CURRT_VAL_TURN_END}">
			 					<input type="hidden" json="CURRT_VAL" id="CURRT_VAL${statu.index}" name="CURRT_VAL"   value="${list.CURRT_VAL}">
			 					<input type="hidden" json="SPCL_TECH_NAME" id="SPCL_TECH_NAME${statu.index}" name="SPCL_TECH_NAME"   value="${list.SPCL_TECH_NAME}">
			 					<input type="hidden" json="SPCL_TECH_TYPE" id="SPCL_TECH_TYPE${statu.index}" name="SPCL_TECH_TYPE"   value="${list.SPCL_TECH_TYPE}">
			 					<input type="hidden"  id="CUST_TURN_VAL${statu.index}" name="CUST_TURN_VAL" json="CUST_TURN_VAL" value="${list.CURRT_VAL}"/>
			                	${list.SPCL_TECH_NAME }替换：
			                </td>
			                <td>${list.CURRT_VAL}</td>
			                <td>替换为</td>
			                <td>
			                	<select id="sel${statu.index}" onchange="upSel('${statu.index}')" disabled="disabled">
			                    </select>
			                    <script>
				                	  var TUENED_VALS="${list.TUENED_VALS}";
				                	  TUENED_VALS = TUENED_VALS.split(",");
				                	  for(var i=0;i<TUENED_VALS.length;i++){
				                	     $("#sel"+${statu.index}).append("<option value='"+TUENED_VALS[i]+"'>"+TUENED_VALS[i]+"</option>");   
				                	  }
				                	  var CUST_TURN_VAL="${list.CUST_TURN_VAL}";
				                	  if(""!=CUST_TURN_VAL&&null!=CUST_TURN_VAL){
				                	  	$("#sel"+${statu.index}).val(CUST_TURN_VAL);
				                	  }
				               </script>
			   				</td>
			            </tr>
	            	</c:if>
	    		</c:forEach>
	        </table>
	    </div>
	    <div>
	    	<span>增加部件：</span>
	        <table id="addUnit">
	        	<tr></tr>
		        	<c:forEach items="${list}" var="list" varStatus="statu">
		    			<c:if test="${list.SPCL_TECH_TYPE eq '部件新增'}">
			    			<tr style="float: left">
				       		  <td class="check">
			 					<input type="checkbox" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}"
			 						<c:if test="${list.SPCL_TECH_DTL_ID!=null}">
			 							 checked="checked" 
			 						</c:if> 
			 					 />
			 				  </td>
				           	  <td style="width:150px">
				           	  	<input type="hidden" json="PRD_SPCL_TECH_ID" id="PRD_SPCL_TECH_ID${statu.index}" name="PRD_SPCL_TECH_ID"   value="${list.PRD_SPCL_TECH_ID}" >
				 					<input type="hidden" json="PRICE_TURN_TYPE" id="PRICE_TURN_TYPE${statu.index}" name="PRICE_TURN_TYPE" value="${list.PRICE_TURN_TYPE}"/>
				 					<input type="hidden" json="PRICE_TURN_VAL" id="PRICE_TURN_VAL${statu.index}" name="PRICE_TURN_VAL"  value="${list.PRICE_TURN_VAL}"/>
				 					<input type="hidden" json="CURRT_VAL_TURN_BEG" id="CURRT_VAL_TURN_BEG${statu.index}" name="CURRT_VAL_TURN_BEG"   value="${list.CURRT_VAL_TURN_BEG}">
				 					<input type="hidden" json="CURRT_VAL_TURN_END" id="CURRT_VAL_TURN_END${statu.index}" name="CURRT_VAL_TURN_END"   value="${list.CURRT_VAL_TURN_END}">
				 					<input type="hidden" json="CURRT_VAL" id="CURRT_VAL${statu.index}" name="CURRT_VAL"   value="${list.CURRT_VAL}">
				 					<input type="hidden" json="SPCL_TECH_NAME" id="SPCL_TECH_NAME${statu.index}" name="SPCL_TECH_NAME"   value="${list.SPCL_TECH_NAME}">
				 					<input type="hidden" json="SPCL_TECH_TYPE" id="SPCL_TECH_TYPE${statu.index}" name="SPCL_TECH_TYPE"   value="${list.SPCL_TECH_TYPE}">
				 					<input type="hidden" id="CUST_TURN_VAL${statu.index}" name="CUST_TURN_VAL" json="CUST_TURN_VAL" value="${list.CURRT_VAL}"/>
				 					<input type="hidden" id="SPCL_TECH_NAME${statu.index}" name="SPCL_TECH_NAME" json="SPCL_TECH_NAME" value="${list.SPCL_TECH_NAME}"/>
				           	  	${list.SPCL_TECH_NAME}
				           	  </td>
				           	 </tr>
			            </c:if>
		    		</c:forEach>
	        </table>
	    </div>
	    <div>
	    	<table style="border: solid;" >
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '非标工艺说明'}">
			    		<tr  style="height:50px">
			            	<td align="right" bgcolor="#FFCCFF">特殊工艺描述</td>
			            	<input type="hidden" value="${list.SPCL_TECH_DTL_ID}" id="REMARKId"/>
			            	<td style="height:50px;">
			                	<textarea cols="50" rows="5" id="REMARK" disabled="disabled">${list.REMARK}</textarea>
			                </td>
			          	</tr>
			         </c:if>
		    	</c:forEach>
	    	</table>
	    </div>
	    <div>
	    	<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '非标工艺说明'}">
	    				<span style="color:red">
	    					${list.EXPLAIN}
	    				</span>
	    			</c:if>
		    	</c:forEach>
	    		<br/>
	    		<input type="hidden" id="showPrice" value="${showPrice}">
	    		<c:if test="${showPrice eq 'show'}">
	    			<h3 align="right"><span style="color:red"> 供货单价：${rst.PRICE} &nbsp;&nbsp;&nbsp;调整后供货单价：<font id="price">${BASE_PRICE}</font></span></h3>
	    		</c:if>
	    	<br/>
	    	<center>
	    	<button class="btn" style="text-align: center;" onclick="window.close()">关闭</button>
	    	</center>
	    </div>
    </div>
</body>
</html>
<script type="text/javascript">
</script>
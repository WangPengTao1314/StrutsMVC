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
	<script type="text/javascript" src="${ctx}/pages/common/techorder/TechorderManager_Edit.js"></script>
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
			height:15px;
			line-height:15px;
		}
		div div{
			width:100%;
			border-top: dashed 1px #999;
			margin-bottom:10px;
			padding:3px 3px;
			clear:both;
		}
		span{
			 color:#F00;
			 font-weight:bold;
		}
		td{
			width:100px;
			height:15px;
		}
		.check{
			width:20px;
		}
		table{
			width:100%;
			height:100%;
		}
		.rightLi{
			margin: 0px 0px;
			padding: 0px 0px;
			width: 90px;
		}
		.newInfo{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
			height: 15px;
		}
	</style>
</head>
<body class="bodycss1">
	<div style="height: 95%">
		<div >
	    	<span>主要参数：</span>
	    	<ul>
	    		<li>特殊工艺编号:${rst.DM_SPCL_TECH_NO}</li>
	        	<li>货品编号：${rst.PRD_NO}</li>
	            <li>货品名称：${rst.PRD_NAME}</li>
	            <li>规格型号：${rst.PRD_SPEC}</li>
	            <li>品牌：${rst.BRAND}</li>
	            <li>花号：${rst.PRD_COLOR}</li>
	            <li>材质：${rst.PRD_MATL}</li>
	            <input type="hidden" id="BASE_PRICE" value="${rst.PRICE}"/>
	            <input type="hidden" id="PRD_ID" value="${PRD_ID}"/>
	            <input type="hidden" id="SPCL_TECH_ID" value="${rst.SPCL_TECH_ID}"/>
	            <input type="hidden" id="USE_FLAG" value="${USE_FLAG}"/>
	            <input type="hidden" id="TABLE" value="${TABLE}"/>
	            <input type="hidden" id="BUSSID" value="${BUSSID}"/>
	            <input type="hidden" id="type" value="${type}"/>
	            <input type="hidden" id="addFlag" value="${addFlag}"/>
	            <input type="hidden" id="TECH_NO_EDIT_FLAG" value="${rst.TECH_NO_EDIT_FLAG}"/>
	            <input type="hidden" id="optOldFlag" value="${optOldFlag}"/>
	            <input type="hidden" id="IS_NO_SPCL_FLAG" value="${IS_NO_SPCL_FLAG}"/>
	        </ul>
	    </div>
	    <c:if test="${saleFlag eq 1}">
	    <div >
	    	<span>编辑新货品信息</span>
	    	<table>
	    		<tr>
	    			<td width="15%" align="right" class="detail_label">新规格：</td>
	    			<td width="35%" class="detail_content">
	    				<input type="text" name="NEW_SPEC" id="NEW_SPEC" size="10" lable="新规格" json="NEW_SPEC" class="newInfo" inputtype="string" value="${NEW_SPEC}" maxlength="50" />
	    			</td>
	    			<td width="15%" align="right" class="detail_label">新花号：</td>
	    			<td width="35%" class="detail_content">
	    				<input type="text" name="NEW_COLOR" id="NEW_COLOR" size="10" lable="新花号" json="NEW_COLOR" class="newInfo" inputtype="string" value="${NEW_COLOR}" maxlength="50" />
	    				<input type="hidden" id="ORDERID" value="${ORDERID}"/>
	    				<input type="hidden" id="editTable" value="${editTable}"/>
	    			</td>
	    			<td width="15%" align="right" class="detail_label">生产相关描述：</td>
	    			<td width="35%" class="detail_content">
	    				<input type="text" name="PRODUCT_REMARK" id="PRODUCT_REMARK" size="10" lable="生产相关描述" json="PRODUCT_REMARK" class="newInfo" inputtype="string" value="${PRODUCT_REMARK}" maxlength="50" />
	    			</td>
	    			<td width="10%"><input type="button" class="btn" value="保存" onclick="saveSale()" /></td>
	    		</tr>
	    	</table>
	    </div>
	    </c:if>
	    <div>
	    	<span>尺寸调整：</span>
	    	<table id="adjustSize">
	    		<tr></tr> 
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '尺寸调整'}">
		    			<tr>
			 				<td class="check">
			 					<input type="checkbox"  sizename="sizeadjust"  json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}" 
			 					 />
			 					 <script type="text/javascript">
			 					   var SPCL_TECH_DTL_ID ="${list.SPCL_TECH_DTL_ID}";
			 					   if(null != SPCL_TECH_DTL_ID&&""!=SPCL_TECH_DTL_ID){
			 					      $("#SPCL_TECH_DTL_ID"+${statu.index}).attr("checked","checked");
			 					   }
			 					 </script>
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
			 					<input type="hidden" json="TUENED_VALS" id="TUENED_VALS${statu.index}" name="TUENED_VALS"   value="${list.TUENED_VALS}">
			 					${list.SPCL_TECH_NAME }:
			 				</td>
			 				<td>
			 					<input type="text" class="text_underline" id="CUST_TURN_VAL${statu.index}" name="CUST_TURN_VAL" json="CUST_TURN_VAL" onkeyup="countPrice()"
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
	    	<span>部件替换：</span>
	    	<table id="unitReplace">
	    		<tr></tr>
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '部件替换'}">
			        	<tr>
			         		<td class="check">
			         			<input type="checkbox" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}"
			         			/>
			         			 <script type="text/javascript">
			 					   var SPCL_TECH_DTL_ID ="${list.SPCL_TECH_DTL_ID}";
			 					   if(null != SPCL_TECH_DTL_ID&&""!=SPCL_TECH_DTL_ID){
			 					      $("#SPCL_TECH_DTL_ID"+${statu.index}).attr("checked","checked");
			 					   }
			 					 </script>
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
			 					<input type="hidden"  id="CUST_TURN_VAL${statu.index}" name="CUST_TURN_VAL" json="CUST_TURN_VAL" value="${list.CUST_TURN_VAL}"/>
			 					<input type="hidden" json="TUENED_VALS" id="TUENED_VALS${statu.index}" name="TUENED_VALS"   value="${list.TUENED_VALS}">
			                	${list.SPCL_TECH_NAME }：
			                </td>
			                <td>${list.CURRT_VAL}</td>
			                <td>替换为</td>
			                <td>
			                	<select id="sel${statu.index}" name="sel" onchange="upSel('${statu.index}')">
			                    </select>
			                    <script>
				                	  var TUENED_VALS="${list.TUENED_VALS}";
				                	  TUENED_VALS = TUENED_VALS.split(",");
				                	  $("#sel"+${statu.index}).append("<option>--请选择--</option>");
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
	    	<span>部件新增：</span>
	        <table id="addUnit" >
	        	<tr></tr>
	        	<c:set var="flag" value="0"></c:set>
		        	<c:forEach items="${list}" var="list" varStatus="statu">
		    			<c:if test="${list.SPCL_TECH_TYPE eq '部件新增'}">
		    				<c:set var="flag" value="${flag+1}"></c:set>
		    				<c:out value=""></c:out>
		    				<c:if test="${flag%2!=0}">
		    					<tr>
		    				</c:if>
					       		<td class="check" name="value_td">
					       		    <input type="checkbox"  id="SPCL_TECH_DTL_ID${statu.index}"   value="${list.SPCL_TECH_DTL_ID}" />
					       		    <input type="hidden" json="PRD_SPCL_TECH_ID" id="PRD_SPCL_TECH_ID${statu.index}" name="PRD_SPCL_TECH_ID${flag%2}"   value="${list.PRD_SPCL_TECH_ID}" >
				 					<input type="hidden" json="PRICE_TURN_TYPE" id="PRICE_TURN_TYPE${statu.index}" name="PRICE_TURN_TYPE${flag%2}" value="${list.PRICE_TURN_TYPE}"/>
				 					<input type="hidden" json="PRICE_TURN_VAL" id="PRICE_TURN_VAL${statu.index}" name="PRICE_TURN_VAL${flag%2}"  value="${list.PRICE_TURN_VAL}"/>
				 					<input type="hidden" json="CURRT_VAL_TURN_BEG" id="CURRT_VAL_TURN_BEG${statu.index}" name="CURRT_VAL_TURN_BEG${flag%2}"   value="${list.CURRT_VAL_TURN_BEG}">
				 					<input type="hidden" json="CURRT_VAL_TURN_END" id="CURRT_VAL_TURN_END${statu.index}" name="CURRT_VAL_TURN_END${flag%2}"   value="${list.CURRT_VAL_TURN_END}">
				 					<input type="hidden" json="CURRT_VAL" id="CURRT_VAL${statu.index}" name="CURRT_VAL${flag%2}"   value="${list.CURRT_VAL}">
				 					<input type="hidden" json="SPCL_TECH_TYPE" id="SPCL_TECH_TYPE${statu.index}" name="SPCL_TECH_TYPE${flag%2}"   value="${list.SPCL_TECH_TYPE}">
				 					<input type="hidden" json="CUST_TURN_VAL" id="CUST_TURN_VAL${statu.index}" name="CUST_TURN_VAL${flag%2}"  value="${list.CURRT_VAL}"/>
				 					<input type="hidden" json="SPCL_TECH_NAME"  id="SPCL_TECH_NAME${statu.index}" name="SPCL_TECH_NAME${flag%2}" value="${list.SPCL_TECH_NAME}"/>
				 					<input type="hidden" json="SPCL_TECH_DTL_ID"  name="SPCL_TECH_DTL_ID${flag%2}" value="${SPCL_TECH_DTL_ID}"/>
				 					<input type="hidden" json="TUENED_VALS" id="TUENED_VALS${statu.index}" name="TUENED_VALS${flag%2}"   value="${list.TUENED_VALS}">
				       		  		<input type="hidden" name="addUnitFlag" value="1"/>
				       		  		  <script type="text/javascript">
				 					   var SPCL_TECH_DTL_ID ="${list.SPCL_TECH_DTL_ID}";
				 					   if(null != SPCL_TECH_DTL_ID&&""!=SPCL_TECH_DTL_ID){
				 					      $("#SPCL_TECH_DTL_ID"+${statu.index}).attr("checked","checked");
				 					   }
				 					 </script>
								</td>
					           	<td style="width: 150px" name="SPCL_TECH_DTL_ID${statu.index}">
				           	  		${list.SPCL_TECH_NAME}
					           </td>
					     <c:if test="${flag%2==0}">
					     	</tr>
					     </c:if>
					     <c:if test="${flag%2!=0&&statu.index==fn:length(list)}">
					     	</tr>
					     </c:if>	
			            </c:if>
		    		</c:forEach>
	        </table>
	    </div>
	    
	    
	    <div>
	    	<table style="border: solid;" id="SAMEREMARKTB" >
	    		<tr></tr>
	    		<c:set var="REMARKFLAG" value="0"></c:set>
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '一般特殊工艺描述' && list.PRD_SPCL_TECH_ID eq null}">
	    				<c:set var="REMARKFLAG" value="1"></c:set>
			    		<tr style="height:50px">
			    			<td style="display: none;">
			    				<input type="checkbox" checked="checked" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}"/>
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
			 					<input type="hidden" name="colorFlag" value="1"/>
			    			</td>
			            	<td align="right" bgcolor="#FFF0F5">一般特殊工艺描述</td>
			            	<td style="height:50px;">
			                	<textarea cols="50" rows="5" id="SAMEREMARK" onkeypress="if(event.keyCode==13) return false;" json="REMARK" name="REMARK">${list.REMARK}</textarea>
			                </td>
			          	</tr>
			         </c:if>
		    	</c:forEach>
			    	<c:if test="${REMARKFLAG eq 0}">
			    		<tr style="height:50px">
				    			<td style="display: none;">
				    				<input type="checkbox" checked="checked" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID" name="SPCL_TECH_DTL_ID"   />
				    				<input type="hidden" json="PRD_SPCL_TECH_ID" id="PRD_SPCL_TECH_ID" name="PRD_SPCL_TECH_ID"    >
				 					<input type="hidden" json="PRICE_TURN_TYPE" id="PRICE_TURN_TYPE" name="PRICE_TURN_TYPE" />
				 					<input type="hidden" json="PRICE_TURN_VAL" id="PRICE_TURN_VAL" name="PRICE_TURN_VAL"  />
				 					<input type="hidden" json="CURRT_VAL_TURN_BEG" id="CURRT_VAL_TURN_BEG" name="CURRT_VAL_TURN_BEG"   >
				 					<input type="hidden" json="CURRT_VAL_TURN_END" id="CURRT_VAL_TURN_END" name="CURRT_VAL_TURN_END"  >
				 					<input type="hidden" json="CURRT_VAL" id="CURRT_VAL" name="CURRT_VAL" >
				 					<input type="hidden" json="SPCL_TECH_NAME" id="SPCL_TECH_NAME" name="SPCL_TECH_NAME" >
				 					<input type="hidden" json="SPCL_TECH_TYPE" id="SPCL_TECH_TYPE" name="SPCL_TECH_TYPE"   value="一般特殊工艺描述">
				 					<input type="hidden" id="CUST_TURN_VAL" name="CUST_TURN_VAL" json="CUST_TURN_VAL" />
				 					<input type="hidden" id="SPCL_TECH_NAME" name="SPCL_TECH_NAME" json="SPCL_TECH_NAME"/>
				 					<input type="hidden" name="colorFlag" value="1"/>
				    			</td>
				            	<td align="right" bgcolor="#FFF0F5">一般特殊工艺描述</td>
				            	<td style="height:50px;">
				                	<textarea cols="50" rows="5" id="SAMEREMARK" onkeypress="if(event.keyCode==13) return false;" json="REMARK" name="REMARK"></textarea>
				                </td>
				          	</tr>
			    	</c:if>
	    	</table>
	    </div>
	    <div id="specialDiv">
	    	<label style="color: red">注意：申请特殊料号时请带上所有尺寸调整、部件替换、部件新增及描述框中的内容</label>
	    	<table style="border: solid;" id="REMARK" >
	    		<tr></tr>
	    		<c:set var="flag" value="0"></c:set>
	    		<c:forEach items="${list}" var="list" varStatus="statu">
	    			<c:if test="${list.SPCL_TECH_TYPE eq '非标工艺说明' && list.PRD_SPCL_TECH_ID eq null}">
	    				<c:set var="flag" value="1"></c:set>
			    		<tr style="height:50px">
			    			<td style="display: none;">
			    				<input type="checkbox" checked="checked" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID${statu.index}" name="SPCL_TECH_DTL_ID"   value="${list.SPCL_TECH_DTL_ID}"/>
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
			 					<input type="hidden" name="colorFlag" value="1"/>
			    			</td>
			            	<td align="right" bgcolor="#FFF0F5">特殊工艺描述</td>
			            	<td style="height:50px;">
			                	<textarea cols="50" rows="5"  id="EXPLAIN" onkeyup="countPrice()" onkeypress="if(event.keyCode==13) return false;" json="REMARK" name="REMARK">${list.REMARK}</textarea>
			                </td>
			          	</tr>
			         </c:if>
		    	</c:forEach>
			    	<c:if test="${flag eq 0 }">
			    		<tr style="height:50px">
				    			<td style="display: none;">
				    				<input type="checkbox" checked="checked" json="SPCL_TECH_DTL_ID" id="SPCL_TECH_DTL_ID" name="SPCL_TECH_DTL_ID"   />
				    				<input type="hidden" json="PRD_SPCL_TECH_ID" id="PRD_SPCL_TECH_ID" name="PRD_SPCL_TECH_ID"    >
				 					<input type="hidden" json="PRICE_TURN_TYPE" id="PRICE_TURN_TYPE" name="PRICE_TURN_TYPE" />
				 					<input type="hidden" json="PRICE_TURN_VAL" id="PRICE_TURN_VAL" name="PRICE_TURN_VAL"  />
				 					<input type="hidden" json="CURRT_VAL_TURN_BEG" id="CURRT_VAL_TURN_BEG" name="CURRT_VAL_TURN_BEG"   >
				 					<input type="hidden" json="CURRT_VAL_TURN_END" id="CURRT_VAL_TURN_END" name="CURRT_VAL_TURN_END"  >
				 					<input type="hidden" json="CURRT_VAL" id="CURRT_VAL" name="CURRT_VAL" >
				 					<input type="hidden" json="SPCL_TECH_NAME" id="SPCL_TECH_NAME" name="SPCL_TECH_NAME" >
				 					<input type="hidden" json="SPCL_TECH_TYPE" id="SPCL_TECH_TYPE" name="SPCL_TECH_TYPE"   value="非标工艺说明">
				 					<input type="hidden" id="CUST_TURN_VAL" name="CUST_TURN_VAL" json="CUST_TURN_VAL" />
				 					<input type="hidden" id="SPCL_TECH_NAME" name="SPCL_TECH_NAME" json="SPCL_TECH_NAME"/>
				 					<input type="hidden" name="colorFlag" value="1"/>
				    			</td>
				            	<td align="right" bgcolor="#FFF0F5">特殊工艺描述</td>
				            	<td style="height:50px;">
				                	<textarea cols="50" rows="5" id="EXPLAIN" onkeyup="countPrice()" onkeypress="if(event.keyCode==13) return false;" json="REMARK" name="REMARK"></textarea>
				                </td>
				          	</tr>
			    	</c:if>
	    	</table>
	    </div>
	    <div >
	    	<span style="color:red">说明：</span>
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
	    			<input style="text-align: center; width: 50px;margin-right: 10px;margin-bottom: 20px;" id="save" onclick="childSave()" type="button" value="确定" class="btn"/>
	    			<input style="text-align: center; width: 50px;margin-bottom: 20px;"   onclick="window.close()" id="close" type="button"  value="关闭" class="btn"/>
	    		</center>
	    		 
	    </div>
	    </div>
</body>
</html>
<script type="text/javascript">
</script>
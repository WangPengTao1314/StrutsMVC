<!--  
/**
 *@module 渠道管理-装修管理
 *@func   装修申请单维护
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
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/decorationapp/DecorationSQ_Edit_T.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	  function  load(){
	     var  RNVTN_PROP = $("#RNVTN_PROP").val();
	     var RNVTN_PROPs = document.getElementsByName("RNVTN_PROPs");
		 for(var i=0;i<RNVTN_PROPs.length;i++){
		     if(RNVTN_PROPs[i].value==RNVTN_PROP){
		        RNVTN_PROPs[i].checked=true;
		     }
		 }
		 var LOCAL_TYPE = $("#LOCAL_TYPE").val();
		 var LOCAL_TYPEs = document.getElementsByName("LOCAL_TYPEs");
	     for(var i=0;i<LOCAL_TYPEs.length;i++){
	          if(LOCAL_TYPEs[i].value==LOCAL_TYPE)
	          { 
	             LOCAL_TYPEs[i].checked=true;
	          }
	       }
	       
	       var BUSS_SCOPE = $("#BUSS_SCOPE").val();
	       var ScopeArrs = BUSS_SCOPE.split(",");
	       var BUSS_SCOPEs  = document.getElementsByName("BUSS_SCOPEs");
	       for(var j=0;j<BUSS_SCOPEs.length;j++){
              for(var i=0;i<ScopeArrs.length;i++){
	           if(typeof(ScopeArrs[i]) !="undefined"){
	             if(BUSS_SCOPEs[j].value==ScopeArrs[i]){
	              BUSS_SCOPEs[j].checked=true;
	             }
              }
	        }
	       }
	       var IS_STAD_FLAG = $("#IS_STAD_FLAG").val();
           var IS_STAD_FLAGs= document.getElementsByName("IS_STAD_FLAGs");
           for(var i=0;i<IS_STAD_FLAGs.length;i++){
              if(IS_STAD_FLAGs[i].value==IS_STAD_FLAG){
                 IS_STAD_FLAGs[i].checked=true;
              }
           }
           
	       var BEAR_WAY = $("#BEAR_WAY").val();
	       var BEAR_WAYs= document.getElementsByName("BEAR_WAYs");
	       for(var i=0;i<BEAR_WAYs.length;i++){
	           if(BEAR_WAYs[i].value==BEAR_WAY){
	               BEAR_WAYs[i].checked=true;
	           }
	       }
	       if( BEAR_WAY=="1"){
	              document.getElementById("showTr").style.display="block";
	              document.getElementById("showTr1").style.display="none";
	              return;
	       }
           if(BEAR_WAY=="2"){
              document.getElementById("showTr").style.display="none";
              document.getElementById("showTr1").style.display="block";
              return;
           }
	  }
	</script>
	<title>新增装修申请单</title>
</head>
<body class="bodycss1" onload="load()">
<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
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
		<input type="hidden" id="selectParams"  name="selectParams"  />
		<input type="hidden" id="selectParamsT" name="selectParamsT" />
		<input type="hidden" id="PVG_IS_STAD_FLAG" name="PVG_IS_STAD_FLAG" value="${pvg.PVG_IS_STAD_FLAG}"/>
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="18%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="20%" class="detail_content">
								<input json="RNVTN_REQ_NAME" name="RNVTN_REQ_NAME"
									id="RNVTN_REQ_NAME" type="text" seltarget="selLL" label="申请人"
									size="30" value="${rst.RNVTN_REQ_NAME}" autocheck="true" inputtype="string" readonly>
							</td>
							<td width="18%" align="right" class="detail_label">
								填报日期：
							</td>
							<td width="20%" class="detail_content">
								<input json="RNVTN_REQ_DATE" name="RNVTN_REQ_DATE"
									id="RNVTN_REQ_DATE" type="text" seltarget="selLL" label="填报日期"
									size="30" value="${date}" autocheck="true" inputtype="string"  readonly>
							</td>
							<td width="18%" align="right" class="detail_label">
								评估单号：
							</td>
							<td width="20%" class="detail_content">
							    <input json="OPEN_TERMINAL_REQ_ID" name="OPEN_TERMINAL_REQ_ID" json="OPEN_TERMINAL_REQ_ID" type="hidden" value="${rst.OPEN_TERMINAL_REQ_ID}" />
								<input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" value="${rst.TERM_NAME}" type="hidden" />
								<input json="OPEN_TERMINAL_REQ_NO" name="OPEN_TERMINAL_REQ_NO" value="${rst.OPEN_TERMINAL_REQ_NO}"
									id="OPEN_TERMINAL_REQ_NO" type="text" label="评估单号"
									size="30" value="" autocheck="true" inputtype="string" readonly>
							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="20%" class="detail_content">
							    <input json="AREA_ID"   name="AREA_ID"   id="AREA_ID" value="${rst.AREA_ID}" type="hidden" />
							    <input json="AREA_NO"   name="AREA_NO"   id="AREA_NO" value="${rst.AREA_NO}" type="hidden" />
							    <input json="AREA_MANAGE_ID"     name="AREA_MANAGE_ID"     id="AREA_MANAGE_ID"   value="${rst.AREA_MANAGE_ID}"   type="hidden" />
							    <input json="AREA_MANAGE_NAME"   name="AREA_MANAGE_NAME"   id="AREA_MANAGE_NAME" value="${rst.AREA_MANAGE_NAME}" type="hidden" />
								<input json="AREA_MANAGE_TEL"    name="AREA_MANAGE_TEL"    id="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}"  type="hidden" />
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text"
									label="所属战区"
								    size="30" inputtype="string" autocheck="true" value="${rst.AREA_NAME}" readonly />  
							</td>
							<td width="18%" align="right" class="detail_label">
								设计师：
							</td>
							<td width="20%" class="detail_content">
								<input json="DESIGN_PERSON" name="DESIGN_PERSON" id="DESIGN_PERSON" type="text"
									label="设计师" size="30" value="${rst.DESIGN_PERSON}" readonly>
 							</td>
							<td width="18%" align="right" class="detail_label">
								加盟商：
							</td>
							<td width="20%" class="detail_content">
							    <input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" type="hidden" value="${rst.CHANN_ID}"/>
							    <input json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" id="CHANN_PERSON_CON" type="hidden" value="${rst.CHANN_PERSON_CON}" />
							    <input json="CHANN_TEL" name="CHANN_TEL" id="CHANN_TEL" type="hidden" value="${rst.CHANN_TEL}" />
							    <input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" type="hidden" value="${rst.CHANN_NO}"/>
								<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text"
									label="加盟商" size="30" value="${rst.CHANN_NAME}" readonly>
							</td>
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="20%" class="detail_content">
							    <input json="TERM_ID" id="TERM_ID" name="TERM_ID" type="hidden" value="${rst.TERM_ID}" />
							    <input json="TERM_NO" id="TERM_NO" name="TERM_NO" type="text" value="${rst.TERM_NO}" size="30" label="门店编号" mustinput="true" autocheck="true" inputtype="string" readonly/>
							    <c:if test="${count eq 0}">
							    <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selectTerm()">
						        </c:if>
							</td>
							<td width="18%" align="right" class="detail_label">
								终端名称：
							</td>
							<td width="20%" class="detail_content">
							    <input json="TERM_NAME" name="TERM_NAME" json="TERM_NAME" type="text" size="30" value="${rst.TERM_NAME}" readonly/>
							</td>
							<td width="18%" align="right" class="detail_label">
								商场名称：
							</td>
							<td width="20%" class="detail_content">
							    <inpit json="SALE_STORE_ID"   name="SALE_STORE_ID"   id="SALE_STORE_ID"   type="hidden" value="${rst.SALE_STORE_ID}" />
								<input json="SALE_STORE_NAME" name="SALE_STORE_NAME" id="SALE_STORE_NAME" type="text"
									label="商场名称" value="${rst.SALE_STORE_NAME}"
								    size="30" inputtype="string" autocheck="true" READONLY/>  
							</td>
						</tr>
						<tr>
						   <td width="18%" align="right" class="detail_label">
								商场地址：
							</td>
							<td width="20%" class="detail_content">
							    <input name="ADDRESS" id="ADDRESS" json="ADDRESS" value="${rst.ADDRESS}" type="text"
							    size="30" label="商场地址" READONLY/>
 							</td>
						   <td width="18%" align="right" class="detail_label">
								商场位置：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							 <input type="hidden"     name="LOCAL_TYPE"   id="LOCAL_TYPE"  json="LOCAL_TYPE" value="${rst.LOCAL_TYPE}"/>
							 <input type="hidden"   name="LOCAL_TYPETt" id="LOCAL_TYPETt"  />
							 <input type="hidden"   name="LOCAL_TYPET"  id="LOCAL_TYPET"  />
							  <c:choose>
						          <c:when test="${pvg.PVG_LOCAL_TYPE eq 1}">
						             <c:forEach items="${result2}" var="rst2" varStatus="row">
								      <input type="radio" name="LOCAL_TYPEs" value="${rst2.DATA_DTL_CODE}" onclick="getLocalType()" />&nbsp;&nbsp;${rst2.LOCAL_TYPE}&nbsp;&nbsp;&nbsp;&nbsp;
								     </c:forEach>
						          </c:when>
						          <c:otherwise>
						             <c:forEach items="${result2}" var="rst2" varStatus="row">
								      <input type="radio" name="LOCAL_TYPEs" value="${rst2.DATA_DTL_CODE}" onclick="getLocalType()" disabled/>&nbsp;&nbsp;${rst2.LOCAL_TYPE}&nbsp;&nbsp;&nbsp;&nbsp;
								     </c:forEach>
				                  </c:otherwise>
					        </c:choose>  
 							</td>
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label">
								预算科目名称：
							</td>
							<td width="20%" class="detail_content">
   					             <input json="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" label="预算科目"
								    autocheck="true" inputtype="string" size="30"  value="${rst.BUDGET_ITEM_NAME}" readonly /> 
						    </td>
							<td width="18%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="20%" class="detail_content">
								<input json="PLAN_SBUSS_DATE" name="PLAN_SBUSS_DATE" id="PLAN_SBUSS_DATE" type="text"
									valueType="chinese:false" label="计划开业时间"
								    size="30" inputtype="string" autocheck="true" value="${rst.PLAN_SBUSS_DATE}" readonly />  
							</td>
							<td width="18%" align="right" class="detail_label">
								实际使用面积：
							</td>
							<td width="20%" class="detail_content">
								<input json="USE_AREA" name="USE_AREA" id="USE_AREA" type="text"
									label="实际使用面积" 
								    size="30" inputtype="string" autocheck="true" value="${rst.USE_AREA}" readonly />  
							</td>
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="20%" class="detail_content">
							  <input type="hidden"  name="RNVTN_PROPT" id="RNVTN_PROPT"/>
							  <input type="hidden" name="RNVTN_PROP"  id="RNVTN_PROP"  json="RNVTN_PROP" value="${rst.RNVTN_PROP}" />
							  <c:forEach items="${result1}" var="rst1" varStatus="row">
						      <input type="radio" name="RNVTN_PROPs" value="${rst1.DATA_DTL_CODE}" onclick="getRnvtnProp()" disabled/>&nbsp;&nbsp;${rst1.RNVTN_PROP}&nbsp;&nbsp;&nbsp;&nbsp;
						      </c:forEach>
 							</td>
							<td width="18%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							<input type="hidden" name="BUSS_SCOPET" id="BUSS_SCOPET" />
							<input type="hidden" name="BUSS_SCOPE"  id="BUSS_SCOPE" json="BUSS_SCOPE" value="${rst.BUSS_SCOPE}"/>
							<c:forEach items="${result}" var="rst" varStatus="row">
						      <input type="checkbox" name="BUSS_SCOPEs" value="${rst.DATA_DTL_CODE}" onclick="getBussScope()" disabled/>&nbsp;&nbsp;${rst.BUSS_SCOPE}&nbsp;&nbsp;&nbsp;&nbsp;
						    </c:forEach>
							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								商场综合情况分析：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input id="ATT_PATH" name="ATT_PATH" json="ATT_PATH" value="${rst.ATT_PATH}" type="hidden" />
								<input id="SALE_STORE_ANALYSE"  name="SALE_STORE_ANALYSE" json="SALE_STORE_ANALYSE" type="hidden" />
							</td>
						 </tr>
						 <tr>
							<td width="18%" align="right" class="detail_label">
								现场照片：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input id="XIANCHANGPIC" name="XIANCHANGPIC" json="XIANCHANGPIC" type="hidden"/><input type="hidden" id="count" name="count" json="count" value="${count}" />
 							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								商场综合平面图：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input id="MARKETPIC" name="MARKETPIC" json="MARKETPIC" type="hidden"/> 
 							</td>
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label">报销标准：</td>
							<td width="20%" class="detail_content" colspan="5">
							  <input type="hidden" name="IS_STAD_FLAG" id="IS_STAD_FLAG" json="IS_STAD_FLAG" value="${rst.IS_STAD_FLAG}" />
							  <c:choose>  
							    <c:when test="${pvg.PVG_IS_STAD_FLAG eq 1 }">
							       <input type="radio" name="IS_STAD_FLAGs" checked="true" value="1" onclick="chk()"/>一、二季度&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="IS_STAD_FLAGs" value="2" onclick="chk()"/>三、四季度
							    </c:when>  
							    <c:otherwise>  
							       <input type="radio" name="radio" json="IS_STAD_FLAG"  id="IS_STAD_FLAG" disabled/>一、二季度&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" json="IS_STAD_FLAG" id="IS_STAD_FLAG" name="radio" disabled/>三、四季度
							    </c:otherwise>  
							   </c:choose>
							</td> 
						</tr>
						<tr id="trT">
						    <td width="18%" align="right" class="detail_label"></td>
							<td width="20%" class="detail_content">
							   总额： <input type="text" name="REIT_AMOUNT" id="REIT_AMOUNT" json="REIT_AMOUNT" value="${rst.REIT_AMOUNT}" label="总额" READONLY/>  
							</td> 
							<td width="18%" align="right" class="detail_label">报销金额(元/平米)：</td>
							<td width="20%" class="detail_content">
							    <c:if test="${count eq 1}">
							     <input type="text" name="REIT_AMOUNT_PS" id="REIT_AMOUNT_PS" json="REIT_AMOUNT_PS" value="${rst.REIT_AMOUNT_PS}" inputtype="string" autocheck="true" label="报销金额" onchange="getReitAmount()"   mustinput="true" />
							   </c:if>
							   <c:if test="${count eq 0}">
							     <input type="text" name="REIT_AMOUNT_PS" id="REIT_AMOUNT_PS" json="REIT_AMOUNT_PS" value="${rst.REIT_AMOUNT_PS}" inputtype="string" autocheck="true" label="报销金额" onchange="getReitAmount()" READONLY/>
							   </c:if>
							</td>
							<td width="18%" align="right" class="detail_label">金额说明：</td>
							<td width="20%" class="detail_content">
								<c:choose>
							          <c:when test="${pvg.PVG_AMOUNT_DESC eq 1}">
							             <input type="text" name="AMOUNT_DESC" id="AMOUNT_DESC" json="AMOUNT_DESC" value="${rst.AMOUNT_DESC}" label="金额说明" />
							          </c:when>
							          <c:otherwise>
							             <input type="text" name="AMOUNT_DESC" id="AMOUNT_DESC" json="AMOUNT_DESC" value="${rst.AMOUNT_DESC}" label="金额说明" READONLY/>
					                  </c:otherwise>
						        </c:choose>  
							</td> 
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								报销承担方式：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input id="BEAR_WAY" name="BEAR_WAY" json="BEAR_WAY" value="${rst.BEAR_WAY}" type="hidden" />
							    <c:choose>  
								   <c:when test="${pvg.PVG_BEAR_WAY eq 1 }">
								       <input type="radio" name="BEAR_WAYs" value="1" onclick="check()"/>三次报销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="BEAR_WAYs"  value="2" onclick="check()"/>特殊政策&nbsp;&nbsp;&nbsp;&nbsp;
 								   </c:when>  
								    <c:otherwise>  
								       <input type="radio" name="BEAR_WAYs" disabled onclick="check()"/>三次报销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="BEAR_WAYs"   disabled onclick="check()"/>特殊政策&nbsp;&nbsp;&nbsp;&nbsp;
 								    </c:otherwise>  
							   </c:choose>
							<td>
						</tr>
						<tr id="showTr" style="display:none;">
						    <td width="18%" align="right" class="detail_label">
							</td>
							<td width="20%" class="detail_content" colspan="5">
							   <textarea json="REMARKTt" name="REMARKTt" id="REMARKTt" readonly label="三次报销"
									rows="3" cols="100%" autocheck="true" inputtype="string"   maxlength="250">装修完成验收合格报50%，满1年25%，满2年25%</textarea>
							</td>
						</tr>
						<tr id="showTr1" style="display:none;">
						    <td width="18%" align="right" class="detail_label">
							</td>
							<td width="20%" class="detail_content" colspan="5">
							   <textarea json="SPEC_CONTENT" name="SPEC_CONTENT" id="SPEC_CONTENT" rows="3" cols="100%" autocheck="true" inputtype="string"   maxlength="250" value="${rst.SPEC_CONTENT}">${rst.SPEC_CONTENT}</textarea>
							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								特殊说明：
							</td>
							<td width="20%" class="detail_content" colspan="5">
								<textarea json="REMARK" name="REMARK" id="REMARK" readonly label="特殊说明"
									rows="15" cols="100%" autocheck="true" inputtype="string"   maxlength="250">${rst.REMARK}</textarea>
							</td>
							</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	   var count = $("#count").val();
	   var PVG_IS_STAD_FLAG = $("#PVG_IS_STAD_FLAG").val();
	   if(PVG_IS_STAD_FLAG=="1"){
	      document.getElementById("trT").style.display="block";
	   }else{
	      document.getElementById("trT").style.display="none";
	   }
	   var path = $("#ATT_PATH").val();
       var array = path.split(";");
       $("#SALE_STORE_ANALYSE").val(array[0]);
       if(count==1){
         uploadFile('SALE_STORE_ANALYSE', 'SAMPLE_DIR', true,true,true);
       }else{
         displayDownFile('SALE_STORE_ANALYSE','SAMPLE_DIR',false,false);
       }
       $("#XIANCHANGPIC").val(array[1]);
       if(count==1){
          uploadFile('XIANCHANGPIC', 'SAMPLE_DIR', true,true,true);
       }else{
          displayDownFile('XIANCHANGPIC','SAMPLE_DIR',false,false);
       }
       $("#MARKETPIC").val(array[2]);
       displayDownFile('MARKETPIC','SAMPLE_DIR',false,false);
	</script>
   </div>
</body>

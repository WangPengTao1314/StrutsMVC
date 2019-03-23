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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/decorationapp/DecorationSQ_Edit.js"></script>
	<script type="text/javascript"
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	    function  sel(){
	       selCommon('BS_168', false, 'OPEN_TERMINAL_REQ_NO', 'OPEN_TERMINAL_REQ_NO', 'forms[0]','TERM_NO,OPEN_TERMINAL_REQ_ID,OPEN_TERMINAL_REQ_NO,AREA_ID,AREA_NO,AREA_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,SALE_STORE_NAME,ADDRESS,PLAN_SBUSS_DATE,USE_AREA,LOCAL_TYPET,BUSS_SCOPET,RNVTN_PROP,TERM_NAME,AREA_MANAGE_TEL,TERM_ID', 'TERM_NO,OPEN_TERMINAL_REQ_ID,OPEN_TERMINAL_REQ_NO,AREA_ID,AREA_NO,AREA_NAME,CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P,AREA_MANAGE_ID,AREA_MANAGE_NAME,SALE_STORE_NAME,ADDRESS,BEG_SBUSS_DATE,BUSS_AREA,LOCAL_TYPE,BUSS_SCOPE,RNVTN_PROP,TERM_NAME,AREA_MANAGE_TEL,TERM_ID', 'selectParams')
	       var LOCAL_TYPET = $("#LOCAL_TYPET").val();
	       var BUSS_SCOPET = $("#BUSS_SCOPET").val();
	       var RNVTN_PROPT = $("#RNVTN_PROP").val();
	        $.ajax({
		    url: "decorationapp.shtml?action=toQueryLocalType",
			type:"POST",
			dataType:"text",
			data:{"LOCAL_TYPE":LOCAL_TYPET,"BUSS_SCOPE":BUSS_SCOPET},
			complete: function (xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					  var result = jsonResult.data;
					  $("#LOCAL_TYPE").val(result.LOCAL_TYPE);
					  var LOCAL_TYPE = document.getElementsByName("LOCAL_TYPEs");
					  for(var i=0;i<LOCAL_TYPE.length;i++){
					     if(LOCAL_TYPE[i].value==result.LOCAL_TYPE){
					        LOCAL_TYPE[i].checked=true;
					     }
					  }
					  
					  var result = jsonResult.data;
					  $("#BUSS_SCOPE").val(result.BUSS_SCOPE);
					  var BUSS_SCOPE = document.getElementsByName("BUSS_SCOPEs");
					  for(var i=0;i<BUSS_SCOPE.length;i++){
					     if(BUSS_SCOPE[i].value==result.BUSS_SCOPE){
					        BUSS_SCOPE[i].checked=true;
					     }
					  }
					  
					  var RNVTN_PROP = document.getElementsByName("RNVTN_PROPs");
					  for(var i=0;i<RNVTN_PROP.length;i++){
					     if(RNVTN_PROP[i].value==RNVTN_PROPT){
					        RNVTN_PROP[i].checked=true;
					     }
					  }
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		 }); 
	    }
	    
      function  getItems(){
	      var CHANN_NAME = $("#CHANN_NAME").val();
	      if(CHANN_NAME!=""){
		      var params = "STATE = '启用' and DEL_FLAG='0' and  CHANN_ID IN ${CHANN_ID} and CHANN_ID='"+$("#CHANN_ID").val()+"'";
		      document.getElementById("selectParamsByItem").value = params;
		      selCommon('BS_197', false, 'BUDGET_ITEM_NAME', 'BUDGET_ITEM_NAME', 'forms[0]','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_TYPE,BUDGET_ITEM_NAME,BUDGET_QUOTA_ID','BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_TYPE,BUDGET_ITEM_NAME,BUDGET_QUOTA_ID', 'selectParamsByItem')
		   }else{
		     parent.showErrorMsg("请先选择加盟商!"); 
		     return;
		   }
	 }
	</script>
	<title>新增装修申请单</title>
</head>
<body class="bodycss1">
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
		<input type="hidden" name="selectParamsByItem"  id="selectParamsByItem" value=""/>
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
									size="30" value="${userName}" autocheck="true" inputtype="string"  mustinput="true"  readonly>
							</td>
							<td width="18%" align="right" class="detail_label">
								填报日期：
							</td>
							<td width="20%" class="detail_content">
								<input json="RNVTN_REQ_DATE" name="RNVTN_REQ_DATE"
									id="RNVTN_REQ_DATE" type="text" seltarget="selLL" label="填报日期"
									size="30" value="${date}" autocheck="true" inputtype="string"  mustinput="true"  readonly>
							</td>
							<td width="18%" align="right" class="detail_label">
								评估单号：
							</td>
							<td width="20%" class="detail_content">
							    <input json="OPEN_TERMINAL_REQ_ID" name="OPEN_TERMINAL_REQ_ID" json="OPEN_TERMINAL_REQ_ID" type="hidden" value="${rst.OPEN_TERMINAL_REQ_ID}" />
								<input json="OPEN_TERMINAL_REQ_NO" name="OPEN_TERMINAL_REQ_NO"
									id="OPEN_TERMINAL_REQ_NO" type="text" seltarget="selLL" label="评估单号"
									size="30" value="" autocheck="true" inputtype="string"  mustinput="true"  value="${rst.OPEN_TERMINAL_REQ_NO}" readonly>
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
								    onClick="sel()">
							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								所属战区：
							</td>
							<td width="20%" class="detail_content">
							    <input json="AREA_ID"   name="AREA_ID"   id="AREA_ID" value="${rst.AREA_ID}" type="hidden" />
							    <input json="AREA_NO"   name="AREA_NO"   id="AREA_NO" value="${rst.AREA_NO}" type="hidden" />
							    <input json="AREA_MANAGE_ID"     name="AREA_MANAGE_ID"      id="AREA_MANAGE_ID"   value="${rst.AREA_MANAGE_ID}"   type="hidden" />
							    <input json="AREA_MANAGE_NAME"   name="AREA_MANAGE_NAME"    id="AREA_MANAGE_NAME" value="${rst.AREA_MANAGE_NAME}" type="hidden" />
								<input json="AREA_MANAGE_TEL"    name="AREA_MANAGE_TEL"     id="AREA_MANAGE_TEL"  value="${rst.AREA_MANAGE_TEL}"  type="hidden" />
								<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text"
									label="所属战区"
								    size="30" inputtype="string" autocheck="true" value="${rst.AREA_NAME}" readonly />  
							</td>
							<td width="18%" align="right" class="detail_label">
								设计师：
							</td>
							<td width="20%" class="detail_content">
							    <input json="DESIGN_ID" name="DESIGN_ID" id="DESIGN_ID" type="hidden" value="${rst.DESIGN_ID}" />
								<input json="DESIGN_PERSON" name="DESIGN_PERSON" id="DESIGN_PERSON" type="text"
									label="设计师" size="30" value="${rst.DESIGN_PERSON}" readonly/>&nbsp;<font color='red'>*</font>
								<img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('BS_170', false, 'DESIGN_PERSON', 'XM', 'forms[0]','DESIGN_PERSON,DESIGN_ID', 'XM,RYXXID', 'selectParamsT')">
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
							 <input type="hidden"     name="LOCAL_TYPE"   id="LOCAL_TYPE"  json="LOCAL_TYPE" />
							 <input type="hidden"   name="LOCAL_TYPETt" id="LOCAL_TYPETt"  />
							 <input type="hidden"   name="LOCAL_TYPET"  id="LOCAL_TYPET"  />
							 <c:forEach items="${result2}" var="rst2" varStatus="row">
						      <input type="radio" name="LOCAL_TYPEs" value="${rst2.DATA_DTL_CODE}" onclick="getLocalType()"/>&nbsp;&nbsp;${rst2.LOCAL_TYPE}&nbsp;&nbsp;&nbsp;&nbsp;
						     </c:forEach>&nbsp;&nbsp;<font color='red'>*</font>
 							</td>
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label">
								预算科目名称：
							</td>
							<td width="20%" class="detail_content">
							     <input json="BUDGET_QUOTA_ID"  name="BUDGET_QUOTA_ID"  id="BUDGET_QUOTA_ID"  type="hidden" value="${rst.BUDGET_QUOTA_ID}">
							     <input json="BUDGET_ITEM_ID"   name="BUDGET_ITEM_ID"   id="BUDGET_ITEM_ID"   type="hidden" value="${rst.BUDGET_ITEM_ID}"/>
		   					     <input json="BUDGET_ITEM_NO"   name="BUDGET_ITEM_NO"   id="BUDGET_ITEM_NO"   type="hidden" value="${rst.BUDGET_ITEM_NO}"/>
		   					     <input json="BUDGET_ITEM_TYPE" name="BUDGET_ITEM_TYPE" id="BUDGET_ITEM_TYPE" type="hidden" value="${rst.BUDGET_ITEM_TYPE}" />
   					             <input json="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME" id="BUDGET_ITEM_NAME" label="预算科目"
								    autocheck="true" inputtype="string" size="30"  value="${rst.BUDGET_ITEM_NAME}" readonly mustinput="true" /> 
							     <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="getItems()">
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
						      <input type="radio" name="RNVTN_PROPs" value="${rst1.DATA_DTL_CODE}" onclick="getRnvtnProp()"/>&nbsp;&nbsp;${rst1.RNVTN_PROP}&nbsp;&nbsp;&nbsp;&nbsp;
						    </c:forEach>&nbsp;&nbsp;<font color='red'>*</font>
 							</td>
							<td width="18%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="20%" class="detail_content" colspan="3">
							<input type="hidden" name="BUSS_SCOPET" id="BUSS_SCOPET" />
							<input type="hidden" name="BUSS_SCOPE"  id="BUSS_SCOPE" json="BUSS_SCOPE" value="${rst.BUSS_SCOPE}"/>
							<c:forEach items="${result}" var="rst" varStatus="row">
						      <input type="checkbox" name="BUSS_SCOPEs" value="${rst.DATA_DTL_CODE}" onclick="getBussScope()"/>&nbsp;&nbsp;${rst.BUSS_SCOPE}&nbsp;&nbsp;&nbsp;&nbsp;
						    </c:forEach>&nbsp;&nbsp;<font color='red'>*</font>
							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								商场综合情况分析：
							</td>
							<td width="20%" class="detail_content" colspan="5">
								<input id="SALE_STORE_ANALYSE"  name="SALE_STORE_ANALYSE" json="SALE_STORE_ANALYSE" type="hidden" />
							</td>
						 </tr>
						 <tr>
							<td width="18%" align="right" class="detail_label">
								现场照片：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							    <input id="XIANCHANGPIC" name="XIANCHANGPIC" json="XIANCHANGPIC" type="hidden"/>
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
						       <input type="radio" name="radio" json="IS_STAD_FLAG"  id="IS_STAD_FLAG" value="1" checked="true"/>一、二季度&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" json="IS_STAD_FLAG" id="IS_STAD_FLAG" name="radio" value="2"/>三、四季度
							</td> 
						</tr>
						<tr>
						    <td width="18%" align="right" class="detail_label"></td>
							<td width="20%" class="detail_content">
							   总额： <input type="text" name="REIT_AMOUNT" id="REIT_AMOUNT" json="REIT_AMOUNT" value="${rst.REIT_AMOUNT}" label="总额" READONLY />
							</td> 
							<td width="18%" align="right" class="detail_label">报销金额(元/平米)：</td>
							<td width="20%" class="detail_content" >
							   <input type="text" name="REIT_AMOUNT_PS" id="REIT_AMOUNT_PS" json="REIT_AMOUNT_PS" value="${rst.REIT_AMOUNT_PS}" inputtype="string" autocheck="true" label="报销金额" onchange="getReitAmount()" READONLY/>
							</td> 
							<td width="18%" align="right" class="detail_label">金额说明：</td>
							<td width="20%" class="detail_content" colspan="3">
							   <input type="text" name="AMOUNT_DESC" id="AMOUNT_DESC" json="AMOUNT_DESC" value="${rst.AMOUNT_DESC}" inputtype="string" autocheck="true" label="金额说明" READONLY size="30"/>
							</td> 
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								报销承担方式：
							</td>
							<td width="20%" class="detail_content" colspan="5">
							  <input type="radio" name="BEAR_WAY" id="BEAR_WAY"  json="BEAR_WAY" value="1" checked="true" onclick="check()"/>三次报销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="BEAR_WAY" id="BEAR_WAY1" json="BEAR_WAY" value="2" onclick="check()"/>特殊政策&nbsp;&nbsp;&nbsp;&nbsp;
							<td>
						</tr>
						<tr id="showTr" style="display:block;">
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
							   <textarea json="SPEC_CONTENT" name="SPEC_CONTENT" id="SPEC_CONTENT" rows="3" cols="100%" autocheck="true" inputtype="string"   maxlength="250"></textarea>
							</td>
						</tr>
						<tr>
							<td width="18%" align="right" class="detail_label">
								特殊说明：
							</td>
							<td width="20%" class="detail_content" colspan="5">
								<textarea json="REMARK" name="REMARK" id="REMARK" readonly label="特殊说明"
									rows="15" cols="100%" autocheck="true" inputtype="string"   maxlength="1000">1、新开店老店重装定义：在同一商场内，同一品牌门店进行版本更新或更换店面位置均为老店翻新；商场内新设品牌门店为新开店，不足300平米的场地，条件允许情况下可开设两店，但门店数只能统计为1家店 。   
2、报销须知：公司将严格按照颁布的门店装修考核标准对门店精致化综合情况进行考核，对不符合相关规定的考核项目进行限期整改及相应处罚， 对于违反不予报销项目，未在规定时间改善者，取消报销资格。
3、在本公司经营的卖场中放置非公司产品：如软床、床垫、床头柜等主体经营产品，经查实立即停止供货（一件以上均同等形式处理），公司即刻取消装修补贴报销资格，追讨已支付的装修补贴，并取消一切优惠政策。
4、在卖场中经营其他非公司辅助销售产品：如电视柜、妆台、衣柜、休闲沙发和未经公司书面批准过的促销品等物品的，经公司查实，将按情节轻重予以5000~10000元处罚，并必须在三天内清除完毕，一年内二次发现此类问题，也将实行停止供货，取消装修补贴报销资格，追讨已支付的装修补贴；并取消一切优惠政策。（卖场中只允许出现用于洽谈之用的桌椅或沙发一套，不许经营标价买卖。）
5、无论加盟商签字与否，报销行为说明加盟商对以上条款的完全认同。
								</textarea>
							</td>
					    </tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	   uploadFile('SALE_STORE_ANALYSE', 'SAMPLE_DIR', true,true,true);
	   uploadFile('XIANCHANGPIC', 'SAMPLE_DIR', true,true,true);
	   uploadFile('MARKETPIC', 'SAMPLE_DIR', true,true,true);
	</script>
   </div>
</body>

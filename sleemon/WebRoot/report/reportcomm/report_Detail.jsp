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
  </head>
  
  <body>
    <body class="bodycss1">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" style="margin-top: -20px;">
			<tr>   
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
						   <td> 
						      基础信息 
						   </td>
						</tr>
						<tr>
							<td width="10%" align="right" class="detail_label">
								流程编号：
							</td>
							<td width="18%" class="detail_content">
								 ${rst.MKM_DAY_RPT_NO}
							</td>
							<td width="10%" align="right" class="detail_label">
								拜访人：
							</td>
							<td width="5%" class="detail_content">
								 ${rst.MKM_NAME}
							</td>
							<td width="10%" align="right" class="detail_label">
								拜访日期：
							</td>
							<td width="15%" class="detail_content" colspan="3">
								 ${rst.VST_DATE}
							</td>
					     </tr>
					     <tr>
					        <td width="5%" align="right" class="detail_label">
								战区：
							</td>
							<td width="18%" class="detail_content">
								${rst.WAREA_NAME}
							</td>
							<td width="10%" align="right" class="detail_label">
								日期：
							</td>
							<td width="15%" class="detail_content">
							    ${rst.REPORT_DATE}
							</td>
							<td width="5%" align="right" class="detail_label">
								状态：
							</td>
							<td width="15%" class="detail_content" colspan="3">
								 ${rst.STATE}
							</td>
						</tr>
						 
						<tr>
						  <td width="12%" align="right" class="detail_label">
								出差途中：
						  </td>
						  <td class="detail_content" colspan="7">
						      ${rst.ON_TRIP}
						  </td>
						</tr>
						<tr>
						  <td width="12%" align="right" class="detail_label">
								公司总部公务处理：
						  </td>
						  <td class="detail_content" colspan="7">
						      ${rst.CORP_BUSS_DEAL}
						  </td>
						</tr>
						<tr>
						  <td width="12%" align="right" class="detail_label">
								调休：
						  </td>
						  <td class="detail_content" colspan="7">
						      ${rst.WAKE_OFF}
						  </td>
						</tr>
					
						<tr>
						   <td> 
						       加盟商拜访
						   </td>
						</tr>
						<tr>
						   <td width="10%" align="left" class="detail_label">
						       加盟商编号:
						   </td>
						   <td width="18%" class="detail_content" colspan="3">
								 ${rstcv.CHANN_NO} 
							</td>
							 <td width="10%" align="left" class="detail_label">
						            加盟商名称:
						   </td>
						   <td width="15%" class="detail_content" colspan="3">
								 ${rstcv.CHANN_NAME} 
							</td>
						</tr>
						<tr>
						     <td width="12%" align="right" class="detail_label" rowspan="2">
								库存信息：
							</td>
							<td width="10%" align="left" class="detail_label">
								床垫： 
							</td>
						    <td width="10%" class="detail_label">
								<span style="float:left;">${rstcv.MATT_AMOUNT}</span>
								<input type="hidden" id="MATT_AMOUNT" name="MATT_AMOUNT" value="${rstcv.MATT_AMOUNT}">
								软床：
							</td>
							<td width="15%" class="detail_content">
								 ${rstcv.SOFT_BED_AMOUNT}<input type="hidden" name="SOFT_BED_AMOUNT" id="SOFT_BED_AMOUNT" value="${rstcv.SOFT_BED_AMOUNT}">
							</td>
							<td width="10%" align="right" class="detail_label">
								其他：  ${rstcv.OTHER_AMOUNT}<input type="hidden" name="OTHER_AMOUNT" id="OTHER_AMOUNT" value="${rstcv.OTHER_AMOUNT}" />
							</td>
							<td width="10%" align="right" class="detail_label">
								合计：
							</td>
							<td width="15%" class="detail_content">
							    <script type="text/javascript">
							         var MATT_AMOUNT = $("#MATT_AMOUNT").val();
							         if(MATT_AMOUNT !=""){
							           MATT_AMOUNT = parseInt(MATT_AMOUNT);
							         } else{
							           MATT_AMOUNT = 0;
							         }
							         
							         var SOFT_BED_AMOUNT = $("#SOFT_BED_AMOUNT").val();
							         if(SOFT_BED_AMOUNT !=""){
							           SOFT_BED_AMOUNT = parseInt(SOFT_BED_AMOUNT);
							         }else{
							           SOFT_BED_AMOUNT = 0;
							         }
							         
							         var OTHER_AMOUNT = $("#OTHER_AMOUNT").val();
							         if(OTHER_AMOUNT !=""){
							            OTHER_AMOUNT = parseInt(OTHER_AMOUNT);
							         }else{
							            OTHER_AMOUNT = 0
							         }
							         var TOTAL_AMOUNT = MATT_AMOUNT+SOFT_BED_AMOUNT+OTHER_AMOUNT;
							         document.write(TOTAL_AMOUNT);
							    </script>
							</td>
						</tr>
						<tr>
							<td width="10%" align="left" class="detail_label">
								附件： 
							</td>
							<td width="15%" class="detail_content" colspan="8">
								<input type="hidden" name="STORE_ATT" id="STORE_ATT" json="STORE_ATT" value="${rstcv.STORE_ATT}" />
							</td>
						</tr>
						<tr>
						   <td width="10%" align="right" class="detail_label">
								加盟商问题：
							</td>
							<td width="15%" class="detail_content" colspan="3">
								 ${rstcv.CHANN_PROBLEM}
							</td>
							<td width="10%" align="right" class="detail_label">
								改善计划：
							</td>
							<td width="15%" class="detail_content" colspan="3">
								 ${rstcv.IMPRV_PLAN}
							</td>
					     </tr>
					     <tr>
							<td width="10%" align="right" class="detail_label">
								竞品信息：
							</td>
							<td width="15%" class="detail_content" colspan="3">
								 ${rstcv.COMPET_PRODUCT}
							</td>
							<td width="10%" align="right" class="detail_label">
								支持需求：
							</td>
							<td width="15%" class="detail_content" colspan="3">
								 ${rstcv.SUPPORT_REQ}
							</td>
						</tr>
							 
						<tr>
						   <td> 
						       推广活动推进
						   </td>
						</tr>
						<tr>
								<td width="10%" align="right" class="detail_label" rowspan="4">
									活动前期准备
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 启动时间:
									${rstact.BEG_DATE}
								</td>
								<td width="5%" align="left" class="detail_label">
									活动形式:
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstact.ACTV_STYLE}
								</td>
								<td width="5%" align="left" class="detail_label">
									活动主题:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									${rstact.ACTV_TITLE}
								</td>
							</tr>
							
							<tr>
							    <td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 落地时间:
									${rstact.AGREE_DATE}
								</td>
								<td width="5%" align="left" class="detail_label">
									活动预算费用:
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstact.ACTV_PRMT_COST}
								</td>
								<td width="5%" align="left" class="detail_label">
									落地地点:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									${rstact.AGREE_ADDR}
								</td>
							</tr>
							<tr>
								<td width="10%" align="left" class="detail_content">
									&nbsp;&nbsp; 预计成交单数:
									${rstact.ADVC_DEAL_BILL_NUM}
								</td>
								<td width="15%" align="left" class="detail_label">
									预计成交额:
								</td>
								<td width="5%" align="left" class="detail_content" colspan="5">
									${rstact.ADVC_DEAL_BILL_AMOUNT}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="8">
									&nbsp;&nbsp; 附件: &nbsp;&nbsp;&nbsp;
									<input type="hidden" name="ACTV_PRMT_ATT" id="ACTV_PRMT_ATT"
										json="ACTV_PRMT_ATT" value="${rstact.ACTV_PRMT_ATT}" />
								</td>
							</tr>
							<tr>
								<td width="10%" align="center" class="detail_label">
									活动推进
								</td>
								<td width="15%" align="left" class="detail_content">
									 &nbsp;&nbsp;推进日期:
									${rstact.FORWORD_DATE}
								</td>
								<td width="5%" align="left" class="detail_label">
									售卡数量:
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstact.SALE_CARD_NUM}
								</td>
								<td width="5%" align="left" class="detail_label">
									其他:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									${rstact.OTHER_REMARK}
								</td>
							</tr>
							
							<tr>
								<td width="10%" align="center" class="detail_label" rowspan="2">
									活动结果
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 售卡数量: &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
									${rstact.SALE_CARD_NUM_END}
								</td>
								<td width="5%" align="left" class="detail_label">
									签到卡数:
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstact.CHECK_CARD_NUM}
								</td>
								<td width="5%" align="left" class="detail_label">
									成交单数:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									${rstact.DEAL_BILL_NUM}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp; 预计成交金额:
									${rstact.ADVC_DEAL_AMOUNT}<input type="hidden" id="ADVC_DEAL_AMOUNT" value="${rstact.ADVC_DEAL_AMOUNT}" />
								</td>
								<td width="5%" align="left" class="detail_label">
									实际投入费用:
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstact.COST_AMOUNT}<input type="hidden" id="COST_AMOUNT" value="${rstact.COST_AMOUNT}" />
								</td>
								<td width="5%" align="left" class="detail_label">
									费效比:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									${rstact.RATE_AMOUNT}
									<script type="text/javascript">
									     var ADVC_DEAL_AMOUNT = $("#ADVC_DEAL_AMOUNT").val();
										 if(ADVC_DEAL_AMOUNT ==""){
										     ADVC_DEAL_AMOUNT = 0;
										 }
										 var COST_AMOUNT = $("#COST_AMOUNT").val();
										 if(COST_AMOUNT ==""){
										     COST_AMOUNT  = 0;
										 }
										 if(ADVC_DEAL_AMOUNT !="" && COST_AMOUNT !=""){
										    var RATE_AMOUNT = (ADVC_DEAL_AMOUNT/COST_AMOUNT).toFixed(2);
										    document.write(RATE_AMOUNT);
										 } 
									</script>
								</td>
							</tr>
							<tr>
								<td border="blod;">
									分销商开发与拜访
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="3">
									合作分销商
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;分销商编号:
									 &nbsp;&nbsp;&nbsp;&nbsp;  
									 ${rstdis.DISTRIBUTOR_NO}
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商名称:
								</td>
								<td width="20%" class="detail_content" colspan="5">
									${rstdis.DISTRIBUTOR_NAME}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;分销主要问题:&nbsp;&nbsp;&nbsp;
									 ${rstdis.MAIN_PROBLEM}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;解决方案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									${rstdis.SOLUTION}
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="3">
									意向分销商
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;分销商类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									${rstdis.WILL_DISTRIBUTOR_TYPE}
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商场名称:
								</td>
								<td width="20%" class="detail_content">
									${rstdis.WILL_SALE_STORE_NAME}
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商姓名:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									${rstdis.WILL_DISTRIBUTOR_NAME}
								</td>
						     </tr>
						     <tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;分销商电话:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									${rstdis.WILL_DISTRIBUTOR_TEL}
								</td>
								<td width="15%" align="left" class="detail_label">
									分销商地址:
								</td>
								<td width="20%" class="detail_content">
									${rstdis.WILL_DISTRIBUTOR_ADDRESS}
								</td>
								<td width="15%" align="left" class="detail_label">
									经营品牌:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									${rstdis.WILL_DISTRIBUTOR_BUSS_BRAND}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;主营品类:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									${rstdis.WILL_DISTRIBUTOR_BUSS_CLASS}
								</td>
								<td width="15%" align="left" class="detail_label">
									意向程度:
								</td>
								<td width="20%" class="detail_content" colspan="5">
									${rstdis.WILL_DEGREE}
								</td>
							</tr>
							<tr>
								<td border="blod;">
									"1+N"合作商开发与拜访
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="3">
									合作客户
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;合作渠道编号:
									 ${rstcoo.DISTRIBUTOR_NO}
								</td>
								<td width="15%" align="left" class="detail_label">
									合作渠道名称:
								</td>
								<td width="20%" class="detail_content" colspan="5">
									${rstcoo.DISTRIBUTOR_NAME}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;合作主要问题:&nbsp;&nbsp;&nbsp;
									${rstcoo.MAIN_PROBLEM}
								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;解决方案:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									${rstcoo.SOLUTION}
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="detail_label" rowspan="4">
									意向合作客户
								</td>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;合作商场类型:&nbsp;&nbsp;
									${rstcoo.MARKET_TYPE}
								</td>
								<td width="15%" align="left" class="detail_label">
									合作商场名称:
								</td>
								<td width="20%" class="detail_content">
									${rstcoo.WILL_SALE_STORE_NAME}
								</td>
								<td width="15%" align="left" class="detail_label">
									合作客户姓名:
								</td>
								<td width="20%" class="detail_content" colspan="3">
								   ${rstcoo.CUST_NAME}
 								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;合作客户电话:&nbsp;&nbsp;&nbsp; 
									${rstcoo.CUST_TEL}
								</td>
								<td width="15%" align="left" class="detail_label">
									商场地址:
								</td>
								<td width="20%" class="detail_content" colspan="5">
									${rstcoo.MARKET_ADDRESS}
								</td>
							</tr>
							  
							<tr>
							    <td width="15%" align="left" class="detail_content">
									&nbsp;&nbsp;经营品牌:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;
									${rstcoo.MARKET_BUSS_BRAND}
								</td>
								<td width="15%" align="left" class="detail_label">
									主营品类:
								</td>
								<td width="20%" class="detail_content">
									${rstcoo.MARKET_BUSS_CLASS}
								</td>
								<td width="15%" align="left" class="detail_label">
									意向程度:
								</td>
								<td width="20%" class="detail_content" colspan="3">
									${rstcoo.WILL_DEGREE}
								</td> 
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_content" colspan="7">
									&nbsp;无意向原因:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									${rstcoo.NO_WILL_RESON}
								</td>
							</tr>  
							 
							<tr>
								<td border="blod;">
									导购员培训
								</td>
							</tr>
							<tr>
							  <td width="15%" align="right" class="detail_label">
									导购员培训人数
								</td>
								<td width="15%" align="left" class="detail_content">
								  ${rstshop.TRAN_NUM}
 								</td>
								<td width="15%" align="left" class="detail_label">
									培训主题:
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstshop.TRAN_TITLE}
								</td>
								<td width="15%" align="left" class="detail_label">
									培训形式:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="3">
									${rstshop.TRAN_TYPE}
								</td>
							</tr>
							<tr>
							  <td width="15%" align="right" class="detail_label">
									培训时间
								</td>
								<td width="15%" align="left" class="detail_content">
									${rstshop.TRAN_DATE}
								</td>
								<td width="15%" align="left" class="detail_label">
									培训照片:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="5"> 
								    <input type="hidden" id="TRAN_PIC" name="TRAN_PIC" json="TRAN_PIC" value="${rstshop.TRAN_PIC}"/>
								</td>
							</tr>  
							 
					        <tr>
								<td border="blod;">
									门店拜访表
								</td>
							</tr>
							<tr>
							  <td width="15%" align="right" class="detail_label">
									门店编号:
								</td>
								<td width="15%" align="left" class="detail_content">
									 ${rsterm.TERM_NO}
								</td>
								<td width="15%" align="right" class="detail_label">
									门店名称:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="5">
								   ${rsterm.TERM_NAME}
 								</td>
							</tr>
							<tr>
							  <td width="15%" align="left" class="detail_label">
									渠道编号:
								</td>
								<td width="15%" align="left" class="detail_content">
								   ${rsterm.CHANN_NO}
								</td>
								<td width="15%" align="left" class="detail_label">
									渠道名称:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="5">
									${rsterm.CHANN_NAME}
								</td>
							</tr>
							<tr> 
							   <td width="15%" align="left" class="detail_label">
									硬装:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
								   ${rsterm.HARD_DECORATE}
 								</td>
						    </tr>
						    <tr>
								<td width="15%" align="left" class="detail_label">
									软装:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
								   ${rsterm.SOFT_DECORATE}
 								</td>
						    </tr>
						    <tr>
								<td width="15%" align="left" class="detail_label">
									导购员:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
								  ${rsterm.SHOPP_GUIDE}
 								</td>
							</tr>
							<tr>
								<td width="15%" align="left" class="detail_label">
									主要问题:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
								   ${rsterm.MAIN_PROBLEM}
 								</td>
						    </tr>
						    <tr>
								<td width="15%" align="left" class="detail_label">
									解决方案:
								</td>
								<td width="15%" align="left" class="detail_content" colspan="8">
								${rsterm.SOLUTION}
 								</td>
							</tr> 
					</table>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		</table>
		<script type="text/javascript">
	       var path = $("#STORE_ATT").val();
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
	       displayDownFile('STORE_ATT',str,false,false);
	       
	       var ACTV_PRMT_ATT = $("#ACTV_PRMT_ATT").val();
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
	       displayDownFile('ACTV_PRMT_ATT',str,false,false);
	       
	       var TRAN_TITLE = $("#TRAN_PIC").val();
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
	       displayDownFile('TRAN_PIC',str,false,false);
       </script>
	</body>
</html>

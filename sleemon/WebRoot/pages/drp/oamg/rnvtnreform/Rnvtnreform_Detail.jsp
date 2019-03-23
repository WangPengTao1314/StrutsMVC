<!-- 
 *@module 渠道管理-装修管理
 *@func   装修整改单维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<title>详情信息</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>
		<c:if test="${isNew == true}">
		当前位置：渠道管理&gt;&gt;装修管理&gt;&gt;装修整改单维护
		</c:if>
		<c:if test="${isNew == false}">
		当前位置：渠道管理&gt;&gt;装修管理&gt;&gt;装修整改单维护
		</c:if>
		</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" id="selectParams" name="selectParams" />
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <input type="hidden" json="RNVTN_CHECK_DTL_ID" value="${rst.RNVTN_CHECK_DTL_ID}">
				<tr>
							<td width="15%" align="right" class="detail_label">
								装修整改单号：
							</td>
							<td width="35%" class="detail_content">
									 ${rst.RNVTN_REFORM_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								施工负责人：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.PROCESS_CHARGE}
							</td>
						</tr>
						<tr>
						   <td width="15%" align="right" class="detail_label">
								装修验收单号：
							</td>
							<td width="35%" class="detail_content">
									 ${rst.RNVTN_CHECK_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								装修申请单号：
							</td>
							<td width="35%" class="detail_content">
								  ${rst.CHANN_RNVTN_NO}
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								验收负责人：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.CHECK_CHARGE}
							</td>
							<td width="15%" align="right" class="detail_label">
								验收时间：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CHECK_TIME}
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
								装修性质：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.RNVTN_PROP}
							</td>
							<td width="15%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.PLAN_SBUSS_DATE}
							</td>
						</tr>
                        
                        <tr>
                           <td width="15%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="35%" class="detail_content">
							   ${rst.SALE_STORE_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								卖场面积：
							</td>
							<td width="35%" class="detail_content">
								${rst.SALE_STORE_AREA}
							</td>
                        </tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="35%" class="detail_content">
								${rst.LOCAL_TYPE}
							</td>
							<td width="15%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="35%" class="detail_content">
								${rst.BUSS_SCOPE}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
							    ${rst.STATE}
						    </td>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content">
							   ${rst.REMARK}
						    </td>
	                    </tr>
                    </table>
                 </td>
            </tr>
      </table>
</form>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script>
      /*
       var path = $("#ATT_PATH").val();
       var strs= new Array();    
       strs=path.split(",");         
       var str="";
       if(strs.length>1){
	       for(var i=0;i<strs.length;i++){
	          str+="SAMPLE_DIR,";
	       }
	       var str = str.substring(0, str.lastIndexOf(','));
	    } else {
	          str="SAMPLE_DIR";
	    }
       displayDownFile('ATT_PATH',str,true,false);
       */
</script>
</body>

<!-- 
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
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
	<title>装修验收单维护详情</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>
		<c:if test="${isNew == true}">
		当前位置：渠道管理&gt;&gt;装修管理&gt;&gt;新增装修验收单维护 
		</c:if>
		<c:if test="${isNew == false}">
		当前位置：渠道管理&gt;&gt;基础信息&gt;&gt;修改装修验收单维护
		</c:if>
		</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" value="${isNew}" id="isNew" name="isNew">
<input type="hidden" value="${rst.STATE}" id="state">
<input type="hidden" id="selectParams" name="selectParams" />
 <input type="hidden" id="RNVTN_CHECK_DTL_ID" name="RNVTN_CHECK_DTL_ID" json="RNVTN_CHECK_DTL_ID" value="${rst.RNVTN_CHECK_DTL_ID}">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
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
							    ${rst.RNVTN_PRINCIPAL}
							</td>
							<td width="15%" align="right" class="detail_label">
								验收时间：
							</td>
							<td width="35%" class="detail_content">
								${rst.RNVTN_CHECK_DATE}
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
								所属战区编号：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								所属战区名称：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_NAME}
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								区域经理电话：
							</td>
							<td width="35%" class="detail_content">
								${rst.AREA_MANAGE_TEL}
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
								行政区域：
							</td>
							<td width="35%" class="detail_content">
								${rst.ZONE_ADDR}
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
								验收得分：
							</td>
							<td width="35%" class="detail_content">
								${rst.CHECK_SCORE}
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
								验收意见：
							</td>
							<td width="35%" class="detail_content">
								${rst.CHECK_REMARK}
							</td>
							<td width="15%" align="right" class="detail_label">
								处罚意见：
							</td>
							<td width="35%" class="detail_content">
								${rst.PUNISH_REMARK}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								附件：
							</td>
							<td width="35%" class="detail_content" colspan="3">
							   <input id="ATT_PATH" json="ATT_PATH" size="60" name="ATT_PATH"  type="hidden"   value="${rst.ATT_PATH}"/>
						    </td>
	                    </tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content" colspan="3">
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
       var path = $("#ATT_PATH").val();
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
       displayDownFile('ATT_PATH',str,false,false);
</script>
</body>

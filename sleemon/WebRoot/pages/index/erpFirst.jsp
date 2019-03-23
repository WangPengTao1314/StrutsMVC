<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>门户主页</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href=".//../styles/drp/css/erpfirpage.css">
		<script type="text/javascript" src="../../scripts/core/jquery-1.4.1.min.js"></script>
		<script type="text/javascript" src=".//../scripts/core/jscharts_mb.js"></script>
		<script type=text/javascript src="${ctx}/pages/index/erpFirst.js"></script>
		<style type="text/css">
	  		#mycredit_show{
			margin: 0px auto; 
			width:400px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:100px;
			left:630px;
			top:220px;
			text-align:center;
			display: block;
		}
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:150px;
		}
		.wenben{
			width: 80px;
			text-align: right;
		}
		.neirong{
			width:150px;
			text-align:left;
		}
	</style>
		
		
		
		
</head>
<body>
 <a href="drpFirpage.shtml?action=toMorePrmt" id="to_more" name="target" label="促销信息查看" style="display: none;"></a>
 <div class="x_content" style="overflow: auto;" >
  <div class="left_c">
   <div class="notice_dtl" >
	    <div class="notice_dtl_h">
	    	<p>&nbsp;<img style="vertical-align: middle;"src=".//../styles/drp/images/deng.png"/>&nbsp;置顶公告</p>
	    </div>
	    <div >
	      <c:if test="${empty firstNotice.NOTICE}">
	        <div class="ad_l" >
	             <h3 id="h3_notice_title"></h3>
	             <p id="p_notice" >暂无公告信息！</p>
	        </div>
	      </c:if>
	      
	      <c:if test="${!empty firstNotice.NOTICE}">
	        <div class="ad_l" style="height: 240px;overflow: auto;">
	          <h3 id="h3_notice_title"></h3>
	          <div id="p_notice"><p> </p></div> 
	        </div>
	      </c:if>
   </div>
  </div>
     
    <div class="news">
      <div class="news_h">
        <p>公告信息</p>
        <span><a id="more_notice" name="target"  menu="gg-0000" label="公告信息查看" style="font-size:16px;text-decoration:none" href="drpFirpage.shtml?action=toMoreNotice">more...</a></span> </div>
      <div class="news_c" id="news_c">
        <ul id="ul_notice">
         <c:forEach items="${noticeList}" var="notice" varStatus="row">
           <c:set var="rr" value="${row.count}"></c:set>
            <li>
             <input type="hidden" id="" name="NOTICE_ID" value="${notice.NOTICE_ID}"/>
             <p><a href="javascript:void(0);" id="a_title_${rr}" name="a_title_">${notice.NOTICE_TITLE}</a></p>
             <span style="float: right;margin-left: 100px;"><a href="javascript:void(0);" onclick="showFile('${notice.NOTICE_ID}','${notice.ATT_PATH}');">附件</a></span>
             <span  class="news_time" >${notice.STATIME}</span>
            </li>
         </c:forEach>
         <c:if test="${empty noticeList}">
           <li>暂无公告信息！</li>
         </c:if>
        </ul>
      </div>
    </div>
  </div>
  
    <div class="right_c">
    <div class="items">
      <div class="items_h">
        <p>待办事宜</p>
      </div>
      <div class="items_c">
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"     qx="BS0010601" label="订货订单处理" menu="BS0D01" href="goodsorder.shtml?action=toFrame" id="good_count">0</a></p>
          <p class="it_button"><a  name="target" qx="BS0010601" label="订货订单处理" menu="BS0D01" href="goodsorder.shtml?action=toFrame" >订货订单处理</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"      qx="BS0011201"  label="待审核工艺单" menu="BS0E02" href="techorder.shtml?action=toFrame" id="techAudit_count">0</a></p>
          <p class="it_button"><a  name="target"  qx="BS0011201"  label="待审核工艺单" menu="BS0E02" href="techorder.shtml?action=toFrame" >待审核工艺单</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"      qx="BS0011301"  label="待核价工艺单" menu="BS0E03" href="techorderprice.shtml?action=toFrame" id="techPrice_count">0</a></p>
          <p class="it_button"><a  name="target"  qx="BS0011301"  label="待核价工艺单" menu="BS0E03" href="techorderprice.shtml?action=toFrame" >待核价工艺单</a></p>
        </div> 
 <!--   <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"     qx="BS0011501"  label="制定交付计划" href="turnoverplan.shtml?action=toFrame" id="turnoverplan_count">0</a></p>
          <p class="it_button"><a  name="target" qx="BS0011501"  label="制定交付计划" href="turnoverplan.shtml?action=toFrame" >制定交付计划</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"     qx="BS0011601"   label="交付计划维护" href="turnoverhd.shtml?action=toFrame" id="turnoverhd_count">0</a></p>
          <p class="it_button"><a  name="target" qx="BS0011601"   label="交付计划维护" href="turnoverhd.shtml?action=toFrame" >交付计划维护</a></p>
        </div> --> 
        <div class="clear"></div>
      </div>
   <!--     <div class="items_c">
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"     qx="BS0011701"   label="货品发运" href="pdtdeliver.shtml?action=toFrame" id="pdtdeliver_count">0</a></p>
          <p class="it_button"><a  name="target" qx="BS0011701"   label="货品发运" href="pdtdeliver.shtml?action=toFrame" >货品发运</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"      qx="BS0011801"  label="发运单确认" href="deliverconfm.shtml?action=toFrame" id="deliverconfm_count">0</a></p>
          <p class="it_button"><a  name="target"  qx="BS0011801"  label="发运单确认" href="deliverconfm.shtml?action=toFrame" >发运单确认</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"      qx="BS0011201"  label="待审核工艺单" href="techorder.shtml?action=toFrame" id="techAudit_count">0</a></p>
          <p class="it_button"><a  name="target"  qx="BS0011201"  label="待审核工艺单" href="techorder.shtml?action=toFrame" >待审核工艺单</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/audit.png" />
          <p class="it_num"><a name="target"      qx="BS0011301"  label="待核价工艺单" href="techorderprice.shtml?action=toFrame" id="techPrice_count">0</a></p>
          <p class="it_button"><a  name="target"  qx="BS0011301"  label="待核价工艺单" href="techorderprice.shtml?action=toFrame" >待核价工艺单</a></p>
        </div> 
        <div class="clear"></div>
     </div> --> 
    </div>
    <!-- 
    <div class="table_c" style="overflow: auto;">
      <div class="table_nav" >
      	<span class="ts" id="span_bar"><a href="javascript:void(0);"  onclick="queryBar(this);" style="text-decoration: none;"><span>快捷方式</span></a></span>
      </div>
      <div style="width:100%; border:1px"> 	
      	 <a name="target" label="订货订单处理" href="goodsorder.shtml?action=toFrame" style="cursor:pointer"><div style="margin-left:40px; position:relative;float:left;background-image:url(.//../styles/drp/images/dhddcl.bmp);width:108px; height:67px;" >
      	 	<span style="position:absolute;bottom:5px; padding:0px;margin:0px; text-align:center;width:100%;">订货订单处理</span>
      	 </div>
      	
      	<a name="target" label="付款凭证确认" href="paymentcmt.shtml?action=toFrame" style="cursor:pointer"><div style="margin-left:40px;position:relative;float:left;background-image:url(.//../styles/drp/images/fkpzqr.bmp);width:108px; height:67px;" >
      	 	<span style="position:absolute;bottom:5px; padding:0px;margin:0px; text-align:center;width:100%;">付款凭证确认</span>
      	</div></a>
      	 
      	<a name="target" label="退货单维护" href="prdreturn.shtml?action=toFrame&module=wh" style="cursor:pointer"><div style="margin-left:40px;position:relative;float:left;background-image:url(.//../styles/drp/images/thsqdwh.bmp);width:108px; height:67px;" >
      	 	<span style="position:absolute;bottom:5px; padding:0px;margin:0px; text-align:center;width:100%;">退货单维护</span>
      	</div></a>
      </div>
      <div style="width:100%; height:15px;"></div>
      <div style="width:100%;">
      	 <a name="target" label="投诉与建议处理" href="advisehd.shtml?action=toFrame" style="cursor:pointer"><div style="margin-left:40px; position:relative;float:left;background-image:url(.//../styles/drp/images/tsyjycl.bmp); height:67px;width:108px;" >
			<span style="position:absolute;bottom:5px; padding:0px;margin:0px; text-align:center;width:100%;">投诉与建议处理</span>
      	 </div></a>
      	 <a name="target" label="推广促销方案维护" href="prmtplan.shtml?action=toFrame&module=wh" style="cursor:pointer"><div style="margin-left:40px; position:relative;float:left;background-image:url(.//../styles/drp/images/tgcxfa.bmp); height:67px;width:108px;" >
			<span style="position:absolute;bottom:5px; padding:0px;margin:0px; text-align:center;width:100%;">推广促销方案维护</span>
      	 </div></a>
      	 <a name="target" label="返利登记" href="rebate.shtml?action=toList" style="cursor:pointer"><div style="margin-left:40px; position:relative;float:left;background-image:url(.//../styles/drp/images/fldj.bmp); height:67px;width:108px;" >
			<span style="position:absolute;bottom:5px; padding:0px;margin:0px; text-align:center;width:100%;">返利登记</span>
      	 </div></a>
      </div>
      <div style="width:100%; height:5px;"></div>
    </div>
    -->
  </div>
  
  
  <div class="clear"></div>
</div>

<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<div id="mycredit_show" style="background-color: #F1F4FB;display: none;word-wrap: break-word; ">
    	<h4 align="center" style="margin-top: 5px" id="fileDown_title">附件下载</h4>
    	<%--<table>
    	 <tr>
    	  <td>
    	  <input type="hidden" name="ATT_PATH" id="ATT_PATH" value=""/>
    	  </td>
    	 </tr>
    	</table>
    	
	    --%><input style="margin-top: 10px" id="close" class="btn" type="button" value="关闭" onclick="closePage();"/>
</div>

</body>
</html>
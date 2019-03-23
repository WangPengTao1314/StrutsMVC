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
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/drp/css/drpfirpage.css">
		<script type=text/javascript src="${ctx}/pages/index/drpFirst.js"></script>
		<script type="text/javascript" src=".//../scripts/core/jscharts_mb.js"></script>
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
<body >
 <a url="drpFirpage.shtml?action=toMorePrmt" id="to_more" 
  href="javascript:void(0);" menu="to_more_prmt"
  name="target" label="促销信息查看" style="display: none;"></a>
 <div class="x_content" >
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
          <div id="p_notice"><p onmouseover="">  </p></div> 
        </div>
      </c:if>
    </div>
     </div>
     
    <div class="news">
      <div class="news_h">
        <p>公告信息</p>
        <span>
	        <a id="more_notice" name="target" 
	        label="公告信息查看" style="font-size:16px;text-decoration:none" 
	        href="javascript:void(0);" 
	        menu = "more_notice"
	        url="drpFirpage.shtml?action=toMoreNotice">more...</a>
        </span> 
        </div>
      <div class="news_c" id="news_c">
        <ul id="ul_notice">
         <c:forEach items="${noticeList}" var="notice" varStatus="row">
           <c:set var="rr" value="${row.count}"></c:set>
            <li>
             <input type="hidden" id="" name="NOTICE_ID" value="${notice.NOTICE_ID}"/>
             <p>
              <a href="javascript:void(0);" onclick="getNoticeInfo('${notice.NOTICE_ID}')"  id="a_title_${rr}" name="a_title_"  
             style="overflow: hidden; text-overflow:clip; white-space:nowrap;width: 260px;">${notice.NOTICE_TITLE}</a> 
              
             </p><%--
             <span style="display:inline-block;position:absolute;width: 400px; overflow: hidden; text-overflow:clip; white-space:nowrap;">${notice.NOTICE_TITLE}</span>
              --%><span style="float: right;margin-left: 100px;"><a href="javascript:void(0);" onclick="showFile('${notice.NOTICE_ID}','${notice.ATT_PATH}');">附件</a></span>
             <span  class="news_time"  > ${notice.STATIME}   </span>
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
        <div class="it_ct"> <img src=".//../styles/drp/images/bag.png" />
          <p class="it_num"><a name="target"     qx="FX0020901" label="预订单录入" menu="DR1C01" href="javascript:void(0);" url="advcorder.shtml?action=toFrame&module=wh&search=true&isFirst=true&STATE=待发货" id="advcorder_count">0</a></p>
          <p class="it_button"><a  name="target" qx="FX0020901" label="预订单录入" menu="DR1C01" href="javascript:void(0);" url="advcorder.shtml?action=toFrame&module=wh&search=true&isFirst=true&STATE=待发货" >待发货预订单</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/arrow.png" />
          <p class="it_num"><a name="target"    qx="FX0030301" label="入库通知单维护" menu="DR2B01" href="javascript:void(0);" url="storeinnotice.shtml?action=toFrame&module=wh&search=isFirst&STATE=提交" id="storeinnotice_count">0</a></p>
          <p class="it_button"><a name="target" qx="FX0030301" label="入库通知单维护" menu="DR2B01" href="javascript:void(0);" url="storeinnotice.shtml?action=toFrame&module=wh&search=isFirst&STATE=提交">待入库</a></p>
        </div>
        <div class="it_ct"> <img src=".//../styles/drp/images/car.png" />
          <p class="it_num"><a name="target"    qx="FX0020701"  label="退货申请单维护" menu="DR1B01"  href="javascript:void(0);"   url="prdreturnreq.shtml?action=toFrame&module=wh&search=isFirst&STATE=提交" id="prdreturnreq_count">0</a></p>
          <p class="it_button"><a name="target" qx="FX0020701"    label="退货申请单维护" menu="DR1B01"  href="javascript:void(0);" url="prdreturnreq.shtml?action=toFrame&module=wh&search=isFirst&STATE=提交">待退货</a></p>
        </div>
        <div class="clear"></div>
      </div>
    </div>
    <div class="table_c" style="overflow: auto;">
      <div class="table_nav">
      <span class="last"    id="span_bar"     name="span_scheme">   <a parent="span_bar" href="javascript:void(0);" style="text-decoration:none" onclick="queryBar(this);">当月销售情况</a></span>
      <span class="current" id="span_store"   name="span_scheme" >  <a parent="span_store" href="javascript:void(0);" style="text-decoration:none" onclick="showStore(this);">库存预警信息</a></span>
      <span class="last"    id="span_deliver" name="span_scheme">   <a parent="span_deliver" href="javascript:void(0);" style="text-decoration:none" onclick="showDelive(this);">发运信息</a></span>
      </div>
      <div class="table_content" id="span_store_div" for="span_store" style="overflow: auto;" name="scheme_div">
        <table cellpadding="0" cellspacing="0" >
          <tbody>
            <tr>
              <th>货品编号</th>
              <th>货品名称</th>
              <th>剩余件数</th>
            </tr>
            <c:forEach items="${storeList}" var="store">
            <tr>
              <td align="center">${store.PRD_NO}</td>
              <td align="center">${store.PRD_NAME}</td>
              <td align="center">${store.STORE_NUM}</td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      <!-- 发运单 -->
      <div class="table_content" id="span_deliver_div" for="span_deliver" style="overflow: auto;display: none;" name="scheme_div">
        <table cellpadding="0" cellspacing="0" id="span_deliver_table">
          <tbody>
            <tr>
              <th>发运单编号</th>
              <th>出货计划号</th>
              <th>预计发货时间</th>
              <th>状态</th>
            </tr>
            <c:forEach items="${storeList}" var="store">
            <tr>
              <td align="center"></td>
              <td align="center"></td>
              <td align="center"></td>
              <td align="center"></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      
       <!-- 柱状图 -->
       <div style="overflow: auto;" name="scheme_div" for="span_bar">
        <div id="graph" class="table_content">暂无数据</div>
       </div>
     </div>
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
 

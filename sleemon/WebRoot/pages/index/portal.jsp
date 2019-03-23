<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
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
		<link rel="stylesheet" type="text/css" href=".//../styles/drp/css/portal.css">
		<script type="text/javascript" src="../../scripts/core/jquery-1.4.1.min.js"></script>
		<script type="text/javascript" src="./../scripts/core/jscharts_mb.js"></script>
		<script type=text/javascript src="${ctx}/pages/index/portal.js"></script>
		
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
	left:680px;
	top:160px;
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


.notice_content{
	margin: 0px auto;   
	width:282px;
	height:230px;
	left:871px;
	top:30px;
	border: 1px;
	z-index:1;
	position:absolute;
	background-color: white;
	border:1px solid black;
	text-align:center;
	display: none;
}

	</style>
</head>
<body>
 <div class="x_content" style="overflow: auto;widtd:100%;" >
  <div class="left_c" >
   <div class="report_div" >
         
	    <div id="report_div2" class="report_tital">
	    	<p id='report_menu'>
	    	   <c:if test="${qxcom.XT0013301 eq 1 }">
	    	   <span id='sale_report' align='center' style="cursor:pointer"><B>线下加盟商出库</B></span>
	    	   </c:if>
	    	   <c:if test="${qxcom.XT0013302 eq 1 }">
	    	   <span id='advc_report' align='center' style="cursor:pointer"><B>线下加盟商订货</B></span>
	    	   </c:if>
	    	   <c:if test="${qxcom.XT0013303 eq 1 }">
	    	   <span id='dstr_report' align='center' style="cursor:pointer"><B>线下直营办销售</B></span>
	    	   </c:if>
	    	   <c:if test="${qxcom.XT0013304 eq 1 }">
	    	   <span id='dstr_report_main' align='center' style="cursor:pointer"><B>直营办销售</B></span>
	    	   </c:if>
	    	   <c:if test="${qxcom.XT0013305 eq 1 }">
	    	   <span id='chann_report' align='center' style="cursor:pointer"><B>渠道相关</B></span>
	    	    </c:if>
	    	</p> 
	    	<iframe id='report_frame' src=""   frameBorder=0 width="100%" height="95%" frameborder="0" marginheight="0" marginwidth="0"  scrolling="auto"></iframe>
	     </div> 
  </div>
 </div>
  
    <div class="right_c">
      <div class="news" id="news">
       <div class="news_h agency_h" ><p>公告信息</p>
        <span><a id="more_notice" name="target"  menu="gg-0000" label="公告信息查看" 
        style="font-size:16px;text-decoration:none" href="drpFirpage.shtml?action=toMoreNotice">more...</a></span> 
        </div>
        
      <div class="news_c" id="news_c">
        <ul id="ul_notice">
         <c:forEach items="${noticeList}" var="notice" varStatus="row">
           <c:set var="rr" value="${row.count}"></c:set>
            <li>
             <input type="hidden" id="" name="NOTICE_ID" value="${notice.NOTICE_ID}"/>
             <p><a href="javascript:void(0);" id="a_title_${rr}" name="a_title_">${notice.NOTICE_TITLE}</a></p>
             <span style="float: right;margin-left: 100px;">
             </span>
             <span  class="news_time" >${notice.STATIME}</span>
            </li>
         </c:forEach>
         <c:if test="${empty noticeList}">
           <li>暂无公告信息！</li>
         </c:if>
        </ul>
      </div>  
    </div>
    <div class="agency">
      <div class="agency_h">
        <p>待办工作</p>
      </div>
      <table id="first" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
		<tr class="fixedRow_" height="25px" >
			<th style="text-align:center;" >待办事项</th>
			<th style="text-align:center;" >待办事项数</th>
			<th style="text-align:center;" >状态</th>
		</tr>
		<c:forEach items="${list}" var="bean" varStatus="row">
			<c:set var="r" value="${row.count % 2}"></c:set>
			<c:set var="rr" value="${row.count}"></c:set>
				<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					<td>
						&nbsp;<a href="javascript:void(0)" onclick="parent.addTab('${ bean.MENU_ID}','${ bean.MENU_NAME}','${ctx}/${bean.MENU_URL }')">${bean.MENU_NAME}</a>&nbsp;
					</td>
					<td align="center"> ${bean.NUM}&nbsp; </td>
					<td align="center"> 待审批&nbsp; </td>
				</tr>
		</c:forEach>
		<c:if test="${empty list}">
			<tr height="25px"> 
			  <td colspan="4">
					暂无待办事项
			 </td>
			</tr>
		</c:if>
	</table>
    </div>
   </div> 
   <div class="notice_content" id="notice_content">
	    <div title="关闭" class="closer" onclick="closeNoticeContent();"></div>
	    <div>
	         <div class="ncon_l" style="overflow-y:auto; ">
	          <h3 id="h3_notice_title"></h3>
	          <p id="p_notice" > </p>
	        </div>
        </div>
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
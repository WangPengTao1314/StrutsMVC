<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href=".//../styles/drp/css/portal.css">
	<link rel="stylesheet" type="text/css" href=".//../styles/drp/css/erpfirpage.css">
	<script type="text/javascript" src="../../scripts/core/jquery-1.4.1.min.js"></script>
	<title>系统公告</title>
	<style type="text/css">
	</style>
</head>
<body class="bodycss1">
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<table height="80%" width="98%" style="margin: 40px 0px 10px 10px;">
	<tr>
	 <td  align="center">
	    <div class="notice_dtl">
	     <div class="notice_dtl_h">
	    	<p>&nbsp;<img style="vertical-align: middle;"src=".//../styles/drp/images/deng.png"/>&nbsp;置顶公告</p>
	     </div>
	    <div>
         <div class="ad_l" style="overflow-y: auto;">
          <h3 id="h3_notice_title">${notice.NOTICE_TITLE}</h3>
          <p id="p_notice" > ${notice.NOTICE} </p>
         </div>
       </div>
     
  </div>
  <input type='hidden' name='ATT_PATH' id='ATT_PATH' value='${notice.ATT_PATH}'/>
 </td>
</tr>
<tr>
 
</tr>
 
</table>
	
  	 			
</body>
<script type="text/javascript">
displayDownFile('ATT_PATH',"SAMPLE_DIR",false,false);
</script>
</html>
 
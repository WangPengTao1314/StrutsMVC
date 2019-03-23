<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
 *@module 共通
 *@func 通用文件上传下载 
 *@version 1.1
 *@author 朱晓文
 -->
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
<link rel="stylesheet" type="text/css" href="${ctx}/scripts/util/uploadfile/uploadify.css">
<script type="text/javascript" src="${ctx}/scripts/util/uploadfile/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/util/uploadfile/swfobject.js"></script>
 </head>

<script type="text/javascript">
var _sizeLimit;
var _fileExt;
/*
 *文件上传
 *@param id ：上传按钮id
 *@param folder：上传的文件二级路径。
 */
function uploadFile(id,folder,autoCommit,callback){
	var obj = $("#"+id);
	if(autoCommit!=false)
	{
	 autoCommit=true;
	}
	obj.uploadify(
		{ 
		
          'uploader'  : ctxPath+'/scripts/util/uploadfile/uploadify.swf',
		  'script'    : ctxPath+'/fileuploadhelper.shtml',
		  'scriptData':{'action':'importExcel','dirpath':folder},
		  'cancelImg' :ctxPath+'/styles/'+theme+'/images/main/cancel.png',
		  'method'    :'GET',
		  'auto'      : autoCommit,
		  'multi'     : false,
		  'fileDesc'  : '*.xlsx;*.xls;',
		  'fileExt'   : '*.xlsx;*.xls;',
		  'sizeLimit' : '52428800',//5M
		  'buttonText':'browse',
		  'buttonImg' :ctxPath+'/styles/'+theme+'/images/plus/upload.png',
		  'width':110,
		  'height':20,
		  onCheck :function(event, checkScript, fileQueueObj, folder, single){
		  },
		  onError:function(event, ID, fileObj, errorObj){},
		  onComplete:function(event, queueID, fileObj, response, data){
		  		eval("jsonResult = "+response);
				if(jsonResult.success===true){
				   obj.attr("value",jsonResult.messages);
				   if(callback!=null&&callback!='')
					{
				    	callback();
					}else
					{
						showMsgPanel("上传成功");
					}
				}else{
				    alert(utf8Decode(jsonResult.messages));
				}
		  }
		}
	);
}
</script>
<iframe id='_hiddenIframe' name='_hiddenIframe' src='#' style='display:none'></iframe>
<form name="_downloadFileForm" method="post" target="_hiddenIframe" action="">
<input  type="text" name="secPath" style="display:none" value=""/>&nbsp;
<input  type="text" name="fileName"  style="display:none" value=""/>&nbsp;
<input  type="text" name="params"  style="display:none" value=""/>&nbsp;
</form>
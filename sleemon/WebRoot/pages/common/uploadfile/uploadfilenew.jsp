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
function uploadFile(id,folder,isdown,ismulupl,autoCommit,callback,chkId){
	var obj = $("#"+id);
	if(autoCommit!=false)
	{
	 autoCommit=true;
	}
	obj.uploadify(
		{ 
		
          'uploader'  : ctxPath+'/scripts/util/uploadfile/uploadify.swf',
		  'script'    : ctxPath+'/fileuploadhelper.shtml',
		  'scriptData':{'action':'uploadFile','dirpath':folder,'ismulupl':ismulupl,'chkId':chkId},
		  'cancelImg' :ctxPath+'/styles/'+theme+'/images/main/cancel.png',
		  'method'    :'GET',
		  'auto'      : autoCommit,
		  'multi'     : ismulupl,
		  'fileDesc'  : '*.jpg;*.gif;*.png;*.rar;*.zip;*.pdf;*.doc;*.xls;*.pptx;*.ppt;',//去掉2007版本的上传校验
		  'fileExt'   : '*.jpg;*.gif;*.png;*.rar;*.zip;*.pdf;*.doc;*.xls;*.pptx;*.ppt;',
		  'sizeLimit' : '52428800',//5M
		  'buttonText':'browse',
		  // 'checkScript':'3123',
		  'buttonImg' :ctxPath+'/styles/'+theme+'/images/plus/upload.png',
		  //'hideButton':'false',
		  'width':110,
		  'height':20,
		  onCheck :function(event, checkScript, fileQueueObj, folder, single){
		  },
		  onError:function(event, ID, fileObj, errorObj){},
		  onComplete:function(event, queueID, fileObj, response, data){
		  		eval("jsonResult = "+response);
				if(jsonResult.success===true){
				   obj.attr("value",jsonResult.messages);
				   if(isdown&&!ismulupl)
				   {
				       var url = obj.val();
				        if(null != url && ""!=url){
				           if($("#theDownImage")!=null&&$("#theDownImage")!='')
				            {
				              $("#theDownImage").remove()
				            }
				            $("#"+id+"Uploader").after("<img id='theDownImage'  title='"+url+"' style='cursor:pointer' src='"+ctxPath+"/styles/"+theme+"/images/plus/download.png' onclick=\"downloadFile('"+id+"','"+folder+"','"+url+"');\">");
	                     }
	               }
				   if(callback!=null&&callback!='')
					{
				    	callback();
					}
				    showMsgPanel("上传成功");
				}else{
				    alert(utf8Decode(jsonResult.messages));
					//showErrorMsg(utf8Decode(jsonResult.messages));
				}
		  }
		}
	);
	if(isdown&&!ismulupl)
	 {
	  
	   var url = obj.val();
 	   if(null != url && ""!=url){
	         if($("#theDownImage")!=null&&$("#theDownImage")!='')
			  {
				 $("#theDownImage").remove()
			  }
	         $("#"+id+"Uploader").after("<img id='theDownImage' style='cursor:pointer' src='"+ctxPath+"/styles/"+theme+"/images/plus/download.png' onclick=\"downloadFile('"+id+"','"+folder+"','"+url+"');\">");
	 }
	}
	
	if(ismulupl)
	 {
	   $("#"+id+"_upl").click(function()
	   {
	    $("#"+id).uploadifyUpload();
	   });
	   
	   $("#"+id+"_clear").click(function()
	   {
	     $("#"+id).uploadifyClearQueue();
	   });
	 }
 }

//文件下载
function downloadFile(id,folder,fileName){
   document._downloadFileForm.action="${ctx}/fileuploadhelper.shtml?action=downloadFile";
   document._downloadFileForm.secPath.value=folder;
   document._downloadFileForm.fileName.value=fileName;
   document._downloadFileForm.submit();
}



//多文件压缩下载
function mulFileDown(jsonParams){
   document._downloadFileForm.action="${ctx}/fileuploadhelper.shtml?action=mulFileDown";
   document._downloadFileForm.params.value=jsonParams;
   document._downloadFileForm.submit();
}

</script>
<iframe id='_hiddenIframe' name='_hiddenIframe' src='#' style='display:none'></iframe>
<form name="_downloadFileForm" method="post" target="_hiddenIframe" action="">
<input  type="text" name="secPath" style="display:none" value=""/>&nbsp;
<input  type="text" name="fileName"  style="display:none" value=""/>&nbsp;
<input  type="text" name="params"  style="display:none" value=""/>&nbsp;
</form>
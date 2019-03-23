<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 
 *@module 共通
 *@func 通用文件上传下载 
 *@version 1.1
 *@author 朱晓文
 -->
 <html>
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
function uploadFile(id,folder,isdown,ismulupl,autoCommit,callback){
	var obj = $("#"+id);
	obj.uploadify(
		{ 
		  'uploader'  : ctxPath+'/scripts/util/uploadfile/uploadify.swf',
		  'script'    : ctxPath+'/fileuploadhelper.shtml',
		  'scriptData':{'action':'uploadFile','dirpath':folder,'ismulupl':ismulupl},
		  'cancelImg' :ctxPath+'/styles/'+theme+'/images/plus/cancel.png',
		  'method'    :'GET',
		  'auto'      : autoCommit!=false?true:autoCommit,
		  'multi'     : ismulupl,
		  'fileDesc'  : '*.jpg;*.gif;*.png;*.rar;*.zip;*.pdf;*.doc;*.xls;*.xlsx;*.docx;*.pptx;*.ppt;',
		  'fileExt'   : '*.jpg;*.gif;*.png;*.rar;*.zip;*.pdf;*.doc;*.xls;*.xlsx;*.docx;*.pptx;*.ppt;',
		  'sizeLimit' : '52428800',//5M
		  'buttonText':'browse',
		  //'checkScript':'3123',
		  'buttonImg' :ctxPath+'/styles/'+theme+'/images/plus/upload.png',
		  //'hideButton':'false',
		  'width':90,
		  'height':22,
		  onCheck :function(event, checkScript, fileQueueObj, folder, single){
		  },
		  onError:function(event, ID, fileObj, errorObj){},
		  onComplete:function(event, queueID, fileObj, response, data){
		  		eval("jsonResult = "+response);
				if(jsonResult.success===true){
				   if(ismulupl)
				   {
				   var _urls = obj.val();
				   if(_urls==null||_urls=='')
				   {
				      obj.attr("value",jsonResult.messages);
				   }else
				   {
				      obj.attr("value",_urls+","+jsonResult.messages);
				   }
				      obj.attr("thePath",jsonResult.messages);
				   }else
				   {
				      obj.attr("value",jsonResult.messages);
				   }
				   
				   if(isdown)
				   {
				      displayDownFile(id+"Uploader",folder,ismulupl==true?false:true,true);
	               }
				   if(callback!=null&&callback!='')
				   {
				    	callback(id);
				   }else
				   {
				     showMsgPanel("上传成功！");
				   }
				}else{
				    showErrorMsg(utf8Decode(jsonResult.messages));
				}
		  }
		}
	);
	if(isdown)
	{
		displayDownFile(id+"Uploader",folder,ismulupl==true?false:true,true);
	}
	if(ismulupl&&!autoCommit)
	 {
	 
	  $("#"+id+"Uploader").after("<input  type='button'   class='btn' id ='uploadMul_clear' value='取消上传' />&nbsp;");
	  $("#"+id+"Uploader").after("<input  type='button'   class='btn' id ='uploadMul_upl' value='开始上传' />&nbsp;");
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



//add by zhuxw
//展示文件下载链接,多个文件请用逗号隔开
function displayDownFile(id,folder,_isSimple,_isModify){
     var obj=getObj(id);
     var _url = obj.attr("thePath");
     if(_url==null||_url=="")
     {
        _url = obj.val();
     }
     if(_url!=null&&_url!="")
      {
       var _urlArr = _url.split(",") ;
       //var _folderArr = folder.split(",") ;
       
       
       var _folderArr = new Array(_urlArr.length);
       for(var j=0;j<_urlArr.length;j++){
       		_folderArr[j] = "SAMPLE_DIR";
       }
       
       
       var i=0;
       if(_urlArr.length!=_folderArr.length)
       {
           showErrorMsg("多文件下载的参数设置不正确!");
           return false;
       }
      
	     var jsonParam = "[";
		 $(_urlArr).each(function(){
          if(null!= this && ""!=this)
          {
           var tempJson = '{"SEDPATH":"' + _folderArr[i]+ '","FILENAME":"' +this+ '"}';
           if(jsonParam=="[")
           {
             jsonParam=jsonParam+tempJson;
           }else
           {
            jsonParam=jsonParam+","+tempJson;
           }
           
           var _fileName=this.substring(this.lastIndexOf('/')+1,this.length);
           
           if(this.indexOf('_')>0&&(this.substring(this.lastIndexOf('_'),this.lastIndexOf('.'))).length==33)
           {
              _fileName=this.substring(this.lastIndexOf('/')+1,this.lastIndexOf('_'));
           }else
           {
             _fileName=this.substring(this.lastIndexOf('/')+1,this.lastIndexOf('.'));
           }
           
           var _fileType=this.substring(this.lastIndexOf('.'),this.length);
           if(!_isModify&&$("#"+id+"rarDown").length==0)
           {
             $("#"+id).after("&nbsp;&nbsp;<img id='"+id+"rarDown' style='cursor:pointer' src='"+ctxPath+"/styles/"+theme+"/images/plus/zip.png' title='压缩下载' >");
           }
           if(_isSimple)
           {
             var name1=id+"theDownImage";
             var name2=id+"theFileName";
             var name3=id+"theCancel";
             var allname=name1+","+name2+","+name3;
             if($("#"+name1).length>0){
                  $("#"+name1).remove();
                  $("#"+name2).remove();
                  if(_isModify)
	              {
	                 $("#"+name3).remove();
                  }
              }    
              $("#"+id).after("<img id='"+name1+"' style='cursor:pointer' src='"
	             +ctxPath+"/styles/"+theme+"/images/plus/"+getFileImg(_fileType)+"' title='下载' onclick=\"downloadFile('"+_folderArr[i]+"','"+this+"');\">");
	          $("#"+name1).after("<span id='"+name2+"'>"+_fileName+_fileType+"&nbsp;</span>");
	          if(_isModify)
	          {
	           $("#"+name2).after("<img id='"+name3+"' style='cursor:pointer' src='"
	             +ctxPath+"/styles/"+theme+"/images/plus/cancel.png' title='删除' onclick=\"deleteObjs('"+id+"','"+this+"','"+allname+"');\">");
	          }
           }else
           {
               
               var myDate = new Date();
               var _fileKey=myDate.getTime()+i;
               var name1=id+"theDownImage"+_fileKey;
               var name2=id+"theFileName"+_fileKey;
               var name3=id+"theCancel"+_fileKey;
               var allname=name1+","+name2+","+name3;
               
               $("#"+id).after("<img id='"+name1+"'  style='cursor:pointer' src='"
	             +ctxPath+"/styles/"+theme+"/images/plus/"+getFileImg(_fileType)+"' title='下载' onclick=\"downloadFile('"+_folderArr[i]+"','"+this+"');\">");
	           $("#"+name1).after("<span id='"+name2+"'>"+_fileName+_fileType+"&nbsp;</span>");
	           if(_isModify)
	           {
	           $("#"+name2).after("<img id='"+name3+"' style='cursor:pointer' src='"
	               +ctxPath+"/styles/"+theme+"/images/plus/cancel.png' title='删除' onclick=\"deleteObjs('"+id+"','"+this+"','"+allname+"');\">");
	           }
           
           }
          }
          i=i+1;
         });
         
         jsonParam =jsonParam+ "]";
         if(!_isModify)
           {
            $("#"+id+"rarDown").click(function(){
            mulFileDown(jsonParam);
	     });
	     }
      }
}

//删除对象
function deleteObjs(id,fileName,objs){
    var _objArr = objs.split(",") ;
    $(_objArr).each(function(){
        $("#"+this).remove();
    });
    var obj = getObj(id);
    var _url=obj.val();
    if(_url.indexOf(fileName)>=0)
    {
      _url=_url.replaceAll(fileName+",","");
      if(_url.indexOf(fileName)>=0)
      {
       _url=_url.replaceAll(fileName,"");
      }
      obj.attr("value",_url);
    }
}

function getObj(id){
   var obj ;
     if(id.indexOf("Uploader")>0)
     {
        obj = $("#"+id.substring(0,id.indexOf("Uploader")));
     }else
     {
        obj = $("#"+id);
     }
     return obj; 
}

function getFileImg(_fileType){
   if(_fileType.toUpperCase()=='.DOC')
   {
      return 'word.png';
   }
    if(_fileType.toUpperCase()=='.DOCX')
   {
      return 'word.png';
   }
   if(_fileType.toUpperCase()=='.XLS'||_fileType.toUpperCase()=='.XLSX')
   {
      return 'excel.png';
   }
   if(_fileType.toUpperCase()=='.PDF')
   {
      return 'pdf.png';
   }
   if(_fileType.toUpperCase()=='.TXT'||_fileType.toUpperCase()=='.SQL')
   {
      return 'text.png';
   }
   if(_fileType.toUpperCase()=='.RAR'||_fileType.toUpperCase()=='.ZIP')
   {
      return 'rar.png';
   }
   if(_fileType.toUpperCase()=='.PPT'||_fileType.toUpperCase()=='.PPTX')
   {
      return 'ppt.png';
   }
   if(_fileType.toUpperCase()=='.AVI'||_fileType.toUpperCase()=='.RMVB'||_fileType.toUpperCase()=='.3GP')
   {
      return 'film.png';
   }
   if(_fileType.toUpperCase()=='.BMP'||_fileType.toUpperCase()=='.JPG'||_fileType.toUpperCase()=='.JPEG'||_fileType.toUpperCase()=='.PNG'||_fileType.toUpperCase()=='.GIF')
   {
      return 'picture.png';
   }
   
   return 'other.png';
   
}


/**
function deleteFile(folder,fileName){
   if(folder==null||folder==''||fileName==null||fileName=='')
   {
     showErrorMsg("没有文件需要删除!");
     return false;
   }
   document._downloadFileForm.secPath.value=folder;
   document._downloadFileForm.fileName.value=fileName;
   document._downloadFileForm.action="${ctx}/fileuploadhelper.shtml?action=deleteFile";
   document._downloadFileForm.submit();
}
**/

//文件下载
function downloadFile(folder,fileName){
   if(folder==null||folder==''||fileName==null||fileName=='')
   {
     showErrorMsg("没有文件需要下载!");
     return false;
   }
   document._downloadFileForm.secPath.value=folder;
   document._downloadFileForm.fileName.value=fileName;
   document._downloadFileForm.action="${ctx}/fileuploadhelper.shtml?action=downloadFile";
   document._downloadFileForm.submit();
}

//多文件压缩下载
function mulFileDown(jsonParams){
   if(jsonParams==null||jsonParams=='[]')
   {
     showErrorMsg("没有文件需要下载!");
     return false;
   }
   document._downloadFileForm.action="${ctx}/fileuploadhelper.shtml?action=mulFileDown";
   document._downloadFileForm.params.value=jsonParams;
   document._downloadFileForm.submit();
}



</script>
<iframe id='_hiddenIframe' name='_hiddenIframe' src='#' style='display:none'></iframe>
<form name="_downloadFileForm" method="post" target="_hiddenIframe" >
<input  type="text" name="secPath" style="display:none" value=""/>&nbsp;
<input  type="text" name="fileName"  style="display:none" value=""/>&nbsp;
<input  type="text" name="params"  style="display:none" value=""/>&nbsp;
</form>
</html>
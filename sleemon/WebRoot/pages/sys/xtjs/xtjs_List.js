/*
  *@module 系统管理
  *@fuc 系统角色主表列表
  *@version 1.1
  *@author 唐赟
  */
$(function(){
     
    listPageInit("xtjs.shtml?action=toList");
	//新增和修改按钮初始化
	addModiToEditFrameInit("xtjs.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("xtjs.shtml?action=delete","JSBH");
	var selid =$.trim($("#selRowId").val());  
	 if(null == selid || "" == selid){
			  btnDisable(["begin","modify","delete","end","sysRight"]);
	 	 	  return;
	 	 } 
	$("#sysRight").click(function(){
	  
        var edID =$.trim($("#selRowId").val());  
		if(!checkEid(edID))return;
        showDialog("readxml.shtml?action=toXTJSQXList&XTJSID="+edID,580,530,"help:no;scroll:no; status:no; center:yes");
        return;
	});
	
	$("#begin").click(function(){
	    var xtjsid = $("input:radio[name='eid']:checked").val();
	    
	    $.ajax({
	        url:"xtjs.shtml?action=updateJSStatus",
            type:"POST",
			dataType:"text",
			data:{"xtjsid":xtjsid,"flag":1},
			complete: function(xhr){
			    eval("jsonResult = "+xhr.responseText);
			    if(jsonResult.success===true){
					parent.showMsgPanel("更新成功");
					$("#pageForm").submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}		   
			}
	    });
	  });
	    
	  $("#end").click(function(){
	    var xtjsid = $("input:radio[name='eid']:checked").val();
	    $.ajax({
	        url:"xtjs.shtml?action=updateJSStatus",
            type:"POST",
			dataType:"text",
			data:{"xtjsid":xtjsid,"flag":2},
			complete: function(xhr){
			     eval("jsonResult = "+xhr.responseText);
			    if(jsonResult.success===true){
					parent.showMsgPanel("更新成功");
					$("#pageForm").submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}				
			}
	    
	    });
	  });
});	
		

function checkEid(edID){		
			if(edID==null || ""==edID.trim()){
				showErrorMsg("请选择角色对象！");
				return false;
			}
			return true;
}

function changeButton(value){

   btnReset();
   if("启用" == value ){
        
		btnDisable(["begin","modify","delete"]);
	}else if( "停用" == value ){
	    
		btnDisable(["end","delete"]);
	}else if( "制定" == value ){
	    btnDisable(["end"]);
	}
}		
	
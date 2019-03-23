$(function(){
	//页面初始化
	listPageInit("ztxx.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("ztxx.shtml?action=delete","ZTXXID");
	var selid =$.trim($("#selRowId").val());  
	 if(null == selid || "" == selid){
	      btnDisable(["begin","modify","delete","end"]);
	      return;
	   }
	$("#begin").click(function(){
		var selRowId = $("#selRowId").val();
	    var ztxxid = $("input:radio[name='eid']:checked").val();
	    
	    $.ajax({
	        url:"ztxx.shtml?action=updateZTStatus",
            type:"POST",
			dataType:"text",
			data:{"ztxxid":ztxxid,"flag":1},
			complete: function(xhr){
			    eval("jsonResult = "+xhr.responseText);
			    if(jsonResult.success===true){
					parent.showMsgPanel("更新成功");
					//window.parent.topcontent.location="ztwh.shtml?action=toList";
					$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
					$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
					changeButton(utf8Decode(jsonResult.messages));
					pageForm.submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}		   
			}
	    });
	  });
	    
	  $("#end").click(function(){
	  	var selRowId = $("#selRowId").val();
	    var ztxxid = $("input:radio[name='eid']:checked").val();
	    $.ajax({
	        url:"ztxx.shtml?action=updateZTStatus",
            type:"POST",
			dataType:"text",
			data:{"ztxxid":ztxxid,"flag":2},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
			    if(jsonResult.success===true){
					parent.showMsgPanel("更新成功");
					//window.parent.topcontent.location="ztxx.shtml?action=toList";
					$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
					$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
					changeButton(utf8Decode(jsonResult.messages));
					pageForm.submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
	    
	    });
	  });
	  
	
	  
	  
});	
	
function checkEid(edID){		
			if(edID==null || ""==edID.trim()){
				showErrorMsg("请选择机构对象！");
				return false;
			}
			return true;
	}
	
function changeButton(value){
  
  value=value.substring(0,2);

   btnReset();
   if("启用" == value ){        
		btnDisable(["begin","modify","delete"]);
	}else if( "停用" == value ){	    
		btnDisable(["end","delete"]);
	}else if( "制定" == value ){
	    btnDisable(["end"]);
	}
}
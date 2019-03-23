$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	//mtbSaveListener("chann.shtml?action=edit","CHANN_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	
	$("#update").click(function(){
	   var channIds = "";
	   var eid = window.parent.topcontent.document.getElementsByName("eid");
	   for(var i=0;i<eid.length;i++){
	      if(eid[i].checked){
	        channIds += eid[i].value+",";
	      }
	   }
	   channIds = channIds.substr(0,channIds.length-1);
	   var AREA_MANAGE_ID = $("#AREA_MANAGE_ID").val();
	   var AREA_MANAGE_NAME = $("#AREA_MANAGE_NAME").val();
	   var AREA_MANAGE_TEL  = $("#AREA_MANAGE_TEL").val();
	   if(AREA_MANAGE_NAME==""){
	      parent.showErrorMsg("区域经理名称不能为空");
	      return;
	   } else {
 		   $.ajax({
					url: "chann.shtml?action=batchUpdate",
					type:"POST",
					dataType:"text",
					data:{"channIds":channIds,"AREA_MANAGE_ID":AREA_MANAGE_ID,"AREA_MANAGE_NAME":AREA_MANAGE_NAME,"AREA_MANAGE_TEL":AREA_MANAGE_TEL},
					complete: function(xhr){
						eval("jsonResult = "+xhr.responseText);
						if(jsonResult.success===true){
							parent.showMsgPanel("保存成功");
						}else{
							parent.showErrorMsg(utf8Decode(jsonResult.messages));
						}
					}
				});
	   }
	});
});


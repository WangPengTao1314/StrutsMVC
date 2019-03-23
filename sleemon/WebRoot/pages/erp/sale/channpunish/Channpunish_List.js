
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
	//页面初始化
	listPageInit("channpunish.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
			  btnDisable(["start","modify","delete","stop"]);
	 	 	  return;
	} 
    $("#punish").click(function(){
    	 var selRowId = $("#selRowId").val();
    	 var name=$("#name"+selRowId).val();
    	 var no=$("#no"+selRowId).val();
    	 $("#channInfo").val(name);
    	$("#punishDiv").show();
    });
    $("#close").click(function(){
    	$("#punishDiv").hide();
    	$("#PUNISH_REMARK").val("");
    	$("#channInfo").val("");
    });
    $("#audit").click(function(){
    	puinsh("puinsh");
    })
    $("#cancel").click(function(){
    	parent.showConfirm("您确认取消惩罚吗?","topcontent.puinsh('cancel');","N");
    	
    })
});
function checkState(state){
	btnReset();
	if("1"==state){
		btnDisable(["punish"]);
	}
	if("1"!=state){
		btnDisable(["cancel"]);
	}
}
function puinsh(type){
	closeWindow();
		var PUNISH_REMARK=$("#PUNISH_REMARK").val();
		var PUNISH_REMARK=$("#PUNISH_REMARK").val();
		if(""!=PUNISH_REMARK&&null!=PUNISH_REMARK){
			if(PUNISH_REMARK.length>120){
				parent.showErrorMsg(utf8Decode("惩罚说明超过最大字符数！"));
		           return false;
			}
		}
    	var CHANN_ID=$("#selRowId").val();
    	$.ajax({
		 url: "channpunish.shtml?action=toPunish&PUNISH_REMARK="+utf8(PUNISH_REMARK)+"&CHANN_ID="+utf8(CHANN_ID)+"&type="+utf8(type),
		 type:"POST",
	 	 dataType:"text",
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				if("puinsh"==type){
					parent.showMsgPanel("提交成功");
				}else{
					parent.showMsgPanel("取消成功");
				}
	            $("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		  }
	    });
}

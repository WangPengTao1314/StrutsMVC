$(function () {
     //初始化校验
	InitFormValidator("mainForm");
	$("#save").click(function(){
	    saveChann();
	});
	
	$("#query").click(function(){
	    queryChann();
	});
});

/**
 * 保存 1区域经理 2 区域 3 生产基地 4区服
 * @param {Object} flag
 * @return {TypeName} 
 */
function saveChann(flag){
	var strEids = "";
	var eids = parent.bottomcontent.document.getElementsByName("eid");
    for(var i=0;i<eids.length;i++){
       if(eids[i].checked){
          strEids = strEids+"'"+eids[i].value+"',";
       }
    }
	strEids = strEids.substr(0,strEids.length-1);
    if(null == strEids || "" == strEids){
	   parent.showErrorMsg("请至少选择一条渠道");
	   return;
 	}
    var jsonData = siglePackJsonData("querytb");
    $.ajax({
		url: "chann.shtml?action=batchUpdateStoreIn",
		type:"POST",
		dataType:"text",
		data:{"channIds":strEids,"jsonData":jsonData,"flag":flag},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				//$("#query").click();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function queryChann(){
	var AREA_NO = $("#AREA_NO").val();
	var AREA_NAME = $("#AREA_NAME").val();
	var CHANN_NAME= $("#CHANN_NAME").val();
	var CHANN_NO = $("#CHANN_NO").val();
	var CHANN_TYPE=$("#CHANN_TYPE").val();
	var CHAA_LVL=$("#CHAA_LVL").val();
	var i = 0;
	if(AREA_NO == ""){
		 i = parseInt(i)+1;
	}
	if(AREA_NAME == ""){
		 i = parseInt(i)+1;
	}
	if(CHANN_NAME == ""){
		 i = parseInt(i)+1;
	}
	if(CHANN_NO == ""){
		 i = parseInt(i)+1;
	}
	if(CHANN_TYPE ==""){
	   i = parseInt(i)+1;
	}
	if(CHAA_LVL == ""){
	   i = parseInt(i)+1;
	}
	if(i == 6){
		parent.showErrorMsg("请选择查询条件");
		return;
	}else{
		parent.bottomcontent.$("#AREA_NO").val(AREA_NO);
		parent.bottomcontent.$("#AREA_NAME").val(AREA_NAME);
		parent.bottomcontent.$("#CHANN_NAME").val(CHANN_NAME);
		parent.bottomcontent.$("#CHANN_NO").val(CHANN_NO);
		parent.bottomcontent.$("#CHAA_LVL").val(CHAA_LVL);
		parent.bottomcontent.$("#CHANN_TYPE").val(CHANN_TYPE);
		parent.bottomcontent.$("#queryForm").attr("action","chann.shtml?action=toStoreInList");
		parent.bottomcontent.$("#queryForm").submit();
    }
}


	function upStoreInFlag(){
	    var flag = document.getElementById("storeInflag").checked;
		if(flag){
			$("#IS_UPDATE_STOREIN_FLAG").val("1");
		}else{
			$("#IS_UPDATE_STOREIN_FLAG").val("0");
		}
	}
 
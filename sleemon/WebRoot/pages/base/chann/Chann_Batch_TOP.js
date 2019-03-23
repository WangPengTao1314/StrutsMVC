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
	var AREA_MANAGE_NAME = $("#AREA_MANAGE_NAME").val();
	if(1 == flag && (null == AREA_MANAGE_NAME || "" == $.trim(AREA_MANAGE_NAME))){
		parent.showErrorMsg("请选择'区域经理'");
		return;
	}
	var AREA_NAME = $("#AREA_NAME_S").val(); 
	if(2 == flag && (null == AREA_NAME || "" == $.trim(AREA_NAME))){
		parent.showErrorMsg("请选择'区域'");
		return;
	}
	var SHIP_POINT_NAME = $("#SHIP_POINT_NAME").val(); 
	if(3 == flag && (null == SHIP_POINT_NAME || "" == $.trim(SHIP_POINT_NAME))){
		parent.showErrorMsg("请选择'生产基地'");
		return;
	}
	var AREA_SER_NAME = $("#AREA_SER_NAME").val(); 
	if(4 == flag && (null == AREA_SER_NAME || "" == $.trim(AREA_SER_NAME))){
		parent.showErrorMsg("请选择'区域服务中心'");
		return;
	}
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
		url: "chann.shtml?action=batchUpdate",
		type:"POST",
		dataType:"text",
		data:{"channIds":strEids,"jsonData":jsonData,"flag":flag},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				$("#query").click();
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
	var PROV=$("#PROV").val();
	var CITY=$("#CITY").val();
	var i = 0;
	if(null == AREA_NO || "" == $.trim(AREA_NO)){
		 i = parseInt(i)+1;
	}
	if(null == AREA_NAME || "" == $.trim(AREA_NAME)){
		 i = parseInt(i)+1;
	}
	if(null == CHANN_NAME || "" == $.trim(CHANN_NAME)){
		 i = parseInt(i)+1;
	}
	if(null == CHANN_NO || "" == $.trim(CHANN_NO)){
		 i = parseInt(i)+1;
	}
	if(i == 4){
		parent.showErrorMsg("请填写查询条件");
		return;
	}
	
	parent.bottomcontent.$("#AREA_NO").val(AREA_NO);
	parent.bottomcontent.$("#AREA_NAME").val(AREA_NAME);
	parent.bottomcontent.$("#CHANN_NAME").val(CHANN_NAME);
	parent.bottomcontent.$("#CHANN_NO").val(CHANN_NO);
	parent.bottomcontent.$("#PROV").val(PROV);
	parent.bottomcontent.$("#CITY").val(CITY);
	parent.bottomcontent.$("#queryForm").attr("action","chann.shtml?action=toBatchList");
	parent.bottomcontent.$("#queryForm").submit();
}
 
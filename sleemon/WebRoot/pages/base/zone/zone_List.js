
/**
 * @module 系统管理
 * @func 行政区划
 * @version 1.1
 * @author 张涛
 */
$(function () {

	//页面初始化
	listPageInit("zone.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("zone.shtml?action=delete", "ZONE_ID");
	
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
			  btnDisable(["start","modify","delete","stop"]);
	 	 	  return;
	} 
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("将删除该行政区划信息,您确认删除吗?","topcontent.listDelConfirm();");
	 });
	
	
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将停用该行政区划，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将启用该行政区划，是否继续?","topcontent.listStartConfirm();");

	});
});

 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
	btnReset();
    if(state == "启用"){
		btnDisable(["start","modify","delete"]);
	}else if(state == "停用"){
		btnDisable(["stop","delete"]);
	}else if (state == "制定"){
		btnDisable(["stop"]);
	}
 }
 
 
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "zone.shtml?action=delete&ZONE_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				//删除树节点
				parent.leftcontent.deleteNodes(selRowId);
				//当前树选中节点
				var selNodesId = parent.leftcontent.getSelNodesId();
				window.location="zone.shtml?action=toList&ZONE_ID="+selNodesId;
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "zone.shtml?action=changeStateStop&ZONE_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "zone.shtml?action=changeStateStart&ZONE_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("状态修改成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
 
}
   
function setNation(){
	//选择国家的场合设定查询条件
	var tempCondition="";		
	var nationValue = $("#NATION").val();
	
	if('' != nationValue){	
		$("#zoneConditionNatioin").val( tempCondition+"NATION= '"+nationValue+"'");		
	}else{
		$("#zoneConditionNatioin").val( tempCondition);
	}	
	
	}

function setProv(){	
	//选择省的场合设定查询条件
	var tempCondition="";	
	var nationValue = $("#NATION").val();	
	var provValue = $("#PROV").val();
	
	if('' != nationValue&&'' == provValue){
		tempCondition=tempCondition+"NATION= '"+nationValue+"'";
	}else if('' == nationValue&&'' != provValue){
		tempCondition=tempCondition+"PROV= '"+provValue+"'";
	}else if('' != nationValue&&'' != provValue){
		tempCondition=tempCondition+"NATION= '"+nationValue+"'"+" and PROV= '"+provValue+"'";
	}
	
	$("#zoneConditionProv").val(tempCondition);
	
}

function setCity(){
	//选择城市的场合设定查询条件
	var tempCondition="";	
	var nationValue = $("#NATION").val();	
	var provValue = $("#PROV").val();
	var cityValue = $("#CITY").val();	
	
	if('' != nationValue&&'' == provValue&&'' == cityValue){
		tempCondition=tempCondition+"NATION= '"+nationValue+"'";
	}else if('' == nationValue&&'' != provValue&&'' == cityValue){
		tempCondition=tempCondition+"PROV= '"+provValue+"'";
	}else if('' == nationValue&&'' == provValue&&'' != cityValue){
		tempCondition=tempCondition+"CITY= '"+cityValue+"'";
	}else if('' == nationValue&&'' != provValue&&'' != cityValue){
		tempCondition=tempCondition+"PROV= '"+provValue+"'"+" and CITY= '"+cityValue+"'";
	}else if('' != nationValue&&'' == provValue&&'' != cityValue){
		tempCondition=tempCondition+"NATION= '"+nationValue+"'"+" and CITY= '"+cityValue+"'";
	}else if('' != nationValue&&'' != provValue&&'' == cityValue){
		tempCondition=tempCondition+"NATION= '"+nationValue+"'"+" and PROV= '"+provValue+"'";
	}else if('' != nationValue&&'' != provValue&&'' != cityValue){
		tempCondition=tempCondition+"NATION= '"+nationValue+"'"+" and PROV= '"+provValue+"'"+" and CITY= '"+cityValue+"'";
	}
	
	$("#zoneConditionCity").val(tempCondition);	
	
}




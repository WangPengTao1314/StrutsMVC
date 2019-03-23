/**
 * @prjName:喜临门营销平台
 * @fileName:Areasaleplan_List
 * @author zcx
 * @time   2014-12-5 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	
	//主从及主从从列表页面通用加载方法
	listPageInit("areasaleplan.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("saleplan.shtml?action=delete", "OPEN_BUSS_PKG_ID");
	$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("1");
	});
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("3");
//		parent.showConfirm("您确认撤销该条信息吗?","topcontent.toFlow(3);","N");
		parent.showConfirm("您确认撤销该条信息吗?","topcontent.revoke();","N");
		
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("2");
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	});
	
	$("#modifyT").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit");
	 	 }
	});
	
	$("#delete").click(function(){
	   var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	   } 
	   parent.showConfirm("您确认删除该条信息吗?","topcontent.listDelConfirm();");
	});
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","delete","commit","revoke","audit","flow"]);
      return;
    }
});

function orpenWindow(){
     var arguemnts = new Object();
     arguemnts.window = window;
	 window.showModalDialog("areasaleplan.shtml?action=toBatch",arguemnts,"dialogwidth=1400px; dialogheight=500px; status=no");
	 var url="areasaleplan.shtml?action=toList";
	 $("#topcontent").attr("src",url);
}

 function getYear(){
   var PLAN_YEAR = $("#PLAN_YEAR").val();
   var STYLE  = $("#STYLE").val();
   qryformChecked('queryForm');
   parent.document.getElementById("selRowId").value="";
   $("#selRowId").val("");
   var url = "areasaleplan.shtml?action=toListT";
   $("#queryForm").attr("action",url+"&search=true&module="+getModule()+"&PLAN_YEAR="+PLAN_YEAR+"&STYLE="+STYLE);
   $("#queryForm").submit();
 }

 function  resetState(state){
    if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=提交
	if (state == "提交") {
	    btnDisable(["delete","modify","commit"]);
	}
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modify","commit","audit"]);
	} 
 }

 
 
 
function revoke(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "areasaleplan.shtml?action=revoke",
		type:"POST",
		data:{"AREA_SALE_PLAN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("撤销成功");
				$("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
 
 //删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "areasaleplan.shtml?action=delete&AREA_SALE_PLAN_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function state(params){
    var state = params.value;
	var PVG_EDIT_AUDIT = $("#PVG_EDIT_AUDIT").val();
    //按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "提交") {
		if(1 == PVG_EDIT_AUDIT){
			btnDisable(["delete","commit"]);
		}else{
			btnDisable(["delete","modifyT","commit"]);
		}
	}
	
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modifyT","modify","commit","audit"]);
	} 
 }
 
 function setHiddenCol(oTable,iCol) {
    if(document.getElementById("AreaNo").style.display=="block"){
	    for (i=0;i < oTable.rows.length ; i++)    
	    {    
	      if(typeof(oTable.rows[i].cells[iCol])!="undefined"){
	        oTable.rows[i].cells[iCol].style.display="none";
	        oTable.rows[i].cells[2].style.display="none";
	       }    
	    } 
	     btnDisable(["add","modify","delete","commit","revoke","audit","flow"]);
         var leng = document.getElementsByName("STYLE");
          for(var j=0;j<leng.length;j++){
              document.getElementsByName("STYLE")[j].value ="1";
          }
         return;
    } else {
         for (i=0;i < oTable.rows.length ; i++)    
	     {    
	      if(typeof(oTable.rows[i].cells[iCol])!="undefined"){
            oTable.rows[i].cells[iCol].style.display="block";
	        oTable.rows[i].cells[2].style.display="block"; 
	       }
	     }
	     btnReset();
         var len = document.getElementsByName("state");
         for(var i=0;i<len.length;i++){
          resetState(document.getElementsByName("state")[i].value);
         }
         var leng = document.getElementsByName("STYLE");
         for(var j=0;j<leng.length;j++){
            document.getElementsByName("STYLE")[j].value = "0";
         }
         return;
    }  
 } 
 
function load(){
     var styleT= "";
     var AREA_SALE_PLAN_ID = $("#AREA_SALE_PLAN_ID").val();
     for(var i=0;i<document.getElementsByName("STYLE").length;i++){
         styleT = document.getElementsByName("STYLE")[0].value;
     }
     var oTable = document.getElementById('ordertb');
     if(styleT==1){
        for (i=0;i < oTable.rows.length ; i++)    
	    {    
	       if(typeof(oTable.rows[i].cells[1])!="undefined"){
		       oTable.rows[i].cells[1].style.display="none";
		       oTable.rows[i].cells[2].style.display="none";  
	       }  
	    } 
        btnDisable(["add","modify","delete","commit","revoke","audit","flow"]);
        var leng = document.getElementsByName("STYLE");
        for(var j=0;j<leng.length;j++){
           document.getElementsByName("STYLE")[j].value  = "1";
        }
        return;
     }
     if(styleT==""){
      if(document.getElementById("AreaNo").style.display=="none"){
          var leng = document.getElementsByName("STYLE");
          for(var j=0;j<leng.length;j++){
             document.getElementsByName("STYLE")[j].value = "1";
          }
      }
      if(document.getElementById("AreaNo").style.display=="block"){
          var leng = document.getElementsByName("STYLE");
          for(var j=0;j<leng.length;j++){
            document.getElementsByName("STYLE")[j].value = "0";
          }
        }
      return;
   }
   if(styleT==0){
       for (i=0;i < oTable.rows.length ; i++)    
	   {   
	     if(typeof(oTable.rows[i].cells[1])!="undefined"){ 
	       oTable.rows[i].cells[1].style.display="block";
	       oTable.rows[i].cells[2].style.display="block"; 
	      }   
	   } 
       btnReset();
       var len = document.getElementsByName("state");
       for(var i=0;i<len.length;i++){
          resetState(document.getElementsByName("state")[i].value);
       }
       var leng = document.getElementsByName("STYLE");
       for(var j=0;j<leng.length-1;j++){
           document.getElementsByName("STYLE")[j].value = "0";
       }
       return;
   }
}
 
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/areasaleplan.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
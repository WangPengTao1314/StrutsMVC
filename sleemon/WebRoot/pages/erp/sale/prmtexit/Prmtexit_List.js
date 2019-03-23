/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtexit_List
 * @author chenj
 * @time   2013-10-19 16:54:28 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("prmtexit.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("prmtexit.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("prmtexit.shtml?action=delete", "PRMT_GOODS_EXTD_ID");
	
	
	$("#commit").click(function () {
		var selRowId = document.getElementById("selRowId").value;
		 
		if (selRowId == "" || null==selRowId) {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		var state = document.getElementById("state"+selRowId).value;
		if(state == "提交" || state==""){
			parent.showErrorMsg(utf8Decode("不能重复提交"));
			return;
		}
		commitPrmtexit(selRowId)
	});
});
function commitPrmtexit(selRowId){
 
	$.ajax({
	 	url: "prmtexit.shtml?action=toCommit&PRMT_GOODS_EXTD_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
 				//document.location.reload();
				saveSuccess("提交成功","prmtexit.shtml?action=toFrame");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function multiRadioJsonData(tableid,isAll){
	if(null == tableid){
		tableid = "jsontb";
	}
	if(null == isAll){
		isAll = false;
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = false;
		if(isAll){
			isEdit = true;
		}else{
			isEdit = $(this).find("input[type='radio']:eq(0)").attr("checked");
		}
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
//			var inputs = $(this).find(":input");
			var tds = $(this).find("td");
			tds.each(function(){
				var inputs = $(this).find(":input");
				if(inputs.length>0){
					inputs.each(function(){
						if(null != $(this).attr("json")){
							var key;
							var value;
							var type = $(this).attr("type");
							if(!isFirst && "checkbox" == type){
								key = $(this).attr("json");
								if($(this).attr("checked")){
									value= 1;
								}else{
									value= 0;
								}
							}else if("radio" == type){
								if($(this).attr("checked")){
									key = $(this).attr("json");
									value= $(this).attr("value");
								}
							}else{
								key = $(this).attr("json");
								value = $(this).attr("value");
								
								isFirst = false;
							}
							var inputtype = $(this).attr("inputtype");
							jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
						}
					});
				}else{
					if(null != $(this).attr("json")){
						var k = $(this).attr("json");
					    var text = $.trim($(this).text());
					    jsonData = jsonData+ "'" + k + "':'" + text +"',";
				    }
				}
				
				
			});
			
		
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit","issuance","over"]);
	}
}
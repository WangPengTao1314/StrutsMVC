/**
 * @prjName:喜临门营销平台
 * @fileName:Sjzdwh_List
 * @author chenj
 * @time   2014-01-30 10:18:20 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("xmsjzdwh.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("xmsjzdwh.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("xmsjzdwh.shtml?action=delete", "DATA_ID");
});

 function setSelOperateEx(obj){
	var value =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
   btnReset();
     if ('启用' == value ){
     btnDisable(["modify","delete"]);
   }else if ('制定' == value){
      btnDisable(["stop"]);
   } else if('停用' == value ){
      btnDisable(["stop","delete"]);
   }else {
      btnDisable(["start","stop"]);
   }
 }

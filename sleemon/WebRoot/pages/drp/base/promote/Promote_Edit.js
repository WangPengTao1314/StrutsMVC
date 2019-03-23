/**
 * @prjName:喜临门营销平台
 * @fileName:活动
 * @author zzb
 * @time    
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("promote.shtml?action=edit","PROMOTE_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
	
});



function formCheckedEx(){
 
	var BEG_DATE = $("#BEG_DATE").val();
	var END_DATE = $("#END_DATE").val();
	if(!dateComplare(END_DATE,BEG_DATE)){
		parent.showErrorMsg(utf8Decode("'活动结束日期'应在'活动开始日期'之后！"));
	    return false;
	}
	
	 
	var now = new Date().format("yyyy-MM-dd"); 
	var b = dateComplare(END_DATE,now);
	if(!b){
		parent.showErrorMsg("'活动结束日期'应在今天后");
		flag =  false;
		return false;
	}
	
	var PROMOTE_DESC = $("#PROMOTE_DESC");
	var PROMOTE_DESC_V = PROMOTE_DESC.val();
	PROMOTE_DESC_V = $.trim(PROMOTE_DESC_V);
	if(null == PROMOTE_DESC_V || "" == PROMOTE_DESC_V){
  	    parent.showErrorMsg("请输入'"+PROMOTE_DESC.attr("label")+"'");
        return false;
	}
	
	return true;
}



function putPromotrNo(obj){
	var prefix = $("#prefix").val();
	var suffix = $("#suffix").val();
	$("#PROMOTE_NO").val(prefix+suffix);
	
}


 //日期比较  
 // date1=2014-06-01  
 // date2=2014-05-01  return  true
 function dateComplare(date1, date2) {
	if(null == date1 || "" == date1){
		return true;
	}
	if(null == date2 || "" == date2){
		return true;
	}
	date1 = date1.toString(); 
    var arr = date1.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    
    date2 = date2.toString(); 
    var arrs = date2.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();
    if (starttimes >= lktimes) {
        return true;
    } 
    
    return false;
     

}
 

 
  /* 格式化日期 */
Date.prototype.format = function(style){
var o = {
    "M+" : this.getMonth() + 1, //month
    "d+" : this.getDate(),      //day
    "h+" : this.getHours(),     //hour
    "m+" : this.getMinutes(),   //minute
    "s+" : this.getSeconds(),   //second
    "w+" : "天一二三四五六".charAt(this.getDay()),   //week
    "q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
    "S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(style)){
    style = style.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
}
for(var k in o){
    if(new RegExp("("+ k +")").test(style)){
      style = style.replace(RegExp.$1,
        RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    }
}
return style;
};



 


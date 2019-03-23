/**
 * @prjName:订货订单处理
 * @fileName:Goodsorder_List
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("sergoodsorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("sergoodsorder.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("sergoodsorder.shtml?action=delete", "GOODS_ORDER_ID");
	
	//form校验设置
	InitFormValidator(0);

	$("#save").click(function(){
		save();
	});
	
	$("#disposed").click(function(){
		disposed();
	});
});


function save(){
	var selRowId = $("#selRowId").val();
	var inputs = $("#ordertb").find("input[type='radio'][checked=true]").parent().parent().find(":input");
	var flag = true;
	var jsonData = "{"
	inputs.each(function(){
		if(null != $(this).attr("json")){
			var key = $(this).attr("json");
		    var value = $(this).val();
		    if(null == value || ""==value){
		    	flag =  false;
		    	return flag;
		    }
		    jsonData = jsonData+ "'" + key + "':'" +value+"',";
		}
	});
	
	if(!flag){
		parent.showMsgPanel("请选择发货点");
		return false;
	}
	jsonData = jsonData.substr(0,jsonData.length-1)+"}";
	
	 $.ajax({
	 	url: "sergoodsorder.shtml?action=edit&GOODS_ORDER_ID="+selRowId+"&parentJsonData="+utf8(jsonData),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				//parent.window.topcontent.location = $("#pageForm").attr("action"); 
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function disposed(){
	var selRowId = $("#selRowId").val();
	var url = "sergoodsorder.shtml?action=toDisposed";
	var selRowId = $("#selRowId").val();
 	if(null == selRowId || "" == selRowId){
 		 parent.showErrorMsg("请选择一条记录");
 	}else{
 	     var paramUrl=document.getElementById("paramUrl");
 	     if(paramUrl!=null&&paramUrl.value!="") {
 	       window.parent.location=url+reqParamsEx()+"&module="+getModule()+"&paramUrl="+utf8((paramUrl.value.replaceAll("&","andflag")).replaceAll("=","equalsflag")); 
 	     }else {
 	      window.parent.location=url+reqParamsEx()+"&module="+getModule(); 
 	     }
 	}
}
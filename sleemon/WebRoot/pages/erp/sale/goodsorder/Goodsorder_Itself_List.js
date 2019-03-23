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
	listPageInit("goodsorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("goodsorder.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("goodsorder.shtml?action=delete", "GOODS_ORDER_ID");
	
	//form校验设置
	InitFormValidator(0);

	$("#return").click(function(){
		var selRowId = $("#selRowId").val();
 	    if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}
		parent.showConfirm("您确认要退回吗?","topcontent.returnConfirm();","N");
	});
	 
	
	$("#commit").click(function(){
		 commit();
	});
});


//回退
function returnConfirm(){
	closeWindow();
	var return_v = window.showModalDialog('goodsorder.shtml?action=toReason',self,'dialogwidth=510px; dialogheight=400px; status=no');
	if("1" == return_v){
		sendMessage();
		saveSuccess("退单成功","goodsorder.shtml?action=toFrame");
    }

}

/**
 * 回退成功后 给制单人发消息
 * @return {TypeName} 
 */
function sendMessage(remark){
	var _xtyhids = $("#XTYHID").val();
	var _jgxxids = "";//$("#JGXXID").val();//如果加了部门，就会对同部门的人发 信息
	var _bmxxids = "";// $("#BMXXID").val();//同上
	var selRowId = $("#selRowId").val();
	var NO = $.trim($("#NO_"+selRowId).text());
	var reason = $("#msg").val();
	var _fsnr = "单据'"+NO+"',因为'"+reason+"'退单";
	if(null != remark && "" !=remark){
		_fsnr = remark;
	}
	$.ajax({
		url: "firstPage.shtml?action=txInsertMessage",
		type:"POST",
		dataType:"text",
		data:{xtyhids:_xtyhids,bmxxids:_bmxxids,jgxxids:_jgxxids,fsnr:_fsnr},
		complete: function(xhr){
		    eval("json = "+xhr.responseText);
		    if(json.success==true){
		        //parent.showErrorMsg(utf8Decode(json.messages));
		    }else{
		       // parent.showErrorMsg(utf8Decode(json.messages));
		    }
		}
	});
}
	

function getShipId(){
	return $("#SHIP_POINT_ID").val();
}

function commit(){
	//获取json数据
//	if(!parent.bottomcontent.checkForm()){
//		return false;
//	}
//	if(!parent.bottomcontent.checkOrderNum()){
//		return false;
//	}
	save();
}
function save(){
	
	var parentData = parent.topcontent.siglePackJsonData("ordertb");
//	alert(parentData);
//	var childData = parent.bottomcontent.getChildJsonData();
	var childData = parent.bottomcontent.multiRadioJsonData("jsontb",true);
	 
	$.ajax({
	 	url: "goodsorder.shtml?action=toCommit",
		type:"POST",
		data:{"parentData":parentData,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel("提交成功");
				saveSuccess("提交成功","goodsorder.shtml?action=toFrame");
				//parent.window.topcontent.location = "goodsorder.shtml?action=toFrame"//;$("#pageForm").attr("action"); 
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function disposed(){
	var selRowId = $("#selRowId").val();
	var url = "goodsorder.shtml?action=toDisposed";
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


 //设置 一览页面 ‘订货总额’
 function setTotalAmount(total){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 var id ="TOTL_AMOUNT_"+selRowId
	 $("#"+id).text(total+" ");
 }
 function getORDER_CHANN_ID(){
	 var ORDER_CHANN_ID=$("#ORDER_CHANN_ID").val();
	 return ORDER_CHANN_ID;
 }
 
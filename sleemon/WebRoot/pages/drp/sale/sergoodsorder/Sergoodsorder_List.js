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
	
	$("#return").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}
		parent.showConfirm("您确认要退回吗?","topcontent.returnConfirm();","N");
	});
	
	$("#commit").click(function(){
		var selRowId = $("#selRowId").val();
 	    if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}
		commit();
	});
	
	
	$("#query").click(function(){
	     $("#STATE option[text='总部退回']").remove();
	});
	
	 
});

//退回
function returnConfirm(){
	closeWindow();
	var return_v = window.showModalDialog('sergoodsorder.shtml?action=toReason',self,'dialogwidth=510px; dialogheight=400px; status=no');
	if("1" == return_v){
		var reason = $("#msg").val();
		sendMessage();
    	saveSuccess("退单成功","sergoodsorder.shtml?action=toFrame");
    }

}

/**
 * 回退成功后 给制单人发消息
 * @return {TypeName} 
 */
function sendMessage(remark){
	var selRowId = $("#selRowId").val();
//	var _xtyhids = $("#XTYHID").val();
	var _xtyhids = $("#CREATOR"+selRowId).val();
	var _jgxxids = "";//$("#JGXXID").val();//如果加了部门，就会对同部门的人发 信息
	var _bmxxids = "";// $("#BMXXID").val();//同上
	var NO = $("#GOODS_ORDER_NO"+selRowId).val();
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
		       // parent.showErrorMsg(utf8Decode(json.messages));
		    }else{
		       // parent.showErrorMsg(utf8Decode(json.messages));
		    }
		}
	});
}
	
function save(o){
	 var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "sergoodsorder.shtml?action=edit&GOODS_ORDER_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				if('1' != o){
					parent.showMsgPanel("保存成功");
					rmoveBtnDis(["disposed"]);
					btnDisable(["save"]);
					$("#flag_" + selRowId).val("flag");
					
					//parent.window.topcontent.location = $("#pageForm").attr("action"); 
				}
				
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//提交
function commit(){
	var selRowId = $("#selRowId").val();
	var childData = "";
	try{
		childData = parent.bottomcontent.multiRadioJsonData("ordertb",true);
	}catch(e){
		
	}
	 
	$.ajax({
	 	url: "sergoodsorder.shtml?action=toCommit",
		type:"POST",
		data:{"selRowId":selRowId,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel("提交成功");
				saveSuccess("提交成功","sergoodsorder.shtml?action=toFrame");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function disposed(){
	var url = "sergoodsorder.shtml?action=toDisposed";
	var selRowId = $("#selRowId").val();
 	if(null == selRowId || "" == selRowId){
 		 parent.showErrorMsg("请选择一条记录");
 		 
 	}else{
// 		var SHIP_POINT_ID = $("#ordertb").find("input[type='radio'][checked=true]").parent().parent().find("input[name='SHIP_POINT_ID']").eq(0).val();
// 		if(null == SHIP_POINT_ID || ""== SHIP_POINT_ID){
// 			parent.showErrorMsg("请选择发货点");
//		    return false;
// 		}
 	     var paramUrl=document.getElementById("paramUrl");
 	     if(paramUrl!=null&&paramUrl.value!="") {
 	       window.parent.location=url+reqParamsEx()+"&module="+getModule()+"&paramUrl="+utf8((paramUrl.value.replaceAll("&","andflag")).replaceAll("=","equalsflag")); 
 	     }else {
 	      window.parent.location=url+reqParamsEx()+"&module="+getModule(); 
 	     }
 	}
}

 function rmoveBtnDis(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).removeAttr("disabled");
	}
 }

 function getORDER_CHANN_ID(){
	 var selRowId = $("#selRowId").val();
	 var ORDER_CHANN_ID = $("#ORDER_CHANN_ID"+selRowId).val();
	 return ORDER_CHANN_ID;
 }
  
 function tt(){
	 //可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = $("#state" + selRowId).val();
	var ORDER_CHANN_ID = $("#state" + selRowId).parent().parent().find("input[name='SHIP_POINT_ID']").val();
	if(null==ORDER_CHANN_ID || ""==ORDER_CHANN_ID){
		btnDisable(["disposed"]);
	}else{
		rmoveBtnDis(["disposed"]);
	}
 }
 
 function setFlag(id){
	$("#flag_" + id).val("disposed"); 
	  
 }
 
  //设置 一览页面 ‘订货总额’
 function setTotalAmount(total){
	 var selRowId = $("#selRowId").val();
	 var id ="TOTAL_AMOUNT"+selRowId
	 $("#"+id).text(total+" ");
 }
 
 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = $("#state" + selRowId).val();
	
	//按钮状态重置
	btnReset();
	
    if(state == "已处理" || state == "总部退回"){
		btnDisable(["disposed","return","commit"]);
	} 
    
 }
 
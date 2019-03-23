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
		 btnDisable(["commit"]);
		var selRowId = $("#selRowId").val();
 	    if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}
		commit();
	});
	 $("#expdate").click(function(){
		 $("#queryForm").attr("action","goodsorder.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
	
	$("#query").click(function(){
	     $("#STATE option[text='区域服务中心退回']").remove();
	});
	
	
   $("#mergeCommit").click(function(){
	   //预先清除上次操作遗留的数据，防止重复提交
	   $("#MERGER_GOODS_ORDER_IDS").val("");
	   $("#childJson").val("");
	   $("#MERGER_REMARKS").val("");
	   
	   var GOODS_ORDER_ID = $("#selRowId").val();
	   var inputs =  $("#ordertb tr:gt(0) input:checked").parent().parent().find("input[type='hidden']");
	   var jsonData = sigleRowJsonData(inputs);
	  
	   if(null == GOODS_ORDER_ID || "" == GOODS_ORDER_ID){
		   parent.showErrorMsg("请选择一条记录");
 		   return;
	   }
	   
	   window.showModalDialog("goodsorder.shtml?action=toMergeFrame&selRowId="+GOODS_ORDER_ID+"&jsonData="+utf8(jsonData),window,"dialogwidth=980px; dialogheight=800px; status=no");
//	      window.open("goodsorder.shtml?action=toMergeFrame&selRowId="+GOODS_ORDER_ID+"&jsonData="+utf8(jsonData),"qq","'height=600px,width=850px");
	   var MERGER_GOODS_ORDER_IDS = $("#MERGER_GOODS_ORDER_IDS").val();
	   var childJson = $("#childJson").val();
	   var selRowId = $("#selRowId").val();
	   var MERGER_REMARKS = $("#MERGER_REMARKS").val();
	     
	   var len = stringLength(MERGER_REMARKS);
	    var temp_REMARK = MERGER_REMARKS;
	   if(len>250){
		   temp_REMARK = diGui(83,MERGER_REMARKS); 
	   }
	    
       if(null == MERGER_GOODS_ORDER_IDS || "" == MERGER_GOODS_ORDER_IDS){
        	return;
       }
      
	   $.ajax({
		url: "goodsorder.shtml?action=mergerBill",
		type:"POST",
		dataType:"text",
		data:{"MERGER_GOODS_ORDER_IDS":MERGER_GOODS_ORDER_IDS,"selRowId":selRowId,"childJson":childJson,"MERGER_REMARKS":temp_REMARK},
		complete: function(xhr){
		    eval("json = "+xhr.responseText);
		    if(json.success==true){
		        //parent.showMsgPanel(utf8Decode(json.messages));
		        saveSuccess(utf8Decode(json.messages),"goodsorder.shtml?action=toFrame");
		    }else{
		        parent.showErrorMsg(utf8Decode(json.messages));
		    }
		}
	});
	   
   });
    
 //时间差变色 
 //dateDiffColor();
	 
});


//递归出最大长度的备注最大长度250
function diGui(l,ostr){
  var str = ostr.substr(0,l);
  var len = stringLength(str);
  if(len<250){
	   l = l+1
	   return diGui(l,ostr);
  }else{
	  return str;
  }
}



//回退
function returnConfirm(){
	closeWindow();
	var return_v = window.showModalDialog('goodsorder.shtml?action=toReason',self,'dialogwidth=510px; dialogheight=400px; status=no');
	if("1" == return_v){
		var reason = $("#msg").val();
		sendMessage();
    	//saveSuccess("退单成功","goodsorder.shtml?action=toFrame");
    	parent.showMsgPanel("退单成功");
	    $("#pageForm").submit();
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
	 	url: "goodsorder.shtml?action=edit&GOODS_ORDER_ID="+selRowId,
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
	parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
	var childData = parent.bottomcontent.multiRadioJsonData("ordertb",true);
	$.ajax({
	 	url: "goodsorder.shtml?action=toCommit",
		type:"POST",
		data:{"selRowId":selRowId,"childData":childData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("提交成功");
				$("#pageForm").submit();
				//parent.window.topcontent.location = "goodsorder.shtml?action=toFrame"//;$("#pageForm").attr("action"); 
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			
			rmoveBtnDis(["commit"]);
		}
	});
}


function disposed(){
	var url = "goodsorder.shtml?action=toDisposed";
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
		btnDisable(["disposed","return","commit","mergeCommit","editRemark"]);
	} 
    
 }
 
 
 function busDisableCommit(){
	 btnDisable(["commit"]);
 }
 
 
  /*
 *将单个form封装成json串
 *
 *@param tableid
 *@return json格式字符串
 */
function sigleRowJsonData(inputs){
	var jsonData = "{";
	inputs.each(function(){
		if(null != $(this).attr("json")){
			var key;
			var value; 
			var type = $(this).attr("type");
			if("checkbox" == type){
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
				value= $(this).attr("value");
			}
			var inputtype = $(this).attr("inputtype");
			jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
		}
	});
	return 	jsonData = jsonData.substr(0,jsonData.length-1)+"}"
}


function showPage(){
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 		  return;
	}
	var REMARK = $("#REMARK"+selRowId).val();
	$("#REMARK").val(REMARK);
	$("#edit_remark").show();
				
//	$.ajax({
//	 	url: "goodsorder.shtml?action=loadParentModel",
//		type:"POST",
//		data:{"selRowId":selRowId},
//		complete: function(xhr){
//			eval("jsonResult = "+xhr.responseText);
//			if(jsonResult.success===true){
//				var data = jsonResult.data;
//				var REMARK = data.REMARK;
//				$("#REMARK").val(REMARK);
//				$("#edit_remark").show();
////				$("#pageForm").submit();
//			}else{
//				 
//			}
//		}
//	});
//		
	
}


function modifyRemark(){
	var selRowId = $("#selRowId").val();
	var REMARK =  $("#REMARK").val();
	if (!mustInputAllCheck($("#REMARK"))) {
		return false;
	}
	$.ajax({
	 	url: "goodsorder.shtml?action=modifyRemark",
		type:"POST",
		data:{"GOODS_ORDER_ID":selRowId,"REMARK":REMARK},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				closePage();
				$("#pageForm").submit();
			}else{
				 
			}
		}
	});
}




function closePage(){
	$("#edit_remark").hide();
}

function getBILL_TYPE(){
	var selRowId = $("#selRowId").val();
	var BILL_TYPE = $("#BILL_TYPE"+selRowId).val();
	return BILL_TYPE;
}

//时间差变色  
function dateDiffColor(){
	var inputs = $("#ordertb tr:gt(0)").find("input[name='DATE_DIFF']");
	inputs.each(function(){
		var date = $(this).val();
		date = parseInt(date);
		if(date >=1 ){
			$(this).parent().parent().find("td").css("background-color", "#E693A2");//花号还原
			  
		}
	});
}


 
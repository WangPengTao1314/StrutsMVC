/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	InitFormValidator("ordertbForm"); 
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox[name='mx_checkbox']").attr("checked","true");
		}else{
			$("#ordertb :checkbox[name='mx_checkbox']").removeAttr("checked");
		}
		
		 
	});
	
	$("#close").click(function(){
	  	var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("关闭此行前，请确认U9系统该行已经关闭","bottomcontent.multiRecClose();");
   });
	
	
  $("#save").click(function(){
	  	var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
	  childSave();	 
   });
	  
	setSelOperateEx();
	
 
	
});
 
 





//行关闭
function multiRecClose(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = $("#state"+selRowId).val();
	
	var FROM_BILL_ID = parent.topcontent.$("#FROM_BILL_ID"+selRowId).val();
	if(null != FROM_BILL_ID && "" != FROM_BILL_ID){
		parent.showErrorMsg("有来源发运单据的不能撤销");
		return;
	}
	
//	var IS_ALL_FREEZE_FLAG = parent.topcontent.$("#IS_ALL_FREEZE_FLAG"+selRowId).val();
//	if(1 == IS_ALL_FREEZE_FLAG || "1" == IS_ALL_FREEZE_FLAG){
//		parent.showErrorMsg("请先解除冻结，再撤销");
//		return;
//	}
	
	 var checkBox = $("#ordertb tr:gt(0) input:checked");
	 var ids = "";
	 if(checkBox.length>0){
		//获取所有选中的记录
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
	 }
	
	$.ajax({
		url: "turnoverhd.shtml?action=closeChilds",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_ID":selRowId,"DELIVER_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					parent.topcontent.$("#queryForm").submit();
				});
//				saveSuccess(utf8Decode(jsonResult.messages),"pdtdeliver.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	 	
	 
	 
	 
}


function childSave(){
	if(!checkChild()){
		return;
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	var childData = parent.bottomcontent.multiPackJsonData("ordertb");
 
  	$.ajax({
		url: "turnoverhd.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":childData,"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
	        eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 parent.showMsgPanel(utf8Decode(jsonResult.messages));
				 parent.bottomcontent.$("#label_1").click();
			}else{
				 parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
	   }
  });
}



//校验  
function checkChild(allChecked){
//	if(allChecked){
//		$("#ordertb input[name='mx_checkbox']").attr("checked","checked");
//	}
    var childCheckBoxs = $("#ordertb input[name='mx_checkbox']:checked");
    var flag = true;
    childCheckBoxs.each(function(){
    	//check新规格
    	var SPCL_TECH_FLAG = $(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
    	SPCL_TECH_FLAG = parseInt(SPCL_TECH_FLAG);
    	if(SPCL_TECH_FLAG == 1){
//    		var NEW_SPEC = $(this).parent().parent().find("input[name='NEW_SPEC']").val();
//    		NEW_SPEC = $.trim(NEW_SPEC);
//    		if(null == NEW_SPEC || "" == NEW_SPEC){
//    			parent.showErrorMsg("请填写'新规格'")
//	    		flag = false;
//			    return false;
//    		}
    		
//    		var NEW_COLOR = $(this).parent().parent().find("input[name='NEW_COLOR']").val();
//			NEW_COLOR = $.trim(NEW_COLOR);
//			if(null == NEW_COLOR || "" == NEW_COLOR){
//				parent.showErrorMsg("请填写'新花号'")
//	    		flag = false;
//			    return false;
//			}
    		
    	}
    	
     
    });
    
    return flag;
}




function checkline(obj){
	$(obj).parent().parent().find("input[name='mx_checkbox']").attr("checked","true");
}





function setVolum(obj){
  var TRUCK_TYPE =	$("#TRUCK_TYPE").val();
  	$.ajax({
		url: "carcalculate.shtml?action=getVolum",
		type:"POST",
		dataType:"text",
		data:{"carType":TRUCK_TYPE},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var volum = jsonResult.data.VOLUME;
				 if(null == volum || ""==volum){
					 volum = 0;
				 }
				 $("#carVolum").val(volum);
				 countVolum();
			}else{
				 
			}
		}
	});
}

function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (state == "\u63d0\u4ea4"||state == "\u9000\u56de"||state == "\u5ba1\u6838\u901a\u8fc7") {
			btnDisable(["recover","cancel","convert"]);
		}
		
		if(state != "已提交生产"){
			btnDisable(["close"]);
		}
		if(state != "未提交"){
			btnDisable(["save"]);
		}
	}
}


 
//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}










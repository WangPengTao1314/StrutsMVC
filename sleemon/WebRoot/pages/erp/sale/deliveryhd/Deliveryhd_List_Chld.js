/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	InitFormValidator("ordertbForm"); 
	headColumnSort("ordertb","ordertbForm");
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox[name='DELIVER_ORDER_DTL_ID']").attr("checked","true");
		}else{
			$("#ordertb :checkbox[name='DELIVER_ORDER_DTL_ID']").removeAttr("checked");
		}
		
		 
	});
		$("#close").click(function(){
	  	var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("关闭之后不可逆,请确认真的要关闭吗","bottomcontent.multiRecClose();");
   });
	
	
  $("#save").click(function(){
  	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	  childSave();	 
   });
	$("#delete").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	})
	setSelOperateEx();
	changeColor();
	
	countTotal();
});

function childSave(){
	if(!checkChild()){
		return;
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	var childData = parent.bottomcontent.multiPackJsonData("ordertb");
  	$.ajax({
		url: "deliveryhd.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":childData,"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
	        eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 parent.showMsgPanel(utf8Decode(jsonResult.messages));
				 parent.$("#YT_MSG_BTN_OK").click(function(){
					parent.$("#label_1").click();
				});
				 
			}else{
				 parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
	   }
  });
}



//校验  
function checkChild(){
	var flag=true;
	var checkBox = $("input[name='DELIVER_ORDER_DTL_ID']:checked");
	if(checkBox.length<1){
		showErrorMsg("请选择明细记录");
		return;
	}
	checkBox.each(function(){
		var PLAN_NUM=$(this).parent().parent().find("input[name='PLAN_NUM']").val();
		if(""==PLAN_NUM||null==PLAN_NUM){
			showErrorMsg("请输入计划发运数量");
			flag=false;
			return false;
		}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re2.test(PLAN_NUM)){
            parent.showErrorMsg("计划发运数量最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
	})
    return flag;
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
		
		if(state != "已提交库房"){
			btnDisable(["close"]);
		}
		if(state != "未提交"){
			btnDisable(["save","add","delete"]);
		}
	}
}


 
//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "deliveryhd.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ORDER_DTL_IDS":ids,"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function countAmount(id){
	var PLAN_NUM=$("#PLAN_NUM"+id).val();
	if(""==PLAN_NUM||null==PLAN_NUM){
		PLAN_NUM=0;
	}
	PLAN_NUM=isNaN(PLAN_NUM)?0:parseFloat(PLAN_NUM);
	var DECT_PRICE=$("#DECT_PRICE"+id).html();
	if(""==DECT_PRICE||null==DECT_PRICE){
		DECT_PRICE=0;
	}
	DECT_PRICE=isNaN(DECT_PRICE)?0:parseFloat(DECT_PRICE);
	var amount=parseFloat(PLAN_NUM*DECT_PRICE).toFixed(2);
	$("#amount"+id).html(amount);
	countTotal();
}
function countTotal(){
	var checkBox = $("input[name='DELIVER_ORDER_DTL_ID']");
	var total_vol=0;//总体积
	var total_num=0;//总数量
	var total_amount=0;//总金额
	var total_CREDIT_FREEZE_PRICE = 0;
	var total_sendNum = 0;
	var total_sendVolume = 0;
	var total_sendAmount = 0;
	
	if(checkBox.length>0){
		checkBox.each(function(){
			var VOLUME=$(this).parent().parent().find("input[name='VOLUME']").val();
			if(""==VOLUME||null==VOLUME){
				VOLUME=0;
			}
			var PLAN_NUM=$(this).parent().parent().find("input[name='PLAN_NUM']").val();
			if(""==PLAN_NUM||null==PLAN_NUM){
				PLAN_NUM=0;
			}
			var SENDED_NUM = $(this).parent().parent().find("input[name='SENDED_NUM']").val();
			SENDED_NUM = Number(SENDED_NUM);
			if(isNaN(SENDED_NUM)){
				SENDED_NUM = 0;
			}
			var DECT_PRICE = $(this).parent().parent().find("td[name='DECT_PRICE']").text();
			DECT_PRICE = $.trim(DECT_PRICE);
			DECT_PRICE = Number(DECT_PRICE);
			if(isNaN(DECT_PRICE)){
				DECT_PRICE = 0;
			}
			
			var CREDIT_FREEZE_PRICE = $(this).parent().parent().find("input[name='CREDIT_FREEZE_PRICE']").val();
			CREDIT_FREEZE_PRICE = Number(CREDIT_FREEZE_PRICE);
			if(isNaN(CREDIT_FREEZE_PRICE)){
				CREDIT_FREEZE_PRICE = 0;
			}
			
			total_sendNum = SENDED_NUM+Number(total_sendNum);
			total_sendVolume = Number(total_sendVolume)+SENDED_NUM*VOLUME;
			total_sendAmount = Number(total_sendAmount)+SENDED_NUM*DECT_PRICE;
			
			PLAN_NUM=isNaN(PLAN_NUM)?0:parseInt(PLAN_NUM,10);
			VOLUME=isNaN(VOLUME)?0:parseFloat(VOLUME);
			total_vol=parseFloat(total_vol+VOLUME*PLAN_NUM);
			total_num=parseFloat(total_num+PLAN_NUM);
			var amount=$(this).parent().parent().find("td[name='price']").html();
			if(""==amount||null==amount){
				amount=0;
			}
			amount=isNaN(amount)?0:parseFloat(amount);
			total_amount=parseFloat(total_amount+amount);
			total_CREDIT_FREEZE_PRICE = Number(total_CREDIT_FREEZE_PRICE) +PLAN_NUM*CREDIT_FREEZE_PRICE 
		})
		$("#allVolume").val(total_vol.toFixed(2));
		$("#allNum").val(parseInt(total_num,10));
		$("#allAmount").val(total_amount.toFixed(2));
		$("#sendNum").val(total_sendNum);
		$("#sendVolume").val(total_sendVolume.toFixed(2));
		$("#sendAmount").val(total_sendAmount.toFixed(2));
		$("#allFreeAmount").val(total_CREDIT_FREEZE_PRICE.toFixed(2));
	}
}
function checkClick(id){
	var checked=$("#"+id).prop("checked");
	if(checked==true){
		$("#"+id).attr('checked','checked');
	}else{
		$("#"+id).removeAttr('checked');
		
	}
}
function openAdd(){
	var checkBox = $("input[name='DELIVER_ORDER_DTL_ID']");
	var ids="";
	if(checkBox.length>0){
		checkBox.each(function(){
			var id=$(this).parent().parent().find("input[name='SALE_ORDER_DTL_ID']").val();
			ids = ids+"'"+id+"',";
		})
		ids = ids.substr(0,ids.length-1);
	}
	if(""==ids){
		ids = " and 1=1";
	}else{
		ids=" and SALE_ORDER_DTL_ID not in ("+ids+")"
	}
	var CHANNS_CHARG=$("#CHANNS_CHARG").val();
	var SHIP_POINT_ID=parent.topcontent.getSHIP_POINT_ID();
	var sql=" ORDER_CHANN_ID  in "+CHANNS_CHARG+ids+" and SHIP_POINT_ID='"+SHIP_POINT_ID+"'";
	$("#selectAdd").val(sql);
	selCommon('BS_121', true, 'SALE_ORDER_DTL_ID', 'SALE_ORDER_DTL_ID','forms[0]','SALE_ORDER_DTL_ID','SALE_ORDER_DTL_ID','selectAddParams');
	addHtml();
}
function addHtml(){
	var SALE_ORDER_DTL_ID=$("#SALE_ORDER_DTL_ID").val();
	$.ajax({
	 	url: "deliveryhd.shtml?action=getChld&SALE_ORDER_DTL_IDS="+utf8(SALE_ORDER_DTL_ID),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var data = jsonResult.data;
				 $.each(data,function(i,rst){
					 if(""!=$("#tdEmpty").html()&&null!=$("#tdEmpty").html()){
						 $("#trEmpty").remove();
					 }
					 var color=rst.PRD_COLOR;
					 if(null==color){
						 color="";
					 }
					 var U9_SALE_ORDER_NO=rst.U9_SALE_ORDER_NO;
					 if(null==U9_SALE_ORDER_NO){
						 U9_SALE_ORDER_NO="";
					 }
					 var PRD_SPEC=rst.PRD_SPEC;
					 if(null==PRD_SPEC){
						 PRD_SPEC="";
					 }
					 var rownum=$("#ordertb tr").length;
					 var r = rownum% 2;
					 var html = "<tr  class='list_row"+r+"' onmouseover='mover(this)' onmouseout='mout(this)' >" +
					 				"<td nowrap='nowrap' align='center' >" +
					 					"<input type='checkbox'  checked='checked'  style='valign:middle' name='DELIVER_ORDER_DTL_ID' id='eid"+rownum+"' json='DELIVER_ORDER_DTL_ID' value='' onclick='checkClick(eid"+rownum+")'/>" +
					 				"</td>" +
					 				"<td nowrap='nowrap' align='center' >"+rst.SALE_ORDER_NO+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.ORDER_CHANN_NO+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.ORDER_CHANN_NAME+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.ADVC_SEND_DATE+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.RECV_ADDR_NO+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.RECV_ADDR+"&nbsp;</td>"  +   
					 				"<td nowrap='nowrap' align='center'>"+rst.PRD_NO+"&nbsp;</td>"  +  
					 				"<td nowrap='nowrap' align='left'>"+rst.PRD_NAME+"&nbsp;</td>"  +  
					 				"<td nowrap='nowrap' align='left'>"+rst.PAR_PRD_NAME+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+PRD_SPEC+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+color+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.BRAND+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center'>"+rst.STD_UNIT+"&nbsp;</td>" +
					 				"<td nowrap='nowrap' align='center' >"+
                     				"<span id='SPECIAL_FLAG"+rownum+"' >";
				 						if(""==rst.SPCL_TECH_FLAG||null==rst.SPCL_TECH_FLAG||0==rst.SPCL_TECH_FLAG){
				 							html=html+"无";
				 						}else{
				 							if(""!=rst.SPCL_SPEC_REMARK||null!=rst.SPCL_SPEC_REMARK){
				 								var SPCL_TECH_ID=rst.SPCL_TECH_ID;
				 								var PRICE=rst.PRICE;
				 								var method="selectTechPage('"+SPCL_TECH_ID+"','"+PRICE+"');";
				 								html=html+"<label class='hideNoticeText' title='"+rst.SPCL_SPEC_REMARK+"'>"+rst.SPCL_SPEC_REMARK+"</label>"+
				 									'<input type="button" class="btn"  value="查看" onclick='+method+' />';		
				 							}
				 						}
	                       			html=html+"</span></td>"+
	                     			"<td nowrap='nowrap' align='right'>" +
	                     				"<input type='text'  size='5' onkeyup='countAmount("+rownum+")' label='计划发运数量' onclick='clickCheckedBox("+rownum+")' value="+rst.NO_SEND_NUM+"  name='PLAN_NUM' json='PLAN_NUM' id='PLAN_NUM"+rownum+"' />&nbsp;"+
				                     	"<input type='hidden' name='VOLUME' value='"+rst.VOLUME+"'/>"+
				                     	"<input type='hidden' name='SALE_ORDER_DTL_ID' value='"+rst.SALE_ORDER_DTL_ID+"'/>"+
				                     	"<input type='hidden' name='SALE_ORDER_DTL_ID' json='SALE_ORDER_DTL_ID' value='"+rst.SALE_ORDER_DTL_ID+"'/>"+
	                     			"</td>" +
	                     			"<td nowrap='nowrap' align='right' >"+rst.ORDER_NUM+"</td>"+
	                     			"<td nowrap='nowrap' align='right' >"+rst.SENDED_NUM+"</td>"+
	                     			"<td nowrap='nowrap' align='right' >"+rst.NO_SEND_NUM+"</td>"+
					 				"<td nowrap='nowrap' align='right' id='DECT_PRICE"+rownum+"' name='DECT_PRICE'>"+rst.DECT_PRICE+"</td>";
	                       			var DECT_PRICE=rst.DECT_PRICE;
	                       			if(""==DECT_PRICE||null==DECT_PRICE){
	                       				DECT_PRICE=0;
	                       			}
	                       			var PLAN_NUM=rst.PLAN_NUM;
	                       			if(""==PLAN_NUM||null==PLAN_NUM){
	                       				PLAN_NUM=0;
	                       			}
	                       			html=html+"<td  nowrap='nowrap' align='right' id='amount"+rownum+"' name='price'>"+parseFloat( DECT_PRICE*PLAN_NUM ).toFixed(2)+"</td>"+
	                       			"<td nowrap='nowrap' align='right' >"+U9_SALE_ORDER_NO+"</td>"+
					 				"<td nowrap='nowrap' align='center' >";
					 				var state="";
					 				if(3==rst.IS_SEND_FIN){
					 					state="关闭";
					 				}else if(1==rst.IS_SEND_FIN){
					 					state="已处理";
					 				}else{
					 					state="未处理";
					 				}
					 				html=html+state+"</td>"+
					 				"<td nowrap='nowrap' align='left' ><input type='text' name='REMARK' json='REMARK' onclick='clickCheckedBox("+rownum+");' maxlength='200' value=''/></td>"+
					 				"</tr>"
					 				
             		$("#ordertb").append(html);
					 InitFormValidator("ordertbForm");
				 });
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	countTotal();
	$("#SALE_ORDER_DTL_ID").val("");
}
function clickCheckedBox(id){
	$('#eid'+id).attr('checked','checked');
}
//行关闭
function multiRecClose(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = $("#state"+selRowId).val();
	
	 var checkBox = $("#ordertb tr:gt(0) input:checked");
	 var ids = "";
	 var flag = true;
	 if(checkBox.length>0){
		//获取所有选中的记录
		checkBox.each(function(){
			var IS_SEND_FIN = $(this).parent().parent().find("input[name='IS_SEND_FIN']").val();
			if(3 == IS_SEND_FIN || "3" == IS_SEND_FIN){
				 flag = false;
				 return false;
			}
		 
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
	 }
	if(!flag){
		parent.showErrorMsg("存在已关闭的行记录，请取消");
		return false;
	}
	$.ajax({
		url: "deliveryhd.shtml?action=closeChilds",
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
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//是否取消过标记 标颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var STATE = $(this).find("input[name='IS_SEND_FIN']").val();
		if("3" == STATE){
		    $(this).find("td").css("background-color", "#FFFF66");//#F3F3F3花号还原
		    $(this).find("input[type='text']").attr("disabled","disabled").attr("class","readonly");//#F3F3F3花号还原
		}
	});
	
}


/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
	headColumnSort("jsontb","listForm");
	InitFormValidator("mainForm");
	InitFormValidator("pageForm");
	InitFormValidator("queryForm");
	//点击关闭按钮关闭页面
	$("#q_close").click(function () {
		window.close();
	});
	$("#q_search").click(function () {
		var SHIP_POINT_NAME=$("#SHIP_POINT_NAME").val();
		if(""==SHIP_POINT_NAME||null==SHIP_POINT_NAME){
			showErrorMsg("请选择发货基地");
			return ;
		}
		$("#queryForm").submit();
	});
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
		countTotal();
	});
	$("#save").click(function(){
		if(!check()){
			return ;
		}
		//childSave();
		addRow();
		
	})
	countTotal();
	
	changeColor();
});


//已发货标记 标颜色
function changeColor(){
	var ordertb = $("#jsontb tr:gt(0)");
	ordertb.each(function(){
		var PLAN_NUM = $(this).find("input[name='PLAN_NUM_LAST']").val();
		PLAN_NUM = Number(PLAN_NUM);
		if(isNaN(PLAN_NUM)){
			PLAN_NUM = 0;
		}
		if(PLAN_NUM>0){
		    $(this).find("td").css("background-color", "#9CEEB0");//#D5F2F3花号还原
		}
	});
	
}


function queryList(){
	var SHIP_POINT_NAME=$("#SHIP_POINT_NAME").val();
	if(""!=SHIP_POINT_NAME&&null!=SHIP_POINT_NAME){
		$("#queryForm").submit();
	}
}
//子表保存
function childSave(){
	var mainData=siglePackJsonData("mainTab");//主表
	var jsonData = multiPackJsonData("jsontb");//明细
	var gchldData=multiPackJsonData("ordertb");//冻结明细
	var SHIP_POINT_NAME=$("#SHIP_POINT_NAME").val();
	var SHIP_POINT_ID=$("#SHIP_POINT_ID").val();
	$.ajax({
		url: "deliveryhd.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"mainData":mainData,"SHIP_POINT_NAME":SHIP_POINT_NAME,"SHIP_POINT_ID":SHIP_POINT_ID,"gchldData":gchldData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				//冻结页面先关闭
				cloce();
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.$("#YT_MSG_BTN_OK").click(function(){
					 window.close();
				     window.opener.parent.topcontent.listRef();
				});
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
function url(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
function hide(){
	if($("#showOrHide").val()=="↑"){
		parent.$("#tabView").hide();
		$("#showOrHide").val("↓");
		parent.$("#btnTab").css({height:'80%'});
	}else{
		parent.$("#tabView").show();
		$("#showOrHide").val("↑");
		parent.$("#btnTab").css({height:'55%'});
	}
}
//验证主表信息和子表信息是否填写完整
function check(){
	var checkBox = $("input[name='SALE_ORDER_DTL_ID']:checked");
	if(checkBox.length<1){
		showErrorMsg("请选择明细记录");
		return;
	}
	var flag=true;
	checkBox.each(function(){
		var PLAN_NUM=$(this).parent().parent().find("input[name='PLAN_NUM']").val();
		if(""==PLAN_NUM||null==PLAN_NUM){
			showErrorMsg("请输入本次发货数量");
			flag=false;
			return false;
		}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re2.test(PLAN_NUM)){
            parent.showErrorMsg("本次发货数量最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
	})
	if(!flag){
		return false;
	}
	var DELIVER_TYPE=$("#DELIVER_TYPE").val();
	if(""==DELIVER_TYPE||null==DELIVER_TYPE){
		parent.showErrorMsg("请选择发运方式！");
		return false;
	}
	var PRVD_NAME=$("#PRVD_NAME").val();
	if(""==PRVD_NAME||null==PRVD_NAME){
		parent.showErrorMsg("请选择物流公司！");
		return false;
	}
//	var TRUCK_TYPE=$("#TRUCK_TYPE").val();
//	if(""==TRUCK_TYPE||null==TRUCK_TYPE){
//		parent.showErrorMsg("请选择车型！");
//		return false;
//	}
	var APPCH_TIME=$("#APPCH_TIME").val();
	if(""==APPCH_TIME||null==APPCH_TIME){
		parent.showErrorMsg("请选择进场时间！");
		return false;
	}
	var SHIP_POINT_NAME=$("#SHIP_POINT_NAME").val();
	if(""==SHIP_POINT_NAME||null==SHIP_POINT_NAME){
		parent.showErrorMsg("请选择查询条件中的发货基地！");
		return false;
	}
	return true;
}
function checkClick(id){
	var checked=$("#"+id).prop("checked");
	if(checked==true){
		$("#"+id).attr('checked','checked');
	}else{
		$("#"+id).removeAttr('checked');
		
	}
	countTotal();
}
function countTotal(){
	var checkBox = $("input[name='SALE_ORDER_DTL_ID']:checked");
	var total_vol=0;//总体积
	var total_num=0;//总数量
	var total_amount=0;//总金额
	var total_freAmount=0;//总冻结金额
	var ref_freeamount=0;//参考冻结信用
	if(checkBox.length>0){
		checkBox.each(function(){
			var VOLUME=$(this).parent().parent().find("input[name='VOLUME']").val();
			var PLAN_NUM=$(this).parent().parent().find("input[name='PLAN_NUM']").val();
			var CREDIT_FREEZE_PRICE=$(this).parent().parent().find("input[name='CREDIT_FREEZE_PRICE']").val();
			if(""==CREDIT_FREEZE_PRICE||null==CREDIT_FREEZE_PRICE){
				CREDIT_FREEZE_PRICE=0;
			}
			if(""==VOLUME||null==VOLUME){
				VOLUME=0;
			}
			if(""==PLAN_NUM||null==PLAN_NUM){
				PLAN_NUM=0;
			}
			VOLUME=isNaN(VOLUME)?0:parseFloat(VOLUME);
			CREDIT_FREEZE_PRICE=isNaN(CREDIT_FREEZE_PRICE)?0:parseFloat(CREDIT_FREEZE_PRICE);
			PLAN_NUM=isNaN(PLAN_NUM)?0:parseFloat(PLAN_NUM);
			total_vol=parseFloat(total_vol+VOLUME*PLAN_NUM);
			PLAN_NUM=isNaN(PLAN_NUM)?0:parseInt(PLAN_NUM,10);
			total_num=parseFloat(total_num+PLAN_NUM);
			var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();
			if(""==DECT_PRICE||null==DECT_PRICE){
				DECT_PRICE=0;
			}
			DECT_PRICE=isNaN(DECT_PRICE)?0:parseFloat(DECT_PRICE);
			var freeAmount=(PLAN_NUM*CREDIT_FREEZE_PRICE).toFixed(2);
			var amount=DECT_PRICE.toFixed(2)*PLAN_NUM;
			total_amount=parseFloat(total_amount)+amount;
			total_freAmount=parseFloat(total_freAmount)+parseFloat(freeAmount);
			// 赠品不加参考冻结信用  edit by jack start
			var IS_FREE_FLAG=$(this).parent().parent().find("input[name='IS_FREE_FLAG']").val();
			if(IS_FREE_FLAG!='1'){
				ref_freeamount=parseFloat(ref_freeamount)+amount;
			}
			// 赠品不加参考冻结信用  edit by jack end 
		})
		$("#total_vol").val(total_vol.toFixed(2));
		$("#total_num").val(parseInt(total_num,10));
		$("#total_amount").val(total_amount.toFixed(2));
		$("#total_freeAmount").val(total_freAmount.toFixed(2));
		$("#freeAmount").val((ref_freeamount-total_freAmount).toFixed(2));
	}
}
function addRow(){
	var checkBox = $("input[name='SALE_ORDER_DTL_ID']:checked");
	var resultId=[];
	var resultNo=[];
	var resultNAME=[];
	checkBox.each(function(){
		var ORDER_CHANN_ID=$(this).parent().parent().find("input[name='ORDER_CHANN_ID']").val();
		var flag=true;
		for(var i=0;i<resultId.length;i++){
				flag=true;
			if(ORDER_CHANN_ID==resultId[i]){
				flag=false;
				return ;
			}
		}
		if(flag){
			var ORDER_CHANN_NO=$(this).parent().parent().find("input[name='ORDER_CHANN_NO']").val();
			var ORDER_CHANN_NAME=$(this).parent().parent().find("input[name='ORDER_CHANN_NAME']").val();
			resultId.push(ORDER_CHANN_ID);
			resultNo.push(ORDER_CHANN_NO);
			resultNAME.push(ORDER_CHANN_NAME);
		}
	})
	for(var i=0;i<resultId.length;i++){
		//样式行
		var rownum = $("#ordertb tr").length;
		var classrow = rownum% 2;
		rownum=_row_num;_row_num++;
		$("#ordertb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
		$("#ordertb tr:last-child")
			    .append("<td nowrap align='center' style='display: none;'><input type='checkbox' checked='checked'  json='ORDER_CHANN_ID' id='ORDER_CHANN_ID"+rownum+"' name='ORDER_CHANN_IDFreeze' value='"+resultId[i]+"'/></td>")
	            .append('<td nowrap align="center">'+resultNo[i]+'<input  json="ORDER_CHANN_NO" id="ORDER_CHANN_NO'+rownum+'" name="ORDER_CHANN_NO"    type="hidden"   value="'+resultNo[i]+'" />&nbsp;</td>')
	            .append('<td nowrap align="center">'+resultNAME[i]+'<input  json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME'+rownum+'" name="ORDER_CHANN_NAME"    type="hidden"   value="'+resultNAME[i]+'" />&nbsp;</td>')
	            .append('<td nowrap align="center"><input  json="FREEZE_AMOUNT" id="FREEZE_AMOUNT'+rownum+'" name="FREEZE_AMOUNT"   label="冻结金额"  type="text"  />&nbsp;</td>')
	           ;
		}
	$("#freezeDiv").show();
}
function cloce(){
	$("#ordertb tr:gt(0)").remove();
	$("#freezeDiv").hide();
}
function toVerify(){
	var checkBox = $("input[name='ORDER_CHANN_IDFreeze']:checked");
	var flag=true;
	checkBox.each(function(){
		var FREEZE_AMOUNT=$(this).parent().parent().find("input[name='FREEZE_AMOUNT']").val();
		if(""==FREEZE_AMOUNT||null==FREEZE_AMOUNT){
			alert("请输入冻结金额");
			flag=false;
			return false;
		}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re2.test(FREEZE_AMOUNT)){
            alert("冻结金额最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
	})
	if(flag){
		childSave();
	}
}
/**
 *@module 系统管理
 *@fuc 单位维护一览js
 *@version 1.0
 *@author 王栋斌
 */

$(function() {
	//主从及主从从列表页面通用加载方法
	listPageInit("paramset.shtml?action=toList");
//	InitFormValidator(0);
	 
	
	$("#save").click(function(){
	    toEditFreezeDays();
	 });
	
	$("#add").click(function(){
		 addRow();
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
		if("" == ids){
			checkBox.parent().parent().remove();
		}else{
			parent.showConfirm("您确认要删除吗","topcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	
	$("#baocun").click(function(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		saveChild();
		
	});
	var IS_RETURN_SALE_AUD_FLAG=$("#IS_RETURN_SALE_AUD_FLAG").val();
	if("1"==IS_RETURN_SALE_AUD_FLAG){
		$("#IS_RETURN_SALE_AUD_FLAG_CHECK").attr("checked","checked");
	}
})


function toEditFreezeDays(){
	var MAX_FREEZE_DAYS = $("#MAX_FREEZE_DAYS");
	var INIT_YEAR=$("#INIT_YEAR").val();
	var INIT_MONTH=$("#INIT_MONTH").val();
	var COST_TYPE=$("#COST_TYPE").val();
	var TAX_RATE=$("#TAX_RATE").val();
	var CHANN_SALE_RATE = $("#CHANN_SALE_RATE").val();
	var ADVC_SCOPE_DAYS=$("#ADVC_SCOPE_DAYS").val();
	var re1 = new RegExp(/^\d{1,2}(\.\d{1,2})?$/);
    if(!re1.test(TAX_RATE)&&TAX_RATE!=null&&TAX_RATE!=""){
        parent.showErrorMsg("税率最多可输入2位正整数和2位小数");
		return false;
    }
    var ex = /^\d+$/;
	 if (""!=ADVC_SCOPE_DAYS&&null!=ADVC_SCOPE_DAYS&&"undefined"!=ADVC_SCOPE_DAYS&&!ex.test(ADVC_SCOPE_DAYS)) {
	    parent.showErrorMsg("转订货范围天数必须为正整数");
		return false;
	} 
    if(COST_TYPE.length>15){
    	parent.showErrorMsg("成本计算方式不能超过15个汉字");
		return false;
    }
    // 校验整型内容
	if (!CheckIntegerInput("#MAX_FREEZE_DAYS")) {
		return false;
	}
	
	if (!CheckFloatInput($("#CHANN_SALE_RATE"))) {
		return false;
	}
     var MAX_FREEZE_DAYS = $("#MAX_FREEZE_DAYS").val();
     var IS_RETURN_SALE_AUD_FLAG=$("#IS_RETURN_SALE_AUD_FLAG").val();
     var IS_SPEC_TECH_ENABLE=$("input[name='specEnable']:checked").val();
     var url ="paramset.shtml?action=toEditFreezeDays";
     $.ajax({
		 url: url,
		 type:"POST",
		 dataType:"text",
		 data:{
    	 	"MAX_FREEZE_DAYS":MAX_FREEZE_DAYS,"INIT_YEAR":INIT_YEAR,"INIT_MONTH":INIT_MONTH,"COST_TYPE":COST_TYPE,
    	 	"TAX_RATE":TAX_RATE,"IS_RETURN_SALE_AUD_FLAG":IS_RETURN_SALE_AUD_FLAG,"CHANN_SALE_RATE":CHANN_SALE_RATE,
    	 	"ADVC_SCOPE_DAYS":ADVC_SCOPE_DAYS,"IS_SPEC_TECH_ENABLE":IS_SPEC_TECH_ENABLE
    	 },
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		 }
   });
 	    
}


function saveChild(){
	var CHANN_ID = $("#CHANN_ID").val();
	var checkBox = $("#ordertb tr:gt(0) input[name='DATA_CONF_ID']:checked");
	var flag = true;
	var DATA_TYPE_OLD = "";
	var DATA_NAME_OLD = "";
	checkBox.each(function(){
		 var DATA_TYPE = $(this).parent().parent().find("input[json='DATA_TYPE']").val();
		 DATA_TYPE = $.trim(DATA_TYPE);
		 if(null == DATA_TYPE || "" == DATA_TYPE){
			 parent.showErrorMsg("请输入'参数类型"+"'");
			 flag = false;
			 return flag;
		 }
		 var DATA_NAME = $(this).parent().parent().find("input[json='DATA_NAME']").val();
		 DATA_NAME = $.trim(DATA_NAME);
		 if(null == DATA_NAME || "" == DATA_NAME){
			 parent.showErrorMsg("请输入'参数名称"+"'");
			 flag = false;
			 return flag;
		 }
		 
	});
	  
	if(!flag){
		return flag;
	}
	
	
	for(var i=0;i<checkBox.length;i++){
		var first = checkBox[i];
		var DATA_TYPE = $(first).parent().parent().find("input[json='DATA_TYPE']").val();
		var DATA_NAME = $(first).parent().parent().find("input[json='DATA_NAME']").val();
		for(var j=checkBox.length-1;j>i;j--){
			var second = checkBox[j];
			var DATA_TYPE_ = $(second).parent().parent().find("input[json='DATA_TYPE']").val();
			var DATA_NAME_ = $(second).parent().parent().find("input[json='DATA_NAME']").val();
			if(DATA_TYPE == DATA_TYPE_){
				if(DATA_NAME == DATA_NAME_){
					parent.showErrorMsg("参数名称重复");
					 $(first).parent().parent().find("input[json='DATA_TYPE']").parent().css("background-color", "#F4E17E");
					 $(first).parent().parent().find("input[json='DATA_NAME']").parent().css("background-color", "#F4E17E");
					 $(second).parent().parent().find("input[json='DATA_TYPE']").parent().css("background-color", "#F4E17E");
					 $(second).parent().parent().find("input[json='DATA_NAME']").parent().css("background-color", "#F4E17E");
					 flag = false;
					 return flag;
				}
			}
		}
	}
     
	if(!flag){
		return flag;
	}
	
	var jsonData = multiPackJsonData("ordertb");
    $.ajax({
		 url: "paramset.shtml?action=edit",
		 type:"POST",
		 dataType:"text",
		 data:{"jsonData":jsonData,"CHANN_ID":CHANN_ID},
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		 }
 	 });
}


function changePage(){
    var DATA_TYPE = $("#DATA_TYPE").val();
    var orderForm = $("#orderForm").submit();
    
//	$.ajax({
//		url: "paramset.shtml?action=query",
//		type:"POST",
//		dataType:"text",
//		data:{"DATA_TYPE":DATA_TYPE},
//		complete: function(xhr){
//			eval("jsonResult = "+xhr.responseText);
//			if(jsonResult.success===true){
//				 var data = jsonResult.data;
//				 $("#ordertb tr:gt(1)").remove();
//				 $.each(data,function(i,item){
//					 var arrs = [
//			          item.DATA_CONF_ID, 
//		              item.DATA_TYPE, 
//		              item.DATA_NAME, 
//		              item.DATA_VAL 
//		            ];
//			       addRow(arrs);
//				 });
//			}else{
//				 
//			}
//		}
//	});
}

function addRow(arrs){
	var DATA_TYPE = $("#DATA_TYPE").val();
	if(null == arrs){
     arrs = ['',DATA_TYPE,'','',''];
		}
	//样式行
	var rownum = $("#ordertb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#ordertb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#ordertb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='DATA_CONF_ID' id='DATA_CONF_ID"+rownum+"' name='DATA_CONF_ID' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="center"><input  json="DATA_TYPE" id="DATA_TYPE'+rownum+'" name="DATA_TYPE"  autocheck="true" label="参数类型"  type="text"   inputtype="string" mustinput="true" readonly  value="'+arrs[1]+'" />&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="DATA_NAME" id="DATA_NAME'+rownum+'" name="DATA_NAME"    label="参数名称"  type="text"      onchange="forNextValue(this);"  onkeyup="forNextValue(this);"   value="'+arrs[2]+'" /><span class="validate">*</span>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="DATA_VAL" id="DATA_VAL'+rownum+'" name="DATA_VAL'+rownum+'"  autocheck="true" label="参数值"  type="text" READONLY  inputtype="string" readonly   value="'+arrs[3]+'" />&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="IS_DEAL_FLAG" id="IS_DEAL_FLAG'+rownum+'" name="IS_DEAL_FLAG"  autocheck="true" label="是否核销"  type="checkbox"       value="'+arrs[4]+'" />&nbsp;</td>')
           ;
    if(1==arrs[4]){
    	$("#IS_DEAL_FLAG"+rownum).prop("checked",true);
    }
	//form校验设置
	trCheckInit($("#ordertb tr:gt("+(rownum-1)+") input"));
	InitFormValidator("orderForm");
	$("#DATA_CONF_ID"+rownum).attr("checked","checked");
}


function forNextValue(obj){
	var DATA_VAL = $(obj).val();
	$(obj).parent().parent().find("input[json='DATA_VAL']").val(DATA_VAL);
}



//function addModiToEditFrameInit1(url){
//	 $("#add").click(function(){
//	     var freezeDays = $("#MAX_FREEZE_DAsaveOrUpdateYS").val();
//	     var CHANN_ID   = $("#CHANN_ID").val();
//	 	 window.parent.location=url+"&module="+getModule()+"&freezeDays="+freezeDays+"&CHANN_ID="+CHANN_ID; 
//	 });
//    }

//修改最大冻结天数
//function saveOrUpdate(){
//     addModiToEditFrameInit1("paramset.shtml?action=toEditFreezeDays");
//}

 

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input[name='DATA_CONF_ID']:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "paramset.shtml?action=delete",
		type:"POST",
		dataType:"text",
		data:{"DATA_CONF_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				//window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function checkFlag(){
	var checked = $("#IS_RETURN_SALE_AUD_FLAG_CHECK").prop("checked");
	if(checked==true){
		$("#IS_RETURN_SALE_AUD_FLAG_CHECK").attr("checked","checked");
		$("#IS_RETURN_SALE_AUD_FLAG").val("1");
	}else{
		$("#IS_RETURN_SALE_AUD_FLAG_CHECK").removeAttr("checked");
		$("#IS_RETURN_SALE_AUD_FLAG").val("0");
	}
}
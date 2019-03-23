/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 发运确认
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
//    init();
	 
	 
	$("#save").click(function(){
		var actionType = getActionType();
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中的明细校验
			if(!listCheckRealSendNum()){
				return;
			}
			 
			childSave();
		}else{
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			allSave();
		}
		 
	});
	
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	
	
});


function getActionType(){
	return parent.document.getElementById("actionType").value;
}
 
 
//返回
function gobacknew()
{
   newGoBack("deliverconfm.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}



//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	
	if(function_exists('formCheckedEx')){
		return formCheckedEx();
	}
	
	//明细校验
	if(!checkChild()){
		return;
	}
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData = multiRadioJsonData("jsontb",true);
     
	$.ajax({
		url: "deliverconfm.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"DELIVER_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","deliverconfm.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}



//子表保存
function childSave(){
	
	var selRowId = getSelRowId();
	var DELIVER_ORDER_NO = parent.topcontent.$("#DELIVER_ORDER_NO"+selRowId).val();
	var jsonData = multiPackJsonData();
   
	$.ajax({
		url: "deliverconfm.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"DELIVER_ORDER_ID":selRowId,"DELIVER_ORDER_NO":DELIVER_ORDER_NO},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_1");
				parent.topcontent.$("#queryForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}



// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              '',
              ''
              
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_TEXT;
	if(0 == arrs[9] || "0" == arrs[9] || "" == arrs[9] || null == arrs[9]){
		SPCL_TECH_TEXT='无';
	}else{
		var id = "'"+arrs[9]+"'";
		var funText = "selectTechPage('"+rownum+"')";
		SPCL_TECH_TEXT='<font style="color: red">有</font><input type="button" class="btn" value="查看" onclick="'+funText+'"/>';
	}
	
	var feibiao = "否";
	if(1 == arrs[10] || "1" == arrs[10]){
		feibiao = "是";
	}
	var address = arrs[11];
	if(address.length>11){
		address = address.substr(0,11);
	}
	var disabledText = "";
	if(arrs[13] == arrs[14]){
		disabledText = "disabled='disabled'";
	}
	
	var rowState = "已处理";
	var IS_SEND_FIN = arrs[17];
	if(null == IS_SEND_FIN || "" == IS_SEND_FIN || 0 == IS_SEND_FIN || "0" == IS_SEND_FIN){
		rowState = "未处理";
	}
	if(3 == IS_SEND_FIN || "3" == IS_SEND_FIN){
		rowState = "关闭";
		disabledText = "disabled='disabled'";
	}
	
	var actionType = getActionType();
	var disabledInput = "";
	if("list" != actionType){
		disabledInput = "disabled='disabled'";
    }else{
    	disabledText = "disabled='disabled'";
    }
	
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'>" +
		    "<input type='checkbox' style='valign:middle' json='DELIVER_ORDER_DTL_ID' name='DELIVER_ORDER_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>") 
		    .append('<td nowrap align="left" label="">'+arrs[1]+'&nbsp;</td>')
		    .append('<td nowrap align="left" label="订货方名称">'+arrs[2]+'&nbsp;</td>')
		    .append('<td nowrap align="center" label="货品编号">'+arrs[3]+'&nbsp;</td>')
            .append('<td nowrap align="left" label="货品名称" >'+arrs[4]+'&nbsp;</td>')
		    .append('<td nowrap align="center" label="规格型号" >'+arrs[5]+'&nbsp;</td>')
            .append('<td nowrap align="left"  label="花号">'+arrs[6]+'&nbsp;</td>')
            .append('<td nowrap align="center" label="品牌"  >'+arrs[7]+'&nbsp;</td>')
          //  .append('<td nowrap align="center" label="标准单位">'+arrs[8]+'&nbsp;</td>')
            .append('<td nowrap align="center"><input type="hidden" id="SPCL_TECH_ID'+rownum+'" value="'+arrs[9]+'" /><span id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_TEXT+'</span>&nbsp;</td>')
            .append('<td nowrap align="center"  label="是否非标">'+feibiao+'&nbsp;</td>')
            .append('<td nowrap align="left" label="收货地址"  title="'+arrs[11]+'">'+address+'&nbsp;</td>')
            .append('<td nowrap align="right" label="预排发运数量">'+arrs[12]+'&nbsp;</td>')
            .append('<td nowrap align="right" label="计划发运数量">'+arrs[13]+'&nbsp;</td>')
            .append('<td nowrap align="right" label="实际发运数量"><input type="text" name="REAL_SEND_NUM" id="REAL_SEND_NUM'+rownum+'" json="REAL_SEND_NUM" label="实际发运数量" size="5" maxlength="11"  textType="float" '+disabledInput+' autocheck="true" value="'+arrs[14]+'" onkeyup="changeDiffNum('+rownum+');"/>&nbsp;</td>')
            .append('<td nowrap align="right" label="差异数量" name="NO_SEND_NUM" id="NO_SEND_NUM_TD'+rownum+'">'+arrs[15]+'&nbsp;</td>')
            .append('<td nowrap align="center" label="剩余货品处理方式"><select id="NO_SEND_DEAL_TYPE'+rownum+'" json="NO_SEND_DEAL_TYPE" value="'+arrs[16]+'" '+disabledText+' onmousewheel="return false"></select></td>')
            .append('<td nowrap align="right" label="状态" name="" >'+rowState+'&nbsp;'+
            '<input type="hidden" label="预排发运数量" id="ADVC_PLAN_NUM'+rownum+'" json="ADVC_PLAN_NUM"  value="'+arrs[12]+'" />'+
            '<input type="hidden" label="计划发运数量" id="PLAN_NUM'+rownum+'"      json="PLAN_NUM"       value="'+arrs[13]+'" />'+
           // '<input type="hidden" label="实际发运数量" id="REAL_SEND_NUM'+rownum+'" json="REAL_SEND_NUM"  value="'+arrs[14]+'" />'+
            '<input type="hidden" label="未发数量"     id="NO_SEND_NUM'+rownum+'"   json="NO_SEND_NUM"       value="'+arrs[15]+'" />'+
            '<input type="hidden" label="订货订单编号"     id="SALE_ORDER_NO'+rownum+'"   json="SALE_ORDER_NO"       value="'+arrs[1]+'" />'+
            '<input type="hidden" label="货品编号"     id="PRD_NO'+rownum+'"   json="PRD_NO"       value="'+ arrs[3] +'" />' +
            '<input type="hidden" label="行状态"     id="IS_SEND_FIN'+rownum+'"   json="IS_SEND_FIN"       value="'+ arrs[17] +'" />' +
            '<input type="hidden" label="历史实发数量"     id="REAL_SEND_NUM_OLD'+rownum+'"   json="REAL_SEND_NUM_OLD"       value="'+ arrs[14] +'" />' +
            '&nbsp;</td>') 
          ;
	
	
	SelDictShow("NO_SEND_DEAL_TYPE"+rownum,"BS_56",arrs[16],"");	  
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
	 
	//form校验设置
	InitFormValidator(0);
	
}
 

function changeDiffNum(rownum){
	
	var obj = $("#REAL_SEND_NUM"+rownum);
	obj.parent().parent().find("input[type='checkbox']").attr("checked","checked");
	var REAL_SEND_NUM = obj.val();
	var PLAN_NUM = $("#PLAN_NUM"+rownum).val();
	var textType = obj.attr("textType");
//	if("float" == textType){
//		 if(!CheckFloatInput(obj)){
//			return false;
//		}
//	}
	REAL_SEND_NUM = Number(REAL_SEND_NUM);
	if(isNaN(REAL_SEND_NUM)){
		REAL_SEND_NUM = 0;
	}
	var NO_SEND_NUM = Number(PLAN_NUM)-REAL_SEND_NUM;
	
	$("#NO_SEND_NUM_TD"+rownum).text(NO_SEND_NUM);
	$("#NO_SEND_NUM"+rownum).val(NO_SEND_NUM);
	
}

//check子表的实发数量
function listCheckRealSendNum(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkBox.each(function(){
		var obj = $(this).parent().parent().find("input[json='REAL_SEND_NUM']")
		if(!CheckFloatInput(obj)){
			flag = false;
			return false;
		}
		var REAL_SEND_NUM = obj.val();
		var PLAN_NUM = $(this).parent().parent().find("input[json='PLAN_NUM']").val();
		 
		if(Number(PLAN_NUM) < Number(REAL_SEND_NUM)){
			parent.showErrorMsg("'实际发运数量'不能大于'计划发运数量'");
			flag = false;
			return false;
		}
		
	});
	
	return flag;
}




//check 剩余货品处理方式
function checkChild(){
	var trs = $("#jsontb tr:gt(0)");
	var flag = true;
	trs.each(function(){
		var NO_SEND_DEAL_TYPE = $(this).find("select[json='NO_SEND_DEAL_TYPE']").val();
		var NO_SEND_NUM = $(this).find("td[name='NO_SEND_NUM']").text();
		var IS_SEND_FIN = $(this).find("input[json='IS_SEND_FIN']").val();
		IS_SEND_FIN = parseInt(IS_SEND_FIN);
		if(isNaN(IS_SEND_FIN)){
			IS_SEND_FIN = 0;
		}
		NO_SEND_NUM = $.trim(NO_SEND_NUM);
		if(Number(NO_SEND_NUM)>0 && IS_SEND_FIN != 3 ){
			if(null == NO_SEND_DEAL_TYPE || "" == NO_SEND_DEAL_TYPE){
				parent.showErrorMsg("请选择'剩余货品处理方式'");
				flag = false;
				return false;
			}
		}
	});
	
	return flag;
	
}



/**
 * 拼装 子表的json 
 * @param {Object} tableid 表ID
 * @param {Object} isAll true->无视是否选择 拼接所有
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function multiRadioJsonData(tableid,isAll){
	if(null == tableid){
		tableid = "jsontb";
	}
	if(null == isAll){
		isAll = false;
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var selectObj = $(this).find("select[json='NO_SEND_DEAL_TYPE']");
		var dis = selectObj.attr("disabled");
	    if("disabled" == dis){
	    	return ;
	    }
			    
		var isEdit = false;
		if(isAll){
			isEdit = true;
		} 
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
//			var inputs = $(this).find(":input");
//			var tds = $(this).find("td");
//			tds.each(function(){
				var inputs = $(this).find(":input");
				if(inputs.length>0){
					inputs.each(function(){
						if(null != $(this).attr("json")){
							var key;
							var value;
							var type = $(this).attr("type");
							if(!isFirst && "checkbox" == type){
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
								value = $(this).attr("value");
								
								isFirst = false;
							}
							var inputtype = $(this).attr("inputtype");
							jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
						}
					});
//				}else{
//					if(null != $(this).attr("json")){
//						var k = $(this).attr("json");
//					    var text = $.trim($(this).text());
//					    jsonData = jsonData+ "'" + k + "':'" + text +"',";
//				    }
				}
				
				
//			});
			
		
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}


//查看特殊工艺
function selectTechPage(rownum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
  
}


 

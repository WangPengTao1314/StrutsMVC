/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
		addRow();
	});
	
	$("body").dblclick(function(){
	  //  addRow();
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
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
			parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	$("#save").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中的明细校验
			if(!formMutiTrChecked()){
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


//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	if(!checkOrderNum()){
		return;
	}
	//获取主表json数据
	var  parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
   var selRowId = getSelRowId();
	$.ajax({
		url: "saledateup.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"SALE_DATE_UP_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","saledateup.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
 
 
 
 

 //校验 订货数量
 function checkOrderNum(){
    var checkBox = $("#jsontb tr:gt(0) input:checked");
    var flag = true;
	checkBox.each(function(){
//		 var TOTAL_NUM = $(this).parent().parent().find("input[json='TOTAL_NUM']");
//		 if (!CheckFloatInput(TOTAL_NUM)) {
//			return false;
//		 }
//		 var num = TOTAL_NUM.val();
//		 var label = TOTAL_NUM.attr("label");
//		 if(null == num || "" == num){
//			parent.showErrorMsg("请输入'"+label+"'");
//			input.focus();
//			flag =  false;
//			return false;
//		}
		 
		 var TOTAL_AMOUNT = $(this).parent().parent().find("input[json='TOTAL_AMOUNT']");
//		 if (!CheckFloatInput(TOTAL_AMOUNT)) {
//			return false;
//		 }
		 var amount = TOTAL_AMOUNT.val();
		 var label_amout = TOTAL_AMOUNT.attr("label");
		 if(null == amount || "" == amount){
			parent.showErrorMsg("请输入'"+label_amout+"'");
			input.focus();
			flag =  false;
			return false;
		}
		var PRD_TYPE=$(this).parent().parent().find("select[json='PRD_TYPE']").val();
		if(""==PRD_TYPE||null==PRD_TYPE){
			parent.showErrorMsg("请选择货品类别");
			input.focus();
			flag =  false;
			return false;
		}
		 
	});
	
	return flag;
 }
 
 
 

 
 
//子表保存
function childSave(){
	if(!checkOrderNum()){
		return;
	}
	
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "saledateup.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"SALE_DATE_UP_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "goodsorderhd.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew(){
   newGoBack("saledateup.shtml?action=toFrame");
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
 
 
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
              '',
              '',
              ''
	        ];
		}
	 
	var actionType=getActionType();//edit页面
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='valign:middle' json='SALE_DATE_UP_DTL_ID' name='SALE_DATE_UP_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input type="hidden" id="PRD_ID'+rownum+'"/>' +
            '<select  json="PRD_TYPE" id="PRD_TYPE'+rownum+'" style="width:153px"  name="PRD_TYPE"  autocheck="true" label="货品类别" inputtype="string"  mustinput="true"     />&nbsp;</td>')
//            '<img id="img" align="absmiddl" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrdType('+rownum+')"/></td>')
//            .append('<td nowrap align="left"><input  json="TOTAL_NUM"    id="TOTAL_NUM'+rownum+'"     name="TOTAL_NUM'+rownum+'"     autocheck="true" label="销售数量"  size="15"  type="text"   inputtype="float"    valueType="8,2"    mustinput="true"     maxlength="11"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="TOTAL_AMOUNT" id="TOTAL_AMOUNT'+rownum+'"  name="TOTAL_AMOUNT'+rownum+'"  autocheck="true" label="销售金额"  style="width:153px"  type="text"   inputtype="float"    valueType="10,2"   mustinput="true"     maxlength="13"   value="'+arrs[3]+'"/>&nbsp;</td>')
            ;
 	SelDictShow("PRD_TYPE"+rownum, "BS_171", arrs[1], "");
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}

 
function selcPrdType(rownum){
    selCommon("BS_95", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_TYPE"+rownum, "PRD_NAME", "selectParams");
}
 
 
    
/**
 * 浮点数的 乘法
 * @param {Object} arg1
 * @param {Object} arg2
 * @return {TypeName} 
 */
function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}   
 
 
//验证数量为数字
function checkNum(){
	alert(1);
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkBox.each(function(){
		var obj = $(this).parent().parent().find("input[name='ORDER_NUM']");
		var ORDER_NUM = obj.val();//订货数量
		var textType = obj.attr("textType");
		 if("int" == textType){
			 if(!CheckIntegerInput(obj)){
				 flag = false;
				 return false;
			}
		}
		 
		if("0"==ORDER_NUM){
			parent.showErrorMsg("订货数量不能为0");
            flag = false;
			return false;
		}
	 
	});
	return flag;
} 
/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10  
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
		//设置主表的 定金总额
	    paddingADVC_AMOUNT();
	    
	    
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
		btuMxRest();
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		btuMxRest();
		return;
	}
 
	var selRowId = getSelRowId();
	//获取主表json数据
	var parentData = parent.topcontent.siglePackJsonData();
	 
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	 
	$.ajax({
		url: "erpadvcorderstatement.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_STATEMENTS_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","erpadvcorderstatement.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
 
 
 
 function btuMxRest(){
	 btnReset(["delete","save","add"]);
 }
 
 
 
//子表保存
function childSave(){
	//明细校验
	if(!checkchild()){
		btuMxRest();
		return;
	}
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	
	$.ajax({
		url: "erpadvcorderstatement.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_STATEMENTS_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_1");
				parent.topcontent.$("#pageForm").submit();
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
		url: "erpadvcorderstatement.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_STATEMENTS_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				//设置主表的 定金总额
	           paddingADVC_AMOUNT();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew(){
	var actionType = getActionType();
	if("list" == actionType){
		 parent.window.gotoBottomPage("label_1");
	}else{
		newGoBack("erpadvcorderstatement.shtml?action=toFrame");
	}
	
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='ADVC_STATEMENTS_ORDER_DTL_ID' id='ADVC_STATEMENTS_ORDER_DTL_ID"+rownum+"' name='ADVC_STATEMENTS_ORDER_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="ADVC_ORDER_NO" id="ADVC_ORDER_NO'+rownum+'" name="ADVC_ORDER_NO"  inputtype="string"  autocheck="true" label="预订单编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[2]+'"/>&nbsp;' +
            "<img name='imgTab' align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcAdvc("+rownum+")'/></td>")
            .append('<input  json="ADVC_ORDER_ID" id="ADVC_ORDER_ID'+rownum+'" name="ADVC_ORDER_ID'+rownum+'"   type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[1]+'"/>')
            .append('<td nowrap align="left"><input  json="BILL_TYPE" id="BILL_TYPE'+rownum+'" name="BILL_TYPE'+rownum+'" size="8" autocheck="true" label="单据类型"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SALE_DATE" id="SALE_DATE'+rownum+'" name="SALE_DATE" mustinput="true"  autocheck="true" label="销售日期" inputtype="string" size="10"   READONLY  value="'+arrs[4]+'"></input>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CUST_NAME" id="CUST_NAME'+rownum+'" name="CUST_NAME'+rownum+'"  autocheck="true" label="客户名称"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="TEL" id="TEL'+rownum+'" name="TEL'+rownum+'"  autocheck="true" label="电话"  type="text" size="12"  readonly inputtype="string"    maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ADVC_AMOUNT" id="ADVC_AMOUNT'+rownum+'" name="ADVC_AMOUNT"  autocheck="true" label="订金金额"  type="text" size="10"  inputtype="string"   readonly       maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CUR_STATEMENTS_AMOUNT" id="CUR_STATEMENTS_AMOUNT'+rownum+'" name="CUR_STATEMENTS_AMOUNT'+rownum+'"  autocheck="true" label="本次结算总额"  type="text" size="10"  inputtype="string"   readonly       maxlength="50"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT"     id="PAYABLE_AMOUNT'+rownum+'"  name="PAYABLE_AMOUNT" autocheck="true" inputtype="" mustinput="true" texttype="float" valueType="8,2" label="应收总额" READONLY size="5" type="text" onkeyup="countPRICE('+rownum+')"    value="'+arrs[9]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="MARKETING_CARD_NO" id="MARKETING_CARD_NO'+rownum+'" name="MARKETING_CARD_NO"  inputtype="string"  autocheck="true" label="卡券编号"    type="text"  mustinput="true"   READONLY  value="'+arrs[11]+'"/>&nbsp;' +
            "<img name='imgTab' align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcMarkCard("+rownum+")'/></td>")
            .append('<input  json="MARKETING_CARD_ID" id="MARKETING_CARD_ID'+rownum+'" name="MARKETING_CARD_ID'+rownum+'"   type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[10]+'"/>' +
            '<input  json="GIFT_AMOUNT" id="GIFT_AMOUNT'+rownum+'" name="GIFT_AMOUNT"   type="hidden"  value="'+arrs[12]+'"/>')
              ;
	 
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[name='ADVC_STATEMENTS_ORDER_DTL_ID']").attr("checked","checked");
	});
	//form校验设置
    trCheckFLoatInit($("#jsontb tr:gt(0) input"))
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
	 
}
 

 

//总部预订单通用选取
function selcAdvc(rownum){
	 var MARKETING_ACT_ID = parent.topcontent.getMARKETING_ACT_ID();
	 if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		 parent.showErrorMsg("请先选择活动信息!");
		 return;
	 }
	 var ids = "";
	 var inputs = $("#jsontb tr:gt(0) input[json='ADVC_ORDER_ID']");
	 inputs.each(function(){
		 var ADVC_ORDER_ID = $(this).val();
		 if(null != ADVC_ORDER_ID && "" != ADVC_ORDER_ID){
			  ids = ids+"'"+ADVC_ORDER_ID+"',";
		 }
	 });
	 ids = ids.substr(0,ids.length-1);
	 var tempsql = "";
	 if("" != ids){
		tempsql = " and ADVC_ORDER_ID not in("+ids+") ";
	 }
	 var SelRowId = getSelRowId();
	 var sql = "(select u.*,"+
		       "(select sum(g.PAYABLE_AMOUNT) GIFT_AMOUNT from ERP_ADVC_GIFT_DTL g where g.DEL_FLAG = 0 and g.ADVC_ORDER_ID = u.ADVC_ORDER_ID) GIFT_AMOUNT"+
		       " from ERP_ADVC_ORDER u"+
		       " where u.STATE = '审核通过'"+
		       " and u.DEL_FLAG = 0"+
		       tempsql +
		       " and not exists (select 1  from ERP_ADVC_STATEMENTS_ORDER_DTL d where d.ADVC_ORDER_ID = u.ADVC_ORDER_ID and d.DEL_FLAG = 0))"
	 
	 
     var obj = selCommon("BS_161", false, "ADVC_ORDER_ID"+rownum, "ADVC_ORDER_ID", "forms[0]","ADVC_ORDER_NO"+rownum+",BILL_TYPE"+rownum+",SALE_DATE"+rownum+",CUST_NAME"+rownum+",TEL"+rownum+",ADVC_AMOUNT"+rownum+",PAYABLE_AMOUNT"+rownum+",GIFT_AMOUNT"+rownum
    	+ ",MARKETING_CARD_ID"+rownum+",MARKETING_CARD_NO"+rownum, 
    	"ADVC_ORDER_NO,BILL_TYPE,SALE_DATE,CUST_NAME,TEL,ADVC_AMOUNT,PAYABLE_AMOUNT,GIFT_AMOUNT,MARKETING_CARD_ID,MARKETING_CARD_NO", "","",sql);
    // var rtnArr = multiSelCommonSet(obj,"ADVC_ORDER_ID,ADVC_ORDER_NO,BILL_TYPE,SALE_DATE,CUST_NAME,TEL,ADVC_AMOUNT,PAYABLE_AMOUNT,GIFT_AMOUNT",rownum);
     
    var actionType = getActionType();
	if("list" == actionType){
		
	}else{
		//设置主表的 定金总额
	   paddingADVC_AMOUNT();
	}
     
}

//卡券通用选取
function selcMarkCard(rownum){
	var MARKETING_ACT_ID = parent.topcontent.getMARKETING_ACT_ID();
	if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		 parent.showErrorMsg("请先选择活动信息!");
		 return;
	}
	
	var ids = "";
	var inputs = $("#jsontb tr:gt(0) input[json='MARKETING_CARD_ID']");
	inputs.each(function(){
		 var MARKETING_CARD_ID = $(this).val();
		 if(null != MARKETING_CARD_ID && "" != MARKETING_CARD_ID){
			  ids = ids+"'"+MARKETING_CARD_ID+"',";
		 }
	 });
	 ids = ids.substr(0,ids.length-1);
	 
	 var params = " MARKETING_ACT_ID='"+MARKETING_ACT_ID+"'  and MARKETING_CARD_ID not in(" 
				   +" select u.MARKETING_CARD_ID from ERP_ADVC_STATEMENTS_ORDER_DTL u where u.DEL_FLAG = 0 "
			       +" and u.ADVC_STATEMENTS_ORDER_ID in"
			       +" (select t.ADVC_STATEMENTS_ORDER_ID from ERP_ADVC_STATEMENTS_ORDER t "
			       +" where t.MARKETING_ACT_ID = '"+MARKETING_ACT_ID+"'"
			       +" and t.DEL_FLAG = 0))";
	 if("" != ids){
		params = params + " and MARKETING_CARD_ID not in("+ids+") "; 
	 }
	 
	 $("#selCardParams").val(params);
	 selCommon("BS_162", false, "MARKETING_CARD_ID"+rownum, "MARKETING_CARD_ID", "forms[0]","MARKETING_CARD_ID"+rownum+",MARKETING_CARD_NO"+rownum, "MARKETING_CARD_ID,MARKETING_CARD_NO", "selCardParams");
	
}


function paddingADVC_AMOUNT(){
	var checkBox = $("#jsontb tr:gt(0) input[name='ADVC_AMOUNT']");
	var total = 0;
	var TOTAL_PAYABLE_AMOUNT = 0;
	var TOTAL_GIFT_AMOUNT = 0;
	checkBox.each(function(){
		var ADVC_AMOUNT = $(this).val();//定金金额
		total = total + Number(ADVC_AMOUNT); 
		var PAYABLE_AMOUNT = $(this).parent().parent().find("input[name='PAYABLE_AMOUNT']").val();
		TOTAL_PAYABLE_AMOUNT = TOTAL_PAYABLE_AMOUNT + FloatFomat(PAYABLE_AMOUNT);
		var GIFT_AMOUNT = $(this).parent().parent().find("input[name='GIFT_AMOUNT']").val();
		TOTAL_GIFT_AMOUNT = TOTAL_GIFT_AMOUNT + FloatFomat(GIFT_AMOUNT);
	});
	total = Math.round(total*100)/100;//计算订金金额，保留2位小数
	TOTAL_PAYABLE_AMOUNT = Math.round(TOTAL_PAYABLE_AMOUNT*100)/100;//计算结算总额，保留2位小数
	TOTAL_GIFT_AMOUNT = Math.round(TOTAL_GIFT_AMOUNT*100)/100;//计算礼品费用，保留2位小数
	
	parent.topcontent.setADVC_AMOUNT(total,TOTAL_PAYABLE_AMOUNT,TOTAL_GIFT_AMOUNT); 
	parent.topcontent.setSTATEMENTS_AMOUNT(TOTAL_PAYABLE_AMOUNT);
	parent.topcontent.setGIFT_AMOUNT(TOTAL_GIFT_AMOUNT);
	
	
};

 
  


function checkchild(){
	var trs = $("#jsontb tr:gt(0)");
	var flag = true;
	trs.each(function(){
		var isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
		if(isEdit){
			var inputs = $(this).find("input[textType='float']");
			inputs.each(function(){
				var label = $(this).attr("label");
				var textType = $(this).attr("textType");
				if("float" == textType){
					// 校验浮点数内容
					if (!CheckFloatInput(this)) {
						flag = false;
						return false;
					}
				}
			});
		}
	});
	 
	return flag;
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


function FloatFomat(arg1){
	var res = Number(arg1);
	if(isNaN(arg1)){
		res = Number(0);
	}
	return res;
}



// add by zhxuw 对单行元素初始化
function trCheckFLoatInit(obj) {
	init_form = true;
	var el, name, val,tempMustinput,tempPttern;
	$(obj).each(function(i,k){
	    el = this;
		name = el.name;
		val = el.value;
		tempMustinput=$(el).attr("mustinput");
		tempPttern=$(el).attr("textType");
		if (name) {
			if(el.readOnly){
				if("cmmx"!=el.className){
				   el.className = ' readonly '+el.className;
				}
			}
			if (tempPttern) {
				// 必输项
				if (tempMustinput && (tempMustinput == true||tempMustinput == 'true')) {
					if (el.mustinputSpan) {
					} else {
						el.insertAdjacentHTML("afterEnd", span_mustinput);
						el.mustinputSpan = true;
					}
				}
			}
		}
	
	});
	
	init_form = false;
}



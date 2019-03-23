


/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
	    addRow();
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
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		//验证通知数量不能大于库存量
		if(!checkNum()){
				return;
			}
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中的零星领料单明细校验
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
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input[name='STOREOUT_NOTICE_DTL_ID']").length>0){
		childData = multiPackJsonData();
	}
	$.ajax({
		url: "storeoutnotice.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STOREOUT_NOTICE_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","storeoutnotice.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storeoutnotice.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREOUT_NOTICE_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.parent.topcontent.pageForm.submit();
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
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "storeoutnotice.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREOUT_NOTICE_DTL_IDS":ids,"STOREOUT_NOTICE_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("storeoutnotice.shtml?action=toFrame");
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
	var SPCL_TECH_FLAG;
	if(arrs[18]==null||arrs[18]==""||arrs[18]=="0"){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREOUT_NOTICE_DTL_ID' id='STOREOUT_NOTICE_DTL_ID"+rownum+"' name='STOREOUT_NOTICE_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;' +
            "<img  align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID" type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[2]+'"/>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR"  autocheck="true" label="花号"  readonly  type="text"   inputtype="string"    maxlength="50"  value="'+arrs[5]+'"/>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" label="品牌"  type="text"   inputtype="string"  style="width:60px;" readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT"  autocheck="true" label="标准单位"  type="text"   inputtype="string"  style="width:60px;"  readonly       maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM"  autocheck="true" label="通知出库数量"  type="text"   style="width:60px;"  mustinput="true"   onkeyup="checNoticekNum('+rownum+');"   value="'+arrs[11]+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价"  type="text"      readonly    style="width:50px;"    value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率"  READONLY type="text"  style="width:30px;"  inputtype="string"     mustinput="true"    value="'+arrs[9]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价"  type="text" READONLY  style="width:60px;" inputtype="string"  mustinput="true"       value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT"  autocheck="true" label="折后金额"  type="text" READONLY  style="width:60px;" inputtype="string"        value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<input  json="STOREOUT_NOTICE_ID" id="STOREOUT_NOTICE_ID'+rownum+'" name="STOREOUT_NOTICE_ID"  label="销售出库通知单ID" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[15]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="特殊工艺单标记" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID"  label="来源单据ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  label="订货数量" type="hidden"/>')
            .append('<input  id="SENDED_NUM'+rownum+'" name="SENDED_NUM"  label="已发货数量" type="hidden"/>')
            .append('<input  id="STORE_NUM'+rownum+'" name="STORE_NUM"  label="库存量" type="hidden" value="'+arrs[19]+'"/>')
              ;
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
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



function checNoticekNum(rownum){
	var NOTICE_NUM = $("#NOTICE_NUM"+rownum).val();
	NOTICE_NUM=isNaN(NOTICE_NUM)?0:parseFloat(NOTICE_NUM);
	var STORE_NUM = $("#STORE_NUM"+rownum).val();
	STORE_NUM = isNaN(STORE_NUM)?0:parseFloat(STORE_NUM);
	if(NOTICE_NUM>STORE_NUM){
		parent.showErrorMsg("通知数量不能大于库存量");
		$("#NOTICE_NUM"+rownum).val("0")
		return;
	}
	var DECT_PRICE = $("#DECT_PRICE"+rownum).val();
	DECT_PRICE = isNaN(DECT_PRICE)?0:parseFloat(DECT_PRICE);
	var r = Math.round((isNaN(NOTICE_NUM*DECT_PRICE)?0:NOTICE_NUM*DECT_PRICE)*100)/100;
	$("#DECT_AMOUNT"+rownum).val(r);
	countTotalMoney();
}



//货品通用选取
    function selcPrd(rownum){
    	var SALE_ORDER_ID = parent.topcontent.getFrom();
    	if(null == SALE_ORDER_ID || "" == SALE_ORDER_ID){
    		parent.showErrorMsg("请选择来源单据信息")
    		return;
    	}
    	var STORE_ID = parent.topcontent.getStoreId();
    	if(null == STORE_ID || "" == STORE_ID){
			parent.showErrorMsg("请选择'出库库房编号'");
			return;
		}
    	
    	var selectParams=" DEL_FLAG=0 and SALE_ORDER_ID='"+SALE_ORDER_ID+"' and STORE_ID='"+STORE_ID+"'";
    	$("#selectParams").val(selectParams);
    	//0155262--start--刘曰刚--2014-06-18//
    	//修改通知出库数量获取方式，原来为订货数量，现在改为订货数量-已发货数量
	     var obj=selCommon("BS_81", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]",
	    	 				"PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",ORDER_NUM"+rownum+",DECT_AMOUNT"+rownum+",FROM_BILL_DTL_ID"+rownum+",SENDED_NUM"+rownum+",STORE_NUM"+rownum, 
	    	 				"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,ORDER_AMOUNT,SALE_ORDER_DTL_ID,SENDED_NUM,STORE_NUM", "selectParams");
	     //订货数量
	     var ORDER_NUM=$("#ORDER_NUM"+rownum).val();
	     if(""==ORDER_NUM||null==ORDER_NUM){
	    	 ORDER_NUM=0;
	     }
	     ORDER_NUM=isNaN(ORDER_NUM)?0:parseFloat(ORDER_NUM);
	     //已发货数量
	     var SENDED_NUM=$("#SENDED_NUM"+rownum).val();
	     if(""==SENDED_NUM||null==SENDED_NUM){
	    	 SENDED_NUM=0;
	     }
	     SENDED_NUM=isNaN(SENDED_NUM)?0:parseFloat(SENDED_NUM);
	     
	     //通知出库数量
	     var NOTICE_NUM=Math.round((isNaN(ORDER_NUM-SENDED_NUM)?0:ORDER_NUM-SENDED_NUM)*100)/100;
	     $("#NOTICE_NUM"+rownum).val(NOTICE_NUM);
	     //0155262--End--刘曰刚--2014-06-18//
	     countTotalMoney();
	     var SPCL_TECH_FLAG=$("#SPCL_TECH_FLAG"+rownum).val();
	     if(""!=SPCL_TECH_FLAG&&null!=SPCL_TECH_FLAG){
	    	 if(SPCL_TECH_FLAG>0){
	    		 $("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />");
	    		 return;
	    	 }
	     }
	     $("#SPECIAL_FLAG"+rownum).html("无");
	     $("#STOREOUT_NOTICE_DTL_ID"+rownum).prop("checked",true);
	}
function url(id){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+id).val();
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
//下帧改变应收金额时算出总金额赋值到上贞应收总金额
	function countTotalMoney(){
		var inputs = $("input[name='DECT_AMOUNT']");
		var total=0;
		inputs.each(function(){
			var v = $(this).val();
			if(""==v){
				v=0;
			}
			var PAYABLE_AMOUNT=isNaN(v)?0:parseFloat(v);
			total = Math.round((isNaN(total+PAYABLE_AMOUNT)?0:total+PAYABLE_AMOUNT)*100)/100;
		});
		parent.topcontent.setAmoutVal(total);
	}
function delDtl(){
	$("#jsontb :checkbox").attr("checked","true");
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
}
function checkNum(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag=true;
	checkBox.each(function(){
		//库存数量
		var STORE_NUM=$(this).parent().parent().find("input[name='STORE_NUM']").val();
		STORE_NUM=isNaN(STORE_NUM)?0:parseFloat(STORE_NUM);
		;
		//通知出库数量
		var NOTICE_NUM=$(this).parent().parent().find("input[name='NOTICE_NUM']").val();
		if(isNaN(NOTICE_NUM)){
			parent.showErrorMsg("通知出库数量必须为数字");
			flag=false;
			return false;
		}
		var re1 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
	        if(!re1.test(NOTICE_NUM)){
	            parent.showErrorMsg("通知出库数量最多可输入8位正整数");
	            flag=false;
				return false;
	     }
		var NOTICE_NUM_ID=$(this).parent().parent().find("input[name='NOTICE_NUM']").attr("id");
		if (!CheckIntegerInput("#"+NOTICE_NUM_ID)) {
			return false;
		}
		NOTICE_NUM=isNaN(NOTICE_NUM)?0:parseFloat(NOTICE_NUM);
		if(0==NOTICE_NUM){
			parent.showErrorMsg("通知数量不能为0");
			flag=false;
			return false;
		}
		if(STORE_NUM<NOTICE_NUM){
			parent.showErrorMsg("通知数量不能大于库存量");
			flag=false;
			return false;
		}
	});
	return flag;
}

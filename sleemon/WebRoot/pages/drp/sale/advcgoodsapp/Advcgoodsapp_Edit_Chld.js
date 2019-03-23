/**
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_Edit_Chld
 * @author lyg
 * @time   2013-11-02 18:55:53 
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
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		//验证通知出库数量
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
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "advcgoodsapp.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_SEND_REQ_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","advcgoodsapp.shtml?action=toFrame");
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
		url: "advcgoodsapp.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_SEND_REQ_ID":selRowId},
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

//check通知数量总和不能超过未发货数量
function checkNoticeNum(){
	var checkBoxs = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkBoxs.each(function(){
		var NOTICE_NUM = $(this).parent().parent().find("input[json='NOTICE_NUM']").val();
		var APPLY_NUM = $(this).parent().parent().find("input[json='APPLY_NUM']").val();
		var ORDER_NUM = $(this).parent().parent().find("input[json='ORDER_NUM']").val();
		if(null == APPLY_NUM || "" == APPLY_NUM){
			APPLY_NUM = 0;
		}
		APPLY_NUM = parseInt(APPLY_NUM);
		NOTICE_NUM = parseInt(NOTICE_NUM);
		ORDER_NUM = parseInt(ORDER_NUM);
		
		if((NOTICE_NUM+APPLY_NUM)>ORDER_NUM){
			parent.showErrorMsg("累计'通知出库数量'不能大于'订货数量'");
			flag = false;
			return false;
		}
	});
	 
   return flag;
}


//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var selRowId = getSelRowId();
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "advcgoodsapp.shtml?action=childDelete&ADVC_SEND_REQ_ID="+selRowId,
		type:"POST",
		dataType:"text",
		data:{"ADVC_SEND_REQ_DTL_IDS":ids},
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
function gobacknew()
{
   newGoBack("advcgoodsapp.shtml?action=toFrame");
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
              '',
              ''
	        ];
		}
	//样式行
	var SPCL_TECH_FLAG;
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	if(null == arrs[11] || "" == arrs[11]){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='ADVC_SEND_REQ_DTL_ID' name='ADVC_SEND_REQ_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID"  label="货品id"  type="hidden"    readonly    mustinput="true"     maxlength="30"  value="'+arrs[8]+'"/>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  autocheck="true" label="货品编号"  type="text" size="8"  inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[1]+'"/>' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_COLOR"  id="PRD_COLOR'+rownum+'" name="PRD_COLOR"  autocheck="true" label="花号"  type="hidden"   inputtype="string"     maxlength="50"  value="'+arrs[4]+'"/>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" label="品牌"  type="text" size="3"  inputtype="string"   readonly      maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT"  autocheck="true" label="标准单位" size="3" type="text"    inputtype="string"   readonly     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'" name="SPECIAL_FLAG">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM" onKeyUp="copyNum('+rownum+')" size="3"  autocheck="true" label="通知出库数量"  type="text"      mustinput="true"   maxlength="11"   value="'+arrs[7]+'"/><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input   id="FREEZE'+rownum+'" name="FREEZE"  autocheck="true" size="8" label="本次冻结数量"  value="'+arrs[22]+'"   readonly  type="text" inputtype="string"  />&nbsp; </td>')
            .append('<td nowrap align="left"><input  json="ORDER_RECV_DATE" id="ORDER_RECV_DATE'+rownum+'" name="ORDER_RECV_DATE"  autocheck="true" style="width:80px;" label="交货日期" onclick="SelectDate();"  mustinput="true"    readonly  type="text" inputtype="date"   value="'+arrs[18]+'" />' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE'+rownum+');">&nbsp; </td>')
            .append('<input  json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID"  label="来源货品信息"  type="hidden"    readonly    mustinput="true"     maxlength="30"  value="'+arrs[10]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[11]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="特殊工艺单标记" type="hidden" value="'+arrs[12]+'"/>')
            .append('<input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  label="单价" type="hidden" value="'+arrs[13]+'"/>')
            .append('<input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  label="折扣率" type="hidden" value="'+arrs[14]+'"/>')
            .append('<input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  label="折后价" type="hidden" value="'+arrs[15]+'"/>')
            .append('<input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT"  label="折后金额" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="FREEZE_NUM" id="FREEZE_NUM'+rownum+'" name="FREEZE_NUM"  autocheck="true" label="冻结数量" type="hidden"   value="'+arrs[17]+'"/>')
            .append('<input  json="FREEZE_TO_SEND_NUM" id="FREEZE_TO_SEND_NUM'+rownum+'" name="FREEZE_TO_SEND_NUM"  label="冻结转发货数量" type="text" value="'+arrs[21]+'"/>')
            .append('<td nowrap align="left"><input  json="SEND_NUM" id="SEND_NUM'+rownum+'" name="SEND_NUM"  label="已发货数量" type="text" style="width:80px;" readonly value="'+arrs[19]+'"/>&nbsp; </td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  label="原订货数量" type="text" style="width:80px;" readonly value="'+arrs[20]+'"/>&nbsp; </td>')
            .append('<input   id="ADV_FREEZE_NUM'+rownum+'" name="ADV_FREEZE_NUM"  label="预订单明细的冻结数量" type="hidden"  value="'+arrs[23]+'"/>')
            .append('<input   id="CAN_NOTICE_NUM'+rownum+'" name="CAN_NOTICE_NUM"  label="可通知出库数量" type="hidden"  value="'+arrs[24]+'"/>')
            .append('<input   id="PRD_TYPE'+rownum+'" json="PRD_TYPE" name="PRD_TYPE"  label="货品类别" type="hidden"  value="'+arrs[25]+'"/>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[9]+'"/>&nbsp;</td>')
            
             ;
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
//货品通用选取
    function selcPrd(rownum){
    	var FROM_BILL_ID=parent.topcontent.getFROM_BILL_ID();
    	var STOREOUT_STORE_ID=parent.topcontent.getSTOREOUT_STORE_ID();
    	if(FROM_BILL_ID==null || FROM_BILL_ID == ""){
    		alert("请选择来源单据编号");
    		return false;
    	}
    	if(STOREOUT_STORE_ID==null || STOREOUT_STORE_ID == ""){
    		alert("请选择出库库房编号");
    		return false;
    	}
		//获取编辑和列表里的来源单据id设置通用选取条件
    	 //0156501--start 刘曰刚 --2014-06-18//
        //修改过滤同一条明细只选一次
    	var dtlId=$("#FROM_BILL_DTL_IDS").val();
    	var ids;
    	if(""==dtlId||null==dtlId){
    		ids="";
    	}else{
    		ids=dtlId+",";
    	}
    	$("input[name='FROM_BILL_DTL_ID']").each(function(){
    		if(""!=$(this).val()&&null!=$(this).val()){
    			ids=ids+"'"+$(this).val()+"',";
    		}
    	});
    	ids = ids.substr(0,ids.length-1);
    	var sql;
    	if(""==ids||null==ids){
    		sql="1=1";
    	}else{
    		sql="ADVC_ORDER_DTL_ID not in ("+ids+")";
    	}
    	 //0156501--End 刘曰刚 --2014-06-18//
		$("#selectParams").val("DEL_FLAG=0 and NVL(ORDER_NUM,0)>NVL(SEND_NUM,0) and  ADVC_ORDER_ID='"+FROM_BILL_ID+"' and (FREEZE_STORE_ID='"+STOREOUT_STORE_ID+"' or NVL(IS_FREEZE_FLAG,0)='0') and "+sql);
		var obj=selCommon("BS_39", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]",
			"PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",FROM_BILL_DTL_ID"+rownum+",NOTICE_NUM"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum
			+",DECT_AMOUNT"+rownum+",FREEZE_NUM"+rownum+",ORDER_RECV_DATE"+rownum+",ORDER_NUM"+rownum+",SEND_NUM"+rownum+",CAN_NOTICE_NUM"+rownum+",PRD_TYPE"+rownum+",REMARK"+rownum, 
			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,ADVC_ORDER_DTL_ID,NUM,SPCL_TECH_ID,SPCL_TECH_FLAG,PRICE,DECT_RATE,DECT_PRICE,PAYABLE_AMOUNT,FREEZE_NUM,ORDER_RECV_DATE,ORDER_NUM,SEND_NUM,NUM,PRD_TYPE,REMARK", "selectParams");
		rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,FROM_BILL_DTL_ID,NOTICE_NUM,SPCL_TECH_ID,SPCL_TECH_FLAG,PRICE,DECT_RATE,DECT_PRICE,DECT_AMOUNT,FREEZE_NUM,ORDER_RECV_DATE,ORDER_NUM,SEND_NUM,CAN_NOTICE_NUM,PRD_TYPE,REMARK",rownum);
		var checkBox=$("input[type='checkbox']");
		checkBox.each(function(){
			var SPCL_TECH_FLAG=$(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
			var SPCL_TECH_ID=$(this).parent().parent().find("input[name='SPCL_TECH_ID']").val();
			if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""||SPCL_TECH_FLAG==0){
				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("无");
			}else{
				var onclick="urlById('"+SPCL_TECH_ID+"')";
				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("<span style='color: red'>有</span><input class='btn'  value='查看' onclick="+onclick+"  type='button' />");
			}
		})
		copyNumByName();
	}
    
function urlById(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

function url(rownum){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}



function delDtl(){
	$("#jsontb :checkbox").attr("checked","true");
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
}



function copyNum(id){
	var NOTICE_NUM=$("#NOTICE_NUM"+id).val();
	if(""==NOTICE_NUM||null==NOTICE_NUM){
		NOTICE_NUM=0;
	}
	NOTICE_NUM=isNaN(NOTICE_NUM)?0:parseFloat(NOTICE_NUM);
	var temp_DECT_PRICE=$("#DECT_PRICE"+id).val();//折后单价
	var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
	var PAYABLE_AMOUNT = Math.round((isNaN(DECT_PRICE*NOTICE_NUM)?0:DECT_PRICE*NOTICE_NUM)*100)/100;//计算应收金额，保留2位小数
	$("#DECT_AMOUNT"+id).val(PAYABLE_AMOUNT);
	var FREEZE_NUM=$("#FREEZE_NUM"+id).val();//冻结数量
	if(""==FREEZE_NUM||null==FREEZE_NUM){
		FREEZE_NUM=0;
	}
	FREEZE_NUM=isNaN(FREEZE_NUM)?0:parseFloat(FREEZE_NUM);
	var FREEZE_TO_SEND_NUM=$("#FREEZE_TO_SEND_NUM"+id).val();//冻结转发货数量
	if(""==FREEZE_TO_SEND_NUM||null==FREEZE_TO_SEND_NUM){
		FREEZE_TO_SEND_NUM=0;
	}
	 
	//预订单明细的冻结数量
	var ADV_FREEZE_NUM = $("#ADV_FREEZE_NUM"+id).val();
	ADV_FREEZE_NUM = parseInt(ADV_FREEZE_NUM);
	if(isNaN(ADV_FREEZE_NUM)){
		ADV_FREEZE_NUM = 0;
	}
	
	//可通知出库数量
	var CAN_NOTICE_NUM = $("#CAN_NOTICE_NUM"+id).val();
	CAN_NOTICE_NUM = parseInt(CAN_NOTICE_NUM);
	if(isNaN(CAN_NOTICE_NUM)){
		CAN_NOTICE_NUM = 0;
	}
	
	//alert("ORDER_NUM="+ORDER_NUM+" ADV_FREEZE_NUM="+ADV_FREEZE_NUM+" NOTICE_NUM="+NOTICE_NUM+" FREEZE_NUM="+FREEZE_NUM);
    //使用预订单明细的冻结数量计算
	FREEZE_NUM = ADV_FREEZE_NUM;
	 
	FREEZE_TO_SEND_NUM=isNaN(FREEZE_TO_SEND_NUM)?0:parseFloat(FREEZE_TO_SEND_NUM);
	var FREEZE=Math.round((isNaN(NOTICE_NUM-FREEZE_NUM-FREEZE_TO_SEND_NUM)?0:NOTICE_NUM-FREEZE_NUM-FREEZE_TO_SEND_NUM),2);
	
	
	if(FREEZE<0 || CAN_NOTICE_NUM<NOTICE_NUM){
		FREEZE=0
	}
	$("#FREEZE"+id).val(FREEZE);
}


function copyNumByName(){
	var checkBox=$("input[type='checkbox']");
	checkBox.each(function(){
		var NOTICE_NUM=$(this).parent().parent().find("input[name='NOTICE_NUM']").val();
		if(""==NOTICE_NUM||null==NOTICE_NUM){
			NOTICE_NUM=0;
		}
		NOTICE_NUM=isNaN(NOTICE_NUM)?0:parseFloat(NOTICE_NUM);
		var temp_DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//折后单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var PAYABLE_AMOUNT = Math.round((isNaN(DECT_PRICE*NOTICE_NUM)?0:DECT_PRICE*NOTICE_NUM)*100)/100;//计算应收金额，保留2位小数
		$(this).parent().parent().find("input[name='DECT_AMOUNT']").val(PAYABLE_AMOUNT);
		var FREEZE_NUM=$(this).parent().parent().find("input[name='FREEZE_NUM']").val();//冻结数量
		if(""==FREEZE_NUM||null==FREEZE_NUM){
			FREEZE_NUM=0;
		}
		FREEZE_NUM=isNaN(FREEZE_NUM)?0:parseFloat(FREEZE_NUM);
		var FREEZE_TO_SEND_NUM=$(this).parent().parent().find("input[name='FREEZE_TO_SEND_NUM']").val();//冻结转发货数量
		if(""==FREEZE_TO_SEND_NUM||null==FREEZE_TO_SEND_NUM){
			FREEZE_TO_SEND_NUM=0;
		}
		 
		//预订单明细的冻结数量
		var ADV_FREEZE_NUM = $(this).parent().parent().find("input[name='ADV_FREEZE_NUM']").val();
		ADV_FREEZE_NUM = parseInt(ADV_FREEZE_NUM);
		if(isNaN(ADV_FREEZE_NUM)){
			ADV_FREEZE_NUM = 0;
		}
		
		//可通知出库数量
		var CAN_NOTICE_NUM = $(this).parent().parent().find("input[name='CAN_NOTICE_NUM']").val();
		CAN_NOTICE_NUM = parseInt(CAN_NOTICE_NUM);
		if(isNaN(CAN_NOTICE_NUM)){
			CAN_NOTICE_NUM = 0;
		}
		
		//alert("ORDER_NUM="+ORDER_NUM+" ADV_FREEZE_NUM="+ADV_FREEZE_NUM+" NOTICE_NUM="+NOTICE_NUM+" FREEZE_NUM="+FREEZE_NUM);
	    //使用预订单明细的冻结数量计算
		FREEZE_NUM = ADV_FREEZE_NUM;
		 
		FREEZE_TO_SEND_NUM=isNaN(FREEZE_TO_SEND_NUM)?0:parseFloat(FREEZE_TO_SEND_NUM);
		var FREEZE=Math.round((isNaN(NOTICE_NUM-FREEZE_NUM-FREEZE_TO_SEND_NUM)?0:NOTICE_NUM-FREEZE_NUM-FREEZE_TO_SEND_NUM),2);
		
		
		if(FREEZE<0 || CAN_NOTICE_NUM<NOTICE_NUM){
			FREEZE=0
		}
		$(this).parent().parent().find("input[name='FREEZE']").val(FREEZE);
	})
}

//验证通知出库数量为数字
function checkNum(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag=true;
	checkBox.each(function(){
		var NOTICE_NUM=$(this).parent().parent().find("input[name='NOTICE_NUM']").val();//通知出库数量
		if(""==NOTICE_NUM||null==NOTICE_NUM){
				parent.showErrorMsg("请输入通知出库数量");
	            flag=false;
				return false;
		}
		if("0"==NOTICE_NUM){
			parent.showErrorMsg("通知出库数量不能为0");
	            flag=false;
				return false;
		}
		var re1 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re1.test(NOTICE_NUM)){
            parent.showErrorMsg("通知出库数量最多可输入8位正整数和两位小数");
            flag=false;
			return false;
        }
	});
	return flag;
}
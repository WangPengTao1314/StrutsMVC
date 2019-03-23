/**
 * @prjName:喜临门营销平台
 * @fileName:Allocate_Edit_Chld
 * @author lyg
 * @time   2013-09-05 13:29:12 
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
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "allocate.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ALLOCATE_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","allocate.shtml?action=toFrame");
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
	var ALLOC_OUT_STORE_ID=parent.topcontent.getALLOC_OUT_STORE_ID();
	$.ajax({
		url: "allocate.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ALLOCATE_ID":selRowId,"ALLOC_OUT_STORE_ID":ALLOC_OUT_STORE_ID},
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
		url: "allocate.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ALLOCATE_DTL_IDS":ids},
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
   newGoBack("allocate.shtml?action=toFrame");
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
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var SPCL_TECH_FLAG;
	if(arrs[15]==null||arrs[15]==""){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input   value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='ALLOCATE_DTL_ID' name='ALLOCATE_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
             .append('<td nowrap align="left" style="display:none;"><input type="hidden" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/></td>')
             .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号" size="15"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" size="15"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" size="15"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号" size="15"  type="text"   inputtype="string"   readonly         maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text" size="8"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="4"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[8]+'"/>&nbsp;</td>')
             .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
           // .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  autocheck="true" label="单价"  type="text"   inputtype="string"   readonly    mustinput="true"     value="'+arrs[9]+'" />&nbsp;</td>')
           // .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'"  autocheck="true" label="折扣率" onkeyup="count('+rownum+')" type="text"   inputtype="float"     mustinput="true"   valueType="4,2"   value="'+arrs[10]+'" />&nbsp;</td>')
            //.append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  autocheck="true" label="折后单价" onkeyup="countDECT_RATE('+rownum+')"  type="text"   inputtype="float"     mustinput="true"   valueType="8,2"   value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ALLOC_NUM" id="ALLOC_NUM'+rownum+'" name="ALLOC_NUM'+rownum+'"  autocheck="true" label="调拨数量" size="8"  type="text" onkeyup="countALLOC_NUM('+rownum+')"  inputtype="float"     mustinput="true"   valueType="8,2"   value="'+arrs[12]+'" />&nbsp;</td>')
            //.append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT'+rownum+'"  autocheck="true" label="折后金额"  type="text" onkeyup="countDECT_AMOUNT('+rownum+')"  inputtype="float"     mustinput="true"   valueType="12,2"   value="'+arrs[13]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[14]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺单标记" type="hidden" value="'+arrs[15]+'"/>')
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function selcPrd(rownum){
		 var ALLOC_OUT_STORE_ID=parent.topcontent.getALLOC_OUT_STORE_ID();
		 if(""==ALLOC_OUT_STORE_ID||null==ALLOC_OUT_STORE_ID){
			 parent.showErrorMsg("请选择调出库房编号");
			 return;
		 }
		 var LEDGER_ID=$("#LEDGER_ID").val();
		 $("#selectParams").val(" PRD_ID in (select PRD_ID from DRP_STORE_ACCT where STORE_ID='"+ALLOC_OUT_STORE_ID+"' and STATE=0)  and STORE_ID='"+ALLOC_OUT_STORE_ID+"' and STOLEDGER='"+LEDGER_ID+"' and COMM_FLAG=1 or (COMM_FLAG=0 and PROLEDGER='"+LEDGER_ID+"') ");
	     var obj=selCommon("BS_71", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,SPCL_TECH_ID,SPCL_TECH_FLAG", "selectParams");
	     var SPCL_TECH_FLAG=$("#SPCL_TECH_FLAG"+rownum).val();
	     if(SPCL_TECH_FLAG>0){
	    	 var SPCL_TECH="<span style='color: red'>有</span><input value='查看' onclick='url("+rownum+")'  type='button' />"
	    	 $("#SPECIAL_FLAG"+rownum).html(SPCL_TECH);
	     }else{
	    	  $("#SPECIAL_FLAG"+rownum).html("无");
	     }
	     
	}
//当键盘按键松开时触发事件
	//输入折扣率时，计算折后单价、应收金额
	 function count(i){
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
		var temp_ALLOC_NUM=$("#ALLOC_NUM"+i).val();//调拨数量
		//判断输入是否为数字，如果部为数字则默认为0
		var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ALLOC_NUM=isNaN(temp_ALLOC_NUM)?0:parseFloat(temp_ALLOC_NUM);//调拨数量
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
		var DECT_AMOUNT = Math.round((isNaN(ALLOC_NUM*DECT_PRICE)?0:ALLOC_NUM*DECT_PRICE)*100)/100;//计算应收金额，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
	}
	//输入折后单价时，计算折扣率和应收金额
	function countDECT_RATE(i){
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_ALLOC_NUM=$("#ALLOC_NUM"+i).val();//调拨数量
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ALLOC_NUM=isNaN(temp_ALLOC_NUM)?0:parseFloat(temp_ALLOC_NUM);//调拨数量
		var DECT_AMOUNT = Math.round((isNaN(DECT_PRICE*ALLOC_NUM)?0:DECT_PRICE*ALLOC_NUM)*100)/100;//计算应收金额，保留2位小数
		var DECT_RATE = Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
		$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
		$("#DECT_RATE"+i).val(DECT_RATE);
	}
	//输入折后金额后计算折后单价和 折扣率
	function countDECT_AMOUNT(i){
		var temp_PRICE=$("#PRICE"+i).val();//单价
		var temp_ALLOC_NUM=$("#ALLOC_NUM"+i).val();//调拨数量
		var temp_DECT_AMOUNT=$("#DECT_AMOUNT"+i).val();//应收金额
		var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
		var ALLOC_NUM=isNaN(temp_ALLOC_NUM)?0:parseFloat(temp_ALLOC_NUM);//调拨数量
		var DECT_AMOUNT=isNaN(temp_DECT_AMOUNT)?0:parseFloat(temp_DECT_AMOUNT);//应收金额
		var DECT_PRICE= Math.round((isNaN(DECT_AMOUNT/ALLOC_NUM)?0:DECT_AMOUNT/ALLOC_NUM)*100)/100;//计算折后单价，保留2位小数
		var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
		$("#DECT_PRICE"+i).val(DECT_PRICE);
		$("#DECT_RATE"+i).val(DECT_RATE);
	}
	//输入订货数量计算应收金额
	function countALLOC_NUM(i){
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var temp_ALLOC_NUM=$("#ALLOC_NUM"+i).val();//调拨数量
		var ALLOC_NUM=isNaN(temp_ALLOC_NUM)?0:parseFloat(temp_ALLOC_NUM);//调拨数量
		var DECT_AMOUNT = Math.round((isNaN(DECT_PRICE*ALLOC_NUM)?0:DECT_PRICE*ALLOC_NUM)*100)/100;//计算应收金额，保留2位小数
		$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
	}
function delDtl(){
	$("#jsontb :checkbox").attr("checked","true");
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
}
function url(rownum){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	var data=window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
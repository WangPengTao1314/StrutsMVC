/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_Edit_Chld
 * @author chenj
 * @time   2013-11-03 16:25:12 
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
	    //addRow();
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
			//检查 返修数量不能大于库存量
			var f = clickSaveCheckNum();
			if(!f){
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
	//检查 返修数量不能大于库存量
	var f = clickSaveCheckNum();
	if(!f){
		return;
	}
			
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	
	//返修日期不能小于当然日期
	var now = new Date();
	var sel = parent.topcontent.document.getElementById("REQ_FINISH_DATE").value;
	sel = sel.replace(/-/g,"/");
	
	var nowDate = new Date(Date.parse(now.getFullYear()+"/"+(now.getMonth() + 1)+"/"+now.getDate()));
	var selDate = new Date(Date.parse(sel));
	if(selDate < nowDate){
		parent.showErrorMsg("返修日期不能小于当前日期！");
		return false;
	}
	
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	 
	$.ajax({
		url: "repairapp.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ERP_REPAIR_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","repairapp.shtml?action=toFrame");
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
		url: "repairapp.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ERP_REPAIR_ORDER_ID":selRowId},
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
		url: "repairapp.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"REPAIR_ORDER_DTL_IDS":ids},
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
   newGoBack("repairapp.shtml?action=toFrame");
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

//清空子表
function clearValue(){
	var childs = $("#jsontb tr:gt(0)");
	var i = 0;
	childs.each(function(){
		i = parseInt(i)+1;
		$(this).find("input[type='text'][json!='REPAIR_ORDER_DTL_ID']").val("");
		//$(this).find("td[name='SPECIAL_FLAG_TD']").html("无");
		$("#SPECIAL_FLAG_SCRN"+i).html("无");
		$("#SPCL_TECH_ID"+i).val("0");
		
		var REPAIR_ATT = document.getElementById("REPAIR_ATT"+i+"UploadertheCancel");
		if(null != REPAIR_ATT){
			REPAIR_ATT.click();
		}
		
		 
		
	});
	
	
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='REPAIR_ORDER_DTL_ID' name='REPAIR_ORDER_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            
            .append('<input type="hidden" name="PRD_ID'+rownum+'" id="PRD_ID'+rownum+'" json="PRD_ID" value="'+arrs[2]+'" />')
            .append('<td nowrap align="left"><input type="text" json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  '
			+' autocheck="true" seltarget="selMX" inputtype="string" label="货品编号" mustinput="true" size="12"  maxlength="32" READONLY value="'+arrs[2]+'"size="15" />&nbsp;' +
		    '<img align="absmiddle" name="selMX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif"'
             +'onClick="selProduct('+rownum+')"> </td>')
            
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display:none"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"   readonly         maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  size="6" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="6" type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            
            .append('<td nowrap align="center" name="SPECIAL_FLAG_TD" ><span id="SPECIAL_FLAG_SCRN'+rownum+'"></span><input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  autocheck="true" label="特殊工艺ID"  type="hidden" value="'+arrs[13]+'"/><input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'" type="hidden" autocheck="true" label="特殊工艺标记"  value="'+arrs[14]+'"/></td>')
            
            .append('<td nowrap align="left"><input  json="REPAIR_PRICE" id="REPAIR_PRICE'+rownum+'" name="REPAIR_PRICE'+rownum+'" style="width:100px;" autocheck="true" label="销售价格"  type="text"   inputtype="string" mustinput="true" readonly  maxlength="11"  value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REPAIR_NUM" id="REPAIR_NUM'+rownum+'" name="REPAIR_NUM'+rownum+'" style="width:65px;" autocheck="true" label="返修数量"  type="text"        maxlength="22"  mustinput="true" onkeyup="countVOLUM('+rownum+')" value="'+arrs[9]+'" />&nbsp;<input type="hidden" id="SAFE_NUM'+rownum+'"  label="实时库存" name="SAFE_NUM" value="'+arrs[15]+'"/></td>')
            .append('<td nowrap align="left"><input  json="REPAIR_AMOUNT" id="REPAIR_AMOUNT'+rownum+'" name="REPAIR_AMOUNT'+rownum+'"  autocheck="true" label="返修金额" size="15" type="text"   inputtype="float"  onkeyup=""        maxlength="8"  value="'+arrs[10]+'"  />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REPAIR_RESON" id="REPAIR_RESON'+rownum+'" name="REPAIR_RESON'+rownum+'"  autocheck="true" label="返修原因"  type="text"   inputtype="string"     mustinput="true"     maxlength="250"  value="'+arrs[11]+'"  />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="VOLUME" id="VOLUME'+rownum+'" name="VOLUME"  autocheck="true" label="体积"  type="text"   inputtype="string" size="5"  readonly     maxlength="22"  value="'+arrs[16]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="TOTAL_VOLUME" id="TOTAL_VOLUME'+rownum+'" name="TOTAL_VOLUME"  autocheck="true" label="总体积"  type="text"  size="5" inputtype="string"   readonly     maxlength="22"   value="'+arrs[17]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REPAIR_ATT" id="REPAIR_ATT'+rownum+'" name="REPAIR_ATT'+rownum+'" type="text" value="'+arrs[12]+'"/>&nbsp;</td>')
            
             
              ;
              
            
            uploadFile("REPAIR_ATT"+rownum, "SAMPLE_DIR", true);
			 
            setSpecTech(rownum);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
	
}
function selProduct(rownum){
	var selRowId = getSelRowId();
	var storeID =   parent.topcontent.document.getElementById("STOREOUT"+selRowId)

	if(storeID==null){
		storeID = parent.topcontent.document.getElementById("STOREOUT_STORE_ID").value;
	}else{
		storeID = storeID.value;
	}
	
	document.getElementById("selectContion").value=" store_id = '"+storeID+"' and COMM_FLAG=1 ";
	selCommon("BS_71", false, "PRD_ID"+rownum, "PRD_ID","forms[1]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",SPCL_TECH_ID"+rownum
		+",SPCL_TECH_FLAG"+rownum+",SAFE_NUM"+rownum+",REPAIR_PRICE"+rownum+",VOLUME"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,SPCL_TECH_ID,SPCL_TECH_FLAG,SAFE_NUM,FACT_PRICE,VOLUME", "selectContion")
	$("#REPAIR_NUM"+rownum).val("");
	//selCommon("BS_71", false, "PRD_ID"+rownum, "PRD_ID","forms[1]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT", "selectContion")
	setSpecTech(rownum);

}
function setSpecTech(rownum){
	var spclTechFlag =  $("#SPCL_TECH_FLAG"+rownum).val();
	if(spclTechFlag !='1' && spclTechFlag !='2'){
		$("#SPECIAL_FLAG_SCRN"+rownum).text('无');
	}else{
		$("#SPECIAL_FLAG_SCRN"+rownum).html('<font  style="color: red">有</font><input value="查看" class="btn" onclick="showSpecTech('+rownum+')"  type="button" />&nbsp;');
	}
}


function countAmount(num){
	var number = $("#REPAIR_NUM"+num).val();
	number = parseInt(number);
	if(number == 0){
		parent.showErrorMsg("请输入'返修数量'");
		return;
	}
	var price = $("#REPAIR_PRICE"+num).val();
	var SAFE_NUM = $("#SAFE_NUM"+num).val();//库存量
	
	if(number>parseInt(SAFE_NUM)){
		parent.showErrorMsg("返修数量不能大于库存量");
		$("#REPAIR_AMOUNT"+num).val(null);
		return;
	}
	var priceVal = 0;
	if(price!=null && $.trim(price)!=""){
		priceVal = parseFloat(price);
	}
	var numberVal=0;
	if(number!=null && $.trim(number)!=""){
		numberVal = parseFloat(number);
	}
	var amount = numberVal * priceVal;
	$("#REPAIR_AMOUNT"+num).val(amount);
	
	var VOLUME = $("#VOLUME"+num).val();
	VOLUME = Number(VOLUME);
	if(isNaN(VOLUME)){
		VOLUME = 0;
	}
	TOTAL_VOLUME = VOLUME*number;
	TOTAL_VOLUME = TOTAL_VOLUME.toFixed(2);
	$("#TOTAL_VOLUME"+num).val(TOTAL_VOLUME);
}


//计算总体积
function countVOLUM(rownum){
	var number = $("#REPAIR_NUM"+rownum).val();
	number = parseInt(number);
	if(isNaN(number)){
		number = 0;
	}
	var VOLUME = $("#VOLUME"+rownum).val();
	VOLUME = Number(VOLUME);
	if(isNaN(VOLUME)){
		VOLUME = 0;
	}
	TOTAL_VOLUME = VOLUME*number;
	TOTAL_VOLUME = TOTAL_VOLUME.toFixed(2);
	$("#TOTAL_VOLUME"+rownum).val(TOTAL_VOLUME);
}


//点击保存的时候 检查 返修数量不能大于库存量
function clickSaveCheckNum(){
	var flag = true;
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
		    var STD_UNIT = $(this).find("input[json='STD_UNIT']").val();
		    STD_UNIT = $.trim(STD_UNIT);
		    if(null == STD_UNIT || "" == STD_UNIT){
		    	return false;
		    }
			var REPAIR_NUM = $(this).find("input[json='REPAIR_NUM']").val();
			var SAFE_NUM = $(this).find("input[name='SAFE_NUM']").val();
			if(parseInt(REPAIR_NUM) > parseInt(SAFE_NUM)){
				parent.showErrorMsg("返修数量不能大于库存量");
				flag = false;
				return false;
			}
		}
	});
	return flag;
}



function showSpecTech(rowNum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rowNum).val();
	var PRICE=$("#REPAIR_PRICE"+rowNum).val();
	window.showModalDialog('techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID='+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
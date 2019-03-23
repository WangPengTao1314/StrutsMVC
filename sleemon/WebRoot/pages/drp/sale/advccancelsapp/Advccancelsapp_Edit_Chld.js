

/**
 * @prjName:喜临门营销平台
 * @fileName:预订发货取消申请
 * @author zzb
 * @time   2014-05-19  
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //form校验设置
	InitFormValidator(0);
	 
	$("#add").click(function(){
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
			//对于选中的明细校验
			if(!formMutiTrChecked()){
				return;
			}
			//check取消数量不能大于通知出库数量
			if(!checkCancelNum()){
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
		url: "advccancelsapp.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_SEND_CANCEL_DTL_IDS":ids},
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
	//check取消数量不能大于通知出库数量
	if(!checkCancelNum()){
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
		url: "advccancelsapp.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_SEND_CANCEL_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","advccancelsapp.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var jsonData = multiPackJsonData();
	var selRowId = getSelRowId();
	$.ajax({
		url: "advccancelsapp.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_SEND_CANCEL_ID":selRowId},
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


//返回
function gobacknew(){
   newGoBack("advccancelsapp.shtml?action=toFrame");
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
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
              ''
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(arrs[9]==null||arrs[9]==""||arrs[9]==0){
		SPCL_TECH_FLAG='无';
	}else{
		var f = "showPage('"+rownum+"')";
		SPCL_TECH_FLAG="<font style='color: red'>有</font> <input class='btn'  value='查看' onclick="+f+" type='button' />";
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='ADVC_SEND_CANCEL_DTL_ID"+rownum+"' json='ADVC_SEND_CANCEL_DTL_ID' name='ADVC_SEND_CANCEL_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input type="hidden" json="PRD_ID" id="PRD_ID'+rownum+'" value="'+arrs[2]+'"/><input  json="PRD_NO" size="8" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[3]+'"/>' +
		    '<img id="img'+rownum+'" align="absmiddl" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrd('+rownum+')"/></td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME"   id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display:none"><input  json="PRD_COLOR"   id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌" size="3" type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'"   name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="3" type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center" name="SPECIAL_FLAG_TD"><span id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</span> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CANCEL_NUM" id="CANCEL_NUM'+rownum+'" name="CANCEL_NUM'+rownum+'"  autocheck="true"  size="3" label="取消数量"  type="text"   mustinput="true"  inputtype="float"  maxlength="22"  value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  autocheck="true" label="单价" size="5"  type="text"      readonly       value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'"  autocheck="true" label="折扣率" size="3"    type="text"   inputtype="float"   readonly  mustinput="true"   valueType="2,2"   value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  autocheck="true" label="折后单价" size="5" type="text" mustinput="true"      inputtype="float"   readonly   valueType="8,2"   value="'+arrs[13]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT"    label="折后金额" readonly size="5" type="text" autocheck="true"  mustinput="true"  readonly  inputtype="float"  value="'+arrs[14]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'" readonly size="5"  autocheck="true" label="通知出库数量"  type="text"   inputtype="string"         maxlength="11"  value="'+arrs[18]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CANCEL_RSON" id="CANCEL_RSON'+rownum+'" name="CANCEL_RSON'+rownum+'"  autocheck="true" label="取消原因"  type="text"   inputtype="string"       maxlength="250"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<input  json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID'+rownum+'"  label="来源明细ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[17]+'"/>')
              ;
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
	InitFormValidator(0);
}

//货品通用选取
function selcPrd(rownum){
	var PRD_ID_TEMP = $("#PRD_ID"+rownum).val();
	var ADVC_SEND_REQ_ID = parent.topcontent.getADVC_SEND_REQ_ID();
	if(null == ADVC_SEND_REQ_ID || "" == ADVC_SEND_REQ_ID){
		parent.showErrorMsg("选选择'预订单发货申请编号'");
		return;
	}
    var inputs = $("#jsontb").find("input[json='PRD_ID']");
    var ids = "";
    inputs.each(function(){
    	var DTL_ID = $(this).parent().parent().find("input[json='ADVC_SEND_CANCEL_DTL_ID']").val();
    	var pId = $(this).val();
    	if(null == DTL_ID || "" == DTL_ID){
    		if(null != pId && "" != pId){
    			ids = ids +"'"+$(this).val()+"',";
    		}
    	}
    });
     
    var sql = " and PRD_ID not in(select distinct d.PRD_ID  from DRP_ADVC_SEND_CANCEL_DTL d where d.DEL_FLAG=0 " +
    " and d.ADVC_SEND_CANCEL_ID in("+
         " select t.ADVC_SEND_CANCEL_ID from DRP_ADVC_SEND_CANCEL t where t.ADVC_SEND_REQ_ID='"+ADVC_SEND_REQ_ID+"'))";
    
    if(null != ids && "" != ids){
       ids = ids.substr(0,ids.length-1);
       sql = sql + " and PRD_ID not in("+ids+") ";
    }
    
    
	$("#selectParams").val(" DEL_FLAG=0  and (CANCEL_FLAG is null or CANCEL_FLAG=0) and ADVC_SEND_REQ_ID='"+ADVC_SEND_REQ_ID+"' "+sql);
    selCommon("BS_77", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum
    	+",BRAND"+rownum+",STD_UNIT"+rownum+",CANCEL_NUM"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum+",DECT_AMOUNT"
    	+rownum+",FROM_BILL_DTL_ID"+rownum+",SPCL_TECH_ID"+rownum+",NOTICE_NUM"+rownum, "PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,NOTICE_NUM,PRICE,DECT_RATE,DECT_PRICE,DECT_AMOUNT,ADVC_SEND_REQ_DTL_ID,SPCL_TECH_ID,NOTICE_NUM", "selectParams");
   
    var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
    if(null != SPCL_TECH_ID && "" != SPCL_TECH_ID){
    	var f = "showPage('"+rownum+"')";
    	var SPCL_TECH_FLAG = "<font style='color: red'>有</font> <input class='btn'  value='查看' onclick="+f+"  type='button' />";
    	$("#SPECIAL_FLAG" + rownum).html(SPCL_TECH_FLAG);
    }
  

}

//check取消数量不能大于通知出库数量
function checkCancelNum(){
	var checkBoxs = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkBoxs.each(function(){
		var CANCEL_NUM = $(this).parent().parent().find("input[json='CANCEL_NUM']").val();
		var NOTICE_NUM = $(this).parent().parent().find("input[json='NOTICE_NUM']").val();
		CANCEL_NUM = parseInt(CANCEL_NUM);
		NOTICE_NUM = parseInt(NOTICE_NUM);
		if(isNaN(CANCEL_NUM)){
			CANCEL_NUM = 0;
		}
		if(isNaN(NOTICE_NUM)){
			NOTICE_NUM = 0;
		}
		
		if(CANCEL_NUM>NOTICE_NUM){
			parent.showErrorMsg("'取消数量'不能大于'通知出库数量'")
			flag = false;
			return flag;
		}
		
	});
	 
	return flag;
	
}
 

function showPage(rownum){
		var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
		var PRICE=$("#PRICE"+rownum).val();
		window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
 
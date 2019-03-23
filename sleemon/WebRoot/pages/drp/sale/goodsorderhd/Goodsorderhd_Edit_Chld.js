/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_Edit_Chld
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
			//
			if(!checkNum()){
				return;
			}
			childSave();
		}else{
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			allSave();
		}
	});
	
	//保存并提交
	$("#saveAndCommit").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("没有明细，不能提交!");
			return;
		}
		if(!checkNum()){
			return;
		}
		if(!checkOrderNum()){
		   return;
	    }
		toCommitAndSave();
 
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	
	
	var BILL_TYPE = parent.topcontent.getBILL_TYPE();
	if("赠品订货" == BILL_TYPE){
		$("#jsontb tr th[name='hideTdByBillType']").css("display","none"); 
		$("#jsontb tr td[name='hideTdByBillType']").css("display","none");
		$("#add").attr("disabled","disabled");
	}
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
	if(!checkNum()){
		return;
	}
	//校验明细的订货数量和交期,返利
	if(!checkOrderNum()){
		return;
	}
	
	//主表的 交期 取 明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
	var resultDate = getORDER_RECV_DATE();
    parent.topcontent.$("#ORDER_RECV_DATE").val(resultDate);
	var selRowId = getSelRowId();
	//获取主表json数据
	var parentData = parentData = parent.topcontent.siglePackJsonData();
	 
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	 //返利折扣
	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	$.ajax({
		url: "goodsorderhd.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"GOODS_ORDER_ID":selRowId,"REBATEDSCT":REBATEDSCT},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","goodsorderhd.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
 

//主表的 交期 取 明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
function getORDER_RECV_DATE(){
	var inputs = $("#jsontb").find("input[json='ORDER_RECV_DATE']");
	var array = [];
	var bigTime = "";
	var resultDate = "";
	for(var i=0;i<inputs.length;i++){
		var date = inputs[i].value;//$(inputs[i]).val()
		var arr = date.split("-");
        var starttime = new Date(arr[0], arr[1], arr[2]);
        var starttimes = starttime.getTime();
        if(null == bigTime || "" == bigTime){
        	 bigTime = starttimes;
        	 resultDate = date;
        }else if(bigTime>starttimes){
        	bigTime = starttimes;
        	resultDate = date;
        }
       
	}
	
	return resultDate;
}
/**
 * 
 * 保存并提交前 校验 明细和 
 * 渠道表里的 付款比例*订单总额>可用信用 alert（‘信用额度不够’）；
 * @return   
 */
 function toCommitAndSave(){
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
	
	//校验明细的订货数量和交期,返利
	if(!checkOrderNum()){
		return;
	}
	
	//主表的 交期 取 明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
	var resultDate = getORDER_RECV_DATE();
    parent.topcontent.$("#ORDER_RECV_DATE").val(resultDate);
    
	var selRowId = getSelRowId();
	var actionType = getActionType();
	//获取json数据
	var parentData = "";
	if("list" == actionType){//表示在一览页面
		
	}else{
		//获取json数据
	    parentData = parent.topcontent.siglePackJsonData();
	}
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	 btnDisable(["delete","save","add","back","saveAndCommit"]);
	 var inputs = $("#jsontb input[name='ORDER_AMOUNT']");
	 var ORDER_AMOUNT = 0;
	 inputs.each(function(){
		 var v = $(this).val();
		 ORDER_AMOUNT = ORDER_AMOUNT + Number(v);
	 });
	 
	 $.ajax({
		url: "goodsorderhd.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"ORDER_AMOUNT":ORDER_AMOUNT},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 doCommitSave(parentData,childData,selRowId);
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				btuMxRest();
			}
		}
	 });
	 
 }
 

 function doCommitSave(parentData,childData,selRowId){
	    //返利折扣
	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	$.ajax({
		url: "goodsorderhd.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"GOODS_ORDER_ID":selRowId,"REBATEDSCT":REBATEDSCT},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var GOODS_ORDER_ID = jsonResult.data.GOODS_ORDER_ID;
				//编辑页面 点击[保存并提交]，先跳转到一览页面 然后根据穿过来的参数GOODS_ORDER_ID 自动提交
				//防止 本页面点击提交后只刷上帧页面
				goFrame("goodsorderhd.shtml?action=toFrame&doCommitSave=true&GOODS_ORDER_ID="+GOODS_ORDER_ID);
			    //调用上帧的 提交
				//parent.topcontent.toFlow("1",GOODS_ORDER_ID);
				 
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
 }
 
 
 
 function btuMxRest(){
	 btnReset(["delete","save","add","back","saveAndCommit"]);
 }
 
 /* 格式化日期 */
Date.prototype.format = function(style){
var o = {
    "M+" : this.getMonth() + 1, //month
    "d+" : this.getDate(),      //day
    "h+" : this.getHours(),     //hour
    "m+" : this.getMinutes(),   //minute
    "s+" : this.getSeconds(),   //second
    "w+" : "天一二三四五六".charAt(this.getDay()),   //week
    "q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
    "S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(style)){
    style = style.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
}
for(var k in o){
    if(new RegExp("("+ k +")").test(style)){
      style = style.replace(RegExp.$1,
        RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    }
}
return style;
};

 //校验 订货数量
 function checkOrderNum(){
    var checkBox = $("#jsontb tr:gt(0) input:checked");
    var flag = true;
    //返利标记
	var IS_USE_REBATE = parent.topcontent.getIS_USE_REBATE();
    //返利折扣
	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	//返利额
	var REBATE_CURRT = parent.topcontent.getREBATE_CURRT();
	//alert(IS_USE_REBATE+"--"+REBATEDSCT+"--"+REBATE_CURRT);
	var total = 0;
	checkBox.each(function(){
		 var input = $(this).parent().parent().find("input[name='ORDER_NUM']").eq(0);
		 var num = input.val();
		 var label = input.attr("label");
		 if(num==0||num==null||num==""){
			parent.showErrorMsg("请输入'"+label+"'");
			input.focus();
			flag =  false;
			return false;
		}
		//计算返利
		if("1" == IS_USE_REBATE){
		    var PRICE = $(this).parent().parent().find("input[json='PRICE']").val();
			PRICE = parseFloat($.trim(PRICE));
			num = parseInt($.trim(num));
			if(!isNaN(PRICE) && !isNaN(num)){
				total = parseFloat(total) + PRICE*REBATEDSCT*num;
			}
			//alert(total+"-"+REBATE_CURRT);
//			放在提交的时候判断
//			if(total>REBATE_CURRT){
//				parent.showErrorMsg("返利金额不足，不能使用返利下单");
//				flag =  false;
//				return false;
//			}
		}
			 
		/*var inputDate = $(this).parent().parent().find("input[json='ORDER_RECV_DATE']").eq(0);
		var date1 = inputDate.val();
		var now = new Date().format("yyyy-MM-dd"); 
		var b = dateComplare(date1,now);
		if(!b){
			parent.showErrorMsg("'"+inputDate.attr("label")+"',不能在今天之前");
			flag =  false;
			return false;
		}*/
		
		
	});
	
	return flag;
 }
 
 
 

 //日期比较  date1=2014-06-01  
 // date2=2014-05-01  return  true
 function dateComplare(date1, date2) {
	if(null == date1 || "" == date1){
		return true;
	}
	if(null == date2 || "" == date2){
		return true;
	}
	date1 = date1.toString(); 
    var arr = date1.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    
    date2 = date2.toString(); 
    var arrs = date2.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();
    if (starttimes >= lktimes) {
        return true;
    } 
    
    return false;
     

}
 
 
//子表保存
function childSave(){
	if(!checkOrderNum()){
		return;
	}
	var selRowId = getSelRowId();
	//返利标记
	var IS_USE_REBATE = parent.topcontent.getIS_USE_REBATE();
	 //返利折扣
	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	
	//主表的 交期 取 明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
	var resultDate = getORDER_RECV_DATE();
	parent.topcontent.$("#ORDER_RECV_DATE"+selRowId).text(resultDate);
	
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "goodsorderhd.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"GOODS_ORDER_ID":selRowId,"ORDER_RECV_DATE":resultDate,"IS_USE_REBATE":IS_USE_REBATE,"REBATEDSCT":REBATEDSCT},
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
function gobacknew()
{
   newGoBack("goodsorderhd.shtml?action=toFrame");
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
	//如果是新增主子表页面并且没有选中使用返利给予提示
	var actionType=getActionType();//edit页面
	if("edit"==actionType){
		checkAdd();
	}
	var SPCL_TECH_FLAG;
	if(arrs[8]==null||arrs[8]==""||arrs[8]=="0"){
		SPECIAL_FLAG_TEXT='无';
	}else{
		SPECIAL_FLAG_TEXT="<span style='color: red'>有</span>"
	}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='GOODS_ORDER_DTL_ID' name='GOODS_ORDER_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="center" >'+arrs[25]+'</td>')
            .append('<td nowrap align="left" style="display:none;"><input type="text" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/></td>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text" size="8"  inputtype="string"   readonly  mustinput="true"     maxlength="30" onfocus="setRowAndCol('+rownum+',2)" value="'+arrs[2]+'"/>&nbsp;' +
            '<img id="img'+rownum+'" align="absmiddl" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrd('+rownum+')"/></td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  size="15" type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100" onfocus="setRowAndCol('+rownum+',3)"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" size="12"  type="text"   inputtype="string"  readonly       maxlength="50"  onfocus="setRowAndCol('+rownum+',4)" value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display: none;"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"   label="花号" size="12"  type="text"   inputtype="string"   readonly       maxlength="50" onfocus="setRowAndCol('+rownum+',5)" value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌" size="5" type="text"   inputtype="string" readonly    mustinput="true"     maxlength="50" onfocus="setRowAndCol('+rownum+',6)" value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位" size="3" type="text"   inputtype="string" readonly     mustinput="true"     maxlength="50" onfocus="setRowAndCol('+rownum+',7)" value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"  name="hideTdByBillType" id="" ><font id="SPECIAL_FLAG'+rownum+'">'+SPECIAL_FLAG_TEXT+'</font> <input name="spclTd" id="spclTd'+rownum+'" class="btn" value="编辑" onclick="url('+rownum+')"  type="button" onfocus="setRowAndCol('+rownum+',8)" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价" size="5" type="text"   inputtype="string"     readonly   maxlength="22" onfocus="setRowAndCol('+rownum+',9)" value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率"  size="2" type="text"   inputtype="string"    readonly    maxlength="6" onfocus="setRowAndCol('+rownum+',10)"  value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价" size="6"  type="text"   inputtype="string"   readonly     maxlength="11" onfocus="setRowAndCol('+rownum+',11)" value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="订货数量" size="3"  type="text"    mustinput="true" textType="int"  onkeyup="countTotal('+rownum+');"  onfocus="setRowAndCol('+rownum+',12)"      maxlength="8"  value="'+arrs[13]+'"/><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_RECV_DATE" id="ORDER_RECV_DATE'+rownum+'" name="ORDER_RECV_DATE"  autocheck="true" label="要求到货日期"  size="8"  readonly  type="text" inputtype="date" onfocus="setRowAndCol('+rownum+',13)"  value="'+arrs[20]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="VOLUME" id="VOLUME'+rownum+'" name="VOLUME"  autocheck="true" label="体积"  type="text"   inputtype="string" size="5"  readonly     maxlength="22" onfocus="setRowAndCol('+rownum+',14)" value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="TOTAL_VOLUME" id="TOTAL_VOLUME'+rownum+'" name="TOTAL_VOLUME"  autocheck="true" label="总体积"  type="text"  size="5" inputtype="string"   readonly     maxlength="22"  onfocus="setRowAndCol('+rownum+',15)" value="'+arrs[16]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_AMOUNT" id="ORDER_AMOUNT'+rownum+'" name="ORDER_AMOUNT"  autocheck="true" label="订货金额"  type="text" size="8"  inputtype="string"   readonly     maxlength="22" onfocus="setRowAndCol('+rownum+',16)" value="'+arrs[14]+'"/>&nbsp;</td>')
//            .append('<td nowrap align="left"><input  json="OLD_PRICE" id="OLD_PRICE'+rownum+'" name="OLD_PRICE'+rownum+'"  autocheck="true" label="原价格"  type="text"   inputtype="string"     readonly   maxlength="22" value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK"  autocheck="true" label="备注"  type="text"    inputtype="string"      maxlength="250" value="'+arrs[23]+'"/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[17]+'"/>')
            .append('<input  json="OLD_SPCL_TECH_ID" id="OLD_SPCL_TECH_ID'+rownum+'" name="OLD_SPCL_TECH_ID'+rownum+'"  label="原特殊工艺单ID" type="hidden" value="'+arrs[17]+'"/>')
            .append('<input  json="CREDIT_FREEZE_PRICE" id="CREDIT_FREEZE_PRICE'+rownum+'" name="CREDIT_FREEZE_PRICE'+rownum+'"  label="冻结单价" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="IS_NO_STAND_FLAG" id="IS_NO_STAND_FLAG'+rownum+'" name="IS_NO_STAND_FLAG'+rownum+'"  label="是否非标" type="hidden" value="'+arrs[19]+'"/>')
            .append('<input  json="TECH_MULT" id="TECH_MULT'+rownum+'" name="TECH_MULT'+rownum+'"  label="特殊工艺加价倍数" type="hidden" value="'+arrs[21]+'"/>')
            .append('<input  json="TECH_AMOUNT" id="TECH_AMOUNT'+rownum+'" name="TECH_AMOUNT'+rownum+'"  label="特殊工艺加价金额" type="hidden" value="'+arrs[22]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺标记" type="hidden" value="'+arrs[8]+'"/>')
            .append('<input  json="PRD_SUIT_FLAG" id="PRD_SUIT_FLAG'+rownum+'" name="PRD_SUIT_FLAG'+rownum+'"  label="套件标记" type="hidden" value="'+arrs[24]+'"/>')
            ;
	var REBATEFLAG=parent.topcontent.getREBATEFLAG();
	if(REBATEFLAG){
		$("#spclTd"+rownum).attr("disabled","disabled");
	}
	//货品套件标记 为1 不给编辑特殊工艺
    var PRD_SUIT_FLAG = arrs[24];
    var btuDisabled = "";
    if(1 == PRD_SUIT_FLAG || "1" == PRD_SUIT_FLAG ){
    	 $("#spclTd"+rownum).attr("disabled","disabled");
    }
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


//货品通用选取
//-- 0156143--Start--刘曰刚--2014-06-16//
//修改渠道折扣率获取渠道
function selcPrd(rownum){
	//渠道折扣率
    var DECT_RATE=$("#DECT_RATE").val();
    if(""==DECT_RATE||null==DECT_RATE){
    	parent.showErrorMsg("总部未设置您的货品价格，请联系总部！");
    	return;
    }
    DECT_RATE=isNaN(DECT_RATE)?0:parseFloat(DECT_RATE);
    
    //如果使用了返利 则折扣率展示为返利折扣
    //返利标记
	var IS_USE_REBATE = parent.topcontent.getIS_USE_REBATE();
	 //返利折扣
	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	if(1 == IS_USE_REBATE || "1" == IS_USE_REBATE){
		DECT_RATE = REBATEDSCT;
	}
    
	var PRD_ID_TEMP=$("#PRD_ID"+rownum).val();
	var AREA_ID = parent.topcontent.getAREA_ID();
	var sql="";
	var REBATEFLAG=parent.topcontent.getREBATEFLAG();
	if(REBATEFLAG){
//		var PRDNOS=$("#PRDNOS").val();
//		sql=" and PAR_PRD_NO in ("+PRDNOS+")"
		sql=" and IS_REBATE_FLAG =1 "
	}
	
	var BILL_TYPE = parent.topcontent.getBILL_TYPE();
	var params = "STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and IS_NO_STAND_FLAG=0 and COMM_FLAG=1";
	if("赠品订货" == BILL_TYPE){
		params = params+" and IS_CAN_FREE_FLAG=1 ";
	}
	
	$("#selectParams").val(params+sql);
    var rtnArr =  selCommon("BS_146", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum
    	+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",VOLUME"+rownum+",PRD_SUIT_FLAG"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE,VOLUME,PRD_SUIT_FLAG", "selectParams");
    multiSelCommonSet(rtnArr,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,VOLUME,PRD_SUIT_FLAG",rownum);
    var leg = rtnArr[0];
    for(var i=0;i<leg;i++){
    	//货品单价
    	var index = rownum+i;
	    var PRICE = $("#PRICE"+index).val();
	    PRICE = isNaN(PRICE)?0:parseFloat(PRICE);
	    //计算折后价
	    var DECT_PRICE = Math.round((isNaN(DECT_RATE*PRICE)?0:DECT_RATE*PRICE)*100)/100;
	    $("#DECT_RATE"+index).val(DECT_RATE);
	    $("#DECT_PRICE"+index).val(DECT_PRICE);
	    
	    //货品套件标记 为1 不给编辑特殊工艺
	    var PRD_SUIT_FLAG = $("#PRD_SUIT_FLAG"+index).val();
	    if(1 == PRD_SUIT_FLAG || "1" == PRD_SUIT_FLAG ){
	    	$("#spclTd"+index).attr("disabled","disabled");
	    }
    }
    
    var PRD_ID = $("#PRD_ID"+rownum).val();
    if(PRD_ID_TEMP!=PRD_ID){
    	$("#SPECIAL_FLAG"+rownum).html("无");
		$("#SPCL_TECH_ID"+rownum).val("");
		$("#ORDER_NUM"+rownum).val("");
		$("#ORDER_AMOUNT"+rownum).val("");
		$("#TOTAL_VOLUME"+rownum).val("");
    }
    paddingPrice();
  //-- 0156143--End--刘曰刚--2014-06-16//
    
    
   //计算折后单价
//   var AREA_ID = parent.topcontent.getAREA_ID();
//   var pId = $("#PRD_ID"+rownum).val();
//   //获取该货品的 折扣
//   var array = getRate(AREA_ID,pId);
//   $("#DECT_RATE"+rownum).val(array[0]);
//   $("#DECT_PRICE"+rownum).val(array[1]);

}
 

//查询 折扣率
function getRate(AREA_ID,pId){
	var array = new Array();
	$.ajax({
		async:false,
		url: "goodsorderhd.shtml?action=getRate",
		type:"POST",
		dataType:"text",
		data:{"AREA_ID":AREA_ID,"PRD_ID":pId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				array[0] = jsonResult.data.DECT_RATE;
				array[1] = jsonResult.data.DECT_PRICE;
				 
//			    if(null == rate || ""==rate){
//			    	var areaNo = jsonResult.data.AREA_NO;
//					var areaName = jsonResult.data.AREA_NAME;
//					parent.showErrorMsg("该用户所在区域("+areaName+")没有设置折扣率，请设置该区域折扣率");
//			    }else{
//			    	$("#rate").val(rate);
//			    	
//			    }
			}
		}
	});
	
	return array;
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

function countTotal(rownum){
	var price = $("#DECT_PRICE"+rownum).val();//折后单价
	if(null == price || "" == price ||"0"==price){
		price = $("#PRICE"+rownum).val(); //单价
		var DECT_RATE = $("#DECT_RATE"+rownum).val(); //折扣率
		price = FloatMul(price,DECT_RATE);
		$("#DECT_PRICE"+rownum).val(price);
	}
	price = $.trim(price);
	var amount = $("#ORDER_NUM"+rownum).val();
	if(null == amount){
		return ;
	}
	amount = $.trim(amount);
	var v = FloatMul(price,amount);
	if(!isNaN(v)){
		$("#ORDER_AMOUNT"+rownum).val(v);
		var VOLUME = $("#VOLUME"+rownum).val();
		VOLUME = FloatMul(VOLUME,amount);
		$("#TOTAL_VOLUME"+rownum).val(VOLUME);
	}else{
		$("#TOTAL_VOLUME"+rownum).val("");
	}
	
	
}

function url(rownum){
		var PRD_ID = $("#PRD_ID"+rownum).val();
		var CHANN_ID = $("#CHANN_ID").val();
		var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
		var SPCL_TECH_ID_OLD = SPCL_TECH_ID;
		var ORDER_NUM = $("#ORDER_NUM"+rownum).val();
		var PRICE = $("#PRICE"+rownum).val();
		var url = "techorderManager.shtml?action=toFrame&filterSpclFlag=1&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_GOODS_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+PRICE;
		var data = window.showModalDialog(url,window,"dialogwidth=800px; dialogheight=700px; status=no");
		if(null == data){
			return;
		}
		
		var SPCL_TECH_FLAG = data.SPCL_TECH_FLAG;//2 是有备注  就是非标订单
		var SPCL_TECH_ID = data.SPCL_TECH_ID;
		if(SPCL_TECH_FLAG != 0){
			$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span>");
		}else{
			$("#SPECIAL_FLAG"+rownum).html("无");
		}
		$("#SPCL_TECH_FLAG"+rownum).val(SPCL_TECH_FLAG);
		$("#SPCL_TECH_ID"+rownum).val(SPCL_TECH_ID);
	    var PRICE = data.PRICE;//单价
		var DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
		$("#PRICE"+rownum).val(PRICE);//单价
		$("#DECT_PRICE"+rownum).val(DECT_PRICE);//折后单价
    	var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
    	$("#DECT_AMOUNT"+rownum).val(DECT_AMOUNT);
    	var ORDER_NUM=$("#ORDER_NUM"+rownum).val();
	    ORDER_NUM=isNaN(ORDER_NUM)?0:parseFloat(ORDER_NUM);
	    var ORDER_AMOUNT=Math.round((isNaN(ORDER_NUM*DECT_PRICE)?0:ORDER_NUM*DECT_PRICE)*100)/100;
	    $("#ORDER_AMOUNT"+rownum).val(ORDER_AMOUNT);
		var IS_NO_STAND_FLAG = "0";//标准
		if(SPCL_TECH_FLAG == 2){
			IS_NO_STAND_FLAG = "1";//非标
		} 
		 
		$("#IS_NO_STAND_FLAG"+rownum).val(IS_NO_STAND_FLAG);
//		countTotal(rownum);
		$("#DECT_PRICE"+rownum).parent().parent().find("input[type='checkbox']").attr("checked","checked");
 
		var GOODS_ORDER_DTL_ID = $("#DECT_PRICE"+rownum).parent().parent().find("input[json='GOODS_ORDER_DTL_ID']").val();
		if(null == GOODS_ORDER_DTL_ID || "" == GOODS_ORDER_DTL_ID){
			return;
		}
		var jsonData = "[{'GOODS_ORDER_DTL_ID':'"+GOODS_ORDER_DTL_ID+"','PRICE':'"+PRICE+"','DECT_RATE':'"+DECT_RATE+"','DECT_PRICE':'"+DECT_PRICE+"','ORDER_NUM':'"+ORDER_NUM+"','ORDER_AMOUNT':'"+ORDER_AMOUNT+"','IS_NO_STAND_FLAG':'"+IS_NO_STAND_FLAG+"','SPCL_TECH_ID':'"+SPCL_TECH_ID+"'}]"
		if(null != GOODS_ORDER_DTL_ID && "" != GOODS_ORDER_DTL_ID){
			updateChildPrice(jsonData);
		}
		
}

//修改过 特殊工艺点击保存的时候 页面要回填相关字段 同时更新数据库相关字段
function updateChildPrice(jsonData){
	//返利标记
	var IS_USE_REBATE = parent.topcontent.getIS_USE_REBATE();
	 //返利折扣
	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	$.ajax({
		url: "goodsorderhd.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"IS_USE_REBATE":IS_USE_REBATE,"REBATEDSCT":REBATEDSCT},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
//			if(jsonResult.success===true){
//				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_1");
//			}else{
//				parent.showErrorMsg(utf8Decode(jsonResult.messages));
//			}
		}
	});
}
function delDtl(){
	$("#jsontb tr:gt(0)").remove();
}
function checkAdd(){
	var addFlag=parent.topcontent.$("#addFlag").val();//主表id
	var flag=$("#flag").val();//addRow方法是否第一次调用
	var REBATEFLAG=parent.topcontent.getREBATEFLAG();//是否使用返利
	var havaAreaSerId=parent.topcontent.$("#havaAreaSerId").val();
//	if((""==addFlag||null==addFlag)&&"1"==flag&&"0"!=havaAreaSerId&&!REBATEFLAG){
//		alert("如使用返利请先点击是否使用返利，再选择货品！");
//	}
	$("#flag").val("0");
}
//验证数量为数字
function checkNum(){
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
function paddingPrice(){
		var checkBox = $("#jsontb tr:gt(0)  input[type='checkbox']:checked");
		checkBox.each(function(){
			var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
			if(null==DECT_RATE||""==DECT_RATE){
				DECT_RATE=1;
				$(this).parent().parent().find("input[name='DECT_RATE']").val(1);
			}
			if(1==DECT_RATE){
				var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
				$(this).parent().parent().find("input[name='DECT_PRICE']").val(PRICE);//折后单价
			}
		});
	}
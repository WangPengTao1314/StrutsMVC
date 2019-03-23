/**
 * @prjName:喜临门营销平台
 * @fileName:Saleorder_Edit_Chld
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
		var checkBox = $("#jsontb tr:gt(0)  input[name='SALE_ORDER_DTL_ID']:checked");
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
			var checkBox = $("#jsontb tr:gt(0)  input[name='SALE_ORDER_DTL_ID']:checked");
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
			$("#jsontb input[name='SALE_ORDER_DTL_ID']").attr("checked","true");
		}else{
			$("#jsontb input[name='SALE_ORDER_DTL_ID']").removeAttr("checked");
		}
		var BILL_TYPE=parent.topcontent.getBILL_TYPE();
		if("赠品订货" == BILL_TYPE){
			$("#jsontb tr th[name='hideTdByBillType']").css("display","none"); 
			$("#jsontb tr td[name='hideTdByBillType']").css("display","none");
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
	if($("#jsontb tr:gt(0) input[name='SALE_ORDER_DTL_ID']:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "saleorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"SALE_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","saleorder.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	
	var BILL_TYPE=parent.topcontent.getBILL_TYPE();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "saleorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"SALE_ORDER_ID":selRowId,"BILL_TYPE":BILL_TYPE},
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
	var checkBox = $("#jsontb tr:gt(0)  input[name='SALE_ORDER_DTL_ID']:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "saleorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids},
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
   newGoBack("saleorder.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[name='SALE_ORDER_DTL_ID']:checked").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) input[name='SALE_ORDER_DTL_ID']:checked").attr("checked","true");
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
	//样式行
	var rownum = $("#jsontb tr").length;
//	var SPCL_TECH_FLAG;
//	if(arrs[18]==null||arrs[18]==""||arrs[18]=="0"){
//		SPECIAL_FLAG_TEXT='无';
//	}else{
//		SPECIAL_FLAG_TEXT="<span style='color: red'>有</span>"
//	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='SALE_ORDER_DTL_ID' name='SALE_ORDER_DTL_ID' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left" style="display:none;"><input type="text" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/></td>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  autocheck="true" label="货品编号"  type="text" size="8"  inputtype="string"   readonly  mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;' +
            '<img id="img'+rownum+'" align="absmiddl" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcPrd('+rownum+')"/></td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称"  size="15" type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100"   value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号" size="12"  type="text"   inputtype="string"  readonly       maxlength="50"   value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display: none;"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR"   label="花号" size="12"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" label="品牌" size="5" type="text"   inputtype="string" readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT"  autocheck="true" label="标准单位" size="3" type="text"   inputtype="string" readonly     mustinput="true"     maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input type="checkbox" label="是否抵库"  name="IS_BACKUP_FLAG" json="IS_BACKUP_FLAG" id="IS_BACKUP_FLAG'+rownum+'" onclick="checkIsFB('+rownum+');" />&nbsp;</td>')
//            .append('<td nowrap align="left"  name="hideTdByBillType" id="" ><font id="SPECIAL_FLAG'+rownum+'">'+SPECIAL_FLAG_TEXT+'</font> <input name="spclTd" id="spclTd'+rownum+'" class="btn" value="编辑" onclick="url('+rownum+')"  type="button"  />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价" size="5" type="text"   inputtype="string"     readonly   maxlength="22"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率"  size="2" type="text"   inputtype="string"    readonly    maxlength="6"   value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价" size="6"  type="text"   inputtype="string"   readonly     maxlength="11"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="订货数量" size="3"  onkeyup="countTotal('+rownum+');" type="text"    mustinput="true" textType="int"        maxlength="8"  value="'+arrs[11]+'"/><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="VOLUME" id="VOLUME'+rownum+'" name="VOLUME"  autocheck="true" label="体积"  type="text"   inputtype="string" size="5"  readonly     maxlength="22"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_AMOUNT" id="ORDER_AMOUNT'+rownum+'" name="ORDER_AMOUNT"  autocheck="true" label="订货金额"  type="text" size="8"  inputtype="string"   readonly     maxlength="22"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[17]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺标记" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="IS_NO_STAND_FLAG" id="IS_NO_STAND_FLAG'+rownum+'" name="IS_NO_STAND_FLAG'+rownum+'"  label="是否非标" type="hidden" value="'+arrs[20]+'"/>')
            ;
	var BILL_TYPE=parent.topcontent.getBILL_TYPE();
//	if("赠品订货"==BILL_TYPE){
//		$("#spclTd"+rownum).attr("disabled","disabled");
//	}
	if(arrs[19]==1){
		$("#IS_BACKUP_FLAG"+rownum).prop("checked",true);
	}
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[name='SALE_ORDER_DTL_ID']:checked").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}

function selcPrd(rownum){
	var CHANN_ID=parent.topcontent.getCHANN_ID();
	if(""==CHANN_ID||null==CHANN_ID){
		parent.showErrorMsg("请选择收货方信息！");
    	return;
	}
	var BILL_TYPE=parent.topcontent.getBILL_TYPE();
	if(""==BILL_TYPE||null==BILL_TYPE){
		parent.showErrorMsg("请选择单据类型！");
    	return;
	}
	$.ajax({
		url: "saleorder.shtml?action=getChannDECT_RATE",
		type:"POST",
		dataType:"text",
		data:{"CHANN_ID":CHANN_ID,"BILL_TYPE":BILL_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var data  = jsonResult.data;
				var DECT_RATE= data.DECT_RATE*1.0;
				if(""==DECT_RATE||null==DECT_RATE){
			    	parent.showErrorMsg("总部未设置所选收货方的折扣，请设置后进行操作！");
			    	return;
			    }
				var sql;
				if(BILL_TYPE=="赠品订货"){
					sql="IS_CAN_FREE_FLAG=1";
				}else{
					sql="1=1";
				}
				$("#selectParams").val(" STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and IS_NO_STAND_FLAG=0 and COMM_FLAG=1 and  "+sql);
			    var rtnArr =  selCommon("BS_65", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum
			    	+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum+",VOLUME"+rownum, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE,VOLUME", "selectParams");
			    multiSelCommonSet(rtnArr,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,VOLUME",rownum);
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
			    }
			    paddingPrice();
			    $("#jsontb input[name='SALE_ORDER_DTL_ID']").attr("checked","true");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
  //-- 0156143--End--刘曰刚--2014-06-16//

}
function paddingPrice(){
		var checkBox = $("#jsontb tr:gt(0)  input[name='SALE_ORDER_DTL_ID']:checked");
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
function delDtl(){
	$("#jsontb tr:gt(0)").remove();
}
function url(rownum){
		var PRD_ID = $("#PRD_ID"+rownum).val();
		var CHANN_ID = parent.topcontent.getCHANN_ID();
		var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
		var ORDER_NUM = $("#ORDER_NUM"+rownum).val();
		var PRICE = $("#PRICE"+rownum).val();
		var url = "techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_GOODS_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+PRICE;
		var data = window.showModalDialog(url,window,"dialogwidth=800px; dialogheight=600px; status=no");
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
		$("#DECT_PRICE"+rownum).parent().parent().find("input[name='SALE_ORDER_DTL_ID']:checked").attr("checked","checked");
 
		var SALE_ORDER_DTL_ID = $("#DECT_PRICE"+rownum).parent().parent().find("input[json='SALE_ORDER_DTL_ID']").val();
		if(null == SALE_ORDER_DTL_ID || "" == SALE_ORDER_DTL_ID){
			return;
		}
		var jsonData = "[{'SALE_ORDER_DTL_ID':'"+SALE_ORDER_DTL_ID+"','PRICE':'"+PRICE+"','DECT_RATE':'"+DECT_RATE+"','DECT_PRICE':'"+DECT_PRICE+"','ORDER_NUM':'"+ORDER_NUM+"','ORDER_AMOUNT':'"+ORDER_AMOUNT+"','IS_NO_STAND_FLAG':'"+IS_NO_STAND_FLAG+"','SPCL_TECH_ID':'"+SPCL_TECH_ID+"'}]"
		if(null != SALE_ORDER_DTL_ID && "" != SALE_ORDER_DTL_ID){
			updateChildPrice(jsonData);
		}
}
//修改过 特殊工艺点击保存的时候 页面要回填相关字段 同时更新数据库相关字段
function updateChildPrice(jsonData){
//	//返利标记
//	var IS_USE_REBATE = parent.topcontent.getIS_USE_REBATE();
//	 //返利折扣
//	var REBATEDSCT = parent.topcontent.getREBATEDSCT();
	$.ajax({
		url: "saleorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
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
//check是否非标 非标不能备货
function checkIsFB(id){
	var IS_NO_STAND_FLAG=$("#IS_NO_STAND_FLAG"+id).val();
	 if("1" == IS_NO_STAND_FLAG){
		 parent.showErrorMsg("非标货品不能抵库");
		 if($("#IS_BACKUP_FLAG"+id).attr("checked")){
			 $("#IS_BACKUP_FLAG"+id).removeAttr("checked");
		 }
	 }
}
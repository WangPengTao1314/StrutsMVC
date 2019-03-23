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
		//下帧改变应收金额时算出总金额赋值到上贞应收总金额
        countTotalMoney();
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
	
	//明细校验
	if(!checkchild()){
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
		url: "erpadvcorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ADVC_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","erpadvcorder.shtml?action=toFrame");
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
		url: "erpadvcorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_ORDER_ID":selRowId},
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
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "erpadvcorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_DTL_IDS":ids,"ADVC_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
			    //下帧改变应收金额时算出总金额赋值到上贞应收总金额
                countTotalMoney();
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
		newGoBack("erpadvcorder.shtml?action=toFrame");
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
	var SPECIAL_FLAG;
	if(arrs[13]==null||arrs[13]==""||arrs[13]=="0"){
		SPECIAL_FLAG='无';
	}else{
		SPECIAL_FLAG="<span style='color: red'>有</span>"
	}
	 
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='ADVC_ORDER_DTL_ID' id='ADVC_ORDER_DTL_ID"+rownum+"' name='ADVC_ORDER_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号" size="8"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;' +
            "<img name='imgTab' align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID" type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[2]+'"/>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><select  json="PRD_TYPE" id="PRD_TYPE'+rownum+'" name="PRD_TYPE" mustinput="true"  autocheck="true" label="货品类型" inputtype="string"    value="'+arrs[14]+'"></select>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text" size="10"  readonly inputtype="string"    maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text" size="3"  inputtype="string"   readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text" size="3"  inputtype="string"   readonly       maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center" id="spclTd'+rownum+'"><font id="SPECIAL_FLAG'+rownum+'">'+SPECIAL_FLAG+'</font> <input class="btn"  value="编辑" onclick="editSpec('+rownum+')"  type="button" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE"     id="PRICE'+rownum+'"       name="PRICE"      autocheck="true"  inputtype=""  mustinput="true" texttype="float" valueType="8,2" label="单价"  size="5" type="text" onkeyup="countPRICE('+rownum+')"    value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'"   name="DECT_RATE"  mustinput="true" texttype="float" valueType="8,2" label="折扣率" size="2" onkeyup="count('+rownum+')" type="text"   value="'+arrs[9]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE" mustinput="true" texttype="float" valueType="8,2" label="折后单价" size="5"  type="text" onkeyup="countDECT_RATE('+rownum+')"   value="'+arrs[10]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'"   name="ORDER_NUM"    autocheck="true"  mustinput="true"  textType="float" valueType="8,2" label="订货数量" onkeyup="countORDER_NUM('+rownum+')" type="text" size="3" maxlength="11"  value="'+arrs[11]+'" />&nbsp;</td>')

            .append('<td nowrap align="left"><input  json="ORDER_RECV_DATE" id="ORDER_RECV_DATE'+rownum+'" name="ORDER_RECV_DATE"  autocheck="true" label="交货日期"  mustinput="true"  onclick="SelectDate();" size="8"  readonly  type="text" inputtype="date"   value="'+arrs[15]+'" />&nbsp;' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE'+rownum+');"></td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" name="PAYABLE_AMOUNT"  autocheck="true" label="应收金额"  type="text" size="7" onkeyup="countDECT_PRICE('+rownum+')"    value="'+arrs[12]+'" />&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[13]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="特殊工艺标记" type="hidden" value="'+arrs[16]+'"/>')
              ;
	var PRD_TYPE = arrs[14];
	if(null == PRD_TYPE || "" == PRD_TYPE){
		PRD_TYPE = "销售货品";
	}
	SelDictShow("PRD_TYPE"+rownum, "BS_53", PRD_TYPE, "");
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[name='ADVC_ORDER_DTL_ID']").attr("checked","checked");
	});
	//form校验设置
    trCheckFLoatInit($("#jsontb tr:gt(0) input"))
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
	 
}
 

function urlShow(rownum){
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

//货品通用选取
function selcPrd(rownum){
	 var PRD_ID_TEMP = $("#PRD_ID"+rownum).val();
	 var PRICE = $("#PRICE"+rownum).val();
	 if(0 == PRICE){
		 $("#PRICE"+rownum).val("");
	 }
	var LEDGER_ID = $("#LEDGER_ID").val();
	$("#selectParams").val(" STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and IS_NO_STAND_FLAG=0 and COMM_FLAG=1 ");
	
     var obj = selCommon("BS_65", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",PRICE"+rownum, 
    	 				"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE", "selectParams");
     rtnArr = multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE",rownum);
     var PRD_ID = $("#PRD_ID"+rownum).val();
     if(PRD_ID_TEMP != PRD_ID){
    	$("#SPECIAL_FLAG"+rownum).html("无");
		$("#SPCL_TECH_ID"+rownum).val("");
		$("#DECT_RATE"+rownum).val("1");
		$("#IS_FREEZE_FLAG"+rownum).prop("checked",false);
		$("#ORDER_NUM"+rownum).val("");
		$("#FREEZE_NUM"+rownum).val("0");
		$("#FREEZE_STORE_ID"+rownum).val("");
		$("#FREEZE_STORE_NO"+rownum).val("");
		$("#FREEZE_STORE_NAME"+rownum).val("");
		$("#PAYABLE_AMOUNT"+rownum).val("");
		$("#DECT_PRICE"+rownum).val($("#PRICE"+rownum).val());
     }
     paddingPrice();
}

function paddingPrice(){
	var checkBox = $("#jsontb tr:gt(0)  input[name='ADVC_ORDER_DTL_ID']:checked");
	checkBox.each(function(){
		var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
		if(1==DECT_RATE){
			var PRICE=$(this).parent().parent().find("input[name='PRICE']").val();//单价
			$(this).parent().parent().find("input[name='DECT_PRICE']").val(PRICE);//折后单价
		}
	});
};



//当键盘按键松开时触发事件
//输入折扣率时，计算折后单价、应收金额
 function count(rownum){
	var temp_PRICE = $("#PRICE"+rownum).val();//单价
	var temp_DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
	var temp_ORDER_NUM = $("#ORDER_NUM"+rownum).val();//订货数量
	var PRD_TYPE = $("#PRD_TYPE"+rownum).val();
	if(null == PRD_TYPE || "" == PRD_TYPE){
		$("#DECT_RATE"+rownum).val("");
		parent.showErrorMsg("请选择货品类型");
		return;
	}
 
	//判断输入是否为数字，如果部为数字则默认为0
	var DECT_RATE = FloatFomat(temp_DECT_RATE);//折扣率
	var PRICE = FloatFomat(temp_PRICE);//单价
	var ORDER_NUM = FloatFomat(temp_ORDER_NUM);//订货数量
	var DECT_PRICE = Math.round(FloatMul(PRICE,DECT_RATE)*100)/100;//计算折后单价，保留2位小数
	var PAYABLE_AMOUNT = Math.round(FloatMul(DECT_PRICE,ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
	$("#DECT_PRICE"+rownum).val(DECT_PRICE);
	$("#PAYABLE_AMOUNT"+rownum).val(PAYABLE_AMOUNT);
	countTotalMoney();
	 
}
 //输入单价时，计算折后单价、应收金额
function countPRICE(rownum){
	var temp_PRICE = $("#PRICE"+rownum).val();//单价
	var temp_DECT_RATE = $("#DECT_RATE"+rownum).val();//折扣率
	var temp_ORDER_NUM = $("#ORDER_NUM"+rownum).val();//订货数量
	//判断输入是否为数字，如果部为数字则默认为0
	var DECT_RATE = FloatFomat(temp_DECT_RATE);//折扣率
	var PRICE = FloatFomat(temp_PRICE);//单价
	var ORDER_NUM = FloatFomat(temp_ORDER_NUM);//订货数量
	var DECT_PRICE = Math.round(FloatMul(PRICE,DECT_RATE)*100)/100;//计算折后单价，保留2位小数
	var PAYABLE_AMOUNT = Math.round(FloatMul(DECT_PRICE,ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
	$("#DECT_PRICE"+rownum).val(DECT_PRICE);
	$("#PAYABLE_AMOUNT"+rownum).val(PAYABLE_AMOUNT);
	countTotalMoney();
	 
}


//输入折后单价时，计算折扣率和应收金额
function countDECT_RATE(rownum){
	var DECT_PRICE = FloatFomat($("#DECT_PRICE"+rownum).val());//折后单价
	var PRICE = FloatFomat($("#PRICE"+rownum).val()); //单价
	var ORDER_NUM = FloatFomat($("#ORDER_NUM"+rownum).val());//订货数量
	var DECT_RATE = FloatFomat($("#DECT_RATE"+rownum).val());//折扣率
	var PAYABLE_AMOUNT = Math.round(FloatMul(DECT_PRICE,ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
	$("#PAYABLE_AMOUNT"+rownum).val(PAYABLE_AMOUNT);
	
	if(null == PRICE || "" == PRICE){
		return;
	}
	if(null == DECT_RATE || "" == DECT_RATE){
		return;
	}
	var DECT_RATE = Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;//计算折扣率，保留2位小数
	$("#DECT_RATE"+rownum).val(DECT_RATE);
	countTotalMoney();
}
	

//输入订货数量计算应收金额
function countORDER_NUM(rownum){
 
	var DECT_PRICE = FloatFomat($("#DECT_PRICE"+rownum).val());//折后单价
	var ORDER_NUM = FloatFomat($("#ORDER_NUM"+rownum).val());//订货数量
    var PAYABLE_AMOUNT = Math.round(FloatMul(DECT_PRICE,ORDER_NUM)*100)/100;//计算应收金额，保留2位小数
	$("#PAYABLE_AMOUNT"+rownum).val(PAYABLE_AMOUNT);
	countTotalMoney();
}
	

//下帧改变应收金额时算出总金额赋值到上贞应收总金额
function countTotalMoney(){
	var actionType = getActionType();
	if("list" == actionType){
		return;
	} 
	var inputs = $("input[name='PAYABLE_AMOUNT']");
	var total=0;
	inputs.each(function(){
		var v = FloatFomat($(this).val());
		var PAYABLE_AMOUNT = isNaN(v)?0:parseFloat(v);
		total = total+PAYABLE_AMOUNT;
	});
	total = Math.round(total*100)/100;//计算应收金额，保留2位小数
	parent.topcontent.setPAYABLE_AMOUNT(total);
}
	
function editSpec(rownum){
	var PRD_ID = $("#PRD_ID"+rownum).val();
	if(null == PRD_ID || "" == PRD_ID){
		parent.showErrorMsg("请先选择货品");
		return;
	}
	var CHANN_ID = $("#CHANN_ID").val();
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	var ADVC_ORDER_DTL_ID = $("#ADVC_ORDER_DTL_ID"+rownum).val();
	//不验证渠道折扣
	var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID+"&citeUrl=editTechWithOutPrice&check=N",window,"dialogwidth=800px; dialogheight=600px; status=no");
	//验证渠道折扣
	//var data=window.showModalDialog("techorderManager.shtml?action=editTechWithOutPrice&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	if(null==data){
		return;
	}
	if(data.SPCL_TECH_FLAG>0){
		$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span>");
	}else{
		$("#SPECIAL_FLAG"+rownum).html("无");
	}
	$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
	$("#SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
}


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



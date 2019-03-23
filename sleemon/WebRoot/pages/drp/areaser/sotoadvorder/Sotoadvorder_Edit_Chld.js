
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
	var REBATEFLAG=$("#REBATEFLAG").val();
	if("1"==REBATEFLAG){
		$("#rebate").prop("checked",true);
	}
    //form校验设置
	InitFormValidator(0);
	//点击关闭按钮关闭页面
	$("#close").click(function () {
		window.close();
	});
	//查看库存
	$("#select").click(function () {
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			var FROM_BILL_DTL_ID = $(this).parent().parent().find("input[json='FROM_BILL_DTL_ID']").val();
			if("" != FROM_BILL_DTL_ID){
				ids = ids+"'"+FROM_BILL_DTL_ID+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		window.open('sotoadvorder.shtml?action=toListPro&FROM_BILL_DTL_IDS='+ids,'库存','height=200, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		
		showConfirm("您确认要删除吗","deleteChild();","N");
	});
	
	$("#create").click(function(){
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
			if(!checkCarVol()){
				 
				return;
		    }
			childSave();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
		getVolumFrom();
	});
	var PrdNos=$("#PrdNos").val();
	if(""!=PrdNos&&null!=PrdNos){
		parent.showErrorMsg("编号为"+PrdNos+"的货品没有设置渠道折扣率，不予显示");
	}
	
	
	$("#firstTable").hover(
		function() {
		    $("#carType option[text='--请选择--']").remove();
	    }, 
	   function() {
		  $("#carType option[text='--请选择--']").remove();
	    }
	);
		
	
	
});



function deleteChild(){
	//获取所有选中的记录
	var ids = "";
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		if("" != $(this).val()){
			ids = ids+"'"+$(this).val()+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
	checkBox.parent().parent().remove();
	//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
	setFloatPostion();
}



//子表保存
function childSave(){
	if(!judgeRebate()){
		return;
	}
	var jsonData = multiPackJsonData();
	var SALE_ORDER_IDS=$("#SALE_ORDER_IDS").val();
	var ORDER_CHANN_ID=$("#ORDER_CHANN_ID").val();
	var ORDER_CHANN_NO=$("#ORDER_CHANN_NO").val();
	var ORDER_CHANN_NAME=$("#ORDER_CHANN_NAME").val();
	var RECV_CHANN_ID=$("#RECV_CHANN_ID").val();
	var RECV_CHANN_NO=$("#RECV_CHANN_NO").val();
	var RECV_CHANN_NAME=$("#RECV_CHANN_NAME").val();
	var RECV_ADDR_NO=$("#RECV_ADDR_NO").val();
	var RECV_ADDR=$("#RECV_ADDR").val();
	var REBATEFLAG=$("#REBATEFLAG").val();
	var REBATEDSCT=$("#REBATEDSCT").val();
	$.ajax({
		url: "sotoadvorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"SALE_ORDER_IDS":SALE_ORDER_IDS,
										"ORDER_CHANN_ID":ORDER_CHANN_ID,
										"ORDER_CHANN_NO":ORDER_CHANN_NO,
										"ORDER_CHANN_NAME":ORDER_CHANN_NAME,
										"RECV_CHANN_ID":RECV_CHANN_ID,
										"RECV_CHANN_NO":RECV_CHANN_NO,
										"RECV_CHANN_NAME":RECV_CHANN_NAME,
										"RECV_ADDR_NO":RECV_ADDR_NO,
										"RECV_ADDR":RECV_ADDR,
										"REBATEFLAG":REBATEFLAG,
										"REBATEDSCT":REBATEDSCT
		},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				alert("生成订货订单成功");
				showMsgPanel("生成订货订单成功");
				$("#YT_MSG_BTN_OK").click(function(){
					window.close();
				});
				
				//$("#pageForm").attr("action","sotoadvorder.shtml?action=toChild&SALE_ORDER_IDS="+utf8(SALE_ORDER_IDS))
				window.opener.parent.topcontent.listRef();
				window.opener.parent.bottomcontent.chldListRef();
				
				
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
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
              '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(arrs[7]==null||arrs[7]==""||arrs[7]==0){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	//0158252--Start--刘曰刚--2014-07-02--//
	//修改使用返利时页面显示折扣率和折后金额为返利折扣
	var REBATEFLAG=$("#REBATEFLAG").val();
	var REBATEDSCT=$("#REBATEDSCT").val();
	var DECT_RATE=0;
	var DECT_PRICE=0;
	if("1"==REBATEFLAG){
		DECT_RATE=REBATEDSCT;
		DECT_PRICE=Math.round((isNaN(arrs[8]*REBATEDSCT)?0:arrs[8]*REBATEDSCT)*100)/100;
	}else{
		DECT_RATE=arrs[9];
		DECT_PRICE=arrs[10];
	}
	//0158252--End--刘曰刚--2014-07-02--//
	var temp_ORDER_NUM=arrs[11]-arrs[19];
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)' onclick='getVolumFrom();' ></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' id='PRD_ID"+rownum+"' json='PRD_ID' name='PRD_ID"+rownum+"' onclick='getVolumFrom()'  value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NO"  id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" label="货品编号"  type="text"  mustinput="true"   READONLY  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" style="width:100px" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_COLOR" style="width:40px" id="PRD_COLOR'+rownum+'" name="PRD_COLOR"  autocheck="true" label="花号"  type="hidden"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[4]+'"/>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND"  autocheck="true" label="品牌"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" style="width:40px" name="STD_UNIT"  autocheck="true" label="标准单位"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价"   type="text"      readonly   style="width:60px"    value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" label="折扣率" style="width:30px"   type="text"   inputtype="float"   readonly  mustinput="true"   valueType="2,2"   value="'+DECT_RATE+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折后单价"  type="text" mustinput="true" style="width:60px"    inputtype="float"   readonly   valueType="8,2"   value="'+DECT_PRICE+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="temp_ORDER_NUM" id="temp_ORDER_NUM'+rownum+'" name="temp_ORDER_NUM"  autocheck="true" style="width:55px" label="订货数量" readonly type="text"   mustinput="true"   inputtype="float"        maxlength="22"  value="'+temp_ORDER_NUM+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  id="num'+rownum+'" name="num"  style="width:50px" label="可用库存" readonly type="text" autocheck="true"  mustinput="true"  readonly  inputtype="float"  value="'+arrs[15]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PLE_REP" id="PLE_REP'+rownum+'" name="PLE_REP"  autocheck="true" label="抵库数量" style="width:50px"  type="text"   inputtype="float"    onkeyup="countPRA_ORDER_NUM('+rownum+')"       maxlength="22" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="实际订货数量" readonly style="width:60px" type="text" mustinput="true"    inputtype="float"   maxlength="22" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT"  autocheck="true" label="订货金额" style="width:60px"  type="text" mustinput="true"    readonly inputtype="float"      valueType="12,2"  />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK"  autocheck="true" label="备注"  type="text"   inputtype="string"  style="width:80px"       maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<input  json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID"  label="销售订单明细ID" type="text" value="'+arrs[14]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[16]+'"/>')
            .append('<input  json="IS_NO_STAND_FLAG" id="IS_NO_STAND_FLAG'+rownum+'" name="IS_NO_STAND_FLAG"  label="是否非标" type="hidden" value="'+arrs[17]+'"/>')
            .append('<input  json="VOLUME" id="VOLUME'+rownum+'" name="VOLUME"  label="体积" type="hidden" value="'+arrs[18]+'"/>')
            .append('<input  json="TOTAL_VOLUME" id="TOTAL_VOLUME'+rownum+'" name="TOTAL_VOLUME"  label="体积" type="hidden" />')
              ;
	countFill(rownum);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}

    //新增数据后计算订货金额，折后单价,实际订货数量,总体积
    function countFill(i){
    	var temp_PRICE=$("#PRICE"+i).val();//单价
    	var temp_VOLUME=$("#VOLUME"+i).val();//体积
    	var temp_DECT_RATE=$("#DECT_RATE"+i).val();//折扣率
    	var PRICE=isNaN(temp_PRICE)?0:parseFloat(temp_PRICE);//单价
    	var VOLUME=isNaN(temp_VOLUME)?1:parseFloat(temp_VOLUME);//体积
    	var DECT_RATE=isNaN(temp_DECT_RATE)?0:parseFloat(temp_DECT_RATE);//折扣率
    	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//计算折后单价，保留2位小数
    	var temp_old_ORDER_NUM=$("#temp_ORDER_NUM"+i).val();//订货数量
    	if(temp_ORDER_NUM==""||temp_ORDER_NUM==null||isNaN(temp_ORDER_NUM)){
    		temp_ORDER_NUM=0;
    	}
    	var temp_ORDER_NUM=temp_old_ORDER_NUM;//实际订货数量
    	var ORDER_NUM=isNaN(temp_ORDER_NUM)?0:parseFloat(temp_ORDER_NUM);//实际订货数量
    	var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*temp_ORDER_NUM)?0:DECT_PRICE*temp_ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
    	var TOTAL_VOLUME=Math.round((isNaN(VOLUME*ORDER_NUM)?0:VOLUME*ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
    	$("#DECT_PRICE"+i).val(DECT_PRICE);
    	$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
    	$("#TOTAL_VOLUME"+i).val(TOTAL_VOLUME);
    	$("#ORDER_NUM"+i).val(temp_ORDER_NUM);
    }
    
 //当键盘按键松开时触发事件
 //输入抵库数量计算实际订货数量，总体积
function countPRA_ORDER_NUM(i){
		var temp_VOLUME=$("#VOLUME"+i).val();//体积
		var VOLUME=isNaN(temp_VOLUME)?1:parseFloat(temp_VOLUME);//体积
		var temp_temp_ORDER_NUM=$("#temp_ORDER_NUM"+i).val();//订货数量
		var temp_ORDER_NUM=isNaN(temp_temp_ORDER_NUM)?0:parseFloat(temp_temp_ORDER_NUM);//订货数量
		var temp_PLE_REP=$("#PLE_REP"+i).val();//抵库数量
		if(""==temp_PLE_REP||null==temp_PLE_REP){
			temp_PLE_REP=0;
		}
		var PLE_REP=isNaN(temp_PLE_REP)?0:parseFloat(temp_PLE_REP);//抵库数量
		var temp_num=$("#num"+i).val();//可用库存
    	var num=isNaN(temp_num)?0:parseFloat(temp_num);//订货数量
    	var round=Math.round((isNaN(num-PLE_REP)?0:num-PLE_REP)*100)/100;//
    	if(round<0){
    		parent.showErrorMsg("抵库数量不得大于可用库存");
			$("#PLE_REP"+i).val("");
			getVolumFrom();
			return;
    	}
		var ORDER_NUM= Math.round((isNaN(temp_ORDER_NUM-PLE_REP)?0:temp_ORDER_NUM-PLE_REP)*100)/100;//计算实际订货数量，保留2位小数
		if(ORDER_NUM<0){
			parent.showErrorMsg("抵库数量不得大于订货数量");
			$("#PLE_REP"+i).val("");
			getVolumFrom();
			return;
		}
		var temp_DECT_PRICE=$("#DECT_PRICE"+i).val();//折后单价
		var DECT_PRICE=isNaN(temp_DECT_PRICE)?0:parseFloat(temp_DECT_PRICE);//折后单价
		var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*ORDER_NUM)?0:DECT_PRICE*ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
		var TOTAL_VOLUME=Math.round((isNaN(VOLUME*ORDER_NUM)?0:VOLUME*ORDER_NUM)*100)/100;//计算订货金额，保留2位小数
		$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
		$("#ORDER_NUM"+i).val(ORDER_NUM);
		$("#TOTAL_VOLUME"+i).val(TOTAL_VOLUME);
		getVolumFrom();
}
	
	
function url(rownum){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	var PRICE=$("#PRICE"+rownum).val();
//	window.open('techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
	
	
	
//下拉框选择的车型 从后台获取该车型的容积
function getVolumFrom(obj){
	var flag=$("#flag").val();//1->区域服务中心0->加盟商
//	if("show"!=flag){
//		return;
//	}
	var carType = $("#carType").val();
	if("init" == obj){
		carType = "5吨(49-53)";
	}
	var MIN_VOLUME = $("#MIN_VOLUME").val();
	var MAX_VOLUME = $("#MAX_VOLUME").val();
	if(null == carType || "" == carType){
		return;
	}
	$.ajax({
		url: "carcalculate.shtml?action=getVolum",
		type:"POST",
		dataType:"text",
		data:{"carType":carType},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var MIN_VOLUME = jsonResult.data.MIN_VOLUME;
				 var MAX_VOLUME = jsonResult.data.MAX_VOLUME;
				 if(null == MIN_VOLUME || ""==MIN_VOLUME){
					 MIN_VOLUME = 0;
				 }
				 if(null == MAX_VOLUME || ""==MAX_VOLUME){
					 MAX_VOLUME = 0;
				 }
				 //设置
				 $("#MIN_VOLUME").val(MIN_VOLUME);
				 $("#MAX_VOLUME").val(MAX_VOLUME);
				 $("#car_VOLUMN").text("(满车："+MIN_VOLUME+"~"+MAX_VOLUME+")");
				 countCar(MIN_VOLUME,MAX_VOLUME);
			} 
		}
	});
	 
}
//计算货品体积
function countCar(MIN_VOLUME,MAX_VOLUME){
	if(0==MIN_VOLUME || "0"==MIN_VOLUME||null==MIN_VOLUME ||""==MIN_VOLUME){
		 $("#car").text(0);
		 return;
	 }
	 var checkBox = $("#jsontb tr:gt(0) input:checked");
	 
	 if(checkBox.length==0){
		 $("#car").text(0);
		 $("#total").html(0);
	     $("#allvol").html(0);
		 return;
	 }
	 var total = 0;
	 var totalMoney = 0;
	 checkBox.each(function(){
		 var money = $(this).parent().parent().find("input[name='DECT_AMOUNT']").eq(0).val();
		 var toMoney = isNaN(money)?0:parseFloat(money);
		 totalMoney = totalMoney + toMoney;
		 
		 var ORDER_NUM_VOL = $(this).parent().parent().find("input[name='ORDER_NUM']").eq(0).val();
		 var totalvol = $(this).parent().parent().find("input[name='VOLUME']").val();
		 totalvol = $.trim(totalvol);
		 totalvol = isNaN(ORDER_NUM_VOL*totalvol)?0:parseFloat(ORDER_NUM_VOL*totalvol);
		 total = Math.round((isNaN(Number(total) + Number(totalvol))?0:Number(total) + Number(totalvol))*100)/100;
	 });
	 var car = Number(total/MAX_VOLUME);
	 car = car.toFixed(2)//保留2位小数
	 $("#car").text(car);
	 $("#total").html(totalMoney);//总金额
	 $("#allvol").html(total);//总体积
	 
	 var arry = car.split(".");
	 if("00" == arry[1] && 0!=car){
		 //parent.showMsgPanel("货品已够整车");
	}
}
//下拉框选择的车型 从后台获取该车型的容积
function changeVolumn(){
   $("#old_VOLUME").val("");//清空 然后在下帧设置值
   getVolumFrom();
}

//判断是否是整车
function checkCarVol(){
	var flag = $("#flag").val();//1->区域服务中心0->加盟商
	
	var allvol = $.trim($("#allvol").text());
    var MIN_VOLUME = $("#MIN_VOLUME").val();
	var MAX_VOLUME = $("#MAX_VOLUME").val();
	allvol = Number(allvol);
	MIN_VOLUME = Number(MIN_VOLUME);
	
	if(allvol < MIN_VOLUME){
		if(0 == flag || "0" == flag){//0->加盟商
			parent.showErrorMsg("加盟商订货，不满一车，不能下单!");
			return false;
	    }else{
	    	parent.showConfirm("不满整车，您确认订货吗?","childSave();","N");
		    return false;
	    }
	}
	if(allvol> MAX_VOLUME){
		parent.showErrorMsg("大于一车，不能下单");
		return false;
	}
	
	return true;
}
//点中返利按钮时，判断是否有返利金额，如果没有，给予提示，不让选中
function rebOp(){
	var checked = $("#rebate").prop("checked");
	var REBATEDSCT=$("#REBATEDSCT").val();
	if(checked&&(""==REBATEDSCT||null==REBATEDSCT||0==REBATEDSCT)){
		parent.showErrorMsg(utf8Decode("总部未设置您的返利折扣，不能使用返利金额，请联系总部！"));
		$("#rebate").removeAttr("checked");
		return;
	}
	if(checked){
		$("#REBATEFLAG").val("1");
		$("#pageForm").submit();
	}else{
		$("#REBATEFLAG").val("0");
		$("#pageForm").submit();
	}
}
//判断返利金额是否足够
function judgeRebate(){
	var checked = $("#rebate").attr("checked");
	if("checked" == checked){
		var total = 0;
		var REBATEDSCT = $("#REBATEDSCT").val();
		var REBATE_CURRT = $("#REBATE_CURRT").val();
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		checkBox.each(function(){
			var PRICE = $(this).parent().parent().find("td[name='PRICE']").text();
			var ORDER_NUM = $(this).parent().parent().find("input[name='ORDER_NUM']").val();
			PRICE = parseFloat($.trim(PRICE));
			ORDER_NUM = parseInt($.trim(ORDER_NUM));
			if(!isNaN(PRICE) && !isNaN(ORDER_NUM)){
				total = parseFloat(total) + PRICE*REBATEDSCT*ORDER_NUM;
			}
		}); 
		
		if(total>REBATE_CURRT){
			parent.showErrorMsg("返利金额不足，不能使用返利下单");
			return false;
		} 
	}
	return true;
}
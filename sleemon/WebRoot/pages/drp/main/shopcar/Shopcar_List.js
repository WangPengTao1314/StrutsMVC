﻿
/**
 *@module 分销业务
 *@func购物车
 *@version 1.1
 *@author lyg
 */
$(function(){
	headColumnSort("jsontb","listForm");
	//添加浮动按钮层的监听
	addFloatDivListener();
	var REBATEFLAG=$("#REBATEFLAG").val();
	if("1"==REBATEFLAG){
		$("#rebate").prop("checked",true);
	}else{
		$("#rebate").prop("checked",false);
	}
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
		
		getVolumFrom();
	});
	$("#save").click(function(){
		if(check()){
			childSave("save");
		}
	})
	//下单
	$("#addPrd").click(function(){
		if(check() && judgeRebate()){
			if(!checkCarVol()){
				//parent.showConfirm("不满整车，您确认订货吗?","bottomcontent.childSave('add');","N");
		    }else{
		    	childSave("add");
		    }
		}
	})
	//下单并提交
 	$("#commit").click(function(){
 		if(check() && judgeRebate()){
			if(!checkCarVol("commit")){
				//parent.showConfirm("不满整车，您确认订货吗?","bottomcontent.childSave('add');","N");
		    }else{
		    	childSave("commit");
		    }
		}
 	})
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var tab=false;
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
			var type=$(this).parent().parent().find("td[name='type']").html();
			if("预订单转订货"==type){
				tab=true;
				parent.showErrorMsg("非手工新增货品不可删除");
				return;
			}
		});
		if(tab){
			return;
		}
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
	
 getVolumFrom();
 
 
});
//验证
function check(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return ;
		}
		var tab = true;
		checkBox.each(function(){
			var input = $(this).parent().parent().find("input[name='ORDER_NUM']").eq(0);
			var num = input.val();
			var label = input.attr("label");
			if(num==0||num==null||num==""){
				parent.showErrorMsg("请输入'"+label+"'");
				tab=false;
				return false;
			}
			if(isNaN(num)){
				parent.showErrorMsg("'"+label+"'输入不合法,请重新输入");
				tab=false;
				return false;
			}
			if(num.indexOf(".")>0){
				parent.showErrorMsg("'"+label+"'只允许输入正整数");
				tab=false;
				return false;
			}
			var re1 = new RegExp(/^\d{0,8}$/);
	        if(!re1.test(num)){
	            parent.showErrorMsg("'"+label+"'最多可输入8位正整数");
	            tab=false;
				return false;
	        }
		});
		return tab;
}

//判断是否是整车
function checkCarVol(type){
	if(""==type||null==type){
		type="add";
	}
	var car = $.trim($("#car").text());
    var allvol = $("#allvol").text();
    allvol = $.trim(allvol);
    var MIN_VOLUME =  parent.$("#MIN_VOLUME").val();
	var MAX_VOLUME =  parent.$("#MAX_VOLUME").val();
	allvol = Number(allvol);
	MIN_VOLUME = Number(MIN_VOLUME);
	if(allvol < MIN_VOLUME){
		parent.showConfirm("不满整车，您确认订货吗?","bottomcontent.childSave('"+type+"');","N");
		return false;
	}
	if(allvol> MAX_VOLUME){
		parent.showErrorMsg("大于一车，不能下单");
		return false;
	}
	return true;
}


function childSave(type){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		//基准价
		var BASE_PRICE=$(this).parent().parent().find("td[name='BASE_PRICE']").html();
		$(this).parent().parent().find("input[name='PRICE']").val(BASE_PRICE);
		//折扣率
		var RATE=$(this).parent().parent().find("td[name='RATE']").html();
		$(this).parent().parent().find("input[name='DECT_RATE']").val(RATE);
		//折后价
		var PRICES=$(this).parent().parent().find("td[name='PRICES']").html();
		$(this).parent().parent().find("input[name='DECT_PRICE']").val(PRICES);
	});
	
	if("save" != type){
		var tabData = parent.getJson();
		if(null == tabData || "" == tabData){
			return;
		}
	}
	
	//返利
	var rebate = 0;
	var REBATEDSCT = "";//返利折扣
	//赠品
	var LARGESSFLAG = 0;
	var LARGESSDSCT = "";//赠品折扣
	if(parent.$("#rebate").prop("checked")){
		rebate = 1;
		REBATEDSCT = $("#REBATEDSCT").val();
	}
	if(parent.$("#larRebate").prop("checked")){
		LARGESSFLAG = 1;
		LARGESSDSCT = $("#LARGESSDSCT").val();
	}
	var ids = "";
	checkBox.each(function(){
		if("" != $(this).val()){
			ids = ids+"'"+$(this).val()+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	
 	//主表的 交期 取 明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
    var ORDER_RECV_DATE =  getORDER_RECV_DATE();
    var jsonData = multiPackJsonData();
    $.ajax({
		url: "shopcar.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData,"type":type,"tabData":tabData,"rebate":rebate,
    	"SHOP_CART_IDS":ids,"ORDER_RECV_DATE":ORDER_RECV_DATE,"REBATEDSCT":REBATEDSCT,"LARGESSFLAG":LARGESSFLAG,"LARGESSDSCT":LARGESSDSCT},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				setTimeout('listForm.submit()',100);
				//parent.gotoBottomPage();
				parent.$("#rebate").prop("checked",false);
				$("#REBATEFLAG").val("");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

 function toFlow(i,GOODS_ORDER_ID) {
	 var cutid = GOODS_ORDER_ID;
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/shopcar.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
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
		url: "shopcar.shtml?action=delete",
		type:"POST",
		dataType:"text",
		data:{"SHOP_CART_IDS":ids},
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

//明细表点击后设置选中
function setEidChecked(obj,id){
	
//	var flag = obj.checked;
//	if(flag){
//		$(obj).removeAttr("checked");
//	}else{
//		$(obj).attr("checked","true");
//	}
	countAmount(id);
	
	
}
//鼠标按下订货数量时,计算金额,总体积
function countAmount(id){
	$("#num"+id).parent().parent().find("input[type='checkbox']").attr("checked","checked");
	var num=$("#num"+id).val();
	var ORDER_NUM=isNaN(num)?0:parseFloat(num);
	var PRICE=$("#PRICES"+id).html();
	var ORDER_AMOUNT=Math.round((isNaN(ORDER_NUM*PRICE)?0:ORDER_NUM*PRICE)*100)/100;
	var temp_VOLUME=$("#VOLUME"+id).val();
	$("#amount"+id).html(ORDER_AMOUNT);
	$("#ORDER_AMOUNT"+id).val(ORDER_AMOUNT);
	var total=0;
	var allvol=0;
	var totalNum=0;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
			var money = $(this).parent().parent().find("input[name='ORDER_AMOUNT']").eq(0).val();
			var toMoney=isNaN(money)?0:parseFloat(money);
			total=Math.round((isNaN(total+toMoney)?0:total+toMoney)*100)/100;
			//数量
			var ORDER_NUM_VOL=$(this).parent().parent().find("input[name='ORDER_NUM']").eq(0).val();
			var VOLUME=$(this).parent().parent().find("td[name='vol']").eq(0).html();
			var vol=isNaN(ORDER_NUM_VOL*VOLUME)?0:parseFloat(ORDER_NUM_VOL*VOLUME);
			vol = Math.round(vol*100)/100;
			allvol=Math.round((isNaN(allvol+vol)?0:allvol+vol)*100)/100;
			totalNum =Math.round((isNaN(totalNum+ORDER_NUM_VOL*1.0)?0:parseFloat(totalNum+ORDER_NUM_VOL*1.0)),2);
	 });
	 $("#total").html(total);
	 $("#allvol").html(allvol);
	 $("#allNum").html(totalNum);//总数量
	 //计算整车
	 var MAX_VOLUME = parent.$("#MAX_VOLUME").val();
	 var car = Number(allvol/MAX_VOLUME);
	 car = car.toFixed(2)//保留2位小数
	 $("#car").text(car);
}
function showImg(obj,id){
	var fileName = $(obj).attr("src");
	
	/**
	 * 获取图片大小
	 * var pic=$("#"+id);
	 * var theImage = new Image();
	 * theImage.src = pic.attr("src");
	 * var imageWidth = theImage.width;
	 * var imageHeight = theImage.height;
	 */
	window.open("shopcar.shtml?action=toPicture&fileName="+fileName,'货品信息图片','height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

//计算车量
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
	     $("#allNum").html(0);
		 return;
	 }
	 var totalvol = 0;
	 var totalMoney = 0;
	 var totalNum = 0;
	 checkBox.each(function(){
		 var money = $(this).parent().parent().find("input[name='ORDER_AMOUNT']").val();
		 if(""==money||null==money){
			 money=0.0;
		 }
		 var toMoney = isNaN(money)?0:parseFloat(money);
		 totalMoney = totalMoney + toMoney;
		 
		 var ORDER_NUM = $(this).parent().parent().find("input[name='ORDER_NUM']").val();
		 ORDER_NUM = parseInt(ORDER_NUM);
		 if(isNaN(ORDER_NUM)){
			 ORDER_NUM = 0;
		 }
		 var vol = $(this).parent().parent().find("td[name='vol']").text();
		 vol = $.trim(vol);
		 if(""==vol||null==vol){
			 vol=0;
		 }
		 vol=isNaN(vol)?0:parseFloat(vol);
		 //修改总体积计算方法，计算后保留两位小数
		 totalNum =  totalNum + ORDER_NUM;
		 totalvol =  totalvol + vol*ORDER_NUM;
	 });
//	  alert(totalvol+":"+totalvol);
	   
	 var car = Number(totalvol/MAX_VOLUME);
	 car = car.toFixed(2)//保留2位小数
	 $("#car").text(car);
	 totalvol = totalvol.toFixed(2);//保留2位小数
	 totalMoney = totalMoney.toFixed(2);
     $("#total").html(totalMoney);//总金额
	 $("#allvol").html(totalvol);//总体积
	 $("#allNum").html(totalNum);//总数量
	 MIN_VOLUME = Number(MIN_VOLUME);
	 MAX_VOLUME = Number(MAX_VOLUME);
	 if(MIN_VOLUME <= totalvol &&  totalvol <=MAX_VOLUME){
		 parent.showMsgPanel("货品已够整车");
	 }
	 
}



//下拉框选择的车型 从后台获取该车型的容积
function getVolumFrom(){
	var volumn = parent.$("#old_VOLUME").val();
	var MIN_VOLUME =  parent.$("#MIN_VOLUME").val();
	var MAX_VOLUME =  parent.$("#MAX_VOLUME").val();
	if(null == MIN_VOLUME || "" == MIN_VOLUME){
		var carType = parent.$("#carType").val();
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
					 var data = jsonResult.data;
					 var MIN_VOLUME = data.MIN_VOLUME;
					 var MAX_VOLUME = data.MAX_VOLUME;
					 if(null == MIN_VOLUME || ""==MIN_VOLUME){
						 MIN_VOLUME = 0;
					 }
					 if(null == MAX_VOLUME || ""==MAX_VOLUME){
						 MAX_VOLUME = 0;
					 }
					 
                     //设置
					 parent.$("#MIN_VOLUME").val(MIN_VOLUME);
					 parent.$("#MAX_VOLUME").val(MAX_VOLUME);
					 
					 parent.$("#car_VOLUMN").text("(满车："+MIN_VOLUME+"~"+MAX_VOLUME+")");
					 countCar(MIN_VOLUME,MAX_VOLUME);
				} 
			}
		});
	}else{
		countCar(MIN_VOLUME,MAX_VOLUME);
	}
	
}

//修改特殊工艺后修改该条的价格
function upPrice(PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,SHOP_CART_ID){
	var ORDER_AMOUNT=ORDER_NUM*DECT_PRICE;
	$.ajax({
		url: "shopcar.shtml?action=upPrice",
		type:"POST",
		dataType:"text",
		data:{"DECT_RATE":DECT_RATE,"DECT_PRICE":DECT_PRICE,"SHOP_CART_ID":SHOP_CART_ID,"PRICE":PRICE,"ORDER_NUM":ORDER_NUM,"ORDER_AMOUNT":ORDER_AMOUNT},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			}else{
				parent.showErrorMsg(utf8Decode("编辑出错"));
			}
		}
	});
}

function url(rownum){
		var PRD_ID=$("#PRD_ID"+rownum).val();
		var CHANN_ID=$("#CHANN_ID").val();
		var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
		var SHOP_CART_ID=$("#eid"+rownum).val();
		var BASE_PRICE=$("#BASE_PRICE"+rownum).html();
		var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&USE_FLAG=1&TABLE=DRP_SHOP_CART&filterSpclFlag=1&BUSSID="+SHOP_CART_ID+"&citeUrl=editTechWithPrice&PRICE="+BASE_PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
		//当打开特殊工艺后直接关闭时
		if(null==data){
			return;
		}
		if(data.SPCL_TECH_FLAG!=0&&data.SPCL_SPEC_REMARK!=null&&data.SPCL_SPEC_REMARK!=""){
			$("#SPECIAL_FLAG"+rownum).html("<label class='hideNoticeText' title='"+data.SPCL_SPEC_REMARK+"'>"+data.SPCL_SPEC_REMARK+"</label>");
		}else{
			$("#SPECIAL_FLAG"+rownum).html("无");
		}
		$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
		var PRICE = data.PRICE;//单价
		var DECT_RATE = $("#RATE"+rownum).html();//折扣率
		var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
		$("#PRICES"+rownum).html(DECT_PRICE);
		$("#BASE_PRICE"+rownum).html(data.PRICE);
		var ORDER_NUM=$("#num"+rownum).val();
		if(""==ORDER_NUM||null==ORDER_NUM){
			ORDER_NUM=0;
		}else{
			ORDER_NUM=parseFloat(ORDER_NUM);
		}
		upPrice(PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,SHOP_CART_ID);
		countAmount(rownum);
}
function urlshow(SPCL_TECH_ID,PRICE){
	window.open('techorderManager.shtml?action=viewTechWithPrice&acqModel=1&SPCL_TECH_ID='+SPCL_TECH_ID+'&PRICE='+PRICE,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
function hide(){
	if($("#showOrHide").val()=="↑"){
		parent.$("#topDiv").hide();
		$("#showOrHide").val("↓");
		parent.$("#bottomdiv").css({height:'100%'});
	}else{
		parent.$("#topDiv").show();
		$("#showOrHide").val("↑");
		parent.$("#bottomdiv").css({height:'73%'});
	}
}



//主表的 交期 取 明细最近的交期  如：2014-7-8和 2014-7-9，取2014-7-8的插到表里
function getORDER_RECV_DATE(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var inputs = $("#jsontb").find("input[json='ORDER_RECV_DATE']");
	var bigTime = "";
	var resultDate = "";
	checkBox.each(function(){
		var date = $(this).parent().parent().find("input[json='ORDER_RECV_DATE']").val();
		date = $.trim(date);
		if(null != date && "" !=date){
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
        
	});
	return resultDate;
}


function pitchChecked(id){
	$("#eid"+id).prop("checked",true);
}


//点中返利按钮时，判断是否有返利金额，如果没有，给予提示，不让选中
function rebOp(){
	var rebate = parent.$("#rebate").prop("checked");
	var larRebate = parent.$("#larRebate").prop("checked");
	if(larRebate&&rebate){
		parent.showErrorMsg(utf8Decode("使用赠品折扣时不能使用返利折扣！"));
		parent.$("#rebate").removeAttr("checked");
		return;
	}
	var REBATEDSCT=$("#REBATEDSCT").val();
	if(rebate&&(""==REBATEDSCT||null==REBATEDSCT||0==REBATEDSCT)){
		parent.showErrorMsg(utf8Decode("总部未设置您的返利折扣，不能使用返利金额，请联系总部！"));
		parent.$("#rebate").removeAttr("checked");
		return;
	}
	if(rebate){
		$("#REBATEFLAG").val("1");
		$("#listForm").submit();
	}else{
		$("#REBATEFLAG").val("0");
		$("#listForm").submit();
	}
}

//点击使用赠品折扣判断是否已选中返利折扣和是否有赠品折扣
function larOp(){
	var rebate = parent.$("#rebate").prop("checked");
	var larRebate = parent.$("#larRebate").prop("checked");
	if(larRebate&&rebate){
		parent.showErrorMsg(utf8Decode("使用返利折扣时不能使用赠品折扣！"));
		parent.$("#larRebate").removeAttr("checked");
		return;
	}
	var LARGESSDSCT=$("#LARGESSDSCT").val();
	if(larRebate&&(""==LARGESSDSCT||null==LARGESSDSCT||0==LARGESSDSCT)){
		parent.showErrorMsg(utf8Decode("总部未设置您的赠品折扣，不能使用赠品订货，请联系总部！"));
		parent.$("#larRebate").removeAttr("checked");
		return;
	}
	if(larRebate){
		$("#LARGESSFLAG").val("1");
		$("#listForm").submit();
	}else{
		$("#LARGESSFLAG").val("0");
		$("#listForm").submit();
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
			var PRICE = $(this).parent().parent().find("td[name='BASE_PRICE']").text();
			var ORDER_NUM = $(this).parent().parent().find("input[json='ORDER_NUM']").val();
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

//购物车类型查询
function changeListPage(obj){
	var SHOP_CART_TYPE_ = $("#SHOP_CART_TYPE_").val();
	if(null == SHOP_CART_TYPE_){
		SHOP_CART_TYPE_ = "";
	}
	$("#SHOP_CART_TYPE").val(SHOP_CART_TYPE_);
	$("#listForm").submit();
}
 

 

//图片加载不出来时，显示默认图片
function checkImag(obj){
	var picture_url = $("#picture_url").val();
	$(obj).attr("src",picture_url+"dafult.jpg");
	 
}


function changeTextArea(obj) {
	var upper = obj.className;
	if("uppercase"==upper){
		obj.value=obj.value.toUpperCase();
	}
	var maxL = obj.maxlength || obj.maxLength;
	var L = stringLength(obj.value);
	if (L > maxL) {
		obj.value = obj.oVal;
	} else {
		obj.oVal = obj.value;
	}
}




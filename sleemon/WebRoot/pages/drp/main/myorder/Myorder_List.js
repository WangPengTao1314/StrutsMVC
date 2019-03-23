
/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	headColumnSort("jsontb");
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	$("#selShopCar").click(function (){
		window.open('shopcar.shtml?action=toInfo','购物车','height=800, width=1000, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	})
	$("#addPrd").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var tab=true;
		checkBox.each(function(){
			var num = $(this).parent().parent().find("input[name='ORDER_NUM']").eq(0).val();
			if(num==0||num==null||num==""){
				alert("请输入订货数量");
				tab=false;
				return false;
			}
			if(isNaN(num)){
				alert("订货数量输入不合法,请重新输入");
				tab=false;
				return false;
			}
			if(num.indexOf(".")>0){
				alert("订货数量只允许输入正整数");
				tab=false;
				return false;
			}
	        var re1 = new RegExp(/^\d{0,8}$/);
	        if(!re1.test(num)){
	           alert("订货数量最多输入八位正整数");
	           tab=false;
				return false;
	        }
		});
		if(tab){
			childSave();
		}
	})
	//hideDiv();
	
//	$("#_gotoPageSize").click(function(){
//		$("#_gotoPageSize option[text='50']").remove();
//		$("#_gotoPageSize option[text='100']").remove();
//	});
});
//明细表点击后设置选中
function setEidChecked(obj){
//	var flag = obj.checked;
//	if(flag){
//		$(obj).removeAttr("checked");
//	}else{
//		$(obj).attr("checked","true");
//	}
}
//加入购物车
function childSave(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		//基准价
		var BASE_PRICE=$(this).parent().parent().find("td[name='BASE_PRICE']").text();
		BASE_PRICE = $.trim(BASE_PRICE);
		$(this).parent().parent().find("input[name='PRICE']").val(BASE_PRICE);
		//折扣率
		var RATE=$(this).parent().parent().find("td[name='RATE']").text();
		RATE = $.trim(RATE);
		$(this).parent().parent().find("input[name='DECT_RATE']").val(RATE);
		//折后价
		var PRICES=$(this).parent().parent().find("td[name='PRICES']").text();
		PRICES = $.trim(PRICES);
		$(this).parent().parent().find("input[name='DECT_PRICE']").val(PRICES);
	});
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "myorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.clickBut();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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
	window.open("myorder.shtml?action=toPicture&fileName="+fileName,'货品信息图片','height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
function url(rownum){
	var PRD_ID=$("#eid"+rownum).val();
	var CHANN_ID=$("#CHANN_ID").val();
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	var BASE_PRICE=$("#BASE_PRICE"+rownum).html();
	var data=window.showModalDialog("techorderManager.shtml?action=toFrame&filterSpclFlag=1&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&citeUrl=editTechWithPrice&PRICE="+BASE_PRICE,window,"dialogwidth=800px; dialogheight=700px; status=no");
	if(null==data){
		return;
	}
	if(data.SPCL_TECH_FLAG!=0){
		$("#SPECIAL_FLAG"+rownum).html("<span  style='color: red'>有</span>");
	}else{
		$("#SPECIAL_FLAG"+rownum).html("无");
	}
	$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
	var PRICE = data.PRICE;//单价
	var DECT_RATE =$("#RATE"+rownum).html();//折扣率
	var DECT_PRICE = Math.round((isNaN(PRICE*DECT_RATE)?0:PRICE*DECT_RATE)*100)/100;//折后单价
	$("#PRICES"+rownum).html(DECT_PRICE);
	$("#BASE_PRICE"+rownum).html(data.PRICE);
}
function pitchChecked(id){
	$("#eid"+id).prop("checked",true);
}


//图片加载不出来时，显示默认图片
function checkImag(obj){
	var picture_url = $("#picture_url").val();
	$(obj).attr("src",picture_url+"dafult.jpg");
	 
}


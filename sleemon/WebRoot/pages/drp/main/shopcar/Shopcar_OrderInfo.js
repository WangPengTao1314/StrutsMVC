

/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	gotoBottomPage();
	showBtnLook(null);
	InitFormValidator("queryForm");
	
	$("#bottomdiv").hover(
		function() {
		    $("#carType option[text='--请选择--']").remove();
	    }, 
	   function() {
		  $("#carType option[text='--请选择--']").remove();
	    }
	);
	
	$("#topDiv").hover(
		function() {
		    $("#carType option[text='--请选择--']").remove();
	    }, 
	   function() {
		  $("#carType option[text='--请选择--']").remove();
	    }
	);
	
	
 

});
//下帧初始化
function gotoBottomPage(action){
	//初始化时下帧页面的action
	if(null == action){
	  action = "toList";
	}
	var url = "shopcar.shtml?action="+action;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
function getJson(){
	var PERSON_CON=$("#PERSON_CON").val();
	var TEL=$("#TEL").val();
	var ADDRESS=$("#RECV_ADDR_NO").val();
	var CHANN_ID=$("#CHANN_ID").val();
	if(""==CHANN_ID||null==CHANN_ID){
		showErrorMsg("请选择收货方信息");
		return null;
	}
	if(""==ADDRESS||null==ADDRESS){
		showErrorMsg("请选择详细地址");
		return null;
	}
	if(""==PERSON_CON||null==PERSON_CON){
		showErrorMsg("请填写联系人");
		return null;
	}
	if(""==TEL||null==TEL){
		showErrorMsg("请填写联系电话");
		return null;
	}
//	if($("#TEL").val()!=null && $("#TEL").val() != ""){
//        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
//        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
//        var TEL1 = re1.test($("#TEL").val());
//        var TEL2 = re2.test($("#TEL").val());
//        if(!TEL1&&!TEL2 ){
//      	   showErrorMsg("联系电话格式输入不正确！");
//           return null;
//        }
//	}
	return siglePackJsonData("channInfo");
}


function showBtnLook(obj){
	var checked = $(obj).attr("checked");
	if(checked){
		rmoveBtnDis(["look"]);
	}else{
		btnDisable(["look"]);
	}
}

function showPage(){
	window.open("carcalculate.shtml?action=listGoodsOrder&page_plag=1&notSend="+utf8("总部未发"),'总部未发货品查看','height=500, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
function selRevcAddr(){
	var CHANN_ID=$("#CHANN_ID").val();
	if(""==CHANN_ID||null==CHANN_ID){
		showErrorMsg("请先选择收货方信息")
		return;
	}
	$("#selectAddrParams").val(" DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+CHANN_ID+"' ");
	selCommon('BS_76', false, 'RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'forms[0]','RECV_ADDR_NO,RECV_ADDR', 'DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams');
}


//下拉框选择的车型 从后台获取该车型的容积
//选择车型的时候 计算需要多少车   调用下帧的 方法
function changeVolumn(){
   $("#old_VOLUME").val("");//清空 然后在下帧设置值
   $("#MIN_VOLUME").val("");
   $("#MAX_VOLUME").val("");
   window.bottomcontent.getVolumFrom();
}

 
 function rmoveBtnDis(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){
		$("#"+arrys[i]).removeAttr("disabled");
	}
 }
 function selStore(){
	var CHANN_ID=$("#CHANN_ID").val();
	if(""==CHANN_ID||null==CHANN_ID){
		showErrorMsg("请先选择收货方信息")
		return;
	}
	var html="STATE ='启用' and DEL_FLAG='0' and BEL_CHANN_ID='"+CHANN_ID+"' "
	$("#selectStore").val(html);
	selCommon('BS_35', false, 'STORE_ID', 'STORE_ID', 'forms[0]','STORE_ID,STORE_NO,STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME', 'selectStore');
 }
 function rebOp(){
	 window.bottomcontent.rebOp();
 }
  function larOp(){
	 window.bottomcontent.larOp();
 }
 function selRECV(){
	 var chann_id_old=$("#CHANN_ID").val();
	 selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME,PERSON_CON,TEL,AREA_ID,AREA_NO,AREA_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME,PERSON_CON,TEL,AREA_ID,AREA_NO,AREA_NAME','selectParams');
	 var chann_id=$("#CHANN_ID").val();
	 if(chann_id_old!=chann_id){
		 $("#RECV_ADDR_NO").val("");
		 $("#RECV_ADDR").val("");
		 $("#STORE_ID").val("");
		 $("#STORE_NO").val("");
		 $("#STORE_NAME").val("");
	 }
 }

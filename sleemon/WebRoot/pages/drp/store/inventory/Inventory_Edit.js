/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_Edit
 * @author lyg
 * @time   2013-09-07 09:54:59 
 * @version 1.1
 */
$(function(){
    //下帧跳转
//	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);
//	acquireTab();
	
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("inventory.shtml?action=edit","PRD_INV_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	
	
});
//设置盘点范围，选择哪条下拉菜单的值就对应隐藏其他tr
   function rangeSelect(tab){
	   //判断修改时用，因下拉菜单获值比页面加载晚，所以需要先把值放到hidden里获取
	   var INV_RANGE=$("#INV_RANGE").val();
	   if(INV_RANGE==""||INV_RANGE==null){
		   INV_RANGE=$("#range").val();
		   $("#range").val("");
	   }
	   if(tab==""||tab==null){
		emptyStorg();
   		emptyPrd();
	   }
   		if(INV_RANGE=="库房盘点"){
   			emptyStorg("隐藏");
   			emptyPrd("隐藏");
   		}else if(INV_RANGE=="库位盘点"){
   			emptyPrd("隐藏");
   		}else if(INV_RANGE=="货品盘点"){
   			emptyStorg("隐藏");
   		}
   		//标识列 值为2时 是页面初始化时加载，用于修改状态用，
   		//初始化时按盘点范围判断隐藏input标签，这时不需要调用下帧隐藏方法，下帧页面在加载完毕时会调用其页面隐藏方法
//   		if(tab!=2){
//   			parent.bottomcontent.hideStorg();
//   			var STORE_ID=$("#STORE_ID").val();
//	   		var STORG_ID=$("#STORG_ID").val();
//	   		var PRD_ID=$("#PRD_ID").val();
//			if(STORE_ID!=null&&STORE_ID!=""&&INV_RANGE=="库房盘点"){
//				parent.window.gotoBottom(STORE_ID);
//			}else if(STORG_ID!=null&&STORG_ID!=""&&INV_RANGE=="库位盘点"){
//				parent.window.gotoBottom(STORE_ID,STORG_ID);
//			}else if(PRD_ID!=null&&PRD_ID!=""&&INV_RANGE=="货品盘点"){
//				parent.window.gotoBottom(STORE_ID,"",PRD_ID);
//			}
//   		}
   		
   }
//获取盘点范围
   function getINV_RANGE(){
	   return $("#INV_RANGE").val();
   }
//隐藏库位信息并且给库位信息赋值默认空或显示库位信息并清空库位信息value
   function emptyStorg(opinion){
	   var STORG_tr=$("#STORG_tr");
	   var STORG_ID=$("#STORG_ID");
   	   var STORG_NO=$("#STORG_NO");
   	   var STORG_NAME=$("#STORG_NAME");
	   if(opinion=="隐藏"){
		   STORG_tr.hide();
		   //为了不被input标签mustinput属性必选项拦截，默认给值为“空”，后天添加时判断盘点范围是否添加该值到MAP里进行新增
		   STORG_ID.val("空");
	   	   STORG_NO.val("空");
	   	   STORG_NAME.val("空");
	   }else{
		   STORG_tr.show();
		   STORG_ID.val(null);
	   	   STORG_NO.val(null);
	   	   STORG_NAME.val(null);
	   }
   }
   //隐藏货品信息并且给货品信息赋值默认空或显示货品信息并清空货品信息value
   function emptyPrd(opinion){
	   var PRD_NO_tr=$("#PRD_NO_tr");
	   var PRD_NAME_tr=$("#PRD_NAME_tr");
	   var PRD_ID=$("#PRD_ID");
   	   var PRD_NO=$("#PRD_NO");
   	   var PRD_NAME=$("#PRD_NAME");
   	   if(opinion=="隐藏"){
   		   PRD_NO_tr.hide();
   		   PRD_NAME_tr.hide();
   		   //为了不被input标签mustinput属性必选项拦截，默认给值为“空”，后天添加时判断盘点范围是否添加该值到MAP里进行新增
	   	   PRD_ID.val("空");
	       PRD_NO.val("空");
	       PRD_NAME.val("空");
   	   }else{
   		   PRD_NO_tr.show();
   		   PRD_NAME_tr.show();
	   	   PRD_ID.val(null);
	       PRD_NO.val(null);
	       PRD_NAME.val(null);
   	   }
   	   
   }
   
   
    
 //库房通用选取
function storeSelCommon(){
	var BMXXID = $("#BMXXID").val();
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var selectParams = "";
	if(null != TERM_ID && "" != TERM_ID){
		//终端用户选择转出库房只能选自己的
	   selectParams = " STATE ='启用' and DEL_FLAG=0 and  BEL_CHANN_ID='"+TERM_ID+"' ";
	}else{
		//渠道人员只能选本渠道和分管的终端的库房
	    selectParams = " STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='"+ZTXXID+"' or BEL_CHANN_ID in "+TERM_CHARGE+")";
	}
	
//	$("#selectParams").val("STATE ='启用' and DEL_FLAG='0' and BEL_CHANN_ID='"+BMXXID+"' ");
	$("#selectParams").val(selectParams);
	var newSTORE_ID=$("#STORE_ID").val();
	selCommon('BS_35', false, 'STORE_ID', 'STORE_ID', 'forms[0]','STORE_ID,STORE_NO,STORE_NAME','STORE_ID,STORE_NO,STORE_NAME', 'selectParams');
	var oldSTORE_ID=$("#STORE_ID").val();
	if(newSTORE_ID==""||newSTORE_ID==null||newSTORE_ID!=oldSTORE_ID){
	 var PRD_ID=$("#PRD_ID").val("");
   	 var PRD_NO=$("#PRD_NO").val("");
   	 var PRD_NAME=$("#PRD_NAME").val("");
   	 var STORG_ID=$("#STORG_ID").val("");
   	 var STORG_NO=$("#STORG_NO").val("");
   	 var STORG_NAME=$("#STORG_NAME").val("");
	}
	rangeSelect("1");
}




//库位通用选取
function storgSelCommon(){
	if($("#STORE_ID").val()==null||$("#STORE_ID").val()==""){
   		alert("请选择库房编号");
   	}else{
   		var STORE_ID=$("#STORE_ID").val();
		$("#selectParams").val("STATE ='启用' and DEL_FLAG='0' and STORE_ID='"+STORE_ID+"'");
   		selCommon('BS_42', false, 'STORG_ID', 'STORG_ID', 'forms[0]','STORG_ID,STORG_NO,STORG_NAME','STORG_ID,STORG_NO,STORG_NAME', 'selectParams')
   		rangeSelect("1");
   	}
}
//货品通用选取
function prdSelCommon(){
//	if($("#STORE_ID").val()==null||$("#STORE_ID").val()==""){
//   		alert("请选择库房编号");
//   	}else{
//   		var STORE_ID=$("#STORE_ID").val();
		var ZTXXID=$("#ZTXXID").val();
		$("#selectPrd").val(" STATE in ('启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG='1' and (COMM_FLAG=1 or ( COMM_FLAG=0 and LEDGER_ID='"+ZTXXID+"'))  ");
   		selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_ID,PRD_NO,PRD_NAME', 'PRD_ID,PRD_NO,PRD_NAME', 'selectPrd')
   		rangeSelect("1");
//   	}
}
function formCheckedEx(){
	//验证盘点类型不为空
	if($("#INV_TYPE").val()==null || $("#INV_TYPE").val() == ""){
      	   parent.showErrorMsg(utf8Decode("请选择盘点类型！"));
           return false;
	}
	//验证盘点范围不为空
	if($("#INV_RANGE").val()==null || $("#INV_RANGE").val() == ""){
      	   parent.showErrorMsg(utf8Decode("请选择盘点范围！"));
           return false;
	}
	return true;
}
function getSTORE_ID(){
	return $("#STORE_ID").val();
}
function getSTORG_ID(){
	return $("#STORG_ID").val();
}








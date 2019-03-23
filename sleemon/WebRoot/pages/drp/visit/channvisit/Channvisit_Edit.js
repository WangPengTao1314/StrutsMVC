
/**
 *@module 渠道管理-拜访管理
 *@func   加盟商拜访表维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("channvisit.shtml?action=edit","CHANN_VISIT_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
});

function formCheckedEx(){
	var VISIT_TYPE = $("#VISIT_TYPE").val();
	if(null == VISIT_TYPE || "" == VISIT_TYPE){
		parent.showErrorMsg("请选择'拜访方式'")
		return false;
	}
	return true;
}


function  getTotal(){
    
    var MATTRESST ;
    var BED_STOCKT;
    var BEDSIDE_STOCKT ;
    var BEDDING_STOCKT ;
    var OTHER_STOCKT   ;
    
    var MATTRESS_STOCK = $("#MATTRESS_STOCK").val();   //床垫
    if(MATTRESS_STOCK==""){
       MATTRESS_STOCKT = 0; 
    } else{
       MATTRESS_STOCKT = parseInt(MATTRESS_STOCK);
    }
    var BED_STOCK      = $("#BED_STOCK").val();        //软床
    if(BED_STOCK==""){
       BED_STOCKT = 0;
    } else {
       BED_STOCKT      = parseInt(BED_STOCK);
    }
    var BEDSIDE_STOCK  = $("#BEDSIDE_STOCK").val();    //床头柜
    if(BEDSIDE_STOCK==""){
       BEDSIDE_STOCKT = 0;
    } else{
       BEDSIDE_STOCKT  = parseInt(BEDSIDE_STOCK);
    }
    var BEDDING_STOCK  = $("#BEDDING_STOCK").val();    //床品
    if( BEDDING_STOCK==""){
        BEDDING_STOCKT  = 0;
    } else {
        BEDDING_STOCKT  = parseInt(BEDDING_STOCK);
    }
    var OTHER_STOCK    = $("#OTHER_STOCK").val();      //其他
    if(OTHER_STOCK==""){
        OTHER_STOCKT  = 0;
    } else {
        OTHER_STOCKT    = parseInt(OTHER_STOCK);
    }
    var TOTAL_STOCK = MATTRESS_STOCKT+BED_STOCKT+BEDSIDE_STOCKT+BEDDING_STOCKT+OTHER_STOCKT;
    document.getElementById("TOTAL_STOCK").value = TOTAL_STOCK;
}

function formCheckedEx() {
    
    var VISIT_TYPE = $("#VISIT_TYPE").val();
    if(VISIT_TYPE==""){
      chkCanErrMsg("", "请选择'拜访方式'");
	  return false;
    }
	return true;
}
 


/**
 *@module 渠道管理-上报管理
 *@func   营销经理日报维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	//addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("channscoremkm.shtml?action=edit","CHANN_SCORE_MKM_ID");
	//下帧跳转
	//parent.window.gotoBottomPage();
 
});


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	return true;
}


function  getTotal(){
    
    var MATT_AMOUNT_STOCKT;
    var SOFT_BED_AMOUNT_STOCKT ;
    var OTHER_AMOUNT_STOCKT ;
    var TOTAL_AMOUNT_STOCKT   ;
    
    var MATT_AMOUNT_STOCKT = $("#MATT_AMOUNT").val();   //床垫
    if(MATT_AMOUNT_STOCKT==""){
       MATT_AMOUNT_STOCKT = 0; 
    } else{
       MATT_AMOUNT_STOCKT = parseInt(MATT_AMOUNT_STOCKT);
    }
    var SOFT_BED_AMOUNT_STOCK      = $("#SOFT_BED_AMOUNT").val();        //软床
    if(SOFT_BED_AMOUNT_STOCK==""){
       SOFT_BED_AMOUNT_STOCKT = 0;
    } else {
       SOFT_BED_AMOUNT_STOCKT      = parseInt(SOFT_BED_AMOUNT_STOCK);
    }
    var OTHER_AMOUNT_STOCK  = $("#OTHER_AMOUNT").val();    //床头柜
    if(OTHER_AMOUNT_STOCK==""){
       OTHER_AMOUNT_STOCKT = 0;
    } else{
       OTHER_AMOUNT_STOCKT  = parseInt(OTHER_AMOUNT_STOCK);
    }
    var TOTAL_AMOUNT_STOCKT = MATT_AMOUNT_STOCKT+SOFT_BED_AMOUNT_STOCKT+OTHER_AMOUNT_STOCKT;
    document.getElementById("TOTAL_AMOUNT").value = TOTAL_AMOUNT_STOCKT;
}


function getRate(){
   
   var ADVC_DEAL_AMOUNT = $("#ADVC_DEAL_AMOUNT").val();
   if(ADVC_DEAL_AMOUNT ==""){
     ADVC_DEAL_AMOUNT = 0;
   }
   var COST_AMOUNT = $("#COST_AMOUNT").val();
   if(COST_AMOUNT ==""){
     COST_AMOUNT  = 0;
   }
   if(ADVC_DEAL_AMOUNT !="" && COST_AMOUNT !=""){
     var RATE_AMOUNT = (ADVC_DEAL_AMOUNT/COST_AMOUNT).toFixed(2);
     $("#RATE_AMOUNT").val(RATE_AMOUNT);
   } 
}

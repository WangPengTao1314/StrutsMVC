
/**
 *@module 渠道管理-拜访管理
 *@func   拓展拜访表维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("expandvisit.shtml?action=edit","EXPAND_VISIT_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
});

function selApplyInfo(){
  selCommon('BS_125', false, 'APPLY_ID', 'RYXXID', 'forms[0]','APPLY_ID,APPLY_PERSON,APPLY_DEP','RYXXID,TNAME,BMMC','selectParamsTt')  
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
//保存
function saveT(){
	var myTable = $("#myTable tr:gt(0)");
	if(myTable.length == 0){
		parent.showErrorMsg("【现有卖场信息调查】必须填写！");
		return;
	} 
	
	var selRowId = getSelRowId();
	var ExistNames  = "";
	var ExistAddrs  = "";
	var ExistAreas  = "";
	var ExistRanges = "";
	var ExistCompetions = "";
	var ExistName    = document.getElementsByName("EXIST_STORE_NAME");
	for(var i=0;i<ExistName.length;i++){
	   if(ExistName[i].value==""){
	      parent.showErrorMsg("【现有卖场信息调查】名称必须填写！");
	      return;
	   }else{
	      ExistNames += ExistName[i].value+"|";
	   }
	}
	ExistNames=ExistNames.substr(0,ExistNames.length-1);
	 
	var ExistAddr    = document.getElementsByName("EXIST_STORE_ADDR");
	for(var i=0;i<ExistAddr.length;i++){
	   if(ExistAddr[i].value==""){
	   parent.showErrorMsg("【现有卖场信息调查】地址必须填写！");
	   return;
	   }else{
	   ExistAddrs += ExistAddr[i].value+"|";
	   }
	}
	ExistAddrs =ExistAddrs.substr(0,ExistAddrs.length-1);
	var ExistArea    = document.getElementsByName("EXIST_STORE_AREA");
	for(var i=0;i<ExistArea.length;i++){
	   if(ExistArea[i].value==""){
	   parent.showErrorMsg("【现有卖场信息调查】面积必须填写！");
	   return;
	   }else{
	   ExistAreas += ExistArea[i].value+"|";
	   }
	}
	ExistAreas =ExistAreas.substr(0,ExistAreas.length-1);
	var ExistRange   = document.getElementsByName("EXIST_STORE_RANGE");
	for(var i=0;i<ExistRange.length;i++){
	   if(ExistRange[i].value==""){
	   parent.showErrorMsg("【现有卖场信息调查】档次排名必须填写！");
	   return;
	   } else {
	   ExistRanges += ExistRange[i].value+"|";
	   }
	}
	ExistRanges =ExistRanges.substr(0,ExistRanges.length-1);
	var ExistCompetion   = document.getElementsByName("EXIST_STORE_COMPETITION");
	for(var i=0;i<ExistCompetion.length;i++){
	   ExistCompetions += ExistCompetion[i].value+"|";
	}
	ExistCompetions =ExistCompetions.substr(0,ExistCompetions.length-1);
 
    var NewNames      = "";
    var NewAddrs      = "";
    var NewAreas      = "";
    var NewRangesT    = "";
    var NewDates      = "";
    
    var NewName  = document.getElementsByName("NEW_STORE_NAME");
	for(var i=0;i<NewName.length;i++){
	   NewNames += NewName[i].value+"|";
	}
	NewNames =NewNames.substr(0,NewNames.length-1);
	var NewAddr  = document.getElementsByName("NEW_STORE_ADDR");
	for(var i=0;i<NewAddr.length;i++){
	   NewAddrs += NewAddr[i].value+"|";
	}
	NewAddrs =NewAddrs.substr(0,NewAddrs.length-1);
	var NewArea  = document.getElementsByName("NEW_STORE_AREA");
	for(var i=0;i<NewArea.length;i++){
	   NewAreas += NewArea[i].value+"|";
	}
	NewAreas =NewAreas.substr(0,NewAreas.length-1);
	var NewRangeT  = document.getElementsByName("NEW_STORE_RANGE");
	for(var i=0;i<NewRangeT.length;i++){
	   NewRangesT += NewRangeT[i].value+"|";
	}
    NewRangesT =NewRangesT.substr(0,NewRangesT.length-1);
    
	var NewDate  = document.getElementsByName("NEW_STORE_DATE");
	for(var i=0;i<NewDate.length;i++){
	   NewDates += NewDate[i].value+"|";
	}
	NewDates =NewDates.substr(0,NewDates.length-1);
	var ChannNames = "";
	var BussScopes = "";
	var StoreNames = "";
	var PurposeNames = "";
	var Tels       = "";
	
	var ChannName  = document.getElementsByName("CHANN_NAME");
	for(var i=0;i<ChannName.length;i++){
	   ChannNames += ChannName[i].value+"|";
	}
	ChannNames =ChannNames.substr(0,ChannNames.length-1);
	var BussScope  = document.getElementsByName("BUSS_SCOPE");
	for(var i=0;i<BussScope.length;i++){
	   BussScopes += BussScope[i].value+"|";
	}
	BussScopes =BussScopes.substr(0,BussScopes.length-1);
	var StoreName  = document.getElementsByName("STORE_NAME");
	for(var i=0;i<StoreName.length;i++){
	   StoreNames += StoreName[i].value+"|";
	}
	StoreNames =StoreNames.substr(0,StoreNames.length-1);
	var PurposeName  = document.getElementsByName("PURPOSE_STORE_NAME");
	for(var i=0;i<PurposeName.length;i++){
	   PurposeNames += PurposeName[i].value+"|";
	}
	PurposeNames =PurposeNames.substr(0,PurposeNames.length-1);
	var Tel  = document.getElementsByName("TEL");
	for(var i=0;i<Tel.length;i++){
	   Tels += Tel[i].value+"|";
	}
	Tels =Tels.substr(0,Tels.length-1);
 
	var  TITLE = $("#TITLE").val();
	var  EXPAND_VISIT_ID = $("#EXPAND_VISIT_ID").val();
	var  EXPAND_VISIT_NO  = $("#EXPAND_VISIT_NO").val();
	
	var  EME_DEGREEs = document.getElementsByName("EME_DEGREE");
	var  EME_DEGREE  = "";
	for(var i=0;i<EME_DEGREEs.length;i++){
	    if(EME_DEGREEs[i].checked){
	       EME_DEGREE = EME_DEGREEs[i].value;
	    } 
	}
	var APPLY_PERSON   = $("#APPLY_PERSON").val();
	var APPLY_DEP      = $("#APPLY_DEP").val();
	var APPLY_DATE     = $("#APPLY_DATE").val();
	//var VISIT_PEOPLE   = $("#XM").val();
	var VISIT_DATE     = $("#VISIT_DATE").val();
	var CHANN_REMARK   = $("#CHANN_REMARK").val();
	var SOLUTION       = $("#SOLUTION").val();
	var SUPPORT_DEMAND = $("#SUPPORT_DEMAND").val();
	var COMPETITION_INFO = $("#COMPETITION_INFO").val();
	var PROCESS        = $("#PROCESS").val();
	var REMARK         = $("#REMARK").val();
	var ATT_PATH       = $("#ATT_PATH").val();
	var ADVANTAGES     = $("#ADVANTAGES").val();
	var VISIT_TYPE     = $("#VISIT_TYPE").val();
	if(APPLY_PERSON==""){
        parent.showErrorMsg("申请人不能为空");
	}else if("" == ExistNames){
		parent.showErrorMsg("【现有卖场信息调查】必须填写！");
		return;
	}
    else {
	    var VISIT_TYPE = $("#VISIT_TYPE").val();
	    if(VISIT_TYPE==""){
	      chkCanErrMsg("", "请选择'拜访方式'");
		  return false;
	    } else {
			$.ajax({
				url: "expandvisit.shtml?action=edit",
				type:"POST",
				dataType:"text",
				data:{"ExistNames":ExistNames,"ExistAddrs":ExistAddrs,"ExistAreas":ExistAreas,"ExistRanges":ExistRanges,"ExistCompetions":ExistCompetions,"TITLE":TITLE,"EXPAND_VISIT_ID":EXPAND_VISIT_ID,"EME_DEGREE":EME_DEGREE,"APPLY_PERSON":APPLY_PERSON,"APPLY_DEP":APPLY_DEP,"APPLY_DATE":APPLY_DATE,"VISIT_DATE":VISIT_DATE,"CHANN_REMARK":CHANN_REMARK,"SOLUTION":SOLUTION,"SUPPORT_DEMAND":SUPPORT_DEMAND,"COMPETITION_INFO":COMPETITION_INFO,"PROCESS":PROCESS,"REMARK":REMARK,"ATT_PATH":ATT_PATH,"NewNames":NewNames,"NewAddrs":NewAddrs,"NewAreas":NewAreas,"NewRanges":NewRangesT,"ChannNames":ChannNames,"BussScopes":BussScopes,"StoreNames":StoreNames,"PurposeNames":PurposeNames,"Tels":Tels,"NewDates":NewDates,"ADVANTAGES":ADVANTAGES,"VISIT_TYPE":VISIT_TYPE},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						saveSuccess("保存成功","expandvisit.shtml?action=toFrames");
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});
		}
	}
}


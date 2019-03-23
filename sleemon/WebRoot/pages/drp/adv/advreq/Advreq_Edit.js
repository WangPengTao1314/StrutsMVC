/**
 * @prjName:喜临门营销平台
 * @fileName:Advreq_Edit
 * @author ghx
 * @time   2014-07-15  
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

function channRankSel(){
	
	var selected = $("#SEL_RANK  option:selected");
	$("#CHANN_RANK").val(selected.text());
	$("#RANK_SCALE").val(selected.val());
	countApply();
}

function  CompareSDate(){
    //广告投放开始时间
    var ADV_START_DATE = $("#ADV_START_DATE").val();
	if(null == ADV_START_DATE || "" == ADV_START_DATE){
		return;
	}
	//广告投放结束时间
	var ADV_END_DATE   = $("#ADV_END_DATE").val();
	if(null == ADV_END_DATE   || "" == ADV_END_DATE){
		return;
	}
    ADV_START_DATE = ADV_START_DATE.replace("-","/"); 
    ADV_END_DATE   = ADV_END_DATE.replace("-","/");  
	if(ADV_END_DATE<ADV_START_DATE){
	    parent.showErrorMsg("广告投放开始时间应该小于投放结束时间");
		$("#ADV_START_DATE").val("");
		return ;
	} 
}

function  CompareEDate(){
    //广告投放开始时间
    var ADV_START_DATE = $("#ADV_START_DATE").val();
	if(null == ADV_START_DATE || "" == ADV_START_DATE){
		return;
	}
	//广告投放结束时间
	var ADV_END_DATE   = $("#ADV_END_DATE").val();
	if(null == ADV_END_DATE   || "" == ADV_END_DATE){
		return;
	}
    ADV_START_DATE = ADV_START_DATE.replace("-","/"); 
    ADV_END_DATE   = ADV_END_DATE.replace("-","/");  
	if(ADV_END_DATE<ADV_START_DATE){
	    parent.showErrorMsg("广告投放结束时间应该大于投放开始时间");
		$("#ADV_END_DATE").val("");
		return ;
	} 
}

//计算向总部申请支持费用
function countApply(){
	var total = $("#ADV_TOTAL_AMOUNT").val(); 
	if("" == total || null == total){
		$("#HEAD_SUP_AMOUNT").val(total);
		return;
	}
	total=isNaN(total)?0:parseFloat(total);//总预算金额	
	var apply = 0;
	
	if(total != 0){
		var rate = $("#SEL_RANK").val();
		if("" == rate || null == rate){
			$("#HEAD_SUP_AMOUNT").val(rate);
			return;
		}
		rate=isNaN(rate)?0:parseFloat(rate);//比例
		
		if(rate !=0 ){
			apply = total*rate;
		}
	}
	$("#HEAD_SUP_AMOUNT").val(apply);
}
/**
 * @prjName:喜临门营销平台
 * @fileName:Trainreq_Edit
 * @author ghx
 * @time   2014-02-28 14:01:04 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

function formCheckedEx(){
	//申请参加人数
	var maxNum = $("#FIXED_PSON_NUM").val();
	var psonNum = $("#REQ_JOIN_NUM").val();
	
	maxNum = parseInt(maxNum);
	if(isNaN(maxNum)){
		maxNum = 0;
	}
	
	if(maxNum > 0){
		psonNum = parseInt(psonNum);
		if(isNaN(psonNum)){
			psonNum = 0;
		}
		
		if(psonNum <= 0){
		     parent.showErrorMsg(utf8Decode("申请参加人数必须大于零！"));
		     return false;
		}else if(psonNum > maxNum){
			 parent.showErrorMsg(utf8Decode("申请参加人数不可超过额定人数【额定人数为 "+ maxNum +" 】！"));
			 return false;
		}
	}
	
   return true;
}
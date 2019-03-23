
/**
 * @module 渠道培训管理
 * @func 培训课程维护
 * @version 1.1
 * @time   2014-07-04 
 * @author ghx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("traincourse.shtml?action=edit","TRAIN_COURSE_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
});


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
				
		
		//培训类型
		if($("#TRAIN_TYPE").val()==null || $("#TRAIN_TYPE").val() == ""){
		      parent.showErrorMsg(utf8Decode("请选择培训类型！"));
		      return false;
		}
		
		var startTime = $("#TRAIN_TIME_BEG").val();
		var endTime = $("#TRAIN_TIME_END").val();
		if(endTime<startTime){
			parent.showErrorMsg("'培训结束时间'不得小于'培训开始时间'");
			return false;
		}
		
		//验证图片文件名长度
		if($("#PIC_PATH").val()!=null && $("#PIC_PATH").val() != ""){
	      	   if($("#PIC_PATH").val().length>260){
	      		    parent.showErrorMsg(utf8Decode("上传图片文件名过长！"));
	      		    return false;
	      	   }
		}
	
	    return true;
	}
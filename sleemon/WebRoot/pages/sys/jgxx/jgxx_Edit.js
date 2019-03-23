/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	var selId = parent.document.getElementById("selRowId").value;
	if(null == selId || "" == selId){
		var treeNodes = parent.leftcontent.getSelNodes();
		//modify by zhuxw
		//old version
		//if("" != treeNodes&&){
		if(treeNodes!=null&&treeNodes.id!=null){
			$("#SJJGID").val(treeNodes.id);
			var jgbhname = treeNodes.name;
			var index = jgbhname.indexOf("(");
			$("#SJJGBH").val(jgbhname.substring(0,index));
			$("#SJJGMC").val(jgbhname.substring(index+1,jgbhname.length-1));
		}
	}
	
	$("#save").click(function(){
		if(!textareaFormCheck('mainForm')){
			return;
		}
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "jgxx.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"JGXXID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					var jgxxId = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="jgxx.shtml?action=toList&JGXXID="+jgxxId+parent.window.reqParamsEx();
					//左边树节点变更
					var sjjg = $("#SJJGID").val();
					if(null == selId || "" == selId){//新增记录
						var newNode = {id:jgxxId,
									   name:$("#JGBH").val()+"("+$("#JGMC").val()+")", 
									   pId:sjjg};
						parent.leftcontent.addTreeNodes(sjjg,newNode);
					}else{//修改记录
						parent.leftcontent.updateNodes(jgxxId,$("#JGBH").val()+"("+$("#JGMC").val()+")");
					}
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("jgxx.shtml?action=edit","JGXXID","jgxx.shtml?action=toList","mainForm");
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();

function textareaFormCheck(chkform) {
	var flag = true;
    var textareas = $("#"+chkform).find("textarea");
	textareas.each(function(){
		if (!CheckStringInput(this)) {
			flag = false;
			return flag;
		}
	});
	return flag;
}

function formCheckedEx(){
	if($("#CZ").val()!=null && $("#CZ").val() != ""){
        var re1 = new RegExp(/^((0\d{2,3})-)|(\d{7,8})(-(\d{3,}))?$/);//传真匹配
        var TEL = re1.test($("#CZ").val());
        if(!TEL ){
      	   parent.showErrorMsg(utf8Decode("传真格式输入不正确！"));
           return false;
        }
	}
	if($("#DZYJ").val()!=null && $("#DZYJ").val() != ""){
        var re1 = new RegExp(/^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/);//email匹配
        var email = re1.test($("#DZYJ").val());
        if(!email ){
      	   parent.showErrorMsg(utf8Decode("电子邮件格式输入不正确！"));
           return false;
        }
	}
	if($("#DH").val()!=null && $("#DH").val() != ""){
	        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
	        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
	        var TEL1 = re1.test($("#DH").val());
	        var TEL2 = re2.test($("#DH").val());
	        if(!TEL1&&!TEL2 ){
	      	   parent.showErrorMsg(utf8Decode("电话格式输入不正确！"));
	           return false;
	        }
		}
	if($("#YZBM").val()!=null && $("#YZBM").val() != ""){
	        var re1 = new RegExp(/^[0-9]{6}$/);//邮政编码匹配
	        var POST = re1.test($("#YZBM").val());
	        if(!POST ){
	      	   parent.showErrorMsg(utf8Decode("邮政编码格式输入不正确！"));
	           return false;
	        }
		}
	return true;

}




/**
 *@module 基础数据
 *@func 部门信息维护
 *@version 1.1
 *@author 吴亚林
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
		//if("" != treeNodes){
		if(treeNodes!=null&&treeNodes.id!=null){
		    var flag = treeNodes.def1;//判断是机构还是部门
			if(1==flag){//部门
			   $("#SJBM").val(treeNodes.id);
			   var jgbhname = treeNodes.name;
			   var index = jgbhname.indexOf("(");
			   $("#SJBMMC").val(jgbhname.substring(index+1,jgbhname.length-1));
			   $.ajax({
		             url: "bmxx.shtml?action=selectZTbyId&BMXXID="+treeNodes.id,
		             type:"POST",
		             dataType:"text",
		             complete: function(xhr){
		                eval("jsonResult = "+xhr.responseText);
		                   if(jsonResult.success===true){
		                       var arr = jsonResult.messages.split(',');
		                       $('#JGMC').val(arr[0]);
		                       $('#JGXXID').val(arr[1]);
		                       $('#ZTXXID').val(arr[2]);
		                   }
		             }
	            });
			}else{
			   $("#JGXXID").val(treeNodes.id);
			   var jgbhname = treeNodes.name;
			   var index = jgbhname.indexOf("(");
			   $("#JGMC").val(jgbhname.substring(index+1,jgbhname.length-1));
			}
		}
	}
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "bmxx.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"BMXXID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
//					var BMXXID = utf8Decode(jsonResult.messages);
//					//上帧页面跳转至编辑后的记录
//					alert(parent.window.reqParamsEx());
//					window.parent.topcontent.location="bmxx.shtml?action=toList&BMXXID="+BMXXID+parent.window.reqParamsEx();
//					//左边树节点变更
//					var sjjg = $("#JGXXID").val();
//					if(null == selId || "" == selId){//新增记录
//						var newNode = {id:BMXXID,
//									   name:$("#BMBH").val()+"("+$("#BMMC").val()+")", 
//									   pId:sjjg};
//						parent.leftcontent.addTreeNodes(sjjg,newNode);
//					}else{//修改记录
//						BMXXID = $("#BMXXID").val();
//						parent.leftcontent.updateNodes(BMXXID,$("#BMBH").val()+"("+$("#BMMC").val()+")");
//					}
					window.parent.topcontent.pageForm.submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，FormId
	//mtbSaveListener("bmxx.shtml?action=edit","BMXXID","bmxx.shtml?action=toList","mainForm");
});

function selSSJG(){
    if($("#SJBMMC").val()!= null || $("#SJBMMC").val() != ""){
         $("#SJBMMC").val("");
    }
    selCommon('System_2', false, 'JGXXID', 'JGXXID', 'forms[0]','JGMC,JGXXID,ZTXXID', 'JGMC,JGXXID,ZTXXID', 'JGZT');
}
function selSJBM(){
	var jgxxid = $("#JGXXID").val();
	if(jgxxid ==""||null==jgxxid){
		parent.showErrorMsg("请先选择所属机构!");
		return;
	}else{
		var BMXXID=$("#BMXXID").val();
		if(""!=BMXXID){
			$("#con").val(" jgxxid='"+jgxxid+"' and  BMZT in ('启用','制定') and DELFLAG=0 and BMXXID != '"+BMXXID+"'");
		}else{
			$("#con").val(" jgxxid='"+jgxxid+"' and  BMZT in ('启用','制定') and DELFLAG=0 ");
		}
		
	}
	selCommon('System_1', false, 'SJBM', 'BMXXID', 'forms[0]','SJBMMC,SJBM', 'BMMC,BMXXID', 'con');
}
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
   // var rel = new RegExp(\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*);
   
   if($("#DZYJ").val()!=null && $("#DZYJ").val() != ""){
      var re1 = new RegExp(/^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/);//email匹配
      var email = re1.test($("#DZYJ").val());
      if(!email ){
         parent.showErrorMsg(utf8Decode("电子邮件格式输入不正确！"));
         return false;
      }
   }
    
    /**
    if($("#DH").val() != null && $("#DH").val() != ""){
        //var re2 = new RegExp(/d{3}-d{8}|d{4}-d{7}/);//国内电话号码
        var re2 = new RegExp(/^\d{3}-^\d{8}|^\d{4}-^\d{7}/);
        var phone = re2.test($("#DH").val());
        if(!phone){
           parent.showErrorMsg(utf8Decode("电话号码格式输入不正确！"));
           return false;
        }
    }
    **/
    if($("#YZBM").val() != null && $("#YZBM").val() != ""){
        var re3 = new RegExp(/[1-9[0-9]{4,}/);//邮政编码
        var post = re3.test($("#YZBM").val());
        if(!post){
           parent.showErrorMsg(utf8Decode("邮政编码格式输入不正确！"));
           return false;
        }
    }
    
    return true;
    
}


function formCheckedEx(){
	
	var YZBM = $("#YZBM");
	var YZBMV = YZBM.val();
	YZBMV = $.trim(YZBMV);
	if(YZBMV != null && YZBMV != ""){
        var re1 = /^[0-9]{6}$/  //邮政编码匹配
        var test1 = re1.test(YZBMV);
        if(!test1){
      	   parent.showErrorMsg("'"+YZBM.attr("label")+"'输入不正确！");
           return false;
        }
	}
	
	var DH = $("#DH");
	var DHV = DH.val();
	DHV = $.trim(DHV);
	if(DHV != null && DHV != ""){
        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
        var test1 = re1.test(DHV);
        var test2 = re2.test(DHV);
        if(!test1 && !test2){
      	   parent.showErrorMsg("'"+DH.attr("label")+"'输入不正确！");
           return false;
        }
	}
	
	var CZ = $("#CZ");
	var CZV = CZ.val();
	CZV = $.trim(CZV);
	if(CZV != null && CZV != ""){
        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
        var test1 = re1.test(CZV);
        if(!test1){
      	   parent.showErrorMsg("'"+CZ.attr("label")+"'输入不正确！");
           return false;
        }
	}
	

	
	
	return true;

}

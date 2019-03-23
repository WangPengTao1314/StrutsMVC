
function getChild(){
  var BUSS_SCOPE = $("#BUSS_SCOPE").val();
  if(BUSS_SCOPE==""){
      parent.showErrorMsg("请选择品牌类型");
      return;
  }else {
   parent.window.gotoBottomPage("label_3");
  }
}
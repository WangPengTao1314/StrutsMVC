

function  getYear(params){
    var len = window.parent.bottomcontent.mainForm.document.getElementsByName("PLAN_YEAR").length;
    for(var i=0;i<len;i++){
       window.parent.bottomcontent.mainForm.document.getElementsByName("PLAN_YEAR")[i].value=params.value;
    }
}

function  getSaleAmount(){
    var len = window.parent.bottomcontent.mainForm.document.getElementsByName("PLAN_SALE_AMOUNT").length;
    var str = 0;
    for(var i=0;i<len;i++){
      if(window.parent.bottomcontent.mainForm.document.getElementsByName("PLAN_SALE_AMOUNT")[i].value !=""){
          var amount = parseFloat(window.parent.bottomcontent.mainForm.document.getElementsByName("PLAN_SALE_AMOUNT")[i].value);
          str+=amount;
      }
    }
    window.parent.topcontent.mainForm.document.getElementById("PLAN_SALE_AMOUNT_TOTAL").value=str;
}


function getChannAmount(){
    var len = window.parent.bottomcontent.mainForm.document.getElementsByName("CHANN_SALE_AMOUNT").length;
    var str = 0;
    for(var i=0;i<len;i++){
      if(window.parent.bottomcontent.mainForm.document.getElementsByName("CHANN_SALE_AMOUNT")[i].value !=""){
          var amount = parseFloat(window.parent.bottomcontent.mainForm.document.getElementsByName("CHANN_SALE_AMOUNT")[i].value);
          str+=amount;
      }
    }
    window.parent.topcontent.mainForm.document.getElementById("CHANN_SALE_AMOUNT_TOTAL").value=str;
}

function  getRRXX(){
    gotoBottomPage("label_3");
}

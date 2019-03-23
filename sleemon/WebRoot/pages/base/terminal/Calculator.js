 var rlt = 0.0;
 var middle = 0.0;
 var flag = 0;    //1:+ 2:- 3:* 4:/
 var equalflag = 1; //0:button equal not pressed  1:button equal pressed
 var changeflag = 0;
 
 
 
 function btShowValue(obj){
	  var tmp = document.getElementById("result");
	  var v = $(obj).val();
	  var realv = $(obj).attr("realvalue");
	  if(null == realv || "" == realv){
		  realv = $(obj).val();
	  }
	  var PRICE_EXPRESS = document.getElementById("PRICE_EXPRESS");
	  if(changeflag == 0){
		  if(tmp.value.indexOf(".") > 0 || parseFloat(tmp.value) != 0){
			   tmp.value += v;
			   PRICE_EXPRESS.value += realv;
		  }else{
			  tmp.value = v;
			  PRICE_EXPRESS.value = realv;
		  }
	  }else{
		  tmp.value = v;
		  PRICE_EXPRESS.value = realv;
          changeflag = 0;
	  }
 }
 
 
 function BtPoint(){
   var tmp = document.getElementById("result");
   var PRICE_EXPRESS = document.getElementById("PRICE_EXPRESS"); 
   if((tmp.value.indexOf(".") < 0) && (tmp.value.length > 0)) {
      tmp.value += ".";
      PRICE_EXPRESS.value += ".";
   }
 }
  
function BtClean(){
  var tmp = document.getElementById("result");
  tmp.value = "";
  
  middle = 0.0;
  rlt = 0.0;
  flag = 0;
  equalflag = 1;
  changeflag = 1;
}
 

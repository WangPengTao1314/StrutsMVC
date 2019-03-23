//使用说明：
//1.引入此JS,
//2.调用initKeyBoardValue方法设置初始行列值
//3.实现重新获得焦点后，要设计行列onfocus="setRowAndCol('+rownum+',13)" 

var currentLine=1;
var currentCol=2;


function setRowAndCol(initrow,initcol){
	currentLine = initrow;
	currentCol = initcol;
}

//如有模块要加快捷方式，要初使化行，列值
function initKeyBoardValue(initrow,initcol){
	currentLine = initrow;
	currentCol = initcol;
}

document.onkeydown=function(e){
  e=window.event||e;
  switch(e.keyCode){
    case 37: //左键
     setLeftCurrentCol();
      changeItem();
      break;
    case 38: //向上键
      setUpCurrentLine();
      changeItem();
      break;
    case 39: //右键
      setRightCurrentCol();
      changeItem();
      break;
    case 40: //向下键
      setDownCurrentLine();
      changeItem();
      break;
    default:
      break;
  }
}

function getTableCol(){
   var table =document.getElementById("jsontb");
   var rows = table.rows.length;
   var colums = table.rows[0].cells.length;
   return colums;
}


function getTableRow(){
   var table =document.getElementById("jsontb");
   var rows = table.rows.length;
   return rows;
}

function setLeftCurrentCol(){
	currentCol--;
	 if(currentCol<=2){
		 currentCol =2;
	 }
}

function setRightCurrentCol(){
	 currentCol++;
	 var col = getTableCol();
	 if(currentCol>col){
		 currentCol = col;
	 }
}


function setUpCurrentLine(){
	currentLine--;
	if(currentLine<=0){
		 currentLine =1;
	 }
}

function setDownCurrentLine(){
	currentLine++;
	 var row = getTableRow();
	 if(currentLine>=row){
		 currentLine = row-1;
	 }
}

//方向键调用
function changeItem(){
  if(document.all)
    var it=document.getElementById("jsontb").children[0];
  else
    var it=document.getElementById("jsontb");
  for(i=0;i<it.rows.length;i++){
    it.rows[i].className="";
  }
  if(currentLine<0){
    currentLine=it.rows.length-1;
  }
  if(currentLine==it.rows.length){
  	currentLine=0;
  }
  var objtab=document.all.jsontb;
  var objrow=objtab.rows[currentLine].getElementsByTagName("INPUT");
  if(typeof(objrow[currentCol])=='undefined'){
	  return;
  }
  if(currentCol<0){
    currentCol=objrow.length-1;
  }else if(currentCol==objrow.length){
    currentCol=0;
  }
  objrow[currentCol].select();
}

//---------------------------------------------------------------
//声明全局变量
//---------------------------------------------------------------
var folderNode = folder;//--定义左边选单
var folderlock = true;//---声明左边选单是否锁定
var foldervar = 0     //---声明左边选单的位置变量
var foldershow = false;
var folderhidden = false; 
var isinfolder = false;

var NodeW = 80;       //---声明节点宽度
var NodeH = 58;       //---声明节点高度
var NodeZoneW = 60 ;   //---声明判断节点左右灵敏范围
var NodeZoneH = 30 ;   //---声明判断节点上下灵敏范围

var curElement;       //---声明选中的元素
var moveElement;      //---声明选中的节点元素
var faketitle;        //---声明拖动条中的名称
var PX = 0;           //---声明滚动条的x轴位移
var PY = 0;           //---声明滚动条的y轴位移
var chooseNode=null;  //---声明选中的Node
var fakeType=0;     //---声明选中的类型

var proNode = processNode; //定义当前实例节点


var selType=new Array("人员","部门","角色","组");

var needShowDlg=false;    //--定义拖动条是否需要递交

var sFlow=new Flow();     //--定义流程
 
var pNode=l1.parentNode;  //--定义复制节点的父亲节点

var listNode=list;        //--定义复制节点
var listNodeImg=listpic;  //--定义复制节点图标
var listNodeTxt=listname; //--定义复制节点文字


var fakeNodeImg=fakepic;   //--定义拖动条图标
var fakeNodeTxt=fakename;  //--定义拖动条文字
var fakeNodeTable=faketable; //--定义拖动条表格

var leftLineNode=l1;      //--定义左斜线
var rightLineNode=l2;     //--定义右斜线
var middleLineNode=l3;    //--定义中直线
var acuteNode=acute;      //--定义线段箭头
var menuLayer= set1;      //--定义右键层

var inWhoLeft= null;         //--记录当前位置在who的左边
var inWhoRight= null;        //--记录当前位置在who的右边
var inWhoBottom= null;       //--记录当前位置在who的底部 
var inWhoTop= null;          //--记录当前位置在who的上部
var inWho = null;            //--记录当前位置在who的内部


//---------------------------------------------------------------
//定义Node类
//---------------------------------------------------------------

function Node(nID,nName,nType,nX,nY,nOperaName,nAssessor,nOperaFlag)
{
this.n_name = nName;                 //声明节点名称
this.n_finish_yn = false;            //声明节点是否已完成
this.n_id = nID;                     //声明节点id号
this.n_type=nType;                   //声明节点类型
this.height = NodeH;                 //声明节点高度
this.weight = NodeW;                 //声明节点宽度
this.sx = nX;                        //声明节点X轴坐标 
this.sy = nY;                        //声明节点Y轴坐标
this.nNode = new Array();            //声明节点的后续节点数组
this.displacement = 0;               //表示画出节点与其父亲节点的位移
this.n_operaName = nOperaName;
this.n_assessor = nAssessor;
this.n_operaFlag = nOperaFlag;
}


//---------------------------------------------------------------
//定义Flow类
//---------------------------------------------------------------
function Flow()
{
this.sNode = new Array();      //---定义流程中的节点
}


//---------------------------------------------------------------
//画出流程图
//---------------------------------------------------------------

//---创建sFlow类
function createFlow() {

    var nodeLength=id.length;
	var pathLength=path.length;
	for (var i=0;i<nodeLength;i++)
	{
       //---根据导入的数组生成每个节点类，并加入sFlow中
	   var tempNode=new Node(id[i],name[i],type[i],iX[i],iY[i],operaName[i],assessor[i],operaFlag[i]);
         //---查找路径数组，找出该节点的后续节点，加入改节点的后续节点数组中
		 var tempnNode=0;
		 for (var j=0;j<pathLength;j++)
		 {
			 var tempPath=path[j];
			 var cpot=tempPath.indexOf("-");
			 if (cpot!=-1)
			 {
				 var startNodeID=tempPath.substring(0,cpot);
				 var endNodeID=tempPath.substring(cpot+1);
                 if (startNodeID==id[i])
                 {
					 //--找到一条符合条件的路径
					 tempNode.nNode[tempnNode]=endNodeID;
					 tempnNode=tempnNode+1;
                 }				
			 }
			 else 
			 {
			     window.status="无效的节点路径"+tempPath;
				 break;
			 }
		 }
       sFlow.sNode[i]=tempNode;
	   
	}
}

function findNodeByID(nodeID) {
   for (var i=0;i<id.length;i++)
   {
	   if (sFlow.sNode[i].n_id==nodeID) return sFlow.sNode[i];
   }
}

//---画出sFlow的图形
function drawFlow() {
   //---画出所有的节点
   for (var i=0;i<id.length;i++)
   {
	   var startNode=sFlow.sNode[i];
	   if (startNode.n_type!="-3")
	   {
          	   drawNode(startNode);	
	   }   
	   //---画出该节点的后续路径
	   for (var j=0;j<sFlow.sNode[i].nNode.length;j++)
	   {
           //得到后续节点ID，找到该节点
           var endNode=findNodeByID(startNode.nNode[j]);
		   drawPath(startNode,endNode);
	   }
   }
}

//---画出node的图形
//---参数为node
function drawNode(cNode) {
   //---改变节点显示文字
   //alert(cNode.n_name+cNode.n_name.slice(0,1));

   listNodeTxt.title = cNode.n_name.length>3?(cNode.n_name.slice(0,1)=="<"?"":cNode.n_name):"";
   listNodeTxt.innerHTML=stringCut(cNode.n_name)+'<br>'+cNode.n_operaName;  
   //--判断节点类型，改变节点图标
   switch (cNode.n_type)
        {
        case -1:
        listNodeImg.src="/sleemon/pages/sys/spflow/image/start.gif";
        //listNodeTxt.innerHTML="申请人";
        break;
        case -2:
        listNodeImg.src="/sleemon/pages/sys/spflow/image/end.gif";
				listNodeTxt.innerHTML="结束";
        break; 		
        case 0:
        listNodeImg.src="/sleemon/pages/sys/spflow/image/user.gif";
        break; 
        case 1:
        listNodeImg.src="/sleemon/pages/sys/spflow/image/depart.gif";
        break;
        case 2:
        listNodeImg.src="/sleemon/pages/sys/spflow/image/role.gif";
		break;
        case 3:
        listNodeImg.src="/sleemon/pages/sys/spflow/image/group.gif";
		break;
        default: 
      }
      
  if (cNode.n_operaFlag == 1) {
  	listNodeImg.style.backgroundImage='url(/sleemon/pages/sys/spflow/image/pass1.gif)';
  } else if (cNode.n_operaFlag == -1) {
  	listNodeImg.style.backgroundImage='url(/sleemon/pages/sys/spflow/image/terminate.gif)';
  } else if (cNode.n_operaFlag == 2) {
  	listNodeImg.style.backgroundImage='url(/sleemon/pages/sys/spflow/image/back.gif)';
	} else if (cNode.n_operaFlag == 3) {
  	listNodeImg.style.backgroundImage='url(/sleemon/pages/sys/spflow/image/jump.gif)';
	} else {
  	listNodeImg.style.backgroundImage='';
	}
	

  //---复制listNode
  var tempNode=listNode.cloneNode(true);
  //---改变tempNode的位置
  tempNode.style.posTop=cNode.sy;
  tempNode.style.posLeft=cNode.sx;
  //---改变tempNode的显示方式
  tempNode.style.visibility="";
  tempNode.title = cNode.n_assessor;
  //tempNode.style.backgroundImage='url(image/pass1.gif)';
  
  for (i=0; i<proNode.length; i++) {
  	if (proNode[i] == cNode.n_id) {
  		tempNode.style.backgroundColor = "#009933";
  	}
  }
  
  //---添加tempNode
  pNode.appendChild(tempNode);   
}

//---画出路径
//---参数为起始路径和结束路径
function drawPath(startNode,endNode) {
	var startX=0;
	var startY=0;
	var endX=0;
	var endY=0;
	var NodeWP = 50;
  endX=endNode.sx+NodeWP/2;
	endY=endNode.sy;
	startX=startNode.sx+NodeWP/2;
	startY=startNode.sy+NodeH;
	//startY=startNode.sy;
  //alert("sX:"+startX+"sY"+startY+"ex"+endX+"eY"+endY);
	if (endNode.n_type==-3)
	{
		endY=endNode.sy+NodeH/2;
	}else {
	drawAcute(endX,endY);
	}
	if (startNode.n_type==-3)
	{
		startY=startNode.sy+NodeH/2; 
	}

	/*
	if (startX==endX)
	{
		//--路径为直线
		drawLine(middleLineNode,startX,startY,1,endY-startY);
	}else if (startX>endX)
	{
		//--路径为左斜线
        drawLine(leftLineNode,endX,startY,startX-endX,endY-startY);
	}else if (startX<endX)
	{
		//--路径为右斜线
        drawLine(rightLineNode,startX,startY,endX-startX,endY-startY);
	}
	*/
	startY = startY+5;

	drawLine(startX,startY,endX,endY);
}

function drawLine2(lineNode,lineX,lineY,lineW,lineH) {
    //---复制lineNode
	var tempNode=lineNode.cloneNode(true);
	tempNode.style.posLeft=lineX;
	tempNode.style.posTop=lineY;
	tempNode.style.posWidth=lineW;
	tempNode.style.posHeight=lineH;
	tempNode.style.visibility="";
    //---添加tempNode
    pNode.appendChild(tempNode);
}

function drawLine(sX,sY,eX,eY) {
/*
   var MyLine = document.createElement("vml:line");
   MyLine.from =sX+"px,"+(sY-18)+"px";
   //MyLine.from ="0px,-18px";
   MyLine.to  = eX+"px,"+(eY-18)+"px";
*/
   //alert("sX:"+sX+"sY"+sY+"ex"+eX+"eY"+eY);
   var mY=eY-(NodeZoneH/4);
   var Line1= document.createElement("vml:line");
   Line1.strokecolor="#0033cc";
   Line1.from = sX+"px,"+(sY-45)+"px";
   Line1.to = sX+"px,"+(mY-40)+"px";
   var Line2= document.createElement("vml:line");
   Line2.strokecolor="#0033cc";
   Line2.from = sX+"px,"+(mY-40)+"px";
   Line2.to = eX+"px,"+(mY-40)+"px";
   var Line3= document.createElement("vml:line");
   Line3.strokecolor="#0033cc";
   Line3.from = eX+"px,"+(mY-40)+"px";
   Line3.to = eX+"px,"+(eY-40)+"px";   
   //alert("1");
   //alert(Line1.from+"Line1to:"+Line1.to);
   pNode.appendChild(Line1);
   //alert("2");
   //alert(Line2.from+"Line2to:"+Line2.to);
   pNode.appendChild(Line2);
   //alert("3");
   //alert(Line3.from+"Line3to:"+Line3.to);
   pNode.appendChild(Line3);
   /*sX=sX+100;
   sY=sY-100;
   eX=eX+100;
   eY=eY-100;
   alert("sX:"+sX+"sY"+sY+"ex"+eX+"eY"+eY);
   var line1 = document.createElement("vml:polyline");
   line1.strokecolor="#0033cc";
   line1.points=sX+"pt,"+sY+"pt,"+sX+"pt,"+eY+"pt,"+eX+"pt,"+eY+"pt";
   alert(line1.points);
   //line1.points="0pt,0pt,0pt,40pt,40pt,40pt";
   line1.filled="false";
   pNode.appendChild(line1);*/
}

function drawAcute(acuteX,acuteY) {
   //---复制acuteNode
   var tempNode=acuteNode.cloneNode(true);
   tempNode.style.posLeft=acuteX-2;
   tempNode.style.posTop=acuteY-2.5;
   tempNode.style.visibility="";
   //---添加tempNode
   pNode.appendChild(tempNode);
}




//---输出sFlow数据，调试
function outputFlow() {
    var outputTxt="sFlow流程";
	outputTxt+="节点数"+sFlow.sNode.length+"<br>";
	for (var i=0;i<sFlow.sNode.length;i++)
	{
		outputTxt+="节点"+sFlow.sNode[i].n_id+"--name:"+sFlow.sNode[i].n_name+"<br>";
		outputTxt+="类型"+sFlow.sNode[i].n_type+"<br>";
	}
    output.innerHTML=outputTxt;
}


//---------------------------------------------------------------
//定义鼠标事件
//---------------------------------------------------------------

//---定义鼠标选中左边元素事件，根据选中的元素，改变拖动条中显示的名称
function MouseDoSel(OperType)
{
  if (window.event.button!=2)
  {
  
	fakeType=OperType;
	curElement = event.srcElement;
 //--判断选中类型，改变拖动条图标
 switch (OperType)
        {
        case 0:
        fakeNodeImg.src="/sleemon/pages/sys/spflow/image/user.gif";
        break; 
        case 1:
        fakeNodeImg.src="/sleemon/pages/sys/spflow/image/depart.gif";
        break;
        case 2:
        fakeNodeImg.src="/sleemon/pages/sys/spflow/image/role.gif";
		break;
        case 3:
        fakeNodeImg.src="/sleemon/pages/sys/spflow/image/group.gif";
		break;
        default: 
        }
 
fakeNodeTxt.innerHTML = selType[fakeType];
fakeNodeTable.style.backgroundColor="#FFFFCC";
  //----设置状态条提醒文字
  window.status="拖动到想添加的位置，变色为正确位置";}
}

//---定义鼠标选中节点事件，拖动后可改变节点位置
function moveNode()
{
	/*
if (window.event.button!=2)
{

if (inWho!=null) {
	fakeType=inWho.n_type;
	chooseNode = inWho;
	moveElement = event.srcElement;
 //--判断选中类型，改变拖动条图标
 switch (fakeType)
        {
        case 0:
        fakeNodeImg.src="/sleemon/pages/sysutil/spflow/image/user.gif";
        break; 
        case 1:
        fakeNodeImg.src="/sleemon/pages/sysutil/spflow/image/depart.gif";
        break;
        case 2:
        fakeNodeImg.src="/sleemon/pages/sysutil/spflow/image/role.gif";
		break;
        case 3:
        fakeNodeImg.src="/sleemon/pages/sysutil/spflow/image/group.gif";
		break;
        default: 
        } 
  fakeNodeTxt.innerHTML = inWho.n_name;
  fakeNodeTable.style.backgroundColor="";
  //----设置状态条提醒文字
  window.status="移动节点到需要的位置";
}
}
*/
}


//---定义鼠标移动时的事件，如果选中元素不为空的话，就让拖动条跟随鼠标移动
function doMouseMove() 
{
var mouseX=window.event.clientX + PX;
var mouseY=window.event.clientY + PY;
//---判断当前鼠标位置
judgeMousePosition(mouseX,mouseY);

if (curElement!=null)
  {
  //---当前状态为拖动条跟随状态

  //拖动条跟随
  fake.style.posLeft = mouseX-15;
  fake.style.posTop = mouseY-15;
  fake.style.display = '';

  //--根据当前位置，改变拖动条的文字。及拖动条表格变色
	if (inWhoTop)
	{
		fakeNodeTable.style.backgroundColor="#C1FDD0";
		fakeNodeTxt.innerHTML="串签";
		submitform.ProNodeID.value = inWhoTop.n_id;
		submitform.event.value="seriesUp";
		window.status="放开鼠标，即可在当前位置串接一个节点";
	}else if (inWhoBottom)
	{
		fakeNodeTable.style.backgroundColor="#C1FDD0";
        fakeNodeTxt.innerHTML="串签";
		submitform.ProNodeID.value = inWhoBottom.n_id;
		submitform.event.value="seriesDown";
		window.status="放开鼠标，即可在当前位置串接一个节点";
	}else if (inWhoLeft)
	{
		fakeNodeTable.style.backgroundColor="#FFCACA";
        fakeNodeTxt.innerHTML="会签";
		submitform.ProNodeID.value = inWhoLeft.n_id;
		submitform.event.value="parallelLeft";
		window.status="放开鼠标，即可在当前位置并联一个节点";
	}else if (inWhoRight)
	{
		fakeNodeTable.style.backgroundColor="#FFCACA";
        fakeNodeTxt.innerHTML="会签";
		submitform.ProNodeID.value = inWhoRight.n_id;
		submitform.event.value="parallelRight";
		window.status="放开鼠标，即可在当前位置并联一个节点";
	}else if (inWho)
	{
		/*
		fakeNodeTable.style.backgroundColor="#DEE3F8";
		fakeNodeTxt.innerHTML="通知";
		submitform.ProNodeID.value = inWho.n_id;
		submitform.event.value="message";
		window.status="放开鼠标，即可在当前位置添加一个通知";
		*/
		fakeNodeTable.style.backgroundColor="#FFFFCC";
	    fakeNodeTxt.innerHTML=selType[fakeType];
	}else {
		fakeNodeTable.style.backgroundColor="#FFFFCC";
	    fakeNodeTxt.innerHTML=selType[fakeType];
	}
  }
if (moveElement!=null)
{
  //---当前状态为拖动条跟随状态

  //拖动条跟随
  fake.style.posLeft = mouseX-15;
  fake.style.posTop = mouseY-15;
  fake.style.display = '';
}

window.event.returnValue = false;

}


//---定义鼠标down事件
function doMouseDown()
{

}

//---定义鼠标up事件，如果up产生在拖动条拖动时，则判断拖动条是否产生提交，
//是，则根据类型提交，不是，则提醒应在变色位置时放开鼠标
function doMouseUp() {
//--拖动产生于拖动条
if (curElement!=null)
  {
  curElement = null;
  fake.style.display = 'none';
  event.returnValue =false;
  //--如果拖动条在节点附近，则添加节点
  if (inWhoTop!=null||inWhoBottom!=null||inWhoLeft!=null||inWhoRight!=null)
  {
	  //--根据所选择的添加类型，打开相应的选择框进行选择
     //var choose=window.showModalDialog('modelnode_factory.po?event=SelectNodeEvent&OperType='+fakeType+'&BusinessID='+submitform.ProcessClass.value,'','dialogwidth=350px; dialogheight=300px; status=no');
     var choose=window.showModalDialog('add_modelNode.jsp?event=SelectNodeEvent&OperType='+fakeType+'&BusinessID='+submitform.ProcessClass.value,'','dialogwidth=450px; dialogheight=300px; status=no');
      //--如果返回结果不为空的话，提交节点
	 if (choose!=null) {
			window.status="";
			submitform.OperID.value =  choose[0];
			submitform.BackFlag.value = choose[2];
			submitform.JumpFlag.value =  choose[5];
			submitform.ExpandFlag.value = choose[8];
			submitform.EditFlag.value = choose[3];
			submitform.CancleFlag.value = choose[6];
			submitform.AgentFlag.value = choose[9];
			submitform.DelayFlag.value = choose[7];
			submitform.AllFlag.value = choose[4];      
			submitform.DelayDay.value = choose[10];
			submitform.OperType.value = fakeType;
			submitform.submit();
   } else {
	     window.status="没有选择任何节点操作人，添加动作取消";
	     scrollback();
	 }
  }
  else {
     window.status="没有拖动到正确的位置，不添加任何节点";
     scrollback();
  }
 }
 
//---拖动节点
if (moveElement!=null)
{
  moveElement = null;
  fake.style.display = 'none';
  event.returnValue =false;
  if (chooseNode!=null)
  {
  //---获得当前位置，提交
  var mouseX=window.event.clientX + PX-15;
  var mouseY=window.event.clientY + PY-15;
  submitform.IX.value = mouseX;
  submitform.IY.value = mouseY;
  submitform.ProNodeID.value=chooseNode.n_id;
  submitform.event.value="move";
  submitform.submit();
  }
}
}

//---定义滚动条的事件
function doscroll(){
      PY = document.body.scrollTop;
      PX = document.body.scrollLeft;
      folder.style.top  = PY;
      folder.style.left = PX + foldervar;
      hotdiv.style.top  = PY;
      hotdiv.style.left = PX;
}


//---判断鼠标当前位置的算法
function judgeMousePosition(cX,cY) {
   var nodeX=0;
   var nodeY=0;
   var halfNodeZoneW=NodeZoneW/2;
   var halfNodeZoneH=NodeZoneH/2;
   var halfNodeW = NodeW/2;
   var halfNodeH = NodeH/2
   inWhoLeft= null;
   inWhoRight = null;
   inWhoBottom= null;       
   inWhoTop= null;        
   inWho = null;   

   for (var i=0;i<id.length;i++ )
   {
      //----虚节点不判断
      if (sFlow.sNode[i].n_type!=-3) {
      //---得到节点的中心位置
	  nodeX=sFlow.sNode[i].sx+halfNodeW;
	  nodeY=sFlow.sNode[i].sy+halfNodeH;
      	  
 	  
         //--根节点和未节点不判断左右
	  if (sFlow.sNode[i].n_type!=-1&&sFlow.sNode[i].n_type!=-2)
      {
       
       //--判断当前位置是否在节点的左边
       if ((cX<=(nodeX-halfNodeW))&&(cX>(nodeX-halfNodeW-halfNodeZoneW))&&(cY<(nodeY+halfNodeH))&&(cY>(nodeY-halfNodeH)))
       {
		   inWhoLeft = sFlow.sNode[i];
		   //window.status="当前位置-->"+inWhoLeft.n_name+"的左边";
		   break;
	   }
	   //--判断当前位置是否在节点的右边
	   if ((cX>=(nodeX+halfNodeW))&&(cX<(nodeX+halfNodeW+halfNodeZoneW))&&(cY<(nodeY+halfNodeH))&&(cY>(nodeY-halfNodeH)))
	   {
		   inWhoRight = sFlow.sNode[i];
		   //window.status="当前位置-->"+inWhoRight.n_name+"的右边";
		   break;
	   }
	   if ((cX>(nodeX-halfNodeW))&&(cX<(nodeX+halfNodeW))&&(cY>(nodeY-halfNodeH))&&(cY<(nodeY+halfNodeH)))
	   {
           inWho = sFlow.sNode[i];
		   //window.status="当前位置-->"+inWho.n_name+"的里面";
		   break;
	   }

	   }
	   //---根节点不判断上节点
	   if (sFlow.sNode[i].n_type!=-1)
	   {
		   if ((cX>(nodeX-halfNodeW))&&(cX<(nodeX+halfNodeW))&&(cY<=(nodeY-halfNodeH))&&(cY>(nodeY-halfNodeH-halfNodeZoneH)))
	   {
		   inWhoTop = sFlow.sNode[i];
		   //window.status="当前位置-->"+inWhoTop.n_name+"的上边";
		   break;
	   }
	   }
	   //--末节点不判断下节点
	   if (sFlow.sNode[i].n_type!=-2)
	   {
		   if ((cX>(nodeX-halfNodeW))&&(cX<(nodeX+halfNodeW))&&(cY>=(nodeY+halfNodeH))&&(cY<(nodeY+halfNodeH+halfNodeZoneH)))
	   {
		   inWhoBottom = sFlow.sNode[i];
		   //window.status="当前位置-->"+inWhoBottom.n_name+"的下边";
		   break;
	   }
	   }
	   }

   }

}



//---------------------------------------------------------------
//定义页面上使用的方法
//---------------------------------------------------------------
//---打开右键菜单
function showMenu()
{
if (inWho!=null)
	{
	chooseNode=inWho;
	menuLayer.style.display = '';
	menuLayer.style.posLeft =window.event.clientX + PX;
	menuLayer.style.posTop  = window.event.clientY + PY;
	}
}
//--改变菜单项颜色
function cl(node)
{
node.style.fontSize = '9pt';
node.style.color = '#FFFFFF';
node.style.background = '#000080';
node.style.cursor ='hand'; 
}

//--还原菜单项颜色
function nr(node)
{
node.style.fontSize = '9pt';
node.style.color = '#000000';
node.style.background = "";
}


//---删除节点
function Node_Del()
{
var tmpNodeName=chooseNode.n_name;
var NodeName;
if (tmpNodeName.length>0?tmpNodeName.slice(0,1)=="<":false)
  {
  var NodeNameBegin = tmpNodeName.indexOf('>');
  var NodeNameEnd = tmpNodeName.indexOf('<',NodeNameBegin);
  NodeName = tmpNodeName.slice(NodeNameBegin+1,NodeNameEnd);
  }
else
  {
  NodeName = tmpNodeName;
  }   
var conf = confirm('确定要删除此节点'+NodeName+'？');
if(conf)
  {
	submitform.ProNodeID.value=chooseNode.n_id;
	submitform.event.value="del";
    submitform.submit();
  }
chooseNode=null;
}

//---修改节点
function Node_Edit() {
	var choose = new Array();
	choose = window.showModalDialog('upd_insNode.jsp?instanceNodeId='+chooseNode.n_id+'&BusinessID='+submitform.ProcessClass.value,'','dialogwidth=460px; dialogheight=200px; status=no');
	if (choose!=null) {
	flow.instanceNodeId.value = choose[0];
	flow.operationer.value = choose[1];
	flow.operationerId.value =  choose[2];
	flow.flowInstanceId.value =  choose[3];
	flow.isProNode.value = choose[4];
	flow.operateFlag.value = "changeOperator";
	flow.submit(); 
	} 
	chooseNode=null;
}

function refresh() {
  submitform.event.value="refresh";
  submitform.submit();
}

function delFlow() {
  submitform.event.value="delFlow";
  submitform.submit();
}

function stringCut(str)
{
var strLen = str.length;
if (strLen>0)
  if (str.slice(0,1)!="<") {  
		if (strLen>7)
	  	return str.slice(0,6)+"..";
		else
	  	return str;  
  } else
    return str;  	  
}


function doContextmenu()
{
event.returnValue = false;
}

//-------------------------------------------------------------
//左边选单处理
//-------------------------------------------------------------
function infolder()
{
isinfolder = true;
}

function outfolder()
{
if (isinfolder)
  {
  scrollback();
  isinfolder = false;  
  }
}

function scrolllock(obj)
{
folderlock = !folderlock;
if (folderlock)
  {
  obj.className='down';
  obj.bgColor='#F2F1EE';
  pushpin.src = '/sleemon/pages/sys/spflow/image/pushpin_h.gif';
  obj.title="开启菜单条自动隐藏";   
  }
else
  {
  obj.className='over';
  obj.bgColor='';
  pushpin.src="/sleemon/pages/sys/spflow/image/pushpin.gif";
  obj.title="关闭菜单条自动隐藏";  
  }  
//if (!folderlock)
//  {
//  scrollback();
//  }
}

function scrollback()
{
folderhidden = false;
if (!folderlock&&(folderNode.style.posLeft+folderNode.style.posWidth)>(PX+5)&&foldershow)
  {
  foldervar = foldervar - 5;
  folderNode.style.posLeft = PX + foldervar; 
  setTimeout('scrollback()',10)  
  }
else
  {
  folderhidden = true;
  }  
}

function scrollall()
{
foldershow = false;
if (!folderlock&&folderNode.style.posLeft<PX&&folderhidden)
  {
  foldervar = foldervar + 5;  
  folderNode.style.posLeft = PX + foldervar; 
  setTimeout('scrollall()',10)  
  }
else
  {
  foldershow = true;
  }  
}

function menuout()
{
if (!folderlock)
  menustate.className='out';
}

function menuover()
{
if (!folderlock)
  menustate.className='over';
}	

function menudown()
{
scrolllock(menustate);
}

function depsel() {
MouseDoSel(1);
}

function rolesel() {
MouseDoSel(2);
}

function personsel() {
MouseDoSel(0);
}

function groupsel() {
MouseDoSel(3);
}

function showAuditing() {
  if (window.event.button == 2) {
  	return true;
  }
	if (inWho != null) {
		chooseNode = inWho;
		var isProcessNode;
	  for (i=0; i<proNode.length; i++) {
	  	if (proNode[i] == chooseNode.n_id) {
	  		isProcessNode = true;
	  		break;
	  	}
	  }  
	  if (readOnly != 't') {
		  if (isProcessNode) {
				var choose = new Array();
				var argus = new Array();
				argus[0] = hasOfficial;
				argus[1] = chooseNode.n_operaName;
				var choose = window.showModalDialog('auditing.jsp?instanceNodeId='+chooseNode.n_id+'&flowInstanceId='+submitform.Processid.value,argus,'dialogwidth=440px; dialogheight=280px; status=no');
				if (choose != null) {
					document.flow.instanceNodeId.value = choose[0];    
					document.flow.nextNodeId.value = choose[1];        
					document.flow.operationContent.value = choose[2];   
					document.flow.operationFlag.value = choose[3];
					document.flow.nextNodeType.value = choose[4];
					document.flow.OperID.value = choose[5];
					document.flow.BackFlag.value = choose[6];
					document.flow.JumpFlag.value = choose[7];
					document.flow.ExpandFlag.value = choose[8];
					document.flow.EditFlag.value = choose[9];
					document.flow.CancleFlag.value = choose[10];
					document.flow.AgentFlag.value = choose[11];
					document.flow.DelayFlag.value = choose[12];
					document.flow.AllFlag.value = choose[13];
					document.flow.DelayDay.value = choose[14];
					document.flow.nodeOperationId.value = choose[15];
					document.flow.OperType.value = choose[16];
					if (document.flow.operationFlag.value > 3)
						document.flow.operateFlag.value = "addNode";
					document.flow.submit();
					readOnly = 't';
				}
				return false;                    
			} else {
				window.showModalDialog('auditing_history.jsp?instanceNodeId='+chooseNode.n_id+'&flowInstanceId='+submitform.Processid.value,'','dialogwidth=400px; dialogheight=240px; status=no');
			}
		} else {
			window.showModalDialog('auditing_history.jsp?instanceNodeId='+chooseNode.n_id+'&flowInstanceId='+submitform.Processid.value,'','dialogwidth=400px; dialogheight=240px; status=no');
		}
	}                                           
}                                             
                                              
//---------------------------------------------------------------
//页面load后执行
//---------------------------------------------------------------

//----定义窗口的onscroll事件
window.onscroll = doscroll;

menustate.onmouseout = menuout; 
menustate.onmouseover = menuover;  
menustate.onmousedown = menudown;
folder.onmouseover = infolder;

depselect.onmousedown = depsel;
roleselect.onmousedown = rolesel;
personselect.onmousedown = personsel;
groupselect.onmousedown = groupsel;

scrollallhot.onmouseover = scrollall;
scrollallhot1.onmouseover = scrollall;
outfolderhot1.onmouseover = outfolder;

//----定义页面的鼠标事件
document.onmousemove = doMouseMove;
document.onmouseup = doMouseUp;
document.onmousedown = doMouseDown;
document.oncontextmenu = doContextmenu;


createFlow();
drawFlow();
outputFlow();


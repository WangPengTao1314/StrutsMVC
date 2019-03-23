<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%> 
<html>
	<head>
	   <title>menu</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/left.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/treeview.css"/>
		<script type="text/javascript" src="${ctx}/scripts/core/jquery-1.6.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/yahoo-min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/event-min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/connection-min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/dom-min.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/treeview-min.js"></script> 
	</head>
	<body style="margin: 0px;"> 
	 <table height="100%"  width="180" border="0" cellpadding="0" cellspacing="0" align="right"> 
	     <!--<tr>
            <td id="pic1" height="26" >
            	  <marquee SCROLLAMOUNT=2 SCROLLDELAY=100 onmouseover= "javascript:this.stop(); "  onmouseout= "javascript:this.start(); ">
            		 <span id="notice" style="cursor: pointer;"  ></span>
            	 </marquee>
            </td>
        </tr>-->
        <tr>
            <td id="pic2" valign="top"> 
                <div id="treeMenus" style="width:100%;height:100%;overflow-y:auto;padding:2px 5px;background:#f6f4eb"></div>    
            </td>
        </tr>
        
	 	<tr>
	 		<td id="pic3" valign="bottom" height="22" >
	 		</td>
	 	</tr> 
	    <tr>
            <td height="5" style="background-color:#fff;"></td>
        </tr>
	 </table> 
	 <div style="display:none">
		<form name="form" action="" target="mainFrame" method="post"></form>
	 </div>
	</body>
	<script type="text/javascript">
	var tree;
	var rootImg=document.getElementById("model");
	var mainFrame=window.top.mainFrame;   		
	
    function treeInit() {  
        buildRandomTextNodeTree(parent.rootMenu);       
    };
    function buildRandomTextNodeTree(flg) {    	
		//instantiate the tree:
        tree = new YAHOO.widget.TreeView("treeMenus");
		//The tree is not created in the DOM until this method is called:
		var oResults=parent.treeData;
		for (var i=0, j=oResults.size; i<j; i++) {
			if(oResults.children[i].id==flg){	
				var node=oResults.children[i];	 
				if(node.img){
					rootImg.src="${ctx}/styles/${theme}/images/left/"+node.img;
				}  
				loadNodes(tree.getRoot(),node,2);
				break;
			}
		}		
		tree.subscribe("labelClick", function(node) {	
			if(!node.isUrl)return;	
			if(mainFrame.g && mainFrame.g('YT_LOAD_MSG')){
				return false;
			}
			mainFrame.addTab(node.no,node.label,"../../"+node.url);
		});
        tree.draw();
    };   
    
    parent.treeCtl.showMenu=function(flg){
    	buildRandomTextNodeTree(flg);
    };

	function loadNodes(node,oResults,lvl){
		for (var i=0, j=oResults.size; i<j; i++) {
	   		if(oResults.children[i]){
	   		    var childNode=oResults.children[i];
	   			var tempNode = new YAHOO.widget.MenuNode("menu"+i, node, false);

	   			if(lvl==2){
	   			
	   			tempNode.label=" <b>" + childNode.name+"</b>";
	   			}else{
	   			
	   			tempNode.label=" " + childNode.name;
	   			}
	   			tempNode.no=childNode.id;
	   			tempNode.multiExpand=true;	
	   			tempNode.labelStyle='';
	   			if(true || childNode.img){
	   				tempNode.myimg="${ctx}/styles/${theme}/images/left/node_close.png";
	   			}
	   		    var hasChildren=childNode.size>0;	   			  			
	   			//加載子集
	   			if(hasChildren){
		   			tempNode.label="&nbsp;<strong>" + childNode.name+"</strong>";
		   			tempNode.myimg="";
	   				tempNode.isUrl=false;
	   				loadNodes(tempNode,oResults.children[i],lvl+1);
	   			}else{
	   				tempNode.isUrl=true;
	   				tempNode.href="#";
	   				tempNode.url=childNode.url;
	   			}
	   		}					
		}	
    };

    YAHOO.util.Event.onDOMReady(treeInit);
    
    //notices();
    //setInterval("notices()",1000*60*10);
    function notices(){
    	$.ajax({
			url: "${ctx}/login.shtml?action=getNotices",
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				$("#notice").text(xhr.responseText);
			}
		});
    }
    
    $("#notice").click(function(){
    	window.showModalDialog('${ctx}/pages/index/notices.jsp',null,'dialogwidth=400px; dialogheight=210px; status=no');
    });
    
    
   $(function(){
      $("#pic1").attr("background","${ctx}/styles/${theme}/images/left/titlebar-active.jpg");
      $("#pic2").attr("background","${ctx}/styles/${theme}/images/left/lefth_02.png");
      $("#pic3").attr("background","${ctx}/styles/${theme}/images/left/lefth_03.png");
      
   });
    
   function getNode(){
	   alert("leftFrame");
	   return document.getElementById("ygtvlabelel4");
   }
</script>
</html>

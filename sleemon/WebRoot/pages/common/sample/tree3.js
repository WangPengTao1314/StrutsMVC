var setting = {
	//样式
	view: {
		//取消展开动画
		expandSpeed: "",
		//设置多选
		selectedMulti: false
	},
	//回调函数
	callback: {
		//点击事件方法
		onClick: zTreeOnClick
	}
};
		
$(function(){
	 $.fn.zTree.init($("#tree"), setting, zNodes);
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转
    //parent.document.getElementById("topcontent").src="jgxx.shtml?action=toList&JGXXID="+treeNode.id;
};

$(function(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	
	//获取某节点
	//var node = treeObj.getNodeByParam("id", 1, parentNode);
	//icon:"../../css/ztreeStyle/img/diy/2.png"
	
	//展开
	$("#open").click(function(){
		treeObj.expandAll(true);
	});
	//收缩
	$("#close").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("tree");
		treeObj.expandAll(false);	
	});
	//删除节点
	$("#removeNode").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		treeObj.removeNode(nodes[0]);
	});
	//删除子节点
	$("#removeChilds").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if (nodes && nodes.length>0) {
			treeObj.removeChilds(nodes[0]);
		}
	});
	
	//增加父节点
	$("#addParentNode").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("tree");
		//增加一个
		var newNode = {name:"newNode1",id:new Date().valueOf()};
		//增加多个
		//var newNodes = [{name:"newNode1"}, {name:"newNode2"}, {name:"newNode3"}];
		newNode = treeObj.addNodes(null, newNode);
	});
	
	//增加子节点
	$("#addChildNode").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		treeNode = nodes[0];
		var newNode = {id:new Date().valueOf(),name:"newNode1", pId:treeNode.id};
		newNode = treeObj.addNodes(treeNode, newNode);//第三个参数表示新增节点后父节点是否需要展开。
	});
	
	//修改子节点
	$("#modify").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		nodes[0].name = "我换名字了";
		treeObj.updateNode(nodes[0]);
	});
});

 
	
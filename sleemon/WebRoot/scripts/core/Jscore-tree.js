/**
  *@module 系统模块   
  *@func 圣迪奥树操作JS   
  *@version 0.19.0[44]  2011-10-11
  *@author zhuxw      
*/
//树所在页面初始化
function treePageInit(){
	$.fn.zTree.init($("#tree"), setting, zNodes);
	//展开
	$("#open").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		treeObj.expandAll(true);
	});
	//收缩
	$("#close").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		treeObj.expandAll(false);	
	});
	$("#refresh").click(function(){
		window.location.reload();
	});
}

//树：新增节点
function addTreeNodes(id,newNode){
	 var treeObj = $.fn.zTree.getZTreeObj("tree");
	 var node = treeObj.getNodeByParam("id", id, null);
	 newNode  = treeObj.addNodes(node, newNode);
	 treeObj.selectNode(newNode[0]);
}

//树：更新节点
function updateNodes(id,name){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var node = treeObj.getNodeByParam("id", id, null);
	node.name = name;
	treeObj.updateNode(node);
	treeObj.selectNode(node);
}

//树：删除节点
function deleteNodes(id){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var node = treeObj.getNodeByParam("id", id, null);
	treeObj.removeNode(node);
}

//树：获取选中的记录Id
function getSelNodesId(){
	var nodes = getSelNodes();
	if("" != nodes){
		return nodes.id;
	}
	return "";
}

//树：获取选中的节点
function getSelNodes(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getSelectedNodes();
	if (nodes && nodes.length>0) {
		return nodes[0];
	}
	return "";
}


//tree初始化设置
function treeSetting(){
	return {
		view: {//样式
			expandSpeed: "",//取消展开动画
			selectedMulti: false//设置不可多选
		},
		callback: {//回调函数
			onClick: zTreeOnClick//点击事件方法
		}
	};
}

//tree异步初始化设置
function ayncTreeSettion(){
	return {
		view: {//样式
			expandSpeed: "",//取消展开动画
			selectedMulti: false//设置多选
		},
		async: {//异步
			enable: true,
			url: getUrl
		},
		callback: {
			beforeExpand: beforeExpand,
			onAsyncSuccess: onAsyncSuccess,
			onAsyncError: onAsyncError,
			onClick: zTreeOnClick//点击事件方法
		}
	};
}

function beforeExpand(treeId, treeNode) {
	if (!treeNode.isAjaxing) {
		ajaxGetNodes(treeNode, "refresh");
		return true;
	} else {
		alert("正在下载数据中，请稍后展开节点。。。");
		return false;
	}
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
	if (!msg || msg.length == 0) {
		return;
	}
	var zTree = $.fn.zTree.getZTreeObj("tree");
	treeNode.icon = "";
	zTree.updateNode(treeNode);
	zTree.selectNode(treeNode.childs[0]);
}

function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	alert("异步获取数据出现异常。");
	treeNode.icon = "";
	zTree.updateNode(treeNode);
}
function ajaxGetNodes(treeNode, reloadType) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (reloadType == "refresh") {
		treeNode.icon = "../../css/zTreeStyle/img/loading.gif";
		zTree.updateNode(treeNode);
	}
	zTree.reAsyncChildNodes(treeNode, reloadType, true);
}

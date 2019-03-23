package com.hoperun.sys.model;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessNode.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class ProcessNode {

	  /** 实例节点id. */
	  public String instanceNodeId = "";

	  /** 实例id. */
	  public String flowInstanceId = "";

	  /** 回退标志 设置为1，否则为0. */
	  public String backFlag = "";

	  /** 跳签标志 设置为1，否则为0. */
	  public String jumpFlag = "";

	  /** 加签标志 设置为1，否则为0. */
	  public String expandFlag = "";

	  /** 修改标志 设置为1，否则为0. */
	  public String editFlag = "";

	  /** 否决标志 设置为1，否则为0. */
	  public String cancleFlag = "";

	  /** 代理标志 设置为1，否则为0. */
	  public String agentFlag = "";

	  /** 超期提示标志 设置为1，否则为0. */
	  public String delayFlag = "";

	  /** The delay time. */
  	public String delayTime = "";

	  /** 多人标志 设置为1，否则为0. */
	  public String allFlag = "";

	  /** The remark. */
  	public String remark = "";

	  /** x坐标. */
	  public int nodeX = 0;

	  /** y坐标. */
	  public int nodeY = 0;
	  
  	/** The instance node operation id. */
  	public String instanceNodeOperationId = "";

	  /** 节点操作者类型 0：用户 1：部门 2：角色 3：工作组��������. */
	  public String operationer = "";

	  /** 节点操作者id. */
	  public String operationerId = "";

	  /** �节点操作名称�������. */
	  public String operationName = "";

	  /** ��ǰ�当前节点所在层次 (nodeY-30)/nodeHeight，提交节点的层次为0. */
	  public int nodeLevel = 0;

	  /** 实例节点的最深层次 (max(nodeY)-30)/nodeHeight, 结束节点所在层次. */
	  public int totalLevel = 0;

	  /**
  	 * Instantiates a new process node.
  	 */
  	public ProcessNode() {
	  }
	  
  	/**
  	 * Gets the instance node id.
  	 * 
  	 * @return the instance node id
  	 */
  	public String getInstanceNodeId() {
	    return instanceNodeId;
	  }
	  
  	/**
  	 * Sets the instance node id.
  	 * 
  	 * @param instanceNodeId the new instance node id
  	 */
  	public void setInstanceNodeId(String instanceNodeId) {
	    this.instanceNodeId = instanceNodeId;
	  }
	  
  	/**
  	 * Sets the flow instance id.
  	 * 
  	 * @param flowInstanceId the new flow instance id
  	 */
  	public void setFlowInstanceId(String flowInstanceId) {
	    this.flowInstanceId = flowInstanceId;
	  }
	  
  	/**
  	 * Gets the flow instance id.
  	 * 
  	 * @return the flow instance id
  	 */
  	public String getFlowInstanceId() {
	    return flowInstanceId;
	  }
	  
  	/**
  	 * Sets the back flag.
  	 * 
  	 * @param backFlag the new back flag
  	 */
  	public void setBackFlag(String backFlag) {
	    this.backFlag = backFlag;
	  }
	  
  	/**
  	 * Gets the back flag.
  	 * 
  	 * @return the back flag
  	 */
  	public String getBackFlag() {
	    return backFlag;
	  }
	  
  	/**
  	 * Sets the jump flag.
  	 * 
  	 * @param jumpFlag the new jump flag
  	 */
  	public void setJumpFlag(String jumpFlag) {
	    this.jumpFlag = jumpFlag;
	  }
	  
  	/**
  	 * Gets the jump flag.
  	 * 
  	 * @return the jump flag
  	 */
  	public String getJumpFlag() {
	    return jumpFlag;
	  }
	  
  	/**
  	 * Sets the expand flag.
  	 * 
  	 * @param expandFlag the new expand flag
  	 */
  	public void setExpandFlag(String expandFlag) {
	    this.expandFlag = expandFlag;
	  }
	  
  	/**
  	 * Gets the expand flag.
  	 * 
  	 * @return the expand flag
  	 */
  	public String getExpandFlag() {
	    return expandFlag;
	  }
	  
  	/**
  	 * Sets the edits the flag.
  	 * 
  	 * @param editFlag the new edits the flag
  	 */
  	public void setEditFlag(String editFlag) {
	    this.editFlag = editFlag;
	  }
	  
  	/**
  	 * Gets the edits the flag.
  	 * 
  	 * @return the edits the flag
  	 */
  	public String getEditFlag() {
	    return editFlag;
	  }
	  
  	/**
  	 * Sets the cancle flag.
  	 * 
  	 * @param cancleFlag the new cancle flag
  	 */
  	public void setCancleFlag(String cancleFlag) {
	    this.cancleFlag = cancleFlag;
	  }
	  
  	/**
  	 * Gets the cancle flag.
  	 * 
  	 * @return the cancle flag
  	 */
  	public String getCancleFlag() {
	    return cancleFlag;
	  }
	  
  	/**
  	 * Sets the agent flag.
  	 * 
  	 * @param agentFlag the new agent flag
  	 */
  	public void setAgentFlag(String agentFlag) {
	    this.agentFlag = agentFlag;
	  }
	  
  	/**
  	 * Gets the agent flag.
  	 * 
  	 * @return the agent flag
  	 */
  	public String getAgentFlag() {
	    return agentFlag;
	  }
	  
  	/**
  	 * Sets the delay flag.
  	 * 
  	 * @param delayFlag the new delay flag
  	 */
  	public void setDelayFlag(String delayFlag) {
	    this.delayFlag = delayFlag;
	  }
	  
  	/**
  	 * Gets the delay flag.
  	 * 
  	 * @return the delay flag
  	 */
  	public String getDelayFlag() {
	    return delayFlag;
	  }
	  
  	/**
  	 * Sets the delay time.
  	 * 
  	 * @param delayTime the new delay time
  	 */
  	public void setDelayTime(String delayTime) {
	    this.delayTime = delayTime;
	  }
	  
  	/**
  	 * Gets the delay time.
  	 * 
  	 * @return the delay time
  	 */
  	public String getDelayTime() {
	    return delayTime;
	  }
	  
  	/**
  	 * Sets the all flag.
  	 * 
  	 * @param allFlag the new all flag
  	 */
  	public void setAllFlag(String allFlag) {
	    this.allFlag = allFlag;
	  }
	  
  	/**
  	 * Gets the all flag.
  	 * 
  	 * @return the all flag
  	 */
  	public String getAllFlag() {
	    return allFlag;
	  }
	  
  	/**
  	 * Sets the remark.
  	 * 
  	 * @param remark the new remark
  	 */
  	public void setRemark(String remark) {
	    this.remark = remark;
	  }
	  
  	/**
  	 * Gets the remark.
  	 * 
  	 * @return the remark
  	 */
  	public String getRemark() {
	    return remark;
	  }
	  
  	/**
  	 * Sets the node x.
  	 * 
  	 * @param nodeX the new node x
  	 */
  	public void setNodeX(int nodeX) {
	    this.nodeX = nodeX;
	  }
	  
  	/**
  	 * Gets the node x.
  	 * 
  	 * @return the node x
  	 */
  	public int getNodeX() {
	    return nodeX;
	  }
	  
  	/**
  	 * Sets the node y.
  	 * 
  	 * @param nodeY the new node y
  	 */
  	public void setNodeY(int nodeY) {
	    this.nodeY = nodeY;
	  }
	  
  	/**
  	 * Gets the node y.
  	 * 
  	 * @return the node y
  	 */
  	public int getNodeY() {
	    return nodeY;
	  }
	  
  	/**
  	 * Sets the instance node operation id.
  	 * 
  	 * @param instanceNodeOperationId the new instance node operation id
  	 */
  	public void setInstanceNodeOperationId(String instanceNodeOperationId) {
	    this.instanceNodeOperationId = instanceNodeOperationId;
	  }
	  
  	/**
  	 * Gets the instance node operation id.
  	 * 
  	 * @return the instance node operation id
  	 */
  	public String getInstanceNodeOperationId() {
	    return instanceNodeOperationId;
	  }
	  
  	/**
  	 * Sets the operationer.
  	 * 
  	 * @param operationer the new operationer
  	 */
  	public void setOperationer(String operationer) {
	    this.operationer = operationer;
	  }
	  
  	/**
  	 * Gets the operationer.
  	 * 
  	 * @return the operationer
  	 */
  	public String getOperationer() {
	    return operationer;
	  }
	  
  	/**
  	 * Sets the operationer id.
  	 * 
  	 * @param operationerId the new operationer id
  	 */
  	public void setOperationerId(String operationerId) {
	    this.operationerId = operationerId;
	  }
	  
  	/**
  	 * Gets the operationer id.
  	 * 
  	 * @return the operationer id
  	 */
  	public String getOperationerId() {
	    return operationerId;
	  }
	  
  	/**
  	 * Sets the operation name.
  	 * 
  	 * @param operationName the new operation name
  	 */
  	public void setOperationName(String operationName) {
	    this.operationName = operationName;
	  }
	  
  	/**
  	 * Gets the operation name.
  	 * 
  	 * @return the operation name
  	 */
  	public String getOperationName() {
	    return operationName;
	  }
	  
  	/**
  	 * Gets the node level.
  	 * 
  	 * @return the node level
  	 */
  	public int getNodeLevel() {
	    return nodeLevel;
	  }
	  
  	/**
  	 * Sets the node level.
  	 * 
  	 * @param nodeLevel the new node level
  	 */
  	public void setNodeLevel(int nodeLevel) {
	    this.nodeLevel = nodeLevel;
	  }
	  
  	/**
  	 * Gets the total level.
  	 * 
  	 * @return the total level
  	 */
  	public int getTotalLevel() {
	    return totalLevel;
	  }
	  
  	/**
  	 * Sets the total level.
  	 * 
  	 * @param totalLevel the new total level
  	 */
  	public void setTotalLevel(int totalLevel) {
	    this.totalLevel = totalLevel;
	  }

}
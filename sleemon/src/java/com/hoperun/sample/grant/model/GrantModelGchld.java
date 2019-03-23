/**
 * prjName:供应链_贵人鸟
 * ucName:不良通知单
 * fileName:GrantModelChld
*/
package com.hoperun.sample.grant.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zhuxw
 * *@createdate 2013-05-15 10:35:31
 */
public class GrantModelGchld extends BaseModel{
   /** 成品不良通知单明细明细ID **/
   private String CPBLTZDMXMXID;
   /** 成品不良通知单明细ID **/
   private String CPBLTZDMXID;
   /** 成品不良通知单ID **/
   private String CPBLTZDID;
   /** 成品质检项目ID **/
   private String WLBH;
   /** 成品质检项目编号 **/
   private String WLMC;
   /** 成品质检项目名称 **/
   private String GGXH;
   /** 质检项目类别 **/
   private String WLSL;
   private String REMARK;
   /**  **/
   private String DELFLAG;
public String getCPBLTZDMXMXID() {
	return CPBLTZDMXMXID;
}
public void setCPBLTZDMXMXID(String cpbltzdmxmxid) {
	CPBLTZDMXMXID = cpbltzdmxmxid;
}
public String getCPBLTZDMXID() {
	return CPBLTZDMXID;
}
public void setCPBLTZDMXID(String cpbltzdmxid) {
	CPBLTZDMXID = cpbltzdmxid;
}
public String getCPBLTZDID() {
	return CPBLTZDID;
}
public void setCPBLTZDID(String cpbltzdid) {
	CPBLTZDID = cpbltzdid;
}
public String getWLBH() {
	return WLBH;
}
public void setWLBH(String wlbh) {
	WLBH = wlbh;
}
public String getWLMC() {
	return WLMC;
}
public void setWLMC(String wlmc) {
	WLMC = wlmc;
}
public String getGGXH() {
	return GGXH;
}
public void setGGXH(String ggxh) {
	GGXH = ggxh;
}
public String getWLSL() {
	return WLSL;
}
public void setWLSL(String wlsl) {
	WLSL = wlsl;
}
public String getREMARK() {
	return REMARK;
}
public void setREMARK(String remark) {
	REMARK = remark;
}
public String getDELFLAG() {
	return DELFLAG;
}
public void setDELFLAG(String delflag) {
	DELFLAG = delflag;
}
 
}
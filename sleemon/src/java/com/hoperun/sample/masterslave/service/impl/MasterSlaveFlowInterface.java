/**
 * 项目名称：平台
 * 系统名：质检管理
 * 文件名：CpbltzdServiceImpl.java
*/
package com.hoperun.sample.masterslave.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterSlaveFlowInterface.
 * 
 * @module 库存管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author zhuxw
 */
public class MasterSlaveFlowInterface implements FlowInterface {

    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowInterface#afterAffirm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession, com.hoperun.sys.service.FlowService)
     */
    @Override
    public boolean afterAffirm(HttpServletRequest request, HttpSession session, FlowService flowService) throws Exception {

        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowInterface#afterAuditing(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession, com.hoperun.sys.service.FlowService)
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean afterAuditing(HttpServletRequest request, HttpSession session, FlowService flowService) throws Exception {
      return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.FlowInterface#beforeAffirm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession, com.hoperun.sys.service.FlowService)
     */
    @Override
    public boolean beforeAffirm(HttpServletRequest request, HttpSession session, FlowService flowService) throws Exception {

        return true;
    }

}

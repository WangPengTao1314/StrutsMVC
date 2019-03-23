/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringContextUtil.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class SpringContextUtil implements ApplicationContextAware {

    /** The application context. */
    private static ApplicationContext applicationContext;


    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        SpringContextUtil.applicationContext = applicationContext;
    }


    /**
     * Gets the application context.
     * 
     * @return the application context
     */
    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }


    /**
     * Gets the bean.
     * 
     * @param beanId the bean id
     * 
     * @return the bean
     * 
     * @throws BeansException the beans exception
     */
    public static Object getBean(String beanId) throws BeansException {

        return applicationContext.getBean(beanId);
    }

}

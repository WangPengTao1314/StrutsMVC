package com.hoperun.sys.model;

import java.util.Vector;

import com.hoperun.sys.action.XTYHQXUtil;
// TODO: Auto-generated Javadoc

/**
 * The Class XTYHQXBean.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class XTYHQXBean
{

    /** The v xtmkqx beans. */
    Vector vXTMKQXBeans;
    
    /** The XTYH bean. */
    XTYHBean XTYHBean;

    /**
     * Instantiates a new xTYHQX bean.
     * 
     * @param aXTYHBean the a xtyh bean
     * 
     * @throws Exception the exception
     */
    public XTYHQXBean(XTYHBean aXTYHBean)
        throws Exception
    {
        XTYHBean = aXTYHBean;
        if(XTYHBean == null)
        {
            throw new Exception("\u89D2\u8272\u5BF9\u8C61\u4E3A\u7A7A\uFF01\u65E0\u6CD5\u521B\u5EFA\u5BF9\u5E94\u7684" +
"\u89D2\u8272\u6743\u9650"
);
        } else
        {
            XTYHQXUtil XTYHQXUtil = new XTYHQXUtil();
            vXTMKQXBeans = XTYHQXUtil.createXTYHQXVector(aXTYHBean.getXTYHID());
            return;
        }
    }

    /**
     * Show.
     * 
     * @return the string
     */
    public String show()
    {
        if(vXTMKQXBeans == null)
        {
            return null;
        }
        int i = 9;
        StringBuffer tempStrBuf = new StringBuffer("");
        for(i = 0; i < vXTMKQXBeans.size(); i++)
        {
            XTMKQXBean aXTMKQXBean = (XTMKQXBean)vXTMKQXBeans.get(i);
            tempStrBuf.append("[" + aXTMKQXBean.getMKBH() + "_" + aXTMKQXBean.getMKMC() + "]" + aXTMKQXBean.getJBMC() + "");
            tempStrBuf.append("<br>");
        }

        return tempStrBuf.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        if(vXTMKQXBeans == null)
        {
            return null;
        }
        int i = 9;
        StringBuffer tempStrBuf = new StringBuffer(XTYHBean.getYHBH());
        tempStrBuf.append("[");
        for(i = 0; i < vXTMKQXBeans.size(); i++)
        {
            XTMKQXBean aXTMKQXBean = (XTMKQXBean)vXTMKQXBeans.get(i);
            tempStrBuf.append("(" + aXTMKQXBean.getXTMKID() + "_" + aXTMKQXBean.getQXJBID() + ")");
        }

        tempStrBuf.append("]");
        return tempStrBuf.toString();
    }
}

package com.hoperun.sys.action;

import java.io.File;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLObject.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class XMLObject
{

    /**
     * Instantiates a new xML object.
     */
    public XMLObject()
    {
    }

    /**
     * Gets the xMLQX node text.
     * 
     * @param XMLFileName the xML file name
     * @param NodeName the node name
     * @param AttributeName the attribute name
     * @param targetValue the target value
     * @param strQXJB the str qxjb
     * @param QXTagName the qX tag name
     * 
     * @return the xMLQX node text
     */
    public String getXMLQXNodeText(String XMLFileName, String NodeName, String AttributeName, String targetValue, String strQXJB, String QXTagName)
    {
        Document document = null;
        NodeList ZMKList = null;
        String NodeText = "";
        try
        {
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          File aFile = new File(File.separator + "jsp" + File.separator + "admin" + File.separator + "privilege" + File.separator + XMLFileName);
          document = builder.parse(aFile);
        
        ZMKList = document.getElementsByTagName(NodeName);
        int i = 0;
        for(i = 0; i < ZMKList.getLength(); i++)
        {
            Element targetZMK = (Element)ZMKList.item(i);
            if(!targetZMK.getAttribute(AttributeName).equals(targetValue))
            {
                continue;
            }
            NodeList QXJBList = targetZMK.getElementsByTagName(QXTagName);
            for(int j = 0; j < QXJBList.getLength(); j++)
            {
                Element targetQXJB = (Element)QXJBList.item(j);
                if(!targetQXJB.getAttribute("JBValue").equals(strQXJB))
                {
                    continue;
                }
                try
                {
                    NodeText = QXJBList.item(j).getFirstChild().getNodeValue();
                }
                catch(Exception ex)
                {
                    NodeText = " 1<>1 ";
                }
                break;
            }

            break;
        }
        } catch(Exception ex)
        {
            System.err.println("\u8BFB\u53D6" + XMLFileName + "\u6587\u4EF6\u6CA1\u6709\u627E\u5230:" + ex.toString());
            ex.printStackTrace();
            return "";
        }
        return NodeText;
    }

    /**
     * Check zqxjb exist.
     * 
     * @param XMLFileName the xML file name
     * @param MKCode the mK code
     * 
     * @return true, if successful
     */
    public boolean checkZQXJBExist(String XMLFileName, String MKCode)
    {
        Document document = null;
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
           File aFile = new File(File.separator + "jsp" + File.separator + "admin" + File.separator + "privilege" + File.separator + XMLFileName);
           document = builder.parse(aFile);
        }
        catch(Exception ex)
        {
            System.err.println("\u8BFB\u53D6" + XMLFileName + "\u6587\u4EF6\u6CA1\u6709\u627E\u5230:" + ex.toString());
            ex.printStackTrace();
            return false;
        }
        NodeList ZMKList = document.getElementsByTagName("ZMKQX");
        int i = 0;
        for(i = 0; i < ZMKList.getLength(); i++)
        {
            Element targetZMK = (Element)ZMKList.item(i);
            if(targetZMK.getAttribute("MKCode").equals(MKCode))
            {
                NodeList QXJBList = targetZMK.getElementsByTagName("ZQXJB");
                if(QXJBList.getLength() > 0)
                {
                    System.err.println("MKCode=" + MKCode + ",QXJBList=" + QXJBList.getLength());
                    return true;
                } else
                {
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Gets the zQXJB.
     * 
     * @return the zQXJB
     */
    private String getZQXJB()
    {
        return null;
    }
}

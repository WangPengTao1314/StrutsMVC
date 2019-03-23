package com.hoperun.commons.edit;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

// UploadBean, EditWebhelper

public class UploadWebHelper
{

    public UploadWebHelper()
    {
        filename = "D:/style2.xml";
        document = null;
        ew = null;
    }

    public UploadBean InitPara()
    {
        UploadBean bean;
        bean = new UploadBean();
        String sToolBar = "";
        try
        {
            List list = document.selectNodes("Edit_Style/style");
            bean.setSfileext(getNodeValue(list, "sfileext"));
            bean.setSfilesize(getNodeValue(list, "sfilesize"));
            bean.setSflashext(getNodeValue(list, "sflashext"));
            bean.setSflashsize(getNodeValue(list, "sflashsize"));
            bean.setSimageext(getNodeValue(list, "simageext"));
            bean.setSimagesize(getNodeValue(list, "simagesize"));
            bean.setSmediaext(getNodeValue(list, "smediaext"));
            bean.setSmediasize(getNodeValue(list, "smediasize"));
            bean.setSremoteext(getNodeValue(list, "sremoteext"));
            bean.setSremotesize(getNodeValue(list, "sremotesize"));
            bean.setSuploaddir(getNodeValue(list, "suploaddir"));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            document = null;
        }
        return bean;
    }

    // 根据传入style名称取样式 zhangshengzhi
    public UploadBean InitPara(String sylename)
    {
        UploadBean bean;
        bean = new UploadBean();
        String sToolBar = "";
        try
        {
            // List Blist = document.selectNodes("Edit_Style/style");
            Element rootNode = document.getRootElement();
            // 取得根节点的名字
            // System.out.println("根节点名称: "+rootNode.getName());
            // 取得所有的二级(下一级)子节点
            List Blist = rootNode.elements();

            for (int l = 0; l < Blist.size(); l++)
            {
                Element el = (Element) Blist.get(l);
                // System.out.println(" \t二级节点名称:"+el.getName());
                // 取得所有的二级子节点的属性
                String attrname = el.attributeValue("name");
                // System.out.println(" \t
                // 属性name:"+el.attributeValue("name")+"---"+sylename);
                // 取得所有的三级(下一级)子节点
                if (attrname.equals(sylename))
                {
                    List list = el.elements();
                    // System.out.println(" \t list:"+sylename+list.toString());
                    bean.setSfileext(getNodeValue(list, "sfileext"));
                    bean.setSfilesize(getNodeValue(list, "sfilesize"));
                    bean.setSflashext(getNodeValue(list, "sflashext"));
                    bean.setSflashsize(getNodeValue(list, "sflashsize"));
                    bean.setSimageext(getNodeValue(list, "simageext"));
                    bean.setSimagesize(getNodeValue(list, "simagesize"));
                    bean.setSmediaext(getNodeValue(list, "smediaext"));
                    bean.setSmediasize(getNodeValue(list, "smediasize"));
                    bean.setSremoteext(getNodeValue(list, "sremoteext"));
                    bean.setSremotesize(getNodeValue(list, "sremotesize"));
                    bean.setSuploaddir(getNodeValue(list, "suploaddir"));
                }
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            document = null;
        }
        return bean;
    }

    public UploadWebHelper getInstance()
    {
        UploadWebHelper uw = new UploadWebHelper();
        SAXReader saxReader = new SAXReader();
        try
        {
            document = saxReader.read(new File(filename));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return uw;
    }

    public String getNodeValue(List list, String Node)
    {
        Iterator it = list.iterator();
        while (it.hasNext())
        {
            Element styleElement = (Element) it.next();
            if (styleElement.getName().equals(Node))
            {
                // System.out.println(" \t
                // styleElement:"+styleElement.getTextTrim());
                return styleElement.getTextTrim();
            }
        }

        return "";

    }

    public String getNodeValue2(List list, String Node)
    {
        Iterator it = list.iterator();
        if (it.hasNext())
        {
            Element styleElement = (Element) it.next();
            Iterator memo = styleElement.elementIterator(Node);
            if (memo.hasNext())
            {
                Element memostring = (Element) memo.next();
                return memostring.getTextTrim();
            } else
            {
                return "";
            }
        } else
        {
            return "";
        }
    }

    public static void main(String args[])
    {
        UploadWebHelper w = new UploadWebHelper();
        w.getInstance();
        UploadBean bean = w.InitPara();
        System.out.println(bean.getSfileext());
    }

    Document document;
    EditWebhelper ew;
    public String filename;

}

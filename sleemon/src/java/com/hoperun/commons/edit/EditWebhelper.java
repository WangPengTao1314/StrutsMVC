package com.hoperun.commons.edit;

import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

//                EditBean

public class EditWebhelper
{

    public EditWebhelper()
    {
        size = 255;
        filename = "D:/style2.xml";
        filename2 = "D:/button2.xml";
        document = null;
        document2 = null;
        ew = null;
        sStyleDir = "blue";
        aButtonCode = new String[size];
        aButtonHTML = new String[size];
    }

    public String Code2HTML(String s_Code)
    {
        String CodeToHTML = "";
        for (int i = 0; i < size; i++)
        {
            if (aButtonCode[i].equals(s_Code))
            {
                CodeToHTML = aButtonHTML[i];
                return CodeToHTML;
            }
        }

        return CodeToHTML;
    }

    public void InitButtonArray()
    {
        try
        {
            List list = document2.selectNodes("Edit_Button/bcode");
            List list2 = document2.selectNodes("Edit_Button/bcode/@name");
            Iterator it = list.iterator();
            Iterator it2 = list2.iterator();
            int i = 0;
            int option = 0;
            while (it.hasNext())
            {
                Attribute attribute = (Attribute) it2.next();
                Element button = (Element) it.next();
                aButtonCode[i] = attribute.getValue();
                option = Integer.parseInt(memoString(button, "btype").getTextTrim());
                switch (option)
                {
                case 0: // '\0'
                    aButtonHTML[i] = "<DIV CLASS=\"" + memoString(button, "bclass").getTextTrim() + "\" TITLE=\"" + memoString(button, "btitle").getTextTrim() + "\" onclick=\""
                            + memoString(button, "bevent").getTextTrim() + "\"><IMG CLASS=\"Ico\" SRC=\"buttonimage/" + sStyleDir + "/" + memoString(button, "bimage").getTextTrim() + "\"></DIV>";
                    break;

                case 1: // '\001'
                    aButtonHTML[i] = "<SELECT CLASS=\"" + memoString(button, "bclass").getTextTrim() + "\" onchange=\"" + memoString(button, "bevent").getTextTrim() + "\">"
                            + memoString(button, "bhtml").getTextTrim() + "</SELECT>";
                    break;

                case 2: // '\002'
                    aButtonHTML[i] = "<DIV CLASS=\"" + memoString(button, "bclass").getTextTrim() + "\">" + notNull(memoString(button, "bhtml").getTextTrim()) + "</DIV>";
                    break;
                }
                i++;
            }
            size = i;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

    public EditBean InitPara()
    {
        EditBean bean;
        bean = new EditBean();
        String sToolBar = "";
        try
        {
            List list = document.selectNodes("Edit_Style/style");
            bean.setSAutoRemote(getNodeValue(list, "sautoremote"));
            bean.setSStyleName("standard");
            bean.setSBaseUrl(getNodeValue(list, "sbaseurl"));
            bean.setSDetectFromWord(getNodeValue(list, "sdetectfromword"));
            bean.setSInitMode(getNodeValue(list, "sinitmod"));
            bean.setSStyleID(getNodeValue(list, "sid"));
            bean.setSStyleDir(getNodeValue(list, "sdir"));
            bean.setNStateFlag(getNodeValue(list, "sstateflag"));
            sStyleDir = getNodeValue(list, "sdir");
            InitButtonArray();
            String aButton[] = getNodeValue(list, "stoolbar1").split("\\|");
            sToolBar = "<table border=0 cellpadding=0 cellspacing=0 width='100%' class='Toolbar' id='eWebEditor_Toolbar'>";
            sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
            for (int i = 0; i < aButton.length; i++)
            {
                if (aButton[i].equalsIgnoreCase("MAXIMIZE"))
                    aButton[i] = "Minimize";
                sToolBar = sToolBar + Code2HTML(aButton[i]);
            }

            sToolBar = sToolBar + "</div></td></tr>";
            String aButton2[] = getNodeValue(list, "stoolbar2").split("\\|");
            if (aButton2.length == 0)
            {
                sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
                for (int j = 0; j < aButton2.length; j++)
                {
                    if (aButton2[j].equalsIgnoreCase("MAXIMIZE"))
                        aButton2[j] = "Minimize";
                    sToolBar = sToolBar + Code2HTML(aButton2[j]);
                }
                sToolBar = sToolBar + "</div></td></tr>";
            }
            sToolBar = sToolBar + "</table>";
            bean.setSToolBar(sToolBar);
            bean.setSStyleName(getNodeValue(list, "sdir"));
            bean.setSStyleUploadDir(getNodeValue(list, "suploaddir"));
            bean.setSVersion("\u98DE\u9C7C\u4FEE\u6539\u7248");
            bean.setSReleaseDate("2004-11-30");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            document = null;
        }
        return bean;
    }

    // 根据传入style名称取样式 zhangshengzhi
    @SuppressWarnings("unchecked")
    public EditBean InitPara(String sylename)
    {
        EditBean bean;
        bean = new EditBean();
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
                    bean.setSStyleName(sylename);
                    bean.setSAutoRemote(getNodeValue(list, "sautoremote"));
                    bean.setSBaseUrl(getNodeValue(list, "sbaseurl"));
                    bean.setSDetectFromWord(getNodeValue(list, "sdetectfromword"));
                    bean.setSInitMode(getNodeValue(list, "sinitmod"));
                    bean.setSStyleID(getNodeValue(list, "sid"));
                    bean.setSStyleDir(getNodeValue(list, "sdir"));
                    bean.setNStateFlag(getNodeValue(list, "sstateflag"));
                    sStyleDir = getNodeValue(list, "sdir");
                    InitButtonArray();
                    String aButton[] = getNodeValue(list, "stoolbar1").split("\\|");
                    // System.out.println(" \t
                    // aButton:"+sylename+aButton.toString());
                    sToolBar = "<table border=0 cellpadding=0 cellspacing=0 width='100%' class='Toolbar' id='eWebEditor_Toolbar'>";
                    sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
                    for (int i = 0; i < aButton.length; i++)
                    {
                        if (aButton[i].equalsIgnoreCase("MAXIMIZE"))
                            aButton[i] = "Minimize";
                        sToolBar = sToolBar + Code2HTML(aButton[i]);
                    }

                    sToolBar = sToolBar + "</div></td></tr>";
                    String aButton2[] = getNodeValue(list, "stoolbar2").split("\\|");
                    if (aButton2.length == 0)
                    {
                        sToolBar = sToolBar + "<tr><td><div class='yToolbar'>";
                        for (int j = 0; j < aButton2.length; j++)
                        {
                            if (aButton2[j].equalsIgnoreCase("MAXIMIZE"))
                                aButton2[j] = "Minimize";
                            sToolBar = sToolBar + Code2HTML(aButton2[j]);
                        }
                        sToolBar = sToolBar + "</div></td></tr>";
                    }
                    sToolBar = sToolBar + "</table>";
                    bean.setSToolBar(sToolBar);
                    bean.setSStyleName(getNodeValue(list, "sdir"));
                    bean.setSStyleUploadDir(getNodeValue(list, "suploaddir"));
                    bean.setSVersion("\u98DE\u9C7C\u4FEE\u6539\u7248");
                    bean.setSReleaseDate("2004-11-30");
                }
            }

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            document = null;
        }
        return bean;
    }

    public EditWebhelper getInstance()
    {
        EditWebhelper ew = new EditWebhelper();
        SAXReader saxReader = new SAXReader();
        try
        {
            document = saxReader.read(new File(filename));
            document2 = saxReader.read(new File(filename2));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return ew;
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
        EditWebhelper editWebhelper1 = new EditWebhelper();
        editWebhelper1.filename = "D:/style2.xml";
        editWebhelper1.getInstance();
        EditBean bean = editWebhelper1.InitPara();
        System.out.println(bean.getSToolBar());
    }

    public Element memoString(Element button, String Node)
    {
        Iterator memo = button.elementIterator(Node);
        if (memo.hasNext())
        {
            Element memostring = (Element) memo.next();
            return memostring;
        } else
        {
            return null;
        }
    }

    public String notNull(String str)
    {
        String s = str;
        if (str == null)
            return "";
        else
            return s;
    }

    String aButtonCode[];
    String aButtonHTML[];
    Document document;
    Document document2;
    EditWebhelper ew;
    public String filename;
    public String filename2;
    String sStyleDir;
    int size;

}

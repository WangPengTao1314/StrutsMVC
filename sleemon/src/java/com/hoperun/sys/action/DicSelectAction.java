/**
  *@module 系统模块   
  *@fuc 系统模块   
  *@version 1.1
  *@author 朱晓文
*/
package com.hoperun.sys.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.servlet.ContextServlet;
import com.hoperun.commons.servlet.DictInfoBean;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.DicSelectModel;
import com.hoperun.sys.service.DictService;

// TODO: Auto-generated Javadoc
/**
 * The Class DicSelectAction.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class DicSelectAction extends BaseAction {

    /** The dict service. */
    private DictService dictService;


    /**
     * 获取字典数据.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void dictSel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        //String jsonArg = ParamUtil.get(request, "jsonData");
        //Id:"test1",dictId:"sel_90",action:"dictSel"
        	
        //DicSelectModel dicSelect = new Gson().fromJson(jsonArg, new TypeToken <DicSelectModel>() {}.getType());
        String dictId = ParamUtil.get(request, "dictId");//dicSelect.getDictId();

        String selCommHead = "System"; // 默认为物流的配置文件
        if (dictId.indexOf("_") != -1) {
            selCommHead = dictId.substring(0, dictId.indexOf("_"));
        } else {
            dictId = selCommHead + "_" + dictId;
        }

        String fileName = this.servlet.getServletContext().getRealPath("/") + File.separator + "pages" + File.separator + "sys" + File.separator + "configfiles"
                + File.separator + "dictionary" + File.separator  + "dictConfig_" + selCommHead + ".xml";
          List <?> dicList = ContextServlet.getDicList(fileName + dictId.substring(dictId.indexOf("_")));
        if (null == dicList) {

          
            dicList = dictService.getDictList(fileName, dictId.substring(selCommHead.length() + 1),ParamUtil.get(request, "condition"));
        }
        String jsonResult = jsonResult();
        // List dicList = ContextServlet.getDicListHm(this.key);
        PrintWriter writer = getWriter(response);
        StringBuilder sbdict = new StringBuilder();
        try {
  
        	String defVal = ParamUtil.get(request, "defaultValue");
            int listCount = dicList.size();
            sbdict.append("<option value=''>--请选择--</option>");
            for (int i = 0; i < listCount; i++) {
                DictInfoBean dictBean = (DictInfoBean) dicList.get(i);                
//                if (defVal.equals(dictBean.getDiccnname())) { modify by  zbb 2014-3-13
                if (defVal.equals(dictBean.getDicname())) {
                    sbdict.append("<option selected value='");
                    sbdict.append(dictBean.getDicname()).append("'>");
                    sbdict.append(dictBean.getDiccnname()).append("</option>");
                } else {
                    sbdict.append("<option value='");
                    sbdict.append(dictBean.getDicname()).append("'>");
                    sbdict.append(dictBean.getDiccnname()).append("</option>");
                }
            }
            jsonResult = jsonResult(true, sbdict.toString());
            if (null != writer) {
                writer.write(jsonResult);
                writer.close();
            }
        } catch (Exception e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    /**
     * Sets the dict service.
     * 
     * @param dictService the new dict service
     */
    public void setDictService(DictService dictService) {

        this.dictService = dictService;
    }

}

package com.hoperun.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.sys.service.XTSQService;

// TODO: Auto-generated Javadoc
/**
 * The Class XTSQServiceImpl.
 */
public class XTSQServiceImpl extends BaseService implements XTSQService {

    /**
     * Selcom list.
     * 
     * @param params the params
     * 
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List selcomList(Map params) {

        return this.findList("sqlcom.query", params);
    }


    /**
     * Selcom.
     * 
     * @param params the params
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map selcom(Map params) {

        return (Map) load("sqlcom.query", params);
    }


    /**
     * Delcom.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    @SuppressWarnings("unchecked")
    public boolean delcom(Map params) {

        return update("sqlcom.delete", params);
    }


    /**
     * Updcom.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    @SuppressWarnings("unchecked")
    public boolean updcom(Map params) {

        return update("sqlcom.update", params);
    }


    /**
     * Inscom.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    @SuppressWarnings("unchecked")
    public boolean inscom(Map params) {

        insert("sqlcom.insert", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTSQService#getQXTab(java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
     */
    @SuppressWarnings("unchecked")
    public String getQXTab(String keyName, String keyID, String adminXTYHID, String loginJGXXID, HttpServletRequest request) throws Exception {

        StringBuffer returnStr = new StringBuffer("");
        String sql = "select SYSTEMID,SYSTEMMC from " + Consts.SYSSCHEMA + ".T_system  ";

        try {
            Map params = new HashMap();
            params.put("SelSQL", sql);
            List resList = selcomList(params);
            int maxlength = resList.size();
            for (int i = 0; i < maxlength; i++) {
                Map temMap = (Map) resList.get(i);
                returnStr.append("<td height=\"14\" class=\"lable_down\" width=\"100\" id=\"BlueLable" + i

                + "\" onclick=\"showit(this.id,'xtyh.shtml?action=toMkxxTree&MKLB=" + temMap.get("SYSTEMID") + "&KeyName=" + keyName

                + "&KeyID=" + keyID + "&sid=" + new Date().getTime() + "'," + i + ")\">" + temMap.get("SYSTEMMC") + "</td>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnStr.toString();

    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTSQService#getMKAry(java.lang.String, int, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String[] getMKAry(String XTMC, int length, String condition) throws Exception {

        StringBuffer aSQL = new StringBuffer();
        aSQL.append("select count(*) NUM from T_SYS_XTMK where MKSM='");
        aSQL.append(XTMC);
        if (Consts.DBTYPE.equals("MSSQL")) {

            aSQL.append("' AND len(rtrim(MKBH))=");
        } else {
            aSQL.append("' AND length(rtrim(MKBH))=");
        }

        aSQL.append(length);
        aSQL.append("AND ");
        aSQL.append(condition);
        Map params = new HashMap();
        params.put("SelSQL", aSQL.toString());
        Map temMap = selcom(params);

        int num = 0;
        if (temMap != null) {
            num = Integer.parseInt(temMap.get("NUM").toString());
        }
        String MKAry[] = new String[num * 3];

        aSQL.delete(0, aSQL.length());

        if (Consts.DBTYPE.equals("MSSQL")) {

            aSQL.append("select MKMC,MKBH,XTMKDESC  from T_SYS_XTMK where MKSM='" + XTMC + "' AND len(rtrim(MKBH))=" + length + " AND " + condition
                    + " order by MKBH ");
        } else {
            aSQL.append("select MKMC,MKBH,XTMKDESC  from T_SYS_XTMK where MKSM='" + XTMC + "' AND length(rtrim(MKBH))=" + length + " AND " + condition
                    + " order by MKBH ");

        }
        params.put("SelSQL", aSQL.toString());
        List reList = selcomList(params);
        int j = 0;
        for (int i = 0; i < reList.size(); i++) {
            Map tempMap = (Map) reList.get(i);
            MKAry[j] = tranCodeN(tempMap.get("MKMC"));
            MKAry[j + 1] = tranCodeN(tempMap.get("MKBH"));
            MKAry[j + 2] = tranCodeN(tempMap.get("XTMKDESC"));
            if (MKAry[j + 2] == null) {
                MKAry[j + 2] = "";
            }

            j = j + 3;
        }

        return MKAry;
    }


    /**
     * Tran code p.
     * 
     * @param Str the str
     * 
     * @return the string
     */
    public String tranCodeP(String Str) {

        return Str == null ? "" : Str;
    }


    /**
     * Tran code n.
     * 
     * @param Str the str
     * 
     * @return the string
     */
    public String tranCodeN(Object Str) {

        return Str == null ? "" : Str.toString();
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTSQService#returnQXJBRadioList(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String returnQXJBRadioList(String MKQXJB) throws Exception {

        StringBuffer returnStrBuf = new StringBuffer("");

        Map params = new HashMap();
        params.put("SelSQL", "select * from T_SYS_QXJB");
        List reList = selcomList(params);
        int i = 0;
        if (MKQXJB.equals("Default")) {
            MKQXJB = "6";
        }
        for (; i < reList.size(); i++) {
            Map tempMap = (Map) reList.get(i);
            returnStrBuf.append("<input type=\"radio\" style=\"border:0px\" name=\"defaultRadio\" ");

            if (tranCodeN(tempMap.get("QXJBID")).equals(MKQXJB)) {
                returnStrBuf.append(" checked ");
            }
            returnStrBuf.append("value='" + tranCodeN(tempMap.get("QXJBID")) + "' onclick=\"callDefaultRadio('" + tranCodeN(tempMap.get("QXJBID"))
                    + "')\">" + tranCodeN(tempMap.get("JBMC")));
        }
        return returnStrBuf.toString();

    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTSQService#getStrMK2(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String getStrMK2(String tableName, String condition) {

        int ary_i = 0;
        StringBuffer strMK = new StringBuffer("");
        Map params = new HashMap();
        params.put("SelSQL", "select XTMKID from " + tableName + condition);
        List reList = selcomList(params);
        for (int i = 0; i < reList.size(); i++) {
            Map tempMap = (Map) reList.get(i);
            strMK.append(tranCodeN(tempMap.get("XTMKID")) + ";");
            ary_i++;
        }

        return strMK.toString();

    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.XTSQService#getStrMK(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String getStrMK(String tableName, String condition) throws Exception {

        try {

            Map params = new HashMap();
            params.put("SelSQL", "select XTSQID,XTMKID from " + tableName + condition);
            List reList = selcomList(params);
            int ary_i = 0;
            StringBuffer strMK = new StringBuffer("");
            for (int i = 0; i < reList.size(); i++) {
                Map tempMap = (Map) reList.get(i);
                strMK.append(tranCodeN(tempMap.get("XTMKID")) + "_" + tranCodeN(tempMap.get("QXJBID")) + ";");
                ary_i++;
            }

            return strMK.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }


    /**
     * 更新用户权限.
     * 
     * @param XTYHID the xTYHID
     * @param MKSM the mKSM
     * @param ins_name2 the ins_name2
     * @param ins_value2 the ins_value2
     * @param selXTMKID the sel xtmkid
     * @param request the request
     * 
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public void insertXTSQ(String XTYHID, String MKSM, String[] ins_name2, String[] ins_value2, String[] selXTMKID, HttpServletRequest request)
            throws Exception {

        //更新用户自有权限信息
        try {

            String tempsql = "delete from T_SYS_XTSQ where XTYHID='" + XTYHID + "' AND XTMKID in (select XTMKID from T_SYS_XTMK where MKSM='" + MKSM
                    + "')";
            Map params = new HashMap();
            params.put("DelSQL", tempsql);
            delcom(params);
            if (selXTMKID != null) {
                int i = 0;
                int j = 0;
                Vector tempVector = new Vector();
                for (i = 0; i < selXTMKID.length; i++) {
                    //if (selXTMKID[i].length() == FINALQXMKCodeLength) {
                    j++;
                    tempVector.add(selXTMKID[i]);
                    //}
                }
                String[] ins_YH_XTMKID = new String[tempVector.size()];
                //                String[] ins_YH_QXJBID = new String[tempVector.size()];
                String tempStr = "";
                for (i = 0; i < tempVector.size(); i++) {
                    tempStr = (String) tempVector.get(i);
                    ins_YH_XTMKID[i] = tempStr;
                    //                    ins_YH_QXJBID[i] = tranCodeP(request.getParameter("radioSel" + tempStr));
                }

                for (i = 0; i < ins_YH_XTMKID.length; i++) {
                    ins_value2[0] = TimeComm.getPreTimeStamp("XTSQ") + i;
                    ins_value2[1] = XTYHID;
                    ins_value2[2] = ins_YH_XTMKID[i];
                    //                    ins_value2[3] = ins_YH_QXJBID[i].substring(FINALQXMKCodeLength + 1, FINALQXMKCodeLength + 2);

                    tempsql = " insert into " + Consts.SYSSCHEMA + ".T_SYS_XTSQ(XTSQID, XTYHID, XTMKID) values('" + ins_value2[0] + "','"
                            + ins_value2[1] + "','" + ins_value2[2] + "')";
                    params.clear();
                    params.put("InsSQL", tempsql);
                    inscom(params);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

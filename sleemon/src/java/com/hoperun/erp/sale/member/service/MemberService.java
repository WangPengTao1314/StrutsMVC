package com.hoperun.erp.sale.member.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.member.model.MemberActModel;
import com.hoperun.erp.sale.member.model.MemberCardModel;
import com.hoperun.erp.sale.member.model.MemberModel;
import com.hoperun.erp.sale.member.model.MemberPointModel;
import com.hoperun.sys.model.UserBean;

public interface MemberService extends IBaseService{
	
	  /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param MEMBER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String MEMBER_ID, MemberModel model,List<MemberActModel> chldList, 
			UserBean userBean);
	
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param CUST_ID
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String MEMBER_ID);
	
	 /**
     * * 根据主表Id查询子表活动记录
     */
	public List<MemberActModel> memberActQuery(String MEMBER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     */
    public List <MemberCardModel> memberCardQuery(String MEMBER_ID);
    
	 /**
     * * 积分流水
     */
    public List <MemberPointModel> memberPointQuery(String MEMBER_ID);
    
    
    /**
     * * 根据子表Id批量加载子表信息
     */
    public List <MemberCardModel> loadMemberCardModels(Map <String, String> params) ;
    /**
     * * 根据子表Id批量加载子表信息
     */
    public List <MemberActModel> loadMemberActModels(Map <String, String> params) ;
    
    /**
	 * 编辑卡券信息
	 * @return
	 */
    public boolean txMemberActEdit(String MEMBER_ID, List<MemberActModel> modellist,UserBean userBean);
    
	/**
	 * 编辑卡券信息
	 * @return
	 */
    public boolean txMemberCardEdit(String MEMBER_ID, List<MemberCardModel> listCard,UserBean userBean);
    
	 /**
     * * 子表的批量删除
     */
    public void txBatch4DeleteMemberCard(Map<String,String>params);
    
    /**
     * 活动子表的批量删除
     */
    public void txBatch4DeleteMemberAct(Map<String,String>params);
    /**
     * 更新状态
     * @param params
     */
    public void txUpdateSate(Map<String,String>params);
    
    /**
     * 查找已经参加的活动ID
     * @param MEMBER_ID
     * @return
     */
    public String queryActIdsByFkId(String MEMBER_ID);
    
    /**
     * 查询已发送的卡券ID
     * @param MEMBER_ID
     * @return
     */
    public String queryCardIdsByFkId(String MEMBER_ID);

}

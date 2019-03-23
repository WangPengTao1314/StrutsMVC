package com.hoperun.sys.service.impl;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService; 
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.CustomQueryBean;
import com.hoperun.sys.model.CustomQueryDtlBean;
import com.hoperun.sys.service.CustQueryService; 

// TODO: Auto-generated Javadoc
/**
 * The Class CustQueryServiceImpl.
 */
public class CustQueryServiceImpl extends BaseService implements CustQueryService {

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#custPageQuery(java.util.Map, int)
	 */
	public IListPage custPageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("CUST.pageCustQuery", "CUST.pageCustQueryCount", params, pageNo);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#txUpdateRptState(java.util.Map)
	 */
	public boolean txUpdateRptState(Map<String, String> map) {
		return this.update("CUST.updateRptState", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#txDeleteRptRow(java.util.Map)
	 */
	public boolean txDeleteRptRow(Map<String, String> map) {
		return this.delete("CUST.DELETE_RPT_ROW_ID", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#queryForByRptPk(java.util.Map)
	 */
	public CustomQueryBean queryForByRptPk(Map<String, String> map) {
		return (CustomQueryBean)this.load("CUST.loadRptQueryDetal", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#txInsertRptMain(java.util.Map)
	 */
	public Object txInsertRptMain(Map<String, String> map) {
		return this.insert("CUST.insertRptMain", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#txUpdateRptMain(java.util.Map)
	 */
	public boolean txUpdateRptMain(Map<String, String> map) {
		return this.update("CUST.updateRptMain", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#checkRptSql(java.util.Map)
	 */
	public boolean checkRptSql(Map<String, String> map) {
		try{
			String rptSql = (String)map.get("rptSql").toUpperCase();
			if(rptSql.indexOf("WHERE")==-1){
				rptSql+=" WHERE 1<>1";
			}else{
				rptSql+=" AND 1<>1";
			}
			map.put("rptSql", rptSql);
			this.findList("CUST.checkRptSql", map);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} 
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#queryRptQueryDtlByPk(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<CustomQueryDtlBean> queryRptQueryDtlByPk(Map<String, String> map) {
		List<CustomQueryDtlBean> queryList = new ArrayList<CustomQueryDtlBean>();
		List<CustomQueryDtlBean> resultList = null;
		try{
			//查询报表SQL显示多少字段
			List<HashMap<String,String>> resultMap = (ArrayList<HashMap<String,String>>)this.findList("CUST.checkRptSql", map);
			
			CustomQueryDtlBean resultBean = null;
			for(HashMap<String,String> listMap:resultMap){
				Iterator iter = listMap.entrySet().iterator(); 
				//遍历报表SQL字段
				while(iter.hasNext()){
					resultBean = new CustomQueryDtlBean();
					Map.Entry entry = (Map.Entry) iter.next(); 
					resultBean.setPhysicName((String)entry.getKey()); 
					queryList.add(resultBean);
				}
				break;
			}
			
			//默认最终结果等于报表字段
			resultList = queryList;
			//查询报表字段对应结果集
			queryList = this.findList("CUST.queryRptDtlSql", map);
			
			//己存在数据时
			if(queryList!=null && queryList.size()>0){
				resultList = changeResultList(resultList,queryList);
			}
			return resultList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 处理报表字段结果集数据.
	 * 
	 * @param resultList the result list
	 * @param queryList the query list
	 * 
	 * @return the list< custom query dtl bean>
	 */
	private List<CustomQueryDtlBean> changeResultList(List<CustomQueryDtlBean> resultList,List<CustomQueryDtlBean> queryList){
		List<CustomQueryDtlBean> rstListBean = new ArrayList<CustomQueryDtlBean>();
		String phy = "";
		//遍历报表字段
		for(CustomQueryDtlBean rstlist:resultList){
			boolean matchFlag = true;
			phy = rstlist.getPhysicName(); 
			//遍历查询到的结果
			for(CustomQueryDtlBean qrylist:queryList){
				//如果查询到的结果中有己经存在的报表字段，就直接取数据库中数据
				if(phy.equals(qrylist.getPhysicName())){
					rstListBean.add(qrylist);
					matchFlag = false;
					break;
				}
			}
			//如果没有保存过到数据库，直接取报表字段
			if(matchFlag){
				rstListBean.add(rstlist);
			}
		}
		return rstListBean;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#queryDisplayRpt(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<CustomQueryDtlBean> queryDisplayRpt(Map<String, String> map) {
		return this.findList("CUST.queryRptDtlSql", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#txSaveRptDtlDate(java.util.Map, java.util.List)
	 */
	public boolean txSaveRptDtlDate(Map<String, String> map, List<CustomQueryDtlBean> list) {
		
		List <Map <String, Object>> listMap = new ArrayList <Map <String, Object>>(); 
		try{
			if(list != null && list.size()>0){ 
				//循环List 对象
				for(CustomQueryDtlBean resultBean:list){
					//Map对象
					Map<String,Object> paraMap = new HashMap<String,Object>();
					//自定义报表配置表主键(自动生成)
					paraMap.put("RPTCOLCONFID",StringUtil.uuid32len());
					//报表主键
					paraMap.put("RPTMODELID",(String)map.get("hidRptPk")); 
					//物理代码
					paraMap.put("PHYSICNAME",resultBean.getPhysicName());
					//逻辑名称
					paraMap.put("LOGICNAME",resultBean.getLogicName());
					//字段类型
					paraMap.put("COL_TYPE",resultBean.getColType());
					//是否查询条件
					paraMap.put("ISCONDITION",resultBean.getIsCondition());
					//是否必输
					paraMap.put("MUSTINPUT",resultBean.getMustInput());
					//匹配模式
					paraMap.put("MATCH_MODEL",resultBean.getMatchModel());
					//组件类型
					paraMap.put("COMPONENTTYPE",resultBean.getCompoentType());
					//组件条件
					paraMap.put("COMPONENTCONDITION",resultBean.getComponentCondition());
					//是否抬头显示
					paraMap.put("HEADDISPLAY",resultBean.getHeadDisplay());
					//排序号
					paraMap.put("COL_INDEX",resultBean.getColIndex());
					//表头显示
					paraMap.put("DISPLAY",resultBean.getDisplay());
					
					//放到List<Map>对象中
					listMap.add(paraMap);
					
					//先删除相关明细数据\单条删除
					map.put("rptColPk",resultBean.getRptColPk());
					this.delete("CUST.doDelRptDtl", map);
				}  
				//批量插入相关数据
				if(listMap!=null && listMap.size()>0)
					this.batch4Update("CUST.doInsertRptDtl", listMap);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#displayRptPageQuery(java.util.Map, int)
	 */
	public IListPage displayRptPageQuery(Map<String, String> params, int pageNo) {  
		return this.pageQuery("CUST.displayPageCustQuery", "CUST.displayPageCustQueryCount", params, pageNo);
	} 
	
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#rptExport(java.util.Map, int)
	 */
	public IListPage rptExport(Map<String, String> params, int pageNo) {
		params.put("displaySql", (String)params.get("displaySql"));
		return this.pageQuery("CUST.rptExportCustQuery", "CUST.rptExportQueryCount", params, pageNo);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#custPageQueryDisplay(java.util.Map)
	 */
	public IListPage custPageQueryDisplay(Map<String,String> params) {
		int pageNo = 1;
		return this.pageQuery("CUST.pageCustQueryDisplay", "CUST.pageCustQueryDisplayCount", params, pageNo);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#txProcessRole(java.util.Map)
	 */
	public boolean txProcessRole(Map<String, String> params) {
		//删除权限
		this.delete("CUST.DELETE_RPT_ROLE_DATE", params);
		
		List <Map <String, Object>> listMap = null;
		Map<String,Object> paraMap = null;
		if(!StringUtil.isEmpty((String)params.get("roleUser"))){
			listMap = new ArrayList <Map <String, Object>>(); 
			String[] arr = ((String)params.get("roleUser")).split(",");
			if(arr!=null&& arr.length>0){
				for(int i=0;i<arr.length;i++){
					paraMap = new HashMap<String,Object>();
					paraMap.put("userPk", arr[i]);
					paraMap.put("rolePk", StringUtil.uuid32len()); 
					paraMap.put("hidRptPk",(String)params.get("hidRptPk"));
					listMap.add(paraMap);
				}
			}
		}
		if(listMap!=null){
			//插入权限(用户)
			this.batch4Update("CUST.insertRptRoleUserCode", listMap);
		}
		
		String roleCode = (String)params.get("roleCode");
		if(!StringUtil.isEmpty(roleCode)){
			roleCode = roleCode.replaceAll(",", "','");
			StringBuffer roleTmp = new StringBuffer();
			roleTmp.append("'");
			roleTmp.append(roleCode);
			roleTmp.append("'");
			params.put("XTJSID", roleTmp.toString());
			//插入权限(角色)
			this.insert("CUST.insertRptRole", params);
		}
		
		String workGroup = (String)params.get("workGroupCode");
		if(!StringUtil.isEmpty(workGroup)){
			workGroup = workGroup.replaceAll(",", "','");
			StringBuffer roleTmp = new StringBuffer();
			roleTmp.append("'");
			roleTmp.append(workGroup);
			roleTmp.append("'");
			//插入权限(工作组)
			this.insert("CUST.insertRptRoleWorkGroup", params);
		} 
		
		return false;
	}

	
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#queryTable(java.util.Map, int)
	 */
	@SuppressWarnings("unchecked")
	public IListPage queryTable(Map<String, String> map, int pageNo) {
		//查询数据库表字段
		StringBuffer sb = null;
		String sql ="";
		try{ 
			String primaryKey = getTablePrimaryKery(map);
			
			List<HashMap<String,String>> tableMap = null;
			//选择表查询
			if(StringUtil.isEmpty(map.get("whereSql"))){
				tableMap = (ArrayList<HashMap<String,String>>)this.findList("CUST.pageQueryTableColOther", map);
				Map<String,String> queryMap = new HashMap<String,String>();
				
				if(tableMap!=null){ 
					sb = new StringBuffer();
					for(HashMap<String,String> querMap:tableMap){
						Iterator iter = querMap.entrySet().iterator(); 
						//遍历报表SQL字段
						while(iter.hasNext()){
							Map.Entry entry = (Map.Entry) iter.next(); 
							sb.append(entry.getValue());
							sb.append(",");
							queryMap.put(StringUtil.nullToSring(entry.getValue()), "PREKEY_PK");
						}
					}
					//加入主键查询为更新操作
					if(StringUtil.isEmpty(queryMap.get(primaryKey))&& !StringUtil.isEmpty(primaryKey)){
						sb.append(primaryKey);
						sb.append(",");
					}
				}
				//如果是选择了表，需要自己拼写SQL 
				if(sb!=null){ 
					sql += "SELECT ";
					sql += (sb.toString()).substring(0,sb.toString().length()-1);
					sql += " FROM ";
					sql += (String)map.get("tableName");
					sql += " WHERE 1=1 ";
					sql += (String)map.get("whereSql");
				}
			}else{
				//校验SQL是否正确
				String whereSql =  map.get("whereSql");
				whereSql = whereSql.toUpperCase();
				if(!StringUtil.isEmpty(whereSql)){ 
					String[] arr = whereSql.split(" ");
					if("SELECT".equals(arr[0])&&whereSql.indexOf(primaryKey)==-1&&whereSql.indexOf("*")==-1){
						whereSql = whereSql.replace("SELECT", "SELECT "+primaryKey+",");
					}

					map.put("rptSql",whereSql);
					this.findList("CUST.checkRptSql", map);
					sql = whereSql;
				}
			}
			//组合成查询条件
			map.put("querySql", sql); 
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return this.pageQuery("CUST.pageQueryTable", "CUST.pageQueryTableCount", map, pageNo);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#queryTableColLength(java.util.HashMap)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HashMap<String, String>> queryTableColLength(
			HashMap<String, String> map) {
		return (ArrayList<HashMap<String,String>>)this.findList("CUST.pageQueryTableCol", map);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#getTablePrimaryKery(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public String getTablePrimaryKery(Map<String, String> map) {
		ArrayList<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>)this.findList("CUST.pageQueryTableColKey", map);
		if(list!=null && list.size()>0){
			HashMap<String,String> rstMap = list.get(0);
			return StringUtil.nullToSring(((Map.Entry)rstMap.entrySet().iterator().next()).getValue());
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#updateTableData(java.util.ArrayList)
	 */
	public boolean updateTableData(ArrayList<Map<String, String>> map) {
		try{
			this.batch4Update("CUST.updateTableData", map);
			return true;
		}catch(Exception e){
			return false;
		} 
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#getTableNameFromWhereSql(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public String getTableNameFromWhereSql(Map<String, String> map) {
		ArrayList<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>)this.findList("CUST.pageQueryTableNameFromWhereSql", map);
		if(list!=null && list.size()>1){
			return "error";
		}else{
			HashMap<String,String> rstMap = (HashMap<String,String>)list.get(0);
			return rstMap==null?"":rstMap.get("TABLE_NAME");
			
		} 
	}

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#enanbleEditTableColumn(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> enanbleEditTableColumn(Map<String, String> map) { 
		HashMap<String, String> rstMap = new HashMap<String, String>();
		ArrayList<HashMap<String,String>> rstList = (ArrayList<HashMap<String,String>>)this.findList("CUST.pageEnAnbleEditTableColumn", map);
		if(rstList!=null && rstList.size()>0){ 
			for(HashMap<String,String> querMap:rstList){
				Iterator iter = querMap.entrySet().iterator(); 
				//遍历报表SQL字段
				while(iter.hasNext()){
					Map.Entry entry = (Map.Entry) iter.next();
					rstMap.put(StringUtil.nullToSring(entry.getValue()), StringUtil.nullToSring(entry.getValue()));
				}
			}
		}
		return rstMap;
	}
	
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.CustQueryService#qryExpData(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Map> qryExpData(Map<String, String> map) {
          return this.findList("CUST.displayPageCustQuery", map);
	}
	
	public List<Map> getRptCol(String RPTMODELID) {
        return this.findList("CUST.getRptCol", RPTMODELID);
	}
}

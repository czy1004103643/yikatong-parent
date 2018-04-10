package com.meatball.api.ykt.dao;

import org.apache.ibatis.annotations.Param;

import com.meatball.api.ykt.model.Account;
/**
 * @Title: AccountMapper.java 
 * @Package com.meatball.api.ykt.dao 
 * @Description: TODO(账户信息dao) 
 * @author jw  
 * @date 2018年3月16日 上午11:04:44 
 * @version V1.0
 */
public interface AccountMapper {
	/**
	 * @Title: deleteByPrimaryKey 
	 * @Description: TODO(根据id删除信息) 
	 * @param bId
	 * @return
	 * @return int    返回类型
	 */
    int deleteByPrimaryKey(Long bId);

    /**
     * @Title: insert 
     * @Description: TODO(新增信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insert(Account record);

    /**
     * @Title: insertSelective 
     * @Description: TODO(新增指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insertSelective(Account record);

    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return Account    返回类型
     */
    Account selectByPrimaryKey(Long bId);

    /**
     * @Title: updateByPrimaryKeySelective 
     * @Description: TODO(根据id修改指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * @Title: updateByPrimaryKey 
     * @Description: TODO(根据id修改信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKey(Account record);
    
    /**
     * @Title: selectAccountByIdcard 
     * @Description: TODO(根据身份证查询账户信息) 
     * @param vIdcard
     * @return
     * @return Account    返回类型
     */
    Account selectAccountByIdcard(@Param("vIdcard") String vIdcard);
    
    /**
     * @Title: selectAccountByPatientidcard 
     * @Description: TODO(根据就诊卡查询账户信息) 
     * @param vPatientidcard
     * @return
     * @return Account    返回类型
     */
    Account selectAccountByPatientidcard(@Param("vPatientidcard")String vPatientidcard);
    
    /**
     * @Title: selectAccountBySincard 
     * @Description: TODO(根据社保卡查询账户信息) 
     * @param vSincard
     * @return
     * @return Account    返回类型
     */
    Account selectAccountBySincard(String vSincard);
    
    /**
     * @Title: selectAccountByHealthcard 
     * @Description: TODO(根据居民健康卡查询账户信息) 
     * @param vHealthcard
     * @return
     * @return Account    返回类型
     */
    Account selectAccountByHealthcard(String vHealthcard);
    
}
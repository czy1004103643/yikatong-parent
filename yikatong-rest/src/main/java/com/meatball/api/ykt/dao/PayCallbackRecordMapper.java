package com.meatball.api.ykt.dao;

import com.meatball.api.ykt.model.PayCallbackRecord;
/**
 * @Title: PayCallbackRecordMapper.java 
 * @Package com.meatball.api.ykt.dao 
 * @Description: TODO(支付平台回调记录dao) 
 * @author jw  
 * @date 2018年3月16日 下午4:46:42 
 * @version V1.0
 */
public interface PayCallbackRecordMapper {
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
    int insert(PayCallbackRecord record);

    /**
     * @Title: insertSelective 
     * @Description: TODO(新增指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insertSelective(PayCallbackRecord record);

    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return PayCallbackRecord    返回类型
     */
    PayCallbackRecord selectByPrimaryKey(Long bId);

    /**
     * @Title: updateByPrimaryKeySelective 
     * @Description: TODO(修改指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKeySelective(PayCallbackRecord record);

    /**
     * @Title: updateByPrimaryKey 
     * @Description: TODO(修改信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKey(PayCallbackRecord record);
}
package com.meatball.api.ykt.dao;

import java.util.List;

import com.meatball.api.ykt.model.RefundRecord;
/**
 * @Title: RefundRecordMapper.java 
 * @Package com.meatball.api.ykt.dao 
 * @Description: TODO(退款记录dao) 
 * @author jw  
 * @date 2018年3月16日 下午4:47:01 
 * @version V1.0
 */
public interface RefundRecordMapper {
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
    int insert(RefundRecord record);

    /**
     * @Title: insertSelective 
     * @Description: TODO(新增指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insertSelective(RefundRecord record);

    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return RefundRecord    返回类型
     */
    RefundRecord selectByPrimaryKey(Long bId);

    /**
     * @Title: updateByPrimaryKeySelective 
     * @Description: TODO(修改指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKeySelective(RefundRecord record);

    /**
     * @Title: updateByPrimaryKey 
     * @Description: TODO(修改信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKey(RefundRecord record);
    
    /**
     * @Title: selectBybAccountid 
     * @Description: TODO(退款记录查询) 
     * @param record
     * @return
     * @return long    返回类型
     */
    List<RefundRecord> selectBybAccountid(Long bAccountid);
}
package com.meatball.api.ykt.dao;

import java.util.List;

import com.meatball.api.ykt.model.ComsumeRecord;
/**
 * @Title: ComsumeRecordMapper.java 
 * @Package com.meatball.api.ykt.dao 
 * @Description: TODO(消费记录dao) 
 * @author jw  
 * @date 2018年3月16日 下午4:46:11 
 * @version V1.0
 */
public interface ComsumeRecordMapper {
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
    int insert(ComsumeRecord record);

    /**
     * @Title: insertSelective 
     * @Description: TODO(新增指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insertSelective(ComsumeRecord record);

    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return ComsumeRecord    返回类型
     */
    ComsumeRecord selectByPrimaryKey(Long bId);

    /**
     * @Title: updateByPrimaryKeySelective 
     * @Description: TODO(修改指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKeySelective(ComsumeRecord record);

    /**
     * @Title: updateByPrimaryKey 
     * @Description: TODO(修改信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKey(ComsumeRecord record);

    /**
     * @Title: selectSuccessComsumeRecord 
     * @Description: TODO(根据订单号查询消费成功的消费记录) 
     * @param bId
     * @return
     * @return ComsumeRecord    返回类型
     */
	ComsumeRecord selectSuccessComsumeRecord(long bId);
	
	/**
     * @Title: selectSuccessComsumeRecord 
     * @Description: TODO(根据订单号查询消费成功的消费记录) 
     * @param bId
     * @return
     * @return ComsumeRecord    返回类型
     */
	List<ComsumeRecord> selectBybAccountid(long bAccountid);
}
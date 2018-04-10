package com.meatball.api.ykt.dao;

import java.util.List;

import com.meatball.api.ykt.model.RechargeRecord;
/**
 * @Title: RechargeRecordMapper.java 
 * @Package com.meatball.api.ykt.dao 
 * @Description: TODO(充值记录dao) 
 * @author jw  
 * @date 2018年3月16日 下午4:01:02 
 * @version V1.0
 */
public interface RechargeRecordMapper {
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
    int insert(RechargeRecord record);

    /**
     * @Title: insertSelective 
     * @Description: TODO(新增指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insertSelective(RechargeRecord record);

    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return RechargeRecord    返回类型
     */
    RechargeRecord selectByPrimaryKey(Long bId);

    /**
     * @Title: updateByPrimaryKeySelective 
     * @Description: TODO(修改指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKeySelective(RechargeRecord record);

    /**
     * @Title: updateByPrimaryKey 
     * @Description: TODO(修改信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKey(RechargeRecord record);
    
    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return RechargeRecord    返回类型
     */
    List<RechargeRecord> selectBAccountid(Long bAccountid);

    /**
     * @Title: selectSuccessRechargeRecord 
     * @Description: TODO(根据订单号查询充值成功的充值记录) 
     * @param bId
     * @return
     * @return RechargeRecord    返回类型
     */
	RechargeRecord selectSuccessRechargeRecord(long bId);
}
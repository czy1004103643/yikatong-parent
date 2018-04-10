package com.meatball.api.ykt.dao;

import com.meatball.api.ykt.model.OperationLog;
/**
 * @Title: OperationLogMapper.java 
 * @Package com.meatball.api.ykt.dao 
 * @Description: TODO(操作日志dao) 
 * @author jw  
 * @date 2018年3月16日 下午4:46:27 
 * @version V1.0
 */
public interface OperationLogMapper {
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
    int insert(OperationLog record);

    /**
     * @Title: insertSelective 
     * @Description: TODO(新增指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int insertSelective(OperationLog record);

    /**
     * @Title: selectByPrimaryKey 
     * @Description: TODO(根据id查询信息) 
     * @param bId
     * @return
     * @return OperationLog    返回类型
     */
    OperationLog selectByPrimaryKey(Long bId);

    /**
     * @Title: updateByPrimaryKeySelective 
     * @Description: TODO(修改指定字段信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKeySelective(OperationLog record);

    /**
     * @Title: updateByPrimaryKey 
     * @Description: TODO(修改信息) 
     * @param record
     * @return
     * @return int    返回类型
     */
    int updateByPrimaryKey(OperationLog record);
}
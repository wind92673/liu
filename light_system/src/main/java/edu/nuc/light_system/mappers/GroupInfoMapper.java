package edu.nuc.light_system.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:分组表Mapper
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface GroupInfoMapper<T, P> extends BaseMapper {
	/**
	 * 根据GroupId查询
	 */
	T selectByGroupId(@Param("groupId") String groupId);

	/**
	 * 根据GroupId更新
	 */
	Integer updateByGroupId(@Param("bean") T t, @Param("groupId") String groupId);

	/**
	 * 根据GroupId删除
	 */
	Integer deleteByGroupId(@Param("groupId") String groupId);


}